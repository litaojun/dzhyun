package com.gw.account.tcptestV3.kickoff;

import com.gw.account.utils.*;

;

public class UserTwoUmarktNotConflict_tobe_kickoffed extends Thread {

	private TcpClient client_servlogin9000;
	String msg = null;
	String message;
	public String getMessage() {
		return message;
	}

	@SuppressWarnings("resource")
	public String doAction() {
		String v3host = "10.15.201.106";
		int v3port = 32226;
		client_servlogin9000 = new TcpClient();
		int tid = 9000;
		int netid = (int) (Math.random() * 10000000);
		int pid = (int) (Math.random() * 10000);
		try {
			client_servlogin9000.open(v3host, v3port);
			client_servlogin9000.myServLogin(v3host, v3port, tid, netid, pid);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {

		}

		// client_servlogin 9001
		TcpClient client_servlogin9001 = new TcpClient();
		tid = 9001;
		try {
			client_servlogin9001.open(v3host, v3port);
			client_servlogin9001.myServLogin(v3host, v3port, tid, netid, pid);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {

		}

		// login on 9001
		/*
		 * String[] arrayuname= new
		 * String[]{"zhangxiaosan","13501559803","zhangchaoxu@qq.com"}; String
		 * uname=arrayuname[MyCheckUtil.GetRandomNum(0, 2)];
		 */
		String uname = "kickoffUser21";
		String pwd = "zxcvbnm";
		String uMarket1 = "1";
		int usrpos1 = 1;
		String appid1 = "0.0-1";
		String uMarket2 = "4";
		String idlow1 = "1111";
		String idhigh1 = "1111";
		String idlow2 = "2222";
		String idhigh2 = "2222";
		try {
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket1,
					usrpos1, appid1, idlow1, idhigh1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket2,
					usrpos1, appid1, idlow2, idhigh2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 判断是否被踢
		   String msg = null;
 		String msg1=null;
 		if (!(msg=client_servlogin9000.read()).isEmpty()) {
 		System.out.println(msg);
 			msg1=msg;
 		} else {
 			msg1="no data received";
 		}
 		
 		message=msg1;
 		return msg1;
 		

	}

}
