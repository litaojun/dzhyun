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
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/3/27.
 */
public class LotterbindTest {
    private static final Log LOG = LogFactory.getLog(LotterbindTest.class);
    private User user = new User();

    @BeforeClass
    public static void globalInit() {
        MyCheckUtil.initialize();
    }

    @Before
    public void setUp() throws IOException, SAXException, InterruptedException {
        user.createUser();
    }

    //=================================dosoap为0=======================================
    /**
     * 填写账号名和lotterid注册，有lotterid，没有dosoap（默认为0），表示使用本地的lotterid
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testLid() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = checkALL("&uname=" + user.getUname() + "&lotterid=" + user.getLotterid());
        assertTrue("填写账号名和lotterid注册", result);
    }

    /**
     * 填写账号名、lotterid和nlotterid注册，有lotterid，没有dosoap（默认为0），表示使用本地的lotterid
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testnLidNlid() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = checkALL("&uname=" + user.getUname() + "&lotterid=" + user.getLotterid() + "&nlotterid=" + user.getNlotterid());
        assertTrue("填写账号名、lotterid和nlotterid注册", result);
    }

    //=================================dosoap为1=======================================
    /**
     * 填写账号名和dosoap注册，表示调用调用大彩接口
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testDosoap() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = checkALL("&uname=" + user.getUname() + "&dosoap=1");
        assertTrue("填写账号名和dosoap注册，表示调用调用大彩接口", result);
    }

//    /**
//     * 填写账号名、lotterid和dosoap注册，表示调用调用大彩接口
//     * @throws IOException
//     * @throws SAXException
//     * @throws NoSuchAlgorithmException
//     */
//    @Test
//    public void testLidDosoap() throws IOException, SAXException, NoSuchAlgorithmException {
//        boolean result = checkALL("&uname=" + uname + "&lotterid=" + lotterid + "&dosoap=1");
//        assertTrue("填写账号名、lotterid和dosoap注册，表示调用调用大彩接口", result);
//    }
//
//    /**
//     * 填写账号名、lotterid、nlotterid和dosoap注册，表示调用调用大彩接口
//     * @throws IOException
//     * @throws SAXException
//     * @throws NoSuchAlgorithmException
//     */
//    @Test
//    public void testnLidNlidDosoap() throws IOException, SAXException, NoSuchAlgorithmException {
//        boolean result = checkALL("&uname=" + uname + "&lotterid=" + lotterid + "&nlotterid=" + nlotterid + "&dosoap=1");
//        assertTrue("填写账号名、lotterid、nlotterid和dosoap注册，表示调用调用大彩接口", result);
//    }

    //=================================重复绑定=======================================
    /**
     * 重复绑定lotterid
     * @throws NoSuchAlgorithmException
     * @throws SAXException
     * @throws IOException
     */
    @Test
    public void BindDuplicateLid() throws NoSuchAlgorithmException, SAXException, IOException {
        boolean result1 = checkALL("&uname=" + user.getUname() + "&lotterid=" + user.getLotterid());
        boolean result2 = checkDuplicate("&uname=" + user.getUname() + "&lotterid=" + user.getLotterid() + "2");
        boolean result = result1 && !result2;
        assertTrue("重复绑定lotterid", result);
    }

    /**
     * 重复绑定lotterid和nlotterid
     * @throws NoSuchAlgorithmException
     * @throws SAXException
     * @throws IOException
     */
    @Test
    public void BindDuplicateLidNlid() throws NoSuchAlgorithmException, SAXException, IOException {
        boolean result1 = checkALL("&uname=" + user.getUname() + "&lotterid=" + user.getLotterid() + "&nlotterid=" + user.getNlotterid());
        boolean result2 = checkDuplicate("&uname=" + user.getUname() + "&lotterid=" + user.getLotterid() + "2" + "&nlotterid=" + user.getNlotterid() + "2");
        boolean result = result1 && !result2;
        assertTrue("重复绑定lotterid和nlotterid", result);
    }

    @Test
    public void BindDuplicateLidDiffNlid() throws NoSuchAlgorithmException, SAXException, IOException {
        boolean result1 = checkALL("&uname=" + user.getUname() + "&lotterid=" + user.getLotterid() + "&nlotterid=" + user.getNlotterid());
        boolean result2 = checkDuplicate("&uname=" + user.getUname() + "&lotterid=" + user.getLotterid() + "&nlotterid=" + user.getNlotterid() + "2");
        boolean result = result1 && !result2;
        assertTrue("重复绑定lotterid和nlotterid", result);
    }

