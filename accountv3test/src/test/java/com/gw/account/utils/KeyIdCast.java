package com.gw.account.utils;

import java.util.HashMap;

/**
 * Created by song on 2015/3/11.
 */
public class KeyIdCast {
    private static HashMap<Integer, String> keyidtokey = new HashMap<Integer, String>();
    private static HashMap<String, Integer> keytokeyid = new HashMap<String, Integer>();

    public static void initialize() {
        keyidtokey.clear();
        keytokeyid.clear();
        putCast(0, "v3name");
        putCast(1, "email");
        putCast(2, "mobile");
        putCast(3, "lotterid");
        putCast(4, "deviceid");
        putCast(5, "pushid");
        putCast(8, "truename");
        putCast(9, "nickname");
        putCast(10, "idcard");
        putCast(13, "qqid");
        putCast(15, "lcb");
        putCast(22, "wxid");
        putCast(27, "xcid");
    }

    public static String getKeybyKeyId(int keyid) {
        return keyidtokey.get(keyid);
    }

    public static int getKeyIdbyKey(String key) {
        return keytokeyid.get(key);
    }

    private static void putCast(int keyid, String key) {
        keyidtokey.put(keyid, key);
        keytokeyid.put(key, keyid);
    }
}
