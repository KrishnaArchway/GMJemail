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
 * File Name               : FileLocation.java 
 * Package Name            : com.archway.globalemail.entity
 * 
 * Date                    : Oct 27, 2005 - 3:01:14 PM
 * 
 * Change Date             : May 18, 2006 3:59:26 PM
 *                           May 30, 2006 12:24:40 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * Holder Bean/Class
 */

public class FileLocation
{

	private String	client				= "";

	private String	created_by			= "";

	private String	creation_date		= "";

	private String	description			= "";

	private String	directory_name		= "";

	private String	facility			= "";

	private String	file_location_id	= "";

	private String	file_name			= "";

	private String	last_update_date	= "";

	private String	last_updated_by		= "";

	private String	path_name			= "";

	private String	unix_network_type	= "";

	private String	usage_type			= "";

	/**
	 * Default Constructor.
	 *
	 */
	public FileLocation ()
	{
	}

	/**
	 * @return Returns the client.
	 */
	public String getClient ()
	{
		return client;
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
	 * @return Returns the description.
	 */
	public String getDescription ()
	{
		return description;
	}

	/**
	 * @return Returns the directory_name.
	 */
	public String getDirectory_name ()
	{
		return directory_name;
	}

	/**
	 * @return Returns the facility.
	 */
	public String getFacility ()
	{
		return facility;
	}

	/**
	 * @return Returns the file_location_id.
	 */
	public String getFile_location_id ()
	{
		return file_location_id;
	}

	/**
	 * @return Returns the file_name.
	 */
	public String getFile_name ()
	{
		return file_name;
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
	 * @return Returns the path_name.
	 */
	public String getPath_name ()
	{
		return path_name;
	}

	/**
	 * @return Returns the unix_network_type.
	 */
	public String getUnix_network_type ()
	{
		return unix_network_type;
	}

	/**
	 * @return Returns the usage_type.
	 */
	public String getUsage_type ()
	{
		return usage_type;
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
	 * @param description
	 *        The description to set.
	 */
	public void setDescription ( String description )
	{
		this.description = description;
	}

	/**
	 * @param directory_name
	 *        The directory_name to set.
	 */
	public void setDirectory_name ( String directory_name )
	{
		this.directory_name = directory_name;
	}

	/**
	 * @param facility
	 *        The facility to set.
	 */
	public void setFacility ( String facility )
	{
		this.facility = facility;
	}

	/**
	 * @param file_location_id
	 *        The file_location_id to set.
	 */
	public void setFile_location_id ( String file_location_id )
	{
		this.file_location_id = file_location_id;
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
	 * @param path_name
	 *        The path_name to set.
	 */
	public void setPath_name ( String path_name )
	{
		this.path_name = path_name;
	}

	/**
	 * @param unix_network_type
	 *        The unix_network_type to set.
	 */
	public void setUnix_network_type ( String unix_network_type )
	{
		this.unix_network_type = unix_network_type;
	}

	/**
	 * @param usage_type
	 *        The usage_type to set.
	 */
	public void setUsage_type ( String usage_type )
	{
		this.usage_type = usage_type;
	}
}