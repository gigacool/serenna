package fr.hartland.serenna.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.hartland.serenna.core.connection.Connection;
import fr.hartland.serenna.core.neuron.InputNeuron;
import fr.hartland.serenna.core.neuron.Layer;
import fr.hartland.serenna.core.neuron.Neuron;
import fr.hartland.serenna.core.neuron.OutputNeuron;

/**
 * Neural network class.
 * 
 */
public class NeuralNetwork extends Unit
{
	private final List<InputNeuron> inputNeurons;
	private final List<OutputNeuron> outputNeurons;

	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            the neural network non unique name identifier.
	 */
	public NeuralNetwork(String name)
	{
		super(name);
		this.inputNeurons = new ArrayList<InputNeuron>(1);
		this.outputNeurons = new ArrayList<OutputNeuron>(1);
	}

	/**
	 * Default constructor.
	 */
	public NeuralNetwork()
	{
		this("network");
	}

	@Override
	public void compute()
	{
		for (OutputNeuron neuron : outputNeurons)
		{
			neuron.compute();
		}
	}

	/**
	 * Returns the network output values.
	 * 
	 * @return values computed during computation.
	 */
	public double[] getValue()
	{
		double[] values = new double[outputNeurons.size()];
		for (int i = 0; i < values.length; i++)
		{
			values[i] = outputNeurons.get(i).getValue();
		}
		return values;
	}

	/**
	 * Sets the input values on input neurons on the network.
	 * 
	 * @param values
	 */
	public void setValue(int... values)
	{
		if (values.length != inputNeurons.size())
		{
			throw new RuntimeException("Incompatible number of input values; expected " + inputNeurons.size()
					+ " values but had " + values.length);
		}
		for (int i = 0; i < values.length; i++)
		{
			inputNeurons.get(i).setValue(values[i]);
		}
		// clean neurons
		for (OutputNeuron neuron : outputNeurons)
		{
			neuron.clear();
		}
	}

	/**
	 * Add an input neuron to the network.
	 * 
	 * @param in
	 *            the input neuron.
	 */
	public void addInput(InputNeuron in)
	{
		inputNeurons.add(in);
	}

	/**
	 * Add an output neuron to the network.
	 * 
	 * @param out
	 *            the output neuron.
	 */
	public void addOutput(OutputNeuron out)
	{
		outputNeurons.add(out);
	}

	/**
	 * Sets the input neurons from the input layer.
	 * 
	 * @param inputLayer
	 *            the layer containing network input neurons.
	 */
	public void setInputLayer(Layer<InputNeuron> inputLayer)
	{
		inputNeurons.clear();
		for (InputNeuron neuron : inputLayer.getNeurons())
		{
			addInput(neuron);
		}
	}

	/**
	 * Sets the output neurons from the output layer.
	 * 
	 * @param outputLayer
	 *            the layer containing network output neurons.
	 */
	public void setOutputLayer(Layer<OutputNeuron> outputLayer)
	{
		outputNeurons.clear();
		for (OutputNeuron neuron : outputLayer.getNeurons())
		{
			addOutput(neuron);
		}
	}

	/**
	 * Computes the number of neurons connected within the network.
	 * 
	 * @return the number of neurons.
	 */
	public int size()
	{
		Set<Neuron> neurons = new HashSet<Neuron>();
		for (Neuron neuron : inputNeurons)
		{
			neurons.add(neuron);
			subSize(neurons, neuron);
		}
		return neurons.size();
	}

	private void subSize(Set<Neuron> neurons, Neuron neuron)
	{
		for (Connection connection : neuron.getOutputConnections())
		{
			Neuron target = connection.getOutputNeuron();
			if (!neurons.contains(target))
			{
				neurons.add(target);
				subSize(neurons, target);
			}
		}
	}

}
