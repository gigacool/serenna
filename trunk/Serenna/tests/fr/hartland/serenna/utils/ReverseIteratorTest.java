package fr.hartland.serenna.utils;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Exposes and tests the reverse list iterations.
 */
public class ReverseIteratorTest
{

	/**
	 * Validate simple list reverse iteration.
	 */
	@Test
	public void testReverseITerator()
	{
		// Setup
		List<String> list = new ArrayList<String>();
		{
			list.add("a");
			list.add("b");
			list.add("c");
		}
		String[] expected = new String[] { "c", "b", "a" };
		int cursor = 0;
		// Test / Assertions
		for (String character : ReverseListIterator.reversed(list))
		{
			Assert.assertEquals(expected[cursor++], character);
		}
		Assert.assertEquals(expected.length, cursor);
	}

}
