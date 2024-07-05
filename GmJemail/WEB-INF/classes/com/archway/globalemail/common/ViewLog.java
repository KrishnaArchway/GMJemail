package com.archway.globalemail.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * File Name               : ViewLog.java 
 * Package Name            : com.archway.globalemail.common
 * 
 * Date                    : Oct 27, 2005 - 2:58:29 PM
 * 
 * Change Date             : May 18, 2006 3:56:25 PM
 *                           May 30, 2006 12:07:23 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */

public class ViewLog extends HttpServlet
{

	private void displayFile (
								File file ,
								double skipPercentage ,
								ServletOutputStream out )
	{
		try
		{
			out.println ( "<br>Preparing to read file!" );
			long length = file.length ();
			double percent = skipPercentage * .01D;
			long skipVal = Math.round ( length * percent );
			out.println ( "<br>length: " + length );
			out.println ( "<br>percent: " + percent );
			out.println ( "<br>skipValue: " + skipVal );
			FileInputStream fis = new FileInputStream ( file );
			BufferedInputStream bis = new BufferedInputStream ( fis );
			BufferedReader reader = new BufferedReader ( new InputStreamReader ( bis ) );
			String line = null;
			reader.skip ( skipVal );
			while ( ( line = reader.readLine () ) != null )
			{
				out.println ( "<br>" + line );
			}
			out.println ( "<br><h2>EOF</h2><br>" );
			out.flush ();
			return;
		}
		catch ( Exception e )
		{
			try
			{
				out.println ( "<br>Exception: " + e + "\nmessage: " + e.getMessage () );
				out.flush ();
				return;
			}
			catch ( IOException ioe )
			{
				log ( "Exception: " + e + "\nmessage: " + e.getMessage () );
			}
		}
	}

	// Forward http get calls to http post handler.
	public void doGet (
						HttpServletRequest request ,
						HttpServletResponse response )
														throws ServletException ,
														IOException
	{
		doPost ( request , response );
	}

	// Handle customer requests.
	public void doPost (
						HttpServletRequest request ,
						HttpServletResponse response )
														throws ServletException ,
														IOException
	{
		String password = request.getParameter ( "p" );
		ServletOutputStream out = response.getOutputStream ();
		if ( password == null || password.length () < 1 || ! password.equalsIgnoreCase ( "m1x1p1yx" ) )
		{
			throw new ServletException ( "Usage Error" );
		}
		out.println ( "<html><h1>ViewLog</h1><br>Usage: ViewLog?filenamename=filename&skip=##<br>file: path of file to read <br>skip: percentage of file to skip (defaults to 0)<br><br>" );
		Double skip = null;
		String filename = request.getParameter ( "filename" );
		try
		{
			skip = new Double ( request.getParameter ( "skip" ) );
		}
		catch ( Exception e )
		{
			skip = new Double ( 0 );
		}
		if ( filename == null )
		{
			filename = "errors";
		}

		File logfile = new File ( filename );
		if ( ! logfile.exists () )
		{
			out.println ( "<br>Log file: " + logfile.getName () + " does not exist." );
			out.flush ();
			out.println ( "</html>" );
			return;
		}
		else if ( ! logfile.canRead () )
		{
			out.println ( "<br>Log file: " + logfile.getName () + " cannot be read." );
			out.flush ();
			out.println ( "</html>" );
			return;
		}
		else if ( logfile.isDirectory () )
		{
			out.println ( "Directory contents:<br><br>" );
			String arr[] = logfile.list ();
			for ( int i = 0 ; i < arr.length ; i++ )
			{
				out.println ( arr [i] );
				out.println ( "<br>" );
			}
			out.println ( "</html>" );
			return;
		}

		displayFile ( logfile , skip.doubleValue () , out );
		out.println ( "</html>" );
	}
}