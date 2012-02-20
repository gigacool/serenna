package fr.hartland.serenna.core.activations;

/**
 * 
 * Defines a typical logistic function. One may want to prefer using the typical
 * {@link HyperbolicTangentActivationFunction} instead.
 * 
 * <pre>
 * f(x) = 1 / (1 + e^{-2b.x})
 * </pre>
 * 
 * @author cedric.hartland
 * 
 */
public class LogisticActivationFunction implements IDifferentiableActivationFunction
{
	private double tangent;

	/**
	 * Default constructor.
	 * 
	 * @param tangent
	 *            the tangent parameter. A typical nice parameter is within [1:5]; other values works as well.
	 */
	public LogisticActivationFunction(double tangent)
	{
		this.tangent = tangent;
	}

	/**
	 * <pre>
	 * f(x) = 1 / (1 + e^{-2b.x})
	 * </pre>
	 */
	@Override
	public double computeValue(double inputValue)
	{
		return 1 / (1 + Math.exp(-2 * tangent * inputValue));
	}

	/**
	 * <pre>
	 * f'(x) = f(x)*(1-f(x))
	 * </pre>
	 */
	@Override
	public double computeDerivedValue(double outputValue)
	{
		double fx = computeValue(outputValue);
		return fx * (1 - fx);
	}

}
