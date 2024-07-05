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
 * File Name               : BillingNotification.java 
 * Package Name            : com.archway.globalemail.entity
 * 
 * Date                    : Oct 27, 2005 - 3:00:29 PM
 * 
 * Change Date             : May 18, 2006 4:04:44 PM
 *                           May 30, 2006 12:22:03 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * Holder Class
 */

public class BillingNotification
{

	private String	file_name;

	private String	file_transmission_status;

	private String	invoice_count;

	private String	invoice_end_date;

	private String	invoice_start_date;

	private String	invoice_total;

	private String	invoicing_type;

	private String	parent_file_id;

	private String	program_code;

	private String	vendor_transmission_code;

	/**
	 * Default Constructor.
	 *
	 */
	public BillingNotification ()
	{

	}

	/**
	 * @return Returns the file_name.
	 */
	public String getFile_name ()
	{
		return file_name;
	}

	/**
	 * @return Returns the file_transmission_status.
	 */
	public String getFile_transmission_status ()
	{
		return file_transmission_status;
	}

	/**
	 * @return Returns the invoice_count.
	 */
	public String getInvoice_count ()
	{
		return invoice_count;
	}

	/**
	 * @return Returns the invoice_end_date.
	 */
	public String getInvoice_end_date ()
	{
		return invoice_end_date;
	}

	/**
	 * @return Returns the invoice_start_date.
	 */
	public String getInvoice_start_date ()
	{
		return invoice_start_date;
	}

	/**
	 * @return Returns the invoice_total.
	 */
	public String getInvoice_total ()
	{
		return invoice_total;
	}

	/**
	 * @return Returns the invoicing_type.
	 */
	public String getInvoicing_type ()
	{
		return invoicing_type;
	}

	/**
	 * @return Returns the parent_file_id.
	 */
	public String getParent_file_id ()
	{
		return parent_file_id;
	}

	/**
	 * @return Returns the program_code.
	 */
	public String getProgram_code ()
	{
		return program_code;
	}

	/**
	 * @return Returns the vendor_transmission_code.
	 */
	public String getVendor_transmission_code ()
	{
		return vendor_transmission_code;
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
	 * @param file_transmission_status
	 *        The file_transmission_status to set.
	 */
	public void setFile_transmission_status ( String file_transmission_status )
	{
		this.file_transmission_status = file_transmission_status;
	}

	/**
	 * @param invoice_count
	 *        The invoice_count to set.
	 */
	public void setInvoice_count ( String invoice_count )
	{
		this.invoice_count = invoice_count;
	}

	/**
	 * @param invoice_end_date
	 *        The invoice_end_date to set.
	 */
	public void setInvoice_end_date ( String invoice_end_date )
	{
		this.invoice_end_date = invoice_end_date;
	}

	/**
	 * @param invoice_start_date
	 *        The invoice_start_date to set.
	 */
	public void setInvoice_start_date ( String invoice_start_date )
	{
		this.invoice_start_date = invoice_start_date;
	}

	/**
	 * @param invoice_total
	 *        The invoice_total to set.
	 */
	public void setInvoice_total ( String invoice_total )
	{
		this.invoice_total = invoice_total;
	}

	/**
	 * @param invoicing_type
	 *        The invoicing_type to set.
	 */
	public void setInvoicing_type ( String invoicing_type )
	{
		this.invoicing_type = invoicing_type;
	}

	/**
	 * @param parent_file_id
	 *        The parent_file_id to set.
	 */
	public void setParent_file_id ( String parent_file_id )
	{
		this.parent_file_id = parent_file_id;
	}

	/**
	 * @param program_code
	 *        The program_code to set.
	 */
	public void setProgram_code ( String program_code )
	{
		this.program_code = program_code;
	}

	/**
	 * @param vendor_transmission_code
	 *        The vendor_transmission_code to set.
	 */
	public void setVendor_transmission_code ( String vendor_transmission_code )
	{
		this.vendor_transmission_code = vendor_transmission_code;
	}
}