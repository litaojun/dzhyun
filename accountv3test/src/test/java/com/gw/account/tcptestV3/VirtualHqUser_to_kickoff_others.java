package com.gw.account.tcptestV3;

import com.alibaba.fastjson.JSON;
import com.gw.account.utils.*;;

/**
 * 提送其他人！
 * @author Administrator
 *
 */
public class VirtualHqUser_to_kickoff_others  extends Thread{

	public void run() {
		// TODO Auto-generated method stub
		try {
			doAction();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		String uMarket="4";
        try {
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket,4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Thread.sleep(10000);
        client_servlogin9000.close();
        client_servlogin9001.close();
        System.out.println("$$$$thread whic kick off others has finished! ");
	}
}
