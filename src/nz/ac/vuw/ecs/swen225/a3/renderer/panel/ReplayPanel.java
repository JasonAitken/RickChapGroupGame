package nz.ac.vuw.ecs.swen225.a3.renderer.panel;

import nz.ac.vuw.ecs.swen225.a3.maze.ReplayBoard;
import nz.ac.vuw.ecs.swen225.a3.persistence.LevelFactory;
import nz.ac.vuw.ecs.swen225.a3.renderer.ChapsCanvas;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * JFrame extention that holds the frame for displaying a replay.
 * @Author Fynn - Creator
 */
public class ReplayPanel extends JFrame {

    public static final int CANVAS_SIZE = 576;
    final static int TICKS_PER_SECOND = 4;

    ChapsCanvas c;
    Timer timer;
    ReplayBoard r;
    ReplayPanel replayWindow;

    /**
     * Creates a JFrame to hold the canvas.
     * @Author Fynn - Creator
     * @param filePath filePath of replay JSON file
     */
    public ReplayPanel(String filePath){
        super("Replay");
        r = LevelFactory.readReplayFile(filePath);
        c = new ChapsCanvas(null, r);
        replayWindow = this;

        this.setSize(600, 600);
        replayWindow.add(c);
        this.setVisible(true);
        this.repaint();
        c.repaint();

    }

    /**
     * Runs ticks to run through replay.
     * @Author - Fynn.
     */
    public void startTicks(){
        c.repaint();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                c.repaint();
                if(!r.runTicks()){
                    this.cancel();
                }
            }
        }, 1000 / TICKS_PER_SECOND, 1000 / TICKS_PER_SECOND);
    }
}
