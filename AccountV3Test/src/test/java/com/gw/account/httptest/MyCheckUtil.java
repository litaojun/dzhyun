package com.gw.account.httptest;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import core.Userinfo;
import org.xml.sax.SAXException;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by song on 2015/3/10.
 */
public class MyCheckUtil {
    private static final Log LOG = LogFactory.getLog(MyCheckUtil.class);
    private static Jedis twemproxy1 = new Jedis("10.15.201.107", 22121);
    private static Jedis twemproxy2 = new Jedis("10.15.201.108", 22121);
    private static Jedis bdb = new Jedis("10.15.108.4", 10001);


    public static boolean checkUserbind(String uname, String keytp, String key) throws IOException, SAXException {
        String keyencode = URLEncoder.encode(keytp + "=" + key, "UTF-8");
        String response = AccInterface.testUserbind("&uname=" + uname + "&key=" + keyencode);
        boolean result = checkResponse(response,uname,keytp,key);
        if (!result) {
            LOG.error("checkUserbind:(" + uname + "," + keytp + "," + key + ")," + response);
        }
        return result;
    }

    public static boolean checkGetUserbind(String uname, String keytp, String key) throws IOException, SAXException {
        String response = AccInterface.testGetUserbind("&uname=" + uname + "&keytp=" + keytp);
        boolean result = checkResponse(response,uname,keytp,key);
        if (!result) {
            LOG.error("checkGetUserbind:(" + uname + "," + keytp + "," + key + ")," + response);
        }
        return result;
    }

    public static boolean checkFindUnamebyKey(String uname, String keytp, String key) throws IOException, SAXException {
        String response = AccInterface.testFindUnamebyKey("&keytp=" + keytp + "&key=" + key);
        String getuname = getValueFromResponse(response, "uname");
        boolean result = response.contains("result=0") && getuname.equals(uname);
        if (!result) {
            LOG.error("checkFindUnamebyKey:(" + uname + "," + keytp + "," + key + ")," + response);
        }
        return result;
    }

    public static boolean checkExist(String uname, String password, String keytp, String key) throws InvalidProtocolBufferException {
        boolean resultredis1;
        boolean resultredis2;
        boolean resultbdb;
        core.Userinfo.UserInfo userInfo1 = Userinfo.UserInfo.parseFrom(twemproxy1.get("u:" + uname));
        Userinfo.UserKeys userKeys1 = Userinfo.UserKeys.parseFrom(twemproxy1.get("ukey:" + uname));
        if (userInfo1.getName().equals(uname) && userInfo1.getPassword().equals(password)) {
            resultredis1 = true;
        } else {
            resultredis1 = false;
            LOG.error("checkExistredis1:(" + uname + "," + password + "),infoSaved(" + userInfo1.getName() + "," + userInfo1.getPassword()
                    + ")");
        }
        long getkey1;
        String getvalue1 = null;
        for(Userinfo.KeyValue keyValue:userKeys1.getKeysList()) {
            getkey1 = keyValue.getKey();
            if (KeyIdCast.getKeyIdbyKey(keytp) == getkey1) {
                getvalue1 = keyValue.getValue();
            }
        }
        if (getvalue1 == null || !getvalue1.equals(key)) {
            resultredis1 = false;
            LOG.error("checkExistredis1:(" + uname + "," + keytp + "," + key + "),keySaved(" + getvalue1 + ")");
        }

        core.Userinfo.UserInfo userInfo2 = Userinfo.UserInfo.parseFrom(twemproxy2.get("u:" + uname));
        Userinfo.UserKeys userKeys2 = Userinfo.UserKeys.parseFrom(twemproxy2.get("ukey:" + uname));
        if (userInfo2.getName().equals(uname) && userInfo2.getPassword().equals(password)) {
            resultredis2 = true;
        } else {
            resultredis2 = false;
            LOG.error("checkExistredis2:(" + uname + "," + password + "),infoSaved(" + userInfo2.getName() + "," + userInfo2.getPassword()
                    + ")");
        }
        long getkey2;
        String getvalue2 = null;
        for(Userinfo.KeyValue keyValue:userKeys2.getKeysList()) {
            getkey2 = keyValue.getKey();
            if (KeyIdCast.getKeyIdbyKey(keytp) == getkey2) {
                getvalue2 = keyValue.getValue();
            }
        }
        if (getvalue2 == null || !getvalue2.equals(key)) {
            resultredis2 = false;
            LOG.error("checkExistredis2:(" + uname + "," + keytp + "," + key + "),keySaved(" + getvalue2 + ")");
        }

        core.Userinfo.UserInfo userInfo3 = Userinfo.UserInfo.parseFrom(twemproxy2.get("u:" + uname));
        Userinfo.UserKeys userKeys3 = Userinfo.UserKeys.parseFrom(twemproxy2.get("ukey:" + uname));
        if (userInfo3.getName().equals(uname) && userInfo3.getPassword().equals(password)) {
            resultbdb = true;
        } else {
            resultbdb = false;
            LOG.error("checkExistBDB:(" + uname + "," + password + "),infoSaved(" + userInfo3.getName() + "," + userInfo3.getPassword()
                    + ")");
        }
        long getkey3;
        String getvalue3 = null;
        for(Userinfo.KeyValue keyValue:userKeys3.getKeysList()) {
            getkey3 = keyValue.getKey();
            if (KeyIdCast.getKeyIdbyKey(keytp) == getkey3) {
                getvalue3 = keyValue.getValue();
            }
        }
        if (getvalue3 == null || !getvalue3.equals(key)) {
            resultbdb = false;
            LOG.error("checkExistBDB:(" + uname + "," + keytp + "," + key + "),keySaved(" + getvalue3 + ")");
        }

        boolean result = resultredis1 && resultredis2 && resultbdb;
        return result;
    }

