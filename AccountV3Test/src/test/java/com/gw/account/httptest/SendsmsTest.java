package com.gw.account.httptest;

import com.gw.account.utils.MyCheckUtil;
import com.gw.account.utils.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/3/27.
 */
public class SendsmsTest {
    private static final Log LOG = LogFactory.getLog(SendsmsTest.class);
    private static User user = new User();
    private static String clientIdcommon = "E5254CB2-826A-477E-B277-8B85571CFB10";
    private static String clientIdverifyCode = "0477C1E0-6812-4288-8AC0-458BFF8CDAD9";
    private static String nlotterid;
    private static String verifyCode;

    @BeforeClass
    public static void globalInit() {
        MyCheckUtil.initialize();
    }

    @Before
    public void setUp() throws IOException, SAXException, InterruptedException {
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
        verifyCode = "666666";
    }

    //=================================正确key发送短信，通用短信接口=======================================

    /**
     * 使用正确用户名发送短信，通用短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsUname() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdcommon + "&keyname=uname" + "&keyvalue=" + user.getUname() + "&message=abc");
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确用户名发送短信，通用短信接口", result);
    }

    /**
     * 使用正确手机号码发送短信，通用短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsMobile() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdcommon + "&keyname=mobile" + "&keyvalue=" + user.getMobile() + "&message=abc");
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确手机号码发送短信，通用短信接口", result);
    }

    /**
     * 使用正确邮箱发送短信，通用短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsEmail() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdcommon + "&keyname=email" + "&keyvalue=" + user.getEmail() + "&message=abc");
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确邮箱发送短信，通用短信接口", result);
    }

    /**
     * 使用正确lotterid发送短信，通用短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsLotterid() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdcommon + "&keyname=lotterid" + "&keyvalue=" + user.getLotterid() + "&message=abc");
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确lotterid发送短信，通用短信接口", result);
    }

//    /**
//     * 使用正确deviceid发送短信，通用短信接口
//     * @throws IOException
//     * @throws SAXException
//     */
//    @Test
//    public void sendsmsDeviceid() throws IOException, SAXException {
//        String response = AccInterface.testSendsms("&clientId=" + clientIdcommon + "&keyname=deviceid" + "&keyvalue=" + deviceid + "&message=abc");
//        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
//        assertTrue("使用正确deviceid发送短信，通用短信接口",result);
//    }

//    /**
//     * 使用正确pushid发送短信，通用短信接口
//     * @throws IOException
//     * @throws SAXException
//     */
//    @Test
//    public void sendsmsPushid() throws IOException, SAXException {
//        String response = AccInterface.testSendsms("&clientId=" + clientIdcommon + "&keyname=pushid" + "&keyvalue=" + pushid + "&message=abc");
//        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
//        assertTrue("使用正确pushid发送短信，通用短信接口",result);
//    }

//    /**
//     * 使用正确nlotterid发送短信，通用短信接口
//     * @throws IOException
//     * @throws SAXException
//     */
//    @Test
//    public void sendsmsNlotterid() throws IOException, SAXException {
//        AccInterface.testLotterbind("&uname=" + uname + "&nlotterid=" + nlotterid);
//        String response = AccInterface.testSendsms("&clientId=" + clientIdcommon + "&keyname=nlotterid" + "&keyvalue=" + nlotterid + "&message=abc");
//        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
//        assertTrue("使用正确nlotterid发送短信，通用短信接口",result);
//    }

