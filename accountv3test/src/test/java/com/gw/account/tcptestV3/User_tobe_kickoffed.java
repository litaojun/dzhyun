package com.gw.account.tcptestV3;
import com.gw.account.utils.*;

public class User_tobe_kickoffed  extends Thread{

	private String  usrname;
	private String  message;
public String getMessage() {
		return message;
	}


public User_tobe_kickoffed(String s){
	usrname=s;
}

@Override
public void run() {
	try {
		doAction(usrname);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	@SuppressWarnings("resource")
	public String doAction(String usrname) throws InterruptedException
	{
		String v3host ="10.15.201.106";
		int v3port=32229;

    	
    	//client_servlogin 9000
        TcpClient client_servlogin9000 = new TcpClient();
        int tid = 9000;
        int  netid=8;
        int pid=8;
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
        msg2=client_servlogin9000.read();
        int i=0;
        while(msg2.isEmpty())
        {
        	System.out.println("*************");
        	msg2=client_servlogin9000.read();
        	System.out.println("msg2:"+msg2);
        	i++;
        	if (i>50){
        		break;
        	}
        	
        }
		/*if (msg2 !=null) {
		    msg=msg2;
			System.out.println("receive kick off msg:" + msg2);
		    
		} else {
			msg="no data received";
			//System.out.println("no data received");
		}*/
		/*Thread.sleep(20000);*/
      // System.out.println("***************************"); 		
        		
        client_servlogin9001.close();
        message=msg;
		return msg;
		
	}
 
}
