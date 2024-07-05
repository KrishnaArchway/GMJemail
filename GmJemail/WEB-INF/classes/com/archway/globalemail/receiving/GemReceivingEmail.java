package com.archway.globalemail.receiving;

import com.archway.globalemail.common.Logger;
import com.archway.globalemail.email.DMLManager;
import com.archway.globalemail.email.EmailFactory;
import com.archway.globalemail.entity.Email;
import com.archway.globalemail.entity.EmailClient;
import com.archway.globalemail.entity.EmailClientRecipient;
import com.archway.globalemail.entity.Receiving;
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
 * File Name               : GemReceivingEmail.java
 * Package Name            : com.archway.globalemail.receiving
 *
 * Date                    : Oct 27, 2005 - 3:06:53 PM
 *
 * Change Date             : Jan 15, 2006 5:21:24 PM
 * 							 Jan 16, 2006 11:13:34 AM
 * 							 Jan 17, 2006
 * 							 Mar 13, 2006 10:18:40 AM
 * 			                 May 18, 2006 4:05:02 PM
 * 							 May 19, 2006 3:26:26 PM
 *                           May 30, 2006 11:23:04 AM
 * 							 May 31, 2006 10:17:39 PM
 *
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */

public class GemReceivingEmail
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

		Logger logger = Logger.getLogger ( "inventoryReceiving" );

		DMLManager dm = new DMLManager ();
		EmailClient ec[] = dm.getEmailClientRecords ( "R" );
		System.out.println ( "\n\n\n" );
		logger.debug ( "********************** Inventory Receiving (Start) **********************" );
		System.out.println ( "********************** Inventory Receiving (Start) **********************" );
		for ( int i = 0 ; i < ec.length ; i++ )
		{

			Receiving rc = dm.getReceivingDetails ( ec [i].getClient () , ec [i].getNomenclature_id () , ec [i].getTrans_id () );
			logger.warn ( "NomenClature id  " + ec [i].getNomenclature_id () );
			EmailClientRecipient ecr[] = dm.getSpecialEmailClientRecipient ( ec [i].getNomenclature_id () , "R" );
			Email email = dm.setSpecialEmailMessageHeader ( ecr );
			email.setSubject ( ec [i].getSubject () );
			email.setFrom ( ec [i].getSender () );

			email = EmailFactory.contructReceivingEmailBody ( ec [i].getPath_name () + ec [i].getFile_name () , rc , email );
			email = EmailFactory.validateEmailAddress ( email );

			decideToSend = EmailFactory.decideToSend ( email );

			//added this if the gm_email_api_pkg.estore_receiving return null
			//then do not send the emial out.
			if ( StringUtility.isStringBlank ( rc.getStock_number () ) )
			{
				decideToSend = false;
			}

			if ( decideToSend )
			{
				transportFlag = EmailFactory.Transport ( ec [i].getNomenclature_id () , email );
				dm.updateEmailHistoryReceiving ( ec [i].getNomenclature_id () , "Y" , ec [i].getClient () );
				System.out.println ( "Email Sent. NomenclatureID  : " + ec [i].getNomenclature_id () );
				logger.warn ( "Email Sent. NomanclatureId = " + ec [i].getNomenclature_id () );
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
				dm.updateEmailHistoryReceiving ( ec [i].getNomenclature_id () , "R" , ec [i].getClient () );
				System.out.println ( "Email Not Sent. NomenclatureID : " + ec [i].getNomenclature_id () );
				logger.warn ( "Email could not be sent lacking mandatory parameters. NomenclatureID = " + ec [i].getNomenclature_id () );
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
		logger.debug ( "********************** Inventory Receiving (End) **********************" );
		System.out.println ( "********************** Inventory Receiving (End) **********************" );
		//		for output in cron email need an RPT at beginning of line.
		System.out.println ( "\nRPT:\tInventory Receiving" );
		System.out.println ( "RPT:\tSuccessful/Rejected\t\t" + counterSent + " : " + counterNotSent + "\n" );

	}

	/**
	 * Default Constructor.
	 *
	 */
	public GemReceivingEmail ()
	{

	}

}