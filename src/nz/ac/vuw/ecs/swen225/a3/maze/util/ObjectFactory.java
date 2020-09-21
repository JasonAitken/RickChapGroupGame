package nz.ac.vuw.ecs.swen225.a3.maze.util;

import nz.ac.vuw.ecs.swen225.a3.maze.actors.Actor;
import nz.ac.vuw.ecs.swen225.a3.maze.exceptions.ActorNotFoundException;
import nz.ac.vuw.ecs.swen225.a3.maze.exceptions.TileNotFoundException;
import nz.ac.vuw.ecs.swen225.a3.maze.exceptions.TreasureNotFoundException;
import nz.ac.vuw.ecs.swen225.a3.maze.tiles.Tile;
import nz.ac.vuw.ecs.swen225.a3.maze.treasures.Treasure;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * util function for creating tiles from either their ID or their class
 *
 * @author Tim - Creator
 * @author Jason - Contributor
 */
public class ObjectFactory {

    /**
     * Makes a tile from the ID provided.
     *
     * @param id  The ID of the tile
     * @param <E> The tile to be returned
     * @return The tile
     * @author Tim - Creator
     */
    public static <E extends Tile> E makeTile(int id, Position pos) {
        if (id < 0 || id >= TileType.values().length) throw new TileNotFoundException("Invalid ID: " + id);

        Class<?> clazz = TileType.values()[id].getType();
        if (Tile.class.isAssignableFrom(clazz)) {
            return makeTile((Class<E>) clazz, pos);
        }
        throw new TileNotFoundException("Class not applicable: " + clazz);
    }


    /**
     * Makes a tile from the Class provided.
     *
     * @param clazz The class to create a tile from
     * @param <E>   The tile to return
     * @return The tile
     * @author Tim - Creator
     */
    private static <E extends Tile> E makeTile(Class<E> clazz, Position pos) {
        try {
            Constructor<E> con = clazz.getConstructor(Position.class);
            return con.newInstance(pos);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new TileNotFoundException("Class not applicable: " + clazz);
        }
    }

    /**
     * Makes a actor from the ID provided.
     *
     * @param id  The ID of the tile
     * @param <E> The actor to be returned
     * @return The actor
     * @author Tim - Creator
     */
    public static <E extends Actor> E makeActor(int id, Position pos) {
        if (id < 0 || id >= ActorType.values().length) throw new ActorNotFoundException("Invalid ID: " + id);

        Class<?> clazz = ActorType.values()[id].getType();
        if (Actor.class.isAssignableFrom(clazz)) {
            return makeActor((Class<E>) clazz, pos);
        }
        throw new ActorNotFoundException("Class not applicable: " + clazz);
    }

    /**
     * Makes an actor from the Class provided.
     *
     * @param clazz The class to create an actor from
     * @param <E>   The actor to return
     * @return The actor
     */
    private static <E extends Actor> E makeActor(Class<E> clazz, Position pos) {
        try {
            Constructor<E> con = clazz.getConstructor(Position.class);
            return con.newInstance(pos);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new ActorNotFoundException("Class not applicable: " + clazz);
        }
    }

    /**
     * Makes a treasure from the ID provided.
     *
     * @param id  The ID of the treasure
     * @param <E> The treasure to be returned
     * @return The treasure
     * @author Jason - Creator
     */
    public static <E extends Treasure> E makeTreasure(int id, Position pos) {
        if (id < 0 || id >= TreasureType.values().length) throw new TreasureNotFoundException("Invalid ID: " + id);

        Class<?> clazz = TreasureType.values()[id].getType();
        if (Treasure.class.isAssignableFrom(clazz)) {
            return makeTreasure((Class<E>) clazz, pos);
        }
        throw new TreasureNotFoundException("Class not applicable: " + clazz);
    }

    /**
     * Makes a treasure from the Class provided.
     *
     * @param clazz The class to create a treasure from
     * @param <E>   The treasure to return
     * @return The treasure
     * @author Jason - Creator
     */
    private static <E extends Treasure> E makeTreasure(Class<E> clazz, Position pos) {
        try {
            Constructor<E> con = clazz.getConstructor(Position.class);
            return con.newInstance(pos);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new TreasureNotFoundException("Class not applicable: " + clazz);
        }
    }
}
