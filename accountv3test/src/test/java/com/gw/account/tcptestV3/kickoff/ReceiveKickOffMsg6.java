package com.gw.account.tcptestV3.kickoff;

import com.gw.account.utils.*;;

public class ReceiveKickOffMsg6  extends Thread{

	private TcpClient client_servlogin9000_6;

	String message;
	public String getMessage6() {
		return message;
	}

	public void run() {
	
			doAction();

	}


	public void doAction() 
	{
		String v3host ="10.15.201.106";
		int v3port=32226;
		//第一個鏈接請求
    	client_servlogin9000_6 = new TcpClient();
        int tid = 9000;
        int  netid1=6;
        int pid1=6;
        try {
        	client_servlogin9000_6.open(v3host, v3port);
        	client_servlogin9000_6.myServLogin(v3host, v3port, tid, netid1, pid1);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
    
        //判断client_servlogin9000_1是否被踢
    	String msg = null;
		if (!(msg = client_servlogin9000_6.read()).isEmpty()) {
			message+=msg;
			
		}
		else {
			message="no data received"; 
		}
        
	}
}


