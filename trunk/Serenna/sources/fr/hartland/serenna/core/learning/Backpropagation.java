package fr.hartland.serenna.core.learning;

import fr.hartland.serenna.core.NetworkLearningContext;
import fr.hartland.serenna.core.NeuralNetwork;

public class Backpropagation
{
	private Samples trainingSet;
	private double learningRate;

	private int numberOfIterations;
	private double errorLowerBound;

	/**
	 * Default constructor. Learning rate is set to 0.1.
	 * 
	 * @param trainingSet
	 *            the training set used for learning.
	 */
	public Backpropagation(NetworkLearningContext networkContext, Samples trainingSet)
	{
		this(trainingSet, 0.1);
	}

	/**
	 * Default constructor.
	 * 
	 * @param trainingSet
	 *            the training set used for learning.
	 * @param learningRate
	 *            back propagation learning rate (supposedly within [0:1]).
	 */
	public Backpropagation(Samples trainingSet, double learningRate)
	{
		this.trainingSet = trainingSet;
		this.learningRate = learningRate;

		this.numberOfIterations = 1000;
		errorLowerBound = 10E-15;
	}

	// For each example e in the training set
	// O = neural-net-output(network, e) ; forward pass
	// T = teacher output for e
	// Calculate error (T - O) at the output units
	// Compute delta_wh for all weights from hidden layer to output layer ; backward pass
	// Compute delta_wi for all weights from input layer to hidden layer ; backward pass continued
	// Update the weights in the network
	// Until all examples classified correctly or stopping criterion satisfied

	public void train(NetworkLearningContext networkContext)
	{
		trainingSet.reset();
		double error = error(networkContext, trainingSet);
		NeuralNetwork network = networkContext.getNetwork();
		int count = 0;
		while (error > errorLowerBound && count++ < numberOfIterations)
		{
			trainingSet.reset();
			error = 0;
			while (trainingSet.hasNext())
			{
				Sample next = trainingSet.next();
				error += networkContext.squareRootError(next.getInputs(), next.getOutput());
				networkContext.computeOutputDeltas(next.getOutput());
				networkContext.updateWeights(learningRate);
			}
			trainingSet.shuffle();
			System.out.println(error);
		}
	}

	double error(NetworkLearningContext networkContext, Samples testSet)
	{
		double error = 0;
		while (testSet.hasNext())
		{
			Sample next = testSet.next();
			error += networkContext.squareRootError(next.getInputs(), next.getOutput());
		}
		return error;
	}

}
