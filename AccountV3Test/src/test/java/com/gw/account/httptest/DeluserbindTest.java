package com.gw.account.httptest;

import com.atopcloud.util.MyBdbUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

/**
 * Created by song on 2015/3/6.
 */
public class DeluserbindTest {
    private static final Log LOG = LogFactory.getLog(DeluserbindTest.class);
    private static String uname;
    private static String email;
    private static String mobile;
    private static String nickname;
    private static String idcard;
    private static String pass_md5_str = "11111111";
    private static MyBdbUtil myBdbUtil = new MyBdbUtil();

    @BeforeClass
    public static void globalInit() throws IOException, SAXException, InterruptedException {
        MyCheckUtil.initialize();
    }

    @Before
    public void setUp() throws IOException, SAXException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        uname = "Test" + "测试" + number;
        email = "Test" + number + "@126.com";
        mobile = "188" + number;
        nickname = "Nick" + number;
        idcard = String.format("%018d",Long.parseLong(number));
        String emailencode = URLEncoder.encode("email=" + email, "UTF-8");
        String mobilencode = URLEncoder.encode("mobile=" + mobile, "UTF-8");
        String nicknameencode = URLEncoder.encode("nickname=" + nickname, "UTF-8");
        String idcardencode = URLEncoder.encode("idcard=" + idcard, "UTF-8");
        AccInterface.testAdduser("&uname=" + uname + "&upass=" + pass_md5_str);
        AccInterface.testUserbind("&uname=" + uname + "&key=" + emailencode +
                "&key=" + mobilencode + "&key="  + nicknameencode + "&key="  + idcardencode);
        sleep(1000);
    }

    @Test
    public void testDelBindEmail() throws IOException, SAXException, NoSuchAlgorithmException, InterruptedException {
        boolean result = checkDelBindKey(uname,"email",email);
        assertTrue("删除邮箱绑定", result);
    }

    @Test
    public void testDelBindMobile() throws IOException, SAXException, NoSuchAlgorithmException, InterruptedException {
        boolean result = checkDelBindKey(uname,"email",email);
        assertTrue("删除手机绑定", result);
    }

    @Test
    public void testDelBindNickname() throws IOException, SAXException, NoSuchAlgorithmException, InterruptedException {
        boolean result = checkDelBindKey(uname,"email",email);
        assertTrue("删除昵称绑定", result);
    }

    @Test
    public void testDelBindIdcard() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkDelBindKey(uname,"email",email);
        assertTrue("删除身份证绑定", result);
    }

    @Test
    public void testDelBindNull() throws IOException, SAXException {
        String result = AccInterface.testDelUserbind("&uname=" + uname + "&keytp=");
        assertTrue("删除参数为空的绑定信息,msg: " + result , result.contains("result=101"));
    }

    public static boolean checkDelBindKey(String uname, String keytp, String key) throws IOException, SAXException, NoSuchAlgorithmException, InterruptedException {
        String response = AccInterface.testDelUserbind("&uname=" + uname + "&key=" + keytp);
        boolean checkresponse = MyCheckUtil.checkResponse(response,uname,keytp,"1");
        boolean checknotexist = MyCheckUtil.checkNotExist(uname,pass_md5_str,keytp,key);
        boolean checknotindex = MyCheckUtil.checkNotIndex(uname,keytp,key);
        boolean result = checkresponse && checknotexist && checknotindex;
        return result;
    }
}
