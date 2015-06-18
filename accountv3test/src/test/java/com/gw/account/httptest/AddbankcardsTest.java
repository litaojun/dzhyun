package com.gw.account.httptest;

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
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/4/16.
 */
public class AddbankcardsTest {
    private static final Log LOG = LogFactory.getLog(AddbankcardsTest.class);
    private User user = new User();
    private String bankcardid;
    private static JSONObject bankidtobankcard = new JSONObject();     //bankcardid指向这张卡对应的所有信息

    @BeforeClass
    public static void globalInit() {
        MyCheckUtil.initialize();
    }

    @Before
    public void setUp() throws IOException, SAXException, InterruptedException {
        user.createUser();
        MyCheckUtil.bindKey(user.getUname(), "idcard", user.getIdcard());
        MyCheckUtil.bindKey(user.getUname(), "truename", user.getTruename());
        bankidtobankcard.clear();
    }

    /**
     * 填写所有字段添加银行卡
     *
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testAllparams() throws IOException, SAXException, InterruptedException {
        JSONObject keyvalue = new JSONObject();
        keyvalue.put("uname", user.getUname());
        keyvalue.put("bankcardno", user.getBankcardno());
        keyvalue.put("bank", user.getBank());
        keyvalue.put("cardtp", user.getCardtp());
        keyvalue.put("provno", user.getProvno());
        keyvalue.put("cityno", user.getCityno());
        keyvalue.put("subbank", user.getSubbank());
        keyvalue.put("mobile", user.getMobile());
        keyvalue.put("ylmobile", user.getYlmobile());
        keyvalue.put("checkstatus", user.getCheckstatus());

//        String bankcardid = MyCheckUtil.getValueFromResponse(response,"bankcardid");
//        bankidtobankcard.put(bankcardid,keyvalue);
        boolean result = checkALL(keyvalue);
        assertTrue("填写所有字段添加银行卡", result);
    }

    //=================================校验方法=======================================
    public boolean checkALL(JSONObject keyvalue) throws IOException, SAXException, InterruptedException {
        String response = turnandAdd(keyvalue);
        bankcardid = MyCheckUtil.getValueFromResponse(response, "bankcardid");
        boolean checkresult = MyCheckUtil.checkResponseSolo(response, "result", "0");
        boolean checkuname = MyCheckUtil.checkResponseSolo(response, "uname", user.getUname());
        boolean checkbankcardno = MyCheckUtil.checkResponseSolo(response, "bankcardno", user.getBankcardno());
        boolean checkbankcardid = bankcardid != null;
        bankidtobankcard.put(bankcardid, keyvalue);
        boolean resultdb = MyCheckUtil.checkBank(user.getUsertid(), bankidtobankcard);
        boolean result = checkresult && checkuname && checkbankcardno && checkbankcardid && resultdb;
        return result;
    }

//    public boolean checkALL(String response, JSONObject bankidtobankcard) throws IOException, SAXException {
//        bankcardid = MyCheckUtil.getValueFromResponse(response,"bankcardid");
//        boolean checkresult = MyCheckUtil.checkResponseSolo(response, "result", "0");
//        boolean checkuname = MyCheckUtil.checkResponseSolo(response, "uname", user.getUname());
//        boolean checkbankcardno = MyCheckUtil.checkResponseSolo(response,"bankcardno",user.getBankcardno());
//        boolean checkbankcardid = bankcardid != null;
//        boolean resultdb = MyCheckUtil.checkBank(user.getUsertid(),bankidtobankcard);
//        boolean result = checkresult && checkuname && checkbankcardno && checkbankcardid && resultdb;
//        return result;
//    }

    public static String turnandAdd(JSONObject keyvalue) throws InterruptedException, SAXException, IOException {
        String request = "";
        for (String key : keyvalue.keySet()) {
            request += "&" + key + "=" + keyvalue.get(key);
        }
        String response = AccInterface.testAddbankcards(request);
        return response;
    }

    public static void addBankcards(User user, int number) throws IOException, SAXException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        JSONObject keyvalue = new JSONObject();
        keyvalue.put("uname", user.getUname());
        if (number == 1) {
            keyvalue.put("bankcardno", user.getBankcardno());
        } else {
//            keyvalue.put("bankcardno", 2 + String.format("%017d", Long.parseLong(df.format(new Date()))));
            keyvalue.put("bankcardno", "622630" + String.format("%012d", Long.parseLong(df.format(new Date()))));
        }
        keyvalue.put("bank", user.getBank());
        keyvalue.put("cardtp", user.getCardtp());
        keyvalue.put("provno", user.getProvno());
        keyvalue.put("cityno", user.getCityno());
        keyvalue.put("subbank", user.getSubbank());
        keyvalue.put("mobile", user.getMobile());
        keyvalue.put("ylmobile", user.getYlmobile());
        keyvalue.put("checkstatus", user.getCheckstatus());
        String response = AddbankcardsTest.turnandAdd(keyvalue);
        String bankcardid = MyCheckUtil.getValueFromResponse(response, "bankcardid");
        bankidtobankcard.put(bankcardid, keyvalue);
    }

    public static void initBankidtobankcard() {
        bankidtobankcard.clear();
    }

    public static JSONObject getBankidtobankcard() {
        return bankidtobankcard;
    }

}
