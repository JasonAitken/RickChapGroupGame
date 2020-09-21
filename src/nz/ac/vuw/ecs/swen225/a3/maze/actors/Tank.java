package nz.ac.vuw.ecs.swen225.a3.maze.actors;


import nz.ac.vuw.ecs.swen225.a3.application.exceptions.AssetNotFoundException;
import nz.ac.vuw.ecs.swen225.a3.maze.util.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Tanks only ever move straight. They turn around when ANY blue button is pressed.
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 */
public class Tank extends Actor {

    private static final Set<TileType> WALKABLE_TILES = new HashSet<>(Collections.singletonList(TileType.FIRE));
    private static Image[] images = new Image[2];
    static {
        images[0] = loadImages("assets/tileSet/tankLeft.png");
        images[1] = loadImages("assets/tileSet/tankRight.png");
    }

    public Tank(Position pos) {
        super(pos, ActorType.TANK);
        this.setDirection(Direction.RIGHT);

        //Registers an onTick listeners
        this.registerOnTickListener((board) -> {
            board.moveActor(getDirection(), this);

            return true;
        });

        //Registers a listener for when the blue button is pressed
        this.registerListener(PrePost.PRE, EventType.BLUE_BUTTON_PRESSED, (board) -> {
            this.setDirection(this.getDirection().inverse());
            switch(this.getDirection()){
                case LEFT:
                    this.image = images[0];
                    break;
                case RIGHT:
                    this.image = images[1];
                    break;
            }
            return true;
        });
    }

    /**
     * Loads an image from the specified file path.
     *
     * @param pathName  The file path to load an image from
     * @return          The image loaded
     * @author Fynn - Creator
     */
    private static Image loadImages(String pathName) {
        try {
            return ImageIO.read(new File(pathName));
        } catch (Exception e) {
            throw new AssetNotFoundException(pathName + ": File not found");
        }
    }

    @Override
    public Set<TileType> getActorWalkableTiles() {
        return WALKABLE_TILES;
    }
}
