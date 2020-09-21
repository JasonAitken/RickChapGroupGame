package nz.ac.vuw.ecs.swen225.a3.persistence;

import nz.ac.vuw.ecs.swen225.a3.application.GUI;
import nz.ac.vuw.ecs.swen225.a3.application.util.FileUtil;
import nz.ac.vuw.ecs.swen225.a3.maze.Board;
import nz.ac.vuw.ecs.swen225.a3.maze.ReplayBoard;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.Actor;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.Player;
import nz.ac.vuw.ecs.swen225.a3.maze.tiles.Tile;
import nz.ac.vuw.ecs.swen225.a3.maze.treasures.Treasure;
import nz.ac.vuw.ecs.swen225.a3.maze.util.ArrayUtil;
import nz.ac.vuw.ecs.swen225.a3.maze.util.MoveInfo;
import nz.ac.vuw.ecs.swen225.a3.maze.util.ObjectFactory;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;

import javax.json.*;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Arrays;


/**
 * Level Factory class created to read in JSON file formats based on a level token, which is then passed to application
 *
 * @author Mike - Creator
 */
public class LevelFactory {
    private final static int MAX_LEVEL = 3;
    private final static int MIN_LEVEL = 1;
    private final static String LEVEL_FOLDER = "assets/levels/level";
    private final static String FILE_FORMAT = ".json";

