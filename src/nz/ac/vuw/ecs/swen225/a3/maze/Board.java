package nz.ac.vuw.ecs.swen225.a3.maze;

import nz.ac.vuw.ecs.swen225.a3.application.GUI;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.Actor;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.Filler;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.Player;
import nz.ac.vuw.ecs.swen225.a3.maze.tiles.Tile;
import nz.ac.vuw.ecs.swen225.a3.maze.treasures.Treasure;
import nz.ac.vuw.ecs.swen225.a3.maze.util.*;
import nz.ac.vuw.ecs.swen225.a3.persistence.SaveReplay;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO: big boi comment
 *
 * @author Mike
 * @author Fynn
 * @author Tim
 */
public class Board implements Serializable {
    private GUI gui;

    //Dimensions of the board
    public static final int BOARD_WIDTH = 30;
    public static final int BOARD_HEIGHT = 30;

    //Initialized in the constructor
    private Tile[][] tiles;
    private Treasure[][] treasures;
    private Actor[][] actors;
    private Player player;
    private int chipsLeft;
    private String hintText;

    private ArrayDeque<ArrayDeque<MoveInfo>> moves = new ArrayDeque<>();
    private ArrayDeque<MoveInfo> currentMoveQueue = new ArrayDeque<>();
    private int currentTick = 0;

    private int levelNumber;

    private int loadedTime;
    private Boolean boardUnlocked;


    /**
     * The board class, used for game logic and rendering.
     *
     * @author Mike - Creator
     * @author Tim - Contribution
     */
    public Board(Tile[][] tiles, Treasure[][] treasures, Actor[][] actors,
                 Player rick, int chipsRequired, String hintText, int time, Boolean unlocked) {
        this.setTiles(tiles);
        this.setTreasures(treasures);
        this.setActors(actors);
        this.player = rick;
        this.chipsLeft = chipsRequired;
        this.hintText = hintText;
        this.loadedTime = time;
        this.boardUnlocked = unlocked;
    }

    /**
     * The board class, used for game logic and rendering.
     *
     * @author Mike - Creator
     * @author Tim - Contribution
     */
    public Board(Tile[][] tiles, Treasure[][] treasures, Actor[][] actors,
                 Player rick, int chipsRequired, String hintText, int time) {
        this(tiles, treasures, actors, rick, chipsRequired, hintText, time, false);
    }


    /**
     * The board class, used for game logic and rendering.
     *
     * @author Mike - Creator
     * @author Tim - Contribution
     */
    public Board(Tile[][] tiles, Treasure[][] treasures, Actor[][] actors,
                 Player rick, int chipsRequired, String hintText) {
        this(tiles, treasures, actors, rick, chipsRequired, hintText, 0);
    }
    /**
     * Gets the main player on this board
     *
     * @return The main player on this board
     */
    public Player getPlayer() {
        return player;
    }
    
    public void setGui(GUI gui) {
    	this.gui = gui;
        gui.setTime(loadedTime);
    }
    
    public GUI getGui() {
    	return this.gui;
    }

    /**
     * Checks if the move you provide is valid given the board.
     *
     * @param move the move you're wanting to know if it's valid
     * @return whether it's valid
     * @author Mike - Creator
     * @author Tim - Contributor
     */
    public boolean isValid(Direction move, Actor mover) {
        Tile targetTile = getTargetTile(move, mover);

        if (targetTile == null) return false;

        //Chip can't move on top of other actors?
        if (!(actors[targetTile.getPosition().getY()][targetTile.getPosition().getX()] instanceof Filler)) return false;

        if (mover instanceof Player) {
            return targetTile.isWalkable();
        } else {
            return mover.getWalkableTiles().contains(targetTile.getType());
        }
    }
    
    public void setLevelNumber(int level) {
    	this.levelNumber = level;
    }

