/**
 * @classnmae SinglePropertyTest.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.app.singleProperty;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyHttpUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.dzhyun.svc.singleProperty.BanKuaiChengFenGuInfo;
import com.gw.dzhyun.svc.singleProperty.DanShangPinShuXingInfo;
import com.gw.dzhyun.svc.singleProperty.SinglePropertyRedisData;
import com.gw.dzhyun.util.ReadFileUtil;


/**
 * @author Litaojun
 * @date   2015年12月2日
 */
public class SinglePropertyTest {
	private SinglePropertyRedisData srd = new SinglePropertyRedisData();
    private SinglePropertyDao spdao = new SinglePropertyDao();
	/**
	 * 
	 * @param 
	 * @Title testBlockPropSinglePry
	 * @Description 测试/block/prop接口接口，通过接口获取数据中包括2个值"股票\\\\市场分类\\\\沪深ST/*ST股"，"股票\\\\沪深ST"
	 * @return void
	 * @throws Exception 
	 * @throws SAXException 
	 *
	 */
	@Test
	public void testBlockPropSinglePry() throws SAXException, Exception
	{
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/block/prop?find=沪深ST";
		System.out.println("urlstr=" + urlstr + "\n");
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		JSONArray jsarr = data.getJSONObject("Data").getJSONArray("RepDataBlockPropOutput").getJSONObject(0).getJSONArray("name");
		boolean sign = jsarr.contains("股票\\\\市场分类\\\\沪深ST/*ST股");
		assertTrue(sign);
		sign = jsarr.contains("股票\\\\沪深ST");
		assertTrue(sign);
		assertSame(jsarr.size(),2);
	}
	
	/**
	 * 
	 * @param @throws SAXException
	 * @param @throws Exception
	 * @Title testBlockObjPropSinglePry
	 * @Description 测试/block/obj接口，比较接口获取的成分股，redis中成分股，mysql数据库中成分股的一致性。
	 * @return void
	 *
	 */
	@Test
	public void testBlockObjPropSinglePry() throws SAXException, Exception
	{
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/block/obj?gql=block=股票\\\\沪深ST";
		System.out.println("urlstr=" + urlstr + "\n");
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		JSONArray jsarr = data.getJSONObject("Data").getJSONArray("RepDataBlockObjOutput").getJSONObject(0).getJSONArray("obj");
		ArrayList<String> jslist = SinglePropertyUtilTool.jsnArrToArrList(jsarr);
		BanKuaiChengFenGuInfo tmpbkcfg = this.srd.getBanKuaiChengFenGuInfo();
		ArrayList<String> redisList = tmpbkcfg.getChengFenGuObjList();
		boolean sign = SinglePropertyUtilTool.listStrCompare(jslist, redisList);
		assertTrue(sign);
	}
	
	/**
	 * 
	 * @param @throws SAXException
	 * @param @throws Exception
	 * @Title testBlockObjPropSinglePryAdd
	 * @Description 测试增量同步，在mysql数据库新增一个成分股。比如在MYSQL数据库新增一个成分股‘SH888882’，验证接口数据中存在该成分股
	 *              测试/block/obj接口
	 *              备注：测试该用例，需要在mysql中新增一条记录SH644793
	 * @return void
	 *
	 */
	@Test
	public void testBlockObjPropSinglePryAddObj() throws SAXException, Exception
	{
		String cfgObj = "SH644793";
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/block/obj?gql=block=股票\\\\沪深ST";
		System.out.println("urlstr=" + urlstr + "\n");
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		JSONArray jsarr = data.getJSONObject("Data").getJSONArray("RepDataBlockObjOutput").getJSONObject(0).getJSONArray("obj");
		ArrayList<String> jslist = SinglePropertyUtilTool.jsnArrToArrList(jsarr);
		assertTrue(jslist.contains(cfgObj));
		BanKuaiChengFenGuInfo tmpbkcfg = this.srd.getBanKuaiChengFenGuInfo();
		ArrayList<String> redisList = tmpbkcfg.getChengFenGuObjList();
		assertTrue(redisList.contains(cfgObj));
		boolean sign = SinglePropertyUtilTool.listStrCompare(jslist, redisList);
		assertTrue(sign);
	}
	
