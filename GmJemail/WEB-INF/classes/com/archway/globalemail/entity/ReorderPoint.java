package com.archway.globalemail.entity;

public class ReorderPoint {

	private String ave_monthly_usage;
	private String company;
	private String description;
	private String description_for_www;
	private String ideal_max_qty;
	private String low_water_point;
	private String reorder_point; // Changed variable name by Gunjan Reorder-point
	private String quantity_on_hand;
	private String quantity_pending;
	private String reorder_lead_time;
	private String stock_number;
	private String total_usage;
	private String uofm_description;
	private String yearly_usage;

	// Default Constructor
	public ReorderPoint() {

	}

	// Getters and Setters

	public String getAve_monthly_usage() {
		return ave_monthly_usage;
	}

	public void setAve_monthly_usage(String ave_monthly_usage) {
		this.ave_monthly_usage = ave_monthly_usage;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription_for_www() {
		return description_for_www;
	}

	public void setDescription_for_www(String description_for_www) {
		this.description_for_www = description_for_www;
	}

	public String getIdeal_max_qty() {
		return ideal_max_qty;
	}

	public void setIdeal_max_qty(String ideal_max_qty) {
		this.ideal_max_qty = ideal_max_qty;
	}

	public String getReorder_point() {
		return reorder_point;
	}

	public void setReorder_point(String reorder_point) {
		this.reorder_point = reorder_point;
	}

	public String getQuantity_on_hand() {
		return quantity_on_hand;
	}

	public void setQuantity_on_hand(String quantity_on_hand) {
		this.quantity_on_hand = quantity_on_hand;
	}

	public String getQuantity_pending() {
		return quantity_pending;
	}

	public void setQuantity_pending(String quantity_pending) {
		this.quantity_pending = quantity_pending;
	}

	public String getReorder_lead_time() {
		return reorder_lead_time;
	}

	public void setReorder_lead_time(String reorder_lead_time) {
		this.reorder_lead_time = reorder_lead_time;
	}

	public String getStock_number() {
		return stock_number;
	}

	public void setStock_number(String stock_number) {
		this.stock_number = stock_number;
	}

	public String getTotal_usage() {
		return total_usage;
	}

	public void setTotal_usage(String total_usage) {
		this.total_usage = total_usage;
	}

	public String getUofm_description() {
		return uofm_description;
	}

	public void setUofm_description(String uofm_description) {
		this.uofm_description = uofm_description;
	}

	public String getYearly_usage() {
		return yearly_usage;
	}

	public void setYearly_usage(String yearly_usage) {
		this.yearly_usage = yearly_usage;
	}

	public String getLow_water_point() {
		return low_water_point;
	}

	public void setLow_water_point(String low_water_point) {
		this.low_water_point = low_water_point;
	}
}
