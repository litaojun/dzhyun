package com.gw.account.httptest;

import com.gw.account.utils.MyCheckUtil;
import com.gw.account.utils.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/4/3.
 */
public class AdduserexTestNew {
    private static final Log LOG = LogFactory.getLog(AdduserexTestNew.class);
    private User user = new User();
    private static String prefix;

    @BeforeClass
    public static void globalInit() {
        MyCheckUtil.initialize();
    }

    @Before
    public void setUp() throws IOException, SAXException, InterruptedException {
        user.createUser();
    }

    //=================================正常注册=======================================
    /**
     * 只填帐号后缀生成方式：rand
     */
    @Test
    public void testGenRand() throws IOException, SAXException {
        String response = AccInterface.testAdduserexNew("&gen=rand");
        boolean result = checkALL(response, "", "");
        assertTrue("只填帐号后缀生成方式：rand", result);
    }

    /**
     * 测试email
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testEmail() throws IOException, SAXException {
        String response = AccInterface.testAdduserexNew("&prefix=" + prefix + "&gen=seq" + "&keytp=" + "email" + "&key=" + user.getEmail()
                + "&upass=" + user.getUpass());
        boolean result = checkALL(response, "email", user.getEmail());
        assertTrue("测试email", result);
    }

    /**
     * 测试mobile
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testMobile() throws IOException, SAXException {
        String response = AccInterface.testAdduserexNew("&prefix=" + prefix + "&gen=seq" + "&keytp=" + "mobile" + "&key=" + user.getMobile()
                + "&upass=" + user.getUpass());
        boolean result = checkALL(response, "mobile", user.getMobile());
        assertTrue("测试mobile", result);
    }

    /**
     * 测试lotterid
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testLotterid() throws IOException, SAXException {
        String response = AccInterface.testAdduserexNew("&prefix=" + prefix + "&gen=seq" + "&keytp=" + "lotterid" + "&key=" + ""
                + "&upass=" + user.getUpass());
        boolean result = checkALL(response, "lotterid", "");
        assertTrue("测试lotterid", result);
    }

    /**
     * 测试deviceid
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testDeviceid() throws IOException, SAXException {
        String response = AccInterface.testAdduserexNew("&prefix=" + prefix + "&gen=seq" + "&keytp=" + "deviceid" + "&key=" + user.getDeviceid()
                + "&upass=" + user.getUpass());
        boolean result = checkALL(response, "deviceid", user.getDeviceid());
        assertTrue("测试lotterid", result);
    }

    /**
     * 测试pushid
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testPushid() throws IOException, SAXException {
        String response = AccInterface.testAdduserexNew("&prefix=" + prefix + "&gen=seq" + "&keytp=" + "pushid" + "&key=" + user.getPushid()
                + "&upass=" + user.getUpass());
        boolean result = checkALL(response, "pushid", user.getPushid());
        assertTrue("测试pushid", result);
    }

    /**
     * 测试truename
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testTruename() throws IOException, SAXException {
        String response = AccInterface.testAdduserexNew("&prefix=" + prefix + "&gen=seq" + "&keytp=" + "truename" + "&key=" + user.getTruename()
                + "&upass=" + user.getUpass());
        boolean result = checkALL(response, "truename", user.getTruename());
        assertTrue("测试truename", result);
    }

    /**
     * 测试nickname
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNickname() throws IOException, SAXException {
        String response = AccInterface.testAdduserexNew("&prefix=" + prefix + "&gen=seq" + "&keytp=" + "nickname" + "&key=" + user.getNickname()
                + "&upass=" + user.getUpass());
        boolean result = checkALL(response, "nickname", user.getNickname());
        assertTrue("测试nickname", result);
    }

    /**
     * 测试idcard
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testIdcard() throws IOException, SAXException {
        String response = AccInterface.testAdduserexNew("&prefix=" + prefix + "&gen=seq" + "&keytp=" + "idcard" + "&key=" + user.getIdcard()
                + "&upass=" + user.getUpass());
        boolean result = checkALL(response, "idcard", user.getIdcard());
        assertTrue("测试idcard", result);
    }

    /**
     * 测试qqid
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testQqid() throws IOException, SAXException {
        String response = AccInterface.testAdduserexNew("&prefix=" + prefix + "&gen=seq" + "&keytp=" + "qqid" + "&key=" + user.getQqid()
                + "&upass=" + user.getUpass());
        boolean result = checkALL(response, "qqid", user.getQqid());
        assertTrue("测试qqid", result);
    }

    /**
     * 测试lcb
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testLcb() throws IOException, SAXException {
        String response = AccInterface.testAdduserexNew("&prefix=" + prefix + "&gen=seq" + "&keytp=" + "lcb" + "&key=" + user.getLcb()
                + "&upass=" + user.getUpass());
        boolean result = checkALL(response, "lcb", user.getLcb());
        assertTrue("测试lcb", result);
    }

    /**
     * 测试wxid
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWxid() throws IOException, SAXException {
        String response = AccInterface.testAdduserexNew("&prefix=" + prefix + "&gen=seq" + "&keytp=" + "wxid" + "&key=" + user.getWxid()
                + "&upass=" + user.getUpass());
        boolean result = checkALL(response, "wxid", user.getWxid());
        assertTrue("测试wxid", result);
    }

    /**
     * 测试xcid
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testXcid() throws IOException, SAXException {
        String response = AccInterface.testAdduserexNew("&prefix=" + prefix + "&gen=seq" + "&keytp=" + "xcid" + "&key=" + user.getXcid()
                + "&upass=" + user.getUpass());
        boolean result = checkALL(response, "xcid", user.getXcid());
        assertTrue("测试xcid", result);
    }

    //=================================错误注册=======================================

    /**
     * 测试超过20字节prefix
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testLongPrefix() throws IOException, SAXException {
        while (prefix.length() <= 20) {
            prefix += "t";
        }
        String response = AccInterface.testAdduserexNew("&prefix=" + prefix + "&gen=seq");
        boolean result = response.contains("result=101") && response.contains("msg=prefix_bad");
        assertTrue("测试超过20字节prefix", result);
    }

    /**
     * 测试错误gen
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongGen() throws IOException, SAXException {
        String response = AccInterface.testAdduserexNew("&gen=ttt");
        boolean result = response.contains("result=101") && response.contains("msg=parameter_error");
        assertTrue("测试错误gen", result);
    }

    /**
     * 测试空prefix
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNullPrefix() throws IOException, SAXException {
        String response = AccInterface.testAdduserexNew("&prefix=" + "" + "&gen=seq");
        boolean result = checkALL(response, "", "");
        assertTrue("测试空prefix", result);
    }

    /**
     * 测试空gen
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNullGen() throws IOException, SAXException {
        String response = AccInterface.testAdduserexNew("&gen=");
        boolean result = response.contains("result=101") && response.contains("msg=gen_bad");
        assertTrue("测试空gen", result);
    }

//    一次只能绑定一个key
//    public boolean checkALL(String response,Map<String,String> keyvalue) {
//        boolean checkcode = MyCheckUtil.getCode(response) == 1;
//        boolean checkresult = MyCheckUtil.checkResponseSolo(response, "result", "0");
//        boolean checkusertid = MyCheckUtil.getValueFromResponse(response, "usertid") != "";
//        boolean result = true;
//        if (keyvalue != null) {
//            boolean checkuname = true;
//            if (keyvalue.containsKey("prefix")) {
//                checkuname = MyCheckUtil.getValueFromResponse(response, "uname").matches(keyvalue.get("prefix") + "\\d{6}");
//            } else {
//                checkuname = MyCheckUtil.getValueFromResponse(response, "uname").matches("\\d{6}");
//            }
//            boolean checkupass = true;
//            if (keyvalue.containsKey("upass")) {
//                checkupass = MyCheckUtil.getValueFromResponse(response, "upass").equals(keyvalue.get("upass"));
//            } else {
//                checkupass = MyCheckUtil.getValueFromResponse(response, "upass").matches("\\d{6}");
//            }
//            boolean checkkeys = true;
//            for (String key : keyvalue.keySet()) {
//                checkkeys = checkkeys && MyCheckUtil.getValueFromResponse(response, key).equals(keyvalue.get(key));
//            }
//            result = checkcode && checkresult && checkusertid && checkuname && checkupass && checkkeys;
//        } else {
//            boolean checkuname = MyCheckUtil.getValueFromResponse(response, "uname").matches("\\d{6}");
//            boolean checkupass = MyCheckUtil.getValueFromResponse(response, "upass").matches("\\d{6}");
//            result = checkcode && checkresult && checkusertid && checkuname && checkupass;
//        }
//        return result;
//    }

    //=================================校验方法=======================================
    public boolean checkALL(String response, String keytp, String key) throws UnsupportedEncodingException {
        boolean checkcode = MyCheckUtil.getCode(response) == 1;
        boolean checkresult = MyCheckUtil.checkResponseSolo(response, "result", "0");
        boolean checkusertid = MyCheckUtil.getValueFromResponse(response, "usertid") != "";
        String getuname = MyCheckUtil.getValueFromResponse(response, "uname");
        boolean checkuname = getuname.matches(prefix + "\\d+") || getuname.matches("\\d+");
        String getupass = MyCheckUtil.getValueFromResponse(response, "upass");
        boolean checkupass = getupass.equals(user.getUpass()) || getupass.matches("\\d{6}");
        boolean checkkey = true;
        if (keytp != "") {
            String getkey = MyCheckUtil.getValueFromResponse(response, keytp);
            if (key != "") {
                checkkey = getkey.equals(key);
            } else {
                checkkey = getkey != null;
            }
        }
        boolean result = checkcode && checkresult && checkusertid && checkuname && checkupass && checkkey;
        return result;
    }
}
