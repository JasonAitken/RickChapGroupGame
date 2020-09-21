package nz.ac.vuw.ecs.swen225.a3.maze.actors;

import nz.ac.vuw.ecs.swen225.a3.application.exceptions.AssetNotFoundException;
import nz.ac.vuw.ecs.swen225.a3.maze.util.ActorType;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Direction;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;
import nz.ac.vuw.ecs.swen225.a3.maze.util.TileType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Gliders move straight. When they reach an unwalkable tile, they then try to go left, right then backwards in
 * that order. Survives water tiles.
 *
 * @author Tim - Creator
 * @author Mike - Contributor
 */
public class Glider extends Actor {

    private static final Set<TileType> WALKABLE_TILES = new HashSet<>(Collections.singletonList(TileType.FIRE));


    private int tickWaitTime = 2;
    private static Image[] images = new Image[4];

    static {
        images[0] = loadImages("assets/tileSet/gliderRIGHT.png");
        images[1] = loadImages("assets/tileSet/gliderDOWN.png");
        images[2] = loadImages("assets/tileSet/gliderLEFT.png");
        images[3] = loadImages("assets/tileSet/gliderUP.png");
    }
    //Can walk on water

    public Glider(Position pos) {
        super(pos, ActorType.GLIDER);

        //
        this.registerOnTickListener((board) -> {

            //Glider probably shouldn't move every tick
            if (board.getCurrentTick() % tickWaitTime == 0) {

                //Find a possible direction we can move in
                for (int i = 1; i < Direction.values().length && !board.isValid(this.getDirection(), this); i++) {
                    this.setDirection(Direction.values()[(i + this.getDirection().ordinal()) % Direction.values().length]);
                    switch (this.getDirection()) {
                        case UP:
                            this.image = images[3];
                            break;
                        case DOWN:
                            this.image = images[1];
                            break;
                        case LEFT:
                            this.image = images[2];
                            break;
                        case RIGHT:
                            this.image = images[0];
                    }
                }

                //Finally move the glider
                board.moveActor(this.getDirection(), this);
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
    protected Set<TileType> getActorWalkableTiles() {
        return WALKABLE_TILES;
    }
}
