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

package com.anifichadia.toolkit.utilities;

/**
 * A collection of various math utility methods
 * 
 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
 *         (http://github.com/AniFichadia)
 */
public class MathUtils
{
	/**
	 * Calculates the min and max values of an array
	 * 
	 * @param values Array to find the min and max from
	 * 
	 * @return Size 2 array in format [min, max]
	 */
	public static int[] minMax(int[] values)
	{
		int min = values[0];
		int max = values[0];
		
		int len = values.length;
		
		for (int i = 1; i < len; i++) {
			int val = values[i];
			
			if (val > max) {
				max = val;
			}
			
			if (val < min) {
				min = val;
			}
		}
		
		return new int[] {min, max};
	}
	
	
	/**
	 * Calculates the min and max values of an array
	 * 
	 * @param values Array to find the min and max from
	 * 
	 * @return Size 2 array in format [min, max]
	 */
	public static double[] minMax(double[] values)
	{
		double min = values[0];
		double max = values[0];
		
		int len = values.length;
		
		for (int i = 1; i < len; i++) {
			double val = values[i];
			
			if (val > max) {
				max = val;
			}
			
			if (val < min) {
				min = val;
			}
		}
		
		return new double[] {min, max};
	}
	
	
	/**
	 * Round double to 2- significant places
	 * 
	 * @param value Double to round
	 * 
	 * @return A double rounded to 2- significant places
	 */
	public static double roundTo2SigPlaces(double value)
	{
		return Math.round (value * 100.0) / 100.0f;
	}
	
	
	/**
	 * Calculate the multiplicative mean of a set of values
	 * 
	 * @param values Array to find the multiplicative mean from
	 * 
	 * @return Multiplicative mean of values
	 */
	public static double multiplicativeMean(double[] values)
	{
		int size = values.length;
		double product = values[0];
		
		for (int i = 1; i < size; i++) {
			product *= values[i];
		}
		
		return Math.pow (product, (1.0f / size));
	}
	
	
	/**
	 * Checks if the specified number is a power of 2
	 * 
	 * @param number Number to check
	 * 
	 * @return Boolean representing if the number is a power of 2
	 */
	public static boolean isPowOf2(int number)
	{
		return ((number & -number) == number);
	}
	
	
	/**
	 * Checks if number is in the square numbers sequence
	 * 
	 * @param number Number to check
	 * 
	 * @return Boolean representing if the number is in the square numbers sequence
	 */
	public static boolean isSquareNumber(int number)
	{
		return ((int) Math.sqrt (number)) == Math.sqrt (number);
	}
	
	
	/**
	 * Calculates the Euclidean distance between two coordinates.
	 * 
	 * Coordinates are represented as an array where the value for each dimension is in each index
	 * 
	 * @param coord1 First Coordinate
	 * @param coord2 Second Coordinate
	 * 
	 * @return Euclidean Distance between coord1 and coord2
	 */
	public static double euclideanDistance(double[] coord1, double[] coord2)
	{
		return distanceBase (coord1, coord2, 2);
	}
	
	
	/**
	 * Calculates the Minkowski distance between two coordinates.
	 * 
	 * Coordinates are represented as an array where the value for each dimension is in each index
	 * 
	 * @param coord1 First Coordinate
	 * @param coord2 Second Coordinate
	 * 
	 * @return Minkowski Distance between coord1 and coord2
	 */
	public static double minkowskiDistance(double[] coord1, double[] coord2)
	{
		return distanceBase (coord1, coord2, coord1.length);
	}
	
	
	/**
	 * Calculates the distance between two coordinates. Is used as a base function for
	 * euclideanDistance(double[], double[]) and minkowskiDistance(double[], double[])
	 * 
	 * Coordinates are represented as an array where the value for each dimension is in each index
	 * 
	 * @param coord1 First Coordinate
	 * @param coord2 Second Coordinate
	 * @param order Order for the distance calculation. Used to calculate the power of each
	 *            dimension and the n-th root for the final distance calculation
	 * 
	 * @return Distance between coord1 and coord2
	 */
	private static final double distanceBase(double[] coord1, double[] coord2, int order)
	{
		if (coord1.length != coord2.length)
			throw new IllegalArgumentException ("Number of dimensions is not equal");
		
		final int NUM_DIMENSIONS = coord1.length;
		double distance = 0;
		
		for (int d = 0; d < NUM_DIMENSIONS; d++) {
			double absDiff = Math.abs (coord2[d] - coord1[d]);
			distance += Math.pow (absDiff, order);
		}
		
		distance = Math.pow (distance, (1.0 / order));;
		
		return distance;
	}
	
	
	/**
	 * Calculates the angle between two coordinates in degrees.
	 * 
	 * Coordinates are represented as an array where the value for each dimension is in each index
	 * 
	 * @param coord1 First Coordinate
	 * @param coord2 Second Coordinate
	 * 
	 * @return Angle between coord1 and coord2
	 */
	public static double angle2DBetween(double[] coord1, double[] coord2)
	{
		if (coord1.length != coord2.length && coord1.length != 2)
			throw new IllegalArgumentException (
					"Number of dimensions is not valid for the provided coordinates");
		
		double xDiff = coord2[0] - coord1[0];
		double yDiff = coord2[1] - coord1[1];
		
		double angle = Math.toDegrees (Math.atan2 (yDiff, xDiff)) - 180;
		
		return angle;
	}
	
	
	/**
	 * Converts an angle from radians to degrees
	 * 
	 * @param angle Angle to convert
	 * 
	 * @return Angle as degrees
	 */
	public static float radianToDegrees(float angle)
	{
		return (float) (angle * 180.0f / Math.PI);
	}
	
	
	/**
	 * Converts an angle from degrees to radians
	 * 
	 * @param angle Angle to convert
	 * 
	 * @return Angle as radians
	 */
	public static float degreesToRadians(float angle)
	{
		return (float) (angle * Math.PI / 180.0f);
	}
}