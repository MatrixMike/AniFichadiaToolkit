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
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.anifichadia.toolkit.file.comparators.FileFolderOrder;
import com.anifichadia.toolkit.file.comparators.NaturalOrderFileComparator;
import com.anifichadia.toolkit.file.filters.DirectoryFilter;
import com.anifichadia.toolkit.file.filters.NormalFileFilter;

/**
 * Collection of File Operations compatible with Java 6. <br />
 * <br />
 * Note: Documentation and refinements pending completion
 * 
 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
 *         (http://github.com/AniFichadia)
 */
public class J6FileOperations
{
	// =============================== Methods ===============================
	public static List<File> findAllFiles(File file, boolean scanSubdir)
	{
		List<File> fileList = new ArrayList<File> ();
		
		if (file.isDirectory ()) {
			List<File> files = Arrays.asList (file.listFiles ());
			Collections.sort (files, new NaturalOrderFileComparator (FileFolderOrder.FILE_FIRST));
			
			for (File f : files) {
				fileList.add (f);
				
				if (f.isDirectory () && scanSubdir) {
					fileList.addAll (findAllFiles (f, scanSubdir));
				}
			}
		} else {
			fileList.add (file);
		}
		
		return fileList;
	}
	
	
	public static List<File> findAllFiles(File file, boolean scanSubdir, FilenameFilter filter)
	{
		List<File> fileList = new ArrayList<File> ();
		
		if (file.isDirectory ()) {
			File[] files = null;
			
			if (filter == null) {
				files = file.listFiles (new NormalFileFilter ());
			} else {
				files = file.listFiles (filter);
			}
			
			Arrays.sort (files, new NaturalOrderFileComparator (FileFolderOrder.FILE_FIRST));
			
			for (File f : files) {
				if ( !f.isDirectory ()) {
					fileList.add (f);
				}
			}
			
			if (scanSubdir) {
				files = file.listFiles (new DirectoryFilter ());
				Arrays.sort (files,
						new NaturalOrderFileComparator (FileFolderOrder.DIRECTORY_FIRST));
				
				for (File f : files) {
					fileList.addAll (findAllFiles (f, scanSubdir, filter));
				}
			}
		} else if (filter.accept (file.getParentFile (), file.getName ())) {
			fileList.add (file);
		}
		
		return fileList;
	}
	
	
	public static List<File> findAllFiles(File file, boolean scanSubdir, FileFilter filter)
	{
		List<File> fileList = new ArrayList<File> ();
		
		if (file.isDirectory ()) {
			File[] files = null;
			
			if (filter == null) {
				files = file.listFiles (new NormalFileFilter ());
			} else {
				files = file.listFiles (filter);
			}
			
			Arrays.sort (files, new NaturalOrderFileComparator (FileFolderOrder.FILE_FIRST));
			
			for (File f : files) {
				if ( !f.isDirectory ()) {
					fileList.add (f);
				}
			}
			
			if (scanSubdir) {
				files = file.listFiles (new DirectoryFilter ());
				Arrays.sort (files,
						new NaturalOrderFileComparator (FileFolderOrder.DIRECTORY_FIRST));
				
				for (File f : files) {
					fileList.addAll (findAllFiles (f, scanSubdir, filter));
				}
			}
		} else if (filter.accept (file)) {
			fileList.add (file);
		}
		
		return fileList;
	}
	
	
	public static long directorySize(File directory)
	{
		long length = 0;
		for (File file : directory.listFiles ()) {
			if (file.isFile ()) {
				length += file.length ();
			} else {
				length += directorySize (file);
			}
		}
		return length;
	}
	
	
	public static File createFolder(String currentDir, String folderName)
	{
		if (currentDir == null)
			return null;
		
		File newFolder = new File (currentDir + File.separator + folderName);
		
		if (newFolder.exists ())
			return null;
		
		if (newFolder.mkdir ())
			return newFolder;
		
		return null;
	}
	
	
	public static void deleteFile(String filePath)
	{
		deleteFile (filePath, null);
	}
	
	
	public static void deleteFile(String filePath, CurrentFileListener cFL)
	{
		File file = new File (filePath);
		
		if (cFL != null) {
			cFL.onFileProcessed (file);
		}
		
		if ( !file.exists ())
			return;
		else if (file.isDirectory ()) {
			File[] files = file.listFiles ();
			
			for (int i = 0; i < files.length; i++) {
				deleteFile (files[i].getAbsolutePath (), cFL);
			}
			
			file.delete ();
		} else {
			file.delete ();
		}
	}
	
	
	public static File copyFile(File sourceFile, File destinationDir)
	{
		return copyFile (sourceFile, destinationDir, null);
	}
	
	
	public static File copyFile(File sourceFile, File destinationDir, CurrentFileListener cFL)
	{
		if ( !sourceFile.exists () || !destinationDir.isDirectory ())
			return null;
		
		if (cFL != null) {
			cFL.onFileProcessed (sourceFile);
		}
		
		if (sourceFile.isDirectory ()) {
			File f = createFolder (destinationDir.getAbsolutePath (), sourceFile.getName ());
			
			File[] files = sourceFile.listFiles ();
			for (int i = 0; i < files.length; i++) {
				copyFile (files[i], f, cFL);
			}
			
			return f;
		} else {
			FileInputStream fis = null;
			FileOutputStream fos = null;
			FileChannel in = null;
			FileChannel out = null;
			
			File destFile = new File (destinationDir.getAbsolutePath () + File.separator
					+ sourceFile.getName ());
			
			try {
				destFile.createNewFile ();
				
				fis = new FileInputStream (sourceFile);
				fos = new FileOutputStream (destFile);
				in = fis.getChannel ();
				out = fos.getChannel ();
				
				long size = in.size ();
				in.transferTo (0, size, out);
			} catch (Throwable e) {
				e.printStackTrace ();
			} finally {
				try {
					if (fis != null) {
						fis.close ();
					}
					
					if (fos != null) {
						fos.close ();
					}
					
					if (in != null && in.isOpen ()) {
						in.close ();
					}
					
					if (out != null && out.isOpen ()) {
						out.close ();
					}
				} catch (IOException e) {}
			}
			
			return destFile;
		}
	}
	
	
	public static File moveFile(File sourceFile, File destinationDir)
	{
		if (destinationDir == null || !destinationDir.isDirectory ())
			return null;
		
		File movedFile = new File (destinationDir.getAbsolutePath () + File.separator
				+ sourceFile.getName ());
		
		if (sourceFile.renameTo (movedFile))
			return movedFile;
		
		return null;
	}
	
	
	public static File renameFile(File sourceFile, String newName)
	{
		File movedFile = new File (sourceFile.getParent () + File.separator + newName);
		
		if (sourceFile.renameTo (movedFile))
			return movedFile;
		
		return null;
	}
	
	
	// ============================= Inner Types =============================
	/**
	 * Listener to respond updates during file manipulation operations.<br />
	 * <br />
	 * Refer to: <br />
	 * <ul>
	 * <li>{@link J6FileOperations#copyFile(File, File, CurrentFileListener)}</li>
	 * <li>{@link J6FileOperations#deleteFile(String, CurrentFileListener)}</li>
	 * </ul>
	 * 
	 * @author Aniruddh Fichadia | Email: Ani.Fichadia@gmail.com | GitHub Username: AniFichadia
	 *         (http://github.com/AniFichadia)
	 */
	public interface CurrentFileListener
	{
		public void onFileProcessed(File f);
	}
}