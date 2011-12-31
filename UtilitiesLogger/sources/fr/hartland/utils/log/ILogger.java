package fr.hartland.utils.log;

/**
 * General logging interface.
 * 
 */
public interface ILogger
{
	/**
	 * Logging detail level.
	 */
	public enum Level {
		/** Logging turned off */
		OFF,
		/** Log only fatal errors */
		FATAL,
		/** log up to error level */
		ERROR,
		/** log up to warnings level */
		WARNING,
		/** log up to info level */
		INFO,
		/** log everything up to debug level */
		DEBUG;
	}

	/**
	 * Log a fatal error message.
	 * 
	 * @param message
	 *            the message to display within logs.
	 */
	void fatal(String message);

	/**
	 * Log an error message.
	 * 
	 * @param message
	 *            the message to display within logs.
	 */
	void error(String message);

	/**
	 * Log a warning message.
	 * 
	 * @param message
	 *            the message to display within logs.
	 */
	void warning(String message);

	/**
	 * Log an information message.
	 * 
	 * @param message
	 *            the message to display within logs.
	 */
	void info(String message);

	/**
	 * Log a debug message.
	 * 
	 * @param message
	 *            the message to display within logs.
	 */
	void debug(String message);

}
