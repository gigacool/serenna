package fr.hartland.utils.log;

/**
 * General logging interface.
 * 
 */
public interface ILogger
{

	/** Logging turned off */
	short OFF = 1 << 0;
	/** Log only fatal errors */
	short FATAL = OFF << 1;
	/** log up to error level */
	short ERROR = FATAL << 1;
	/** log up to warnings level */
	short WARNING = ERROR << 1;
	/** log up to info level */
	short INFO = WARNING << 1;
	/** log everything up to debug level */
	short DEBUG = INFO << 1;

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
