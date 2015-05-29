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
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/4/5.
 */
public class UpdpassTestNew {
    private static final Log LOG = LogFactory.getLog(UpdpassTestNew.class);
    private User user = new User();
    private String upass = "22222222";

    @BeforeClass
    public static void globalInit() {
        MyCheckUtil.initialize();
    }

    @Before
    public void setUp() throws IOException, SAXException, InterruptedException {
        user.createUser();
    }

    //=================================正常修改=======================================
    /**
     * 用户名修改密码
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUname() throws IOException, SAXException, NoSuchAlgorithmException {
        String response = AccInterface.testUpdpassNew("&uname=" + user.getUname() + "&upass=" + upass);
        boolean result = checkALL(response);
        assertTrue("用户名修改密码",result);
    }

    /**
     * 用户名修改密码,MD5
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUnameMD5() throws IOException, SAXException, NoSuchAlgorithmException {
        String response = AccInterface.testUpdpassNew("&uname=" + user.getUname() + "&upass=" + MyCheckUtil.encodePassword(upass));
        boolean result = checkALL(response);
        assertTrue("用户名修改密码,MD5",result);
    }

    /**
     * 用户名修改密码，加原密码
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUnameOpass() throws IOException, SAXException, NoSuchAlgorithmException {
        String response = AccInterface.testUpdpassNew("&uname=" + user.getUname() + "&upass=" + upass + "&opass=" + user.getUpass());
        boolean result = checkALL(response);
        assertTrue("用户名修改密码，加原密码",result);
    }

    /**
     * 用户名修改密码,加原密码，MD5
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUnameOpassMD5() throws IOException, SAXException, NoSuchAlgorithmException {
        String response = AccInterface.testUpdpassNew("&uname=" + user.getUname() + "&upass=" + MyCheckUtil.encodePassword(upass)
                + "&opass=" + MyCheckUtil.encodePassword(URLDecoder.decode(user.getUpass(),"UTF-8")));
        boolean result = checkALL(response);
        assertTrue("用户名修改密码,加原密码，MD5",result);
    }

    /**
     * 测试email修改密码
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testEmail() throws IOException, SAXException, NoSuchAlgorithmException {
        MyCheckUtil.bindKey(user.getUname(),"email",user.getEmail());
        String response = AccInterface.testUpdpassNew("&uname=" + user.getEmail() + "&upass=" + upass + "&keytp=" + "email");
        boolean result = checkALL(response);
        assertTrue("测试email修改密码",result);
    }

    /**
     * 测试mobile修改密码
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testMobile() throws IOException, SAXException, NoSuchAlgorithmException {
        MyCheckUtil.bindKey(user.getUname(),"mobile",user.getMobile());
        String response = AccInterface.testUpdpassNew("&uname=" + user.getMobile() + "&upass=" + upass + "&keytp=" + "mobile");
        boolean result = checkALL(response);
        assertTrue("测试mobile修改密码",result);
    }

    /**
     * 测试lotterid修改密码
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testLotterid() throws IOException, SAXException, NoSuchAlgorithmException {
        AccInterface.testLotterbind("&uname=" + user.getUname() + "&lotterid=" + user.getLotterid());
        String response = AccInterface.testUpdpassNew("&uname=" + user.getLotterid() + "&upass=" + upass + "&keytp=" + "lotterid");
        boolean result = checkALL(response);
        assertTrue("测试lotterid修改密码",result);
    }

//    /**
//     * 测试deviceid修改密码
//     * @throws IOException
//     * @throws SAXException
//     * @throws NoSuchAlgorithmException
//     */
//    @Test
//    public void testDeviceid() throws IOException, SAXException, NoSuchAlgorithmException {
//        MyCheckUtil.bindKey(uname,"deviceid",deviceid);
//        String response = AccInterface.testUpdpassNew("&uname=" + deviceid + "&upass=" + upass + "&keytp=" + "deviceid");
//        boolean result = checkALL(response);
//        assertTrue("测试deviceid修改密码",result);
//    }
//
//    /**
//     * 测试pushid修改密码
//     * @throws IOException
//     * @throws SAXException
//     * @throws NoSuchAlgorithmException
//     */
//    @Test
//    public void testPushid() throws IOException, SAXException, NoSuchAlgorithmException {
//        MyCheckUtil.bindKey(uname,"pushid",pushid);
//        String response = AccInterface.testUpdpassNew("&uname=" + pushid + "&upass=" + upass + "&keytp=" + "pushid");
//        boolean result = checkALL(response);
//        assertTrue("测试pushid修改密码",result);
//    }

