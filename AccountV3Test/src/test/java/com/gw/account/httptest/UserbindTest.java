package com.gw.account.httptest;

import com.atopcloud.util.MyBdbUtil;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

/**
 * Created by song on 2015/3/4.
 */
public class UserbindTest {
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
    public void testBindEmail() throws IOException, SAXException, InterruptedException {
        boolean result = MyCheckUtil.checkALL(uname,pass_md5_str,"email",email);
        assertTrue("Case2:正常手机绑定",result);
    }

    @Test
    public void testBindMobile() throws IOException, SAXException, InterruptedException {
        boolean result = MyCheckUtil.checkALL(uname,pass_md5_str,"mobile",mobile);
        assertTrue("Case2:正常手机绑定",result);
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
    public void testBindTruename() throws IOException, SAXException, InterruptedException {
        String emailencode = URLEncoder.encode("email=" + email,"UTF-8");
        String params = "&uname=" + uname + "&key=" + emailencode;
        String result = AccInterface.testUserbind(params);
        String getuname = myBdbUtil.getValue("k_1:" + email);
        assertTrue("Case1:正常邮箱绑定,msg: " + result, result.contains("result=0") && getuname.equals(uname.toLowerCase()));
    }

    @Test
    public void testBindNickname() throws IOException, SAXException {
        String nicknameencode = URLEncoder.encode("nickname=" + nickname,"UTF-8");
        String params = "&uname=" + uname + "&key=" + nicknameencode;
        String result = AccInterface.testUserbind(params);
        String getuname = myBdbUtil.getValue("k_9:" + nickname);
        assertTrue("Case3:正常昵称绑定,msg: " + result , result.contains("result=0") && getuname.equals(uname.toLowerCase()));
    }

    @Test
    public void testBindIdcard18() throws IOException, SAXException {
        String idcardencode = URLEncoder.encode("idcard=" + idcard_18,"UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        String getuname = myBdbUtil.getValue("k_10:" + idcard_18);
        assertTrue("Case4:正常18位身份证绑定,msg: " + result , result.contains("result=0") && getuname.equals(uname.toLowerCase()));
    }

    @Test
    public void testBindIdcard18x() throws IOException, SAXException {
        String idcardencode = URLEncoder.encode("idcard=" + idcard_18_x,"UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        String getuname = myBdbUtil.getValue("k_10:" + idcard_18_x);
        assertTrue("Case4:正常18位带x身份证绑定,msg: " + result , result.contains("result=0") && getuname.equals(uname.toLowerCase()));
    }

    @Test
    public void testBindIdcard18X() throws IOException, SAXException {
        String idcardencode = URLEncoder.encode("idcard=" + idcard_18_X,"UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        String getuname = myBdbUtil.getValue("k_10:" + idcard_18_X);
        assertTrue("Case4:正常18位带X身份证绑定,msg: " + result , result.contains("result=0") && getuname.equals(uname.toLowerCase()));
    }

    @Test
    public void testBindIdcard15() throws IOException, SAXException {
        String idcardencode = URLEncoder.encode("idcard=" + idcard_15,"UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        String getuname = myBdbUtil.getValue("k_10:" + idcard_15);
        assertTrue("Case4:正常15位身份证绑定,msg: " + result , result.contains("result=0") && getuname.equals(uname.toLowerCase()));
    }

    @Test
    public void testBindIdcard15x() throws IOException, SAXException {
        String idcardencode = URLEncoder.encode("idcard=" + idcard_15_x,"UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        String getuname = myBdbUtil.getValue("k_10:" + idcard_15_x);
        assertTrue("Case4:正常15位带x身份证绑定,msg: " + result , result.contains("result=0") && getuname.equals(uname.toLowerCase()));
    }

    @Test
    public void testBindIdcard15X() throws IOException, SAXException {
        String idcardencode = URLEncoder.encode("idcard=" + idcard_15_X,"UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        String getuname = myBdbUtil.getValue("k_10:" + idcard_15_X);
        assertTrue("Case4:正常15位带X身份证绑定,msg: " + result , result.contains("result=0") && getuname.equals(uname.toLowerCase()));
    }

    //=================================重复绑定=======================================
    public static void createDupuser() throws IOException, SAXException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        uname_dup = "Test" + "测试" + number;
        AccInterface.testAdduser("&uname=" + uname_dup + "&upass=" + pass_md5_str);
        sleep(1000);
    }

    @Test
    public void testBindDuplicateEmail() throws IOException, SAXException, InterruptedException {
        createDupuser();
        String emailencode = URLEncoder.encode("email=" + email,"UTF-8");
        String params = "&uname=" + uname + "&key=" + emailencode;
        String params2 = "&uname=" + uname_dup + "&key=" + emailencode;
        String result = AccInterface.testUserbind(params);
        String result2 = AccInterface.testUserbind(params2);
        assertTrue("Case1:重复邮箱绑定,msg: " + result2 , result2.contains("result=114"));
    }

    @Test
    public void testBindDuplicateMobile() throws IOException, SAXException, InterruptedException {
        createDupuser();
        String mobileencode = URLEncoder.encode("mobile=" + mobile,"UTF-8");
        String params = "&uname=" + uname + "&key=" + mobileencode;
        String params2 = "&uname=" + uname_dup + "&key=" + mobileencode;
        String result = AccInterface.testUserbind(params);
        String result2 = AccInterface.testUserbind(params2);
        assertTrue("Case2:重复手机绑定,msg: " + result2 , result2.contains("result=114"));
    }

    @Test
    public void testBindDuplicateNickname() throws IOException, SAXException, InterruptedException {
        createDupuser();
        String nicknameencode = URLEncoder.encode("nickname=" + nickname,"UTF-8");
        String params = "&uname=" + uname + "&key=" + nicknameencode;
        String params2 = "&uname=" + uname_dup + "&key=" + nicknameencode;
        String result = AccInterface.testUserbind(params);
        String result2 = AccInterface.testUserbind(params2);
        assertTrue("Case3:重复昵称绑定,msg: " + result2 , result2.contains("result=114"));
    }

    @Test
    public void testBindDuplicateIdcard18() throws IOException, SAXException, InterruptedException {
        createDupuser();
        String idcardencode = URLEncoder.encode("idcard=" + idcard_18,"UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String params2 = "&uname=" + uname_dup + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        String result2 = AccInterface.testUserbind(params2);
        assertTrue("Case4:重复18位身份证绑定,msg: " + result2 , result2.contains("result=114"));
    }

    @Test
    public void testBindDuplicateIdcard18x() throws IOException, SAXException, InterruptedException {
        createDupuser();
        String idcardencode = URLEncoder.encode("idcard=" + idcard_18_x,"UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String params2 = "&uname=" + uname_dup + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        String result2 = AccInterface.testUserbind(params2);
        assertTrue("Case4:重复18位带x身份证绑定,msg: " + result2 , result2.contains("result=114"));
    }

    @Test
    public void testBindDuplicateIdcard18X() throws IOException, SAXException, InterruptedException {
        createDupuser();
        String idcardencode = URLEncoder.encode("idcard=" + idcard_18_X,"UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String params2 = "&uname=" + uname_dup + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        String result2 = AccInterface.testUserbind(params2);
        assertTrue("Case4:重复18位带X身份证绑定,msg: " + result2 , result2.contains("result=114"));
    }

    @Test
    public void testBindDuplicateIdcard15() throws IOException, SAXException, InterruptedException {
        createDupuser();
        String idcardencode = URLEncoder.encode("idcard=" + idcard_15,"UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String params2 = "&uname=" + uname_dup + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        String result2 = AccInterface.testUserbind(params2);
        assertTrue("Case4:重复15位身份证绑定,msg: " + result2 , result2.contains("result=114"));
    }

    @Test
    public void testBindDuplicateIdcard15x() throws IOException, SAXException, InterruptedException {
        createDupuser();
        String idcardencode = URLEncoder.encode("idcard=" + idcard_15_x,"UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String params2 = "&uname=" + uname_dup + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        String result2 = AccInterface.testUserbind(params2);
        assertTrue("Case4:重复15位带x身份证绑定,msg: " + result2 , result2.contains("result=114"));
    }

    @Test
    public void testBindDuplicateIdcard15X() throws IOException, SAXException, InterruptedException {
        createDupuser();
        String idcardencode = URLEncoder.encode("idcard=" + idcard_15_X,"UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String params2 = "&uname=" + uname_dup + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        String result2 = AccInterface.testUserbind(params2);
        assertTrue("Case4:重复15位带X身份证绑定,msg: " + result2 , result2.contains("result=114"));
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
        assertTrue("Case1:超过200字符的邮箱绑定,msg: " + result , result.contains("result=105"));
    }

    @Test
    public void testBindLackofAtEmail() throws IOException, SAXException, InterruptedException {
        String emailencode = URLEncoder.encode("email=" + uname + "126.com","UTF-8");
        String params = "&uname=" + uname + "&key=" + emailencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("Case1:不包含@的邮箱绑定,msg: " + result , result.contains("result=105"));
    }

    @Test
    public void testBindAtisFirstEmail() throws IOException, SAXException, InterruptedException {
        String emailencode = URLEncoder.encode("email=@126.com","UTF-8");
        String params = "&uname=" + uname + "&key=" + emailencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("Case1:@是首字符的邮箱绑定,msg: " + result , result.contains("result=105"));
    }

    @Test
    public void testBindLackofDotEmail() throws IOException, SAXException, InterruptedException {
        String emailencode = URLEncoder.encode("email=" + uname + "@126com","UTF-8");
        String params = "&uname=" + uname + "&key=" + emailencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("Case1:@之后没有.的邮箱绑定,msg: " + result , result.contains("result=105"));
    }

    @Test
    public void testBindNullbtwAtDotEmail() throws IOException, SAXException, InterruptedException {
        String emailencode = URLEncoder.encode("email=" + uname + "@.com","UTF-8");
        String params = "&uname=" + uname + "&key=" + emailencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("Case1:@与.之间不存在其他字符的邮箱绑定,msg: " + result , result.contains("result=105"));
    }

    @Test
    public void testBindNullAftDotEmail() throws IOException, SAXException, InterruptedException {
        String emailencode = URLEncoder.encode("email=" + uname + "@126.","UTF-8");
        String params = "&uname=" + uname + "&key=" + emailencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("Case1:.之后不存在其他字符的邮箱绑定,msg: " + result , result.contains("result=105"));
    }


    //以“1”开头11 位数字（最长15 位数字）
    @Test
    public void testBindOneNotFirstMobile() throws IOException, SAXException, InterruptedException {
        String mobileencode = URLEncoder.encode("mobile=2" + mobile.substring(1),"UTF-8");
        String params = "&uname=" + uname + "&key=" + mobileencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("Case2:不是以1开头的手机绑定,msg: " + result , result.contains("result=104"));
    }

    @Test
    public void testBindLessthan11Mobile() throws IOException, SAXException, InterruptedException {
        String mobileencode = URLEncoder.encode("mobile=" + mobile.substring(0,9),"UTF-8");
        String params = "&uname=" + uname + "&key=" + mobileencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("Case2:少于11位的手机绑定,msg: " + result , result.contains("result=104"));
    }

    @Test
    public void testBindMorethan15Mobile() throws IOException, SAXException, InterruptedException {
        String mobileencode = URLEncoder.encode("mobile=" + mobile + "11111","UTF-8");
        String params = "&uname=" + uname + "&key=" + mobileencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("Case2:多于15位的手机绑定,msg: " + result , result.contains("result=104"));
    }

    @Test
    public void testBindWithCharMobile() throws IOException, SAXException, InterruptedException {
        String mobileencode = URLEncoder.encode("mobile=" + mobile.substring(0,9) + 't',"UTF-8");
        String params = "&uname=" + uname + "&key=" + mobileencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("Case2:带字符非纯数字的手机绑定,msg: " + result , result.contains("result=104"));
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
        assertTrue("Case3:过长昵称绑定,msg: " + result , result.contains("result=0"));
    }

    //15 位数字或18 位数字（末位可为“x”）
    @Test
    public void testBindNot15or18Idcard() throws IOException, SAXException {
        String idcardencode = URLEncoder.encode("idcard=" + idcard_18.substring(2),"UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("Case4:非15或18位身份证绑定,msg: " + result , result.contains("result=0"));
    }

    @Test
    public void testBindTailNotxXCharIdcard() throws IOException, SAXException {
        String idcardencode = URLEncoder.encode("idcard=" + idcard_18.substring(1) + 't',"UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("Case4:末位是非x或者X字符的其它字符身份证绑定,msg: " + result , result.contains("result=0"));
    }

    @Test
    public void testBindNotTailCharIdcard() throws IOException, SAXException {
        StringBuffer stringBuffer = new StringBuffer(idcard_18);
        StringBuffer idcardwithchar = stringBuffer.insert(2,'t');
        String idcardencode = URLEncoder.encode("idcard=" + idcardwithchar.toString().substring(1),"UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("Case4:非末位带字符的身份证绑定,msg: " + result , result.contains("result=0"));
    }

    //=================================空参数绑定=======================================
    @Test
    public void testBindNullEmail() throws IOException, SAXException, InterruptedException {
        String emailencode = URLEncoder.encode("email=","UTF-8");
        String params = "&uname=" + uname + "&key=" + emailencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("Case1:空邮箱绑定,msg: " + result , result.contains("result=0"));
    }

    @Test
    public void testBindNullMobile() throws IOException, SAXException, InterruptedException {
        String mobileencode = URLEncoder.encode("mobile=","UTF-8");
        String params = "&uname=" + uname + "&key=" + mobileencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("Case2:空手机绑定,msg: " + result , result.contains("result=0"));
    }

    @Test
    public void testBindNullNickname() throws IOException, SAXException {
        String nicknameencode = URLEncoder.encode("nickname=","UTF-8");
        String params = "&uname=" + uname + "&key=" + nicknameencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("Case3:空昵称绑定,msg: " + result , result.contains("result=0"));
    }

    @Test
    public void testBindNullIdcard() throws IOException, SAXException {
        String idcardencode = URLEncoder.encode("idcard=","UTF-8");
        String params = "&uname=" + uname + "&key=" + idcardencode;
        String result = AccInterface.testUserbind(params);
        assertTrue("Case4:空身份证绑定,msg: " + result , result.contains("result=0"));
    }
}
