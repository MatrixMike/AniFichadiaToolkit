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
import java.util.Comparator;

/**
 * Basic, case insensitive comparator for sorting files in the same directory. Files can be either
 * sorted with Directories (default) or Files listed first. Sorting uses file name String
 * comparison (refer to {@link String#compareTo(String)}.
 * 
 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
 *         (http://github.com/AniFichadia)
 */
public class FileComparator implements Comparator<File>
{
	// ============================= Attributes ==============================
	/** Order to list Directories and Files in */
	protected final FileFolderOrder	order;
	
	
	// ============================ Constructors =============================
	public FileComparator ()
	{
		this (FileFolderOrder.DIRECTORY_FIRST);
	}
	
	
	public FileComparator (FileFolderOrder order)
	{
		super ();
		this.order = order;
	}
	
	
	// ============================= Implemented =============================
	@ Override
	public int compare(File lhs, File rhs)
	{
		if (lhs.isDirectory () && !rhs.isDirectory ()) {
			if (order == FileFolderOrder.DIRECTORY_FIRST)
				return -1; // list directories first
			else
				return 1; // list directories last
		} else if ( !lhs.isDirectory () && rhs.isDirectory ())
			if (order == FileFolderOrder.DIRECTORY_FIRST)
				return 1; // list files first
			else
				return -1; // list files last
		else {
			String lhsName = FileComparatorUtils.getStandardizedName (lhs);
			String rhsName = FileComparatorUtils.getStandardizedName (rhs);
			
			// Use String comparison for the file names to determine their order
			return lhsName.compareTo (rhsName);
		}
	}
	
	
	// ========================== Getters & Setters ==========================
	public FileFolderOrder getOrder()
	{
		return order;
	}
}