package fr.hartland.serenna.core.neurons;

import fr.hartland.serenna.core.activations.IActivationFunction;

public class HiddenNeuron extends Neuron
{
	private static int neuronIdentifier = 0;

	/**
	 * Default constructor.
	 * 
	 * @param activationFunction
	 *            the activation function associated to the neuron.
	 */
	public HiddenNeuron(IActivationFunction activationFunction)
	{
		this("hidden:" + neuronIdentifier++, activationFunction);
	}

	/**
	 * Constructor with custom name.
	 * 
	 * @param identifier
	 *            the neuron non necessarily unique identifier.
	 * @param activationFunction
	 *            the activation function associated to the neuron.
	 */
	public HiddenNeuron(String identifier, IActivationFunction activationFunction)
	{
		super(identifier, activationFunction);
	}

}
