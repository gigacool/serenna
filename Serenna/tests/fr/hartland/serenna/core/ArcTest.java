package fr.hartland.serenna.core;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import fr.hartland.serenna.core.Connection;
import fr.hartland.serenna.core.Node;

/**
 * Exposes {@link Connection} functionalities.
 * 
 */
public class ArcTest
{
	private static void assertHasSingleItem(Iterable<Connection> inputArcs, Connection arc)
	{
		Iterator<Connection> inputArcsIter = inputArcs.iterator();
		Assert.assertTrue(inputArcsIter.hasNext());
		Assert.assertEquals(arc, inputArcsIter.next());
		Assert.assertFalse(inputArcsIter.hasNext());
	}

	private static class MockNode extends Node
	{

		public MockNode(String name)
		{
			super(name);
		}

		@Override
		public void compute()
		{
			// NOP
		}
	}

	/**
	 * Test Arc creation
	 */
	@Test
	public void arcCreation()
	{
		// Setup
		MockNode node0 = new MockNode("input");
		MockNode node1 = new MockNode("output");
		// Test
		Connection arc = new Connection(node0, node1);
		// Assertions
		Assert.assertEquals(node0, arc.getInputNode());
		Assert.assertEquals(node1, arc.getOutputNode());
		assertHasSingleItem(node1.getInputArcs(), arc);
		assertHasSingleItem(node0.getOutputArcs(), arc);
		Assert.assertFalse(node1.getOutputArcs().iterator().hasNext());
		Assert.assertFalse(node0.getInputArcs().iterator().hasNext());
	}
}
