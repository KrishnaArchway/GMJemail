package com.archway.globalemail.common;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;

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
 * File Name               : ConnectionCacheImpl.java 
 * Package Name            : com.archway.globalemail.common
 * 
 * Date                    : Oct 27, 2005 - 2:56:29 PM
 * 
 * Change Date             : May 18, 2006 3:52:35 PM
 *                           May 30, 2006 11:54:51 AM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * @Purpose of this Class : Provides a Singleton wrapper for OracleConnectionCacheImpl. Originally an
 * extension on the class It was changed to a wrapper when it was discovered
 * that when calling close() on the parent class objects that referenced it were
 * dereferenced. ConnectionCacheImpl now wraps the class to allow
 * getConnection() to return a proxy Connection that checks for database
 * diconnects and can perform database security authentication on every
 * connection.  
 */
public class ConnectionCacheImpl extends OracleConnectionCacheImpl
{

	private static Category						cat				= null;

	private static java.util.Date				lastRefresh		= new java.util.Date ();

	/*static
	 {
	 if (!LogConfig.isLogging())
	 {
	 LogConfig.initLogging();
	 }
	 cat = Category.getInstance(com.archway.globalemail.common.ConnectionCacheImpl.class);
	 System.runFinalizersOnExit(true);
	 }*/

	//logger
	private static Logger						logger			= Logger.getLogger ( ConnectionCacheImpl.class );

	private static long							REFRESH_DELAY	= new Long ( ConfigurationServlet.getApplicationProperty ( Constants.CONN_POOL_REFRESH_DELAY_PARM , "3600" ) ).longValue ();

	private static OracleConnectionCacheImpl	theCache		= null;

	private static ConnectionCacheImpl			wrapper			= null;

	/**
	 * Clean up database connection cache.
	 */
	public static void cleanup ()
	{
		//logger.debug("cleanup()");
		if ( theCache != null )
		{
			try
			{
				theCache.close ();
				theCache = null;
				//logger.debug("completed cache.close() during cleanup");
			}
			catch ( Exception e )
			{
				logger.debug ( "failed to run cache.close() during cleanup: " + e.getMessage () );
			}
		}
	}

	/**
	 * Ensures a single instance per classloader. Double checked locking is not
	 * used due to recent indications that it is not thread safe in Java.
	 * 
	 * @return ConnectionCacheImpl that wraps OracleConnectionCacheImpl
	 */
	public static synchronized final ConnectionCacheImpl getCache ()
	{
		if ( wrapper == null )
		{
			try
			{
				wrapper = new ConnectionCacheImpl ();
				initOracleConnectionCacheImpl (); //GAE moved inside condition
				// 12/5/2001
			}
			catch ( Exception e )
			{
				logger.fatal ( "Unable to initialize connection pool: " + e.getMessage () );
				logger.fatal ( "Check database configuration in the application properties file: " + IConstants.APP_PROP_FILE );
			}
		}
		return wrapper;
	}

	/**
	 * Initializes and if necessary creates new instance of
	 * OracleConnectionCacheImpl
	 */
	private static synchronized void initOracleConnectionCacheImpl ()
																		throws SQLException
	{
		if ( theCache == null )
		{
			//logger.debug("creating new oracle cache instance.");
			theCache = new OracleConnectionCacheImpl ();
		}
		Properties dbProps = ConfigurationServlet.getApplicationPropertiesFile ();
		theCache.setURL ( dbProps.getProperty ( Constants.DB_URL_PARM ) );
		theCache.setUser ( dbProps.getProperty ( Constants.DB_USER_PARM ) );
		theCache.setPassword ( dbProps.getProperty ( Constants.DB_PW_PARM ) );
		theCache.setMaxLimit ( new Integer ( dbProps.getProperty ( Constants.DB_MAX_CACHED_CONN_PARM , "15" ) ).intValue () );
		theCache.setCacheScheme ( new Integer ( dbProps.getProperty ( Constants.DB_CACHE_SCHEME_PARM , new Integer ( OracleConnectionCacheImpl.DYNAMIC_SCHEME ).toString () ) ).intValue () );

		//logger.info("ConnectionCacheImpl has been initialized.");
		logger.info ( "URL: " + theCache.getURL () );
		//logger.info("UID: "+ dbProps.getProperty( Constants.DB_USER_PARM ));
		//logger.info("Current Scheme: " + theCache.getCacheScheme());
		//logger.info("(DYNAMIC_SCHEME = "+ OracleConnectionCacheImpl.DYNAMIC_SCHEME
		// + ", FIXED_RETURN_NULL_SCHEME = " +
		// OracleConnectionCacheImpl.FIXED_RETURN_NULL_SCHEME + ")");
	}

	/**
	 * Default Constructor.
	 * 
	 * @throws java.sql.SQLException
	 */
	private ConnectionCacheImpl () throws java.sql.SQLException
	{
	}

	/** @see OracleConnectionCacheImpl */
	public void close () throws SQLException
	{
		theCache.close ();
	}

	/** @see OracleConnectionCacheImpl */
	public void closePooledConnection ( PooledConnection pc )
																throws SQLException
	{
		theCache.closePooledConnection ( pc );
	}

