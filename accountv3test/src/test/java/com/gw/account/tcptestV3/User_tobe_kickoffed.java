package com.gw.account.tcptestV3;
import com.gw.account.utils.*;;

public class User_tobe_kickoffed  extends Thread{


	@SuppressWarnings("resource")
	public String doAction(String usrname) throws InterruptedException
	{
		String v3host ="10.15.201.106";
		int v3port=32229;

    	
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
        
        String uname=usrname;
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

        //判断是否被踢
        String msg = null;
        String msg2 = null;
		if (!(msg2=client_servlogin9000.read()).isEmpty()) {
		    msg=msg2;
			//System.out.println("receive kick off msg:" + msg2);
		} else {
			msg="no data received";
			//System.out.println("no data received");
		}
        		
        		
        client_servlogin9001.close();
		return msg;
	}
 
}
