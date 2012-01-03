package fr.hartland.serenna.core.neurons;

import java.util.ArrayList;
import java.util.List;

import fr.hartland.serenna.core.Connection;
import fr.hartland.serenna.core.Node;
import fr.hartland.serenna.core.activations.IActivationFunction;

/**
 * Defines a specific output neuron. An output neuron takes values from its inputs and processes it so it can be fetched outside
 * of the network.
 */
public class OutputNeuron extends Node implements IOutputNeuron
{
	/** the value calculated during a computation step. */
	private double value;

	/** The activation function */
	private IActivationFunction activationFunction;

	private final List<Connection> inputs;

	private boolean computed;

	/**
	 * Default constructor. The neuron has an identifier name and an activation function.
	 * 
	 * @param name
	 *            the neuron non unique identifier name.
	 * @param activationFunction
	 *            the activation function associated to the neuron.
	 */
	public OutputNeuron(String name, IActivationFunction activationFunction)
	{
		super(name);
		this.inputs = new ArrayList<Connection>();
		this.activationFunction = activationFunction;
	}

	@Override
	public void addInput(Connection input)
	{
		this.inputs.add(input);
	}

	/**
	 * Returns the input connections defined for the current node.
	 * 
	 * @return the input connections.
	 */
	protected Iterable<Connection> getInputConnections()
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
		for (Connection connection : getInputConnections())
		{
			connection.getInputNode().compute();
			tmp += connection.getValue();
		}
		this.value = activationFunction.computeValue(tmp);
	}

	@Override
	public double getValue()
	{
		return value;
	}

}
