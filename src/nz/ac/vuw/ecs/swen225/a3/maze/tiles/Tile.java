package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.GameObject;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * Abstract super tile class, contains functions required by every tile irregardless of it's
 * type. The class simple contains the listeners that will be run on each event process, the position
 * of the tile, and the type of the tile.
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 * @author Jason - Contributor
 */
public abstract class Tile extends GameObject {

    private boolean walkable;

    /**
     * Constructor of the tile, takes the position and the tile type.
     * Note: the tile type should be hardcoded by each subclass, as in
     * not passed to it as a parameter.
     *
     * @param pos  The position of the tile
     * @param type The type of the tile
     * @author Tim - Creator
     */
    public Tile(Position pos, TileType type) {
        super(pos, type);
        this.walkable = type.isWalkable();

    }

    /**
     * Returns whether or not this tile is walkable or not
     *
     * @return Whether this tile is walkable
     */
    public boolean isWalkable() {
        return walkable;
    }

    /**
     * Set if the Tile is walkable
     *
     * @param walkable to set what the tile is
     * @author Max - Creator
     */
    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }
}
