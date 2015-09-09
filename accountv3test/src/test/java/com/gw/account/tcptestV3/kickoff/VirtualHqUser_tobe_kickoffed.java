package com.gw.account.tcptestV3.kickoff;
import com.gw.account.utils.*;;

public class VirtualHqUser_tobe_kickoffed  extends Thread{

/*	public void run() {
		try {
			doAction();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//("10.15.201.106", 32226);
		
	}
*/
	@SuppressWarnings("resource")
	public String doAction() throws InterruptedException
	{
		String v3host ="10.15.201.106";
		int v3port=32226;

    	
    	//client_servlogin 9000
        TcpClient client_servlogin9000 = new TcpClient();
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
        
        String uname="kickoffUser1";
		String pwd= "zxcvbnm";
		String uMarket="4";
		int usrpos=4;
		String appid="0.0-1";
		String idlow="1111";
		String idhigh="1111";
        try {
        	client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket,usrpos,appid,idlow,idhigh);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        
        String uname1="kickoffUser2";
		String appid1="0.0-1";
		String idlow1="2222";
		String idhigh1="2222";
        try {
        	client_servlogin9001.myLogin(v3host, v3port, uname1, pwd, uMarket,usrpos,appid1,idlow1,idhigh1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
		
		return msg1;
	}
 
}
