package nz.ac.vuw.ecs.swen225.a3.maze.treasures;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TreasureType;

/**
 * @author Mike - Contributor
 */
public class Filler extends Treasure {

    public Filler(Position location) {
        super(location, TreasureType.FILLER);
    }

    public Filler() {
        super(TreasureType.FILLER);
    }
}
