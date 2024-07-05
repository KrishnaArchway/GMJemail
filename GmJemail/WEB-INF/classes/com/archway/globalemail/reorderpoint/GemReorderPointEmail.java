package com.archway.globalemail.reorderpoint;

import com.archway.globalemail.common.Logger;
import com.archway.globalemail.email.DMLManager;
import com.archway.globalemail.email.EmailFactory;
import com.archway.globalemail.entity.Email;
import com.archway.globalemail.entity.EmailClient;
import com.archway.globalemail.entity.EmailClientRecipient;
import com.archway.globalemail.entity.ReorderPoint;
import com.archway.globalemail.util.StringUtility;

public class GemReorderPointEmail
{

	/**
	 * Main Method.
	 * @param args
	 */
	
	public static void main ( String [] args )
	{

		boolean decideToSend = false;
		boolean reorderPointFlag = false;
		boolean transportFlag = false;
		int counterSent = 0;
		int counterNotSent = 0;

		Logger logger = Logger.getLogger ( "reorderPoint" );

		System.out.println ( "\n\n\n" );
		System.out.println ( "********************** Reorder Point (Start) **********************" );
		logger.debug ( "********************** Reorder Point (Start) **********************" );
		DMLManager dm = new DMLManager ();

		reorderPointFlag = dm.CreateReorderPointRecInEmailHistory ( "1" );

		EmailClient ec[] = dm.getEmailClientRecords ( "R" );
		for ( int i = 0 ; i < ec.length ; i++ )
		{
			logger.warn ( "Nomenclature id : " + ec [i].getNomenclature_id () );
			ReorderPoint rp = dm.getReorderPointDetails ( ec [i].getNomenclature_id () );
			EmailClientRecipient ecr[] = dm.getSpecialEmailClientRecipient ( ec [i].getNomenclature_id () , "R" );
			Email email = dm.setSpecialEmailMessageHeader ( ecr );
			email.setSubject ( ec [i].getSubject () );
			email.setFrom ( ec [i].getSender () );

			email = EmailFactory.constructReorderPointEmailBody ( ec [i].getPath_name () + ec [i].getFile_name () , rp , email );
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
		System.out.println ( "********************** Reorder Point (End) **********************" );
		logger.debug ( "********************** Reorder Point (End) **********************" );
		//for output in cron email need an RPT at beginning of line.
		System.out.println ( "\nRPT:\tReorder Point" );
		System.out.println ( "RPT:\tSuccessful/Rejected\t\t" + counterSent + " : " + counterNotSent + "\n" );
	}

	/**
	 * Default Constructor.
	 *
	 */
	public GemReorderPointEmail ()
	{

	}
}

