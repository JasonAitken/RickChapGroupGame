package nz.ac.vuw.ecs.swen225.a3.maze.exceptions;

/**
 * Called if a valid change of actor location is actually invalid
 * @author Max - Creator
 */
public class InvalidLocationChangeException extends RuntimeException {

    /**
     * Standard RuntimeException which is passed an exception message
     *
     * @param message The exception message
     * @author Max - Creator
     */
    public InvalidLocationChangeException(String message) {
        super(message);
    }
}
