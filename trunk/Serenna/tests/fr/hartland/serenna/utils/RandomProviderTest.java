package fr.hartland.serenna.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test the random provider singleton.
 * 
 */
public class RandomProviderTest
{

	/**
	 * Given a seed initialization, assert that the generator produces the same sequence of random numbers.
	 */
	@Test
	public void repeatNumbersThroughSeed()
	{
		RandomProvider random = RandomProvider.getInstance();
		int size = 100;
		random.setSeed(1);
		double[] left = new double[size];
		for (int i = 0; i < size; i++)
		{
			left[i] = random.nextDouble();
		}

		random.setSeed(1);
		for (int i = 0; i < size; i++)
		{
			Assert.assertEquals(left[i], random.nextDouble(), 10E-6);
		}
	}

	/**
	 * doubleWithinRange
	 */
	@Test
	public void doubleWithinRange()
	{
		// Setup
		RandomProvider random = RandomProvider.getInstance();
		// Test, Assertions
		for (int i = 0; i < 10000; i++)
		{
			double nextDouble = random.nextDouble(-2, 2);
			Assert.assertTrue(nextDouble <= 2);
			Assert.assertTrue(nextDouble >= -2);
		}
		for (int i = 0; i < 10000; i++)
		{
			double nextDouble = random.nextDouble(0, 2);
			Assert.assertTrue(nextDouble <= 2);
			Assert.assertTrue(nextDouble >= 0);
		}
		for (int i = 0; i < 10000; i++)
		{
			double nextDouble = random.nextDouble(-2, 0);
			Assert.assertTrue(nextDouble <= 0);
			Assert.assertTrue(nextDouble >= -2);
		}
	}

}
