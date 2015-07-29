package com.gw.account.tcptestV3.kickoff;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.xml.sax.SAXException;

import com.gw.account.tcptestV3.UpdpassTcpTest;
import com.gw.account.utils.*;;

public class UserMorelogoutError_tobe_kickoffed  extends Thread{

	String msg=null;

	public void run() {
	
			doAction();

	}
	

	@SuppressWarnings("resource")
	public void doAction() 
	{
		String v3host ="10.15.201.106";
		int v3port=32229;
		 //第一個鏈接請求
        TcpClient client_servlogin9001_1 = new TcpClient();
        int  netid1=1;
        int pid1=1;
       int tid_9001 = 9001;
        try {
        	client_servlogin9001_1.open(v3host, v3port);
        	client_servlogin9001_1.myServLogin(v3host, v3port, tid_9001, netid1, pid1);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
        //login on 9001
        //第一個鏈接登錄

        String uname="zhangchaoxu00022";
		String pwd="zcx123456";
		String uMarket1="1";
		int usrpos1=1;
		String appid="0.0-1";
		String idlow="1111";
		String idhigh="1111";
        try {
        	client_servlogin9001_1.myLogin(v3host, v3port, uname, pwd, uMarket1,usrpos1,appid,idlow,idhigh);
        
		} catch (InterruptedException e) {
			e.printStackTrace();
		}   
        //第二個鏈接請求
        TcpClient client_servlogin9001_2 = new TcpClient();
        int  netid2=2;
        int pid2=2;
        try {
        	client_servlogin9001_2.open(v3host, v3port);
        	client_servlogin9001_2.myServLogin(v3host, v3port, tid_9001, netid2, pid2);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
      
        //login on 9001
        //第二個鏈接登錄
		String uMarket2="2";
		int usrpos2=2;
        try {
        	client_servlogin9001_2.myLogin(v3host, v3port, uname, pwd, uMarket2,usrpos2,appid,idlow,idhigh);
        	Thread.sleep(1000);
        	client_servlogin9001_2.myLogOut(v3host, v3port, uname, uMarket2, usrpos2, netid2, pid2, appid);
        
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
        
      //第三個鏈接請求
        TcpClient client_servlogin9001_3 = new TcpClient();
        int  netid3=3;
        int pid3=3;
        try {
        	client_servlogin9001_3.open(v3host, v3port);
        	client_servlogin9001_3.myServLogin(v3host, v3port, tid_9001, netid3, pid3);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
        //login on 9001
        //第三個鏈接登錄
        String uMarket3="4";
  		int usrpos3=3;
          try {
          	client_servlogin9001_3.myLogin(v3host, v3port, uname, pwd, uMarket3,usrpos3,appid,idlow,idhigh);
        	client_servlogin9001_3.myLogOut(v3host, v3port, uname, uMarket3, usrpos3, netid3, pid3, appid);
  		} catch (InterruptedException e) {
  			e.printStackTrace();
  		}
        //第四個鏈接請求
          TcpClient client_servlogin9001_4 = new TcpClient();
          int  netid4=4;
          int pid4=4;
          try {
          	client_servlogin9001_4.open(v3host, v3port);
          	client_servlogin9001_4.myServLogin(v3host, v3port, tid_9001, netid4, pid4);

          } catch (InterruptedException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  			
  		}
          finally{
          	
          }
          //login on 9001
          //第四個鏈接登錄
          String uMarket4="8";
    		int usrpos4=4;
            try {
            	client_servlogin9001_4.myLogin(v3host, v3port, uname, pwd, uMarket4,usrpos4,appid,idlow,idhigh);
            	client_servlogin9001_4.myLogOut(v3host, v3port, uname, uMarket4, usrpos4, netid1, pid4, appid);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
            //第五個鏈接請求
            TcpClient client_servlogin9001_5 = new TcpClient();
            int  netid5=5;
            int pid5=5;
            try {
            	client_servlogin9001_5.open(v3host, v3port);
            	client_servlogin9001_5.myServLogin(v3host, v3port, tid_9001, netid5, pid5);

            } catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			
    		}
            finally{
            	
            }
            //login on 9001
            //第五個鏈接登錄
            String uMarket5="16";
      		int usrpos5=5;
              try {
              	client_servlogin9001_5.myLogin(v3host, v3port, uname, pwd, uMarket5,usrpos5,appid,idlow,idhigh);
            	client_servlogin9001_5.myLogOut(v3host, v3port, uname, uMarket2, usrpos1, netid5, pid2, appid);
      		} catch (InterruptedException e) {
      			e.printStackTrace();
      		}
              //第六個鏈接請求
              TcpClient client_servlogin9001_6 = new TcpClient();
              int  netid6=6;
              int pid6=6;
              try {
              	client_servlogin9001_6.open(v3host, v3port);
              	client_servlogin9001_6.myServLogin(v3host, v3port, tid_9001, netid6, pid6);

              } catch (InterruptedException e) {
      			// TODO Auto-generated catch block
      			e.printStackTrace();
      			
      		}
              finally{
              	
              }
              //login on 9001
              //第六個鏈接登錄
              String uMarket6="32";
        		int usrpos6=6;
                try {
                	client_servlogin9001_6.myLogin(v3host, v3port, uname, pwd, uMarket6,usrpos6,appid,idlow,idhigh);
                	client_servlogin9001_6.myLogOut(v3host, v3port, uname, uMarket6, usrpos2, netid2, pid2, appid);
        		} catch (InterruptedException e) {
        			e.printStackTrace();
        		}
                //第七個鏈接請求
                TcpClient client_servlogin9001_7= new TcpClient();
                int  netid7=7;
                int pid7=7;
                try {
                	client_servlogin9001_7.open(v3host, v3port);
                	client_servlogin9001_7.myServLogin(v3host, v3port, tid_9001, netid7, pid7);

                } catch (InterruptedException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        			
        		}
                finally{
                	
                }
                //login on 9001
                //第七個鏈接登錄
                String uMarket7="64";
          		int usrpos7=7;
                  try {
                  	client_servlogin9001_7.myLogin(v3host, v3port, uname, pwd, uMarket7,usrpos7,appid,idlow,idhigh);
          		} catch (InterruptedException e) {
          			e.printStackTrace();
          		}
                  //第八個鏈接請求
                  TcpClient client_servlogin9001_8= new TcpClient();
                  int  netid8=8;
                  int pid8=8;
                  try {
                  	client_servlogin9001_8.open(v3host, v3port);
                  	client_servlogin9001_8.myServLogin(v3host, v3port, tid_9001, netid8, pid8);

                  } catch (InterruptedException e) {
          			// TODO Auto-generated catch block
          			e.printStackTrace();
          			
          		}
                  finally{
                  	
                  }
                  //login on 9001
                  //第八個鏈接登錄
                  String uMarket8="128";
            		int usrpos8=8;
                    try {
                    	client_servlogin9001_8.myLogin(v3host, v3port, uname, pwd, uMarket8,usrpos8,appid,idlow,idhigh);
                    	client_servlogin9001_8.myLogOut(v3host, v3port, uname, uMarket8, usrpos8, netid8, pid8, appid);
            		} catch (InterruptedException e) {
            			e.printStackTrace();
            		}
                  //第九個鏈接請求
                    TcpClient client_servlogin9001_9= new TcpClient();
                    int  netid9=9;
                    int pid9=9;
                    try {
                    	client_servlogin9001_9.open(v3host, v3port);
                    	client_servlogin9001_9.myServLogin(v3host, v3port, tid_9001, netid9, pid9);

                    } catch (InterruptedException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            			
            		}
                    finally{
                    	
                    }
                    //login on 9001
                    //第九個鏈接登錄
                    String idlow1="2222";
            		String idhigh1="2222";
                    String uMarket9="32";
              		int usrpos9=9;
                      try {
                      	client_servlogin9001_9.myLogin(v3host, v3port, uname, pwd, uMarket9,usrpos9,appid,idlow1,idhigh1);
              		} catch (InterruptedException e) {
              			e.printStackTrace();
              		}
	}
}


