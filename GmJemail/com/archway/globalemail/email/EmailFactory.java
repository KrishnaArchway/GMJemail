package com.archway.globalemail.email;

import com.archway.globalemail.common.ConfigurationServlet;
import com.archway.globalemail.common.Logger;
import com.archway.globalemail.entity.BackOrder;
import com.archway.globalemail.entity.BillingNotification;
import com.archway.globalemail.entity.Email;
import com.archway.globalemail.entity.LowWater;
import com.archway.globalemail.entity.Nomenclature;
import com.archway.globalemail.entity.Order;
import com.archway.globalemail.entity.OrderedItems;
import com.archway.globalemail.entity.OverLimit;
import com.archway.globalemail.entity.Packing;
import com.archway.globalemail.entity.Receiving;
import com.archway.globalemail.entity.ReorderPoint;
import com.archway.globalemail.util.DateUtility;
import com.archway.globalemail.util.EncrypterDecrypter;
import com.archway.globalemail.util.Html;
import com.archway.globalemail.util.StringUtility;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.text.MaskFormatter;

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
 * File Name               : EmailFactory.java
 * Package Name            : com.archway.globalemail.email
 *
 * Date                    : Oct 27, 2005 - 2:58:58 PM
 *
 * Change Date             : Mar 17, 2006 7:27:48 AM
 *                           04/04/2006
 *  						 Monday, March 27, 2006
 *                           Tuesday, May 16, 2006
 *                           Thursday, May 18, 2006
 * 							 May 24, 2006 12:58:27 PM
 * 						     May 24, 2006 1:03:30 PM
 * 							 May 24, 2006 1:04:07 PM
 * 							 May 24, 2006 1:04:12 PM
 * 							 May 24, 2006 1:04:17 PM
 *                           May 30, 2006 12:10:50 PM
 * 					         May 30, 2006 4:48:09 PM
 * 							 May 30, 2006 5:05:59 PM
 *                           Jun 2, 2006 6:37:31 PM
 *                           Oct 2, 2006 8:48:20 PM
 *                           Oct 2, 2006 8:53:28 PM
 *                           Oct 12, 2006 10:57:51 AM
 *                           Jan 10, 2007 9:31:19 AM
 *                           Mar 11, 2007 1:39:56 AM
 *                           Apr 10, 2007 3:05:01 PM
 *                           Jun 19, 2007 4:09:42 PM
 *							 Thursday, June 28, 2007
 *							 Jul 12, 2007 12:40:36 PM
 *							 Jul 24, 2007 12:09:40 AM
 *							 Aug 3, 2007 1:37:50 AM
 *							 Aug 3, 2007 1:53:36 AM
 *							 Aug 17, 2007 10:54:28 AM
 *							 Sep 17, 2007 11:45:24 PM
 *							 Dec 1, 2009 1:03:51 PM
 *							 Jul 23, 2014 10:39:57 AM
 *
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * EmailFactory Class reads the template and constructs email body.
 *
 */

public class EmailFactory
{

	/**
	 * final variable for tracking link for Airborne.
	 */
	public static final String	AIRBORNE_LINK							= "http://track.dhl-usa.com/TrackByNbr.asp?ShipmentNumber=";

	/**
	 * AVERAGE_MONTHLY_USAGE_QTY
	 */
	public static final String	AVERAGE_MONTHLY_USAGE_QTY				= "%average_monthly_usage_qty%";

	/**
	 * bill to address 1
	 */
	public static final String	BILL_ADDRESS1							= "%bill_address1%";

	/**
	 * bill to address 2
	 */
	public static final String	BILL_ADDRESS2							= "%bill_address2%";

	/**
	 * bill to address 3
	 */
	public static final String	BILL_ADDRESS3							= "%bill_address3%";

	/**
	 * bill to city
	 */
	public static final String	BILL_CITY								= "%bill_city%";

	/**
	 * bill to state
	 */
	public static final String	BILL_STATE								= "%bill_state%";

	/**
	 * BILL_TO_ADDRESS1
	 */
	public static final String	BILL_TO_ADDRESS1						= "%bill_to_address1%";

	/**
	 * BILL_TO_BP
	 */
	public static final String	BILL_TO_BP								= "%bill_to_bp%";

	/**
	 * BILL_TO_CITY
	 */
	public static final String	BILL_TO_CITY							= "%bill_to_city%";

	/**
	 * BILL_TO_DESTINATION
	 */
	public static final String	BILL_TO_DESTINATION						= "%bill_to_destination%";

	/**
	 * BILL_TO_FIRST_NAME
	 */
	public static final String	BILL_TO_FIRST_NAME						= "%bill_to_first_name%";

	/**
	 * BILL_TO_LAST_NAME
	 */
	public static final String	BILL_TO_LAST_NAME						= "%bill_to_last_name%";

	/**
	 * BILL_TO_STATE_PROVINCE
	 */
	public static final String	BILL_TO_STATE_PROVINCE					= "%bill_to_state_province%";

	/**
	 * BILL_TO_ZIP
	 */
	public static final String	BILL_TO_ZIP								= "%bill_to_zip%";

	/**
	 * BILL_ZIP
	 */
	public static final String	BILL_ZIP								= "%bill_zip%";

	/**
	 * BILLTO_SHIPTO_INFO
	 */
	public static final String	BILLTO_SHIPTO_INFO						= "%billto_shipto_info%";

	/**
	 * BILLTO_SHIPTO_INFO_DWD
	 */
	public static final String	BILLTO_SHIPTO_INFO_DWD					= "%billto_shipto_info_dwd%";

	/**
	 * BUSINESS_PARTNER_EMAIL
	 */
	public static final String	BUSINESS_PARTNER_EMAIL					= "%business_partner_email%";

	/**
	 * BUSINESS_PARTNER_ID
	 */
	public static final String	BUSINESS_PARTNER_ID						= "%business_partner_id%";

	/**
	 * BUSINESS_PARTNER_PHONE
	 */
	public static final String	BUSINESS_PARTNER_PHONE					= "%business_partner_phone%";

	/**
	 * BUSINESS_PARTNER_TYPE
	 */
	public static final String	BUSINESS_PARTNER_TYPE					= "%business_partner_type%";

	public static final String	CLIENT_ORDER_NUMBER						= "%client_order_number%";

	/**
	 * CREDIT_CARD_NUMBER
	 */
	public static final String	CREDIT_CARD_NUMBER						= "%credit_card_number%";

	/**
	 * CREDIT_CARD_TYPE
	 */
	public static final String	CREDIT_CARD_TYPE						= "%credit_card_type%";

	/**
	 * CREDIT_EXPIRATION_DATE
	 */
	public static final String	CREDIT_EXPIRATION_DATE					= "%credit_expiration_date%";

	/**
	 * CURRENT_INVENTORY_AVAILABLE
	 */
	public static final String	CURRENT_INVENTORY_AVAILABLE				= "%current_inventory_available%";

	/**
	 * CURRENT_INVENTORY_AVAILABLE_TO_ORDER
	 */
	public static final String	CURRENT_INVENTORY_AVAILABLE_TO_ORDER	= "%current_inventory_available_to_order%";

	/**
	 * CURRENT_INVENTORY_LEVEL
	 */
	public static final String	CURRENT_INVENTORY_LEVEL					= "%current_inventory_level%";

	/**
	 * DATE_CREATED
	 */
	public static final String	DATE_CREATED							= "%date_created%";

	/**
	 * DATE_RECEIVED
	 */
	public static final String	DATE_RECEIVED							= "%date_received%";

	/**
	 * DHL_LINK
	 */
	public static final String	DHL_LINK								= "http://track.dhl-usa.com/TrackByNbr.asp?ShipmentNumber=";

	/**
	 * DWD_ORDER_ITEMS
	 */
	public static final String	DWD_ORDER_ITEMS							= "%$dwd$%";

	//public static final String	FEDEX_LINK								= "http://www.fedex.com/cgi-bin/tracking?action=track&language=english&cntry_code=us&initial=x&tracknumbers=";

	/**
	 * EARLIEST_BACKORDER_DATE
	 */
	public static final String	EARLIEST_BACKORDER_DATE					= "%earliest_backorder_date%";

	/**
	 * EFULFILLMENT
	 */
	public static final String	EFULFILLMENT							= "%efulfillment%";

	/**
	 * EFULFILLMENT_REPEAT
	 */
	public static final String	EFULFILLMENT_REPEAT						= "%efulfillment_repeat%";

	/**
	 * ESTIMATED_ARRIVAL_DATE
	 */
	public static final String	ESTIMATED_ARRIVAL_DATE					= "%estimated_arrival_date%";

	/**
	 * FEDEX_LINK
	 */
	public static final String	FEDEX_LINK								= "http://fedex.com/Tracking?ascend_header=1&cntry_code=us&language=english&mps=y&action=track&tracknumbers=";

	/**
	 * FILE_NAME
	 */
	public static final String	FILE_NAME								= "%file_name%";

	/**
	 * IDEAL_MAXIMUM_QTY
	 */
	public static final String	IDEAL_MAXIMUM_QTY						= "%ideal_maximum_qty%";

	/**
	 * ITEM_TYPE
	 */
	public static final String	ITEM_TYPE								= "%item_type%";

	/**
	 * JOB_NUMBER
	 */
	public static final String	JOB_NUMBER								= "%job_number%";

	/**
	 * LAST_12_MONTH_USAGE_QTY
	 */
	public static final String	LAST_12_MONTH_USAGE_QTY					= "%last_12_month_usage_qty%";

	/**
	 * LATEST_BACKORDER_DATE
	 */
	public static final String	LATEST_BACKORDER_DATE					= "%latest_backorder_date%";

	/**
	 * LEAD_TIME
	 */
	public static final String	LEAD_TIME								= "%lead_time%";

	/**
	 * LIST_ORDER_ID
	 */
	public static final String	LIST_ORDER_ID							= "%list_order_id%";

	/**
	 * Logger
	 */
	private static Logger		logger									= Logger.getLogger ( EmailFactory.class );

	/**
	 * LOW_WATER_POINT
	 */
	public static final String	LOW_WATER_POINT							= "%low_water_point%";

	/**
	 * NUMBER_OF_INVOICE
	 */
	public static final String	NUMBER_OF_INVOICE						= "%number_of_invoice%";

	/**
	 * NUMBER_OF_ORDERS_ON_BACKORDER
	 */
	public static final String	NUMBER_OF_ORDERS_ON_BACKORDER			= "%number_of_orders_on_backorder%";

	/**
	 * ORDER_DATE
	 */
	public static final String	ORDER_DATE								= "%order_date%";

	/**
	 * ORDER_ITEMS
	 */
	public static final String	ORDER_ITEMS								= "%$$%";

	/**
	 * ORDER_LIMIT_MAXIMUM
	 */
	public static final String	ORDER_LIMIT_MAXIMUM						= "%order_limit_maximum%";

	/**
	 * ORDER_LIMIT_MINIMUM
	 */
	public static final String	ORDER_LIMIT_MINIMUM						= "%order_limit_minimum%";

	/**
	 * ORDER_NUMBER
	 */
	public static final String	ORDER_NUMBER							= "%order_number%";

	/**
	 * ORDER_PLACER
	 */
	public static final String	ORDER_PLACER							= "%order_placer%";

	/**
	 * OVERLIMIT
	 */
	public static final String	OVERLIMIT								= "%overlimit%";

	/**
	 * OVERLIMIT_DETAILS
	 */
	public static final String	OVERLIMIT_DETAILS						= "%overlimit_details%";

	/**
	 * PIECE_QUANTITY_ON_BACKORDER
	 */
	public static final String	PIECE_QUANTITY_ON_BACKORDER				= "%piece_quantity_on_backorder%";

	/**
	 * QUANTITY_ORDERED
	 */
	public static final String	QUANTITY_ORDERED						= "%quantity_ordered%";

	/**
	 * QUANTITY_RECEIVED
	 */
	public static final String	QUANTITY_RECEIVED						= "%quantity_received%";

	/**
	 * RE_ORDER_LEAD_TIME
	 */
	public static final String	RE_ORDER_LEAD_TIME						= "%re_order_lead_time%";

	/**
	 * Reason
	 */
	public static final String	REASON									= "%reason%";

	/**
	 * REMINDER_DATE
	 */
	public static final String	REMINDER_DATE							= "%reminder_date%";

	/**
	 * ROADWAY_LINK
	 */
	public static final String	ROADWAY_LINK							= "http://www.quiktrak.roadway.com/cgi-bin/quiktrak?pro0=";

	/**
	 * SATURN_ORDER_ITEMS
	 */
	public static final String	SATURN_ORDER_ITEMS						= "%$saturn$%";

	/**
	 * SATURN_ORDER_SHIPMENT_DETAIL
	 */
	public static final String	SATURN_ORDER_SHIPMENT_DETAIL			= "%saturn_order_shipment_detail%";

	/**
	 * SHIP_ADDRESS1
	 */
	public static final String	SHIP_ADDRESS1							= "%ship_address1%";