    /**
     * Gets the target tile from the given move and actor
     *
     * @param move  The move
     * @param mover The actor
     * @return The target tile
     * @author Tim - Creator
     */
    public Tile getTargetTile(Direction move, Actor mover) {
        if (move == Direction.NONE) return null;
        Position targetPosition = mover.getPosition().add(move.getOffset());

        if (targetPosition.getX() < 0 || targetPosition.getX() >= BOARD_WIDTH) return null;
        if (targetPosition.getY() < 0 || targetPosition.getY() >= BOARD_HEIGHT) return null;

        return tiles[targetPosition.getY()][targetPosition.getX()];
    }


    /**
     * simple getter to return the tile layer
     *
     * @return the tile layer
     * @author Mike - Creator
     */
    public Tile[][] getTileLayer() {
        return tiles.clone();
    }

    /**
     * simple getter to return the treasure layer
     *
     * @return the treasure layer
     * @author Mike - Creator
     */
    public Treasure[][] getTreasureLayer() {
        return treasures.clone();
    }

    /**
     * Gets the actor layer
     *
     * @return The actor layer
     * @author Tim - Creator
     */
    public Actor[][] getActorLayer() {
        return actors.clone();
    }

    /**
     * @param tiles The tile layer to set
     * @author Mike
     * Sets the tile layer of this board, clones the passed parameter to remove any chance of stray references
     * @author Mike - Creator
     */
    private void setTiles(Tile[][] tiles) {
        this.tiles = tiles.clone();
    }

    /**
     * Sets the treasure layer of this board, clones the passed parameter to remove any chance of stray references
     *
     * @param treasures The treasure layer to set
     * @author Mike - Creator
     */
    private void setTreasures(Treasure[][] treasures) {
        this.treasures = treasures.clone();
    }

    /**
     * Sets the actor layer of this board, clones the passed parameter to remove any chance of stray references
     *
     * @param actors The actor layer to set
     * @author Tim - Creator
     */
    private void setActors(Actor[][] actors) {
        this.actors = actors.clone();
    }

    /**
     * Processes a game tick - Calls all of the ontick listeners and also moves the player if they need to be moved
     *
     * @author Tim - Creator
     */
    public void processTick() {
        currentTick++;
        this.movePlayer();
        this.processEvent(tiles, null, null);
        this.processEvent(treasures, null, null);
        this.processEvent(actors, null, null);

        if(currentMoveQueue.size() > 0) {
            moves.add(currentMoveQueue);
        }

        currentMoveQueue = new ArrayDeque<MoveInfo>();
    }

    /**
     * Gets the current tick that the game is on
     *
     * @return The current tick that the game is on
     * @author Tim - Creator
     */
    public int getCurrentTick() {
        return currentTick;
    }


    /**
     * Moves the given actor to the tile in the given position
     *
     * @param direction The direction in which to move the actor
     * @param actor     The actor in which to move
     * @author Tim - Creator
     */
    public void moveActor(Direction direction, Actor actor) {
        if (!this.isValid(direction, actor)) return;
        Tile targetTile = this.getTargetTile(direction, actor);
        Tile currentTile = tiles[actor.getPosition().getY()][actor.getPosition().getX()];

        if (targetTile == null || currentTile == null) return;


        currentMoveQueue.push(new MoveInfo(actor.getPosition(), direction.getMove()));
        targetTile.processEvent(PrePost.PRE, EventType.MONSTER_MOVED_TO, this);
        currentTile.processEvent(PrePost.PRE, EventType.CHIP_MOVED_FROM, this);

        actors[targetTile.getPosition().getY()][targetTile.getPosition().getX()] = actor;
        actors[actor.getPosition().getY()][actor.getPosition().getX()] = new Filler(actor.getPosition());

        actor.movePosition(direction);

        targetTile.processEvent(PrePost.POST, EventType.MONSTER_MOVED_TO, this);
        currentTile.processEvent(PrePost.POST, EventType.CHIP_MOVED_FROM, this);
    }

