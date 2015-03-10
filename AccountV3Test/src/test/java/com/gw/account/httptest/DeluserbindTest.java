package com.gw.account.httptest;

import com.atopcloud.util.MyBdbUtil;
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
 * Created by song on 2015/3/6.
 */
public class DeluserbindTest {
    private static String uname;
    private static String email;
    private static String mobile;
    private static String nickname;
    private static String idcard;
    private static String pass_md5_str = "11111111";
    private static MyBdbUtil myBdbUtil = new MyBdbUtil();

    @BeforeClass
    public static void globalInit() throws IOException, SAXException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        uname = "Test" + "测试" + number;
        email = "Test" + number + "@126.com";
        mobile = "188" + number;
        nickname = "Nick" + number;
        idcard = String.format("%018d",Long.parseLong(number));
        String emailencode = URLEncoder.encode("email=" + email, "UTF-8");
        String mobilencode = URLEncoder.encode("mobile=" + mobile, "UTF-8");
        String nicknameencode = URLEncoder.encode("nickname=" + nickname, "UTF-8");
        String idcardencode = URLEncoder.encode("idcard=" + idcard, "UTF-8");
        AccInterface.testAdduser("&uname=" + uname + "&upass=" + pass_md5_str);
        AccInterface.testUserbind("&uname=" + uname + "&key=" + emailencode +
                "&key=" + mobilencode + "&key="  + nicknameencode + "&key="  + idcardencode);
        sleep(1000);
    }

    @Test
    public void testDelBindEmail() throws IOException, SAXException {
        String result = AccInterface.testDelUserbind("&uname=" + uname + "&keytp=email");
        int unamestartindex = result.indexOf("uname=") + 6;
        int unameendindex = (result.indexOf("&",unamestartindex) != -1) ? result.indexOf("&",unamestartindex) : result.length();
        String getuname = result.substring(unamestartindex,unameendindex);
        int emailstartindex = result.indexOf("email=") + 6;
        int emailendindex = (result.indexOf("&",emailstartindex) != -1) ? result.indexOf("&",emailstartindex) : result.length();
        String getemail = result.substring(emailstartindex,emailendindex);
        String getunamebdb = myBdbUtil.getValue("k_1:" + email);
        assertTrue("删除绑定邮箱,msg: " + result , result.contains("result=0") && getuname.equals(uname)
                && getemail.equals("1") && getunamebdb == null);
    }

    @Test
    public void testDelBindMobile() throws IOException, SAXException {
        String result = AccInterface.testDelUserbind("&uname=" + uname + "&keytp=mobile");
        int unamestartindex = result.indexOf("uname=") + 6;
        int unameendindex = (result.indexOf("&",unamestartindex) != -1) ? result.indexOf("&",unamestartindex) : result.length();
        String getuname = result.substring(unamestartindex,unameendindex);
        int mobilestartindex = result.indexOf("mobile=") + 7;
        int mobileendindex = (result.indexOf("&",mobilestartindex) != -1) ? result.indexOf("&",mobilestartindex) : result.length();
        String getmobile = result.substring(mobilestartindex,mobileendindex);
        String getunamebdb = myBdbUtil.getValue("k_2:" + mobile);
        assertTrue("删除绑定手机,msg: " + result , result.contains("result=0") && getuname.equals(uname)
                && getmobile.equals("1") && getunamebdb == null);
    }

    @Test
    public void testDelBindNickname() throws IOException, SAXException {
        String result = AccInterface.testDelUserbind("&uname=" + uname + "&keytp=nickname");
        int unamestartindex = result.indexOf("uname=") + 6;
        int unameendindex = (result.indexOf("&",unamestartindex) != -1) ? result.indexOf("&",unamestartindex) : result.length();
        String getuname = result.substring(unamestartindex,unameendindex);
        int nicknamestartindex = result.indexOf("nickname=") + 9;
        int nicknameendindex = (result.indexOf("&",nicknamestartindex) != -1) ? result.indexOf("&",nicknamestartindex) : result.length();
        String getnickname = result.substring(nicknamestartindex,nicknameendindex);
        String getunamebdb = myBdbUtil.getValue("k_9:" + nickname);
        assertTrue("删除绑定昵称,msg: " + result , result.contains("result=0") && getuname.equals(uname)
                && getnickname.equals("1") && getunamebdb == null);
    }

    @Test
    public void testDelBindIdcard() throws IOException, SAXException, InterruptedException {
        String result = AccInterface.testDelUserbind("&uname=" + uname + "&keytp=idcard");
        int unamestartindex = result.indexOf("uname=") + 6;
        int unameendindex = (result.indexOf("&",unamestartindex) != -1) ? result.indexOf("&",unamestartindex) : result.length();
        String getuname = result.substring(unamestartindex,unameendindex);
        int idcardstartindex = result.indexOf("idcard=") + 7;
        int idcardendindex = (result.indexOf("&",idcardstartindex) != -1) ? result.indexOf("&",idcardstartindex) : result.length();
        String getidcard = result.substring(idcardstartindex,idcardendindex);
        String getunamebdb = myBdbUtil.getValue("k_10:" + idcard);
        assertTrue("删除绑定身份证,msg: " + result , result.contains("result=0") && getuname.equals(uname)
                && getidcard.equals("1") && getunamebdb == null);
    }

    @Test
    public void testDelBindNull() throws IOException, SAXException {
        String result = AccInterface.testDelUserbind("&uname=" + uname + "&keytp=");
        assertTrue("删除参数为空的绑定信息,msg: " + result , result.contains("result=101"));
    }
}
