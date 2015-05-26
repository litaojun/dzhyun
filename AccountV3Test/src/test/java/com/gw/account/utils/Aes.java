package com.gw.account.utils;

import com.google.common.base.Throwables;
import com.google.common.io.BaseEncoding;
import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Aes {
    private static final Logger logger = Logger.getLogger(Aes.class);

    private static String paddingString(String str) {
        if (str.length() == 16) {
            return str;
        } else if (str.length() > 16) {
            return str.substring(0, 16);
        } else {
            return (str + "1234567812345678").substring(0, 16);
        }
    }

    public static String encrypt(String data, String key, String iv) {
        return crypt(Cipher.ENCRYPT_MODE, "AES/CBC/PKCS5Padding", data, key, iv);
    }

    public static String decrypt(String data, String key, String iv) {
        return crypt(Cipher.DECRYPT_MODE, "AES/CBC/PKCS5Padding", data, key, iv);
    }

    public static String crypt(int mode, String cipherType, String data, String key, String iv) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(paddingString(key).getBytes("ISO-8859-1"), "AES");
            Cipher cipher = Cipher.getInstance(cipherType);
            cipher.init(mode, skeySpec, new IvParameterSpec(paddingString(iv).getBytes("ISO-8859-1")));
            if (mode == Cipher.ENCRYPT_MODE) {
                return BaseEncoding.base64().encode(cipher.doFinal(data.getBytes("utf-8")));
            } else {
                return new String(cipher.doFinal(BaseEncoding.base64().decode(data)), "utf-8");
            }
        } catch (Throwable t) {
            logger.error("crypt error", t);
            Throwables.propagate(t);
            return null;
        }
    }
    
    public static String encrypt(String data, String key) {
        return crypt(Cipher.ENCRYPT_MODE, "AES/ECB/PKCS5Padding", data, key);
    }

    public static String decrypt(String data, String key) {
        return crypt(Cipher.DECRYPT_MODE, "AES/ECB/PKCS5Padding", data, key);
    }

    public static String crypt(int mode, String cipherType, String data, String key) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(paddingString(key).getBytes("ISO-8859-1"), "AES");
            Cipher cipher = Cipher.getInstance(cipherType);
            cipher.init(mode, skeySpec);
            if (mode == Cipher.ENCRYPT_MODE) {
                return BaseEncoding.base64().encode(cipher.doFinal(data.getBytes("utf-8")));
            } else {
//                System.out.println("xxxxxxxxxxxxxxxxxxx");
//                System.out.println(data);
                byte[] buff = BaseEncoding.base64().decode(data);
//                System.out.println(BaseEncoding.base16().encode(buff));
//                System.out.println("yyyyyyyyyyyyyyyy");
                return new String(cipher.doFinal(buff), "utf-8");
            }
        } catch (Throwable t) {
            logger.error("crypt error", t);
            Throwables.propagate(t);
            return null;
        }
    }
    
//    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
//
//    	/*String s = "201405190907379178n2";
//    	String encode = Aes.encrypt(s, "hello");
//    	System.out.println(encode);
//
//    	encode="mWknmcFtHX2Fulh5a1dalSsKFC96PPqMw6nkNKo7+gE=";
//    	String decode = Aes.decrypt(encode, "hello");
//    	System.out.println(decode);
//
//    	s = "201405191030033578n2";
//    	encode = Aes.encrypt(s, "hello", "201405191030033578");
//    	System.out.println(encode);
//
//
//    	encode="mWknmcFtHX2Fulh5a1dalSsKFC96PPqMw6nkNKo7+gE=";
//    	decode = Aes.decrypt(encode, "hello", "582452");
//    	System.out.println(decode);*/
//
//    	/*System.out.println(Aes.decrypt("+h2Xy8DfrP8FpNXdwSskHA2+G2b3kXl/qlMYOcEuQOj+rK8JDLQ/7JfAou0qWwrj", "As&MobileClient**"));
//
//    	//System.out.println(Aes.encrypt("{\"code\":\"0\", \"msg\":\"ok\",\"data\":\"ok\"}", "As&MobileClient**"));
//    	System.out.println(AccEncrypt.MD5Encrypt("As&MobileClient**"));
//    	System.out.println(Aes.encrypt(AccEncrypt.MD5Encrypt("12345"), "C46E8DCFFE82A8D6"));*/
//
//    	//As&MobileClient**
//    	//String key = "C46E8DCFFE82A8D6";
//    	//byte[] bytes = md.digest(key.getBytes());
//    	String content = "abcdefg";
//    	String key = "As&MobileClient*";
//    	SecretKeySpec sks = new SecretKeySpec(key.getBytes(), "AES");
//    	Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//    	cipher.init(Cipher.ENCRYPT_MODE, sks);
//    	String md5Str = AccEncrypt.MD5Encrypt(content).toUpperCase();
//    	System.out.println(BaseEncoding.base64().encode(cipher.doFinal(md5Str.getBytes())));
//    	//System.out.println(BaseEncoding.base64().encode(cipher.doFinal(md.digest("abcdefg".getBytes("UTF-8")))));
//
//    	/*SecretKeySpec sks = new SecretKeySpec("As&MobileClient*".getBytes(), "AES");
//    	Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//    	cipher.init(Cipher.DECRYPT_MODE, sks);
//    	System.out.println(new String(cipher.doFinal(BaseEncoding.base64().decode("ZzeKffSpnTbCOoMZfsxveWofhbqniR+/IZsk090Y0C1xqTVdCbQSraHfk3hhkQgP")), "utf-8"));*/
//    	System.out.println(Aes.encrypt("2141463156131721", "hello"));
//    }
}
