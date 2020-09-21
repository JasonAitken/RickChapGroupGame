package nz.ac.vuw.ecs.swen225.a3.maze.treasures;


import nz.ac.vuw.ecs.swen225.a3.application.util.GUIUtil;
import nz.ac.vuw.ecs.swen225.a3.maze.GameObject;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.Player;
import nz.ac.vuw.ecs.swen225.a3.maze.util.EventType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.PrePost;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TreasureType;

/**
 * Generic class for all consumable pickups within the game, all consumable object
 * types will extend this and this will contain universal information about items.
 *
 * @author Fynn - Creator
 * @author Mike - Contributor
 */
public abstract class Treasure extends GameObject {

    private boolean pickedUp = false;

    /**
     * Constructor for generic Treasure, takes and stores name of item and type of Treasure,
     * this is subject to change.
     *
     * @param location Where it is intended to be placed on the map
     * @param type     the type that this treasure will be
     * @author Fynn - Creator
     */
    Treasure(Position location, TreasureType type) {
        super(location, type);

        // Pickup Listener
        this.registerListener(PrePost.POST, EventType.CHIP_MOVED_TO, (board) -> {
            if (!pickedUp) {
                Player player = board.getPlayer();

                player.pickup(this);
                this.image = GUIUtil.ASSETS.get(TreasureType.FILLER);
                pickedUp = true;
                if (this instanceof Chip) {
                    board.pickupChip();
                }

            }
            return true;
        });
    }

    /**
     * Tells if the treasure has been picked up yet or not.
     *
     * @return true if the Treasure has been picked up.
     * @author mike - Creator
     */
    public boolean isPickedUp() {
        return pickedUp;
    }


//    /**
//     * Constructor for generic Treasure, takes and stores name of item and type of Treasure,
//     * this is subject to change.
//     *
//     * @param location Where it is intended to be placed on the map
//     * @param type     the type that this treasure will be
//     * @author Fynn - Creator
//     */
//    Treasure(Position location, TreasureType type) {
//        super(location, type);
//        this.type = type;
//    }

    /**
     * Constructor for generic Treasure, takes and stores name of item and type of Treasure,
     * this is subject to change.
     *
     * @param type the type that this treasure will be
     * @author Mike - Creator
     */
    Treasure(TreasureType type) {
        super(type);
    }
}
