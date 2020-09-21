package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.EventType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.PrePost;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * Traps the player on this tile until the brown button is pressed.
 *
 * @author Tim - Creator
 * @author mike - Contributor
 * @author Max - Contirbutor
 */
public class TrapTile extends Tile {

    private boolean engaged = true;

    public TrapTile(Position pos) {
        super(pos, TileType.TRAP);

        this.registerListener(PrePost.PRE, EventType.CHIP_MOVED_FROM, (board) -> !engaged);

        this.registerListener(PrePost.PRE, EventType.MONSTER_MOVED_FROM, board -> !engaged);

        this.registerListener(PrePost.PRE, EventType.BROWN_BUTTON_PRESSED, (board) -> {
            engaged = false;

            return true;
        });

        this.registerListener(PrePost.PRE, EventType.BROWN_BUTTON_RELEASED, (board) -> {
            engaged = true;

            return true;
        });
    }
}
