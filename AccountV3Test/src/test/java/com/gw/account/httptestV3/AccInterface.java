package com.gw.account.httptestV3;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by Hihiri on 2015/6/2.
 */
public class AccInterface {
    private static final Log LOG = LogFactory.getLog(AccInterface.class);
    private static String serverIP = "http://10.15.108.4:8080/";

    public static String testXcscbind(String params) throws IOException, SAXException {
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/xcscbind";
        LOG.debug("xcscbind: " + urlString + "," + params);
        PostMethodWebRequest post = new PostMethodWebRequest(urlString, new ByteArrayInputStream(params.getBytes()), "UTF-8");
        WebResponse response = web.getResponse(post);
        LOG.debug("xcscbind: " + response.getText());
        return response.getText();
    }

    public static String testAdduserex(String params) throws IOException, SAXException {
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/adduserex";
        LOG.debug("adduserex: " + urlString + "," + params);
        PostMethodWebRequest post = new PostMethodWebRequest(urlString, new ByteArrayInputStream(params.getBytes()), "UTF-8");
        WebResponse response = web.getResponse(post);
        LOG.debug("adduserex: " + response.getText());
        return response.getText();
    }

    public static String testUpdpass(String params) throws IOException, SAXException {
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/updpass";
        LOG.debug("updpass: " + urlString + "," + params);
        PostMethodWebRequest post = new PostMethodWebRequest(urlString, new ByteArrayInputStream(params.getBytes()), "UTF-8");
        WebResponse response = web.getResponse(post);
        LOG.debug("updpass: " + response.getText());
        return response.getText();
    }


}
