package nz.ac.vuw.ecs.swen225.a3.maze.treasures;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TreasureType;

/**
 * Yellow keys act as a wall to blocks and monsters when on the ground.
 *
 * @author Fynn - Creator
 * @author Max - Contributor
 * @author Mike - Contributor
 */
public class KeyYellow extends Key {

    public KeyYellow(Position pos) {
        super(pos, TreasureType.KEY_YELLOW);
    }

}