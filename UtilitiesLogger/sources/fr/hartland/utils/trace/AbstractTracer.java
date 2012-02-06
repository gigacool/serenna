package fr.hartland.utils.trace;

import java.io.PrintStream;

abstract class AbstractTracer<T> implements ITracer<T>
{

	private final PrintStream out;
	private String separator;
	private final StringBuilder builder;

	protected AbstractTracer(PrintStream out)
	{
		this.out = out;
		this.separator = ";";
		this.builder = new StringBuilder();

	}

	protected PrintStream getOut()
	{
		return out;
	}

	private void clearRow()
	{
		builder.setLength(0);
	}

	@Override
	public void setSeparator(String separator)
	{
		this.separator = separator;
	}

	@Override
	public void addDataRow(T... values)
	{
		clearRow();
		boolean first = false;
		for (T value : values)
		{
			if (!first)
			{
				first = true;
			} else
			{
				builder.append(separator);
			}
			builder.append(value);
		}
		out.println(builder.toString());
	}

}
