package nz.ac.vuw.ecs.swen225.a3.maze.util;

import nz.ac.vuw.ecs.swen225.a3.maze.tiles.*;

/**
 * Contains all important information about certain tiles that exist in the game.
 *
 * @author Tim - Creator
 */
public enum TileType implements Type {

    /**
     * Not sure if all of these should be defined as "tiles", but we can always change that when we get there.
     * For now here is a complete list of all the tiles I could find on the wikipedia along with their respective
     * IDs and representing classes.
     */
    FREE(0, "free", FreeTile.class, true, "assets/tileSet/free.png", "Fr"),//0
    WALL(1, "wall", WallTile.class, false, "assets/tileSet/wall.png", "Wl"),//1
    WATER(2, "water", WaterTile.class, true, "assets/tileSet/water.png", "Wr"),//2
    DIRT(3, "dirt", DirtTile.class, true, "assets/tileSet/dirt.png", "Di"),//3
    FIRE(4, "fire", FireTile.class, true, "assets/tileSet/fire.png", "Fe"),//4
    ICE(5, "ice", IceTile.class, true, "assets/tileSet/ice.png", "Ic"),//5
    FORCE_FLOOR(6, "force floor", ForceFloorTile.class, true, "assets/tileSet/forceFloor.png", "FF"),//6
    TOGGLE_WALL(7, "toggle wall", ToggleWallTile.class, true, "assets/tileSet/toggleWall.png", "TW"),//7
    BLUE_WALL(8, "blue wall", BlueWallTile.class, false, "assets/tileSet/blueWall.png", "BW"),//8
    HIDDEN_WALL(9, "hidden wall", HiddenWallTile.class, true, "assets/tileSet/free.png", "HW"),//9
    INVISIBLE_WALL(10, "invisible wall", InvisibleWallTile.class, false, "assets/tileSet/free.png", "IW"),//10
    GRAVEL(11, "gravel", GravelTile.class, true, "assets/tileSet/gravel.png", "Gr"),//11
    TELEPORT(12, "teleport", TeleportTile.class, true, "assets/tileSet/teleport.png", "Tp"),//12
    RECESSED_WALL(13, "recessed wall", RecessedWallTile.class, true, "assets/tileSet/recessedWall.png", "RW"),//13
    TRAP(14, "trap", TrapTile.class, true, "assets/tileSet/trap.png", "Tr"),//14
    BOMB(15, "bomb", BombTile.class, true, "assets/tileSet/bomb.png", "Bm"),//15
    SOCKET(16, "socket", SocketTile.class, true, "assets/tileSet/gate.png", "Sk"),//16
    HINT(17, "hint", HintTile.class, true, "assets/tileSet/hint.png", "Hi"),//17
    REDDOOR(18, "red door", RedDoorTile.class, true, "assets/tileSet/redDoor.png", "RD"),//18
    BLUEDOOR(19, "blue door", BlueDoorTile.class, true, "assets/tileSet/blueDoor.png", "BD"),//19
    GREENDOOR(20, "green door", GreenDoorTile.class, true, "assets/tileSet/greenDoor.png", "GD"),//20
    YELLOWDOOR(21, "yellow door", YellowDoorTile.class, true, "assets/tileSet/yellowDoor.png", "YD"),//21
    EXIT(22, "exit", ExitTile.class, true, "assets/tileSet/warpPad.png", "Ex"),
    FORCE_FLOOR_UP(23, "force floor up", ForceFloorUpTile.class, true, "assets/tileSet/ForceMoveUp.png", "FU"),//23
    FORCE_FLOOR_DOWN(24, "force floor down", ForceFloorDownTile.class, true, "assets/tileSet/ForceMoveDown.png", "FD"),//24
    FORCE_FLOOR_LEFT(25, "force floor left", ForceFloorLeftTile.class, true, "assets/tileSet/ForceMoveLeft.png", "FL"),//25
    FORCE_FLOOR_RIGHT(26, "force floor right", ForceFloorRightTile.class, true, "assets/tileSet/ForceMoveRight.png", "FR"),//26
    BLUE_BUTTON(27, "blue button", BlueButton.class, true, "assets/tileSet/blueButton.png", "Bb"),//23
    GREEN_BUTTON(28, "green button", GreenButton.class, true, "assets/tileSet/greenButton.png", "Gb"),//24
    RED_BUTTON(29, "red button", RedButton.class, true, "assets/tileSet/redButton.png", "Rb"),//25
    BROWN_BUTTON(30, "brown button", BrownButton.class, true, "assets/tileSet/brownButton.png", "Br"),
    BLUE_FREE(31, "blue free", BlueFreeTile.class, true, "assets/tileSet/blueWall.png", "Bf"),//26
	TIMER_WALL(32, "timer tile", TimerWallTile.class, true, "assets/tileSet/timerWall.png", "TW");//27

//    RANDOM_FORCE_FLOOR( "random force floor",   RandomForceFloorTile.class, true);  //17


    private String name;
    private Class<? extends Tile> clazz;
    private boolean walkable;
    private String filePath;
    private String representative;
    private int id;

    /**
     * Creates an Tile type with the relevant information required.
     *
     * @param name           The general "name" of the tile
     * @param clazz          The representing class of the tile
     * @param walkable       Whether or not an actor is allowed to walk on this tile
     * @param filePath       The filepath to the image presenting this tile
     * @param representative The string representative for this tile
     * @author Tim      - Creator
     * @author Mike     - Contributor
     * @author Tim - Creator
     */
    TileType(int id, String name, Class<? extends Tile> clazz, boolean walkable, String filePath, String representative) {
        this.id = id;
        this.name = name;
        this.clazz = clazz;
        this.walkable = walkable;
        this.filePath = filePath;
        this.representative = representative;
    }

    @Override
    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getFilePath() {
        return filePath;
    }

    @Override
    public String getStringRepresentative() {
        return representative;
    }

    @Override
    public Class<? extends Tile> getType() {
        return clazz;
    }


    /**
     * Gets whether or not an actor is able to walk on this type of tile
     *
     * @return Whether or not an actor can walk on this type of tile
     */
    public boolean isWalkable() {
        return walkable;
    }
}