	/**
	 * SHIP_ADDRESS2
	 */
	public static final String	SHIP_ADDRESS2							= "%ship_address2%";

	/**
	 * SHIP_ADDRESS3
	 */
	public static final String	SHIP_ADDRESS3							= "%ship_address3%";

	/**
	 * SHIP_CITY
	 */
	public static final String	SHIP_CITY								= "%ship_city%";

	/**
	 * SHIP_STATE
	 */
	public static final String	SHIP_STATE								= "%ship_state%";

	/**
	 * SHIP_TO_ADDRESS1
	 */
	public static final String	SHIP_TO_ADDRESS1						= "%ship_to_address1%";

	/**
	 * SHIP_TO_BP
	 */
	public static final String	SHIP_TO_BP								= "%ship_to_bp%";

	/**
	 * SHIP_TO_CITY
	 */
	public static final String	SHIP_TO_CITY							= "%ship_to_city%";

	/**
	 * SHIP_TO_COMPANY
	 */
	public static final String	SHIP_TO_COMPANY							= "%ship_to_company%";

	/**
	 * SHIP_TO_DESTINATION
	 */
	public static final String	SHIP_TO_DESTINATION						= "%ship_to_destination%";

	/**
	 * SHIP_TO_FIRST_NAME
	 */
	public static final String	SHIP_TO_FIRST_NAME						= "%ship_to_first_name%";

	/**
	 * SHIP_TO_LAST_NAME
	 */
	public static final String	SHIP_TO_LAST_NAME						= "%ship_to_last_name%";

	/**
	 * SHIP_TO_STATE_PROVINCE
	 */
	public static final String	SHIP_TO_STATE_PROVINCE					= "%ship_to_state_province%";

	/**
	 * SHIP_TO_ZIP
	 */
	public static final String	SHIP_TO_ZIP								= "%ship_to_zip%";

	/**
	 * SHIP_ZIP
	 */
	public static final String	SHIP_ZIP								= "%ship_zip%";

	/**
	 * SHIPMENT_METHOD
	 */
	public static final String	SHIPMENT_METHOD							= "%shipment_method%";

	/**
	 * STOCK_DESCRIPTION
	 */
	public static final String	STOCK_DESCRIPTION						= "%stock_description%";

	/**
	 * STOCK_NUMBER
	 */
	public static final String	STOCK_NUMBER							= "%stock_number%";

	/**
	 * SUPPLIER
	 */
	public static final String	SUPPLIER								= "%supplier%";

	/**
	 * SYSDATE
	 */
	public static final String	SYSDATE									= "%sysdate%";

	/**
	 * TOTAL_AMOUNT
	 */
	public static final String	TOTAL_AMOUNT							= "%total_amount%";

	/**
	 * TOTAL_USAGE_QTY
	 */
	public static final String	TOTAL_USAGE_QTY							= "%total_usage_qty%";

	/**
	 * TRACKING_LINK
	 */
	public static final String	TRACKING_LINK							= "%tracking_link%";

	/**
	 * TRACKING_NUMBER
	 */
	public static final String	TRACKING_NUMBER							= "%tracking_number%";

	/**
	 * UNIT_OF_MEASURE_DESCRIPTION
	 */
	public static final String	UNIT_OF_MEASURE_DESCRIPTION				= "%unit_of_measure_description%";

	/**
	 * UPS_LINK
	 */
	public static final String	UPS_LINK								= "http://wwwapps.ups.com/WebTracking/processInputRequest?TypeOfInquiryNumber=T&InquiryNumber1=";

	/**
	 * UPS_VALUE_LINK
	 */
	public static final String	UPS_VALUE_LINK							= "%ups_link%";

	/**
	 * USPS_LINK
	 */
	public static final String	USPS_LINK								= "http://trkcnfrm1.smi.usps.com/PTSInternetWeb/InterLabelInquiry.do?origTrackNum=";

	public static final String	LAST_SHIPPED_DATE                       = "%last_shipped_date%";
	
	private static final String REORDER_POINT = "%re_order_point%";
	
