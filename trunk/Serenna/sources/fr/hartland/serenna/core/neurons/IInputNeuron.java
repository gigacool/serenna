package fr.hartland.serenna.core.neurons;

import fr.hartland.serenna.core.Connection;
import fr.hartland.serenna.core.INode;

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
}
