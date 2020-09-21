package nz.ac.vuw.ecs.swen225.a3.maze.exceptions;

/**
 * Runtime exception for when loading levels, thrown is a specified level is not actually
 * found in the assets
 *
 * @author Mike - Creator
 */
public class LevelNotFoundException extends RuntimeException {

    /**
     * Standard RuntimeException which is passed an exception message
     *
     * @param message The exception message
     * @author Max - Creator
     */
    public LevelNotFoundException(String message) {
        super(message);
    }
}
