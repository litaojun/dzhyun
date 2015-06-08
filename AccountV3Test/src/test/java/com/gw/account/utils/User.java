package com.gw.account.utils;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;

/**
 * Created by Hihiri on 2015/4/27.
 */
public class User {
    private String usertid;
    private String uname;
    private String upass;
    private String email;
    private String mobile;
    private String lotterid;
    private String deviceid;
    private String pushid;
    private String nlotterid;
    private String truename;
    private String nickname;
    private String idcard;
    private String qqid;
    private String lcb;
    private String wxid;
    private String xcid;
    private String bankcardno;
    private String bank;
    private String cardtp;
    private String provno;
    private String cityno;
    private String subbank;
    private String ylmobile;
    private String checkstatus;
    private String number;
    private String opendate;
    private String opentype;
    private String source;
    private String prefix;

    public void createUser() throws IOException, SAXException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
        SimpleDateFormat dfy = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date date = new Date();
        number = df.format(date);
        uname = "Test" + "测试_" + number;
        upass = "Ww123!@#$%^*()_+-=[];',./?<>`~";
        email = "Test" + number + "@126.com";
        mobile = "188" + number;
        lotterid = "Ltr" + number;
        deviceid = "Device" + number;
        pushid = "Push" + number;
        nlotterid = "2015" + number;
        truename = "True" + "测试_" + number;
        nickname = "Nick" + "测试_" + number;
        idcard = String.format("%018d", Long.parseLong(number));
        qqid = "qq" + number;
        lcb = "lcb" + number;
        wxid = "wx" + number;
        xcid = "xc" + number;
//        bankcardno = String.format("%018d", Long.parseLong(number));
        bankcardno = "622630" + String.format("%012d", Long.parseLong(number));
        bank = "002";
        cardtp = "1";
        provno = "1";
        cityno = "10";
        subbank = "支行名称";
        ylmobile = "188" + number;
        checkstatus = "2";
        usertid = MyCheckUtil.addUser(uname, getURLupass());
        opendate = dfy.format(date);
        opentype = "Type" + "测试_" + number;
        source = "Source" + "测试_" + number;
        prefix = "pre";
        sleep(1000);
    }

    public String getUsertid() {
        return usertid;
    }

    public String getUname() {
        return uname;
    }

    public String getUpass() {
        return upass;
    }

    public String getURLupass() throws UnsupportedEncodingException {
        return URLEncoder.encode(upass, "UTF-8");
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getLotterid() {
        return lotterid;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public String getPushid() {
        return pushid;
    }

    public String getNlotterid() {
        return nlotterid;
    }

    public String getTruename() {
        return truename;
    }

    public String getNickname() {
        return nickname;
    }

    public String getIdcard() {
        return idcard;
    }

    public String getQqid() {
        return qqid;
    }

    public String getLcb() {
        return lcb;
    }

    public String getWxid() {
        return wxid;
    }

    public String getXcid() {
        return xcid;
    }

    public String getBankcardno() {
        return bankcardno;
    }

    public String getBank() {
        return bank;
    }

    public String getCardtp() {
        return cardtp;
    }

    public String getProvno() {
        return provno;
    }

    public String getCityno() {
        return cityno;
    }

    public String getSubbank() {
        return subbank;
    }

    public String getYlmobile() {
        return ylmobile;
    }

    public String getCheckstatus() {
        return checkstatus;
    }

    public String getNumber() {
        return number;
    }

    public String getOpendate() {
        return opendate;
    }

    public String getOpentype() {
        return opentype;
    }

    public String getSource() {
        return source;
    }

    public String getPrefix() {
        return prefix;
    }
}
