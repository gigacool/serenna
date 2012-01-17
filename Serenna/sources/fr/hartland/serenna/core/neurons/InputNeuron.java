package fr.hartland.serenna.core.neurons;

import fr.hartland.serenna.core.activations.LinearActivationFunction;

public class InputNeuron extends Neuron
{
	private static int neuronIdentifier = 0;

	/**
	 * Default constructor.
	 */
	public InputNeuron()
	{
		this("input:" + neuronIdentifier++);
	}

	/**
	 * Constructor with custom name.
	 * 
	 * @param identifier
	 *            the neuron non necessarily unique identifier.
	 */
	public InputNeuron(String identifier)
	{
		super(identifier, new LinearActivationFunction());
	}

	@Override
	public void setValue(double value)
	{
		super.setValue(value);
	}

	@Override
	protected void compute()
	{
		// NOP
	}

}
