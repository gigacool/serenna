package fr.hartland.utils.trace;

/**
 * Provides an utility for data logging. Data are rendered through columns ordered typical CSV. like outputs.
 * 
 * @param <T>
 *            Output data type.
 */
public interface ITracer<T>
{
	/**
	 * Defines the data column separation sequence.
	 * 
	 * @param separator
	 *            data column separator.
	 */
	void setSeparator(String separator);

	/**
	 * Add a row set of T data to the trace log.
	 * 
	 * @param values
	 *            the row values.
	 */
	void addDataRow(T... values);

}
