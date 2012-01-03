package fr.hartland.serenna.core;

import org.junit.Assert;
import org.junit.Test;

import fr.hartland.serenna.core.activations.LinearActivationFunction;
import fr.hartland.serenna.core.connection.Connection;
import fr.hartland.serenna.core.neurons.InputNeuron;
import fr.hartland.serenna.core.neurons.Neuron;
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
			InputNeuron in = new InputNeuron("input");
			Neuron hidden = new Neuron("hidden", new LinearActivationFunction());
			OutputNeuron out = new OutputNeuron("output", new LinearActivationFunction());

			in.connect(hidden, new Connection(in, hidden));
			hidden.connect(out, new Connection(hidden, out));

			network.addInput(in);
			network.addOutput(out);
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
