package com.atopcloud.util;


import static org.junit.Assert.assertEquals;

//检验bdb通用接口
import com.atopcloud.util.MyBdbUtil;

public class MyCheckBdb {
	
		public static  boolean CheckBdb(String curtimeuname ,String myuid ,String myu ,String k_1email,String k_2mobile,String ukey_uname,String k_o){
  //	public static  boolean CheckBdb(String curtimeuname ,String myuid ,String myu){

		boolean ret = true;
		MyBdbUtil Mybdb = new MyBdbUtil();
		//校验uid的key中对应 的用户名是否正确
		if (ret == true && myuid != ""){
			String retmyuid = Mybdb.getValue(myuid);
		//	System.out.println(retmyuid);
			int uid = retmyuid.indexOf(curtimeuname);
			System.out.println("Find userid:" +uid);
			if (uid == -1){
				System.out.println("Do't Find userid:" +myuid);
				ret = false;
			}
				 
//			return retmyuid;
		}
		
//		System.out.println(ret);
		//检验ukey（用户名为key,eg:"u:lidb20150211165336"）,是否正确存储用户名等相关信息
		if (ret == true && myu != ""){
			String retmyu = Mybdb.getValue(myu);
		//	System.out.println(retmyu);
			int uid = retmyu.indexOf(curtimeuname);
			System.out.println("Find ukey:" +uid);
			if (uid == -1){
		//		System.out.println("ok.....");
				System.out.println("Do't Find ukey:" +myu);
				ret = false;
			}
		}
		//检验email中对应的用户（用户名为key,eg:"k_1:10213150936@qq.com"）,是否正确存储用户名等相关信息
		if (ret == true && k_1email != ""){
			String retmyuid = Mybdb.getValue("k_1:"+k_1email+"");
		//	System.out.println(retmyuid);
			int email = retmyuid.indexOf(curtimeuname);
			System.out.println("Find useremail:" +email);
			if (email == -1){
				System.out.println("Do't Find useremail:" +email);
				ret = false;
			}
				 
//			return retmyuid;
		}
		
		
		if (ret == true && k_2mobile != ""){
			String retmyuid = Mybdb.getValue("k_2:"+k_2mobile+"");
		//	System.out.println(retmyuid);
			int mobile = retmyuid.indexOf(curtimeuname);
			System.out.println("Find usermobile:" +mobile);
			if (mobile == -1){
				System.out.println("Do't Find mobile:" +mobile);
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
		boolean tt = test.CheckBdb("1t1", "22t2","33tt3");

	}
*/
}
