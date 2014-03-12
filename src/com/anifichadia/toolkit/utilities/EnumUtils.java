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
 * A collection of various enum utility methods
 * 
 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
 *         (http://github.com/AniFichadia)
 */
public class EnumUtils
{
	// =============================== Methods ===============================
	/**
	 * Converts an Enum's name value into sentence like strings. <br />
	 * <br />
	 * Note: this only applies to enums that follow constant case (this is not checked) (eg.
	 * CONSTANT_CASE)<br />
	 * <br />
	 * Example: <br />
	 * <ul>
	 * <li>CONSTANT_CASE -> Constant Case</li>
	 * <li>CONSTANT_CASE -> Constant case</li>
	 * </ul>
	 * 
	 * @param e Enum to stringify
	 * @param capitalizeFirstOnly Boolean representing if only the first word should be capitalized
	 * 
	 * @return The provided Enum's name value as a sentence like strings.
	 */
	public static String stringifyEnumName(Enum<?> e, boolean capitalizeFirstOnly)
	{
		// Split enum name based on the "_" character
		String[] origNameSplit = e.name ().split ("_");
		int len = origNameSplit.length;
		
		StringBuilder sb = new StringBuilder ();
		// Construct string from name value
		for (int i = 0; i < len; i++) {
			String s = origNameSplit[i];
			
			if (capitalizeFirstOnly && i == 0 || !capitalizeFirstOnly) {
				// Handle capitalization of name components
				
				// Capitalize first character
				sb.append (s.charAt (0));
				
				if (s.length () > 1) {// Convert remaining characters to lower case
					sb.append (s.substring (1).toLowerCase ());
				}
			} else {// lower case all characters
				sb.append (s.toLowerCase ());
			}
			
			// Append space if necessary
			if (i < len - 1) {
				sb.append (" ");
			}
		}
		
		return sb.toString ();
	}
	
	
	/**
	 * Refer to {@link #stringifyEnumName(Enum, boolean)}. Supplies the boolean parameter as false.
	 */
	public static String stringifyEnumName(Enum<?> e)
	{
		return stringifyEnumName (e, false);
	}
}