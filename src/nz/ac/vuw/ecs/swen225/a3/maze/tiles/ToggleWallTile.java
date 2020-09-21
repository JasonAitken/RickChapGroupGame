package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.application.util.GUIUtil;
import nz.ac.vuw.ecs.swen225.a3.maze.util.EventType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.PrePost;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * Toggles when green button is clicked.
 * @author Tim - Creator
 * @author mike - Contributor
 * @author Max - Contributor
 */
public class ToggleWallTile extends Tile {
    private boolean up = true;

    public ToggleWallTile(Position pos) {
        super(pos, TileType.TOGGLE_WALL);

        this.registerListener(PrePost.PRE, EventType.CHIP_MOVED_TO, (board) -> !up);

        this.registerListener(PrePost.PRE, EventType.GREEN_BUTTON_PRESSED, (board) -> {
            up = !up;
            this.image = up ? GUIUtil.ASSETS.get(TileType.TOGGLE_WALL) : GUIUtil.ASSETS.get(TileType.FREE);
            if(!up) {
            	board.setTileAt(pos.getX(), pos.getY(), new FreeTile(pos));
            }
            return true;
        });
    }
}
