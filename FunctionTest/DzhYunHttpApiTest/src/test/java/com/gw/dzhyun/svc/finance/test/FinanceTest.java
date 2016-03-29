/**
 * @classnmae FinanceTest.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.finance.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import com.gw.dzhyun.svc.finance.util.BasicFinanceData;
import org.junit.Test;

import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.dzhyun.svc.finance.mysql.FinanceBaseDao;
import com.gw.dzhyun.svc.finance.probuff.Dzhsvcfinance;

import com.gw.dzhyun.svc.finance.redis.FinanceBaseRedis;
import com.gw.dzhyun.svc.finance.util.FinanceUtil;
import com.gw.dzhyun.svc.finance.util.RongZiRongQuanShuJu;
import com.gw.dzhyun.svc.finance.util.DividData;

/**
 * @author Litaojun
 * @date   2016年1月8日
 *  需求设计文档：http://dms.gw.com.cn/pages/viewpage.action?pageId=135299453
 */
public class FinanceTest {

	FinanceBaseDao mysqlfbdao = new FinanceBaseDao();
	FinanceBaseRedis fbr = new FinanceBaseRedis();
	/**
	 * 
	 * @param @throws InvalidProtocolBufferException
	 * @Title testBasicFinanceDataSH
	 * @Description SH600816|6430
	 *              基本财务数据SH市场
	 *              BasicFinance	A9		基本财务数据
	 *              http://dms.gw.com.cn/pages/viewpage.action?pageId=133562388
	 * @return void
	 *
	 */
	@Test
	public void testBasicFinanceDataSH() throws InvalidProtocolBufferException, SQLException
	{
		System.out.println("testBasicFinanceDataSH----start");
		
		//从REDIS取出SH600816股票基本财务数据保存到HashMap<String,Dzhsvcfinance.BasicFinanceData>
		HashMap<String,Dzhsvcfinance.BasicFinanceData> ddd = fbr.getBasicFinanceDataFromRedis("A90", "SH", 6430);
		HashMap<String,BasicFinanceData> hmdata = FinanceUtil.tranBasicFinanceDataHashMap(ddd);
		//从mysql取出SH600816股票基本财务数据保存到ArrayList<BasicFinanceData>
		ArrayList<BasicFinanceData> fd = this.mysqlfbdao.getBasicFinanceDataListByObj("SH600816");
		boolean sign = FinanceUtil.compareMysqlRedisBasicFinanceData(hmdata,fd);
		assertTrue(sign);
	}
	
	/**
	 * 
	 * @param @throws InvalidProtocolBufferException
	 * @Title testBasicFinanceDataSZ
	 * @Description SZ000002|1
	 *               基本财务数据SZ市场
	 * @return void
	 * @throws SQLException 
	 *
	 */
	@Test
	public void testBasicFinanceDataSZ() throws InvalidProtocolBufferException, SQLException
	{
		System.out.println("testBasicFinanceDataSZ----start");
		HashMap<String,Dzhsvcfinance.BasicFinanceData> ddd = fbr.getBasicFinanceDataFromRedis("A90", "SZ", 1);
		HashMap<String,BasicFinanceData> hmdata = FinanceUtil.tranBasicFinanceDataHashMap(ddd);
		ArrayList<BasicFinanceData> fd = this.mysqlfbdao.getBasicFinanceDataListByObj("SZ000002");
		boolean sign = FinanceUtil.compareMysqlRedisBasicFinanceData(hmdata,fd);
		assertTrue(sign);
	}

/**
 * 
 * @param @throws InvalidProtocolBufferException
 * @Title testBasicFinanceDataSO
 * @Description SO400051|43
 *              基本财务数据SO市场
 * @return void
 * @throws SQLException 
 *
 */
	@Test
	public void testBasicFinanceDataSO() throws InvalidProtocolBufferException, SQLException
	{
		System.out.println("testBasicFinanceDataSO----start");
		HashMap<String,Dzhsvcfinance.BasicFinanceData> ddd = fbr.getBasicFinanceDataFromRedis("A90", "SO", 23);
		HashMap<String,BasicFinanceData> hmdata = FinanceUtil.tranBasicFinanceDataHashMap(ddd);
		ArrayList<BasicFinanceData> fd = this.mysqlfbdao.getBasicFinanceDataListByObj("SZ000002");
		boolean sign = FinanceUtil.compareMysqlRedisBasicFinanceData(hmdata,fd);
		assertTrue(sign);
	}
	
	
	/**
	 * 
	 * @param @throws InvalidProtocolBufferException
	 * @Title testDividDataSH
	 * @Description TODO
	 * @return void
	 *
	 */
	
