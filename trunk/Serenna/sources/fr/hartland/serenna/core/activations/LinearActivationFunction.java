package fr.hartland.serenna.core.activations;

/**
 * Defines f(x) = a.x + b.
 */
public class LinearActivationFunction implements IDifferentiableActivationFunction {

	private double a;
	private double b;

	/**
	 * Default constructor. Provides f(x) = x;
	 */
	public LinearActivationFunction()
	{
		this(1, 0);
	}

	/**
	 * Default constructor. Provides f(x) = a.x + b;
	 * 
	 * @param a
	 *            term 1.
	 * @param b
	 *            term 2.
	 */
	public LinearActivationFunction(double a, double b)
	{
		this.a = a;
		this.b = b;
	}

	@Override
	public double computeValue(double inputValue)
	{
		return a * inputValue + b;
	}

	@Override
	public double computeDerivedValue(double outputValue)
	{
		return a;
	}

}
