package com.archway.globalemail.newitemsetup;

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
 * File Name               : GemNewItemCreationEmail.java
 * Package Name            : com.archway.globalemail.newitemsetup
 *
 * Date                    : Oct 27, 2005 - 3:06:25 PM
 *
 * Change Date             : May 15, 2006 12:10:57 PM
 *                           May 18, 2006 4:05:02 PM
 *                           May 30, 2006 11:16:26 AM
 *                           May 30, 2006 12:30:37 PM
 * 							 May 31, 2006 10:14:59 PM
 *
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */
public class GemNewItemCreationEmail
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

		Logger logger = Logger.getLogger ( "newItemSetup" );

		DMLManager dm = new DMLManager ();
		EmailClient ec[] = dm.getEmailClientRecords ( "K" );
		System.out.println ( "\n\n\n" );
		System.out.println ( "********************** New Item Setup (Start) **********************" );
		logger.debug ( "********************** New Item Setup (Start) **********************" );
		for ( int i = 0 ; i < ec.length ; i++ )
		{
			logger.debug ( "Nomenclature id : " + ec [i].getNomenclature_id () );
			Nomenclature nc = dm.getNomenclatureDetails ( ec [i].getNomenclature_id () );
			EmailClientRecipient ecr[] = dm.getSpecialEmailClientRecipient ( ec [i].getNomenclature_id () , "K" );
			Email email = dm.setSpecialEmailMessageHeader ( ecr );
			email.setSubject ( ec [i].getSubject () );
			email.setFrom ( ec [i].getSender () );
			email = EmailFactory.contructNewItemSetupEmailBody ( ec [i].getPath_name () + ec [i].getFile_name () , nc , email );
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
			logger.warn ( "**************New Item Setup *****************" + ec [i].getNomenclature_id () );
		}
		System.out.println ( "********************** New Item Setup (End) **********************" );
		logger.warn ( "********************** New Item Setup (End) **********************" );
		//		for output in cron email need an RPT at beginning of line.
		System.out.println ( "\nRPT:\tNew Item Setup" );
		System.out.println ( "RPT:\tSuccessful/Rejected\t\t" + counterSent + " : " + counterNotSent + "\n" );

	}

	/**
	 * Default Constructor.
	 *
	 */
	public GemNewItemCreationEmail ()
	{

	}
}