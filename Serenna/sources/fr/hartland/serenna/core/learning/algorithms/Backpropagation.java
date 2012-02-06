package fr.hartland.serenna.core.learning.algorithms;

import fr.hartland.serenna.core.NetworkLearningContext;
import fr.hartland.serenna.core.learning.LearningContext;
import fr.hartland.serenna.core.learning.Sample;
import fr.hartland.serenna.core.learning.Samples;
import fr.hartland.utils.trace.ConsoleTracer;
import fr.hartland.utils.trace.ITracer;

/**
 * Implements the typical back propagation algorithm.
 * 
 * @author cedric.hartland
 */
public class Backpropagation extends LearningContext
{
	/** Algorithm default learning rate. */
	public static final double DEFAULT_LEARNING_RATE = 0.1;

	/** Number of training iterations before stopping algorithm processing. */
	public static final int DEFAULT_NUMBER_ITERATIONS = 1000;

	private double learningRate;
	private int numberOfIterations;
	private double errorLowerBound;

	private final ITracer<Number> trace;

	/**
	 * Default constructor. Learning rate is set to 0.1.
	 * 
	 * @param trainingSet
	 *            the training set used for learning.
	 */
	public Backpropagation(Samples trainingSet)
	{
		this(new ConsoleTracer<Number>(), trainingSet, DEFAULT_LEARNING_RATE, DEFAULT_NUMBER_ITERATIONS);
	}

	/**
	 * Default constructor.
	 * 
	 * @param trace
	 *            The learning process trace.
	 * @param trainingSet
	 *            the training set used for learning.
	 * @param learningRate
	 *            back propagation learning rate (supposedly within [0:1]).
	 * @param numberOfIterations
	 *            Number of training iterations before stopping algorithm processing.
	 */
	public Backpropagation(ITracer<Number> trace, Samples trainingSet, double learningRate, int numberOfIterations)
	{
		super(trainingSet);
		this.trace = trace;
		this.learningRate = learningRate;
		this.numberOfIterations = numberOfIterations;
		this.errorLowerBound = 10E-15;
	}

	@Override
	public void train(NetworkLearningContext networkContext)
	{
		getTrainingSet().reset();
		double error = error(networkContext, getTrainingSet());

		trace.addDataRow(0, error);
		for (int iteration = 1; iteration <= numberOfIterations && error > errorLowerBound; iteration++)
		{
			getTrainingSet().reset();
			error = 0;
			while (getTrainingSet().hasNext())
			{
				Sample next = getTrainingSet().next();
				error += networkContext.squareRootError(next.getInputs(), next.getOutput());
				networkContext.computeOutputDeltas(next.getOutput());
				networkContext.updateWeights(learningRate);
			}
			getTrainingSet().shuffle();
			trace.addDataRow(iteration, error);
		}
	}

}
