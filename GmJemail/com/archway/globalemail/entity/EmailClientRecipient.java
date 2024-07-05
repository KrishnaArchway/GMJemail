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
 * File Name               : EmailClientRecipient.java 
 * Package Name            : com.archway.globalemail.entity
 * 
 * Date                    : Oct 27, 2005 - 3:00:56 PM
 * 
 * Change Date             : May 18, 2006 3:58:16 PM
 *                           May 30, 2006 12:23:33 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * Holder Bean/Class
 */
public class EmailClientRecipient
{

	private String	created_by					= "";

	private String	creation_date				= "";

	private String	email_address				= "";

	private String	email_bp_type				= "";

	private String	email_client_id				= "";

	private String	email_client_recipient_id	= "";

	private String	last_update_date			= "";

	private String	last_updated_by				= "";

	private String	recipient_type				= "";

	private String	valid_flag					= "";

	/**
	 * Default Constructor.
	 *
	 */
	public EmailClientRecipient ()
	{
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
	 * @return Returns the recipient_type.
	 */
	public String getRecipient_type ()
	{
		return recipient_type;
	}

	/**
	 * @return Returns the valid_flag.
	 */
	public String getValid_flag ()
	{
		return valid_flag;
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
	 * @param recipient_type
	 *        The recipient_type to set.
	 */
	public void setRecipient_type ( String recipient_type )
	{
		this.recipient_type = recipient_type;
	}

	/**
	 * @param valid_flag
	 *        The valid_flag to set.
	 */
	public void setValid_flag ( String valid_flag )
	{
		this.valid_flag = valid_flag;
	}
}