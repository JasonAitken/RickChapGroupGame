package nz.ac.vuw.ecs.swen225.a3.tests;

import nz.ac.vuw.ecs.swen225.a3.maze.Board;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Direction;
import nz.ac.vuw.ecs.swen225.a3.persistence.LevelFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class MazeTests {
    //TODO: tests for each of the exception types.
    //TODO: tests for each of the listeners

    private static final String LEVEL_ONE = "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Wl|Wl|Wl|Wl|Fr|Wl|Wl|Wl|Wl|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Wl|Wl|Wl|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Wl|Ex|Wl|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Wl|Wl|Wl|Wl|GD|Wl|Sk|Wl|GD|Wl|Wl|Wl|Wl|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|BD|Fr|Fr|Fr|Fr|Fr|RD|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Wl|Fr|Fr|Pl|Fr|Fr|Wl|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Wl|Wl|Wl|Wl|Fr|Fr|Fr|Fr|Fr|Wl|Wl|Wl|Wl|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|RD|Fr|Fr|Fr|Fr|Fr|BD|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Wl|Wl|Wl|Wl|Wl|YD|Wl|YD|Wl|Wl|Wl|Wl|Wl|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Wl|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Wl|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Wl|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Wl|Wl|Wl|Wl|Wl|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
            "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n";

    @Test
    public void test01() {
        //Basic movement test
        Board board = LevelFactory.readFile(1);

        assertEquals("Map not equal", LEVEL_ONE, board.tilesToString());
    }

    /**
     * Simple test for moving
     *
     * @author Tim - Creator
     */
    @Test
    public void test02() {
        Board board = LevelFactory.readFile(1);

        String answer = "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Wl|Wl|Wl|Wl|Fr|Wl|Wl|Wl|Wl|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Wl|Wl|Wl|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Wl|Ex|Wl|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Wl|Wl|Wl|Wl|GD|Wl|Sk|Wl|GD|Wl|Wl|Wl|Wl|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|BD|Fr|Fr|Pl|Fr|Fr|RD|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Wl|Fr|Fr|Hi|Fr|Fr|Wl|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Wl|Wl|Wl|Wl|Fr|Fr|Fr|Fr|Fr|Wl|Wl|Wl|Wl|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|RD|Fr|Fr|Fr|Fr|Fr|BD|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Wl|Wl|Wl|Wl|Wl|YD|Wl|YD|Wl|Wl|Wl|Wl|Wl|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Wl|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Wl|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Wl|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Wl|Wl|Wl|Wl|Wl|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n";

        board.getPlayer().setMove(Direction.UP);
        board.processTick();

        assertEquals("Map not equal", answer, board.tilesToString());
    }

    /**
     * Simple test for actor movement
     *
     * @author Tim - Creator
     */
    @Test
    public void test03() {
        Board board = LevelFactory.readFile(2);

        String answer = "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Gl|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Ta|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Pl|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n";

        board.processTick();

        assertEquals("Map not equal", answer, board.actorsToString());
    }

    /**
     * Testing invalid actor movement
     *
     * @author Tim - Creator
     */
    @Test
    public void test04() {
        Board board = LevelFactory.readFile(1);

        String answer = "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Wl|Wl|Wl|Wl|Fr|Wl|Wl|Wl|Wl|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Wl|Wl|Wl|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Wl|Ex|Wl|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Wl|Wl|Wl|Wl|GD|Wl|Sk|Wl|GD|Wl|Wl|Wl|Wl|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|BD|Fr|Fr|Fr|Fr|Fr|RD|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Wl|Fr|Fr|Hi|Fr|Pl|Wl|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Wl|Wl|Wl|Wl|Fr|Fr|Fr|Fr|Fr|Wl|Wl|Wl|Wl|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Fr|RD|Fr|Fr|Fr|Fr|Fr|BD|Fr|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Wl|Wl|Wl|Wl|Wl|YD|Wl|YD|Wl|Wl|Wl|Wl|Wl|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Wl|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Wl|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Fr|Fr|Wl|Fr|Fr|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Wl|Wl|Wl|Wl|Wl|Wl|Wl|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n" +
                "Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|Fr|\n";

        board.getPlayer().setMove(Direction.RIGHT);
        board.processTick();
        board.getPlayer().setMove(Direction.RIGHT);
        board.processTick();
        board.getPlayer().setMove(Direction.RIGHT);
        board.processTick();

        assertEquals("Map not equal", answer, board.tilesToString());
    }

    /**
     * Testing actor movement
     *
     * @author Tim - Creator
     */
    @Test
    public void test05() {

        Board board = LevelFactory.readFile(2);

        String answer = "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Gl|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Ta|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Pl|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n";

        board.processTick();
        board.processTick();
        board.processTick();
        board.processTick();
        board.processTick();
        board.processTick();
        board.processTick();

        assertEquals("Map not equal", answer, board.actorsToString());
    }

    /**
     * Testing moving outside of bounds
     *
     * @author Tim - Creator
     */
    @Test
    public void test06() {
        Board board = LevelFactory.readFile(3);

        board.processTick();
        board.processTick();
        board.processTick();
        board.processTick();
        board.processTick();
        board.processTick();

        String answer = "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Pl|Fi|Fi|Fi|Fi|Fi|Fi|Ta|Fi|Gl|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Te|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Gl|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n" +
                "Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|Fi|\n";

        assertEquals("Map not equal", answer, board.actorsToString());
    }


    /**
     * Picking chips up
     *
     * @author Tim - Creator
     */
    @Test
    public void test07(){
        Board board = LevelFactory.readFile(1);

        board.getPlayer().setMove(Direction.RIGHT);
        board.processTick();
        board.getPlayer().setMove(Direction.RIGHT);
        board.processTick();
        board.getPlayer().setMove(Direction.DOWN);
        board.processTick();

//        assertEquals("Map not equal", );

        System.out.println(board.treasuresToString());

    }
}