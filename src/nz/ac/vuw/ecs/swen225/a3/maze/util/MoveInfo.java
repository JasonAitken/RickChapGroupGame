package nz.ac.vuw.ecs.swen225.a3.maze.util;

import java.io.Serializable;

/**
 * Represents a move in the game
 *
 * @author Fynn - Creator
 */
public class MoveInfo implements Serializable {
    private Position position;
    private int moveType;

    public MoveInfo(Position p, int m) {
        this.position = p;
        this.moveType = m;
    }

    /**
     * Gets the position that this MoveInfo represents
     *
     * @return The position
     * @author Fynn - Creator
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Gets the move type that this MoveInfo represents
     *
     * @return The move type
     * @author Fynn - Creator
     */
    public Direction getMoveType(){
        switch (moveType){
            case 1:
                return Direction.UP;
            case 2:
                return Direction.RIGHT;
            case 3:
                return Direction.DOWN;
            case 4:
                return Direction.LEFT;

        }
        return Direction.NONE;
    }

    public String toString() {
        return position.getX() + " " + position.getY() + " " + moveType;
    }
}
