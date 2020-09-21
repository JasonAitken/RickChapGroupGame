package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.EventType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.PrePost;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * On activation, changes the state of every toggle wall.
 *
 * @author Tim - Creator
 * @author Max - Contributor
 */
public class GreenButton extends Tile {

    public GreenButton(Position pos) {
        super(pos, TileType.GREEN_BUTTON);

        this.registerListener(PrePost.POST, EventType.CHIP_MOVED_TO, (board) -> {

            board.processEvent(board.getTileLayer(), PrePost.PRE, EventType.GREEN_BUTTON_PRESSED);
            return true;
        });

        this.registerListener(PrePost.POST, EventType.MONSTER_MOVED_TO, (board) -> {

            board.processEvent(board.getTileLayer(), PrePost.PRE, EventType.GREEN_BUTTON_PRESSED);
            return true;
        });
    }
}