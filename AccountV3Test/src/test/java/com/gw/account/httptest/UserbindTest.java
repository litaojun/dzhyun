package com.gw.account.httptest;

import com.atopcloud.util.MyBdbUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;
import redis.clients.jedis.BinaryJedis;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

/**
 * Created by song on 2015/3/4.
 */
public class UserbindTest {
    private static final Log LOG = LogFactory.getLog(UserbindTest.class);
    private static String uname;
    private static String email;
    private static String mobile;
    private static String lotterid;
    private static String deviceid;
    private static String pushid;
    private static String nlotterid;
    private static String truename;
    private static String nickname;
    private static String idcard_18;
    private static String idcard_18_x;
    private static String idcard_18_X;
    private static String idcard_15;
    private static String idcard_15_x;
    private static String idcard_15_X;
    private static String qqid;
    private static String lcb;
    private static String wxid;
    //private static String bankcard;
    //private static String
    private static String xcid;
    private static String pass_md5_str = "11111111";
    private static String uname_dup;
    private static MyBdbUtil myBdbUtil = new MyBdbUtil();


    @BeforeClass
    public static void globalInit() {
        MyCheckUtil.initialize();
    }

    @Before
    public void setUp() throws IOException, SAXException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        uname = "Test" + "测试" + number;
        email = "Test" + number + "@126.com";
        mobile = "188" + number;
        lotterid = "Lotter" + number;
        deviceid = "Device" + number;
        pushid = "Push" + number;
        nlotterid = "Nlotter" + number;
        truename = "True" + "测试" + number;
        nickname = "Nick" + "测试" + number;
        idcard_18 = String.format("%018d", Long.parseLong(number));
        idcard_18_x = String.format("%017d",Long.parseLong(number)) + "x";
        idcard_18_X = String.format("%017d", Long.parseLong(number)) + "X";
        idcard_15 = String.format("%015d", Long.parseLong(number));
        idcard_15_x = String.format("%014d", Long.parseLong(number)) + "x";
        idcard_15_X = String.format("%014d", Long.parseLong(number)) + "X";
        qqid = "qq" + number;
        lcb = "lcb" + number;
        wxid = "wx" + number;
        xcid= "xc" + number;
        AccInterface.testAdduser("&uname=" + uname + "&upass=" + pass_md5_str);
        sleep(1000);
    }


    //=================================正常绑定=======================================
    @Test
    public void testBindEmail() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = MyCheckUtil.checkALL(uname, pass_md5_str, "email", email);
        assertTrue("正常邮箱绑定", result);
    }

    @Test
    public void testBindMobile() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = MyCheckUtil.checkALL(uname, pass_md5_str, "mobile", mobile);
        assertTrue("正常手机绑定",result);
    }

//    @Test
//    public void testBindLotterid() throws IOException, SAXException, InterruptedException {
//        String emailencode = URLEncoder.encode("lotterid=" + lotterid,"UTF-8");
//        String params = "&uname=" + uname + "&key=" + emailencode;
//        String result = AccInterface.testUserbind(params);
//        String getuname = myBdbUtil.getValue("k_3:" + lotterid);
//        assertTrue("Case1:正常彩票账户绑定,msg: " + result, result.contains("result=0") && getuname.equals(uname.toLowerCase()));
//    }

//    @Test
//    public void testBindDeviceid() throws IOException, SAXException, InterruptedException {
//        String emailencode = URLEncoder.encode("deviceid=" + deviceid,"UTF-8");
//        String params = "&uname=" + uname + "&key=" + emailencode;
//        String result = AccInterface.testUserbind(params);
//        String getuname = myBdbUtil.getValue("k_4:" + deviceid);
//        assertTrue("Case1:正常移动设备ID绑定,msg: " + result, result.contains("result=0") && getuname.equals(uname.toLowerCase()));
//    }

//    @Test
//    public void testBindPushid() throws IOException, SAXException, InterruptedException {
//        String emailencode = URLEncoder.encode("pushid=" + pushid,"UTF-8");
//        String params = "&uname=" + uname + "&key=" + emailencode;
//        String result = AccInterface.testUserbind(params);
//        String getuname = myBdbUtil.getValue("k_5:" + pushid);
//        assertTrue("Case1:正常手机推送ID绑定,msg: " + result, result.contains("result=0") && getuname.equals(uname.toLowerCase()));
//    }

