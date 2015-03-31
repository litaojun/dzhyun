package com.gw.account.httptest;

import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/3/26.
 */
public class Adduser365Test {
    private static final Log LOG = LogFactory.getLog(Adduser365Test.class);
    private String mobile;
    private String pass_md5_str = "11111111";

    @BeforeClass
    public static void globalInit() {
        MyCheckUtil.initialize();
    }

    @Before
    public void setUp() throws InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        mobile = "188" + number;
        sleep(1000);
    }

    public boolean checkBase(String response, String upass) throws InvalidProtocolBufferException, NoSuchAlgorithmException {
        String getuname = MyCheckUtil.getValueFromResponse(response, "uname");
        String getupass = MyCheckUtil.getValueFromResponse(response, "upass");
        String getusertid = MyCheckUtil.getValueFromResponse(response, "usertid");
        boolean resultbd1 = MyCheckUtil.checkExist(getuname, getupass,"mobile", mobile);
        boolean resultdb2 = MyCheckUtil.checkUid(getuname, getusertid);
        if (upass != "") {
            return response.contains("result=0") && resultbd1 && resultdb2 && getupass.equals(upass);
        } else {
            return response.contains("result=0") && resultbd1 && resultdb2;
        }
    }

    /**
     * 正确手机号作为参数
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCorrectMobile() throws IOException, SAXException, NoSuchAlgorithmException {
        String response = AccInterface.testAdduser365("&mobile=" + mobile);
        boolean result = checkBase(response, "");
        assertTrue("正确手机号作为参数注册",result);
    }

    /**
     * 正确手机号和密码作为参数
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCorrectMobileandUpass() throws IOException, SAXException, NoSuchAlgorithmException {
        String response = AccInterface.testAdduser365("&mobile=" + mobile + "&upass=" + pass_md5_str);
        boolean result = checkBase(response, pass_md5_str);
        assertTrue("正确手机号和密码作为参数注册",result);
    }

    /**
     * 手机号超过15字节
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testLongMobile() throws IOException, SAXException, NoSuchAlgorithmException {
        mobile = mobile + "11111";
        String response = AccInterface.testAdduser365("&mobile=" + mobile);
        boolean result = response.contains("result=114");
        assertTrue("手机号超过15字节注册", result);
    }

    /**
     * 密码超过50字节
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testLongUpass() throws IOException, SAXException, NoSuchAlgorithmException {
        StringBuffer longupass = new StringBuffer();
        for (int i=0; i<=51; i++) {
            longupass.append("1");
        }
        String response = AccInterface.testAdduser365("&mobile=" + mobile + "&upass=" + longupass.toString());
        boolean result = response.contains("result=114");
        assertTrue("密码超过50字节注册", result);
    }

    /**
     * 手机号为空
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testNullMobile() throws IOException, SAXException, NoSuchAlgorithmException {
        String response = AccInterface.testAdduser365("&mobile=");
        boolean result = response.contains("result=114");
        assertTrue("手机号为空注册",result);
    }
}
