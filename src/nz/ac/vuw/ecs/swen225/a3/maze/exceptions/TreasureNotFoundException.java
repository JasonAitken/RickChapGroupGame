package nz.ac.vuw.ecs.swen225.a3.maze.exceptions;

/**
 * Exception for when an invalid Treasure ID or invalid Treasure type is passed to the Object Factory
 *
 * @author Max - Creator
 */
public class TreasureNotFoundException extends RuntimeException {

    /**
     * Standard RuntimeException which is passed an exception message
     *
     * @param message The exception message
     */
    public TreasureNotFoundException(String message) {
        super(message);
    }
}
