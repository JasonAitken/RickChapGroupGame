package nz.ac.vuw.ecs.swen225.a3.maze.treasures;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TreasureType;

/**
 * Red keys open red locked doors.
 *
 * @author Fynn - Creator
 * @author Max - Contributor
 * @author Mike - Contributor
 */
public class KeyRed extends Key {

    public KeyRed(Position pos) {
        super(pos, TreasureType.KEY_RED);
    }

}