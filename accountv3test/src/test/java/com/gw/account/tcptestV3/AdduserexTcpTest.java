package com.gw.account.tcptestV3;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
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
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/4/7.
 */
public class AdduserexTcpTest {
    private static final Log LOG = LogFactory.getLog(AdduserexTcpTest.class);
    private User user = new User();

    @BeforeClass
    public static void globalInit() {
        MyCheckUtil.initialize();
    }

    @Before
    public void setUp() throws IOException, SAXException, InterruptedException {
        user.createUser();
    }

    //=================================正常注册=======================================

    /**
     * 只填帐号后缀生成方式：rand
     */
    @Test
    public void testGenRand() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "gensfx", "rand"
                )
        );
        String response = AccInterfaceTcp.testAdduserexTcp(request);
        boolean result = checkALL(request, response);
        assertTrue("只填帐号后缀生成方式：rand", result);
    }

    /**
     * 只填帐号后缀生成方式：rand,string
     */
    @Test
    public void testGenRandString() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "gensfx", "rand",
                        "sfxtp", "string",
                        "sfxlen", "12"
                )
        );
        String response = AccInterfaceTcp.testAdduserexTcp(request);
        boolean result = checkALL(request, response);
        assertTrue("只填帐号后缀生成方式：rand,string", result);
    }

    /**
     * 为true时，不做真正注册，仅生成帐号名
     */
    @Test
    public void testOnlyuname() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "gensfx", "rand",
                        "onlyuname", "true"
                )
        );
        String response = AccInterfaceTcp.testAdduserexTcp(request);
        boolean result = MyCheckUtil.checkJsonResponseSolo(response, "result", "0")
                && MyCheckUtil.getValueFromJsonResponse(response, "uname") != null;
        assertTrue("为true时，不做真正注册，仅生成帐号名", result);
    }

    /**
     * 测试email
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testEmail() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "prefix", user.getPrefix(),
                        "gensfx", "seq",
                        "keys", ImmutableList.of(ImmutableMap.of("email", user.getEmail())),
                        "upass", user.getUpass()
                )
        );
        String response = AccInterfaceTcp.testAdduserexTcp(request);
        boolean result = checkALL(request, response);
        assertTrue("测试email", result);
    }

    /**
     * 测试mobile
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testMobile() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "prefix", user.getPrefix(),
                        "gensfx", "seq",
                        "keys", ImmutableList.of(ImmutableMap.of("mobile", user.getMobile())),
                        "upass", user.getUpass()
                )
        );
        String response = AccInterfaceTcp.testAdduserexTcp(request);
        boolean result = checkALL(request, response);
        assertTrue("测试mobile", result);
    }

    /**
     * 测试lotterid
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testLotterid() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "prefix", user.getPrefix(),
                        "gensfx", "seq",
                        "keys", ImmutableList.of(ImmutableMap.of("lotterid", "")),
                        "upass", user.getUpass()
                )
        );
        String response = AccInterfaceTcp.testAdduserexTcp(request);
        boolean result = checkALL(request, response);
        assertTrue("测试lotterid", result);
    }

//    /**
//     * 测试deviceid
//     *
//     * @throws IOException
//     * @throws SAXException
//     */
//    @Test
//    public void testDeviceid() throws IOException, SAXException, NoSuchAlgorithmException {
//        String request = JSON.toJSONString(
//                ImmutableMap.of(
//                        "prefix", user.getPrefix(),
//                        "gensfx", "seq",
//                        "keys", ImmutableList.of(ImmutableMap.of("deviceid", user.getDeviceid())),
//                        "upass", user.getUpass()
//                )
//        );
//        String response = AccInterfaceTcp.testAdduserexTcp(request);
//        boolean result = checkALL(request, response);
//        assertTrue("测试lotterid", result);
//    }

