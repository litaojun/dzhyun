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
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/3/6.
 */
public class DeluserbindTest {
    private static final Log LOG = LogFactory.getLog(DeluserbindTest.class);
    private static User user = new User();

    @BeforeClass
    public static void globalInit() throws IOException, SAXException, InterruptedException {
        MyCheckUtil.initialize();
    }

    @Before
    public void setUp() throws IOException, SAXException, InterruptedException {
        user.createUser();
        MyCheckUtil.bindKey(user.getUname(),"email",user.getEmail());
        MyCheckUtil.bindKey(user.getUname(),"mobile",user.getMobile());
        MyCheckUtil.bindKey(user.getUname(),"nickname",user.getNickname());
        MyCheckUtil.bindKey(user.getUname(),"idcard",user.getIdcard());
    }

    /**
     * 删除邮箱绑定
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     * @throws InterruptedException
     */
    @Test
    public void testDelBindEmail() throws IOException, SAXException, NoSuchAlgorithmException, InterruptedException {
        boolean result = checkDelBindKey(user.getUname(),"email",user.getEmail());
        assertTrue("删除邮箱绑定", result);
    }

    /**
     * 删除手机绑定
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     * @throws InterruptedException
     */
    @Test
    public void testDelBindMobile() throws IOException, SAXException, NoSuchAlgorithmException, InterruptedException {
        boolean result = checkDelBindKey(user.getUname(),"mobile",user.getMobile());
        assertTrue("删除手机绑定", result);
    }

    /**
     * 删除昵称绑定
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     * @throws InterruptedException
     */
    @Test
    public void testDelBindNickname() throws IOException, SAXException, NoSuchAlgorithmException, InterruptedException {
        boolean result = checkDelBindKey(user.getUname(),"nickname",user.getNickname());
        assertTrue("删除昵称绑定", result);
    }

    /**
     * 删除身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDelBindIdcard() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkDelBindKey(user.getUname(),"idcard",user.getIdcard());
        assertTrue("删除身份证绑定", result);
    }

    /**
     * 删除参数为空的绑定信息
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testDelBindNull() throws IOException, SAXException {
        String result = AccInterface.testDelUserbind("&uname=" + user.getUname() + "&key=");
        assertTrue("删除参数为空的绑定信息,msg: " + result , result.contains("result=101"));
    }

    public static boolean checkDelBindKey(String uname, String keytp, String key) throws IOException, SAXException, NoSuchAlgorithmException, InterruptedException {
        String response = AccInterface.testDelUserbind("&uname=" + user.getUname() + "&key=" + keytp);
        boolean checkresponse = MyCheckUtil.checkResponse(response,user.getUname(),keytp,"1");
        boolean checku = MyCheckUtil.checkU(user.getUsertid(), URLDecoder.decode(user.getUpass(),"UTF-8"));
        boolean checknotukey = MyCheckUtil.checkNotUkey(user.getUsertid(), keytp, key);
        boolean checknotindex = MyCheckUtil.checkNotIndex(user.getUsertid(),keytp,key);
        boolean result = checkresponse && checku && checknotukey && checknotindex;
        return result;
    }
}
