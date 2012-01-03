package fr.hartland.serenna.core.activations;

import org.junit.Assert;
import org.junit.Test;

import fr.hartland.serenna.core.activations.HeavisideActivationFunction;
import fr.hartland.serenna.core.activations.IActivationFunction;

/**
 * Validate heaviside activation function core components methods.
 * 
 */
public class HeavisideActivationFunctionTest
{
	/** tests the unit step function. */
	@Test
	public void testUnitStepFunction()
	{
		// Setup
		IActivationFunction function = new HeavisideActivationFunction();
		// Tests
		Assert.assertEquals(1, function.computeValue(0), 10E-6);
		Assert.assertEquals(1, function.computeValue(1), 10E-6);
		Assert.assertEquals(1, function.computeValue(1000), 10E-6);
		Assert.assertEquals(0, function.computeValue(-1), 10E-6);
		Assert.assertEquals(0, function.computeValue(-100), 10E-6);
	}
}
