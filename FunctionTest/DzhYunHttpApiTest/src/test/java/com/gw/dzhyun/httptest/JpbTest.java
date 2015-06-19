/**
 *验证云平台提供的 http格式请求。
 *使用了HttpUnit工具和JUnit框架。
 *1）、junit是java单元测试框架
 *2）、HttpUnit作为junit的辅助工具，可以理解为api提供者。
 */
package com.gw.dzhyun.httptest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
//import com.gw.dzhyun.util.MyQuoteDynaUtil;
import com.gw.dzhyun.util.MykbspiritUtil;
//
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;
/**
 * @author Lizhiqiang
 *
 */
public class JpbTest {
	//变量
	String ip = MyConfigUtil.getConfig("ip");
	String port=MyConfigUtil.getConfig("port");
	String input= "pfyh";    //键盘宝输入，沪深股拼音或代码
	String type= "0";		//0证券、1指标
	String market= "SH";	//SH、SZ市场分类
	String count= "1";
	String delist= "0";		//0不过滤退市股票，1过滤，默认为1
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//System.out.println("setup");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		//System.out.println("teardown");
	}

	
	/**
	 * 1.1.1根据拼音/代码/汉字查询股票（汉字查询不正确）
        input=p&type=0
	 * @throws Exception 
	 */
	@Test
	public void t1estpyJpb() throws Exception {
		input="p";
		type= "0";
		
		//kbspirit?input=p&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 1.1.2根据拼音/代码/汉字查询股票（汉字查询不正确）
        input=pfyh&type=0
	 * @throws Exception 
	 */
	@Test
	public void t2estQuanpinJpb() throws Exception {
		input="pfyh";
		type= "0";
		
		//kbspirit?input=pfyh&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}

	/**
	 * 1.1.3根据拼音/代码/汉字查询股票（汉字查询不正确）
        input=6&type=0
	 * @throws Exception 
	 */
	@Test
	public void t3estpcodeJpb() throws Exception {
		input="6";
		type= "0";
		
		//kbspirit?input=6&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 1.1.4根据拼音/代码/汉字查询股票（汉字查询不正确）
        input=600000&type=0
	 * @throws Exception 
	 */
	@Test
	public void t4estcodeJpb() throws Exception {
		input="600000";
		type= "0";
		
		//kbspirit?input=600000&type=0
		//http://10.15.144.80/kbspirit?input=天晟新材&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 1.1.5根据拼音/代码/汉字查询股票（汉字查询不正确）
        input=SH0&type=0
	 * @throws Exception 
	 */
	@Test
	public void t5estmcodeJpb() throws Exception {
		input="SH0";
		type= "0";
		
		//kbspirit?input=SH0&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 1.1.6根据拼音/代码/汉字查询股票（汉字查询不正确）
        input=重&type=0
	 * @throws Exception 
	 */
	@Test
	public void t6estChineseJpb() throws Exception {
		input="重";
		type= "0";
		
		//kbspirit?input=重&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(ret);
	}
	
	/**
	 * 1.1.7根据拼音/代码/汉字查询股票
        input=天晟新材&type=0 (繁体字)
	 * @throws Exception 
	 */
	@Test
	public void t7estfChineseJpb() throws Exception {
		input="天晟新材";
		type= "0";
		
		//kbspirit?input=天晟新材&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(ret);
	}
	
	/**
	 * 1.2查询指标
        input=MA&type=1
	 * @throws Exception 
	 */
	@Test
	public void t8estIndicatorJpb() throws Exception {
		input="MA";
		type= "1";
		
		//kbspirit?input=MA&type=1
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(ret);
	}
	
	/**
	 * 1.3.1按代码、市场查询股票
        input&type&market
	 * @throws Exception 
	 */
	@Test
	public void t9estSHJpb() throws Exception {
		input="60";
		type= "0";
		market="SH";
				
		//kbspirit?input=60&type=0&market=SH
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&market=" + market;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 1.3.2按代码、市场查询股票
        input&type&market
	 * @throws Exception 
	 */
	@Test
	public void t10estSZJpb() throws Exception {
		input="60";
		type= "0";
		market="SZ";
				
		//kbspirit?input=60&type=0&market=SZ
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&market=" + market;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 1.3.3按代码、市场查询股票
        input&type&market
	 * @throws Exception 
	 */
	@Test
	public void t11estSHSZJpb() throws Exception {
		input="605";
		type= "0";
		market="SH,SZ";
				
		//kbspirit?input=605&type=0&market=SH,SZ
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&market=" + market;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 1.4按代码、市场查询一定数量股票(通过脚本查询出count数量为2*count,通过网页查询正确)
        input&type&market&count
	 * @throws Exception 
	 */
	@Test
	public void t12estcountJpb() throws Exception {
		input="60";
		type= "0";
		market="SH,SZ";
		count="22";
				
		//kbspirit?input=60&type=0&market=SH&count=22
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&market=" + market+ "&count=" + count;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 1.5.1搜索串长度<=4时，会先查找后四位有没有，然后再从第一位开始匹配
        input&type&market&count
	 * @throws Exception 
	 */
	@Test
	public void t13est4lengthJpb() throws Exception {
		input="0016";
		type= "0";
				
		//kbspirit?input=0016&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 1.5.2搜索长度>4时，必须第一位就匹配
        input&type&market&count
	 * @throws Exception 
	 */
	@Test
	public void t14est5lengthJpb() throws Exception {
		input="60151";
		type= "0";
				
		//kbspirit?input=60151&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 1.6.1多音字
        input=ccdg(长城电工)
	 * @throws Exception 
	 */
	@Test
	public void t15estMultiPyJpb() throws Exception {
		input="ccdg";
		type= "0";
				
		//kbspirit?input=z&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	/**
	 * 1.6.2多音字
        input=zcdg(长城电工)
	 * @throws Exception 
	 */
	@Test
	public void t16estMultiPyJpb() throws Exception {
		input="zcdg";
		type= "0";
				
		//kbspirit?input=z&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	/**
	 * 1.7.1退市股票(暂无支持的退市股票)
       delist=0不过滤
	 * @throws Exception 
	 */
	@Test
	public void t17estNdelistJpb() throws Exception {
		input="SH601299";
		type= "0";
		delist="0";
				
		//kbspirit?input=SH601299&type=0&delist=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(ret);
	}
	
	/**
	 * 1.7.2退市股票(暂无支持的退市股票)
       delist=1过滤
	 * @throws Exception 
	 */
	@Test
	public void t18estdelistJpb() throws Exception {
		input="SH601299";
		type= "0";
		delist="0";
				
		//kbspirit?input=SH601299&type=0&delist=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(ret);
	}
	
}
