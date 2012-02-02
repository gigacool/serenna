package fr.hartland.serenna.utils;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class ReverseIteratorTest
{

	@Test
	public void testReverseITerator()
	{
		List<String> list = new ArrayList<String>();
		{
			list.add("a");
			list.add("b");
			list.add("c");
		}
		String[] expected = new String[] { "c", "b", "a" };
		int cursor = 0;

		for (String character : ReverseListIterator.reversed(list))
		{
			Assert.assertEquals(expected[cursor++], character);
		}
		Assert.assertEquals(expected.length, cursor);
	}

}