	/**
	 * Contructs Email body for Back Order Email.
	 *
	 * @param FileName
	 * @param bo
	 * @param email
	 * @return
	 */
	public static Email contructBackOrderEmailBody (
													String FileName ,
													BackOrder bo ,
													Email email )
	{
		String str = "";
		BufferedReader in = null;
		try
		{
			in = new BufferedReader ( new FileReader ( FileName ) );
			StringBuffer body = new StringBuffer ();
			String temp = "";
			while ( ( str = in.readLine () ) != null )
			{
				temp = str;

				if ( str.trim ().equalsIgnoreCase ( CURRENT_INVENTORY_AVAILABLE ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( bo.getCurrent_inventory_available () );
				}
				if ( str.trim ().equalsIgnoreCase ( IDEAL_MAXIMUM_QTY ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( bo.getIdeal_maximum_qty () );
				}
				if ( str.trim ().equalsIgnoreCase ( UNIT_OF_MEASURE_DESCRIPTION ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( bo.getUnit_of_measure_description () );
				}
				if ( str.trim ().equalsIgnoreCase ( RE_ORDER_LEAD_TIME ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( bo.getReorder_lead_time () );
				}
				if ( str.trim ().equalsIgnoreCase ( PIECE_QUANTITY_ON_BACKORDER ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( bo.getPiece_quantity_on_backorder () );
				}
				if ( str.trim ().equalsIgnoreCase ( NUMBER_OF_ORDERS_ON_BACKORDER ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( bo.getNumber_of_orders_on_backorder () );
				}
				if ( str.trim ().equalsIgnoreCase ( LATEST_BACKORDER_DATE ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( bo.getLatest_backorder_date () );
				}
				if ( str.trim ().equalsIgnoreCase ( STOCK_NUMBER ) )
				{
					temp = bo.getStock_number ();
				}
				if ( str.trim ().equalsIgnoreCase ( STOCK_DESCRIPTION ) )
				{
					temp = bo.getDescription ();
				}
				if ( str.trim ().equalsIgnoreCase ( SUPPLIER ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( bo.getSupplier () );
				}
				if ( str.trim ().equalsIgnoreCase ( EARLIEST_BACKORDER_DATE ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( bo.getEarliest_backorder_date () );
				}
				body.append ( StringUtility.processNullToEmpty ( temp ) );
				str = "";
				temp = "";
			}
			logger.warn ( Html.cleanHtml ( body.toString () ) );
			email.setBody ( body.toString () );
		}
		catch ( Exception e )
		{
			e.printStackTrace ();
			logger.warn ( "Error in EmailBodyFactory.java contructBackOrderEmailBody " + e.getMessage () );
		}
		finally
		{
			try
			{
				in.close ();
			}
			catch ( Exception fin )
			{
				logger.warn ( "FINALLY -- Error in EmailBodyFactory.java contructBackOrderEmailBody " + fin.getMessage () );
			}
		}
		return email;
	}

	/**
	 * bar's email body
	 *
	 * @param FileName
	 * @param bn
	 * @param email
	 * @return
	 */
	public static Email contructBarsBillingEmailBody (
														String FileName ,
														BillingNotification bn ,
														Email email )
	{
		String str = "";
		BufferedReader in = null;
		try
		{
			in = new BufferedReader ( new FileReader ( FileName ) );
			StringBuffer body = new StringBuffer ();
			String temp = "";
			while ( ( str = in.readLine () ) != null )
			{
				temp = str;
				if ( str.trim ().equalsIgnoreCase ( FILE_NAME ) )
				{
					temp = bn.getFile_name ();
				}
				if ( str.trim ().equalsIgnoreCase ( NUMBER_OF_INVOICE ) )
				{
					temp = bn.getInvoice_count ();
				}
				if ( str.trim ().equalsIgnoreCase ( TOTAL_AMOUNT ) )
				{
					temp = StringUtility.displayDollars ( bn.getInvoice_total () );
				}

				body.append ( StringUtility.processNullToEmpty ( temp ) );
				str = "";
				temp = "";
			}
			logger.warn ( Html.cleanHtml ( body.toString () ) );
			email.setBody ( body.toString () );
		}
		catch ( Exception e )
		{
			e.printStackTrace ();
			logger.warn ( "Error in EmailBodyFactory.java contructBarsBillingEmailBody " + e.getMessage () );
		}
		finally
		{
			try
			{
				in.close ();
			}
			catch ( Exception fin )
			{
				logger.warn ( "FINALLY -- Error in EmailBodyFactory.java contructBarsBillingEmailBody " + fin.getMessage () );
			}
		}
		return email;

	}

	/**
	 * constructs email body for low water
	 *
	 * @param FileName
	 * @param lw
	 * @param email
	 * @return
	 */
	public static Email contructLowWaterEmailBody (
													String FileName ,
													LowWater lw ,
													Email email )
	{
		String str = "";
		BufferedReader in = null;
		try
		{
			in = new BufferedReader ( new FileReader ( FileName ) );
			StringBuffer body = new StringBuffer ();
			String temp = "";
			while ( ( str = in.readLine () ) != null )
			{
				temp = str;
				if ( str.trim ().equalsIgnoreCase ( STOCK_NUMBER ) )
				{
					temp = lw.getStock_number ();
				}
				if ( str.trim ().equalsIgnoreCase ( STOCK_DESCRIPTION ) )
				{
					temp = lw.getDescription ();
				}
				if ( str.trim ().equalsIgnoreCase ( LOW_WATER_POINT ) )
				{
					temp = lw.getLow_water_point ();
				}
				if ( str.trim ().equalsIgnoreCase ( CURRENT_INVENTORY_LEVEL ) )
				{
					temp = lw.getQuantity_on_hand ();
				}
				if ( str.trim ().equalsIgnoreCase ( CURRENT_INVENTORY_AVAILABLE_TO_ORDER ) )
				{
					temp = lw.getQuantity_pending ();
				}
				if ( str.trim ().equalsIgnoreCase ( LEAD_TIME ) )
				{
					temp = lw.getReorder_lead_time ();
				}
				if ( str.trim ().equalsIgnoreCase ( IDEAL_MAXIMUM_QTY ) )
				{
					temp = lw.getIdeal_max_qty ();
				}
				if ( str.trim ().equalsIgnoreCase ( SUPPLIER ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( lw.getCompany () );
				}
				if ( str.trim ().equalsIgnoreCase ( UNIT_OF_MEASURE_DESCRIPTION ) )
				{
					temp = lw.getUofm_description ();
				}
				if ( str.trim ().equalsIgnoreCase ( SYSDATE ) )
				{
					temp = DateUtility.getUSDate ();
				}
				if ( str.trim ().equalsIgnoreCase ( TOTAL_USAGE_QTY ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( lw.getTotal_usage () );
				}
				if ( str.trim ().equalsIgnoreCase ( LAST_12_MONTH_USAGE_QTY ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( lw.getYearly_usage () );
				}
				if ( str.trim ().equalsIgnoreCase ( AVERAGE_MONTHLY_USAGE_QTY ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( lw.getAve_monthly_usage () );
				}

				body.append ( StringUtility.processNullToEmpty ( temp ) );
				str = "";
				temp = "";
			}
			logger.warn ( Html.cleanHtml ( body.toString () ) );
			email.setBody ( body.toString () );
		}
		catch ( Exception e )
		{
			e.printStackTrace ();
			logger.warn ( "Error in EmailBodyFactory.java contructLowWaterEmailBody " + e.getMessage () );
		}
		finally
		{
			try
			{
				in.close ();
			}
			catch ( Exception fin )
			{
				logger.warn ( "FINALLY -- Error in EmailBodyFactory.java contructLowWaterEmailBody " + fin.getMessage () );
			}
		}
		return email;
	}


    /**
	 * 
	 * For Reorder point
	 */
	public static Email constructReorderPointEmailBody(
	        String fileName,
	        ReorderPoint reorderPoint,
	        Email email) {
	    String str = "";
	    BufferedReader in = null;
	    try {
	        in = new BufferedReader(new FileReader(fileName));
	        StringBuffer body = new StringBuffer();
	        String temp = "";
	        while ((str = in.readLine()) != null) {
	            temp = str;
	            if (str.trim().equalsIgnoreCase(STOCK_NUMBER)) {
	                temp = reorderPoint.getStock_number();
	            }
	            if (str.trim().equalsIgnoreCase(STOCK_DESCRIPTION)) {
	                temp = reorderPoint.getDescription();
	            }
	            if (str.trim().equalsIgnoreCase(REORDER_POINT)) {
	                temp = reorderPoint.getReorder_point();
	            }
	            if (str.trim().equalsIgnoreCase(CURRENT_INVENTORY_LEVEL)) {
	                temp = reorderPoint.getQuantity_on_hand();
	            }
	            if (str.trim().equalsIgnoreCase(CURRENT_INVENTORY_AVAILABLE_TO_ORDER)) {
	                temp = reorderPoint.getQuantity_pending();
	            }
	            if (str.trim().equalsIgnoreCase(LEAD_TIME)) {
	                temp = reorderPoint.getReorder_lead_time();
	            }
	            if (str.trim().equalsIgnoreCase(IDEAL_MAXIMUM_QTY)) {
	                temp = reorderPoint.getIdeal_max_qty();
	            }
	            if (str.trim().equalsIgnoreCase(SUPPLIER)) {
	                temp = StringUtility.displayNotAvailableForBlank(reorderPoint.getCompany());
	            }
	            if (str.trim().equalsIgnoreCase(UNIT_OF_MEASURE_DESCRIPTION)) {
	                temp = reorderPoint.getDescription();
	            }
	            if (str.trim().equalsIgnoreCase(SYSDATE)) {
	                temp = DateUtility.getUSDate();
	            }
	            if (str.trim().equalsIgnoreCase(TOTAL_USAGE_QTY)) {
	                temp = StringUtility.displayNotAvailableForBlank(reorderPoint.getTotal_usage());
	            }
	            if (str.trim().equalsIgnoreCase(LAST_12_MONTH_USAGE_QTY)) {
	                temp = StringUtility.displayNotAvailableForBlank(reorderPoint.getYearly_usage());
	            }
	            if ( str.trim ().equalsIgnoreCase ( AVERAGE_MONTHLY_USAGE_QTY ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( reorderPoint.getAve_monthly_usage () );
				}

	            body.append(StringUtility.processNullToEmpty(temp));
	            str = "";
	            temp = "";
	        }
	        logger.warn(Html.cleanHtml(body.toString()));
	        email.setBody(body.toString());
	    } catch (Exception e) {
	        e.printStackTrace();
	        logger.warn("Error in EmailBodyFactory.java constructReorderPointEmailBody " + e.getMessage());
	    } finally {
	        try {
	            if (in != null) {
	                in.close();
	            }
	        } catch (Exception fin) {
	            logger.warn("FINALLY -- Error in EmailBodyFactory.java constructReorderPointEmailBody " + fin.getMessage());
	        }
	    }
	    return email;
	}
	/**
	 * new item body
	 *
	 * @param FileName
	 * @param nc
	 * @param email
	 * @return
	 */
	public static Email contructNewItemSetupEmailBody (
														String FileName ,
														Nomenclature nc ,
														Email email )
	{
		String str = "";
		BufferedReader in = null;
		try
		{
			in = new BufferedReader ( new FileReader ( FileName ) );
			StringBuffer body = new StringBuffer ();
			String temp = "";
			while ( ( str = in.readLine () ) != null )
			{
				temp = str;
				if ( str.trim ().equalsIgnoreCase ( STOCK_NUMBER ) )
				{
					temp = nc.getStock_number ();
				}
				if ( str.trim ().equalsIgnoreCase ( STOCK_DESCRIPTION ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( nc.getDescription () );
				}
				if ( str.trim ().equalsIgnoreCase ( UNIT_OF_MEASURE_DESCRIPTION ) )
				{
					temp = nc.getUofm_description ();
				}
				if ( str.trim ().equalsIgnoreCase ( DATE_CREATED ) )
				{
					temp = nc.getDate_created ();
				}
				if ( str.trim ().equalsIgnoreCase ( ITEM_TYPE ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( nc.getItem_type () );
				}

				body.append ( StringUtility.processNullToEmpty ( temp ) );
				str = "";
				temp = "";
			}
			logger.warn ( Html.cleanHtml ( body.toString () ) );
			email.setBody ( Html.cleanHtml ( body.toString () ) );
		}
		catch ( Exception e )
		{
			e.printStackTrace ();
			logger.warn ( "Error in EmailFactory.java contructScrapEmailBody " + e.getMessage () );
		}
		finally
		{
			try
			{
				in.close ();
			}
			catch ( Exception fin )
			{
				logger.warn ( "FINALLY -- Error in EmailFactory.java contructLowWaterEmailBody " + fin.getMessage () );
			}
		}
		return email;
	}

	/**
	 * Builds body for the order email.
	 *
	 * @param FileName
	 * @param order
	 * @param orderedItems
	 * @param email
	 * @return
	 */
	public static Email contructOrderEmailBody (
												String FileName ,
												Order order ,
												OrderedItems orderedItems[] ,
												Email email ,
												boolean eFulFillmentFlag ,
												String client )
	{
		String str = "";
		BufferedReader in = null;
		//System.out.println("Order Number in EmailFactory   " + order.getOrder_number());
		try
		{
			in = new BufferedReader ( new FileReader ( FileName ) );
			StringBuffer body = new StringBuffer ();
			String temp = "";
			while ( ( str = in.readLine () ) != null )
			{
				temp = str;
				if ( str.trim ().equalsIgnoreCase ( ORDER_NUMBER ) )
				{
					temp = order.getOrder_number ();
				}
				if ( str.trim ().equalsIgnoreCase ( CLIENT_ORDER_NUMBER ) )
				{
					if ( str.trim ().equalsIgnoreCase ( CLIENT_ORDER_NUMBER ) )
					{
						if ( StringUtility.isStringBlank ( order.getClient_order_number () ) )
						{
							temp = "N/A";
						}
						else
						{
							temp = order.getClient_order_number ().toUpperCase ().replaceAll ( "FMR" , "" );
						}
					}
				}
				if ( str.trim ().equalsIgnoreCase ( ORDER_DATE ) )
				{
					temp = order.getOrder_date ();
				}
				if ( str.trim ().equalsIgnoreCase ( ORDER_ITEMS ) )
				{
					temp = getItemDetails ( orderedItems );
				}
				if ( str.trim ().equalsIgnoreCase ( DWD_ORDER_ITEMS ) )
				{
					temp = getDwdItemDetails ( orderedItems );
				}
				if ( str.trim ().equalsIgnoreCase ( SATURN_ORDER_ITEMS ) )
				{
					temp = getSaturnItemDetails ( orderedItems );
				}
				if ( str.trim ().equalsIgnoreCase ( BILLTO_SHIPTO_INFO ) )
				{
					temp = getBillTo_ShipTo_Info ( order );
				}
				if ( str.trim ().equalsIgnoreCase ( BILLTO_SHIPTO_INFO_DWD ) )
				{
					temp = getBillTo_ShipTo_Info_Dwd ( order );
				}
				if ( str.trim ().equalsIgnoreCase ( EFULFILLMENT ) && eFulFillmentFlag )
				{
					temp = getEFulFillmentLink ( order , client );
				}
				if ( str.trim ().equalsIgnoreCase ( EFULFILLMENT ) && ! eFulFillmentFlag )
				{
					temp = "";
				}
				if ( str.trim ().equalsIgnoreCase ( EFULFILLMENT_REPEAT ) && eFulFillmentFlag )
				{
					temp = getEFulFillmentRepeatLink ( order , client );
				}
				if ( str.trim ().equalsIgnoreCase ( EFULFILLMENT_REPEAT ) && ! eFulFillmentFlag )
				{
					temp = "";
				}

				if ( str.trim ().equalsIgnoreCase ( LIST_ORDER_ID ) )
				{
					temp = StringUtility.processNullToEmpty ( order.getList_order_id () );
				}

				body.append ( temp );
				str = "";
				temp = "";
			}
			logger.warn ( Html.cleanHtml ( body.toString () ) );
			email.setBody ( body.toString () );
		}
		catch ( Exception e )
		{
			e.printStackTrace ();
			logger.warn ( "Error in EmailBodyFactory.java contructEmailBody " + e.getMessage () );
		}
		finally
		{
			try
			{
				in.close ();
			}
			catch ( Exception fin )
			{
				logger.warn ( "FINALLY -- Error in EmailBodyFactory.java contructEmailBody " + fin.getMessage () );
			}
		}
		return email;
	}

	/**
	 * generates overlimit body.
	 *
	 * @param FileName
	 * @param ol
	 * @param order
	 * @param email
	 * @return
	 */

	public static Email contructOverLimitEmailBody (
													String FileName ,
													OverLimit ol ,
													Order order ,
													Email email ,
													String order_number )
	{
		String str = "";
		BufferedReader in = null;
		try
		{
			in = new BufferedReader ( new FileReader ( FileName ) );
			StringBuffer body = new StringBuffer ();
			String temp = "";
			while ( ( str = in.readLine () ) != null )
			{
				temp = str;
				if ( str.trim ().equalsIgnoreCase ( STOCK_NUMBER ) )
				{
					temp = ol.getStock_number ();
				}
				if ( str.trim ().equalsIgnoreCase ( ORDER_NUMBER ) )
				{
					temp = ol.getOrder_number ();
				}
				if ( str.trim ().equalsIgnoreCase ( ORDER_PLACER ) )
				{
					if ( StringUtility.isStringBlank ( ol.getBill_to_first_name () ) && StringUtility.isStringBlank ( ol.getBill_to_last_name () ) )
					{
						temp = StringUtility.displayNotAvailableForBlank ( "" );
					}
					else
					{
						temp = ol.getBill_to_first_name () + " " + ol.getBill_to_last_name ();
					}
				}
				if ( str.trim ().equalsIgnoreCase ( STOCK_DESCRIPTION ) )
				{
					temp = ol.getDescription ();
				}
				if ( str.trim ().equalsIgnoreCase ( QUANTITY_ORDERED ) )
				{
					temp = ol.getQuantity_to_be_shipped ();
				}
				if ( str.trim ().equalsIgnoreCase ( ORDER_LIMIT_MINIMUM ) )
				{
					temp = ol.getOrder_limit_minimum_qty ();
				}
				if ( str.trim ().equalsIgnoreCase ( ORDER_LIMIT_MAXIMUM ) )
				{
					temp = ol.getOrder_limit_maximum_qty ();
				}
				if ( str.trim ().equalsIgnoreCase ( OVERLIMIT ) )
				{
					temp = getOverLimitLink ( email.getTo () , order_number );
				}
				if ( str.trim ().equalsIgnoreCase ( OVERLIMIT_DETAILS ) )
				{
					temp = getOverLimitDetails ( order );
				}

				temp = StringUtility.displayNotAvailableForBlank ( temp );
				body.append ( temp );
				str = "";
				temp = "";
			}
			logger.warn ( Html.cleanHtml ( body.toString () ) );
			email.setBody ( body.toString () );
		}
		catch ( Exception e )
		{
			e.printStackTrace ();
			logger.warn ( "Error in EmailBodyFactory.java contructOverLimitEmailBody " + e.getMessage () );
		}
		finally
		{
			try
			{
				in.close ();
			}
			catch ( Exception fin )
			{
				logger.warn ( "FINALLY -- Error in EmailBodyFactory.java contructOverLimitEmailBody " + fin.getMessage () );
			}
		}
		return email;
	}

	/**
	 * constructs pod body
	 *
	 * @param FileName
	 * @param order
	 * @param orderedItems
	 * @param email
	 * @return
	 */
	public static Email contructPickListEmailBody (
													String FileName ,
													Order order ,
													OrderedItems orderedItems[] ,
													Email email )
	{
		String str = "";
		BufferedReader in = null;
		try
		{
			in = new BufferedReader ( new FileReader ( FileName ) );
			StringBuffer body = new StringBuffer ();
			String temp = "";
			while ( ( str = in.readLine () ) != null )
			{
				temp = str;
				if ( str.trim ().equalsIgnoreCase ( ORDER_NUMBER ) )
				{
					temp = order.getOrder_number ();
				}
				if ( str.trim ().equalsIgnoreCase ( ORDER_DATE ) )
				{
					temp = order.getOrder_date ();
				}
				if ( str.trim ().equalsIgnoreCase ( BILL_TO_BP ) )
				{
					temp = order.getBusiness_partner ();
				}
				if ( str.trim ().equalsIgnoreCase ( SHIP_TO_ADDRESS1 ) )
				{
					temp = order.getShip_to_address1 ();
				}
				if ( str.trim ().equalsIgnoreCase ( SHIP_TO_CITY ) )
				{
					temp = order.getShip_to_city ();
				}
				if ( str.trim ().equalsIgnoreCase ( SHIP_TO_STATE_PROVINCE ) )
				{
					temp = order.getShip_to_state_province ();
				}
				if ( str.trim ().equalsIgnoreCase ( SHIP_TO_ZIP ) )
				{
					temp = order.getShip_to_zip ();
				}
				if ( str.trim ().equalsIgnoreCase ( BILL_TO_ADDRESS1 ) )
				{
					temp = order.getBill_to_address1 ();
				}
				if ( str.trim ().equalsIgnoreCase ( BILL_TO_CITY ) )
				{
					temp = order.getBill_to_city ();
				}
				if ( str.trim ().equalsIgnoreCase ( BILL_TO_STATE_PROVINCE ) )
				{
					temp = order.getBill_to_state_province ();
				}
				if ( str.trim ().equalsIgnoreCase ( BILL_TO_ZIP ) )
				{
					temp = order.getBill_to_zip ();
				}
				if ( str.trim ().equalsIgnoreCase ( SHIP_TO_BP ) )
				{
					temp = order.getShip_to_business_partner ();
				}
				if ( str.trim ().equalsIgnoreCase ( JOB_NUMBER ) )
				{
					temp = order.getJob_number ();
				}
				if ( str.trim ().equalsIgnoreCase ( ORDER_ITEMS ) )
				{
					temp = getPickListItemDetails ( orderedItems );
				}
				body.append ( temp );
				str = "";
				temp = "";
			}
			logger.warn ( Html.cleanHtml ( Html.cleanHtml ( body.toString () ) ) );
			email.setBody ( body.toString () );
		}
		catch ( Exception e )
		{
			logger.warn ( "Error in EmailBodyFactory.java contructPickListEmailBody " + e.getMessage () );
		}
		finally
		{
			try
			{
				in.close ();
			}
			catch ( Exception fin )
			{
				logger.warn ( "FINALLY -- Error in EmailBodyFactory.java contructPickListEmailBody " + fin.getMessage () );
			}
		}
		return email;
	}

	/**
	 * consructs receiving email body
	 *
	 * @param FileName
	 * @param rc
	 * @param email
	 * @return
	 */
	public static Email contructReceivingEmailBody (
													String FileName ,
													Receiving rc ,
													Email email )
	{
		String str = "";
		BufferedReader in = null;
		try
		{
			in = new BufferedReader ( new FileReader ( FileName ) );
			StringBuffer body = new StringBuffer ();
			String temp = "";
			while ( ( str = in.readLine () ) != null )
			{
				temp = str;
				if ( str.trim ().equalsIgnoreCase ( STOCK_NUMBER ) )
				{
					temp = rc.getStock_number ();
				}
				if ( str.trim ().equalsIgnoreCase ( STOCK_DESCRIPTION ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( rc.getDescription () );
				}
				if ( str.trim ().equalsIgnoreCase ( QUANTITY_RECEIVED ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( rc.getQuantity_received () );
				}
				if ( str.trim ().equalsIgnoreCase ( CURRENT_INVENTORY_LEVEL ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( rc.getQuantity_on_hand () );
				}
				if ( str.trim ().equalsIgnoreCase ( UNIT_OF_MEASURE_DESCRIPTION ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( rc.getUofm_description () );
				}
				if ( str.trim ().equalsIgnoreCase ( DATE_RECEIVED ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( rc.getDate_received () );
				}
				if ( str.trim ().equalsIgnoreCase ( SUPPLIER ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( rc.getSupplier () );
				}

				StringUtility.processNullToEmpty ( temp );
				body.append ( temp );
				str = "";
				temp = "";
			}
			logger.warn ( Html.cleanHtml ( body.toString () ) );
			email.setBody ( body.toString () );
		}
		catch ( Exception e )
		{
			e.printStackTrace ();
			logger.warn ( "Error in EmailBodyFactory.java contructReceivingEmailBody " + e.getMessage () );
		}
		finally
		{
			try
			{
				in.close ();
			}
			catch ( Exception fin )
			{
				logger.warn ( "FINALLY -- Error in EmailBodyFactory.java contructReceivingEmailBody " + fin.getMessage () );
			}
		}
		return email;
	}

	/**
	 * rush email body
	 *
	 * @param FileName
	 * @param order
	 * @param email
	 * @return
	 */
	public static Email contructRushEmailBody (
												String FileName ,
												Order order ,
												Email email ,
												OrderedItems orderedItems[] )
	{
		String str = "";
		BufferedReader in = null;
		//System.out.println("FileName " + FileName);
		try
		{

			in = new BufferedReader ( new FileReader ( FileName ) );
			StringBuffer body = new StringBuffer ();
			String temp = "";
			while ( ( str = in.readLine () ) != null )
			{
				temp = str;
				if ( str.trim ().equalsIgnoreCase ( ORDER_PLACER ) )
				{
					temp = StringUtility.processNullToEmpty ( order.getBill_to_first_name () ) + " " + StringUtility.processNullToEmpty ( order.getBill_to_last_name () );
				}
				if ( str.trim ().equalsIgnoreCase ( ORDER_NUMBER ) )
				{
					temp = order.getOrder_number ();
				}
				if ( str.trim ().equalsIgnoreCase ( BUSINESS_PARTNER_TYPE ) ) //do
				{
					temp = order.getBusiness_partner_type ();
				}
				if ( str.trim ().equalsIgnoreCase ( BUSINESS_PARTNER_ID ) ) //do
				{
					temp = order.getBusiness_partner ();
				}
				if ( str.trim ().equalsIgnoreCase ( BUSINESS_PARTNER_EMAIL ) )
				{
					temp = order.getBill_to_email ();
				}
				if ( str.trim ().equalsIgnoreCase ( BUSINESS_PARTNER_PHONE ) )
				{
					temp = order.getBill_to_phone_number ();
				}
				if ( str.trim ().equalsIgnoreCase ( ORDER_ITEMS ) )
				{
					temp = getItemDetails4Rush ( orderedItems );
				}
				if ( str.trim ().equalsIgnoreCase ( REASON ) )
				{
					/*temp = "";
					 temp += " <table border=0 width=500 > ";
					 temp += " <tr> ";
					 temp += "       <td align=right width=250><font face=Arial size=2 valign=top> ";
					 temp += "       	<b> ";
					 temp += "      		 Reason :&nbsp; ";
					 temp += "      	</b> ";
					 temp += "       </font> ";
					 temp += "       <td align=left width=250><font face=Arial size=2> ";
					 temp += " " + order.getReason_comment ();
					 temp += "         </font> ";
					 temp += "       </td> ";
					 temp += " </tr> <table>";*/
					temp = StringUtility.processNullToEmpty ( order.getReason_comment () );
				}
				if ( str.trim ().equalsIgnoreCase ( SHIP_TO_DESTINATION ) )
				{
					temp = "";
					temp += " <table border=0 width=500> ";
					temp += " <tr> ";
					temp += "       <td align=right width=250><font face=Arial size=2 valign=top> ";
					temp += "       	<b> ";
					temp += "      		 Order Destination :&nbsp; ";
					temp += "      	</b> ";
					temp += "       </font> ";
					temp += "       <td align=left width=250><font face=Arial size=2> ";
					temp += " " + order.getShip_to_address1 ();
					temp += "         </font> ";
					temp += "       </td> ";
					temp += " </tr> ";
					if ( ! StringUtility.isStringBlank ( order.getShip_to_address2 () ) )
					{
						temp += " <tr> ";
						temp += "       <td align=right width=250><font face=Arial size=2> ";
						temp += "       	<b> ";
						temp += "      		 &nbsp; ";
						temp += "      	</b> ";
						temp += "       </font> ";
						temp += "       <td align=left width=250><font face=Arial size=2> ";
						temp += " " + order.getShip_to_address2 ();
						temp += "         </font> ";
						temp += "       </td> ";
						temp += " </tr> ";
					}
					if ( ! StringUtility.isStringBlank ( order.getShip_to_address3 () ) )
					{
						temp += " <tr> ";
						temp += "       <td align=right width=250><font face=Arial size=2> ";
						temp += "       	<b> ";
						temp += "      		 &nbsp; ";
						temp += "      	</b> ";
						temp += "       </font> ";
						temp += "       <td align=left width=250><font face=Arial size=2> ";
						temp += " " + order.getShip_to_address3 ();
						temp += "         </font> ";
						temp += "       </td> ";
						temp += " </tr> ";
					}
					if ( ! StringUtility.isStringBlank ( order.getShip_to_city () ) )
					{
						temp += " <tr> ";
						temp += "       <td align=right width=250><font face=Arial size=2> ";
						temp += "       	<b> ";
						temp += "      		 &nbsp; ";
						temp += "      	</b> ";
						temp += "       </font> ";
						temp += "       <td align=left width=250><font face=Arial size=2> ";
						temp += " " + order.getShip_to_city ();
						temp += "         </font> ";
						temp += "       </td> ";
						temp += " </tr> ";
					}
					if ( ! StringUtility.isStringBlank ( order.getShip_to_state_province () ) )
					{
						temp += " <tr> ";
						temp += "       <td align=right width=250><font face=Arial size=2> ";
						temp += "       	<b> ";
						temp += "      		 &nbsp; ";
						temp += "      	</b> ";
						temp += "       </font> ";
						temp += "       <td align=left width=250><font face=Arial size=2> ";
						temp += " " + order.getShip_to_state_province ();
						temp += "         </font> ";
						temp += "       </td> ";
						temp += " </tr> ";
					}
					if ( ! StringUtility.isStringBlank ( order.getShip_to_zip () ) )
					{
						temp += " <tr> ";
						temp += "       <td align=right width=250><font face=Arial size=2> ";
						temp += "       	<b> ";
						temp += "      		 &nbsp; ";
						temp += "      	</b> ";
						temp += "       </font> ";
						temp += "       <td align=left width=250><font face=Arial size=2> ";
						temp += " " + order.getShip_to_zip ();
						temp += "         </font> ";
						temp += "       </td> ";
						temp += " </tr> ";
					}
					temp += "</table>";
				}

				body.append ( StringUtility.processNullToEmpty ( temp ) );
				str = "";
				temp = "";
			}
			logger.warn ( Html.cleanHtml ( body.toString () ) );
			email.setBody ( Html.cleanHtml ( body.toString () ) );
		}
		catch ( Exception e )
		{
			e.printStackTrace ();
			logger.warn ( "Error in EmailBodyFactory.java contructRushEmailBody " + e.getMessage () );
		}
		finally
		{
			try
			{
				in.close ();
			}
			catch ( Exception fin )
			{
				logger.warn ( "FINALLY -- Error in EmailBodyFactory.java contructRushEmailBody " + fin.getMessage () );
			}
		}
		return email;
	}

	/**
	 * contruct Scrap Email Body
	 *
	 * @param FileName
	 * @param nc
	 * @param email
	 * @return
	 */
	public static Email contructScrapEmailBody (
												String FileName ,
												Nomenclature nc ,
												Email email )
	{
		String str = "";
		BufferedReader in = null;
		try
		{
			in = new BufferedReader ( new FileReader ( FileName ) );
			StringBuffer body = new StringBuffer ();
			String temp = "";
			while ( ( str = in.readLine () ) != null )
			{
				temp = str;
				if ( str.trim ().equalsIgnoreCase ( STOCK_NUMBER ) )
				{
					temp = nc.getStock_number ();
				}
				if ( str.trim ().equalsIgnoreCase ( STOCK_DESCRIPTION ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( nc.getDescription () );
				}
				if ( str.trim ().equalsIgnoreCase ( UNIT_OF_MEASURE_DESCRIPTION ) )
				{
					temp = nc.getUofm_description ();
				}
				if ( str.trim ().equalsIgnoreCase ( REMINDER_DATE ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( nc.getReview_for_scrap_date () );
				}
				if ( str.trim ().equalsIgnoreCase ( CURRENT_INVENTORY_AVAILABLE ) )
				{
					temp = nc.getAvailableQty ();
				}
				if ( str.trim ().equalsIgnoreCase ( IDEAL_MAXIMUM_QTY ) )
				{
					temp = nc.getIdeal_max_qty ();
				}
				if ( str.trim ().equalsIgnoreCase ( LAST_SHIPPED_DATE ) )
				{
					temp = StringUtility.displayNotAvailableForBlank ( nc.getLast_shipped_date () );
				}

				body.append ( StringUtility.processNullToEmpty ( temp ) );
				str = "";
				temp = "";
			}
			logger.warn ( Html.cleanHtml ( body.toString () ) );
			email.setBody ( Html.cleanHtml ( body.toString () ) );
		}
		catch ( Exception e )
		{
			e.printStackTrace ();
			logger.warn ( "Error in EmailFactory.java contructScrapEmailBody " + e.getMessage () );
		}
		finally
		{
			try
			{
				in.close ();
			}
			catch ( Exception fin )
			{
				logger.warn ( "FINALLY -- Error in EmailFactory.java contructLowWaterEmailBody " + fin.getMessage () );
			}
		}
		return email;
	}

	/**
	 * Builds body for the order emial.
	 *
	 * @param fileLocation
	 * @param order
	 * @param orderedItems
	 * @param email
	 * @return
	 */
	public static Email contructShipmentEmailBody (
													String FileName ,
													Order order ,
													OrderedItems orderedItems[] ,
													Email email ,
													Packing packing )
	{
		String str = "";
		BufferedReader in = null;
		try
		{
			in = new BufferedReader ( new FileReader ( FileName ) );
			StringBuffer body = new StringBuffer ();
			String temp = "";
			while ( ( str = in.readLine () ) != null )
			{
				temp = str;
				if ( str.trim ().equalsIgnoreCase ( ORDER_NUMBER ) )
				{
					temp = order.getOrder_number ();
				}
				if ( str.trim ().equalsIgnoreCase ( CLIENT_ORDER_NUMBER ) )
				{
					if ( StringUtility.isStringBlank ( order.getClient_order_number () ) )
					{
						temp = "N/A";
					}
					else
					{
						temp = order.getClient_order_number ().toUpperCase ().replaceAll ( "FMR" , "" );
					}
				}
				if ( str.trim ().equalsIgnoreCase ( ORDER_DATE ) )
				{
					temp = order.getOrder_date ();
				}
				if ( str.trim ().equalsIgnoreCase ( ORDER_ITEMS ) )
				{
					temp = getShipmentItemDetails ( orderedItems );
				}
				if ( str.trim ().equalsIgnoreCase ( ORDER_ITEMS ) )
				{
					temp = getItemDetails ( orderedItems );
				}
				if ( str.trim ().equalsIgnoreCase ( DWD_ORDER_ITEMS ) )
				{
					temp = getDwdItemDetails ( orderedItems );
				}
				if ( str.trim ().equalsIgnoreCase ( SATURN_ORDER_ITEMS ) )
				{
					temp = getSaturnItemDetails ( orderedItems );
				}
				if ( str.trim ().equalsIgnoreCase ( BILLTO_SHIPTO_INFO ) )
				{
					temp = getBillTo_ShipTo_Info ( order );
				}
				if ( str.trim ().equalsIgnoreCase ( SHIP_TO_COMPANY ) )
				{
					temp = order.getShip_to_company ();
				}
				if ( str.trim ().equalsIgnoreCase ( SHIPMENT_METHOD ) )
				{
					temp = StringUtility.isStringBlank ( packing.getActual_carrier () ) ? "Currently not available" : packing.getActual_carrier ();
				}
				if ( str.trim ().equalsIgnoreCase ( ESTIMATED_ARRIVAL_DATE ) )
				{
					temp = StringUtility.isStringBlank ( packing.getArrive_date () ) ? "Currently not available" : packing.getArrive_date ();
				}
				if ( str.trim ().equalsIgnoreCase ( TRACKING_NUMBER ) )
				{
					temp = packing.getTracking_number () == null ? "Currently not available" : getDecideTrackingLink ( packing , "" );
				}
				if ( str.trim ().equalsIgnoreCase ( TRACKING_LINK ) )
				{
					//do  temp = getDecideTrackingLink ( packing );

				}
				if ( str.trim ().equalsIgnoreCase ( UPS_VALUE_LINK ) )
				{
					temp = packing.getTracking_number () == null ? "http://www.ups.com" : UPS_LINK + packing.getTracking_number ();
				}
				if ( str.trim ().equalsIgnoreCase ( SATURN_ORDER_SHIPMENT_DETAIL ) )
				{
					temp = getStaturnOrderShipmentDetail ( order , orderedItems );
				}
				if ( str.trim ().equalsIgnoreCase ( CREDIT_CARD_TYPE ) )
				{
					temp = order.getPayment_type ();
				}
				if ( str.trim ().equalsIgnoreCase ( CREDIT_CARD_NUMBER ) )
				{
					temp = StringUtility.processNullToEmpty ( order.getCredit_card_number () );
				}
				if ( str.trim ().equalsIgnoreCase ( CREDIT_EXPIRATION_DATE ) )
				{
					temp = order.getCredit_card_expiration_date ();
				}

				if ( str.trim ().equalsIgnoreCase ( SHIP_TO_FIRST_NAME ) )
				{
					temp = StringUtility.processNullToEmpty ( order.getShip_to_first_name () );
				}

				if ( str.trim ().equalsIgnoreCase ( SHIP_TO_LAST_NAME ) )
				{
					temp = StringUtility.processNullToEmpty ( order.getShip_to_last_name () );
				}
				//LIST_ORDER_ID
				if ( str.trim ().equalsIgnoreCase ( LIST_ORDER_ID ) )
				{
					temp = StringUtility.processNullToEmpty ( order.getList_order_id () );
				}
				if ( str.trim ().equalsIgnoreCase ( BILLTO_SHIPTO_INFO_DWD ) )
				{
					temp = getBillTo_ShipTo_Info_Dwd ( order );
				}

				body.append ( temp );
				str = "";
				temp = "";
			}
			logger.warn ( Html.cleanHtml ( body.toString () ) );
			email.setBody ( body.toString () );
		}
		catch ( Exception e )
		{
			logger.warn ( "Error in EmailBodyFactory.java contructShipmentEmailBody " + e.getMessage () );
		}
		finally
		{
			try
			{
				in.close ();
			}
			catch ( Exception fin )
			{
				logger.warn ( "FINALLY -- Error in EmailBodyFactory.java contructShipmentEmailBody " + fin.getMessage () );
			}
		}
		return email;
	}

	/**
	 * Converts a given String to Array with "," in it.
	 *
	 * @param in
	 * @return
	 */
	public static String [] convertComma2Array ( String in )
	{
		return in.split ( "," );
	}

	/**
	 * check if all the mandatory parameters present in entity Email.
	 *
	 * @param email
	 * @return boolean
	 */
	public static boolean decideToSend ( Email email )
	{
		boolean returnFlag = true;

		logger.warn ( "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
		logger.warn ( "sender " + email.getFrom () );
		logger.warn ( "to " + email.getTo () );
		logger.warn ( "subject " + email.getSubject () );
		logger.warn ( "body " + email.getBody () );
		logger.warn ( "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );

		//from
		if ( StringUtility.isStringBlank ( email.getFrom () ) )
		{
			return false;
		}
		//to
		if ( StringUtility.isStringBlank ( email.getTo () ) )
		{
			return false;
		}
		//subject
		if ( StringUtility.isStringBlank ( email.getSubject () ) )
		{
			return false;
		}
		//body
		if ( StringUtility.isStringBlank ( email.getBody () ) )
		{
			return false;
		}

		/*if ((email.getTo() != null && email.getTo().length() > 0) && (email.getSubject() != null && email.getSubject().length() > 0)
		 && (email.getFrom() != null && email.getFrom().length() > 0) && (email.getBody() != null && email.getBody().length() > 0))
		 {
		 returnFlag = true;
		 }*/
		return returnFlag;
	}

	/**
	 * add bill to ship to information in email body
	 *
	 * @param order
	 * @return
	 */
	public static String getBillTo_ShipTo_Info ( Order order )
	{
		String returnString = "";
		returnString += "  <table border=\"0\" width=\"500\">   ";
		returnString += "    <tr> ";
		returnString += "      <td width=\"85\"></td> ";
		returnString += "      <td width=\"207\" nowrap align=\"left\"><font face=\"Arial\" size=\"2\"><b>Bill to ";
		returnString += "        Information</b></font></td> ";
		returnString += "      <td width=\"207\" nowrap align=\"left\"><font face=\"Arial\" size=\"2\"><b>Ship to ";
		returnString += "        Information</b></font></td> ";
		returnString += "    </tr> ";
		returnString += "    <tr> ";
		returnString += "      <td  align=\"right\" nowrap width=\"85\"><font face=\"Arial\" size=\"2\"><b>Name :&nbsp;</b></font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getBill_to_first_name () ) + " " + StringUtility.processNullToEmpty ( order.getBill_to_last_name () ) + "</font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getShip_to_first_name () ) + " " + StringUtility.processNullToEmpty ( order.getShip_to_last_name () ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += "    <tr> ";
		returnString += "      <td  align=\"right\"  nowrap width=\"85\"><font face=\"Arial\" size=\"2\"><b>Address1 :&nbsp;</b></font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getBill_to_address1 () ) + "</font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getShip_to_address1 () ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += "    <tr> ";
		returnString += "      <td  align=\"right\"  nowrap width=\"85\"><font face=\"Arial\" size=\"2\"><b>Address2 :&nbsp;</b></font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getBill_to_address2 () ) + "</font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getShip_to_address2 () ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += "    <tr> ";
		returnString += "      <td  align=\"right\"  nowrap width=\"85\"><font face=\"Arial\" size=\"2\"><b>City :&nbsp;</b></font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getBill_to_city () ) + "</font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getShip_to_city () ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += "    <tr> ";
		returnString += "      <td  align=\"right\"  nowrap width=\"85\"><font face=\"Arial\" size=\"2\"><b>State :&nbsp;</b></font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getBill_to_state_province () ) + "</font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getShip_to_state_province () ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += "    <tr> ";
		returnString += "      <td  align=\"right\"  nowrap width=\"85\"><font face=\"Arial\" size=\"2\"><b>Zip :&nbsp;</b></font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getBill_to_zip () ) + "</font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getShip_to_zip () ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += "    <tr> ";
		returnString += "      <td  align=\"right\"  nowrap width=\"85\"><font face=\"Arial\" size=\"2\"><b>Country :&nbsp;&nbsp;</b></font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getBill_to_country () ) + "</font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getShip_to_country () ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += "    <tr> ";
		returnString += "      <td  align=\"right\"  nowrap width=\"85\"><font face=\"Arial\" size=\"2\"><b>Phone :&nbsp;</b></font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( StringUtility.FormatPhone ( order.getBill_to_phone_number () ) ) + "</font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( StringUtility.FormatPhone ( order.getShip_to_phone_number () ) ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += "  </table> ";

		return returnString;
	}

	/**
	 * Custom to add the company name in the order email
	 * Emial_History.Email_Client_ID in (42,43,46,47,48,49,50,51,52,53)
	 *
	 * Method Added on : Oct 2, 2006 8:52:35 PM
	 *
	 * @param order
	 * @return
	 */
	public static String getBillTo_ShipTo_Info_Dwd ( Order order )
	{
		String returnString = "";
		returnString += "  <table border=\"0\" width=\"500\">   ";
		returnString += "    <tr> ";
		returnString += "      <td width=\"85\"></td> ";
		returnString += "      <td width=\"207\" nowrap align=\"left\"><font face=\"Arial\" size=\"2\"><b>Bill to ";
		returnString += "        Information</b></font></td> ";
		returnString += "      <td width=\"207\" nowrap align=\"left\"><font face=\"Arial\" size=\"2\"><b>Ship to ";
		returnString += "        Information</b></font></td> ";
		returnString += "    </tr> ";
		returnString += "    <tr> ";
		returnString += "      <td  align=\"right\" nowrap width=\"85\"><font face=\"Arial\" size=\"2\"><b>Company :&nbsp;</b></font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getBill_to_company () ) + "</font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getShip_to_company () ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += "    <tr> ";
		returnString += "      <td  align=\"right\" nowrap width=\"85\"><font face=\"Arial\" size=\"2\"><b>Name :&nbsp;</b></font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getBill_to_first_name () ) + " " + StringUtility.processNullToEmpty ( order.getBill_to_last_name () ) + "</font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getShip_to_first_name () ) + " " + StringUtility.processNullToEmpty ( order.getShip_to_last_name () ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += "    <tr> ";
		returnString += "      <td  align=\"right\"  nowrap width=\"85\"><font face=\"Arial\" size=\"2\"><b>Address1 :&nbsp;</b></font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getBill_to_address1 () ) + "</font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getShip_to_address1 () ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += "    <tr> ";
		returnString += "      <td  align=\"right\"  nowrap width=\"85\"><font face=\"Arial\" size=\"2\"><b>Address2 :&nbsp;</b></font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getBill_to_address2 () ) + "</font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getShip_to_address2 () ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += "    <tr> ";
		returnString += "      <td  align=\"right\"  nowrap width=\"85\"><font face=\"Arial\" size=\"2\"><b>City :&nbsp;</b></font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getBill_to_city () ) + "</font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getShip_to_city () ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += "    <tr> ";
		returnString += "      <td  align=\"right\"  nowrap width=\"85\"><font face=\"Arial\" size=\"2\"><b>State :&nbsp;</b></font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getBill_to_state_province () ) + "</font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getShip_to_state_province () ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += "    <tr> ";
		returnString += "      <td  align=\"right\"  nowrap width=\"85\"><font face=\"Arial\" size=\"2\"><b>Zip :&nbsp;</b></font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getBill_to_zip () ) + "</font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getShip_to_zip () ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += "    <tr> ";
		returnString += "      <td  align=\"right\"  nowrap width=\"85\"><font face=\"Arial\" size=\"2\"><b>Country :&nbsp;&nbsp;</b></font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getBill_to_country () ) + "</font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( order.getShip_to_country () ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += "    <tr> ";
		returnString += "      <td  align=\"right\"  nowrap width=\"85\"><font face=\"Arial\" size=\"2\"><b>Phone :&nbsp;</b></font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( StringUtility.FormatPhone ( order.getBill_to_phone_number () ) ) + "</font></td> ";
		returnString += "      <td  align=\"left\" width=\"207\"><font size=\"2\" face=\"Arial\">" + StringUtility.processNullToEmpty ( StringUtility.FormatPhone ( order.getShip_to_phone_number () ) ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += "  </table> ";

		return returnString;
	}

	/**
	 * adds tracking link in shipment email.
	 *
	 * @param packing
	 * @return
	 */
	public static String getDecideTrackingLink ( Packing packing )
	{
		String returnString = "";
		if ( packing.getActual_carrier ().equalsIgnoreCase ( "UPS" ) )
		{
			returnString += " <table border=0 width=500>   ";
			returnString += "   <tr>			       ";
			returnString += "     <td><font face=Arial size=2>";
			returnString += "      <a href=" + UPS_LINK + packing.getTracking_number ();
			returnString += "       target=_blank>UPS Tracking</a></font>";
			returnString += " </td>						   ";
			returnString += "     <td></td>					     ";
			returnString += "   </tr>					       ";
			returnString += " <table>						 ";
		}
		else
		{
			if ( packing.getActual_carrier ().equalsIgnoreCase ( "FEDEX" ) )
			{
				returnString += " <table border=0 width=500>   ";
				returnString += "   <tr>			       ";
				returnString += "     <td><font face=Arial size=2>";
				returnString += "      <a href=" + FEDEX_LINK + packing.getTracking_number ();
				returnString += "       target=_blank>FEDEX Tracking</a></font>";
				returnString += " </td>						   ";
				returnString += "     <td></td>					     ";
				returnString += "   </tr>					       ";
				returnString += " <table>						 ";

			}
			else
			{
				/*returnString += " <table border=0 width=500>   ";
				 returnString += "   <tr>			       ";
				 returnString += "     <td><font face=Arial size=2>";
				 returnString += "      <a href=" + FEDEX_LINK + packing.getTracking_number ();
				 returnString += "       target=_blank>FEDEX Tracking</a></font>";
				 returnString += " </td>						   ";
				 returnString += "     <td></td>					     ";
				 returnString += "   </tr>					       ";
				 returnString += " <table>						 ";*/
				returnString = "";
			}
		}
		return returnString;
	}

	/**
	 * overloaded method for tracking number link.
	 *
	 * @param packing
	 * @param temp
	 * @return
	 */

	public static String getDecideTrackingLink ( Packing packing , String temp )
	{
		String returnString = "";
		if ( packing.getActual_carrier ().equalsIgnoreCase ( "UPS" ) )
		{
			returnString += "      <a href=" + UPS_LINK + packing.getTracking_number ();
			returnString += "       target=_blank>" + packing.getTracking_number () + "</a></font><br>";
		}
		else
		{
			if ( packing.getActual_carrier ().equalsIgnoreCase ( "FEDEX" ) )
			{
				returnString += "      <a href=" + FEDEX_LINK + packing.getTracking_number ();
				returnString += "       target=_blank>" + packing.getTracking_number () + "</a></font><br>";
			}
			else
			{
				returnString = packing.getTracking_number () + "<br>";
			}
		}
		return returnString;
	}

	/**
	 * constructs dwd orders items.
	 *
	 * @param orderedItems
	 * @return
	 */
	public static String getDwdItemDetails ( OrderedItems orderedItems[] )
	{
		//System.out.println("length getDwdItemDetails  1   " + orderedItems.length);
		double tot = 0;
		String returnString = "";
		returnString += " <table border=1 width=509> ";
		returnString += " <tbody> ";
		returnString += "   <tr> ";
		returnString += "     <td align=center width=82 valign=top ><font face=Arial size=2><b>Item Number</b></font></td> ";
		returnString += "     <td align=center width=145 valign=top ><font face=Arial size=2><b>Item Description</b></font></td> ";
		returnString += "     <td align=center width=87 valign=top ><font face=Arial size=2><b>Qty Ordered</b></font></td> ";
		returnString += "     <td align=center width=87 valign=top ><font face=Arial size=2><b>Qty Backordered</b></font></td> ";
		returnString += "     <td align=center width=87 valign=top ><font face=Arial size=2><b>Qty Cancelled</b></font></td> ";
		returnString += "     <td align=center width=90 valign=top ><font face=Arial size=2><b>Unit Price</b></font></td> ";
		returnString += "     <td align=center width=90 valign=top ><font face=Arial size=2><b>Ext. Amount</b></font></td> ";
		returnString += "    </tr> ";
		for ( int i = 0 ; i < orderedItems.length ; i++ )
		{
			//System.out.println("here " + i);
			returnString += "   <tr> ";
			returnString += "     <td align=center width=82 valign=top ><font face=Arial size=2>" + orderedItems [i].getStock_number () + "</font></td> ";
			returnString += "     <td align=center width=145 valign=top ><font face=Arial size=2>" + orderedItems [i].getDescription () + "</font></td> ";
			returnString += "     <td align=center width=87 valign=top ><font face=Arial size=2>" + orderedItems [i].getQuantity_ordered () + "</font></td> ";
			returnString += "     <td align=center width=87 valign=top ><font face=Arial size=2>" + orderedItems [i].getQuantity_backOrdered () + "</font></td> ";
			returnString += "     <td align=center width=87 valign=top ><font face=Arial size=2>" + orderedItems [i].getQuantity_cancelled () + "</font></td> ";
			returnString += "     <td align=center width=90 valign=top ><font face=Arial size=2>" + StringUtility.displayDollars ( orderedItems [i].getUnit_price () ) + "</font></td> ";
			returnString += "     <td align=center width=90 valign=top ><font face=Arial size=2>" + StringUtility.displayDollars ( orderedItems [i].getExt_amount () ) + "</font></td> ";
			returnString += "    </tr> ";
			tot += Double.parseDouble ( orderedItems [i].getExt_amount () );
		}
		returnString += "  </table><br><table border=0 width=509><tbody><tr>";
		//returnString += "     <td align=center width=82 valign=top ><font face=Arial size=2>&nbsp;</font></td> ";
		//returnString += "     <td align=center width=145 valign=top ><font face=Arial size=2>&nbsp;</font></td> ";
		returnString += "     <td align=center colspan=5 width=87 valign=top ><font face=Arial size=2>&nbsp;</font></td> ";
		returnString += "     <td align=center width=130 valign=top ><font face=Arial size=2><b>Product Total :&nbsp</b></font></td> ";
		returnString += "     <td align=center width=90 valign=top ><font face=Arial size=2>" + StringUtility.displayDollars ( String.valueOf ( tot ) ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += " </tbody> ";
		returnString += " </table> ";
		return returnString;
	}

	/**
	 * EFulFillment Link for order emials.
	 *
	 * @param order
	 * @param client
	 * @return
	 * @throws Exception
	 */
	public static String getEFulFillmentLink ( Order order , String client )
																			throws Exception
	{
		//EncrypterDecrypter ed = new EncrypterDecrypter();
		String encryption = ConfigurationServlet.getApplicationProperty ( "DO_ENCRYPTION" );
		String encrypted_order_number = "";
		String randomString = EncrypterDecrypter.Encrypt ( DateUtility.getCurrentDateTime () );
		String returnString = "";
		String url = "";

		//System.out.println("From EmailFactory " + order.getMedia_id());

		returnString += " <br>    ";
		returnString += " <table border=0 width=500>   ";
		returnString += "   <tbody>   ";
		returnString += "     <tr>   ";
		returnString += "       <td align=center><font face=Arial size=2>   ";
		returnString += "       If items on your order are fulfilled electronically and are available for download,   ";
		returnString += "       click on the link below to open the file.  This link will be available for one week.   ";
		returnString += "       Please open the file and save it to a local directory.</font>   ";
		returnString += "       </td>   ";
		returnString += "     </tr>   ";
		returnString += "   </tbody>   ";
		returnString += " </table>   ";
		returnString += " <br>   ";

		returnString += " <table border=0 width=500>  ";
		returnString += " <tbody>  ";
		returnString += "     <tr>  ";
		returnString += "       <td align=center><font face=Arial size=2>";
		if ( encryption.equalsIgnoreCase ( "YES" ) )
		{
			encrypted_order_number = EncrypterDecrypter.Encrypt ( order.getOrder_number () );
			url = "'" + url + "'";
			url = ConfigurationServlet.getApplicationProperty ( order.getMedia_id () ) + "eor=" + encrypted_order_number + "&ts=" + randomString;
			returnString = "<a href=" + url + " target=_blank><font face=Arial size=2>CLICK HERE TO ACCESS E-FULFILLMENT ITEMS</font></a>";
		}
		else
		{
			url = ConfigurationServlet.getApplicationProperty ( order.getMedia_id () ) + "eor=" + order.getOrder_number () + "&ts=" + randomString;
			url = "'" + url + "'";
			returnString = "<a href=" + url + " target=_blank><font face=Arial size=2>CLICK HERE TO ACCESS E-FULFILLMENT ITEMS</font></a>";
		}
		returnString += "</font></td>  ";
		returnString += "     </tr>  ";
		returnString += "   </tbody>  ";
		returnString += " </table>  ";
		return returnString;
	}

	/**
	 * Efulfillment Repat link for Order Conf emails.
	 * 
	 * Created by Amar Bhatt on Sep 17, 2007 11:50:16 PM
	 * 
	 * @param order
	 * @param client
	 * @return
	 * @throws Exception
	 */
	public static String getEFulFillmentRepeatLink ( Order order , String client )
																					throws Exception
	{
		//EncrypterDecrypter ed = new EncrypterDecrypter();
		String encryption = ConfigurationServlet.getApplicationProperty ( "DO_ENCRYPTION" );
		String encrypted_order_number = "";
		String randomString = EncrypterDecrypter.Encrypt ( DateUtility.getCurrentDateTime () );
		String returnString = "";
		String url = "";

		//System.out.println("From EmailFactory " + order.getMedia_id());

		returnString += " <br><table border=1 width=500 cellpadding='0' cellspacing='0' bordercolorlight='#808080' bordercolordark='#808080' bordercolor='#808080'> <tr><td>    ";
		returnString += " <table border=0 width=500 >   ";
		returnString += "   <tbody>   ";
		returnString += "     <tr>   ";
		returnString += "       <td align=center><font face=Arial size=2>   ";
		returnString += "       This order contains at least one e-fulfillment (downloadable) item.   ";
		returnString += "       The link is available to you for 14 days.  Please click the link below to receive these items.   ";
		returnString += "       Once you have downloaded the item, it will be available for you to print.</font>   ";
		returnString += "       </td>   ";
		returnString += "     </tr>   ";
		returnString += "     <tr>  ";
		returnString += "       <td align=center><font face=Arial size=2>";
		if ( encryption.equalsIgnoreCase ( "YES" ) )
		{
			encrypted_order_number = EncrypterDecrypter.Encrypt ( order.getOrder_number () );
			url = "'" + url + "'";
			url = ConfigurationServlet.getApplicationProperty ( order.getMedia_id () ) + "eor=" + encrypted_order_number + "&ts=" + randomString;
			returnString += "<a href=" + url + " target=_blank><font face=Arial size=2>CLICK HERE TO ACCESS E-FULFILLMENT ITEMS</font></a>";
		}
		else
		{
			url = ConfigurationServlet.getApplicationProperty ( order.getMedia_id () ) + "eor=" + order.getOrder_number () + "&ts=" + randomString;
			url = "'" + url + "'";
			returnString += "<a href=" + url + " target=_blank><font face=Arial size=2>CLICK HERE TO ACCESS E-FULFILLMENT ITEMS</font></a>";
		}
		returnString += "</font></td>  ";
		returnString += "     </tr>  ";
		returnString += "   </tbody>  ";
		returnString += " </table></tr><td></table><br>";
		return returnString;
	}

	/**
	 * gets amount
	 *
	 * @param val1
	 * @param val2
	 * @return
	 */
	public static String getExtAmount ( String val1 , String val2 )
	{
		float v1 , v2 = 0;
		v1 = Float.parseFloat ( val1 );
		v2 = Float.parseFloat ( val2 );
		return ( v1 * v2 ) + "";
	}

	/**
	 * constructs the order detail section
	 *
	 * @param orderedItems
	 * @return
	 */
	public static String getItemDetails ( OrderedItems orderedItems[] )
	{
		//System.out.println("length getDwdItemDetails  2   " + orderedItems.length);
		double tot = 0;
		String returnString = "";
		returnString += " <table border=1 width=509> ";
		returnString += " <tbody> ";
		returnString += "   <tr> ";
		returnString += "     <td align=center width=82 valign=top ><font face=Arial size=2><b>Item Number</b></font></td> ";
		returnString += "     <td align=center width=145 valign=top ><font face=Arial size=2><b>Item Description</b></font></td> ";
		returnString += "     <td align=center width=87 valign=top ><font face=Arial size=2><b>Qty Ordered</b></font></td> ";
		returnString += "     <td align=center width=90 valign=top ><font face=Arial size=2><b>Unit Price</b></font></td> ";
		returnString += "     <td align=center width=90 valign=top ><font face=Arial size=2><b>Ext. Amount</b></font></td> ";
		returnString += "    </tr> ";
		for ( int i = 0 ; i < orderedItems.length ; i++ )
		{
			//System.out.println("here " + i);
			returnString += "   <tr> ";
			returnString += "     <td align=center width=82 valign=top ><font face=Arial size=2>" + orderedItems [i].getStock_number () + "</font></td> ";
			returnString += "     <td align=center width=145 valign=top ><font face=Arial size=2>" + orderedItems [i].getDescription () + "</font></td> ";
			returnString += "     <td align=center width=87 valign=top ><font face=Arial size=2>" + orderedItems [i].getQuantity_ordered () + "</font></td> ";
			returnString += "     <td align=center width=90 valign=top ><font face=Arial size=2>" + StringUtility.displayDollars ( orderedItems [i].getUnit_price () ) + "</font></td> ";
			returnString += "     <td align=center width=90 valign=top ><font face=Arial size=2>" + StringUtility.displayDollars ( orderedItems [i].getExt_amount () ) + "</font></td> ";
			returnString += "    </tr> ";
			tot += Double.parseDouble ( orderedItems [i].getExt_amount () );
		}
		returnString += "  </table><br><table border=0 width=509><tbody><tr>";
		//returnString += "     <td align=center width=82 valign=top ><font face=Arial size=2>&nbsp;</font></td> ";
		//returnString += "     <td align=center width=145 valign=top ><font face=Arial size=2>&nbsp;</font></td> ";
		returnString += "     <td align=center colspan=3 width=87 valign=top ><font face=Arial size=2>&nbsp;</font></td> ";
		returnString += "     <td align=center width=130 valign=top ><font face=Arial size=2><b>Product Total :&nbsp</b></font></td> ";
		returnString += "     <td align=center width=90 valign=top ><font face=Arial size=2>" + StringUtility.displayDollars ( String.valueOf ( tot ) ) + "</font></td> ";
		returnString += "    </tr> ";
		returnString += " </tbody> ";
		returnString += " </table> ";
		return returnString;
	}

	/**
	 * constructs order detail number for rush email.
	 *
	 * @param orderedItems
	 * @return
	 */
	public static String getItemDetails4Rush ( OrderedItems orderedItems[] )
	{
		//System.out.println("length " + orderedItems.length);
		String returnString = "";
		returnString += "<table border=1 width=509 ID='XX'> ";
		//returnString += " <tbody> ";
		returnString += "   <tr> ";
		returnString += "     <td align=center width=82><font face=Arial size=2><b>Item Number</b></font></td> ";
		returnString += "     <td align=center width=145><font face=Arial size=2><b>Item Description</b></font></td> ";
		returnString += "     <td align=center width=87><font face=Arial size=2><b>Qty Ordered</b></font></td> ";
		returnString += "    </tr> ";
		for ( int i = 0 ; i < orderedItems.length ; i++ )
		{
			//System.out.println("here " + i);
			returnString += "   <tr> ";
			returnString += "     <td align=center width=82><font face=Arial size=2>" + orderedItems [i].getStock_number () + "</font></td> ";
			returnString += "     <td align=center width=145><font face=Arial size=2>" + orderedItems [i].getDescription () + "</font></td> ";
			returnString += "     <td align=center width=87><font face=Arial size=2>" + orderedItems [i].getQuantity_ordered () + "</font></td> ";
			returnString += "    </tr> ";
		}
		//returnString += " </tbody> ";
		returnString += " </table>";
		return returnString;
	}

	/**
	 * For Over Limit email Phone Number Format 
	 * 
	 *  Added the below method on Tuesday, September 26, 2017 
	 * 
	 * @param in
	 * @return
	 */
	public static String formatPhoneNumber ( String in ) throws Exception
	{
		String phoneMask = "###-###-####";
		String phoneNumber = in;

		if ( phoneNumber.length () == 10 && phoneNumber != null)
		{
			MaskFormatter maskFormatter = new MaskFormatter ( phoneMask );
			maskFormatter.setValueContainsLiteralCharacters ( false );
			return maskFormatter.valueToString ( phoneNumber );
		}
		else
		{
			return "X";
		}
	}

	/**
	 * For Over Limit Email.
	 *
	 * Added on Apr 10, 2007 5:26:34 PM
	 *
	 * @param order
	 * @return
	 */
	public static String getOverLimitDetails ( Order order ) throws Exception
	{
		String phoneNumber ="";
		String returnString = "";
		returnString += "  <div align='left'> ";
		returnString += "    <table border='0' cellpadding='0' cellspacing='0' width='98%'>   ";
		returnString += "      <tr> ";
		returnString += "        <td width='20%'><font face='Arial' size='2'><b>Order Number</b></font></td> ";
		returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>:</font></b></td> ";
		returnString += "        <td width='76%'><font face='Arial' size='2'>" + order.getOrder_number () + "</font></td> ";
		returnString += "      </tr> ";
		returnString += "      <tr> ";
		returnString += "        <td width='20%'><font face='Arial' size='2'><b>Order Date</b></font></td> ";
		returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>:</font></b></td> ";
		returnString += "        <td width='76%'><font face='Arial' size='2'>" + order.getOrder_date () + "</font></td> ";
		returnString += "      </tr> ";
		returnString += "      <tr> ";
		returnString += "        <td width='20%'><font face='Arial' size='2'><b>Order Placer</b></font></td> ";
		returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>:</font></b></td> ";
		returnString += "        <td width='76%'><font face='Arial' size='2'>" + StringUtility.processNullToEmpty ( order.getBusiness_partner () ) + "</font></td> ";
		returnString += "      </tr> ";
		returnString += "      <tr> ";
		returnString += "        <td width='20%'><font face='Arial' size='2'><b>Type</b></font></td> ";
		returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>:</font></b></td> ";
		returnString += "        <td width='76%'><font face='Arial' size='2'>" + order.getBusiness_partner_type () + "</font></td> ";
		returnString += "      </tr> ";

		returnString += "      <tr> ";
		returnString += "        <td width='20%'></td> ";
		returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>&nbsp;</font></b></td> ";
		returnString += "        <td width='76%'><font face='Arial' size='2'>&nbsp;</font></td> ";
		returnString += "      </tr> ";

		
		phoneNumber = formatPhoneNumber(order.getBill_to_phone_number ());
		if(phoneNumber!=null && phoneNumber.equalsIgnoreCase("X"))
		{
			returnString += "      <tr> ";
			returnString += "        <td width='20%'><font face='Arial' size='2'><b>Bill To Phone</b></font></td> ";
			returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>:</font></b></td> ";
			returnString += "        <td width='76%'><font face='Arial' size='2'>" + order.getBill_to_phone_number () + "</font></td> ";
			returnString += "      </tr> ";
		}
		else
		{
			returnString += "      <tr> ";
			returnString += "        <td width='20%'><font face='Arial' size='2'><b>Bill To Phone</b></font></td> ";
			returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>:</font></b></td> ";
			returnString += "        <td width='76%'><font face='Arial' size='2'>" + phoneNumber + "</font></td> ";
			returnString += "      </tr> ";
		}
		
		
		
		returnString += "      <tr> ";
		returnString += "        <td width='20%'><font face='Arial' size='2'><b>Bill To Name</b></font></td> ";
		returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>:</font></b></td> ";
		returnString += "        <td width='76%'><font face='Arial' size='2'>" + StringUtility.processNullToEmpty ( order.getBill_to_first_name () ) + " " + StringUtility.processNullToEmpty ( order.getBill_to_last_name () ) + "</font></td> ";
		returnString += "      </tr> ";
		returnString += "      <tr> ";
		returnString += "        <td width='20%'><font face='Arial' size='2'><b>Bill To Company</b></font></td> ";
		returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>:</font></b></td> ";
		returnString += "        <td width='76%'><font face='Arial' size='2'>" + StringUtility.processNullToEmpty ( order.getBill_to_company () ) + "</font></td> ";
		returnString += "      </tr> ";
		returnString += "      <tr> ";
		returnString += "        <td width='20%'><font face='Arial' size='2'><b>Bill To Address</b></font></td> ";
		returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>:</font></b></td> ";
		returnString += "        <td width='76%'><font face='Arial' size='2'>" + order.getBill_to_address1 () + "</font></td> ";
		returnString += "      </tr> ";
		returnString += "      <tr> ";
		returnString += "        <td width='20%'></td> ";
		returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>&nbsp;</font></b></td> ";
		returnString += "        <td width='76%'><font face='Arial' size='2'>" + order.getBill_to_city () + " " + order.getBill_to_state_province () + " " + order.getBill_to_zip () + "</font></td> ";
		returnString += "      </tr> ";

		returnString += "      <tr> ";
		returnString += "        <td width='20%'></td> ";
		returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>&nbsp;</font></b></td> ";
		returnString += "        <td width='76%'><font face='Arial' size='2'>&nbsp;</font></td> ";
		returnString += "      </tr> ";

		//addin ship to information per innotas id 1719192492
		phoneNumber = formatPhoneNumber(order.getShip_to_phone_number ());
		if(phoneNumber!=null && phoneNumber.equalsIgnoreCase("X"))
		{
		returnString += "      <tr> ";
		returnString += "        <td width='20%'><font face='Arial' size='2'><b>Ship To Phone</b></font></td> ";
		returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>:</font></b></td> ";
		returnString += "        <td width='76%'><font face='Arial' size='2'>" + StringUtility.processNullToEmpty ( order.getShip_to_phone_number () ) + "</font></td> ";
		returnString += "      </tr> ";
		}
		else
		{
			returnString += "      <tr> ";
			returnString += "        <td width='20%'><font face='Arial' size='2'><b>Ship To Phone</b></font></td> ";
			returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>:</font></b></td> ";
			returnString += "        <td width='76%'><font face='Arial' size='2'>" + phoneNumber + "</font></td> ";
			returnString += "      </tr> ";
		}
		
		
		returnString += "      <tr> ";
		returnString += "        <td width='20%'><font face='Arial' size='2'><b>Ship To Name</b></font></td> ";
		returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>:</font></b></td> ";
		returnString += "        <td width='76%'><font face='Arial' size='2'>" + StringUtility.processNullToEmpty ( order.getShip_to_first_name () ) + " " + StringUtility.processNullToEmpty ( order.getShip_to_last_name () ) + "</font></td> ";
		returnString += "      </tr> ";
		returnString += "      <tr> ";
		returnString += "        <td width='20%'><font face='Arial' size='2'><b>Ship To Company</b></font></td> ";
		returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>:</font></b></td> ";
		returnString += "        <td width='76%'><font face='Arial' size='2'>" + StringUtility.processNullToEmpty ( order.getShip_to_company () ) + "</font></td> ";
		returnString += "      </tr> ";
		returnString += "      <tr> ";
		returnString += "        <td width='20%'><font face='Arial' size='2'><b>Ship To Email</b></font></td> ";
		returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>:</font></b></td> ";
		returnString += "        <td width='76%'><font face='Arial' size='2'>" + StringUtility.processNullToEmpty ( order.getShip_to_email() ) + "</font></td> ";
		returnString += "      </tr> ";

		returnString += "      <tr> ";
		returnString += "        <td width='20%'></td> ";
		returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>&nbsp;</font></b></td> ";
		returnString += "        <td width='76%'><font face='Arial' size='2'>&nbsp;</font></td> ";
		returnString += "      </tr> ";

		returnString += "      <tr> ";
		returnString += "        <td width='20%'><b>Overlimit Reason</b></td> ";
		returnString += "        <td width='4%' align='center'><b><font face='Arial' size='2'>:</font></b></td> ";
		returnString += "        <td width='76%'><font face='Arial' size='2'>" + order.getReason_comment () + "</font></td> ";
		returnString += "      </tr> ";
		returnString += "    </table> ";
		returnString += "  </div> ";
		return returnString;
	}

	/**
	 * generates overlimit link for emial body.
	 *
	 * @param emailAddress
	 * @param orderNumber
	 * @return
	 * @throws Exception
	 */
	public static String getOverLimitLink (
											String emailAddress ,
											String orderNumber )
																throws Exception
	{
		String returnString = "";
		String encryption = ConfigurationServlet.getApplicationProperty ( "DO_ENCRYPTION" );
		String encrypted_order_number = "";
		String encrypted_emailAddress = "";
		//added a delay here so the 2 over limit emials do not have the same reandomString;
		Thread.sleep ( 1000 );
		String randomString = EncrypterDecrypter.Encrypt ( DateUtility.getCurrentDateTime () );
		String url = ConfigurationServlet.getApplicationProperty ( "OVERLIMIT" );
		String newUrl = ConfigurationServlet.getApplicationProperty ( "OVERLIMIT" );
		String hideInEmail = "<br><input type='hidden' name='eor' value='" + orderNumber + "'><br><input type='hidden' name='eem' value='" + emailAddress + "'>";
		if ( encryption.equalsIgnoreCase ( "YES" ) )
		{
			encrypted_order_number = EncrypterDecrypter.Encrypt ( orderNumber );
			encrypted_emailAddress = EncrypterDecrypter.Encrypt ( emailAddress );
			url += "eor=" + encrypted_order_number + "&eem=" + encrypted_emailAddress + "&ts=" + randomString;
			newUrl += "overValues=" + encrypted_order_number + "~" + encrypted_emailAddress + "&ts=" + randomString;
			//returnString = "<a href=" + newUrl + " target=_blank>Click Here</a> to approve Over Limit Items" + hideInEmail;
			returnString = "<a href=" + url + " target=_blank>Click Here</a> to approve Over Limit Items" + hideInEmail;
		}
		else
		{
			url += "eor=" + orderNumber + "&eem=" + emailAddress + "&ts=" + randomString;
			newUrl += "overValues=" + orderNumber + "~" + emailAddress + "&ts=" + randomString;
			//returnString = "<a href=" + newUrl + " target=_blank>Click Here</a> to approve Over Limit Items" + hideInEmail;
			returnString = "<a href=" + url + " target=_blank>Click Here</a> to approve Over Limit Items" + hideInEmail;
		}
		return returnString;
	}

	/**
	 * contructs the order detail section
	 *
	 * @param orderedItems
	 * @return
	 */
	public static String getPickListItemDetails ( OrderedItems orderedItems[] )
	{
		String returnString = "";
		boolean flag = false;
		returnString += " <table border=1 width=509> ";
		returnString += " <tbody> ";
		returnString += "   <tr> ";
		returnString += "     <td align=center width=20%><font face=Arial size=2><b>Stock Number</b></font></td> ";
		returnString += "     <td align=center width=20%><font face=Arial size=2><b>Stock Description</b></font></td> ";
		returnString += "     <td align=center width=20%><font face=Arial size=2><b>Sequence</b></font></td> ";
		returnString += "     <td align=center width=20%><font face=Arial size=2><b>Image Name</b></font></td> ";
		returnString += "     <td align=center width=20%><font face=Arial size=2><b>Qty</b></font></td> ";
		returnString += "    </tr> ";
		for ( int i = 0 ; i < orderedItems.length ; i++ )
		{
			//System.out.println(i);
			returnString += "   <tr> ";

			returnString += "     <td align=center width=20%><font face=Arial size=2>" + orderedItems [i].getStock_number () + "</font></td> ";
			returnString += "     <td align=center width=20%><font face=Arial size=2>" + orderedItems [i].getDescription () + "</font></td> ";
			returnString += "     <td align=center width=20%><font face=Arial size=2>" + orderedItems [i].getSequence_number () + "</font></td> ";
			returnString += "     <td align=center width=20%><font face=Arial size=2>" + orderedItems [i].getImage_name () + "</font></td> ";
			returnString += "     <td align=center width=20%><font face=Arial size=2>" + orderedItems [i].getQuantity_to_be_picked () + "</font></td> ";

			returnString += "    </tr> ";
			flag = true;
		}
		if ( ! flag )
		{
			returnString += "   <tr> ";
			returnString += "     <td colspan=\"5\" align=center width=100%><font face=Arial size=2>No items available to display</font></td> ";
			returnString += "    </tr> ";
		}
		returnString += " </tbody> ";
		returnString += " </table> ";
		return returnString;
	}

	/**
	 * constructs saturn orders items.
	 *
	 * @param orderedItems
	 * @return
	 */
	public static String getSaturnItemDetails ( OrderedItems orderedItems[] )
	{
		//System.out.println("length " + orderedItems.length);

		String returnString = "";
		returnString += " <table border=1 width=509> ";
		returnString += " <tbody> ";
		returnString += "   <tr> ";
		returnString += "     <td align=center width=82 valign=top ><font face=Arial size=2><b>Item Number</b></font></td> ";
		returnString += "     <td align=center width=145 valign=top ><font face=Arial size=2><b>Item Description</b></font></td> ";
		returnString += "     <td align=center width=87 valign=top ><font face=Arial size=2><b>Qty Ordered</b></font></td> ";
		returnString += "    </tr> ";
		for ( int i = 0 ; i < orderedItems.length ; i++ )
		{
			//System.out.println("here " + i);
			returnString += "   <tr> ";
			returnString += "     <td align=center width=82 valign=top ><font face=Arial size=2>" + orderedItems [i].getStock_number () + "</font></td> ";
			returnString += "     <td align=center width=145 valign=top ><font face=Arial size=2>" + orderedItems [i].getDescription () + "</font></td> ";
			returnString += "     <td align=center width=87 valign=top ><font face=Arial size=2>" + orderedItems [i].getQuantity_ordered () + "</font></td> ";
			returnString += "    </tr> ";

		}

		returnString += " </tbody> ";
		returnString += " </table> ";
		return returnString;
	}

	/**
	 * contructs the shipment detail section
	 *
	 * @param orderedItems
	 * @return
	 */
	public static String getShipmentItemDetails ( OrderedItems orderedItems[] )
	{
		String returnString = "";
		returnString += " <table border=1 width=509> ";
		returnString += " <tbody> ";
		returnString += "   <tr> ";
		returnString += "     <td align=center width=82><font face=Arial size=2><b>Item Number</b></font></td> ";
		returnString += "     <td align=center width=145><font face=Arial size=2><b>Item Description</b></font></td> ";
		returnString += "     <td align=center width=87><font face=Arial size=2><b>Qty Ordered</b></font></td> ";
		returnString += "     <td align=center width=87><font face=Arial size=2><b>Qty Shipped</b></font></td> ";
		returnString += "    </tr> ";
		for ( int i = 0 ; i < orderedItems.length ; i++ )
		{
			//System.out.println(i);
			returnString += "   <tr> ";
			returnString += "     <td align=center width=82><font face=Arial size=2>" + orderedItems [i].getStock_number () + "</font></td> ";
			returnString += "     <td align=center width=145><font face=Arial size=2>" + orderedItems [i].getDescription () + "</font></td> ";
			returnString += "     <td align=center width=87><font face=Arial size=2>" + orderedItems [i].getQuantity_ordered () + "</font></td> ";
			returnString += "     <td align=center width=87><font face=Arial size=2>" + orderedItems [i].getQuantity_shipped () + "</font></td> ";
			returnString += "    </tr> ";
		}
		returnString += " </tbody> ";
		returnString += " </table> ";
		return returnString;
	}

	/**
	 * constructs saturn email body.
	 *
	 * @param order
	 * @param orderedItems
	 * @return
	 */
	public static String getStaturnOrderShipmentDetail (
														Order order ,
														OrderedItems orderedItems[] )
	{

		float runningProductTotal = 0;
		String returnString = "";
		returnString += " <table border=\"1\" width=\"500\">    ";
		returnString += "   <tr> ";
		returnString += "     <td align=\"center\"><font face=\"Arial\" size=\"2\"><b>Item Number</b></font></td> ";
		returnString += "     <td align=\"center\"><font face=\"Arial\" size=\"2\"><b>Item Description</b></font></td> ";
		returnString += "     <td align=\"center\"><font face=\"Arial\" size=\"2\"><b>Qty Ordered</b></font></td> ";
		returnString += "     <td align=\"center\"><font face=\"Arial\" size=\"2\"><b>Qty Shipped</b></font></td> ";
		returnString += "     <td align=\"center\"><font face=\"Arial\" size=\"2\"><b>Unit Price</b></font></td> ";
		returnString += "     <td align=\"center\"><font face=\"Arial\" size=\"2\"><b>Ext. Amount</b></font></td> ";
		returnString += "   </tr> ";
		for ( int i = 0 ; i < orderedItems.length ; i++ )
		{
			returnString += "   <tr> ";
			returnString += "     <td><font face=\"Arial\" size=\"2\">" + orderedItems [i].getStock_number () + "</font></td> ";
			returnString += "     <td><font face=\"Arial\" size=\"2\">" + orderedItems [i].getDescription () + "</font></td> ";
			returnString += "     <td><font face=\"Arial\" size=\"2\">" + orderedItems [i].getQuantity_ordered () + "</font></td> ";
			returnString += "     <td><font face=\"Arial\" size=\"2\">" + orderedItems [i].getQuantity_shipped () + "</font></td> ";
			returnString += "     <td><font face=\"Arial\" size=\"2\">" + orderedItems [i].getUnit_price () + "</font></td> ";
			returnString += "     <td><font face=\"Arial\" size=\"2\">" + StringUtility.displayDollars ( getExtAmount ( orderedItems [i].getQuantity_shipped () , orderedItems [i].getUnit_price () ) ) + "</font></td> ";
			returnString += "   </tr> ";
			runningProductTotal += Double.parseDouble ( getExtAmount ( orderedItems [i].getQuantity_shipped () , orderedItems [i].getUnit_price () ) );
		}
		returnString += "   <tr> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		returnString += "     <td align=\"right\" colspan=5><font face=\"Arial\" size=\"2\"><b>Product Total :</b></font></td> ";
		returnString += "     <td align=\"right\"><font face=\"Arial\" size=\"2\">" + StringUtility.displayDollars ( runningProductTotal ) + "</font></td> ";
		returnString += "   </tr> ";
		returnString += "   <tr> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		// returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		returnString += "     <td align=\"right\" colspan=5><font face=\"Arial\" size=\"2\"><b>Tax (MI) :</b></font></td> ";
		returnString += "     <td align=\"right\"><font face=\"Arial\" size=\"2\">" + StringUtility.displayDollars ( order.getState_tax_amount () ) + "</font></td> ";
		returnString += "   </tr> ";
		returnString += "   <tr> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		returnString += "     <td align=\"right\" colspan=5><font face=\"Arial\" size=\"2\"><b>Shipping Charges :</b></font></td> ";
		returnString += "     <td align=\"right\"><font face=\"Arial\" size=\"2\">" + StringUtility.displayDollars ( order.getShipping_amount () ) + "</font></td> ";
		returnString += "   </tr> ";
		returnString += "   <tr> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		//returnString += " <td align=\"left\" colspan=\"6\"><font face=\"Arial\"
		// size=\"2\" ><hr></font></td> ";
		//returnString += " </tr> ";
		returnString += "   <tr> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		//returnString += " <td align=\"left\"><font face=\"Arial\"
		// size=\"2\">&nbsp;</font></td> ";
		returnString += "     <td align=\"right\" colspan=5><font face=\"Arial\" size=\"2\"><b>Total Charged Amount :</b></font></td> ";
		returnString += "     <td align=\"right\"><font face=\"Arial\" size=\"2\">" + getTotalShippingAmount ( String.valueOf ( runningProductTotal ) , order.getState_tax_amount () , order.getShipping_amount () ) + "</font></td> ";
		returnString += "   </tr> ";
		returnString += " </table> ";

		return returnString;
	}

	/**
	 * get's grand total of an order
	 *
	 * @param val1
	 * @param val2
	 * @param val3
	 * @return
	 */
	public static String getTotalShippingAmount (
													String val1 ,
													String val2 ,
													String val3 )
	{
		float v1 , v2 , v3 = 0;
		v1 = Float.parseFloat ( val1 );
		v2 = Float.parseFloat ( val2 );
		v3 = Float.parseFloat ( val3 );
		return StringUtility.displayDollars ( v1 + v2 + v3 );
	}

	/**
	 * Send an email.
	 *
	 * @param order_number
	 * @param email
	 * @return boolean
	 */
	public static boolean Transport ( String val , Email email )
	{
		//SendMail sm = new SendMail();
		boolean sendMailFlag = false;

		String from = "";
		String to[] = null;
		String cc[] = null;
		String bcc[] = null;
		String subject = "";
		String fileAttachment[] = null;
		String body = "";

		try
		{
			from = email.getFrom ();
			if ( ! StringUtility.isStringBlank ( email.getTo () ) )
			{
				to = email.getTo ().split ( "," );
			}
			if ( ! StringUtility.isStringBlank ( email.getCc () ) )
			{
				cc = email.getCc ().split ( "," );
			}
			if ( ! StringUtility.isStringBlank ( email.getBcc () ) )
			{
				bcc = email.getBcc ().split ( "," );
			}
			fileAttachment = null;
			subject = email.getSubject ();
			body = email.getBody ();
			//sendMailFlag = sm.send_email(email.getFrom(), email.getTo(), email.getCc(), email.getBcc(), email.getSubject(), email.getFilename(), "", email.getBody());
			sendMailFlag = com.archway.globalemail.email.Email.SendHtmlEmailWithFileAttachment ( from , to , cc , bcc , subject , fileAttachment , body );
		}
		catch ( Exception e )
		{
			logger.warn ( "For " + val + "Transport Falied due to the following message " + e.getMessage () );
		}
		return sendMailFlag;
	}

	/**
	 * validates to,cc,bcc email address in email entity
	 *
	 * @param email
	 * @return entity Email
	 */
	public static Email validateEmailAddress ( Email email )
	{
		String to = "";
		String cc = "";
		String bcc = "";
		to = email.getTo ();
		cc = email.getCc ();
		bcc = email.getBcc ();
		if ( to != null && ( to.length () > 0 ) && StringUtility.isEmailAddressValid ( to ) )
		{
			email.setTo ( to );
		}
		if ( cc != null && ( cc.length () > 0 ) && StringUtility.isEmailAddressValid ( cc ) )
		{
			email.setCc ( cc );
		}
		if ( bcc != null && ( bcc.length () > 0 ) && StringUtility.isEmailAddressValid ( bcc ) )
		{
			email.setBcc ( bcc );
		}
		return email;
	}

	/**
	 * Default Constructor
	 */
	public EmailFactory ()
	{
	}

}