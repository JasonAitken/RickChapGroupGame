package nz.ac.vuw.ecs.swen225.a3.maze.actors;

import nz.ac.vuw.ecs.swen225.a3.maze.util.ActorType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a filler actor, used in populating the actor layer in board.
 *
 * @author Tim - Creator
 */
public class Filler extends Actor {

    public Filler(Position pos) {
        super(pos, ActorType.FILLER);
    }

    @Override
    public Set<TileType> getActorWalkableTiles() {
        return new HashSet<>();
    }
}
