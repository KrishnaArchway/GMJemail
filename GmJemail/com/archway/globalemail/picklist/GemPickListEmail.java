package com.archway.globalemail.picklist;

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
 * File Name               : GemPickListEmail.java
 * Package Name            : com.archway.globalemail.picklist
 *
 * Date                    : Oct 27, 2005 - 3:06:46 PM
 *
 * Change Date             : Jan 24, 2006 11:58:17 AM
 *                           Monday, March 13, 2006
 *                           Tuesday, March 14, 2006
 *                           Thursday, March 16, 2006
 *                           Friday, March 17, 2006
 *                           Monday, March 27, 2006
 *                           Tuesday, March 28, 2006
 *                           May 18, 2006 4:05:02 PM
 *                           May 30, 2006 11:21:35 AM
 *                           May 30, 2006 12:33:04 PM
 *                           May 31, 2006 2:46:36 PM
 * 							 May 31, 2006 10:17:06 PM
 * 						     Jun 26, 2006 8:42:24 PM
 *                           Jun 27, 2006 10:02:50 AM
 *
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * This class send out POD (Print On Demand) Emails.
 */

public class GemPickListEmail
{

	/**
	 * Main Method.
	 *
	 * @param args
	 */
	public static void main ( String [] args )
	{
		Logger logger = Logger.getLogger ( "pickList" );

		boolean decideToSend = false;
		boolean transportFlag = false;
		int counterSent = 0;
		int counterNotSent = 0;

		DMLManager dm = new DMLManager ();
		EmailClient emailClient[] = dm.getEmailClientRecords ( "D" );
		System.out.println ( "\n\n\n" );
		System.out.println ( "********************** Pick List (Start) **********************" );
		logger.debug ( "********************** Pick List (Start) **********************" );
		for ( int i = 0 ; i < emailClient.length ; i++ )
		{

			decideToSend = false;
			transportFlag = false;
			EmailClientRecipient ecr[] = dm.getEmailClientRecipientRecords ( emailClient [i].getEmail_client_id () );
			Order order = dm.getPickListOrderRecords ( emailClient [i].getClient () , emailClient [i].getOrder_number () );
			OrderedItems orderedItems[] = dm.getPickListOrderedItemsRecords ( emailClient [i].getClient () , emailClient [i].getOrder_number () );
			Email email = dm.setPickListEmailMessageHeader ( ecr );
			email.setSubject ( emailClient [i].getSubject () );
			email.setFrom ( emailClient [i].getSender () );

			email = EmailFactory.contructPickListEmailBody ( emailClient [i].getPath_name () + emailClient [i].getFile_name () , order , orderedItems , email );
			
			email = EmailFactory.validateEmailAddress ( email );
			decideToSend = EmailFactory.decideToSend ( email );
			if ( decideToSend )
			{
				transportFlag = EmailFactory.Transport ( emailClient [i].getOrder_number () , email );
				dm.updateEmailHistory ( emailClient [i].getEmail_history_id () , "Y" );
				System.out.println ( "Email Sent Order Number : " + emailClient [i].getOrder_number () );
				logger.warn ( "Email Sent Order Number : " + emailClient [i].getOrder_number () );
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
				dm.updateEmailHistory ( emailClient [i].getEmail_history_id () , "R" );
				System.out.println ( "Email Not Sent Order Number : " + emailClient [i].getOrder_number () );
				logger.warn ( "Email could not be sent lacking mandatory parameters. " + emailClient [i].getOrder_number () );
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
		logger.debug ( "********************** Pick List (End) **********************" );
		System.out.println ( "********************** Pick List (End) **********************" );
		//		for output in cron email need an RPT at beginning of line.
		System.out.println ( "\nRPT:\tPick List" );
		System.out.println ( "RPT:\tSuccessful/Rejected\t\t" + counterSent + " : " + counterNotSent + "\n" );
	}

	/**
	 * Default Constructor.
	 *
	 */
	public GemPickListEmail ()
	{

	}

}