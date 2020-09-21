package nz.ac.vuw.ecs.swen225.a3.maze.actors;

import nz.ac.vuw.ecs.swen225.a3.maze.GameObject;
import nz.ac.vuw.ecs.swen225.a3.maze.util.ActorType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Direction;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Actors represent either a computer player or human player.
 * @author Tim - Creator
 * @author Mike - Contributor
 */
public abstract class Actor extends GameObject {

    private Set<TileType> walkableTiles = new HashSet<>(
            Arrays.asList(TileType.FREE, TileType.ICE, TileType.FORCE_FLOOR,
                    TileType.FORCE_FLOOR_DOWN, TileType.FORCE_FLOOR_LEFT, TileType.FORCE_FLOOR_RIGHT,
                    TileType.FORCE_FLOOR_UP, TileType.BOMB, TileType.GREEN_BUTTON, TileType.BLUE_BUTTON,
                    TileType.RED_BUTTON, TileType.BROWN_BUTTON,
            TileType.HINT, TileType.WATER));

    private Direction direction;

    /**
     * Creates an Actor with both a position and type, this is deprecated as position
     * should no longer be stored in the class itself but handled by the board itself.
     *
     * @param pos  The position of the actor
     * @param type The type of the actor
     * @author Tim - Creator
     */
    public Actor(Position pos, ActorType type) {
        super(pos, type);
        this.direction = Direction.RIGHT;
        this.walkableTiles.addAll(this.getActorWalkableTiles());

        // Make actors kill the player
        this.registerOnTickListener(board -> {
            Position playerPosition = board.getPlayer().getPosition();
            if (!(board.getActorLayer()[playerPosition.getY()][playerPosition.getX()] instanceof Filler)) {
                board.killPlayer("Chip got killed by the " + board.getActorLayer()[playerPosition.getY()][playerPosition.getX()].getType().getName());
            }

            return true;
        });
    }

    /**
     * Creates an Actor with just a type.
     * @param type The type of the Actor - Should not be passed as a parameter,
     *             but hardcoded by each class that
     *             extends Actor.
     * @author Mike - Creator
     */
    public Actor(ActorType type) {
        super(type);
    }

//    /**
//     * Gets the ActorType of the object.
//     *
//     * @return The type of this actor.
//     * @author Tim - Creator
//     */
//    public ActorType getType() {
//        return type;
//    }

    /**
     * Gets the direction of this actor
     *
     * @return The direction of this actor
     * @author Tim - Creator
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets the direction of this actor
     *
     * @param direction The direction to set
     * @author Tim - Creator
     */
    public void setDirection(Direction direction) {
        if (direction != Direction.NONE) {
            this.direction = direction;
        }
    }

    /**
     * Gets the set of tiles that this actor can walk on
     *
     * @return The set of tiles this actor can walk on
     * @author Tim - Creator
     */
    public Set<TileType> getWalkableTiles() {
        return Collections.unmodifiableSet(walkableTiles);
    }

    /**
     * Gets the set of tiles that subclass-actors can walk on
     *
     * @return The tileset that this subclass-actor can walk on
     * @author Tim - Creator
     */
    protected abstract Set<TileType> getActorWalkableTiles();

}