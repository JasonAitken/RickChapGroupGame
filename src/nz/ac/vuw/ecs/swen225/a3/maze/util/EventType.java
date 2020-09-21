package nz.ac.vuw.ecs.swen225.a3.maze.util;

/**
 * Represents all of the events that can happen in the game, used for firing listeners depending on the event
 * happening
 *
 * @author Tim - Creator
 */
public enum EventType {
    CHIP_MOVED_TO,
    CHIP_MOVED_FROM,

    SOCKET_LOGIC,
    GRAVEL_LOGIC,
    FORCE_FLOOR_LOGIC,

    BROWN_BUTTON_PRESSED,
    BROWN_BUTTON_RELEASED,
    RED_BUTTON_PRESSED,
    BLUE_BUTTON_PRESSED,
    GREEN_BUTTON_PRESSED,
    MONSTER_MOVED_TO,
    MONSTER_MOVED_FROM;
}
