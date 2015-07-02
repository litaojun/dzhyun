package com.gw.dzhyun.httptest;
/**
 *验证云平台提供的 http格式请求。
 *使用了HttpUnit工具和JUnit框架。
 *1）、junit是java单元测试框架
 *2）、HttpUnit作为junit的辅助工具，可以理解为api提供者。
 *功能说明：
 *1）参数从resources/沪深A股.txt读取，依次遍历所有；
 */
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Filter;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSONArray;
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
import com.gw.dzhyun.util.GetTestJson;
import com.gw.dzhyun.util.JsonFormatAssert;
import com.gw.dzhyun.util.MyNewsGetUtil;

import com.gw.dzhyun.util.TestCaseManagr;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Properties;

import org.xml.sax.SAXException;

import com.gw.dzhyun.httpinterface.NewsGetInterface;
import com.gw.dzhyun.util.GetTestJson;



@RunWith(Parameterized.class)
public class NewsGetTestPara extends GetTestJson {

	private int retcode;
	JsonFormatAssert jsonAssert = new JsonFormatAssert();
	public NewsGetTestPara(String code,String type,String error,int retcode)
	{
		this.code = code;
		this.type = type;
		this.error = error;
		this.retcode = retcode;
	}

	@Test
	public void testMulStockNinetype() throws SAXException, Exception
	{
		 //System.out.println("222222222222222222222222222222222222222");
		// TODO Auto-generated method stub
			JSONObject data = this.getJSONObject(this.code,this.type);
			if(this.retcode==0)
			{
				//assertNotNull("错误：股票数据为null",data);
				JSONArray jarr = this.getJSONArrayByString();
				if(jarr!=null)
				       assertSame(jsonAssert.assertJsonArray(jarr, new String[]{"ver","act","newsID","newsTitle"}),0);
			}
			else
				assertNull("错误：//股票数据为null",data);
			int retcodeqq = this.getRetCodeByString();
			assertSame(retcodeqq,this.retcode);
			JSONObject parentData = this.getJSONParentByStr();
			assertSame(jsonAssert.assertJson(parentData,new String[]{"Qid","Err","Counter","Data"}),0);
			
	}
	@Parameters 
	public static Collection dateFeed() throws IOException
	{
      TestCaseManagr a = new TestCaseManagr("E:\\环境文档\\测试用例.xlsx","Sheet1",9);
      return a.traveCollection();
//		return Arrays.asList(new Object[][] { 
//            {"SH600000","1",null,0},
//            {"SH600004","9",null,0},
//            {"SH600005,SH600006,SH600007","1",null,0},
//            {"SH600010,SH600008,SH600009","9",null,0},
//            {"","1",null,-1},
//            {"SH600011","",null,-1},
//            {"SH99999916","1",null,0},
//            {"SH600012","2",null,-1},
//            {"SH600013","9","&typex=9",0},
//            {"SH600000,SH600004,SH600005,SH600006,SH600007,SH600008,SH600009,SH600010,SH600011,SH600012,SH600015,SH600016,SH600017,SH600018,SH600019,SH600020,SH600021,SH600022,SH600023,SH600026,SH600027,SH600028,SH600029,SH600030,SH600031,SH600033,SH600035,SH600036,SH600037,SH600038,SH600039,SH600048,SH600050,SH600051,SH600052,SH600053,SH600054,SH600055,SH600056,SH600057,SH600058,SH600059,SH600060,SH600061,SH600062,SH600063,SH600064,SH600066,SH600067,SH600068,SH600069,SH600070,SH600071,SH600073,SH600074,SH600076,SH600077,SH600078,SH600079,SH600080,SH600081,SH600082,SH600083,SH600084,SH600085,SH600086,SH600088,SH600089,SH600090,SH600093,SH600094,SH600095,SH600096,SH600097,SH600098,SH600099,SH600100,SH600101,SH600103,SH600104,SH600105,SH600106,SH600107,SH600108,SH600109,SH600110,SH600111,SH600112,SH600113,SH600114,SH600115,SH600116,SH600117,SH600118,SH600119,SH600120,SH600121,SH600122,SH600123,SH600125,SH600126,SH600127,SH600128,SH600129,SH600130,SH600131,SH600132,SH600133,SH600135,SH600136,SH600137,SH600138,SH600139,SH600141,SH600143,SH600146,SH600148,SH600149,SH600150,SH600151,SH600152,SH600153,SH600155,SH600156,SH600157,SH600158,SH600159,SH600160,SH600161,SH600162,SH600163,SH600165,SH600166,SH600167,SH600168,SH600169,SH600170,SH600171,SH600172,SH600173,SH600175,SH600176,SH600177,SH600179,SH600180,SH600182,SH600183,SH600184,SH600185,SH600186,SH600187,SH600188,SH600189,SH600190,SH600191,SH600192,SH600193,SH600195,SH600196,SH600197,SH600198,SH600199,SH600200,SH600201,SH600202,SH600203,SH600206,SH600207,SH600208,SH600209,SH600210,SH600211,SH600212,SH600213,SH600215,SH600216,SH600217,SH600218,SH600219,SH600220,SH600221,SH600222,SH600223,SH600225,SH600226,SH600227,SH600229,SH600230,SH600231,SH600232,SH600233,SH600234,SH600235,SH600236,SH600237,SH600238,SH600239,SH600240,SH600241","1",null,0}
//		});
	}


}