    /**
     * 重复绑定lotterid，dosoap为1。调用大彩接口时lotterid不能过长
     * @throws NoSuchAlgorithmException
     * @throws SAXException
     * @throws IOException
     */
    @Test
    public void BindDuplicateLidDosoap() throws NoSuchAlgorithmException, SAXException, IOException {
        boolean result1 = checkALL("&uname=" + user.getUname() + "&lotterid=" + user.getLotterid() + "&dosoap=1");
        boolean result2 = checkDuplicate("&uname=" + user.getUname() + "&lotterid=" + user.getLotterid() + "2" + "&dosoap=1");
        boolean result = result1 && !result2;
        assertTrue("重复绑定lotterid", result);
    }

    /**
     * 重复绑定lotterid和nlotterid，dosoap为1
     * @throws NoSuchAlgorithmException
     * @throws SAXException
     * @throws IOException
     */
    @Test
    public void BindDuplicateLidNlidDosoap() throws NoSuchAlgorithmException, SAXException, IOException {
        boolean result1 = checkALL("&uname=" + user.getUname() + "&lotterid=" + user.getLotterid() + "&nlotterid=" + user.getNlotterid());
        boolean result2 = checkDuplicate("&uname=" + user.getUname() + "&lotterid=" + user.getLotterid() + "2" + "&nlotterid=" + user.getNlotterid() + "2" + "&dosoap=1");
        boolean result = result1 && !result2;
        assertTrue("重复绑定lotterid和nlotterid", result);
    }

    public boolean checkDuplicate(String params) throws IOException, SAXException {
        String response = AccInterface.testLotterbind(params);
        boolean checkcode = MyCheckUtil.getCode(response) == 2;
        boolean checkuname = MyCheckUtil.getValueFromResponse(response,"uname").equals(user.getUname());
        String getlotterid = MyCheckUtil.getValueFromResponse(response,"lotterid");
        String getnlotterid = MyCheckUtil.getValueFromResponse(response,"nlotterid");
        boolean checklotterid = true;
        boolean checknlotterid = true;
        if (params.contains("lotterid")) {
            String plotterid = MyCheckUtil.getValueFromResponse(params,"lotterid");
            checklotterid = getlotterid.equals(plotterid);
        }
        if (params.contains("nlotterid")) {
            String pnlotterid = MyCheckUtil.getValueFromResponse(params,"nlotterid");
            checknlotterid = getnlotterid.equals(pnlotterid);
        }
        boolean result = checkcode && checkuname && checklotterid && checknlotterid;
        return result;
    }

//    //=================================强制绑定=======================================
//    /**
//     * 强制绑定lotterid
//     * @throws NoSuchAlgorithmException
//     * @throws SAXException
//     * @throws IOException
//     */
//    @Test
//    public void ForceBindDuplicateLid() throws NoSuchAlgorithmException, SAXException, IOException {
//        boolean result1 = checkALL("&uname=" + uname + "&lotterid=" + lotterid);
//        boolean result2 = checkALL("&uname=" + uname + "&lotterid=" + lotterid + "2" + "&doflush=1");
//        boolean result = result1 && result2;
//        assertTrue("强制绑定lotterid", result);
//    }
//
//    /**
//     * 强制绑定lotterid和nlotterid
//     * @throws NoSuchAlgorithmException
//     * @throws SAXException
//     * @throws IOException
//     */
//    @Test
//    public void ForceBindDuplicateLidNlid() throws NoSuchAlgorithmException, SAXException, IOException {
//        boolean result1 = checkALL("&uname=" + uname + "&lotterid=" + lotterid + "&nlotterid=" + nlotterid);
//        boolean result2 = checkDuplicate("&uname=" + uname + "&lotterid=" + lotterid + "2" + "&nlotterid=" + nlotterid + "2" + "&doflush=1");
//        boolean result = result1 && result2;
//        assertTrue("强制绑定lotterid和nlotterid", result);
//    }
//
//    /**
//     * 强制绑定lotterid，dosoap为1
//     * @throws NoSuchAlgorithmException
//     * @throws SAXException
//     * @throws IOException
//     */
//    @Test
//    public void ForceBindDuplicateLidDosoap() throws NoSuchAlgorithmException, SAXException, IOException {
//        boolean result1 = checkALL("&uname=" + uname + "&lotterid=" + lotterid + "&dosoap=1");
//        boolean result2 = checkDuplicate("&uname=" + uname + "&lotterid=" + lotterid + "2" + "&dosoap=1" + "&doflush=1");
//        boolean result = result1 && result2;
//        assertTrue("强制绑定lotterid", result);
//    }
//
//    /**
//     * 强制绑定lotterid和nlotterid，dosoap为1
//     * @throws NoSuchAlgorithmException
//     * @throws SAXException
//     * @throws IOException
//     */
//    @Test
//    public void ForceBindDuplicateLidNlidDosoap() throws NoSuchAlgorithmException, SAXException, IOException {
//        boolean result1 = checkALL("&uname=" + uname + "&lotterid=" + lotterid + "&nlotterid=" + nlotterid);
//        boolean result2 = checkDuplicate("&uname=" + uname + "&lotterid=" + lotterid + "2" + "&nlotterid=" + nlotterid + "2" + "&dosoap=1" + "&doflush=1");
//        boolean result = result1 && result2;
//        assertTrue("强制绑定lotterid和nlotterid", result);
//    }

