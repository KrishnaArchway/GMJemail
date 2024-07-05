package com.archway.globalemail.util;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;

import com.archway.globalemail.common.Logger;

/**
 * 
 * Archway Marketing Services.
 * 7525 Cogswell Rd,
 * Romulus, MI - 48174.
 * Phone - 734.713.2000
 * 
 * Project Name            : gmJemail
 * 
 * Author                  : bhattam0 - Amar Bhatt Email:(AMAR_BHATT@ARCHWAY.COM) Ext:(2019)
 * File Name               : HostCommand.java 
 * Package Name            : com.archway.globalemail.util
 * 
 * Date                    : Mar 13, 2006 11:46:44 AM
 * 
 * Change Date             : Tuesday, May 16, 2006
 * 						     May 18, 2006 4:14:05 PM
 *                           May 30, 2006 12:36:23 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * Class to execute host commands
 *  
 * this class also contains other methods related to the system.
 */

public class HostCommand
{

	/**
	 * Display System properties on Std Output
	 *
	 */
	public static void displaySystemProperties ()
	{
		System.out.println ();
		System.out.println ( "+-------------------------+" );
		System.out.println ( "| QUERY SYSTEM PROPERTIES |" );
		System.out.println ( "+-------------------------+" );

		Properties sysProps = System.getProperties ();

		System.out.println ();
		System.out.println ( "  ------------------------------------------------" );
		System.out.println ( "  Use list() method to list all property values..." );
		System.out.println ( "  ------------------------------------------------" );
		System.out.println ();

		sysProps.list ( System.out );

		System.out.println ();
		System.out.println ( "  ------------------------------------------------" );
		System.out.println ( "  Get Property Names and Enumerate through them..." );
		System.out.println ( "  ------------------------------------------------" );
		System.out.println ();

		Enumeration enProps = sysProps.propertyNames ();
		String key = "";
		while ( enProps.hasMoreElements () )
		{
			key = ( String ) enProps.nextElement ();
			System.out.println ( "  " + key + "  ->  " + sysProps.getProperty ( key ) );
		}

	}

	/**
	 * Get current size of heap in bytes
	 * 
	 * @return
	 */
	public static String getCurrentHeapSize ()
	{
		long heapSize = Runtime.getRuntime ().totalMemory ();
		return String.valueOf ( heapSize );
	}

	/**
	 * Get amount of free memory within the heap in bytes. This size will increase
	 * after garbage collection and decrease as new objects are created.
	 * 
	 * @return
	 */
	public static String getFreeHeapSize ()
	{
		long heapFreeSize = Runtime.getRuntime ().freeMemory ();
		return String.valueOf ( heapFreeSize );
	}

	/**
	 * Get maximum size of heap in bytes. 
	 * The heap cannot grow beyond this size.
	 * Any attempt will result in an OutOfMemoryException.
	 * 
	 * @return
	 */
	public static String getMaxHeapSize ()
	{
		long heapMaxSize = Runtime.getRuntime ().maxMemory ();
		return String.valueOf ( heapMaxSize );
	}

	/**
	 * return enumeration of System Properties
	 * 
	 * @return
	 */
	public static Enumeration getSystemProperties ()
	{
		Properties sysProps = System.getProperties ();
		Enumeration enProps = sysProps.propertyNames ();
		return enProps;
	}

	/**
	 * runs the host command passed
	 * 
	 * @param command
	 * @return
	 */
	public static boolean runHostCommand ( String command )
	{
		boolean flag = false;
		try
		{
			if ( command.length () != 0 )
			{
				Process p = Runtime.getRuntime ().exec ( command );
			}
			flag = true;
		}
		catch ( Exception e )
		{
			flag = false;
			e.printStackTrace ();
		}
		return flag;
	}

	public static void setLogFileMode ()
	{

		InputStream propertiesFile = null;
		try
		{
			Properties sysProps = System.getProperties ();
			String key = "os.name";
			String operatingSystem = sysProps.getProperty ( key ).toUpperCase ();
			//System.out.println("OS  "+operatingSystem);
			propertiesFile = Logger.class.getClassLoader ().getResourceAsStream ( "log4j.properties" );
			Properties logProperties = new Properties ();
			logProperties.load ( propertiesFile );
			Enumeration enProps = logProperties.keys ();
			key = "";
			String lstrKey = "";
			StringBuffer k = new StringBuffer ();
			String cmd = "";
			while ( enProps.hasMoreElements () )
			{
				key = ( String ) enProps.nextElement ();
				lstrKey = key.toUpperCase ();
				if ( lstrKey.indexOf ( "FILE" ) > 0 )
				{
					k.append ( key + "," );
				}
			}
			String array[] = k.toString ().split ( "," );
			Arrays.sort ( array , String.CASE_INSENSITIVE_ORDER );
			for ( int j = 0 ; j < array.length ; j++ )
			{
				System.out.println ( logProperties.getProperty ( array [j] ) );
				if ( operatingSystem.indexOf ( "WIND" ) == - 1 )
				{
					//cmd += logProperties.getProperty ( array [j] ) + " ";
					cmd = "chmod 664 " + logProperties.getProperty ( array [j] );
					runHostCommand ( cmd );

				}
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace ();
		}

	}

	/**
	 * Default Constructor
	 *
	 */
	public HostCommand ()
	{

	}
}