package com.archway.globalemail.email;

import java.net.InetAddress;

import com.archway.globalemail.common.ConfigurationServlet;

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
 * File Name               : SmtpFinder.java 
 * Package Name            : com.archway.globalemail.email
 * 
 * Date                    : Nov 10, 2005 - 12:23:37 PM
 * 
 * Change Date             : Mar 17, 2006 9:18:10 AM
 *                           Monday, March 27, 2006
 * 			                 May 18, 2006 3:57:31 PM
 *                           May 30, 2006 12:19:38 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */

public class SmtpFinder
{

	/**
	 * Default Constructor.
	 *
	 */

	public SmtpFinder ()
	{

	}

	/**
	 * Get's the local server name. for relaying emails via SMTP.
	 * 
	 * @return
	 */
	public String getLocalSmtpServer ()
	{
		return ConfigurationServlet.getApplicationProperty ( "SMTP_SERVER_NAME" );
	}

	/**
	 * Get's the local server name.
	 * 
	 */
	public String getServerName () throws Exception
	{
		String host = "";
		InetAddress address = InetAddress.getLocalHost ();
		host = address.getHostName ();
		return host;
	}

}