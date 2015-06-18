package com.gw.account.httptestV3;

import com.alibaba.fastjson.JSONObject;
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
 * Created by Hihiri on 2015/3/30.
 */
public class XcscbindTest {
    private static final Log LOG = LogFactory.getLog(XcscbindTest.class);
    private static User user = new User();

    @BeforeClass
    public static void globalInit() {
        MyCheckUtil.initialize();
    }

    /**
     * 新建用户，获取usertid，绑定手机号
     *
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Before
    public void setUp() throws IOException, SAXException, InterruptedException {
        user.createUser();
        MyCheckUtil.bindKey(user.getUname(), "mobile", user.getMobile());
    }

    //=================================不绑定，只转发到V2=======================================

    /**
     * 只填手机号注册,如果uname为空的时候则不进行绑定，只将请求传递到v2系统
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testMobile() throws IOException, SAXException {
        JSONObject jsonrequest = new JSONObject();
        jsonrequest.put("mobile", user.getMobile());
        String stringresponse = AccInterface.testXcscbind(jsonrequest.toJSONString());
        boolean result = MyCheckUtil.checkJsonResponseSolo(stringresponse, "result", "0")
                && MyCheckUtil.checkJsonResponseSolo(stringresponse, "mobile", user.getMobile());
        assertTrue("只填手机号注册", result);
    }

    //=================================正常绑定=======================================

    /**
     * 填写用户名、手机和xcid注册
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testUnameXcid() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = checkALL("xcid", user.getXcid());
        assertTrue("填写用户名、手机和xcid注册", result);
    }

//    /**
//     * 填写用户名、手机和truename注册
//     * @throws IOException
//     * @throws SAXException
//     */
//    @Test
//    public void testUnameTruename() throws IOException, SAXException, NoSuchAlgorithmException {
//        boolean result = checkALLNotKey("truename", truename);
//        assertTrue("填写用户名、手机和truename注册",result);
//    }
//
//    /**
//     * 填写用户名、手机和idcard注册
//     * @throws IOException
//     * @throws SAXException
//     */
//    @Test
//    public void testUnameIdcard() throws IOException, SAXException, NoSuchAlgorithmException {
//        boolean result = checkALL("idcard", idcard);
//        assertTrue("填写用户名、手机和idcard注册",result);
//    }
//
//    /**
//     * 填写用户名、手机和opendate注册
//     * @throws IOException
//     * @throws SAXException
//     */
//    @Test
//    public void testUnameOpendate() throws IOException, SAXException, NoSuchAlgorithmException {
//        boolean result = checkALLNotKey("opendate", opendate);
//        assertTrue("填写用户名、手机和opendate注册",result);
//    }
//
//    /**
//     * 填写用户名、手机和opentype注册
//     * @throws IOException
//     * @throws SAXException
//     */
//    @Test
//    public void testUnameOpentype() throws IOException, SAXException, NoSuchAlgorithmException {
//        boolean result = checkALLNotKey("opentype", opentype);
//        assertTrue("填写用户名、手机和opentype注册",result);
//    }
//
//    /**
//     * 填写用户名、手机和source注册
//     * @throws IOException
//     * @throws SAXException
//     */
//    @Test
//    public void testUnameSource() throws IOException, SAXException, NoSuchAlgorithmException {
//        boolean result = checkALLNotKey("source", source);
//        assertTrue("填写用户名、手机和source注册",result);
//    }
    //=================================错误绑定=======================================

