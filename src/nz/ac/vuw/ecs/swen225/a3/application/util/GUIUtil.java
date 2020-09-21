package nz.ac.vuw.ecs.swen225.a3.application.util;

import nz.ac.vuw.ecs.swen225.a3.application.GUI;
import nz.ac.vuw.ecs.swen225.a3.application.exceptions.AssetNotFoundException;
import nz.ac.vuw.ecs.swen225.a3.maze.util.ActorType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TreasureType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Type;
import nz.ac.vuw.ecs.swen225.a3.renderer.panel.InfoPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Basic GUI util class for creating commonly used objects
 *
 * @author Tim
 * @author Mike - Contributor
 * @author Fynn - Debugger
 */
public class GUIUtil {

    public static final Map<Type, Image> ASSETS;
    public static final Map<String, Font> FONTS;

    //Loads all of the assets and fonts into their maps
    static {
        //Create temporary map for assets
        Map<Type, Image> temporaryAssets = new HashMap<>();
        File file = new File(".");

        loadImages(TileType.class, temporaryAssets);
        loadImages(TreasureType.class, temporaryAssets);
        loadImages(ActorType.class, temporaryAssets);

        //Assigns assets to an unmodifiable version of it
        ASSETS = Collections.unmodifiableMap(temporaryAssets);


        //Create temporary map for fonts
        Map<String, Font> temporaryFonts = new HashMap<>();

        //Load the font into the map
        try {
            temporaryFonts.put("computer", Font.createFont(Font.PLAIN, new File("assets/fonts/retro_computer_personal_use.ttf")));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        //Assign fonts to an unmodifiable version of it
        FONTS = Collections.unmodifiableMap(temporaryFonts);
    }

    private static final Font LABEL_FONT = FONTS.get("computer").deriveFont(25f);
    private static final Dimension LABEL_DIMENSION = new Dimension(InfoPanel.WIDTH, GUI.CANVAS_SIZE / 4);

    /**
     * Loads all of the representing images from the given type into the given map
     *
     * @param type     The type of objects to load images for
     * @param mappings The map to add the images to
     * @param <E>      The type to load images for
     * @author Tim - Creator
     */
    private static <E extends Enum<E> & Type> void loadImages(Class<E> type, Map<Type, Image> mappings) {
        Arrays.stream(type.getEnumConstants()).filter((t) -> !t.getName().equals("filler")).forEach((t) -> {
            try {
                mappings.put(t, ImageIO.read(new
                        File(t.getFilePath())));
            } catch (IOException e) {
                throw new AssetNotFoundException("Asset for " + t + " not found.");
            }
        });
    }

    /**
     * Creates a GridBagConstraint with the provided parameters
     *
     * @param x      The x position
     * @param y      The y position
     * @param width  Width of the cell
     * @param height Height of the cell
     * @param anchor Where to anchor the cell
     * @return The GridBagConstraint
     * @author Tim
     */
    public static GridBagConstraints makeConstraints(int x, int y, int width, int height, int anchor) {
        GridBagConstraints gb = new GridBagConstraints();
        gb.gridy = y;
        gb.gridx = x;
        gb.gridwidth = width;
        gb.gridheight = height;
        gb.anchor = anchor;
        return gb;
    }

    /**
     * {@link #makeConstraints(int, int, int, int, int)}
     */
    public static GridBagConstraints makeConstraints(int x, int y, int width, int height) {
        return makeConstraints(x, y, width, height, GridBagConstraints.CENTER);
    }

    /**
     * Creates a rather simple JPanel used in InfoPanel
     *
     * @param labelText The text to make the panels label
     * @return The panel itself
     * @author Tim - Creator
     */
    public static JPanel makeGenericInfoPanel(String labelText) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(LABEL_DIMENSION);
        JLabel label = new JLabel(labelText);
        label.setFont(LABEL_FONT);
        panel.add(label, GUIUtil.makeConstraints(0, 0, 1, 1));

        return panel;
    }

    /**
     * Initialises all of the static variables
     *
     * @author Tim - Creator
     */
    public static void initialize() { }
}
