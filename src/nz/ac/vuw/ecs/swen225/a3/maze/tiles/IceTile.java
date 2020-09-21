package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.actors.Player;
import nz.ac.vuw.ecs.swen225.a3.maze.util.*;

/**
 * Ice tiles make any moving object that step on them move in the direction they stepped on the tile
 * at double the normal speed. There are four ice corners that change the direction of a sliding object -
 * doesn’t prevent normal movement through them. Treated as a normal tile if Chip is wearing the ice
 * skates.
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 * @author Max - Contributor
 */
public class IceTile extends Tile {
    public IceTile(Position pos) {
        super(pos, TileType.ICE);
        //TODO @max BOOTS

        this.registerListener(PrePost.POST, EventType.CHIP_MOVED_TO, (board) -> {
            Player player = board.getPlayer();

            if(!player.hasBoots(TreasureType.ICE_BOOTS)) {
                player.setNextForcedMove(player.getDirection());
            }
            return true;
        });
    }
}
