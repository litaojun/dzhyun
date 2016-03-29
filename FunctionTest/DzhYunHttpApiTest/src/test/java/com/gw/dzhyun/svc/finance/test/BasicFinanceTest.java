/**
 * @classnmae BasicFinanceTest.java
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
import com.gw.dzhyun.svc.finance.util.BasicFinanceData;
import com.gw.dzhyun.svc.finance.util.DividData;
import com.gw.dzhyun.svc.finance.util.FinanceUtil;

/**
 * @author Litaojun
 * @date   2016年1月13日
 */
public class BasicFinanceTest
{
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
		assertTrue(fd.size()>=1);
		boolean sign = FinanceUtil.compareMysqlRedisBasicFinanceData(hmdata,fd);
		assertTrue(sign);
	}
	
	/**
	 * 
	 * @param @throws InvalidProtocolBufferException
	 * @Title testBasicFinanceDataSZ
	 * @Description SZ000012|10
	 *               基本财务数据SZ市场
	 * @return void
	 * @throws SQLException 
	 *
	 */
	@Test
	public void testBasicFinanceDataSZ() throws InvalidProtocolBufferException, SQLException
	{
		System.out.println("testBasicFinanceDataSZ----start");
		HashMap<String,Dzhsvcfinance.BasicFinanceData> ddd = fbr.getBasicFinanceDataFromRedis("A90", "SZ", 22);
		HashMap<String,BasicFinanceData> redishmdata = FinanceUtil.tranBasicFinanceDataHashMap(ddd);
		ArrayList<BasicFinanceData> mysqlfd = this.mysqlfbdao.getBasicFinanceDataListByObj("SZ000026");
		assertTrue(mysqlfd.size()>=1);
		boolean sign = FinanceUtil.compareMysqlRedisBasicFinanceData(redishmdata,mysqlfd);
		assertTrue(sign);
	}

/**
 * 
 * @param @throws InvalidProtocolBufferException
 * @Title testBasicFinanceDataSO
 * @Description SO400028|22
 *              基本财务数据SO市场
 * @return void
 * @throws SQLException 
 *
 */
	@Test
	public void testBasicFinanceDataSO() throws InvalidProtocolBufferException, SQLException
	{
		System.out.println("testBasicFinanceDataSO----start");
		HashMap<String,Dzhsvcfinance.BasicFinanceData> ddd = fbr.getBasicFinanceDataFromRedis("A90", "SO", 22);
		HashMap<String,BasicFinanceData> hmdata = FinanceUtil.tranBasicFinanceDataHashMap(ddd);
		ArrayList<BasicFinanceData> fd = this.mysqlfbdao.getBasicFinanceDataListByObj("SO400028");
		assertTrue(fd.size()>=1);
		boolean sign = FinanceUtil.compareMysqlRedisBasicFinanceData(hmdata,fd);
		assertTrue(sign);
	}
	
	
	/**
	 * 
	 * @param @throws InvalidProtocolBufferException
	 * @Title testBasicFinanceDataSZ
	 * @Description SZ000012|10
	 *               基本财务数据SZ市场
	 * @return void
	 * @throws SQLException 
	 *
	 */
	@Test
	public void testAddBasicFinanceDataSZ() throws InvalidProtocolBufferException, SQLException
	{
		String adddate = "20151231000000";
		System.out.println("testBasicFinanceDataSZ----start");
		HashMap<String,Dzhsvcfinance.BasicFinanceData> ddd = fbr.getBasicFinanceDataFromRedis("A90", "SZ", 10);
		HashMap<String,BasicFinanceData> redishmdata = FinanceUtil.tranBasicFinanceDataHashMap(ddd);
		ArrayList<BasicFinanceData> mysqlfd = this.mysqlfbdao.getBasicFinanceDataListByObj("SZ000012");
		BasicFinanceData newdivid = FinanceUtil.findBasicFinanceDataByRepdate(mysqlfd, adddate);
		newdivid.print();
		boolean sign = FinanceUtil.compareAddMysqlBasicFinanceDataInRedis(redishmdata, newdivid);
		assertTrue(sign);

	}

}
