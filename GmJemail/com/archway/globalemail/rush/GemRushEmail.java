package com.archway.globalemail.rush;

import java.io.IOException;
import java.sql.SQLException;

import com.archway.globalemail.common.Logger;
import com.archway.globalemail.email.DMLManager;
import com.archway.globalemail.email.EmailFactory;
import com.archway.globalemail.entity.Email;
import com.archway.globalemail.entity.EmailClient;
import com.archway.globalemail.entity.EmailClientRecipient;
import com.archway.globalemail.entity.Order;
import com.archway.globalemail.entity.OrderedItems;
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
 * File Name               : GemRushEmail.java
 * Package Name            : com.archway.globalemail.rush
 *
 * Date                    : Oct 27, 2005 - 3:07:00 PM
 *
 * Change Date             : May 15, 2006 2:03:58 PM
 * 							 May 18, 2006 4:05:02 PM
 *                           May 30, 2006 11:28:03 AM
 * 							 May 31, 2006 10:18:07 PM
 * 							 Jul 24, 2007 12:09:58 AM
 * 							 Aug 17, 2007 10:54:38 AM
 *
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */

public class GemRushEmail
{

	/**
	 * Logger "rush"
	 */
	public static Logger	logger	= Logger.getLogger ( "rush" );

	/**
	 * Main Method.
	 *
	 * @param args
	 * @throws IOException
	 * @throws SQLException
	 * @throws Exception
	 */
	public static void main ( String [] args )
												throws IOException ,
												SQLException ,
												Exception
	{

		boolean decideToSend = false;
		boolean transportFlag = false;
		boolean createRushFlag = false;
		int counterSent = 0;
		int counterNotSent = 0;

		DMLManager dm = new DMLManager ();

		createRushFlag = dm.CreateRushRecInEmailHistory ( "1" );

		EmailClient ec[] = dm.getEmailClientRecords ( "U" );
		System.out.println ( "\n\n\n" );
		System.out.println ( "********************** Rush (Start) **********************" );
		logger.debug ( "********************** Rush (Start) **********************" );
		for ( int i = 0 ; i < ec.length ; i++ )
		{

			EmailClientRecipient ecr[] = dm.getEmailClientRecipientRecords ( ec [i].getEmail_client_id () );
			Order order = dm.getOrderRecord ( ec [i].getClient () , ec [i].getOrder_number () );
			OrderedItems [] orderedItems = dm.getOrderItemsRecords ( ec [i].getClient () , ec [i].getOrder_number () );

			Email email = dm.setEmailMessageHeader ( ecr , order );
			email.setSubject ( ec [i].getSubject () );
			email.setFrom ( ec [i].getSender () );
			email = EmailFactory.contructRushEmailBody ( ec [i].getPath_name () + ec [i].getFile_name () , order , email , orderedItems );
			email = EmailFactory.validateEmailAddress ( email );
			
			decideToSend = EmailFactory.decideToSend ( email );

			if ( decideToSend )
			{
				transportFlag = EmailFactory.Transport ( ec [i].getOrder_number () , email );
				dm.updateEmailHistory ( ec [i].getEmail_history_id () , "Y" );
				System.out.println ( "Email Sent Rush Order Number : " + ec [i].getOrder_number () );
				logger.warn ( "Email Sent. Order Number = " + ec [i].getOrder_number () );
				logger.warn ( "From " + email.getFrom () );
				logger.warn ( "To " + email.getTo () );
				logger.warn ( "Cc " + email.getCc () );
				logger.warn ( "Bcc " + email.getBcc () );
				logger.warn ( "Subject " + email.getSubject () );
				logger.warn ( "Body " + StringUtility.displayEmailBodyMessage ( email.getBody () ) );
				logger.warn ( " " );
				counterSent++;
			}
			else
			{
				dm.updateEmailHistory ( ec [i].getEmail_history_id () , "R" );
				System.out.println ( "Email Not Sent Rush Order Number : " + ec [i].getOrder_number () );
				logger.warn ( "Email could not be sent lacking mandatory parameters. Order Number = " + ec [i].getOrder_number () );
				logger.warn ( "From " + email.getFrom () );
				logger.warn ( "To " + email.getTo () );
				logger.warn ( "Cc " + email.getCc () );
				logger.warn ( "Bcc " + email.getBcc () );
				logger.warn ( "Subject " + email.getSubject () );
				logger.warn ( "Body " + StringUtility.displayEmailBodyMessage ( email.getBody () ) );
				logger.warn ( " " );
				counterNotSent++;
			}
		}
		logger.debug ( "********************** Rush (End) **********************" );
		System.out.println ( "********************** Rush (End) **********************" );
		//		for output in cron email need an RPT at beginning of line.
		System.out.println ( "\nRPT:\tRush" );
		System.out.println ( "RPT:\tSuccessful/Rejected\t\t" + counterSent + " : " + counterNotSent + "\n" );

	}

	/**
	 * Default Constructor.
	 *
	 */
	public GemRushEmail ()
	{

	}

}