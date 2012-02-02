package fr.hartland.serenna.core.learning;

import fr.hartland.serenna.core.NetworkLearningContext;

/**
 * Provide a common context for learning algorithms.
 */
public abstract class LearningContext
{
	private final Samples trainingSet;

	/**
	 * Default constructor. Learning rate is set to 0.1.
	 * 
	 * @param trainingSet
	 *            the training set used for learning.
	 */
	protected LearningContext(Samples trainingSet)
	{
		this.trainingSet = trainingSet;
	}

	/**
	 * Given a sample set, computes the overall error rate over the network computations.
	 * 
	 * @param networkContext
	 *            the neural network learning context.
	 * @param testSet
	 *            the samples set.
	 * @return the summed squared root error on each sample for the given neural network.
	 */
	public double error(NetworkLearningContext networkContext, Samples testSet)
	{
		double error = 0;
		while (testSet.hasNext())
		{
			Sample next = testSet.next();
			error += networkContext.squareRootError(next.getInputs(), next.getOutput());
		}
		return error;
	}

	/**
	 * @return the trainingSet.
	 */
	public Samples getTrainingSet()
	{
		return trainingSet;
	}

	/**
	 * Performs the training algorithm.
	 * 
	 * @param networkContext
	 *            the neural network within its learning context.
	 */
	public abstract void train(NetworkLearningContext networkContext);

}
