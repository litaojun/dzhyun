package com.gw.account.tcptestV3;

import com.alibaba.fastjson.JSON;
import com.gw.account.utils.MyCheckUtil;
import com.gw.account.utils.TcpClient;
import com.gw.account.utils.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

import java.security.NoSuchAlgorithmException;


/**
 * Created by Hihiri on 2015/4/7.
 */
public class LogOutTcpTest {
    //=================================正常注册=======================================

    /**
     * 只填帐号后缀生成方式：rand
     */
    @Test
    public void testLogOut() throws IOException, SAXException, NoSuchAlgorithmException {
        String request = JSON.toJSONString(
        		TcpClient.M(
        				"uname" , "zhangchaoxu"//"testcrmv3007",
         	
                 )
        );
        AccInterfaceTcp.testLogOut(request);
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
    }
}
