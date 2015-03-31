package com.gw.account.httptest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/3/27.
 */
public class CheckpassTest {
    private static final Log LOG = LogFactory.getLog(CheckpassTest.class);
    private static String uname;
    private static String email;
    private static String mobile;
    private static String lotterid;
    private static String deviceid;
    private static String pushid;
    private static String nlotterid;
    private static String truename;
    private static String nickname;
    private static String idcard;
    private static String qqid;
    private static String lcb;
    private static String wxid;
    //private static String bankcard;
    //private static String
    private static String xcid;
    private static String upass = "11111111";

    @BeforeClass
    public static void globalInit() throws IOException, SAXException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        uname = "Test" + "测试_" + number;
        email = "Test" + number + "@126.com";
        mobile = "188" + number;
        lotterid = "Lotter" + number;
        deviceid = "Device" + number;
        pushid = "Push" + number;
        nlotterid = "Nlotter" + number;
        truename = "True" + "测试" + number;
        nickname = "Nick" + "测试_" + number;
        idcard = String.format("%018d", Long.parseLong(number));
        qqid = "qq" + number;
        lcb = "lcb" + number;
        wxid = "wx" + number;
        xcid= "xc" + number;
        AccInterface.testAdduser("&uname=" + uname + "&upass=" + upass);
        bindKey(uname,"email",email);
        bindKey(uname,"mobile",mobile);
        bindKey(uname,"lotterid",lotterid);
        bindKey(uname,"deviceid",deviceid);
        bindKey(uname,"pushid",pushid);
        bindKey(uname,"nlotterid",nlotterid);
        bindKey(uname,"truename",truename);
        bindKey(uname,"nickname",nickname);
        bindKey(uname,"idcard",idcard);
        bindKey(uname,"qqid",qqid);
        bindKey(uname,"lcb",lcb);
        bindKey(uname,"wxid",wxid);
        bindKey(uname,"xcid",xcid);
    }

    public static void bindKey(String uname, String keytp, String key) throws IOException, SAXException {
        String keyencode = URLEncoder.encode(keytp + "=" + key, "UTF-8");
        AccInterface.testUserbind("&uname=" + uname + "&key=" + keyencode);
    }

    //=================================正确key和密码=======================================
    /**
     * 正确用户名和密码作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectUnameandUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + uname + "&passmd5=" + MyCheckUtil.encodePassword(upass);
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确用户名和密码作为参数，不返回用户名",result);
    }

    /**
     * 正确用户名和密码作为参数，返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectUnameandUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + uname + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = MyCheckUtil.checkResponseSolo(response, "uname", uname);
        assertTrue("正确用户名和密码作为参数，返回用户名",result);
    }

    /**
     * 正确邮箱和密码作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectEmailandUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + email + "&passmd5=" + MyCheckUtil.encodePassword(upass);
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确邮箱和密码作为参数，不返回用户名",result);
    }

    /**
     * 正确邮箱和密码作为参数，返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectEmailandUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + email + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = MyCheckUtil.checkResponse(response, uname, "email", email);
        assertTrue("正确邮箱和密码作为参数，返回用户名",result);
    }

    /**
     * 正确手机和密码作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectMobileandUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + mobile + "&passmd5=" + MyCheckUtil.encodePassword(upass);
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确手机和密码作为参数，不返回用户名",result);
    }

    /**
     * 正确手机和密码作为参数，返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectMobileandUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + mobile + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = MyCheckUtil.checkResponse(response, uname, "mobile", mobile);
        assertTrue("正确手机和密码作为参数，返回用户名",result);
    }

    /**
     * 正确彩票账户和密码作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectLotteridandUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + lotterid + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "&keytp=lotterid";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确彩票账户和密码作为参数，不返回用户名",result);
    }

    /**
     * 正确彩票账户和密码作为参数，返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectLotteridandUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + lotterid + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "keytp=lotterid&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = MyCheckUtil.checkResponse(response,uname,"lotterid",lotterid);
        assertTrue("正确彩票账户和密码作为参数，返回用户名",result);
    }

    /**
     * 正确昵称和密码作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectNicknameandUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + nickname + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "&keytp=nickname";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确昵称和密码作为参数，不返回用户名",result);
    }

    /**
     * 正确昵称和密码作为参数，返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectNicknameandUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + nickname + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "keytp=nickname&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = MyCheckUtil.checkResponse(response,uname,"nickname",nickname);
        assertTrue("正确昵称和密码作为参数，返回用户名",result);
    }

    /**
     * 正确身份证号码和密码作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectIdcardandUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + idcard + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "&keytp=idcard";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确身份证号码和密码作为参数，不返回用户名",result);
    }

    /**
     * 正确身份证号码和密码作为参数，返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectIdcardandUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + idcard + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "keytp=idcard&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = MyCheckUtil.checkResponse(response,uname,"idcard",idcard);
        assertTrue("正确身份证号码和密码作为参数，返回用户名",result);
    }

    //=================================错误key，正确密码=======================================
    /**
     * 错误用户名作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongUname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + uname + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(upass);
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("错误用户名作为参数，不返回用户名",result);
    }

    /**
     * 错误用户名作为参数，返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongUnameRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + uname + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("错误用户名作为参数，返回用户名",result);
    }

    /**
     * 错误邮箱作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongEmail() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + email + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(upass);
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("错误邮箱作为参数，不返回用户名",result);
    }

    /**
     * 错误邮箱作为参数，返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongEmailRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + email + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("错误邮箱作为参数，返回用户名",result);
    }

    /**
     * 错误手机作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongMobile() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + mobile + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(upass);
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("错误手机作为参数，不返回用户名",result);
    }

    /**
     * 错误手机作为参数，返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongMobileRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + mobile + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("错误手机作为参数，返回用户名",result);
    }

    /**
     * 错误彩票账户作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongLotterid() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + lotterid + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "&keytp=lotterid";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("错误彩票账户作为参数，不返回用户名",result);
    }

    /**
     * 错误彩票账户作为参数，返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongLotteridRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + lotterid + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "keytp=lotterid&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("错误彩票账户作为参数，返回用户名",result);
    }

    /**
     * 错误昵称作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongNickname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + nickname + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "&keytp=nickname";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("错误昵称作为参数，不返回用户名",result);
    }

    /**
     * 错误昵称作为参数，返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongNicknameRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + nickname + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "keytp=nickname&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("错误昵称作为参数，返回用户名",result);
    }

    /**
     * 错误身份证号码作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongIdcard() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + idcard + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "&keytp=idcard";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("错误身份证号码作为参数，不返回用户名",result);
    }

    /**
     * 错误身份证号码作为参数，返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongIdcardRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + idcard + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "keytp=idcard&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("错误身份证号码作为参数，返回用户名",result);
    }

    //=================================正确key，错误密码=======================================
    /**
     * 正确用户名错误密码作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testUnameWrongUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + uname + "&passmd5=" + MyCheckUtil.encodePassword(upass + "wrong");
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确用户名错误密码作为参数，不返回用户名",result);
    }

    /**
     * 正确用户名错误密码作为参数，返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testUnameWrongUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + uname + "&passmd5=" + MyCheckUtil.encodePassword(upass + "wrong") + "&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确用户名错误密码作为参数，返回用户名",result);
    }

    /**
     * 正确邮箱错误密码作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testEmailWrongUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + email + "&passmd5=" + MyCheckUtil.encodePassword(upass + "wrong");
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确邮箱错误密码作为参数，不返回用户名",result);
    }

    /**
     * 正确邮箱错误密码作为参数，返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testEmailWrongUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + email + "&passmd5=" + MyCheckUtil.encodePassword(upass + "wrong") + "&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确邮箱错误密码作为参数，返回用户名",result);
    }

    /**
     * 正确手机错误密码作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testMobileWrongUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + mobile + "&passmd5=" + MyCheckUtil.encodePassword(upass + "wrong");
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确手机错误密码作为参数，不返回用户名",result);
    }

    /**
     * 正确手机错误密码作为参数，返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testMobileWrongUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + mobile + "&passmd5=" + MyCheckUtil.encodePassword(upass + "wrong") + "&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确手机错误密码作为参数，返回用户名",result);
    }

    /**
     * 正确彩票账户错误密码作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testLotteridWrongUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + lotterid + "&passmd5=" + MyCheckUtil.encodePassword(upass + "wrong") + "&keytp=lotterid";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确彩票账户错误密码作为参数，不返回用户名",result);
    }

    /**
     * 正确彩票账户错误密码作为参数，返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testLotteridWrongUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + lotterid + "&passmd5=" + MyCheckUtil.encodePassword(upass + "wrong") + "keytp=lotterid&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确彩票账户错误密码作为参数，返回用户名",result);
    }

    /**
     * 正确昵称错误密码作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNicknameWrongUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + nickname + "&passmd5=" + MyCheckUtil.encodePassword(upass + "wrong") + "&keytp=nickname";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确昵称错误密码作为参数，不返回用户名",result);
    }

    /**
     * 正确昵称错误密码作为参数，返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNicknameWrongUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + nickname + "&passmd5=" + MyCheckUtil.encodePassword(upass + "wrong") + "keytp=nickname&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确昵称错误密码作为参数，返回用户名",result);
    }

    /**
     * 正确身份证号码错误密码作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testIdcardWrongUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + idcard + "&passmd5=" + MyCheckUtil.encodePassword(upass + "wrong") + "&keytp=idcard";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确身份证号码错误密码作为参数，不返回用户名",result);
    }

    /**
     * 正确身份证号码错误密码作为参数，返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testIdcardWrongUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + idcard + "&passmd5=" + MyCheckUtil.encodePassword(upass + "wrong") + "keytp=idcard&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确身份证号码错误密码作为参数，返回用户名",result);
    }

    //=================================空key，正确密码=======================================
    /**
     * 空用户名和正确密码作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNullUname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + "" + "&passmd5=" + MyCheckUtil.encodePassword(upass);
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("空用户名和正确密码作为参数，不返回用户名",result);
    }

    //=================================正确key，空密码=======================================
    /**
     * 正确用户名和空密码作为参数，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testUnameNullUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + uname + "&passmd5=" + "";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确用户名和空密码作为参数，不返回用户名",result);
    }

    //=================================使用其他用户绑定唯一key，未指定keytp=======================================
    /**
     * 正确彩票账户和密码作为参数，未指定keytp，不返回用户名
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testLotteridNullKeytp() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + lotterid + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确彩票账户和密码作为参数，未指定keytp，不返回用户名",result);
    }

    //=================================使用其他用户绑定唯一key，runame不为1=======================================
    /**
     * 正确彩票账户和密码作为参数，runame不为1
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testLotteridRunameNot1() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + lotterid + "&passmd5=" + MyCheckUtil.encodePassword(upass) + "keytp=lotterid&runame=2";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确彩票账户和密码作为参数，runame不为1",result);
    }
}
