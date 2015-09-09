package com.gw.account.tcptestV3.kickoff;

import com.gw.account.utils.*;;

public class UserNotConflict_tobe_kickoffed  extends Thread{

	private TcpClient client_servlogin9000;
	String msg=null;
	String message;

	public String getMessage() {
		return message;
	}

	public void run() {
	
			doAction();

	}

	@SuppressWarnings("resource")
	public String  doAction() 
	{
		String v3host ="10.15.201.106";
		int v3port=32226;
		//建立9000链接
    	client_servlogin9000 = new TcpClient();
        int tid = 9000;
        int  netid=(int)(Math.random()*10000000);
        int pid=(int)(Math.random()*10000);
        try {
        	client_servlogin9000.open(v3host, v3port);
        	client_servlogin9000.myServLogin(v3host, v3port, tid, netid, pid);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
        
    	//建立9000链接
        TcpClient client_servlogin9001 = new TcpClient();
       tid = 9001;
        try {
        	client_servlogin9001.open(v3host, v3port);
        	client_servlogin9001.myServLogin(v3host, v3port, tid, netid, pid);

        } catch (InterruptedException e) {
			e.printStackTrace();		
		}
        finally{
        	
        }
        
        //9001链接登录
  
        String uname="kickoffUser5";
		String pwd= "zxcvbnm";
		String uMarket="1";
		int usrpos=4;
		String appid="0.0-1";
		String idlow="1111";
		String idhigh="1111";
        try {
        	client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket,usrpos,appid,idlow,idhigh);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        //判断9000是否收到踢人消息
        String msg = null;
		String msg1=null;
		if (!(msg=client_servlogin9000.read()).isEmpty()) {
		
			msg1=msg;
		} else {
			msg1="no data received";
		}
		
		message=msg1;
		return msg1;
     
	}


 
}