    /**
     * 验证返回值包含result=0，验证返回值是否和库里数据一致，验证返回值里面的uname是否和注册的uname一致
     * @param params
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    public boolean checkALL(String params) throws IOException, SAXException, NoSuchAlgorithmException {
        String response = AccInterface.testLotterbind(params);
        boolean checkcode = MyCheckUtil.getCode(response)==1;
        boolean checkresult = MyCheckUtil.checkResponseSolo(response,"result","0");
        boolean checkuname = MyCheckUtil.checkResponseSolo(response, "uname", user.getUname());
        String getlotterid = MyCheckUtil.getValueFromResponse(response, "lotterid");
        String getnlotterid = MyCheckUtil.getValueFromResponse(response, "nlotterid");
        boolean lotteridudb = MyCheckUtil.checkU(user.getUsertid(), URLDecoder.decode(user.getUpass(),"UTF-8"));
        boolean lotteridukeydb = MyCheckUtil.checkUkey(user.getUsertid(), "lotterid", getlotterid);
//        boolean nlotteriddb = MyCheckUtil.checkExist(usertid,upass,"nlotterid",nlotterid);     nlotterid不是key
        boolean lotteridindexdb = MyCheckUtil.checkIndex(user.getUsertid(),"lotterid",getlotterid);
//        boolean nlotteridindexdb = MyCheckUtil.checkIndex(usertid,"nlotterid",nlotterid);      nlotterid不是key
//        boolean unamedb = MyCheckUtil.checkUid(getuname,"");         V3不支持
        boolean checklotterid = true;
        boolean checknlotterid = true;
        if (params.contains("lotterid")) {
            String plotterid = MyCheckUtil.getValueFromResponse(params,"lotterid");
            checklotterid = getlotterid.equals(plotterid);
        }
        if (params.contains("nlotterid")) {
            String pnlotterid = MyCheckUtil.getValueFromResponse(params,"nlotterid");
            checknlotterid = getnlotterid.equals(pnlotterid);
        }
        boolean result = checkcode && checkresult && checkuname && checklotterid && checknlotterid && lotteridudb
                && lotteridukeydb && lotteridindexdb;
        return result;
    }


    //=================================错误绑定=======================================
    /**
     * 只填账号名注册，必须有本地lotterid或者调用大彩接口
     * @throws NoSuchAlgorithmException
     * @throws SAXException
     * @throws IOException
     */
    @Test
    public void testUname() throws NoSuchAlgorithmException, SAXException, IOException {
        String response = AccInterface.testLotterbind("&uname=" + user.getUname());
        boolean result = MyCheckUtil.getCode(response)==-100 && MyCheckUtil.getValueFromResponse(response,"uname").equals(user.getUname());
        assertTrue("只填账号名注册", result);
    }

    /**
     * 填写账号名和nlotterid注册，必须有本地lotterid或者调用大彩接口
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testnNlid() throws IOException, SAXException, NoSuchAlgorithmException {
        String response = AccInterface.testLotterbind("&uname=" + user.getUname() + "&nlotterid=" + user.getNlotterid());
        boolean result = MyCheckUtil.getCode(response)==-100 && MyCheckUtil.getValueFromResponse(response,"uname").equals(user.getUname());
        assertTrue("填写账号名和nlotterid注册", result);
    }
}
