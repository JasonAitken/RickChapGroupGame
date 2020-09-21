package nz.ac.vuw.ecs.swen225.a3.application.util;

import nz.ac.vuw.ecs.swen225.a3.application.GUI;
import nz.ac.vuw.ecs.swen225.a3.maze.Board;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Direction;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.time.LocalDate;

import static java.awt.event.KeyEvent.*;


/**
 * Handles key presses
 *
 * @author Max - Creator
 * @author Fynn - Contributor
 */
public class KeyboardInputListener implements KeyListener {

    private boolean[] moveDirections = new boolean[4];

	private Integer pausedTimerValue;
	private boolean gamePaused = false;

    private Board board;
    private GUI gui;

    /**
     * @param board board that KeyboardInputListener must influence on move input.
     * @param gui   GUI that KeyboardInputListener mist influence on shortcut key input.
     *
     * @author Max - Creator
     */
    public KeyboardInputListener(Board board, GUI gui) {
        this.board = board;
        this.gui = gui;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

	//TODO Comment - Max
    @Override
    public void keyPressed(KeyEvent e) {
		if (gamePaused && e.getKeyCode() == VK_SPACE) return;

        if (!e.isControlDown()) {
        switch (e.getKeyCode()) {
            case VK_A:
            case VK_LEFT:
                board.getPlayer().setMove(Direction.LEFT);
                break;
            case VK_D:
            case VK_RIGHT:
                board.getPlayer().setMove(Direction.RIGHT);
                break;
            case VK_W:
            case VK_UP:
                board.getPlayer().setMove(Direction.UP);
                break;
            case VK_S:
            case VK_DOWN:
                board.getPlayer().setMove(Direction.DOWN);
                break;
            case VK_SPACE:
				if (!gamePaused) {
					pausedTimerValue = gui.getTime();
					gui.pauseGame();
					gamePaused = true;
				}
                break;
            case VK_ESCAPE:
				if (pausedTimerValue < 0) {
					break;
				}

				gui.unpauseGame(pausedTimerValue);
				pausedTimerValue = -1;
				gamePaused = false;

                break;
            default:
                break;
        }
        }
        else {

            switch (e.getKeyCode()) {
                case VK_X:
                    //exit game
                    gui.dispose();
                    System.exit(0);
                    break;

                case VK_S:

					break;
                case VK_R:

                    //resume saved game
                    break;
                case VK_P:
                    //start new game at last unfinished level
                	gui.highestLevelPlay();

//					if (FileUtil.fileExists("last-game.json", Paths.get("assets/levels/")))
//						gui.initializeLevel(new File("assets/levels/last-game.json"));


                    break;
                case VK_F:
                    String s = JOptionPane.showInputDialog(null, "Enter file title", "File Title");

					if (s.equals("File Title")) {
                        LocalDate date = LocalDate.now();
                        board.saveBoardReplay(date.toString());
                        break;
                    }
                    else{
                        board.saveBoardReplay(s);
                        break;
                    }
                case VK_1:
					gui.initializeLevel(1);
					break;
				case VK_2:
					if(gui.getLevelsUnlocked()>=2) gui.initializeLevel(2);
					break;
				case VK_3:
					if(gui.getLevelsUnlocked()>=3) gui.initializeLevel(3);
					break;
                default:
                    try {
                        int levelNumber = e.getKeyChar();
//                    if (levelNumber > levelAmount) break;
//                    load level x
					} catch (Exception er) {
						er.printStackTrace();
					}
			}
		}
	}

	//TODO Comment
	public String chooseFile() {
		JFileChooser fileMenu = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".json files", "json");
		fileMenu.setFileFilter(filter);
		fileMenu.setCurrentDirectory(new File("assets/replay"));
		int rval = fileMenu.showOpenDialog(gui);
		if (rval == JFileChooser.APPROVE_OPTION) {
			return (fileMenu.getSelectedFile().getName());
		}
		return null;
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public void setBoard(Board b){
        this.board = b;
    }
}
