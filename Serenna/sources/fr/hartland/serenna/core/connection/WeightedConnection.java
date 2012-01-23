package fr.hartland.serenna.core.connection;

import fr.hartland.serenna.core.neuron.HiddenNeuron;
import fr.hartland.serenna.core.neuron.Layer;
import fr.hartland.serenna.core.neuron.Neuron;
import fr.hartland.serenna.utils.RandomProvider;

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

	/**
	 * Returns the connection weight value.
	 * 
	 * @return the weight.
	 */
	public double getWeight()
	{
		return weight;
	}
	
	@Override
	public double getValue()
	{
		return weight * super.getValue();
	}

	/**
	 * Connection factory builder between two layers. Each neuron from one layer is fully connected to all other neurons from the
	 * other layer.
	 * 
	 * @param source
	 *            the source layer.
	 * @param target
	 *            the destination layer.
	 * @param defaultWeight
	 *            the default connection weights.
	 */
	public static void buildConnections(Layer<? extends Neuron> source, Layer<? extends Neuron> target, double defaultWeight)
	{
		for (Neuron sourceNeuron : source.getNeurons())
		{
			for (Neuron targetNeuron : target.getNeurons())
			{
				buildConnection(sourceNeuron, targetNeuron, defaultWeight);
			}
		}
	}

	/**
	 * Connection factory builder between two layers. Each neuron from one layer is fully connected to all other neurons from the
	 * other layer. Each connection weights are set randomly in [-1:1].
	 * 
	 * @param source
	 *            the source layer.
	 * @param target
	 *            the destination layer.
	 */
	public static void buildConnections(Layer<? extends Neuron> source, Layer<? extends Neuron> target)
	{
		for (Neuron sourceNeuron : source.getNeurons())
		{
			for (Neuron targetNeuron : target.getNeurons())
			{
				buildConnection(sourceNeuron, targetNeuron, RandomProvider.getInstance().nextDouble(-1, 1));
			}
		}
	}

	/**
	 * Connection factory builder between one layer and a neuron. Each neuron from source layer is fully connected to target
	 * neuron.
	 * 
	 * @param source
	 *            the source layer.
	 * @param target
	 *            the destination neuron.
	 * @param defaultWeight
	 *            the default connection weights.
	 */
	public static void buildConnections(Layer<HiddenNeuron> source, HiddenNeuron target, double defaultWeight)
	{
		for (Neuron sourceNeuron : source.getNeurons())
		{
			buildConnection(sourceNeuron, target, defaultWeight);
		}
	}

	/**
	 * Connection factory builder between one layer and a neuron. Each neuron from source layer is fully connected to target
	 * neuron. Each connection weights are set randomly in [-1:1].
	 * 
	 * @param source
	 *            the source layer.
	 * @param target
	 *            the destination neuron.
	 */
	public static void buildConnections(Layer<HiddenNeuron> source, HiddenNeuron target)
	{
		for (Neuron sourceNeuron : source.getNeurons())
		{
			buildConnection(sourceNeuron, target, RandomProvider.getInstance().nextDouble(-1, 1));
		}
	}

	/**
	 * Connection factory builder between one layer and a neuron. Each neuron from target layer is fully connected to source
	 * neuron.
	 * 
	 * @param source
	 *            the source neuron.
	 * @param target
	 *            the destination layer.
	 * @param defaultWeight
	 *            the default connection weights.
	 */
	public static void buildConnections(HiddenNeuron source, Layer<HiddenNeuron> target, double defaultWeight)
	{
		for (Neuron targetNeuron : target.getNeurons())
		{
			buildConnection(source, targetNeuron, defaultWeight);
		}
	}

	/**
	 * Connection factory builder between one layer and a neuron. Each neuron from target layer is fully connected to source
	 * neuron. Each connection weights are set randomly in [-1:1].
	 * 
	 * @param source
	 *            the source neuron.
	 * @param target
	 *            the destination layer.
	 */
	public static void buildConnections(HiddenNeuron source, Layer<HiddenNeuron> target)
	{
		for (Neuron targetNeuron : target.getNeurons())
		{
			buildConnection(source, targetNeuron, RandomProvider.getInstance().nextDouble(-1, 1));
		}
	}

	
}
