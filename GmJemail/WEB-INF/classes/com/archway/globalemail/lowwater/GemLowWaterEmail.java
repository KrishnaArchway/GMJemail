package com.archway.globalemail.lowwater;

import com.archway.globalemail.common.Logger;
import com.archway.globalemail.email.DMLManager;
import com.archway.globalemail.email.EmailFactory;
import com.archway.globalemail.entity.Email;
import com.archway.globalemail.entity.EmailClient;
import com.archway.globalemail.entity.EmailClientRecipient;
import com.archway.globalemail.entity.LowWater;
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
 * File Name               : GemLowWaterEmail.java
 * Package Name            : com.archway.globalemail.lowwater
 *
 * Date                    : Oct 27, 2005 - 3:06:17 PM
 *
 * Change Date             : May 18, 2006 4:05:02 PM
 *                           May 30, 2006 11:15:19 AM
 *                           May 30, 2006 12:29:44 PM
 * 							 May 31, 2006 10:14:49 PM
 * 							 Jun 6, 2006 6:46:34 AM
 * 							 Aug 3, 2007 1:29:09 AM
 *
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */

public class GemLowWaterEmail
{

	/**
	 * Main Method.
	 * @param args
	 */
	public static void main ( String [] args )
	{

		boolean decideToSend = false;
		boolean lowWaterFlag = false;
		boolean transportFlag = false;
		int counterSent = 0;
		int counterNotSent = 0;

		Logger logger = Logger.getLogger ( "lowWater" );

		System.out.println ( "\n\n\n" );
		System.out.println ( "********************** Low Water (Start) **********************" );
		logger.debug ( "********************** Low Water (Start) **********************" );
		DMLManager dm = new DMLManager ();

		lowWaterFlag = dm.CreateLowWaterRecInEmailHistory ( "1" );

		EmailClient ec[] = dm.getEmailClientRecords ( "L" );
		for ( int i = 0 ; i < ec.length ; i++ )
		{
			logger.warn ( "Nomenclature id : " + ec [i].getNomenclature_id () );
			LowWater lw = dm.getLowWaterDetails ( ec [i].getNomenclature_id () );
			EmailClientRecipient ecr[] = dm.getSpecialEmailClientRecipient ( ec [i].getNomenclature_id () , "L" );
			Email email = dm.setSpecialEmailMessageHeader ( ecr );
			email.setSubject ( ec [i].getSubject () );
			email.setFrom ( ec [i].getSender () );

			email = EmailFactory.contructLowWaterEmailBody ( ec [i].getPath_name () + ec [i].getFile_name () , lw , email );
			email = EmailFactory.validateEmailAddress ( email );
			decideToSend = EmailFactory.decideToSend ( email );
			if ( decideToSend )
			{
				transportFlag = EmailFactory.Transport ( ec [i].getNomenclature_id () , email );
				dm.updateEmailHistory ( ec [i].getEmail_history_id () , "Y" );
				System.out.println ( "Email Sent Nomenclature id : " + ec [i].getNomenclature_id () );
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
				dm.updateEmailHistory ( ec [i].getEmail_history_id () , "R" );
				System.out.println ( "Email Not Sent Nomenclature id : " + ec [i].getNomenclature_id () );
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
		System.out.println ( "********************** Low Water (End) **********************" );
		logger.debug ( "********************** Low Water (End) **********************" );
		//for output in cron email need an RPT at beginning of line.
		System.out.println ( "\nRPT:\tLow Water" );
		System.out.println ( "RPT:\tSuccessful/Rejected\t\t" + counterSent + " : " + counterNotSent + "\n" );
	}

	/**
	 * Default Constructor.
	 *
	 */
	public GemLowWaterEmail ()
	{

	}
}