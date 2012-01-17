package fr.hartland.serenna.core.neuron;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import fr.hartland.serenna.core.activations.IActivationFunction;
import fr.hartland.serenna.core.activations.LinearActivationFunction;

/**
 * Exposes layer building helper factories functionalities.
 */
public class NeuralLayerTest
{

	/**
	 * Hidden layer creation.
	 */
	@Test
	public void createHiddenLayer()
	{
		// Setup
		IActivationFunction activationFunction = new LinearActivationFunction();
		Layer<HiddenNeuron> layer = Layer.buildHiddenLayer(10, activationFunction);
		// Test
		Iterable<HiddenNeuron> neurons = layer.getNeurons();
		// Assertions
		Assert.assertNotNull(neurons);
		Iterator<HiddenNeuron> neuronsIterator = neurons.iterator();
		for (int i = 0; i < 10; i++)
		{
			Assert.assertTrue(neuronsIterator.hasNext());
			Assert.assertEquals(activationFunction, neuronsIterator.next().getActivationFunction());
		}
		Assert.assertFalse(neuronsIterator.hasNext());
	}

	/**
	 * Output layer creation.
	 */
	@Test
	public void createOutputLayer()
	{
		// Setup
		IActivationFunction activationFunction = new LinearActivationFunction();
		Layer<OutputNeuron> layer = Layer.buildOutputLayer(10, activationFunction);
		// Test
		Iterable<OutputNeuron> neurons = layer.getNeurons();
		// Assertions
		Assert.assertNotNull(neurons);
		Iterator<OutputNeuron> neuronsIterator = neurons.iterator();
		for (int i = 0; i < 10; i++)
		{
			Assert.assertTrue(neuronsIterator.hasNext());
			Assert.assertEquals(activationFunction, neuronsIterator.next().getActivationFunction());
		}
		Assert.assertFalse(neuronsIterator.hasNext());
	}

	/**
	 * Input layer creation.
	 */
	@Test
	public void createInputLayer()
	{
		// Setup
		Layer<InputNeuron> layer = Layer.buildInputLayer(10);
		// Test
		Iterable<InputNeuron> neurons = layer.getNeurons();
		// Assertions
		Assert.assertNotNull(neurons);
		Iterator<InputNeuron> neuronsIterator = neurons.iterator();
		for (int i = 0; i < 10; i++)
		{
			Assert.assertTrue(neuronsIterator.hasNext());
			Assert.assertTrue(neuronsIterator.next().getActivationFunction() instanceof LinearActivationFunction);
		}
		Assert.assertFalse(neuronsIterator.hasNext());
	}

}
