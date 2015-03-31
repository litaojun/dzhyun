package com.gw.account.httptest;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;



public class LogoutTest {
    private String uname;
    private String email;
    private String mobile;
    private String upass = "11111111";
    private String uname_NotExist = "nnnnnnnn";
    private String email_NotExist = "nnnnnnnn@126.com";
    private String mobile_NotExist = "18000000000";

    @Before
    public void setUp() throws IOException, SAXException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        uname = "Test" + "测试" + number;
        email = "Test" + number + "@126.com";
        mobile = "188" + number;
        String emailencode = URLEncoder.encode("email=" + email, "UTF-8");
        String mobilencode = URLEncoder.encode("mobile=" + mobile, "UTF-8");
        AccInterface.testAdduser("&uname=" + uname + "&upass=" + upass + "&key=" + emailencode + "&key=" + mobilencode);
        sleep(1000);
    }

    @Test
    public void testCorrectUnamelogout() throws IOException, SAXException {
        AccInterface.testLogin("&uname=" + uname + "&upass=" + upass);
        String string = AccInterface.testLogout("&uname=" + uname);
        assertTrue("正常账号名退出,msg: " + string , string.contains("result=0"));
    }

    @Test
    public void testCorrectEmaillogout() throws IOException, SAXException {
        AccInterface.testLogin("&uname=" + email + "&upass=" + upass);
        String string = AccInterface.testLogout("&uname=" + email);
        assertTrue("正常邮箱退出,msg: " + string , string.contains("result=0"));
    }

    @Test
    public void testCorrectMobilelogout() throws IOException, SAXException {
        AccInterface.testLogin("&uname=" + mobile + "&upass=" + upass);
        String string = AccInterface.testLogout("&uname=" + mobile);
        assertTrue("正常手机退出,msg: " + string , string.contains("result=0"));
    }

    @Test
    public void testNotExistUnamelogout() throws IOException, SAXException {
        AccInterface.testLogin("&uname=" + uname + "&upass=" + upass);
        String string = AccInterface.testLogout("&uname=" + uname_NotExist);
        assertTrue("退出账号名不存在,msg: " + string , string.contains("result=0"));
    }

    @Test
    public void testNotExistEmaillogout() throws IOException, SAXException {
        AccInterface.testLogin("&uname=" + email + "&upass=" + upass);
        String string = AccInterface.testLogout("&uname=" + email_NotExist);
        assertTrue("退出邮箱不存在,msg: " + string , string.contains("result=0"));
    }

    @Test
    public void testNotExistMobilelogout() throws IOException, SAXException {
        AccInterface.testLogin("&uname=" + mobile + "&upass=" + upass);
        String string = AccInterface.testLogout("&uname=" + mobile_NotExist);
        assertTrue("退出手机不存在,msg: " + string , string.contains("result=0"));
    }

    @Test
    public void testNullUnamelogout() throws IOException, SAXException {
        AccInterface.testLogin("&uname=" + uname + "&upass=" + upass);
        String string = AccInterface.testLogout("&uname=" + "");
        assertTrue("退出用户名为空,msg: " + string , string.contains("result=101"));
    }
}
