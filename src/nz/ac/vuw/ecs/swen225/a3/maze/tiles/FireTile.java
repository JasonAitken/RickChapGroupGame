package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.*;

import javax.swing.*;

/**
 * Fire acts as a wall to bugs. Monsters and Chip will die when they step on the tile except
 * for Chip with the fire boots and the fire ball.
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 */
public class FireTile extends Tile {

    public FireTile(Position pos) {
        super(pos, TileType.FIRE);
        this.registerListener(PrePost.POST, EventType.CHIP_MOVED_TO, (board) -> {
            if (!board.getPlayer().hasBoots(TreasureType.FIRE_BOOTS)) {
                board.killPlayer("You burned in a fire");
            }

            return true;
        });
    }
}
