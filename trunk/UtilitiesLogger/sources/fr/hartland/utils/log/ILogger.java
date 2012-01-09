package fr.hartland.utils.log;

/**
 * General logging interface.
 * 
 */
public interface ILogger {

	/** Logging turned off */
	short OFF = 1 << 0;
	/** Log only fatal errors */
	short FATAL = 1 << 1;
	/** log up to error level */
	short ERROR = 1 << 2;
	/** log up to warnings level */
	short WARNING = 1 << 3;
	/** log up to info level */
	short INFO = 1 << 4;
	/** log everything up to debug level */
	short DEBUG = 1 << 5;

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
