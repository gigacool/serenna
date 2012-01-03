package fr.hartland.serenna.core;

/**
 * Generic root interface for all possible neural network nodes.
 */
public interface INode
{
	/**
	 * Given weighted input value(s) provided to the node, compute the output value.
	 */
	abstract void compute();

	/**
	 * Returns the value contained within the node if any; else return default value.
	 * 
	 * @return value computed during computation.
	 */
	double getValue();
	
}