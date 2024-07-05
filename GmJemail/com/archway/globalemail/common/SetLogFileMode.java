package com.archway.globalemail.common;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;

import com.archway.globalemail.util.HostCommand;

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
 * File Name               : SetLogFileMode.java 
 * Package Name            : com.archway.globalemail.common
 * 
 * Date                    : Mar 13, 2006 11:46:36 AM
 * 
 * Change Date             : Tuesday, May 16, 2006  
 * 							 May 30, 2006 12:06:27 PM (DEPRECATED)	
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * 
 * DEPRECATED but can be used later
 * 
 * 
 */

public class SetLogFileMode
{

	//logger
	protected static final Logger	logger	= Logger.getLogger ( SetLogFileMode.class );

	public static void main ( String [] args )
	{
		logger.entering ( "SetLogFileMode main method" );
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
					logger.debug ( "Host Command :- " + cmd );
					HostCommand.runHostCommand ( cmd );

				}
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace ();
		}
		finally
		{
			logger.exiting ( "SetLogFileMode main method" );
		}
	}
}