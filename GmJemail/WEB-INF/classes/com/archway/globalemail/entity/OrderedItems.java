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
 * File Name               : OrderedItems.java 
 * Package Name            : com.archway.globalemail.entity
 * 
 * Date                    : Oct 27, 2005 - 3:01:51 PM
 * 
 * Change Date             : May 18, 2006 4:00:58 PM
 *                           May 30, 2006 12:26:39 PM
 *                           Dec 1, 2009 1:03:57 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * Holder Bean/Class
 */
public class OrderedItems
{

	private String	description				= "";

	private String	description_for_www		= "";

	private String	efullfillment_item		= "";

	private String	ext_amount				= "";

	private String	image_name				= "";

	private String	nomenclature_id			= "";

	private String	ptoduct_total			= "";

	private String	quantity_ordered		= "";

	private String	quantity_shipped		= "";

	private String	quantity_to_be_picked	= "";

	private String	quantity_to_be_shipped	= "";

	private String	sequence_number			= "";

	private String	stock_number			= "";

	private String	unit_price				= "";

	private String	quantity_backOrdered	= "";

	private String	quantity_cancelled		= "";

	/**
	 * @return Returns the quantity_cancelled.
	 */
	public String getQuantity_cancelled ()
	{
		return quantity_cancelled;
	}

	/**
	 * @param quantity_cancelled The quantity_cancelled to set.
	 */
	public void setQuantity_cancelled ( String quantity_cancelled )
	{
		this.quantity_cancelled = quantity_cancelled;
	}

	/**
	 * Default Constructor.
	 *
	 */
	public OrderedItems ()
	{
	}

	/**
	 * @return Returns the quantity_backOrdered.
	 */
	public String getQuantity_backOrdered ()
	{
		return quantity_backOrdered;
	}

	/**
	 * @param quantity_backOrdered The quantity_backOrdered to set.
	 */
	public void setQuantity_backOrdered ( String quantity_backOrdered )
	{
		this.quantity_backOrdered = quantity_backOrdered;
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
	 * @return Returns the efullfillment_item.
	 */
	public String getEfullfillment_item ()
	{
		return efullfillment_item;
	}

	/**
	 * @return Returns the ext_amount.
	 */
	public String getExt_amount ()
	{
		return ext_amount;
	}

	/**
	 * @return Returns the image_name.
	 */
	public String getImage_name ()
	{
		return image_name;
	}

	/**
	 * @return Returns the nomenclature_id.
	 */
	public String getNomenclature_id ()
	{
		return nomenclature_id;
	}

	/**
	 * @return Returns the ptoduct_total.
	 */
	public String getPtoduct_total ()
	{
		return ptoduct_total;
	}

	/**
	 * @return Returns the quantity_ordered.
	 */
	public String getQuantity_ordered ()
	{
		return quantity_ordered;
	}

	/**
	 * @return Returns the quantity_shipped.
	 */
	public String getQuantity_shipped ()
	{
		return quantity_shipped;
	}

	/**
	 * @return Returns the quantity_to_be_picked.
	 */
	public String getQuantity_to_be_picked ()
	{
		return quantity_to_be_picked;
	}

	/**
	 * @return Returns the quantity_to_be_shipped.
	 */
	public String getQuantity_to_be_shipped ()
	{
		return quantity_to_be_shipped;
	}

	/**
	 * @return Returns the sequence_number.
	 */
	public String getSequence_number ()
	{
		return sequence_number;
	}

	/**
	 * @return Returns the stock_number.
	 */
	public String getStock_number ()
	{
		return stock_number;
	}

	/**
	 * @return Returns the unit_price.
	 */
	public String getUnit_price ()
	{
		return unit_price;
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
	 * @param efullfillment_item
	 *        The efullfillment_item to set.
	 */
	public void setEfullfillment_item ( String efullfillment_item )
	{
		this.efullfillment_item = efullfillment_item;
	}

	/**
	 * @param ext_amount The ext_amount to set.
	 */
	public void setExt_amount ( String ext_amount )
	{
		this.ext_amount = ext_amount;
	}

	/**
	 * @param image_name
	 *        The image_name to set.
	 */
	public void setImage_name ( String image_name )
	{
		this.image_name = image_name;
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
	 * @param ptoduct_total The ptoduct_total to set.
	 */
	public void setPtoduct_total ( String ptoduct_total )
	{
		this.ptoduct_total = ptoduct_total;
	}

	/**
	 * @param quantity_ordered
	 *        The quantity_ordered to set.
	 */
	public void setQuantity_ordered ( String quantity_ordered )
	{
		this.quantity_ordered = quantity_ordered;
	}

	/**
	 * @param quantity_shipped
	 *        The quantity_shipped to set.
	 */
	public void setQuantity_shipped ( String quantity_shipped )
	{
		this.quantity_shipped = quantity_shipped;
	}

	/**
	 * @param quantity_to_be_picked
	 *        The quantity_to_be_picked to set.
	 */
	public void setQuantity_to_be_picked ( String quantity_to_be_picked )
	{
		this.quantity_to_be_picked = quantity_to_be_picked;
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
	 * @param sequence_number
	 *        The sequence_number to set.
	 */
	public void setSequence_number ( String sequence_number )
	{
		this.sequence_number = sequence_number;
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
	 * @param unit_price
	 *        The unit_price to set.
	 */
	public void setUnit_price ( String unit_price )
	{
		this.unit_price = unit_price;
	}
}