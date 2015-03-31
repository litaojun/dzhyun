package com.gw.account.httptest;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/3/27.
 */
public class LotterbindTest {
    private static String uname;
    private static String lotterid;
    private static String nlotterid;
    private static String upass = "11111111";

    @Before
    public static void setUp() throws IOException, SAXException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        uname = "Test" + "测试_" + number;
        lotterid = "Lotter" + "测试_" + number;
        nlotterid = "Nlotter" + "测试_" + number;
        AccInterface.testAdduser("&uname=" + uname + "&upass=" + upass);
    }

    /**
     * 验证返回值包含result=0，验证返回值是否和库里数据一致，验证返回值里面的uname是否和注册的uname一致
     * @param response
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    public static boolean checkALL(String response) throws IOException, SAXException, NoSuchAlgorithmException {
        String getuname = MyCheckUtil.getValueFromResponse(response,"uname");
        String getlotterid = MyCheckUtil.getValueFromResponse(response, "lotterid");
        String getnlotterid = MyCheckUtil.getValueFromResponse(response,"nlotterid");
        boolean lotteriddb = MyCheckUtil.checkExist(getuname,upass,"lotterid",getlotterid);
        boolean nlotteriddb = MyCheckUtil.checkExist(getuname,upass,"nlotterid",getnlotterid);
        boolean unamedb = MyCheckUtil.checkUid(getuname,"");
        boolean lotteridindexdb = MyCheckUtil.checkIndex(getuname,"lotterid",getlotterid);
        boolean nlotteridindexdb = MyCheckUtil.checkIndex(getuname,"nlotterid",getnlotterid);
        boolean result = response.contains("result=0") && getuname.equals(uname) && lotteriddb && nlotteriddb
                && unamedb && lotteridindexdb && nlotteridindexdb;
        return result;
    }

    /**
     * 只填账号名注册
     * @throws NoSuchAlgorithmException
     * @throws SAXException
     * @throws IOException
     */
    @Test
    public static void testUname() throws NoSuchAlgorithmException, SAXException, IOException {
        String response = AccInterface.testLotterbind("&uname=" + uname);
        boolean result = checkALL(response);
        assertTrue("只填账号名注册,msg: " + response , result);
    }

    /**
     * 填写账号名和lotterid注册
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public static void testLid() throws IOException, SAXException, NoSuchAlgorithmException {
        String response = AccInterface.testLotterbind("&uname=" + uname + "lotterid" + lotterid);
        String getlotterid = MyCheckUtil.getValueFromResponse(response,"lotterid");
        boolean result = checkALL(response) && getlotterid.equals(lotterid);
        assertTrue("填写账号名和lotterid注册,msg: " + response , result);
    }

    /**
     * 填写账号名和nlotterid注册
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public static void testnNlid() throws IOException, SAXException, NoSuchAlgorithmException {
        String response = AccInterface.testLotterbind("&uname=" + uname + "nlotterid" + nlotterid);
        String getnlotterid = MyCheckUtil.getValueFromResponse(response,"nlotterid");
        boolean result = checkALL(response) && getnlotterid.equals(nlotterid);
        assertTrue("填写账号名和nlotterid注册,msg: " + response , result);
    }

    /**
     * 填写账号名、lotterid和nlotterid注册
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public static void testnLidNlid() throws IOException, SAXException, NoSuchAlgorithmException {
        String response = AccInterface.testLotterbind("&uname=" + uname + "lotterid" + lotterid + "nlotterid" + nlotterid);
        String getlotterid = MyCheckUtil.getValueFromResponse(response,"lotterid");
        String getnlotterid = MyCheckUtil.getValueFromResponse(response,"nlotterid");
        boolean result = checkALL(response) && getlotterid.equals(lotterid) && getnlotterid.equals(nlotterid);
        assertTrue("填写账号名、lotterid和nlotterid注册,msg: " + response , result);
    }

    /**
     * doflush强制绑定
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public static void testDoflush() throws IOException, SAXException, NoSuchAlgorithmException {
        String prepare = AccInterface.testLotterbind("&uname=" + uname);
        String oldlotterid = MyCheckUtil.getValueFromResponse(prepare,"lotterid");
        String oldnlotterid = MyCheckUtil.getValueFromResponse(prepare,"nlotterid");
        boolean resultprepare = checkALL(prepare);

        String response = AccInterface.testLotterbind("&uname=" + uname + "lotterid" + lotterid + "nlotterid" + nlotterid + "doflush=1");
        String getlotterid = MyCheckUtil.getValueFromResponse(response,"lotterid");
        String getnlotterid = MyCheckUtil.getValueFromResponse(response,"nlotterid");
        boolean resultnow = checkALL(response) && getlotterid.equals(lotterid) && getnlotterid.equals(nlotterid);

        boolean resultflushed = MyCheckUtil.checkNotExist(uname,upass,"lotterid",oldlotterid) && MyCheckUtil.checkNotExist(uname, upass, "nlotterid", oldnlotterid)
                && MyCheckUtil.checkNotIndex(uname,"lotterid",oldlotterid) && MyCheckUtil.checkNotIndex(uname,"nlotterid",oldnlotterid);

        boolean result = resultprepare && resultnow && resultflushed;
        assertTrue("doflush强制绑定,msg: " + response , result);
    }




}
