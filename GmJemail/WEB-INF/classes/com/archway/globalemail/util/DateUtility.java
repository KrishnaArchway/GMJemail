package com.archway.globalemail.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
 * File Name               : DateUtility.java 
 * Package Name            : com.archway.globalemail.util
 * 
 * Date                    : Oct 27, 2005 - 3:07:21 PM
 * 
 * Change Date             : May 18, 2006 4:13:18 PM
 *                           May 30, 2006 12:35:27 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */

public class DateUtility
{

	//	 date format constant

	public static final int		M_D_Y					= 0;																																																																									// 01-31-2004

	public static final int		M_Y						= 2;																																																																									//01-2004

	public static final String	SERVER_TIME_ZONE_ID		= "GMT-5";

	public static final String	TIME_ZONES[]			= new String[]
															{ "GMT-12" , "GMT-11" , "GMT-10" , "GMT-9" , "GMT-8" , "GMT-7" , "GMT-6" , "GMT-5" , "GMT-4" , "GMT-3" , "GMT-2" , "GMT-1" , "GMT" , "GMT+1" , "GMT+2" , "GMT+3" , "GMT+4" , "GMT+5" , "GMT+6" , "GMT+7" , "GMT+8" , "GMT+9" , "GMT+10" , "GMT+11" , "GMT+12" , "GMT+13" };

	// 23:59:59

	public static final String	TIME_ZONES_DISPLAY[]	= new String[]
															{ "GMT-12" , "GMT-11" , "GMT-10" , "GMT-9" , "GMT-8 (PST)" , "GMT-7 (MST)" , "GMT-6 (CST)" , "GMT-5 (EST)" , "GMT-4" , "GMT-3" , "GMT-2" , "GMT-1" , "GMT" , "GMT+1" , "GMT+2" , "GMT+3" , "GMT+4" , "GMT+5" , "GMT+6" , "GMT+7" , "GMT+8" , "GMT+9" , "GMT+10" , "GMT+11" , "GMT+12" , "GMT+13" };

	public static final int		Y_M_D					= 1;																																																																									//2004-01-31

	public static final int		Y_M_D_H_M_S				= 3;																																																																									//2004-01-31

	/**
	 * gets current date and time
	 * 
	 * @return
	 */
	public static String getCurrentDateTime ()
	{
		GregorianCalendar now = ( GregorianCalendar ) Calendar.getInstance ();

		String month = now.get ( Calendar.MONDAY ) + 1 + "";
		if ( month.length () == 1 )
		{
			month = "0" + month;
		}

		String date = now.get ( Calendar.DATE ) + "";
		if ( date.length () == 1 )
		{
			date = "0" + date;
		}

		String year = now.get ( Calendar.YEAR ) + "";

		String hour = now.get ( Calendar.HOUR ) + "";
		if ( hour.length () == 1 )
		{
			hour = "0" + hour;
		}

		String minute = now.get ( Calendar.MINUTE ) + "";
		if ( minute.length () == 1 )
		{
			minute = "0" + minute;
		}

		String second = now.get ( Calendar.SECOND ) + "";
		if ( second.length () == 1 )
		{
			second = "0" + second;
		}

		String amPm = now.get ( Calendar.AM_PM ) == Calendar.AM ? "AM" : "PM";

		return month + "/" + date + "/" + year + " " + hour + ":" + minute + ":" + second + " " + amPm;
	}

	/**
	 * @return current date in "Wednesday, August 3, 2005" format.
	 */
	public static String getLongdate ()
	{
		Date today;
		String output;
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat ( "EEEEE, MMMMM d, yyyy" );
		today = new Date ();
		output = formatter.format ( today );
		return output;
	}

	/**
	 * get the current date in mm-dd-yyyy format
	 * 
	 * @return
	 */
	public static String getUSDate ()
	{
		GregorianCalendar now = ( GregorianCalendar ) Calendar.getInstance ();
		String year = new Integer ( now.get ( Calendar.YEAR ) ).toString ();
		String month = new Integer ( now.get ( Calendar.MONTH ) + 1 ).toString ();
		String day = new Integer ( now.get ( Calendar.DAY_OF_MONTH ) ).toString ();
		return month + "-" + day + "-" + year;
	}

	/**
	 * Reverse the date that is passed in. Assumes no time is present
	 * 
	 * @param The
	 *        date as a string. Accepts either YYYY-MM-DD or MM-DD-YYYY
	 * @return YYYY-MM-DD -> MM-DD-YYYY or MM-DD-YYYY -> YYYY-MM-DD
	 */
	public static String reverseDate ( String date )
	{
		if ( date == null || date.equals ( "" ) || date.equals ( "0000-00-00" ) )
																					return "";
		if ( date.equals ( "&nbsp;" ) ) return date;
		String parts[];
		String dateDelimeter = "-";
		if ( date.indexOf ( "/" ) > - 1 ) dateDelimeter = "/";
		parts = date.split ( dateDelimeter );

		if ( Integer.parseInt ( parts [0] ) > 1000 ) //YYYY-MM-DD -> MM-DD-YYYY
			return parts [1] + "-" + parts [2] + "-" + parts [0];
		else
			//MM-DD-YYYY -> YYYY-MM-DD
			return parts [2] + "-" + parts [0] + "-" + parts [1];
	}

	//	GregorianCalendar date;
	String	months[]	=
							{ "January" , "February" , "March" , "April" , "May" , "June" , "July" , "August" , "September" , "October" , "November" , "December" };

	public DateUtility ()
	{

	}

}