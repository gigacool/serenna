package fr.hartland.serenna.core.connection;

import fr.hartland.serenna.core.neuron.HiddenNeuron;
import fr.hartland.serenna.core.neuron.Layer;
import fr.hartland.serenna.core.neuron.Neuron;
import fr.hartland.serenna.utils.RandomProvider;

/**
 * A connection provide a link from one neuron to another. The connection role is to convey values changed or unchanged (e.g. a
 * weighted connection).
 * 
 */
public class Connection
{
	private Neuron inputNeuron;
	private Neuron outputNeuron;
	private double weight;

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
		this(inputNeuron, outputNeuron, 1);
	}

	/**
	 * Default constructor. An arc goes from one neuron to another.
	 * 
	 * @param inputNeuron
	 *            the neuron providing value to the arc.
	 * @param outputNeuron
	 *            the destination neuron to get the value conducted by the arc.
	 * @param weight
	 *            the arc weight to apply to value incoming from input neuron.
	 */
	protected Connection(Neuron inputNeuron, Neuron outputNeuron, double weight)
	{
		this.inputNeuron = inputNeuron;
		this.outputNeuron = outputNeuron;
		this.weight = weight;
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
	public static Connection buildConnection(Neuron inputNeuron, Neuron outputNeuron, double weight)
	{
		Connection connection = new Connection(inputNeuron, outputNeuron, weight);
		inputNeuron.addOutput(connection);
		outputNeuron.addInput(connection);
		return connection;
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
	 * (Re)Defines the arc weight.
	 * 
	 * @param weight
	 *            the new arc weight.
	 */
	public void setWeight(double weight)
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

	/**
	 * Returns the value contained within the neuron if any; else return default value.
	 * 
	 * @return value computed during computation.
	 */
	public double getValue()
	{
		return weight * inputNeuron.getValue();
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
	public Neuron getOutputNeuron()
	{
		return outputNeuron;
	}

}
