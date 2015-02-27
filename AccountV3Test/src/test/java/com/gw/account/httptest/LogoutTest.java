package com.gw.account.httptest;

import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

import static org.junit.Assert.*;



public class LogoutTest {
    private String uname = "eeeeeeee";
    private String email = "eeeeeeee@126.com";
    private String mobile = "13000000000";
    private String pass_md5_str = "11111111";
    private String uname_NotExist = "nnnnnnnn";
    private String email_NotExist = "nnnnnnnn@126.com";
    private String mobile_NotExist = "13011111111";

    @Test
    public void testCorrectUnamelogout() throws IOException, SAXException {
        AccInterface.testLogin("",uname,pass_md5_str);
        String string = AccInterface.testLogout("Case1:正常账号名退出",uname);
        assertTrue("Case1:正常账号名退出,msg: " + string , string.contains("result=0"));
    }

    @Test
    public void testCorrectEmaillogout() throws IOException, SAXException {
        AccInterface.testLogin("",email,pass_md5_str);
        String string = AccInterface.testLogout("Case2:正常邮箱退出",email);
        assertTrue("Case2:正常邮箱退出,msg: " + string , string.contains("result=0"));
    }

    @Test
    public void testCorrectMobilelogout() throws IOException, SAXException {
        AccInterface.testLogin("",mobile,pass_md5_str);
        String string = AccInterface.testLogout("Case2:正常手机退出",mobile);
        assertTrue("Case2:正常手机退出,msg: " + string , string.contains("result=0"));
    }

    @Test
    public void testNotExistUnamelogout() throws IOException, SAXException {
        AccInterface.testLogin("",uname,pass_md5_str);
        String string = AccInterface.testLogout("Case4:退出账号名不存在",uname_NotExist);
        assertTrue("Case4:退出账号名不存在,msg: " + string , string.contains("result=0"));
    }

    @Test
    public void testNotExistEmaillogout() throws IOException, SAXException {
        AccInterface.testLogin("",email,pass_md5_str);
        String string = AccInterface.testLogout("Case5:退出邮箱不存在",email_NotExist);
        assertTrue("Case5:退出邮箱不存在,msg: " + string , string.contains("result=0"));
    }

    @Test
    public void testNotExistMobilelogout() throws IOException, SAXException {
        AccInterface.testLogin("",mobile,pass_md5_str);
        String string = AccInterface.testLogout("Case6:退出手机不存在",mobile_NotExist);
        assertTrue("Case6:退出手机不存在,msg: " + string , string.contains("result=0"));
    }

    @Test
    public void testNullUnamelogout() throws IOException, SAXException {
        AccInterface.testLogin("",uname,pass_md5_str);
        String string = AccInterface.testLogout("Case2:退出用户名为空","");
        assertTrue("Case2:退出用户名为空,msg: " + string , string.contains("result=101"));
    }



//	@Test
//	public void testUnamenull() throws IOException,SAXException{
//         String accresult = AccInterface.testLogout("Case1:空用户名退出", "");
//         assertEquals("-101",accresult);
//	}
//
//    @Test
//    public void testUnameNotExist() throws IOException,SAXException{
//    	String accresult = AccInterface.testLogout("Case3:用户名不存在", "notexist");
//    	assertTrue("True",accresult.contains("result=2"));
//    }
//    @Test
//    public void testUnamegt29() throws IOException,SAXException{
//    	String accresult = AccInterface.testLogout("Case4:用户名超过29位","abcdefghijklmnopqrstuvwxyz1234");
//    	assertEquals("-101","accresult");
//    }
}
