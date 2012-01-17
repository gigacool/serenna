package fr.hartland.serenna.core.neurons;

import java.util.ArrayList;
import java.util.List;

import fr.hartland.serenna.core.Unit;
import fr.hartland.serenna.core.activations.IActivationFunction;
import fr.hartland.serenna.core.connection.Connection;

/**
 * Exposes core functionalities for a neuronal neuron.
 */
public abstract class Neuron extends Unit
{
	private static int neuronIdentifier = 0;

	private final List<Connection> inputs;
	private final List<Connection> outputs;

	/** The activation function */
	private IActivationFunction activationFunction;

	/** the value calculated during a computation step. */
	private double value;

	/**
	 * Default constructor. The neuron has an identifier name and an activation function.
	 * 
	 * @param name
	 *            the neuron non unique identifier name.
	 * @param activationFunction
	 *            the activation function associated to the neuron.
	 */
	protected Neuron(String name, IActivationFunction activationFunction)
	{
		super(name);
		this.inputs = new ArrayList<Connection>();
		this.outputs = new ArrayList<Connection>();
		this.activationFunction = activationFunction;
	}

	/**
	 * Default constructor.
	 * 
	 * @param activationFunction
	 *            the activation function associated to the neuron.
	 */
	public Neuron(IActivationFunction activationFunction)
	{
		this("neuron:" + neuronIdentifier++, activationFunction);
	}

	/**
	 * Returns the activation function defined for the neuron.
	 * 
	 * @return the activation function.
	 */
	protected IActivationFunction getActivationFunction()
	{
		return activationFunction;
	}

	/**
	 * Returns the value contained within the node if any; else return default value.
	 * 
	 * @return value computed during computation.
	 */
	public double getValue()
	{
		return value;
	}

	/**
	 * Defines the neuron value.
	 * 
	 * @param value
	 *            the value to set
	 */
	void setValue(double value)
	{
		this.value = value;
	}

	/**
	 * Append an input connection connection. The actual neuron become the output target for the connection.
	 * 
	 * @param input
	 *            the input connection connection.
	 */
	public void addInput(Connection input)
	{
		this.inputs.add(input);
	}

	/**
	 * Returns the input connections defined for the current neuron.
	 * 
	 * @return the input connections.
	 */
	protected Iterable<Connection> getInputConnections()
	{
		return inputs;
	}

	/**
	 * Add an output connection to this neuron.
	 * 
	 * @param output
	 *            the output connection linking to another neuron.
	 */
	public void addOutput(Connection output)
	{
		this.outputs.add(output);
	}

	/**
	 * Returns the output connections defined for the current neuron.
	 * 
	 * @return the output connections.
	 */
	protected Iterable<Connection> getOutputConnections()
	{
		return outputs;
	}

	private boolean isComputing;

	@Override
	protected void compute()
	{
		synchronized (this)
		{
			if (isComputing)
			{
				return;
			}
			isComputing = true;
		}
		double tmp = 0;
		for (Connection connection : getInputConnections())
		{
			connection.getInputNeuron().compute();
			tmp += connection.getValue();
		}
		setValue(this.getActivationFunction().computeValue(tmp));
	}

	/**
	 * Clean the compute step afterwards.
	 */
	protected void clear()
	{
		isComputing = false;
		for (Connection inputConnection : inputs)
		{
			inputConnection.getInputNeuron().clear();
		}
	}

}
