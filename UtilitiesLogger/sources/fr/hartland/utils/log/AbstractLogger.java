package fr.hartland.utils.log;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The abstract logger provides core functionalities common to any
 * {@link ILogger} implementation.
 */
abstract class AbstractLogger implements ILogger {

	private final PrintStream out;
	private short level;

	protected static String lineSeparator = System
			.getProperty("line.separator");
	protected static DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	protected AbstractLogger(short level, PrintStream out) {
		this.out = out;
		this.level = level;
	}

	protected PrintStream getOut() {
		return out;
	}

	/**
	 * Sets the logging level.
	 * 
	 * @param level
	 *            logging level.
	 */
	public void setLevel(short level) {
		if (level == this.level) {
			return;
		}
		debug("Changing logging level from " + this.level + " to " + level);
		short oldLevel = this.level;
		this.level = level;
		debug("Changied logging level from " + oldLevel + " to " + this.level);
	}

	public String getLevel() {
		return getLevel(level);
	}

	public String getLevel(short messageLevel) {
		switch (messageLevel) {
		case OFF:
			return "OFF";
		case FATAL:
			return "FATAL";
		case ERROR:
			return "ERROR";
		case WARNING:
			return "WARNING";
		case INFO:
			return "INFO";
		case DEBUG:
			return "DEBUG";
		}
		return "LEVEL INCORRECTLY SET";
	}

	protected String buildFormattedMessage(short messageLevel, String message) {
		StringBuilder builder = new StringBuilder();
		{
			builder.append(dateFormat.format(new Date()));
			builder.append(' ');
			builder.append(getLevel(messageLevel));
			builder.append("\t: ");
			builder.append(message
					.replaceAll("[\\r\\n]+", lineSeparator + '\t'));
		}
		return builder.toString();
	}

	@Override
	public void fatal(String message) {
		if (level >= FATAL) {
			out.println(buildFormattedMessage(FATAL, message));
		}
	}

	@Override
	public void error(String message) {
		if (level >= ERROR) {
			out.println(buildFormattedMessage(ERROR, message));
		}
	}

	@Override
	public void warning(String message) {
		if (level >= WARNING) {
			out.println(buildFormattedMessage(WARNING, message));
		}
	}

	@Override
	public void info(String message) {
		if (level >= INFO) {
			out.println(buildFormattedMessage(INFO, message));
		}
	}

	@Override
	public void debug(String message) {
		if (level >= DEBUG) {
			out.println(buildFormattedMessage(DEBUG, message));
		}
	}

}
