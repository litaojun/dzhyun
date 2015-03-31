package com.gw.account.httptest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/3/27.
 */
public class SendsmsTest {
    private static final Log LOG = LogFactory.getLog(CheckpassTest.class);
    private static String clientId;
    private static String uname;
    private static String email;
    private static String mobile;
    private static String lotterid;
    private static String deviceid;
    private static String pushid;
    private static String nlotterid;
    private static String truename;
    private static String nickname;
    private static String idcard;
    private static String qqid;
    private static String lcb;
    private static String wxid;
    //private static String bankcard;
    //private static String
    private static String xcid;
    private static String upass = "11111111";
    private static String verifyCode = "666666";

    @BeforeClass
    public static void globalInit() throws IOException, SAXException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        uname = "Test" + "测试_" + number;
        email = "Test" + number + "@126.com";
        mobile = "188" + number;
        lotterid = "Lotter" + number;
        deviceid = "Device" + number;
        pushid = "Push" + number;
        nlotterid = "Nlotter" + number;
        truename = "True" + "测试" + number;
        nickname = "Nick" + "测试_" + number;
        idcard = String.format("%018d", Long.parseLong(number));
        qqid = "qq" + number;
        lcb = "lcb" + number;
        wxid = "wx" + number;
        xcid= "xc" + number;
        AccInterface.testAdduser("&uname=" + uname + "&upass=" + upass);
        bindKey(uname,"email",email);
        bindKey(uname,"mobile",mobile);
        bindKey(uname,"lotterid",lotterid);
        bindKey(uname,"deviceid",deviceid);
        bindKey(uname,"pushid",pushid);
        bindKey(uname,"nlotterid",nlotterid);
        bindKey(uname,"truename",truename);
        bindKey(uname,"nickname",nickname);
        bindKey(uname,"idcard",idcard);
        bindKey(uname,"qqid",qqid);
        bindKey(uname,"lcb",lcb);
        bindKey(uname,"wxid",wxid);
        bindKey(uname,"xcid",xcid);
    }

    public static void bindKey(String uname, String keytp, String key) throws IOException, SAXException {
        String keyencode = URLEncoder.encode(keytp + "=" + key, "UTF-8");
        AccInterface.testUserbind("&uname=" + uname + "&key=" + keyencode);
    }

    //=================================正确key发送短信，不加验证码=======================================
    /**
     * 使用正确用户名发送短信，不加验证码
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public static void sendsmsUname() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientId + "&keyname=uname" + "&keyvalue=" + uname);
        int code = Integer.parseInt(MyCheckUtil.getValueFromResponse(response,"code"));
        boolean result = response.contains("result=0") && code > 0;
        assertTrue("使用正确用户名发送短信，不加验证码",result);
    }

    /**
     * 使用正确手机号码发送短信，不加验证码
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public static void sendsmsMobile() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientId + "&keyname=mobile" + "&keyvalue=" + mobile);
        int code = Integer.parseInt(MyCheckUtil.getValueFromResponse(response,"code"));
        boolean result = response.contains("result=0") && code > 0;
        assertTrue("使用正确手机号码发送短信，不加验证码",result);
    }

    /**
     * 使用正确邮箱发送短信，不加验证码
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public static void sendsmsEmail() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientId + "&keyname=email" + "&keyvalue=" + email);
        int code = Integer.parseInt(MyCheckUtil.getValueFromResponse(response,"code"));
        boolean result = response.contains("result=0") && code > 0;
        assertTrue("使用正确邮箱发送短信，不加验证码",result);
    }

    //=================================正确key发送短信，加验证码=======================================
    /**
     * 使用正确用户名发送短信，加验证码
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public static void sendsmsUnameandverifyCode() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientId + "&keyname=uname" + "&keyvalue=" + uname + "&verifyCode=" + verifyCode);
        int code = Integer.parseInt(MyCheckUtil.getValueFromResponse(response,"code"));
        boolean result = response.contains("result=0") && code > 0;
        assertTrue("使用正确用户名发送短信，加验证码",result);
    }

    /**
     * 使用正确手机号码发送短信，加验证码
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public static void sendsmsMobileandverifyCode() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientId + "&keyname=mobile" + "&keyvalue=" + mobile + "&verifyCode=" + verifyCode);
        int code = Integer.parseInt(MyCheckUtil.getValueFromResponse(response,"code"));
        boolean result = response.contains("result=0") && code > 0;
        assertTrue("使用正确手机号码发送短信，加验证码",result);
    }

    /**
     * 使用正确邮箱发送短信，加验证码
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public static void sendsmsEmailandverifyCode() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientId + "&keyname=email" + "&keyvalue=" + email + "&verifyCode=" + verifyCode);
        int code = Integer.parseInt(MyCheckUtil.getValueFromResponse(response,"code"));
        boolean result = response.contains("result=0") && code > 0;
        assertTrue("使用正确邮箱发送短信，加验证码",result);
    }
}
