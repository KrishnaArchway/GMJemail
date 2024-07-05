package com.archway.globalemail.email;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import com.archway.globalemail.common.ConfigurationServlet;
import com.archway.globalemail.common.Logger;
import com.archway.globalemail.util.Html;
import com.archway.globalemail.util.StringUtility;

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
 * File Name               : Email.java 
 * Package Name            : com.archway.globalemail.email
 * 
 * Date                    : Nov 10, 2005 - 12:23:56 PM
 * 
 * Change Date             : Jan 17, 2006 7:53:20 PM
 * 							 Feb 02, 2006 10:12 PM
 * 						     Feb 07, 2006 10:36 AM
 *                           Tuesday, May 16, 2006
 *                           May 30, 2006 12:10:22 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * Class to send emails's 
 */

public class Email
{

	//logger
	protected static final Logger	logger	= Logger.getLogger ( Email.class );

	/**
	 * Send text email with file attachment.
	 * 
	 * @param from
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param fileName
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static boolean SendEmailWithFileAttachment (
														String from ,
														String to[] ,
														String cc[] ,
														String bcc[] ,
														String subject ,
														String fileName[] ,
														String text )
																		throws Exception
	{
		logger.entering ( "SendEmailWithFileAttachment(String, String[], String [], String[], String,String[],String)" );
		boolean returnFlag = false;
		try
		{
			SmtpFinder sf = new SmtpFinder ();

			//       Create the email message
			MultiPartEmail email = new MultiPartEmail ();
			email.setHostName ( sf.getLocalSmtpServer () );
			email.setFrom ( from , from );
			email.setBounceAddress ( from );
			email.addReplyTo ( from , from );
			for ( int i = 0 ; i < to.length ; i++ )
			{
				email.addTo ( to [i] , to [i] );
			}
			if ( cc != null )
			{
				for ( int i = 0 ; i < cc.length ; i++ )
				{
					email.addCc ( cc [i] , cc [i] );
				}
			}
			if ( bcc != null )
			{
				for ( int i = 0 ; i < bcc.length ; i++ )
				{
					email.addBcc ( bcc [i] , bcc [i] );
				}
			}
			email.setSubject ( StringUtility.displayNoSubjectForBlank ( subject ) );
			email.setMsg ( text );

			for ( int i = 0 ; i < fileName.length ; i++ )
			{
				//Create the attachment
				EmailAttachment attachment = new EmailAttachment ();
				attachment.setPath ( fileName [i] );
				attachment.setDisposition ( EmailAttachment.ATTACHMENT );
				attachment.setDescription ( Splitter ( fileName [i] ) );
				attachment.setName ( "" );
				//add the attachment
				email.attach ( attachment );
			}

			email.setCharset ( "iso-8859-1" );
			//send the email
			email.send ();

			returnFlag = true;
		}
		catch ( Exception e )
		{
			logger.debug ( "Exception encountered Email.java " + e.getMessage () );
		}
		logger.exiting ( "SendEmailWithFileAttachment(String, String[], String [], String[], String,String[],String)" );
		return returnFlag;

	}

	/**
	 * Send Email with File Attachment and Body as HTML format.
	 * 
	 * @param from
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param fileName
	 * @param htmlText
	 * @return
	 */
	public static boolean SendHtmlEmailWithFileAttachment (
															String from ,
															String to[] ,
															String cc[] ,
															String bcc[] ,
															String subject ,
															String fileName[] ,
															String htmlText )
																				throws Exception
	{
		logger.entering ( "SendHtmlEmailWithFileAttachment(String, String[], String [], String[], String,String[],String)" );
		boolean returnFlag = false;
		try
		{
			SmtpFinder sf = new SmtpFinder ();
			//Create the email message
			HtmlEmail email = new HtmlEmail ();
			email.setHostName ( sf.getLocalSmtpServer () );
			email.setFrom ( from , from );
			email.setBounceAddress ( from );
			email.addReplyTo ( from , from );

			// START sets the emails address depending on which Database the email job is running againts.
			String ENVIRONMENT = ConfigurationServlet.getApplicationProperty ( "DB_URL_PARM" ).toUpperCase ();
			String emailAlwaysBcc = "";
			if ( ENVIRONMENT.indexOf ( "GMPRD" ) != - 1 )
			{
				email.addBcc ( ConfigurationServlet.getApplicationProperty ( "PROD_EMAIL_BCC_ALWAYS" ) );
			}

			if ( ENVIRONMENT.indexOf ( "GMDEV" ) != - 1 || ENVIRONMENT.indexOf ( "GMTST" ) != - 1 )
			{
				to = null;
				cc = null;
				bcc = null;
				to = new String[1];
				to [0] = ConfigurationServlet.getApplicationProperty ( "DEV_EMAIL_BCC_ALWAYS" );
				email.addBcc ( ConfigurationServlet.getApplicationProperty ( "DEV_EMAIL_BCC_ALWAYS" ) );
			}

			if ( ENVIRONMENT.indexOf ( "GMQA" ) != - 1 )
			{
				to = null;
				cc = null;
				bcc = null;
				to = new String[1];
				to [0] = ConfigurationServlet.getApplicationProperty ( "QA_EMAIL_BCC_ALWAYS" );
				email.addBcc ( ConfigurationServlet.getApplicationProperty ( "QA_EMAIL_BCC_ALWAYS" ) );
				email.addCc( ConfigurationServlet.getApplicationProperty ( "DEV_EMAIL_BCC_ALWAYS" ) );
			}

			//END sets the emails address depending on which Database the email job is running againts.

			for ( int i = 0 ; i < to.length ; i++ )
			{
				email.addTo ( to [i] , to [i] );
			}
			if ( cc != null )
			{
				for ( int i = 0 ; i < cc.length ; i++ )
				{
					email.addCc ( cc [i] , cc [i] );
				}
			}
			if ( bcc != null )
			{
				for ( int i = 0 ; i < bcc.length ; i++ )
				{
					email.addBcc ( bcc [i] , bcc [i] );
				}
			}
			email.setSubject ( StringUtility.displayNoSubjectForBlank ( subject ) );

			//Create the attachment if any....
			if ( fileName != null )
			{
				for ( int i = 0 ; i < fileName.length ; i++ )
				{
					if ( ! StringUtility.isStringBlank ( fileName [i] ) )
					{

						EmailAttachment attachment = new EmailAttachment ();
						attachment.setPath ( fileName [i] );
						attachment.setDisposition ( EmailAttachment.ATTACHMENT );
						attachment.setDescription ( Splitter ( fileName [i] ) );
						attachment.setName ( "" );
						//add the attachment
						email.attach ( attachment );
					}
				}
			}

			email.setCharset ( "iso-8859-1" );
			// set the html message
			email.setHtmlMsg ( Html.cleanHtml ( htmlText ) );
			//email.setHtmlMsg (  htmlText  );
			// set the alternative message
			email.setTextMsg ( "Your email client does not support HTML messages, Please Contact HelpDesk : " + from );
			// send the email
			email.send ();
			returnFlag = true;
		}
		catch ( Exception e )
		{
			logger.debug ( "Exception encountered Email.java " + e.getMessage () );
		}
		logger.exiting ( "SendHtmlEmailWithFileAttachment(String, String[], String [], String[], String,String[],String)" );
		return returnFlag;
	}

