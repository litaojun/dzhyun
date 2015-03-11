package com.gw.account.httptest;

import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

/**
 * Created by song on 2015/3/4.
 */
public class UserbindTest {
    //static long mobile = 13000000000;
    @Test
    public void testBindEmail() throws IOException, SAXException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String number = df.format(new Date());
        String uname = "test" + number;
        String regurl = "&uname=" + uname + "&upass=11111111";
        AccInterface.testAdduser(regurl);
        sleep(2000);
        String emailencode = URLEncoder.encode("email=" + uname + "@126.com","UTF-8");
        String params = "&uname=" + uname + "&key=" + emailencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("Case1:正常邮箱绑定,msg: " + result , result.contains("result=0"));
    }

    @Test
    public void testBindMobile() throws IOException, SAXException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String number = df.format(new Date());
        String uname = "test" + number;
        String regurl = "&uname=" + uname + "&upass=11111111";
        AccInterface.testAdduser(regurl);
        sleep(2000);
        String emailencode = URLEncoder.encode("mobile=" + uname + "@126.com","UTF-8");
        String params = "&uname=" + uname + "&key=" + emailencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("Case1:正常邮箱绑定,msg: " + result , result.contains("result=0"));
    }

//    @Test
//    public void testBindNickname() throws IOException, SAXException {
//        String string = AccInterface.testLogin("Case1:正常账号名登录",uname,pass_md5_str);
//        assertTrue("Case1:正常账号名登录,msg: " + string , string.contains("result=0"));
//    }
//
//    @Test
//    public void testBindIdcard() throws IOException, SAXException {
//        String string = AccInterface.testLogin("Case1:正常账号名登录",uname,pass_md5_str);
//        assertTrue("Case1:正常账号名登录,msg: " + string , string.contains("result=0"));
//    }
}