	/**
	 * 
	 * @param @throws InvalidProtocolBufferException
	 * @Title testDividDataSH
	 * @Description SH600623|6256
	 *              除权数据SH市场
	 *              DividData	B1	1	5	除权数据
	 *              http://dms.gw.com.cn/pages/viewpage.action?pageId=133562388
	 * @return void
	 * @throws SQLException 
	 *
	 */
	@Test
	public void testDividDataSH() throws InvalidProtocolBufferException, SQLException
	{
		System.out.println("testDividDataSH----start");

		HashMap<String,Dzhsvcfinance.DividData> fdd = fbr.getDividDataFromRedis("B10", "SH", 6256);
		HashMap<String,DividData> hmdata = FinanceUtil.tranDividDataHashMap(fdd);
		ArrayList<DividData> ddd= this.mysqlfbdao.getDividDataListByObj("SH600623");
		boolean sign = FinanceUtil.compareMysqlRedisDividData2(hmdata,ddd);
		assertTrue(sign);
		
	}
	
	/**
	 * 
	 * @param @throws InvalidProtocolBufferException
	 * @Title testDividDataSZ
	 * @Description SZ000968|437
	 *              除权数据SZ市场
	 *              DividData	B1	1	5	除权数据
	 *              http://dms.gw.com.cn/pages/viewpage.action?pageId=133562388
	 * @return void
	 * @throws SQLException 
	 *
	 */
	@Test
	public void testDividDataSZ() throws InvalidProtocolBufferException, SQLException
	{
		System.out.println("testDividDataSZ----start");
		HashMap<String,Dzhsvcfinance.DividData> fdd = fbr.getDividDataFromRedis("B10", "SZ", 437);
		HashMap<String,DividData> hmdata = FinanceUtil.tranDividDataHashMap(fdd);
		ArrayList<DividData> ddd= this.mysqlfbdao.getDividDataListByObj("SZ000968");
		boolean sign = FinanceUtil.compareMysqlRedisDividData2(hmdata,ddd);
		assertTrue(sign);
	}
	
	/**
	 * 
	 * @param @throws InvalidProtocolBufferException
	 * @Title testDividDataSO
	 * @Description SO430003|61
	 *              除权数据SO市场
	 *              DividData	B1	1	5	除权数据
	 *              http://dms.gw.com.cn/pages/viewpage.action?pageId=133562388
	 * @return void
	 * @throws SQLException 
	 *
	 */
	@Test
	public void testDividDataSO() throws InvalidProtocolBufferException, SQLException
	{
		System.out.println("testDividDataSO----start");

		HashMap<String,Dzhsvcfinance.DividData> fdd = fbr.getDividDataFromRedis("B10", "SO", 61);
		HashMap<String,DividData> hmdata = FinanceUtil.tranDividDataHashMap(fdd);
		ArrayList<DividData> ddd= this.mysqlfbdao.getDividDataListByObj("SO430003");
		boolean sign = FinanceUtil.compareMysqlRedisDividData2(hmdata,ddd);
		assertTrue(sign);
	}

	/**
	 * 
	 * @param @throws InvalidProtocolBufferException
	 * @Title testShishiFinanceDataSH
	 * @Description SH600005|5743
	 *              实时财务数据SH市场
	 *              RealTimeFinance	C8	1	5	实时财务数据
	 *              http://dms.gw.com.cn/pages/viewpage.action?pageId=133562388
	 * @return void
	 * @throws SQLException 
	 *
	 */

