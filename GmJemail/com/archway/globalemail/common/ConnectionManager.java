package com.archway.globalemail.common;

import java.sql.Connection;
import java.util.Properties;

import oracle.jdbc.pool.OracleConnectionCacheImpl;

import org.apache.log4j.Category;

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
 * File Name               : ConnectionManager.java 
 * Package Name            : com.archway.globalemail.common
 * 
 * Date                    : Oct 27, 2005 - 2:56:45 PM
 * 
 * Change Date             : May 18, 2006 3:52:08 PM
 * 							 May 30, 2006 11:55:46 AM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * @Purpose of this Class : * Singleton that manages access to the single OracleConnectionCache for
 * application. An unsynchronized accessor method is provided for obtaining the
 * cache to improve performance. A getInstance method is provided to allow a
 * ConfigurationServlet to initialize the cache. * 
 */

public class ConnectionManager
{

	private static OracleConnectionCacheImpl	cache			= null;

	private static Category						cat				= null;

	private static ConnectionManager			connMgr			= null;

	/*static
	 {
	 if (!LogConfig.isLogging())
	 {
	 LogConfig.initLogging();
	 }
	 cat = Category.getInstance(com.archway.globalemail.common.ConnectionManager.class);
	 System.runFinalizersOnExit(true);
	 init();
	 }*/

	//logger
	private static Logger						logger			= Logger.getLogger ( ConnectionManager.class );

	private static boolean						reinitialize	= false;

	/**
	 * Clean up database connection cache.
	 */
	public static void cleanup ()
	{
		if ( cache != null )
		{
			try
			{
				cache.close ();
				cat.debug ( "completed cache.close during cleanup" );
				cache = null;
				reinitialize = true;
			}
			catch ( Exception e )
			{
				cat.debug ( "failed to run cache.close during cleanup: " + e.getMessage () );
			}
		}
	}

	/**
	 * @return the only instance of OracleConnectionCacheImpl to be used in the
	 *         application.
	 * @throw ArchwayException that indicates a fatal error if thrown. Only
	 *        occurs if there is a bug. Note: The method is not synchronized
	 *        intentionally. It avoids the need for this by simply throwing an
	 *        exception if the cache is null.
	 */
	public static synchronized OracleConnectionCacheImpl getCache ()
																	throws ArchwayException
	{
		if ( cache == null || reinitialize )
		{
			cat.debug ( "getCache calling init dbcache" );
			initializeDatabaseCache ( ConfigurationServlet.getApplicationPropertiesFile () );
			reinitialize = false;
		}
		cat.debug ( "ConnCache Active size: " + cache.getActiveSize () );
		cat.debug ( "ConnCache Cache size: " + cache.getCacheSize () );
		return cache;
	}

	/**
	 * Initializes ConnectionManager with properties read from application
	 * properties file.
	 */
	private static void init ()
	{
		//find properties file
		Properties props = ConfigurationServlet.getApplicationPropertiesFile ();
		//pass to db cache init
		try
		{
			initializeDatabaseCache ( props );
		}
		catch ( ArchwayException ge )
		{
			logger.debug ( "Unable to initialize database from properties file! Error: " + ge.getMessage () );
		}
	}

	/**
	 * @throw ArchwayException indicating a fatal error during database cache
	 *        initialization. ConfigurationServlet is the only class that should
	 *        call this method.
	 */
	public static synchronized final void initializeDatabaseCache ()
																	throws ArchwayException
	{
		//initialize conn cache here
		if ( cache != null )
		{
			cat.debug ( "db cache already initd" );
			throw new ArchwayException ( "Database connection cache is already initialized" , 0 ); //assign
			// a
			// real
			// error
			// code
			// to
			// this!!!
		}
		cache = ConnectionCacheImpl.getCache ();
	}

	/**
	 * @param Properties
	 *        file containing the database initialization properties
	 * @throw ArchwayException indicating a fatal error during database cache
	 *        initialization.
	 */
	public static synchronized final void initializeDatabaseCache (
																	Properties dbProps )
																						throws ArchwayException
	{
		if ( cache != null )
		{
			throw new ArchwayException ( "Database connection cache is already initialized" , 0 ); //assign
			// a
			// real
			// error
			// code
			// to
			// this!!!
		}
		cache = ConnectionCacheImpl.getCache ();
	}

	/**
	 * Overrides Object.finalize(). Does not get called in Jserv without
	 * System.runFinalizersOnExit() method call prior.
	 */
	protected void finalize ()
	{
		cat.debug ( "finalize called" );
		cleanup ();
	}

	/**
	 * A simple test attempting to get a connection from the cache. Designed to
	 * catch errors right away.
	 * 
	 * @throw a ArchwayException indicating a fatal error if a connection cannot
	 *        be obtained from cache.
	 */
	private void testConnection () throws ArchwayException
	{
		Connection conn = null;
		try
		{
			conn = cache.getConnection ();
			if ( conn == null )
			{
				logger.debug ( "Database intialization failed" );
				throw new ArchwayException ( "Database intialization failed" , IConstants.FATAL_ERROR );
			}
		}
		catch ( Exception e )
		{
			logger.debug ( "Database intialization failed" );
			throw new ArchwayException ( "Database intialization failed" , IConstants.FATAL_ERROR );
		}
		finally
		{
			if ( conn != null )
			{
				try
				{
					conn.close ();
				}
				catch ( Exception e )
				{/* ignore */
				}
			}
		}
	}

}