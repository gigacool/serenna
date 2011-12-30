package fr.hartland.serenna.core.activations;

/**
 * Exposes the core functionalities for an activation function used within a neuron.
 */
public interface IActivationFunction {

	/**
	 * Compute the function f value given an input parameter.
	 * 
	 * @param inputValue
	 *            the input value.
	 * @return f(input)
	 */
	double computeValue(double inputValue);

}
