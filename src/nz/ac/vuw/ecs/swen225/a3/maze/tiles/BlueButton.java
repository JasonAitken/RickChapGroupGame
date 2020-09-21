package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.EventType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.PrePost;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * On activation, causes every tank to reverse direction.
 *
 * @author Tim - Creator
 */
public class BlueButton extends Tile {

    public BlueButton(Position pos) {
        super(pos, TileType.BLUE_BUTTON);

        //Register event for chip
        this.registerListener(PrePost.POST, EventType.CHIP_MOVED_TO, board -> {

            board.processEvent(board.getActorLayer(), PrePost.PRE, EventType.BLUE_BUTTON_PRESSED);
            return true;
        });

        //Register event for monster
        this.registerListener(PrePost.POST, EventType.MONSTER_MOVED_TO, board -> {

            board.processEvent(board.getActorLayer(), PrePost.PRE, EventType.BLUE_BUTTON_PRESSED);
            return true;
        });
    }
}
