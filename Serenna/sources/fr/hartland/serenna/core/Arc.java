package fr.hartland.serenna.core;


/**
 * An arc provide a link from one node to another. The arc role is to convey values changed or unchanged (e.g. a weighted arc).
 * 
 */
public class Arc {

	private Node inputNode;
	private Node outputNode;

	/**
	 * Default constructor. An arc goes from one node to another.
	 * 
	 * @param inputNode
	 *            the node providing value to the arc.
	 * @param outputNode
	 *            the destination node to get the value conducted by the arc.
	 */
	public Arc(Node inputNode, Node outputNode)
	{
		this.inputNode = inputNode;
		this.outputNode = outputNode;

		this.inputNode.addOutput(this);
		this.outputNode.addInput(this);
	}

	/**
	 * Returns the arc input node.
	 * 
	 * @return the input node.
	 */
	public Node getInputNode()
	{
		return inputNode;
	}

	/**
	 * Returns the arc output node.
	 * 
	 * @return the output node.
	 */
	public Node getOutputNode()
	{
		return outputNode;
	}

	/**
	 * Returns the value, changed or not, from the input neuron.
	 * 
	 * @return the value from the input neuron via the arc.
	 */
	public double getValue()
	{
		return inputNode.getValue();
	}

}
