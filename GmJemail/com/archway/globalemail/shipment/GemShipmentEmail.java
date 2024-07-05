package com.archway.globalemail.shipment;

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
import com.archway.globalemail.entity.Packing;
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
 * File Name               : GemShipmentEmail.java
 * Package Name            : com.archway.globalemail.shipment
 *
 * Date                    : Oct 27, 2005 - 3:07:14 PM
 *
 * Change Date             : Jan 26, 2006 11:11:45 AM
 *                           Wednesday, May 17, 2006
 *                           Thursday, May 18, 2006
 *                           May 18, 2006 4:05:02 PM
 *                           May 30, 2006 11:34:44 AM
 * 			                 May 31, 2006 10:19:09 PM
 * 							 Oct 12, 2006 9:36:07 AM
 * 							 Jan 28, 2008 10:23:35 AM
 * 							 Dec 1, 2009 1:03:25 PM
 *
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * Send Out Shipments Emails.
 */

public class GemShipmentEmail
{
	// final variables.
	public static final String	DWD_MEDIA_ID_E_STORE	= "E-STORE";

	public static final String	DWD_MEDIA_ID_EMAIL		= "EMAIL";

	public static final String	DWD_MEDIA_ID_FAX		= "FAX";

	public static final String	DWD_MEDIA_ID_MAIL		= "MAIL";

	public static final String	DWD_MEDIA_ID_PHONE		= "PHONE";

	public static final String	SATURN_MEDIA_ID			= "SATURN";

	/**
	 * Main Method.
	 *
	 * @param args
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void main ( String [] args )
												throws IOException ,
												SQLException
	{
		boolean decideToSend = false;
		boolean transportFlag = false;
		int counterSent = 0;
		int counterNotSent = 0;
		boolean staurnSendEmailFlag=false;
		
		Logger logger = Logger.getLogger ( "shipmentEmail" );

		DMLManager dm = new DMLManager ();
		EmailClient ec[] = dm.getEmailClientRecords ( "S" );
		System.out.println ( "\n\n\n" );
		System.out.println ( "********************** Order Shipment (Start) **********************" );
		logger.debug ( "********************** Order Shipment (Start) **********************" );
		for ( int i = 0 ; i < ec.length ; i++ )
		{
			EmailClientRecipient ecr[] = dm.getEmailClientRecipientRecords ( ec [i].getEmail_client_id () );
			Order order = dm.getOrderRecord ( ec [i].getClient () , ec [i].getOrder_number () );
			OrderedItems [] orderedItems = dm.getOrderItemsRecords ( ec [i].getClient () , ec [i].getOrder_number () );
			Packing packing = dm.getPackingShipmentRecord ( ec [i].getClient () , ec [i].getOrder_number () );
			Email email = dm.setEmailMessageHeader ( ecr , order );
			
			if(!StringUtility.isStringBlank(order.getShip_to_e_mail_cc()))
				email.setCc(order.getShip_to_e_mail_cc());
			
			
			email.setSubject ( ec [i].getSubject () );
			email.setFrom ( ec [i].getSender () );
			
			/*System.out.println("FIle Name "+ec [i].getPath_name () + ec [i].getFile_name ());
			System.out.println("Order "+order.getBill_to_email() + " "+order.getShip_to_e_mail());
			System.out.println("orderedItems  "+orderedItems.length);
			System.out.println("packing  "+packing.getActual_carrier());*/
			
			email = EmailFactory.contructShipmentEmailBody ( ec [i].getPath_name () + ec [i].getFile_name () , order , orderedItems , email , packing );
			
			email = EmailFactory.validateEmailAddress ( email );
			decideToSend = EmailFactory.decideToSend ( email );
			
			//System.out.println("decideToSend  before "+decideToSend);
			
			//added just for staurn email.
			if(order.getMedia_id().equalsIgnoreCase(SATURN_MEDIA_ID))
			{
				
				for (int x=0;x<orderedItems.length;x++)
				{
					if(orderedItems[x].getQuantity_shipped().equalsIgnoreCase("0") )
					{
						//System.out.println("Qty "+orderedItems[x].getQuantity_shipped());
						staurnSendEmailFlag=false;
					}
					else
					{
						//System.out.println("Qty ~   "+orderedItems[x].getQuantity_shipped());
						if(decideToSend)
						{
							staurnSendEmailFlag=true;
							break;
						}
						else
						{
							staurnSendEmailFlag=false;
						}
					}
				}
				decideToSend=staurnSendEmailFlag;
			}
	
			//System.out.println("decideToSend  after "+decideToSend);
			
			if ( decideToSend )
			{

				transportFlag = EmailFactory.Transport ( ec [i].getOrder_number () , email );
				dm.updateEmailHistory ( ec [i].getEmail_history_id () , "Y" );
				System.out.println ( "Email Sent Order Number : " + ec [i].getOrder_number () );
				logger.debug ( "Email Sent. " + ec [i].getOrder_number () );
				logger.debug ( "From " + email.getFrom () );
				logger.debug ( "To " + email.getTo () );
				logger.debug ( "Cc " + email.getCc () );
				logger.debug ( "Bcc " + email.getBcc () );
				logger.debug ( "Subject " + email.getSubject () );
				logger.debug ( "Body " + StringUtility.displayEmailBodyMessage ( email.getBody () ) );
				logger.warn ( " " );
				counterSent++;
			}
			else
			{
				dm.updateEmailHistory ( ec [i].getEmail_history_id () , "R" );
				System.out.println ( "Email Not Sent Order Number : " + ec [i].getOrder_number () );
				logger.debug ( "Email could not be sent lacking mandatory parameters. " + ec [i].getOrder_number () );
				logger.debug ( "From " + email.getFrom () );
				logger.debug ( "To " + email.getTo () );
				logger.debug ( "Cc " + email.getCc () );
				logger.debug ( "Bcc " + email.getBcc () );
				logger.debug ( "Subject " + email.getSubject () );
				logger.debug ( "Body " + StringUtility.displayEmailBodyMessage ( email.getBody () ) );
				logger.warn ( " " );
				counterNotSent++;
			}
		}
		logger.debug ( "********************** Order Shipment (Start) **********************" );
		System.out.println ( "********************** Order Shipment (Start) **********************" );
		//		for output in cron email need an RPT at beginning of line.
		System.out.println ( "\nRPT:\tOrder Shipment" );
		System.out.println ( "RPT:\tSuccessful/Rejected\t\t" + counterSent + " : " + counterNotSent + "\n" );
	}

	/**
	 * Default Constructor.
	 *
	 */
	public GemShipmentEmail ()
	{

	}
}