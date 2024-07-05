package com.archway.globalemail.barbilling;

import com.archway.globalemail.common.Logger;
import com.archway.globalemail.email.DMLManager;
import com.archway.globalemail.email.EmailFactory;
import com.archway.globalemail.entity.BillingNotification;
import com.archway.globalemail.entity.Email;
import com.archway.globalemail.entity.EmailClient;
import com.archway.globalemail.entity.EmailClientRecipient;
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
 * File Name               : GemBarsBillingNotification.java
 * Package Name            : com.archway.globalemail.barbilling
 *
 * Date                    : Feb 2, 2006 10:16:40 PM
 *
 * Change Date             : Thursday, May 18, 2006
 *                           May 30, 2006 11:08:45 AM
 *                           May 30, 2006 11:49:59 AM
 *                           May 31, 2006 10:00:47 PM
 *
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */
public class GemBarsBillingNotification
{

	/**
	 * Main Method.
	 *
	 * @param args
	 */
	public static void main ( String [] args )
	{
		Logger logger = Logger.getLogger ( "barBilling" );

		boolean decideToSend = false;
		boolean transportFlag = false;

		int counterSent = 0;
		int counterNotSent = 0;

		DMLManager dm = new DMLManager ();
		EmailClient emailClient[] = dm.getEmailClientRecords ( "B" );
		System.out.println ( "\n\n\n" );
		System.out.println ( "********************** Bars Billing Setup (Start) **********************" );
		logger.debug ( "********************** Bars Billing Setup (Start) **********************" );
		for ( int i = 0 ; i < emailClient.length ; i++ )
		{

			decideToSend = false;
			transportFlag = false;
			logger.warn ( "Invoice File id : " + emailClient [i].getInvoice_file_id () );
			EmailClientRecipient ecr[] = dm.getEmailClientRecipientRecords ( emailClient [i].getEmail_client_id () );
			BillingNotification bn = dm.getBillingNotificationRecords ( emailClient [i].getInvoice_file_id () );
			Email email = dm.setBarsEmailMessageHeader ( ecr );
			email.setSubject ( emailClient [i].getSubject () );
			email.setFrom ( emailClient [i].getSender () );
			email = EmailFactory.contructBarsBillingEmailBody ( emailClient [i].getPath_name () + emailClient [i].getFile_name () , bn , email );
			email = EmailFactory.validateEmailAddress ( email );
			decideToSend = EmailFactory.decideToSend ( email );
			if ( decideToSend )
			{
				transportFlag = EmailFactory.Transport ( "(Email_History_id) " + emailClient [i].getEmail_history_id () , email );
				dm.updateEmailHistory ( emailClient [i].getEmail_history_id () , "Y" );
				System.out.println ( "Email Sent Invoice File id : " + emailClient [i].getInvoice_file_id () );
				logger.warn ( "Email Sent." );
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
				System.out.println ( "Email Not Sent Invoice File id : " + emailClient [i].getInvoice_file_id () );
				logger.warn ( "Email could not be sent lacking mandatory parameters." );
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
		System.out.println ( "********************** Bars Billing Setup (End) **********************" );
		logger.debug ( "********************** Bars Billing Setup (End) **********************" );
		//for output in cron email need an RPT at beginning of line.
		System.out.println ( "\nRPT:\tBars Billing Setup" );
		System.out.println ( "RPT:\tSuccessful/Rejected\t\t" + counterSent + " : " + counterNotSent + "\n" );

	}
}