package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * Displays text on the canvas.
 *
 * @author Jason - Creator
 * @author Mike - Contributor
 */
public class HintTile extends Tile {
    public HintTile(Position pos) {
        super(pos, TileType.HINT);

    }
}
