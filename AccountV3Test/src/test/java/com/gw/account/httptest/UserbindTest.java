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
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/3/4.
 */
//返回信息不查result
public class UserbindTest {
    private static final Log LOG = LogFactory.getLog(UserbindTest.class);
    private static User user = new User();
    private static String idcard_18;
    private static String idcard_18_x;
    private static String idcard_18_X;
    private static String idcard_15;
    private static String idcard_15_x;
    private static String idcard_15_X;
    private static String uname_dup;
    private static String usertid_dup;


    @BeforeClass
    public static void globalInit() {
        MyCheckUtil.initialize();
    }

    @Before
    public void setUp() throws IOException, SAXException, InterruptedException {
        user.createUser();
        idcard_18 = String.format("%018d", Long.parseLong(user.getNumber()));
        idcard_18_x = String.format("%017d",Long.parseLong(user.getNumber())) + "x";
        idcard_18_X = String.format("%017d", Long.parseLong(user.getNumber())) + "X";
        idcard_15 = String.format("%015d", Long.parseLong(user.getNumber()));
        idcard_15_x = String.format("%014d", Long.parseLong(user.getNumber())) + "x";
        idcard_15_X = String.format("%014d", Long.parseLong(user.getNumber())) + "X";
    }

    //=================================正常绑定=======================================

