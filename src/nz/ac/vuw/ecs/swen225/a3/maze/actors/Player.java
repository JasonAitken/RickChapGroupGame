package nz.ac.vuw.ecs.swen225.a3.maze.actors;

import nz.ac.vuw.ecs.swen225.a3.application.exceptions.InvalidTypeException;
import nz.ac.vuw.ecs.swen225.a3.maze.treasures.Treasure;
import nz.ac.vuw.ecs.swen225.a3.maze.util.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Tim - Creator
 * @author Mike - Contributor
 */
public class Player extends Actor {

    private static Set<TileType> WALKABLE_TILES = new HashSet<>(Arrays.stream(TileType.values()).filter(TileType::isWalkable).collect(Collectors.toList()));

    public static final int INVENTORY_SIZE = 8;

    private int[] inventory;
    private Direction nextMove = Direction.NONE; //Default to null
    private Direction nextForcedMove = Direction.NONE;

    /**
     * Default player constructor.
     *
     * @param pos       the position of the player.
     * @param inventory the initial inventory.
     * @author mike - Contributor.
     */
    public Player(Position pos, int[] inventory) {
        super(pos, ActorType.PLAYER);

        //TODO Write a test for this
        assert (inventory.length == INVENTORY_SIZE);
        this.inventory = inventory;
    }

    public Player(Position pos) {
        super(pos, ActorType.PLAYER);
        this.inventory = new int[INVENTORY_SIZE];
    }


    /**
     * Returns players next move, or null if none has been specified.
     * @return players next move
     */
    public Direction getMove() {
        return nextMove;
    }

    /**
     * Sets players buffered move.
     * @param move move type
     */
    public void setMove(Direction move) {
        this.nextMove = move;
        if (move != Direction.NONE && this.getNextForcedMove() == Direction.NONE) {
            this.setDirection(move);
        }
    }

    /**
     * Picks up the treasure and puts it in the inventory.
     *
     * @param pickup the picked up treasure
     * @author Max - Creator
     */
    public void pickup(Treasure pickup) {
        int slot = ((TreasureType) (pickup.getType())).getSlot();
        if (slot != -1)
            inventory[slot]++;
    }

    /**
     * Checks if a given TreasureType is a key.
     * @param key the TreasureType to check
     * @return if the given type is a key
     * @author Max - Creator
     */
    private boolean isKey(TreasureType key) {
        return key.equals(TreasureType.KEY_BLUE) ||
                key.equals(TreasureType.KEY_RED) ||
                key.equals(TreasureType.KEY_YELLOW) ||
                key.equals(TreasureType.KEY_GREEN);
    }

    /**
     * Checks if a given TreasureType is a boots.
     * @param boots the TreasureType to check
     * @return if the given type is a boot
     * @author Jason - Creator
     */
    private boolean isBoots(TreasureType boots) {
        return boots.equals(TreasureType.SUCTION_BOOTS) 
        		|| boots.equals(TreasureType.ICE_BOOTS) 
        		|| boots.equals(TreasureType.FIRE_BOOTS)
        		|| boots.equals(TreasureType.FLIPPERS);
    }

    /**
     * Checks if the player has the given key type in the inventory.
     *
     * @param key the key type to check
     * @return if the player has one or more of the key
     * @author Max - Creator
     */
    public boolean hasKey(TreasureType key) {
        if (!isKey(key)) throw new InvalidTypeException();

        return inventory[key.getSlot()] > 0;
    }

    /**
     * Checks if the player has the given boots type in the inventory.
     *
     * @param boots the boots type to check
     * @return if the player has boots
     * @author Jason - Creator
     */
    public boolean hasBoots(TreasureType boots) {
        if (!isBoots(boots)) throw new InvalidTypeException();

        return inventory[boots.getSlot()] > 0;
    }

    /**
     * Tells the player to use a key.
     *
     * @param key The TreasureType of the key to be used.
     * @return if the given key is able to be used
     * @throws InvalidTypeException if given TreasureType is not a key type
     * @author Max - Creator
     */
    public boolean useKey(TreasureType key) {
        if (hasKey(key)) {
            if (key.consumeOnUse()) {
                inventory[key.getSlot()]--;
            }
            return true;
        }
        return false;
    }


    /**
     * Get the players inventory.
     *
     * @return the inventory
     * @author Max - Creator
     */
    public int[] getInventory() {
        return inventory.clone();
    }

    /**
     * Print the players inventory.
     *
     * @author Max - Creator
     */
    public void printInventory() {
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            TreasureType treasure = null;
            for (TreasureType t : TreasureType.values()) {
                if (t.getSlot() == i) treasure = t;
            }
            if (treasure != null) System.out.println(treasure.getName() + ": " + inventory[i]);
        }
    }

    @Override
    public String toString() {
        return "Player{" + "inventory=" + Arrays.toString(inventory) + ", pos=" + super.getPosition() + '}';
    }

    /**
     * Returns a JSON object of Rick
     *
     * @return the JSON representing Chap
     * @author Mike - Creator
     */
    public String toJson() {
        Position pos = getPosition();

        return "\"player\": {\n" +
                "\"position\": [" + pos.getX() + "," + pos.getY() + "],\n" +
                "\"inventory\": " + Arrays.toString(this.getInventory()) + "\n" +
                "}";
    }

    /**
     * Gets the players next forced move.
     *
     * @return The players forced direction
     * @author Max - Creator
     */
    public Direction getNextForcedMove() {
        return nextForcedMove;
    }

    /**
     * Sets the players next forced move.
     *
     * @param nextForcedMove    The move to assign the players next forced move to
     * @author Max - Creator
     */
    public void setNextForcedMove(Direction nextForcedMove) {
        this.nextForcedMove = nextForcedMove;
        this.setDirection(nextForcedMove);
    }

    @Override
    public Set<TileType> getActorWalkableTiles() {
        return WALKABLE_TILES;
    }
}
