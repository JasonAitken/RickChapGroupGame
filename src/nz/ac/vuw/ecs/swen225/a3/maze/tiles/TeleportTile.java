package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.maze.Board;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.Actor;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.Player;
import nz.ac.vuw.ecs.swen225.a3.maze.util.EventType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.PrePost;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * Teleports player to another teleport.
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 * @author Max - Contributor
 */
public class TeleportTile extends Tile {
    public TeleportTile(Position pos) {
        super(pos, TileType.TELEPORT);

        this.registerListener(PrePost.POST, EventType.CHIP_MOVED_TO, (board) -> {
            Player player = board.getPlayer();
            Tile[][] tiles = board.getTileLayer();
            Position initialPos = this.getPosition();

            int y = initialPos.getY();
            int x = initialPos.getX() - 1;
            while (!initialPos.equals(new Position(x, y))) {
                // Back a row
                if (x < 0) {
                    x = Board.BOARD_WIDTH - 1;
                    y--;
                    if (y < 0) {
                        y = Board.BOARD_HEIGHT - 1;
                    }
                }

                //Checks
                if (tiles[y][x] instanceof TeleportTile) {
                    // Check if we can teleport here
                    Player tempActor = new Player(new Position(x, y));
                    if (board.isValid(player.getDirection(), tempActor)) {
                        //Teleport there
                        board.moveActorTo(initialPos, new Position(x, y));
                        player.setNextForcedMove(player.getDirection());
                    }
                }

                //Prep for next check
                x--;
            }

            return true;
        });
    }
}
