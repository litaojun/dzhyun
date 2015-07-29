package com.gw.account.httptest;

import com.gw.account.utils.MyCheckUtil;
import com.gw.account.utils.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/3/27.
 */
public class CheckpassTest {
    private static final Log LOG = LogFactory.getLog(CheckpassTest.class);
    private static User user = new User();
    private static String nlotterid;

    @BeforeClass
    public static void globalInit() throws IOException, SAXException, InterruptedException {
        MyCheckUtil.initialize();
        user.createUser();
        MyCheckUtil.bindKey(user.getUname(), "email", user.getEmail());
        MyCheckUtil.bindKey(user.getUname(), "mobile", user.getMobile());
        String response = AccInterface.testLotterbind("&uname=" + user.getUname() + "&lotterid=" + user.getLotterid());
        MyCheckUtil.bindKey(user.getUname(), "deviceid", user.getDeviceid());
//        MyCheckUtil.bindKey(user.getUname(),"pushid",pushid); 单独不可以绑定
        nlotterid = MyCheckUtil.getValueFromResponse(response, "nlotterid");
        MyCheckUtil.bindKey(user.getUname(), "truename", user.getTruename());
        MyCheckUtil.bindKey(user.getUname(), "nickname", user.getNickname());
        MyCheckUtil.bindKey(user.getUname(), "idcard", user.getIdcard());
        MyCheckUtil.bindKey(user.getUname(), "qqid", user.getQqid());
        MyCheckUtil.bindKey(user.getUname(), "lcb", user.getLcb());
        MyCheckUtil.bindKey(user.getUname(), "wxid", user.getWxid());
        MyCheckUtil.bindKey(user.getUname(), "xcid", user.getXcid());
    }

    //=================================正确key和密码=======================================

