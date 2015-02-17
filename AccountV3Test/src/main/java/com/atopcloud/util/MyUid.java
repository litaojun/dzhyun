package com.atopcloud.util;

//取服务返回信息中的userid

public class MyUid {
	
    //取服务器返回的uid
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
	//取新成的手机号
    public static String Monbile(String Curtime){
    	
		int mob = Curtime.indexOf("2015");		
		if (mob >= 0 ){
			String newmoblie = Curtime.substring(4,14);
			String retnewmoblie = "1"+newmoblie+"";
			System.out.println(retnewmoblie);
		    return retnewmoblie ;
		}else{			
			System.out.println("新号码生成失败");
			return null;
		}	
		

	}
    
 //取新生成的邮箱   
public static String Email(String Curtime){
    	
		int mob = Curtime.indexOf("2015");		
		if (mob >= 0 ){
			String newemail = Curtime.substring(4,14);
			String retnewemail = "1"+newemail+"@qq.com";
			System.out.println(retnewemail);
			
		    return retnewemail ;
		}else{			
			System.out.println("新邮箱生成失败");
			return null;
		}	
		

	}


    public static void main(String arg[]) {
		MyUid ttMyUid = new MyUid();
	    String test = ttMyUid.Email("20150212173918")	;
	}

}

