package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.application.util.GUIUtil;
import nz.ac.vuw.ecs.swen225.a3.maze.util.EventType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.PrePost;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * Exit door for the level, only allows you through once you have collected the correct amount
 * chips.
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 * @author Jason - Contributor
 */
public class SocketTile extends Tile {
    public SocketTile(Position pos) {
        super(pos, TileType.SOCKET);

        this.registerListener(PrePost.PRE, EventType.CHIP_MOVED_TO, (board) -> {

            if (board.getChipsLeft() != 0) {
                return false;
            } else {
                image = GUIUtil.ASSETS.get(TileType.FREE);
                this.setWalkable(true);
            }


            return true;
        });


    }
}
