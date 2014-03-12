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

package com.anifichadia.toolkit.file.io;

import java.io.File;

/**
 * Abstract class for file I/O operations.
 * 
 * @param <T> Type of object to read from or write to files
 * 
 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
 *         (http://github.com/AniFichadia)
 */
public abstract class FileManager<T>
{
	// =============================== Methods ===============================
	/**
	 * Read object from a file using the provided File object.
	 * 
	 * @param file File to read object from
	 * 
	 * @return Object from file, or null on exception
	 */
	public abstract T readFromFile(final File file);
	
	
	/**
	 * Refer to {@link #readFromFile(File)}
	 * 
	 * Uses {@link #readFromFile(File)} by constructing a file from the path parameter
	 */
	public T readFromFile(final String path)
	{
		return readFromFile (new File (path));
	}
	
	
	/**
	 * Write specified object to file.
	 * 
	 * @param file File to write to
	 * @param object Object to write to the file
	 * @param replaceIfExists Boolean to replace the file if it already exists
	 * 
	 * @return Boolean representing write success or failure
	 */
	public abstract boolean writeToFile(final File file, T object, boolean replaceIfExists);
	
	
	/**
	 * Refer to {@link #writeToFile(File, Object, boolean)}
	 * 
	 * Uses {@link #writeToFile(File, Object, boolean)} by constructing a file from the path
	 * parameter
	 */
	public boolean writeToFile(final String path, T object, boolean replaceIfExists)
	{
		return writeToFile (new File (path), object, replaceIfExists);
	}
}