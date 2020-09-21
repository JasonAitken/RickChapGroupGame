package nz.ac.vuw.ecs.swen225.a3.renderer.panel;

import nz.ac.vuw.ecs.swen225.a3.application.GUI;
import nz.ac.vuw.ecs.swen225.a3.application.util.GUIUtil;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.Player;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TreasureType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains all information about the player and game, for example it contains and displays the current level
 * the player is on, how much time is left on a level, how many chips are left to collect, and what is in the players
 * inventory.
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 */
public class InfoPanel extends JPanel {

    public static final int WIDTH = 300;

    //Scalars for resizeable
    private static final float ICON_WIDTH_SCALAR = 0.1666667f;
    private static final float ICON_HEIGHT_SCALAR = 0.347222f;
    private static final float FONT_SIZE_SCALAR = 0.0666667f;

    //Load all the icon assets
    private static final Map<Type, ImageIcon> INVENTORY_ICONS = new HashMap<>();

    static {
        Arrays.stream(TreasureType.values()).forEach((t) -> {
            if (t == TreasureType.FILLER) return;
            Image image = GUIUtil.ASSETS.get(t);
            INVENTORY_ICONS.put(t, new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        });
    }

    private JPanel levelPanel;
    private JPanel timePanel;
    private JLabel timeCounter;
    private JPanel chipPanel;
    private JPanel inventoryPanel;
    private JPanel replayPanel;

    private JLabel[] inventorySlots;

    private GUI gui;

    /**
     * Initializes an InfoPanel to be shown on the main GUI
     *
     * @param gui A reference to main GUI in-case we need to call anything from there
     */
    public InfoPanel(GUI gui) {
        super(new GridBagLayout());
        this.gui = gui;
        initializePanels();
    }

    /**
     * Sets up the level panel
     *
     * @author Tim - Creator
     */
    private void setupLevelPanel() {
        JLabel levelLabel = new JLabel(String.valueOf(gui.getBoard().getLevelNumber()));
        levelLabel.setFont(GUIUtil.FONTS.get("computer").deriveFont(20f));

        levelPanel.add(levelLabel, GUIUtil.makeConstraints(0, 1, 1, 1));
    }

    /**
     * Initializes the main panels that make up the info panel
     *
     * @author Tim - Creator
     */
    private void initializePanels() {
        this.levelPanel = GUIUtil.makeGenericInfoPanel("Level");
        this.timePanel = GUIUtil.makeGenericInfoPanel("Time");
        this.chipPanel = GUIUtil.makeGenericInfoPanel("Chips Left");
        this.inventoryPanel = GUIUtil.makeGenericInfoPanel("Inventory");

        this.setupLevelPanel();
        this.setupTimePanel();
        this.setupChipPanel();
        this.setupInventoryPanel();

        this.add(levelPanel, GUIUtil.makeConstraints(0, 0, 1, 1));
        this.add(timePanel, GUIUtil.makeConstraints(0, 1, 1, 1));
        this.add(chipPanel, GUIUtil.makeConstraints(0, 2, 1, 1));
        this.add(inventoryPanel, GUIUtil.makeConstraints(0, 3, 1, 1));

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = getWidth();
                int height = (int) ((double) getHeight() / 4f);

                //Recalculate font size
                Font font = GUIUtil.FONTS.get("computer").deriveFont(FONT_SIZE_SCALAR * width);

                levelPanel.getComponent(0).setFont(font);
                timePanel.getComponent(0).setFont(font);
                chipPanel.getComponent(0).setFont(font);
                inventoryPanel.getComponent(0).setFont(font);

                //Reassign new sizes, -1 to height because for some reason if I don't it doesn't work very well
                //the -1 is causing issues when swaping levels, this is currently avoidable by just resizing the frame
                //but removing the -1 causes the frame to freeze up when scaling in the y axis
                levelPanel.setPreferredSize(new Dimension(width, height));
                timePanel.setPreferredSize(new Dimension(width, height));
                chipPanel.setPreferredSize(new Dimension(width, height));
                inventoryPanel.setPreferredSize(new Dimension(width, height));

                //Resize the inventory icons
                Arrays.stream(TreasureType.values()).filter(type -> type.getSlot() != -1).forEach(type ->
                        inventorySlots[type.getSlot()].setIcon(new ImageIcon(GUIUtil.ASSETS.get(type).
                                getScaledInstance((int) (ICON_WIDTH_SCALAR * width), (int) (ICON_HEIGHT_SCALAR * height), Image.SCALE_SMOOTH))));

                revalidate();
            }
        });
    }

    /**
     * Initializes the inventory panel
     *
     * @author Tim - Creator
     */
    private void setupInventoryPanel() {
        inventorySlots = new JLabel[Player.INVENTORY_SIZE];

        JPanel inventoryContent = new JPanel(new GridBagLayout());

        inventoryPanel.add(inventoryContent, GUIUtil.makeConstraints(0, 2, 1, 1, GridBagConstraints.CENTER));

        int divisor = Player.INVENTORY_SIZE / 2;

        //Set up the labels
        Arrays.stream(TreasureType.values()).filter(type -> type.getSlot() != -1).forEach(type -> {
            JLabel inventorySlot = new JLabel("0");
            inventorySlot.setIcon(INVENTORY_ICONS.get(type));
            inventorySlots[type.getSlot()] = inventorySlot;
            inventoryContent.add(inventorySlot, GUIUtil.makeConstraints(type.getSlot() % divisor, type.getSlot() / divisor, 1, 1));
        });
    }

    /**
     * Initializes the chip panel
     *
     * @author Tim - Creator
     */
    private void setupChipPanel() {
        JLabel chipCounter = new JLabel("" + gui.getBoard().getChipsLeft());
        chipCounter.setFont(GUIUtil.FONTS.get("computer").deriveFont(20f));

        chipPanel.add(chipCounter, GUIUtil.makeConstraints(0, 1, 1, 1));
    }

    /**
     * Sets up the time panel
     *
     * @author Tim - Creator
     */
    private void setupTimePanel() {
        timeCounter = new JLabel(String.valueOf(gui.getTime()));
        timeCounter.setFont(GUIUtil.FONTS.get("computer").deriveFont(20f));

        timePanel.add(timeCounter, GUIUtil.makeConstraints(0, 1, 1, 1));
    }

    /**
     * Sets up the time panel
     *
     * @author Tim - Creator
     */
    public void updateTime(int time) {
        timeCounter.setText(String.valueOf(time));
    }

    /**
     * Updates the entire panel, this is called every game tick
     *
     * @author Tim - Creator
     */
    public void updatePanel() {
        //Update the inventory
        Player player = gui.getBoard().getPlayer();
        for (int i = 0; i < Player.INVENTORY_SIZE; i++) {
            if (inventorySlots[i] != null) {
                inventorySlots[i].setText("" + player.getInventory()[i]);
            }
        }

        //Update the chips counter
        ((JLabel) (chipPanel.getComponent(1))).setText(String.valueOf(gui.getBoard().getChipsLeft()));
    }

}
