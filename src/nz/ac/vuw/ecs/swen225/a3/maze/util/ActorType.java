package nz.ac.vuw.ecs.swen225.a3.maze.util;

import nz.ac.vuw.ecs.swen225.a3.maze.actors.*;

/**
 * Contains all important information about the actors that exist in the game.
 *
 * @author Tim - Creator
 */
public enum ActorType implements Type {

    FILLER(0, "filler", Filler.class, null, "Fi"),
    PLAYER(1, "player", Player.class, "assets/tileSet/chap.png", "Pl"),        //0
    BUG(2, "bug", Bug.class, "assets/tileSet/bug.png", "Bu"),         //1
    TANK(3, "tank", Tank.class, "assets/tileSet/tankRight.png", "Ta"),        //2
    GLIDER(4, "glider", Glider.class, "assets/tileSet/gliderRIGHT.png", "Gl"),      //3
    FIREBALL(5, "fireball", FireBall.class, "assets/tileSet/fireBall.png", "Fi"),    //4
    //THIEF(6, "thief", Thief.class, "assets/tileSet/thief.png"),       //5
    TEETH(6, "teeth", Teeth.class, "assets/tileSet/teeth.png", "Te");//6

    private String name;
    private Class<? extends Actor> clazz;
    private String filePath;
    private int id;
    private String stringRepresentative;

    /**
     * Creates an Actor type with the relevant information required.
     *
     * @param name     The general "name" of the actor
     * @param clazz    The representing class of the actor
     * @param filePath The filepath to the image presenting this actor
     * @author Tim - Creator
     */
    ActorType(int id, String name, Class<? extends Actor> clazz, String filePath, String stringRepresentative) {
        this.id = id;
        this.name = name;
        this.clazz = clazz;
        this.filePath = filePath;
        this.stringRepresentative = stringRepresentative;
    }

    @Override
    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Class<? extends Actor> getType() {
        return clazz;
    }

    @Override
    public String getFilePath() {
        return filePath;
    }

    @Override
    public String getStringRepresentative() {
        return stringRepresentative;
    }


}
