package fr.hartland.serenna.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic class for all possible neural network nodes.
 */
public abstract class Node {

	/** the value calculated during a computation step. */
	private double value;

	private final String name;

	private final List<Connection> inputs;
	private final List<Connection> outputs;

	/**
	 * Default constructor. Each node can have an identifier (non necessarily unique).
	 * 
	 * @param name
	 *            the node name.
	 */
	public Node(String name)
	{
		this.name = name;
		this.inputs = new ArrayList<Connection>();
		this.outputs = new ArrayList<Connection>();
	}

	/**
	 * Returns the node name.
	 * 
	 * @return the node name.
	 */
	public final String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return '(' + name + ')';
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
	 * Append an output connection connection. The actual node become the input target for the connection.
	 * 
	 * @param output
	 *            the output connection connection.
	 */
	public void addOutput(Connection output)
	{
		this.outputs.add(output);
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

	/**
	 * Returns the output connections defined for the current node.
	 * 
	 * @return the output connections.
	 */
	public Iterable<Connection> getOutputConnections()
	{
		return outputs;
	}

	/**
	 * Given weighted input value(s) provided to the node, compute the output value.
	 */
	public abstract void compute();

	/**
	 * Returns the value contained within the node if any; else return default value.
	 * 
	 * @return value computed during computation.
	 * @see #compute()
	 */
	public double getValue()
	{
		return value;
	}

	/**
	 * Defines the node value.
	 * 
	 * @param value
	 *            the value to set
	 */
	void setValue(double value)
	{
		this.value = value;
	}

}
