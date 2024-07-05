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
 * File Name               : EmailClient.java 
 * Package Name            : com.archway.globalemail.entity
 * 
 * Date                    : Oct 27, 2005 - 3:00:45 PM
 * 
 * Change Date             : May 18, 2006 4:03:58 PM
 * 						     May 30, 2006 12:23:08 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * Holder Bean/Class
 */

public class EmailClient
{

	private String	business_partner_id;

	private String	client;

	private String	email_address;

	private String	email_bp_type;

	private String	email_client_id;

	private String	email_client_recipient_id;

	private String	email_history_id;

	private String	email_type;

	private String	file_description;

	private String	file_name;

	private String	html_file_location_id;

	private String	invoice_file_id;

	private String	job_number_id;

	private String	media_id		= "";

	private String	nomenclature_id;

	private String	order_number;

	private String	packslip_logo	= "";

	private String	path_name;

	private String	priority		= "";

	private String	recipient_type;

	private String	sender;

	private String	subject;

	private String	trans_id;

	/**
	 * default constructor
	 */
	public EmailClient ()
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
	 * @return Returns the client.
	 */
	public String getClient ()
	{
		return client;
	}

	/**
	 * @return Returns the email_address.
	 */
	public String getEmail_address ()
	{
		return email_address;
	}

	/**
	 * @return Returns the email_bp_type.
	 */
	public String getEmail_bp_type ()
	{
		return email_bp_type;
	}

	/**
	 * @return Returns the email_client_id.
	 */
	public String getEmail_client_id ()
	{
		return email_client_id;
	}

	/**
	 * @return Returns the email_client_recipient_id.
	 */
	public String getEmail_client_recipient_id ()
	{
		return email_client_recipient_id;
	}

	/**
	 * @return Returns the email_history_id.
	 */
	public String getEmail_history_id ()
	{
		return email_history_id;
	}

	/**
	 * @return Returns the email_type.
	 */
	public String getEmail_type ()
	{
		return email_type;
	}

	/**
	 * @return Returns the file_description.
	 */
	public String getFile_description ()
	{
		return file_description;
	}

	/**
	 * @return Returns the file_name.
	 */
	public String getFile_name ()
	{
		return file_name;
	}

	/**
	 * @return Returns the html_file_location_id.
	 */
	public String getHtml_file_location_id ()
	{
		return html_file_location_id;
	}

	/**
	 * @return Returns the invoice_file_id.
	 */
	public String getInvoice_file_id ()
	{
		return invoice_file_id;
	}

	/**
	 * @return Returns the job_number_id.
	 */
	public String getJob_number_id ()
	{
		return job_number_id;
	}

	/**
	 * @return Returns the media_id.
	 */
	public String getMedia_id ()
	{
		return media_id;
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
	 * @return Returns the packslip_logo.
	 */
	public String getPackslip_logo ()
	{
		return packslip_logo;
	}

	/**
	 * @return Returns the path_name.
	 */
	public String getPath_name ()
	{
		return path_name;
	}

	/**
	 * @return Returns the priority.
	 */
	public String getPriority ()
	{
		return priority;
	}

	/**
	 * @return Returns the recipient_type.
	 */
	public String getRecipient_type ()
	{
		return recipient_type;
	}

	/**
	 * @return Returns the sender.
	 */
	public String getSender ()
	{
		return sender;
	}

	/**
	 * @return Returns the subject.
	 */
	public String getSubject ()
	{
		return subject;
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
	 * @param client
	 *        The client to set.
	 */
	public void setClient ( String client )
	{
		this.client = client;
	}

	/**
	 * @param email_address
	 *        The email_address to set.
	 */
	public void setEmail_address ( String email_address )
	{
		this.email_address = email_address;
	}

	/**
	 * @param email_bp_type
	 *        The email_bp_type to set.
	 */
	public void setEmail_bp_type ( String email_bp_type )
	{
		this.email_bp_type = email_bp_type;
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
	 * @param email_client_recipient_id
	 *        The email_client_recipient_id to set.
	 */
	public void setEmail_client_recipient_id ( String email_client_recipient_id )
	{
		this.email_client_recipient_id = email_client_recipient_id;
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
	 * @param email_type
	 *        The email_type to set.
	 */
	public void setEmail_type ( String email_type )
	{
		this.email_type = email_type;
	}

	/**
	 * @param file_description
	 *        The file_description to set.
	 */
	public void setFile_description ( String file_description )
	{
		this.file_description = file_description;
	}

	/**
	 * @param file_name
	 *        The file_name to set.
	 */
	public void setFile_name ( String file_name )
	{
		this.file_name = file_name;
	}

	/**
	 * @param html_file_location_id
	 *        The html_file_location_id to set.
	 */
	public void setHtml_file_location_id ( String html_file_location_id )
	{
		this.html_file_location_id = html_file_location_id;
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
	 * @param job_number_id
	 *        The job_number_id to set.
	 */
	public void setJob_number_id ( String job_number_id )
	{
		this.job_number_id = job_number_id;
	}

	/**
	 * @param media_id
	 *        The media_id to set.
	 */
	public void setMedia_id ( String media_id )
	{
		this.media_id = media_id;
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
	 * @param packslip_logo
	 *        The packslip_logo to set.
	 */
	public void setPackslip_logo ( String packslip_logo )
	{
		this.packslip_logo = packslip_logo;
	}

	/**
	 * @param path_name
	 *        The path_name to set.
	 */
	public void setPath_name ( String path_name )
	{
		this.path_name = path_name;
	}

	/**
	 * @param priority
	 *        The priority to set.
	 */
	public void setPriority ( String priority )
	{
		this.priority = priority;
	}

	/**
	 * @param recipient_type
	 *        The recipient_type to set.
	 */
	public void setRecipient_type ( String recipient_type )
	{
		this.recipient_type = recipient_type;
	}

	/**
	 * @param sender
	 *        The sender to set.
	 */
	public void setSender ( String sender )
	{
		this.sender = sender;
	}

	/**
	 * @param subject
	 *        The subject to set.
	 */
	public void setSubject ( String subject )
	{
		this.subject = subject;
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