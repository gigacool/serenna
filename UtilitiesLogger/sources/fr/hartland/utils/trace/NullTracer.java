package fr.hartland.utils.trace;

/**
 * No logging is performed.
 * 
 * @param <T>
 *            Output data type.
 */
public class NullTracer<T> implements ITracer<T>
{

	@Override
	public void setSeparator(String separator)
	{
		// NOP
	}

	@Override
	public void addDataRow(T... values)
	{
		// NOP
	}

}
