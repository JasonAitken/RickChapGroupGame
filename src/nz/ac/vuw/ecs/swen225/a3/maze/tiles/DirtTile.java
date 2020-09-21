package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.application.util.GUIUtil;
import nz.ac.vuw.ecs.swen225.a3.maze.util.EventType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.PrePost;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * Dirt tiles act as walls to monsters and blocks until stood on by the player
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 * @author Max - Contributor
 */
public class DirtTile extends Tile {
    private boolean isDirt = false;

    public DirtTile(Position pos) {
        super(pos, TileType.DIRT);

        this.registerListener(PrePost.PRE, EventType.MONSTER_MOVED_TO, (board) -> isDirt);

        // Change state when stood on
        this.registerListener(PrePost.POST, EventType.CHIP_MOVED_TO, (board) -> {
            image = GUIUtil.ASSETS.get(TileType.FREE);
            board.setTileAt(pos.getX(), pos.getY(), new FreeTile(pos));
            isDirt = true;

            return false;
        });
    }


}
