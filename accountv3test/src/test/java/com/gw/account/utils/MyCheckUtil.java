package com.gw.account.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.account.core.Bankinfo;
import com.gw.account.core.Userinfo;
import com.gw.account.httptest.AccInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.xml.sax.SAXException;
import redis.clients.jedis.BinaryJedis;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Hihiri on 2015/3/10.
 */
public class MyCheckUtil {
    private static final Log LOG = LogFactory.getLog(MyCheckUtil.class);
    private static BinaryJedis database1 = new BinaryJedis("10.15.201.107", 22121);  //twemproxy1
    private static BinaryJedis database2 = new BinaryJedis("10.15.201.108", 22121);  //twemproxy2
    private static BinaryJedis database3 = new BinaryJedis("10.15.108.5", 10001);    //BDB
    private static List<BinaryJedis> checklist = new ArrayList<BinaryJedis>();

    private static long uMarket_96 = 620708805;
    private static String extrabuf_96 = "{\"J365\":\"1\",\"QS\":0,\"YYB\":0,\"SC\":\"616514533\",\"GN\":\"65568\",\"UT\":0,\"NI\":0,\"CP\":\"2147483648\",\"CC\":0,\"PZ1\":\"0\",\"PZ2\":\"1647763\",\"PZ3\":\"139608596\",\"PZ4\":\"0\",\"PZ5\":\"1758484278\",\"PZ6\":\"16\",\"PZ7\":\"44\",\"PN\":\"\",\"HT\":\"\",\"DS\":{\"10001\":\"1437719939\"}}";
    private static String AES_KEY = "hell0";

    public static void initialize() {
        PropertyConfigurator.configure("config/log4j.properties");
        checklist.clear();
        checklist.add(database3);
        KeyIdCast.initialize();
    }

    //=================================验证接口=======================================
    /**
     * 验证findUnamebyKey的返回
     *
     * @param uname
     * @param keytp
     * @param key
     * @return
     * @throws IOException
     * @throws SAXException
     */
    public static boolean checkFindUnamebyKey(String uname, String keytp, String key) throws IOException, SAXException {
        String response = AccInterface.testFindUnamebyKey("&keytp=" + keytp + "&key=" + key);
        boolean result = response.contains("result=0")
                && getValueFromResponse(response, "uname").equals(uname.toLowerCase());
        if (!result) {
            LOG.error("checkFindUnamebyKey:(" + uname + "," + keytp + "," + key + ")," + response);
        }
        return result;
    }

    //=================================验证数据库=======================================
    public static boolean checkUSolo(BinaryJedis databasesolo, String uname, String password) throws InvalidProtocolBufferException, NoSuchAlgorithmException {
        boolean resultsolo = true;
        byte[] u = databasesolo.get(("u:" + uname.toLowerCase()).getBytes());
        if (u == null) {
            LOG.error("checkExist" + databasesolo.toString() + ": (u:" + uname.toLowerCase() + ") No data!");
            resultsolo = false;
        } else {
            com.gw.account.core.Userinfo.UserInfo userInfosolo = Userinfo.UserInfo.parseFrom(u);
            if (!userInfosolo.getName().equals(uname.toLowerCase()) || (!userInfosolo.getPassword().equals(encodePassword(password))
                    && !userInfosolo.getPassword().equals(password))) {
                LOG.error("checkU" + databasesolo.toString() + ":(" + uname + "," + password + "), infoSaved("
                        + userInfosolo.getName() + "," + userInfosolo.getPassword() + ")");
                resultsolo = false;
            }
        }
        return resultsolo;
    }