	/**
	 * 
	 * @param @throws SAXException
	 * @param @throws Exception
	 * @Title testBlockObjPropSinglePryDelObj
	 * @Description 测试增量同步，在mysql数据库删除一个成分股。比如在MYSQL数据库删除一个成分股‘SH888882’，验证接口数据中存在该成分股
	 *              测试/block/obj接口
	 *              备注：测试该用例，需要在mysql中删除一条已存在记录SH644793
	 * @return void
	 *
	 */
	@Test
	public void testBlockObjPropSinglePryDelObj() throws SAXException, Exception
	{
		String cfgObj = "SH644793";
		String urlstr = "http://"
				+ MyConfigUtil.getConfig("ip")
				+ ":"
				+ MyConfigUtil.getConfig("port")
				+ "/block/obj?gql=block=股票\\\\沪深ST";
		System.out.println("urlstr=" + urlstr + "\n");
		String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
		System.out.println("retstr=" + retstr + "\n");
		JSONObject data = JSON.parseObject(retstr);
		JSONArray jsarr = data.getJSONObject("Data").getJSONArray("RepDataBlockObjOutput").getJSONObject(0).getJSONArray("obj");
		ArrayList<String> jslist = SinglePropertyUtilTool.jsnArrToArrList(jsarr);
		assertFalse(jslist.contains(cfgObj));
		BanKuaiChengFenGuInfo tmpbkcfg = this.srd.getBanKuaiChengFenGuInfo();
		ArrayList<String> redisList = tmpbkcfg.getChengFenGuObjList();
		assertFalse(redisList.contains(cfgObj));
		boolean sign = SinglePropertyUtilTool.listStrCompare(jslist, redisList);
		assertTrue(sign);
	}
	
	/**
	 * 
	 * @param 
	 * @Title testDanShangPinShuXing
	 * @Description 单商品采集，比较mysql与redis中数据的一致性；
	 *              该用例比对SH市场数据
	 * @return void
	 * @throws InvalidProtocolBufferException 
	 *
	 */
	@Test
	public void testDanShangPinShuXingSH() throws InvalidProtocolBufferException
	{
		HashMap<String,DanShangPinShuXingInfo> mysqlhmp = spdao.getSdprtyHashMapByMysql("SH");
		HashMap<String,DanShangPinShuXingInfo> redishmp = this.srd.getBanKuaiShuXing("SH");
		boolean sign = SinglePropertyUtilTool.hashmapDanShangPinShuXingInfoComare(mysqlhmp, redishmp);
		assertTrue(sign);
	}
	/**
	 * 
	 * @param 
	 * @Title testDanShangPinShuXing
	 * @Description 单商品采集，比较mysql与redis中数据的一致性；
	 *              该用例比对SZ市场数据
	 * @return void
	 * @throws InvalidProtocolBufferException 
	 *
	 */
	@Test
	public void testDanShangPinShuXingSZ() throws InvalidProtocolBufferException
	{
		HashMap<String,DanShangPinShuXingInfo> mysqlhmp = spdao.getSdprtyHashMapByMysql("SZ");
		HashMap<String,DanShangPinShuXingInfo> redishmp = this.srd.getBanKuaiShuXing("SZ");
		boolean sign = SinglePropertyUtilTool.hashmapDanShangPinShuXingInfoComare(mysqlhmp, redishmp);
		assertTrue(sign);
	}
	/**
	 * 
	 * @param 
	 * @Title testDanShangPinShuXing
	 * @Description 单商品采集，比较mysql与redis中数据的一致性；
	 *              该用例比对ZI市场数据
	 * @return void
	 * @throws InvalidProtocolBufferException 
	 *
	 */
	@Test
	public void testDanShangPinShuXingZI() throws InvalidProtocolBufferException
	{
		HashMap<String,DanShangPinShuXingInfo> mysqlhmp = spdao.getSdprtyHashMapByMysql("ZI");
		HashMap<String,DanShangPinShuXingInfo> redishmp = this.srd.getBanKuaiShuXing("ZI");
		boolean sign = SinglePropertyUtilTool.hashmapDanShangPinShuXingInfoComare(mysqlhmp, redishmp);
		assertTrue(sign);
	}
	/**
	 * 
	 * @param 
	 * @Title testDanShangPinShuXing
	 * @Description 单商品采集，比较mysql与redis中数据的一致性；
	 *              该用例比对SW市场数据
	 * @return void
	 * @throws InvalidProtocolBufferException 
	 *
	 */
	@Test
	public void testDanShangPinShuXingSW() throws InvalidProtocolBufferException
	{
		HashMap<String,DanShangPinShuXingInfo> mysqlhmp = spdao.getSdprtyHashMapByMysql("SW");
		HashMap<String,DanShangPinShuXingInfo> redishmp = this.srd.getBanKuaiShuXing("SW");
		boolean sign = SinglePropertyUtilTool.hashmapDanShangPinShuXingInfoComare(mysqlhmp, redishmp);
		assertTrue(sign);
	}
	
