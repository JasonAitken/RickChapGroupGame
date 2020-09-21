package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * Acts as a wall tile but looks like a blue wall.
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 * @author Max - Contributor
 */
public class BlueWallTile extends Tile {

    public BlueWallTile(Position pos) {
        super(pos, TileType.BLUE_WALL);
    }

}
