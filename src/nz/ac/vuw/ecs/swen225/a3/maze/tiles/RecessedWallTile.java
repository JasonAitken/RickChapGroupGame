package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.application.util.GUIUtil;
import nz.ac.vuw.ecs.swen225.a3.maze.util.EventType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.PrePost;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * Recessed Wall acts as a wall to everything but the Player. When the player stands on
 * the recessed wall, it turns into a Wall tile.
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 * @author Max - Contributer
 */
public class RecessedWallTile extends Tile {
    private boolean isRecessed = true;

    public RecessedWallTile(Position pos) {
        super(pos, TileType.RECESSED_WALL);

        // Listener for monsters
        this.registerListener(PrePost.PRE, EventType.MONSTER_MOVED_TO, (board) -> false);

        // Listener for player standing on the recessed wall
        this.registerListener(PrePost.POST, EventType.CHIP_MOVED_TO, (board) -> {
            if (isRecessed) {
                image = GUIUtil.ASSETS.get(TileType.WALL);
                setWalkable(false);
                isRecessed = false;
                return true;
            } else return false;
        });
    }
}
