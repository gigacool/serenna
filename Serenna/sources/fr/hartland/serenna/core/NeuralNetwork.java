package fr.hartland.serenna.core;

import java.util.ArrayList;
import java.util.List;

import fr.hartland.serenna.core.neurons.IOutputNeuron;
import fr.hartland.serenna.core.neurons.InputNeuron;
import fr.hartland.serenna.core.neurons.OutputNeuron;

/**
 * Neural network class.
 * 
 */
public class NeuralNetwork extends Node
{
	private final List<InputNeuron> inputNodes;
	private final List<IOutputNeuron> outputNodes;

	/**
	 * Default constructor.
	 * 
	 * @param name
	 *            the neural network non unique name identifier.
	 */
	public NeuralNetwork(String name)
	{
		super(name);
		this.inputNodes = new ArrayList<InputNeuron>(1);
		this.outputNodes = new ArrayList<IOutputNeuron>(1);
	}

	@Override
	public void compute()
	{
		for (IOutputNeuron neuron : outputNodes)
		{
			neuron.compute();
		}
	}

	/**
	 * Returns the network output values.
	 * 
	 * @return values computed during computation.
	 */
	@Override
	public double[] getValue()
	{
		double[] values = new double[outputNodes.size()];
		for (int i = 0; i < values.length; i++)
		{
			values[i] = outputNodes.get(i).getValue();
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
		if (values.length != inputNodes.size())
		{
			throw new RuntimeException("Incompatible number of input values; expected " + inputNodes.size() + " values but had "
					+ values.length);
		}
		for (int i = 0; i < values.length; i++)
		{
			inputNodes.get(i).setValue(values[i]);
		}
		// clean compute state within networks.
		for (IOutputNeuron neuron : outputNodes)
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
		inputNodes.add(in);
	}

	/**
	 * Add an output neuron to the network.
	 * 
	 * @param out
	 *            the output neuron.
	 */
	public void addOutput(OutputNeuron out)
	{
		outputNodes.add(out);
	}

	@Override
	public void clear()
	{
		for (IOutputNeuron neuron : outputNodes)
		{
			neuron.clear();
		}
	}

}
