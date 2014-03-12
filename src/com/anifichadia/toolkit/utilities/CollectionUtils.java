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

import java.util.ArrayList;
import java.util.Collection;

import com.anifichadia.toolkit.random.Randomiser;

/**
 * A collection of various Java Collection class utility functions
 * 
 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
 *         (http://github.com/AniFichadia)
 */
public class CollectionUtils
{
	/**
	 * Generates and returns a shuffled version of a collection without disturbing its order.
	 * 
	 * @param original Collection to shuffle
	 * 
	 * @return A shuffled copy of the original collection
	 */
	public static <T> Collection<T> shuffleCopyOfCollection(Collection<T> original)
	{
		ArrayList<T> shuffled = new ArrayList<> ();
		
		ArrayList<T> originalCopy = new ArrayList<> (original);
		while ( !originalCopy.isEmpty ()) {
			int next = Randomiser.randIntBetween (0, originalCopy.size ());
			shuffled.add (originalCopy.remove (next));
		}
		
		return shuffled;
	}
	
	
	/**
	 * Generates a shuffled version of a collection. Note: order of the original collection is
	 * disturbed.
	 * 
	 * @param original Collection to shuffle
	 */
	public static <T> void shuffleCollection(Collection<T> original)
	{
		Collection<T> shuffled = CollectionUtils.shuffleCopyOfCollection (original);
		original.clear ();
		original.addAll (shuffled);
	}
}