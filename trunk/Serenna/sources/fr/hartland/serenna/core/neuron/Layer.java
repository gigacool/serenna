package fr.hartland.serenna.core.neuron;

import java.util.ArrayList;
import java.util.List;

import fr.hartland.serenna.core.activations.IActivationFunction;

/**
 * A {@link Layer} instance proposes layered network building simplifications.
 * 
 * @param <T>
 *            a neuron type.
 */
public class Layer<T extends Neuron>
{
	private final List<T> neurons;

	private Layer(int numberOfNeurons)
	{
		this.neurons = new ArrayList<T>(numberOfNeurons);
	}

	/**
	 * Returns the layer neurons.
	 * 
	 * @return the neurons within the layer.
	 */
	public Iterable<T> getNeurons()
	{
		return neurons;
	}

	/**
	 * Hidden neuron layer factory.
	 * 
	 * @param numberOfNeurons
	 *            the number of neurons within the layer.
	 * @param activationFunction
	 *            the activation function instance to be set within the neurons.
	 * @return the layer containing hidden neurons.
	 */
	public static Layer<HiddenNeuron> buildHiddenLayer(int numberOfNeurons, IActivationFunction activationFunction)
	{
		Layer<HiddenNeuron> layer = new Layer<HiddenNeuron>(numberOfNeurons);
		for (int i = 0; i < numberOfNeurons; i++)
		{
			layer.neurons.add(new HiddenNeuron(activationFunction));
		}
		return layer;
	}

	/**
	 * Output neuron layer factory.
	 * 
	 * @param numberOfNeurons
	 *            the number of neurons within the layer.
	 * @param activationFunction
	 *            the activation function instance to be set within the neurons.
	 * @return the layer containing output neurons.
	 */
	public static Layer<OutputNeuron> buildOutputLayer(int numberOfNeurons, IActivationFunction activationFunction)
	{
		Layer<OutputNeuron> layer = new Layer<OutputNeuron>(numberOfNeurons);
		for (int i = 0; i < numberOfNeurons; i++)
		{
			layer.neurons.add(new OutputNeuron(activationFunction));
		}
		return layer;
	}

	/**
	 * Input neuron layer factory.
	 * 
	 * @param numberOfNeurons
	 *            the number of neurons within the layer.
	 * @return the layer containing input neurons.
	 */
	public static Layer<InputNeuron> buildInputLayer(int numberOfNeurons)
	{
		Layer<InputNeuron> layer = new Layer<InputNeuron>(numberOfNeurons);
		for (int i = 0; i < numberOfNeurons; i++)
		{
			layer.neurons.add(new InputNeuron());
		}
		return layer;
	}

}
