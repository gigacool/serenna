package fr.hartland.utils.log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Logger to file outputs.
 */
public class FileLogger extends AbstractLogger {

	protected FileLogger(short level, String filePath) throws FileNotFoundException
	{
		this(level, filePath, false);
	}

	protected FileLogger(short level, String filePath, boolean append) throws FileNotFoundException
	{
		super(level, new PrintStream(new FileOutputStream(filePath, append), true));
	}

	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
		getOut().close();
	}
}
