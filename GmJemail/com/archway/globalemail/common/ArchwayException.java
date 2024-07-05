package com.archway.globalemail.common;

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
 * File Name               : ArchwayException.java 
 * Package Name            : com.archway.globalemail.common
 * 
 * Date                    : Feb 4, 2006 9:23:56 AM
 * 
 * Change Date             : May 18, 2006 3:56:25 PM
 *                           May 30, 2006 11:53:33 AM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */
public class ArchwayException extends Exception
{

	private static Category	cat		= null;

	private int				_iErrorCode;

	private String			_strErrorMessage;

	/*static
	 {
	 if (!LogConfig.isLogging())
	 {
	 LogConfig.initLogging();
	 }
	 cat = Category.getInstance(com.archway.globalemail.common.ArchwayException.class);
	 }*/
	Logger					logger	= Logger.getLogger ( ArchwayException.class );

	/**
	 * Creates a <code>ArchwayException</code> object.
	 * 
	 * @param e
	 *        the exception.
	 * @param errorCode
	 *        the application associated error code.
	 */

	public ArchwayException ( String errorMessage , int errorCode )
	{

		super ( errorMessage + " Error Code=" + errorCode );

		this._strErrorMessage = errorMessage;

		this._iErrorCode = errorCode;

		cat.info ( errorMessage + " Error Code=" + errorCode );

	}

	/**
	 * Gets the parameter name.
	 * 
	 * @returns the parameter name.
	 */

	public int getErrorCode ()
	{

		return _iErrorCode;

	}

	/**
	 * Gets the parameter name.
	 * 
	 * @returns the parameter name.
	 */

	public String getErrorMessage ()
	{

		return _strErrorMessage;

	}
}