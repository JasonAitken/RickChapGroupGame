package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.actors.Player;
import nz.ac.vuw.ecs.swen225.a3.maze.util.*;

/**
 * Forces a player in the upward direction.
 *
 * @author Jason - Creator
 */
public class ForceFloorUpTile extends Tile {

    public ForceFloorUpTile(Position pos) {
        super(pos, TileType.FORCE_FLOOR_UP);
        //Register listener to implement main Force floor logic
        this.registerListener(PrePost.POST, EventType.CHIP_MOVED_TO, (board) -> {

            Player player = board.getPlayer();
            if (!player.hasBoots(TreasureType.SUCTION_BOOTS)) {
                player.setNextForcedMove(Direction.UP);
            }
            return false;
        });
    }
}
