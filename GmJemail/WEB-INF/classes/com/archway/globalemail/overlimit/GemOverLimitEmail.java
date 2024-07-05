package com.archway.globalemail.overlimit;

import java.io.IOException;
import java.sql.SQLException;

import com.archway.globalemail.common.Logger;
import com.archway.globalemail.email.DMLManager;
import com.archway.globalemail.email.EmailFactory;
import com.archway.globalemail.entity.Email;
import com.archway.globalemail.entity.EmailClient;
import com.archway.globalemail.entity.EmailClientRecipient;
import com.archway.globalemail.entity.Order;
import com.archway.globalemail.entity.OverLimit;
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
 * File Name               : GemOverLimitEmail.java
 * Package Name            : com.archway.globalemail.overlimit
 *
 * Date                    : Nov 10, 2005 - 2:01:42 PM
 *
 * Change Date             : May 15, 2006 10:09:27 PM
 *                           Tuesday, May 16, 2006
 * 							 May 18, 2006 4:05:02 PM
 *                           May 30, 2006 11:20:14 AM
 *                           May 30, 2006 12:32:36 PM
 * 			                 May 31, 2006 10:16:26 PM
 * 							 Jun 2, 2006 6:37:05 PM
 * 							 Mar 11, 2007 1:38:48 AM
 * 							 Apr 10, 2007 3:03:39 PM
 * 							 Apr 18, 2007 1:41:34 PM
 *                           Jan 28, 2008 10:33:06 AM
 *
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */
public class GemOverLimitEmail
{

	// logger
	private static Logger	logger	= Logger.getLogger ( "overLimit" );

	/**
	 * Main Method.
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
		boolean sendEmailFlag = false;
		boolean createOverLimitFlag = false;
		int counterSent = 0;
		int counterNotSent = 0;
		DMLManager dm = new DMLManager ();
		String individualEmailAddress[] = null;
		String indEmailAddress = "";

		createOverLimitFlag = dm.CreateOverLimitRecInEmailHistory ( "1" );

		EmailClient ec[] = dm.getEmailClientRecords ( "V" );
		System.out.println ( "\n\n\n" );
		System.out.println ( "********************** Order Over Limit (Start) **********************" );
		logger.debug ( "********************** Order Over Limit (Start) **********************" );
		for ( int i = 0 ; i < ec.length ; i++ )
		{

			logger.warn ( "OverLimit Order Number : " + ec [i].getOrder_number () );
			OverLimit ol = null;//dm.getOverLimitDetails ( ec [i].getClient () , ec [i].getOrder_number () , ec [i].getNomenclature_id () );
			sendEmailFlag = false;

			EmailClientRecipient ecr[] = dm.getOLSpecialEmailClientRecipient ( ec [i].getOrder_number() );
			Order order = dm.getOrderRecord ( ec [i].getClient () , ec [i].getOrder_number () );
			Email email = dm.setEmailMessageHeader ( ecr , order );
			email.setSubject ( ec [i].getSubject () );
			email.setFrom ( ec [i].getSender () );
			email.setBcc ( "gm_fulfillment@archway.com" );

			indEmailAddress = email.getTo () + "," + email.getCc ();
			System.out.println(ec[i].getEmail_history_id()+"  first "+indEmailAddress);
			individualEmailAddress = StringUtility.getUniqueValues ( indEmailAddress.split ( "," ) );
			/*for ( int z = 0 ; z < individualEmailAddress.length ; z++ )
			{
				System.out.println(z+"  in Array "+individualEmailAddress[z]);
			}*/
			
			for ( int j = 0 ; j < individualEmailAddress.length ; j++ )
			{
				email.setTo ( individualEmailAddress [j] );
				email.setCc ( "" );
				email.setBcc ( "gm_fulfillment@archway.com" );
				email = EmailFactory.contructOverLimitEmailBody ( ec [i].getPath_name () + ec [i].getFile_name () , ol , order , email , ec[i].getOrder_number() );
				logger.warn(email.getBody());
				email = EmailFactory.validateEmailAddress ( email );
				decideToSend = EmailFactory.decideToSend ( email );
				sendEmailFlag = StringUtility.isStringBlank ( ec[i].getOrder_number() );// returns true if the string is null
				if ( ! sendEmailFlag )
				{
					if ( decideToSend )
					{
						transportFlag = EmailFactory.Transport ( ec [i].getOrder_number () , email );
						dm.updateEmailHistory ( ec [i].getEmail_history_id () , "Y" );
						System.out.println ( "Email Sent OverLimit Order Number : " + ec [i].getOrder_number () );
						logger.warn ( "Email Sent." + ec [i].getOrder_number () );
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
						System.out.println ( "Email Not Sent OverLimit Order Number : " + ec [i].getOrder_number () );
						logger.warn ( "Email could not be sent lacking mandatory parameters. " + ec [i].getOrder_number () );
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
			}
		}
		logger.debug ( "********************** Order Over Limit (End) **********************" );
		System.out.println ( "********************** Order Over Limit (End) **********************" );
		//		for output in cron email need an RPT at beginning of line.
		System.out.println ( "\nRPT:\tOrder Over Limit" );
		System.out.println ( "RPT:\tSuccessful/Rejected\t\t" + counterSent + " : " + counterNotSent + "\n" );
	}

	/**
	 * Default Constructor.
	 */
	public GemOverLimitEmail ()
	{

	}

}