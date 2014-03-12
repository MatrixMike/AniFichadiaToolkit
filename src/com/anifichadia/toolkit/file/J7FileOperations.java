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

package com.anifichadia.toolkit.file;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * Collection of File Operations compatible with Java 7.
 * 
 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
 *         (http://github.com/AniFichadia)
 */
public class J7FileOperations extends J6FileOperations
{
	// =============================== Methods ===============================
	/**
	 * Calculates the number of lines in a file.
	 * 
	 * Note: This is limited to Long.MAX_VALUE number of lines
	 * 
	 * @param file File to calculate line count from
	 * 
	 * @return Number of lines in the file or -1 on exception
	 */
	public static int getLineCount(final File file)
	{
		FileReader fR = null;
		LineNumberReader lNR = null;
		
		try { // Use LineNumberReader to calculate line count
			fR = new FileReader (file);
			lNR = new LineNumberReader (fR);
			
			// Skip past Long.MAX_VALUE lines (if the number of lines is less, it doesn't matter)
			// and return line number.
			lNR.skip (Long.MAX_VALUE);
			
			return lNR.getLineNumber ();
		} catch (Exception e) {
			return -1;
		} finally { // Close FileReader and LineNumberReader
			if (fR != null) {
				try {
					fR.close ();
				} catch (IOException e) {}
			}
			
			if (lNR != null) {
				try {
					lNR.close ();
				} catch (IOException e) {}
			}
		}
	}
	
	
	/**
	 * Refer to {@link #getLineCount(File)}
	 * 
	 * Uses {@link #getLineCount(File)} by constructing a file from the path parameter
	 */
	public static int getLineCount(String path)
	{
		return getLineCount (new File (path));
	}
}