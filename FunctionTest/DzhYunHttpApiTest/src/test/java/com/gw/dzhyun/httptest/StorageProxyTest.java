package com.gw.dzhyun.httptest;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.atopcloud.util.MyHttpUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.dzhyun.proxy.DataCompare;
import com.gw.dzhyun.proxy.Dzhstorageproxy;
import com.gw.dzhyun.proxy.JedisOperator;
import com.gw.dzhyun.proxy.KeysCreate;
import com.gw.dzhyun.proxy.ProxyDataTrans;
import com.gw.dzhyun.proxy.ZmqProxySocket;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.testcasefilter.MyTestCaseFilter;
import com.atopcloud.util.ByteBuffer2StringUtil;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
import com.atopcloud.util.PropertiesManager;
import com.dzhyun.proto.Dzhoutput.QuoteDynaOutput;
import com.dzhyun.proto.Dzhoutput.QuoteDynaSingle;
import com.dzhyun.proto.Dzhua.UAResponse;
import com.google.protobuf.ByteString;
import com.googlecode.protobuf.format.JsonFormat;
import com.gw.dzhyun.util.MyNewsGetUtil;
import com.gw.dzhyun.util.MyQuoteDynaUtil;
//http unit
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;






















//io
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.Properties;

public class StorageProxyTest {
	private KeysCreate keyc = null;
	private JedisOperator jop = null ;
	private ZmqProxySocket zmproxysocket = null;
	private Dzhstorageproxy.StoreRequest  srequest = null;
	public StorageProxyTest()
	{
		
	}
	@Before
	public void init()
	{
		this.keyc = new KeysCreate();
		this.jop = new JedisOperator();
		zmproxysocket = new ZmqProxySocket();
	}
	
	@Ignore
	@Test
	public void testOneKeyList()
	{
//		String keys = this.keyc.getKey();
//		Long num = this.jop.delKey(keys);
//		ArrayList<String> alist = this.keyc.getArrayList(500, 2);
//		System.out.println("alist="+alist.toString());
//		Dzhstorageproxy.ListStoreRequest x =ProxyDataTrans.crateStoreListStoreRequest();
//		ArrayList<Dzhstorageproxy.ListStoreRequest> ylist = new ArrayList<Dzhstorageproxy.ListStoreRequest>();
//		ylist.add(x);
		this.srequest = ProxyDataTrans.crateStoreRequest(this.keyc,1,1,true,1);
		System.out.println("xx="+ProxyDataTrans.getKeysByStoreRequest(this.srequest)[0]);
		try {
			//System.out.println("keys="+keys.substring(0, 7)+ keys.substring(7, keys.length()));
			zmproxysocket.sendOrResvData(this.srequest);
			String keystr = ProxyDataTrans.getKeysByStoreRequest(this.srequest)[0];
			ArrayList<String> xlist = this.jop.getJredisList(keystr, 0, 1);
			ArrayList<String> alist = ProxyDataTrans.getListByStoreRequestOrKey(keystr, this.srequest);
			System.out.println("keystr="+keystr+"xlist="+xlist.toString()+"alist="+alist.toString());
			assert(DataCompare.compareArrayList(alist,xlist)==1);
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


		public int testOutputJsonxing(String code) throws SAXException,Exception
		{
			String urlString = "http://" + "10.15.144.80" + ":" +80 + "/quote/dyna?obj=" + code + "&output=json";  //每个测试方法需要修改
			urlString ="http://10.15.144.80/indicator/calc?obj=" + code +"&name=ARBR&period=1min&start=1&count=33";
			String type="json";
			int retcode=0;
			String ret =MyHttpUtil.getQuoteDyna(urlString,type);
			//System.out.println("ret="+ret);

			//JSONObject data = MyNewsGetUtil.getNews(ret);
			JSONObject data = MyNewsGetUtil.getIndicatorCalc(ret);
			if(data == null)
			{
				System.out.println("error code="+code);
				retcode = 1;
			}
			return retcode;

		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList alt = new ArrayList();
		StorageProxyTest spt = new StorageProxyTest();
		try {
			int i = spt.testOutputJsonxing("SZ399961");
			System.out.println("i="+i);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
