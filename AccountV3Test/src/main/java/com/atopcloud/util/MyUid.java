package com.atopcloud.util;

//取服务返回信息中的userid

public class MyUid {
	

	public static String Uid(String resUid){
		int uid = resUid.indexOf("usertid");
		int unm = resUid.indexOf("uname");
		int res = resUid.indexOf("result");
		
		if (uid > unm && uid > res ){
			String myresuid = resUid.substring(resUid.lastIndexOf("usertid=")+8);
			return myresuid;
		}else{
			int num = resUid.indexOf("usertid=");
			int num2 = resUid.indexOf("&result");
			String myresuid = resUid.substring(num+8, num2);
		    return myresuid;
		}		

	}

}

