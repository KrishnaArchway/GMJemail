package com.archway.globalemail.entity;

/**
 * Archway Marketing Services.
 * 7525 Cogswell Rd,
 * Romulus, MI - 48174.
 * Phone - 734.713.2000
 * 
 * Project Name            : gmJemail
 * 
 * Author                  : bhattam0 - Amar Bhatt Email:(AMAR_BHATT@ARCHWAY.COM) Ext:(2019)
 * File Name               : BackOrder.java 
 * Package Name            : com.archway.globalemail.entity
 * 
 * Date                    : Jun 19, 2007 3:02:59 PM
 * 
 * Change Date             : Jun 19, 2007 3:02:59 PM
 * 							 Jun 19, 2007 3:09:11 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */

public class BackOrder
{

	private String	current_inventory_available;

	private String	description;

	private String	earliest_backorder_date;

	private String	ideal_maximum_qty;

	private String	latest_backorder_date;

	private String	number_of_orders_on_backorder;

	private String	piece_quantity_on_backorder;

	private String	reorder_lead_time;

	private String	stock_number;

	private String	supplier;

	private String	unit_of_measure_description;

	/**
	 * Default Contructor.
	 *
	 */
	public BackOrder ()
	{
		this.stock_number = "";
		this.description = "";
		this.earliest_backorder_date = "";
		this.latest_backorder_date = "";
		this.number_of_orders_on_backorder = "";
		this.piece_quantity_on_backorder = "";
		this.current_inventory_available = "";
		this.reorder_lead_time = "";
		this.ideal_maximum_qty = "";
		this.supplier = "";
		this.unit_of_measure_description = "";
	}

	/**
	 * @return Returns the current_inventory_available.
	 */
	public String getCurrent_inventory_available ()
	{
		return current_inventory_available;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription ()
	{
		return description;
	}

	/**
	 * @return Returns the earliest_backorder_date.
	 */
	public String getEarliest_backorder_date ()
	{
		return earliest_backorder_date;
	}

	/**
	 * @return Returns the ideal_maximum_qty.
	 */
	public String getIdeal_maximum_qty ()
	{
		return ideal_maximum_qty;
	}

	/**
	 * @return Returns the latest_backorder_date.
	 */
	public String getLatest_backorder_date ()
	{
		return latest_backorder_date;
	}

	/**
	 * @return Returns the number_of_orders_on_backorder.
	 */
	public String getNumber_of_orders_on_backorder ()
	{
		return number_of_orders_on_backorder;
	}

	/**
	 * @return Returns the piece_quantity_on_backorder.
	 */
	public String getPiece_quantity_on_backorder ()
	{
		return piece_quantity_on_backorder;
	}

	/**
	 * @return Returns the reorder_lead_time.
	 */
	public String getReorder_lead_time ()
	{
		return reorder_lead_time;
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
	 * @return Returns the unit_of_measure_description.
	 */
	public String getUnit_of_measure_description ()
	{
		return unit_of_measure_description;
	}

	/**
	 * @param current_inventory_available The current_inventory_available to set.
	 */
	public void setCurrent_inventory_available (
												String current_inventory_available )
	{
		this.current_inventory_available = current_inventory_available;
	}

	/**
	 * @param description The description to set.
	 */
	public void setDescription ( String description )
	{
		this.description = description;
	}

	/**
	 * @param earliest_backorder_date The earliest_backorder_date to set.
	 */
	public void setEarliest_backorder_date ( String earliest_backorder_date )
	{
		this.earliest_backorder_date = earliest_backorder_date;
	}

	/**
	 * @param ideal_maximum_qty The ideal_maximum_qty to set.
	 */
	public void setIdeal_maximum_qty ( String ideal_maximum_qty )
	{
		this.ideal_maximum_qty = ideal_maximum_qty;
	}

	/**
	 * @param latest_backorder_date The latest_backorder_date to set.
	 */
	public void setLatest_backorder_date ( String latest_backorder_date )
	{
		this.latest_backorder_date = latest_backorder_date;
	}

	/**
	 * @param number_of_orders_on_backorder The number_of_orders_on_backorder to set.
	 */
	public void setNumber_of_orders_on_backorder (
													String number_of_orders_on_backorder )
	{
		this.number_of_orders_on_backorder = number_of_orders_on_backorder;
	}

	/**
	 * @param piece_quantity_on_backorder The piece_quantity_on_backorder to set.
	 */
	public void setPiece_quantity_on_backorder (
												String piece_quantity_on_backorder )
	{
		this.piece_quantity_on_backorder = piece_quantity_on_backorder;
	}

	/**
	 * @param reorder_lead_time The reorder_lead_time to set.
	 */
	public void setReorder_lead_time ( String reorder_lead_time )
	{
		this.reorder_lead_time = reorder_lead_time;
	}

	/**
	 * @param stock_number The stock_number to set.
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
	 * @param unit_of_measure_description The unit_of_measure_description to set.
	 */
	public void setUnit_of_measure_description (
												String unit_of_measure_description )
	{
		this.unit_of_measure_description = unit_of_measure_description;
	}

}
