package com.gw.account.httptest;

import com.gw.account.utils.MyCheckUtil;
import com.gw.account.utils.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/3/27.
 */
public class CheckuserTest {
    private static User user = new User();

    @BeforeClass
    public static void globalInit() throws IOException, SAXException, InterruptedException {
        MyCheckUtil.initialize();
        user.createUser();
    }

    /**
     * 正确用户名作为参数验证用户
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCheckCorrectUser() throws IOException, SAXException {
        String response = AccInterface.testCheckuser("&uname=" + user.getUname());
        boolean result = response.contains("result=0") && MyCheckUtil.checkResponseSolo(response,"uname",user.getUname().toLowerCase());
        assertTrue("正确用户名作为参数验证用户",result);
    }

    /**
     * 错误用户名作为参数验证用户
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCheckWrongUser() throws IOException, SAXException {
        String response = AccInterface.testCheckuser("&uname=" + user.getUname() + "wrong");
        boolean result = response.contains("result=2");
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
        boolean result = response.contains("result=101");
        assertTrue("空用户名作为参数验证用户",result);
    }
}
