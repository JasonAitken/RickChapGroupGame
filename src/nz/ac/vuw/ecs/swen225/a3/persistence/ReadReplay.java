package nz.ac.vuw.ecs.swen225.a3.persistence;

import nz.ac.vuw.ecs.swen225.a3.maze.util.MoveInfo;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.*;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * This class reads replays from a file
 * @author Fynn - Creator.
 */
public class ReadReplay {
    private ArrayDeque<ArrayDeque<MoveInfo>> moveArray = new ArrayDeque<ArrayDeque<MoveInfo>>();
    private String levelNum;
    private String fileName;
    private File file;


    /**
     * Constructor that reads JSON object from user selected file
     * @param f User selected file name
     */
    public ReadReplay(String f) {
        this.fileName = "assets/replay/" + f;
        //System.out.println(fileName);
        //We will read in the entire file to this string
        String file = null;
        try {
            file = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Ensure we succeeded in reading the file.
        if (file != null) {

            //TODO: If file is null break out
        }
        //Create a reader
        JsonReader reader = Json.createReader(new StringReader(file));
        //Get the toplevel Object. Close the reader.
        JsonObject topLevel = reader.readObject();
        reader.close();


        levelNum = topLevel.get("Level").toString();
        JsonArray jArray = topLevel.getJsonArray("moveList");


        ArrayDeque<MoveInfo> queue;
        MoveInfo m;


        for (int i = 0; i < jArray.size(); i++ ){
            queue = new ArrayDeque<MoveInfo>();
            JsonArray current = jArray.getJsonArray(i);
            for(int y = 0; y < current.size(); y++){
                m = getMoveInfo(current.get(y).asJsonObject().getString("move"));
                queue.push(m);
            }
            moveArray.push(queue);
        }
    }

    private MoveInfo getMoveInfo(String m){
        int posX, posY, direc;
        MoveInfo move;

        String[] splitted = m.split(" ");
        posX = Integer.parseInt(splitted[0]);
        posY = Integer.parseInt(splitted[1]);
        direc = Integer.parseInt(splitted[2]);

        move = new MoveInfo(new Position(posX, posY), direc);
        return move;

    }

    public ArrayDeque<ArrayDeque<MoveInfo>> getMoves(){
        return moveArray;
    }

    public int getLevel(){
        return Integer.parseInt(levelNum.substring(1, 2));
    }

}
