package com.gw.account.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import org.apache.commons.codec.digest.DigestUtils;

import com.gw.accservice.tools.TypeEnum.CgiVerEnum;

public class AccEncrypt 
{
	public static final byte RandWordFirstFactor = (byte)0x1F;
	
	public static String MD5Encrypt(String text)
	{
		//SQLSERVER2005:
		//sys.fn_VarBinToHexStr(hashbytes('MD5', @text)) = "0x"+MD5Encrypt(text)
		
		return DigestUtils.md5Hex(text.getBytes());
	}
	
	public static int getRandWord(byte firstFactor)
	{
		byte[] wordBytes = new byte[4];
		for (int i=0; i<4; i++)
		{
			wordBytes [i] = (byte)(Math.random () * 256 - 128);
		}
		
		wordBytes[3] = (byte) (wordBytes[3] & firstFactor);
		
		return bytesToInt(wordBytes);
	}
	
	private static int TeaKey0[] = {0x2e992010, 0x0acfd3da};
	private static int TeaKey1[] = {0x492f20e3, 0x019fe12b};
	
	public static int[] getTeaKey(double ver)
	{
		if(ver==CgiVerEnum.CgiVer3_1.getVal())
			return TeaKey1;
		else
			return TeaKey0;
	}
	
	public static byte[] TeaEncrypt(byte[] pbuf, int TeaKey[])
	{ 
		int k[] = new int[4];
		k[0] = TeaKey[0];
		k[1] = TeaKey[1];
		k[2] = TeaKey[0] + TeaKey[1];
		k[3] = TeaKey[0] - TeaKey[1];
		
		int i=0, j=0;
		
		int nLength = (int)(pbuf.length/4);
		int v[] = new int[nLength];
		
		byte dword[] = new byte[4];
		for(i=0, j=0; i<pbuf.length && j<nLength; i=i+4, j++)
		{
			dword[0] = pbuf[i];
			dword[1] = pbuf[i+1];
			dword[2] = pbuf[i+2];
			dword[3] = pbuf[i+3];
			
			v[j] = bytesToInt(dword);
		}
		
		int n = nLength - 1;
		if( n>=1 )
		{
			int z = v[n], y = v[0], delta = 0x9E3779B9 , sum = 0 , e ;
			int q = 6 + 52 / ( n + 1 ) ; 
			
			while( q -- > 0 ) 
			{ 
				sum = ( sum + delta ) ; 
				e = sum >>> 2 & 3 ; 
				for(i = 0 ; i < n ; i ++ ) 
				{ 
					y = v [ i + 1 ] ; 
					v [ i ] += (( z >>> 4 ^ y << 2 ) + ( y >>> 3 ^ z << 4 )) ^ (( sum ^ y ) + ( k [ (i & 3) ^ e ] ^ z )) ; 
					z = v[i];
				} 
				y = v [ 0 ] ; 
				v [ n ] += (( z >>> 4 ^ y << 2 ) + ( y >>> 3 ^ z << 4 )) ^ (( sum ^ y ) + ( k [ (i & 3) ^ e ] ^ z )) ; 
				z = v[n];
			}
			
			for(i=0, j=0; i<pbuf.length && j<nLength; i=i+4, j++)
			{
				dword = intToBytes(v[j]);
				
				pbuf[i] = dword[0];
				pbuf[i+1] = dword[1];
				pbuf[i+2] = dword[2];
				pbuf[i+3] = dword[3];
			}
		}
		
		if(nLength<=1 || pbuf.length%4!=0)
		{
			int nr = nLength>1 ? nLength*4 : 0;
			
			for(i=nr; i<pbuf.length; i++)
			{
				byte[] kb = intToBytes(k[i%4]);
				pbuf[i] ^= kb[0];
			}
		}
		
		return pbuf;
	}
	
