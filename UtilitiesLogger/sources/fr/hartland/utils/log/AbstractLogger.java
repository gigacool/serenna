package fr.hartland.utils.log;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

abstract class AbstractLogger implements ILogger {

	private final PrintStream out;
	private Level level;

	protected static String lineSeparator = System.getProperty("line.separator");
	protected static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	protected AbstractLogger(Level level, PrintStream out)
	{
		this.out = out;
		this.level = level;
	}

	protected PrintStream getOut()
	{
		return out;
	}

	/**
	 * Sets the logging level.
	 * 
	 * @param level
	 *            logging level.
	 */
	public void setLevel(Level level)
	{
		if (level == this.level)
		{
			return;
		}
		debug("Changing logging level from " + this.level + " to " + level);
		Level oldLevel = this.level;
		this.level = level;
		debug("Changied logging level from " + oldLevel + " to " + this.level);

	}

	protected String buildFormattedMessage(Level messageLevel, String message)
	{
		StringBuilder builder = new StringBuilder();
		{
			builder.append(dateFormat.format(new Date()));
			builder.append(' ');
			builder.append(messageLevel.toString());
			builder.append("\t: ");
			builder.append(message.replaceAll("[\\r\\n]+", lineSeparator + '\t'));
		}
		return builder.toString();
	}

	@Override
	public void fatal(String message)
	{
		switch (level) {
		case OFF:
			break;
		case FATAL:
		case ERROR:
		case WARNING:
		case INFO:
		case DEBUG:
			out.println(buildFormattedMessage(Level.FATAL, message));
			break;
		}
	}

	@Override
	public void error(String message)
	{
		switch (level) {
		case OFF:
		case FATAL:
			break;
		case ERROR:
		case WARNING:
		case INFO:
		case DEBUG:
			out.println(buildFormattedMessage(Level.ERROR, message));
			break;
		}
	}

	@Override
	public void warning(String message)
	{
		switch (level) {
		case OFF:
		case FATAL:
		case ERROR:
			break;
		case WARNING:
		case INFO:
		case DEBUG:
			out.println(buildFormattedMessage(Level.WARNING, message));
			break;
		}
	}

	@Override
	public void info(String message)
	{
		switch (level) {
		case OFF:
		case FATAL:
		case ERROR:
		case WARNING:
			break;
		case INFO:
		case DEBUG:
			out.println(buildFormattedMessage(Level.INFO, message));
			break;
		}
	}

	@Override
	public void debug(String message)
	{
		switch (level) {
		case OFF:
		case FATAL:
		case ERROR:
		case WARNING:
		case INFO:
			break;
		case DEBUG:
			out.println(buildFormattedMessage(Level.DEBUG, message));
			break;
		}
	}

}
