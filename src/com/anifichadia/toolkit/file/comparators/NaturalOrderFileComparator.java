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

import com.eekboom.utils.Strings;

/**
 * Comparator for case insensitive natural order file sorting for files in the same directory. Files
 * can be either sorted with Directories (default) or Files listed first (this does not change the
 * order within directories or files though). <br />
 * <br />
 * Note: Natural order comparison requires more processing than String comparison, so performance is
 * not guaranteed. <br />
 * <br />
 * Credit goes to Stephen Kelvin Friedrich for the natural order sorting algorithm provided in the
 * {@link Strings} class. (<a
 * href="http://www.eekboom.com/java/compareNatural/src/com/eekboom/utils/Strings.java"
 * target="_blank">http://www.eekboom.com/java/compareNatural/src/com/eekboom/utils/Strings.java</a>
 * )
 * 
 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
 *         (http://github.com/AniFichadia)
 */
public class NaturalOrderFileComparator implements Comparator<File>
{
	// ============================= Attributes ==============================
	/** Order to list Directories and Files in */
	protected final FileFolderOrder	order;
	
	
	// ============================ Constructors =============================
	public NaturalOrderFileComparator ()
	{
		this (FileFolderOrder.DIRECTORY_FIRST);
	}
	
	
	public NaturalOrderFileComparator (FileFolderOrder order)
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
			
			// Use natural order comparison to determine file order
			return Strings.compareNatural (lhsName, rhsName);
		}
	}
	
	
	// =============================== Methods ===============================
	public FileFolderOrder getOrder()
	{
		return order;
	}
}