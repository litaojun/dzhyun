package com.gw.account.tcptestV3.kickoff;

import com.gw.account.utils.*;;

public class UserMoreAppidMap_tobe_kickoffed  extends Thread{

	private TcpClient client_servlogin9000_1;
	String msg=null;
	String message;
	public String getMessage() {
		return message;
	}
	public void run() {
	
			doAction();

	}

	@SuppressWarnings("resource")
	public void doAction() 
	{
		String v3host ="10.15.201.106";
		int v3port=32226;
		//第一個鏈接請求
    	client_servlogin9000_1 = new TcpClient();
        int tid = 9000;
        int  netid1=(int)(Math.random()*10000000);
        int pid1=(int)(Math.random()*10000);
        try {
        	client_servlogin9000_1.open(v3host, v3port);
        	client_servlogin9000_1.myServLogin(v3host, v3port, tid, netid1, pid1);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
      //client_servlogin 9001
        TcpClient client_servlogin9001_1 = new TcpClient();
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
        //第二個鏈接請求
        TcpClient client_servlogin9000_2 = new TcpClient();
        int  netid2=(int)(Math.random()*10000000);
        int pid2=(int)(Math.random()*10000);
        try {
        	client_servlogin9000_2.open(v3host, v3port);
        	client_servlogin9000_2.myServLogin(v3host, v3port, tid, netid2, pid2);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
        TcpClient client_servlogin9001_2 = new TcpClient();
        try {
        	client_servlogin9001_2.open(v3host, v3port);
        	client_servlogin9001_2.myServLogin(v3host, v3port, tid_9001, netid2, pid2);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
      //第三個鏈接請求
        TcpClient client_servlogin9000_3 = new TcpClient();
        int  netid3=(int)(Math.random()*10000000);
        int pid3=(int)(Math.random()*10000);
        try {
        	client_servlogin9000_3.open(v3host, v3port);
        	client_servlogin9000_3.myServLogin(v3host, v3port, tid, netid3, pid3);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
        
        TcpClient client_servlogin9001_3 = new TcpClient();
        try {
        	client_servlogin9001_3.open(v3host, v3port);
        	client_servlogin9001_3.myServLogin(v3host, v3port, tid_9001, netid3, pid3);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
        
//第四個鏈接請求
        TcpClient client_servlogin9000_4 = new TcpClient();
        int  netid4=(int)(Math.random()*10000000);
        int pid4=(int)(Math.random()*10000);
        try {
        	client_servlogin9000_4.open(v3host, v3port);
        	client_servlogin9000_4.myServLogin(v3host, v3port, tid, netid4, pid4);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
        TcpClient client_servlogin9001_4 = new TcpClient();
        try {
        	client_servlogin9001_4.open(v3host, v3port);
        	client_servlogin9001_4.myServLogin(v3host, v3port, tid_9001, netid4, pid4);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
        //第五個鏈接請求
        TcpClient client_servlogin9000_5 = new TcpClient();
        int  netid5=(int)(Math.random()*10000000);
        int pid5=(int)(Math.random()*10000);
        try {
        	client_servlogin9000_5.open(v3host, v3port);
        	client_servlogin9000_5.myServLogin(v3host, v3port, tid, netid5, pid5);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
        TcpClient client_servlogin9001_5 = new TcpClient();
        try {
        	client_servlogin9001_5.open(v3host, v3port);
        	client_servlogin9001_5.myServLogin(v3host, v3port, tid_9001, netid5, pid5);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
        //第六個鏈接請求
        TcpClient client_servlogin9000_6 = new TcpClient();
        int  netid6=(int)(Math.random()*10000000);
        int pid6=(int)(Math.random()*10000);
        try {
        	client_servlogin9000_6.open(v3host, v3port);
        	client_servlogin9000_6.myServLogin(v3host, v3port, tid, netid6, pid6);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
        TcpClient client_servlogin9001_6 = new TcpClient();
        try {
        	client_servlogin9001_6.open(v3host, v3port);
        	client_servlogin9001_6.myServLogin(v3host, v3port, tid_9001, netid6, pid6);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
        //第七個鏈接請求
        TcpClient client_servlogin9000_7 = new TcpClient();
        int  netid7=(int)(Math.random()*10000000);
        int pid7=(int)(Math.random()*10000);
        try {
        	client_servlogin9000_7.open(v3host, v3port);
        	client_servlogin9000_7.myServLogin(v3host, v3port, tid, netid7, pid7);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
        TcpClient client_servlogin9001_7 = new TcpClient();
        try {
        	client_servlogin9001_7.open(v3host, v3port);
        	client_servlogin9001_7.myServLogin(v3host, v3port, tid_9001, netid7, pid7);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
        //第八個鏈接請求
        TcpClient client_servlogin9000_8 = new TcpClient();
        int  netid8=(int)(Math.random()*10000000);
        int pid8=(int)(Math.random()*10000);
        try {
        	client_servlogin9000_8.open(v3host, v3port);
        	client_servlogin9000_8.myServLogin(v3host, v3port, tid, netid8, pid8);

        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        finally{
        	
        }
        TcpClient client_servlogin9001_8 = new TcpClient();
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
        //第一個鏈接登錄

        String uname="kickoffUser35";
		String pwd= "zxcvbnm";
		String uMarket1="1";
		int usrpos1=1;
		String appid1="0.0008";
		String idlow="1111";
		String idhigh="1111";
        try {
        	client_servlogin9001_1.myLogin(v3host, v3port, uname, pwd, uMarket1,usrpos1,appid1,idlow,idhigh);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        //第二個鏈接登錄
        String appid2="4.0001";
		String uMarket2="2";
		int usrpos2=2;
        try {
        	client_servlogin9001_2.myLogin(v3host, v3port, uname, pwd, uMarket2,usrpos2,appid2,idlow,idhigh);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
      //第三個鏈接登錄
            String appid3="3.00008";
      		String uMarket3="4";
      		int usrpos3=3;
              try {
              	client_servlogin9001_3.myLogin(v3host, v3port, uname, pwd, uMarket3,usrpos3,appid3,idlow,idhigh);
      		} catch (InterruptedException e) {
      			e.printStackTrace();
      		}
              //第四個鏈接登錄
                String appid4="8.00056";
        		String uMarket4="8";
        		int usrpos4=4;
                try {
                	client_servlogin9001_4.myLogin(v3host, v3port, uname, pwd, uMarket4,usrpos4,appid4,idlow,idhigh);
        		} catch (InterruptedException e) {
        			e.printStackTrace();
        		}
              //第五個鏈接登錄
                String appid5="0.0008";
        		String uMarket5="16";
        		int usrpos5=5;
                try {
                	client_servlogin9001_5.myLogin(v3host, v3port, uname, pwd, uMarket5,usrpos5,appid5,idlow,idhigh);
        		} catch (InterruptedException e) {
        			e.printStackTrace();
        		}
                //第六個鏈接登錄
                String appid6="0.0008";
        		String uMarket6="32";
        		int usrpos6=6;
                try {
                	client_servlogin9001_6.myLogin(v3host, v3port, uname, pwd, uMarket6,usrpos6,appid6,idlow,idhigh);
        		} catch (InterruptedException e) {
        			e.printStackTrace();
        		}
                //第七個鏈接登錄
                String appid7="0.0008";
        		String uMarket7="64";
        		int usrpos7=7;
                try {
                	client_servlogin9001_7.myLogin(v3host, v3port, uname, pwd, uMarket7,usrpos7,appid7,idlow,idhigh);
        		} catch (InterruptedException e) {
        			e.printStackTrace();
        		}
                //第八個鏈接登錄
                String appid8="0.0008";
        		String uMarket8="128";
        		int usrpos8=8;
                try {
                	client_servlogin9001_8.myLogin(v3host, v3port, uname, pwd, uMarket8,usrpos8,appid8,idlow,idhigh);
        		} catch (InterruptedException e) {
        			e.printStackTrace();
        		}
                //判断client_servlogin9000_1是否被踢
                String msg1=null;
                 if(!(msg1 =client_servlogin9000_1.read() ).isEmpty())
                 {
                 	System.out.println("receive client_servlogin9000_1 kick off msg:"+msg1);
                 }
                 else{
                 	System.out.println("client_servlogin9000_1 no data received");   
                 }
                 
                 //判断client_servlogin9000_2是否被踢
                 String msg2=null;
                 if(!(msg2 =client_servlogin9000_2.read() ).isEmpty())
                 {
                 	System.out.println("receive client_servlogin9000_2 kick off msg:"+msg2);
                 }
                 else{
                 	System.out.println("client_servlogin9000_2 no data received");   
                 }
                 //判断client_servlogin9000_3是否被踢
                 String msg3=null;
                 if(!(msg3 =client_servlogin9000_3.read() ).isEmpty())
                 {
                 	System.out.println("receive client_servlogin9000_3 kick off msg:"+msg3);
                 }
                 else{
                 	System.out.println("client_servlogin9000_3 no data received");   
                 }
                 //判断client_servlogin9000_4是否被踢
                 String msg4=null;
                 if(!(msg4 =client_servlogin9000_4.read() ).isEmpty())
                 {
                 	System.out.println("receive client_servlogin9000_4 kick off msg:"+msg4);
                 }
                 else{
                 	System.out.println("client_servlogin9000_4 no data received");   
                 }
                 //判断client_servlogin9000_5是否被踢
                 String msg5=null;
                 if(!(msg5 =client_servlogin9000_5.read() ).isEmpty())
                 {
                 	System.out.println("receive client_servlogin9000_5 kick off msg:"+msg5);
                 }
                 else{
                 	System.out.println("client_servlogin9000_5 no data received");    
                 }
               //判断client_servlogin9000_6是否被踢
                 String msg6=null;
                 if(!(msg6 =client_servlogin9000_6.read() ).isEmpty())
                 {
                 	System.out.println("receive client_servlogin9000_6 kick off msg:"+msg6);
                 }
                 else{
                 	System.out.println("client_servlogin9000_6 no data received");   
                 }
                 //判断client_servlogin9000_7是否被踢
                 String msg7=null;
                 if(!(msg7 =client_servlogin9000_7.read() ).isEmpty())
                 {
                 	
         			message+=msg7;
                 }
                 else{
                 	message="no data received"; 
                 }
               //判断client_servlogin9000_8是否被踢
                 
                 
             	String msg8 = null;
         		if (!(msg8 = client_servlogin9000_8.read()).isEmpty()) {
         				
         				message+=msg8;
         			}
         		else {
         			message="no data received";
         		}
         		
		
        client_servlogin9001_1.close(); 
        client_servlogin9001_2.close(); 
        client_servlogin9001_3.close(); 
        client_servlogin9001_4.close(); 
        client_servlogin9001_5.close(); 
        client_servlogin9001_6.close(); 
        client_servlogin9001_7.close();   
        client_servlogin9001_8.close(); 
	}
}


