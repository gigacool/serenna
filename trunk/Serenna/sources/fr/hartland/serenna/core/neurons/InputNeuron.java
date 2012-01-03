package fr.hartland.serenna.core.neurons;

import java.util.ArrayList;
import java.util.List;

import fr.hartland.serenna.core.Node;
import fr.hartland.serenna.core.connection.Connection;

/**
 * Defines a specific input neuron. An input neuron takes a value as an input and return it to its outgoing connections.
 */
public class InputNeuron extends Node implements IInputNeuron
{
	private final List<Connection> outputs;
	/** the value calculated during a computation step. */
	private double value;

	/**
	 * Default constructor. Each node can have an identifier (non necessarily unique).
	 * 
	 * @param name
	 *            the neuron name identifier.
	 */
	public InputNeuron(String name)
	{
		super(name);
		this.outputs = new ArrayList<Connection>();
	}

	@Override
	public void connect(IOutputNeuron outputNode, Connection connection)
	{
		NeuronHelper.connect(this, outputNode, connection);
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

	/**
	 * Defines the node value.
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(double value)
	{
		this.value = value;
	}

	@Override
	public void compute()
	{
		// NOP -- keep the input value intact
	}


}
