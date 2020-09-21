package nz.ac.vuw.ecs.swen225.a3.maze.actors;

import nz.ac.vuw.ecs.swen225.a3.maze.util.ActorType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Direction;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Fireballs move straight. When they reach an unwalkable tile they then try to go right, left then backwards in
 * that order. Survives fire tiles.
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 * @author Max - Contributer
 */
public class FireBall extends Actor {

    private static final Set<TileType> WALKABLE_TILES = new HashSet<>(Collections.singletonList(TileType.FIRE));

    private int tickWaitTime = 2;

    public FireBall(Position pos) {
        super(pos, ActorType.FIREBALL);

        this.setDirection(Direction.DOWN);

        this.registerOnTickListener((board) -> {

            //Glider probably shouldn't move every tick
            if (board.getCurrentTick() % tickWaitTime == 0) {

                //Find a possible direction we can move in
                for (int i = 0; i < Direction.values().length && !board.isValid(this.getDirection(), this); i++) {
                    this.setDirection(Direction.values()[(this.getDirection().ordinal() + 1) % Direction.values().length]);
                    tickWaitTime = this.getDirection().ordinal() + 1;
                }

                //Finally move the fireball
                board.moveActor(this.getDirection(), this);
            }
            return true;
        });
    }

    @Override
    public Set<TileType> getActorWalkableTiles() {
        return WALKABLE_TILES;
    }
}