	/**
	 * send simple text email.
	 * 
	 * @param from
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public static boolean SendSimpleEmail (
											String from ,
											String to[] ,
											String cc[] ,
											String bcc[] ,
											String subject ,
											String text ) throws Exception
	{
		logger.entering ( "SendSimpleEmail(String, String[], String [], String[], String,String)" );
		boolean returnFlag = false;
		try
		{
			SmtpFinder sf = new SmtpFinder ();
			SimpleEmail email = new SimpleEmail ();
			email.setHostName ( sf.getLocalSmtpServer () );
			email.setFrom ( from , from );
			email.setBounceAddress ( from );
			email.addReplyTo ( from , from );
			for ( int i = 0 ; i < to.length ; i++ )
			{
				email.addTo ( to [i] , to [i] );
			}
			if ( cc != null )
			{
				for ( int i = 0 ; i < cc.length ; i++ )
				{
					email.addCc ( cc [i] , cc [i] );
				}
			}
			if ( bcc != null )
			{
				for ( int i = 0 ; i < bcc.length ; i++ )
				{
					email.addBcc ( bcc [i] , bcc [i] );
				}
			}

			email.setSubject ( StringUtility.displayNoSubjectForBlank ( subject ) );
			email.setMsg ( text );
			email.setCharset ( "iso-8859-1" );
			email.send ();
			returnFlag = true;
		}
		catch ( Exception e )
		{
			logger.debug ( "Exception encountered Email.java " + e.getMessage () );
		}
		logger.exiting ( "SendSimpleEmail(String, String[], String [], String[], String,String)" );
		return returnFlag;
	}

	/**
	 * returns just the file name from the path
	 * i.e. "c:/temp/temp/dummy.txt" will return "dummy.txt"
	 * 
	 * @param str
	 * @return
	 */
	public static String Splitter ( String str )
	{
		int maxLength = 0;

		if ( ! StringUtility.isStringBlank ( str ) )
		{
			//str=str.replaceAll("\\\","/");
			String [] temp = str.split ( "/" );
			maxLength = temp.length;
			return temp [maxLength - 1];
		}
		else
		{
			return "";
		}
	}

	/**
	 * Default Constructor.
	 *
	 */
	public Email ()
	{

	}

}