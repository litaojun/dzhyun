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

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/4/17.
 */
public class DelbankcardsTest {
    private static final Log LOG = LogFactory.getLog(DelbankcardsTest.class);
    private User user = new User();

    @BeforeClass
    public static void globalInit() {
        MyCheckUtil.initialize();
    }

    @Before
    public void setUp() throws IOException, SAXException, InterruptedException {
        user.createUser();
        MyCheckUtil.bindKey(user.getUname(),"idcard",user.getIdcard());
        MyCheckUtil.bindKey(user.getUname(),"truename",user.getTruename());
        AddbankcardsTest.initBankidtobankcard();
        AddbankcardsTest.addBankcards(user,1);
    }

    /**
     * 依次删除所有银行卡，并验证正确性
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testDelBankcards() throws IOException, SAXException {
        boolean result = true;
        for (String key:AddbankcardsTest.getBankidtobankcard().keySet()) {
            String response = AccInterface.testDelbankcards("&uname=" + user.getUname() + "&bankcardid=" + key);
            result = result && checkALL(response,key);
        }
        assertTrue("依次删除所有银行卡，并验证正确性",result);
    }



    public boolean checkALL(String reponse,String bankcardid) throws InvalidProtocolBufferException {
        boolean checkresult = MyCheckUtil.checkResponseSolo(reponse,"result","0");
        boolean checkuname = MyCheckUtil.checkResponseSolo(reponse,"uname",user.getUname());
        boolean checkdelbank = MyCheckUtil.checkDelBank(user.getUsertid(), bankcardid);
        boolean result = checkresult && checkuname && checkdelbank;
        return result;
    }
}
