package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TreasureType;

/**
 * Blue door opens with a blue key
 *
 * @author Jason - Creator
 * @author Mike - Contributor
 */
public class BlueDoorTile extends DoorTile {

    public BlueDoorTile(Position pos) {
        super(pos, TileType.BLUEDOOR, TreasureType.KEY_BLUE);
    }

}

