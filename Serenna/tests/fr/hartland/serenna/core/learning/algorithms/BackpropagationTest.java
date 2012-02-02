package fr.hartland.serenna.core.learning.algorithms;

import org.junit.Test;

import fr.hartland.serenna.core.NetworkLearningContext;
import fr.hartland.serenna.core.NeuralNetwork;
import fr.hartland.serenna.core.NeuralNetworkTest;
import fr.hartland.serenna.core.learning.Samples;
import fr.hartland.serenna.core.learning.SamplesTest;
import fr.hartland.utils.trace.NullTracer;

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

		Backpropagation trainer = new Backpropagation(new NullTracer<Number>(), trainingSet, 0.05, 1000);

		// Test
		trainer.train(new NetworkLearningContext(network));
		// Assertions
	}

}
