package fr.hartland.serenna.core.connection;

import fr.hartland.serenna.core.neurons.Neuron;

/**
 * A weighted arc is an arc altering the value conducted from input neuron to output neuron via an multiplicative weight. A weight
 * of 1 does not alter the value.
 */
public class WeightedConnection extends Connection
{
	private double weight;

	/**
	 * Default constructor. Builds a weighted arc.
	 * 
	 * @param inputNeuron
	 *            the neuron providing value to the arc.
	 * @param outputNeuron
	 *            the destination neuron to get the value conducted by the arc.
	 * @param weight
	 *            the arc weight to apply to value incoming from input neuron.
	 */
	protected WeightedConnection(Neuron inputNeuron, Neuron outputNeuron, double weight)
	{
		super(inputNeuron, outputNeuron);
		this.weight = weight;
	}

	/**
	 * Connection factory builder.
	 * 
	 * @param inputNeuron
	 *            the neuron providing value to the arc.
	 * @param outputNeuron
	 *            the destination neuron to get the value conducted by the arc.
	 * @param weight
	 *            the arc weight to apply to value incoming from input neuron.
	 * @return the neuron properly set.
	 */
	public static WeightedConnection buildConnection(Neuron inputNeuron, Neuron outputNeuron, double weight)
	{
		WeightedConnection connection = new WeightedConnection(inputNeuron, outputNeuron, weight);
		inputNeuron.addOutput(connection);
		outputNeuron.addInput(connection);
		return connection;
	}

	/**
	 * (Re)Defines the arc weight.
	 * 
	 * @param weight
	 *            the new arc weight.
	 */
	void setWeight(double weight)
	{
		this.weight = weight;
	}

	@Override
	public double getValue()
	{
		return weight * super.getValue();
	}
}
