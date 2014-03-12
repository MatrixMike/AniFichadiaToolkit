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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Serializes an Object to and from a file using Java Serialization. <br />
 * <br />
 * Note: Reading from files is unchecked due to Java Generics
 * 
 * @param <T> Type of object to read from or write to files. Must implement the Serializable
 *            interface
 * 
 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
 *         (http://github.com/AniFichadia)
 */
public class SerializableFileManager<T extends Serializable> extends FileManager<T>
{
	// ============================== Inherited ==============================
	@ SuppressWarnings ("unchecked")
	@ Override
	public T readFromFile(final File file)
	{
		T object = null;
		
		FileInputStream fIS = null;
		ObjectInputStream oIS = null;
		
		try { // Read object from file using FileInputStream and ObjectInputStream
			fIS = new FileInputStream (file);
			oIS = new ObjectInputStream (fIS);
			
			object = (T) oIS.readObject ();
		} catch (Exception e) {
			e.printStackTrace ();
		} finally { // Close FileInputStream and ObjectInputStream
			if (fIS != null) {
				try {
					fIS.close ();
				} catch (IOException e) {}
			}
			
			if (oIS != null) {
				try {
					oIS.close ();
				} catch (IOException e) {}
			}
		}
		
		return object;
	}
	
	
	@ Override
	public boolean writeToFile(final File file, T object, boolean replaceIfExists)
	{
		// Delete file if it exists
		if (file.exists () && replaceIfExists) {
			file.delete ();
		} else
			return false;
		
		FileOutputStream fOS = null;
		ObjectOutputStream oOS = null;
		
		try { // Write object to file using FileOutputStream and ObjectOutputStream
			fOS = new FileOutputStream (file);
			oOS = new ObjectOutputStream (fOS);
			
			oOS.writeObject (object);
			oOS.close ();
		} catch (IOException e) {
			e.printStackTrace ();
			return false;
		} finally { // Close FileOutputStream and ObjectOutputStream
			if (fOS != null) {
				try {
					fOS.close ();
				} catch (IOException e) {}
			}
			
			if (oOS != null) {
				try {
					oOS.close ();
				} catch (IOException e) {}
			}
		}
		
		return true;
	}
}