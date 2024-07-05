package com.archway.globalemail.util;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

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
 * File Name               : StringUtility.java 
 * Package Name            : com.archway.globalemail.util
 * 
 * Date                    : Oct 27, 2005 - 3:07:51 PM
 * 
 * Change Date             : May 15, 2006 5:08:28 PM
 *                           May 18, 2006 4:14:05 PM
 * 							 May 19, 2006 9:15:03 AM
 * 							 May 24, 2006 12:12:23 PM
 *                           May 24, 2006 1:00:00 PM
 *                           May 30, 2006 12:37:03 PM
 * 							 Jun 2, 2006 3:01:20 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */
public class StringUtility
{

	/**
	 * Formats a float value to look like $x.xx
	 * 
	 * @param amount
	 * @return
	 */
	public static String displayDollars ( float amount )
	{
		Currency dollars = Currency.getInstance ( "USD" );
		NumberFormat usFormat = NumberFormat.getCurrencyInstance ( Locale.US );
		return usFormat.format ( amount );
	}

	/**
	 * Formats a string representation of a float value to look like $x.xx
	 * 
	 * @param amount
	 * @return
	 */
	public static String displayDollars ( String amount )
	{
		if ( amount == null || amount.equals ( "" ) ) amount = "0.00";
		return displayDollars ( new Float ( amount ).floatValue () );
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static String displayEmailBodyMessage ( String input )
	{
		//StringUtils.isBlank()
		if ( StringUtils.isBlank ( input ) )
		{
			return "Email Body Not Available";
		}
		else
		{
			return "Email Body Available";
		}
	}

	/**
	 * Returns a string with the formatted float value such as xxx.xx if the
	 * value is only xxx.x Scale is the number of decimal places to include.
	 * 
	 * @param amount
	 * @param scale
	 * @return
	 */
	public static String displayFloat ( float amount , int scale )
	{
		/*
		 * below would require the percent to be stored as .XX instead of XXX.XX%
		 * NumberFormat numFormat = NumberFormat.getPercentInstance();
		 * numFormat.setMaximumFractionDigits(scale);
		 * numFormat.setMinimumFractionDigits(scale); return
		 * numFormat.format(amount);
		 */
		NumberFormat numFormat = NumberFormat.getNumberInstance ();
		numFormat.setMaximumFractionDigits ( scale );
		numFormat.setMinimumFractionDigits ( scale );
		return numFormat.format ( amount );
	}

	/**
	 * returns "N/A" for a blank string
	 * 
	 * @param input
	 * @return
	 */
	public static String displayNAForBlank ( String input )
	{
		if ( StringUtils.isBlank ( input ) )
		{
			return "N/A";
		}
		else
		{
			return input;
		}
	}

	/**
	 * returns "No Subject" for a blank string
	 * 
	 * @param input
	 * @return
	 */
	public static String displayNoSubjectForBlank ( String input )
	{
		if ( StringUtils.isBlank ( input ) )
		{
			return "No Subject";
		}
		else
		{
			return input;
		}
	}

	/**
	 * returns "Not Available" for a blank string
	 * 
	 * @param input
	 * @return
	 */
	public static String displayNotAvailableForBlank ( String input )
	{
		//StringUtils.isBlank()
		if ( StringUtils.isBlank ( input ) )
		{
			return "Not Available";
		}
		else
		{
			return input;
		}
	}

	/**
	 * Replaces illegal characters with legal ones. Used to filter database
	 * content for display in HTML.
	 * 
	 * @param string
	 * @return
	 */
	public static String escape ( String string )
	{

		string = StringUtils.replace ( string , "&" , "&#38;" );
		string = StringUtils.replace ( string , "<" , "&lt;" );
		string = StringUtils.replace ( string , ">" , "&gt;" );
		string = StringUtils.replace ( string , "!" , "&#33;" );
		string = StringUtils.replace ( string , "{" , "&#123;" );
		string = StringUtils.replace ( string , "}" , "&#125;" );
		/*
		 * to remove these 3 lines? string = StringUtils.replace(string, "\\n",
		 * "\\\\n"); string = StringUtils.replace(string, "\\\'", "\\\'\\\'");
		 * string = StringUtils.replace(string, "'", "\\'");
		 */
		string = StringUtils.replace ( string , "'" , "&#39;" );
		string = StringUtils.replace ( string , "\"" , "&quot;" );

		return string;
	}

	/**
	 * formats a phone number
	 * 
	 * @param phone
	 * @return
	 */
	public static String FormatPhone ( String phone )
	{
		/*boolean flag = false;
		 String aString1 = phone;
		 StringBuffer newString1 = new StringBuffer ();
		 String returnString = "";
		 if ( ! isStringBlank ( phone ) )
		 {

		 newString1.append ( "(" );
		 for ( int i = 0 ; i < aString1.length () ; i++ )
		 {
		 if ( Character.isDigit ( aString1.charAt ( i ) ) )
		 newString1.append ( aString1.charAt ( i ) );
		 if ( i == 2 ) newString1.append ( ")-" );
		 if ( i == 5 ) newString1.append ( "-" );

		 }
		 //cat.warn("from UtilFormat  "+newString1.toString());
		 //return newString1.toString();
		 flag = true;
		 }
		 else
		 {
		 flag = false;
		 }

		 if ( flag == true )
		 {
		 returnString = ( newString1.toString ().length () >= 3 ) ? newString1.toString () : "";
		 }
		 else
		 {
		 returnString = "";
		 }*/

		return phone;

	}

	/**
	 * returns array of unique values.
	 * 
	 * @param arr[]
	 * @return
	 */
	public static String [] getUniqueValues ( String arr[] )
	{
		String returnArray[] = null;
		SortedSet set = new TreeSet ();
		for ( int i = 0 ; i < arr.length ; i++ )
		{
			set.add ( arr [i].toUpperCase ().trim() );
		}
		returnArray = ( String [] ) set.toArray ( new String[set.size ()] );
		return returnArray;

	}

	/**
	 * determins if the email address is valid or not.
	 * 
	 * @param emailAddress
	 * @return
	 */
	public static boolean isEmailAddressValid ( String emailAddress )
	{
		final String QUOTEDSTRING = "\"(?:[^\"\\\\]|\\\\\\p{ASCII})*\"";
		final String ATOM = "[^()<>@,;:\\\\\".\\[\\] \\x28\\p{Cntrl}]+";
		final String WORD = "(?:" + ATOM + "|" + QUOTEDSTRING + ")";
		final String SUBDOMAIN = "(?:" + ATOM + "|\\[(?:[^\\[\\]\\\\]|\\\\\\p{ASCII})*\\])";
		final String DOMAIN = SUBDOMAIN + "(?:\\." + SUBDOMAIN + ")*";
		final String LOCALPART = WORD + "(?:\\." + WORD + ")*";
		final String EMAIL = LOCALPART + "@" + DOMAIN;
		Pattern p = Pattern.compile ( EMAIL );
		Matcher m = p.matcher ( emailAddress.trim () );
		return ( m.matches () );
	}

	/**
	 * Test if the string is blank (null or empty string or equals to "null")
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isStringBlank ( String string )
	{
		return string == null || string.trim ().length () == 0 || string.trim ().equalsIgnoreCase ( "null" );
	}

	/**
	 * This method process parameters to return the correct value
	 * 
	 * @param input
	 * @return
	 */
	public static String processNullToEmpty ( String input )
	{
		if ( input == null || input.equalsIgnoreCase ( "null" ) )
			return "";
		else
			return input;
	}

	/**
	 * Escape any bad characters that are going into a sql string...
	 * 
	 * @param string
	 * @return
	 */
	public static String quote ( String string )
	{
		if ( string == null ) return string;
		//string = StringUtils.replace(string, "$", "\\$");
		string = StringUtils.replace ( string , "\\" , "\\\\" );
		string = StringUtils.replace ( string , "\\\'" , "\\\'\\\'" );
		string = StringUtils.replace ( string , "'" , "\\'" );
		return string;
	}

	/**
	 * Default Constructor.
	 *
	 */
	public StringUtility ()
	{

	}

	/*public static void test ()
	 {
	 try
	 {

	 Format formatter;
	 formatter = new SimpleDateFormat ( "MM/dd/yyyy" );
	 File f = new File ( "c:/usage_200602.htm" );
	 System.out.println ( f.lastModified () );
	 Date x = new Date ( f.lastModified () );
	 System.out.println ( x );
	 String s = formatter.format ( x );
	 System.out.println ( s );
	 
	 Date currentDate = new Date();
	 

	 }
	 catch ( Exception e )
	 {
	 System.out.println ( e.getMessage () );
	 }

	 }*/

}