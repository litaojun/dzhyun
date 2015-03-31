package com.gw.account.httptest;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/3/30.
 */
public class XcscbindTest {
    private static String uname;
    private static String usertid;
    private static String mobile;
    private static String xcid;
    private static String truename;
    private static String idcard;
    private static String opendate;
    private static String opentype;
    private static String source;

    @Before
    public  void setUp() throws IOException, SAXException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        uname = "Test" + "测试_" + number;
        mobile = "188" + number;
        String response = AccInterface.testAdduser("uname=" + uname);
        usertid = MyCheckUtil.getValueFromResponse(response,"usertid");
    }

    /**
     * 只填手机号注册
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testMobile() throws IOException, SAXException {
        JSONObject jsonrequest = new JSONObject();
        jsonrequest.put("mobile",mobile);
        String stringresponse = AccInterface.testXcscbind(jsonrequest.toJSONString());
        JSONObject jsonreponse = JSONObject.parseObject(stringresponse);
        boolean result = jsonreponse.get("result").equals("0") && jsonreponse.get("mobile").equals(mobile);
        assertTrue("只填手机号注册: " + stringresponse , result);
    }
}
