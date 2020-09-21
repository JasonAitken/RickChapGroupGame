package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.actors.Player;
import nz.ac.vuw.ecs.swen225.a3.maze.util.*;

/**
 * Forces the player is the leftwards direction
 *
 * @author Jason - Creator
 */
public class ForceFloorLeftTile extends Tile {

    int direction;

    public ForceFloorLeftTile(Position pos) {
        super(pos, TileType.FORCE_FLOOR_LEFT);
        //Register listener to implement main Force floor logic
        this.registerListener(PrePost.POST, EventType.CHIP_MOVED_TO, (board) -> {

            Player player = board.getPlayer();
            if (!player.hasBoots(TreasureType.SUCTION_BOOTS)) {
                player.setNextForcedMove(Direction.LEFT);
            }
            return false;
        });
    }
}
