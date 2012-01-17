package fr.hartland.serenna.core;

import org.junit.Assert;
import org.junit.Test;

import fr.hartland.serenna.core.activations.LinearActivationFunction;
import fr.hartland.serenna.core.connection.Connection;
import fr.hartland.serenna.core.neurons.HiddenNeuron;
import fr.hartland.serenna.core.neurons.InputNeuron;
import fr.hartland.serenna.core.neurons.OutputNeuron;

/**
 * Exposes neural network functionalities.
 * 
 */
public class NeuralNetworkTest
{
	private static NeuralNetwork generate111LinearNetwork()
	{
		NeuralNetwork network = new NeuralNetwork("network");
		{
			InputNeuron input = new InputNeuron("input");
			HiddenNeuron hidden = new HiddenNeuron("hidden", new LinearActivationFunction());
			OutputNeuron output = new OutputNeuron("output", new LinearActivationFunction());

			Connection.buildConnection(input, hidden);
			Connection.buildConnection(hidden, output);

			network.addInput(input);
			network.addOutput(output);
		}
		return network;
	}

	/**
	 * simpleNetworkComputation
	 */
	@Test
	public void simpleNetworkComputation()
	{
		// Setup
		NeuralNetwork network = generate111LinearNetwork();
		// Test
		network.setValue(10);
		network.compute();
		double[] output = network.getValue();
		// Assertions
		Assert.assertEquals(1, output.length);
		Assert.assertEquals(10, output[0], 10E-6);
	}

	/**
	 * simpleNetworkComputations have several steps follow.
	 */
	@Test
	public void simpleNetworkComputations()
	{
		NeuralNetwork network = generate111LinearNetwork();
		// Test
		for (int i = 0; i < 10; i++)
		{
			network.setValue(i);
			network.compute();
			double[] output = network.getValue();
			// Assertions
			Assert.assertEquals(1, output.length);
			Assert.assertEquals(i, output[0], 10E-6);
		}
	}

}
