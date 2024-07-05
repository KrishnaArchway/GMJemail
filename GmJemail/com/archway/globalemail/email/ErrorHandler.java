package com.archway.globalemail.email;

import com.archway.globalemail.common.ConfigurationServlet;
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
 * File Name               : ErrorHandler.java 
 * Package Name            : com.archway.globalemail.email
 * 
 * Date                    : Oct 27, 2005 - 2:59:10 PM
 * 
 * Change Date             : May 18, 2006 3:57:09 PM
 *                           May 30, 2006 12:15:00 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */

public class ErrorHandler
{

	//logger
	private static Logger	logger	= Logger.getLogger ( ErrorHandler.class );

	/**
	 * 
	 * @param notifyWhat
	 */
	public static void NotifyError ( String notifyWhat )
	{
		String errorMessage = ConfigurationServlet.getApplicationProperty ( notifyWhat );
		logger.warn ( errorMessage );
	}

}