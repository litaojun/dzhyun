package com.gw.account.httptest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
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
    private static String mobile = "18800000000";
    private static String pass_md5_str = "11111111";
    private static String uname_NotExist = "nnnnnnnn";
    private static String email_NotExist = "nnnnnnnn@126.com";
    private static String mobile_NotExist = "18999999999";
    private static String pass_md5_str_Wrong = "22222222";


    @BeforeClass
    public static void globalInit() throws IOException, SAXException {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        uname = df.format(new Date());
        email = uname + "@126.com";
        String s = mobile.substring(2);
        long l = Long.parseLong("800000000") + 1;
        mobile = "18" + String.valueOf(Long.getLong(mobile.substring(2)) + 1);
        AccInterface.testAdduser("&uname=" + uname + "&upass=" + pass_md5_str);
        String emailencode = URLEncoder.encode("email=" + email, "UTF-8");
        String mobilencode = URLEncoder.encode("mobile=" + mobile, "UTF-8");
        String params = "&uname=" + uname + "&key=" + emailencode + "&key=" + mobilencode;
        System.out.println(params);
        AccInterface.testUserbind(params);
    }


    @AfterClass
    public static void globalDestory() {
        System.out.println("destory all method...");
    }

    @After
    public void tearDown() throws IOException, SAXException {
        AccInterface.testLogout("&uname="+uname);
    }

    @Test
    public void testCorrectUnamelogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("Case1:正常账号名登录",uname,pass_md5_str);
        assertTrue("Case1:正常账号名登录,msg: " + string , string.contains("result=0"));
    }

    @Test
    public void testCorrectEmaillogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("Case2:正常邮箱登录",email,pass_md5_str);
        assertTrue("Case2:正常邮箱登录,msg: " + string , string.contains("result=0"));
    }

    @Test
    public void testCorrectMobilelogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("Case3:正常手机登录",mobile,pass_md5_str);
        assertTrue("Case3:正常手机登录,msg: " + string , string.contains("result=0"));
    }

    @Test
    public void testNotExistUnamelogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("Case4:登录账号名不存在",uname_NotExist,pass_md5_str);
        assertTrue("Case4:登录账号名不存在,msg: " + string , string.contains("result=2"));
    }

    @Test
    public void testNotExistEmaillogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("Case5:登录邮箱不存在",email_NotExist,pass_md5_str);
        assertTrue("Case5:登录邮箱不存在,msg: " + string , string.contains("result=2"));
    }

    @Test
    public void testNotExistMobilelogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("Case6:登录手机不存在",mobile_NotExist,pass_md5_str);
        assertTrue("Case6:登录手机不存在,msg: " + string , string.contains("result=2"));
    }

    @Test
    public void testNullUnamelogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("Case7:登录用户名为空","",pass_md5_str);
        assertTrue("Case7:登录用户名为空,msg: " + string , string.contains("result=1"));
    }

    @Test
    public void testUnameWrongPasswordlogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("Case8:账号名登录密码错误",uname,pass_md5_str_Wrong);
        assertTrue("Case8:账号名登录密码错误,msg: " + string , string.contains("result=59"));
    }

    @Test
    public void testEmailWrongPasswordlogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("Case8:邮箱登录密码错误",email,pass_md5_str_Wrong);
        assertTrue("Case9:邮箱登录密码错误,msg: " + string , string.contains("result=59"));
    }

    @Test
    public void testMobileWrongPasswordlogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("Case8:手机登录密码错误",mobile,pass_md5_str_Wrong);
        assertTrue("Case10:手机登录密码错误,msg: " + string , string.contains("result=59"));
    }

    @Test
    public void testUnameNullPasswordlogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("Case8:账号名登录密码为空",uname,"");
        assertTrue("Case11:账号名登录密码为空,msg: " + string , string.contains("result=101"));
    }

    @Test
    public void testEmailNullPasswordlogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("Case8:邮箱登录密码为空",email,"");
        assertTrue("Case12:邮箱登录密码为空,msg: " + string , string.contains("result=101"));
    }

    @Test
    public void testMobileNullPasswordlogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("Case8:手机登录密码为空",mobile,"");
        assertTrue("Case13:手机登录密码为空,msg: " + string , string.contains("result=101"));
    }
}
