package fr.hartland.serenna.core.learning;

import org.junit.Test;

import fr.hartland.serenna.core.NetworkLearningContext;
import fr.hartland.serenna.core.NeuralNetwork;
import fr.hartland.serenna.core.NeuralNetworkTest;
import fr.hartland.serenna.core.learning.algorithms.Backpropagation;
import fr.hartland.utils.trace.ConsoleTracer;
import fr.hartland.utils.trace.ITracer;

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

		ITracer<Number> tracer = new ConsoleTracer<Number>();
		tracer.setSeparator("\t");
		Backpropagation trainer = new Backpropagation(tracer, trainingSet, 0.05, 1000);

		// Test
		trainer.train(new NetworkLearningContext(network));
		// Assertions

	}

}
