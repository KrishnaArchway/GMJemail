package com.archway.globalemail.backorder;

import com.archway.globalemail.common.Logger;
import com.archway.globalemail.email.DMLManager;
import com.archway.globalemail.email.EmailFactory;
import com.archway.globalemail.entity.BackOrder;
import com.archway.globalemail.entity.Email;
import com.archway.globalemail.entity.EmailClient;
import com.archway.globalemail.entity.EmailClientRecipient;
import com.archway.globalemail.util.StringUtility;

/**
 * Archway Marketing Services.
 * 7525 Cogswell Rd,
 * Romulus, MI - 48174.
 * Phone - 734.713.2000
 * 
 * Project Name            : gmJemail
 * 
 * Author                  : bhattam0 - Amar Bhatt Email:(AMAR_BHATT@ARCHWAY.COM) Ext:(2019)
 * File Name               : GemBackOrderEmail.java 
 * Package Name            : com.archway.globalemail.backorder
 * 
 * Date                    : Jun 19, 2007 1:16:35 PM
 * 
 * Change Date             : Jun 19, 2007 1:16:35 PM
 * 							 Jun 19, 2007 2:56:32 PM
 * 							 Jun 19, 2007 4:03:40 PM
 * 							 Jun 26, 2007 9:37:14 AM
 * 							 Jul 17, 2007 5:08:53 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */

public class GemBackOrderEmail
{

	/**
	 * @param args
	 */
	public static void main ( String [] args )
	{
		//logger
		Logger logger = Logger.getLogger ( GemBackOrderEmail.helperContants.LOGGER_NAME );

		//variables Declaration
		boolean decideToSend = false;
		boolean backOrderFlag = false;
		boolean transportFlag = false;
		int counterSent = 0;
		int counterNotSent = 0;

		//messages
		System.out.println ( "\n\n\n" );
		System.out.println ( "********************** BackOrder (Start) **********************" );
		logger.debug ( "********************** Backorder (Start) **********************" );

		//database object manager
		DMLManager dm = new DMLManager ();

		//create the backorder email by calling GM_EMAIL_API_PKG.CREATE_BACKORDER_DATA
		backOrderFlag = dm.CreateBackOrderRecInEmailHistory ( GemBackOrderEmail.helperContants.CLIENT_ID );

		EmailClient ec[] = dm.getEmailClientRecords ( GemBackOrderEmail.helperContants.EMAIL_TYPE );
		for ( int i = 0 ; i < ec.length ; i++ )
		{
			logger.warn ( "Nomenclature id : " + ec [i].getNomenclature_id () );
			//create backorder object
			BackOrder bo = dm.getBackOrderDetails ( GemBackOrderEmail.helperContants.CLIENT_ID,ec [i].getNomenclature_id () );
			//create the Email CLient recipient object
			EmailClientRecipient ecr[] = dm.getSpecialEmailClientRecipient ( ec [i].getNomenclature_id () , GemBackOrderEmail.helperContants.EMAIL_TYPE );

			//set all the header for email .
			Email email = dm.setSpecialEmailMessageHeader ( ecr );
			email.setSubject ( ec [i].getSubject () );
			email.setFrom ( ec [i].getSender () );

			//construct the email body.
			email = EmailFactory.contructBackOrderEmailBody ( ec [i].getPath_name () + ec [i].getFile_name () , bo , email );
			//validate the email address 
			email = EmailFactory.validateEmailAddress ( email );
			//decide to send the emials.
			decideToSend = EmailFactory.decideToSend ( email );
			
			if(StringUtility.isStringBlank(bo.getStock_number()))
			{
				decideToSend=false;
			}
			
			
			if ( decideToSend )
			{
				//send the emial out.
				transportFlag = EmailFactory.Transport ( ec [i].getNomenclature_id () , email );
				//update the email history table. and make it as processed.
				dm.updateEmailHistory ( ec [i].getEmail_history_id () , GemBackOrderEmail.helperContants.SUCCESS_EMAIL_FLAG );
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
				//update the email history table. and mark it as not processed.
				dm.updateEmailHistory ( ec [i].getEmail_history_id () , GemBackOrderEmail.helperContants.REJECT_EMAIL_FLAG );
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
		System.out.println ( "********************** BackOrder (End) **********************" );
		logger.debug ( "********************** BackOrder (End) **********************" );

		//for output in cron email need an RPT at beginning of line.
		System.out.println ( "\nRPT:\tBackOrder Emails" );
		System.out.println ( "RPT:\tSuccessful/Rejected\t\t" + counterSent + " : " + counterNotSent + "\n" );
	}

	class helperContants
	{

		public static final String	CLIENT_ID			= "1";

		public static final String	EMAIL_TYPE			= "E";

		public static final String	LOGGER_NAME			= "backorder";

		public static final String	SUCCESS_EMAIL_FLAG	= "Y";

		public static final String	REJECT_EMAIL_FLAG	= "R";

	}

	/**
	 * Default Constructor.
	 * 
	 *
	 */
	public GemBackOrderEmail ()
	{

	}
}
