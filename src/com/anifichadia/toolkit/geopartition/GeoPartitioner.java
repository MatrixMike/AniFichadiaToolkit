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

package com.anifichadia.toolkit.geopartition;

import com.anifichadia.toolkit.geometry.Coordinate2D;
import com.anifichadia.toolkit.utilities.MathUtils;

/**
 * Calculates equal sized partitions for a bounding box.
 * 
 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
 *         (http://github.com/AniFichadia)
 */
public class GeoPartitioner
{
	// =============================== Methods ===============================
	/**
	 * Create equal sized partitions based on coordinates. Currently supports 1, 2, numbers in the
	 * square number sequence (eg. 1, 4, 16, 25, 36 ...) and powers of 2, as the number of
	 * partitions.
	 * 
	 * Coordinates provided must be for the opposing corners of the bounding box. This can be the
	 * South West and North East corners, or North West and South East corners of the bounding box
	 * 
	 * @param c1 First Coordinate of the original bounding box
	 * @param c2 Second Coordinate of the original bounding box
	 * @param numPartitions Total number of partitions
	 * @param partitionIndex Index of the current partition
	 * 
	 * @return 2-Coordinate value array containing the coordinates for the partitioned bounding box.
	 * 
	 * @throws Exception
	 */
	public static Coordinate2D[] partition(Coordinate2D c1, Coordinate2D c2, int numPartitions,
			int partitionIndex) throws Exception
	{
		if (numPartitions < 1)
			throw new IllegalArgumentException (
					"Number of partitions is less than 1. numPartitions: " + numPartitions);
		else if (partitionIndex < 0)
			throw new IllegalArgumentException ("Partition Index is less than 0. Partition Index: "
					+ partitionIndex);
		else if (partitionIndex >= numPartitions)
			throw new IllegalArgumentException (
					"Partition Index is more than the number of partitions");
		
		double x1 = c1.getX ();
		double y1 = c1.getY ();
		double x2 = c2.getX ();
		double y2 = c2.getY ();
		
		// Individual partition x- and y- sizes
		double partitionSizeX = 0;
		double partitionSizeY = 0;
		
		// Partition offset based on partitionIndex
		int positionX = 0;
		int positionY = 0;
		
		if (numPartitions == 1) {// Just use original bounding box
			partitionSizeX = x2 - x1;
			partitionSizeY = y2 - y1;
		} else if (numPartitions == 2) {
			// Split bounding box into two partitions. Splits so the length of the overlapping edge
			// is minimal (eg. if bounding box is rectangular)
			
			if (x2 - x1 >= y2 - y1) {
				partitionSizeX = (x2 - x1) / 2;
				partitionSizeY = (y2 - y1);
				
				positionX = partitionIndex;
			} else {
				partitionSizeX = (x2 - x1);
				partitionSizeY = (y2 - y1) / 2;
				
				positionY = partitionIndex;
			}
		} else if (MathUtils.isSquareNumber (numPartitions)) {
			// Splits bounding box equally
			
			// Calculate proportion to split partition
			int split = (int) (Math.log (numPartitions) / Math.log (2));
			
			partitionSizeX = (x2 - x1) / split;
			partitionSizeY = (y2 - y1) / split;
			
			// Calculate position based on index. Simple modulo and division maps index into a grid
			positionX = partitionIndex % split;
			positionY = partitionIndex / split;
		} else if (MathUtils.isPowOf2 (numPartitions)) {
			/*
			 * Calculates partitionSizeX and partitionSizeY using the splitX and splitY
			 * 
			 * Calculate splitX and splitY as the two "sequential" factors of numPartitions that
			 * multiply to equal numPartitions.
			 * 
			 * Uses an optimization that assumes that splitX will be the "first" power of 2
			 * greater than squareRoot(numPartitions) and splitY is the "first" power of 2 less than
			 * squareRoot(numPartitions)
			 * 
			 * Eg. For numPartitions = 128
			 * root = squareRoot(numPartitions) = squareRoot(128) = 11.31~ (approx.)
			 * Factors of 128 are: 128, 64, 32, 16, 8, 4, 2, 1.
			 * First factor above root is 16 and first factor below root is 8.
			 * So splitX is 16, and splitY is 8
			 * Also, splitX * splitY = 16 * 8 = 128 = numPartitions
			 * 
			 * IT WORKS!
			 */
			
			double root = Math.sqrt (numPartitions);
			// Calculate splitX. Uses optimization: finds the closest power of 2 greater than
			// squareRoot(numPartitions)
			int power = (int) Math.ceil (Math.log (root) / Math.log (2));
			int splitX = (int) Math.pow (2, power);
			// Calculate splitY. Assume splitY is 2^(power - 1)
			int splitY = (int) Math.pow (2, power - 1);
			
			if (splitX * splitY == numPartitions) { // Just check it is correct
				// Calculate partition sizes
				partitionSizeX = (x2 - x1) / splitX;
				partitionSizeY = (y2 - y1) / splitY;
				
				positionX = partitionIndex % splitX;
				positionY = (partitionIndex - positionX) / splitX;
			} else {
				StringBuilder sb = new StringBuilder ();
				
				sb.append ("Something went wrong during power of 2 partition calculations. Diagnostics:");
				sb.append ("\n");
				sb.append (String.format (
						"Num Partitions: %d \t partition index: %d \t C1: (%s) \t C2: (%s)",
						numPartitions, partitionIndex, c1.toString (), c2.toString ()));
				sb.append ("\n");
				sb.append (String.format ("splitX = %d = 2 ^ %d", splitX, power));
				sb.append ("\n");
				sb.append (String.format ("splitY = %d = 2 ^ %d", splitY, power - 1));
				sb.append ("\n");
				sb.append (String.format (
						"splitX * splitY = %d \t numPartitions = %d \t equal?: %b",
						splitX * splitY, numPartitions, splitX * splitY == numPartitions));
				
				throw new Exception (sb.toString ());
			}
		} else {
			StringBuilder sb = new StringBuilder ();
			sb.append ("Invalid number of partitions. Number of Partitions is currently not supported");
			sb.append ("\n");
			sb.append (String.format (
					"Num Partitions: %d \t partition index: %d \t C1: (%s) \t C2: (%s)",
					numPartitions, partitionIndex, c1.toString (), c2.toString ()));
			sb.append ("\n");
			sb.append ("Value may be above 2^31?");
			
			throw new IllegalArgumentException (sb.toString ());
		}
		
		// Calculate new bounding box coordinates for partition.
		double partitionX1 = x1 + (positionX * partitionSizeX);
		double partitionY1 = y1 + (positionY * partitionSizeY);
		double partitionX2 = x1 + ((positionX + 1) * partitionSizeX);
		double partitionY2 = y1 + ((positionY + 1) * partitionSizeY);
		
		return new Coordinate2D[] {
				new Coordinate2D (partitionX1, partitionY1),
				new Coordinate2D (partitionX2, partitionY2)};
	}
}