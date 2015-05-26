package com.gw.account.httptest;

import com.alibaba.fastjson.JSONArray;
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

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

/**
* Created by song on 2015/5/7.
*/
public class GetbankcardsTest {
    private static final Log LOG = LogFactory.getLog(GetbankcardsTest.class);
    private String AES_KEY = "dzhCard20219988!";
    User user = new User();

    @BeforeClass
    public static void globalInit() {
        MyCheckUtil.initialize();
    }

    @Before
    public void setUp() throws InterruptedException, SAXException, IOException {
        user.createUser();
        MyCheckUtil.bindKey(user.getUname(), "idcard", user.getIdcard());
        MyCheckUtil.bindKey(user.getUname(), "truename", user.getTruename());
        AddbankcardsTest.initBankidtobankcard();
        AddbankcardsTest.addBankcards(user, 1);
    }

    /**
     * 添加一张银行卡，查询银行卡
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testUname() throws IOException, SAXException {
        String response = AccInterface.testGetbankcards("&uname=" + user.getUname());
        boolean result = checkALL(response, AddbankcardsTest.getBankidtobankcard(),"",0);
        assertTrue("添加一张银行卡，查询银行卡", result);
    }

    /**
     * 增加两张卡，查询全部银行卡
     * @throws InterruptedException
     * @throws SAXException
     * @throws IOException
     */
    @Test
    public void testUnameTwobanks() throws InterruptedException, SAXException, IOException {
        AddbankcardsTest.addBankcards(user,2);
        sleep(1000);
        String response = AccInterface.testGetbankcards("&uname=" + user.getUname());
        boolean result = checkALL(response, AddbankcardsTest.getBankidtobankcard(),"",0);
        assertTrue("增加两张卡，查询全部银行卡", result);
    }

    /**
     * 增加两张卡，分别查询两张银行卡
     * @throws InterruptedException
     * @throws SAXException
     * @throws IOException
     */
    @Test
    public void testUnameandBankcardid() throws InterruptedException, SAXException, IOException {
        AddbankcardsTest.addBankcards(user,2);
        boolean result = true;
        for (String key:AddbankcardsTest.getBankidtobankcard().keySet()) {
            String response = AccInterface.testGetbankcards("&uname=" + user.getUname() + "&bankcardid=" + key);
            result = result && checkALL(response, AddbankcardsTest.getBankidtobankcard(), key,0);
        }
        assertTrue("增加两张卡，分别查询两张银行卡", result);
    }

    /**
     * 查询带星号的银行卡和手机号
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testMask() throws IOException, SAXException {
        String response = AccInterface.testGetmaskbankcards("&uname=" + user.getUname());
        boolean result = checkALL(response, AddbankcardsTest.getBankidtobankcard(),"",1);
        assertTrue("查询带星号的银行卡和手机号", result);
    }

    private boolean checkALL(String response, JSONObject bankidtobankcard, String bankcardid, int mask) {
        boolean chenkcode = MyCheckUtil.getCode(response) == 1;
//        boolean checkresult = MyCheckUtil.checkResponseSolo(response, "result", "0");
        boolean checkuname = MyCheckUtil.checkResponseSolo(response, "uname", user.getUname().toLowerCase());
        JSONArray jsonArrayget = JSONArray.parseArray(MyCheckUtil.getValueFromResponse(response, "bankinfos"));
        boolean checkbankcards = true;
        if (bankcardid != "") {
            checkbankcards = checkBankcardsSolo(bankidtobankcard, jsonArrayget, bankcardid,mask);
        } else {
            for (String key:bankidtobankcard.keySet()) {
                boolean checkbankcardssolo = checkBankcardsSolo(bankidtobankcard,jsonArrayget,key,mask);
                checkbankcards = checkbankcards && checkbankcardssolo;
            }
        }
        boolean result = chenkcode && checkuname && checkbankcards;
        return result;
    }

    public boolean checkBankcardsSolo(JSONObject bankidtobankcard,JSONArray jsonArrayget, String bankcardid, int mask) {
        JSONObject jsonObject = bankidtobankcard.getJSONObject(bankcardid);
        JSONObject jsonObjectget = new JSONObject();
        for (int i=0; i<jsonArrayget.size(); i++) {
            jsonObjectget = jsonArrayget.getJSONObject(i);
            if (bankcardid.equals(jsonObjectget.getString("bankcardid")))
                break;
        }
        for (String key : jsonObject.keySet()) {
            if (key.equals("uname"))
                continue;
            String value = jsonObject.getString(key);
            if (key.equals("bankcardno") && mask>0) {
                String f4 = value.substring(0,4);
                String t4 = value.substring(value.length()-4,value.length());
                String stars = "";
                for (int i=0; i<10; i++)
                    stars += "*";
                value = f4 + stars + t4;
            }
            if (key.equals("mobile") && mask>0) {
                String f4 = value.substring(0,4);
                String t3 = value.substring(value.length()-3,value.length());
                String stars = "";
                for (int i=0; i<4; i++)
                    stars += "*";
                value = f4 + stars + t3;
            }
            if (value.equals(jsonObjectget.getString(key))
                    || mask>0 && key.equals("bankcardno") && value.equals(jsonObjectget.getString(key))
                    || mask>0 && key.equals("mobile") && value.equals(jsonObjectget.getString(key))
                    || key.equals("ylmobile") && value.equals(jsonObjectget.getString("recallmobile"))) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }


}
