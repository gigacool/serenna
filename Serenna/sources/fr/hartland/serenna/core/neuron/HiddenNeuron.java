package fr.hartland.serenna.core.neuron;

import fr.hartland.serenna.core.activations.IActivationFunction;

/**
 * Provide hidden neuron specific behavior (i.e. typical neuron behavior as opposed to input or output neurons).
 */
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