    /**
     * 使用正确nickname发送短信，通用短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsNickname() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdcommon + "&keyname=nickname" + "&keyvalue=" + user.getNickname() + "&message=abc");
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确nickname发送短信，通用短信接口", result);
    }

    /**
     * 使用正确idcard发送短信，通用短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsIdcard() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdcommon + "&keyname=idcard" + "&keyvalue=" + user.getIdcard() + "&message=abc");
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确idcard发送短信，通用短信接口", result);
    }

    /**
     * 使用正确qqid发送短信，通用短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsQqid() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdcommon + "&keyname=qqid" + "&keyvalue=" + user.getQqid() + "&message=abc");
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确qqid发送短信，通用短信接口", result);
    }

    /**
     * 使用正确lcb发送短信，通用短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsLcb() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdcommon + "&keyname=lcb" + "&keyvalue=" + user.getLcb() + "&message=abc");
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确lcb发送短信，通用短信接口", result);
    }

    /**
     * 使用正确wxid发送短信，通用短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsWxid() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdcommon + "&keyname=wxid" + "&keyvalue=" + user.getWxid() + "&message=abc");
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确wxid发送短信，通用短信接口", result);
    }

    /**
     * 使用正确xcid发送短信，通用短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsXcid() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdcommon + "&keyname=xcid" + "&keyvalue=" + user.getXcid() + "&message=abc");
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确xcid发送短信，通用短信接口", result);
    }

    //=================================正确key发送短信，验证码短信接口=======================================

    /**
     * 使用正确用户名发送短信，验证码短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsUnameandverifyCode() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdverifyCode + "&keyname=uname" + "&keyvalue=" + user.getUname() + "&verifyCode=" + verifyCode);
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确用户名发送短信，验证码短信接口", result);
    }

    /**
     * 使用正确手机号码发送短信，验证码短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsMobileandverifyCode() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdverifyCode + "&keyname=mobile" + "&keyvalue=" + user.getMobile() + "&verifyCode=" + verifyCode);
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确手机号码发送短信，验证码短信接口", result);
    }

    /**
     * 使用正确邮箱发送短信，验证码短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsEmailandverifyCode() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdverifyCode + "&keyname=email" + "&keyvalue=" + user.getEmail() + "&verifyCode=" + verifyCode);
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确邮箱发送短信，验证码短信接口", result);
    }

    /**
     * 使用正确lotterid发送短信，验证码短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsLotteridandverifyCode() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdverifyCode + "&keyname=lotterid" + "&keyvalue=" + user.getLotterid() + "&verifyCode=" + verifyCode);
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确lotterid发送短信，验证码短信接口", result);
    }

//    /**
//     * 使用正确deviceid发送短信，验证码短信接口
//     * @throws IOException
//     * @throws SAXException
//     */
//    @Test
//    public void sendsmsDeviceidandverifyCode() throws IOException, SAXException {
//        String response = AccInterface.testSendsms("&clientId=" + clientIdverifyCode + "&keyname=deviceid" + "&keyvalue=" + deviceid + "&verifyCode=" + verifyCode);
//        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
//        assertTrue("使用正确deviceid发送短信，验证码短信接口",result);
//    }

//    /**
//     * 使用正确pushid发送短信，验证码短信接口
//     * @throws IOException
//     * @throws SAXException
//     */
//    @Test
//    public void sendsmsPushidandverifyCode() throws IOException, SAXException {
//        String response = AccInterface.testSendsms("&clientId=" + clientIdverifyCode + "&keyname=pushid" + "&keyvalue=" + pushid + "&verifyCode=" + verifyCode);
//        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
//        assertTrue("使用正确pushid发送短信，验证码短信接口",result);
//    }

//    /**
//     * 使用正确nlotterid发送短信，验证码短信接口
//     * @throws IOException
//     * @throws SAXException
//     */
//    @Test
//    public void sendsmsNlotteridandverifyCode() throws IOException, SAXException {
//        String response = AccInterface.testSendsms("&clientId=" + clientIdverifyCode + "&keyname=nlotterid" + "&keyvalue=" + nlotterid + "&verifyCode=" + verifyCode);
//        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
//        assertTrue("使用正确nlotterid发送短信，验证码短信接口",result);
//    }

