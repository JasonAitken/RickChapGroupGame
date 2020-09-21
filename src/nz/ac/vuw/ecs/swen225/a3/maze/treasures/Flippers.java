package nz.ac.vuw.ecs.swen225.a3.maze.treasures;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TreasureType;

/**
 * Flippers stop you dying in water.
 *
 * @author Max - Creator
 */
public class Flippers extends Boots {
    public Flippers(Position pos) {
        super(pos, TreasureType.FLIPPERS);
    }
}
