package fr.hartland.serenna.core.neurons;

import junit.framework.Assert;

import org.junit.Test;

import fr.hartland.serenna.core.INode;
import fr.hartland.serenna.core.activations.LinearActivationFunction;
import fr.hartland.serenna.core.connection.Connection;

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
		INode neuron = new Neuron("testNeuron", new LinearActivationFunction());
		// Test
		neuron.compute();
		// Assertions
		Assert.assertEquals(0, neuron.getValue(), 10E-6);
	}

	/** test a neuron having an input neuron providing a value */
	@Test
	public void testNeuronSetup()
	{
		// Setup
		InputNeuron in = new InputNeuron("inputNeuron");
		IOutputNeuron out = new Neuron("testNeuron", new LinearActivationFunction());
		in.connect(out, new Connection(in, out));

		in.setValue(10);
		// Test
		out.compute();
		// Assertions
		Assert.assertEquals(10, out.getValue(), 10E-6);
	}
}