    /**
     * 验证uname、upass在所有库中是否存储正确
     *
     * @param uname
     * @param password
     * @return
     * @throws InvalidProtocolBufferException
     * @throws NoSuchAlgorithmException
     */
    public static boolean checkU(String uname, String password) throws InvalidProtocolBufferException, NoSuchAlgorithmException {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkUSolo(databasesolo, uname, password);
            result = result && resultsolo;
        }
        return result;
    }

    /**
     * 验证uname、upass在所有库中是否存储错误
     *
     * @param uname
     * @param password
     * @return
     * @throws InvalidProtocolBufferException
     * @throws NoSuchAlgorithmException
     */
    public static boolean checkNotU(String uname, String password) throws InvalidProtocolBufferException, NoSuchAlgorithmException {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkUSolo(databasesolo, uname, password);
            result = result && !resultsolo;
        }
        return result;
    }

    public static boolean checkUkeySolo(BinaryJedis databasesolo, String uname, String keytp, String key) throws InvalidProtocolBufferException, NoSuchAlgorithmException {
        boolean resultsolo = true;
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
                if (getkeysolo == KeyIdCast.getKeyIdbyKey(keytp)) {
                    getvaluesolo = keyValue.getValue();
                }
            }
            if (getvaluesolo == null || !getvaluesolo.equals(key)) {
                LOG.error("checkUkey" + databasesolo.toString() + ":(" + uname + "," + keytp + "," + key + "), keySaved("
                        + getvaluesolo + ")");
                resultsolo = false;
            }
        }
        return resultsolo;
    }

    /**
     * 验证uname、keytp和key在所有库中是否存储正确
     *
     * @param uname
     * @param keytp
     * @param key
     * @return
     * @throws InvalidProtocolBufferException
     * @throws NoSuchAlgorithmException
     */
    public static boolean checkUkey(String uname, String keytp, String key) throws InvalidProtocolBufferException, NoSuchAlgorithmException {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkUkeySolo(databasesolo, uname, keytp, key);
            result = result && resultsolo;
        }
        return result;
    }

    /**
     * 验证uname、keytp和key在所有库中是否存储错误
     *
     * @param uname
     * @param keytp
     * @param key
     * @return
     * @throws InvalidProtocolBufferException
     * @throws NoSuchAlgorithmException
     */
    public static boolean checkNotUkey(String uname, String keytp, String key) throws InvalidProtocolBufferException, NoSuchAlgorithmException {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkUkeySolo(databasesolo, uname, keytp, key);
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

    /**
     * 验证keytp和key在所有库中均有索引指向uname
     *
     * @param uname
     * @param keytp
     * @param key
     * @return
     */
    public static boolean checkIndex(String uname, String keytp, String key) {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkIndexSolo(databasesolo, uname, keytp, key);
            result = result && resultsolo;
        }
        return result;
    }

    /**
     * 验证keytp和key在所有库中均没有索引指向uname
     *
     * @param uname
     * @param keytp
     * @param key
     * @return
     */
    public static boolean checkNotIndex(String uname, String keytp, String key) {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkIndexSolo(databasesolo, uname, keytp, key);
            result = result && !resultsolo;
        }
        return result;
    }

    /**
     * 根据传入的uname得到库里的uid，再与传入的uid（可以为“”）比较是否一致；通过库里的uid得到对应的uname，与传入的uname比较是否一致
     *
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
            com.gw.account.core.Userinfo.UserInfo userInfosolo = Userinfo.UserInfo.parseFrom(u);
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

    /**
     * 验证uid在所有库中均指向uname
     *
     * @param uname
     * @param uid
     * @return
     * @throws InvalidProtocolBufferException
     */
    public static boolean checkUid(String uname, String uid) throws InvalidProtocolBufferException {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkUidSolo(databasesolo, uname, uid);
            result = result && resultsolo;
        }
        return result;
    }

    /**
     * 验证uid在所有库中均没有指向uname
     *
     * @param uname
     * @param uid
     * @return
     * @throws InvalidProtocolBufferException
     */
    public static boolean checkNotUid(String uname, String uid) throws InvalidProtocolBufferException {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkUidSolo(databasesolo, uname, uid);
            result = result && !resultsolo;
        }
        return result;
    }

    public static boolean checkURSolo(BinaryJedis databasesolo, String uname, JSONObject keyvalue) throws InvalidProtocolBufferException {
        boolean resultsolo = true;
        byte[] ur = databasesolo.get(("ur:" + uname.toLowerCase()).getBytes());
        if (keyvalue == null) {
            resultsolo = ur == null;
        } else if (ur == null) {
            LOG.error("checkUR" + databasesolo.toString() + ": (ur:" + uname + ") No data!");
            resultsolo = false;
        } else {
            com.gw.account.core.Userinfo.UserRight userRight = Userinfo.UserRight.parseFrom(ur);
            long Marketdb = userRight.getMarket();
            String RightBufdb = userRight.getRightStr();
            long uMarket = Long.parseLong(keyvalue.getString("uMarket"));
            String extrabuf = keyvalue.getString("extrabuf");
            resultsolo = Marketdb == uMarket && RightBufdb.equals(extrabuf);
        }
        return resultsolo;
    }

    public static boolean checkUR(String uname, JSONObject keyvalue) throws InvalidProtocolBufferException {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkURSolo(databasesolo, uname, keyvalue);
            result = result && resultsolo;
        }
        return result;
    }

    public static boolean checkNotUR(String uname, JSONObject keyvalue) throws InvalidProtocolBufferException {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkURSolo(databasesolo, uname, keyvalue);
            result = result && !resultsolo;
        }
        return result;
    }

    public static boolean checkBankSolo(BinaryJedis databasesolo, String uname, JSONObject bankidtobankcard) throws InvalidProtocolBufferException {
        boolean resultsolo = true;
        byte[] bank = databasesolo.get(("ubank:" + uname.toLowerCase()).getBytes());
        if (bankidtobankcard == null) {
            resultsolo = (bank == null);
        } else {
            if (bank == null) {
                LOG.error("checkBank" + databasesolo.toString() + ": (ubank:" + uname + ") No data!");
                resultsolo = false;
            } else {
                com.gw.account.core.Bankinfo.BankList bankList = Bankinfo.BankList.parseFrom(bank);
                String string = bankListToJson(bankList);
                LOG.debug(string);
                JSONObject jsonObject = JSON.parseObject(string);
                JSONArray jsonArray = jsonObject.getJSONArray("Banks");
                JSONObject jsonObjectwr = null;
                JSONObject jsonObjectdb = null;
                for (String bankcardid : bankidtobankcard.keySet()) {
                    jsonObjectwr = bankidtobankcard.getJSONObject(bankcardid);
                    for (int i = 0; i < jsonArray.size(); i++) {
                        if (bankcardid.equals(jsonArray.getJSONObject(i).getString("bankcardid"))) {
                            jsonObjectdb = jsonArray.getJSONObject(i);
                            break;
                        }
                    }
                    for (String key : jsonObjectwr.keySet()) {
                        if (key.equals("uname"))
                            continue;
                        if (jsonObjectwr.getString(key).equals(jsonObjectdb.getString(key))
                                || key.equals("bankcardno") && jsonObjectwr.getString(key).equals(Aes.decrypt(jsonObjectdb.getString(key), AES_KEY))
                                || key.equals("ylmobile") && jsonObjectwr.getString(key).equals(jsonObjectdb.getString("recallmobile"))) {
                            continue;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return resultsolo;
    }

    public static boolean checkBank(String uname, JSONObject keyvalue) throws InvalidProtocolBufferException {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkBankSolo(databasesolo, uname, keyvalue);
            result = result && resultsolo;
        }
        return result;
    }

    public static boolean checkNotBank(String uname, JSONObject keyvalue) throws InvalidProtocolBufferException {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkBankSolo(databasesolo, uname, keyvalue);
            result = result && !resultsolo;
        }
        return result;
    }

    public static boolean checkDelBankSolo(BinaryJedis databasesolo, String uname, String bankcardid) throws InvalidProtocolBufferException {
        boolean resultsolo = true;
        byte[] bank = databasesolo.get(("ubank:" + uname.toLowerCase()).getBytes());
        if (bank == null) {
            LOG.error("checkBank" + databasesolo.toString() + ": (ubank:" + uname + ") No data!");
            resultsolo = false;
        } else {
            com.gw.account.core.Bankinfo.BankList bankList = Bankinfo.BankList.parseFrom(bank);
            String string = bankListToJson(bankList);
            LOG.debug(string);
            JSONObject jsonObject = JSON.parseObject(string);
            JSONArray jsonArray = jsonObject.getJSONArray("DelBanks");
            boolean exist = false;
            for (int i = 0; i < jsonArray.size(); i++) {
                if (bankcardid.equals(jsonArray.getJSONObject(i).getString("bankcardid"))) {
                    exist = true;
                }
            }
            resultsolo = exist;
        }
        return resultsolo;
    }

    public static boolean checkDelBank(String uname, String bankcardid) throws InvalidProtocolBufferException {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkDelBankSolo(databasesolo, uname, bankcardid);
            result = result && resultsolo;
        }
        return result;
    }

    public static boolean checkNotDelBank(String uname, String bankcardid) throws InvalidProtocolBufferException {
        boolean result = true;
        for (BinaryJedis databasesolo : checklist) {
            boolean resultsolo = checkDelBankSolo(databasesolo, uname, bankcardid);
            result = result && !resultsolo;
        }
        return result;
    }

    //=================================工具方法=======================================
    /**
     * 从response中提取keytp的值
     *
     * @param response
     * @param keytp
     * @return
     */
    public static String getValueFromResponse(String response, String keytp) {
        int startindex = response.indexOf(keytp) + keytp.length() + 1;
        int endindex = (response.indexOf("&", startindex) != -1) ? response.indexOf("&", startindex) : response.length();
        String value = response.substring(startindex, endindex);
        return value;
    }

    /**
     * 验证response中keytp的值是否等于key
     *
     * @param response
     * @param keytp
     * @param key
     * @return
     */
    public static boolean checkResponseSolo(String response, String keytp, String key) {
        String getkey = getValueFromResponse(response, keytp);
        boolean result = getkey.equals(key);
        return result;
    }

    /**
     * 从JSON的response中提取keytp的值
     *
     * @param response
     * @param keytp
     * @return
     */
    public static String getValueFromJsonResponse(String response, String keytp) {
        JSONObject jsonreponse = JSONObject.parseObject(response);
        String value = jsonreponse.getString(keytp);
        return value;
    }

    /**
     * 从JSON的response中提取keys的值
     *
     * @param response
     * @return
     */
    public static JSONArray getKeysFromJsonResponse(String response) {
        JSONObject jsonreponse = JSONObject.parseObject(response);
        JSONArray value = (JSONArray) jsonreponse.get("keys");
        return value;
    }

    /**
     * 验证JSON的response中keytp的值是否等于key
     *
     * @param response
     * @param keytp
     * @param key
     * @return
     */
    public static boolean checkJsonResponseSolo(String response, String keytp, String key) {
        String getkey = getValueFromJsonResponse(response, keytp);
        boolean result = getkey.equals(key);
        return result;
    }

    /**
     * 新建用户，从response中提取usertid并返回
     *
     * @param uname
     * @param upass
     * @return
     * @throws IOException
     * @throws SAXException
     */
    public static String addUser(String uname, String upass) throws IOException, SAXException {
        String response = AccInterface.testAdduser("&uname=" + uname + "&upass=" + upass);
        String usertid = MyCheckUtil.getValueFromResponse(response, "usertid");
        return usertid;
    }

    /**
     * 将key绑定到uname上，并返回response
     *
     * @param uname
     * @param keytp
     * @param key
     * @return
     * @throws IOException
     * @throws SAXException
     */
    public static String bindKey(String uname, String keytp, String key) throws IOException, SAXException {
        String keyencode = URLEncoder.encode(keytp + "=" + key, "UTF-8");
        String response = AccInterface.testUserbind("&uname=" + uname + "&key=" + keyencode);
        return response;
    }

    /**
     * 将upass做MD5处理
     *
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
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

    public static String encodeMD5(String string) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6',
                '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdinst = MessageDigest.getInstance("MD5");
            byte[] md = mdinst.digest(string.getBytes());
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从response中提取code值
     *
     * @param response
     * @return
     */
    public static int getCode(String response) {
        int index = response.indexOf("&");
        int code = Integer.parseInt(response.substring(0, index));
        return code;
    }

    public static String bankListToJson(Bankinfo.BankList l) {
        Map<String, Object> map = new TreeMap<String, Object>();
        List<Object> o = new ArrayList<Object>();
        for (Bankinfo.BankInfo bi : l.getBanksList()) {
            o.add(bankToJson(bi));
        }
        map.put("Banks", o);
        if (l.hasPassword()) {
            map.put("Password", l.getPassword());
        }
        List<Object> o2 = new ArrayList<Object>();
        for (Bankinfo.BankInfo bi : l.getDelBanksList()) {
            o2.add(bankToJson(bi));
        }
        map.put("DelBanks", o2);
        return JSON.toJSONString(map);
    }

    public static Map<String, Object> bankToJson(Bankinfo.BankInfo b) {
        Map<String, Object> map = new TreeMap<String, Object>();
        Class c = b.getClass();
        for (Method m : c.getMethods()) {
            String name = m.getName();
            if (!name.substring(0, 3).equals("get")) {
                continue;
            }
            try {
                Method hasM = c.getMethod("has" + name.substring(3));
                Boolean bv1 = (Boolean) hasM.invoke(b);
                if (!bv1.booleanValue()) {
                    continue;
                }
            } catch (NoSuchMethodException e) {
                continue;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                Object v = m.invoke(b);
                map.put(name.substring(3).toLowerCase(), v);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return map;
    }

}
