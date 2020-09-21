package nz.ac.vuw.ecs.swen225.a3.tests;

import nz.ac.vuw.ecs.swen225.a3.application.GUI;
import nz.ac.vuw.ecs.swen225.a3.maze.Board;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.Player;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Direction;
import nz.ac.vuw.ecs.swen225.a3.persistence.FileOperations;
import nz.ac.vuw.ecs.swen225.a3.persistence.LevelFactory;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
import java.util.logging.Level;

public class PersistenceTests {

    @Test (expected = java.io.NotSerializableException.class)
    public void saveLevelTest01() {
        Board b = LevelFactory.readFile(1);
        Player p = b.getPlayer();
        p.setMove(Direction.LEFT);
        b.processTick();
        FileOperations.saveGame("saveGame", b);
        Board bNew = LevelFactory.readFile(new File("assets/savegames/saveGame.json"));

    }
}
