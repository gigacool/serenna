package fr.hartland.serenna.networks;

import fr.hartland.serenna.core.NeuralNetwork;
import fr.hartland.serenna.core.activations.HyperbolicTangentActivationFunction;
import fr.hartland.serenna.core.activations.LinearActivationFunction;
import fr.hartland.serenna.core.connection.Connection;
import fr.hartland.serenna.core.neuron.InputNeuron;
import fr.hartland.serenna.core.neuron.Layer;
import fr.hartland.serenna.core.neuron.Neuron;
import fr.hartland.serenna.core.neuron.OutputNeuron;

/**
 * Provide factory methods to build generic architecture neural networks.
 * 
 * @author cedric.hartland
 * 
 */
public class NetworkFactory
{
	private NetworkFactory()
	{
		// NOP
	}

	/**
	 * Typical layered neural network builder. The connection weights are set randomly in [-1:1]. Hidden and output
	 * neurons are set with a default {@link HyperbolicTangentActivationFunction} activation function.
	 * 
	 * @param inputLayerSize
	 *            the number of input neurons within the input layer.
	 * @param outputLayerSize
	 *            the number of output neurons within the output layer.
	 * @param layerSizes
	 *            the ordered hidden network layer sizes.
	 * @return the network with provided layer sizes and linear activation functions
	 */
	public NeuralNetwork buildMultiLayerNeuralNetwork(int inputLayerSize, int outputLayerSize, int... layerSizes)
	{
		NeuralNetwork network = new NeuralNetwork();
		{
			Layer<InputNeuron> inputs = Layer.buildInputLayer(inputLayerSize);
			network.setInputLayer(inputs);

			Layer<? extends Neuron> inputLayer = inputs;
			for (int i = 0; i < layerSizes.length; i++)
			{
				Layer<? extends Neuron> targetLayer = Layer.buildHiddenLayer(layerSizes[i],
						new LinearActivationFunction());
				Connection.buildConnections(inputLayer, targetLayer);
				inputLayer = targetLayer;
			}
			Layer<OutputNeuron> outputLayer = Layer.buildOutputLayer(outputLayerSize, new LinearActivationFunction());
			Connection.buildConnections(inputLayer, outputLayer);
			network.setOutputLayer(outputLayer);
		}
		return network;
	}

}
