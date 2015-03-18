package com.gw.account.httptest;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

import org.junit.*;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by song on 2015/2/12.
 */
public class LoginTest {
    private static String uname;
    private static String email;
    private static String mobile;
    private static String pass_md5_str = "11111111";
    private static String uname_NotExist = "nnnnnnnn";
    private static String email_NotExist = "nnnnnnnn@126.com";
    private static String mobile_NotExist = "18000000000";
    private static String pass_md5_str_Wrong = "22222222";


    @Before
    public void setUp() throws IOException, SAXException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        uname = "Test" + "测试" + number;
        email = "Test" + number + "@126.com";
        mobile = "188" + number;
        String emailencode = URLEncoder.encode("email=" + email, "UTF-8");
        String mobilencode = URLEncoder.encode("mobile=" + mobile, "UTF-8");
        AccInterface.testAdduser("&uname=" + uname + "&upass=" + pass_md5_str);
        AccInterface.testUserbind("&uname=" + uname + "&key=" + emailencode + "&key=" + mobilencode);
        sleep(1000);
    }

    @Test
    public void testCorrectUnamelogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + uname + "&upass=" + pass_md5_str);
        assertTrue("正常账号名登录,msg: " + string , string.contains("result=0"));
    }

    @Test
    public void testCorrectEmaillogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + email + "&upass=" + pass_md5_str);
        assertTrue("正常邮箱登录,msg: " + string , string.contains("result=0"));
    }

    @Test
    public void testCorrectMobilelogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + mobile + "&upass=" + pass_md5_str);
        assertTrue("正常手机登录,msg: " + string , string.contains("result=0"));
    }

    @Test
    public void testNotExistUnamelogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + uname_NotExist + "&upass=" + pass_md5_str);
        assertTrue("登录账号名不存在,msg: " + string , string.contains("result=2"));
    }

    @Test
    public void testNotExistEmaillogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + email_NotExist + "&upass=" + pass_md5_str);
        assertTrue("登录邮箱不存在,msg: " + string , string.contains("result=2"));
    }

    @Test
    public void testNotExistMobilelogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + mobile_NotExist + "&upass=" + pass_md5_str);
        assertTrue("登录手机不存在,msg: " + string , string.contains("result=2"));
    }

    @Test
    public void testNullUnamelogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + "" + "&upass=" + pass_md5_str);
        assertTrue("登录用户名为空,msg: " + string , string.contains("result=1"));
    }

    @Test
    public void testUnameWrongPasswordlogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + uname + "&upass=" + pass_md5_str_Wrong);
        assertTrue("账号名登录密码错误,msg: " + string , string.contains("result=59"));
    }

    @Test
    public void testEmailWrongPasswordlogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + email + "&upass=" + pass_md5_str_Wrong);
        assertTrue("邮箱登录密码错误,msg: " + string , string.contains("result=59"));
    }

    @Test
    public void testMobileWrongPasswordlogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + mobile + "&upass=" + pass_md5_str_Wrong);
        assertTrue("手机登录密码错误,msg: " + string , string.contains("result=59"));
    }

    @Test
    public void testUnameNullPasswordlogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + uname + "&upass=" + "");
        assertTrue("账号名登录密码为空,msg: " + string , string.contains("result=101"));
    }

    @Test
    public void testEmailNullPasswordlogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + email + "&upass=" + "");
        assertTrue("邮箱登录密码为空,msg: " + string , string.contains("result=101"));
    }

    @Test
    public void testMobileNullPasswordlogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + mobile + "&upass=" + "");
        assertTrue("手机登录密码为空,msg: " + string , string.contains("result=101"));
    }
}
