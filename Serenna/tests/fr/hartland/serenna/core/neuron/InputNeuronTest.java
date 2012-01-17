package fr.hartland.serenna.core.neuron;

import junit.framework.Assert;

import org.junit.Test;

import fr.hartland.serenna.core.neuron.InputNeuron;

/**
 * Exposes input neurons functionalities.
 * 
 */
public class InputNeuronTest
{
	/** Sets the input value and compute does nothing so output value is same as input. */
	@Test
	public void computeInputNeuron()
	{
		// Setup
		InputNeuron input = new InputNeuron("input");
		input.setValue(1);
		// Test
		input.compute();
		// Assert
		Assert.assertEquals(1, input.getValue(), 10E-6);
	}

}