	/**
	 * Overrides Object.finalize(). Does not get called in Jserv without
	 * System.runFinalizersOnExit() method call prior.
	 */
	protected void finalize ()
	{
		//logger.debug("finalize called");
		cleanup ();
	}

	/** @see OracleConnectionCacheImpl */
	public int getActiveSize ()
	{
		return theCache.getActiveSize ();
	}

	/** @see OracleConnectionCacheImpl */
	public int getCacheScheme ()
	{
		return theCache.getCacheScheme ();
	}

	/** @see OracleConnectionCacheImpl */
	public int getCacheSize ()
	{
		return theCache.getCacheSize ();
	}

	/**
	 * @return a Connection wrapped by ConnectionProxy
	 * @see ConnectionProxy
	 * @deprecated This method will soon be unavailable in order to enforce view
	 *             security. Use getConnection(String loginID).
	 */
	public synchronized Connection getConnection () throws SQLException
	{
		//logger.debug("This method should no longer be used. Use
		// getConnection(String loginID) instead.");
		Connection conn = theCache.getConnection ();
		ConnectionProxy proxy = new ConnectionProxy ( wrapper , conn );
		if ( proxy.isClosed () )
		{
			wrapper.refresh ();
			proxy = new ConnectionProxy ( wrapper , theCache.getConnection () );
			if ( proxy.isClosed () )
			{
				logger.info ( "Connection still closed right out of cache after refresh of pool" );
				throw new SQLException ( "The site is temporarily unavailable.  Please try again in a few moments." );

			}
		}
		proxy.prepareConnection ();
		return proxy;
	}

	/**
	 * @param String
	 *        representing the loginID of the client requesting Connection
	 * @return a Connection wrapped by ConnectionProxy
	 * @see ConnectionProxy
	 */
	public synchronized Connection getConnection ( String loginID )
																	throws SQLException
	{
		Connection conn = theCache.getConnection ();
		ConnectionProxy proxy = new ConnectionProxy ( wrapper , conn , loginID );
		if ( proxy.isClosed () )
		{
			wrapper.refresh ();
			proxy = new ConnectionProxy ( wrapper , theCache.getConnection () , loginID );
			if ( proxy.isClosed () )
			{
				//logger.debug("Connection still closed right out after refresh of
				// pool");
				throw new SQLException ( "The site is temporarily unavailable.  Please try again in a few moments." );

			}
		}
		proxy.prepareConnection ( loginID );
		return proxy;
	}

	/** @see OracleConnectionCacheImpl */
	public synchronized Connection getConnection ( String user , String passwd )
																				throws SQLException
	{
		return this.getConnection ();
	}

	/** @see OracleConnectionCacheImpl */
	public int getMaxLimit ()
	{
		return theCache.getMaxLimit ();
	}

	/** @see OracleConnectionCacheImpl */
	public int getMinLimit ()
	{
		return theCache.getMinLimit ();
	}

	/** @see OracleConnectionCacheImpl */
	public Reference getReference () throws NamingException
	{
		return theCache.getReference ();
	}

	/**
	 * Closes all connections in pool and dereferences OracleConnectionCacheImpl
	 * instance.
	 */
	synchronized void refresh () throws SQLException
	{
		long timeSinceLastRefresh = System.currentTimeMillis () - lastRefresh.getTime ();
		//logger.debug("System.currentTimeMillis(): " + System.currentTimeMillis());
		//logger.debug("lastRefresh.getTime(): " + lastRefresh.getTime());
		//logger.debug("timeSinceLastRefresh: " + timeSinceLastRefresh);
		//logger.debug("REFRESH_DELAY: " + REFRESH_DELAY);
		if ( timeSinceLastRefresh < REFRESH_DELAY )
		{
			//logger.info("Connection attempting refresh before REFRESH_DELAY
			// ended");
			return;
			//throw new SQLException("Database is temporarily unavailable. Try
			// again momentarily");
		}
		lastRefresh = new java.util.Date ();
		//logger.debug("refresh() at " + lastRefresh);
		if ( theCache != null )
		{
			theCache.close ();
			theCache = null; //must dereference
			//logger.debug("closed and dereferenced wrapped oracle cache.");
		}
		initOracleConnectionCacheImpl ();
	}

	/** @see OracleConnectionCacheImpl */
	public void reusePooledConnection ( PooledConnection pc )
																throws SQLException
	{
		theCache.reusePooledConnection ( pc );
	}

	/** @see OracleConnectionCacheImpl */
	public void setCacheScheme ( int s ) throws SQLException
	{
		theCache.setCacheScheme ( s );
	}

	/** @see OracleConnectionCacheImpl */
	public void setConnectionPoolDataSource ( ConnectionPoolDataSource ds )
																			throws SQLException
	{
		theCache.setConnectionPoolDataSource ( ds );
	}

	/** @see OracleConnectionCacheImpl */
	public void setMaxLimit ( int l ) throws SQLException
	{
		theCache.setMaxLimit ( l );
	}

	/** @see OracleConnectionCacheImpl */
	public void setMinLimit ( int l ) throws SQLException
	{
		theCache.setMinLimit ( l );
	}

}