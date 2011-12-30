package fr.hartland.serenna.core.arcs;

import fr.hartland.serenna.core.Arc;
import fr.hartland.serenna.core.Node;

/**
 * A weighted arc is an arc altering the value conducted from input neuron to output neuron via an multiplicative weight. A weight
 * of 1 does not alter the value.
 */
public class WeightedArc extends Arc
{
	private double weight;

	/**
	 * Default constructor. Builds a weighted arc.
	 * 
	 * @param inputNode
	 *            the node providing value to the arc.
	 * @param outputNode
	 *            the destination node to get the value conducted by the arc.
	 * @param weight
	 *            the arc weight to apply to value incoming from input node.
	 */
	public WeightedArc(Node inputNode, Node outputNode, double weight)
	{
		super(inputNode, outputNode);
		this.weight = weight;
	}

	/**
	 * (Re)Defines the arc weight.
	 * 
	 * @param weight
	 *            the new arc weight.
	 */
	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	@Override
	public double getValue()
	{
		return weight * super.getValue();
	}
}
