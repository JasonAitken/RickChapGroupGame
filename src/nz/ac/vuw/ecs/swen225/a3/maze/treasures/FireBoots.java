package nz.ac.vuw.ecs.swen225.a3.maze.treasures;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TreasureType;

/**
 * Fire boots stop you from dying in fire.
 *
 * @author Max - Creator
 */
public class FireBoots extends Boots {
    public FireBoots(Position location) {
        super(location, TreasureType.FIRE_BOOTS);
    }
}
