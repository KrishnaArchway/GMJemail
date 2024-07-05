package com.archway.globalemail.common;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Writer;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

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
 * File Name               : ConfigurationServlet.java 
 * Package Name            : com.archway.globalemail.common
 * 
 * Date                    : Feb 2, 2006 10:17:29 PM
 * 
 * Change Date             : May 18, 2006 3:56:25 PM
 *                           May 30, 2006 11:53:49 AM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 * ConfigurationServlet initializes the application at JVM startup. Provides
 * static method for retrieving properties files until which time a startup
 * servlet can be used with JSP's in archway's servlet engine. Jserv doesn't
 * currently support object sharing between JSP and servlet zone contexts. 
 */

public class ConfigurationServlet extends HttpServlet
{

	public static Properties	properties	= null;

	/**
	 * Simply logs the properties in use.
	 * 
	 * @param Properties
	 *        file containing application property values.
	 */
	public static void checkRequiredProperties ( Properties props )
	{
		if ( props == null )
		{
			//csLog("Application properties does not exist");
			return;
		}
		//csLog("check required.");
		for ( int i = 0 ; i < IConstants.requiredAppProps.length ; i++ )
		{
			if ( props.getProperty ( IConstants.requiredAppProps [i] ) == null )
			{
				//csLog("Application Property: " + IConstants.requiredAppProps[i] +
				// " DOES NOT EXIST!");
				//csLog("Please check <application root>/WEB-INF/classes/com/wins/"
				// + IConstants.APP_PROP_FILE +" for the specified property.");
			}
		}
	}

	/**
	 * 
	 * 
	 * @param msg
	 */
	public static void csLog ( String msg )
	{
		Writer writer = null;
		try
		{
			//File file = new File("cslog.txt");
			File file = new File ( ".." + File.separator + "Jserv" + File.separator + "logs" , "jserv.log" );
			String path = file.getAbsolutePath ();
			if ( ! file.exists () )
			{
				file = new File ( "logs" , "error_log" ); //Can't log to jserv.log? Try
				// apache's log file.
				path = file.getAbsolutePath ();
				if ( ! file.exists () )
				{
					file.createNewFile ();
				}
			}
			writer = new FileWriter ( path , true );
			writer.write ( new Date ().toString () + "   ConfigurationServlet: " + msg + "\r\n" );
		}
		catch ( Exception e )
		{
		}
		finally
		{
			try
			{
				writer.flush ();
				writer.close ();
			}
			catch ( Exception ee )
			{
			}
		}

	}

	/**
	 * Loads application properties file if found. Otherwise returns empty
	 * properties object. This method is intended for Jserv, not Tomcat or Oracle
	 * Servlet Engine use. When the servlet engine is upgraded, the J2EE standard
	 * web.xml configuration file should be used. The method was placed in this
	 * unused servlet in order to locate a commonly used method in the object
	 * that is intended to perform this functionality as a startup servlet once
	 * the upgrade occurs.
	 * 
	 * @return application properties file if found, otherwise empty object.
	 */
	public static synchronized Properties getApplicationPropertiesFile ()
	{
		//csLog("loading: " + IConstants.APP_PROP_FILE);
		properties = new Properties ();
		try
		{
			InputStream is = Class.forName ( "com.archway.globalemail.common.ConfigurationServlet" ).getResourceAsStream ( IConstants.APP_PROP_FILE );
			properties.load ( is );
			is.close ();
			//csLog("app file loaded: " + IConstants.APP_PROP_FILE);
			checkRequiredProperties ( properties );
			logProperties ( properties );
		}
		catch ( Exception e )
		{/* Not much you can do to notify user unfortunately. */
		}
		return properties;
	}

	/**
	 * Convenience method for retrieving a specific property value.
	 * 
	 * @param String
	 *        defining the property requested
	 * @return String containing the value or a null if none exists.
	 */
	public static synchronized String getApplicationProperty (
																String propertyName )
	{
		//csLog("getApplicationProperty called.");
		if ( properties == null )
		{
			properties = getApplicationPropertiesFile ();
		}
		String ret = properties.getProperty ( propertyName );
		if ( ret == null )
		{
			//csLog("Requested Application property " + propertyName + " not
			// found.");
		}
		return ret;
	}

	/**
	 * Convenience method for retrieving a specific property value.
	 * 
	 * @param String
	 *        defining the property requested
	 * @param String
	 *        defining a value to default to if property doesn't exist.
	 * @return String containing the value or a null if none exists.
	 */
	public static synchronized String getApplicationProperty (
																String propertyName ,
																String defaultValue )
	{
		if ( properties == null )
		{
			properties = getApplicationPropertiesFile ();
		}
		String ret = properties.getProperty ( propertyName );
		if ( ret == null )
		{
			//csLog("Requested Application property " + propertyName + " not
			// found. Using default value.");
			ret = defaultValue;
		}
		return ret;
	}

	/**
	 * Simply logs the properties in use.
	 * 
	 * @param Properties
	 *        file containing application property values.
	 */
	public static void logProperties ( Properties props )
	{
		if ( props == null )
		{
			//csLog("props is null");
			return;
		}
		Enumeration enumx = props.propertyNames ();
		while ( enumx.hasMoreElements () )
		{
			String name = ( String ) enumx.nextElement ();
			//csLog("Prop Name: " + name + " Value: " + props.get(name));
		}
	}

	/**
	 * test the properties file.
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Properties testPropertiesFile () throws Exception
	{
		Properties propertiesx = new Properties ();
		InputStream is = Class.forName ( "com.archway.globalemail.common.ConfigurationServlet" ).getResourceAsStream ( IConstants.APP_PROP_FILE );
		propertiesx.load ( is );
		//csLog("test of app file loaded: " + IConstants.APP_PROP_FILE);
		logProperties ( propertiesx );
		is.close ();
		return propertiesx;
	}

	/**
	 * ConfigurationServlet constructor.
	 */
	public ConfigurationServlet ()
	{
		super ();
	}

	/**
	 * Converts servlet initialization parameters to a properties file usable by
	 * ConnectionManager.
	 * 
	 * @param config
	 *        is the ServletConfig used to populate properties file.
	 * @return Properties file containing name-value pairs extracted from
	 *         ServletConfig
	 */
	private Properties convertToProps ( ServletConfig config )
	{
		Enumeration enumx = config.getInitParameterNames ();
		Properties props = new Properties ();
		String namex = null;
		String value = null;
		while ( enumx.hasMoreElements () )
		{
			namex = ( String ) enumx.nextElement ();
			value = config.getInitParameter ( namex );
			props.setProperty ( namex , value );
		}
		return props;
	}

	/** Performs servlet and application cleanup */
	public void destroy ()
	{
		csLog ( "destroy called" );
		//ConnectionManager.cleanup();
	}

	/**
	 * Initializes any application classes that need it based on init parameters.
	 * 
	 * @param ServeltConfig
	 *        object from which servlet settings are retrieved.
	 */
	public void init ( ServletConfig config ) throws ServletException
	{
		super.init ( config );
	}

}