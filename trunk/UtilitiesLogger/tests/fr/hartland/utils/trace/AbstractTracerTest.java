package fr.hartland.utils.trace;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

/**
 * Provides minimal tests for the abstract tracer implementation.
 */
public class AbstractTracerTest {

	protected static String lineSeparator = System.getProperty("line.separator");

	/**
	 * testLoggerDebug make sure every data is printed whatever their level.
	 */
	@Test
	public void testLoggerDebug()
	{
		// Setup
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		AbstractTracer tracer = new AbstractTracer(new PrintStream(out)) {
			// NOP
		};
		// Test
		tracer.addDataRow("1", "2", "3");
		tracer.addDataRow("6", "5", "4");
		tracer.addDataRow("7", "8", "9");
		// Assertions
		String result = new String(out.toByteArray());
		Assert.assertTrue(result.contains("1;2;3" + lineSeparator + "6;5;4" + lineSeparator + "7;8;9"));
	}

}
