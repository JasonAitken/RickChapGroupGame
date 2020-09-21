package nz.ac.vuw.ecs.swen225.a3.maze.tiles;

import nz.ac.vuw.ecs.swen225.a3.application.util.GUIUtil;
import nz.ac.vuw.ecs.swen225.a3.maze.util.EventType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.PrePost;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;
import nz.ac.vuw.ecs.swen225.a3.application.GUI;

/**
 * Exits the current level, returns to main menu.
 *
 * @author Jason - Creator
 * @author Mike - Contributor
 */
public class ExitTile extends Tile {

    //can make a field for the different lock colors
    // can also make a field for opened, and then make it walkable when it is

    public ExitTile(Position pos) {
        super(pos, TileType.EXIT);
        
        this.registerListener(PrePost.POST, EventType.CHIP_MOVED_TO, (board) -> {
        	board.getGui().levelCompleted();
        	
//        	board.getGui().returnToMenu();
            
            return false;
        });
    }

}