    /**
     * Processes an event on the given 2d array of GameObjects with the given parameters
     *
     * @param layer   The layer in which to execute the event on
     * @param prePost Whether or not the event is executed before or after the given action
     * @param event   The event itself
     * @author Tim - Creator
     */
    public void processEvent(GameObject[][] layer, PrePost prePost, EventType event) {
        Set<GameObject> processedObjects = new HashSet<>();

        //Stream through all of the gameobjects, filter out any filler ones
        Arrays.stream(layer).
                forEach((row) -> Arrays.stream(row).
                        filter((gameObject -> !gameObject.getType().getName().equals("filler"))).
                        forEach((gameObject) -> {
                            if (processedObjects.add(gameObject)) {
                                if (event == null) {
                                    gameObject.processOnTickEvent(this);
                                } else {
                                    gameObject.processEvent(prePost, event, this);
                                }
                            }
                        }));
    }

    /**
     * Saves the current board to the specified file path
     *
     * @param s The file path
     * @author Fynn - Creator
     */
    public void saveBoardReplay(String s) {
        new SaveReplay(moves, s, Integer.toString(gui.getLevelNumber()));

    }

    /**
     * Moves the player if they have a move
     *
     * @author Fynn - Creator
     * @author Max - Contributer
     * @author Tim - Contributer
     */
    public void movePlayer() {
        Position p = player.getPosition();
        Direction move = player.getMove();
        if (player.getNextForcedMove() != Direction.NONE) {
            move = player.getNextForcedMove();
        }
        Tile targetTile;

        if (this.isValid(move, player)) {
//            Treasure pickup = treasures[player.getPosition().getY()][player.getPosition().getX()];

            targetTile = getTargetTile(move, player);//tiles[player.getPosition().getY()][player.getPosition().getX()];
            Tile currentTile = tiles[player.getPosition().getY()][player.getPosition().getX()];

            //If all pre event listeners say good to go dude, then go chur
            if ((targetTile != null && targetTile.processEvent(PrePost.PRE, EventType.CHIP_MOVED_TO, this)) &&
                    (currentTile != null && currentTile.processEvent(PrePost.PRE, EventType.CHIP_MOVED_FROM, this))) {
                currentMoveQueue.push(new MoveInfo(p, move.getMove()));
                player.movePosition(move);
                player.setNextForcedMove(Direction.NONE);

                treasures[targetTile.getPosition().getY()][targetTile.getPosition().getX()].processEvent(PrePost.POST, EventType.CHIP_MOVED_TO, this);
                targetTile.processEvent(PrePost.POST, EventType.CHIP_MOVED_TO, this);
                currentTile.processEvent(PrePost.POST, EventType.CHIP_MOVED_FROM, this);
            }

            player.setMove(Direction.NONE);
        }
    }

    /**
     * Gets the number of chips left to collect
     *
     * @return The number of chips left to collect
     * @author Tim - Creator
     */
    public int getChipsLeft() {
        return chipsLeft;
    }

    /**
     * Decrements the chips left count.
     */
    public void pickupChip() {
        if (chipsLeft > 0) chipsLeft--;
    }

    public void setTileAt(int x, int y, Tile tile) {
        if (x < 0 || y < 0) throw new ArrayIndexOutOfBoundsException();
        if (x >= BOARD_WIDTH || y >= BOARD_HEIGHT) throw new ArrayIndexOutOfBoundsException();

        tiles[y][x] = tile;
    }

