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
public class ConnectionTest
{
	private static void assertHasSingleItem(Iterable<Connection> inputConnections, Connection connection)
	{
		Iterator<Connection> inputConnectionsIter = inputConnections.iterator();
		Assert.assertTrue(inputConnectionsIter.hasNext());
		Assert.assertEquals(connection, inputConnectionsIter.next());
		Assert.assertFalse(inputConnectionsIter.hasNext());
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
	 * Test Connection creation
	 */
	@Test
	public void connectionCreation()
	{
		// Setup
		MockNode node0 = new MockNode("input");
		MockNode node1 = new MockNode("output");
		// Test
		Connection connection = new Connection(node0, node1);
		// Assertions
		Assert.assertEquals(node0, connection.getInputNode());
		Assert.assertEquals(node1, connection.getOutputNode());
		assertHasSingleItem(node1.getInputConnections(), connection);
		assertHasSingleItem(node0.getOutputConnections(), connection);
		Assert.assertFalse(node1.getOutputConnections().iterator().hasNext());
		Assert.assertFalse(node0.getInputConnections().iterator().hasNext());
	}
}
