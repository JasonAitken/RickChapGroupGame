package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.EventType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.PrePost;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * Bomb kills the player, and all actors
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 */
public class BombTile extends Tile {

    public BombTile(Position pos) {
        super(pos, TileType.BOMB);

        this.registerListener(PrePost.POST, EventType.CHIP_MOVED_TO, board -> {
            board.killPlayer("Chip blew up!");

            return true;
        });
    }

}
