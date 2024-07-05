package com.archway.globalemail.entity;

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
 * File Name               : OverLimit.java 
 * Package Name            : com.archway.globalemail.entity
 * 
 * Date                    : Oct 27, 2005 - 3:02:00 PM
 * 
 * Change Date             : May 18, 2006 4:01:24 PM
 *                           May 30, 2006 12:27:22 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * Holder Bean/Class
 */
public class OverLimit
{

	/**
	 * Default Constructor.
	 *
	 */
	public OverLimit ()
	{

	}

	private String	order_number			= "";

	private String	business_partner		= "";

	private String	bill_to_first_name		= "";

	private String	bill_to_last_name		= "";

	private String	bill_to_company			= "";

	private String	stock_number			= "";

	private String	quantity_to_be_shipped	= "";

	private String	description_for_www		= "";

	private String	Order_limit_minimum_qty	= "";

	private String	Order_limit_maximum_qty	= "";

	private String	description				= "";

	/**
	 * @return Returns the description.
	 */
	public String getDescription ()
	{
		return description;
	}

	/**
	 * @param description The description to set.
	 */
	public void setDescription ( String description )
	{
		this.description = description;
	}

	/**
	 * @return Returns the bill_to_company.
	 */
	public String getBill_to_company ()
	{
		return bill_to_company;
	}

	/**
	 * @param bill_to_company
	 *        The bill_to_company to set.
	 */
	public void setBill_to_company ( String bill_to_company )
	{
		this.bill_to_company = bill_to_company;
	}

	/**
	 * @return Returns the bill_to_first_name.
	 */
	public String getBill_to_first_name ()
	{
		return bill_to_first_name;
	}

	/**
	 * @param bill_to_first_name
	 *        The bill_to_first_name to set.
	 */
	public void setBill_to_first_name ( String bill_to_first_name )
	{
		this.bill_to_first_name = bill_to_first_name;
	}

	/**
	 * @return Returns the bill_to_last_name.
	 */
	public String getBill_to_last_name ()
	{
		return bill_to_last_name;
	}

	/**
	 * @param bill_to_last_name
	 *        The bill_to_last_name to set.
	 */
	public void setBill_to_last_name ( String bill_to_last_name )
	{
		this.bill_to_last_name = bill_to_last_name;
	}

	/**
	 * @return Returns the business_partner.
	 */
	public String getBusiness_partner ()
	{
		return business_partner;
	}

	/**
	 * @param business_partner
	 *        The business_partner to set.
	 */
	public void setBusiness_partner ( String business_partner )
	{
		this.business_partner = business_partner;
	}

	/**
	 * @return Returns the description_for_www.
	 */
	public String getDescription_for_www ()
	{
		return description_for_www;
	}

	/**
	 * @param description_for_www
	 *        The description_for_www to set.
	 */
	public void setDescription_for_www ( String description_for_www )
	{
		this.description_for_www = description_for_www;
	}

	/**
	 * @return Returns the order_limit_maximum_qty.
	 */
	public String getOrder_limit_maximum_qty ()
	{
		return Order_limit_maximum_qty;
	}

	/**
	 * @param order_limit_maximum_qty
	 *        The order_limit_maximum_qty to set.
	 */
	public void setOrder_limit_maximum_qty ( String order_limit_maximum_qty )
	{
		Order_limit_maximum_qty = order_limit_maximum_qty;
	}

	/**
	 * @return Returns the order_limit_minimum_qty.
	 */
	public String getOrder_limit_minimum_qty ()
	{
		return Order_limit_minimum_qty;
	}

	/**
	 * @param order_limit_minimum_qty
	 *        The order_limit_minimum_qty to set.
	 */
	public void setOrder_limit_minimum_qty ( String order_limit_minimum_qty )
	{
		Order_limit_minimum_qty = order_limit_minimum_qty;
	}

	/**
	 * @return Returns the order_number.
	 */
	public String getOrder_number ()
	{
		return order_number;
	}

	/**
	 * @param order_number
	 *        The order_number to set.
	 */
	public void setOrder_number ( String order_number )
	{
		this.order_number = order_number;
	}

	/**
	 * @return Returns the quantity_to_be_shipped.
	 */
	public String getQuantity_to_be_shipped ()
	{
		return quantity_to_be_shipped;
	}

	/**
	 * @param quantity_to_be_shipped
	 *        The quantity_to_be_shipped to set.
	 */
	public void setQuantity_to_be_shipped ( String quantity_to_be_shipped )
	{
		this.quantity_to_be_shipped = quantity_to_be_shipped;
	}

	/**
	 * @return Returns the stock_number.
	 */
	public String getStock_number ()
	{
		return stock_number;
	}

	/**
	 * @param stock_number
	 *        The stock_number to set.
	 */
	public void setStock_number ( String stock_number )
	{
		this.stock_number = stock_number;
	}
}