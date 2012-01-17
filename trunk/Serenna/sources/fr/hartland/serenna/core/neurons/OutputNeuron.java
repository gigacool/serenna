package fr.hartland.serenna.core.neurons;

import fr.hartland.serenna.core.activations.IActivationFunction;

public class OutputNeuron extends Neuron
{
	private static int neuronIdentifier = 0;

	/**
	 * Default constructor.
	 * 
	 * @param activationFunction
	 *            the activation function associated to the neuron.
	 */
	public OutputNeuron(IActivationFunction activationFunction)
	{
		this("output:" + neuronIdentifier++, activationFunction);
	}

	/**
	 * Constructor with custom name.
	 * 
	 * @param identifier
	 *            the neuron non necessarily unique identifier.
	 * @param activationFunction
	 *            the activation function associated to the neuron.
	 */
	public OutputNeuron(String identifier, IActivationFunction activationFunction)
	{
		super(identifier, activationFunction);
	}

	@Override
	public void compute()
	{
		super.compute();
	}

	@Override
	public void clear()
	{
		super.clear();
	}

}
