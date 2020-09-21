package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TreasureType;

/**
 * Opens with a yellow key.
 *
 * @author Jason - Creator
 * @author Mike - Contributor
 */
public class YellowDoorTile extends DoorTile {

    public YellowDoorTile(Position pos) {
        super(pos, TileType.YELLOWDOOR, TreasureType.KEY_YELLOW);
    }

}
