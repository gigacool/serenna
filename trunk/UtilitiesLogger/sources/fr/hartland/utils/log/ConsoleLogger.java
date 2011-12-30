package fr.hartland.utils.log;

/**
 * Provides a logger to a console output.
 */
public class ConsoleLogger extends AbstractLogger {

	/**
	 * Default constructor.
	 * 
	 * @param level
	 *            logging level.
	 */
	protected ConsoleLogger(Level level)
	{
		super(level, System.out);
	}

	@Override
	protected String buildFormattedMessage(Level messageLevel, String message)
	{
		StringBuilder builder = new StringBuilder();
		{
			builder.append(messageLevel.toString());
			builder.append("\t: ");
			builder.append(message.replaceAll("[\\r\\n]+", lineSeparator + '\t'));
		}
		return builder.toString();
	}

}
