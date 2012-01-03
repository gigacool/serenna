package fr.hartland.serenna.core.neurons;

import java.util.ArrayList;
import java.util.List;

import fr.hartland.serenna.core.Connection;
import fr.hartland.serenna.core.activations.IActivationFunction;

/**
 * Exposes core functionalities for a neuronal node.
 * 
 */
public class HiddenNeuron extends InputNeuron
{
	private final List<Connection> inputs;

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
	public HiddenNeuron(String name, IActivationFunction activationFunction)
	{
		super(name);
		this.activationFunction = activationFunction;
		this.inputs = new ArrayList<Connection>();
	}

	/**
	 * Append an input connection connection. The actual node become the output target for the connection.
	 * 
	 * @param input
	 *            the input connection connection.
	 */
	public void addInput(Connection input)
	{
		this.inputs.add(input);
	}

	/**
	 * Returns the input connections defined for the current node.
	 * 
	 * @return the input connections.
	 */
	public Iterable<Connection> getInputConnections()
	{
		return inputs;
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
		for (Connection arc : getInputConnections())
		{
			arc.getInputNode().compute();
			tmp += arc.getValue();
		}
		setValue(activationFunction.computeValue(tmp));
	}

}
