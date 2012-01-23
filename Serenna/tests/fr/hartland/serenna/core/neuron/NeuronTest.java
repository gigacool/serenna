package fr.hartland.serenna.core.neuron;

import junit.framework.Assert;

import org.junit.Test;

import fr.hartland.serenna.core.activations.IActivationFunction;
import fr.hartland.serenna.core.activations.LinearActivationFunction;
import fr.hartland.serenna.core.connection.Connection;

/**
 * Test the neuron class functionalities.
 */
public class NeuronTest
{
	private static Neuron buildAnonymousNeuron(String neuronName, IActivationFunction activationFunction)
	{
		Neuron neuron = new Neuron(neuronName, activationFunction) {
			// NOP
		};
		return neuron;
	}

	/** test a neuron compute with no inputs */
	@Test
	public void testSingleNeuron()
	{
		// Setup
		Neuron neuron = buildAnonymousNeuron("testNeuron", new LinearActivationFunction());
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
		Neuron input = buildAnonymousNeuron("input", new LinearActivationFunction());
		Neuron target = buildAnonymousNeuron("target", new LinearActivationFunction());
		Connection.buildConnection(input, target);
		input.setValue(10);
		// Test
		target.compute();
		// Assertions
		// only the input neuron being an actual InputNeuron will return 10 as its output value.
		Assert.assertEquals(0, target.getValue(), 10E-6);
	}
}
