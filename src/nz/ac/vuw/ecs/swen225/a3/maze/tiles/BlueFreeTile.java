package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * Looks like a blue wall tile but acts as a free tile.
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 * @author Max - Contributer
 */
public class BlueFreeTile extends Tile {

    public BlueFreeTile(Position pos) {
        super(pos, TileType.BLUE_FREE);
    }

}
