package com.gw.account.tcptestV3.kickoff;

import com.gw.account.utils.*;;

/**
 * 提送其他人！
 * @author Administrator
 *
 */
public class UserMoreNotConflict_to_kickoff_others  extends Thread{

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
		int v3port=32229;
    	
    	//client_servlogin 9000
        TcpClient client_servlogin9000 = new TcpClient();
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
       
        int[] arrayusrpos=new int[]{12,4};
        String uname="zhangchaoxu55555";
		String pwd= "zcx123456";
		String uMarket="256";
		int usrpos=arrayusrpos[MyCheckUtil.GetRandomNum(0, 1)];
		String appid="0.0-1";
		String idlow="2222";
		String idhigh="2222";
        try {
			client_servlogin9001.myLogin(v3host, v3port, uname, pwd, uMarket,usrpos,appid,idlow,idhigh);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Thread.sleep(10000);
        client_servlogin9000.close();
        client_servlogin9001.close();
//        System.out.println("kick off others has finished! ");
	}
}