    /**
     * 测试nickname修改密码
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testNickname() throws IOException, SAXException, NoSuchAlgorithmException {
        MyCheckUtil.bindKey(user.getUname(),"nickname",user.getNickname());
        String response = AccInterface.testUpdpassNew("&uname=" + user.getNickname() + "&upass=" + upass + "&keytp=" + "nickname");
        boolean result = checkALL(response);
        assertTrue("测试nickname修改密码",result);
    }

    /**
     * 测试idcard修改密码
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testIdcard() throws IOException, SAXException, NoSuchAlgorithmException {
        MyCheckUtil.bindKey(user.getUname(),"idcard",user.getIdcard());
        String response = AccInterface.testUpdpassNew("&uname=" + user.getIdcard() + "&upass=" + upass + "&keytp=" + "idcard");
        boolean result = checkALL(response);
        assertTrue("测试idcard修改密码",result);
    }

    /**
     * 测试qqid修改密码
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testQqid() throws IOException, SAXException, NoSuchAlgorithmException {
        MyCheckUtil.bindKey(user.getUname(),"qqid",user.getQqid());
        String response = AccInterface.testUpdpassNew("&uname=" + user.getQqid() + "&upass=" + upass + "&keytp=" + "qqid");
        boolean result = checkALL(response);
        assertTrue("测试qqid修改密码",result);
    }

    /**
     * 测试lcb修改密码
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testLcb() throws IOException, SAXException, NoSuchAlgorithmException {
        MyCheckUtil.bindKey(user.getUname(),"lcb",user.getLcb());
        String response = AccInterface.testUpdpassNew("&uname=" + user.getLcb() + "&upass=" + upass + "&keytp=" + "lcb");
        boolean result = checkALL(response);
        assertTrue("测试lcb修改密码",result);
    }

    /**
     * 测试wxid修改密码
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testWxid() throws IOException, SAXException, NoSuchAlgorithmException {
        MyCheckUtil.bindKey(user.getUname(),"wxid",user.getWxid());
        String response = AccInterface.testUpdpassNew("&uname=" + user.getWxid() + "&upass=" + upass + "&keytp=" + "wxid");
        boolean result = checkALL(response);
        assertTrue("测试wxid修改密码",result);
    }

    /**
     * 测试xcid修改密码
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testXcid() throws IOException, SAXException, NoSuchAlgorithmException {
        MyCheckUtil.bindKey(user.getUname(),"xcid",user.getXcid());
        String response = AccInterface.testUpdpassNew("&uname=" + user.getXcid() + "&upass=" + upass + "&keytp=" + "xcid");
        boolean result = checkALL(response);
        assertTrue("测试xcid修改密码",result);
    }

    /**
     * 空旧密码修改密码
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNullOpass() throws IOException, SAXException, NoSuchAlgorithmException {
        String response = AccInterface.testUpdpassNew("&uname=" + user.getUname() + "&upass=" + upass + "&opass=" + "");
        boolean result = checkALL(response);
        assertTrue("空旧密码修改密码",result);
    }

    public boolean checkALL(String response) throws InvalidProtocolBufferException, NoSuchAlgorithmException {
        boolean checkresult = response.contains("result=0");
        boolean checkuname = MyCheckUtil.getValueFromResponse(response,"uname").equals(user.getUname().toLowerCase());
        boolean checkupassdb = MyCheckUtil.checkU(user.getUsertid(),upass);
        boolean result = checkresult && checkuname && checkupassdb;
        return result;
    }
    //=================================错误修改=======================================
    /**
     * 错误用户名修改密码
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongUname() throws IOException, SAXException {
        String response = AccInterface.testUpdpassNew("&uname=" + user.getUname() + "wrong" + "&upass=" + upass);
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("错误用户名修改密码",result);
    }

    /**
     * 错误旧密码修改密码
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongOpass() throws IOException, SAXException {
        String response = AccInterface.testUpdpassNew("&uname=" + user.getUname() + "&upass=" + upass + "&opass=" + user.getUpass() + "wrong");
        boolean result = response.contains("&result=59") && response.contains("msg=password_error");
        assertTrue("错误旧密码修改密码",result);
    }

    /**
     * 错误keytp修改密码
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testWrongKeytp() throws IOException, SAXException, NoSuchAlgorithmException {
        MyCheckUtil.bindKey(user.getUname(),"email",user.getEmail());
        String response = AccInterface.testUpdpassNew("&uname=" + user.getEmail() + "&upass=" + upass + "&keytp=" + "email" + "wrong");
        boolean result = response.contains("result=113") && response.contains("msg=key_not_found");
        assertTrue("错误keytp修改密码",result);
    }

    /**
     * 空用户名修改密码
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNullUname() throws IOException, SAXException {
        String response = AccInterface.testUpdpassNew("&uname=" + "" + "&upass=" + upass);
        boolean result = response.contains("result=101") && response.contains("msg=uname_bad");
        assertTrue("空用户名修改密码",result);
    }

    /**
     * 空密码修改密码
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNullUpass() throws IOException, SAXException {
        String response = AccInterface.testUpdpassNew("&uname=" + user.getUname() + "&upass=" + "");
        boolean result = response.contains("result=101") && response.contains("msg=upass_bad");
        assertTrue("空密码修改密码",result);
    }

    /**
     * 空keytp修改密码
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testWrongEmail() throws IOException, SAXException, NoSuchAlgorithmException {
        MyCheckUtil.bindKey(user.getUname(),"email",user.getEmail());
        String response = AccInterface.testUpdpassNew("&uname=" + user.getEmail() + "&upass=" + upass + "&keytp=" + "");
        boolean result = response.contains("result=2") && response.contains("msg=user_not_found");
        assertTrue("空keytp修改密码",result);
    }
}
