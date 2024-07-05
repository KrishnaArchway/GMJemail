package com.archway.globalemail.common;

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
 * File Name               : IConstants.java 
 * Package Name            : com.archway.globalemail.common
 * 
 * Date                    : Oct 27, 2005 - 2:57:57 PM
 * 
 * Change Date             : May 18, 2006 3:54:52 PM
 * 							 May 30, 2006 11:52:44 AM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */

public interface IConstants
{

	//property File.
	public static final String		APP_PROP_FILE					= "jemail.gm.app.properties";

	//database cache constants
	public static final String		DB_URL_PARM						= "DB_URL_PARM";

	public static final String		DB_USER_PARM					= "DB_USER_PARM";

	public static final String		DB_PW_PARM						= "DB_PW_PARM";

	public static final String		DB_MAX_CACHED_CONN_PARM			= "DB_MAX_CACHED_CONN_PARM";

	public static final String		DB_CACHE_SCHEME_PARM			= "DB_CACHE_SCHEME_PARM";

	public static final String		CONN_POOL_REFRESH_DELAY_PARM	= "CONN_POOL_REFRESH_DELAY_PARM";

	public static final String		LOG_FILE_URI_PARM				= "LOG_FILE_URI_PARM";

	public static final String		JSP_DIRECTORY_PARM				= "JSP_DIRECTORY";

	public static final String		CLIENT_ID_PARM					= "CLIENT_ID";

	//Database Disconnect Messages watched by ConnectionProxy 
	public static final String		NOT_CONNECTED_MSG				= "Not Connected to Oracle";

	public static final String		SESSION_KILLED_MSG				= "Your session has been killed";

	public static final String		END_OF_FILE_MSG					= "end of file on communication channel";

	public static final String		NOT_CONNECTED_CODE				= "ORA-03114";

	public static final String		SESSION_KILLED_CODE				= "ORA-00028";

	public static final String		END_OF_FILE_CODE				= "ORA-03113";

	//error codes
	public static final int			FATAL_ERROR						= 1000;

	public static final int			WARNING							= 1001;

	//error msgs
	public static final String		DB_ERROR_MSG					= "Error retrieving data.  Please try again later.";

	public static final String		UNKNOWN_ERROR					= "UNKNOWN_ERROR";

	//  THE DEFAULT STATUS, NO ERROR

	public final static int			OK								= 0;

	//  DATABASE ERRORS 50 TO 99

	public final static int			GET_DB_CONNECTION_ERROR			= 50;

	public final static int			RELEASE_DB_CONNECTION_ERROR		= 51;

	public final static int			POPULATE_DATA_ERROR				= 52;

	public final static int			RESULT_SET_CLOSE_ERROR			= 53;

	public final static int			STMT_CLOSE_ERROR				= 54;

	public final static int			SELECT_ERROR					= 55;

	public final static int			DELETE_ERROR					= 56;

	public final static int			ADVANCE_KEY_ERROR				= 57;

	public final static int			ROLLBACK_ERROR					= 58;

	public final static int			UPDATE_ERROR					= 59;

	//	AUTHENTICATION AND SESSION MGMT. 100 TO 199

	//E-Mail Error
	public final static int			MAIL_ERROR						= 1002;

	//E-Mail Constants
	public static final String		HELP_EMAIL_PARM					= "HELP_EMAIL";

	public static final String		MAIL_HOST_PARM					= "MAIL_HOST";

	public static final String		ADMIN_EMAIL_ADDRESS_PARM		= "ADMIN_EMAIL_ADDRESS";

	public static final String		ERROR_EMAIL_ADDRESS_PARM		= "ERROR_EMAIL_ADDRESS";

	public static final String		ERROR_SUBJECT_PARM				= "ERROR_SUBJECT";

	public static final String		ERROR_TEXT_MESSAGE_PARM			= "ERROR_TEXT_MESSAGE";

	public static final String		JSERV_LOG_FILE					= "jserv_log";

	public static final String []	requiredAppProps				=
																		{ "DB_URL_PARM" , "DB_USER_PARM" , "DB_PW_PARM" , "DB_MAX_CACHED_CONN_PARM" , "DB_CACHE_SCHEME_PARM" , "LOG_FILE_URI_PARM" , "HELP_EMAIL" , "MAIL_HOST" , "CONN_POOL_REFRESH_DELAY_PARM" , "CLIENT_ID" , "JSP_DIRECTORY" };

	public static final boolean		DB_SECURITY_ENABLED				= false;
}