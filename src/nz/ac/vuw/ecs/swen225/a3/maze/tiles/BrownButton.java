package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.EventType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.PrePost;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * Brown button opens the trap when held down.
 * @author Max - Creator
 */
public class BrownButton extends Tile {

    public BrownButton(Position pos) {
        super(pos, TileType.BROWN_BUTTON);

        this.registerListener(PrePost.POST, EventType.MONSTER_MOVED_TO, (board) -> {
            board.processEvent(board.getTileLayer(), PrePost.PRE, EventType.BROWN_BUTTON_PRESSED);
            return true;
        });

        this.registerListener(PrePost.POST, EventType.CHIP_MOVED_TO, (board) -> {
            board.processEvent(board.getTileLayer(), PrePost.PRE, EventType.BROWN_BUTTON_PRESSED);
            return true;
        });

        this.registerListener(PrePost.POST, EventType.CHIP_MOVED_FROM, board -> {
            board.processEvent(board.getTileLayer(), PrePost.PRE, EventType.BROWN_BUTTON_RELEASED);
            return true;
        });

        this.registerListener(PrePost.POST, EventType.MONSTER_MOVED_FROM, board -> {
            board.processEvent(board.getTileLayer(), PrePost.PRE, EventType.BROWN_BUTTON_RELEASED);
            return true;
        });
    }
}
