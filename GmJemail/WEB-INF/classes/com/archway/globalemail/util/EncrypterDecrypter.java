package com.archway.globalemail.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import com.archway.globalemail.common.ConfigurationServlet;
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
 * File Name               : EncrypterDecrypter.java 
 * Package Name            : com.archway.globalemail.util
 * 
 * Date                    : Oct 27, 2005 - 3:07:28 PM
 * 
 * Change Date             : May 18, 2006 4:13:39 PM
 *                           May 30, 2006 12:35:40 PM
 * 
 * ---------------------------------------------------------
 * Description :- Add Class Description here
 * ---------------------------------------------------------
 */
public class EncrypterDecrypter
{

	public static String	algorithm			= "PBEWithMD5AndDES";

	public static int		count				= 23;

	public static char []	encryptionPassword	= ConfigurationServlet.getApplicationProperty ( "PASS_PHRASE" ).toCharArray ();

	public static byte []	salt				=
													{ ( byte ) 0x67 , ( byte ) 0x7b , ( byte ) 0xa1 , ( byte ) 0x85 , ( byte ) 0x6b , ( byte ) 0xd1 , ( byte ) 0xfe , ( byte ) 0x91 };

	/**
	 * Decrypts a given string depending on the parsephrase specifies in
	 * properties file.
	 * 
	 * @param toDecrypt
	 * @return
	 */
	public static String Decrypt ( String toDecrypt ) throws Exception
	{
		EncrypterDecrypter ed = new EncrypterDecrypter ();
		return ed.decryptWebPassword ( toDecrypt );
	}

	/**
	 * Encrypts a given string depending on the parsephrase specifies in
	 * properties file.
	 * 
	 * @param toEncrypt
	 * @return
	 */
	public static String Encrypt ( String toEncrypt ) throws Exception
	{
		EncrypterDecrypter ed = new EncrypterDecrypter ();
		return ed.encryptWebPassword ( toEncrypt.getBytes () );
	}

	private PBEKeySpec	encPass;

	Logger				logger	= Logger.getLogger ( "encryptDecrypt" );

	/** Creates a new instance of EncryptionMachine */
	public EncrypterDecrypter ()
	{
		Provider sunJCE = new com.sun.crypto.provider.SunJCE ();
		Security.addProvider ( sunJCE );

		// set the encryption password. Must have this class in order to decrypt
		// the password. see java policy setup
		this.encPass = new PBEKeySpec ( encryptionPassword );
	}

	/**
	 * Convenience method to convert a byte array to a hex string.
	 * 
	 * @param data
	 *        the byte[] to convert
	 * @return String the converted byte[]
	 */
	private String bytesToHex ( byte [] data )
	{
		StringBuffer buf = new StringBuffer ();
		for ( int i = 0 ; i < data.length ; i++ )
		{
			buf.append ( byteToHex ( data [i] ) );
		}
		return ( buf.toString () );
	}

	/**
	 * Convenience method to convert a byte to a hex string.
	 * 
	 * @param data
	 *        the byte to convert
	 * @return String the converted byte
	 */
	private String byteToHex ( byte data )
	{
		StringBuffer buf = new StringBuffer ();
		buf.append ( toHexChar ( ( data >>> 4 ) & 0x0F ) );
		buf.append ( toHexChar ( data & 0x0F ) );
		return buf.toString ();
	}

	public byte [] decryptPassword ( byte [] input )
	{
		Cipher pbeCipher = getCipher ( Cipher.DECRYPT_MODE );
		try
		{
			return pbeCipher.doFinal ( input );
		}
		catch ( IllegalBlockSizeException illegalBlockSz )
		{
			logger.debug ( "Illegal Block Size" );
		}
		catch ( BadPaddingException badPad )
		{
			logger.debug ( "BadPaddingException thrown" );
		}
		return null;
	}

