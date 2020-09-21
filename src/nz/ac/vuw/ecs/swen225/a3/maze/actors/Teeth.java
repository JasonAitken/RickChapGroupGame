package nz.ac.vuw.ecs.swen225.a3.maze.actors;

import nz.ac.vuw.ecs.swen225.a3.maze.util.ActorType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Direction;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Teeth move towards the player. NOT PATH-FINDING! Walks towards the player if if it can in any
 * direction.
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 * @author Max - Contributor
 */
public class Teeth extends Actor {

    private static final Set<TileType> WALKABLE_TILES = new HashSet<>(Collections.singletonList(TileType.FIRE));

    private int tickWaitTime = 2;

    public Teeth(Position pos) {
        super(pos, ActorType.TEETH);

        this.registerOnTickListener((board) -> {
            if (board.getCurrentTick() % tickWaitTime == 0) {
                Position playerPosition = board.getPlayer().getPosition();

                // Determine the shortest route to the player
                int dy = playerPosition.getY() - this.getPosition().getY();
                int dx = playerPosition.getX() - this.getPosition().getX();

                if(dy == 0 && dx == 0){
                    return true;
                } else if (Math.abs(dx) > Math.abs(dy)) {
                    // Move Right
                    if (dx > 0) this.setDirection(Direction.RIGHT);
                        // Move Left
                    else this.setDirection(Direction.LEFT);
                } else {
                    // Move Down
                    if (dy > 0) this.setDirection(Direction.DOWN);
                        // Move Up
                    else this.setDirection(Direction.UP);
                }

                // Move the teeth
                if (board.isValid(this.getDirection(), this)) board.moveActor(this.getDirection(), this);
            }

            return true;
        });
    }

    @Override
    protected Set<TileType> getActorWalkableTiles() {
        return WALKABLE_TILES;
    }
}
