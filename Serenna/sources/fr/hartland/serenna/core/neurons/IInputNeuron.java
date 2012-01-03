package fr.hartland.serenna.core.neurons;

import fr.hartland.serenna.core.INode;
import fr.hartland.serenna.core.connection.Connection;

/**
 * Input role for a neuron. An input neuron provides output connections to some other neuron.
 */
public interface IInputNeuron extends INode
{
	/**
	 * Append an output connection connection. The actual node become the input target for the connection.
	 * 
	 * @param output
	 *            the output connection connection.
	 */
	void addOutput(Connection output);

	/**
	 * Connects the current node to an output node with the provided connection.
	 * 
	 * @param outputNode
	 *            the output node to connect to.
	 * @param connection
	 *            the connection.
	 */
	void connect(IOutputNeuron outputNode, Connection connection);

}
