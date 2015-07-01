    package com.gw.dzhyun.util;  
      
    import org.zeromq.ZMQ;  

import com.gw.dzhyun.proxy.Dzhstorageproxy;
      
    public class Request {  
        public static void main(String args[]) {  
            for (int j = 0;  j < 1; j++) {  
                new Thread(new Runnable(){  
      
                    public void run() {  
                        // TODO Auto-generated method stub  
                        ZMQ.Context context = ZMQ.context(1);  //创建一个I/O线程的上下文  
                        ZMQ.Socket socket = context.socket(ZMQ.DEALER);   //创建一个request类型的socket，这里可以将其简单的理解为客户端，用于向response端发送数据
                        
                        //System.out.println("yyyyyyy");
                        socket.connect("tcp://10.15.144.80:10700");   //与response端建立连接  
                        //System.out.println("xxxx");
                        long now = System.currentTimeMillis();  
                           // String request = "hello";  
                        System.out.println("xxxx");
                        //socket.send(request.getBytes());   //向reponse端发送数据
                         socket.send(Dzhstorageproxy.getxxByte(),0);
                         System.out.println("yyyyyyy");
                         byte[] response = socket.recv();   //接收response发送回来的数据  正在request/response模型中，send之后必须要recv之后才能继续send，这可能是为了保证整个request/response的流程走完
                         System.out.println("zzzz");
                         Dzhstorageproxy.StoreResponse dsr = Dzhstorageproxy.tranByteToObject(response);
                         System.out.println(dsr.getId());
                		 System.out.println(dsr.getSeq());
                		 System.out.println(dsr.getCommon(0).getResult());
                         socket.close();
                         long after = System.currentTimeMillis();  
                         System.out.println((after - now) / 1000);  
                    }  
                      
                }).start();
            }  
              
        }  
    }  