	@Test
	public void testShishiFinanceDataSH() throws InvalidProtocolBufferException, SQLException
	{
		System.out.println("testShishiFinanceDataSH----start");

		Dzhsvcfinance.BasicFinanceData x = fbr.getSSFinanceDataByKeyFromRedis("C80", "SH", 5743);
		
		BasicFinanceData ssfdata = new BasicFinanceData();
		ssfdata.parseFromDzhsvcfinanceBasicFinanceData(x);
		BasicFinanceData sff = this.mysqlfbdao.getSsFinanceDataListByObj("SH600005");
		assertTrue(ssfdata!=null && ssfdata.equals(sff));
		//ssfdata.print();
	}
	
	
	/**
	 * 
	 * @param @throws InvalidProtocolBufferException
	 * @Title testShishiFinanceDataSH
	 * @Description SZ000004|2
	 *              实时财务数据SZ市场
	 *              RealTimeFinance	C8	1	5	实时财务数据
	 *              http://dms.gw.com.cn/pages/viewpage.action?pageId=133562388
	 * @return void
	 * @throws SQLException 
	 *
	 */
	@Test
	public void testShishiFinanceDataSZ() throws InvalidProtocolBufferException, SQLException
	{
		System.out.println("testShishiFinanceDataSZ----start");

		Dzhsvcfinance.BasicFinanceData x = fbr.getSSFinanceDataByKeyFromRedis("C80", "SZ", 2);
		//System.out.println("xx="+x.getAccufundps()+"xx="+x.getAdjassetps());
		BasicFinanceData ssfdata = new BasicFinanceData();
		ssfdata.parseFromDzhsvcfinanceBasicFinanceData(x);
		BasicFinanceData sff = this.mysqlfbdao.getSsFinanceDataListByObj("SZ000004");
		assertTrue(ssfdata!=null && ssfdata.equals(sff));
	}
	
	/**
	 * 
	 * @param @throws InvalidProtocolBufferException
	 * @Title testShishiFinanceDataSH
	 * @Description SO400057|49
	 *              实时财务数据SO市场
	 *              RealTimeFinance	C8	1	5	实时财务数据
	 *              http://dms.gw.com.cn/pages/viewpage.action?pageId=133562388
	 * @return void
	 * @throws SQLException 
	 *
	 */
	@Test
	public void testShishiFinanceDataSO() throws InvalidProtocolBufferException, SQLException
	{
		System.out.println("testShishiFinanceDataSO----start");

		Dzhsvcfinance.BasicFinanceData x = fbr.getSSFinanceDataByKeyFromRedis("C80", "SO", 49);
		BasicFinanceData ssfdata = new BasicFinanceData();
		ssfdata.parseFromDzhsvcfinanceBasicFinanceData(x);
		BasicFinanceData sff = this.mysqlfbdao.getSsFinanceDataListByObj("SO400057");
		assertTrue(ssfdata!=null && ssfdata.equals(sff));
	}
	
