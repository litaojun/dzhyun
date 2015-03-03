package com.gw.account.httptest;

public class T {

	public static void main(String[] args) {
		String stl = "uname=lidb20150211140053&usertid=15839328&result=0";
		int num = stl.indexOf("usertid=");
		int num2 = stl.indexOf("&result");
		String s = stl.substring(num+8, num2);
		String s1 = stl.substring(stl.lastIndexOf("usertid=")+8);
		System.out.println(s1);
		System.out.print(s);

	}

}
