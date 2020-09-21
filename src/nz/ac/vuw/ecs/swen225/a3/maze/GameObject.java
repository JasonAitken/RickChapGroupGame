package nz.ac.vuw.ecs.swen225.a3.maze;

import nz.ac.vuw.ecs.swen225.a3.application.util.GUIUtil;
import nz.ac.vuw.ecs.swen225.a3.maze.listeners.GameObjectListener;
import nz.ac.vuw.ecs.swen225.a3.maze.util.*;
import nz.ac.vuw.ecs.swen225.a3.renderer.ChapsCanvas;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * General abstract class that every object in the game should have, handles rendering, positioning and the listener
 * system
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 */
public abstract class GameObject implements Serializable {

    protected Image image;        //The representing image of this GameObject
    private Position position;
    private Type type;

    //Listeners for when something moves /to/ this objects position, and /before/ the event
    private Map<EventType, List<GameObjectListener>> preActionListeners;
    //Listeners for when something moves /to/ this objects position, and /after/ the event
    private Map<EventType, List<GameObjectListener>> postActionListeners;

    //Listeners to be fired on each game tick, mostly used by actors
    private List<GameObjectListener> onTickListeners;

    /**
     * Creates a game object using a position and a type
     *
     * @param pos  The position of the GameObject
     * @param type The type of the GameObject
     * @author Tim - Creaotr
     */
    protected GameObject(Position pos, Type type) {
        this.image = GUIUtil.ASSETS.get(type);
        this.position = pos;
        this.type = type;

        this.preActionListeners = new HashMap<>();
        this.postActionListeners = new HashMap<>();

        this.onTickListeners = new ArrayList<>();

        for (EventType eventType : EventType.values()) {
            preActionListeners.put(eventType, new ArrayList<>());
            postActionListeners.put(eventType, new ArrayList<>());
        }
    }

    /**
     * Creates a game object using just a type
     *
     * @param type The type of the GameObject
     * @author Tim - Creator
     */
    protected GameObject(Type type) {
        this.type = type;
        this.image = GUIUtil.ASSETS.get(type);
    }

    /**
     * Registers a movement-to listener to this game object, registered to either pre or post action depending on
     * the given place argument and registered to the event type depending on the given type argument
     *
     * @param place    Whether or not this listener should be fired pre or post the event
     * @param type     The type of event to register this event to
     * @param listener The actual listener
     * @author Tim - Creator
     */
    public void registerListener(PrePost place, EventType type, GameObjectListener listener) {
        Map<EventType, List<GameObjectListener>> relevantMap = place == PrePost.PRE ? preActionListeners : postActionListeners;
        relevantMap.get(type).add(listener);
    }

    /**
     * Registers a listener to be fired everytime a tick is processed
     *
     * @param listener The listener to register
     * @author Tim - Creator
     */
    public void registerOnTickListener(GameObjectListener listener) {
        this.onTickListeners.add(listener);
    }

    /**
     * Processes an event for this tile, loops through all registered listeners and executes them.
     *
     * @param event The event that happened
     * @author Tim - Creator
     */
    public boolean processEvent(PrePost place, EventType event, Board board) {
        Map<EventType, List<GameObjectListener>> relevantMap = place == PrePost.PRE ? preActionListeners : postActionListeners;
        boolean returned = true;
        for (GameObjectListener listener : relevantMap.get(event)) {
            if (!listener.execute(board)) {
                returned = false;
            }
        }

        return returned;
    }

    /**
     * Processes the on tick events
     *
     * @param board The board to process the events on
     * @author Tim - Creator
     */
    public void processOnTickEvent(Board board) {
        for (GameObjectListener listener : onTickListeners) {
            listener.execute(board);
        }
    }


    /**
     * Renders this GameObject on the provided graphics pane using the passed on x and y location. The width and height
     * of the image is stored in the canvas panel.
     *
     * @param g The graphics object in which to render the object on
     * @param x The x position of the object (left)
     * @param y The y position of the object (top)
     * @author Tim - Creator
     */
    public void render(Graphics g, int x, int y) {
        g.drawImage(image, x, y, ChapsCanvas.getTileWidth(), ChapsCanvas.getTileHeight(), null);
    }

    /**
     * Gets the image that represents this game object
     *
     * @return The image representing this game object.
     * @author Tim - Creator
     */
    public Image getImage() {
        return image;
    }

    /**
     * Gets the position of this GameObject.
     *
     * @return The position of this GameObject
     * @author Tim - Creator
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the position of this GameObject
     * @param position position to set
     * @author Max - Creator
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Gets the type of this GameObject.
     *
     * @return The type of this GameObject
     * @author Tim - Creator
     */
    public Type getType() {
        return type;
    }

    /**
     * Takes move type and moves player in direction specified, regardless of what exists
     * in that position.
     *
     * @param move move type
     * @author Fynn - Creator
     * @author Tim - Contributor
     */
    public void movePosition(Direction move) {
        this.position = this.position.add(move.getOffset());
    }

    /**
     * Gets the two-letter string representative of this game object
     *
     * @return The two letter string representative of this game object
     * @author Tim - Creator
     */
    public String getStringRepresentative() {
        return type.getStringRepresentative();
    }

}