	/**
	 * 
	 * @param 
	 * @Title testDanShangPinShuXing
	 * @Description 单商品采集，比较mysql与redis中数据的一致性；
	 *              该用例比对HI市场数据
	 * @return void
	 * @throws InvalidProtocolBufferException 
	 *
	 */
	@Test
	public void testDanShangPinShuXingHI() throws InvalidProtocolBufferException
	{
		HashMap<String,DanShangPinShuXingInfo> mysqlhmp = spdao.getSdprtyHashMapByMysql("HI");
		HashMap<String,DanShangPinShuXingInfo> redishmp = this.srd.getBanKuaiShuXing("HI");
		boolean sign = SinglePropertyUtilTool.hashmapDanShangPinShuXingInfoComare(mysqlhmp, redishmp);
		assertTrue(sign);
	}
	
	/**
	 * 
	 * @param 
	 * @Title testDanShangPinShuXing
	 * @Description 单商品采集，比较mysql与redis中数据的一致性；
	 *              该用例比对SO市场数据
	 * @return void
	 * @throws InvalidProtocolBufferException 
	 *
	 */
	@Test
	public void testDanShangPinShuXingSO() throws InvalidProtocolBufferException
	{
		HashMap<String,DanShangPinShuXingInfo> mysqlhmp = spdao.getSdprtyHashMapByMysql("SO");
		HashMap<String,DanShangPinShuXingInfo> redishmp = this.srd.getBanKuaiShuXing("SO");
		boolean sign = SinglePropertyUtilTool.hashmapDanShangPinShuXingInfoComare(mysqlhmp, redishmp);
		assertTrue(sign);
	}
	
	/**
	 * 
	 * @param 
	 * @Title testDanShangPinShuXing
	 * @Description 单商品采集，比较mysql与redis中数据的一致性；
	 *              该用例比对IF市场数据
	 * @return void
	 * @throws InvalidProtocolBufferException 
	 *
	 */
	@Test
	public void testDanShangPinShuXingIF() throws InvalidProtocolBufferException
	{
		HashMap<String,DanShangPinShuXingInfo> mysqlhmp = spdao.getSdprtyHashMapByMysql("IF");
		HashMap<String,DanShangPinShuXingInfo> redishmp = this.srd.getBanKuaiShuXing("IF");
		boolean sign = SinglePropertyUtilTool.hashmapDanShangPinShuXingInfoComare(mysqlhmp, redishmp);
		assertTrue(sign);
	}
	
