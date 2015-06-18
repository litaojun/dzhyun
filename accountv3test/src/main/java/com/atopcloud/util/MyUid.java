package com.atopcloud.util;

import org.apache.log4j.Logger;

//取服务返回信息中的userid

public class MyUid {

    static Logger log = Logger.getLogger(MyUid.class);

    //取服务器返回的uid
    public static String Uid(String resUid) {
        int uid = resUid.indexOf("usertid");
        int unm = resUid.indexOf("uname");
        int res = resUid.indexOf("result");


        if (uid > unm && uid > res) {
            String myresuid = resUid.substring(resUid.lastIndexOf("usertid=") + 8);
            return myresuid;
        } else {
            int num = resUid.indexOf("usertid=");
            int num2 = resUid.indexOf("&result");
            String myresuid = resUid.substring(num + 8, num2);
            return myresuid;
        }


    }

    //取服务器返回的uidex
    public static String Uidex(String resUid) {
        int uid = resUid.indexOf("usertid");
        int unm = resUid.indexOf("uname");
        int res = resUid.indexOf("result");
        int repa = resUid.indexOf("upass");
        if (uid > unm && uid > res && unm > repa) {
            String myresuid = resUid.substring(resUid.lastIndexOf("usertid=") + 8);
            log.info(myresuid);
            return myresuid;
        } else {
            int num = resUid.indexOf("usertid=");
            int num2 = resUid.indexOf("&upass");
            String myresuid = resUid.substring(num + 8, num2);
            log.info(myresuid);
            return myresuid;
        }


    }

    //取服务器返回的uname
    public static String Una(String resUna) {
        int uid = resUna.indexOf("usertid");
        int unm = resUna.indexOf("uname");
        int res = resUna.indexOf("result");
        int repa = resUna.indexOf("upass");

        if (unm > uid && unm > res && unm > repa) {
            String myresUna = resUna.substring(resUna.lastIndexOf("uname=") + 6);
            log.info(myresUna);
            return myresUna;
        } else {
            int num = resUna.indexOf("uname=");
            int num2 = resUna.indexOf("&usertid");
            String myresUna = resUna.substring(num + 6, num2);
            log.info(myresUna);
            return myresUna;
        }

    }

    //取新成的手机号
    public static String Monbile(String Curtime) {

        int mob = Curtime.indexOf("2015");
        if (mob >= 0) {
            String newmoblie = Curtime.substring(4, 14);
            String retnewmoblie = "1" + newmoblie + "";
            log.info(retnewmoblie);
            return retnewmoblie;
        } else {
            log.info("新号码生成失败");
            return null;
        }


    }

    //取新生成的邮箱
    public static String Email(String Curtime) {

        int mob = Curtime.indexOf("2015");
        if (mob >= 0) {
            String newemail = Curtime.substring(4, 14);
            String retnewemail = "1" + newemail + "@qq.com";
            log.info(retnewemail);

            return retnewemail;
        } else {
            log.info("新邮箱生成失败");
            return null;
        }


    }


    public static void main(String arg[]) {
        MyUid ttMyUid = new MyUid();
        String test = ttMyUid.Email("20150212173918");
    }

}

