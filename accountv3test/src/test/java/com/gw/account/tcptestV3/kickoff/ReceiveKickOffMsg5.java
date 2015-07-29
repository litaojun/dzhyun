package com.gw.account.tcptestV3.kickoff;

import com.gw.account.utils.*;;

public class ReceiveKickOffMsg5  extends Thread{

	private TcpClient client_servlogin9000_5;


	public void run() {
	
			doAction();

	}


	public void doAction() 
	{
		String v3host ="10.15.201.106";
		int v3port=32229;
		//第一個鏈接請求
    	client_servlogin9000_5 = new TcpClient();
        int tid = 9000;
        int  netid1=5;
        int pid1=5;
        try {
        	client_servlogin9000_5.open(v3host, v3port);
        	client_servlogin9000_5.myServLogin(v3host, v3port, tid, netid1, pid1);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
    
        //判断client_servlogin9000_1是否被踢
    	String msg = null;
		if (!(msg = client_servlogin9000_5.read()).isEmpty()) {
			System.out.println("client_servlogin9000_5 receive kick off msg:" + msg);
			
		}
		else {
			System.out.println("client_servlogin9000_5 no data received");
		}

        
	}
}


