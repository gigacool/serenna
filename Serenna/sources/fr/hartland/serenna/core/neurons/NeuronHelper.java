package fr.hartland.serenna.core.neurons;

import fr.hartland.serenna.core.connection.Connection;

class NeuronHelper
{
	static void connect(IInputNeuron inputNeuron, IOutputNeuron outputNode, Connection connection)
	{
		outputNode.addInput(connection);
		inputNeuron.addOutput(connection);
	}
}
