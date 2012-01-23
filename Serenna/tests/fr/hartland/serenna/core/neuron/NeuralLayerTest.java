package fr.hartland.serenna.core.neuron;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import fr.hartland.serenna.core.activations.IActivationFunction;
import fr.hartland.serenna.core.activations.LinearActivationFunction;
import fr.hartland.serenna.core.connection.Connection;
import fr.hartland.serenna.core.connection.WeightedConnection;

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

	/**
	 * connectLayerToLayer
	 */
	@Test
	public void connectLayerToLayer()
	{
		// Setup
		IActivationFunction activationFunction = new LinearActivationFunction();
		Layer<HiddenNeuron> source = Layer.buildHiddenLayer(10, activationFunction);
		Layer<HiddenNeuron> target = Layer.buildHiddenLayer(10, activationFunction);
		// Test
		Connection.buildConnections(source, target);
		// Assertions
		for (Neuron sourceNeuron : source.getNeurons())
		{
			int count = 0;
			for (Connection connection : sourceNeuron.getOutputConnections())
			{
				boolean found = false;
				for (Neuron targetNeuron : target.getNeurons())
				{
					if (targetNeuron.equals(connection.getOutputNeuron()))
					{
						found = true;
						continue;
					}
				}
				Assert.assertTrue(found);
				count++;
			}
			Assert.assertEquals(10, count);
		}
	}

	/**
	 * connectNeuronToLayer
	 */
	@Test
	public void connectNeuronToLayer()
	{
		// Setup
		IActivationFunction activationFunction = new LinearActivationFunction();
		Layer<HiddenNeuron> source = Layer.buildHiddenLayer(10, activationFunction);
		HiddenNeuron target = new HiddenNeuron(activationFunction);
		// Test
		Connection.buildConnections(source, target);
		// Assertions
		for (Neuron sourceNeuron : source.getNeurons())
		{
			int count = 0;
			for (Connection connection : sourceNeuron.getOutputConnections())
			{
				Assert.assertEquals(target, connection.getOutputNeuron());
				count++;
			}
			Assert.assertEquals(1, count);
		}
	}

	/**
	 * connectNeuronToLayer
	 */
	@Test
	public void connectLayerToNeuron()
	{
		// Setup
		IActivationFunction activationFunction = new LinearActivationFunction();
		HiddenNeuron source = new HiddenNeuron(activationFunction);
		Layer<HiddenNeuron> target = Layer.buildHiddenLayer(10, activationFunction);
		// Test
		Connection.buildConnections(source, target);
		// Assertions
		for (Neuron targetNeuron : target.getNeurons())
		{
			int count = 0;
			for (Connection connection : targetNeuron.getInputConnections())
			{
				Assert.assertEquals(source, connection.getInputNeuron());
				count++;
			}
			Assert.assertEquals(1, count);
		}
	}

	/**
	 * connectLayerToLayer
	 */
	@Test
	public void weightedConnectLayerToLayerDefaultValue()
	{
		// Setup
		IActivationFunction activationFunction = new LinearActivationFunction();
		Layer<HiddenNeuron> source = Layer.buildHiddenLayer(10, activationFunction);
		Layer<HiddenNeuron> target = Layer.buildHiddenLayer(10, activationFunction);
		// Test
		WeightedConnection.buildConnections(source, target, 1);
		// Assertions
		for (Neuron sourceNeuron : source.getNeurons())
		{
			int count = 0;
			for (Connection connection : sourceNeuron.getOutputConnections())
			{
				Assert.assertTrue(connection instanceof WeightedConnection);
				WeightedConnection weightedConnection = (WeightedConnection) connection;
				
				boolean found = false;
				for (Neuron targetNeuron : target.getNeurons())
				{
					if (targetNeuron.equals(weightedConnection.getOutputNeuron()))
					{
						Assert.assertEquals(1, weightedConnection.getWeight(), 10E-6);
						found = true;
						continue;
					}
				}
				Assert.assertTrue(found);
				count++;
			}
			Assert.assertEquals(10, count);
		}
	}

	/**
	 * connectNeuronToLayer
	 */
	@Test
	public void weightedConnectNeuronToLayerDefaultValue()
	{
		// Setup
		IActivationFunction activationFunction = new LinearActivationFunction();
		Layer<HiddenNeuron> source = Layer.buildHiddenLayer(10, activationFunction);
		HiddenNeuron target = new HiddenNeuron(activationFunction);
		// Test
		WeightedConnection.buildConnections(source, target, 1);
		// Assertions
		for (Neuron sourceNeuron : source.getNeurons())
		{
			int count = 0;
			for (Connection connection : sourceNeuron.getOutputConnections())
			{
				Assert.assertTrue(connection instanceof WeightedConnection);
				WeightedConnection weightedConnection = (WeightedConnection) connection;
				Assert.assertEquals(1, weightedConnection.getWeight(), 10E-6);
				Assert.assertEquals(target, connection.getOutputNeuron());
				count++;
			}
			Assert.assertEquals(1, count);
		}
	}

	/**
	 * connectNeuronToLayer
	 */
	@Test
	public void weightedConnectLayerToNeuronDefaultValue()
	{
		// Setup
		IActivationFunction activationFunction = new LinearActivationFunction();
		HiddenNeuron source = new HiddenNeuron(activationFunction);
		Layer<HiddenNeuron> target = Layer.buildHiddenLayer(10, activationFunction);
		// Test
		WeightedConnection.buildConnections(source, target, 1);
		// Assertions
		for (Neuron targetNeuron : target.getNeurons())
		{
			int count = 0;
			for (Connection connection : targetNeuron.getInputConnections())
			{
				Assert.assertTrue(connection instanceof WeightedConnection);
				WeightedConnection weightedConnection = (WeightedConnection) connection;
				Assert.assertEquals(1, weightedConnection.getWeight(), 10E-6);
				Assert.assertEquals(source, connection.getInputNeuron());
				count++;
			}
			Assert.assertEquals(1, count);
		}
	}

	/**
	 * connectLayerToLayer
	 */
	@Test
	public void weightedConnectLayerToLayer()
	{
		// Setup
		IActivationFunction activationFunction = new LinearActivationFunction();
		Layer<HiddenNeuron> source = Layer.buildHiddenLayer(10, activationFunction);
		Layer<HiddenNeuron> target = Layer.buildHiddenLayer(10, activationFunction);
		// Test
		WeightedConnection.buildConnections(source, target);
		// Assertions
		for (Neuron sourceNeuron : source.getNeurons())
		{
			int count = 0;
			for (Connection connection : sourceNeuron.getOutputConnections())
			{
				Assert.assertTrue(connection instanceof WeightedConnection);
				WeightedConnection weightedConnection = (WeightedConnection) connection;
				Assert.assertTrue(Math.abs(weightedConnection.getWeight()) <= 1);
				
				boolean found = false;
				for (Neuron targetNeuron : target.getNeurons())
				{
					if (targetNeuron.equals(weightedConnection.getOutputNeuron()))
					{
						found = true;
						continue;
					}
				}
				Assert.assertTrue(found);
				count++;
			}
			Assert.assertEquals(10, count);
		}
	}

	/**
	 * connectNeuronToLayer
	 */
	@Test
	public void weightedConnectNeuronToLayer()
	{
		// Setup
		IActivationFunction activationFunction = new LinearActivationFunction();
		Layer<HiddenNeuron> source = Layer.buildHiddenLayer(10, activationFunction);
		HiddenNeuron target = new HiddenNeuron(activationFunction);
		// Test
		WeightedConnection.buildConnections(source, target);
		// Assertions
		for (Neuron sourceNeuron : source.getNeurons())
		{
			int count = 0;
			for (Connection connection : sourceNeuron.getOutputConnections())
			{
				Assert.assertTrue(connection instanceof WeightedConnection);
				WeightedConnection weightedConnection = (WeightedConnection) connection;
				Assert.assertTrue(Math.abs(weightedConnection.getWeight()) <= 1);
				
				Assert.assertEquals(target, weightedConnection.getOutputNeuron());
				count++;
			}
			Assert.assertEquals(1, count);
		}
	}

	/**
	 * connectNeuronToLayer
	 */
	@Test
	public void weightedConnectLayerToNeuron()
	{
		// Setup
		IActivationFunction activationFunction = new LinearActivationFunction();
		HiddenNeuron source = new HiddenNeuron(activationFunction);
		Layer<HiddenNeuron> target = Layer.buildHiddenLayer(10, activationFunction);
		// Test
		WeightedConnection.buildConnections(source, target);
		// Assertions
		for (Neuron targetNeuron : target.getNeurons())
		{
			int count = 0;
			for (Connection connection : targetNeuron.getInputConnections())
			{
				Assert.assertTrue(connection instanceof WeightedConnection);
				WeightedConnection weightedConnection = (WeightedConnection) connection;
				Assert.assertTrue(Math.abs(weightedConnection.getWeight()) <= 1);
				
				Assert.assertEquals(source, weightedConnection.getInputNeuron());
				count++;
			}
			Assert.assertEquals(1, count);
		}
	}

}
