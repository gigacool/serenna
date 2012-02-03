package fr.hartland.serenna.core.learning;

/**
 * Represent a training or test sample. A Sample has input values to be submitted to the network as an input. The sample also
 * contains output values to be considered the expected output of the network for the provided input.
 */
public class Sample
{
	private final double[] inputs;
	private final double[] outputs;

	/**
	 * Default constructor. Note that the values array are not cloned.
	 * 
	 * @param inputs
	 *            the input values.
	 * @param ouputs
	 *            the expected output values.
	 */
	public Sample(double[] inputs, double[] ouputs)
	{
		this.inputs = inputs;
		this.outputs = ouputs;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (o.getClass() != this.getClass()) return false;
		Sample so = (Sample) o;
		if (so.inputs.length != this.inputs.length || so.outputs.length != this.outputs.length)
			return false;

		for (int i = 0; i < inputs.length; i++)
		{
			if (Math.abs(so.inputs[i] - this.inputs[i]) > 10E-12)
				return false;
		}
		for (int i = 0; i < outputs.length; i++)
		{
			if (Math.abs(so.outputs[i] - this.outputs[i]) > 10E-12)
				return false;
		}
		return true;
	}

	@Override
	public int hashCode()
	{
		int hash = 1;
		for (int i = 0; i < inputs.length; i++)
		{
			hash = hash * 10 + Double.valueOf(inputs[i]).hashCode();
		}
		for (int i = 0; i < outputs.length; i++)
		{
			hash = hash * 10 + Double.valueOf(outputs[i]).hashCode();
		}
		return hash;
	}

	/**
	 * Returns the number of output values for the sample.
	 * 
	 * @return number of output values.
	 */
	public int getOutputLength()
	{
		return outputs.length;
	}

	/**
	 * Returns the number of input values for the sample.
	 * 
	 * @return number of input values.
	 */
	public int getInputLength()
	{
		return inputs.length;
	}

	/**
	 * @return the expected outputs.
	 */
	public double[] getOutput()
	{
		return outputs;
	}

	/**
	 * @return the sample inputs.
	 */
	public double[] getInputs()
	{
		return inputs;
	}

}
