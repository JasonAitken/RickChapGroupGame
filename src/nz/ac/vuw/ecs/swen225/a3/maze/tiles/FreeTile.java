package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * Class for representing a free tile in the game
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 */
public class FreeTile extends Tile {

    public FreeTile(Position pos) {
        super(pos, TileType.FREE);
    }
}