    /**
     * 正确用户名和密码作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectUnameandUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getUname() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass());
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确用户名和密码作为参数，不返回用户名", result);
    }

    /**
     * 正确用户名和密码作为参数，返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectUnameandUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getUname() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0") && MyCheckUtil.checkResponseSolo(response, "uname", user.getUname().toLowerCase());
        assertTrue("正确用户名和密码作为参数，返回用户名", result);
    }

    /**
     * 正确邮箱和密码作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectEmailandUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getEmail() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass());
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确邮箱和密码作为参数，不返回用户名", result);
    }

    /**
     * 正确邮箱和密码作为参数，返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectEmailandUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getEmail() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = MyCheckUtil.checkResponseSolo(response, "uname", user.getUname().toLowerCase());
        assertTrue("正确邮箱和密码作为参数，返回用户名", result);
    }

    /**
     * 正确手机和密码作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectMobileandUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getMobile() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass());
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确手机和密码作为参数，不返回用户名", result);
    }

    /**
     * 正确手机和密码作为参数，返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectMobileandUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getMobile() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = MyCheckUtil.checkResponseSolo(response, "uname", user.getUname().toLowerCase());
        assertTrue("正确手机和密码作为参数，返回用户名", result);
    }

    /**
     * 正确彩票账户和密码作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectLotteridandUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getLotterid() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "&keytp=lotterid";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确彩票账户和密码作为参数，不返回用户名", result);
    }

    /**
     * 正确彩票账户和密码作为参数，返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectLotteridandUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getLotterid() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "&keytp=lotterid&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确彩票账户和密码作为参数，返回用户名", result);
    }

    /**
     * 正确昵称和密码作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectNicknameandUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getNickname() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "&keytp=nickname";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确昵称和密码作为参数，不返回用户名", result);
    }

    /**
     * 正确昵称和密码作为参数，返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectNicknameandUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getNickname() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "&keytp=nickname&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0") && MyCheckUtil.checkResponseSolo(response, "uname", user.getUname().toLowerCase());
        assertTrue("正确昵称和密码作为参数，返回用户名", result);
    }

    /**
     * 正确身份证号码和密码作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectIdcardandUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getIdcard() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "&keytp=idcard";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0");
        assertTrue("正确身份证号码和密码作为参数，不返回用户名", result);
    }

    /**
     * 正确身份证号码和密码作为参数，返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testCorrectIdcardandUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getIdcard() + "&passmd5=" + MyCheckUtil.encodeMD5(user.getUpass()) + "&keytp=idcard&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0") && MyCheckUtil.checkResponseSolo(response, "uname", user.getUname().toLowerCase());
        assertTrue("正确身份证号码和密码作为参数，返回用户名", result);
    }

    //=================================错误key，正确密码=======================================

    /**
     * 错误用户名作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongUname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getUname() + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass());
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("错误用户名作为参数，不返回用户名", result);
    }

    /**
     * 错误用户名作为参数，返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongUnameRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getUname() + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("错误用户名作为参数，返回用户名", result);
    }

    /**
     * 错误邮箱作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongEmail() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getEmail() + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass());
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("错误邮箱作为参数，不返回用户名", result);
    }

    /**
     * 错误邮箱作为参数，返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongEmailRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getEmail() + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("错误邮箱作为参数，返回用户名", result);
    }

    /**
     * 错误手机作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongMobile() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getMobile() + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass());
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("错误手机作为参数，不返回用户名", result);
    }

    /**
     * 错误手机作为参数，返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongMobileRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getMobile() + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("错误手机作为参数，返回用户名", result);
    }

    /**
     * 错误彩票账户作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongLotterid() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getLotterid() + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "&keytp=lotterid";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("错误彩票账户作为参数，不返回用户名", result);
    }

    /**
     * 错误彩票账户作为参数，返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongLotteridRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getLotterid() + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "keytp=lotterid&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("错误彩票账户作为参数，返回用户名", result);
    }

    /**
     * 错误昵称作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongNickname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getNickname() + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "&keytp=nickname";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("错误昵称作为参数，不返回用户名", result);
    }

    /**
     * 错误昵称作为参数，返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongNicknameRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getNickname() + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "keytp=nickname&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("错误昵称作为参数，返回用户名", result);
    }

    /**
     * 错误身份证号码作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongIdcard() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getIdcard() + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "&keytp=idcard";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("错误身份证号码作为参数，不返回用户名", result);
    }

    /**
     * 错误身份证号码作为参数，返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongIdcardRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getIdcard() + "wrong" + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "keytp=idcard&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("错误身份证号码作为参数，返回用户名", result);
    }

    //=================================正确key，错误密码=======================================

    /**
     * 正确用户名错误密码作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testUnameWrongUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getUname() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass() + "wrong");
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=59") && response.contains("msg=password_error");
        assertTrue("正确用户名错误密码作为参数，不返回用户名", result);
    }

    /**
     * 正确用户名错误密码作为参数，返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testUnameWrongUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getUname() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass() + "wrong") + "&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=59") && response.contains("msg=password_error");
        assertTrue("正确用户名错误密码作为参数，返回用户名", result);
    }

    /**
     * 正确邮箱错误密码作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testEmailWrongUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getEmail() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass() + "wrong");
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("正确邮箱错误密码作为参数，不返回用户名", result);
    }

    /**
     * 正确邮箱错误密码作为参数，返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testEmailWrongUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getEmail() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass() + "wrong") + "&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("正确邮箱错误密码作为参数，返回用户名", result);
    }

    /**
     * 正确手机错误密码作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testMobileWrongUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getMobile() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass() + "wrong");
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("正确手机错误密码作为参数，不返回用户名", result);
    }

    /**
     * 正确手机错误密码作为参数，返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testMobileWrongUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getMobile() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass() + "wrong") + "&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("正确手机错误密码作为参数，返回用户名", result);
    }

    /**
     * 正确彩票账户错误密码作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testLotteridWrongUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getLotterid() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass() + "wrong") + "&keytp=lotterid";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=59") && response.contains("msg=password_error");
        assertTrue("正确彩票账户错误密码作为参数，不返回用户名", result);
    }

    /**
     * 正确彩票账户错误密码作为参数，返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testLotteridWrongUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getLotterid() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass() + "wrong") + "keytp=lotterid&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("正确彩票账户错误密码作为参数，返回用户名", result);
    }

    /**
     * 正确昵称错误密码作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNicknameWrongUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getNickname() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass() + "wrong") + "&keytp=nickname";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=59") && response.contains("msg=password_error");
        assertTrue("正确昵称错误密码作为参数，不返回用户名", result);
    }

    /**
     * 正确昵称错误密码作为参数，返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNicknameWrongUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getNickname() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass() + "wrong") + "keytp=nickname&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("正确昵称错误密码作为参数，返回用户名", result);
    }

    /**
     * 正确身份证号码错误密码作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testIdcardWrongUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getIdcard() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass() + "wrong") + "&keytp=idcard";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=59") && response.contains("msg=password_error");
        assertTrue("正确身份证号码错误密码作为参数，不返回用户名", result);
    }

    /**
     * 正确身份证号码错误密码作为参数，返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testIdcardWrongUpassRuname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getIdcard() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass() + "wrong") + "keytp=idcard&runame=1";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("正确身份证号码错误密码作为参数，返回用户名", result);
    }

    //=================================空key，正确密码=======================================

    /**
     * 空用户名和正确密码作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNullUname() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + "" + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass());
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=101") && response.contains("msg=uname_bad");
        assertTrue("空用户名和正确密码作为参数，不返回用户名", result);
    }

    //=================================正确key，空密码=======================================

    /**
     * 正确用户名和空密码作为参数，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testUnameNullUpass() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getUname() + "&passmd5=" + "";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=101") && response.contains("msg=passmd5_bad");
        assertTrue("正确用户名和空密码作为参数，不返回用户名", result);
    }

    //=================================使用其他用户绑定唯一key，未指定keytp=======================================

    /**
     * 正确彩票账户和密码作为参数，未指定keytp，不返回用户名
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testLotteridNullKeytp() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getLotterid() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("正确彩票账户和密码作为参数，未指定keytp，不返回用户名", result);
    }

    //=================================使用其他用户绑定唯一key，runame不为1=======================================

    /**
     * 正确彩票账户和密码作为参数，runame不为1，为其他值
     *
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testLotteridRunameNot1() throws NoSuchAlgorithmException, IOException, SAXException {
        String params = "&uname=" + user.getLotterid() + "&passmd5=" + MyCheckUtil.encodePassword(user.getUpass()) + "&keytp=lotterid&runame=2";
        String response = AccInterface.testCheckpass(params);
        boolean result = response.contains("result=0") && !response.contains("uname");
        assertTrue("正确彩票账户和密码作为参数，runame不为1，为其他值", result);
    }
}