    /**
     * 使用正确nickname发送短信，验证码短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsNicknameandverifyCode() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdverifyCode + "&keyname=nickname" + "&keyvalue=" + user.getNickname() + "&verifyCode=" + verifyCode);
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确nickname发送短信，验证码短信接口", result);
    }

    /**
     * 使用正确idcard发送短信，验证码短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsIdcardandverifyCode() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdverifyCode + "&keyname=idcard" + "&keyvalue=" + user.getIdcard() + "&verifyCode=" + verifyCode);
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确idcard发送短信，验证码短信接口", result);
    }

    /**
     * 使用正确qqid发送短信，验证码短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsQqidandverifyCode() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdverifyCode + "&keyname=qqid" + "&keyvalue=" + user.getQqid() + "&verifyCode=" + verifyCode);
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确qqid发送短信，验证码短信接口", result);
    }

    /**
     * 使用正确lcb发送短信，验证码短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsLcbandverifyCode() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdverifyCode + "&keyname=lcb" + "&keyvalue=" + user.getLcb() + "&verifyCode=" + verifyCode);
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确lcb发送短信，验证码短信接口", result);
    }

    /**
     * 使用正确wxid发送短信，验证码短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsWxidandverifyCode() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdverifyCode + "&keyname=wxid" + "&keyvalue=" + user.getWxid() + "&verifyCode=" + verifyCode);
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确wxid发送短信，验证码短信接口", result);
    }

    /**
     * 使用正确xcid发送短信，验证码短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsXcidandverifyCode() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdverifyCode + "&keyname=xcid" + "&keyvalue=" + user.getXcid() + "&verifyCode=" + verifyCode);
        boolean result = response.contains("result=0") && MyCheckUtil.getCode(response) > 0;
        assertTrue("使用正确xcid发送短信，验证码短信接口", result);
    }

    //=================================错误参数=======================================

    /**
     * 使用错误clientId发送短信，通用短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsWrongClientId() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdcommon + "wrong" + "&keyname=uname" + "&keyvalue=" + user.getUname() + "&message=abc");
        boolean result = response.contains("result=106") && response.contains("msg=data_error");
        assertTrue("使用错误clientId发送短信，通用短信接口", result);
    }

    /**
     * 使用错误用户名发送短信，通用短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsWrongUname() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdcommon + "&keyname=uname" + "&keyvalue=" + user.getUname() + "wrong" + "&message=abc");
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("使用错误用户名发送短信，通用短信接口", result);
    }

    /**
     * 验证码超过10字节
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsLongVerifyCode() throws IOException, SAXException {
        while (verifyCode.length() <= 10) {
            verifyCode += "t";
        }
        String response = AccInterface.testSendsms("&clientId=" + clientIdverifyCode + "&keyname=uname" + "&keyvalue=" + user.getUname() + "&verifyCode=" + verifyCode);
        boolean result = response.contains("result=106") && response.contains("msg=data_error");
        assertTrue("验证码超过10字节", result);
    }


    //=================================空参数=======================================

    /**
     * 使用空clientId发送短信，通用短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsNullClientId() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + "" + "&keyname=uname" + "&keyvalue=" + user.getUname() + "&message=abc");
        boolean result = response.contains("result=101") && response.contains("msg=clientId_bad");
        assertTrue("使用空clientId发送短信，通用短信接口", result);
    }

    /**
     * 使用空用户名发送短信，通用短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsNullUname() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdcommon + "&keyname=uname" + "&keyvalue=" + "" + "&message=abc");
        boolean result = response.contains("result=101") && response.contains("msg=keyvalue_bad");
        assertTrue("使用空用户名发送短信，通用短信接口", result);
    }

    /**
     * 使用空message发送短信，通用短信接口
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsNullMessage() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdcommon + "&keyname=uname" + "&keyvalue=" + user.getUname() + "&message=");
        boolean result = response.contains("result=106") && response.contains("msg=data_error");
        assertTrue("使用空message发送短信，通用短信接口", result);
    }

    /**
     * 验证码为空
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void sendsmsNullVerifyCode() throws IOException, SAXException {
        String response = AccInterface.testSendsms("&clientId=" + clientIdverifyCode + "&keyname=uname" + "&keyvalue=" + user.getUname() + "&verifyCode=" + "");
        boolean result = response.contains("result=106") && response.contains("msg=data_error");
        assertTrue("验证码为空", result);
    }
}
