package fr.hartland.serenna.core.connection;

import fr.hartland.serenna.core.neurons.Neuron;

/**
 * A connection provide a link from one neuron to another. The connection role is to convey values changed or unchanged (e.g. a
 * weighted connection).
 * 
 */
public class Connection
{
	private Neuron inputNeuron;
	private Neuron outputNeuron;

	/**
	 * Default constructor. An arc goes from one neuron to another.
	 * 
	 * @param inputNeuron
	 *            the neuron providing value to the arc.
	 * @param outputNeuron
	 *            the destination neuron to get the value conducted by the arc.
	 */
	protected Connection(Neuron inputNeuron, Neuron outputNeuron)
	{
		this.inputNeuron = inputNeuron;
		this.outputNeuron = outputNeuron;
	}

	/**
	 * Connection factory builder.
	 * 
	 * @param inputNeuron
	 *            the neuron providing value to the arc.
	 * @param outputNeuron
	 *            the destination neuron to get the value conducted by the arc.
	 * @return the neuron properly set.
	 */
	public static Connection buildConnection(Neuron inputNeuron, Neuron outputNeuron)
	{
		Connection connection = new Connection(inputNeuron, outputNeuron);
		inputNeuron.addOutput(connection);
		outputNeuron.addInput(connection);
		return connection;
	}

	/**
	 * Returns the arc input neuron.
	 * 
	 * @return the input neuron.
	 */
	public Neuron getInputNeuron()
	{
		return inputNeuron;
	}

	/**
	 * Returns the arc output neuron.
	 * 
	 * @return the output neuron.
	 */
	Neuron getOutputNeuron()
	{
		return outputNeuron;
	}

	/**
	 * Returns the value contained within the neuron if any; else return default value.
	 * 
	 * @return value computed during computation.
	 */
	public double getValue()
	{
		return inputNeuron.getValue();
	}
}
