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
 * File Name               : EmailHistory.java 
 * Package Name            : com.archway.globalemail.entity
 * 
 * Date                    : Oct 27, 2005 - 3:01:04 PM
 * 
 * Change Date             : May 18, 2006 3:59:09 PM
 *                           May 30, 2006 12:24:15 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */

public class EmailHistory
{

	private String	business_partner_id	= "";

	private String	created_by			= "";

	private String	creation_date		= "";

	private String	email_client_id		= "";

	private String	email_history_id	= "";

	private String	invoice_file_id		= "";

	private String	last_update_date	= "";

	private String	last_updated_by		= "";

	private String	nomenclature_id		= "";

	private String	order_number		= "";

	private String	sent_date			= "";

	private String	sent_flag			= "";

	private String	trans_id			= "";

	/**
	 * Default Constructor.
	 *
	 */
	public EmailHistory ()
	{
	}

	/**
	 * @return Returns the business_partner_id.
	 */
	public String getBusiness_partner_id ()
	{
		return business_partner_id;
	}

	/**
	 * @return Returns the created_by.
	 */
	public String getCreated_by ()
	{
		return created_by;
	}

	/**
	 * @return Returns the creation_date.
	 */
	public String getCreation_date ()
	{
		return creation_date;
	}

	/**
	 * @return Returns the email_client_id.
	 */
	public String getEmail_client_id ()
	{
		return email_client_id;
	}

	/**
	 * @return Returns the email_history_id.
	 */
	public String getEmail_history_id ()
	{
		return email_history_id;
	}

	/**
	 * @return Returns the invoice_file_id.
	 */
	public String getInvoice_file_id ()
	{
		return invoice_file_id;
	}

	/**
	 * @return Returns the last_update_date.
	 */
	public String getLast_update_date ()
	{
		return last_update_date;
	}

	/**
	 * @return Returns the last_updated_by.
	 */
	public String getLast_updated_by ()
	{
		return last_updated_by;
	}

	/**
	 * @return Returns the nomenclature_id.
	 */
	public String getNomenclature_id ()
	{
		return nomenclature_id;
	}

	/**
	 * @return Returns the order_number.
	 */
	public String getOrder_number ()
	{
		return order_number;
	}

	/**
	 * @return Returns the sent_date.
	 */
	public String getSent_date ()
	{
		return sent_date;
	}

	/**
	 * @return Returns the sent_flag.
	 */
	public String getSent_flag ()
	{
		return sent_flag;
	}

	/**
	 * @return Returns the trans_id.
	 */
	public String getTrans_id ()
	{
		return trans_id;
	}

	/**
	 * @param business_partner_id
	 *        The business_partner_id to set.
	 */
	public void setBusiness_partner_id ( String business_partner_id )
	{
		this.business_partner_id = business_partner_id;
	}

	/**
	 * @param created_by
	 *        The created_by to set.
	 */
	public void setCreated_by ( String created_by )
	{
		this.created_by = created_by;
	}

	/**
	 * @param creation_date
	 *        The creation_date to set.
	 */
	public void setCreation_date ( String creation_date )
	{
		this.creation_date = creation_date;
	}

	/**
	 * @param email_client_id
	 *        The email_client_id to set.
	 */
	public void setEmail_client_id ( String email_client_id )
	{
		this.email_client_id = email_client_id;
	}

	/**
	 * @param email_history_id
	 *        The email_history_id to set.
	 */
	public void setEmail_history_id ( String email_history_id )
	{
		this.email_history_id = email_history_id;
	}

	/**
	 * @param invoice_file_id
	 *        The invoice_file_id to set.
	 */
	public void setInvoice_file_id ( String invoice_file_id )
	{
		this.invoice_file_id = invoice_file_id;
	}

	/**
	 * @param last_update_date
	 *        The last_update_date to set.
	 */
	public void setLast_update_date ( String last_update_date )
	{
		this.last_update_date = last_update_date;
	}

	/**
	 * @param last_updated_by
	 *        The last_updated_by to set.
	 */
	public void setLast_updated_by ( String last_updated_by )
	{
		this.last_updated_by = last_updated_by;
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
	 * @param order_number
	 *        The order_number to set.
	 */
	public void setOrder_number ( String order_number )
	{
		this.order_number = order_number;
	}

	/**
	 * @param sent_date
	 *        The sent_date to set.
	 */
	public void setSent_date ( String sent_date )
	{
		this.sent_date = sent_date;
	}

	/**
	 * @param sent_flag
	 *        The sent_flag to set.
	 */
	public void setSent_flag ( String sent_flag )
	{
		this.sent_flag = sent_flag;
	}

	/**
	 * @param trans_id
	 *        The trans_id to set.
	 */
	public void setTrans_id ( String trans_id )
	{
		this.trans_id = trans_id;
	}
}