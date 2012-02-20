package fr.hartland.serenna.core.activations;

/**
 * Provide a typical sigmoid activation function with low calculation cost.
 * 
 * @author cedric.hartland
 */
public class HyperbolicTangentActivationFunction implements IDifferentiableActivationFunction
{

	/**
	 * Default constructor.
	 */
	public HyperbolicTangentActivationFunction()
	{
		// NOP
	}

	@Override
	public double computeValue(double inputValue)
	{
		return Math.tanh(inputValue);
	}

	/**
	 * <pre>
	 * dtanh(x)/dx = 1 -tanh²(x)
	 * </pre>
	 */
	@Override
	public double computeDerivedValue(double outputValue)
	{
		return 1 - outputValue * outputValue;
	}

}