    /**
     * @param levelID the ID of the level you wish to load
     * @return The beginning state of that level
     * @author Mike - Creator
     */
    public static Board readFile(int levelID) {
        //Pre-checks
        assert levelID >= MIN_LEVEL;
        assert levelID <= MAX_LEVEL;
        //TODO Testing

        //Build the path of the file we will be accessing
        String path = LEVEL_FOLDER + levelID + FILE_FORMAT;


        //We will read in the entire file to this string
        String file = null;
        try {
            file = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Ensure we succeeded in reading the file.
        assert file != null;
        //Create a reader
        JsonReader reader = Json.createReader(new StringReader(file));
        //Get the toplevel Object. Close the reader.
        JsonObject topLevel = reader.readObject();
        reader.close();

        //Parse each layer and attribute into the respective places.

        Tile[][] tiles = parseTiles(topLevel.getJsonObject("tiles"));
        Treasure[][] treasures = parseTreasures(topLevel.getJsonObject("treasures"));
        Player rick = parsePlayer(topLevel.getJsonObject("player"));
        Actor[][] actors = parseActors(topLevel.getJsonObject("actors"));

        int chipsRequired = topLevel.getInt("chipsRequired");
        int time = topLevel.getInt("time");
        String hintText = topLevel.getString("hint");

        Boolean unlockedFile = topLevel.getBoolean("unlocked");

        //Make the new board and return it
        Board ret = new Board(tiles, treasures, actors, rick, chipsRequired, hintText, time * GUI.TICKS_PER_SECOND, unlockedFile);
        //Post Checks
        assert ret.getBoardUnlocked() == unlockedFile;

        assert Arrays.deepEquals(ret.getTileLayer(), tiles);
        assert ret.getTileLayer().length == Board.BOARD_WIDTH;
        assert ret.getTileLayer()[0].length == Board.BOARD_HEIGHT;

        assert Arrays.deepEquals(ret.getTreasureLayer(), treasures);
        assert ret.getPlayer().equals(rick);
        assert ret.getChipsLeft() == chipsRequired;

        //Make sure the hint text is not an empty string or null
        assert !ret.getHintText().isEmpty();
        assert ret.getHintText() != null;

        //TODO make some more post checks

        return ret;
    }

    /**
     * @param file the file we're checking
     * @return The beginning state of that level
     * @author Mike - Creator
     */
    public static Board readFile(File file) {
        //Pre-checks
        assert !file.isDirectory();
        assert FileUtil.getExtension(file).equals("json");

        //TODO Testing

        //TODO Make it not get mad when there are things that
        // don't exist that don't need to be there


        //We will read in the entire file to this string
        String fileString;
        fileString = file.toString();

        //Create a reader
        JsonReader reader = Json.createReader(new StringReader(fileString));
        //Get the toplevel Object. Close the reader.
        JsonObject topLevel = reader.readObject();
        reader.close();

        //Parse each layer and attribute into the respective places.
        Tile[][] tiles = parseTiles(topLevel.getJsonObject("tiles"));
        Treasure[][] treasures = parseTreasures(topLevel.getJsonObject("treasures"));
        Player rick = parsePlayer(topLevel.getJsonObject("player"));
        Actor[][] actors = parseActors(topLevel.getJsonObject("actors"));

        //Items that are their own attributes and don't need to be parsed.
        int chipsRequired = topLevel.getInt("chipsRequired");
        int time = topLevel.getInt("time");
        String hintText = topLevel.getString("hint");

        Boolean unlockedFile = topLevel.getBoolean("unlocked");

        //Make the new board and return it
        Board ret = new Board(
                tiles,
                treasures,
                actors,
                rick,
                chipsRequired,
                hintText,
                time * GUI.TICKS_PER_SECOND,
                unlockedFile);
        //Post Checks
        assert ret.getBoardUnlocked() == unlockedFile;

        assert Arrays.deepEquals(ret.getTileLayer(), tiles);
        assert ret.getTileLayer().length == Board.BOARD_WIDTH;
        assert ret.getTileLayer()[0].length == Board.BOARD_HEIGHT;

        assert Arrays.deepEquals(ret.getTreasureLayer(), treasures);
        assert ret.getPlayer().equals(rick);
        assert ret.getChipsLeft() == chipsRequired;

        //Make sure the hint text is not an empty string or null
        assert !ret.getHintText().isEmpty();
        assert ret.getHintText() != null;

        //TODO make some more post checks

        return ret;
    }

    public static ReplayBoard readReplayFile(String filePath){
        ReadReplay r = new ReadReplay(filePath);
        ArrayDeque<ArrayDeque<MoveInfo>> infoList = new ArrayDeque<ArrayDeque<MoveInfo>>();
        infoList = r.getMoves();

        String path = LEVEL_FOLDER + r.getLevel() + FILE_FORMAT;
        //We will read in the entire file to this string
        String file = null;
        try {
            file = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Ensure we succeeded in reading the file.
        assert file != null;
        //Create a reader
        JsonReader reader = Json.createReader(new StringReader(file));
        //Get the toplevel Object. Close the reader.
        JsonObject topLevel = reader.readObject();
        reader.close();

        //Parse each layer and attribute into the respective places.

        Tile[][] tiles = parseTiles(topLevel.getJsonObject("tiles"));
        Treasure[][] treasures = parseTreasures(topLevel.getJsonObject("treasures"));
        Player rick = parsePlayer(topLevel.getJsonObject("player"));
        Actor[][] actors = parseActors(topLevel.getJsonObject("actors"));
        return new ReplayBoard(tiles, treasures, actors, rick, infoList);
    }

    /**
     * Parses a given JsonObject and returns the Actor instances.
     *
     * @param toplevel Must be the "actors" JsonObject.
     * @author Mike - Creator
     * @return A 2d array of actors.
     */

     private static Actor[][] parseActors(JsonObject toplevel) {
        //TODO Use streams!
        //TODO Pre and post checks
        //TODO Testing

        //Make somewhere to put the tiles
        Actor[][] actors = new Actor[Board.BOARD_HEIGHT][Board.BOARD_WIDTH];

        //Get the item that we really want (positions and ID's)
        JsonArray rows = toplevel.getJsonArray("rows");

        //Make a dummy 2d array for the ID's to be stored in
        Object[][] genericTiles = parseRows(rows);

        //Loop through all ID's
        for (int y = 0; y < genericTiles.length; y++) {
            assert genericTiles[y].length == Board.BOARD_WIDTH;
            for (int x = 0; x < genericTiles[y].length; x++) {
                //Store the new instance in the appropriate place we made for the tiles.
                actors[y][x] = ObjectFactory.makeActor(
                        Integer.parseInt(genericTiles[y][x].toString()),
                        new Position(x, y)
                );
            }
        }

        return actors;
    }

    /**
     * Parses a given JsonObject and returns the Tile instances.
     *
     * @param tile Must be the "tiles" JsonObject.
     * @author Mike - Creator
     * @return A 2d array of tiles.
     */
    private static Tile[][] parseTiles(JsonObject tile) {
        //TODO Use streams!
        //TODO Pre and post checks
        //TODO Testing

        //Make somewhere to put the tiles
        Tile[][] tiles = new Tile[Board.BOARD_HEIGHT][Board.BOARD_WIDTH];

        //Get the item that we really want (positions and ID's)
        JsonArray rows = tile.getJsonArray("rows");

        //Make a dummy 2d array for the ID's to be stored in
        Object[][] genericTiles = parseRows(rows);

        //Loop through all ID's
        for (int y = 0; y < genericTiles.length; y++) {
            assert genericTiles[y].length == Board.BOARD_WIDTH;
            for (int x = 0; x < genericTiles[y].length; x++) {
                //Store the new instance in the appropriate place we made for the tiles.
                tiles[y][x] = ObjectFactory.makeTile(
                        Integer.parseInt(genericTiles[y][x].toString()),
                        new Position(x, y)
                );
            }
        }

        return tiles;
    }

    /**
     * Parses a given JsonObject and returns the Treasure instances.
     *
     * @param treasure Must be the "tiles" JsonObject.
     * @return A 2d array of tiles.
     * @author Mike - Creator
     */
    private static Treasure[][] parseTreasures(JsonObject treasure) {
        //TODO Pre and post checks
        //TODO Use streams!
        //TODO Testing!
        //TODO Generify the 'parse' methods?
        //Make somewhere to put the treasures
        Treasure[][] treasures = new Treasure[Board.BOARD_HEIGHT][Board.BOARD_WIDTH];

        //Get the item that we really want (positions and ID's)
        JsonArray rows = treasure.getJsonArray("rows");

        //Make a dummy 2d array for the ID's to be stored in
        Object[][] genericTiles = parseRows(rows);

        for (int y = 0; y < genericTiles.length; y++) {
            assert genericTiles[y].length == Board.BOARD_WIDTH;
            for (int x = 0; x < genericTiles[y].length; x++) {
                treasures[y][x] = ObjectFactory.makeTreasure(
                        Integer.parseInt(genericTiles[y][x].toString()),
                        new Position(x, y)
                );
            }
        }

        return treasures;
    }

    /**
     * Parses a given JsonObject and returns the Position specified.
     *
     * @param player Must be of the "player" JsonObject.
     * @author Mike - Creator
     * @return The position.
     */
    private static Player parsePlayer(JsonObject player) {
        //TODO Testing!
        //TODO Pre and post checks
        Position pos = Position.fromObjArray(player.getJsonArray("position").toArray());
        int[] inventory = ArrayUtil.toIntArray(player.getJsonArray("inventory").toArray());

        return new Player(pos, inventory);
    }

    /**
     * Parses a given JsonObject and returns the values as a 2d array.
     *
     * @param json Must be of the "row" JsonObject.
     * @author Mike - Creator
     * @return A 2d Object array, usually containing ID's.
     */
    private static Object[][] parseRows(JsonArray json) {
        //TODO Pre and post checks
        //TODO Testing!
        Object[][] cellArray = new Object[Board.BOARD_HEIGHT][Board.BOARD_WIDTH];

        for (int i = 0; i < json.size(); i++) {
            JsonValue row = json.get(i);
            JsonObject rowObject = row.asJsonObject().getJsonObject("row");
            JsonArray cells = rowObject.getJsonArray("cells");

            cellArray[i] = cells.asJsonArray().toArray();
        }
        return cellArray;
    }
}
