package com.gw.account.httptest;

import com.google.protobuf.InvalidProtocolBufferException;
import core.Userinfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.xml.sax.SAXException;
import redis.clients.jedis.BinaryJedis;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hihiri on 2015/3/10.
 */
public class MyCheckUtil {
    private static final Log LOG = LogFactory.getLog(MyCheckUtil.class);
    private static BinaryJedis database1 = new BinaryJedis("10.15.201.107", 22121);  //twemproxy1
    private static BinaryJedis database2 = new BinaryJedis("10.15.201.108", 22121);  //twemproxy2
    private static BinaryJedis database3 = new BinaryJedis("10.15.108.5", 10001);    //BDB
    private static List<BinaryJedis> checklist = new ArrayList<BinaryJedis>();

    public static void initialize() {
        PropertyConfigurator.configure("config/log4j.properties");
        checklist.clear();
        checklist.add(database3);
        KeyIdCast.initialize();
    }

    //=================================验证接口=======================================

    /**
     * 绑定key并验证返回uname和key，返回错误则打印uname、key和返回信息
     * @param uname
     * @param keytp
     * @param key
     * @return
     * @throws IOException
     * @throws SAXException
     */
    public static boolean checkUserbind(String uname, String keytp, String key) throws IOException, SAXException {
        String keyencode = URLEncoder.encode(keytp + "=" + key, "UTF-8");
        String response = AccInterface.testUserbind("&uname=" + uname + "&key=" + keyencode);
        boolean result = checkResponse(response, uname, keytp, key);
        if (!result) {
            LOG.error("checkUserbind:(" + uname + "," + keytp + "," + key + ")," + response);
        }
        return result;
    }

    public static boolean checkGetUserbind(String uname, String keytp, String key) throws IOException, SAXException {
        String response = AccInterface.testGetUserbind("&uname=" + uname + "&keytp=" + keytp);
        boolean result = checkResponse(response, uname, keytp, key);
        if (!result) {
            LOG.error("checkGetUserbind:(" + uname + "," + keytp + "," + key + ")," + response);
        }
        return result;
    }

    public static boolean checkFindUnamebyKey(String uname, String keytp, String key) throws IOException, SAXException {
        String response = AccInterface.testFindUnamebyKey("&keytp=" + keytp + "&key=" + key);
        String getuname = getValueFromResponse(response, "uname");
        boolean result = response.contains("result=0") && getuname.equals(uname.toLowerCase());
        if (!result) {
            LOG.error("checkFindUnamebyKey:(" + uname + "," + keytp + "," + key + ")," + response);
        }
        return result;
    }


    //=================================验证数据库=======================================
    public static boolean checkExistSolo(BinaryJedis databasesolo, String uname, String password, String keytp, String key) throws InvalidProtocolBufferException, NoSuchAlgorithmException {
        boolean resultsolo = true;
        byte[] u = databasesolo.get(("u:" + uname.toLowerCase()).getBytes());
        if (u == null) {
            LOG.error("checkExist" + databasesolo.toString() + ": (u:" + uname.toLowerCase() + ") No data!");
            resultsolo = false;
        } else {
            core.Userinfo.UserInfo userInfosolo = Userinfo.UserInfo.parseFrom(u);
            if (userInfosolo.getName().equals(uname.toLowerCase()) && userInfosolo.getPassword().equals(encodePassword(password))) {
                resultsolo = true;
            } else {
                LOG.error("checkExist" + databasesolo.toString() + ":(" + uname + "," + password + "), infoSaved("
                        + userInfosolo.getName() + "," + userInfosolo.getPassword() + ")");
                resultsolo = false;
            }
        }
        byte[] ukey = databasesolo.get(("ukey:" + uname.toLowerCase()).getBytes());
        if (ukey == null) {
            LOG.warn("checkExist" + databasesolo.toString() + ": (ukey:" + uname.toLowerCase() + ") No data!");
            resultsolo = false;
        } else {
            Userinfo.UserKeys userKeyssolo = Userinfo.UserKeys.parseFrom(ukey);
            long getkeysolo;
            String getvaluesolo = null;
            for (Userinfo.KeyValue keyValue : userKeyssolo.getKeysList()) {
                getkeysolo = keyValue.getKey();
                if (KeyIdCast.getKeyIdbyKey(keytp) == getkeysolo) {
                    getvaluesolo = keyValue.getValue();
                }
            }
            if (getvaluesolo == null || !getvaluesolo.equals(key)) {
                LOG.error("checkExist" + databasesolo.toString() + ":(" + uname + "," + keytp + "," + key + "), keySaved("
                        + getvaluesolo + ")");
                resultsolo = false;
            }
        }
        return resultsolo;
    }

