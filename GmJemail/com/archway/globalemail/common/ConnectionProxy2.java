package com.archway.globalemail.common;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * 
 * Archway Marketing Services.
 * 7525 Cogswell Rd,
 * Romulus, MI - 48174.
 * Phone - 734.713.2000
 * 
 * Project Name            : jemail
 * 
 * Author                  : bhattam0 - Amar Bhatt Email:(AMAR_BHATT@ARCHWAY.COM) Ext:(2019)
 * Date                    : Jan 9, 2006 12:28:30 PM
 * File Name               : ConnectionProxy.java 
 * Package Name            : com.archway.globalemail.common
 * 
 * Description 
 * ---------------------------------------------------------
 * 
 * ConnectionProxy provides a wrapper for the Connection delivered by
 * OracleConnectionCacheImpl. Calls to methods on the Connection are intercepted
 * and wrapped in a try catch block that evaluates SQLExceptions. If exceptions
 * are caused by a temporary disconnect from the database the proxy notifies the
 * cache that it should refresh the pool. This class also provides an
 * opportunity to support database level security. A method has been stubbed out
 * that would provide each Connection the opportunity to notify the GEM database
 * of the user making the request.
 *
 */
public class ConnectionProxy2 implements java.sql.Connection
{

	private com.archway.globalemail.common.ConnectionCacheImpl	cache		= null;

	private String												loginID		= null;

	private java.sql.Connection									conn		= null;

	private static final boolean								VALID		= false;

	private static final boolean								CORRUPTED	= true;

	/**
	 * Database ping. Use extreme caution when altering. This SQL is run on
	 * EVERY connection!
	 */
	private static final String									TEST_SQL	= "select sysdate from dual";

	/*
	 * private static Category cat = null; static { if (!LogConfig.isLogging()) {
	 * LogConfig.initLogging(); } cat =
	 * Category.getInstance(com.archway.globalemail.common.ConnectionProxy.class); }
	 */

	private static Logger										logger		= Logger
																					.getLogger ( ConnectionProxy2.class );








	@Override
	public void setSchema(String schema) throws SQLException {
		

	}

	@Override
	public String getSchema() throws SQLException {
			return null;
	}
	







    @Override 
    public int getNetworkTimeout() throws SQLException
    { 
        return 0;
    }


@Override
    	public void abort(Executor executor) throws SQLException {
    		
    
    	}
    
    	@Override
    	public void setNetworkTimeout(Executor executor, int milliseconds)
    			throws SQLException {
    		
    
	}



public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
throw new UnsupportedOperationException();
}

public Blob createBlob() throws SQLException {
throw new UnsupportedOperationException();
}

public Clob createClob() throws SQLException {
throw new UnsupportedOperationException();
}

public NClob createNClob() throws SQLException {
throw new UnsupportedOperationException();
}

public SQLXML createSQLXML() throws SQLException {
throw new UnsupportedOperationException();
}

public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
throw new UnsupportedOperationException();
}

public Properties getClientInfo() throws SQLException {
throw new UnsupportedOperationException();
}

public String getClientInfo(String name) throws SQLException {
throw new UnsupportedOperationException();
}

public boolean isValid(int timeout) throws SQLException {
throw new UnsupportedOperationException();
}

public void setClientInfo(Properties properties) throws SQLClientInfoException {
throw new UnsupportedOperationException();
}

public void setClientInfo(String name, String value) throws SQLClientInfoException {
throw new UnsupportedOperationException();
}

public boolean isWrapperFor(Class arg0) throws SQLException {
throw new UnsupportedOperationException();
}

