package nz.ac.vuw.ecs.swen225.a3.maze.treasures;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TreasureType;

/**
 * A Key is a treasure that is used to open a door
 *
 * @author Max - Creator
 */
public abstract class Key extends Treasure {
    //Constants
    public static final boolean CONSUMED = true;
    public static final boolean REMAINS = false;

    /**
     * Constructor for generic Treasure, takes and stores name of item and type of Treasure,
     * this is subject to change.
     *
     * @param location The location of the Key
     * @param type     The KeyType
     * @author Max - Creator
     */
    public Key(Position location, TreasureType type) {
        super(location, type);
    }

}
