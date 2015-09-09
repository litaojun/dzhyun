package com.gw.account.tcptestV3.kickoff;

import com.gw.account.utils.*;


/**
 * @author zhangchaoxu
 * 此类表示
 */ 
public class UserLogout_tobe_kickoffed  extends Thread{

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
	public String doAction() 
	{
		String v3host ="10.15.201.106";
		int v3port=32226;
    	client_servlogin9000 = new TcpClient();
        int tid = 9000;
        int  netid=1;
        int pid=1;
        try {
        	client_servlogin9000.open(v3host, v3port);
        	client_servlogin9000.myServLogin(v3host, v3port, tid, netid, pid);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
        
    	//client_servlogin 9001
        TcpClient client_servlogin9001 = new TcpClient();
       tid = 9001;
        try {
        	client_servlogin9001.open(v3host, v3port);
        	client_servlogin9001.myServLogin(v3host, v3port, tid, netid, pid);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
        
        //login on 9001
    /*    String[] arrayuname= new String[]{"zhangxiaosan","13501559803","zhangchaoxu@qq.com"};
        String uname=arrayuname[MyCheckUtil.GetRandomNum(0, 2)];*/
        String uname="kickoffUser10";
		String pwd= "zxcvbnm";
		String uMarket="4";
		int usrpos=4;
		String appid="0.0-1";
		String idlow="1111";
		String idhigh="1111";
        try {
        	client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket,usrpos,appid,idlow,idhigh);
        	client_servlogin9001.myLogOut(v3host, v3port, uname, uMarket, usrpos, netid, pid, appid);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        
        

        //判断是否被踢
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
