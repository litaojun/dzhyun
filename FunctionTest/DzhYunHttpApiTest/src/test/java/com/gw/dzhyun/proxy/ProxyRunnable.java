package com.gw.dzhyun.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProxyRunnable implements Runnable {
	public static List<String> grouptitle = null;
	public static List<String> obj = null;
	public ProxyRunnable()
	{
		if(grouptitle ==null)
		{
			grouptitle =  ProxyDataTrans.getArrayList(7,10);
			System.out.println("grouptitle="+grouptitle.toString());
		}
		if(obj ==null)
		{
			obj = ProxyDataTrans.getArrayList(20,100);
			System.out.println("obj="+obj.toString());
		}
	}
	public void run() {
		// TODO Auto-generated method stub
		
			try {
				while(true)
				{
					System.out.println("xxxx");
					ZmqProxySocket zps = new ZmqProxySocket();
				     zps.sendOrResvData(this.getStoreRequest());
//				     try {
//						this.wait(5);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				     zps.closeZmqSocket();
				     System.out.println("yyyyyyyyy");
				     try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("fffffffffffffffffffffff");
	}
	public Dzhstorageproxy.StoreRequest getStoreRequest()
	{
		List<String> tlt = new ArrayList<String>();
		int i = new Random().nextInt(8000);
		//List<String> grouptitle =  ProxyDataTrans.getArrayList();
		//List<String> obj = ProxyDataTrans.getArrayList();
		String[] aa = new String[2];
		aa[0] = ProxyRunnable.grouptitle.get(ProxyDataTrans.getRandomInt(ProxyRunnable.grouptitle.size()-1));
		aa[1] = ProxyRunnable.obj.get(ProxyDataTrans.getRandomInt(ProxyRunnable.obj.size()-1));
		tlt = ProxyDataTrans.getArrayList(10,20);
		Dzhstorageproxy.ListStoreRequest ss = ProxyDataTrans.crateStoreListStoreRequest(true, 5000,aa[0], aa[1], tlt);
		List<Dzhstorageproxy.ListStoreRequest> kk = new ArrayList<Dzhstorageproxy.ListStoreRequest>();
		kk.add(ss);
		Dzhstorageproxy.StoreRequest ff = ProxyDataTrans.crateStoreRequest(ProxyDataTrans.getRandomInt(1000), ProxyDataTrans.getRandomInt(1000), kk);
		return ff;
	}
	public static void main(String[] args)
	{
		ProxyRunnable x = new ProxyRunnable();
		for(int i=0;i<100;i++)
			new Thread(new ProxyRunnable(),"xx"+i).start();
	}

}
