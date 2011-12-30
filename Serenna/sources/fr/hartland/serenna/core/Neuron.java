package fr.hartland.serenna.core;

import fr.hartland.serenna.core.activations.IActivationFunction;

/**
 * Exposes core functionalities for a neuronal node.
 * 
 */
public class Neuron extends Node {

	/** The activation function */
	private IActivationFunction activationFunction;

	private boolean computed;

	/**
	 * Default constructor. The neuron has an identifier name and an activation function.
	 * 
	 * @param name
	 *            the neuron non unique identifier name.
	 * @param activationFunction
	 *            the activation function associated to the neuron.
	 */
	public Neuron(String name, IActivationFunction activationFunction)
	{
		super(name);
		this.activationFunction = activationFunction;
	}

	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            neuron name non unique identifier.
	 */
	public Neuron(String name)
	{
		super(name);
	}

	@Override
	public void compute()
	{
		synchronized (this)
		{
			if (computed)
			{
				return;
			}
			computed = true;
		}
		double tmp = 0;
		for (Arc arc : getInputArcs())
		{
			arc.getInputNode().compute();
			tmp += arc.getValue();
		}
		setValue(activationFunction.computeValue(tmp));
	}

}
