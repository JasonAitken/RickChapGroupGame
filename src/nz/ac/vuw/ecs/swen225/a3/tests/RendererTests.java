package nz.ac.vuw.ecs.swen225.a3.tests;

import nz.ac.vuw.ecs.swen225.a3.application.GUI;
import nz.ac.vuw.ecs.swen225.a3.maze.Board;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.Actor;
import nz.ac.vuw.ecs.swen225.a3.maze.tiles.FreeTile;
import nz.ac.vuw.ecs.swen225.a3.maze.tiles.Tile;
import nz.ac.vuw.ecs.swen225.a3.maze.treasures.Filler;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.FireBall;
import nz.ac.vuw.ecs.swen225.a3.maze.treasures.Treasure;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.renderer.ChapsCanvas;
import org.junit.Test;

import javax.swing.*;
import java.util.Arrays;

/**
 * Tests that perform checks on the canvas in the renderer package.
 *
 * @author Max - Creator
 */
public class RendererTests {

    /**
     * Checks that chaps canvas has correct variables.
     *
     * @author Max - Creator
     */
    @Test
    public void test1() {
        GUI gui = new GUI();
        Tile[][] tiles = new Tile[30][30];
        Treasure[][] treasures = new Treasure[30][30];
        Actor[][] actors = new Actor[30][30];

        Arrays.stream(tiles).forEach(t -> Arrays.fill(t, new FreeTile(new Position(0, 0))));

        Arrays.stream(treasures).forEach(t -> Arrays.fill(t, new Filler(new Position(0, 0))));

        Arrays.stream(actors).forEach(t -> Arrays.fill(t, new FireBall(new Position(0, 0))));

        Board board = new Board(tiles, treasures, actors, null, 0, null);
        ChapsCanvas chapsCanvas = new ChapsCanvas(gui, board);

        JFrame holder = new JFrame();
        holder.add(chapsCanvas);
        holder.repaint();

    }
}
