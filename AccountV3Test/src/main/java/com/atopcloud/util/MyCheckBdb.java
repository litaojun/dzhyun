package com.atopcloud.util;

import static org.junit.Assert.assertEquals;

//检验bdb通用接口
import com.atopcloud.util.MyBdbUtil;

public class MyCheckBdb {
	
	//	public static  boolean CheckBdb(String curtimeuname ,String myuid ,String myu, String myuts ,String k_1email,String k_2mobile,String ukey_uname,String k_o){
		public static  boolean CheckBdb(String curtimeuname ,String myuid ,String myu){

		boolean ret = true;
		MyBdbUtil Mybdb = new MyBdbUtil();
		if (ret == true && myuid != ""){
			String retmyuid = Mybdb.getValue(myuid);
			int uid = retmyuid.indexOf(curtimeuname);
			
			if (uid == -1){
				ret = false;
			}
				 
//			return retmyuid;
		}
		
//		System.out.println(ret);
		if (ret == true && myu != ""){
			String retmyuid = Mybdb.getValue(myu);
			int uid = retmyuid.indexOf(curtimeuname);
			
			if (uid == -1){
//				System.out.println("ok.....");
				ret = false;
			}
		}
		System.out.println(ret);
		return ret;
		
		
		
	}
/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyCheckBdb test = new MyCheckBdb();
		boolean tt = test.CheckBdb("", "");

	}
*/
}
