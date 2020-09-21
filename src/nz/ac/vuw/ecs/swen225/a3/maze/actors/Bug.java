package nz.ac.vuw.ecs.swen225.a3.maze.actors;

import nz.ac.vuw.ecs.swen225.a3.maze.util.ActorType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

import java.util.HashSet;
import java.util.Set;

/**
 * Class for representing a bug, a bug will always move left. Otherwise it goes straight, if it can't go either
 * direction then it goes right, then backwards. The bug treats fire as a wall.
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 */
public class Bug extends Actor {

    public Bug(Position pos) {
        super(pos, ActorType.BUG);
    }

    @Override
    public Set<TileType> getActorWalkableTiles() {
        return new HashSet<>();
    }
}
