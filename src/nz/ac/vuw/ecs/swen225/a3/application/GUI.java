package nz.ac.vuw.ecs.swen225.a3.application;

import nz.ac.vuw.ecs.swen225.a3.renderer.panel.InfoPanel;
import nz.ac.vuw.ecs.swen225.a3.renderer.panel.ReplayPanel;
import nz.ac.vuw.ecs.swen225.a3.application.util.FileUtil;
import nz.ac.vuw.ecs.swen225.a3.application.util.GUIUtil;
import nz.ac.vuw.ecs.swen225.a3.application.util.KeyboardInputListener;
import nz.ac.vuw.ecs.swen225.a3.maze.Board;
import nz.ac.vuw.ecs.swen225.a3.persistence.JsonUtils;
import nz.ac.vuw.ecs.swen225.a3.persistence.LevelFactory;
import nz.ac.vuw.ecs.swen225.a3.renderer.ChapsCanvas;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Main GUI class, controls almost all of the functions
 * carried out by the interface.
 *
 *
 * @author Tim
 * @author Mike
 * @author Jason
 * @author Fynn
 */
public class GUI extends JFrame implements WindowListener {
    /**
     * GUI Fields
     */
    public static final int CANVAS_SIZE = 576;

    public static final int TICKS_PER_SECOND = 5;

    //Use getContentPane to get current panel!
    private JPanel mainPanel; //The GUI's main content panel
    private ChapsCanvas canvasPanel;
    private InfoPanel infoPanel;
    private JComboBox<String> levelCB;
    private KeyboardInputListener listener;
    private Board board;
    private Timer timer;
    private int time;
    private JFrame startMenuMain;
    private boolean timerInit = false;
    private int numberOfLevels = 3;
    private int levelsUnlocked = 3; //set to 1
    private int selectedLevel = 1;
    private int selectedReplay = 1;
    private boolean lastGameExists = FileUtil.fileExists(LAST_GAME_FILE, Paths.get(SAVEGAME_FOLDER));

    private static final String LAST_GAME_FILE = "last-game.json";
    private static final String FILE_FORMAT = ".json";
    private static final String SAVEGAME_FOLDER = "assets/levels/";


    /**
     * GUI constructor, initializes inner panels and creates initial board from player selected level.
     * Also creates listeners for window and keyboard input which is then passed to game board.
     *
     * @author Tim - Creator
     * @author Jason - contributor
     */
    public GUI() {
        initializeStartMenu();
    }

    /**
     * Acts as the GUI constructor for the selected level. Initializes inner panels and creates initial board from player selected level.
     * Also creates listeners for window and keyboard input which is then passed to game board.
     *
     * @author Tim - Creator
     * @author Jason - contributor
     */
    public void initializeLevel(int levelNo) {
    	time = 0;
        this.selectedLevel = levelNo;
        this.addWindowListener(this);

        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        this.time = 0;

        //Initialize main panels
        this.board = LevelFactory.readFile(selectedLevel);
        this.mainPanel = new JPanel(new GridBagLayout());
        this.canvasPanel = new ChapsCanvas(this, board);
        this.infoPanel = new InfoPanel(this);
        this.setContentPane(mainPanel); //is currently main
        this.setTitle("Player Chap");
        this.board.setGui(this);
        this.board.setLevelNumber(selectedLevel);

        //make the menu bar
        initializeMenuBar();

        //Populate main panel
        mainPanel.add(canvasPanel, GUIUtil.makeConstraints(0, 0, 1, 1));
        mainPanel.add(infoPanel, GUIUtil.makeConstraints(1, 0, 1, 1));

        initializeResizeable();
        initializeTimer();

        //Leave this at the bottom
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(new KeyboardInputListener(board, this));

        this.addKeyListener(listener);
        this.pack();
        this.setVisible(true);
    }
    
