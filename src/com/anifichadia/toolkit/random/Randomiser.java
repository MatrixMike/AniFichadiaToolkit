/**
 * Copyright (C) 2014 Aniruddh Fichadia
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 * 
 * If you use or enhance the code, please let me know using the provided author information or via
 * email Ani.Fichadia@gmail.com.
 */

package com.anifichadia.toolkit.random;

import java.util.Random;

/**
 * Wrapper for the {@link Random} class to perform various random number generation methods. <br />
 * <br />
 * Note: May not be thread safe. Uses a single, static {@link Random} object
 * 
 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
 *         (http://github.com/AniFichadia)
 */
public class Randomiser
{
	// ============================= Attributes ==============================
	private static Random	rand	= new Random ();
	
	
	// =============================== Methods ===============================
	/**
	 * Generates a randomly generated integer
	 * 
	 * @return A pseudo randomly generated integer.
	 */
	public static int randInt()
	{
		return rand.nextInt ();
	}
	
	
	/**
	 * Generates a random integer between a requested range. Value will be in range: [start,
	 * endExclusive)
	 * 
	 * @param start Minimum number to generate
	 * @param endExclusive Maximum number to generate, excluding the value. Just add 1 to the value
	 *            to include
	 * 
	 * @return Random integer in range [start, endExclusive)
	 */
	public static int randIntBetween(int start, int endExclusive)
	{
		return rand.nextInt (endExclusive - start) + start;
	}
	
	
	/**
	 * Generates a randomly generated float
	 * 
	 * @return A pseudo randomly generated float.
	 */
	public static float randFloat()
	{
		return rand.nextFloat ();
	}
	
	
	/**
	 * Generates a random float between a requested range. Value will be in range: [start,
	 * endExclusive)
	 * 
	 * @param start Minimum number to generate
	 * @param endExclusive Maximum number to generate, excluding the value. Just add 1 to the value
	 *            to include
	 * 
	 * @return Random float in range [start, endExclusive)
	 */
	public static float randFloatBetween(float start, float endExclusive)
	{
		return rand.nextFloat () * (endExclusive - start) + start;
	}
	
	
	/**
	 * Generates a randomly generated double
	 * 
	 * @return A pseudo randomly generated double.
	 */
	public static double randDouble()
	{
		return rand.nextDouble ();
	}
	
	
	/**
	 * Generates a random double between a requested range. Value will be in range: [start,
	 * endExclusive)
	 * 
	 * @param start Minimum number to generate
	 * @param endExclusive Maximum number to generate, excluding the value. Just add 1 to the value
	 *            to include
	 * 
	 * @return Random double in range [start, endExclusive)
	 */
	public static double randDoubleBetween(double start, double endExclusive)
	{
		return rand.nextDouble () * (endExclusive - start) + start;
	}
	
	
	/**
	 * Generates a random number using a normal distribution and the provided mean and standard
	 * deviations
	 * 
	 * @param mean Mean to use for normal distribution
	 * @param stddev Standard deviation to use for normal distribution
	 * 
	 * @return random Number using a normal distribution and the provided mean and standard
	 *         deviations
	 */
	public static double randNormal(double mean, double stddev)
	{
		return (rand.nextGaussian () * stddev) + mean;
	}
	
	
	/**
	 * Refer to {@link #randNormal(double, double)}<br />
	 * <br />
	 * Uses defaults for a "standard" normal distribution (mean of 0.0, standard deviation of 1.0)
	 */
	public static double randStdNormal()
	{
		return randNormal (0, 1);
	}
	
	
	/**
	 * Generates a random boolean value
	 * 
	 * @return A random boolean value
	 */
	public static boolean randBoolean()
	{
		return rand.nextBoolean ();
	}
	
	
	/**
	 * Generates a random number between 0 and the specified maximum. This does not use the
	 * {@link Random} class, but instead uses the {@link Math#random()} method.
	 * 
	 * @param max Max number to generate, inclusive
	 * 
	 * @return number in range [0, max]
	 */
	public static int mathRandInt(int max)
	{
		return (int) (Math.random () * (max + 1));
	}
	
	
	// ========================== Delegate Methods ===========================
	/**
	 * Refer to {@link Random#setSeed(long)}
	 */
	public static void setSeed(long seed)
	{
		rand.setSeed (seed);
	}
}