/**
 * @classnmae DividDataTest.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.finance.test;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.dzhyun.svc.finance.mysql.FinanceBaseDao;
import com.gw.dzhyun.svc.finance.probuff.Dzhsvcfinance;
import com.gw.dzhyun.svc.finance.redis.FinanceBaseRedis;
import com.gw.dzhyun.svc.finance.util.DividData;
import com.gw.dzhyun.svc.finance.util.FinanceUtil;

/**
 * @author Litaojun
 * @date   2016年1月13日
 */
public class DividDataTest {
	FinanceBaseDao mysqlfbdao = new FinanceBaseDao();
	FinanceBaseRedis fbr = new FinanceBaseRedis();
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
	

}
