package com.archway.globalemail.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.w3c.tidy.Tidy;

import com.archway.globalemail.common.Logger;

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
 * File Name               : html.java 
 * Package Name            : com.archway.globalemail.util
 * 
 * Date                    : Feb 2, 2006 10:51:07 PM
 * 
 * Change Date             : Feb 3, 2006 1:25 pm
 *                           Monday, March 27, 2006
 *                           May 18, 2006 4:14:05 PM
 *                           May 30, 2006 12:36:31 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */

public class Html
{

	public static final Logger	logger	= Logger.getLogger ( Html.class );

	/**
	 * formats and clean the string containing html
	 * 
	 * @param in
	 * @return
	 */
	public static String cleanHtml ( String in )
	{

		String outx = "";
		try
		{
			Tidy tidy = new Tidy ();
			tidy.setCharEncoding ( 4 );
			long tidylength;
			//at input not XML
			tidy.setQuiet ( true );
			tidy.setShowWarnings ( false );
			//tidy.setXmlOut(true);
			tidy.setXHTML ( true );
			tidy.setWord2000 ( true );
			tidy.setUpperCaseTags ( true );
			tidy.setCharEncoding ( org.w3c.tidy.Configuration.UTF8 );
			tidy.setMakeClean ( true );
			tidy.setSmartIndent ( true );
			tidy.setUpperCaseAttrs ( true );
			String s = in;
			char chars[] = s.toCharArray ();
			byte bytes[] = new byte[chars.length];
			for ( int i = 0 ; i < chars.length ; i++ )
			{
				bytes [i] = ( byte ) chars [i];
			}
			ByteArrayInputStream bais = new ByteArrayInputStream ( bytes );
			ByteArrayOutputStream out = new ByteArrayOutputStream ();
			tidy.parse ( bais , out );
			//outx = "\n********\n" + out.toString () + "********\n";
			outx = out.toString ();
		}
		catch ( Exception e )
		{
			logger.warn ( e.getMessage () );
		}
		return outx;
	}

	/**
	 * Default Constructor
	 *
	 */
	public Html ()
	{

	}
}