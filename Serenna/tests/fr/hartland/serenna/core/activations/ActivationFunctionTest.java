package fr.hartland.serenna.core.activations;

import org.junit.Assert;
import org.junit.Test;

/**
 * Validate linear activation function core components methods.
 * 
 */
public class ActivationFunctionTest
{
	/** tests the unit step function. */
	@Test
	public void unitStepFunction()
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

	/** tests the linear activation function. */
	@Test
	public void linearActivationFunction()
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

	/** tests the hyperbolic tangent function. */
	@Test
	public void tanhActivationFunction()
	{
		// Setup
		IActivationFunction function = new HyperbolicTangentActivationFunction();
		// Tests
		Assert.assertEquals(-1, function.computeValue(-1000), 10E-3);
		Assert.assertEquals(-0.76, function.computeValue(-1), 10E-3);
		Assert.assertEquals(-0.46, function.computeValue(-0.5), 10E-3);
		Assert.assertEquals(0, function.computeValue(0), 10E-3);
		Assert.assertEquals(0.46, function.computeValue(0.5), 10E-3);
		Assert.assertEquals(0.76, function.computeValue(1), 10E-3);
		Assert.assertEquals(1, function.computeValue(1000), 10E-3);
	}

	/** tests the logistic function. */
	@Test
	public void logisticFunction()
	{
		// Setup
		IActivationFunction function = new LogisticActivationFunction(1);
		// Tests
		Assert.assertEquals(0, function.computeValue(-1000), 10E-3);
		Assert.assertEquals(0.12, function.computeValue(-1), 10E-3);
		Assert.assertEquals(0.27, function.computeValue(-0.5), 10E-3);
		Assert.assertEquals(0.5, function.computeValue(0), 10E-3);
		Assert.assertEquals(0.73, function.computeValue(0.5), 10E-3);
		Assert.assertEquals(0.88, function.computeValue(1), 10E-3);
		Assert.assertEquals(1, function.computeValue(1000), 10E-3);
	}
}
