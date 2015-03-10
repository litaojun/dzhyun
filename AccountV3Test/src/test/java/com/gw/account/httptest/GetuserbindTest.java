package com.gw.account.httptest;

import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertTrue;

/**
 * Created by song on 2015/3/6.
 */
public class GetuserbindTest {
    private static String uname;
    private static String unameforidcard;
    private static String email;
    private static String mobile;
    private static String nickname;
    private static String idcard_18;
    private static String idcard_18_x;
    private static String idcard_18_X;
    private static String idcard_15;
    private static String idcard_15_x;
    private static String idcard_15_X;
    private static String pass_md5_str = "11111111";

    @BeforeClass
    public static void globalInit() throws IOException, SAXException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        uname = "Test" + "测试" + number;
        email = "Test" + number + "@126.com";
        mobile = "188" + number;
        nickname = "Nick" + number;
        idcard_18 = String.format("%018d",Long.parseLong(number));
        idcard_18_x = String.format("%017d",Long.parseLong(number)) + "x";
        idcard_18_X = String.format("%017d",Long.parseLong(number)) + "X";
        idcard_15 = String.format("%015d",Long.parseLong(number));
        idcard_15_x = String.format("%014d",Long.parseLong(number)) + "x";
        idcard_15_X = String.format("%014d",Long.parseLong(number)) + "X";
        String emailencode = URLEncoder.encode("email=" + email, "UTF-8");
        String mobilencode = URLEncoder.encode("mobile=" + mobile, "UTF-8");
        String nicknameencode = URLEncoder.encode("nickname=" + nickname, "UTF-8");
        AccInterface.testAdduser("&uname=" + uname + "&upass=" + pass_md5_str);
        AccInterface.testUserbind("&uname=" + uname + "&key="  + emailencode
                + "&key=" + mobilencode + "&key="  + nicknameencode);
        sleep(1000);
    }


    @Test
    public void testGetBindEmail() throws IOException, SAXException {
        String result = AccInterface.testGetUserbind("&uname=" + uname + "&keytp=email");
        int unamestartindex = result.indexOf("uname=") + 6;
        int unameendindex = (result.indexOf("&",unamestartindex) != -1) ? result.indexOf("&",unamestartindex) : result.length();
        String getuname = result.substring(unamestartindex,unameendindex);
        int emailstartindex = result.indexOf("email=") + 6;
        int emailendindex = (result.indexOf("&",emailstartindex) != -1) ? result.indexOf("&",emailstartindex) : result.length();
        String getemail = result.substring(emailstartindex,emailendindex);
        assertTrue("查询绑定邮箱,msg: " + result , getuname.equals(uname) && getemail.equals(email));
    }

    @Test
    public void testGetBindMobile() throws IOException, SAXException {
        String result = AccInterface.testGetUserbind("&uname=" + uname + "&keytp=mobile");
        int unamestartindex = result.indexOf("uname=") + 6;
        int unameendindex = (result.indexOf("&",unamestartindex) != -1) ? result.indexOf("&",unamestartindex) : result.length();
        String getuname = result.substring(unamestartindex,unameendindex);
        int mobilestartindex = result.indexOf("mobile=") + 7;
        int mobileendindex = (result.indexOf("&",mobilestartindex) != -1) ? result.indexOf("&",mobilestartindex) : result.length();
        String getmobile = result.substring(mobilestartindex,mobileendindex);
        assertTrue("查询绑定手机,msg: " + result , getuname.equals(uname) && getmobile.equals(mobile));
    }

    @Test
    public void testGetBindNickname() throws IOException, SAXException {
        String result = AccInterface.testGetUserbind("&uname=" + uname + "&keytp=nickname");
        int unamestartindex = result.indexOf("uname=") + 6;
        int unameendindex = (result.indexOf("&",unamestartindex) != -1) ? result.indexOf("&",unamestartindex) : result.length();
        String getuname = result.substring(unamestartindex,unameendindex);
        int nicknamestartindex = result.indexOf("nickname=") + 9;
        int nicknameendindex = (result.indexOf("&",nicknamestartindex) != -1) ? result.indexOf("&",nicknamestartindex) : result.length();
        String getnickname = result.substring(nicknamestartindex,nicknameendindex);
        assertTrue("查询绑定昵称,msg: " + result , getuname.equals(uname) && getnickname.equals(nickname));
    }

    public static void createUserBind(String idcard) throws IOException, SAXException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        String number = df.format(new Date());
        unameforidcard = "Test" + "测试" + number;
        String idcardencode = URLEncoder.encode("idcard=" + idcard, "UTF-8");
        String params = "&uname=" + unameforidcard + "&key=" + idcardencode;
        AccInterface.testAdduser("&uname=" + unameforidcard + "&upass=" + pass_md5_str);
        AccInterface.testUserbind(params);
        sleep(1000);
    }

    @Test
    public void testGetBindIdcard18() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_18);
        String result = AccInterface.testGetUserbind("&uname=" + unameforidcard + "&keytp=idcard");
        int unamestartindex = result.indexOf("uname=") + 6;
        int unameendindex = (result.indexOf("&",unamestartindex) != -1) ? result.indexOf("&",unamestartindex) : result.length();
        String getuname = result.substring(unamestartindex,unameendindex);
        int idcardstartindex = result.indexOf("idcard=") + 7;
        int idcardendindex = (result.indexOf("&",idcardstartindex) != -1) ? result.indexOf("&",idcardstartindex) : result.length();
        String getidcard = result.substring(idcardstartindex,idcardendindex);
        assertTrue("查询绑定18位身份证,msg: " + result , getuname.equals(unameforidcard) && getidcard.equals(idcard_18));
    }

    @Test
    public void testGetBindIdcard18x() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_18_x);
        String result = AccInterface.testGetUserbind("&uname=" + unameforidcard + "&keytp=idcard");
        int unamestartindex = result.indexOf("uname=") + 6;
        int unameendindex = (result.indexOf("&",unamestartindex) != -1) ? result.indexOf("&",unamestartindex) : result.length();
        String getuname = result.substring(unamestartindex,unameendindex);
        int idcardstartindex = result.indexOf("idcard=") + 7;
        int idcardendindex = (result.indexOf("&",idcardstartindex) != -1) ? result.indexOf("&",idcardstartindex) : result.length();
        String getidcard = result.substring(idcardstartindex,idcardendindex);
        assertTrue("查询绑定18位带x身份证,msg: " + result , getuname.equals(unameforidcard) && getidcard.equals(idcard_18_x));    }

    @Test
    public void testGetBindIdcard18X() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_18_X);
        String result = AccInterface.testGetUserbind("&uname=" + unameforidcard + "&keytp=idcard");
        int unamestartindex = result.indexOf("uname=") + 6;
        int unameendindex = (result.indexOf("&",unamestartindex) != -1) ? result.indexOf("&",unamestartindex) : result.length();
        String getuname = result.substring(unamestartindex,unameendindex);
        int idcardstartindex = result.indexOf("idcard=") + 7;
        int idcardendindex = (result.indexOf("&",idcardstartindex) != -1) ? result.indexOf("&",idcardstartindex) : result.length();
        String getidcard = result.substring(idcardstartindex,idcardendindex);
        assertTrue("查询绑定18位带X身份证,msg: " + result , getuname.equals(unameforidcard) && getidcard.equals(idcard_18_X));
    }

    @Test
    public void testGetBindIdcard15() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_15);
        String result = AccInterface.testGetUserbind("&uname=" + unameforidcard + "&keytp=idcard");
        int unamestartindex = result.indexOf("uname=") + 6;
        int unameendindex = (result.indexOf("&",unamestartindex) != -1) ? result.indexOf("&",unamestartindex) : result.length();
        String getuname = result.substring(unamestartindex,unameendindex);
        int idcardstartindex = result.indexOf("idcard=") + 7;
        int idcardendindex = (result.indexOf("&",idcardstartindex) != -1) ? result.indexOf("&",idcardstartindex) : result.length();
        String getidcard = result.substring(idcardstartindex,idcardendindex);
        assertTrue("查询绑定15位身份证,msg: " + result , getuname.equals(unameforidcard) && getidcard.equals(idcard_15));
    }

    @Test
    public void testGetBindIdcard15x() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_15_x);
        String result = AccInterface.testGetUserbind("&uname=" + unameforidcard + "&keytp=idcard");
        int unamestartindex = result.indexOf("uname=") + 6;
        int unameendindex = (result.indexOf("&",unamestartindex) != -1) ? result.indexOf("&",unamestartindex) : result.length();
        String getuname = result.substring(unamestartindex,unameendindex);
        int idcardstartindex = result.indexOf("idcard=") + 7;
        int idcardendindex = (result.indexOf("&",idcardstartindex) != -1) ? result.indexOf("&",idcardstartindex) : result.length();
        String getidcard = result.substring(idcardstartindex,idcardendindex);
        assertTrue("查询绑定15位带x身份证,msg: " + result , getuname.equals(unameforidcard) && getidcard.equals(idcard_15_x));
    }

    @Test
    public void testGetBindIdcard15X() throws IOException, SAXException, InterruptedException {
        createUserBind(idcard_15_X);
        String result = AccInterface.testGetUserbind("&uname=" + unameforidcard + "&keytp=idcard");
        int unamestartindex = result.indexOf("uname=") + 6;
        int unameendindex = (result.indexOf("&",unamestartindex) != -1) ? result.indexOf("&",unamestartindex) : result.length();
        String getuname = result.substring(unamestartindex,unameendindex);
        int idcardstartindex = result.indexOf("idcard=") + 7;
        int idcardendindex = (result.indexOf("&",idcardstartindex) != -1) ? result.indexOf("&",idcardstartindex) : result.length();
        String getidcard = result.substring(idcardstartindex,idcardendindex);
        assertTrue("查询绑定15位带X身份证,msg: " + result , getuname.equals(unameforidcard) && getidcard.equals(idcard_15_X));
    }

    @Test
    public void testGetBindNull() throws IOException, SAXException {
        String result = AccInterface.testGetUserbind("&uname=" + uname + "&keytp=");
        assertTrue("查询参数为空的绑定信息,msg: " + result , result.contains("result=0"));
    }
}
