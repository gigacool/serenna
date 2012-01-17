package fr.hartland.serenna.core;

/**
 * Generic class for all possible neural network nodes.
 */
public abstract class Unit
{
	private final String name;

	/**
	 * Default constructor. Each node can have an identifier (non necessarily unique).
	 * 
	 * @param name
	 *            the node name.
	 */
	protected Unit(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return '(' + name + ')';
	}

	/**
	 * Given weighted input value(s) provided to the node, compute the output value.
	 */
	protected abstract void compute();


}
