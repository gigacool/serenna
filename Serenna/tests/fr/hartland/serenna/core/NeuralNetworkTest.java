package fr.hartland.serenna.core;

import org.junit.Assert;
import org.junit.Test;

import fr.hartland.serenna.core.activations.LinearActivationFunction;
import fr.hartland.serenna.core.connection.Connection;
import fr.hartland.serenna.core.neuron.HiddenNeuron;
import fr.hartland.serenna.core.neuron.InputNeuron;
import fr.hartland.serenna.core.neuron.Layer;
import fr.hartland.serenna.core.neuron.Neuron;
import fr.hartland.serenna.core.neuron.OutputNeuron;

/**
 * Exposes neural network functionalities.
 * 
 */
public class NeuralNetworkTest
{
	/**
	 * Network factory for tests.
	 * 
	 * @param weight
	 *            default weight for all connections.
	 * @param layerSizes
	 *            #input, (#hidden)* #output
	 * @return the network with provided layer sizes and linear activation functions
	 */
	@SuppressWarnings("unchecked")
	public static NeuralNetwork buildNetwork(double weight, int... layerSizes)
	{
		Assert.assertTrue(layerSizes.length >= 2);

		NeuralNetwork network = new NeuralNetwork();
		{
			Layer<? extends Neuron> inputLayer = Layer.buildInputLayer(layerSizes[0]);
			network.setInputLayer((Layer<InputNeuron>) inputLayer);

			for (int i = 1; i < layerSizes.length - 1; i++)
			{
				Layer<? extends Neuron> targetLayer = Layer.buildHiddenLayer(layerSizes[i], new LinearActivationFunction());
				Connection.buildConnections(inputLayer, targetLayer, weight);
				inputLayer = targetLayer;
			}
			Layer<OutputNeuron> outputLayer = Layer.buildOutputLayer(layerSizes[layerSizes.length - 1],
					new LinearActivationFunction());
			Connection.buildConnections(inputLayer, outputLayer, weight);
			network.setOutputLayer(outputLayer);
		}
		return network;
	}

	/**
	 * buildNetwork validates neural network building through layers.
	 */
	@Test
	public void exposesNetworkBuilding()
	{
		// Setup
		NeuralNetwork network = buildNetwork(0.1, 10, 10, 10, 10);
		// Test
		network.setValue(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		network.compute();
		double[] outputs = network.getValue();
		// Assertions
		for (double output : outputs)
		{
			Assert.assertEquals(1.0, output, 1E-6);
		}
	}

	/**
	 * countNumberOfNeurons computes the number of neurons within the network.
	 */
	@Test
	public void countNumberOfNeurons()
	{
		NeuralNetwork network = buildNetwork(0.1, 10, 10, 10, 10);
		// Test
		Assert.assertEquals(40, network.size());
	}

	/**
	 * countNumberOfNeurons computes the number of neurons within the network.
	 */
	@Test
	public void countNumberOfNeuronsSimpleTopo()
	{
		// Setup
		NeuralNetwork network = new NeuralNetwork();
		{
			InputNeuron input = new InputNeuron("input");
			HiddenNeuron hidden1 = new HiddenNeuron("hidden1", new LinearActivationFunction());
			HiddenNeuron hidden2 = new HiddenNeuron("hidden2", new LinearActivationFunction());
			OutputNeuron output = new OutputNeuron("output", new LinearActivationFunction());

			Connection.buildConnection(input, hidden1);
			Connection.buildConnection(input, hidden2);
			Connection.buildConnection(hidden1, hidden2);
			Connection.buildConnection(hidden1, output);
			Connection.buildConnection(hidden2, output);

			network.addInput(input);
			network.addOutput(output);
		}
		// Test
		Assert.assertEquals(4, network.size());
	}

	/**
	 * simpleNetworkComputation
	 */
	@Test
	public void noHiddenLayersimpleNetworkComputation()
	{
		// Setup
		NeuralNetwork network = buildNetwork(1.0, 1, 1);
		// Test
		network.setValue(2);
		network.compute();
		double[] output = network.getValue();
		// Assertions
		Assert.assertEquals(1, output.length);
		Assert.assertEquals(2, output[0], 10E-6);
	}

	/**
	 * simpleNetworkComputation
	 */
	@Test
	public void simpleNetworkComputation()
	{
		// Setup
		NeuralNetwork network = buildNetwork(1.0, 1, 1, 1);
		// Test
		network.setValue(1);
		network.compute();
		double[] output = network.getValue();
		// Assertions
		Assert.assertEquals(1, output.length);
		Assert.assertEquals(1, output[0], 10E-6);
	}

	/**
	 * simpleNetworkComputation
	 */
	@Test
	public void networkComputation()
	{
		// Setup
		NeuralNetwork network = buildNetwork(1.0, 1, 10, 1);
		// Test
		network.setValue(1);
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
		NeuralNetwork network = buildNetwork(1.0, 1, 1, 1);
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
