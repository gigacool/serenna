package fr.hartland.serenna.core;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Exposes {@link Unit} functionalities.
 * 
 */
public class UnitTest
{
	// TODO shall we kill Unit class as it became almost empty ?

	/** Validates the toString rendering to the unit name. */
	@Test
	public void testToStringRendering()
	{
		Unit unit = new Unit("testName") {

			@Override
			protected void compute()
			{
				// NOP
			}
		};
		Assert.assertEquals("(testName)", unit.toString());
	}

}
