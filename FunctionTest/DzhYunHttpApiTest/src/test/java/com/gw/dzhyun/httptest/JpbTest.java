/**
 *验证云平台提供的 http格式请求。
 *使用了HttpUnit工具和JUnit框架。
 *1）、junit是java单元测试框架
 *2）、HttpUnit作为junit的辅助工具，可以理解为api提供者。
 *
 *1）测试键盘宝中输入拼音或代码的匹配数据（不指定count时，默认输出结果的ShuJu数量<=20条）
            涉及字段：
	1、input=p、pfyh、6、600000（证券：拼音或代码，可不全）或MA（指标）（默认只搜SH/SZ市场的，如果要搜其他市场，要带market参数）
	2、type:0、1(0证券，1指标)
	3、market:SH、SZ市场分类（* 代表全部搜索）
	4、count:指定输出结果数量
	5、delist:0、1（0不过滤退市股票，1过滤，默认为1）
  2）测试键盘宝中输入主题拼音或代码的匹配数据（输出结果的ShuJu数量<=20条）
	涉及字段：
	1、input=中国证券报、pfyh、6、600000（主题的拼音或汉字--拼音至少含首字母或匹配名称中的英文字母，汉字可不全，主题成分股的代码--代码数字全）
	2、type:2(2主题)
	3、count:指定输出结果数量
	4、kuozhan：1、2（为1显示成份股obj的列表,用'\n'换行符分割；为2不显示，默认为2）
  3)测试键盘宝中输入主题拼音或代码的匹配数据（输出结果的ShuJu数量默认为5条股票、5条营业部）
           涉及字段：
    1、input=input=z、中、光大证券、机构（首字母、部分名称、股票名称、营业部名称）
    2、type:3(3龙虎榜)
    3、count:指定输出结果数量
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
 * 
 * @author Wangying001
 * @date 2015年11月12日
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
	String kuozhan="2";
	String token="5158e9ef5b9e4c059173882648549ac7";
	
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
	 * 1.1.1根据拼音/代码/汉字查询股票
        input=p&type=0
	 * @throws Exception 
	 */
	@Test
	public void t111estpyJpb() throws Exception {
		input="p";
		type= "0";
		
		//kbspirit?input=p&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
		
		//yfloat转换
		/*JSONObject  jsonyfloatResponse = TranYfloatStatic.startTrans2(ret);
		  System.out.println(jsonyfloatResponse);*/
	}
	
	/**
	 * 1.1.2根据拼音/代码/汉字查询股票
        input=pfyh&type=0
	 * @throws Exception 
	 */
	@Test
	public void t112estQuanpinJpb() throws Exception {
		input="pfyh";
		type= "0";
		
		//kbspirit?input=pfyh&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}

	/**
	 * 1.1.3根据拼音/代码/汉字查询股票
        input=6&type=0
	 * @throws Exception 
	 */
	@Test
	public void t113estpcodeJpb() throws Exception {
		input="6";
		type= "0";
		
		//kbspirit?input=6&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 1.1.4根据拼音/代码/汉字查询股票
        input=600000&type=0
	 * @throws Exception 
	 */
	@Test
	public void t114estcodeJpb() throws Exception {
		input="600000";
		type= "0";
		
		//kbspirit?input=600000&type=0
		//http://10.15.144.80/kbspirit?input=天晟新材&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 1.1.5根据拼音/代码/汉字查询股票
        input=SH0&type=0
	 * @throws Exception 
	 */
	@Test
	public void t115estmcodeJpb() throws Exception {
		input="SH0";
		type= "0";
		
		//kbspirit?input=SH0&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	
	/**
	 * 1.1.6根据拼音/代码/汉字查询股票
        input=重&type=0
	 * @throws Exception 
	 */
	@Test
	public void t116estChineseJpb() throws Exception {
		input="重";
		type= "0";
		
		//kbspirit?input=重&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
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
	public void t117estfChineseJpb() throws Exception {
		input="天晟新材";
		type= "0";
		
		//kbspirit?input=天晟新材&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 1.2.1查询指标---type=1
        input=MA&type=1
	 * @throws Exception 
	 */
	@Test
	public void t121estIndicatorJpb() throws Exception {
		input="MA";
		type= "1";
		count="2";
		
		//kbspirit?input=MA&type=1&count=2
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(ret);
	}
	
	/**
	 * 1.2.2查询指标
        input=量&type=1,有些指标只有DaiMa，没有MingCheng
	 * @throws Exception 
	 */
	@Test
	public void t122estIndicatorJpb() throws Exception {
		input="量";
		type= "1";
		
		//kbspirit?input=MA&type=1
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
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
	public void t131estSHJpb() throws Exception {
		input="602";
		type= "0";
		market="SH";
				
		//kbspirit?input=602&type=0&market=SH
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&market=" + market+ "&token=" + token;  //每个测试方法需要修改
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
	public void t132estSZJpb() throws Exception {
		input="602";
		type= "0";
		market="SZ";
				
		//kbspirit?input=60&type=0&market=SZ
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&market=" + market+ "&token=" + token;  //每个测试方法需要修改
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
	public void t133estSOJpb() throws Exception {
		input="02";
		type= "0";
		market="SO";
				
		//kbspirit?input=02&type=0&market=SO
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&market=" + market+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 1.3.4按代码、市场查询股票
        input&type&market
	 * @throws Exception 
	 */
	@Test
	public void t134estB$Jpb() throws Exception {
		input="02";
		type= "0";
		market="B$";
				
		//kbspirit?input=02&type=0&market=B$
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&market=" + market+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 1.3.5按代码、市场查询股票
        input&type&market
	 * @throws Exception 
	 */
	@Test
	public void t135estallmarketJpb() throws Exception {
		input="B$9";
		type= "0";
		market="*";
				
		//kbspirit?input=B$9&market=*&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&market=" + market+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 1.3.6按代码、市场查询股票
        input&type&market
	 * @throws Exception 
	 */
	@Test
	public void t136estallmarketJpb() throws Exception {
		input="960";
		type= "0";
		market="SH,SZ,SO,B$";
				
		//kbspirit?input=960&type=0&market=SH,SZ,SO,B$
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&market=" + market+ "&token=" + token;  //每个测试方法需要修改
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
	public void t141estcountJpb() throws Exception {
		input="007";
		type= "0";
		market="SH,SZ";
		count="90";
				
		//kbspirit?input=60&type=0&market=SH&count=90
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&market=" + market+ "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
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
	public void t151est4lengthJpb() throws Exception {
		input="0016";
		type= "0";
				
		//kbspirit?input=0016&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
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
	public void t152est5lengthJpb() throws Exception {
		input="60151";
		type= "0";
				
		//kbspirit?input=60151&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
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
	public void t161estMultiPyJpb() throws Exception {
		input="ccdg";
		type= "0";
				
		//kbspirit?input=z&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
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
	public void t162estMultiPyJpb() throws Exception {
		input="zcdg";
		type= "0";
				
		//kbspirit?input=z&type=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
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
	public void t171estNdelistJpb() throws Exception {
		input="SH601299";
		type= "0";
		delist="0";
				
		//kbspirit?input=SH601299&type=0&delist=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
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
	public void t172estdelistJpb() throws Exception {
		input="SH601299";
		type= "0";
		delist="0";
				
		//kbspirit?input=SH601299&type=0&delist=0
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(ret);
	}
	
	
	/**
	 * 2.1.1主题投资相关----type=2
	 * 根据主题的首字母、主题中文名称(全或部分)、成分股完整代码查询主题
        input=z&type=2
	 * @throws Exception 
	 */
	@Test
	public void t211estztJpb() throws Exception {
		input="z";
		type= "2";
		
		//kbspirit?input=z&type=2
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 2.1.2主题投资相关----type=2
	 * 根据主题的首字母、主题中文名称(全或部分)、成分股完整代码查询主题
        input=z&type=2
	 * @throws Exception 
	 */
	@Test
	public void t212estztJpb() throws Exception {
		input="znjj";
		type= "2";
		
		//kbspirit?input=znjj&type=2
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 2.1.3主题投资相关----type=2
	 * 根据主题的首字母、主题中文名称(全或部分)、成分股完整代码查询主题
        input=z&type=2
	 * @throws Exception 
	 */
	@Test
	public void t213estztJpb() throws Exception {
		input="2";
		type= "2";
		
		//kbspirit?input=2&type=2
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 2.1.4主题投资相关----type=2
	 * 根据主题的首字母、主题中文名称(全或部分)、成分股完整代码查询主题
        input=z&type=2
	 * @throws Exception 
	 */
	@Test
	public void t214estztJpb() throws Exception {
		input="家";
		type= "2";
		
		//kbspirit?input=家&type=2
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 2.1.5主题投资相关----type=2
	 * 根据主题的首字母、主题中文名称(全或部分)、成分股完整代码查询主题
        input=z&type=2
	 * @throws Exception 
	 */
	@Test
	public void t215estztJpb() throws Exception {
		input="600221";
		type= "2";
		
		//kbspirit?input=600221&type=2
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 2.1.6主题投资相关----type=2
	 * 根据主题的首字母、主题中文名称(全或部分)、成分股完整代码查询主题
        input=z&type=2
	 * @throws Exception 
	 */
	@Test
	public void t216estztJpb() throws Exception {
		input="sh600221";
		type= "2";
		
		//kbspirit?input=sh600221&type=2
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 2.1.7主题投资相关----type=2
	 * 根据obj搜索主题功能增强, 支持逗号分隔多个obj 
        input=z&type=2
	 * @throws Exception 
	 */
	@Test
	public void t217estztJpb() throws Exception {
		input="sh600221,sh600600,000777";
		type= "2";
		
		//kbspirit?input=sh600221,sh600600,000777&type=2
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		/*JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);*/
		System.out.println(ret);
	}
	
	/**
	 * 2.2.1主题投资相关----type=2
	 * 查询一定数量主题
       count=2
	 * @throws Exception 
	 */
	@Test
	public void t221estztJpb() throws Exception {
		input="家";
		type= "2";
		count="2";
		
		//kbspirit?input=家&type=2&count=2
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&count=" + count+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 2.3.1主题投资相关----type=2
	 * 查看成分股
        kuozhan=1
	 * @throws Exception 
	 */
	@Test
	public void t231estztJpb() throws Exception {
		input="2";
		type= "2";
		kuozhan="1";
		count="5";
		
		//kbspirit?input=2&type=2&kuozhan=1&count=5
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+  "&kuozhan=" + kuozhan+ "&count=" + count+"&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 2.3.2主题投资相关----type=2
	 * 查看成分股，为2不显示，默认为2
        kuozhan=2
	 * @throws Exception 
	 */
	@Test
	public void t232estztJpb() throws Exception {
		input="2";
		type= "2";
		kuozhan="2";
		
		//kbspirit?input=2&type=2&kuozhan=2
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+  "&kuozhan=" + kuozhan+"&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 2.3.4主题投资相关----type=2
	 * 查看成分股（一次搜多个obj，但每个obj只列count个主题）
        kuozhan=1
	 * @throws Exception 
	 */
	@Test
	public void t233estztJpb() throws Exception {
		input="sh600221,sh600600,000777";
		type= "2";
		kuozhan="1";
		
		//kbspirit?input=2&type=2&kuozhan=1&count=5
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+  "&kuozhan=" + kuozhan+"&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		/*JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);*/
		System.out.println(ret);
	}
	
	/**
	 * 2.3.4主题投资相关----type=2
	 * 查看成分股（一次搜多个obj，但每个obj只列count个主题）
        kuozhan=1
	 * @throws Exception 
	 */
	@Test
	public void t234estztJpb() throws Exception {
		input="sh600221,sh600600,000777";
		type= "2";
		kuozhan="1";
		count="1";
		
		//kbspirit?input=2&type=2&kuozhan=1&count=5
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+  "&kuozhan=" + kuozhan+ "&count=" + count+"&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		/*JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);*/
		System.out.println(ret);
	}
	
	/**
	 * 2.4.1多音字
        input=z(zhong重工装备)
	 * @throws Exception 
	 */
	@Test
	public void t241estMultiPyJpb() throws Exception {
		input="zgzb";
		type= "2";
				
		//kbspirit?input=zgzb&type=2
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	/**
	 * 2.4.2多音字
        input=c(chong重工装备)
	 * @throws Exception 
	 */
	@Test
	public void t242estMultiPyJpb() throws Exception {
		input="cgzb";
		type= "2";
				
		//kbspirit?input=cgzb&type=2
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	
	/**
	 * 3.1.1龙虎榜相关----type=3
	 * 根据股票名称、营业部名称、股票首字母、营业部首字母、股票代码查询龙虎榜
	 * （输出结果的ShuJu数量默认为5条股票、5条营业部）
        input=z&type=3
	 * @throws Exception 
	 */
	@Test
	public void t311estztJpb() throws Exception {
		input="z";
		type= "3";
		
		//kbspirit?input=z&type=3
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 3.1.2龙虎榜相关----type=3
        input=中&type=3
	 * @throws Exception 
	 */
	@Test
	public void t312estztJpb() throws Exception {
		input="中";
		type= "3";
		
		//kbspirit?input=中&type=3
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 3.1.3龙虎榜相关----type=3
        input=光大证券&type=3
	 * @throws Exception 
	 */
	@Test
	public void t313estztJpb() throws Exception {
		input="光大证券";
		type= "3";
		
		//kbspirit?input=光大证券&type=3
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 3.1.4龙虎榜相关----type=3
        input=机构&type=3
	 * @throws Exception 
	 */
	@Test
	public void t314estztJpb() throws Exception {
		input="专用";
		type= "3";
		
		//kbspirit?input=机构&type=3
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 3.1.5龙虎榜相关----type=3
	 *  input=2&type=3
	 * @throws Exception 
	 */
	@Test
	public void t315estztJpb() throws Exception {
		input="2";
		type= "3";
		
		//kbspirit?input=2&type=3
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 3.1.6龙虎榜相关----type=3
        input=601788&type=3
	 * @throws Exception 
	 */
	@Test
	public void t316estztJpb() throws Exception {
		input="601788";
		type= "3";
		
		//kbspirit?input=601788&type=3
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 3.1.7龙虎榜相关----type=3
        input=6017&type=3   前缀匹配搜索
	 * @throws Exception 
	 */
	@Test
	public void t317estztJpb() throws Exception {
		input="6017";
		type= "3";
		
		//kbspirit?input=6017&type=3
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 3.1.8龙虎榜相关----type=3
        input=1788&type=3   后四位代码搜索
	 * @throws Exception 
	 */
	@Test
	public void t318estztJpb() throws Exception {
		input="1788";
		type= "3";
		
		//kbspirit?input=1788&type=3
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 3.2.1龙虎榜相关----type=3
	 * 查询一定数量主题
       count=2
	 * @throws Exception 
	 */
	@Test
	public void t321estztJpb() throws Exception {
		input="中";
		type= "3";
		count="10";
		
		//kbspirit?input=中&type=3&count=10
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&count=" + count + "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 3.2.2龙虎榜相关----type=3
	 * 查询一定数量主题
       count=10（符合数量小于count）
	 * @throws Exception 
	 */
	@Test
	public void t322estztJpb() throws Exception {
		input="沪";
		type= "3";
		count="10";
		
		//kbspirit?input=沪&type=3&count=10
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&count=" + count + "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	
	/**
	 * 3.3.1 龙虎榜相关----type=3
	 *  多音字
        input=at(at奥拓电子)
	 * @throws Exception 
	 */
	@Test
	public void t331estMultiPyJpb() throws Exception {
		input="at";
		type= "3";
				
		//kbspirit?input=at&type=3
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}
	/**
	 * 3.3.2龙虎榜相关----type=3
	 *  多音字
       input=yz(at奥拓电子)
	 * @throws Exception 
	 */
	@Test
	public void t332estMultiPyJpb() throws Exception {
		input="yz";
		type= "3";
				
		//kbspirit?input=yz&type=3
		String urlString = "http://" + ip + ":" +port + "/kbspirit?input=" + input + "&type=" + type+ "&token=" + token;  //每个测试方法需要修改
		String type="json";
		
		String ret =MyHttpUtil. getData(urlString,type);
		assertNotNull("错误：键盘宝返回null",ret);
		JSONArray jieguo = MykbspiritUtil.getkbspiritByGuanJianZi(ret, input);
		assertNotNull("错误：键盘宝为null",jieguo);
		System.out.println(jieguo);
	}	
	
}