    /**
     * 正常邮箱绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testBindEmail() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkALL(user.getUname(), user.getUpass(), "email", user.getEmail());
        assertTrue("正常邮箱绑定", result);
    }

    /**
     * 正常手机绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testBindMobile() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkALL(user.getUname(), user.getUpass(), "mobile", user.getMobile());
        assertTrue("正常手机绑定",result);
    }

//    @Test
//    public void testBindLotterid() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
//        boolean result = checkALL(uname, upass, "lotterid", lotterid);
//        assertTrue("正常彩票账户绑定",result);
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

    /**
     * 正常真实姓名绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testBindTruename() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkALLNotKey(user.getUname(), user.getUpass(), "truename", user.getTruename());
        assertTrue("正常真实姓名绑定",result);
    }

    /**
     * 正常昵称绑定
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testBindNickname() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = checkALL(user.getUname(), user.getUpass(), "nickname", user.getNickname());
        assertTrue("正常昵称绑定",result);
    }

    /**
     * 正常18位身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testBindIdcard18() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = checkALL(user.getUname(), user.getUpass(), "idcard", idcard_18);
        assertTrue("正常18位身份证绑定",result);
    }

    /**
     * 正常18位带x身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testBindIdcard18x() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = checkALL(user.getUname(), user.getUpass(), "idcard", idcard_18_x);
        assertTrue("正常18位带x身份证绑定",result);
    }

    /**
     * 正常18位带X身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testBindIdcard18X() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = checkALL(user.getUname(), user.getUpass(), "idcard", idcard_18_x);
        assertTrue("正常18位带X身份证绑定",result);
    }

    /**
     * 正常15位身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testBindIdcard15() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = checkALL(user.getUname(), user.getUpass(), "idcard", idcard_18_x);
        assertTrue("正常15位身份证绑定",result);
    }

    /**
     * 正常15位带x身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testBindIdcard15x() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = checkALL(user.getUname(), user.getUpass(), "idcard", idcard_18_x);
        assertTrue("正常15位带x身份证绑定",result);
    }

    /**
     * 正常15位带X身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testBindIdcard15X() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = checkALL(user.getUname(), user.getUpass(), "idcard", idcard_18_x);
        assertTrue("正常15位带X身份证绑定",result);
    }

    public static boolean checkALL(String uname, String upass, String keytp, String key) throws IOException, SAXException, NoSuchAlgorithmException {
        boolean checkuserbind = MyCheckUtil.checkUserbind(uname, keytp, key);
        boolean checkgetuserbind = MyCheckUtil.checkGetUserbind(uname, keytp, key);
        boolean checkfindunamebykey = MyCheckUtil.checkFindUnamebyKey(uname, keytp, key);
        boolean checku = MyCheckUtil.checkU(user.getUsertid(), URLDecoder.decode(upass,"UTF-8"));
        boolean checkukey = MyCheckUtil.checkUkey(user.getUsertid(), keytp, key);
        boolean checkindex = MyCheckUtil.checkIndex(user.getUsertid(), keytp, key);
//        boolean checkuid = MyCheckUtil.checkUid(uname, "");               V3中不支持
        return checkuserbind && checkgetuserbind && checkfindunamebykey && checku && checkukey && checkindex;
    }

    public static boolean checkALLNotKey(String uname, String upass, String keytp, String key) throws IOException, SAXException, NoSuchAlgorithmException {
        boolean checkuserbind = MyCheckUtil.checkUserbind(uname, keytp, key);
        boolean checkgetuserbind = MyCheckUtil.checkGetUserbind(uname, keytp, key);
        boolean checku = MyCheckUtil.checkU(user.getUsertid(), URLDecoder.decode(upass,"UTF-8"));
        boolean checkukey = MyCheckUtil.checkUkey(user.getUsertid(), keytp, key);
//        boolean checkuid = MyCheckUtil.checkUid(uname, "");               V3中不支持
        return checkuserbind && checkgetuserbind && checku && checkukey;
    }

    //=================================重复绑定=======================================

    /**
     * 重复邮箱绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindDuplicateEmail() throws IOException, SAXException, InterruptedException {
        boolean result = checkDuplicateKeyBind("email", user.getEmail());
        assertTrue("重复邮箱绑定",result);
    }

    /**
     * 重复手机绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindDuplicateMobile() throws IOException, SAXException, InterruptedException {
        boolean result = checkDuplicateKeyBind("mobile", user.getMobile());
        assertTrue("重复手机绑定",result);
    }

    /**
     * 重复真名绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testBindDuplicateTruename() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        createDupUser();
        MyCheckUtil.bindKey(user.getUname(),"truename",user.getTruename());
        MyCheckUtil.bindKey(uname_dup,"truename",user.getTruename());
        boolean resultu1 = MyCheckUtil.checkU(user.getUsertid(), URLDecoder.decode(user.getUpass(),"UTF-8"));
        boolean resultukey1 = MyCheckUtil.checkUkey(user.getUsertid(), "truename", user.getTruename());
        boolean resultu2 = MyCheckUtil.checkU(usertid_dup, URLDecoder.decode(user.getUpass(), "UTF-8"));
        boolean resultukey2 = MyCheckUtil.checkUkey(usertid_dup, "truename", user.getTruename());
        boolean result = resultu1 && resultukey1 && resultu2 && resultukey2;
        assertTrue("重复真名绑定",result);
    }

    /**
     * 重复昵称绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindDuplicateNickname() throws IOException, SAXException, InterruptedException {
        boolean result = checkDuplicateKeyBind("nickname", user.getNickname());
        assertTrue("重复昵称绑定",result);
    }

    /**
     * 重复18位身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindDuplicateIdcard18() throws IOException, SAXException, InterruptedException {
        boolean result = checkDuplicateKeyBind("idcard", idcard_18);
        assertTrue("重复18位身份证绑定",result);
    }

    /**
     * 重复18位带x身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindDuplicateIdcard18x() throws IOException, SAXException, InterruptedException {
        boolean result = checkDuplicateKeyBind("idcard", idcard_18_x);
        assertTrue("重复18位带x身份证绑定",result);
    }

    /**
     * 重复18位带X身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindDuplicateIdcard18X() throws IOException, SAXException, InterruptedException {
        boolean result = checkDuplicateKeyBind("idcard", idcard_18_X);
        assertTrue("重复18位带X身份证绑定",result);
    }

    /**
     * 重复15位身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindDuplicateIdcard15() throws IOException, SAXException, InterruptedException {
        boolean result = checkDuplicateKeyBind("idcard", idcard_15);
        assertTrue("重复15位身份证绑定",result);
    }

    /**
     * 重复15位带x身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindDuplicateIdcard15x() throws IOException, SAXException, InterruptedException {
        boolean result = checkDuplicateKeyBind("idcard", idcard_15_x);
        assertTrue("重复15位带x身份证绑定",result);
    }

    /**
     * 重复15位带X身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindDuplicateIdcard15X() throws IOException, SAXException, InterruptedException {
        boolean result = checkDuplicateKeyBind("idcard", idcard_15_X);
        assertTrue("重复15位带X身份证绑定",result);
    }

    public static void createDupUser() throws IOException, SAXException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        uname_dup = "Test" + "测试_" + number;
        usertid_dup = MyCheckUtil.addUser(uname_dup,user.getUpass());
        sleep(1000);
    }

    public static boolean checkDuplicateKeyBind(String keytp, String key) throws InterruptedException, SAXException, IOException {
        createDupUser();
        boolean result1 = MyCheckUtil.checkUserbind(user.getUname(),keytp,key);
        String response2 = MyCheckUtil.bindKey(uname_dup,keytp,key);
        int code2 = MyCheckUtil.getCode(response2);
        String unameoccupy = MyCheckUtil.getValueFromResponse(response2,"uname");
        String msg = MyCheckUtil.getValueFromResponse(response2,"msg");
        boolean result2 = code2==-230 && unameoccupy.equals(user.getUname().toLowerCase()) && msg.equals("duplicate_key," + keytp);
        boolean result = result1 && result2;
        return result;
    }

    //=================================强制绑定=======================================

    /**
     * 强制绑定重复邮箱
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testForceBindDuplicateEmail() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkForceBindDuplicateKey("email", user.getEmail());
        assertTrue("强制绑定重复邮箱",result);
    }

    /**
     * 强制重复手机绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testForceBindDuplicateMobile() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkForceBindDuplicateKey("mobile", user.getMobile());
        assertTrue("强制重复手机绑定",result);
    }

    /**
     * 强制重复昵称绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testForceBindDuplicateNickname() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkDuplicateKeyBind("nickname", user.getNickname());
        assertTrue("强制重复昵称绑定",result);
    }

    /**
     * 强制重复18位身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testForceBindDuplicateIdcard18() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkForceBindDuplicateKey("idcard", idcard_18);
        assertTrue("强制重复18位身份证绑定",result);
    }

    /**
     * 强制重复18位带x身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testForceBindDuplicateIdcard18x() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkForceBindDuplicateKey("idcard", idcard_18_x);
        assertTrue("强制重复18位带x身份证绑定",result);
    }

    /**
     * 强制重复18位带X身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testForceBindDuplicateIdcard18X() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkForceBindDuplicateKey("idcard", idcard_18_X);
        assertTrue("强制重复18位带X身份证绑定",result);
    }

    /**
     * 强制重复15位身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testForceBindDuplicateIdcard15() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkForceBindDuplicateKey("idcard", idcard_15);
        assertTrue("强制重复15位身份证绑定",result);
    }

    /**
     * 强制重复15位带x身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testForceBindDuplicateIdcard15x() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkForceBindDuplicateKey("idcard", idcard_15_x);
        assertTrue("强制重复15位带x身份证绑定",result);
    }

    /**
     * 强制重复15位带X身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testForceBindDuplicateIdcard15X() throws IOException, SAXException, InterruptedException, NoSuchAlgorithmException {
        boolean result = checkForceBindDuplicateKey("idcard", idcard_15_X);
        assertTrue("强制重复15位带X身份证绑定",result);
    }

    public static boolean checkForceBindDuplicateKey(String keytp, String key) throws InterruptedException, SAXException, IOException, NoSuchAlgorithmException {
        createDupUser();
        String keyencode = URLEncoder.encode(keytp + "=" + key,"UTF-8");
        String params1 = "&uname=" + user.getUname() + "&key=" + keyencode;
        String params2 = "&uname=" + uname_dup + "&key=" + keyencode + "&doflush=1";
        String response1 = AccInterface.testUserbind(params1);
        String response2 = AccInterface.testUserbind(params2);

        boolean checkresponse1 = MyCheckUtil.checkResponseNoResult(response1, user.getUname(), keytp,key);
        boolean checkresponse2 = MyCheckUtil.checkResponseNoResult(response2, uname_dup, keytp,key);

        boolean checkexistu1 = MyCheckUtil.checkU(user.getUsertid(), URLDecoder.decode(user.getUpass(),"UTF-8"));
        boolean checknotexistukey1 = MyCheckUtil.checkNotUkey(user.getUsertid(), keytp, key);
        boolean checkexistu2 = MyCheckUtil.checkU(usertid_dup, URLDecoder.decode(user.getUpass(), "UTF-8"));
        boolean checkexistukey2 = MyCheckUtil.checkUkey(usertid_dup, keytp, key);

        boolean checknotindex1 = MyCheckUtil.checkNotIndex(user.getUsertid(),keytp,key);
        boolean checkindex2 = MyCheckUtil.checkIndex(usertid_dup,keytp,key);

//        boolean checkuid1 = MyCheckUtil.checkUid(uname,"");              V3不支持
//        boolean checkuid2 = MyCheckUtil.checkUid(uname_dup,"");

        boolean result = checkresponse1 && checkresponse2 && checkexistu1 && checknotexistukey1 && checkexistu2
                && checkexistukey2 && checknotindex1 && checkindex2;
        return result;
    }

    //=================================错误绑定=======================================
    //最长200 个字符（包含“@”且“@”不是首字符、“@”之后有“.”且“@”与“.”之间存在其他字符、“.”之后存在其他字符）

    /**
     * 超过200字符的邮箱绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindTooLongEmail() throws IOException, SAXException, InterruptedException {
        StringBuffer toolongemail = new StringBuffer(user.getEmail());
        for (int i=0; i<=192; i++) {
            toolongemail.append("t");
        }
        String result = MyCheckUtil.bindKey(user.getUname(),"email",toolongemail.toString() + "@126.com");
        assertTrue("超过200字符的邮箱绑定,msg: " + result , result.contains("result=105"));
    }

    /**
     * 不包含@的邮箱绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindLackofAtEmail() throws IOException, SAXException, InterruptedException {
        String result = MyCheckUtil.bindKey(user.getUname(),"email",user.getUname() + "126.com");
        assertTrue("不包含@的邮箱绑定,msg: " + result, result.contains("result=105"));
    }

    /**
     * @是首字符的邮箱绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindAtisFirstEmail() throws IOException, SAXException, InterruptedException {
        String result = MyCheckUtil.bindKey(user.getUname(), "email","@126.com");
        assertTrue("@是首字符的邮箱绑定,msg: " + result, result.contains("result=105"));
    }

    /**
     * @之后没有.的邮箱绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindLackofDotEmail() throws IOException, SAXException, InterruptedException {
        String result = MyCheckUtil.bindKey(user.getUname(),"email",user.getUname() + "@126com");
        assertTrue("@之后没有.的邮箱绑定,msg: " + result, result.contains("result=105"));
    }

    /**
     * @与.之间不存在其他字符的邮箱绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindNullbtwAtDotEmail() throws IOException, SAXException, InterruptedException {
        String result = MyCheckUtil.bindKey(user.getUname(), "email", user.getUname() + "@.com");
        assertTrue("@与.之间不存在其他字符的邮箱绑定,msg: " + result , result.contains("result=105"));
    }

    /**
     * .之后不存在其他字符的邮箱绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindNullAftDotEmail() throws IOException, SAXException, InterruptedException {
        String result = MyCheckUtil.bindKey(user.getUname(), "email", user.getUname() + "@126.");
        assertTrue(".之后不存在其他字符的邮箱绑定,msg: " + result , result.contains("result=105"));
    }


    /**
     * 不是以1开头的手机绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    //以“1”开头11 位数字（最长15 位数字）
    @Test
    public void testBindOneNotFirstMobile() throws IOException, SAXException, InterruptedException {
        String result = MyCheckUtil.bindKey(user.getUname(), "mobile", "2" + user.getMobile().substring(1));
        assertTrue("不是以1开头的手机绑定,msg: " + result , result.contains("result=104"));
    }

    /**
     * 少于11位的手机绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindLessthan11Mobile() throws IOException, SAXException, InterruptedException {
        String result = MyCheckUtil.bindKey(user.getUname(), "mobile", user.getMobile().substring(0, 9));
        assertTrue("少于11位的手机绑定,msg: " + result , result.contains("result=104"));
    }

    /**
     * 多于15位的手机绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindMorethan15Mobile() throws IOException, SAXException, InterruptedException {
        String longmobile = user.getMobile();
        while (longmobile.length() <= 15) {
            longmobile += "t";
        }
        String result = MyCheckUtil.bindKey(user.getUname(), "mobile", longmobile);
        assertTrue("多于15位的手机绑定,msg: " + result , result.contains("result=104"));
    }

    /**
     * 带字符非纯数字的手机绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindWithCharMobile() throws IOException, SAXException, InterruptedException {
        String result = MyCheckUtil.bindKey(user.getUname(), "mobile", user.getMobile().substring(0, 9) + 't');
        assertTrue("带字符非纯数字的手机绑定,msg: " + result , result.contains("result=104"));
    }

    /**
     * 过长真名绑定
     * @throws IOException
     * @throws SAXException
     */
    //最大长度200 个字符
    @Test
    public void testBindTooLongTruename() throws IOException, SAXException {
        String longtruename = user.getTruename();
        while (longtruename.length() <= 200) {
            longtruename += "t";
        }
        String result = MyCheckUtil.bindKey(user.getUname(), "truename", longtruename);
        assertTrue("过长真名绑定,msg: " + result , result.contains("result=101"));
    }

