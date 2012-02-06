package fr.hartland.serenna.utils;

import java.util.Random;

/**
 * First experiment to provide a repeatable random function for a given starting seed.
 * 
 * @author cedric.hartland
 */
public class RandomProvider
{
	private static RandomProvider provider = new RandomProvider();

	private Random random;
	private long seed;

	/**
	 * Default constructor.
	 */
	private RandomProvider()
	{
		this.seed = new Random().nextLong();
		this.random = new Random(seed);
	}

	/**
	 * Returns the random provider singleton instance.
	 * 
	 * @return the random provider instance.
	 */
	public static RandomProvider getInstance()
	{
		return provider;
	}

	/**
	 * Sets the seed of this random number generator using a single long seed. The general contract of setSeed is that
	 * it alters the state of this random number generator object so as to be in exactly the same state as if it had
	 * just been created with the argument seed as a seed.
	 * 
	 * @param seed
	 *            the seed number.
	 */
	public void setSeed(long seed)
	{
		this.seed = seed;
		random.setSeed(seed);
	}

	/**
	 * @return the random generator initial seed.
	 */
	public long getSeed()
	{
		return seed;
	}

	/**
	 * Provides the next random double value between <code>0.0</code> and <code>1.0</code>.
	 * 
	 * @return a random value in [0:1].
	 */
	public double nextDouble()
	{
		return random.nextDouble();
	}

	/**
	 * Provides the next random double value between <code>min</code> and <code>max</code>. If the lower bound were to
	 * be larger than the smaller, a runtime exception is thrown.
	 * 
	 * @param min
	 *            the lower bound.
	 * @param max
	 *            the upper bound.
	 * @return a random value in [min:max].
	 */
	public double nextDouble(double min, double max)
	{
		if (max < min)
		{
			throw new ArithmeticException("lower bound is " + min + " while upper bound is " + max
					+ ". The bounds should be switched.");
		}
		double range = max - min;
		return random.nextDouble() * range + min;
	}

}