    public static boolean checkExist(String uname, String password, String keytp, String key) throws InvalidProtocolBufferException, NoSuchAlgorithmException {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkExistSolo(databasesolo, uname, password, keytp, key);
            result = result && resultsolo;
        }
        return result;
    }

    public static boolean checkNotExist(String uname, String password, String keytp, String key) throws InvalidProtocolBufferException, NoSuchAlgorithmException {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkExistSolo(databasesolo, uname, password, keytp, key);
            result = result && !resultsolo;
        }
        return result;
    }

    public static boolean checkIndexSolo(BinaryJedis databasesolo, String uname, String keytp, String key) {
        int keyid = KeyIdCast.getKeyIdbyKey(keytp);
        boolean resultsolo = true;
        byte[] unamesolo = databasesolo.get(("k_" + keyid + ":" + key).getBytes());
        if (unamesolo == null) {
            LOG.error("checkIndex" + databasesolo.toString() + ": (" + "k_" + keyid + ":" + key + ") No data!");
            resultsolo = false;
        } else {
            String unamesolostring = new String(unamesolo);
            if (!unamesolostring.equals(uname.toLowerCase())) {
                LOG.error("checkIndex" + databasesolo.toString() + ":(" + uname + "), unameSaved:(" + unamesolostring + ")");
                resultsolo = false;
            }
        }
        return resultsolo;
    }

    public static boolean checkIndex(String uname, String keytp, String key) {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkIndexSolo(databasesolo, uname, keytp, key);
            result = result && resultsolo;
        }
        return result;
    }

    public static boolean checkNotIndex(String uname, String keytp, String key) {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkIndexSolo(databasesolo, uname, keytp, key);
            result = result && !resultsolo;
        }
        return result;
    }

    /**
     * 根据传入的uname得到库里的uid，再与传入的uid比较是否一致，通过库里的uid得到对应的uname，与传入的uname比较是否一致
     * @param databasesolo
     * @param uname
     * @param uid
     * @return
     * @throws InvalidProtocolBufferException
     */
    public static boolean checkUidSolo(BinaryJedis databasesolo, String uname, String uid) throws InvalidProtocolBufferException {
        boolean resultsolo = true;
        byte[] u = databasesolo.get(("u:" + uname.toLowerCase()).getBytes());
        if (u == null) {
            LOG.error("checkUid" + databasesolo.toString() + ": (u:" + uname.toLowerCase() + ") No data!");
            resultsolo = false;
        } else {
            core.Userinfo.UserInfo userInfosolo = Userinfo.UserInfo.parseFrom(u);
            long idsolo = userInfosolo.getId();
            if (uid != "") {
                long longuid = Long.parseLong(uid);
                if (idsolo != longuid) {
                    LOG.error("checkUid" + databasesolo.toString() + ": (" + uid + "), uidSaved:(" + idsolo + ")");
                    resultsolo = false;
                }
            }
            byte[] unamesolo = databasesolo.get(("uid:" + idsolo).getBytes());
            if (unamesolo == null) {
                LOG.error("checkUid" + databasesolo.toString() + ": (uid:" + idsolo + ") No data!");
                resultsolo = false;
            }
            String unamesolostring = new String(unamesolo);
            if (!unamesolostring.equals(uname.toLowerCase())) {
                LOG.error("checkUid" + databasesolo.toString() + ": (" + uname + "), unameSaved:(" + unamesolostring + ")");
                resultsolo = false;
            }
        }
        return resultsolo;
    }

    public static boolean checkUid(String uname, String uid) throws InvalidProtocolBufferException {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkUidSolo(databasesolo, uname, uid);
            result = result && resultsolo;
        }
        return result;
    }

    public static boolean checkNotUid(String uname, String uid) throws InvalidProtocolBufferException {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkUidSolo(databasesolo, uname, uid);
            result = result && !resultsolo;
        }
        return result;
    }

    //=================================工具方法=======================================
    public static String getValueFromResponse(String response, String keytp) {
        int startindex = response.indexOf(keytp) + keytp.length() + 1;
        int endindex = (response.indexOf("&", startindex) != -1) ? response.indexOf("&", startindex) : response.length();
        String value = response.substring(startindex, endindex);
        return value;
    }

    public static boolean checkResponseSolo(String response, String keytp, String key) {
        String getkey = getValueFromResponse(response, keytp);
        boolean result = getkey.equals(key);
        return result;
    }

    public static boolean checkResponse(String response, String uname, String keytp, String key) {
        boolean result = response.contains("result=0") && checkResponseSolo(response,"uname",uname) && checkResponseSolo(response,keytp,key);
        return result;
    }

    public static String encodePassword(String password) throws NoSuchAlgorithmException {
        String plaintext = password;
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(plaintext.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        String hashtext = bigInt.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }
}
