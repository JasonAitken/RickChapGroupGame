package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TreasureType;

/**
 * Opens with the green key.
 *
 * @author Jason - Creator
 * @author Mike - Contributor
 */
public class GreenDoorTile extends DoorTile {

    public GreenDoorTile(Position pos) {
        super(pos, TileType.GREENDOOR, TreasureType.KEY_GREEN);
    }

}
