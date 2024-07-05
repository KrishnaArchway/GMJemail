package com.archway.globalemail.order;

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
 * File Name               : GemOrderEmail.java
 * Package Name            : com.archway.globalemail.order
 *
 * Date                    : Oct 27, 2005 - 3:06:33 PM
 *
 * Change Date             : May 17, 2006 8:38:53 PM
 *                           Thursday, May 18, 2006
 *                           May 30, 2006 11:18:03 AM
 *                           May 30, 2006 12:32:17 PM
 * 							 May 30, 2006 12:34:17 PM
 * 							 May 31, 2006 10:15:46 PM
 * 							 Jun 19, 2007 4:17:45 PM
 * 							 Jan 28, 2008 10:23:28 AM
 *
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */

public class GemOrderEmail
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

		Logger logger = Logger.getLogger ( "orderEmail" );

		DMLManager dm = new DMLManager ();
		EmailClient ec[] = dm.getEmailClientRecords ( "O" );
		System.out.println ( "\n\n\n" );
		System.out.println ( "********************** Order (Start) **********************" );
		logger.debug ( "********************** Order (Start) **********************" );
		for ( int i = 0 ; i < ec.length ; i++ )
		{
			logger.warn ( "Order Number : " + ec [i].getOrder_number () );
			eFulFillmentFlag = false;
			EmailClientRecipient ecr[] = dm.getEmailClientRecipientRecords ( ec [i].getEmail_client_id () );
			Order order = dm.getOrderRecord ( ec [i].getClient () , ec [i].getOrder_number () );
			OrderedItems [] orderedItems = dm.getOrderItemsRecords ( ec [i].getClient () , ec [i].getOrder_number () );
			eFulFillmentFlag = dm.getEFulFillmentFlag ( orderedItems );
			Email email = dm.setEmailMessageHeader ( ecr , order );
			if(!StringUtility.isStringBlank(order.getBill_to_e_mail_cc()))
				email.setCc(order.getBill_to_e_mail_cc());
			
			
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

			/*
			 // code for SPO Rush Authorization request
			 if (ec[i].getPriority().equalsIgnoreCase("1") && ec[i].getPackslip_logo().equalsIgnoreCase("A"))
			 {
			 //send the rush email out.
			 GemRushEmail.doRushEmail(ec[i].getOrder_number());
			 }
			 // code for Over Limit Authorization Request
			 for (int x = 0; x < orderedItems.length; x++)
			 {
			 //System.out.println(orderedItems.length + " Over Limit from Order
			 // " + ec[i].getOrder_number() + " " +
			 // orderedItems[x].getNomenclature_id());
			 GemOverLimitEmail.doOverLimitEmail(ec[i].getOrder_number(), orderedItems[x].getNomenclature_id());
			 }*/

		}
		logger.debug ( "********************** Order (End) **********************" );
		System.out.println ( "********************** Order (End) **********************" );
		//		for output in cron email need an RPT at beginning of line.
		System.out.println ( "\nRPT:\tOrder" );
		System.out.println ( "RPT:\tSuccessful/Rejected\t\t" + counterSent + " : " + counterNotSent + "\n" );
	}

	/**
	 * Default Constructor.
	 *
	 */
	public GemOrderEmail ()
	{

	}
}