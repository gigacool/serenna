package fr.hartland.utils.trace;

/**
 * Provides an utility for data logging. Data are rendered through columns
 * ordered typical CSV. like outputs.
 */
public interface ITracer {

	/**
	 * Defines the data column separation sequence.
	 * 
	 * @param separator
	 *            data column separator.
	 */
	void setSeparator(String separator);

	/**
	 * Add a set of double precision floating point data.
	 * 
	 * @param values
	 *            the row values.
	 */
	void addDataRow(double... values);

	/**
	 * Add a set of simple precision floating point data.
	 * 
	 * @param values
	 *            the row values.
	 */
	void addDataRow(float... values);

	/**
	 * Add a set of integer data.
	 * 
	 * @param values
	 *            the row values.
	 */
	void addDataRow(int... values);

	/**
	 * Add a set of short integer data.
	 * 
	 * @param values
	 *            the row values.
	 */
	void addDataRow(short... values);

	/**
	 * Add a set of object data.
	 * 
	 * @param values
	 *            the row values.
	 */
	void addDataRow(Object... values);
}
