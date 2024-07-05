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
 * File Name               : Receiving.java 
 * Package Name            : com.archway.globalemail.entity
 * 
 * Date                    : Oct 27, 2005 - 3:03:54 PM
 * 
 * Change Date             : May 18, 2006 4:02:26 PM
 *  						 May 30, 2006 12:29:07 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * Holder Bean/Class
 */

public class Receiving
{

	private String	date_received		= "";

	private String	description			= "";

	private String	description_for_www	= "";

	private String	nomenclature_id		= "";

	private String	quantity_on_hand	= "";

	private String	quantity_received	= "";

	private String	stock_number		= "";

	private String	supplier			= "";

	private String	trans_quantity		= "";

	private String	uofm_description	= "";

	/**
	 * Default Constructor.
	 *
	 */
	public Receiving ()
	{

	}

	/**
	 * @return Returns the date_received.
	 */
	public String getDate_received ()
	{
		return date_received;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription ()
	{
		return description;
	}

	/**
	 * @return Returns the description_for_www.
	 */
	public String getDescription_for_www ()
	{
		return description_for_www;
	}

	/**
	 * @return Returns the nomenclature_id.
	 */
	public String getNomenclature_id ()
	{
		return nomenclature_id;
	}

	/**
	 * @return Returns the quantity_on_hand.
	 */
	public String getQuantity_on_hand ()
	{
		return quantity_on_hand;
	}

	/**
	 * @return Returns the quantity_received.
	 */
	public String getQuantity_received ()
	{
		return quantity_received;
	}

	/**
	 * @return Returns the stock_number.
	 */
	public String getStock_number ()
	{
		return stock_number;
	}

	/**
	 * @return Returns the supplier.
	 */
	public String getSupplier ()
	{
		return supplier;
	}

	/**
	 * @return Returns the trans_quantity.
	 */
	public String getTrans_quantity ()
	{
		return trans_quantity;
	}

	/**
	 * @return Returns the uofm_description.
	 */
	public String getUofm_description ()
	{
		return uofm_description;
	}

	/**
	 * @param date_received The date_received to set.
	 */
	public void setDate_received ( String date_received )
	{
		this.date_received = date_received;
	}

	/**
	 * @param description The description to set.
	 */
	public void setDescription ( String description )
	{
		this.description = description;
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
	 * @param nomenclature_id The nomenclature_id to set.
	 */
	public void setNomenclature_id ( String nomenclature_id )
	{
		this.nomenclature_id = nomenclature_id;
	}

	/**
	 * @param quantity_on_hand
	 *        The quantity_on_hand to set.
	 */
	public void setQuantity_on_hand ( String quantity_on_hand )
	{
		this.quantity_on_hand = quantity_on_hand;
	}

	/**
	 * @param quantity_received
	 *        The quantity_received to set.
	 */
	public void setQuantity_received ( String quantity_received )
	{
		this.quantity_received = quantity_received;
	}

	/**
	 * @param stock_number
	 *        The stock_number to set.
	 */
	public void setStock_number ( String stock_number )
	{
		this.stock_number = stock_number;
	}

	/**
	 * @param supplier The supplier to set.
	 */
	public void setSupplier ( String supplier )
	{
		this.supplier = supplier;
	}

	/**
	 * @param trans_quantity
	 *        The trans_quantity to set.
	 */
	public void setTrans_quantity ( String trans_quantity )
	{
		this.trans_quantity = trans_quantity;
	}

	/**
	 * @param uofm_description The uofm_description to set.
	 */
	public void setUofm_description ( String uofm_description )
	{
		this.uofm_description = uofm_description;
	}
}