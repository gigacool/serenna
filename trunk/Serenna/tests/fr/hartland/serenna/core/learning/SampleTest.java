package fr.hartland.serenna.core.learning;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Validate Sample technical methods.
 * 
 */
public class SampleTest
{

	/**
	 * testHashCode
	 */
	@Test
	public void testHashCode()
	{
		// Setup
		double[] inputs1 = new double[] { 1 };
		double[] inputs2 = new double[] { 1 };
		double[] inputs3 = new double[] { 2 };
		double[] outputs = new double[] { 2 };

		Sample sample1 = new Sample(inputs1, outputs);
		Sample sample2 = new Sample(inputs1, outputs);
		Sample sample3 = new Sample(inputs2, outputs);
		Sample sample4 = new Sample(inputs3, outputs);

		// Test
		// Assertions
		Assert.assertTrue(sample1.hashCode() == sample2.hashCode());
		Assert.assertTrue(sample1.hashCode() == sample3.hashCode());
		Assert.assertFalse(sample1.hashCode() == sample4.hashCode());
	}

	/**
	 * test equals on samples with same contents.
	 */
	@Test
	public void testEqualsObject()
	{
		double[] inputs1 = new double[] { 1 };
		double[] inputs2 = new double[] { 1 };
		double[] inputs3 = new double[] { 2 };
		double[] outputs = new double[] { 2 };

		Sample sample1 = new Sample(inputs1, outputs);
		Sample sample2 = new Sample(inputs1, outputs);
		Sample sample3 = new Sample(inputs2, outputs);
		Sample sample4 = new Sample(inputs3, outputs);

		// Test
		// Assertions
		Assert.assertTrue(sample1.equals(sample2));
		Assert.assertTrue(sample1.equals(sample3));
		Assert.assertFalse(sample1.equals(sample4));
	}

}
