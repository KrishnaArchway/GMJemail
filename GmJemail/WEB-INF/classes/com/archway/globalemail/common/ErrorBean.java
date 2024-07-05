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
 * File Name               : ErrorBean.java 
 * Package Name            : com.archway.globalemail.common
 * 
 * Date                    : Oct 27, 2005 - 2:57:41 PM
 * 
 * Change Date             : May 18, 2006 3:54:32 PM
 *                           May 30, 2006 11:59:01 AM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */

public class ErrorBean
{

	private String	strErrorCode;

	private String	strErrorMessage;

	/**
	 * Default Constructor.
	 *
	 */
	public ErrorBean ()
	{
	}

	public String getStrErrorCode ()
	{
		return strErrorCode;
	}

	public String getStrErrorMessage ()
	{
		return strErrorMessage;
	}

	public void setError ( String newStrErrorCode , String newStrErrorMessage )
	{
		setStrErrorCode ( newStrErrorCode );
		setStrErrorMessage ( newStrErrorMessage );
	}

	public void setStrErrorCode ( String newStrErrorCode )
	{
		strErrorCode = newStrErrorCode;
	}

	public void setStrErrorMessage ( String newStrErrorMessage )
	{
		strErrorMessage = newStrErrorMessage;
	}
}