	/**
	 * 
	 * @param 
	 * @Title testDanShangPinShuXing
	 * @Description 单商品采集，比较mysql与redis中数据的一致性；
	 *              该用例比对B$市场数据
	 * @return void
	 * @throws InvalidProtocolBufferException 
	 *
	 */
	@Test
	public void testDanShangPinShuXingB$() throws InvalidProtocolBufferException
	{
		HashMap<String,DanShangPinShuXingInfo> mysqlhmp = spdao.getSdprtyHashMapByMysql("B$");
		HashMap<String,DanShangPinShuXingInfo> redishmp = this.srd.getBanKuaiShuXing("B$");
		boolean sign = SinglePropertyUtilTool.hashmapDanShangPinShuXingInfoComare(mysqlhmp, redishmp);
		assertTrue(sign);
	}
	
	/**
	 * 
	 * @param @throws InvalidProtocolBufferException
	 * @Title testDanShangPinShuXingSH
	 * @Description 单商品采集，在mysql中动态新增一条数据后，验证该数据能采集的redis中
	 *              在mysql数据库中新增SH市场的4条记录，分别覆盖4种场景，具体见测试用例
	 * @return void
	 *
	 */
	@Test
	public void testDanShangPinShuXingAddSH() throws InvalidProtocolBufferException
	{
		String objarr[] =new String[] { "SH699923","SH699924","SH699925","SH699926"};
		String tobj ="SH699920";
		HashMap<String,DanShangPinShuXingInfo> mysqlhmp = spdao.getSdprtyHashMapByMysql("SH");
		HashMap<String,DanShangPinShuXingInfo> redishmp = this.srd.getBanKuaiShuXing("SH");
		boolean sign = SinglePropertyUtilTool.hashmapDanShangPinShuXingInfoComare(mysqlhmp, redishmp);
		assertTrue(sign);
		for(String curobj:objarr)
		{
			System.out.println("curobj="+curobj);
			assertTrue(redishmp.containsKey(curobj));
		}
	}
	
	/**
	 * 
	 * @param @throws InvalidProtocolBufferException
	 * @Title testDanShangPinShuXingSH
	 * @Description 单商品采集，在mysql中动态新增一条数据后，验证该数据能采集的redis中
	 *              在mysql数据库中新增SZ市场的4条记录，分别覆盖4种场景，具体见测试用例
	 * @return void
	 *
	 */
	@Test
	public void testDanShangPinShuXingAddSZ() throws InvalidProtocolBufferException
	{
		String objarr[] =new String[] { "SZ699923","SZ699924","SZ699925","SZ699926"};
		String tobj ="SH699920";
		HashMap<String,DanShangPinShuXingInfo> mysqlhmp = spdao.getSdprtyHashMapByMysql("SZ");
		HashMap<String,DanShangPinShuXingInfo> redishmp = this.srd.getBanKuaiShuXing("SZ");
		boolean sign = SinglePropertyUtilTool.hashmapDanShangPinShuXingInfoComare(mysqlhmp, redishmp);
		//assertTrue(sign);
		for(String curobj:objarr)
		{
			System.out.println("curobj="+curobj);
			assertTrue(redishmp.containsKey(curobj));
		}
	}

	/**
	 * @param @param args
	 * @Title main
	 * @Description TODO
	 * @return void
	 * @throws Exception 
	 * @throws SAXException 
	 * 
	 */
	public static void main(String[] args) throws SAXException, Exception {
		// TODO Auto-generated method stub
		ArrayList<String> a = ReadFileUtil.readFileToArrayListByFilePath("E:\\litj\\共享\\我的工作\\oam发布\\oam发布.txt");
		for(String url:a)
		{
			String urlstr = url;
			System.out.println("urlstr=" + urlstr + "\n");
			String retstr = MyHttpUtil.getQuoteDyna(urlstr, "json");
			System.out.println("retstr=" + retstr + "\n");
			JSONObject data = JSON.parseObject(retstr);
		}
	}

}
