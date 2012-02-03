package fr.hartland.serenna.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.hartland.serenna.core.activations.IDifferentiableActivationFunction;
import fr.hartland.serenna.core.connection.Connection;
import fr.hartland.serenna.core.neuron.Neuron;
import fr.hartland.serenna.core.neuron.NeuronLearningContext;
import fr.hartland.serenna.utils.ReverseListIterator;

/**
 * Provide a dedicated context to apply learning algorithms on neural networks. The main reason to have a separate class concerns
 * the separation of concepts from network value calculations and network training.
 *
 * @author cedric.hartland
 */
public class NetworkLearningContext
{
	private final NeuralNetwork network;
	private final Map<Neuron, Double> neuronDeltas;

	private final Set<Neuron> visited;
	private final List<Neuron> orderedNeurons;

    /**
     * Default constructor. Setup the learning context for the provided neural network.
     *
     * @param network
     *            the neural network.
     */
	public NetworkLearningContext(NeuralNetwork network)
	{
		this.network = network;
		this.neuronDeltas = new HashMap<Neuron, Double>();
		this.visited = new HashSet<Neuron>();

		this.orderedNeurons = new ArrayList<Neuron>();
		for (Neuron neuron : network.getOutputLayer())
		{
			visit(orderedNeurons, visited, neuron);
		}
	}

	/**
	 * Returns the network associated to the network learning context.
	 *
	 * @return the neural network.
	 */
	public NeuralNetwork getNetwork()
	{
		return network;
	}

	/**
	 * Depth first search algorithm to compute ordered list of neurons through their connections.
	 *
	 * @param orderedNeurons
	 *            the result list.
	 * @param visitedNeurons
	 *            the neurons already visited.
	 * @param neuron
	 *            the neuron to visit.
	 */
	private static void visit(List<Neuron> orderedNeurons, Set<Neuron> visitedNeurons, Neuron neuron)
	{
		if (visitedNeurons.contains(neuron))
		{
			return;
		}
		visitedNeurons.add(neuron);
		for (Neuron parent : NeuronLearningContext.getInputNeurons(neuron))
		{
			visit(orderedNeurons, visitedNeurons, parent);
		}
		orderedNeurons.add(neuron);
	}

    /**
     * Computes the square root error of the network output to the expected output, for a given input.
     *
     * @param input
     *            the network input value.
     * @param expectedOutput
     *            the expected network output result.
     * @return the square root error in between the output neuron actual and expected values.
     */
	public double squareRootError(double[] input, double[] expectedOutput)
	{
		double result = 0;
		network.setValue(input);
		network.compute();
		double[] actual = network.getValue();
		for (int i = 0; i < actual.length; i++)
		{
			result += Math.sqrt((actual[i] - expectedOutput[i]) * (actual[i] - expectedOutput[i]));
		}
		return result;
	}

	/**
	 * Computes the error delta for each neurons, for the given difference between the actual network output and the provided
	 * expected output.
	 *
	 * @param expectedOutputs
	 *            the network expected output.
	 */
	public void computeOutputDeltas(double[] expectedOutputs)
	{
		assert (network.getOutputLayer().size() == expectedOutputs.length);
		visited.clear();
		// output neurons
		for (int i = 0; i < expectedOutputs.length; i++)
		{
			Neuron neuron = network.getOutputLayer().get(i);
			neuronDeltas.put(neuron, expectedOutputs[i] - neuron.getValue());
			visited.add(neuron);
		}
		// other neurons
		for (Neuron neuron : ReverseListIterator.reversed(orderedNeurons))
		{
			if (visited.contains(neuron))
			{
				continue;
			}
			NeuronLearningContext.computeDelta(neuron, neuronDeltas);
			visited.add(neuron);
		}
	}

	/**
	 * Operate a weight update accordingly to the following function : <code> w_{ij}' = w_{ij} + eta.deltaErr.df(x)/x</code>
	 *
	 * @param learningRate
	 *            learning step rate.
	 */
	public void updateWeights(double learningRate)
	{
		for (Neuron neuron : orderedNeurons)
		{
			for (Connection connection : neuron.getOutputConnections())
			{
				IDifferentiableActivationFunction f = NeuronLearningContext.getDifferentiableActivationFunction(connection
						.getOutputNeuron());
				double newWeight = connection.getWeight() + learningRate * neuronDeltas.get(connection.getOutputNeuron())
						* f.computeDerivedValue(connection.getOutputNeuron().getValue());
				connection.setWeight(newWeight);
			}
		}
	}

}
