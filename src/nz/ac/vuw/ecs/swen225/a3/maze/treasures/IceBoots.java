package nz.ac.vuw.ecs.swen225.a3.maze.treasures;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TreasureType;

/**
 * Lets the player walk on ice.
 * @author Max - Creator
 */
public class IceBoots extends Boots {
    public IceBoots(Position position) {
        super(position, TreasureType.ICE_BOOTS);
    }
}
