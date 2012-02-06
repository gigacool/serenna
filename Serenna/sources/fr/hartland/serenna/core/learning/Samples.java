package fr.hartland.serenna.core.learning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Test or training set.
 * 
 * @author cedric.hartland
 */
public class Samples implements Iterator<Sample>
{
	private final List<Sample> samples;

	private int cursor;

	/**
	 * Default constructor.
	 */
	public Samples()
	{
		this.samples = new ArrayList<Sample>();
	}

	/**
	 * Create and append a new sample.
	 * 
	 * @param inputs
	 *            the input values.
	 * @param outputs
	 *            the output values.
	 */
	public void add(double[] inputs, double[] outputs)
	{
		this.samples.add(new Sample(inputs, outputs));
	}

	/**
	 * Returns the number of samples.
	 * 
	 * @return the number of samples.
	 */
	public int length()
	{
		return samples.size();
	}

	@Override
	public boolean hasNext()
	{
		return cursor < samples.size();
	}

	@Override
	public Sample next()
	{
		if (samples.size() <= cursor)
			throw new NoSuchElementException();
		return samples.get(cursor++);
	}

	@Override
	public void remove()
	{
		samples.remove(cursor);
	}

	/**
	 * Resets the iterator, not preserving samples order.
	 */
	public void reset()
	{
		cursor = 0;
	}

	/**
	 * Resets the iterator, not preserving samples order.
	 */
	public void shuffle()
	{
		Collections.shuffle(samples);
		cursor = 0;
	}

}