//    /**
//     * 测试pushid
//     *
//     * @throws IOException
//     * @throws SAXException
//     */
//    @Test
//    public void testPushid() throws IOException, SAXException, NoSuchAlgorithmException {
//        String request = JSON.toJSONString(
//                ImmutableMap.of(
//                        "prefix", prefix,
//                        "gensfx", "seq",
//                        "keys", ImmutableList.of(ImmutableMap.of("pushid", pushid)),
//                        "upass", upass
//                )
//        );
//        String response = AccInterfaceTcp.testAdduserexTcp(request);
//        boolean result = checkALL(request,response);
//        assertTrue("测试pushid", result);
//    }

    /**
     * 测试truename
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testTruename() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "prefix", user.getPrefix(),
                        "gensfx", "seq",
                        "keys", ImmutableList.of(ImmutableMap.of("truename", user.getTruename())),
                        "upass", user.getUpass()
                )
        );
        String response = AccInterfaceTcp.testAdduserexTcp(request);
        boolean result = checkALL(request, response);
        assertTrue("测试truename", result);
    }

    /**
     * 测试nickname
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNickname() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "prefix", user.getPrefix(),
                        "gensfx", "seq",
                        "keys", ImmutableList.of(ImmutableMap.of("nickname", user.getNickname())),
                        "upass", user.getUpass()
                )
        );
        String response = AccInterfaceTcp.testAdduserexTcp(request);
        boolean result = checkALL(request, response);
        assertTrue("测试nickname", result);
    }

    /**
     * 测试idcard
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testIdcard() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "prefix", user.getPrefix(),
                        "gensfx", "seq",
                        "keys", ImmutableList.of(ImmutableMap.of("idcard", user.getIdcard())),
                        "upass", user.getUpass()
                )
        );
        String response = AccInterfaceTcp.testAdduserexTcp(request);
        boolean result = checkALL(request, response);
        assertTrue("测试idcard", result);
    }

    /**
     * 测试qqid
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testQqid() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "prefix", user.getPrefix(),
                        "gensfx", "seq",
                        "keys", ImmutableList.of(ImmutableMap.of("qqid", user.getQqid())),
                        "upass", user.getUpass()
                )
        );
        String response = AccInterfaceTcp.testAdduserexTcp(request);
        boolean result = checkALL(request, response);
        assertTrue("测试qqid", result);
    }

    /**
     * 测试lcb
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testLcb() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "prefix", user.getPrefix(),
                        "gensfx", "seq",
                        "keys", ImmutableList.of(ImmutableMap.of("lcb", user.getLcb())),
                        "upass", user.getUpass()
                )
        );
        String response = AccInterfaceTcp.testAdduserexTcp(request);
        boolean result = checkALL(request, response);
        assertTrue("测试lcb", result);
    }

    /**
     * 测试wxid
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWxid() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "prefix", user.getPrefix(),
                        "gensfx", "seq",
                        "keys", ImmutableList.of(ImmutableMap.of("wxid", user.getWxid())),
                        "upass", user.getUpass()
                )
        );
        String response = AccInterfaceTcp.testAdduserexTcp(request);
        boolean result = checkALL(request, response);
        assertTrue("测试wxid", result);
    }

    /**
     * 测试xcid
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testXcid() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "prefix", user.getPrefix(),
                        "gensfx", "seq",
                        "keys", ImmutableList.of(ImmutableMap.of("xcid", user.getXcid())),
                        "upass", user.getUpass()
                )
        );
        String response = AccInterfaceTcp.testAdduserexTcp(request);
        boolean result = checkALL(request, response);
        assertTrue("测试xcid", result);
    }

    public boolean checkALL(String request, String response) throws InvalidProtocolBufferException, NoSuchAlgorithmException, UnsupportedEncodingException {
//        boolean checkcode = MyCheckUtil.getCode(response) == 1;
        boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response, "result", "0");
        String getusertid = MyCheckUtil.getValueFromJsonResponse(response, "usertid");
        String reqsfxtp = MyCheckUtil.getValueFromJsonResponse(request, "sfxtp");
        String reqsfxlen = MyCheckUtil.getValueFromJsonResponse(request, "sfxlen");
        String getuname = MyCheckUtil.getValueFromJsonResponse(response, "uname");
        boolean checkuname = true;
        if (reqsfxlen != null) {
            if (reqsfxtp != null && reqsfxtp.equals("string")) {
                checkuname = getuname.matches(user.getPrefix() + "[A-Za-z]{" + reqsfxlen + "}") || getuname.matches("[A-Za-z]{" + reqsfxlen + "}");
            } else {
                checkuname = getuname.matches(user.getPrefix() + "\\d{" + reqsfxlen + "}") || getuname.matches("\\d{" + reqsfxlen + "}");
            }
        } else {
            if (reqsfxtp != null && reqsfxtp.equals("string")) {
                checkuname = getuname.matches(user.getPrefix() + "[A-Za-z]*") || getuname.matches("[A-Za-z]*");
            } else {
                checkuname = getuname.matches(user.getPrefix() + "\\d*") || getuname.matches("\\d*");
            }
        }
        String getupass = MyCheckUtil.getValueFromJsonResponse(response, "upass");
//        boolean checkupass = getupass.equals(upass) || getupass.matches("\\d{6}");
        boolean checkupass = true;
        String upassrequest = MyCheckUtil.getValueFromJsonResponse(request, "upass");
        if (upassrequest != null) {
            checkupass = getupass.equals(MyCheckUtil.encodePassword(upassrequest));
        } else {
            checkupass = getupass != null;
        }
        boolean checkudb = MyCheckUtil.checkU(getusertid, getupass);
        boolean checkkey = true;
        boolean checkukeydb = true;
        boolean checkindexdb = true;
        JSONArray keysrequest = MyCheckUtil.getKeysFromJsonResponse(request);
        JSONArray keysresponse = MyCheckUtil.getKeysFromJsonResponse(response);
        if (keysrequest != null) {
            for (int index = 0; index < keysrequest.size(); index++) {
                JSONObject keys = keysrequest.getJSONObject(index);
                for (String keytp : keys.keySet()) {
                    if (keytp.equals("flush"))
                        continue;
                    String getkey = keysresponse.getJSONObject(index).getString(keytp);
                    if (!keytp.equals("lotterid")) {
                        checkkey = checkkey && getkey.equals(keys.getString(keytp));
                    }
                    checkukeydb = checkukeydb && MyCheckUtil.checkUkey(getusertid, keytp, getkey);
                    if (!keytp.equals("deviceid") && !keytp.equals("truename")) {
                        checkindexdb = checkindexdb && MyCheckUtil.checkIndex(getusertid, keytp, getkey);
                    }
                }
            }
        }
        boolean result = checkresult && checkuname && checkupass && checkudb && checkkey && checkukeydb && checkindexdb;
        return result;
    }

    //=================================重复key注册=======================================

    /**
     * 测试重复email注册
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testDuplicateEmail() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "gensfx", "seq",
                        "keys", ImmutableList.of(ImmutableMap.of("email", user.getEmail()))
                )
        );
        String responsepre = AccInterfaceTcp.testAdduserexTcp(request);
        String response = AccInterfaceTcp.testAdduserexTcp(request);
        boolean result = MyCheckUtil.checkJsonResponseSolo(response, "result", "114")
                && MyCheckUtil.checkJsonResponseSolo(response, "msg", "key_dup");
        assertTrue("测试重复email注册", result);
    }

    //=================================重复key强制注册=======================================

    /**
     * 测试重复email强制注册
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testForceDuplicateEmail() throws IOException, SAXException, NoSuchAlgorithmException {
        String requestpre = JSON.toJSONString(
                ImmutableMap.of(
                        "gensfx", "seq",
                        "keys", ImmutableList.of(ImmutableMap.of("email", user.getEmail()))
                )
        );
        String responsepre = AccInterfaceTcp.testAdduserexTcp(requestpre);
        boolean resultpre = checkALL(requestpre, responsepre);
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "gensfx", "seq",
                        "keys", ImmutableList.of(ImmutableMap.of("email", user.getEmail(), "flush", true))
                )
        );
        String response = AccInterfaceTcp.testAdduserexTcp(request);
        boolean resultforce = checkALL(request, response);
        boolean resultaft = !checkALL(requestpre, responsepre);
        boolean result = resultpre && resultforce && resultaft;
        assertTrue("测试重复email强制注册", result);
    }

    //=================================绑定冲突findback返回账号名密码=======================================

    /**
     * 测试重复email，不返回失败，返回反查到的帐号名、密码
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testDuplicateEmailFindback() throws IOException, SAXException, NoSuchAlgorithmException {
        String requestpre = JSON.toJSONString(
                ImmutableMap.of(
                        "gensfx", "seq",
                        "keys", ImmutableList.of(ImmutableMap.of("email", user.getEmail()))
                )
        );
        String responsepre = AccInterfaceTcp.testAdduserexTcp(requestpre);
        boolean resultpre = checkALL(requestpre, responsepre);
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "gensfx", "seq",
                        "findback", true,
                        "keys", ImmutableList.of(ImmutableMap.of("email", user.getEmail()))
                )
        );
        String response = AccInterfaceTcp.testAdduserexTcp(request);
        boolean resultback = MyCheckUtil.checkJsonResponseSolo(response, "uname", MyCheckUtil.getValueFromJsonResponse(responsepre, "uname"))
                && MyCheckUtil.checkJsonResponseSolo(response, "upass", MyCheckUtil.getValueFromJsonResponse(responsepre, "upass"));
        boolean result = resultpre && resultback;
        assertTrue("测试重复email，不返回失败，返回反查到的帐号名、密码", result);
    }

    //=================================错误注册=======================================

    /**
     * 测试超过20字节prefix
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testLongPrefix() throws IOException, SAXException {
        String longprefix = user.getPrefix();
        while (longprefix.length() <= 20) {
            longprefix += "t";
        }
        String response = AccInterfaceTcp.testAdduserexTcp(JSON.toJSONString(
                ImmutableMap.of(
                        "prefix", longprefix,
                        "gensfx", "seq"
                )
        ));
        boolean result = MyCheckUtil.getValueFromJsonResponse(response, "result").equals("101") &&
                MyCheckUtil.getValueFromJsonResponse(response, "msg").equals("prefix_bad");
        assertTrue("测试超过20字节prefix", result);
    }

    /**
     * 测试错误gen
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testWrongGen() throws IOException, SAXException {
        String response = AccInterfaceTcp.testAdduserexTcp(JSON.toJSONString(
                ImmutableMap.of(
                        "gensfx", "ttt"
                )
        ));
        boolean result = MyCheckUtil.getValueFromJsonResponse(response, "result").equals("101") &&
                MyCheckUtil.getValueFromJsonResponse(response, "msg").equals("parameter_error");
        assertTrue("测试错误gen", result);
    }

    /**
     * 测试空prefix
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNullPrefix() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "prefix", "",
                        "gensfx", "seq"
                )
        );
        String response = AccInterfaceTcp.testAdduserexTcp(request);
        boolean result = checkALL(request, response);
        assertTrue("测试空prefix", result);
    }

    /**
     * 测试空gen
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testNullGen() throws IOException, SAXException {
        String response = AccInterfaceTcp.testAdduserexTcp(JSON.toJSONString(
                ImmutableMap.of(
                        "gensfx", ""
                )
        ));
        boolean result = MyCheckUtil.getValueFromJsonResponse(response, "result").equals("101") &&
                MyCheckUtil.getValueFromJsonResponse(response, "msg").equals("gensfx_bad");
        assertTrue("测试空gen", result);
    }
}
