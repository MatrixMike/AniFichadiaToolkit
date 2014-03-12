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

package com.anifichadia.toolkit.math.statistics;

import java.util.Arrays;

/**
 * Calculates and stores basic statistical values (mean, variance, standard deviation and median)
 * for a data set. <br />
 * <br />
 * Note: Calculations are for the population, not a sample.
 * 
 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
 *         (http://github.com/AniFichadia)
 */
public class Statistics
{
	// ============================= Attributes ==============================
	/** Data to calculate statistical values from */
	protected final double[]	data;
	/** Size of data */
	protected final int			size;
	
	/** Mean */
	protected double			mean;
	/** Variance */
	protected double			variance;
	/** Standard Deviation */
	protected double			stdDev;
	/** Median */
	protected double			median;
	
	
	// ============================ Constructors =============================
	public Statistics (double[] data)
	{
		this.data = data;
		size = data.length;
		
		calculateStatistics ();
	}
	
	
	// =============================== Methods ===============================
	/** Calculates all statistics */
	protected void calculateStatistics()
	{
		calculateMean ();
		calculateVariance ();
		calculateStdDev ();
		calculateMedian ();
	}
	
	
	/** Calculates the mean of the data */
	protected void calculateMean()
	{
		double sum = 0.0;
		for (double a : data) {
			sum += a;
		}
		
		mean = sum / size;
	}
	
	
	/** Calculates the variance of the data */
	protected void calculateVariance()
	{
		double mean = getMean ();
		double temp = 0;
		for (double a : data) {
			temp += Math.pow ((mean - a), 2);// * (mean - a);
		}
		
		variance = temp / size;
	}
	
	
	/** Calculates the standard deviation of the data */
	protected void calculateStdDev()
	{
		stdDev = Math.sqrt (getVariance ());
	}
	
	
	/** Calculates the median of the data */
	protected void calculateMedian()
	{
		double[] b = new double[data.length];
		System.arraycopy (data, 0, b, 0, b.length);
		Arrays.sort (b);
		
		if (data.length % 2 == 0) {
			median = (b[(b.length / 2) - 1] + b[b.length / 2]) / 2.0;
		} else {
			median = b[b.length / 2];
		}
	}
	
	
	/**
	 * Calculates the number of standard deviations a value lies from the mean
	 * 
	 * @param value Value to test
	 * 
	 * @return Number of standard deviations the value is from the mean
	 */
	public double getNumStdDevsFromMean(double value)
	{
		return (getMean () - value) / getStdDev ();
	}
	
	
	// ========================== Getters & Setters ==========================
	public double[] getData()
	{
		return data;
	}
	
	
	public int getSize()
	{
		return size;
	}
	
	
	public double getMean()
	{
		return mean;
	}
	
	
	public double getVariance()
	{
		return variance;
	}
	
	
	public double getStdDev()
	{
		return stdDev;
	}
	
	
	public double getMedian()
	{
		return median;
	}
}