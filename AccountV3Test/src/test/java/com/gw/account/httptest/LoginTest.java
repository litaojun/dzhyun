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


/**
 * Created by Hihiri on 2015/2/12.
 */
public class LoginTest {
    private static final Log LOG = LogFactory.getLog(LoginTest.class);
    private User user = new User();
    private static String uname_NotExist = "nnnnnnnn";
    private static String email_NotExist = "nnnnnnnn@126.com";
    private static String mobile_NotExist = "18000000000";
    private static String upass_Wrong = "22222222";

    @BeforeClass
    public static void globalInit() {
        MyCheckUtil.initialize();
    }

    @Before
    public void setUp() throws IOException, SAXException, InterruptedException {
        user.createUser();
        MyCheckUtil.bindKey(user.getUname(),"email",user.getEmail());
        MyCheckUtil.bindKey(user.getUname(),"mobile",user.getMobile());
    }

    /**
     * 正常账号名登录
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectUnamelogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + user.getUname() + "&upass=" + user.getUpass());
        assertTrue("正常账号名登录,msg: " + string , string.contains("result=0"));
    }

    /**
     * 正常邮箱登录
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectEmaillogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + user.getEmail() + "&upass=" + user.getUpass());
        assertTrue("正常邮箱登录,msg: " + string , string.contains("result=0"));
    }

    /**
     * 正常手机登录
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectMobilelogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + user.getMobile() + "&upass=" + user.getUpass());
        assertTrue("正常手机登录,msg: " + string , string.contains("result=0"));
    }

    /**
     * 登录账号名不存在
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNotExistUnamelogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + uname_NotExist + "&upass=" + user.getUpass());
        assertTrue("登录账号名不存在,msg: " + string , string.contains("result=2"));
    }

    /**
     * 登录邮箱不存在
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNotExistEmaillogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + email_NotExist + "&upass=" + user.getUpass());
        assertTrue("登录邮箱不存在,msg: " + string , string.contains("result=2"));
    }

    /**
     * 登录手机不存在
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNotExistMobilelogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + mobile_NotExist + "&upass=" + user.getUpass());
        assertTrue("登录手机不存在,msg: " + string , string.contains("result=2"));
    }

    /**
     * 登录用户名为空
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNullUnamelogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + "" + "&upass=" + user.getUpass());
        assertTrue("登录用户名为空,msg: " + string , string.contains("result=1"));
    }

    /**
     * 账号名登录密码错误
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testUnameWrongPasswordlogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + user.getUname() + "&upass=" + upass_Wrong);
        assertTrue("账号名登录密码错误,msg: " + string , string.contains("result=59"));
    }

    /**
     * 邮箱登录密码错误
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testEmailWrongPasswordlogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + user.getEmail() + "&upass=" + upass_Wrong);
        assertTrue("邮箱登录密码错误,msg: " + string , string.contains("result=2"));
    }

    /**
     * 手机登录密码错误
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testMobileWrongPasswordlogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + user.getMobile() + "&upass=" + upass_Wrong);
        assertTrue("手机登录密码错误,msg: " + string , string.contains("result=2"));
    }

    /**
     * 账号名登录密码为空
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testUnameNullPasswordlogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + user.getUname() + "&upass=" + "");
        assertTrue("账号名登录密码为空,msg: " + string , string.contains("result=101"));
    }

    /**
     * 邮箱登录密码为空
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testEmailNullPasswordlogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + user.getEmail() + "&upass=" + "");
        assertTrue("邮箱登录密码为空,msg: " + string , string.contains("result=101"));
    }

    /**
     * 手机登录密码为空
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testMobileNullPasswordlogin() throws IOException, SAXException {
        String string = AccInterface.testLogin("&uname=" + user.getMobile() + "&upass=" + "");
        assertTrue("手机登录密码为空,msg: " + string , string.contains("result=101"));
    }
}
