package nz.ac.vuw.ecs.swen225.a3.maze.exceptions;

/**
 * Exception for when an invalid Tile ID or invalid Tile type is passed to the Object Factory
 *
 * @author Tim - Creator
 */
public class TileNotFoundException extends RuntimeException {

    /**
     * Standard RuntimeException which is passed an exception message
     *
     * @param message The exception message
     * @author Tim - Creator
     */
    public TileNotFoundException(String message) {
        super(message);
    }
}
