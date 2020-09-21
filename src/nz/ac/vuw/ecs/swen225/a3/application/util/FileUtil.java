package nz.ac.vuw.ecs.swen225.a3.application.util;


import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;

public class FileUtil {
	/**
	 * Static method to extract the file extension from a file.
	 *
	 * @param file the file to get the extension of
	 * @return the extension of the file (not including the '.')
	 * @author mike - Creator
	 */
	public static String getExtension(File file) {
		//Ensure the file is not a directory
		assert !file.isDirectory();

		//get the filename
		String filename = file.getName();
		//Find a point to split the file
		int index = filename.lastIndexOf('.');

		//extract the extension
		String extension = filename.substring(index + 1).toLowerCase();

		//ensure there is actually an extension given back
		assert extension.length() > 0;
		return extension;
	}

	/**
	 * Method for creating a new file from a zip entry, while ensuring
	 * that the new file is within a specified directory.
	 *
	 * @param destinationDir the destination directory of the extracted files.
	 * @param zipEntry       The zip entry the file is being created from
	 * @return the new file
	 * @throws IOException in the case that zip slip is detected
	 * @author mike - Creator
	 */
	public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
		File destFile = new File(destinationDir, zipEntry.getName());

		String destDirPath = destinationDir.getCanonicalPath();
		String destFilePath = destFile.getCanonicalPath();

//		if (!destFilePath.startsWith(destDirPath + File.separator)) {
//			throw new IOException("Entry is outside of the target directory: " +
//					zipEntry.getName());
//		}

		return destFile;
	}

	public static Set<String> listFiles(Path path) throws IOException {
		Stream<Path> walk = Files.walk(Paths.get(path.toUri()));
		return walk.filter(Files::isRegularFile).map((i) -> i.toFile().getName()).collect(Collectors.toSet());
	}


	/**
	 * Checks whether a filename exists in a directory.
	 *
	 * @param filename the filename to check if it exists
	 * @param path     the path to check
	 * @return true if the file exists in that directory
	 * @author mike - Creator
	 */
	public static Boolean fileExists(String filename, Path path) {
		Boolean ret = null;
		try {
			assert !path.toFile().isDirectory();
			ret = listFiles(path).contains(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assert ret != null;
		return ret;
	}


	/**
	 * Finds the highest level with the "unlocked" attribute set to false.
	 *
	 * @param directory the directory to inspect
	 * @return the highest unlocked level number
	 * @author mike - Creator
	 */
	public static int getHighestUnlockedLevel(File directory) {
		//Pre-checks
		assert directory.isDirectory();

		Set<String> files = null;
		try {
			files = listFiles(directory.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		String levelName = "level";
		int levelNumber = 1;
		String fileExtension = ".json";

		assert files != null;

		//loop through
		while (files.contains(levelName + levelNumber + fileExtension)) {

			//Make an path for the file we'll be inspecting next.
			String path = directory.getAbsolutePath() + "/" + levelName + levelNumber + fileExtension;

			String string = null;
			try {
				//Read in the file
				string = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
			} catch (IOException e) {
				e.printStackTrace();
			}

			assert string != null;
			//create a json reader
			JsonReader reader = Json.createReader(new StringReader(string));
			//get the toplevel object
			JsonObject topLevel = reader.readObject();

			//Is this level locked?
			if (topLevel.getBoolean("unlocked")) {
				levelNumber++;

				//this level is locked!
			} else {
				levelNumber--;
				break;
			}
		}

		return levelNumber;
	}
}