    /**
     * Turns the tile layer into a string, used for tests
     *
     * @return The tile layer as a string
     * @author Tim - Creator
     */
    public String tilesToString() {
        StringBuilder builder = new StringBuilder();

        //Build the map
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                builder.append(tile.getStringRepresentative()).append("|");
            }
            builder.append("\n");
        }

        //Calculate where the player is on the map
        int playerPos = ((player.getPosition().getY() * BOARD_WIDTH) + player.getPosition().getX()) * 3;
        playerPos += player.getPosition().getY();

        //Change the characters at player position to something that can represent the player
        builder.setCharAt(playerPos, 'P');
        builder.setCharAt(playerPos + 1, 'l');

        return builder.toString();
    }

    /**
     * Turns the actor layer into a string, used for tests
     *
     * @return The actor layer as a string
     * @author Tim - Creator
     */
    public String actorsToString() {
        StringBuilder builder = new StringBuilder();

        //Build the map
        for (Actor[] row : actors) {
            for (Actor actor : row) {
                builder.append(actor.getStringRepresentative()).append("|");
            }
            builder.append("\n");
        }

        //Calculate where the player is on the map
        int playerPos = ((player.getPosition().getY() * BOARD_WIDTH) + player.getPosition().getX()) * 3;
        playerPos += player.getPosition().getY();

        //Change the characters at player position to something that can represent the player
        builder.setCharAt(playerPos, 'P');
        builder.setCharAt(playerPos + 1, 'l');

        return builder.toString();
    }

    /**
     * Turns the treasure layer into a string, used for tests
     *
     * @return The treasure layer as a string
     * @author Tim - Creator
     */
    public String treasuresToString() {
        StringBuilder builder = new StringBuilder();

        //Build the map
        for (Treasure[] treasure : treasures) {
            for (Treasure actor : treasure) {
                builder.append(actor.getStringRepresentative()).append("|");
            }
            builder.append("\n");
        }

        //Calculate where the player is on the map
        int playerPos = ((player.getPosition().getY() * BOARD_WIDTH) + player.getPosition().getX()) * 3;
        playerPos += player.getPosition().getY();

        //Change the characters at player position to something that can represent the player
        builder.setCharAt(playerPos, 'P');
        builder.setCharAt(playerPos + 1, 'l');

        return builder.toString();
    }

    /**
     * Gets the level number that the player is currently on
     *
     * @return The level number the player is currently on
     * TODO: Author - not sure who did it
     */
    public int getLevelNumber() {
        return levelNumber;
    }

    /**
     * returns a String of JSON representing this board.
     *
     * @return the String
     * @author Mike - Creator
     */
    public String toJson() {

        String str = "{\n" +
                player.toJson() +
                tilesToJson() +
                treasuresToJson() +
                chipsRequiredToJson() +
                actorsToJson() +
                "\n}";
        return str;
    }

    /**
     * Returns a string JSON representing the tile layer.
     *
     * @return The tile layer represented as a JSON string
     * @author Mike - Creator
     */
    public String tilesToJson() {
        final String preRows = "\"tiles\": {\n" +
                "    \"rows\": [";

        final String preRow = "\n      {" +
                "\n        \"row\": {" +
                "\n          \"cells\": [";

        final String postRow = "]\n" +
                "        }\n" +
                "      }";

        final String postRows = "    ]\n" +
                "  }";

        StringBuilder stringBuilder = new StringBuilder();


        //Loop through all the cells in the board
        stringBuilder.append(preRows);
        for (int y = 0; y < tiles.length; y++) {

            stringBuilder.append(preRow);
            Tile[] row = tiles[y];
            for (int x = 0; x < row.length; x++) {
                stringBuilder.append(row[x].getType().getID());
                //TODO get an 'effective' id instead of actual id

                //Make sure commas are only between items (not at the end)
                if (row.length - x > 1) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append(postRow);

            //Make sure commas are only between items (not at the end)
            if (tiles.length - y > 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(postRows);

        return stringBuilder.toString();
    }

    /**
     * Returns a string JSON representing the treasure layer.
     *
     * @return The treasure layer represented as a JSON string
     * @author Mike - Creator
     */
    public String treasuresToJson() {
        final String preRows = "\"treasures\": {\n" +
                "    \"rows\": [";

        final String preRow = "\n      {" +
                "\n        \"row\": {" +
                "\n          \"cells\": [";

        final String postRow = "]\n" +
                "        }\n" +
                "      }";

        final String postRows = "    ]\n" +
                "  }";

        StringBuilder stringBuilder = new StringBuilder();

        Treasure[][] treasures = getTreasureLayer();

        //Loop through all the cells in the board
        stringBuilder.append(preRows);
        for (int y = 0; y < treasures.length; y++) {

            stringBuilder.append(preRow);
            Treasure[] row = treasures[y];
            for (int x = 0; x < row.length; x++) {
                //If this treasure is not a filler
                if (row[x].getType() != TreasureType.FILLER) {
                    //has it been picked up?
                    if (row[x].isPickedUp()) {
                        stringBuilder.append(TreasureType.FILLER.getID());
                    } else {
                        stringBuilder.append(row[x].getType().getID());
                    }
                } else {
                    stringBuilder.append(row[x].getType().getID());
                }

                //Make sure commas are only between items (not at the end)
                if (row.length - x > 1) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append(postRow);

            //Make sure commas are only between items (not at the end)
            if (treasures.length - y > 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(postRows);

        return stringBuilder.toString();
    }
    /**
     * Returns a string JSON representing the actor layer.
     *
     * @return The actor layer represented as a JSON string
     * @author Mike - Creator
     */
    public String actorsToJson() {
        final String preRows = "\"actors\": {\n" +
                "    \"rows\": [";

        final String preRow = "\n      {" +
                "\n        \"row\": {" +
                "\n          \"cells\": [";

        final String postRow = "]\n" +
                "        }\n" +
                "      }";

        final String postRows = "    ]\n" +
                "  }";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(preRows);

        //Loop through all the cells in the board
        for (int y = 0; y < actors.length; y++) {

            stringBuilder.append(preRow);
            Actor[] row = actors[y];
            for (int x = 0; x < row.length; x++) {
                stringBuilder.append(row[x].getType().getID());

                //Make sure commas are only between items (not at the end)
                if (row.length - x > 1) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append(postRow);

            //Make sure commas are only between items (not at the end)
            if (actors.length - y > 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append(postRows);

        return stringBuilder.toString();
    }

    /**
     * Returns the number of chips required as a JSON formatted string.
     *
     * @return the string.
     * @author Mike - Creator
     */
    public String chipsRequiredToJson() {
        assert chipsLeft <= 0;
        return "\"chipsRequired\": " + chipsLeft;
    }

    /**
     * Gets the level's hint text.
     * @return The hint text
     * @author Max - Creator
     * @author mike - Contributor
     */
    public String getHintText() {
        return hintText;
    }

    /**
     * Gets the level's hint text as a json string
     *
     * @return the string
     * @author mike - creator
     */
    public String getHintTextJson() {
        return "\"hint\": \"" + hintText + "\"";
    }

    /**
     * Moves the player from one position to another.
     * @param from the position to move from
     * @param to the position to move to
     * @author Max - Creator
     */
    public void moveActorTo(Position from, Position to) {
        if (player.getPosition().equals(from)) {
            player.setPosition(to);
        }
    }

    /**
     * Kills the player and displays a message.
     * @param deathMessage The message to display
     * @author Max - Creator
     */
    public void killPlayer(String deathMessage) {
        if (gui.getTimer() != null) {
            gui.getTimer().cancel();
			moves = new ArrayDeque<>();
            JOptionPane.showMessageDialog(null, deathMessage);
            gui.returnToMenu();
        }
    }

    /**
     * Returns if this board has been completed or not.
     *
     * @return Whether or not the board has been completed
     * @author Jason - Creator
     */
    public Boolean getBoardUnlocked() {
        return boardUnlocked;
    }

    /**
     * Sets whether or not this board has been completed.
     *
     * @author Jason - Creator
     */
    public void setBoardUnlocked(Boolean value) {
        this.boardUnlocked = value;
    }
}