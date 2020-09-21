package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.application.util.GUIUtil;
import nz.ac.vuw.ecs.swen225.a3.maze.util.EventType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.PrePost;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

/**
 * Recessed Wall acts as a wall to everything but the Player. When the player stands on
 * the recessed wall, it turns into a Wall tile.
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 * @author Max - Contributer
 */
public class TimerWallTile extends Tile {
    private boolean isRecessed = true;
    private int startTime;

    public TimerWallTile(Position pos) {
        super(pos, TileType.TIMER_WALL);

        // Listener for monsters
        this.registerListener(PrePost.PRE, EventType.MONSTER_MOVED_TO, (board) -> false);

        // Listener for player standing on the recessed wall
        this.registerListener(PrePost.POST, EventType.CHIP_MOVED_TO, (board) -> {
            if (isRecessed) {
                image = GUIUtil.ASSETS.get(TileType.WALL);
                setWalkable(false);
                isRecessed = false;
                startTime = board.getCurrentTick();
                return true;
            } else return false;
        });
        
        this.registerOnTickListener((board) -> {
        	if(!isRecessed) {
        		if((board.getCurrentTick() - startTime) == 25) {
        			image = GUIUtil.ASSETS.get(TileType.FREE);
                    setWalkable(true);
        		} return false;
        	} return true;
        });
    }
}
