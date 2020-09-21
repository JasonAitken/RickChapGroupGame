package nz.ac.vuw.ecs.swen225.a3.maze.exceptions;

/**
 * Exception for when an invalid Actor ID or invalid Actor type is passed to the Object Factory
 *
 * @author Tim - Creator
 */
public class ActorNotFoundException extends RuntimeException {

    /**
     * Standard RuntimeException which is passed an exception message
     *
     * @param message The exception message
     * @author Tim - Creator
     */
    public ActorNotFoundException(String message) {
        super(message);
    }
}
