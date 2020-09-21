package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.actors.Player;
import nz.ac.vuw.ecs.swen225.a3.maze.util.*;

/**
 * @author Tim - Creator
 * @author Mike - Contributor
 * @author Jason - Contributor
 */
public class ForceFloorTile extends Tile {

    int direction;

    public ForceFloorTile(Position pos) {
        super(pos, TileType.FORCE_FLOOR);
        //Register listener to implement main Force floor logic
        this.registerListener(PrePost.POST, EventType.CHIP_MOVED_TO, (board) -> {

            Player player = board.getPlayer();

            player.movePosition(Direction.DOWN);

            return false;
        });
    }
}
