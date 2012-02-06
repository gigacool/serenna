package fr.hartland.serenna.utils;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Provide a simple mean to reverse iterate on a list collection. Adapted from web samples.
 * 
 * @param <T>
 *            the list type content.
 * 
 * @author cedric.hartland
 */
public class ReverseListIterator<T> implements Iterable<T>
{
	private final List<T> original;

	/**
	 * Default constructor.
	 * 
	 * @param original
	 *            the original list to reverse iterate over.
	 */
	ReverseListIterator(List<T> original)
	{
		this.original = original;
	}

	@Override
	public Iterator<T> iterator()
	{
		final ListIterator<T> iterator = original.listIterator(original.size());

		return new Iterator<T>()
		{
			@Override
			public boolean hasNext()
			{
				return iterator.hasPrevious();
			}

			@Override
			public T next()
			{
				return iterator.previous();
			}

			@Override
			public void remove()
			{
				iterator.remove();
			}
		};
	}

	/**
	 * Factory builder for a reverse list iterator.
	 * 
	 * @param <T>
	 *            iterated type.
	 * 
	 * @param original
	 *            the original list to reverse iterate over
	 * @return the reversed list iterator.
	 */
	public static <T> ReverseListIterator<T> reversed(List<T> original)
	{
		return new ReverseListIterator<T>(original);
	}
}
