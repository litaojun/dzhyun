package com.gw.account.tcptestV3;

public class T {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		VirtualHqUser_tobe_kickoffed vhq1=new VirtualHqUser_tobe_kickoffed();
		VirtualHqUser_to_kickoff_others vhq2=new VirtualHqUser_to_kickoff_others();
		
		//test
		vhq1.start();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		vhq2.start();
		
		Thread.sleep(1000);
		vhq1.join();
		vhq2.join();

		System.out.println("test over!");
	}

}
