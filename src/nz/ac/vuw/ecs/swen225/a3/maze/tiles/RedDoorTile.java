package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TreasureType;

/**
 * Opens with a red key.
 *
 * @author Jason - Creator
 * @author Mike - Contributor
 */
public class RedDoorTile extends DoorTile {

    public RedDoorTile(Position pos) {
        super(pos, TileType.REDDOOR, TreasureType.KEY_RED);
    }

}

