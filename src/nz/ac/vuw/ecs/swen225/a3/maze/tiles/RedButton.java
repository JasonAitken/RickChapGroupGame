package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * On activation, causes the connected clone machine to activate.
 *
 * @author Tim - Creator
 */
public class RedButton extends Tile {

    public RedButton(Position pos) {
        super(pos, TileType.RED_BUTTON);
    }
}
