package nz.ac.vuw.ecs.swen225.a3.persistence;

import nz.ac.vuw.ecs.swen225.a3.maze.util.MoveInfo;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Position;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.FileWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Saves a set of moves on a level to a JSON object.
 * @Author Fynn - Creator
 */
public class SaveReplay {
    private ArrayList<ArrayDeque<MoveInfo>> moveArray = new ArrayList<ArrayDeque<MoveInfo>>();

    String currentLevel;
    String fileName;
    private MoveInfo currentMove;
    private Position currentPos;

    /**
     * Constructor that builds a JSON object from a set of moves and a filename
     * @Author Fynn - Creator
     * @param m Queue of Queues of stored moves
     * @param f Filename
     * @param l Level ID
     */
    public SaveReplay(ArrayDeque<ArrayDeque<MoveInfo>> m, String f, String l) {
        fileName = f;
        currentLevel = l;

        for (ArrayDeque<MoveInfo> q : m) {
            moveArray.add(q);
        }
        Collections.reverse(moveArray);

        try {

            JsonArrayBuilder arrayBuild = Json.createArrayBuilder();
            for (ArrayDeque<MoveInfo> a : moveArray) {
                JsonArrayBuilder moveArray = Json.createArrayBuilder();
                for (MoveInfo move : a) {
                    moveArray.add(Json.createObjectBuilder().add("move", move.toString()));
                }
                arrayBuild.add(moveArray);

            }

            JsonObjectBuilder json = Json.createObjectBuilder();
            json.add("Level", currentLevel);
            json.add("moveList", arrayBuild);


            JsonObject out = json.build();

            //TODO Might not close the writer if an exception is thrown
            //TODO UTF-8 encoding @fynn
            FileWriter writer = new FileWriter("assets/replay/" + fileName + ".json", false);
            writer.write(out.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
