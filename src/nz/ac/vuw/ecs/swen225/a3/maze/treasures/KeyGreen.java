package nz.ac.vuw.ecs.swen225.a3.maze.treasures;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TreasureType;

/**
 * Green keys are not consumed on use.
 *
 * @author Fynn - Creator
 * @author Max - Contributer
 */
public class KeyGreen extends Key {

    public KeyGreen(Position pos) {
        super(pos, TreasureType.KEY_GREEN);
    }

}