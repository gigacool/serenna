package fr.hartland.serenna.core.neurons;

import java.util.ArrayList;
import java.util.List;

import fr.hartland.serenna.core.Node;
import fr.hartland.serenna.core.activations.IActivationFunction;
import fr.hartland.serenna.core.connection.Connection;

/**
 * Exposes core functionalities for a neuronal node.
 */
public class Neuron extends Node implements IInputNeuron, IOutputNeuron
{
	/** the value calculated during a computation step. */
	private double value;

	private final List<Connection> inputs;
	private final List<Connection> outputs;

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
		this.inputs = new ArrayList<Connection>();
		this.outputs = new ArrayList<Connection>();
		this.activationFunction = activationFunction;
	}

	@Override
	public void connect(IOutputNeuron outputNode, Connection connection)
	{
		NeuronHelper.connect(this, outputNode, connection);
	}

	/**
	 * Append an input connection connection. The actual node become the output target for the connection.
	 * 
	 * @param input
	 *            the input connection connection.
	 */
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
	public void addOutput(Connection output)
	{
		this.outputs.add(output);
	}

	/**
	 * Returns the output connections defined for the current node.
	 * 
	 * @return the output connections.
	 */
	protected Iterable<Connection> getOutputConnections()
	{
		return outputs;
	}

	@Override
	public Double getValue()
	{
		return value;
	}

}
