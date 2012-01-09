package fr.hartland.utils.log;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Provides uniform mean to create file targeted loggers.
 * 
 */
public class LoggerManager {

	private static Map<String, FileLogger> fileLoggers = new HashMap<String, FileLogger>();
	private static ConsoleLogger console = new ConsoleLogger(ILogger.DEBUG);

	/**
	 * Builds or reuse the console logger. Change the logger level if logger
	 * existed with a different level.
	 * 
	 * @param level
	 *            the logging level.
	 * @return the console logger.
	 */
	public static ILogger buildConsoleLogger(short level) {
		console.setLevel(level);
		return console;
	}

	/**
	 * Build a new file logger with provided parameters or return an already
	 * existing logger, setting the level.
	 * 
	 * @param level
	 *            the logging level.
	 * @param filePath
	 *            the log file path.
	 * @return the file logger.
	 * @throws FileNotFoundException
	 *             if file is not found.
	 */
	public static ILogger buildFileLogger(short level, String filePath)
			throws FileNotFoundException {
		return buildFileLogger(level, filePath, false);
	}

	/**
	 * Build a new file logger with provided parameters or return an already
	 * existing logger, setting the level.
	 * 
	 * @param level
	 *            the logging level.
	 * @param filePath
	 *            the log file path.
	 * @param append
	 *            concatenate logs to the file if true or replace content if
	 *            false.
	 * @return the file logger.
	 * @throws FileNotFoundException
	 *             if file is not found.
	 */
	public static ILogger buildFileLogger(short level, String filePath,
			boolean append) throws FileNotFoundException {
		synchronized (fileLoggers) {
			if (fileLoggers.containsKey(filePath)) {
				FileLogger logger = fileLoggers.get(filePath);
				logger.setLevel(level);
				return logger;
			}
			FileLogger logger = new FileLogger(level, filePath, append);
			fileLoggers.put(filePath, logger);
			return logger;
		}
	}
}
