package nz.ac.vuw.ecs.swen225.a3.maze.treasures;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TreasureType;

/**
 * boots treasure.
 *
 * @author Jason - Creator
 */
public abstract class Boots extends Treasure {

    public Boots(Position location, TreasureType type) {
        super(location, type);
    }

}
