package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.application.util.GUIUtil;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.Player;
import nz.ac.vuw.ecs.swen225.a3.maze.util.*;

/**
 * Door tiles take keys and open
 *
 * @author Max - Creator
 */
public abstract class DoorTile extends Tile {
    public static final int OPENED = 0;
    public static final int CLOSED = 1;

    private TreasureType key;
    private boolean open = false;

    /**
     * Constructor for generic Door, takes and stores name of Door and type of Door,
     * this is subject to change.
     *
     * @param pos  The location of the Door
     * @param type The DoorType (for each color)
     * @author Jason - Creator
     * @author Max - Contribution
     */
    public DoorTile(Position pos, TileType type, TreasureType key) {
        super(pos, type);
        this.key = key;

        this.registerListener(PrePost.PRE, EventType.CHIP_MOVED_TO, (board) -> {
            Player player = board.getPlayer();
            if (player.hasKey(key) && !open) {
                player.useKey(key);
                setWalkable(true);
                open = true;
                image = GUIUtil.ASSETS.get(TileType.FREE);
                board.setTileAt(pos.getX(), pos.getY(), new FreeTile(pos));
                return true;
            } else if (!open) {
                return false;
            }
            return true;
        });
    }

    /**
     * Gets the key required by this door
     *
     * @return The key required by this door
     * @author Max - Creator
     */
    public TreasureType getKey() {
        return key;
    }

    public boolean isOpen() {
        return open;
    }
}
