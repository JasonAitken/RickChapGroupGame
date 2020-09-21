package nz.ac.vuw.ecs.swen225.a3.maze;

import nz.ac.vuw.ecs.swen225.a3.application.exceptions.InvalidTypeException;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.Actor;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.Filler;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.Player;
import nz.ac.vuw.ecs.swen225.a3.maze.exceptions.ActorNotFoundException;
import nz.ac.vuw.ecs.swen225.a3.maze.tiles.Tile;
import nz.ac.vuw.ecs.swen225.a3.maze.treasures.Treasure;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Direction;
import nz.ac.vuw.ecs.swen225.a3.maze.util.MoveInfo;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Board extension that takes a queue of moves to perform on the actors within the level
 * in order to replay a previous game.
 * @author Fynn -Creator
 */
public class ReplayBoard extends Board {

    /**
     * @param moveArray Queue of Queues of Move information
     * @param currentQueue Rhe queue of moves currently being popped
     */
    private ArrayDeque<ArrayDeque<MoveInfo>> moveArray;
    private ArrayDeque<MoveInfo> currentQueue;


    /**
     * Constructor for a ReplayBoard, takes board info and passes it to a super board
     * object, then also a set of moves to iterate though
     * @author Fynn - Creator
     *
     * @param tiles Array of tiles
     * @param treasures Array of Treasures
     * @param actors Array of Actors
     * @param p Player actor
     * @param moveArray Queue of moves
     */
    public ReplayBoard(Tile[][] tiles, Treasure[][] treasures, Actor[][] actors, Player p,
                ArrayDeque<ArrayDeque<MoveInfo>> moveArray){

        super(tiles, treasures, actors, p, 100, "poopoopeepee");
        this.moveArray = moveArray;
    }

    /**
     * Performs a tick's worth of moves upon the board.
     * @return returns false if there are no more moves to be made
     */
    public boolean runTicks(){
        if(moveArray.size() <= 0){
            return false;
        }

        currentQueue = moveArray.pop();
        while(currentQueue.size() > 0){
            Actor[][] actors = super.getActorLayer();
            MoveInfo cMove = currentQueue.pop();
            Player p = super.getPlayer();
            Position pos = cMove.getPosition();
            Position playerPos = p.getPosition();

            if(pos.getX() == playerPos.getX() && pos.getY() == playerPos.getY()){
                p.setMove(cMove.getMoveType());
                super.movePlayer();
            }
            else{
                Actor a = actors[pos.getY()][pos.getX()];
                if(a instanceof Filler){
                    throw new ActorNotFoundException("Filler");
                }
                else{
                    super.moveActor(cMove.getMoveType(), a);
                }
            }


        }

        return true;
    }
}
