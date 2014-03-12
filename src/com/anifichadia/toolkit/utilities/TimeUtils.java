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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A collection of various time utility methods
 * 
 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
 *         (http://github.com/AniFichadia)
 */
public class TimeUtils
{
	// =============================== Methods ===============================
	/**
	 * Formats an long integer time value in format (-)hh:mm:ss(.S)
	 * 
	 * @param time Long value to convert into a time string
	 * 
	 * @return A time string in the format hh:mm:ss
	 */
	public static String formatTimeLikeTimer(long time, boolean appendMs)
	{
		boolean isPositive = (time >= 0);
		
		if ( !isPositive) {
			time *= -1;
		}
		
		long milliSeconds = time;
		long seconds = time / 1000;
		long minutes = seconds / 60;
		long hours = minutes / 60;
		
		milliSeconds = milliSeconds % 1000;
		seconds = seconds % 60;
		minutes = minutes % 60;
		hours = hours % 24;
		
		String msTrim = (milliSeconds + "").charAt (0) + "";
		
		return ((isPositive) ? "" : "-") + hours + ":" + ((minutes < 10) ? "0" + minutes : minutes)
				+ ":" + ((seconds < 10) ? "0" + seconds : seconds) + (appendMs ? "." + msTrim : "");
	}
	
	
	/**
	 * Refer to {@link #formatTimeLikeTimer(long, boolean)}. Supplies boolean argument as true
	 */
	public static String formatTimeLikeTimer(long time)
	{
		return formatTimeLikeTimer (time, true);
	}
	
	
	/**
	 * Wrapper for SimpleDateFormat
	 * 
	 * @param time Unix Epoch timestamp for date
	 * @param pattern SimpleDateFormat formatting pattern
	 * 
	 * @return SimpleDateFormat'ed time
	 */
	public static String formatTime(long time, String pattern)
	{
		return new SimpleDateFormat (pattern).format (new Date (time));
	}
	
	
	/**
	 * Refer to formatTimeIntAsTime(long)
	 * 
	 * Used for integer values instead of longs.
	 */
	public static String formatTimeIntAsTime(int time)
	{
		return formatTimeLikeTimer (time);
	}
	
	
	public static long timeAsMillis(int days, int hours, int minutes, int seconds, int millis)
	{
		return days * 24 * 60 * 60 * 1000 + hours * 60 * 60 * 1000 + minutes * 60 * 1000 + seconds
				* 1000 + millis;
	}
}