package com.gw.account.tcptestV3;

import com.alibaba.fastjson.JSON;
import com.gw.account.utils.*;;

public class VirtualHqUser_tobe_kickoffed  extends Thread{

	public void run() {
		// TODO Auto-generated method stub
		System.out.println("this is a virtual hq user! run at " + System.currentTimeMillis());
		try {
			doAction();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//("10.15.201.106", 32226);
		
	}

	public void doAction() throws InterruptedException
	{
		String v3host ="10.15.201.106";
		int v3port=32226;
    	String msg="";
    	boolean timeoutflag=false;
    	
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
        
        //login on 9001
        String uname="testcrmv3007";
		String pwd= "111111";
		String uMarket="12";
        try {
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket,12);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //判断是否被踢
        String msg2=null;
        if((msg2 =client_servlogin9000.read() )!= null)
        {
        	System.out.println("############receive kick off msg:"+msg2);
	        System.out.println("thread is kicked off! ");
//	        //response kickoff to v3
//	        int userpos=4;
//	        int currpos=12;
//	        client_servlogin9000.myKickoff(v3host, v3port, uname, pwd, uMarket, userpos, currpos, netid, pid);
//	        client_servlogin9000.close();
        }
        
        client_servlogin9001.close();
       
        System.out.println("thread is over! ");
	}
 
}