	public String decryptWebPassword ( String input ) throws IOException
	{
		//return the hex string to it's byte form
		byte [] b = hexToByteArray ( input );
		byte [] output = decryptPassword ( b );
		ByteArrayOutputStream bos = new ByteArrayOutputStream ();
		try
		{
			bos.write ( output );
		}
		catch ( IOException ioEx )
		{
			throw new IOException ( "Unable to write bytes to output stream." );
		}

		return bos.toString ();
	}

	public byte [] encryptPassword ( byte [] input )
	{
		Cipher pbeCipher = getCipher ( Cipher.ENCRYPT_MODE );
		try
		{
			return pbeCipher.doFinal ( input );
		}
		catch ( IllegalBlockSizeException illegalBlockSz )
		{
			logger.debug ( "Illegal Block Size" );
		}
		catch ( BadPaddingException badPad )
		{
			logger.debug ( "BadPaddingException thrown" );
		}
		return null;
	}

	/**
	 * Instead of simply returning an encrypted string of text this method will
	 * return a hex variation of the encrypted string.
	 * 
	 * @param input
	 *        A byte array containing the password to be
	 */
	public String encryptWebPassword ( byte [] input )
	{
		byte [] encodedPassword = encryptPassword ( input );
		String output = bytesToHex ( encodedPassword );
		/*
		 * StringBuffer output = new StringBuffer(); for(int i = 0; i <
		 * encodedPassword.length; i++) { byte b = encodedPassword[i];
		 * output.append(byteToHex(b)).append("|"); }
		 */
		return output;
	}

	private Cipher getCipher ( int mode )
	{
		try
		{
			PBEParameterSpec pbeParamSpec;
			SecretKeyFactory keyFac;

			// Create PBE parameter set
			pbeParamSpec = new PBEParameterSpec ( salt , count );
			//set up algorithm

			keyFac = SecretKeyFactory.getInstance ( algorithm , "SunJCE" );
			SecretKey pbeKey = keyFac.generateSecret ( this.encPass );
			//create Cipher
			Cipher pbeCipher = Cipher.getInstance ( algorithm );
			pbeCipher.init ( mode , pbeKey , pbeParamSpec );
			return pbeCipher;
		}
		catch ( NoSuchAlgorithmException nsAlgEx )
		{
			logger.debug ( "NoSuchAlgorithmException thrown: " + nsAlgEx.toString () );
		}
		catch ( NoSuchProviderException nsProvEx )
		{
			logger.debug ( "NoSuchProviderException thrown: " + nsProvEx.toString () );
		}
		catch ( InvalidKeySpecException invalidKeySpec )
		{
			logger.debug ( "InvalidKeySpecException thrown: " + invalidKeySpec.toString () );
		}
		catch ( InvalidKeyException invalidKey )
		{
			logger.debug ( "InvalidKeyException thrown: " + invalidKey.toString () );
		}
		catch ( InvalidAlgorithmParameterException iape )
		{
			logger.debug ( "InvalidAlgorithmParameterException thrown: " + iape.toString () );
		}
		catch ( NoSuchPaddingException nsPad )
		{
			logger.debug ( "NoSuchPaddingException thrown: " + nsPad.toString () );
		}
		return null;
	}

	public byte [] hexToByteArray ( String string )
													throws NumberFormatException
	{
		byte [] bytes = new byte[string.length () / 2];
		for ( int i = 0 ; i < bytes.length ; i++ )
		{
			bytes [i] = ( byte ) Integer.parseInt ( string.substring ( i * 2 , ( i * 2 ) + 2 ) , 16 );
		}
		return bytes;
	}

	/**
	 * Convenience method to convert an int to a hex char.
	 * 
	 * @param i
	 *        the int to convert
	 * @return char the converted char
	 */
	private char toHexChar ( int i )
	{
		if ( ( 0 <= i ) && ( i <= 9 ) )
			return ( char ) ( '0' + i );
		else
			return ( char ) ( 'a' + ( i - 10 ) );
	}

}

