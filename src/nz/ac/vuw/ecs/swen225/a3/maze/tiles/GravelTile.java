package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.EventType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.PrePost;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * Class for representing gravel in the game.
 * Gravel acts as a wall to monsters.
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 * @author Jason - Contributor
 */
public class GravelTile extends Tile {

    public GravelTile(Position pos) {
        super(pos, TileType.GRAVEL);
    }
}
