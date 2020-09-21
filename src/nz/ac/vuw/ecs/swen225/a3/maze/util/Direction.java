package nz.ac.vuw.ecs.swen225.a3.maze.util;

/**
 * Simple enum for representing direction of an actor, may or may not actually be used by the subclass.
 *
 * @author Tim - Creator
 */
public enum Direction {
    NONE(null, 0),
    UP(new Position(0, -1), 1),
    RIGHT(new Position(1, 0), 2),
    DOWN(new Position(0, 1), 3),
    LEFT(new Position(-1, 0), 4);

    private Position offset;
    private int move;

    Direction(Position offset, int move) {
        this.offset = offset;
        this.move = move;
    }

    /**
     * Gets the directional offset of this direction
     *
     * @return The directional offset of this direction
     * @author Tim - Creator
     */
    public Position getOffset() {
        return offset;
    }

    /**
     * Gets the integer representative of this direction
     *
     * @return The integer representative of this direction
     * @author Tim - Creator
     */
    public int getMove() {
        return move;
    }

    /**
     * Gets the inverse of this direction
     *
     * @return The inverse of this direction
     * @author Max - Creator
     * @author Tim - Contributor
     */
    public Direction inverse() {
        if (this == NONE) return NONE;
        int value = this.ordinal() + 2;
        return Direction.values()[value - (value > 4 ? 4 : 0)];
    }
}
