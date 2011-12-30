package fr.hartland.utils.trace;

/**
 * Provides an interface toward console tracing.
 */
public class ConsoleTracer extends AbstractTracer {

	/**
	 * Default constructor.
	 */
	public ConsoleTracer()
	{
		super(System.out);
	}

}
