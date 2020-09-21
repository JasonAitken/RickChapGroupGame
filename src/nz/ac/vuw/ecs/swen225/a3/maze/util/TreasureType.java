/*

 */
package nz.ac.vuw.ecs.swen225.a3.maze.util;

import nz.ac.vuw.ecs.swen225.a3.maze.treasures.*;

/**
 * enum that holds information about all different types of consumables (treasures)
 * currently in the game
 *
 * @author Fynn - Creator
 */
public enum TreasureType implements Type {

    /**
     * add new stuff here as we need it
     */
    FILLER(0, "filler", Filler.class, null, -1, null, "Fi"),                                //0
    KEY_RED(1, "red key", KeyRed.class, "assets/tileSet/redKey.png", 0, true, "Kr"),        //1
    KEY_YELLOW(2, "yellow key", KeyYellow.class, "assets/tileSet/yellowKey.png", 1, true, "Ky"),    //2
    KEY_GREEN(3, "green key", KeyGreen.class, "assets/tileSet/greenKey.png", 2, false, "Kg"),        //3
    KEY_BLUE(4, "blue key", KeyBlue.class, "assets/tileSet/blueKey.png", 3, true, "Kb"),        //4
    CHIP(5, "chip", Chip.class, "assets/tileSet/chip.png", -1, null, "Ch"),
    SUCTION_BOOTS(6, "suction boots", SuctionBoots.class, "assets/tileSet/suctionBoots.png", 4, null, "Sb"),                //5
    ICE_BOOTS(7, "ice boots", IceBoots.class, "assets/tileSet/iceBoots.png", 5, null, "Ib"),                //6
    FIRE_BOOTS(8, "fire boots", FireBoots.class, "assets/tileSet/fireBoots.png", 6, null, "Fb"), //7
    FLIPPERS(9, "flippers", Flippers.class, "assets/tileSet/flippers.png", 7, null, "Fl"); //8


    private String name;
    private String filePath;
    private Class<? extends Treasure> type;
    private int slot;
    private int id;
    private Boolean consumeOnUse;
    private String stringRepresentative;


    /**
     * Creates an Treasure type with the relevant information required.
     *
     * @param name     The general "name" of the treasure
     * @param type     The representing class of the treasure
     * @param filePath The filepath to the image presenting this treasure
     * @author Fynn - Creator
     */
    TreasureType(int id, String name, Class<? extends Treasure> type, String filePath, int slot, Boolean consumeOnUse, String stringRepresentative) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.filePath = filePath;
        this.slot = slot;
        if (consumeOnUse == null) this.consumeOnUse = true;
        else this.consumeOnUse = consumeOnUse;
        this.stringRepresentative = stringRepresentative;
    }

    public Class<? extends Treasure> getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getFilePath() {
        return filePath;
    }

    @Override
    public String getStringRepresentative() {
        return stringRepresentative;
    }

    /**
     * Gets the slot that this item should be in in the inventory
     *
     * @return The slot number of the inventory
     * @author Max - Creator
     */
    public int getSlot() {
        return slot;
    }


    public boolean consumeOnUse() {
        return consumeOnUse;
    }
}
