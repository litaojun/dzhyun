package com.gw.account.httptest;

import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/3/27.
 */
public class CheckuserTest {
    private static String uname;

    @BeforeClass
    public void globalInit() throws IOException, SAXException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        uname = "Test" + "测试_" + number;
        AccInterface.testAdduser("&uname=" + uname);
    }

    /**
     * 正确用户名作为参数验证用户
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCheckCorrectUser() throws IOException, SAXException {
        String response = AccInterface.testCheckuser("&uname=" + uname);
        String getuname = MyCheckUtil.getValueFromResponse(response,"uname");
        boolean result = response.contains("result=0") && getuname.equals(uname.toLowerCase());
        assertTrue("正确用户名作为参数验证用户",result);
    }

    /**
     * 错误用户名作为参数验证用户
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCheckWrongUser() throws IOException, SAXException {
        String response = AccInterface.testCheckuser("&uname=wrong" + uname);
        boolean result = response.contains("result=114");
        assertTrue("错误用户名作为参数验证用户",result);
    }

    /**
     * 空用户名作为参数验证用户
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCheckNullUser() throws IOException, SAXException {
        String response = AccInterface.testCheckuser("&uname=" + "");
        boolean result = response.contains("result=114");
        assertTrue("空用户名作为参数验证用户",result);
    }
}
