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
 * File Name               : Nomenclature.java 
 * Package Name            : com.archway.globalemail.entity
 * 
 * Date                    : Oct 27, 2005 - 3:01:30 PM
 * 
 * Change Date             : May 18, 2006 4:00:13 PM
 * 						     May 30, 2006 12:25:51 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * Holder Bean/Class
 */
public class Nomenclature
{

	private String	availableQty;

	private String	date_created;

	private String	description;

	private String	description_for_www;

	private String	ideal_max_qty;

	private String	item_type;

	private String	nomenclature_id;

	private String	review_for_scrap_date;

	private String	stock_number;

	private String	uofm_description;
	
	private String	last_shipped_date;

	/**
	 * Default Constructor.
	 *
	 */
	public Nomenclature ()
	{

	}

	/**
	 * @return Returns the availableQty.
	 */
	public String getAvailableQty ()
	{
		return availableQty;
	}

	/**
	 * @return Returns the date_created.
	 */
	public String getDate_created ()
	{
		return date_created;
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
	 * @return Returns the ideal_max_qty.
	 */
	public String getIdeal_max_qty ()
	{
		return ideal_max_qty;
	}

	/**
	 * @return Returns the item_type.
	 */
	public String getItem_type ()
	{
		return item_type;
	}

	/**
	 * @return Returns the nomenclature_id.
	 */
	public String getNomenclature_id ()
	{
		return nomenclature_id;
	}

	/**
	 * @return Returns the review_for_scrap_date.
	 */
	public String getReview_for_scrap_date ()
	{
		return review_for_scrap_date;
	}

	/**
	 * @return Returns the stock_number.
	 */
	public String getStock_number ()
	{
		return stock_number;
	}

	/**
	 * @return Returns the uofm_description.
	 */
	public String getUofm_description ()
	{
		return uofm_description;
	}
	
	
	/**
	 * @return Returns the last_shipped_date.
	 */
	public String getLast_shipped_date ()
	{
		return last_shipped_date;
	}

	/**
	 * @param availableQty The availableQty to set.
	 */
	public void setAvailableQty ( String availableQty )
	{
		this.availableQty = availableQty;
	}

	/**
	 * @param date_created The date_created to set.
	 */
	public void setDate_created ( String date_created )
	{
		this.date_created = date_created;
	}

	/**
	 * @param description
	 *        The description to set.
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
	 * @param ideal_max_qty The ideal_max_qty to set.
	 */
	public void setIdeal_max_qty ( String ideal_max_qty )
	{
		this.ideal_max_qty = ideal_max_qty;
	}

	/**
	 * @param item_type The item_type to set.
	 */
	public void setItem_type ( String item_type )
	{
		this.item_type = item_type;
	}

	/**
	 * @param nomenclature_id
	 *        The nomenclature_id to set.
	 */
	public void setNomenclature_id ( String nomenclature_id )
	{
		this.nomenclature_id = nomenclature_id;
	}

	/**
	 * @param review_for_scrap_date The review_for_scrap_date to set.
	 */
	public void setReview_for_scrap_date ( String review_for_scrap_date )
	{
		this.review_for_scrap_date = review_for_scrap_date;
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
	 * @param uofm_description The uofm_description to set.
	 */
	public void setUofm_description ( String uofm_description )
	{
		this.uofm_description = uofm_description;
	}
	
	/**
	 * @param last_shipped_date The last_shipped_date to set.
	 */
	public void setLast_shipped_date ( String last_shipped_date )
	{
		this.last_shipped_date = last_shipped_date;
	}
}