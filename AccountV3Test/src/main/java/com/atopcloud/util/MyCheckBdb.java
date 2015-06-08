package com.atopcloud.util;


import org.apache.log4j.Logger;

//检验bdb通用接口

public class MyCheckBdb {

    static Logger log = Logger.getLogger(MyCheckBdb.class);

    public static boolean CheckBdb(String curtimeuname, String myuid, String myu, String k_1email, String k_2mobile, String ukey_uname, String k_o) {
        //	public static  boolean CheckBdb(String curtimeuname ,String myuid ,String myu){

        boolean ret = true;
        MyBdbUtil Mybdb = new MyBdbUtil();
        //校验uid的key中对应 的用户名是否正确
        if (ret == true && myuid != "") {
            String retmyuid = Mybdb.getValue(myuid);
            //	log.info(retmyuid);
            int uid = retmyuid.indexOf(curtimeuname);
            log.info("Find userid:" + uid);
            if (uid == -1) {
                log.info("Do't Find userid:" + myuid);
                ret = false;
            }

//			return retmyuid;
        }

//		log.info(ret);
        //检验ukey（用户名为key,eg:"u:lidb20150211165336"）,是否正确存储用户名等相关信息
        if (ret == true && myu != "") {
            String retmyu = Mybdb.getValue(myu);
            //	log.info(retmyu);
            int uid = retmyu.indexOf(curtimeuname);
            log.info("Find ukey:" + uid);
            if (uid == -1) {
                //		log.info("ok.....");
                log.info("Do't Find ukey:" + myu);
                ret = false;
            }
        }
        //检验email中对应的用户（用户名为key,eg:"k_1:10213150936@qq.com"）,是否正确存储用户名等相关信息
        if (ret == true && k_1email != "") {
            String retmyuid = Mybdb.getValue("k_1:" + k_1email + "");
            //	log.info(retmyuid);
            int email = retmyuid.indexOf(curtimeuname);
            log.info("Find useremail:" + email);
            if (email == -1) {
                log.info("Do't Find useremail:" + email);
                ret = false;
            }

//			return retmyuid;
        }


        if (ret == true && k_2mobile != "") {
            String retmyuid = Mybdb.getValue("k_2:" + k_2mobile + "");
            //	log.info(retmyuid);
            int mobile = retmyuid.indexOf(curtimeuname);
            log.info("Find usermobile:" + mobile);
            if (mobile == -1) {
                log.info("Do't Find mobile:" + mobile);
                ret = false;
            }

        }
        log.info(ret);
        return ret;

    }

	
/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyCheckBdb test = new MyCheckBdb();
		boolean tt = test.CheckBdb("1t1", "22t2","33tt3");

	}
*/
}
