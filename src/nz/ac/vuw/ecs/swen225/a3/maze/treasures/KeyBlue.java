package nz.ac.vuw.ecs.swen225.a3.maze.treasures;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TreasureType;

/**
 * Blue keys are destroyed when a block or monster stands on them.
 *
 * @author Fynn - Creator
 * @author Max - Contributer
 */
public class KeyBlue extends Key {

    public KeyBlue(Position pos) {
        super(pos, TreasureType.KEY_BLUE);
    }

}
