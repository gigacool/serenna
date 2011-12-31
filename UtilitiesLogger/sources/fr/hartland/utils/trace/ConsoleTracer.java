package fr.hartland.utils.trace;

/**
 * Provides an interface toward console tracing.
 * 
 * @param <T>
 *            Output data type.
 */
public class ConsoleTracer<T> extends AbstractTracer<T>
{

	/**
	 * Default constructor.
	 */
	public ConsoleTracer()
	{
		super(System.out);
	}

}