	public static byte[] TeaDecrypt(byte[] pbuf, int TeaKey[])
	{ 
		int k[] = new int[4];
		k[0] = TeaKey[0];
		k[1] = TeaKey[1];
		k[2] = TeaKey[0] + TeaKey[1];
		k[3] = TeaKey[0] - TeaKey[1];
		
		int i=0, j=0;
		
		int nLength = (int)(pbuf.length/4);
		int v[] = new int[nLength];
		
		byte dword[] = new byte[4];
		for(i=0, j=0; i<pbuf.length && j<nLength; i=i+4, j++)
		{
			dword[0] = pbuf[i];
			dword[1] = pbuf[i+1];
			dword[2] = pbuf[i+2];
			dword[3] = pbuf[i+3];
			
			v[j] = bytesToInt(dword);
		}
		
		int n = nLength - 1 ; 
		if( n >= 1 ) 
		{ 
			int z = v[n], y = v[0], delta = 0x9E3779B9 , sum = 0 , e ;
			int q = 6 + 52 / ( n + 1 ) ;
			sum = q * delta ; 
			while( sum != 0 ) 
			{ 
				e = sum >>> 2 & 3 ; 
				for ( i = n ; i > 0 ; i -- ) 
				{ 
					z = v [ i - 1 ] ; 
					v [ i ] -= (( z >>> 4 ^ y << 2 ) + ( y >>> 3 ^ z << 4 )) ^ (( sum ^ y ) + ( k [ (i & 3) ^ e ] ^ z )) ; 
					y = v[i];
				} 
				z = v [ n ] ; 
				v [ 0 ] -= (( z >>> 4 ^ y << 2 ) + ( y >>> 3 ^ z << 4 )) ^ (( sum ^ y ) + ( k [ (i & 3) ^ e ] ^ z )) ;
				y = v[0];
				sum = sum - delta ; 
			}
			
			for(i=0, j=0; i<pbuf.length && j<nLength; i=i+4, j++)
			{
				dword = intToBytes(v[j]);
				
				pbuf[i] = dword[0];
				pbuf[i+1] = dword[1];
				pbuf[i+2] = dword[2];
				pbuf[i+3] = dword[3];
			}
		}
		
		if(nLength<=1 || pbuf.length%4!=0)
		{
			int nr = nLength>1 ? nLength*4 : 0;
			
			for(i=nr; i<pbuf.length; i++)
			{
				byte[] kb = intToBytes(k[i%4]);
				pbuf[i] ^= kb[0];
			}
		}
		
		return pbuf;
	}
	
	public static String genLv2Seed ()
    {
		byte[] seed = new byte[16];
		for (int i=0; i< seed.length; i++)
		{
			seed [i] = (byte)(Math.random () * 256 - 128);
		}
		return new BASE64Encoder ().encode (seed);
    }
	
	public static String Lv2Encrypt(String seed, String password)
	{
		try
		{
			BASE64Encoder encoder = new BASE64Encoder ();
			BASE64Decoder decoder = new BASE64Decoder ();

			byte[] seedBytes = decoder.decodeBuffer (seed);
			byte[] pwdBytes = password.getBytes ("UTF-16LE");
			
			//System.out.println("seedBytes:");
			//System.out.println(getBytesHex(seedBytes));
			
			//System.out.println("pwdBytes:");
			//System.out.println(getBytesHex(pwdBytes));

			byte[] source = new byte [seedBytes.length + pwdBytes.length];
			System.arraycopy (seedBytes, 0, source, 0, seedBytes.length);
			System.arraycopy (pwdBytes, 0, source, seedBytes.length, pwdBytes.length);
			
			//System.out.println("source:");
			//System.out.println(getBytesHex(source));

			java.security.MessageDigest alga = java.security.MessageDigest.getInstance ("SHA1");
			alga.update (source);
			byte[] target = alga.digest ();

			return encoder.encode (target);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static String Base64Encode(byte[] strBytes)
	{
		BASE64Encoder encoder = new BASE64Encoder();
		String base64str = encoder.encode(strBytes);
		base64str = base64str.replace("\r\n", "");
		base64str = base64str.replace("\r", "");
		base64str = base64str.replace("\n", "");
		
		return base64str;
	}
	
	public static byte[] Base64Decode(String base64str)
	{
		try
		{
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] strBytes = decoder.decodeBuffer(base64str);
			
			return strBytes;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static byte[] intToBytes(int i) 
	{
		byte[] bt = new byte[4];
		bt[0] = (byte) (0xff & i);
		bt[1] = (byte) ((0xff00 & i) >> 8);
		bt[2] = (byte) ((0xff0000 & i) >> 16);
		bt[3] = (byte) ((0xff000000 & i) >> 24);
		
		return bt;
	}

	public  static int bytesToInt(byte[] bytes) 
	{
		int num = bytes[0] & 0xFF;
		num |= ((bytes[1] << 8) & 0xFF00);
		num |= ((bytes[2] << 16) & 0xFF0000);
		num |= ((bytes[3] << 24) & 0xFF000000);
		
		return num;
	}
	
	public static byte[] hexToBytes( String hexstr )
	{
		if (hexstr == null)
		{
			return null;
		}
		int l = hexstr.length();
		if (l % 2 == 1)
		{
			return null;
		}
		byte[] b = new byte[l / 2];
		for (int i = 0; i != l / 2; i++)
		{
			b[i] = (byte) Integer.parseInt(hexstr.substring(i * 2, i * 2 + 2), 16);
		}
		
		return b;
	}
	
	public static String bytesToHex(byte buf[]) 
	{
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;
		
		for (i = 0; i < buf.length; i++) 
		{
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");
			
			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}
		
		return strbuf.toString();
	}
	
	public static String getBytesHex( byte[] b )
	{
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++)
		{
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
			{
				hs = hs + "0" + stmp;
			}
			else
			{
				hs = hs + stmp;
			}
			
			hs += " ";
		}
		
		return hs.toUpperCase();
	}
	
}
