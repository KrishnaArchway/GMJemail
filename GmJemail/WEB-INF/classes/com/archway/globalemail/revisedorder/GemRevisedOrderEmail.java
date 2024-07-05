package com.archway.globalemail.revisedorder;

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
 * File Name               : GemRevisedOrderEmail.java 
 * Package Name            : com.archway.globalemail.revisedorder
 * 
 * Date                    : Jan 23, 2008 3:41:19 PM
 * 
 * Change Date             : Jan 23, 2008 3:41:19 PM
 * 							 Jan 23, 2008 3:50:19 PM
 * 							 Jan 28, 2008 10:33:20 AM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */

public class GemRevisedOrderEmail 
{

	// final variables.
	public static final String	DWD_MEDIA_ID_E_STORE	= "E-STORE";

	public static final String	DWD_MEDIA_ID_EMAIL		= "EMAIL";

	public static final String	DWD_MEDIA_ID_FAX		= "FAX";

	public static final String	DWD_MEDIA_ID_MAIL		= "MAIL";

	public static final String	DWD_MEDIA_ID_PHONE		= "PHONE";

	public static final String	SATURN_MEDIA_ID			= "SATURN";

	/**
	 * main method.
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
		boolean eFulFillmentFlag = false;
		int counterSent = 0;
		int counterNotSent = 0;

		Logger logger = Logger.getLogger ( "revisedOrderEmail" );

		DMLManager dm = new DMLManager ();
		EmailClient ec[] = dm.getEmailClientRecords ( "F" );
		System.out.println ( "\n\n\n" );
		System.out.println ( "********************** Revised Order (Start) **********************" );
		logger.debug ( "********************** Revised Order (Start) **********************" );
		for ( int i = 0 ; i < ec.length ; i++ )
		{
			logger.warn ( "Order Number : " + ec [i].getOrder_number () );
			eFulFillmentFlag = false;
			EmailClientRecipient ecr[] = dm.getEmailClientRecipientRecords ( ec [i].getEmail_client_id () );
			Order order = dm.getOrderRecord ( ec [i].getClient () , ec [i].getOrder_number () );
			OrderedItems [] orderedItems = dm.getOrderItemsRecords ( ec [i].getClient () , ec [i].getOrder_number () );
			eFulFillmentFlag = dm.getEFulFillmentFlag ( orderedItems );
			Email email = dm.setEmailMessageHeader ( ecr , order );
			email.setSubject ( ec [i].getSubject () );
			email.setFrom ( ec [i].getSender () );
			email = EmailFactory.contructOrderEmailBody ( ec [i].getPath_name () + ec [i].getFile_name () , order , orderedItems , email , eFulFillmentFlag , ec [i].getClient () );
					
			email = EmailFactory.validateEmailAddress ( email );
			decideToSend = EmailFactory.decideToSend ( email );
			if ( decideToSend )
			{
				transportFlag = EmailFactory.Transport ( ec [i].getOrder_number () , email );
				dm.updateEmailHistory ( ec [i].getEmail_history_id () , "Y" );
				System.out.println ( "Email Sent Order Number : " + ec [i].getOrder_number () );
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
				System.out.println ( "Email Not Sent Order Number : " + ec [i].getOrder_number () );
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
		logger.debug ( "********************** Revised Order (End) **********************" );
		System.out.println ( "********************** Revised Order (End) **********************" );
		//		for output in cron email need an RPT at beginning of line.
		System.out.println ( "\nRPT:\tRevised Order" );
		System.out.println ( "RPT:\tSuccessful/Rejected\t\t" + counterSent + " : " + counterNotSent + "\n" );
	}

	/**
	 * Default Constructor.
	 *
	 */
	public GemRevisedOrderEmail ()
	{

	}
}