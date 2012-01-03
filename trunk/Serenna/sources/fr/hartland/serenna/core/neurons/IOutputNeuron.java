package fr.hartland.serenna.core.neurons;

import fr.hartland.serenna.core.INode;
import fr.hartland.serenna.core.connection.Connection;

/**
 * Defines a specific output neuron. An output neuron fetches values from its inputs.
 */
public interface IOutputNeuron extends INode
{
	/**
	 * Append an input connection connection. The actual node become the output target for the connection.
	 * 
	 * @param input
	 *            the input connection connection.
	 */
	void addInput(Connection input);
}
