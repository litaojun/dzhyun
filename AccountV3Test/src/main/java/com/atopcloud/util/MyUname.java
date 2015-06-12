package com.atopcloud.util;

//取服务返回信息中的userid

public class MyUname {

    //取服务器返回的uid
    public static String Uname(String resUid) {
        //1&uname=renguohua1&result=0
        int unm = resUid.indexOf("uname");
        int res = resUid.indexOf("result");


        if (unm < res) {
            String myuname = resUid.substring(resUid.lastIndexOf("uname=") + 6, resUid.lastIndexOf("&result="));
            return myuname;
        } else {
            String myuname = resUid.substring(resUid.lastIndexOf("uname=") + 6, resUid.length());
            return myuname;
        }


    }


}

