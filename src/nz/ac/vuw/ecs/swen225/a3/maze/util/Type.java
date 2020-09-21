package nz.ac.vuw.ecs.swen225.a3.maze.util;

/**
 * Generic Type interface, should be implemented by all enums used to store information about different objects within
 * the game
 *
 * @author Tim - Creator
 */
public interface Type {
    /**
     * Gets the type of this enum
     *
     * @return The type of this enum
     * @author Tim - Creator
     */
    Class<?> getType();

    /**
     * Gets the general "name" of representing game object
     *
     * @return The name of the representing game object
     * @author Tim - Creator
     */
    String getName();

    /**
     * Gets the filepath to the image representing this game object
     *
     * @return The filepath to the image
     * @author Tim - Creator
     */
    String getFilePath();

    /**
     * Gets the string representative for this type, as in W for Wall, F for free etc
     *
     * @return The String representative
     * @author Tim - Creator
     */
    String getStringRepresentative();

    /**
     * Gets the ID for this type. Assigned in constructor.
     *
     * @return The ID
     * @author Mike - Creator
     */
    int getID();
}
