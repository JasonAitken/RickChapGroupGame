package nz.ac.vuw.ecs.swen225.a3.persistence;

import nz.ac.vuw.ecs.swen225.a3.maze.Board;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

//TODO comment
public class FileOperations {
    private final static String saveLocation = "assets/savegames/";
    private final static String extension = ".save";

    /**
     * This method will save a board object to a file.
     *
     * @param filename the filename you'd like to save it as
     * @param board    the board to be saved
     * @author Mike - Creator
     */
    public static void saveGame(String filename, Object board) {
        //Pre check
        if (!(board instanceof Board)) throw new IllegalArgumentException("board is not an instance of Board");

        try {
            FileOutputStream fileOut = new FileOutputStream(
                    saveLocation + filename + extension);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(board);
            objectOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to write file to disk");
        }
    }

}
