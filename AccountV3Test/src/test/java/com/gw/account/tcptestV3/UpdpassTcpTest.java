package com.gw.account.tcptestV3;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.account.httptest.AccInterface;
import com.gw.account.tcptest.AccInterfaceTcp;
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
 * Created by Hihiri on 2015/5/27.
 */
public class UpdpassTcpTest {
    private static final Log LOG = LogFactory.getLog(UpdpassTcpTest.class);
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
    public void testUnameOpass() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname", user.getUname(),
                        "upass",upass,
                        "opass",user.getUpass()
                )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
        boolean result = checkALL(response);
        assertTrue("用户名修改密码",result);
    }

    /**
     * 用户名修改密码，MD5
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUnameOpassMD5() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname", user.getUname(),
                        "upass",MyCheckUtil.encodePassword(upass),
                        "opass",MyCheckUtil.encodePassword(user.getUpass())
                )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
        boolean result = checkALL(response);
        assertTrue("用户名修改密码，MD5",result);
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
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname",user.getEmail(),
                        "upass",upass,
                        "opass",user.getUpass(),
                        "keytp","email"
                )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
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
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname",user.getMobile(),
                        "upass",upass,
                        "opass",user.getUpass(),
                        "keytp","mobile"
                )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
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
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname",user.getLotterid(),
                        "upass",upass,
                        "opass",user.getUpass(),
                        "keytp","lotterid"
                )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
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
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname",user.getNickname(),
                        "upass",upass,
                        "opass",user.getUpass(),
                        "keytp","nickname"
                )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
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
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname",user.getIdcard(),
                        "upass",upass,
                        "opass",user.getUpass(),
                        "keytp","idcard"
                )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
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
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname",user.getQqid(),
                        "upass",upass,
                        "opass",user.getUpass(),
                        "keytp","qqid"
                )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
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
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname",user.getLcb(),
                        "upass",upass,
                        "opass",user.getUpass(),
                        "keytp","lcb"
                )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
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
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname",user.getWxid(),
                        "upass",upass,
                        "opass",user.getUpass(),
                        "keytp","wxid"
                )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
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
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname",user.getXcid(),
                        "upass",upass,
                        "opass",user.getUpass(),
                        "keytp","xcid"
                )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
        boolean result = checkALL(response);
        assertTrue("测试xcid修改密码",result);
    }

    /**
     * 空keytp修改密码
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testNullKeytp() throws IOException, SAXException, NoSuchAlgorithmException {
        MyCheckUtil.bindKey(user.getUname(),"email",user.getEmail());
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname",user.getEmail(),
                        "upass",upass,
                        "opass",user.getUpass(),
                        "keytp",""
                )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
        boolean result = checkALL(response);
        assertTrue("空keytp修改密码",result);
    }

    public boolean checkALL(String response) throws InvalidProtocolBufferException, NoSuchAlgorithmException {
        boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response, "result", "0");
        boolean checkuname = MyCheckUtil.checkJsonResponseSolo(response,"uname",user.getUname().toLowerCase());
        boolean checkupassdb = MyCheckUtil.checkU(user.getUsertid(),MyCheckUtil.encodePassword(upass));
        boolean result = checkresult && checkuname && checkupassdb;
        return result;
    }
    //=================================错误修改=======================================
    /**
     * 用户名修改密码，无opass
     * @throws IOException
     * @throws SAXException
     * @throws java.security.NoSuchAlgorithmException
     */
    @Test
    public void testUnameNopass() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname", user.getUname(),
                        "upass",upass
                )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
        boolean result = MyCheckUtil.checkJsonResponseSolo(response,"msg","opass_bad")
                && JSON.parseObject(response).getIntValue("result")==101;
        assertTrue("用户名修改密码，无opass",result);
    }

    /**
     * 空旧密码修改密码
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNullOpass() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname",user.getUname(),
                        "upass",upass,
                        "opass",""
                )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
        boolean result = MyCheckUtil.checkJsonResponseSolo(response,"msg","opass_bad")
                && JSON.parseObject(response).getIntValue("result")==101;
        assertTrue("空旧密码修改密码",result);
    }

    /**
     * 错误用户名修改密码
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongUname() throws IOException, SAXException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname",user.getUname() + "wrong",
                        "upass",upass,
                        "opass",user.getUpass()
                )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
        boolean result = MyCheckUtil.checkJsonResponseSolo(response, "msg", "user_not_found")
                && JSON.parseObject(response).getIntValue("result")==2
                && MyCheckUtil.checkJsonResponseSolo(response, "uname", user.getUname()+"wrong");
        assertTrue("错误用户名修改密码",result);
    }

    /**
     * 错误旧密码修改密码
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongOpass() throws IOException, SAXException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname",user.getUname(),
                        "upass",upass,
                        "opass",user.getUpass() + "wrong"
                )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
        boolean result = MyCheckUtil.checkJsonResponseSolo(response, "msg", "password_error")
                && JSON.parseObject(response).getIntValue("result")==59
                && MyCheckUtil.checkJsonResponseSolo(response, "uname", user.getUname());
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
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname",user.getEmail(),
                        "upass",upass,
                        "opass",user.getUpass(),
                        "keytp","email" + "wrong"
                )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
        boolean result = MyCheckUtil.checkJsonResponseSolo(response, "msg", "key_not_found")
                && JSON.parseObject(response).getIntValue("result")==113
                && MyCheckUtil.checkJsonResponseSolo(response, "uname", user.getEmail());
        assertTrue("错误keytp修改密码",result);
    }

    /**
     * 空用户名修改密码
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNullUname() throws IOException, SAXException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname","",
                        "upass",upass,
                        "opass",user.getUpass()
                )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
        boolean result = MyCheckUtil.checkJsonResponseSolo(response, "msg", "uname_bad")
                && JSON.parseObject(response).getIntValue("result")==101;
        assertTrue("空用户名修改密码",result);
    }

    /**
     * 空密码修改密码
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNullUpass() throws IOException, SAXException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname",user.getUname(),
                        "upass","",
                        "opass",user.getUpass()
                        )
        );
        String response = AccInterfaceTcp.testUpdpassTcp(request);
        boolean result = MyCheckUtil.checkJsonResponseSolo(response, "msg", "upass_bad")
                && JSON.parseObject(response).getIntValue("result")==101;
        assertTrue("空密码修改密码",result);
    }
}
