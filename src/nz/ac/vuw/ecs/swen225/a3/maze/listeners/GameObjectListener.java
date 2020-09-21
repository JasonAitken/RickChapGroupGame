package nz.ac.vuw.ecs.swen225.a3.maze.listeners;

import nz.ac.vuw.ecs.swen225.a3.maze.Board;

/**
 * Template functional interface - may change in the future.
 * Used for the different listeners that are executed on events
 *
 * @author Tim - Creator
 */
@FunctionalInterface
public interface GameObjectListener {
    boolean execute(Board board);
}
