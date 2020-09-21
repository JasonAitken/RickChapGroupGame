package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * It's a wall, but looks like a free tile.
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 */
public class InvisibleWallTile extends Tile {
    public InvisibleWallTile(Position pos) {
        super(pos, TileType.INVISIBLE_WALL);
    }
}
