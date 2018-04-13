package server;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class RobotLogger {
	private static final Logger LOGGER = Logger.getLogger(RobotLogger.class.getName());
	private static FileHandler file;

	/**
	 * Constructor, sets up the logger and creates a text file if it doesn't exist
	 * already.
	 */
	public RobotLogger() {
		File logFile = new File("files/log.txt");
		try {
			logFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			file = new FileHandler("files/log.txt", true);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}

		// Format
		file.setFormatter(new SimpleFormatter());

		// Console handler
		LOGGER.setUseParentHandlers(true);
		LOGGER.addHandler(file);
		LOGGER.setLevel(Level.INFO);
	}
	public static void main(String[] args) {
		new RobotLogger();
		LOGGER.info("RobotLogger main test");
	}
}