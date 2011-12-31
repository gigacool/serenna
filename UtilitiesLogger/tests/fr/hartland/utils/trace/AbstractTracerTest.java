package fr.hartland.utils.trace;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

/**
 * Provides minimal tests for the abstract tracer implementation.
 */
public class AbstractTracerTest
{

	protected static String lineSeparator = System.getProperty("line.separator");

	/**
	 * testStringTrace make sure String data are correctly traced.
	 */
	@Test
	public void testStringTrace()
	{
		// Setup
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ITracer<String> tracer = new AbstractTracer<String>(new PrintStream(out)) {
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

	/**
	 * testIntegerTrace make sure integer data are correctly traced.
	 */
	@Test
	public void testIntegerTrace()
	{
		// Setup
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ITracer<Integer> tracer = new AbstractTracer<Integer>(new PrintStream(out)) {
			// NOP
		};
		// Test
		tracer.addDataRow(1, 2, 3);
		tracer.addDataRow(6, 5, 4);
		tracer.addDataRow(7, 8, 9);
		// Assertions
		String result = new String(out.toByteArray());
		Assert.assertTrue(result.contains("1;2;3" + lineSeparator + "6;5;4" + lineSeparator + "7;8;9"));
	}

	/**
	 * testDoubleTrace make sure every double data are correctly traced.
	 */
	@Test
	public void testDoubleTrace()
	{
		// Setup
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ITracer<Double> tracer = new AbstractTracer<Double>(new PrintStream(out)) {
			// NOP
		};
		// Test
		tracer.addDataRow(1., 2., 3.);
		tracer.addDataRow(6., 5., 4.);
		tracer.addDataRow(7., 8., 9.);
		// Assertions
		String result = new String(out.toByteArray());
		System.out.println(result);
		Assert.assertTrue(result.contains("1.0;2.0;3.0" + lineSeparator + "6.0;5.0;4.0" + lineSeparator + "7.0;8.0;9.0"));
	}

}
