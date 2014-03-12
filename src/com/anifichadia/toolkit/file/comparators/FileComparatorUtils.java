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

package com.anifichadia.toolkit.file.comparators;

import java.io.File;

/**
 * Collection of utility methods for File Comparators within this package. Used to standardize the
 * location and implementation of methods used throughout this package.
 * 
 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
 *         (http://github.com/AniFichadia)
 */
public class FileComparatorUtils
{
	// =============================== Methods ===============================
	/**
	 * Generate a standardized name for case insensitive file comparison ({@link File#getName()} in
	 * lower case).
	 * 
	 * @param file File to generate name from
	 * 
	 * @return A standardized name for case insensitive file comparison
	 */
	public static String getStandardizedName(File file)
	{
		return file.getName ().toLowerCase ();
	}
}