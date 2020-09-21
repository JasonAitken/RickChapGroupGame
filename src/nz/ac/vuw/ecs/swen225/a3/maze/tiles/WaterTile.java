package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.*;

/**
 * Water will kill any monster, other than the glider, that steps on the tile. When Chip steps on the tile
 * he will be killed unless he is wearing flippers. When a block is pushed on the tile, the block is removed
 * and the tile changes to a dirt tile. If Chip is swimming in the tile when a block is pushed into the tile
 * then Chip will die. If a monster steps on the tile while Chip is swimming in it, the monster will die.
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 * @author Max - Contributor
 */
public class WaterTile extends Tile {
    public WaterTile(Position pos) {
        super(pos, TileType.WATER);

        this.registerListener(PrePost.POST, EventType.CHIP_MOVED_TO, board -> {
            if (!board.getPlayer().hasBoots(TreasureType.FLIPPERS)) {
                board.killPlayer("Rick drowned. He needs flippers to swim.");
            }

            return true;
        });
    }
}