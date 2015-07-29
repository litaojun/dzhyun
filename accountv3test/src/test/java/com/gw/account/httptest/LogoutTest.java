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

import static org.junit.Assert.assertTrue;


public class LogoutTest {
    private static final Log LOG = LogFactory.getLog(LogoutTest.class);
    private User user = new User();
    private String uname_NotExist = "nnnnnnnn";
    private String email_NotExist = "nnnnnnnn@126.com";
    private String mobile_NotExist = "18000000000";

    @BeforeClass
    public static void globalInit() {
        MyCheckUtil.initialize();
    }

    @Before
    public void setUp() throws IOException, SAXException, InterruptedException {
        user.createUser();
        MyCheckUtil.bindKey(user.getUname(), "email", user.getEmail());
        MyCheckUtil.bindKey(user.getUname(), "mobile", user.getMobile());
        AccInterface.testLogin("&uname=" + user.getUname() + "&upass=" + user.getUpass());
    }

    /**
     * 正常账号名退出
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectUnamelogout() throws IOException, SAXException {
        String string = AccInterface.testLogout("&uname=" + user.getUname());
        assertTrue("正常账号名退出,msg: " + string, string.contains("result=0"));
    }

    /**
     * 正常邮箱退出
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectEmaillogout() throws IOException, SAXException {
        String string = AccInterface.testLogout("&uname=" + user.getEmail());
        assertTrue("正常邮箱退出,msg: " + string, string.contains("result=0"));
    }

    /**
     * 正常手机退出
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectMobilelogout() throws IOException, SAXException {
        String string = AccInterface.testLogout("&uname=" + user.getMobile());
        assertTrue("正常手机退出,msg: " + string, string.contains("result=0"));
    }

    /**
     * 退出账号名不存在
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNotExistUnamelogout() throws IOException, SAXException {
        String string = AccInterface.testLogout("&uname=" + uname_NotExist);
        assertTrue("退出账号名不存在,msg: " + string, string.contains("result=0"));
    }

    /**
     * 退出邮箱不存在
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNotExistEmaillogout() throws IOException, SAXException {
        String string = AccInterface.testLogout("&uname=" + email_NotExist);
        assertTrue("退出邮箱不存在,msg: " + string, string.contains("result=0"));
    }

    /**
     * 退出手机不存在
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNotExistMobilelogout() throws IOException, SAXException {
        String string = AccInterface.testLogout("&uname=" + mobile_NotExist);
        assertTrue("退出手机不存在,msg: " + string, string.contains("result=0"));
    }

    /**
     * 退出用户名为空
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNullUnamelogout() throws IOException, SAXException {
        String string = AccInterface.testLogout("&uname=" + "");
        assertTrue("退出用户名为空,msg: " + string, string.contains("result=101"));
    }
}
