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
        idcard_18_x = String.format("%017d",Long.parseLong(user.getNumber())) + "x";
        idcard_18_X = String.format("%017d",Long.parseLong(user.getNumber())) + "X";
        idcard_15 = String.format("%015d",Long.parseLong(user.getNumber()));
        idcard_15_x = String.format("%014d",Long.parseLong(user.getNumber())) + "x";
        idcard_15_X = String.format("%014d",Long.parseLong(user.getNumber())) + "X";
        MyCheckUtil.bindKey(user.getUname(),"email",user.getEmail());
        MyCheckUtil.bindKey(user.getUname(),"mobile",user.getMobile());
        MyCheckUtil.bindKey(user.getUname(),"truename",user.getTruename());
        MyCheckUtil.bindKey(user.getUname(),"nickname",user.getNickname());
    }

    /**
     * 查询绑定邮箱
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testGetBindEmail() throws IOException, SAXException {
        String response = AccInterface.testGetUserbind("&uname=" + user.getUname() + "&keytp=email");
        boolean result = MyCheckUtil.checkResponseNoResult(response, user.getUname(), "email", user.getEmail());
        assertTrue("查询绑定邮箱,msg: " + response , result);
    }

    /**
     * 查询绑定手机
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testGetBindMobile() throws IOException, SAXException {
        String response = AccInterface.testGetUserbind("&uname=" + user.getUname() + "&keytp=mobile");
        boolean result = MyCheckUtil.checkResponseNoResult(response, user.getUname(), "mobile", user.getMobile());
        assertTrue("查询绑定手机,msg: " + response , result);
    }

    /**
     * 查询绑定真名
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testGetBindTruename() throws IOException, SAXException {
        String response = AccInterface.testGetUserbind("&uname=" + user.getUname() + "&keytp=truename");
        boolean result = MyCheckUtil.checkResponseNoResult(response, user.getUname(), "truename", user.getTruename());
        assertTrue("查询绑定真名,msg: " + response , result);
    }

    /**
     * 查询绑定昵称
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testGetBindNickname() throws IOException, SAXException {
        String response = AccInterface.testGetUserbind("&uname=" + user.getUname() + "&keytp=nickname");
        boolean result = MyCheckUtil.checkResponseNoResult(response, user.getUname(), "nickname", user.getNickname());
        assertTrue("查询绑定昵称,msg: " + response , result);
    }

    public static void createUserBind(String idcard) throws IOException, SAXException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        unameforidcard = "Test" + "测试" + number;
        AccInterface.testAdduser("&uname=" + unameforidcard + "&upass=" + user.getUpass());
        MyCheckUtil.bindKey(unameforidcard,"idcard",idcard);
        sleep(1000);
    }

    /**
     * 查询绑定的18位身份证
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testGetBindIdcard18() throws IOException, SAXException, InterruptedException {
        createUserBind(user.getIdcard());
        String response = AccInterface.testGetUserbind("&uname=" + unameforidcard + "&keytp=idcard");
        boolean result = MyCheckUtil.checkResponseNoResult(response, unameforidcard, "idcard", user.getIdcard());
        assertTrue("查询绑定身份证,msg: " + response , result);
    }

    /**
     * 查询绑定的18位带x身份证
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testGetBindIdcard18x() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_18_x);
        String response = AccInterface.testGetUserbind("&uname=" + unameforidcard + "&keytp=idcard");
        boolean result = MyCheckUtil.checkResponseNoResult(response, unameforidcard, "idcard", idcard_18_x);
        assertTrue("查询绑定身份证,msg: " + response, result);
    }

    /**
     * 查询绑定的18位带X身份证
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testGetBindIdcard18X() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_18_X);
        String response = AccInterface.testGetUserbind("&uname=" + unameforidcard + "&keytp=idcard");
        boolean result = MyCheckUtil.checkResponseNoResult(response, unameforidcard, "idcard", idcard_18_X);
        assertTrue("查询绑定身份证,msg: " + response, result);
    }

    /**
     * 查询绑定的15位身份证
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testGetBindIdcard15() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_15);
        String response = AccInterface.testGetUserbind("&uname=" + unameforidcard + "&keytp=idcard");
        boolean result = MyCheckUtil.checkResponseNoResult(response, unameforidcard, "idcard", idcard_15);
        assertTrue("查询绑定身份证,msg: " + response, result);
    }

    /**
     * 查询绑定的15位带x身份证
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testGetBindIdcard15x() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_15_x);
        String response = AccInterface.testGetUserbind("&uname=" + unameforidcard + "&keytp=idcard");
        boolean result = MyCheckUtil.checkResponseNoResult(response, unameforidcard, "idcard", idcard_15_x);
        assertTrue("查询绑定身份证,msg: " + response, result);
    }

    /**
     * 查询绑定的15位带X身份证
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testGetBindIdcard15X() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_15_X);
        String response = AccInterface.testGetUserbind("&uname=" + unameforidcard + "&keytp=idcard");
        boolean result = MyCheckUtil.checkResponseNoResult(response, unameforidcard, "idcard", idcard_15_X);
        assertTrue("查询绑定身份证,msg: " + response, result);
    }

    /**
     * 查询keytp是空的绑定
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testGetBindNull() throws IOException, SAXException {      //不传递是可以的,表示就是uname，带keytp表示这个uname是个key
                                                                          //比如可以直接用mobile来进行查询
        String response = AccInterface.testGetUserbind("&uname=" + user.getUname() + "&keytp=");
        boolean result = MyCheckUtil.getCode(response)==1;
        assertTrue("查询参数为空的绑定信息,msg: " + response , result);
    }
}
