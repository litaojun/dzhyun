package com.gw.account.tcptest;

import com.gw.account.utils.TcpClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Created by Hihiri on 2015/4/9.
 */
public class AccInterfaceTcp {
    private static final Log LOG = LogFactory.getLog(AccInterfaceTcp.class);
    private static String serverIP = "10.15.201.106";
    private static int serverPort = 32226;

    public static String testAdduserexTcp(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        LOG.debug("AdduserexTCP: " + urlparam);
        TcpClient client = new TcpClient();
        client.open(serverIP, serverPort);
        String response = client.adduserex(urlparam);
        client.close();
        LOG.debug("AdduserexTCP: " + response);
        return response;
    }

    public static String testUsergetTcp(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        LOG.debug("usergetTCP: " + urlparam);
        TcpClient client = new TcpClient();
        client.open(serverIP, serverPort);
        String response = client.userget(urlparam);
        client.close();
        LOG.debug("usergetTCP: " + response);
        return response;
    }

    public static String testUpdpassTcp(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        LOG.debug("updpassTCP: " + urlparam);
        TcpClient client = new TcpClient();
        client.open(serverIP, serverPort);
        String response = client.updpass(urlparam);
        client.close();
        LOG.debug("updpassTCP: " + response);
        return response;
    }
}