    /**
     * Acts as the GUI constructor for the last game/saved game. Initializes inner panels and creates initial board from player selected level.
     * Also creates listeners for window and keyboard input which is then passed to game board.
     *
     * @author Jason - Creator
     */
    public void initializeLevel(File file) {
    	time = 0;
        //this.selectedLevel = get level from file
        this.addWindowListener(this);

        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        this.time = 0;

        //Initialize main panels
        this.board = LevelFactory.readFile(file);
        this.mainPanel = new JPanel(new GridBagLayout());
        this.canvasPanel = new ChapsCanvas(this, board);
        this.infoPanel = new InfoPanel(this);
        this.setContentPane(mainPanel); //is currently main
        this.setTitle("Player Chap");
        this.board.setGui(this);
        this.board.setLevelNumber(selectedLevel);

        //make the menu bar
        initializeMenuBar();

        //Populate main panel
        mainPanel.add(canvasPanel, GUIUtil.makeConstraints(0, 0, 1, 1));
        mainPanel.add(infoPanel, GUIUtil.makeConstraints(1, 0, 1, 1));

        initializeResizeable();
        initializeTimer();

        //Leave this at the bottom
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(new KeyboardInputListener(board, this));
        this.pack();
        this.setVisible(true);
    }

    /**
     * Initializes the game timer.
     *
     * @author Tim - Creator
     */
    public void initializeTimer() {
        final boolean[] updated = {true};
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if(!updated[0]) return;
                updated[0] = false;
                infoPanel.updateTime(++time / TICKS_PER_SECOND);
                board.processTick();
                canvasPanel.repaint();
                updated[0] = true;
            }
        }, 1000 / TICKS_PER_SECOND, 1000 / TICKS_PER_SECOND);
    }

    /**
     * Sets the time of the timer
     *
     * @param time the time to set it to
     * @author mike - Creator
     */
    public void setTime(int time) {
        this.time = time;
    }


    /**
     * Initializes the menu Bar.
     *
     * @author Tim - Creator
     * @author Jason - contributor
     */
    private void initializeMenuBar() {
        //Make the menu bar
        JMenuBar menuBar = new JMenuBar();

        //Make the things we want on the menubar
        JMenu mainMenu = new JMenu("Menu");
        JMenuItem reset = new JMenuItem("Reset");
        JMenuItem save = new JMenuItem("Save Game");
        JMenuItem load = new JMenuItem("Load Game");
        JMenuItem startMenu = new JMenuItem("Main Menu");
        JMenuItem quit = new JMenuItem("Quit");

        //Add those things to the menu bar
        mainMenu.add(reset);
        mainMenu.add(startMenu);
        mainMenu.add(save);
        mainMenu.add(load);
        mainMenu.add(quit);

        //Add listeners so the buttons do things
        quit.addActionListener((l) -> System.exit(0));
        startMenu.addActionListener((l) -> {
            returnToMenu();
        });
        save.addActionListener((l) -> {
            try {
                saveBoard("save", getBoard(), getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        load.addActionListener((l) -> {
            loadBoard();

        });

        menuBar.add(mainMenu);
        this.setJMenuBar(menuBar);
    }

    public void pauseGame() {
        timer.cancel();
        timer = null;
    }

    public void unpauseGame(int time) {
        initializeTimer();
        this.time = time;
    }


    /**
     * Returns the user to the main menu
     *
     * @author Jason - Creator
     */
    public void returnToMenu() {
    	if(timer != null) {
            startMenuMain.setVisible(true);
            this.setVisible(false);
            timer.cancel();
            timer = null;
        }
    }

    /**
     * Completes the current level.
     * Sets player on the new level.
     * @author Jason - Creator
     */
    public void levelCompleted() {
    	
    	timer.cancel();
        timer = null;
    	if(selectedLevel>=levelsUnlocked) {
    		if(levelsUnlocked+1 <= numberOfLevels) levelsUnlocked++;
    		levelCB.addItem("Level " + levelsUnlocked);
    		levelCB.update(getGraphics());
    	} else returnToMenu();
    	if(selectedLevel<numberOfLevels) initializeLevel(selectedLevel+=1);
    	else returnToMenu();
    }
    
    /**
     * Returns levelsUnlocked;
     * 
     * @author Jason - Creator
     */
    public int getLevelsUnlocked() {
    	return this.levelsUnlocked;
    }
    
    /**
     * sets levelsUnlocked;
     * 
     * @author Jason - Creator
     */
    public void setLevelsUnlocked(int amount) {
    	this.levelsUnlocked = amount;
    }

    /**
     * Initializes Start menu and button logic for start menu
     * makes level selector.
     *
     * @author Jason - Creator
     */
    private void initializeStartMenu() {
        //makes the start menu
        //TODO just to show where this is
        //adds levels to levelList
        String[] levelList = new String[levelsUnlocked];
        for (int i = 0; i < levelsUnlocked; i++) {
            levelList[i] = "Level " + (i + 1);
        }
        

        startMenuMain = new JFrame("Start Menu");
        JPanel startMainPanel = new JPanel();
        JButton startB = new JButton("START");
        JButton replayB = new JButton("REPLAY");
        JButton resumeB = new JButton("RESUME");
        JButton highestB = new JButton("HIGHEST LEVEL");
        levelCB = new JComboBox<String>(levelList);
        //levelCB.setEditable(true);
        JButton exitB = new JButton("EXIT");


        startMenuMain.add(startMainPanel);
        startMainPanel.add(resumeB);
        startMainPanel.add(replayB);
        startMainPanel.add(startB);
        startMainPanel.add(levelCB);
        startMainPanel.add(highestB);
        startMainPanel.add(Box.createRigidArea(new Dimension(0, 150)));
        startMainPanel.add(exitB);

        startMenuMain.setSize(200, 400);
        startMenuMain.setLocation(600, 100);

        startMenuMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        exitB.addActionListener((l) -> System.exit(0));
        resumeB.addActionListener((l) -> {
        	if(lastGameExists){
        		this.setVisible(true); 
        		startMenuMain.setVisible(false);
        		initializeLevel(new File("assests/levels/last-game.json")); 
        	}
        });
        
        highestB.addActionListener((l) -> {
        	highestLevelPlay();    //startB.addActionListener((l) -> initializeLevel(selectedLevel));
        });
        startB.addActionListener((l) -> {
            this.setVisible(true); 
            startMenuMain.setVisible(false);
            initializeLevel(selectedLevel); 
        });


        replayB.addActionListener((l) -> {
        	returnToMenu();
            String filePath;
            if((filePath = chooseFile()) != null){
                ReplayPanel r = new ReplayPanel(filePath);
                r.startTicks();
            }
        });
        levelCB.addActionListener((l) -> selectedLevel = levelCB.getSelectedIndex() + 1);

        startMenuMain.setVisible(true);
    }
    
    //TODO author - this is copied from keyboardlistner
    public String chooseFile() {
		JFileChooser fileMenu = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".json files", "json");
		fileMenu.setFileFilter(filter);
		fileMenu.setCurrentDirectory(new File("assets/replay"));
		int rval = fileMenu.showOpenDialog(this);
		if (rval == JFileChooser.APPROVE_OPTION) {
			return (fileMenu.getSelectedFile().getName());
		}
		return null;
	}
    
    public void highestLevelPlay() {
    	FileUtil.getHighestUnlockedLevel(new File("assets/levels/"));
        this.setVisible(true);              //startB.addActionListener((l) -> this.setVisible(true));
        startMenuMain.setVisible(false);    //startB.addActionListener((l) -> startMenuMain.setVisible(false));
        initializeLevel(levelsUnlocked); 
    }

    /**
     * Initializes logic for resizing the window.
     *
     * @author Tim - Creator
     */
    private void initializeResizeable() {
        canvasPanel.setPreferredSize(new Dimension(CANVAS_SIZE, CANVAS_SIZE));
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                //TODO: Fix recursiveness when full screen
                int width = mainPanel.getWidth();
                int height = mainPanel.getHeight();

                //Calculate the width of the canvas, needs to be a multiple of 9 and roughly 2/3rds of the overall size
                int closestWidthMultiple = (int) Math.round(((double) (width * (2f / 3f))) / ChapsCanvas.VIEW_DISTANCE) * ChapsCanvas.VIEW_DISTANCE;

                //Calculate the height of the canvas, once again needs to be a multiple of 9
                int closestHeightMultiple = (int) Math.round((double) height / ChapsCanvas.VIEW_DISTANCE) * ChapsCanvas.VIEW_DISTANCE;

                canvasPanel.setPreferredSize(new Dimension(closestWidthMultiple, closestHeightMultiple));
                infoPanel.setPreferredSize(new Dimension(width - closestWidthMultiple, closestHeightMultiple));

                //gui.revalidate();
                pack();
            }
        });

    }

    /**
     * Saves the current game state with a filename of the given parameter.
     *
     * @param board    - the game to save
     * @param fileName the file to save it as
     * @author Mike - Creator
     */
    private static void saveBoard(String fileName, Board board, int time) throws IOException {

        String preSchema = "{\n";
        String schema = "  \"$schema\": \"http://json-schema.org/draft-04/schema#\"";
        String postSchema = "\n}";


        String path = SAVEGAME_FOLDER + fileName + FILE_FORMAT;
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));

        writer.write(preSchema);
        writer.write(JsonUtils.separator(",\n  ",
                schema,
                board.getPlayer().toJson(),
                board.getHintTextJson(),
                board.chipsRequiredToJson(),
                "\"time\": " + time,
                "\"unlocked\": " + board.getBoardUnlocked().toString(),
                board.tilesToJson(),
                board.treasuresToJson(),
                board.actorsToJson())
        );
        writer.write(postSchema);
        writer.close();
    }

    /**
     * Makes a filechooser and loads the file that the user has chosen
     *
     * @author mike - Creator
     */
    private void loadBoard() {
        //Make the file chooser
        final JFileChooser fc = new JFileChooser();

        //Make it open in the current working directory
        File workingDirectory = new File(System.getProperty("user.dir"));
        fc.setCurrentDirectory(workingDirectory);

        //Only allow zip or json files
        fc.addChoosableFileFilter(new FileNameExtensionFilter(
                "JSON or ZIP only", "zip", "json"
        ));

        //ask the user to choose a file
        int returnVal = fc.showOpenDialog(GUI.this);

        //if the user didn't choose a file, throw an exception
        if (returnVal != JFileChooser.APPROVE_OPTION) {//choose the file
            throw new IllegalArgumentException("json and zip files only \n" +
                    "File chosen: " + fc.getSelectedFile().getAbsolutePath());
        }

        String extension = FileUtil.getExtension(fc.getSelectedFile());

        File file = fc.getSelectedFile();
        File levelsDir = new File("assets/levels/");
        byte[] buffer = new byte[1024];

        try {
            //if it's zip, unzip it and add it to the levels directory
            if (extension.equals("zip")) {
                ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
                ZipEntry zipEntry = zis.getNextEntry();

                while (zipEntry != null) {

                    //ensure that the json being extracted matches a regex
                    String pattern = "level[0-9]+(.json){1}";
                    if (!zipEntry.getName().matches(pattern)) {
                        zis.closeEntry();
                        zis.close();
                        throw new IllegalArgumentException("File did not match the required regex of \"" +
                                pattern + "\nWith file " + zipEntry.getName());
                    }

                    //ensure that the json being extracted is not in the list of files already in that director
                    if (FileUtil.listFiles(levelsDir.toPath()).contains(zipEntry.getName())) {
                        zis.closeEntry();
                        zis.close();
                        throw new IllegalArgumentException(
                                "There is already a level with that name in the levels folder!");
                    }

                    File newFile = FileUtil.newFile(levelsDir, zipEntry);
                    FileOutputStream fos = new FileOutputStream(newFile);

                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                    zipEntry = zis.getNextEntry();
                }


                zis.closeEntry();
                zis.close();


                //if it's a JSON, just open it and play the level
            } else if (extension.equals("json")) {
                Files.copy(file.toPath(),
                        new FileOutputStream(
                                new File("assets/levels/" + file.getName()))
                );

                initializeLevel(new File("assets/levels/" + file.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * Gets the current tick that the game is on
     *
     * @return The tick
     * @author Tim - Creator
     */
    public int getTime() {
        return time;
    }

    /**
     * Gets the canvas panel that the game is rendered on
     *
     * @return The canvas panel
     * @author Tim - Creator
     */
    public ChapsCanvas getCanvasPanel() {
        return canvasPanel;
    }

    /**
     * Gets the info panel that holds information about the game
     *
     * @return The info panel
     * @author Tim - Creator
     */
    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    /**
     * Gets the board this GUI is controlling
     *
     * @return The board this GUI is controlling
     * @author Tim - Creator
     */
    public Board getBoard() {
        return board;
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        timer.cancel();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }


    /**
     * Gets the timer that controls the tick system
     *
     * @return The timer
     * @author Max - Creator
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * Gets the current level number
     *
     * @return The current level number
     * @author Jason - Creator
     */
    public int getLevelNumber(){
        return selectedLevel;
    }
}
