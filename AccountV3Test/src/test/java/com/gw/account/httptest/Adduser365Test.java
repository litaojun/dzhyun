package com.gw.account.httptest;

import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.account.utils.MyCheckUtil;
import com.gw.account.utils.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/3/26.
 */
public class Adduser365Test {
    private static final Log LOG = LogFactory.getLog(Adduser365Test.class);
    User user = new User();

    @BeforeClass
    public static void globalInit() {
        MyCheckUtil.initialize();
    }

    @Before
    public void setUp() throws InterruptedException, IOException, SAXException {
        user.createUser();
    }

    //=================================正确注册=======================================
    /**
     * 无参数
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCorrect() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = checkALL("");
        assertTrue("无参数",result);
    }

    /**
     * 正确手机号作为参数
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testCorrectMobile() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = checkALL("&mobile=" + user.getMobile());
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
        boolean result = checkALL("&mobile=" + user.getMobile() + "&upass=" + user.getUpass());
        assertTrue("正确手机号和密码作为参数注册",result);
    }

    /**
     * 验证返回值里的基本信息在库中是否存在
     * @param params
     * @return
     * @throws InvalidProtocolBufferException
     * @throws NoSuchAlgorithmException
     */
    public boolean checkALL(String params) throws IOException, NoSuchAlgorithmException, SAXException {
        String response = AccInterface.testAdduser365(params);
        boolean checkcode = MyCheckUtil.getCode(response) == 1;
        boolean checkresult = response.contains("result=0");
        String getusertid = MyCheckUtil.getValueFromResponse(response,"usertid");
        String getuname = MyCheckUtil.getValueFromResponse(response, "uname");
        boolean checkuname = getuname.matches("lvt" + "\\d{6}");
        String getupass = MyCheckUtil.getValueFromResponse(response,"upass");
        boolean checkupass = true;
        if (params.contains("upass") && !MyCheckUtil.getValueFromResponse(params,"upass").equals("")) {
            checkupass = getupass.equals(user.getUpass());
        } else {
            checkupass = getupass.matches("\\d{6}");
        }
        boolean udb = MyCheckUtil.checkU(getusertid, getupass);
        boolean ukeyunamedb = MyCheckUtil.checkUkey(getusertid, "v3name", getuname);
        boolean indexunamedb = MyCheckUtil.checkIndex(getusertid,"v3name",getuname);
        boolean ukeymobiledb = true;
        boolean indexmobiledb = true;
        if (params.contains("mobile") && !MyCheckUtil.getValueFromResponse(params,"mobile").equals("")) {
            ukeymobiledb = MyCheckUtil.checkUkey(getusertid, "mobile", user.getMobile());
            indexmobiledb = MyCheckUtil.checkIndex(getusertid, "mobile", user.getMobile());
        }

        boolean result = checkcode && checkresult && checkuname && checkupass && udb && ukeyunamedb && indexunamedb
                && ukeymobiledb && indexmobiledb;
        return result;
    }

    //=================================错误注册=======================================
    /**
     * 手机号超过15字节
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testLongMobile() throws IOException, SAXException, NoSuchAlgorithmException {
        String longmobile = user.getMobile();
        while (user.getMobile().length() <= 15) {
            longmobile += "1";
        }
        String response = AccInterface.testAdduser365("&mobile=" + longmobile);
        boolean result = response.contains("result=104") && response.contains("msg=mobile_error");
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
        String longupass = user.getUpass();
        while (user.getUpass().length() <= 50) {
            longupass += "t";
        }
        String response = AccInterface.testAdduser365("&mobile=" + user.getMobile() + "&upass=" + longupass);
        boolean result = response.contains("result=101") && response.contains("msg=upass_bad");
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
        boolean result = checkALL("&mobile=" + "");
        assertTrue("手机号为空注册",result);
    }

    /**
     * 密码为空
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testNullUpass() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = checkALL("&mobile=" + user.getMobile() + "&upass=" + "");
        assertTrue("密码为空", result);
    }
}
