package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.application.util.GUIUtil;
import nz.ac.vuw.ecs.swen225.a3.maze.util.EventType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.PrePost;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * It's a wall, but looks like a free tile until chap tries to stand on it.
 *
 * @author Tim - Creator
 */
public class HiddenWallTile extends Tile {

    public HiddenWallTile(Position pos) {
        super(pos, TileType.HIDDEN_WALL);

        //Register listener to implement main hidden wall logic
        this.registerListener(PrePost.PRE, EventType.CHIP_MOVED_TO, (board) -> {

            image = GUIUtil.ASSETS.get(TileType.WALL);
            this.setWalkable(false);

            return false;
        });
    }
}
