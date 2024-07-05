package com.archway.globalemail.scrap;

import com.archway.globalemail.common.Logger;
import com.archway.globalemail.email.DMLManager;
import com.archway.globalemail.email.EmailFactory;
import com.archway.globalemail.entity.Email;
import com.archway.globalemail.entity.EmailClient;
import com.archway.globalemail.entity.EmailClientRecipient;
import com.archway.globalemail.entity.Nomenclature;
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
 * File Name               : GemScrapEmail.java
 * Package Name            : com.archway.globalemail.scrap
 *
 * Date                    : Oct 27, 2005 - 3:07:07 PM
 *
 * Change Date             : Friday, May 12, 2006
 *                           Monday, May 15, 2006
 *                           May 18, 2006 4:05:02 PM
 *                           May 30, 2006 11:30:00 AM
 * 							 May 31, 2006 10:18:40 PM
 *
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */

public class GemScrapEmail
{

	/**
	 * Main Method.
	 *
	 * @param args
	 */
	public static void main ( String [] args )
	{

		boolean decideToSend = false;
		boolean transportFlag = false;
		int counterSent = 0;
		int counterNotSent = 0;

		//Logger logger = Logger.getLogger ( "scrap" );

		DMLManager dm = new DMLManager ();
		EmailClient ec[] = dm.getEmailClientRecords ( "W" );
		System.out.println ( "\n\n\n" );
		System.out.println ( "********************** Scrap (Start) **********************" );
		//logger.debug ( "********************** Scrap (Start) **********************" );
		for ( int i = 0 ; i < ec.length ; i++ )
		{
			//logger.debug ( "Nomenclature id : " + ec [i].getNomenclature_id () );
			Nomenclature nc = dm.getNomenclatureDetails ( ec [i].getNomenclature_id () );
			EmailClientRecipient ecr[] = dm.getSpecialEmailClientRecipient ( ec [i].getNomenclature_id () , "W" );
			Email email = dm.setSpecialEmailMessageHeader ( ecr );
			email.setSubject ( ec [i].getSubject () );
			email.setFrom ( ec [i].getSender () );
			email = EmailFactory.contructScrapEmailBody ( ec [i].getPath_name () + ec [i].getFile_name () , nc , email );
			email = EmailFactory.validateEmailAddress ( email );
			decideToSend = EmailFactory.decideToSend ( email );
			if ( decideToSend )
			{
				transportFlag = EmailFactory.Transport ( ec [i].getNomenclature_id () , email );
				dm.updateEmailHistory ( ec [i].getEmail_history_id () , "Y" );
				System.out.println ( "Email Sent Nomenclature id : " + ec [i].getNomenclature_id () );
				/*
				 * logger.debug ( "Email Sent." ); logger.debug ( "From " + email.getFrom () );
				 * logger.debug ( "To " + email.getTo () ); logger.debug ( "Cc " + email.getCc
				 * () ); logger.debug ( "Bcc " + email.getBcc () ); logger.debug ( "Subject " +
				 * email.getSubject () ); logger.debug ( "Body " +
				 * StringUtility.displayEmailBodyMessage ( email.getBody () ) ); logger.warn (
				 * " " );
				 */
				counterSent++;
			}
			else
			{
				dm.updateEmailHistory ( ec [i].getEmail_history_id () , "R" );
				/*
				 * logger.debug ( "Email could not be sent lacking mandatory parameters." );
				 * logger.debug ( "From " + email.getFrom () ); logger.debug ( "To " +
				 * email.getTo () ); logger.debug ( "Cc " + email.getCc () ); logger.debug (
				 * "Bcc " + email.getBcc () ); logger.debug ( "Subject " + email.getSubject ()
				 * ); logger.debug ( "Body " + StringUtility.displayEmailBodyMessage (
				 * email.getBody () ) ); logger.warn ( " " );
				 */
				counterNotSent++;
			}

		}
		//logger.debug ( "********************** Scrap (End) **********************" );
		System.out.println ( "********************** Scrap (End) **********************" );
		//		for output in cron email need an RPT at beginning of line.
		System.out.println ( "\nRPT:\tScrap" );
		System.out.println ( "RPT:\tSuccessful/Rejected\t\t" + counterSent + " : " + counterNotSent + "\n" );

	}

	/**
	 * Default Constructor.
	 *
	 */
	public GemScrapEmail ()
	{

	}
}