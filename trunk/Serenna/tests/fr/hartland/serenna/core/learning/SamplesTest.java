package fr.hartland.serenna.core.learning;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Validate samples container.
 * 
 */
public class SamplesTest
{
	static Samples generateTestSamples()
	{
		Samples samples = new Samples();
		{
			samples.add(new double[] { -10 }, new double[] { 10 });
			samples.add(new double[] { -1 }, new double[] { 1 });
			samples.add(new double[] { 0 }, new double[] { 0 });
			samples.add(new double[] { 1 }, new double[] { -1 });
			samples.add(new double[] { 10 }, new double[] { -10 });
		}
		return samples;
	}

	private static List<Sample> generateTestExpectedSamples()
	{
		List<Sample> expectedSamples = new ArrayList<Sample>();
		{
			expectedSamples.add(new Sample(new double[] { -10 }, new double[] { 10 }));
			expectedSamples.add(new Sample(new double[] { -1 }, new double[] { 1 }));
			expectedSamples.add(new Sample(new double[] { 0 }, new double[] { 0 }));
			expectedSamples.add(new Sample(new double[] { 1 }, new double[] { -1 }));
			expectedSamples.add(new Sample(new double[] { 10 }, new double[] { -10 }));
		}
		return expectedSamples;
	}

	/**
	 * testShuffle. Chances are this test fails even though shuffle method still work. Probability that it fails should be low
	 * enough although that running it twice should expose malfunction.
	 */
	@Test
	public void testShuffle()
	{
		List<Sample> expectedSamples = generateTestExpectedSamples();
		Samples samples = generateTestSamples();
		// Test, Assertions
		int count = 0;
		boolean shuffled = false;
		do
		{
			samples.shuffle();
			for (int i = 0; i < samples.length(); i++)
			{
				if (!samples.next().equals(expectedSamples.get(i)))
					shuffled = true;
			}
		} while (!shuffled && count++ < 100);
		Assert.assertTrue(shuffled);
	}

	/**
	 * Validate sample set cration.
	 */
	@Test
	public void createSimpleSet()
	{
		// Setup
		List<Sample> expectedSamples = generateTestExpectedSamples();

		// Test
		Samples samples = generateTestSamples();

		for (int i = 0; i < samples.length(); i++)
		{
			Assert.assertTrue(samples.next().equals(expectedSamples.get(i)));
		}
		Assert.assertFalse(samples.hasNext());

	}

}