public Object unwrap(Class arg0) throws SQLException {
throw new UnsupportedOperationException();
}







	/**
	 * Constructor for Connection without database level security.
	 * 
	 * @param com.archway.globalemail.common.ConnectionCacheImpl
	 *            is a reference to the wrapped OracleConnectionCacheImpl
	 * @param java.sql.Connection
	 *            is a reference to the Connection to be wrapped.
	 * @return an instance of ConnectionProxy wrapping passed connection.
	 */
	public ConnectionProxy2 (
							com.archway.globalemail.common.ConnectionCacheImpl theCache ,
							java.sql.Connection theConn )
	{
		this.cache = theCache;
		this.conn = theConn;
		//cat.debug("ConnectionProxy created");
	}

	/**
	 * Constructor for Connection with database level security.
	 * 
	 * @param com.archway.globalemail.common.ConnectionCacheImpl
	 *            is a reference to the wrapped OracleConnectionCacheImpl
	 * @param java.sql.Connection
	 *            is a reference to the Connection to be wrapped.
	 * @param String
	 *            is the login id of the client making the request.
	 * @return an instance of ConnectionProxy wrapping passed connection.
	 */
	public ConnectionProxy2 (
							com.archway.globalemail.common.ConnectionCacheImpl theCache ,
							java.sql.Connection theConn ,
							String loginID )
	{
		this.cache = theCache;
		this.conn = theConn;
	}

	/** @see java.sql.Connection */
	public void setCatalog ( java.lang.String catalog ) throws java.sql.SQLException
	{
		try
		{
			conn.setCatalog ( catalog );
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public void rollback () throws java.sql.SQLException
	{
		try
		{
			conn.rollback ();
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public void clearWarnings () throws java.sql.SQLException
	{
		try
		{
			conn.clearWarnings ();
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public java.util.Map getTypeMap () throws java.sql.SQLException
	{
		try
		{
			return conn.getTypeMap ();
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public int getTransactionIsolation () throws java.sql.SQLException
	{
		try
		{
			return conn.getTransactionIsolation ();
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public java.sql.PreparedStatement prepareStatement ( java.lang.String statement )
																						throws java.sql.SQLException
	{
		try
		{
			return conn.prepareStatement ( statement );
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public void setTransactionIsolation ( int level ) throws java.sql.SQLException
	{
		try
		{
			conn.setTransactionIsolation ( level );
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/**
	 * checks to see if the connection is closed. added functionality to really
	 * check if connection is closed since the regular java.sql. function still
	 * thinks the connection is open if the database goes down while the
	 * connection is open.
	 * 
	 * @see java.sql.Connection#isClosed
	 */
	public boolean isClosed () throws java.sql.SQLException
	{
		Statement stmt = null;
		try
		{
			if ( ! conn.isClosed () )
			{
				stmt = conn.createStatement ();
				stmt.executeQuery ( TEST_SQL );
				return false;
			}
			else
			{
				return true;
			}
		}
		catch ( SQLException sqe ) //Assume any exception means that the
								   // connection
		// was really not funcitoning
		{
			try
			{
				conn.close ();
			}
			catch ( Exception ex )
			{/* ignore */
			}
			return true; //return true if there is ANY problem with this
			// connection at all. This will cause it to be removed
			// from pool.
		}
		finally
		{
			try
			{
				stmt.close ();
			}
			catch ( Exception e )
			{
			}
		}
	}

	/** @see java.sql.Connection */
	public java.sql.Statement createStatement () throws java.sql.SQLException
	{
		//cat.debug("createStatement()");
		try
		{
			return conn.createStatement ();
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public java.sql.Statement createStatement ( int resultSetType , int resultSetConcurrency )
																								throws java.sql.SQLException
	{
		try
		{
			return conn.createStatement ( resultSetType , resultSetConcurrency );
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public void setAutoCommit ( boolean autoCommit ) throws java.sql.SQLException
	{
		try
		{
			conn.setAutoCommit ( autoCommit );
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public java.sql.CallableStatement prepareCall (
													java.lang.String sql ,
													int resultSetType ,
													int resultSetConcurrency )
																				throws java.sql.SQLException
	{
		try
		{
			return conn.prepareCall ( sql , resultSetType , resultSetConcurrency );
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public void commit () throws java.sql.SQLException
	{
		try
		{
			conn.commit ();
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public java.lang.String getCatalog () throws java.sql.SQLException
	{
		try
		{
			return conn.getCatalog ();
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public java.sql.PreparedStatement prepareStatement (
														java.lang.String sql ,
														int resultSetType ,
														int resultSetConcurrency )
																					throws java.sql.SQLException
	{
		try
		{
			return conn.prepareStatement ( sql , resultSetType , resultSetConcurrency );
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public boolean isReadOnly () throws java.sql.SQLException
	{
		try
		{
			return conn.isReadOnly ();
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public void setReadOnly ( boolean readOnly ) throws java.sql.SQLException
	{
		try
		{
			conn.setReadOnly ( readOnly );
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public java.sql.DatabaseMetaData getMetaData () throws java.sql.SQLException
	{
		try
		{
			return conn.getMetaData ();
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public boolean getAutoCommit () throws java.sql.SQLException
	{
		try
		{
			return conn.getAutoCommit ();
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public java.lang.String nativeSQL ( java.lang.String nativesql ) throws java.sql.SQLException
	{
		try
		{
			return conn.nativeSQL ( nativesql );
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public java.sql.CallableStatement prepareCall ( java.lang.String prepareCall )
																					throws java.sql.SQLException
	{
		try
		{
			return conn.prepareCall ( prepareCall );
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public void setTypeMap ( final java.util.Map map ) throws java.sql.SQLException
	{
		try
		{
			conn.setTypeMap ( map );
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public java.sql.SQLWarning getWarnings () throws java.sql.SQLException
	{
		try
		{
			return conn.getWarnings ();
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/** @see java.sql.Connection */
	public void close () throws java.sql.SQLException
	{
		try
		{
			conn.close ();
		}
		catch ( SQLException sqe )
		{
			if ( evaluateError ( sqe ) == CORRUPTED )
			{
				cache.refresh ();
			}
			throw sqe;
		}
	}

	/**
	 * Evaluates any SQLException for potential loss of database connectivity.
	 * 
	 * @param SQLException
	 *            thrown by any Connection method
	 * @return boolean indicating whether Connection is still VALID.
	 */
	private boolean evaluateError ( SQLException sqe )
	{
		logger.debug ( "evaluateError, sqe: " + sqe.getMessage () );
		//Check for messages and codes in case codes or message change. Just a
		// little added security.
		if ( sqe.getMessage ().toUpperCase ().indexOf ( Constants.NOT_CONNECTED_CODE ) > 0
				|| sqe.getMessage ().toUpperCase ().indexOf ( Constants.SESSION_KILLED_CODE ) > 0
				|| sqe.getMessage ().toUpperCase ().indexOf ( Constants.END_OF_FILE_CODE ) > 0
				|| sqe.getMessage ().toUpperCase ().indexOf ( Constants.NOT_CONNECTED_MSG ) > 0
				|| sqe.getMessage ().toUpperCase ().indexOf ( Constants.SESSION_KILLED_MSG ) > 0
				|| sqe.getMessage ().toUpperCase ().indexOf ( Constants.END_OF_FILE_MSG ) > 0 )
		{
			return CORRUPTED;
		}
		return VALID;
	}

	/**
	 * Sets the current User in GEM so that GEM security built into views will
	 * function. Currently logged on user will then have only their own
	 * permissions on data.
	 * 
	 * @return an int indicating 0 for failure and 1 for success.
	 */
	private int setGEMUser ( String strLoginID )
	{
		//cat.debug("setGEMUser()");
		//cat.debug("strLoginID: " + strLoginID);
		CallableStatement cstmt = null;
		String sql = "{call gem_security_logon_pkg.setLoginId(?)}";
		try
		{
			cstmt = this.conn.prepareCall ( sql );
			cstmt.setString ( 1 , strLoginID );
			cstmt.execute ();
		}
		catch ( SQLException sqe )
		{
			logger.debug ( "setGEMUser() sqe: " + sqe.getMessage () );
			return 0; //0 is false
		}
		finally
		{
			try
			{
				cstmt.close ();
			}
			catch ( Exception e )
			{/* ignore */
			}
		}
		return 1;
	}

	void prepareConnection () throws SQLException
	{
		if ( IConstants.DB_SECURITY_ENABLED )
		{
			logger
					.debug ( "An unsecure connection has been requested with Database-level security enabled." );
			//setGEMUser(loginID);
		}
		this.setAutoCommit ( true );
	}

	void prepareConnection ( String loginID ) throws SQLException
	{
		if ( IConstants.DB_SECURITY_ENABLED )
		{
			if ( null == loginID || "null".equals ( loginID ) )
			{
				//logger.debug("Obtaining a secure connection with an invalid
				// user
				// account");
			}
			setGEMUser ( loginID );
		}
		this.setAutoCommit ( true );
	}

	public void setLoginID ( String user )
	{
		loginID = user;
	}

	public String getLoginId ()
	{
		return loginID;
	}

	/**
	 * Test method to force a cache refresh.
	 */
	void testCacheRefresh () throws SQLException
	{
		cache.refresh ();
	}

	/** dummy methods to get this to compile. */
	public int getHoldability ()
	{
		return 0;
	}

	public void setHoldability ( int i )
	{
	}

	public Savepoint setSavepoint ()
	{
		return null;
	}

	public Savepoint setSavepoint ( String s )
	{
		return null;
	}

	public void releaseSavepoint ( Savepoint sp )
	{
	}

	public void rollback ( Savepoint s )
	{
	}

	public Statement createStatement ( int i , int j , int k )
	{
		return null;
	}

	public Statement prepareCall ( int i , int j , int k , int l )
	{
		return null;
	}

	public PreparedStatement prepareStatement ( String s , int i )
	{
		return null;
	}

	public PreparedStatement prepareStatement ( String s , int [] i )
	{
		return null;
	}

	public PreparedStatement prepareStatement ( String s , String [] ss )
	{
		return null;
	}

	public PreparedStatement prepareStatement ( String s , int i , int j , int k )
	{
		return null;
	}

	public CallableStatement prepareCall ( String s , int i , int j , int k )
	{
		return null;
	}

}
