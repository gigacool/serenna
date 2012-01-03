package fr.hartland.serenna.core;

/**
 * Generic class for all possible neural network nodes.
 */
public abstract class Node implements INode
{

	private final String name;

	/**
	 * Default constructor. Each node can have an identifier (non necessarily unique).
	 * 
	 * @param name
	 *            the node name.
	 */
	protected Node(String name)
	{
		this.name = name;
	}

	/**
	 * Returns the node name.
	 * 
	 * @return the node name.
	 */
	public final String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return '(' + name + ')';
	}


}
