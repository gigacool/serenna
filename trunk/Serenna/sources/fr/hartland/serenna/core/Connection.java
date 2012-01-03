package fr.hartland.serenna.core;

import fr.hartland.serenna.core.neurons.IInputNeuron;
import fr.hartland.serenna.core.neurons.IOutputNeuron;

/**
 * A connection provide a link from one node to another. The connection role is to convey values changed or unchanged (e.g. a
 * weighted connection).
 * 
 */
public class Connection
{

	private IInputNeuron inputNode;
	private IOutputNeuron outputNode;

	/**
	 * Default constructor. An arc goes from one node to another.
	 * 
	 * @param inputNode
	 *            the node providing value to the arc.
	 * @param outputNode
	 *            the destination node to get the value conducted by the arc.
	 */
	public Connection(IInputNeuron inputNode, IOutputNeuron outputNode)
	{
		this.inputNode = inputNode;
		this.outputNode = outputNode;

		this.inputNode.addOutput(this);
		this.outputNode.addInput(this);
	}

	/**
	 * Returns the arc input node.
	 * 
	 * @return the input node.
	 */
	public IInputNeuron getInputNode()
	{
		return inputNode;
	}

	/**
	 * Returns the arc output node.
	 * 
	 * @return the output node.
	 */
	public IOutputNeuron getOutputNode()
	{
		return outputNode;
	}

	/**
	 * Returns the value contained within the node if any; else return default value.
	 * 
	 * @return value computed during computation.
	 */
	public double getValue()
	{
		return inputNode.getValue();
	}

}
