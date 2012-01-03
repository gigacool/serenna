package fr.hartland.serenna.core.neurons;

import junit.framework.Assert;

import org.junit.Test;

import fr.hartland.serenna.core.INode;
import fr.hartland.serenna.core.activations.LinearActivationFunction;

/**
 * Test the neuron class functionalities.
 */
public class NeuronTest
{

	/** test a neuron compute with no inputs */
	@Test
	public void testSingleNeuron()
	{
		// Setup
		INode neuron = new HiddenNeuron("testNeuron", new LinearActivationFunction());
		// Test
		neuron.compute();
		// Assertions
		Assert.assertEquals(0, neuron.getValue(), 10E-6);
	}

	/** test a neuron compute with one single input */
	@Test
	public void testSingleNeuronWithValueSet()
	{
		// Setup
		HiddenNeuron neuron = new HiddenNeuron("testNeuron", new LinearActivationFunction());
		neuron.setValue(10);
		// Test
		neuron.compute();
		// Assertions
		Assert.assertEquals(0, neuron.getValue(), 10E-6);
	}

}
