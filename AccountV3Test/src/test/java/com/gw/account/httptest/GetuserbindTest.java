package com.gw.account.httptest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

/**
 * Created by song on 2015/3/6.
 */
public class GetuserbindTest {
    private static final Log LOG = LogFactory.getLog(GetuserbindTest.class);
    private static String uname;
    private static String unameforidcard;
    private static String email;
    private static String mobile;
    private static String truename;
    private static String nickname;
    private static String idcard_18;
    private static String idcard_18_x;
    private static String idcard_18_X;
    private static String idcard_15;
    private static String idcard_15_x;
    private static String idcard_15_X;
    private static String pass_md5_str = "11111111";

    @BeforeClass
    public static void globalInit() throws IOException, SAXException, InterruptedException {
        MyCheckUtil.initialize();
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        uname = "Test" + "测试" + number;
        email = "Test" + number + "@126.com";
        mobile = "188" + number;
        truename = "True" + "测试" + number;
        nickname = "Nick" + number;
        idcard_18 = String.format("%018d",Long.parseLong(number));
        idcard_18_x = String.format("%017d",Long.parseLong(number)) + "x";
        idcard_18_X = String.format("%017d",Long.parseLong(number)) + "X";
        idcard_15 = String.format("%015d",Long.parseLong(number));
        idcard_15_x = String.format("%014d",Long.parseLong(number)) + "x";
        idcard_15_X = String.format("%014d",Long.parseLong(number)) + "X";
        String emailencode = URLEncoder.encode("email=" + email, "UTF-8");
        String mobilencode = URLEncoder.encode("mobile=" + mobile, "UTF-8");
        String truenameencode = URLEncoder.encode("truename=" + truename, "UTF-8");
        String nicknameencode = URLEncoder.encode("nickname=" + nickname, "UTF-8");
        AccInterface.testAdduser("&uname=" + uname + "&upass=" + pass_md5_str);
        AccInterface.testUserbind("&uname=" + uname + "&key="  + emailencode + "&key=" + mobilencode
                + "&key=" + truenameencode + "&key=" + nicknameencode);
        sleep(1000);
    }


    @Test
    public void testGetBindEmail() throws IOException, SAXException {
        String response = AccInterface.testGetUserbind("&uname=" + uname + "&keytp=email");
        boolean result = MyCheckUtil.checkResponse(response,uname,"email",email);
        assertTrue("查询绑定邮箱,msg: " + response , result);
    }

    @Test
    public void testGetBindMobile() throws IOException, SAXException {
        String response = AccInterface.testGetUserbind("&uname=" + uname + "&keytp=mobile");
        boolean result = MyCheckUtil.checkResponse(response,uname,"mobile",mobile);
        assertTrue("查询绑定手机,msg: " + response , result);
    }

    @Test
    public void testGetBindTruename() throws IOException, SAXException {
        String response = AccInterface.testGetUserbind("&uname=" + uname + "&keytp=truename");
        boolean result = MyCheckUtil.checkResponse(response,uname,"truename",truename);
        assertTrue("查询绑定昵称,msg: " + response , result);
    }

    @Test
    public void testGetBindNickname() throws IOException, SAXException {
        String response = AccInterface.testGetUserbind("&uname=" + uname + "&keytp=nickname");
        boolean result = MyCheckUtil.checkResponse(response,uname,"nickname",nickname);
        assertTrue("查询绑定昵称,msg: " + response , result);
    }

    public static void createUserBind(String idcard) throws IOException, SAXException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        unameforidcard = "Test" + "测试" + number;
        String idcardencode = URLEncoder.encode("idcard=" + idcard, "UTF-8");
        String params = "&uname=" + unameforidcard + "&key=" + idcardencode;
        AccInterface.testAdduser("&uname=" + unameforidcard + "&upass=" + pass_md5_str);
        AccInterface.testUserbind(params);
        sleep(1000);
    }

    @Test
    public void testGetBindIdcard18() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_18);
        String response = AccInterface.testGetUserbind("&uname=" + unameforidcard + "&keytp=idcard");
        boolean result = MyCheckUtil.checkResponse(response,unameforidcard,"idcard",idcard_18);
        assertTrue("查询绑定身份证,msg: " + response , result);
    }

    @Test
    public void testGetBindIdcard18x() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_18_x);
        String response = AccInterface.testGetUserbind("&uname=" + unameforidcard + "&keytp=idcard");
        boolean result = MyCheckUtil.checkResponse(response, unameforidcard, "idcard", idcard_18_x);
        assertTrue("查询绑定身份证,msg: " + response, result);
    }

    @Test
    public void testGetBindIdcard18X() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_18_X);
        String response = AccInterface.testGetUserbind("&uname=" + unameforidcard + "&keytp=idcard");
        boolean result = MyCheckUtil.checkResponse(response, unameforidcard, "idcard", idcard_18_X);
        assertTrue("查询绑定身份证,msg: " + response, result);
    }

    @Test
    public void testGetBindIdcard15() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_15);
        String response = AccInterface.testGetUserbind("&uname=" + unameforidcard + "&keytp=idcard");
        boolean result = MyCheckUtil.checkResponse(response, unameforidcard, "idcard", idcard_15);
        assertTrue("查询绑定身份证,msg: " + response, result);
    }

    @Test
    public void testGetBindIdcard15x() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_15_x);
        String response = AccInterface.testGetUserbind("&uname=" + unameforidcard + "&keytp=idcard");
        boolean result = MyCheckUtil.checkResponse(response, unameforidcard, "idcard", idcard_15_x);
        assertTrue("查询绑定身份证,msg: " + response, result);
    }

    @Test
    public void testGetBindIdcard15X() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_15_X);
        String response = AccInterface.testGetUserbind("&uname=" + unameforidcard + "&keytp=idcard");
        boolean result = MyCheckUtil.checkResponse(response, unameforidcard, "idcard", idcard_15_X);
        assertTrue("查询绑定身份证,msg: " + response, result);
    }

    @Test
    public void testGetBindNull() throws IOException, SAXException {      //不传递是可以的,表示就是uname，带keytp表示这个uname是个key
                                                                          // 比如可以直接用mobile来进行查询
        String result = AccInterface.testGetUserbind("&uname=" + uname + "&keytp=");
        assertTrue("查询参数为空的绑定信息,msg: " + result , result.contains("result=0"));
    }
}
