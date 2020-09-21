package nz.ac.vuw.ecs.swen225.a3.application.exceptions;

/**
 * Exception for when an asset which is expected to be at the presenting enums file path is not actually there
 *
 * @author Tim - Creator
 */
public class AssetNotFoundException extends RuntimeException {

    /**
     * Standard RuntimeException which is passed an exception message
     *
     * @param message The exception message
     * @author Tim - Creator
     */
    public AssetNotFoundException(String message) {
        super(message);
    }
}
