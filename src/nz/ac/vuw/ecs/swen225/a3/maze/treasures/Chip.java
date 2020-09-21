package nz.ac.vuw.ecs.swen225.a3.maze.treasures;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TreasureType;

/**
 * Chips are needed to open the socket.
 *
 * @author Mike - Contributor
 * @author Max - Contributer
 */
public class Chip extends Treasure {
    public Chip(Position pos) {
        super(pos, TreasureType.CHIP);
    }
}
