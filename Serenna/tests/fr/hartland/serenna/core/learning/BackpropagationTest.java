package fr.hartland.serenna.core.learning;

import org.junit.Assert;
import org.junit.Test;

import fr.hartland.serenna.core.NetworkLearningContext;
import fr.hartland.serenna.core.NeuralNetwork;
import fr.hartland.serenna.core.NeuralNetworkTest;

/**
 * Exposes {@link Backpropagation} learning algorithm.
 */
public class BackpropagationTest
{

	/**
	 * test the learning algorithm on a linear problem.
	 */
	@Test
	public void testLearning()
	{
		// Setup
		NeuralNetwork network = NeuralNetworkTest.buildNetwork(1, 1, 1);
		Samples trainingSet = SamplesTest.generateTestSamples();

		double learningRate = 0.05;

		Backpropagation trainer = new Backpropagation(trainingSet, learningRate);

		// Test
		trainer.train(new NetworkLearningContext(network));
		// Assertions
		
	}
	


}
