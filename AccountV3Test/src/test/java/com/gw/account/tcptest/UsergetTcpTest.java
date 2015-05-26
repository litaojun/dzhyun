package com.gw.account.tcptest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.gw.account.httptest.AccInterface;
import com.gw.account.utils.MyCheckUtil;
import com.gw.account.utils.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/4/7.
 */
public class UsergetTcpTest {
    private static final Log LOG = LogFactory.getLog(UsergetTcpTest.class);
    private static User user = new User();
    private static String nlotterid;

    @BeforeClass
    public static void globalInit() throws IOException, SAXException, InterruptedException {
        MyCheckUtil.initialize();
        user.createUser();
        MyCheckUtil.bindKey(user.getUname(), "email", user.getEmail());
        MyCheckUtil.bindKey(user.getUname(), "mobile", user.getMobile());
        String response = AccInterface.testLotterbind("&uname=" + user.getUname() + "&lotterid=" + user.getLotterid());
//        MyCheckUtil.bindKey(uname, "deviceid", deviceid);
//        MyCheckUtil.bindKey(uname, "pushid", pushid);
        nlotterid = MyCheckUtil.getValueFromResponse(response, "nlotterid");
        MyCheckUtil.bindKey(user.getUname(), "truename", user.getTruename());
        MyCheckUtil.bindKey(user.getUname(), "nickname", user.getNickname());
        MyCheckUtil.bindKey(user.getUname(), "idcard", user.getIdcard());
        MyCheckUtil.bindKey(user.getUname(), "qqid", user.getQqid());
        MyCheckUtil.bindKey(user.getUname(), "lcb", user.getLcb());
        MyCheckUtil.bindKey(user.getUname(), "wxid", user.getWxid());
        MyCheckUtil.bindKey(user.getUname(), "xcid", user.getXcid());
    }

    /**
     * 用户名请求
     * @throws IOException
     * @throws SAXException
     * @throws java.security.NoSuchAlgorithmException
     */
    @Test
    public void testUname() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname", user.getUname()
                )
        );
        String response = AccInterfaceTcp.testUsergetTcp(request);
        boolean result = checkALL(null,response);
        assertTrue("用户名请求", result);
    }

    /**
     * 用户名密码请求
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUnameUpass() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname", user.getUname(),
                        "upass", MyCheckUtil.encodePassword(user.getUpass())
                )
        );
        String response = AccInterfaceTcp.testUsergetTcp(request);
        boolean result = checkALL(null,response);
        assertTrue("用户名密码请求", result);
    }

    /**
     * 用户名请求exreq
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testExreq() throws IOException, SAXException, NoSuchAlgorithmException {
//        String exreq = "{\"keys\":[\"email\",\"mobile\",\"lotterid\",\"deviceid\",\"pushid\",\"nlotterid\",\"truename\",\"nickname\"," +
//                "\"idcard\",\"qqid\",\"lcb\",\"wxid\",\"xcid\"]}";
        ImmutableMap exreq = ImmutableMap.of(
                "keys", ImmutableList.of("email","mobile","lotterid","nlotterid","truename",
                        "nickname","idcard","qqid","lcb","wxid","xcid")
        );
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname",user.getUname(),
                        "exreq",exreq
                )
        );
        String response = AccInterfaceTcp.testUsergetTcp(request);
        boolean result = checkALL(exreq,response);
        assertTrue("用户名请求exresp", result);
    }

    /**
     * 用户名请求exreq,keytp
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testExreqKeytp() throws IOException, SAXException, NoSuchAlgorithmException {
//        String exreq = "{\"keys\":[\"email\",\"mobile\",\"lotterid\",\"deviceid\",\"pushid\",\"nlotterid\",\"truename\",\"nickname\"," +
//                "\"idcard\",\"qqid\",\"lcb\",\"wxid\",\"xcid\"],\"keytp\":\"mobile\"}";
        ImmutableMap exreq = ImmutableMap.of(
                "keys", ImmutableList.of("email","mobile","lotterid","nlotterid","truename",
                        "nickname","idcard","qqid","lcb","wxid","xcid"),
                "keytp", "mobile"
        );
        String request = JSON.toJSONString(
                ImmutableMap.of(
                        "uname", user.getMobile(),
                        "exreq", exreq
                )
        );
        String response = AccInterfaceTcp.testUsergetTcp(request);
        boolean result = checkALL(exreq,response);
        assertTrue("用户名请求exresp", result);
    }

    public boolean checkALL(ImmutableMap exreq, String response) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response, "iResult", "0");
        boolean checkupass = MyCheckUtil.checkJsonResponseSolo(response, "pass_md5_str", MyCheckUtil.encodePassword(URLDecoder.decode(user.getUpass(), "UTF-8")));
        boolean checkkeys = true;
        boolean checkrealuname = true;
        if (exreq!=null && exreq.containsKey("keys")) {
            ImmutableList keys = (ImmutableList) exreq.get("keys");
            JSONObject exrespget = JSON.parseObject(MyCheckUtil.getValueFromJsonResponse(response, "exresp"));
            JSONArray keysget = exrespget.getJSONArray("keys");
            String realunameget = exrespget.getString("realuname");
            for (int i=0; i<keys.size(); i++) {
                boolean exist = false;
                for (int j=0; j<keysget.size(); j++) {
                    for(String key:keysget.getJSONObject(j).keySet())
                        if (keys.get(i).equals(key)) {
                            exist = true;
                            break;
                        }
                }
                checkkeys = checkkeys && exist;
            }
            checkrealuname = user.getUname().toLowerCase().equals(realunameget);
        }
        boolean result = checkresult && checkupass && checkkeys && checkrealuname;
        return result;
    }
}
