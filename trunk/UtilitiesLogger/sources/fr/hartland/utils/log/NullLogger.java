package fr.hartland.utils.log;

/**
 * No logging is performed.
 */
public class NullLogger implements ILogger
{
	@Override
	public void fatal(String message)
	{
		// NOP
	}

	@Override
	public void error(String message)
	{
		// NOP
	}

	@Override
	public void warning(String message)
	{
		// NOP
	}

	@Override
	public void info(String message)
	{
		// NOP
	}

	@Override
	public void debug(String message)
	{
		// NOP
	}
}
