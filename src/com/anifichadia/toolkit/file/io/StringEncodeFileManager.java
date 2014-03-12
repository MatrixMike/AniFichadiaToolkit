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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Reads and writes Objects from raw text files. <br />
 * <br />
 * Note: Assumes {@link StandardCharsets#ISO_8859_1} as Default Encoding
 * 
 * @param <T> Type of object to read from or write to files
 * 
 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
 *         (http://github.com/AniFichadia)
 */
public abstract class StringEncodeFileManager<T> extends FileManager<T>
{
	// ============================= Attributes ==============================
	/** Encoding of file. Assumes it is {@link StandardCharsets#ISO_8859_1} by default */
	protected Charset	encoding		= StandardCharsets.ISO_8859_1;
	
	/**
	 * Manipulatable flag to skip the remaining content of a file. Gets/must be reset to false when
	 * performing an operations
	 */
	protected boolean	skipRemaining	= false;
	
	
	// ============================ Constructors =============================
	public StringEncodeFileManager ()
	{
		super ();
	}
	
	
	public StringEncodeFileManager (Charset encoding)
	{
		super ();
		this.encoding = encoding;
	}
	
	
	// ============================== Inherited ==============================
	/**
	 * Notes:
	 * - While reading, {@link #skipRemaining} can be set to true (using
	 * {@link #setSkipRemaining(boolean)} to ignore the remaining file contents
	 */
	@ Override
	public T readFromFile(final File file)
	{
		skipRemaining = false;
		
		Path filePath = Paths.get (file.getAbsolutePath ());
		T object = null;
		BufferedReader reader = null;
		
		try { // Read file using BufferedReader
			reader = Files.newBufferedReader (filePath, encoding);
			
			// Get instantiated object. Workaround to avoid issues with Java Generics
			object = instantiateObj ();
			
			int lineNum = 0;
			String line = null;
			
			// Read file, line by line
			while ((line = reader.readLine ()) != null && !skipRemaining) {
				lineNum++;
				processLine (object, line, lineNum);
			}
		} catch (IOException e) {
			e.printStackTrace ();
		} finally { // Close BufferedReader
			if (reader != null) {
				try {
					reader.close ();
				} catch (IOException e) {}
			}
		}
		
		return object;
	}
	
	
	/**
	 * Notes:
	 * - Refer to {@link #toWriteableString(Object)}, this is used to encode the object as a String
	 */
	@ Override
	public boolean writeToFile(final File file, T object, boolean replaceIfExists)
	{
		skipRemaining = false;
		
		// Delete file if it exists
		if (file.exists () && replaceIfExists) {
			file.delete ();
		} else
			return false;
		
		Path path = Paths.get (file.getAbsolutePath ());
		BufferedWriter writer = null;
		
		try { // Use BufferedWriter to write to file
			writer = Files.newBufferedWriter (path, encoding);
			
			writer.write (toWriteableString (object));
		} catch (IOException e) {
			e.printStackTrace ();
			return false;
		} finally { // Close BufferedWriter
			if (writer != null) {
				try {
					writer.close ();
				} catch (IOException e) {}
			}
		}
		
		return true;
	}
	
	
	// =============================== Methods ===============================
	/**
	 * Create a string writable version of the object. By default uses toString(), but should be
	 * overridden if needed.
	 * 
	 * @param object Object to create writable string for
	 * 
	 * @return String representing object to be written to file
	 */
	public String toWriteableString(T object)
	{
		return object.toString ();
	}
	
	
	// ============================== Abstracted =============================
	/**
	 * Create an instantiated object of generic type T. This is used as a workaround to create an
	 * object when reading files due to Java generics.
	 * 
	 * @return Instantiated object of generic type T
	 */
	public abstract T instantiateObj();
	
	
	/**
	 * Process a particular line. Abstracted since it depends on the object type, configuration,
	 * etc.
	 * 
	 * @param object Object to alter using line
	 * @param line String contents of the line
	 * @param lineNum Line Number
	 */
	public abstract void processLine(T object, String line, int lineNum);
	
	
	// ========================== Getters & Setters ==========================
	public Charset getEncoding()
	{
		return encoding;
	}
	
	
	public void setEncoding(Charset encoding)
	{
		this.encoding = encoding;
	}
	
	
	public final boolean isSkipRemaining()
	{
		return skipRemaining;
	}
	
	
	public final void setSkipRemaining(boolean skipRemaining)
	{
		this.skipRemaining = skipRemaining;
	}
}