package nz.ac.vuw.ecs.swen225.a3.tests;

import nz.ac.vuw.ecs.swen225.a3.application.GUI;
import nz.ac.vuw.ecs.swen225.a3.application.util.FileUtil;
import nz.ac.vuw.ecs.swen225.a3.maze.Board;
import nz.ac.vuw.ecs.swen225.a3.maze.actors.Player;
import nz.ac.vuw.ecs.swen225.a3.maze.util.Direction;
import nz.ac.vuw.ecs.swen225.a3.persistence.FileOperations;
import nz.ac.vuw.ecs.swen225.a3.persistence.LevelFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ApplicationTests {

	//Utils
	@Test
	public void getExtensionTest01() {
		File file = new File("assets/levels/level1.json");

		assertEquals("json", FileUtil.getExtension(file));
	}


	//new file
	@Test
	public void newFileTest01() {
		try {
			File correctFile = new File("assets/levels/level2.json");


			File file = new File("assets/zips/level2.zip");
			ZipInputStream zis = new ZipInputStream(new FileInputStream(file));

			ZipEntry zipEntry = zis.getNextEntry();

			FileUtil.newFile(new File("assets/test/level"), zipEntry);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = AssertionError.class)
	public void guiTest() {
		new GUI();
	}

	//destination outside target directory

	//list files


	//file exists
	@Test
	public void fileExists() {
		Path path = Paths.get("assets/zips/level2.json");

		assertTrue(FileUtil.fileExists("level2.json", path));
	}

	@Test
	public void getHighestLevel() {
		Path path = Paths.get("assets/levels/");
		assertEquals(3, FileUtil.getHighestUnlockedLevel(path.toFile()));
	}

	@Test
	public void getHighestLevelNoItems() {
		Path path = Paths.get("assets/fonts/");
		assertEquals(1, FileUtil.getHighestUnlockedLevel(path.toFile()));
	}


}
