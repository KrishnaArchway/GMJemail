package com.archway.globalemail.email;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import oracle.jdbc.driver.OracleTypes;

import com.archway.globalemail.common.ConnectionCacheImpl;
import com.archway.globalemail.common.Logger;
import com.archway.globalemail.entity.BackOrder;
import com.archway.globalemail.entity.BillingNotification;
import com.archway.globalemail.entity.Email;
import com.archway.globalemail.entity.EmailClient;
import com.archway.globalemail.entity.EmailClientRecipient;
import com.archway.globalemail.entity.LowWater;
import com.archway.globalemail.entity.Nomenclature;
import com.archway.globalemail.entity.Order;
import com.archway.globalemail.entity.OrderedItems;
import com.archway.globalemail.entity.OverLimit;
import com.archway.globalemail.entity.Packing;
import com.archway.globalemail.entity.Receiving;
import com.archway.globalemail.entity.ReorderPoint;
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
 * File Name               : DMLManager.java
 * Package Name            : com.archway.globalemail.email
 *
 * Date                    : Oct 27, 2005 - 2:58:41 PM
 *
 * Change Date             : Oct 27, 2005 - 2:58:41 PM
 * 							 Jan 15, 2006 5:20:40 PM
 * 							 Jan 16, 2006 11:13:34 AM
 * 						     Jan 17, 2006 11:13:34 AM
 * 							 Jan 24, 2006 11:58:17 AM
 *                           Mar 13, 2006 3:55:51 PM
 *                           Friday, March 17, 2006
 *                           Monday, March 27, 2006
 *                           Wednesday, May 17, 2006
 *                           Thursday, May 18, 2006
 *                           May 30, 2006 11:49:10 AM
 *                           May 30, 2006 12:07:41 PM
 *                           Jun 2, 2006 6:37:22 PM
 *                           Jun 19, 2007 2:49:26 PM
 *							 Thursday, June 28, 2007
 *							 Jul 24, 2007 12:04:37 AM
 *							 Aug 3, 2007 1:31:02 AM
 *							 Jan 28, 2008 10:23:19 AM
 *							 Dec 1, 2009 1:03:41 PM
 *							 Nov 27, 2012 9:01:53 AM
 *							 Jul 23, 2014 10:40:18 AM
 *                           Jun  21,2024  05:00:00 AM
 *
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * Performs all the DML activity for gmJemail application.
 *
 * Note (May 30, 2006 12:09:44 PM):- In future the ConnectionCacheImpl class needs to upgraded/recoded for JDBC connection pooling to
 * avoid deprecation message at build time.
 *
 */

public class DMLManager
{

	/**
	 * logger
	 */
	private static Logger	logger		= Logger.getLogger ( DMLManager.class );

	/**
	 * Connection pooling object
	 */
	ConnectionCacheImpl		theCache	= null;

	/**
	 * Default constructor
	 */
	public DMLManager ()
	{
		theCache = ConnectionCacheImpl.getCache ();
	}

