package fr.hartland.serenna.core.neuron;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.hartland.serenna.core.NetworkLearningContext;
import fr.hartland.serenna.core.activations.IDifferentiableActivationFunction;
import fr.hartland.serenna.core.connection.Connection;

/**
 * This class provides learning algorithm facilities for neurons, following a similar model from
 * {@link NetworkLearningContext}.
 * 
 * @author cedric.hartland
 */
public class NeuronLearningContext
{
	private NeuronLearningContext()
	{
		// NOP
	}

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
			delta += connection.getWeight() * deltas.get(connection.getOutputNeuron());
		}
		deltas.put(neuron, delta);
	}

	/**
	 * Returns the neuron differentiable activation function. IF the activation function is not differentiable, as it is
	 * likely it cannot be used through the learning process, null is returned instead. It is up to the learning process
	 * to decide whether is is an issue or not.
	 * 
	 * @param neuron
	 *            the neuron whose activation function is required.
	 * @return the neuron differentiable activation function if applies, else, {@code null}.
	 */
	public static IDifferentiableActivationFunction getDifferentiableActivationFunction(Neuron neuron)
	{
		if (neuron.getActivationFunction() instanceof IDifferentiableActivationFunction)
		{
			return IDifferentiableActivationFunction.class.cast(neuron.getActivationFunction());
		}
		return null;
	}

}
