package nz.ac.vuw.ecs.swen225.a3.renderer;

import nz.ac.vuw.ecs.swen225.a3.application.GUI;
import nz.ac.vuw.ecs.swen225.a3.application.util.GUIUtil;
import nz.ac.vuw.ecs.swen225.a3.maze.Board;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.Actor;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.Player;
import nz.ac.vuw.ecs.swen225.a3.maze.tiles.HintTile;
import nz.ac.vuw.ecs.swen225.a3.maze.tiles.Tile;
import nz.ac.vuw.ecs.swen225.a3.maze.treasures.Treasure;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Controls the rendering of the game
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 */
public class ChapsCanvas extends JPanel {
    public
    static final int VIEW_DISTANCE = 7;
    private static int TILE_WIDTH = 64;
    private static int TILE_HEIGHT = 64;

    private GUI gui;    //Just in case we need to call anything on the controlling class
    private Board board;

    public ChapsCanvas(GUI gui, Board board) {
        super(new GridBagLayout());
        this.gui = gui;
        this.board = board;
        this.setBackground(Color.RED);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = e.getComponent().getWidth();
                int height = getHeight();

                //Recalculate the tile sizes
                TILE_WIDTH = width / VIEW_DISTANCE;
                TILE_HEIGHT = height / VIEW_DISTANCE;
                repaint();
            }
        });
    }

    /**
     * Paints the board onto the canvas
     *
     * @param g The graphics object to paint with
     * @author Tim - Creator
     */
    @Override
    public void paint(Graphics g) {
        Tile[][] tiles = board.getTileLayer();
        Treasure[][] treasures = board.getTreasureLayer();
        Actor[][] actors = board.getActorLayer();

        Player rick = board.getPlayer();
        Position pPosition = rick.getPosition();
        int viewRadius = VIEW_DISTANCE / 2;   //Calculate the radius around the player we should render, by default this should be 4 (9/2 in integer division)

        //Calculate the minimum and maximum x,y values that we need to render between
        int xMin = Math.max(0, pPosition.getX() - viewRadius);
        int yMin = Math.max(0, pPosition.getY() - viewRadius);
        int xMax = Math.min(Board.BOARD_WIDTH - 1, pPosition.getX() + viewRadius);
        int yMax = Math.min(Board.BOARD_HEIGHT - 1, pPosition.getY() + viewRadius);

        //If we're near the left side or top side
        if (xMin == 0) xMax += Math.abs((xMax - xMin) - VIEW_DISTANCE);
        if (yMin == 0) yMax += Math.abs((yMax - yMin) - VIEW_DISTANCE);

        //If we're near the right side or bottom side
        if (xMax == Board.BOARD_WIDTH - 1) xMin -= Math.abs((xMax - xMin) - VIEW_DISTANCE) - 1;
        if (yMax == Board.BOARD_WIDTH - 1) yMin -= Math.abs((yMax - yMin) - VIEW_DISTANCE) - 1;

        //Render everything
        for (int y = yMin; y <= yMax; y++) {
            for (int x = xMin; x <= xMax; x++) {
                double actualX = (x - xMin) * TILE_WIDTH;
                double actualY = (y - yMin) * TILE_HEIGHT;
                tiles[y][x].render(g, (int) actualX, (int) actualY);
                treasures[y][x].render(g, (int) actualX, (int) actualY);
                actors[y][x].render(g, (int) actualX, (int) actualY);
            }
        }

        //Render rick
        int playerX = ((pPosition.getX() - xMin) * TILE_WIDTH);
        int playerY = ((pPosition.getY() - yMin) * TILE_HEIGHT);
        rick.render(g, playerX, playerY);

        if (tiles[pPosition.getY()][pPosition.getX()] instanceof HintTile) {
            g.setColor(Color.RED);
            g.setFont(GUIUtil.FONTS.get("computer").deriveFont(20f));
            g.drawString(board.getHintText(), playerX + TILE_WIDTH, playerY);
        }

        if(gui != null) {
            gui.getInfoPanel().updatePanel();
        }
    }

    /**
     * Gets the tile width of the game
     *
     * @return The tile width in pixels
     * @author Tim - Creator
     */
    public static int getTileWidth() {
        return TILE_WIDTH;
    }

    /**
     * Gets the tile height of the game
     *
     * @return The tile height in pixels
     * @author Tim - Creator
     */
    public static int getTileHeight() {
        return TILE_HEIGHT;
    }
}
