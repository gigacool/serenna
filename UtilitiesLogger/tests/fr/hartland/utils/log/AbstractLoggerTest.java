package fr.hartland.utils.log;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

/**
 * Provides minimal tests for the abstract logger implementation.
 */
public class AbstractLoggerTest {

	/**
	 * testLoggerOff make sure no data is outputed to print stream if logger
	 * parameter is set to off.
	 */
	@Test
	public void testLoggerOff() {
		// Setup
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		AbstractLogger logger = new AbstractLogger(ILogger.OFF,
				new PrintStream(out)) {
			// NOP
		};
		// Test
		logger.fatal("message1");
		logger.error("message2");
		logger.warning("message3");
		logger.info("message4");
		logger.debug("message5");
		// Assertions
		String result = new String(out.toByteArray());
		Assert.assertFalse(result.contains("message1"));
		Assert.assertFalse(result.contains("message2"));
		Assert.assertFalse(result.contains("message3"));
		Assert.assertFalse(result.contains("message4"));
		Assert.assertFalse(result.contains("message5"));
	}

	/**
	 * testLoggerFatal make sure no data is printed with lower level than fatal
	 * error.
	 */
	@Test
	public void testLoggerFatal() {
		// Setup
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		AbstractLogger logger = new AbstractLogger(ILogger.FATAL,
				new PrintStream(out)) {
			// NOP
		};
		// Test
		logger.fatal("message1");
		logger.error("message2");
		logger.warning("message3");
		logger.info("message4");
		logger.debug("message5");
		// Assertions
		String result = new String(out.toByteArray());
		Assert.assertTrue(result.contains("message1"));
		Assert.assertFalse(result.contains("message2"));
		Assert.assertFalse(result.contains("message3"));
		Assert.assertFalse(result.contains("message4"));
		Assert.assertFalse(result.contains("message5"));
	}

	/**
	 * testLoggerError make sure no data is printed with lower level than error.
	 */
	@Test
	public void testLoggerError() {
		// Setup
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		AbstractLogger logger = new AbstractLogger(ILogger.ERROR,
				new PrintStream(out)) {
			// NOP
		};
		// Test
		logger.fatal("message1");
		logger.error("message2");
		logger.warning("message3");
		logger.info("message4");
		logger.debug("message5");
		// Assertions
		String result = new String(out.toByteArray());
		Assert.assertTrue(result.contains("message1"));
		Assert.assertTrue(result.contains("message2"));
		Assert.assertFalse(result.contains("message3"));
		Assert.assertFalse(result.contains("message4"));
		Assert.assertFalse(result.contains("message5"));
	}

	/**
	 * testLoggerWarning make sure no data is printed with lower level than
	 * warning.
	 */
	@Test
	public void testLoggerWarning() {
		// Setup
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		AbstractLogger logger = new AbstractLogger(ILogger.WARNING,
				new PrintStream(out)) {
			// NOP
		};
		// Test
		logger.fatal("message1");
		logger.error("message2");
		logger.warning("message3");
		logger.info("message4");
		logger.debug("message5");
		// Assertions
		String result = new String(out.toByteArray());
		Assert.assertTrue(result.contains("message1"));
		Assert.assertTrue(result.contains("message2"));
		Assert.assertTrue(result.contains("message3"));
		Assert.assertFalse(result.contains("message4"));
		Assert.assertFalse(result.contains("message5"));
	}

	/**
	 * testLoggerInfo make sure no data is printed with lower level than info.
	 */
	@Test
	public void testLoggerInfo() {
		// Setup
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		AbstractLogger logger = new AbstractLogger(ILogger.INFO,
				new PrintStream(out)) {
			// NOP
		};
		// Test
		logger.fatal("message1");
		logger.error("message2");
		logger.warning("message3");
		logger.info("message4");
		logger.debug("message5");
		// Assertions
		String result = new String(out.toByteArray());
		Assert.assertTrue(result.contains("message1"));
		Assert.assertTrue(result.contains("message2"));
		Assert.assertTrue(result.contains("message3"));
		Assert.assertTrue(result.contains("message4"));
		Assert.assertFalse(result.contains("message5"));
	}

	/**
	 * testLoggerDebug make sure every data is printed whatever their level.
	 */
	@Test
	public void testLoggerDebug() {
		// Setup
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		AbstractLogger logger = new AbstractLogger(ILogger.DEBUG,
				new PrintStream(out)) {
			// NOP
		};
		// Test
		logger.fatal("message1");
		logger.error("message2");
		logger.warning("message3");
		logger.info("message4");
		logger.debug("message5");
		// Assertions
		String result = new String(out.toByteArray());
		Assert.assertTrue(result.contains("message1"));
		Assert.assertTrue(result.contains("message2"));
		Assert.assertTrue(result.contains("message3"));
		Assert.assertTrue(result.contains("message4"));
		Assert.assertTrue(result.contains("message5"));
	}

}