    /**
     * 绑定超过50字节的xcid
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testLongXcid() throws IOException, SAXException, NoSuchAlgorithmException {
        String longxcid = user.getXcid();
        while (longxcid.length() <= 50) {
            longxcid += "t";
        }
        String stringresponse = Xcscbind("xcid", longxcid);
        String getretcode = MyCheckUtil.getValueFromJsonResponse(stringresponse, "retcode");
        boolean result = getretcode.equals("1");
        assertTrue("绑定超过50字节的xcid", result);
    }

    //=================================工具方法=======================================

    /**
     * 绑定用户名、手机号和key，并验证返回信息
     *
     * @param keytp
     * @param key
     * @return
     * @throws IOException
     * @throws SAXException
     */
    public static String Xcscbind(String keytp, String key) throws IOException, SAXException {
        JSONObject jsonrequest = new JSONObject();
        jsonrequest.put("uname", user.getUname());
        jsonrequest.put("mobile", user.getMobile());
        jsonrequest.put(keytp, key);
        String stringresponse = AccInterface.testXcscbind(jsonrequest.toJSONString());
        return stringresponse;
    }

    /**
     * 绑定用户名、手机号和key，验证返回信息，验证
     *
     * @param keytp
     * @param key
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    public static boolean checkALL(String keytp, String key) throws IOException, SAXException, NoSuchAlgorithmException {
        String stringresponse = Xcscbind(keytp, key);
        boolean checkuserbind = MyCheckUtil.checkJsonResponseSolo(stringresponse, "result", "0")
                && MyCheckUtil.checkJsonResponseSolo(stringresponse, "uname", user.getUname())
                && MyCheckUtil.checkJsonResponseSolo(stringresponse, keytp, key)
                && MyCheckUtil.checkJsonResponseSolo(stringresponse, "mobile", user.getMobile());
        boolean checku = MyCheckUtil.checkU(user.getUsertid().toLowerCase(), user.getUpass());
        boolean checkukey1 = MyCheckUtil.checkUkey(user.getUsertid().toLowerCase(), "v3name", user.getUname().toLowerCase());
        boolean checkindex1 = MyCheckUtil.checkIndex(user.getUsertid().toLowerCase(), "v3name", user.getUname().toLowerCase());
        boolean checkukey2 = MyCheckUtil.checkUkey(user.getUsertid().toLowerCase(), "mobile", user.getMobile());
        boolean checkindex2 = MyCheckUtil.checkIndex(user.getUsertid().toLowerCase(), "mobile", user.getMobile());
        boolean checkukey3 = MyCheckUtil.checkUkey(user.getUsertid().toLowerCase(), keytp, key);
        boolean checkindex3 = MyCheckUtil.checkIndex(user.getUsertid().toLowerCase(), keytp, key);
        return checkuserbind && checku && checkukey1 && checkindex1 && checkukey2 && checkindex2 && checkukey3 && checkindex3;
    }

    public static boolean checkALLNotKey(String keytp, String key) throws IOException, SAXException, NoSuchAlgorithmException {
        String stringresponse = Xcscbind(keytp, key);
        boolean checkuserbind = MyCheckUtil.checkJsonResponseSolo(stringresponse, "result", "0")
                && MyCheckUtil.checkJsonResponseSolo(stringresponse, "uname", user.getUname())
                && MyCheckUtil.checkJsonResponseSolo(stringresponse, keytp, key)
                && MyCheckUtil.checkJsonResponseSolo(stringresponse, "mobile", user.getMobile());
        boolean checku1 = MyCheckUtil.checkU(user.getUsertid().toLowerCase(), user.getUpass());
        boolean checkukey1 = MyCheckUtil.checkUkey(user.getUsertid().toLowerCase(), "v3name", user.getUname().toLowerCase());
        boolean checkindex1 = MyCheckUtil.checkIndex(user.getUsertid().toLowerCase(), "v3name", user.getUname().toLowerCase());
        boolean checkukey2 = MyCheckUtil.checkUkey(user.getUsertid().toLowerCase(), "mobile", user.getMobile());
        boolean checkindex2 = MyCheckUtil.checkIndex(user.getUsertid().toLowerCase(), "mobile", user.getMobile());
        boolean checkukey3 = MyCheckUtil.checkUkey(user.getUsertid().toLowerCase(), keytp, key);
        return checkuserbind && checku1 && checkukey1 && checkindex1 && checkukey2 && checkindex2 && checkukey3;
    }
}
