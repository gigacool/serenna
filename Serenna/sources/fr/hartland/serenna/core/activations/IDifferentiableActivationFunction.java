package fr.hartland.serenna.core.activations;

/**
 * Represents a function f(x) such that df/dx exists (i.e. the function is differentiable.
 */
public interface IDifferentiableActivationFunction extends IActivationFunction {

	/**
	 * Compute the derived value for a provided output value.
	 * 
	 * @param outputValue
	 *            the output value produced by the activation function.
	 * @return the derived value.
	 */
	double computeDerivedValue(double outputValue);

}
