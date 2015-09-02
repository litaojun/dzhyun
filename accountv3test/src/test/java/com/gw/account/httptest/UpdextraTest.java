package com.gw.account.httptest;

import com.alibaba.fastjson.JSONObject;
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

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/4/15.
 */
public class UpdextraTest {
    private static final Log LOG = LogFactory.getLog(UpdextraTest.class);
    private User user = new User();
    private String uMarket_115 = "620708805";
    private String extrabuf_115 = "{\"J365\":\"1\",\"QS\":0,\"YYB\":0,\"SC\":\"620708805\",\"GN\":\"65568\",\"UT\":0,\"NI\":0,\"CP\":\"0\",\"CC\":0,\"PZ1\":\"262144\",\"PZ2\":\"1647763\",\"PZ3\":\"139608596\",\"PZ4\":\"0\",\"PZ5\":\"1758486326\",\"PZ6\":\"16\",\"PZ7\":\"44\",\"PN\":\"大智慧365标准版\",\"HT\":\"\",\"QXD\":{\"115\":1440979199,\"236\":1440979199},\"DS\":{\"10002\":\"1440979199\",\"10001\":\"1437720304\"}}";
//    private String uMarket_115 = "616514501";
//    private String extrabuf_115 = "{\"QS\":0,\"YYB\":0,\"SC\":\"616514501\",\"GN\":\"65536\",\"UT\":0,\"NI\":0,\"CP\":\"2147483648\",\"CC\":0,\"PZ1\":\"0\",\"PZ2\":\"1647763\",\"PZ3\":\"139608596\",\"PZ4\":\"0\",\"PZ5\":\"1758484278\",\"PZ6\":\"16\",\"PZ7\":\"44\",\"PN\":\"\",\"HT\":\"\",\"QXD\":{\"93\":1463702399},\"DS\":{\"10001\":\"1440657736\"}}";

    @BeforeClass
    public static void globalInit() {
        MyCheckUtil.initialize();
    }

    @Before
    public void setUp() throws IOException, SAXException, InterruptedException {
        user.createUser();
    }

    /**
     * 测试新建用户是否为默认权限
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testDefUR() throws IOException, SAXException, InterruptedException {
        boolean result = checkALL("", "");
        assertTrue("测试新建用户是否为默认权限", result);
    }

    /**
     * 将新建用户的权限赋值为115权限
     *
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testUpdextra115() throws IOException, SAXException {
        String request = "&uname=" + user.getUname() + "&uMarket=" + uMarket_115 + "&extrabuf=" + extrabuf_115;
        String response = AccInterface.testUpdextra(request);
        boolean result = checkALL(request, response);
        assertTrue("更新为115权限", result);
    }

    //=================================校验方法=======================================
    public boolean checkALL(String request, String response) throws InvalidProtocolBufferException {
        boolean result = true;
        if (response != "") {
            boolean checkresult = MyCheckUtil.checkResponseSolo(response, "result", "0");
            boolean checkuname = MyCheckUtil.checkResponseSolo(response, "uname", user.getUname().toLowerCase()) ||
                    MyCheckUtil.checkResponseSolo(response, "uname", user.getUname());
            result = checkresult && checkuname;
        }
        
        boolean checkdb = true;
        if (request.contains("uMarket")) {
            String uMarketwr = MyCheckUtil.getValueFromResponse(request, "uMarket");
            String extrabufwr = MyCheckUtil.getValueFromResponse(request, "extrabuf");
            JSONObject valuewr = new JSONObject();
            valuewr.put("uMarket", uMarketwr);
            valuewr.put("extrabuf", extrabufwr);
            checkdb = MyCheckUtil.checkUR(user.getUname(), valuewr);
        } else {
            checkdb = MyCheckUtil.checkUR(user.getUname(), null);
        }
        result = result && checkdb;
        return result;
    }


}
