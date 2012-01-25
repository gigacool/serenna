package fr.hartland.serenna.core;

import org.junit.Assert;
import org.junit.Test;

import fr.hartland.serenna.core.activations.LinearActivationFunction;
import fr.hartland.serenna.core.connection.Connection;
import fr.hartland.serenna.core.connection.WeightedConnection;
import fr.hartland.serenna.core.neuron.HiddenNeuron;
import fr.hartland.serenna.core.neuron.InputNeuron;
import fr.hartland.serenna.core.neuron.Layer;
import fr.hartland.serenna.core.neuron.OutputNeuron;

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
	 * buildNetwork validates neural network building through layers.
	 */
	@Test
	public void buildNetwork()
	{
		// Setup
		NeuralNetwork network = new NeuralNetwork();
		{
			Layer<InputNeuron> inputLayer = Layer.buildInputLayer(10);
			Layer<HiddenNeuron> firstLayer = Layer.buildHiddenLayer(10, new LinearActivationFunction());
			Layer<HiddenNeuron> secondLayer = Layer.buildHiddenLayer(10, new LinearActivationFunction());
			Layer<OutputNeuron> outputLayer = Layer.buildOutputLayer(10, new LinearActivationFunction());

			WeightedConnection.buildConnections(inputLayer, firstLayer, 0.1);
			WeightedConnection.buildConnections(firstLayer, secondLayer, 0.1);
			WeightedConnection.buildConnections(secondLayer, outputLayer, 0.1);

			network.setInputLayer(inputLayer);
			network.setOutputLayer(outputLayer);
		}
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
		// Setup
		NeuralNetwork network = new NeuralNetwork();
		{
			Layer<InputNeuron> inputLayer = Layer.buildInputLayer(10);
			Layer<HiddenNeuron> firstLayer = Layer.buildHiddenLayer(10, new LinearActivationFunction());
			Layer<HiddenNeuron> secondLayer = Layer.buildHiddenLayer(10, new LinearActivationFunction());
			Layer<OutputNeuron> outputLayer = Layer.buildOutputLayer(10, new LinearActivationFunction());

			WeightedConnection.buildConnections(inputLayer, firstLayer, 0.1);
			WeightedConnection.buildConnections(firstLayer, secondLayer, 0.1);
			WeightedConnection.buildConnections(secondLayer, outputLayer, 0.1);

			network.setInputLayer(inputLayer);
			network.setOutputLayer(outputLayer);
		}
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
