package fr.hartland.serenna.core.activations;

/**
 * Defines generalized heaviside step function:
 * 
 * <pre>
 * f(x) = { a, x < threshold;
 *        { b, x >= threshold;
 * </pre>
 * 
 * @author cedric.hartland
 */
public class HeavisideActivationFunction implements IActivationFunction
{

	private double threshold;

	private double below;
	private double above;

	/**
	 * Default constructor. Provides f(x) = x;
	 */
	public HeavisideActivationFunction()
	{
		this(0, 1, 0);
	}

	/**
	 * Default constructor. Provides
	 * 
	 * <pre>
	 * f(x) = { above, x < threshold; 
	 *        { over, x >= threshold;
	 * </pre>
	 * 
	 * @param threshold
	 *            threshold
	 * @param below
	 *            value returned when x >= threshold.
	 * @param above
	 *            value returned when x < threshold.
	 */
	public HeavisideActivationFunction(double threshold, double below, double above)
	{
		this.threshold = threshold;
		this.below = below;
		this.above = above;
	}

	@Override
	public double computeValue(double inputValue)
	{
		return inputValue < threshold ? above : below;
	}

}
