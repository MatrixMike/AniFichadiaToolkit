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

/**
 * Represents a command line argument supplied to a program
 * 
 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
 *         (http://github.com/AniFichadia)
 */
public class Argument
{
	// ============================= Attributes ==============================
	/** Type of Argument */
	private final ArgType	argType;
	/** Marker or flag identifying argument. This is case sensitive */
	private final String	marker;
	/** Name of Argument */
	private final String	name;
	/** Mandatory or not */
	private final boolean	mandatory;
	/** A description of the argument */
	private final String	description;
	/** Default value. Default value is used if useDefaultOnUnset is true */
	private final String	defaultValue;
	/** Use default value if argument isn't set. If default value is set, so is this flag */
	private final boolean	useDefaultOnUnset;
	
	
	// ============================ Constructors =============================
	public Argument (ArgType argType, String marker, String name, boolean mandatory,
			String description)
	{
		super ();
		this.argType = argType;
		this.marker = marker;
		this.name = name;
		this.mandatory = mandatory;
		this.description = description;
		defaultValue = null;
		useDefaultOnUnset = false;
	}
	
	
	public Argument (ArgType argType, String marker, String name, boolean mandatory,
			String description, String defaultValue)
	{
		super ();
		this.argType = argType;
		this.marker = marker;
		this.name = name;
		this.mandatory = mandatory;
		this.description = description;
		this.defaultValue = defaultValue;
		useDefaultOnUnset = true;
	}
	
	
	// ========================== Getters & Setters ==========================
	public String getDescription()
	{
		return description;
	}
	
	
	public String getDefaultValue()
	{
		return defaultValue;
	}
	
	
	public boolean isUseDefaultOnUnset()
	{
		return useDefaultOnUnset;
	}
	
	
	public ArgType getArgType()
	{
		return argType;
	}
	
	
	public String getMarker()
	{
		return marker;
	}
	
	
	public String getName()
	{
		return name;
	}
	
	
	public boolean isMandatory()
	{
		return mandatory;
	}
	
	
	// ============================= Inner Types =============================
	/**
	 * Type of command line argument. Can be either: <br />
	 * <ul>
	 * <li>a flag</li>
	 * <li>single value</li>
	 * <li>multi-value</li>
	 * </ul>
	 * 
	 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
	 *         (http://github.com/AniFichadia)
	 */
	public enum ArgType
	{
		/** Flag type argument (format: [marker]). Simple true or false flag */
		FLAG,
		/** Single value, assumed to be in format [marker] [value] */
		SINGLE_VALUE,
		/**
		 * Multi value. Marker can be used multiple times. Final value is a comma separated list of
		 * values
		 */
		MULTI_VALUE;
	}
}
