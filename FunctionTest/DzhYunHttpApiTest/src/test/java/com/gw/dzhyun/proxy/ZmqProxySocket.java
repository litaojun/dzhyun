package com.gw.dzhyun.proxy;

import org.zeromq.ZMQ;

import com.atopcloud.util.MyConfigUtil;
import com.google.protobuf.InvalidProtocolBufferException;
public class ZmqProxySocket {
    private static ZMQ.Socket socket = null;
    private  ZMQ.Socket zmqsocket = null;
    public ZmqProxySocket()
    {
    	ZmqProxySocket.init();
    	ZMQ.Context context = ZMQ.context(1); 
    	this.zmqsocket = context.socket(ZMQ.DEALER);  
    	String proxyConnStr = MyConfigUtil.getConfig("proxyConnStr");
    	this.zmqsocket.connect(proxyConnStr);
    }
    private static void init()
    {
    	if(socket==null)
    	{
    		
    		ZMQ.Context context = ZMQ.context(1); 
    		
    		socket = context.socket(ZMQ.DEALER);

    		socket.setIdentity("Client1".getBytes());
    		String proxyConnStr = MyConfigUtil.getConfig("proxyConnStr");
    		socket.connect(proxyConnStr);
    	}
    }
    public static ZMQ.Socket getSocket()
    {
    	if(socket == null)
    		init();
    	return socket;
    }
    public Dzhstorageproxy.StoreResponse sendOrResvData(Dzhstorageproxy.StoreRequest a ) throws InvalidProtocolBufferException
    {
    	Dzhstorageproxy.StoreResponse dsr=null;
    	//ZmqProxySocket.getSocket().send(a.toByteArray());
    	this.zmqsocket.send(a.toByteArray(),0);
		//dsr = Dzhstorageproxy.StoreResponse.parseFrom(ZmqProxySocket.getSocket().recv());\
    	dsr = Dzhstorageproxy.StoreResponse.parseFrom(this.zmqsocket.recv());
    	return dsr;
    }
    public void closeZmqSocket()
    {
    	this.zmqsocket.close();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