	/**
	 *
	 * Generates BackOrder data to send out emails, in email_history table.
	 * Method created on Jun 19, 2007 2:51:27 PM
	 *
	 *
	 * @param client
	 * @return
	 */
	public boolean CreateBackOrderRecInEmailHistory ( String client )
	{
		logger.entering ( "CreateBackOrderRecInEmailHistory( String )" );
		boolean returnFlag = false;
		Connection conn = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.CreateBackOrderRecInEmailHistorySql ();
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , client );
			cstmt.registerOutParameter ( 2 , java.sql.Types.INTEGER );
			cstmt.execute ();
			returnFlag = ( cstmt.getInt ( 2 ) == 0 );
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java CreateBackOrderRecInEmailHistory " + e.getMessage () );
		}
		finally
		{
			try
			{
				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java CreateBackOrderRecInEmailHistory " + fin.getMessage () );
			}
			logger.exiting ( "CreateBackOrderRecInEmailHistory( String )" );
		}
		return returnFlag;

	}

	/**
	 * creates low water recode in email history table.
	 *
	 * @param client
	 * @return
	 */
	public boolean CreateLowWaterRecInEmailHistory ( String client )
	{
		logger.entering ( "CreateLowWaterRecInEmailHistory(String  )" );
		boolean returnFlag = false;
		Connection conn = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.CreateLowWaterRecInEmailHistorySql ();
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , client );
			cstmt.registerOutParameter ( 2 , java.sql.Types.INTEGER );
			//cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstmt.execute ();
			returnFlag = ( cstmt.getInt ( 2 ) == 0 );
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java CreateLowWaterRecInEmailHistory " + e.getMessage () );
		}
		finally
		{
			try
			{
				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java CreateLowWaterRecInEmailHistory " + fin.getMessage () );
			}
			logger.exiting ( "CreateLowWaterRecInEmailHistory(String  )" );
		}
		return returnFlag;

	}
	/**
	 * creates Reorder Point recode in email history table.
	 *
	 * @param client
	 * @return
	 */

	public boolean CreateReorderPointRecInEmailHistory(String client) {
		logger.entering("CreateReorderPointRecInEmailHistory(String)");
		boolean returnFlag = false;
		Connection conn = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.CreateReorderPointRecInEmailHistorySql();
		try {
			conn = theCache.getConnection();
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, client);
			cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
			cstmt.execute();
			returnFlag = (cstmt.getInt(2) == 0);
		} catch (Exception e) {
			logger.debug("Error in DMLManager.java CreateReorderPointRecInEmailHistory " + e.getMessage());
		} finally {
			try {
				if (cstmt != null) {
					cstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception fin) {
				logger.debug(
						"FINALLY -- Error in DMLManager.java CreateReorderPointRecInEmailHistory " + fin.getMessage());
			}
			logger.exiting("CreateReorderPointRecInEmailHistory(String)");
		}
		return returnFlag;
	}

	/**
	 * Creates Over Limit Records in Email History Table.
	 *
	 * @param client
	 * @return
	 */
	public boolean CreateOverLimitRecInEmailHistory ( String client )
	{
		logger.entering ( "CreateOverLimitRecInEmailHistory(String  )" );
		boolean returnFlag = false;
		Connection conn = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.CreateOverLimitRecInEmailHistorySql ();
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , client );
			cstmt.registerOutParameter ( 2 , java.sql.Types.INTEGER );
			cstmt.registerOutParameter ( 3 , java.sql.Types.VARCHAR );
			cstmt.execute ();
			returnFlag = ( cstmt.getInt ( 2 ) == 0 );
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java CreateOverLimitRecInEmailHistory " + e.getMessage () );
		}
		finally
		{
			try
			{
				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java CreateOverLimitRecInEmailHistory " + fin.getMessage () );
			}
			logger.exiting ( "CreateOverLimitRecInEmailHistory(String  )" );
		}
		return returnFlag;

	}

	/**
	 * Creates Rush Records into Email History Table.
	 *
	 * @param client
	 * @return
	 */
	public boolean CreateRushRecInEmailHistory ( String client )
	{
		logger.entering ( "CreateRushRecInEmailHistory(String  )" );
		boolean returnFlag = false;
		Connection conn = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.CreateRushRecInEmailHistorySql ();
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , client );
			cstmt.registerOutParameter ( 2 , java.sql.Types.INTEGER );
			cstmt.registerOutParameter ( 3 , java.sql.Types.VARCHAR );
			cstmt.execute ();
			returnFlag = ( cstmt.getInt ( 2 ) == 0 );
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java CreateRushRecInEmailHistory " + e.getMessage () );
		}
		finally
		{
			try
			{
				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java CreateOverLimitRecInEmailHistory " + fin.getMessage () );
			}
			logger.exiting ( "CreateRushRecInEmailHistory(String  )" );
		}
		return returnFlag;

	}

	/**
	 * Gets BackOrder Data Information
	 * Method created on Jun 19, 2007 4:08:14 PM
	 *
	 * @param noMenclatureID
	 * @return
	 */
	public BackOrder getBackOrderDetails ( String client , String noMenclatureID )
	{
		logger.entering ( "getBackOrderDetails(String  )" );
		BackOrder bo = new BackOrder ();
		Connection conn = null;
		ResultSet rst = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getBackOrderDetailsSql ();
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , client );
			cstmt.setString ( 2 , noMenclatureID );

			cstmt.registerOutParameter ( 3 , OracleTypes.CURSOR );
			cstmt.registerOutParameter ( 4 , java.sql.Types.VARCHAR );
			cstmt.execute ();
			rst = ( ResultSet ) cstmt.getObject ( 3 );
			while ( rst.next () )
			{
				bo.setStock_number ( rst.getString ( "stock_number" ) );
				bo.setDescription ( rst.getString ( "description" ) );
				bo.setEarliest_backorder_date ( rst.getString ( "earliest_backorder_date" ) );
				bo.setLatest_backorder_date ( rst.getString ( "latest_backorder_date" ) );
				bo.setNumber_of_orders_on_backorder ( rst.getString ( "number_of_orders_on_backorder" ) );
				bo.setPiece_quantity_on_backorder ( rst.getString ( "piece_quantity_on_backorder" ) );
				bo.setCurrent_inventory_available ( rst.getString ( "current_inventory_available" ) );
				bo.setReorder_lead_time ( rst.getString ( "reorder_lead_time" ) );
				bo.setIdeal_maximum_qty ( rst.getString ( "ideal_maximum_qty" ) );
				bo.setSupplier ( rst.getString ( "supplier" ) );
				bo.setUnit_of_measure_description ( rst.getString ( "unit_of_measure_description" ) );
			}
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java getBackOrderDetails " + e.getMessage () );
		}
		finally
		{
			try
			{
				rst.close ();
				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java getBackOrderDetails " + fin.getMessage () );
			}
			logger.exiting ( "getBackOrderDetails(String  )" );
		}

		return bo;
	}

	/**
	 * For Bars
	 * @param invoice_id
	 * @return
	 */
	public BillingNotification getBillingNotificationRecords ( String invoice_id )
	{
		logger.entering ( "getBillingNotificationRecords(String  )" );
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getBillingNotificationSql ();
		BillingNotification bn = new BillingNotification ();
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , invoice_id );
			cstmt.registerOutParameter ( 2 , OracleTypes.CURSOR );
			cstmt.registerOutParameter ( 3 , java.sql.Types.VARCHAR );
			cstmt.execute ();
			rs = ( ResultSet ) cstmt.getObject ( 2 );
			while ( rs.next () )
			{

				bn.setFile_name ( rs.getString ( "file_name" ) );
				bn.setFile_transmission_status ( rs.getString ( "file_transmission_status" ) );
				bn.setInvoice_start_date ( rs.getString ( "invoice_start_date" ) );
				bn.setInvoice_end_date ( rs.getString ( "invoice_end_date" ) );
				bn.setVendor_transmission_code ( rs.getString ( "vendor_transmission_code" ) );
				bn.setProgram_code ( rs.getString ( "program_code" ) );
				bn.setInvoicing_type ( rs.getString ( "invoicing_type" ) );
				bn.setParent_file_id ( rs.getString ( "parent_file_id" ) );
				bn.setInvoice_total ( rs.getString ( "invoice_total" ) );
				bn.setInvoice_count ( rs.getString ( "invoice_count" ) );

			}
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java getBillingNotificationRecords " + e.getMessage () );
		}
		finally
		{
			try
			{
				rs.close ();
				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java getBillingNotificationRecords " + fin.getMessage () );
			}
			logger.exiting ( "getBillingNotificationRecords(String  )" );
		}
		return bn;
	}

	/**
	 * this method determines to efullfillment download link
	 *
	 * @param orderedItems
	 * @return
	 */
	public boolean getEFulFillmentFlag ( OrderedItems orderedItems[] )
	{
		logger.entering ( "getEFulFillmentFlag(OrderedItems  )" );
		boolean returnFlag = false;
		for ( int i = 0 ; i < orderedItems.length ; i++ )
		{
			if ( orderedItems [i].getEfullfillment_item ().equalsIgnoreCase ( "Y" ) )
			{
				returnFlag = true;
				break;
			}
			else
			{
				returnFlag = false;
			}
		}
		logger.exiting ( "getEFulFillmentFlag(OrderedItems  )" );
		return returnFlag;
	}

	/**
	 * Gets recipients details from email_client_recipient
	 *
	 * @param email_client_id
	 * @return
	 */
	public EmailClientRecipient [] getEmailClientRecipientRecords (
																	String email_client_id )
	{
		logger.entering ( "getEmailClientRecipientRecords( String )" );
		Connection conn = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getEmailClientRecipientRecordsSql ();
		Vector list = new Vector ();
		ResultSet rs = null;
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , email_client_id );
			cstmt.registerOutParameter ( 2 , OracleTypes.CURSOR );
			cstmt.registerOutParameter ( 3 , OracleTypes.VARCHAR );
			cstmt.execute ();
			rs = ( ResultSet ) cstmt.getObject ( 2 );
			/*
			 * SELECT c.EMAIL_CLIENT_RECIPIENT_ID email_client_recipient_id,
			 * c.recipient_type recipient_type, c.email_address email_address,
			 * c.email_bp_type email_bp_type
			 */
			while ( rs.next () )
			{
				EmailClientRecipient ecr = new EmailClientRecipient ();
				ecr.setEmail_client_recipient_id ( rs.getString ( "email_client_recipient_id" ) );
				ecr.setRecipient_type ( rs.getString ( "recipient_type" ) );
				ecr.setEmail_address ( rs.getString ( "email_address" ) );
				ecr.setEmail_bp_type ( rs.getString ( "email_bp_type" ) );
				list.addElement ( ecr );
			}
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java getEmailClientRecipientRecords " + e.getMessage () );
		}
		finally
		{
			try
			{

				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java getEmailClientRecipientRecords " + fin.getMessage () );
			}
			logger.exiting ( "getEmailClientRecipientRecords( String )" );
		}
		return ( EmailClientRecipient [] ) list.toArray ( new EmailClientRecipient[0] );

	}

	/**
	 * gets record to sendemail
	 *
	 * @param whichEmail
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public EmailClient [] getEmailClientRecords ( String whichEmail )
	{
		logger.entering ( "getEmailClientRecords( String )" );
		Connection conn = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getEmailClientRecordsSql ();
		Vector list = new Vector ();
		ResultSet rs = null;
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , ( whichEmail != null ? whichEmail : "$$" ) );
			//cstmt.setString(2, "");
			cstmt.registerOutParameter ( 2 , OracleTypes.CURSOR );
			cstmt.registerOutParameter ( 3 , OracleTypes.VARCHAR );
			cstmt.execute ();
			rs = ( ResultSet ) cstmt.getObject ( 2 );
			while ( rs.next () )
			{
				/*
				 * SELECT a.ORDER_NUMBER order_number, a.business_partner_id
				 * business_partner_id, a.nomenclature_id nomenclature_id,
				 * a.invoice_file_id invoice_file_id, a.trans_id trans_id,
				 * b.email_client_id email_client_id, b.client client,
				 * b.job_number_id job_number_id, b.email_type email_type , b.sender
				 * sender, b.subject subject, b.HTML_FILE_LOCATION_ID
				 * html_file_location_id, d.description file_description,
				 * d.path_name path_name, d.file_name file_name , a.email_history_id
				 * email_history_id --amar
				 */
				EmailClient ec = new EmailClient ();
				ec.setOrder_number ( rs.getString ( "order_number" ) );
				ec.setBusiness_partner_id ( rs.getString ( "business_partner_id" ) );
				ec.setNomenclature_id ( rs.getString ( "nomenclature_id" ) );
				ec.setInvoice_file_id ( rs.getString ( "invoice_file_id" ) );
				ec.setTrans_id ( rs.getString ( "trans_id" ) );
				ec.setEmail_client_id ( rs.getString ( "email_client_id" ) );
				ec.setClient ( rs.getString ( "client" ) );
				ec.setJob_number_id ( rs.getString ( "job_number_id" ) );
				ec.setEmail_type ( rs.getString ( "email_type" ) );
				ec.setSender ( rs.getString ( "sender" ) );
				ec.setSubject ( rs.getString ( "subject" ) );
				ec.setHtml_file_location_id ( rs.getString ( "html_file_location_id" ) );
				ec.setFile_description ( rs.getString ( "file_description" ) );
				ec.setPath_name ( rs.getString ( "path_name" ) );
				ec.setFile_name ( rs.getString ( "file_name" ) );
				ec.setEmail_history_id ( rs.getString ( "email_history_id" ) );
				ec.setPriority ( rs.getString ( "priority" ) );
				ec.setPackslip_logo ( rs.getString ( "packslip_logo" ) );
				ec.setMedia_id ( rs.getString ( "media_id" ) );
				list.addElement ( ec );
			}
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java getEmailClientRecords " + e.getMessage () );
		}
		finally
		{
			try
			{

				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java getEmailClientRecords " + fin.getMessage () );
			}
			logger.exiting ( "getEmailClientRecords( String )" );
		}
		return ( EmailClient [] ) list.toArray ( new EmailClient[0] );

	}

	/**
	 * gets record to sendemail with order number
	 *
	 * @param whichEmail
	 * @param order_number
	 * @return
	 */
	public EmailClient [] getEmailClientRecords (
													String whichEmail ,
													String order_number )
	{
		logger.entering ( "getEmailClientRecords(String , String )" );
		Connection conn = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getEmailClientRecordsSql ();
		Vector list = new Vector ();
		ResultSet rs = null;
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , ( whichEmail != null ? whichEmail : "$$" ) );
			cstmt.setString ( 2 , order_number );
			cstmt.registerOutParameter ( 3 , OracleTypes.CURSOR );
			cstmt.registerOutParameter ( 4 , OracleTypes.VARCHAR );
			cstmt.execute ();
			rs = ( ResultSet ) cstmt.getObject ( 3 );
			while ( rs.next () )
			{
				/*
				 * SELECT a.ORDER_NUMBER order_number, a.business_partner_id
				 * business_partner_id, a.nomenclature_id nomenclature_id,
				 * a.invoice_file_id invoice_file_id, a.trans_id trans_id,
				 * b.email_client_id email_client_id, b.client client,
				 * b.job_number_id job_number_id, b.email_type email_type , b.sender
				 * sender, b.subject subject, b.HTML_FILE_LOCATION_ID
				 * html_file_location_id, d.description file_description,
				 * d.path_name path_name, d.file_name file_name , a.email_history_id
				 * email_history_id --amar
				 */
				EmailClient ec = new EmailClient ();
				ec.setOrder_number ( rs.getString ( "order_number" ) );
				ec.setBusiness_partner_id ( rs.getString ( "business_partner_id" ) );
				ec.setNomenclature_id ( rs.getString ( "nomenclature_id" ) );
				ec.setInvoice_file_id ( rs.getString ( "invoice_file_id" ) );
				ec.setTrans_id ( rs.getString ( "trans_id" ) );
				ec.setEmail_client_id ( rs.getString ( "email_client_id" ) );
				ec.setClient ( rs.getString ( "client" ) );
				ec.setJob_number_id ( rs.getString ( "job_number_id" ) );
				ec.setEmail_type ( rs.getString ( "email_type" ) );
				ec.setSender ( rs.getString ( "sender" ) );
				ec.setSubject ( rs.getString ( "subject" ) );
				ec.setHtml_file_location_id ( rs.getString ( "html_file_location_id" ) );
				ec.setFile_description ( rs.getString ( "file_description" ) );
				ec.setPath_name ( rs.getString ( "path_name" ) );
				ec.setFile_name ( rs.getString ( "file_name" ) );
				ec.setEmail_history_id ( rs.getString ( "email_history_id" ) );
				ec.setPriority ( rs.getString ( "priority" ) );
				ec.setPackslip_logo ( rs.getString ( "packslip_logo" ) );
				list.addElement ( ec );
			}
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java getEmailClientRecords " + e.getMessage () );
		}
		finally
		{
			try
			{

				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java getEmailClientRecords " + fin.getMessage () );
			}
			logger.exiting ( "getEmailClientRecords(String , String )" );
		}
		return ( EmailClient [] ) list.toArray ( new EmailClient[0] );

	}

	/**
	 * to get the low water details.
	 *
	 * @param noMenclatureID
	 * @return
	 */
	public LowWater getLowWaterDetails ( String noMenclatureID )
	{
		logger.entering ( "getLowWaterDetails(String  )" );
		LowWater lw = new LowWater ();
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getLowWaterDetailsSql ();
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , noMenclatureID );
			cstmt.registerOutParameter ( 2 , OracleTypes.CURSOR );
			cstmt.registerOutParameter ( 3 , java.sql.Types.VARCHAR );
			cstmt.execute ();
			rs = ( ResultSet ) cstmt.getObject ( 2 );
			while ( rs.next () )
			{
				lw.setStock_number ( rs.getString ( "stock_number" ) );
				lw.setDescription_for_www ( rs.getString ( "description_for_www" ) );
				lw.setLow_water_point ( rs.getString ( "low_water_point" ) );
				lw.setReorder_lead_time ( rs.getString ( "reorder_lead_time" ) );
				lw.setIdeal_max_qty ( rs.getString ( "ideal_max_qty" ) );
				lw.setCompany ( rs.getString ( "company" ) );
				lw.setQuantity_on_hand ( rs.getString ( "quantity_on_hand" ) );
				lw.setQuantity_pending ( rs.getString ( "quantity_pending" ) );
				lw.setUofm_description ( rs.getString ( "uofm_description" ) );
				lw.setDescription ( rs.getString ( "description" ) );
				lw.setYearly_usage ( rs.getString ( "yearly_usage" ) );
				lw.setTotal_usage ( rs.getString ( "total_usage" ) );
				lw.setAve_monthly_usage ( rs.getString ( "ave_monthly_usage" ) );
			}
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java getLowWaterDetails " + e.getMessage () );
		}
		finally
		{
			try
			{
				rs.close ();
				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java getLowWaterDetails " + fin.getMessage () );
			}
			logger.exiting ( "getLowWaterDetails(String  )" );
		}

		return lw;
	}
	
	public ReorderPoint getReorderPointDetails(String noMenclatureID) {
		logger.entering("getReorderPointDetails(String  )");
		ReorderPoint rp = new ReorderPoint();
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getReOrderPointDetailsSql();
		try {
			conn = theCache.getConnection();
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, noMenclatureID);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(2);
			while (rs.next()) {
				rp.setStock_number(rs.getString("stock_number"));
				rp.setDescription_for_www(rs.getString("description_for_www"));
				rp.setReorder_point(rs.getString("re_order_point"));
				rp.setLow_water_point(rs.getString("low_water_point"));
				rp.setReorder_lead_time(rs.getString("reorder_lead_time"));
				
				rp.setIdeal_max_qty(rs.getString("ideal_max_qty"));
				rp.setCompany(rs.getString("company"));
				rp.setQuantity_on_hand(rs.getString("quantity_on_hand"));
				rp.setQuantity_pending(rs.getString("quantity_pending"));
				rp.setUofm_description(rs.getString("uofm_description"));
				rp.setDescription(rs.getString("description"));
				rp.setYearly_usage(rs.getString("yearly_usage"));
				rp.setTotal_usage(rs.getString("total_usage"));
				rp.setAve_monthly_usage(rs.getString("ave_monthly_usage"));
			}
		} catch (Exception e) {
			logger.debug("Error in DMLManager.java getReorderPointDetails " + e.getMessage());
		} finally {
			try {
				rs.close();
				cstmt.close();
				conn.close();
			} catch (Exception fin) {
				logger.debug("FINALLY -- Error in DMLManager.java getReorderPointDetails " + fin.getMessage());
			}
			logger.exiting("getReorderPointDetails(String  )");
		}

		return rp;
	}


	/**
	 * @param noMenclatureID
	 * @return
	 */
	public Nomenclature getNomenclatureDetails ( String noMenclatureID )
	{
		logger.entering ( "getNomenclatureDetails(String  )" );
		Nomenclature nc = new Nomenclature ();
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getNomenclatureDetailsSql ();
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , noMenclatureID );
			cstmt.registerOutParameter ( 2 , OracleTypes.CURSOR );
			cstmt.registerOutParameter ( 3 , java.sql.Types.VARCHAR );
			cstmt.execute ();
			rs = ( ResultSet ) cstmt.getObject ( 2 );
			while ( rs.next () )
			{
				nc.setStock_number ( rs.getString ( "stock_number" ) );
				nc.setDescription_for_www ( rs.getString ( "description_for_www" ) );
				nc.setDescription ( rs.getString ( "description" ) );
				nc.setNomenclature_id ( rs.getString ( "nomenclature_id" ) );
				nc.setDate_created ( rs.getString ( "date_created" ) );
				nc.setItem_type ( rs.getString ( "item_type" ) );
				nc.setUofm_description ( rs.getString ( "uofm_description" ) );
				nc.setReview_for_scrap_date ( rs.getString ( "review_for_scrap_date" ) );
				nc.setIdeal_max_qty ( rs.getString ( "ideal_max_qty" ) );
				//nc.setAvailableQty ( rs.getString("available_qty") );
				nc.setAvailableQty ( rs.getString ( "quantity_on_hand" ) );
				nc.setLast_shipped_date ( rs.getString ( "last_shipping_date" ) );
			}
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java getNomenclatureDetails " + e.getMessage () );
		}
		finally
		{
			try
			{
				rs.close ();
				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java getNomenclatureDetails " + fin.getMessage () );
			}
			logger.exiting ( "getNomenclatureDetails(String  )" );
		}

		return nc;
	}

	/**
	 * for over limit.
	 *
	 * @param order_number
	 * @return
	 */
	public EmailClientRecipient [] getOLSpecialEmailClientRecipient (
																		String order_number )
	{
		logger.entering ( "getSpecialEmailClientRecipient(String , String )" );
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getOLSpecialEmailClientRecipientSql ();
		Vector lists = new Vector ();
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , order_number );
			cstmt.registerOutParameter ( 2 , OracleTypes.CURSOR );
			cstmt.registerOutParameter ( 3 , java.sql.Types.INTEGER );
			cstmt.registerOutParameter ( 4 , java.sql.Types.VARCHAR );
			cstmt.execute ();
			rs = ( ResultSet ) cstmt.getObject ( 2 );
			while ( rs.next () )
			{
				EmailClientRecipient ecr = new EmailClientRecipient ();
				ecr.setEmail_address ( rs.getString ( "email_address" ) );
				ecr.setRecipient_type ( "T" );
				lists.addElement ( ecr );
			}
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java getSpecialEmailClientRecipient " + e.getMessage () );
		}
		finally
		{
			try
			{
				rs.close ();
				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java getSpecialEmailClientRecipient " + fin.getMessage () );
			}
			logger.exiting ( "getSpecialEmailClientRecipient(String , String )" );

		}
		return ( EmailClientRecipient [] ) lists.toArray ( new EmailClientRecipient[0] );

	}

	/**
	 * get order item records
	 *
	 * @param client
	 * @param order_number
	 * @return
	 */
	public OrderedItems [] getOrderItemsRecords (
													String client ,
													String order_number )
	{
		logger.entering ( "getOrderItemsRecords( String , String)" );
		Connection conn = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getOrderItemsRecordsSql ();
		Vector list = new Vector ();
		ResultSet rs = null;

		//logger.debug ( "client  " + client );
		//logger.debug ( "order number " + order_number );

		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , client );
			cstmt.setString ( 2 , order_number );
			cstmt.registerOutParameter ( 3 , OracleTypes.CURSOR );
			cstmt.registerOutParameter ( 4 , OracleTypes.VARCHAR );
			cstmt.execute ();
			rs = ( ResultSet ) cstmt.getObject ( 3 );
			while ( rs.next () )
			{
				System.out.println ( "stock number " + rs.getString ( "stock_number" ) );
				OrderedItems oi = new OrderedItems ();
				oi.setStock_number ( rs.getString ( "stock_number" ) );
				oi.setQuantity_ordered ( rs.getString ( "quantity_ordered" ) );
				oi.setQuantity_to_be_shipped ( rs.getString ( "quantity_to_be_shipped" ) );
				oi.setQuantity_shipped ( rs.getString ( "quantity_shipped" ) );
				oi.setUnit_price ( rs.getString ( "unit_price" ) );
				oi.setDescription_for_www ( rs.getString ( "description_for_www" ) );
				oi.setDescription ( rs.getString ( "description" ) );
				oi.setEfullfillment_item ( rs.getString ( "efullfillment_item" ) );
				oi.setNomenclature_id ( rs.getString ( "nomenclature_id" ) );
				oi.setExt_amount ( rs.getString ( "ext_amount" ) );
				oi.setQuantity_backOrdered ( rs.getString ( "quantity_backordered" ) );
				oi.setQuantity_cancelled ( rs.getString ( "quantity_cancelled" ) );
				list.addElement ( oi );
			}
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java getOrderItemsRecords " + e.getMessage () );
		}
		finally
		{
			try
			{

				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java getOrderItemsRecords " + fin.getMessage () );
			}
			logger.exiting ( "getOrderItemsRecords( String , String)" );
		}
		return ( OrderedItems [] ) list.toArray ( new OrderedItems[0] );
	}

	/**
	 * gets order record
	 *
	 * @param client
	 * @param order_number
	 * @return
	 */

	public Order getOrderRecord ( String client , String order_number )
	{
		logger.entering ( "getOrderRecord( String , String)" );
		Connection conn = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getOrderRecordSql ();
		Vector list = new Vector ();
		ResultSet rs = null;
		Order order = new Order ();
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , client );
			cstmt.setString ( 2 , order_number );
			//System.out.println("Passing to proc  " + client + "   -   " + order_number);

			cstmt.registerOutParameter ( 3 , OracleTypes.CURSOR );
			cstmt.registerOutParameter ( 4 , OracleTypes.INTEGER );
			cstmt.execute ();
			rs = ( ResultSet ) cstmt.getObject ( 3 );

			while ( rs.next () )
			{
				/*order.setOrder_number(rs.getString("order_number"));
				 order.setOrder_date(rs.getString("order_date"));
				 order.setMedia_id(rs.getString("media_id"));
				 order.setShip_date(rs.getString("ship_date"));
				 order.setBill_to_company(rs.getString("bill_to_company"));
				 order.setBill_to_salutation(rs.getString("bill_to_salutation"));
				 order.setBill_to_first_name(rs.getString("bill_to_first_name"));
				 order.setBill_to_middle_initial(rs.getString("bill_to_middle_initial"));
				 order.setBill_to_last_name(rs.getString("bill_to_last_name"));
				 order.setBill_to_address1(rs.getString("bill_to_address1"));
				 order.setBill_to_address2(rs.getString("bill_to_address2"));
				 order.setBill_to_address3(rs.getString("bill_to_address3"));
				 order.setBill_to_city(rs.getString("bill_to_city"));
				 order.setBill_to_company(rs.getString("bill_to_company"));
				 order.setBill_to_country(rs.getString("bill_to_country"));
				 order.setBill_to_county(rs.getString("bill_to_county"));
				 order.setBill_to_email(rs.getString("bill_to_email"));
				 order.setBill_to_state_province(rs.getString("bill_to_state_province"));
				 order.setBill_to_zip(rs.getString("bill_to_zip"));
				 order.setBill_to_phone_number(rs.getString("bill_to_phone_number"));
				 order.setShip_to_company(rs.getString("ship_to_company"));
				 order.setShip_to_salutation(rs.getString("ship_to_salutation"));
				 order.setShip_to_first_name(rs.getString("ship_to_first_name"));
				 order.setShip_to_middle_initial(rs.getString("ship_to_middle_initial"));
				 order.setShip_to_last_name(rs.getString("ship_to_last_name"));
				 order.setShip_to_address1(rs.getString("ship_to_address1"));
				 order.setShip_to_address2(rs.getString("ship_to_address2"));
				 order.setShip_to_address3(rs.getString("ship_to_address3"));
				 order.setShip_to_city(rs.getString("ship_to_city"));
				 order.setShip_to_company(rs.getString("ship_to_company"));
				 order.setShip_to_country(rs.getString("ship_to_country"));
				 order.setShip_to_county(rs.getString("ship_to_county"));
				 order.setShip_to_email(rs.getString("ship_to_email"));
				 order.setShip_to_state_province(rs.getString("ship_to_state_province"));
				 order.setShip_to_zip(rs.getString("ship_to_zip"));
				 order.setShip_to_phone_number(rs.getString("ship_to_phone_number"));
				 order.setCredit_card_number(rs.getString("credit_card_number"));
				 order.setCredit_card_expiration_date(rs.getString("credit_card_expiration_date"));
				 order.setPayment_type(rs.getString("payment_type"));
				 order.setOrder_amount(rs.getString("order_amount"));
				 order.setShipping_amount(rs.getString("shipping_amount"));
				 order.setRush_amount(rs.getString("rush_amount"));
				 order.setState_tax_amount(rs.getString("state_tax_amount"));
				 order.setList_order_id(rs.getString("list_order_id"));*/

				order.setOrder_number ( StringUtility.processNullToEmpty ( rs.getString ( "order_number" ) ) );
				order.setClient_order_number ( StringUtility.processNullToEmpty ( rs.getString ( "client_order_number" ) ) );
				order.setOrder_date ( StringUtility.processNullToEmpty ( rs.getString ( "order_date" ) ) );
				order.setMedia_id ( StringUtility.processNullToEmpty ( rs.getString ( "media_id" ) ) );
				order.setShip_date ( StringUtility.processNullToEmpty ( rs.getString ( "ship_date" ) ) );
				order.setBill_to_company ( StringUtility.processNullToEmpty ( rs.getString ( "bill_to_company" ) ) );
				order.setBill_to_salutation ( StringUtility.processNullToEmpty ( rs.getString ( "bill_to_salutation" ) ) );
				order.setBill_to_first_name ( StringUtility.processNullToEmpty ( rs.getString ( "bill_to_first_name" ) ) );
				order.setBill_to_middle_initial ( StringUtility.processNullToEmpty ( rs.getString ( "bill_to_middle_initial" ) ) );
				order.setBill_to_last_name ( StringUtility.processNullToEmpty ( rs.getString ( "bill_to_last_name" ) ) );
				order.setBill_to_address1 ( StringUtility.processNullToEmpty ( rs.getString ( "bill_to_address1" ) ) );
				order.setBill_to_address2 ( StringUtility.processNullToEmpty ( rs.getString ( "bill_to_address2" ) ) );
				order.setBill_to_address3 ( StringUtility.processNullToEmpty ( rs.getString ( "bill_to_address3" ) ) );
				order.setBill_to_city ( StringUtility.processNullToEmpty ( rs.getString ( "bill_to_city" ) ) );
				order.setBill_to_company ( StringUtility.processNullToEmpty ( rs.getString ( "bill_to_company" ) ) );
				order.setBill_to_country ( StringUtility.processNullToEmpty ( rs.getString ( "bill_to_country" ) ) );
				order.setBill_to_county ( StringUtility.processNullToEmpty ( rs.getString ( "bill_to_county" ) ) );
				order.setBill_to_email ( StringUtility.processNullToEmpty ( rs.getString ( "bill_to_email" ) ) );
				order.setBill_to_state_province ( StringUtility.processNullToEmpty ( rs.getString ( "bill_to_state_province" ) ) );
				order.setBill_to_zip ( StringUtility.processNullToEmpty ( rs.getString ( "bill_to_zip" ) ) );
				order.setBill_to_phone_number ( StringUtility.processNullToEmpty ( rs.getString ( "bill_to_phone_number" ) ) );
				order.setShip_to_company ( StringUtility.processNullToEmpty ( rs.getString ( "ship_to_company" ) ) );
				order.setShip_to_salutation ( StringUtility.processNullToEmpty ( rs.getString ( "ship_to_salutation" ) ) );
				order.setShip_to_first_name ( StringUtility.processNullToEmpty ( rs.getString ( "ship_to_first_name" ) ) );
				order.setShip_to_middle_initial ( StringUtility.processNullToEmpty ( rs.getString ( "ship_to_middle_initial" ) ) );
				order.setShip_to_last_name ( StringUtility.processNullToEmpty ( rs.getString ( "ship_to_last_name" ) ) );
				order.setShip_to_address1 ( StringUtility.processNullToEmpty ( rs.getString ( "ship_to_address1" ) ) );
				order.setShip_to_address2 ( StringUtility.processNullToEmpty ( rs.getString ( "ship_to_address2" ) ) );
				order.setShip_to_address3 ( StringUtility.processNullToEmpty ( rs.getString ( "ship_to_address3" ) ) );
				order.setShip_to_city ( StringUtility.processNullToEmpty ( rs.getString ( "ship_to_city" ) ) );
				order.setShip_to_company ( StringUtility.processNullToEmpty ( rs.getString ( "ship_to_company" ) ) );
				order.setShip_to_country ( StringUtility.processNullToEmpty ( rs.getString ( "ship_to_country" ) ) );
				order.setShip_to_county ( StringUtility.processNullToEmpty ( rs.getString ( "ship_to_county" ) ) );
				order.setShip_to_email ( StringUtility.processNullToEmpty ( rs.getString ( "ship_to_email" ) ) );
				order.setShip_to_state_province ( StringUtility.processNullToEmpty ( rs.getString ( "ship_to_state_province" ) ) );
				order.setShip_to_zip ( StringUtility.processNullToEmpty ( rs.getString ( "ship_to_zip" ) ) );
				order.setShip_to_phone_number ( StringUtility.processNullToEmpty ( rs.getString ( "ship_to_phone_number" ) ) );
				order.setCredit_card_number ( StringUtility.processNullToEmpty ( rs.getString ( "credit_card_number" ) ) );
				order.setCredit_card_expiration_date ( StringUtility.processNullToEmpty ( rs.getString ( "credit_card_expiration_date" ) ) );
				order.setPayment_type ( StringUtility.processNullToEmpty ( rs.getString ( "payment_type" ) ) );
				order.setOrder_amount ( StringUtility.processNullToEmpty ( rs.getString ( "order_amount" ) ) );
				order.setShipping_amount ( StringUtility.processNullToEmpty ( rs.getString ( "shipping_amount" ) ) );
				order.setRush_amount ( StringUtility.processNullToEmpty ( rs.getString ( "rush_amount" ) ) );
				order.setState_tax_amount ( StringUtility.processNullToEmpty ( rs.getString ( "state_tax_amount" ) ) );
				order.setList_order_id ( StringUtility.processNullToEmpty ( rs.getString ( "list_order_id" ) ) );
				order.setBusiness_partner ( StringUtility.processNullToEmpty ( rs.getString ( "business_partner" ) ) );
				order.setBusiness_partner_type ( StringUtility.processNullToEmpty ( rs.getString ( "business_partner_type" ) ) );
				order.setOrder_taker ( StringUtility.processNullToEmpty ( rs.getString ( "order_taker" ) ) );
				order.setReason_comment ( StringUtility.processNullToEmpty ( rs.getString ( "reason_comment" ) ) );
				order.setShip_to_e_mail ( StringUtility.processNullToEmpty ( rs.getString ( "ship_to_e_mail" ) ) );
				order.setShip_to_e_mail_cc ( StringUtility.processNullToEmpty ( rs.getString ( "ship_to_e_mail_cc" ) ).replaceAll ( "[\\n\\t ]" , "" ) );
				order.setBill_to_e_mail_cc ( StringUtility.processNullToEmpty ( rs.getString ( "bill_to_e_mail_cc" ) ).replaceAll ( "[\\n\\t ]" , "" ) );

			}

		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java getOrderRecord " + e.getMessage () );
		}
		finally
		{
			try
			{

				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java getOrderRecord " + fin.getMessage () );
			}
			logger.exiting ( "getOrderRecord( String , String)" );
		}
		return order;

	}

	/**
	 * Over Limit Details
	 *
	 * @param client
	 * @param order_number
	 * @return
	 */
	public OverLimit getOverLimitDetails (
											String client ,
											String order_number ,
											String nomenclature_id

	)
	{
		logger.entering ( "getOverLimitDetails(String,String,String  )" );
		OverLimit ol = new OverLimit ();
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getOverLimitDetailsSql ();
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , client );
			cstmt.setString ( 2 , nomenclature_id );
			cstmt.setString ( 3 , order_number );
			cstmt.registerOutParameter ( 4 , OracleTypes.CURSOR );
			cstmt.registerOutParameter ( 5 , java.sql.Types.VARCHAR );
			cstmt.execute ();
			rs = ( ResultSet ) cstmt.getObject ( 4 );
			while ( rs.next () )
			{
				//System.out.println("getOverLimitDetails    " + rs.getString("order_number"));
				ol.setOrder_number ( rs.getString ( "order_number" ) );
				ol.setBusiness_partner ( rs.getString ( "business_partner" ) );
				ol.setBill_to_first_name ( rs.getString ( "bill_to_first_name" ) );
				ol.setBill_to_last_name ( rs.getString ( "bill_to_last_name" ) );
				ol.setBill_to_company ( rs.getString ( "bill_to_company" ) );
				ol.setStock_number ( rs.getString ( "stock_number" ) );
				ol.setQuantity_to_be_shipped ( rs.getString ( "quantity_to_be_shipped" ) );
				ol.setDescription_for_www ( rs.getString ( "description_for_www" ) );
				ol.setOrder_limit_minimum_qty ( rs.getString ( "order_limit_minimum_qty" ) );
				ol.setOrder_limit_maximum_qty ( rs.getString ( "order_limit_maximum_qty" ) );
				ol.setDescription ( rs.getString ( "description" ) );
			}
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java getOverLimitDetails " + e.getMessage () );
		}
		finally
		{
			try
			{
				rs.close ();
				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java getOverLimitDetails " + fin.getMessage () );
			}
			logger.exiting ( "getOverLimitDetails(String,String,String  )" );
		}

		return ol;
	}

	/**
	 * for over limit
	 *
	 * @param noMenclatureID
	 * @param emailType
	 * @return
	 */
	public EmailClientRecipient [] getOverLimitSpecialEmailClientRecipient (
																			String noMenclatureID ,
																			String order_number )
	{
		logger.entering ( "getOverLimitSpecialEmailClientRecipient(String , String )" );
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getOverLimitSpecialEmailClientRecipientSql ();
		Vector lists = new Vector ();
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , order_number );
			cstmt.setString ( 2 , noMenclatureID );
			cstmt.registerOutParameter ( 3 , OracleTypes.CURSOR );
			cstmt.registerOutParameter ( 4 , java.sql.Types.VARCHAR );
			cstmt.execute ();
			rs = ( ResultSet ) cstmt.getObject ( 3 );
			while ( rs.next () )
			{
				EmailClientRecipient ecr = new EmailClientRecipient ();
				ecr.setEmail_address ( rs.getString ( "email_address" ) );
				ecr.setRecipient_type ( rs.getString ( "recipient_type" ) );
				lists.addElement ( ecr );
			}
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java getOverLimitSpecialEmailClientRecipient " + e.getMessage () );
		}
		finally
		{
			try
			{
				rs.close ();
				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java getOverLimitSpecialEmailClientRecipient " + fin.getMessage () );
			}
			logger.exiting ( "getOverLimitSpecialEmailClientRecipient(String , String )" );
		}
		return ( EmailClientRecipient [] ) lists.toArray ( new EmailClientRecipient[0] );

	}

	/**
	 * gets packing information.
	 *
	 * @param client
	 * @param order_number
	 * @return
	 */
	public Packing getPackingShipmentRecord (
												String client ,
												String order_number )
	{
		logger.entering ( "getPackingShipmentRecord(String , String )" );
		Packing pk = new Packing ();
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getPackingShipmentRecordSql ();
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , client );
			cstmt.setString ( 2 , order_number );
			cstmt.registerOutParameter ( 3 , OracleTypes.CURSOR );
			cstmt.registerOutParameter ( 4 , java.sql.Types.VARCHAR );
			cstmt.execute ();
			rs = ( ResultSet ) cstmt.getObject ( 3 );
			while ( rs.next () )
			{
				pk.setOrder_number ( rs.getString ( "order_number" ) );
				pk.setTracking_number ( rs.getString ( "tracking_number" ) );
				pk.setShipment_type_description ( rs.getString ( "shipment_type_description" ) );
				pk.setArrive_date ( rs.getString ( "arrive_date" ) );
				pk.setPackage_number ( rs.getString ( "package_number" ) );
				pk.setStatus ( rs.getString ( "status" ) );
				pk.setWeight ( rs.getString ( "weight" ) );
				pk.setBase_charge ( rs.getString ( "base_charge" ) );
				pk.setActual_carrier ( rs.getString ( "actual_carrier" ) );
				pk.setActual_shipment_type ( rs.getString ( "actual_shipment_type" ) );
				pk.setShip_zone ( rs.getString ( "ship_zone" ) );
				pk.setShip_date ( rs.getString ( "ship_date" ) );
				pk.setShipping_update_date ( rs.getString ( "shipping_update_date" ) );
				pk.setPack_seq ( rs.getString ( "pack_seq" ) );
				pk.setActual_arrival_date ( rs.getString ( "actual_arrival_date" ) );
				pk.setEstimated_arrival_date ( rs.getString ( "estimated_arrival_date" ) );
				pk.setActual_pickup_date ( rs.getString ( "actual_pickup_date" ) );
				pk.setEstimated_base_charge ( rs.getString ( "estimated_base_charge" ) );
			}
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java getPackingShipmentRecord " + e.getMessage () );
		}
		finally
		{
			try
			{
				rs.close ();
				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java getPackingShipmentRecord " + fin.getMessage () );
			}
			logger.exiting ( "getPackingShipmentRecord(String , String )" );
		}
		return pk;
	}

	/**
	 * get email history record for pick list emails.
	 *
	 * @return entity EmailHistory
	 */
	public EmailClient [] getPickListEmailHistoryRecords ()
	{
		logger.entering ( "getPickListEmailHistoryRecords( )" );
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getPickListEmailHistorySql ();
		Vector lists = new Vector ();
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.registerOutParameter ( 1 , OracleTypes.CURSOR );
			cstmt.registerOutParameter ( 2 , java.sql.Types.INTEGER );
			cstmt.registerOutParameter ( 3 , java.sql.Types.VARCHAR );
			cstmt.execute ();
			rs = ( ResultSet ) cstmt.getObject ( 1 );
			while ( rs.next () )
			{
				EmailClient eh = new EmailClient ();
				eh.setEmail_history_id ( rs.getString ( "email_history_id" ) );
				eh.setEmail_client_id ( rs.getString ( "email_client_id" ) );
				eh.setOrder_number ( rs.getString ( "order_number" ) );
				lists.addElement ( eh );
			}
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java getPickListEmailHistoryRecords " + e.getMessage () );
		}
		finally
		{
			try
			{
				rs.close ();
				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java getPickListEmailHistoryRecords " + fin.getMessage () );
			}
			logger.exiting ( "getPickListEmailHistoryRecords( )" );
		}
		return ( EmailClient [] ) lists.toArray ( new EmailClient[0] );
	}

	/**
	 * populate enity OrderedItems for picklist emails.
	 *
	 * @param client
	 * @param order_number
	 * @return entity OrderedItems
	 */
	public OrderedItems [] getPickListOrderedItemsRecords (
															String client ,
															String order_number )
	{
		logger.entering ( "getPickListOrderedItemsRecords(String , String )" );
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getPickListOrderSql ();
		Vector lists = new Vector ();
		Order order = new Order ();
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , client );
			cstmt.setString ( 2 , order_number );
			cstmt.registerOutParameter ( 3 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 4 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 5 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 6 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 7 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 8 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 9 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 10 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 11 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 12 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 13 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 14 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 15 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 16 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 17 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 18 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 19 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 20 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 21 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 22 , OracleTypes.CURSOR );
			cstmt.registerOutParameter ( 23 , java.sql.Types.INTEGER );
			cstmt.registerOutParameter ( 24 , java.sql.Types.VARCHAR );
			cstmt.execute ();
			rs = ( ResultSet ) cstmt.getObject ( 22 );
			while ( rs.next () )
			{
				OrderedItems oi = new OrderedItems ();
				oi.setStock_number ( rs.getString ( "stock_number" ) );
				oi.setDescription ( rs.getString ( "description" ) );
				oi.setSequence_number ( rs.getString ( "sequence_number" ) );
				oi.setImage_name ( rs.getString ( "image_name" ) );
				oi.setQuantity_to_be_picked ( rs.getString ( "quantity_to_be_picked" ) );
				lists.addElement ( oi );
			}

		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java getPickListOrderedItemsRecords " + e.getMessage () );
		}
		finally
		{
			try
			{
				rs.close ();
				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java getPickListOrderedItemsRecords " + fin.getMessage () );
			}
			logger.exiting ( "getPickListOrderedItemsRecords(String , String )" );
		}
		return ( OrderedItems [] ) lists.toArray ( new OrderedItems[0] );
	}

	/**
	 * populate enity Order for picklist emails.
	 *
	 * @param client
	 * @param order_number
	 * @return entity Order
	 */
	public Order getPickListOrderRecords ( String client , String order_number )
	{
		logger.entering ( "getPickListOrderRecords(String , String )" );
		Connection conn = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getPickListOrderSql ();
		Vector lists = new Vector ();
		Order order = new Order ();
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , client );
			cstmt.setString ( 2 , order_number );
			cstmt.registerOutParameter ( 3 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 4 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 5 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 6 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 7 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 8 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 9 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 10 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 11 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 12 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 13 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 14 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 15 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 16 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 17 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 18 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 19 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 20 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 21 , java.sql.Types.VARCHAR );
			cstmt.registerOutParameter ( 22 , OracleTypes.CURSOR );
			cstmt.registerOutParameter ( 23 , java.sql.Types.INTEGER );
			cstmt.registerOutParameter ( 24 , java.sql.Types.VARCHAR );
			cstmt.execute ();

			order.setOrder_number ( cstmt.getString ( 3 ) );
			order.setJob_number ( StringUtility.displayNotAvailableForBlank ( cstmt.getString ( 4 ) ) );
			order.setOrder_date ( cstmt.getString ( 5 ) );
			order.setBusiness_partner ( StringUtility.displayNotAvailableForBlank ( cstmt.getString ( 6 ) ) );
			order.setBill_to_last_name ( StringUtility.displayNotAvailableForBlank ( cstmt.getString ( 7 ) ) );
			order.setBill_to_first_name ( StringUtility.displayNotAvailableForBlank ( cstmt.getString ( 8 ) ) );
			order.setBill_to_company ( StringUtility.displayNotAvailableForBlank ( cstmt.getString ( 9 ) ) );
			order.setBill_to_address1 ( cstmt.getString ( 10 ) );
			order.setBill_to_city ( cstmt.getString ( 11 ) );
			order.setBill_to_state_province ( cstmt.getString ( 12 ) );
			order.setBill_to_zip ( cstmt.getString ( 13 ) );
			order.setShip_to_business_partner ( StringUtility.displayNotAvailableForBlank ( cstmt.getString ( 14 ) ) );
			order.setShip_to_last_name ( StringUtility.displayNotAvailableForBlank ( cstmt.getString ( 15 ) ) );
			order.setShip_to_first_name ( StringUtility.displayNotAvailableForBlank ( cstmt.getString ( 16 ) ) );
			order.setShip_to_company ( StringUtility.displayNotAvailableForBlank ( cstmt.getString ( 17 ) ) );
			order.setShip_to_address1 ( cstmt.getString ( 18 ) );
			order.setShip_to_city ( cstmt.getString ( 19 ) );
			order.setShip_to_state_province ( cstmt.getString ( 20 ) );
			order.setShip_to_zip ( cstmt.getString ( 21 ) );

		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java getPickListOrderRecords " + e.getMessage () );
		}
		finally
		{
			try
			{

				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java getPickListOrderRecords " + fin.getMessage () );
			}
			logger.exiting ( "getPickListOrderRecords(String , String )" );
		}
		return order;
	}

	/**
	 * To get the Receiving Details.
	 *
	 * @param client
	 * @param noMenclatureID
	 * @param trans_id
	 * @return
	 */
	public Receiving getReceivingDetails (
											String client ,
											String noMenclatureID ,
											String trans_id )
	{
		logger.entering ( "getReceivingDetails(String,String,String  )" );
		Receiving rc = new Receiving ();
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getReceivingDetailsSql ();
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , client );
			cstmt.setString ( 2 , noMenclatureID );
			//cstmt.setString ( 2 , trans_id );
			cstmt.registerOutParameter ( 3 , OracleTypes.CURSOR );
			cstmt.registerOutParameter ( 4 , java.sql.Types.INTEGER );
			cstmt.execute ();

			rs = ( ResultSet ) cstmt.getObject ( 3 );
			while ( rs.next () )
			{
				rc.setStock_number ( rs.getString ( "stock_number" ) );
				rc.setDescription_for_www ( rs.getString ( "description_for_www" ) );
				rc.setQuantity_on_hand ( rs.getString ( "quantity_on_hand" ) );
				rc.setQuantity_received ( rs.getString ( "quantity_received" ) );
				rc.setTrans_quantity ( "0" );
				rc.setNomenclature_id ( rs.getString ( "nomenclature_id" ) );
				rc.setDescription ( rs.getString ( "description" ) );
				rc.setUofm_description ( rs.getString ( "uofm_description" ) );
				rc.setDate_received ( rs.getString ( "date_received" ) );
				rc.setSupplier ( rs.getString ( "supplier" ) );

			}
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java getReceivingDetails " + e.getMessage () );
		}
		finally
		{
			try
			{
				rs.close ();
				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java getReceivingDetails " + fin.getMessage () );
			}
			logger.exiting ( "getReceivingDetails(String,String,String  )" );
		}

		return rc;
	}

	/**
	 * gets special email address (Business logic)
	 *
	 * @param noMenclatureID
	 * @param emailType
	 * @return
	 */
	public EmailClientRecipient [] getSpecialEmailClientRecipient (
																	String noMenclatureID ,
																	String emailType )
	{
		logger.entering ( "getSpecialEmailClientRecipient(String , String )" );
		Connection conn = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getSpecialEmailClientRecipientSql ();
		Vector lists = new Vector ();
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , noMenclatureID );
			cstmt.setString ( 2 , emailType );
			cstmt.registerOutParameter ( 3 , OracleTypes.CURSOR );
			cstmt.registerOutParameter ( 4 , java.sql.Types.VARCHAR );
			cstmt.execute ();
			rs = ( ResultSet ) cstmt.getObject ( 3 );
			while ( rs.next () )
			{
				EmailClientRecipient ecr = new EmailClientRecipient ();
				ecr.setEmail_address ( rs.getString ( "email_address" ) );
				ecr.setRecipient_type ( rs.getString ( "recipient_type" ) );
				lists.addElement ( ecr );
			}
		}
		catch ( Exception e )
		{
			logger.debug ( "Error in DMLManager.java getSpecialEmailClientRecipient " + e.getMessage () );
		}
		finally
		{
			try
			{
				rs.close ();
				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java getSpecialEmailClientRecipient " + fin.getMessage () );
			}
			logger.exiting ( "getSpecialEmailClientRecipient(String , String )" );

		}
		return ( EmailClientRecipient [] ) lists.toArray ( new EmailClientRecipient[0] );

	}

	/**
	 * Sets Bars Email Headers
	 *
	 * @param ecr
	 * @return
	 */
	public Email setBarsEmailMessageHeader ( EmailClientRecipient ecr[] )
	{
		logger.entering ( "setBarsEmailMessageHeader(EmailClientRecipient  )" );
		Email email = new Email ();
		String to = "";
		String cc = "";
		String bcc = "";
		for ( int i = 0 ; i < ecr.length ; i++ )
		{
			if ( ecr [i].getEmail_address () != null && ecr [i].getEmail_address ().length () > 0 )
			{
				if ( ecr [i].getRecipient_type () != null && ecr [i].getRecipient_type ().length () >= 0 )
				{
					if ( ecr [i].getRecipient_type ().equalsIgnoreCase ( "T" ) )
					{
						to += ecr [i].getEmail_address () + ",";
					}
					if ( ecr [i].getRecipient_type ().equalsIgnoreCase ( "C" ) )
					{
						cc += ecr [i].getEmail_address () + ",";
					}
					if ( ecr [i].getRecipient_type ().equalsIgnoreCase ( "B" ) )
					{
						bcc += ecr [i].getEmail_address () + ",";
					}

				}
			}
		}
		if ( to.length () != 0 )
								email.setTo ( to.substring ( 0 , to.length () - 1 ) );
		if ( cc.length () != 0 )
								email.setCc ( cc.substring ( 0 , cc.length () - 1 ) );
		if ( bcc.length () != 0 )
									email.setBcc ( bcc.substring ( 0 , bcc.length () - 1 ) );

		//System.out.println("Emial to " + email.getTo());
		//System.out.println("Emial cc " + email.getCc());
		//System.out.println("Emial bcc " + email.getBcc());
		logger.exiting ( "setBarsEmailMessageHeader(EmailClientRecipient  )" );
		return email;
	}

	/**
	 * sets email header
	 *
	 * @param ecr
	 * @param order
	 * @return
	 */
	public Email setEmailMessageHeader (
										EmailClientRecipient ecr[] ,
										Order order )
	{
		logger.entering ( "setEmailMessageHeader( EmailClientRecipient,Order)" );
		Email email = new Email ();
		String to = "";
		String cc = "";
		String bcc = "";
		for ( int i = 0 ; i < ecr.length ; i++ )
		{
			if ( ecr [i].getEmail_address () != null && ecr [i].getEmail_address ().length () > 0 )
			{
				if ( ecr [i].getRecipient_type () != null && ecr [i].getRecipient_type ().length () >= 0 )
				{
					if ( ecr [i].getRecipient_type ().equalsIgnoreCase ( "T" ) )
					{
						to += ecr [i].getEmail_address () + ",";
					}
					if ( ecr [i].getRecipient_type ().equalsIgnoreCase ( "C" ) )
					{
						cc += ecr [i].getEmail_address () + ",";
					}
					if ( ecr [i].getRecipient_type ().equalsIgnoreCase ( "B" ) )
					{
						bcc += ecr [i].getEmail_address () + ",";
					}

				}
			}
			else
			{
				if ( ecr [i].getEmail_bp_type () != null && ecr [i].getEmail_bp_type ().length () >= 0 )
				{
					if ( ecr [i].getEmail_bp_type ().equalsIgnoreCase ( "B" ) )
					{
						if ( ecr [i].getRecipient_type ().equalsIgnoreCase ( "T" ) || ecr [i].getRecipient_type () == null )
						{
							to += order.getBill_to_email () + ",";
						}
						if ( ecr [i].getRecipient_type ().equalsIgnoreCase ( "C" ) )
						{
							cc += order.getBill_to_email () + ",";
						}
						if ( ecr [i].getRecipient_type ().equalsIgnoreCase ( "B" ) )
						{
							bcc += order.getBill_to_email () + ",";
						}

					}
					else
					{

						if ( ecr [i].getRecipient_type ().equalsIgnoreCase ( "T" ) )
						{
							to += order.getShip_to_email () + ",";
						}
						if ( ecr [i].getRecipient_type ().equalsIgnoreCase ( "C" ) )
						{
							cc += order.getShip_to_email () + ",";
						}
						if ( ecr [i].getRecipient_type ().equalsIgnoreCase ( "B" ) )
						{
							bcc += order.getShip_to_email () + ",";
						}

					}
				}
			}
		}
		if ( to.length () != 0 )
								email.setTo ( to.substring ( 0 , to.length () - 1 ) );
		if ( cc.length () != 0 )
								email.setCc ( cc.substring ( 0 , cc.length () - 1 ) );
		if ( bcc.length () != 0 )
									email.setBcc ( bcc.substring ( 0 , bcc.length () - 1 ) );

		//logger.warn("Emial to " + email.getTo());
		//logger.warn("Emial cc " + email.getCc());
		//logger.warn("Emial bcc " + email.getBcc());
		logger.exiting ( "setEmailMessageHeader( EmailClientRecipient,Order)" );
		return email;
	}

	/**
	 * sets email header for POD Emails.
	 *
	 * @param ecr
	 * @param order
	 * @return
	 */
	public Email setPickListEmailMessageHeader ( EmailClientRecipient ecr[] )
	{
		logger.entering ( "setPickListEmailMessageHeader(EmailClientRecipient  )" );
		Email email = new Email ();
		String to = "";
		String cc = "";
		String bcc = "";
		for ( int i = 0 ; i < ecr.length ; i++ )
		{
			if ( ecr [i].getEmail_address () != null && ecr [i].getEmail_address ().length () > 0 )
			{
				if ( ecr [i].getRecipient_type () != null && ecr [i].getRecipient_type ().length () >= 0 )
				{
					if ( ecr [i].getRecipient_type ().equalsIgnoreCase ( "T" ) )
					{
						to += ecr [i].getEmail_address () + ",";
					}
					if ( ecr [i].getRecipient_type ().equalsIgnoreCase ( "C" ) )
					{
						cc += ecr [i].getEmail_address () + ",";
					}
					if ( ecr [i].getRecipient_type ().equalsIgnoreCase ( "B" ) )
					{
						bcc += ecr [i].getEmail_address () + ",";
					}

				}
			}
		}
		if ( to.length () != 0 )
								email.setTo ( to.substring ( 0 , to.length () - 1 ) );
		if ( cc.length () != 0 )
								email.setCc ( cc.substring ( 0 , cc.length () - 1 ) );
		if ( bcc.length () != 0 )
									email.setBcc ( bcc.substring ( 0 , bcc.length () - 1 ) );
		logger.exiting ( "setPickListEmailMessageHeader(EmailClientRecipient  )" );
		return email;
	}

	/**
	 * sets special email headers
	 *
	 * @param ecr
	 * @return
	 */
	public Email setSpecialEmailMessageHeader ( EmailClientRecipient ecr[] )
	{
		logger.entering ( "setSpecialEmailMessageHeader( EmailClientRecipient)" );
		Email email = new Email ();
		String to = "";
		String cc = "";
		String bcc = "";
		for ( int i = 0 ; i < ecr.length ; i++ )
		{
			if ( ecr [i].getEmail_address () != null && ecr [i].getEmail_address ().length () > 0 )
			{
				if ( ecr [i].getRecipient_type () != null && ecr [i].getRecipient_type ().length () >= 0 )
				{
					if ( ecr [i].getRecipient_type ().equalsIgnoreCase ( "T" ) )
					{
						to += ecr [i].getEmail_address () + ",";
					}
					if ( ecr [i].getRecipient_type ().equalsIgnoreCase ( "C" ) )
					{
						cc += ecr [i].getEmail_address () + ",";
					}
					if ( ecr [i].getRecipient_type ().equalsIgnoreCase ( "B" ) )
					{
						bcc += ecr [i].getEmail_address () + ",";
					}
				}
			}
		}
		if ( to.length () != 0 )
								email.setTo ( to.substring ( 0 , to.length () - 1 ) );
		if ( cc.length () != 0 )
								email.setCc ( cc.substring ( 0 , cc.length () - 1 ) );
		if ( bcc.length () != 0 )
									email.setBcc ( bcc.substring ( 0 , bcc.length () - 1 ) );

		//System.out.println ( "Emial to " + email.getTo () );
		//System.out.println ( "Emial cc " + email.getCc () );
		//System.out.println ( "Emial bcc " + email.getBcc () );
		logger.exiting ( "setSpecialEmailMessageHeader( EmailClientRecipient)" );
		return email;
	}

	/**
	 * Updates Records processed
	 *
	 * @param email_history_id
	 * @param flag
	 * @return
	 */
	public boolean updateEmailHistory ( String email_history_id , String flag )
	{
		logger.entering ( "updateEmailHistory( String,String)" );
		Connection conn = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getUpdateEmailHistorySql ();
		boolean returnFlag = false;
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , email_history_id );
			cstmt.setString ( 2 , flag );
			cstmt.registerOutParameter ( 3 , OracleTypes.VARCHAR );
			returnFlag = cstmt.execute ();

		}
		catch ( Exception e )
		{
			returnFlag = false;
			logger.debug ( "Error in DMLManager.java updateEmailHistory " + e.getMessage () );
		}
		finally
		{
			try
			{

				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java updateEmailHistory " + fin.getMessage () );
			}
			logger.exiting ( "updateEmailHistory( String,String)" );
		}
		return returnFlag;
	}

	/**
	 * Update Only for Receiving Emails.
	 *
	 * @param email_history_id
	 * @param flag
	 * @return
	 */

	public boolean updateEmailHistoryReceiving (
												String nomenclatureID ,
												String flag ,
												String client )
	{
		logger.entering ( "updateEmailHistoryReceiving( String , String )" );
		Connection conn = null;
		CallableStatement cstmt = null;
		String sql = SQLHelper.getUpdateEmailHistoryReceivingSql ();
		boolean returnFlag = false;
		try
		{
			conn = theCache.getConnection ();
			cstmt = conn.prepareCall ( sql );
			cstmt.setString ( 1 , nomenclatureID );
			cstmt.setString ( 2 , flag );
			cstmt.setString ( 3 , client );

			cstmt.registerOutParameter ( 4 , OracleTypes.NUMBER );
			returnFlag = cstmt.execute ();

		}
		catch ( Exception e )
		{
			returnFlag = false;
			logger.debug ( "Error in DMLManager.java updateEmailHistoryReceiving " + e.getMessage () );
		}
		finally
		{

			try
			{

				cstmt.close ();
				conn.close ();
			}
			catch ( Exception fin )
			{
				logger.debug ( "FINALLY -- Error in DMLManager.java updateEmailHistoryReceiving " + fin.getMessage () );
			}
			logger.exiting ( "updateEmailHistoryReceiving( String , String )" );
		}
		return returnFlag;
	}

}
