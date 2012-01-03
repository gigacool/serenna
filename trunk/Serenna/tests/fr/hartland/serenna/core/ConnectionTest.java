package fr.hartland.serenna.core;

import org.junit.Assert;
import org.junit.Test;

import fr.hartland.serenna.core.activations.LinearActivationFunction;
import fr.hartland.serenna.core.neurons.IInputNeuron;
import fr.hartland.serenna.core.neurons.IOutputNeuron;
import fr.hartland.serenna.core.neurons.InputNeuron;
import fr.hartland.serenna.core.neurons.OutputNeuron;

/**
 * Exposes {@link Connection} functionalities.
 * 
 */
public class ConnectionTest
{

	/**
	 * Test Connection creation
	 */
	@Test
	public void connectionCreation()
	{
		// Setup
		IInputNeuron node0 = new InputNeuron("input");
		IOutputNeuron node1 = new OutputNeuron("output", new LinearActivationFunction());
		// Test
		Connection connection = new Connection(node0, node1);
		// Assertions
		Assert.assertEquals(node0, connection.getInputNode());
		Assert.assertEquals(node1, connection.getOutputNode());
	}
}
