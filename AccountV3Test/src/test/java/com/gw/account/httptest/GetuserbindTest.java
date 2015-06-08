package com.gw.account.httptest;

import com.gw.account.utils.MyCheckUtil;
import com.gw.account.utils.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/3/6.
 */
public class GetuserbindTest {
    private static final Log LOG = LogFactory.getLog(GetuserbindTest.class);
    private static User user = new User();
    private static String unameforidcard;
    private static String idcard_18_x;
    private static String idcard_18_X;
    private static String idcard_15;
    private static String idcard_15_x;
    private static String idcard_15_X;

    @BeforeClass
    public static void globalInit() throws IOException, SAXException, InterruptedException {
        MyCheckUtil.initialize();
        user.createUser();
        idcard_18_x = String.format("%017d", Long.parseLong(user.getNumber())) + "x";
        idcard_18_X = String.format("%017d", Long.parseLong(user.getNumber())) + "X";
        idcard_15 = String.format("%015d", Long.parseLong(user.getNumber()));
        idcard_15_x = String.format("%014d", Long.parseLong(user.getNumber())) + "x";
        idcard_15_X = String.format("%014d", Long.parseLong(user.getNumber())) + "X";
        MyCheckUtil.bindKey(user.getUname(), "email", user.getEmail());
        MyCheckUtil.bindKey(user.getUname(), "mobile", user.getMobile());
        MyCheckUtil.bindKey(user.getUname(), "truename", user.getTruename());
        MyCheckUtil.bindKey(user.getUname(), "nickname", user.getNickname());
    }

    /**
     * 查询绑定邮箱
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testGetBindEmail() throws IOException, SAXException {
        boolean result = checkGetUserbind(user.getUname(), "email", user.getEmail());
        assertTrue("查询绑定邮箱", result);
    }

    /**
     * 查询绑定手机
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testGetBindMobile() throws IOException, SAXException {
        boolean result = checkGetUserbind(user.getUname(), "mobile", user.getMobile());
        assertTrue("查询绑定手机", result);
    }

    /**
     * 查询绑定真名
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testGetBindTruename() throws IOException, SAXException {
        boolean result = checkGetUserbind(user.getUname(), "truename", user.getTruename());
        assertTrue("查询绑定真名", result);
    }

    /**
     * 查询绑定昵称
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testGetBindNickname() throws IOException, SAXException {
        boolean result = checkGetUserbind(user.getUname(), "nickname", user.getNickname());
        assertTrue("查询绑定昵称", result);
    }

    public static void createUserBind(String idcard) throws IOException, SAXException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        unameforidcard = "Test" + "测试" + number;
        AccInterface.testAdduser("&uname=" + unameforidcard + "&upass=" + user.getUpass());
        MyCheckUtil.bindKey(unameforidcard, "idcard", idcard);
        sleep(1000);
    }

    /**
     * 查询绑定的18位身份证
     *
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testGetBindIdcard18() throws IOException, SAXException, InterruptedException {
        createUserBind(user.getIdcard());
        boolean result = checkGetUserbind(unameforidcard, "idcard", user.getIdcard());
        assertTrue("查询绑定身份证", result);
    }

    /**
     * 查询绑定的18位带x身份证
     *
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testGetBindIdcard18x() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_18_x);
        boolean result = checkGetUserbind(unameforidcard, "idcard", idcard_18_x);
        assertTrue("查询绑定身份证", result);
    }

    /**
     * 查询绑定的18位带X身份证
     *
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testGetBindIdcard18X() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_18_X);
        boolean result = checkGetUserbind(unameforidcard, "idcard", idcard_18_X);
        assertTrue("查询绑定身份证", result);
    }

    /**
     * 查询绑定的15位身份证
     *
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testGetBindIdcard15() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_15);
        boolean result = checkGetUserbind(unameforidcard, "idcard", idcard_15);
        assertTrue("查询绑定身份证", result);
    }

    /**
     * 查询绑定的15位带x身份证
     *
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testGetBindIdcard15x() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_15_x);
        boolean result = checkGetUserbind(unameforidcard, "idcard", idcard_15_x);
        assertTrue("查询绑定身份证", result);
    }

    /**
     * 查询绑定的15位带X身份证
     *
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testGetBindIdcard15X() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_15_X);
        boolean result = checkGetUserbind(unameforidcard, "idcard", idcard_15_X);
        assertTrue("查询绑定身份证", result);
    }

    /**
     * 查询keytp是空的绑定
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testGetBindNull() throws IOException, SAXException {      //不传递是可以的,表示就是uname，带keytp表示这个uname是个key
        //比如可以直接用mobile来进行查询
        String response = AccInterface.testGetUserbind("&uname=" + user.getUname() + "&keytp=");
        boolean result = MyCheckUtil.getCode(response) == 1;
        assertTrue("查询参数为空的绑定信息,msg: " + response, result);
    }

    //=================================工具方法=======================================
    /**
     * 验证getUserbind的返回
     *
     * @param uname
     * @param keytp
     * @param key
     * @return
     * @throws IOException
     * @throws SAXException
     */
    public static boolean checkGetUserbind(String uname, String keytp, String key) throws IOException, SAXException {
        String response = AccInterface.testGetUserbind("&uname=" + uname + "&keytp=" + keytp);
        boolean result = MyCheckUtil.getCode(response) == 1 && MyCheckUtil.checkResponseSolo(response, "uname", uname) && MyCheckUtil.checkResponseSolo(response, keytp, key);
        if (!result) {
            LOG.error("checkGetUserbind:(" + uname + "," + keytp + "," + key + ")," + response);
        }
        return result;
    }
}
