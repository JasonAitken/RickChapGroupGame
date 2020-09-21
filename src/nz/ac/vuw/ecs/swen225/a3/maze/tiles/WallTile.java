package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * Represents a wall in the game, no actor can pass this tile.
 *
 * @author Tim - Creator
 */
public class WallTile extends Tile {
    public WallTile(Position pos) {
        super(pos, TileType.WALL);
    }
}
