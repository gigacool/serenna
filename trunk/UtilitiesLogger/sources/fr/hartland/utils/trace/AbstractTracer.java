package fr.hartland.utils.trace;

import java.io.PrintStream;

abstract class AbstractTracer implements ITracer {

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
	public void addDataRow(double... values)
	{
		clearRow();
		boolean first = false;
		for (double value : values)
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

	@Override
	public void addDataRow(float... values)
	{
		clearRow();
		boolean first = false;
		for (float value : values)
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

	@Override
	public void addDataRow(int... values)
	{
		clearRow();
		boolean first = false;
		for (int value : values)
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

	@Override
	public void addDataRow(short... values)
	{
		clearRow();
		boolean first = false;
		for (short value : values)
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

	@Override
	public void addDataRow(Object... values)
	{
		clearRow();
		boolean first = false;
		for (Object value : values)
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
