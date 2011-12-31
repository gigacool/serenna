package fr.hartland.utils.trace;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Provides an interface toward file output tracing.
 * 
 * @param <T>
 *            Output data type.
 */
public class FileTracer<T> extends AbstractTracer<T>
{

	/**
	 * Default constructor.
	 * 
	 * @param filePath
	 *            the output target file path.
	 * @throws FileNotFoundException
	 *             if the file is not found.
	 */
	public FileTracer(String filePath) throws FileNotFoundException
	{
		this(filePath, false);
	}

	/**
	 * Default constructor.
	 * 
	 * @param filePath
	 *            the output target file path.
	 * @param append
	 *            true if the contents are to be appended to existing file, false to overwrite.
	 * @throws FileNotFoundException
	 *             if the file is not found.
	 */
	public FileTracer(String filePath, boolean append) throws FileNotFoundException
	{
		super(new PrintStream(new FileOutputStream(filePath, append), true));
	}

	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
		getOut().close();
	}
}