    /**
     * 过长昵称绑定
     * @throws IOException
     * @throws SAXException
     */
    //最大长度200 个字符
    @Test
    public void testBindTooLongNickname() throws IOException, SAXException {
        String longnickname = user.getNickname();
        while (longnickname.length() <= 200) {
            longnickname += "t";
        }
        String result = MyCheckUtil.bindKey(user.getUname(), "nickname", longnickname);
        assertTrue("过长昵称绑定,msg: " + result , result.contains("result=101"));
    }

    /**
     * 非15或18位身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    //15 位数字或18 位数字（末位可为“x”）
    @Test
    public void testBindNot15or18Idcard() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = checkALL(user.getUname(),user.getUpass(), "idcard", idcard_18.substring(2));
        assertTrue("非15或18位身份证绑定" , result);
    }

    /**
     * 末位是非x或者X字符的其它字符身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testBindTailNotxXCharIdcard() throws IOException, SAXException, NoSuchAlgorithmException {
        boolean result = checkALL(user.getUname(), user.getUpass(), "idcard", idcard_18.substring(1) + 't');
        assertTrue("末位是非x或者X字符的其它字符身份证绑定", result);
    }

    /**
     * 非末位带字符的身份证绑定
     * @throws IOException
     * @throws SAXException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testBindNotTailCharIdcard() throws IOException, SAXException, NoSuchAlgorithmException {
        StringBuffer stringBuffer = new StringBuffer(idcard_18);
        StringBuffer idcardwithchar = stringBuffer.insert(2,'t');
        boolean result = checkALL(user.getUname(), user.getUpass(), "idcard", idcardwithchar.toString().substring(1));
        assertTrue("非末位带字符的身份证绑定", result);
    }

    //=================================空参数绑定=======================================
    /**
     * 空邮箱绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindNullEmail() throws IOException, SAXException, InterruptedException {
        boolean result = checkNullKey(user.getUname(), "email");
        assertTrue("空邮箱绑定", result);
    }

    /**
     * 空手机绑定
     * @throws IOException
     * @throws SAXException
     * @throws InterruptedException
     */
    @Test
    public void testBindNullMobile() throws IOException, SAXException, InterruptedException {
        boolean result = checkNullKey(user.getUname(), "mobile");
        assertTrue("空手机绑定", result);
    }

    /**
     * 空昵称绑定
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testBindNullNickname() throws IOException, SAXException {
        boolean result = checkNullKey(user.getUname(), "nickname");
        assertTrue("空昵称绑定", result);
    }

    /**
     * 空身份证绑定
     * @throws IOException
     * @throws SAXException
     */
    @Test
    public void testBindNullIdcard() throws IOException, SAXException {
        boolean result = checkNullKey(user.getUname(), "idcard");
        assertTrue("空身份证绑定", result);
    }

    /**
     * 空key绑定
     * @param uname
     * @param keytp
     * @return
     * @throws IOException
     * @throws SAXException
     */
    public boolean checkNullKey(String uname, String keytp) throws IOException, SAXException {
        String response = MyCheckUtil.bindKey(uname,keytp,"");
        boolean result = MyCheckUtil.getCode(response) == 1 && MyCheckUtil.getValueFromResponse(response,"uname").equals(uname);
        return result;
    }
}
