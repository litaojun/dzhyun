package com.gw.account.tcptestV3;

public class T {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		KickoffTcpTest KickoffTcpTest=new KickoffTcpTest();
		String name="kickoff00000012";//"zhangchaoxu121212";
		String result=KickoffTcpTest.testKickOffMsg(name);
		System.out.println("返回的消息："+result);
	}
}
