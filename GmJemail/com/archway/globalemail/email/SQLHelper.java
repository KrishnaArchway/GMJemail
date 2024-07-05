package com.archway.globalemail.email;

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
 * File Name               : SQLHelper.java
 * Package Name            : com.archway.globalemail.email
 *
 * Date                    : Jan 15, 2006 11:37:19 AM
 *
 * Change Date             : Jan 16, 2006 5:17:09 AM
 * 							 Jan 16, 2006 11:20:30 AM
 * 						     Jan 17, 2006 11:13:34 PM
 *                           Friday, March 17, 2006
 *                           Thursday, May 18, 2006
 *                           May 30, 2006 12:21:34 PM
 *                           Jun 2, 2006 6:37:13 PM
 *                           Jun 27, 2006 10:06:56 AM
 *                           Jun 19, 2007 2:50:32 PM
 *							 Thursday, June 28, 2007
 *
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * All Sql Queries in the class file.
 *
 */

public class SQLHelper
{

	/**
	 *
	 * @return
	 */
	public static String CreateLowWaterRecInEmailHistorySql ()
	{
		return "{call GM_EMAIL_API_PKG.Create_low_water_data(?,?)}";
	}
	
	/**
	 *
	 * @return
	 */	
	public static String CreateReorderPointRecInEmailHistorySql ()
	{
		return "{call GM_EMAIL_API_PKG.Create_reorder_point_data(?,?)}";
	}

	/**
	 *
	 * @return
	 */
	public static String CreateOverLimitRecInEmailHistorySql ()
	{
		return "{call GM_EMAIL_API_PKG.Create_Over_limit_data(?,?,?)}";
	}

	/**
	 *
	 * @return
	 */
	public static String CreateRushRecInEmailHistorySql ()
	{
		return "{call GM_EMAIL_API_PKG.Create_rush_data(?,?,?)}";
	}

	/**
	 *
	 * @return
	 */
	public static String getBillingNotificationSql ()
	{
		return "{call GM_EMAIL_API_PKG.ESTORE_GET_INVOICE_INFO(?,?,?)}";
	}

	/**
	 *
	 * @return
	 */
	public static String getEmailClientRecipientRecordsSql ()
	{
		return "{call GM_EMAIL_API_PKG.GET_EMAIL_RECIPIENT(?,?,?)}";
	}

	/**
	 *
	 * @return
	 */
	public static String getEmailClientRecordsSql ()
	{
		return "{call GM_EMAIL_API_PKG.GET_EMAIL_CLIENT(?,?,?)}";
	}

	/**
	 *
	 * @return
	 */
	public static String getLowWaterDetailsSql ()
	{
		return "{call GM_EMAIL_API_PKG.ESTORE_LOW_WATER(?,?,?)}";
	}
	
	/**
	 *
	 * @return
	 */
	
	public static String getReOrderPointDetailsSql ()
	{
		return "{call GM_EMAIL_API_PKG.ESTORE_REORDER_POINT(?,?,?)}";
	}

	/**
	 *
	 * @return
	 */
	public static String getNomenclatureDetailsSql ()
	{
		return "{call GM_EMAIL_API_PKG.GetNomenclature(?,?,?)}";
	}

	/**
	 *
	 * @return
	 */
	public static String getOrderItemsRecordsSql ()
	{
		return "{call GM_EMAIL_API_PKG.ESTORE_ORDER_DETAIL(?,?,?,?)}";
	}

	/**
	 *
	 * @return
	 */
	public static String getOrderRecordSql ()
	{
		return "{call GM_EMAIL_API_PKG.ESTORE_ITEM_INFO(?,?,?,?)}";
	}

	/**
	 *
	 * @return
	 */
	public static String getOverLimitDetailsSql ()
	{
		return "{call GM_EMAIL_API_PKG.ESTORE_OVER_LIMIT(?,?,?,?,?)}";
	}

	/**
	 *
	 * @return
	 */
	public static String getOverLimitSpecialEmailClientRecipientSql ()
	{
		return "{call GM_EMAIL_API_PKG.GET_OVER_LIMIT_EMAIL(?,?,?,?)}";
	}

	/**
	 *
	 * @return
	 */
	public static String getPackingShipmentRecordSql ()
	{
		return "{call GM_EMAIL_API_PKG.ESTORE_TRACK_INFO(?,?,?,?)}";
	}

	/**
	 * returns String to populate EmailHistory entity
	 *
	 * @return String
	 */
	public static String getPickListEmailHistorySql ()
	{
		return "call GET_POD_ORDERS(?,?,?)";
	}

	/**
	 * return string topopulatepacking list (pod) email
	 *
	 * @return String
	 */
	public static String getPickListOrderSql ()
	{
		return "{call GM_EMAIL_API_PKG.GET_POD_ITEMS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	}

	/**
	 *
	 * @return
	 */
	public static String getReceivingDetailsSql ()
	{
		return "{call GM_EMAIL_API_PKG.ESTORE_RECEIVING(?,?,?,?)}";
	}

	/**
	 *
	 * @return
	 */
	public static String getSpecialEmailClientRecipientSql ()
	{
		return "{call GM_EMAIL_API_PKG.GET_SPECIAL_EMAILS(?,?,?,?)}";
	}

	/**
	 * This procedure is only used by receiving emails.
	 * @return
	 */
	public static String getUpdateEmailHistoryReceivingSql ()
	{
		return "{call GM_EMAIL_API_PKG.UPDATE_EMAIL_HIST(?,?,?,?)}";
	}

	/**
	 *
	 * @return
	 */
	public static String getUpdateEmailHistorySql ()
	{
		return "{call GM_EMAIL_API_PKG.UPDATE_EMAIL_CLIENT(?,?,?)}";
	}

	/**
	 *
	 * @return
	 */
	public static String getOLSpecialEmailClientRecipientSql ()
	{
		return "{call GM_EMAIL_API_PKG.OVERLIMIT_EMAIL_ADDRESS(?,?,?,?)}";
	}

	/**
	 * proc to create backorder emails.
	 *
	 * @return
	 */
	public static String CreateBackOrderRecInEmailHistorySql ()
	{
		return "{call GM_EMAIL_API_PKG.CREATE_BACKORDER_DATA(?,?)}";
	}

	/**
	 * backOrder details.
	 *
	 * @return
	 */
	public static String getBackOrderDetailsSql()
	{
		return "{call GM_EMAIL_API_PKG.GET_BACKORDER_EMAIL(?,?,?,?)}";
	}


	/**
	 * Default Constructor
	 *
	 */
	public SQLHelper ()
	{
	}

}