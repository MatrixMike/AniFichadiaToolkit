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

package com.anifichadia.toolkit.program;

import java.util.Hashtable;

import com.anifichadia.toolkit.utilities.EnumUtils;

/**
 * Basic command line argument parser.
 * 
 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
 *         (http://github.com/AniFichadia)
 */
public class ArgumentParser
{
	/**
	 * Parses command line arguments
	 * 
	 * @param arguments Array of program arguments
	 * @param args Command line arguments
	 * 
	 * @return Hashtable containing values for arguments. Key is an arguments marker, value is the
	 *         parsed values
	 * 
	 * @throws Exception Exception if multiple arguments use the same marker, or if mandatory
	 *             arguments are missing
	 */
	public static Hashtable<Argument, String> parseArguments(Argument[] arguments, String[] args)
			throws Exception
	{
		// Load argument markers into Hashtable for quicker reference later
		Hashtable<String, Argument> argsTable = new Hashtable<> ();
		for (Argument a : arguments) {
			if (argsTable.containsKey (a.getMarker ()))
				throw new Exception (
						"Multiple arguments with the same marker have been used for marker: "
								+ a.getMarker ());
			
			argsTable.put (a.getMarker (), a);
		}
		
		// Hashtable of values
		Hashtable<Argument, String> values = new Hashtable<> ();
		
		int argsLen = args.length;
		// Iterate through all arguments
		for (int a = 0; a < argsLen; a++) {
			String arg = args[a];
			// Get argument for marker
			Argument argument = argsTable.get (arg);
			
			if (argument == null) {
				System.out.println (String.format ("Argument: \"%s\" is invalid", arg));
			} else {
				// Parse based on argument type
				switch (argument.getArgType ()) {
					case FLAG :
						values.put (argument, "true");
						break;
					case SINGLE_VALUE :
						values.put (argument, args[++a]);
						break;
					case MULTI_VALUE :
						if (values.containsKey (argument)) {
							values.put (argument, values.get (argument) + "," + args[++a]);
						} else {
							values.put (argument, args[++a]);
						}
						break;
				}
				
				System.out.println (String.format ("%-32s:\t %s", argument.getName (),
						values.get (argument)));
			}
		}
		
		boolean missingMandatory = false;
		// Check for missing mandatory arguments and defaults
		for (Argument argument : arguments) {
			if ( !values.containsKey (argument)) {
				if ((argument.isMandatory () && argument.isUseDefaultOnUnset () && argument
						.getArgType () != Argument.ArgType.FLAG) || (argument.isMandatory ())) {
					missingMandatory = true;
					System.out.println (String.format ("Argument \"%s\" is mandatory and missing",
							argument.getName ()));
				}
				
				if (argument.getArgType () == Argument.ArgType.FLAG) {
					values.put (argument, "false");
				}
				
				if (argument.isUseDefaultOnUnset ()) {
					values.put (argument, argument.getDefaultValue ());
					System.out.println (String.format ("Using default \"%s\" for Argument \"%s\"",
							argument.getDefaultValue (), argument.getName ()));
				}
			}
		}
		
		if (missingMandatory)
			throw new Exception ("Mandatory arguments are missing");
		
		return values;
	}
	
	
	/**
	 * Generate a help message for the provided arguments
	 * 
	 * @param pre String containing text to appear before the help message
	 * @param arguments Array of program arguments
	 * @param post String containing text to appear after the help message
	 * 
	 * @return A help message for the provided arguments
	 */
	public static String generateHelp(String pre, Argument[] arguments, String post)
	{
		StringBuilder sb = new StringBuilder ();
		
		// Append pre if necessary
		if (pre != null && pre.trim ().length () > 0) {
			sb.append (pre);
			sb.append ("\n");
		}
		
		sb.append ("Arguments:");
		sb.append ("\n");
		
		int argsLen = arguments.length;
		// Generate help message for each argument
		for (int a = 0; a < argsLen; a++) {
			Argument arg = arguments[a];
			
			sb.append (String.format ("\t%s - %s (Type: %s):", arg.getMarker (), arg.getName (),
					EnumUtils.stringifyEnumName (arg.getArgType (), true)));
			
			sb.append ("\n");
			sb.append (String.format ("\t\t\t%s", arg.getDescription ()));
			
			if (arg.getDefaultValue () != null) {
				sb.append ("\n");
				sb.append (String.format ("\t\tDefault: %s", arg.getDefaultValue ()));
			}
			
			if (a < (argsLen - 1)) {
				sb.append ("\n");
			}
		}
		
		// Append post if necessary
		if (post != null && post.trim ().length () > 0) {
			sb.append (post);
			sb.append ("\n");
		}
		
		return sb.toString ();
	}
}