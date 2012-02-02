package fr.hartland.serenna.core.neuron;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.hartland.serenna.core.activations.IDifferentiableActivationFunction;
import fr.hartland.serenna.core.connection.Connection;

public class NeuronLearningContext
{

	/**
	 * Compute the input neurons connected to the given neuron.
	 * 
	 * @param n
	 *            the neuron whose input neurons are searched.
	 * @return input neurons for a given neuron.
	 */
	public static Iterable<Neuron> getInputNeurons(Neuron n)
	{
		List<Neuron> result = new ArrayList<Neuron>();
		for (Connection connection : n.getInputConnections())
		{
			result.add(connection.getInputNeuron());
		}
		return result;
	}

	/**
	 * Computes the error delta for the given neuron.
	 * 
	 * @param neuron
	 *            the neuron whose delta is computed.
	 * @param deltas
	 *            the deltas for neurons. Current neuron outputs are assumed to be already computed.
	 */
	public static void computeDelta(Neuron neuron, Map<Neuron, Double> deltas)
	{
		double delta = 0;
		for (Connection connection : neuron.getOutputConnections())
		{
			Neuron out = connection.getOutputNeuron();
			delta += connection.getWeight() * deltas.get(out);
		}
		deltas.put(neuron, delta);
	}

	public static IDifferentiableActivationFunction getDifferentiableActivationFunction(Neuron neuron)
	{
		if (neuron.getActivationFunction() instanceof IDifferentiableActivationFunction)
		{
			return IDifferentiableActivationFunction.class.cast(neuron.getActivationFunction());
		}
		return null;
	}

}
