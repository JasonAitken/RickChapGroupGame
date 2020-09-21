package nz.ac.vuw.ecs.swen225.a3.maze.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * Simple util class for recording positions, all coordinates are immutable, this is to ensure
 * we don't get stray pointers to positions of objects and change it by accident
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 */
public class Position implements Serializable {
    private final int x;
    private final int y;

    /**
     * Creates a position from the provided x and y positions, these are stored as final values to ensure they are not
     * changed by stray references
     *
     * @param x The x value of the position
     * @param y The y value of the position
     * @author Tim - Creator
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x value of this position
     *
     * @return The x value of this position
     * @author Tim - Creator
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y value of this position
     *
     * @return The y value of this position
     * @author Tim - Creator
     */
    public int getY() {
        return y;
    }

    /**
     * Adds another position onto this position and returns the new position
     *
     * @param addition The position to add to this position
     * @return The new position
     * @author Tim - Creator
     */
    public Position add(Position addition) {
        return new Position(this.x + addition.getX(), this.y + addition.getY());
    }

    public Position minus(Position subtraction) {
        return new Position(this.x - subtraction.getX(), this.y - subtraction.getY());
    }

    /**
     * Creates a new Position object from an integer array in the format x, y
     *
     * @return a new Position Object
     * @author Mike - Creator
     */
    public static Position fromObjArray(Object[] objArr) {
        //TODO Pre and post checks!
        //TODO Testing!
        Integer[] integerArray = ArrayUtil.toIntegerArray(objArr);

        if (integerArray.length == 2) {
            return new Position(integerArray[0], integerArray[1]);
        }
        //TODO: Throw exception
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
