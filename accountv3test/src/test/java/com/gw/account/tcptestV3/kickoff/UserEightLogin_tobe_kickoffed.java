package com.gw.account.tcptestV3.kickoff;

import com.gw.account.utils.*;

;

public class UserEightLogin_tobe_kickoffed extends Thread {

	private TcpClient client_servlogin9000;
	String msg = null;

	public void run() {

		doAction();

	}

	@SuppressWarnings("resource")
	public void doAction() {
		String v3host = "10.15.201.106";
		int v3port = 32226;
		client_servlogin9000 = new TcpClient();
		int tid = 9000;
		int netid = (int) (Math.random() * 10000000);
		int pid = (int) (Math.random() * 10000);
		String uname = "kickoffUser1";
		String pwd = "zxcvbnm";
		String uMarket1 = "1";
		String uMarket2 = "2";
		String uMarket3 = "4";
		String uMarket4 = "8";
		String uMarket5 = "16";
		String uMarket6 = "32";
		String uMarket7 = "64";
		String uMarket8 = "128";
		int usrpos1 = 1;
		int usrpos2 = 2;
		int usrpos3 = 3;
		int usrpos4 = 4;
		int usrpos5 = 5;
		int usrpos6 = 6;
		int usrpos7 = 7;
		int usrpos8 = 8;
		String appid = "0.0-1";
		String idlow1 = "1111";
		String idhigh1 = "1111";
		String idlow2 = "2222";
		String idhigh2 = "2222";
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

		try {
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket1,
					usrpos1, appid, idlow1, idhigh1);
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket2,
					usrpos2, appid, idlow1, idhigh1);
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket3,
					usrpos3, appid, idlow1, idhigh1);
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket4,
					usrpos4, appid, idlow1, idhigh1);
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket5,
					usrpos5, appid, idlow1, idhigh1);
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket6,
					usrpos6, appid, idlow1, idhigh1);
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket7,
					usrpos7, appid, idlow1, idhigh1);
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket8,
					usrpos8, appid, idlow1, idhigh1);
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
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket1,
					usrpos1, appid, idlow2, idhigh2);
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket2,
					usrpos2, appid, idlow2, idhigh2);
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket3,
					usrpos3, appid, idlow2, idhigh2);
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket4,
					usrpos4, appid, idlow2, idhigh2);
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket5,
					usrpos5, appid, idlow2, idhigh2);
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket6,
					usrpos6, appid, idlow2, idhigh2);
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket7,
					usrpos7, appid, idlow2, idhigh2);
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket8,
					usrpos8, appid, idlow2, idhigh2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 判断是否被踢
		String msg = null;
		if (!(msg = client_servlogin9000.read()).isEmpty()) {
			System.out.println("receive kick off msg:" + msg);
			int i = 0;
			while (i <7) {
				msg = client_servlogin9000.read();
				System.out.println("receive kick off msg:" + msg);
				i++;
			}
		}

		else {
			System.out.println("no data received");
		}
		
		client_servlogin9001.close();

	}

}