    public static boolean checkIndex(String uname, String keytp, String key) {
        int keyid = KeyIdCast.getKeyIdbyKey(keytp);
        String unameredis1 = twemproxy1.get("k_" + keyid + ":" + key);
        String unameredis2 = twemproxy2.get("k_" + keyid + ":" + key);
        String unamebdb = bdb.get("k_" + keyid + ":" + key);

        boolean result = unameredis1.equals(uname) && unameredis2.equals(uname) && unamebdb.equals(uname);
        if (!result) {
            LOG.error("checkIndex: (" + uname + "), unameSaved: (" + unameredis1 + "," +
                    unameredis2 + "," + unamebdb + ")");
        }
        return result;
    }

    public static boolean checkUid (String uname) throws InvalidProtocolBufferException {
        core.Userinfo.UserInfo userInfo1 = Userinfo.UserInfo.parseFrom(twemproxy1.get("u:" + uname));
        long id1 = userInfo1.getId();
        String unameredis1 = twemproxy1.get("uid:" + id1);

        core.Userinfo.UserInfo userInfo2 = Userinfo.UserInfo.parseFrom(twemproxy2.get("u:" + uname));
        long id2 = userInfo2.getId();
        String unameredis2 = twemproxy2.get("uid:" + id2);

        core.Userinfo.UserInfo userInfo3 = Userinfo.UserInfo.parseFrom(bdb.get("u:" + uname));
        long id3 = userInfo3.getId();
        String unamebdb = bdb.get("uid:" + id3);

        boolean result = unameredis1.equals(uname) && unameredis2.equals(uname) && unamebdb.equals(uname);
        if (!result) {
            LOG.error("checkUid: (" + uname + "), unameSaved: (" + unameredis1 + "," +
                    unameredis2 + "," + unamebdb + ")");
        }
        return result;
    }

    public static boolean checkALL(String uname, String pass_md5_str, String keytp, String key) throws IOException, SAXException {
        boolean checkuserbind = checkUserbind(uname, keytp, key);
        boolean checkgetuserbind = checkGetUserbind(uname, keytp, key);
        boolean checkfindunamebykey = checkFindUnamebyKey(uname, keytp, key);
        boolean checkexist = checkExist(uname, pass_md5_str, keytp, key);
        boolean checkindex = checkIndex(uname, keytp, key);
        boolean checkuid = checkUid(uname);
        return checkuserbind && checkgetuserbind && checkfindunamebykey && checkexist && checkindex && checkuid;
    }

    public static String getValueFromResponse(String response, String keytp) {
        int startindex = response.indexOf(keytp) + keytp.length() + 1;
        int endindex = (response.indexOf("&",startindex) != -1) ? response.indexOf("&",startindex) : response.length();
        String value = response.substring(startindex,endindex);
        return value;
    }

    public static boolean checkResponse(String response, String uname, String keytp, String key) {
        String getuname = getValueFromResponse(response, "uname");
        String getkey = getValueFromResponse(response,keytp);
        boolean result = response.contains("result=0") && getuname.equals(uname) && getkey.equals(key);
        return result;
    }

}
