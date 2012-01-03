package fr.hartland.serenna.core.activations;

import org.junit.Assert;
import org.junit.Test;

/**
 * Validate linear activation function core components methods.
 * 
 */
public class LinearActivationFunctionTest
{
	/** tests the unit step function. */
	@Test
	public void testUnitStepFunction()
	{
		// Setup
		IActivationFunction function = new LinearActivationFunction();
		// Tests
		Assert.assertEquals(1, function.computeValue(1), 10E-6);
		Assert.assertEquals(1, function.computeValue(1), 10E-6);
		Assert.assertEquals(100, function.computeValue(100), 10E-6);
		Assert.assertEquals(-1, function.computeValue(-1), 10E-6);
		Assert.assertEquals(-100, function.computeValue(-100), 10E-6);
	}
}
