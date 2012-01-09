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
	protected ConsoleLogger(short level)
	{
		super(level, System.out);
	}

	@Override
	protected String buildFormattedMessage(short messageLevel, String message)
	{
		StringBuilder builder = new StringBuilder();
		{
			builder.append(getLevel(messageLevel));
			builder.append("\t: ");
			builder.append(message.replaceAll("[\\r\\n]+", lineSeparator + '\t'));
		}
		return builder.toString();
	}

}