//    @Test
//    public void testBindNlotterid() throws IOException, SAXException, InterruptedException {
//        String emailencode = URLEncoder.encode("nlotterid=" + nlotterid,"UTF-8");
//        String params = "&uname=" + uname + "&key=" + emailencode;
//        String result = AccInterface.testUserbind(params);
//        String getuname = myBdbUtil.getValue("k_6:" + nlotterid);
//        assertTrue("Case1:正常彩票账户ID绑定,msg: " + result, result.contains("result=0") && getuname.equals(uname.toLowerCase()));
//    }

    @Test
    public void testBindTruename() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = MyCheckUtil.checkALLNotKey(uname, pass_md5_str, "truename", truename);
        assertTrue("正常真实姓名绑定",result);
    }

    @Test
    public void testBindNickname() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = MyCheckUtil.checkALL(uname, pass_md5_str, "nickname", nickname);
        assertTrue("正常昵称绑定",result);
    }

    @Test
    public void testBindIdcard18() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = MyCheckUtil.checkALL(uname, pass_md5_str, "idcard", idcard_18);
        assertTrue("正常18位身份证绑定",result);
    }

    @Test
    public void testBindIdcard18x() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = MyCheckUtil.checkALL(uname, pass_md5_str, "idcard", idcard_18_x);
        assertTrue("正常18位带x身份证绑定",result);
    }

    @Test
    public void testBindIdcard18X() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = MyCheckUtil.checkALL(uname, pass_md5_str, "idcard", idcard_18_x);
        assertTrue("正常18位带X身份证绑定",result);
    }

    @Test
    public void testBindIdcard15() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = MyCheckUtil.checkALL(uname, pass_md5_str, "idcard", idcard_18_x);
        assertTrue("正常15位身份证绑定",result);
    }

    @Test
    public void testBindIdcard15x() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = MyCheckUtil.checkALL(uname, pass_md5_str, "idcard", idcard_18_x);
        assertTrue("正常15位带x身份证绑定",result);
    }

    @Test
    public void testBindIdcard15X() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = MyCheckUtil.checkALL(uname, pass_md5_str, "idcard", idcard_18_x);
        assertTrue("正常15位带X身份证绑定",result);
    }

    //=================================重复绑定=======================================
    @Test
    public void testBindDuplicateEmail() throws IOException, SAXException, InterruptedException {
        boolean result = checkDuplicateKeyBind("email", email);
        assertTrue("重复邮箱绑定",result);
    }

    @Test
    public void testBindDuplicateMobile() throws IOException, SAXException, InterruptedException {
        boolean result = checkDuplicateKeyBind("mobile", mobile);
        assertTrue("重复手机绑定",result);
    }

    @Test
    public void testBindDuplicateTruename() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        createDupUser();
        String keyencode = URLEncoder.encode("truename=" + truename,"UTF-8");
        String params1 = "&uname=" + uname + "&key=" + keyencode;
        String params2 = "&uname=" + uname_dup + "&key=" + keyencode;
        String response1 = AccInterface.testUserbind(params1);
        String response2 = AccInterface.testUserbind(params2);
        boolean result = response2.contains("result=0");
        assertTrue("重复真名绑定",result);
    }

    @Test
    public void testBindDuplicateNickname() throws IOException, SAXException, InterruptedException {
        boolean result = checkDuplicateKeyBind("nickname", nickname);
        assertTrue("重复昵称绑定",result);
    }

    @Test
    public void testBindDuplicateIdcard18() throws IOException, SAXException, InterruptedException {
        boolean result = checkDuplicateKeyBind("idcard", idcard_18);
        assertTrue("重复18位身份证绑定",result);
    }

    @Test
    public void testBindDuplicateIdcard18x() throws IOException, SAXException, InterruptedException {
        boolean result = checkDuplicateKeyBind("idcard", idcard_18_x);
        assertTrue("重复18位带x身份证绑定",result);
    }

    @Test
    public void testBindDuplicateIdcard18X() throws IOException, SAXException, InterruptedException {
        boolean result = checkDuplicateKeyBind("idcard", idcard_18_X);
        assertTrue("重复18位带X身份证绑定",result);
    }

    @Test
    public void testBindDuplicateIdcard15() throws IOException, SAXException, InterruptedException {
        boolean result = checkDuplicateKeyBind("idcard", idcard_15);
        assertTrue("重复15位身份证绑定",result);
    }

    @Test
    public void testBindDuplicateIdcard15x() throws IOException, SAXException, InterruptedException {
        boolean result = checkDuplicateKeyBind("idcard", idcard_15_x);
        assertTrue("重复15位带x身份证绑定",result);
    }

    @Test
    public void testBindDuplicateIdcard15X() throws IOException, SAXException, InterruptedException {
        boolean result = checkDuplicateKeyBind("idcard", idcard_15_X);
        assertTrue("重复15位带X身份证绑定",result);
    }

    public static void createDupUser() throws IOException, SAXException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        uname_dup = "Test" + "测试" + number;
        AccInterface.testAdduser("&uname=" + uname_dup + "&upass=" + pass_md5_str);
        sleep(1000);
    }

    public static boolean checkDuplicateKeyBind(String keytp, String key) throws InterruptedException, SAXException, IOException {
        createDupUser();
        String keyencode = URLEncoder.encode(keytp + "=" + key,"UTF-8");
        String params = "&uname=" + uname + "&key=" + keyencode;
        String params2 = "&uname=" + uname_dup + "&key=" + keyencode;
        String response = AccInterface.testUserbind(params);
        String response2 = AccInterface.testUserbind(params2);
        boolean result = response2.contains("result=114");
        if (!result) {
            LOG.error("checkDuplicateKey:" + response2);
        }
        return result;
    }

    //=================================强制绑定=======================================
    @Test
    public void testForceBindDuplicateEmail() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkForceBindDuplicateKey("email", email);
        assertTrue("强制绑定重复邮箱",result);
    }

    @Test
    public void testForceBindDuplicateMobile() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkForceBindDuplicateKey("mobile", mobile);
        assertTrue("强制重复手机绑定",result);
    }

    @Test
    public void testForceBindDuplicateNickname() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkForceBindDuplicateKey("nickname", nickname);
        assertTrue("强制重复昵称绑定",result);
    }

    @Test
    public void testForceBindDuplicateIdcard18() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkForceBindDuplicateKey("idcard", idcard_18);
        assertTrue("强制重复18位身份证绑定",result);
    }

    @Test
    public void testForceBindDuplicateIdcard18x() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkForceBindDuplicateKey("idcard", idcard_18_x);
        assertTrue("强制重复18位带x身份证绑定",result);
    }

    @Test
    public void testForceBindDuplicateIdcard18X() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkForceBindDuplicateKey("idcard", idcard_18_X);
        assertTrue("强制重复18位带X身份证绑定",result);
    }

    @Test
    public void testForceBindDuplicateIdcard15() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkForceBindDuplicateKey("idcard", idcard_15);
        assertTrue("强制重复15位身份证绑定",result);
    }

    @Test
    public void testForceBindDuplicateIdcard15x() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkForceBindDuplicateKey("idcard", idcard_15_x);
        assertTrue("强制重复15位带x身份证绑定",result);
    }

    @Test
    public void testForceBindDuplicateIdcard15X() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkForceBindDuplicateKey("idcard", idcard_15_X);
        assertTrue("强制重复15位带X身份证绑定",result);
    }

    public static boolean checkForceBindDuplicateKey(String keytp, String key) throws InterruptedException, SAXException, IOException, NoSuchAlgorithmException {
        createDupUser();
        String keyencode = URLEncoder.encode(keytp + "=" + key,"UTF-8");
        String params1 = "&uname=" + uname + "&key=" + keyencode;
        String params2 = "&uname=" + uname_dup + "&key=" + keyencode + "&doflush=1";
        String response1 = AccInterface.testUserbind(params1);
        String response2 = AccInterface.testUserbind(params2);
        boolean checkresponse1 = MyCheckUtil.checkResponse(response1,uname,keytp,key);
        boolean checkresponse2 = MyCheckUtil.checkResponse(response2,uname_dup,keytp,key);

        boolean checknotexist1 = MyCheckUtil.checkNotExist(uname,pass_md5_str,keytp,key);
        boolean checkexist2 = MyCheckUtil.checkExist(uname_dup,pass_md5_str,keytp,key);

        boolean checknotindex1 = MyCheckUtil.checkNotIndex(uname,keytp,key);
        boolean checkindex2 = MyCheckUtil.checkIndex(uname_dup,keytp,key);

        boolean checkuid1 = MyCheckUtil.checkUid(uname);
        boolean checkuid2 = MyCheckUtil.checkUid(uname_dup);

        boolean result = checkresponse1 && checkresponse2 && checknotexist1 && checkexist2 && checknotindex1 && checkindex2 && checkuid1 && checkuid2;
        return result;
    }

    //=================================错误绑定=======================================
    //最长200 个字符（包含“@”且“@”不是首字符、“@”之后有“.”且“@”与“.”之间存在其他字符、“.”之后存在其他字符）
    @Test
    public void testBindTooLongEmail() throws IOException, SAXException, InterruptedException {
        StringBuffer toolongemail = new StringBuffer(email);
        for (int i=0; i<=192; i++) {
            toolongemail.append("t");
        }
        String emailencode = URLEncoder.encode("email=" + toolongemail.toString() + "@126.com","UTF-8");
        String params = "&uname=" + uname + "&key=" + emailencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("超过200字符的邮箱绑定,msg: " + result , result.contains("result=105"));
    }

    @Test
    public void testBindLackofAtEmail() throws IOException, SAXException, InterruptedException {
        String emailencode = URLEncoder.encode("email=" + uname + "126.com","UTF-8");
        String params = "&uname=" + uname + "&key=" + emailencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("不包含@的邮箱绑定,msg: " + result , result.contains("result=105"));
    }

    @Test
    public void testBindAtisFirstEmail() throws IOException, SAXException, InterruptedException {
        String emailencode = URLEncoder.encode("email=@126.com","UTF-8");
        String params = "&uname=" + uname + "&key=" + emailencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("@是首字符的邮箱绑定,msg: " + result , result.contains("result=105"));
    }

    @Test
    public void testBindLackofDotEmail() throws IOException, SAXException, InterruptedException {
        String emailencode = URLEncoder.encode("email=" + uname + "@126com","UTF-8");
        String params = "&uname=" + uname + "&key=" + emailencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("@之后没有.的邮箱绑定,msg: " + result , result.contains("result=105"));
    }

    @Test
    public void testBindNullbtwAtDotEmail() throws IOException, SAXException, InterruptedException {
        String emailencode = URLEncoder.encode("email=" + uname + "@.com","UTF-8");
        String params = "&uname=" + uname + "&key=" + emailencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("@与.之间不存在其他字符的邮箱绑定,msg: " + result , result.contains("result=105"));
    }

    @Test
    public void testBindNullAftDotEmail() throws IOException, SAXException, InterruptedException {
        String emailencode = URLEncoder.encode("email=" + uname + "@126.","UTF-8");
        String params = "&uname=" + uname + "&key=" + emailencode;
        String result = AccInterface.testUserbind(params);
        assertTrue(".之后不存在其他字符的邮箱绑定,msg: " + result , result.contains("result=105"));
    }


    //以“1”开头11 位数字（最长15 位数字）
    @Test
    public void testBindOneNotFirstMobile() throws IOException, SAXException, InterruptedException {
        String mobileencode = URLEncoder.encode("mobile=2" + mobile.substring(1),"UTF-8");
        String params = "&uname=" + uname + "&key=" + mobileencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("不是以1开头的手机绑定,msg: " + result , result.contains("result=104"));
    }

    @Test
    public void testBindLessthan11Mobile() throws IOException, SAXException, InterruptedException {
        String mobileencode = URLEncoder.encode("mobile=" + mobile.substring(0,9),"UTF-8");
        String params = "&uname=" + uname + "&key=" + mobileencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("少于11位的手机绑定,msg: " + result , result.contains("result=104"));
    }

    @Test
    public void testBindMorethan15Mobile() throws IOException, SAXException, InterruptedException {
        String mobileencode = URLEncoder.encode("mobile=" + mobile + "11111","UTF-8");
        String params = "&uname=" + uname + "&key=" + mobileencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("多于15位的手机绑定,msg: " + result , result.contains("result=104"));
    }

    @Test
    public void testBindWithCharMobile() throws IOException, SAXException, InterruptedException {
        String mobileencode = URLEncoder.encode("mobile=" + mobile.substring(0,9) + 't',"UTF-8");
        String params = "&uname=" + uname + "&key=" + mobileencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("带字符非纯数字的手机绑定,msg: " + result , result.contains("result=104"));
    }

    //最大长度200 个字符
    @Test
    public void testBindTooLongTruename() throws IOException, SAXException {
        StringBuffer toolongtruename = new StringBuffer(truename);
        for (int i=0; i<=192; i++) {
            toolongtruename.append('t');
        }
        String truenameencode = URLEncoder.encode("truename=" + toolongtruename.toString(),"UTF-8");
        String params = "&uname=" + uname + "&key=" + truenameencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("过长真名绑定,msg: " + result , result.contains("result=0"));
    }

    //最大长度200 个字符
    @Test
    public void testBindTooLongNickname() throws IOException, SAXException {
        StringBuffer toolongnickname = new StringBuffer(nickname);
        for (int i=0; i<=192; i++) {
            toolongnickname.append('t');
        }
        String nicknameencode = URLEncoder.encode("nickname=" + toolongnickname.toString(),"UTF-8");
        String params = "&uname=" + uname + "&key=" + nicknameencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("过长昵称绑定,msg: " + result , result.contains("result=0"));
    }

    //15 位数字或18 位数字（末位可为“x”）
    @Test
    public void testBindNot15or18Idcard() throws IOException, SAXException {
        String idcardencode = URLEncoder.encode("idcard=" + idcard_18.substring(2),"UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("非15或18位身份证绑定,msg: " + result , result.contains("result=0"));
    }

    @Test
    public void testBindTailNotxXCharIdcard() throws IOException, SAXException {
        String idcardencode = URLEncoder.encode("idcard=" + idcard_18.substring(1) + 't',"UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("末位是非x或者X字符的其它字符身份证绑定,msg: " + result , result.contains("result=0"));
    }

    @Test
    public void testBindNotTailCharIdcard() throws IOException, SAXException {
        StringBuffer stringBuffer = new StringBuffer(idcard_18);
        StringBuffer idcardwithchar = stringBuffer.insert(2,'t');
        String idcardencode = URLEncoder.encode("idcard=" + idcardwithchar.toString().substring(1),"UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("非末位带字符的身份证绑定,msg: " + result , result.contains("result=0"));
    }

    //=================================空参数绑定=======================================
    @Test
    public void testBindNullEmail() throws IOException, SAXException, InterruptedException {
        String emailencode = URLEncoder.encode("email=","UTF-8");
        String params = "&uname=" + uname + "&key=" + emailencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("空邮箱绑定,msg: " + result , result.contains("result=0"));
    }

    @Test
    public void testBindNullMobile() throws IOException, SAXException, InterruptedException {
        String mobileencode = URLEncoder.encode("mobile=","UTF-8");
        String params = "&uname=" + uname + "&key=" + mobileencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("空手机绑定,msg: " + result , result.contains("result=0"));
    }

    @Test
    public void testBindNullNickname() throws IOException, SAXException {
        String nicknameencode = URLEncoder.encode("nickname=","UTF-8");
        String params = "&uname=" + uname + "&key=" + nicknameencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("空昵称绑定,msg: " + result , result.contains("result=0"));
    }

    @Test
    public void testBindNullIdcard() throws IOException, SAXException {
        String idcardencode = URLEncoder.encode("idcard=","UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("空身份证绑定,msg: " + result , result.contains("result=0"));
    }
}
