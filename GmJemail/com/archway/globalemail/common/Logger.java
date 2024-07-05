package com.archway.globalemail.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

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
 * File Name               : Logger.java 
 * Package Name            : com.archway.globalemail.common
 * 
 * Date                    : Nov 10, 2005 - 10:44:16 AM
 * 
 * Change Date             : May 18, 2006 3:55:07 PM
 *                           May 30, 2006 12:02:41 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * @Purpose of this Class : Wrapper for the logging utility. The purpose of this class is to guarantee
 * logger initialization and to simplify switching out logger implementation in
 * the future if needed. 
 */

public class Logger
{

	private static Boolean	logging	= new Boolean ( false );
	static
	{
		if ( ! isLogging () )
		{
			init ();
		}
	}

	/**
	 * returns logger
	 * 
	 * @param clazz
	 * @return
	 */
	public static Logger getLogger ( Class clazz )
	{
		return new Logger ( clazz );
	}

	/**
	 * 
	 * @param className
	 * @return
	 */
	public static Logger getLogger ( String className )
	{
		return new Logger ( className );
	}

	/**
	 * loads the loggers per log4j.properties file.
	 *
	 */
	private static void init ()
	{
		InputStream propertiesFile = null;
		try
		{
			propertiesFile = Logger.class.getClassLoader ().getResourceAsStream ( "log4j.properties" );
			Properties logProperties = new Properties ();
			logProperties.load ( propertiesFile );
			PropertyConfigurator.configure ( logProperties );
			synchronized ( logging )
			{
				logging = new Boolean ( true );
			}
			org.apache.log4j.Logger.getLogger ( Logger.class ).info ( "Logging has been initialized" );
		}
		catch ( IOException ioe )
		{
			System.out.println ( "Logger initialization failed due to: " + ioe );
		}
		finally
		{
			if ( null != propertiesFile )
			{
				try
				{
					propertiesFile.close ();
				}
				catch ( Exception e )
				{
				}
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	private static boolean isLogging ()
	{
		synchronized ( logging )
		{
			return logging.booleanValue ();
		}
	}

	private org.apache.log4j.Logger	adapter	= null;

	/**
	 * 
	 * @param clazz
	 */
	private Logger ( Class clazz )
	{
		adapter = org.apache.log4j.Logger.getLogger ( clazz );
	}

	/**
	 * 
	 * @param className
	 */
	private Logger ( String className )
	{
		adapter = org.apache.log4j.Logger.getLogger ( className );
	}

	/**
	 * 
	 * @param object
	 */
	public void debug ( Object object )
	{
		adapter.debug ( object );
	}

	/**
	 * 
	 * @param object
	 * @param throwable
	 */
	public void debug ( Object object , Throwable throwable )
	{
		adapter.debug ( object , throwable );
	}

	/**
	 * 
	 * @param methodName
	 */
	public void entering ( String methodName )
	{
		debug ( "Entering " + methodName );
	}

	/**
	 * 
	 * @param object
	 */
	public void error ( Object object )
	{
		adapter.error ( object );
	}

	/**
	 * 
	 * @param object
	 * @param throwable
	 */
	public void error ( Object object , Throwable throwable )
	{
		adapter.error ( object , throwable );
	}

	/**
	 * 
	 * @param methodName
	 */
	public void exiting ( String methodName )
	{
		debug ( "Exiting " + methodName );
	}

	/**
	 * 
	 * @param object
	 */
	public void fatal ( Object object )
	{
		adapter.fatal ( object );
	}

	/**
	 * 	
	 * @param object
	 * @param throwable
	 */
	public void fatal ( Object object , Throwable throwable )
	{
		adapter.fatal ( object , throwable );
	}

	/**
	 * 
	 * @param object
	 */
	public void info ( Object object )
	{
		adapter.info ( object );
	}

	/**
	 * 
	 * @param object
	 * @param throwable
	 */
	public void info ( Object object , Throwable throwable )
	{
		adapter.info ( object , throwable );
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDebugEnabled ()
	{
		return adapter.isDebugEnabled ();
	}

	/**
	 * 
	 * @return
	 */
	public boolean isInfoEnabled ()
	{
		return adapter.isInfoEnabled ();
	}

	/**
	 * 
	 * @param object
	 */
	public void warn ( Object object )
	{
		adapter.warn ( object );
	}

	/**
	 * 
	 * @param object
	 * @param throwable
	 */
	public void warn ( Object object , Throwable throwable )
	{
		adapter.warn ( object );
	}
}