	/**
	 * 
	 * @param @throws InvalidProtocolBufferException
	 * @Title testRongZiRongQuanShuJuSH
	 * @Description SH600028|5762
	 *              融资融卷SH市场
	 *              RongZiRongQuan	E2	1	5	融资融券数据
	 * @return void
	 * @throws SQLException 
	 *
	 */
	@Test
	public void testRongZiRongQuanShuJuSH() throws InvalidProtocolBufferException, SQLException
	{
		System.out.println("testRongZiRongQuanShuJuSH----start");

		ArrayList<Dzhsvcfinance.RongZiRongQuanShuJu> ff  = fbr.getRongZiRongQuanShuJuFromRedis("E20", "SH", 5762);
		ArrayList<RongZiRongQuanShuJu> result = FinanceUtil.tranRongZiRongQuanShuJuHashMap(ff);
		ArrayList<RongZiRongQuanShuJu> daolist = this.mysqlfbdao.getRongZiRongQuanShuJuListByObj("SH600028");
		boolean sign = daolist.containsAll(result);
		//boolean sign = FinanceUtil.compareListMysqlRedisRongZiRongQuanShuJu(daolist, result);
		assertTrue(sign);
	}
	/**
	 * 
	 * @param @throws InvalidProtocolBufferException
	 * @Title testRongZiRongQuanShuJuSZ
	 * @Description SZ000157|68
	 *              融资融卷SZ市场
	 *              RongZiRongQuan	E2	1	5	融资融券数据
	 * @return void
	 * @throws SQLException 
	 *
	 */
	@Test
	public void testRongZiRongQuanShuJuSZ() throws InvalidProtocolBufferException, SQLException
	{
		System.out.println("testRongZiRongQuanShuJuSZ----start");

		ArrayList<Dzhsvcfinance.RongZiRongQuanShuJu> ff  = fbr.getRongZiRongQuanShuJuFromRedis("E20", "SZ", 68);
		ArrayList<RongZiRongQuanShuJu> result = FinanceUtil.tranRongZiRongQuanShuJuHashMap(ff);
		ArrayList<RongZiRongQuanShuJu> daolist = this.mysqlfbdao.getRongZiRongQuanShuJuListByObj("SZ000157");
		boolean sign = daolist.containsAll(result);
		//boolean sign = FinanceUtil.compareListMysqlRedisRongZiRongQuanShuJu(daolist, result);
		assertTrue(sign);
		System.out.println("testRongZiRongQuanShuJuSZ----end");
	}
	
	
	/**
	 * 
	 * @param @throws InvalidProtocolBufferException
	 * @Title testDividDataSH
	 * @Description SH600623|6256
	 *              除权数据SH市场
	 *              DividData	B1	1	5	除权数据
	 *              http://dms.gw.com.cn/pages/viewpage.action?pageId=133562388
	 *              新增股票SH600623的一个除权日期
	 * @return void
	 * @throws SQLException 
	 *
	 */
	@Test
	public void testAddDividDataSH() throws InvalidProtocolBufferException, SQLException
	{
		System.out.println("testAddDividDataSH----start");
        String cqdate = "20151112000000";
		HashMap<String,Dzhsvcfinance.DividData> redisfdd = fbr.getDividDataFromRedis("B10", "SH", 6256);
		HashMap<String,DividData> redishmdata = FinanceUtil.tranDividDataHashMap(redisfdd);
		ArrayList<DividData> mysqldivdList= this.mysqlfbdao.getDividDataListByObj("SH600623");
		DividData newdivid = FinanceUtil.findDivDataByChuQuanRiQi(mysqldivdList, cqdate);
		newdivid.print();
		boolean sign = FinanceUtil.compareAddMysqlDividDataInRedis(redishmdata, newdivid);
		assertTrue(sign);
		
	}
	@Test
	public void testAddDividDataSZ() throws InvalidProtocolBufferException, SQLException
	{
		System.out.println("testAddDividDataSZ----start");
        String cqdate = "20160126000000";
		HashMap<String,Dzhsvcfinance.DividData> redisfdd = fbr.getDividDataFromRedis("B10", "SZ", 721);
		HashMap<String,DividData> redishmdata = FinanceUtil.tranDividDataHashMap(redisfdd);
		ArrayList<DividData> mysqldivdList= this.mysqlfbdao.getDividDataListByObj("SZ002258");
		DividData newdivid = FinanceUtil.findDivDataByChuQuanRiQi(mysqldivdList, cqdate);
		boolean sign = FinanceUtil.compareAddMysqlDividDataInRedis(redishmdata, newdivid);
		assertTrue(sign);
		
	}
	
	/**
	 * @param @param args
	 * @Title main
	 * @Description TODO
	 * @return void
	 * @throws SQLException 
	 * @throws InvalidProtocolBufferException 
	 * 
	 */
	public static void main(String[] args) throws InvalidProtocolBufferException, SQLException {
		// TODO Auto-generated method stub
		FinanceTest ts = new FinanceTest();
		ts.testRongZiRongQuanShuJuSH();
	}

}
