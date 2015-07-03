package com.gw.account.httptest;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

import java.io.IOException;

//


public class AccInterface {
    private static final Log LOG = LogFactory.getLog(AccInterface.class);
    private static String serverIP = "http://10.15.108.4:8080/";

    //1.adduser接口的构造方法
    public static String testAdduser(String casename, String unameinput) throws IOException, SAXException {
        LOG.debug(casename);
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=adduser&uname=" + unameinput + "";
        LOG.debug("testAdduser: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("testAdduser: " + response.getText());
        return response.getText();

    }

    //adduser接口的构造方法
    public static String testAdduser(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();

        //	LOG.debug("Request:"+urlparam.trim());
//		LOG.debug("RequestPararms:"+urlparam.trim());
        String urlString = serverIP + "AccService/AccServlet.do?method=adduser" + urlparam.trim();
//		LOG.debug("Request:" + urlparam.trim());
//		String urlString = serverIP + "AccService/AccServlet.do?method=adduser"+urlparam.trim();
//        LOG.debug("Url: "+ urlString);
        LOG.debug("testAdduser: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        //	LOG.debug("Response:" +response.getText());
//		LOG.debug("Response:" +response.getText());
        //	LOG.debug("testAdduser: " +response.getText());
//		LOG.debug("Response:" +response.getText());
        LOG.debug("testAdduser: " + response.getText());
        return response.getText();
    }

    //adduserex接口的构造方法
    public static String testAdduserex(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        LOG.debug("Request:" + urlparam.trim());
        String urlString = serverIP + "AccService/AccServlet.do?method=adduserex" + urlparam.trim();
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("Response:" + response.getText());
        return response.getText();
    }

    //2.userget接口的构造方法
    public static String testUserget(String casename, String unameinput) throws IOException, SAXException {
        LOG.debug(casename);
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=userget&uname=" + unameinput + "";
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        return response.getText();
    }

    //userget接口的构造方法
    public static String testUserget(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        LOG.debug("Request:" + urlparam.trim());
        String urlString = serverIP + "AccService/AccServlet.do?method=userget" + urlparam.trim();
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("Response:" + response.getText());
        return response.getText();
    }

    //	//3.login接口的构造方法
//	public static String testLogin(String casename, String unameinput, String upassinput) throws IOException, SAXException{
//	    LOG.debug(casename);
//		WebConversation  	web = new WebConversation();
//		String urlString = serverIP + "AccService/AccServlet.do?method=login&uname="+unameinput+"&upass="+upassinput;
//        LOG.debug("testLogin: " + urlString);
//		GetMethodWebRequest get = new GetMethodWebRequest(urlString);
//		WebResponse response = web.getResponse(get);
//        LOG.debug("testLogin: " + response.getText());
//		return response.getText();
//	}
    //login接口的构造方法
    public static String testLogin(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=login" + urlparam.trim();
        LOG.debug("testLogin: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("testLogin: " + response.getText());
        return response.getText();
    }

//	//4.logout接口的构造方法
//	public static String testLogout(String casename, String unameinput) throws IOException,SAXException{
//		    LOG.debug(casename);
//			WebConversation  	web = new WebConversation();
//			String urlString = serverIP + "AccService/AccServlet.do?method=logout&uname="+unameinput+"";
//            LOG.debug("testLogout: " + urlString);
//			GetMethodWebRequest get = new GetMethodWebRequest(urlString);
//			WebResponse response = web.getResponse(get);
//            LOG.debug("testLogout: " + response.getText());
//			return response.getText();
//	}

    //logout接口的构造方法
    public static String testLogout(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=logout" + urlparam.trim();
        LOG.debug("testLogout: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("testLogout: " + response.getText());
        return response.getText();
    }

    //userbind接口的构造方法
    public static String testUserbind(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=userkeybind" + urlparam.trim();
        LOG.debug("testUserbind: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("testUserbind: " + response.getText());
        return response.getText();
    }

    //getuserbind接口的构造方法
    public static String testGetUserbind(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=getuserbind" + urlparam.trim();
        LOG.debug("testGetUserbind: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("testGetUserbind: " + response.getText());
        return response.getText();
    }

    //getuserbind接口的构造方法
    public static String testDelUserbind(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=deluserbind" + urlparam.trim();
        LOG.debug("testDelUserbind: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("testDelUserbind: " + response.getText());
        return response.getText();
    }


    public static String testFindUnamebyKey(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=findunamebykey" + urlparam.trim();
        LOG.debug("findunamebykey: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("findunamebykey: " + response.getText());
        return response.getText();
    }

    public static String testAdduser365(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=adduser365" + urlparam.trim();
        LOG.debug("adduser365: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("adduser365: " + response.getText());
        return response.getText();
    }

    public static String testCheckuser(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=checkuser" + urlparam.trim();
        LOG.debug("checkuser: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("checkuser: " + response.getText());
        return response.getText();
    }

    public static String testCheckpass(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=checkpass" + urlparam.trim();
        LOG.debug("checkpass: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("checkpass: " + response.getText());
        return response.getText();
    }

    public static String testSendsms(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=sendsms" + urlparam.trim();
        LOG.debug("sendsms: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("sendsms: " + response.getText());
        return response.getText();
    }

    public static String testLotterbind(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=lotterbind" + urlparam.trim();
        LOG.debug("lotterbind: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("lotterbind: " + response.getText());
        return response.getText();
    }

    public static String testAdduserexNew(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=adduserex" + urlparam.trim();
        LOG.debug("adduserex: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("adduserex: " + response.getText());
        return response.getText();
    }

    public static String testUpdpassNew(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=updpass" + urlparam.trim();
        LOG.debug("updpass: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("updpass: " + response.getText());
        return response.getText();
    }

    public static String testUsergetNew(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=userget" + urlparam.trim();
        LOG.debug("userget: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("userget: " + response.getText());
        return response.getText();
    }

    public static String testUpdextra(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=updextra" + urlparam.trim();
        LOG.debug("updextra: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("updextra: " + response.getText());
        return response.getText();
    }

    public static String testAddbankcards(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=addbankcards" + urlparam.trim();
        LOG.debug("addbankcards: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("addbankcards: " + response.getText());
        return response.getText();
    }

    public static String testGetbankcards(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=getbankcards" + urlparam.trim();
        LOG.debug("getbankcards: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("getbankcards: " + response.getText());
        return response.getText();
    }

    public static String testGetmaskbankcards(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=getmaskbankcards" + urlparam.trim();
        LOG.debug("getmaskbankcards: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("getmaskbankcards: " + response.getText());
        return response.getText();
    }

    public static String testDelbankcards(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=delbankcards" + urlparam.trim();
        LOG.debug("delbankcards: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("delbankcards: " + response.getText());
        return response.getText();
    }

    public static String testAdduserNew(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=adduser" + urlparam.trim();
        LOG.debug("adduser: " + urlString);
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("adduser: " + response.getText());
        return response.getText();
    }


    //5.updpass接口的构造方法
    public static String Updpass(String casename, String unameinput, String upassinput) throws IOException, SAXException {
        LOG.debug(casename);
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=updpass&uname=" + unameinput + "&upass" + upassinput + "";
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("\n");
        return response.getText();
    }

    //Updpass接口的构造方法
    public static String testUpdpass(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        LOG.debug("Request:" + urlparam.trim());
        //	LOG.debug("Request:"+urlparam.trim());
        String urlString = serverIP + "AccService/AccServlet.do?method=updpass" + urlparam.trim();
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        //	LOG.debug("Response:" +response.getText());
        LOG.debug("Response:" + response.getText());
        return response.getText();
    }

    /*

        //login接口的构造方法
        public static String testUpdpass(String params) throws IOException,SAXException{
            StringBuffer sb = new StringBuffer();
            sb.append(params);
            String urlparam = sb.toString();
            WebConversation web = new WebConversation();
            String urlString = "http://10.15.108.114:9001/AccService/AccServlet.do?method=updpass"+urlparam.trim();
            GetMethodWebRequest get = new GetMethodWebRequest(urlString);
            WebResponse response = web.getResponse(get);
            LOG.debug(response.getText());
            return response.getText();
        }
    */
    //6.updbase接口的构造方法
    public static String testUpdbase(String casename, String unameinput) throws IOException, SAXException {
        LOG.debug(casename);
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=updbase&uname=" + unameinput + "";
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("\n");
        return response.getText();
    }

    //updbase接口的构造方法
    public static String testUpdbase(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=updbase" + urlparam.trim();
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug(response.getText());
        return response.getText();
    }

    //7.updextra接口的构造方法
    public static String testUpextra(String casename, String unameinput, String uMaketinput, String extrabufinput) throws IOException, SAXException {
        LOG.debug(casename);
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=updextra&uname=" + unameinput + "&uMaket=" + uMaketinput + "&extrabuf=" + extrabufinput + "";
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("\n");
        return response.getText();
    }

    //updextra接口的构造方法
    public static String testUpextra(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=updextra" + urlparam.trim();
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug(response.getText());
        return response.getText();
    }

    //8.deluser接口的构造方法
    public static String testDeluser(String casename, String unameinput) throws IOException, SAXException {
        LOG.debug(casename);
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=deluser&uname=" + unameinput + "&upass=" + unameinput + "";
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug("\n");
        return response.getText();
    }

    //deluser接口的构造方法
    public static String testDeluser(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=deluser" + urlparam.trim();
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug(response.getText());
        return response.getText();
    }


    //findunamebykey
    public static String testFindunamebykey(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=findunamebykey" + urlparam.trim();
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug(response.getText());
        return response.getText();
    }

    //findunamebykeyD-大写
    public static String testFindunamebykeyD(String params) throws IOException, SAXException {
        StringBuffer sb = new StringBuffer();
        sb.append(params);
        String urlparam = sb.toString();
        WebConversation web = new WebConversation();
        String urlString = serverIP + "AccService/AccServlet.do?method=FindUnameByKey" + urlparam.trim();
        GetMethodWebRequest get = new GetMethodWebRequest(urlString);
        WebResponse response = web.getResponse(get);
        LOG.debug(response.getText());
        return response.getText();
    }
}


