import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;


public class TCPClient {

  /**
   * @param args
   * @throws Exception 
   * @throws UnknownHostException 
   * TCP套接字由，源IP地址，源端口号，目标IP地址，目标端口号组成的。
   */
  public static void main(String[] args) throws UnknownHostException, Exception {
    // TODO Auto-generated method stub
    String sentence;
    String modifiedSentence;
    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));	// 获取键盘输入的数据
    Socket clientSocket = new Socket("10.15.44.99", 6789);								// 建立连接时传入IP地址和端口号，TCP是可靠的连接。
    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    sentence = inFromUser.readLine();
    outToServer.writeBytes(sentence + '\n');
    modifiedSentence = inFromServer.readLine();
    System.out.println("From Server: " + modifiedSentence);
    clientSocket.close();
  }

}