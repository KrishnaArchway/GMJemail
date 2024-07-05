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
 * File Name               : Email.java 
 * Package Name            : com.archway.globalemail.entity
 * 
 * Date                    : Oct 27, 2005 - 3:00:34 PM
 * 
 * Change Date             : May 18, 2006 4:04:18 PM
 *                           May 30, 2006 12:22:31 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * Holder Bean/Class
 * 
 */

public class Email
{

	private String	bcc				= "";

	private String	body			= "";

	private String	cc				= "";

	private String	displayBccName	= "";

	private String	displayCcName	= "";

	private String	displayFromName	= "";

	private String	displayToName	= "";

	private String	filename		= "";

	private String	from			= "";

	private String	subject			= "";

	private String	to				= "";

	/**
	 * Default Constructor.
	 *
	 */
	public Email ()
	{
	}

	/**
	 * @return Returns the bcc.
	 */
	public String getBcc ()
	{
		return bcc;
	}

	/**
	 * @return Returns the body.
	 */
	public String getBody ()
	{
		return body;
	}

	/**
	 * @return Returns the cc.
	 */
	public String getCc ()
	{
		return cc;
	}

	/**
	 * @return Returns the displayBccName.
	 */
	public String getDisplayBccName ()
	{
		return displayBccName;
	}

	/**
	 * @return Returns the displayCcName.
	 */
	public String getDisplayCcName ()
	{
		return displayCcName;
	}

	/**
	 * @return Returns the displayFromName.
	 */
	public String getDisplayFromName ()
	{
		return displayFromName;
	}

	/**
	 * @return Returns the displayToName.
	 */
	public String getDisplayToName ()
	{
		return displayToName;
	}

	/**
	 * @return Returns the filename.
	 */
	public String getFilename ()
	{
		return filename;
	}

	/**
	 * @return Returns the from.
	 */
	public String getFrom ()
	{
		return from;
	}

	/**
	 * @return Returns the subject.
	 */
	public String getSubject ()
	{
		return subject;
	}

	/**
	 * @return Returns the to.
	 */
	public String getTo ()
	{
		return to;
	}

	/**
	 * @param bcc
	 *        The bcc to set.
	 */
	public void setBcc ( String bcc )
	{
		this.bcc = bcc;
	}

	/**
	 * @param body
	 *        The body to set.
	 */
	public void setBody ( String body )
	{
		this.body = body;
	}

	/**
	 * @param cc
	 *        The cc to set.
	 */
	public void setCc ( String cc )
	{
		this.cc = cc;
	}

	/**
	 * @param displayBccName
	 *        The displayBccName to set.
	 */
	public void setDisplayBccName ( String displayBccName )
	{
		this.displayBccName = displayBccName;
	}

	/**
	 * @param displayCcName
	 *        The displayCcName to set.
	 */
	public void setDisplayCcName ( String displayCcName )
	{
		this.displayCcName = displayCcName;
	}

	/**
	 * @param displayFromName
	 *        The displayFromName to set.
	 */
	public void setDisplayFromName ( String displayFromName )
	{
		this.displayFromName = displayFromName;
	}

	/**
	 * @param displayToName
	 *        The displayToName to set.
	 */
	public void setDisplayToName ( String displayToName )
	{
		this.displayToName = displayToName;
	}

	/**
	 * @param filename
	 *        The filename to set.
	 */
	public void setFilename ( String filename )
	{
		this.filename = filename;
	}

	/**
	 * @param from
	 *        The from to set.
	 */
	public void setFrom ( String from )
	{
		this.from = from;
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
	 * @param to
	 *        The to to set.
	 */
	public void setTo ( String to )
	{
		this.to = to;
	}
}