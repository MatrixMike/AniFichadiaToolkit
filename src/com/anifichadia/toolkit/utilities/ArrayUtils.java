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
 * A collection of various array utility methods
 * 
 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
 *         (http://github.com/AniFichadia)
 */
public class ArrayUtils
{
	/**
	 * Excludes an element from a provided array and returns a copy without the element.
	 * 
	 * @param src Source array to exclude from
	 * @param excludeIndex index to exclude from src
	 * 
	 * @return (New) array without the element at index 'excludeIndex'
	 */
	public static double[] arrayExclude(double[] src, int excludeIndex)
	{
		double[] temp = new double[src.length - 1];
		
		// Copy upto the element
		System.arraycopy (src, 0, temp, 0, excludeIndex);
		// Copy after the element
		System.arraycopy (src, excludeIndex + 1, temp, excludeIndex, src.length - excludeIndex - 1);
		
		return temp;
	}
}