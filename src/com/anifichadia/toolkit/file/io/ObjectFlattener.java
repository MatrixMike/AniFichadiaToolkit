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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Flattens Serializable objects to and from byte arrays.
 * 
 * @author Aniruddh Fichadia (Ani.Fichadia@gmail.com)
 */
public class ObjectFlattener
{
	// ============================= Attributes ==============================
	public static final String	TAG	= ObjectFlattener.class.getSimpleName ();
	
	
	// =============================== Methods ===============================
	/**
	 * Un-flatten object from a byte[]
	 * 
	 * @param bytes byte[] to un-flatten object from
	 * 
	 * @return Object un-flattened from the provided byte[]
	 */
	@ SuppressWarnings ("unchecked")
	public static <T extends Serializable> T fromByteArray(byte[] bytes)
	{
		ByteArrayInputStream bis = null;
		ObjectInput in = null;
		T object = null;
		
		try { // Read object from a byte[] using ByteArrayInputStream and ObjectInputStream
			bis = new ByteArrayInputStream (bytes);
			in = new ObjectInputStream (bis);
			
			object = (T) in.readObject ();
		} catch (IOException e) {
			e.printStackTrace ();
		} catch (ClassNotFoundException e) {
			e.printStackTrace ();
		} finally { // Close ByteArrayInputStream and ObjectInputStream
			if (bis != null) {
				try {
					bis.close ();
				} catch (IOException ex) {}
			}
			
			if (in != null) {
				try {
					in.close ();
				} catch (IOException ex) {}
			}
		}
		
		return object;
	}
	
	
	/**
	 * Flatten object into a byte[]
	 * 
	 * @param object Object to flatten
	 * 
	 * @return byte[] representation of the provided Object
	 */
	public static byte[] toByteArray(Serializable object)
	{
		ByteArrayOutputStream bOS = null;
		ObjectOutput oOS = null;
		
		byte[] bytes = null;
		
		try { // Write object to a byte[] using ByteArrayOutputStream and ObjectOutputStream
			bOS = new ByteArrayOutputStream ();
			oOS = new ObjectOutputStream (bOS);
			oOS.writeObject (object);
			
			bytes = bOS.toByteArray ();
		} catch (IOException e) {
			e.printStackTrace ();
		} finally { // Close ByteArrayOutputStream and ObjectOutputStream
			if (bOS != null) {
				try {
					bOS.close ();
				} catch (IOException ex) {}
			}
			
			if (oOS != null) {
				try {
					oOS.close ();
				} catch (IOException ex) {}
			}
		}
		
		return bytes;
	}
}