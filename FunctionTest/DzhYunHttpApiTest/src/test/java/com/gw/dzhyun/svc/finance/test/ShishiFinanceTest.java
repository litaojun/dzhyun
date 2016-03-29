/**
 * @classnmae ShishiFinanceTest.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.finance.test;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.dzhyun.svc.finance.mysql.FinanceBaseDao;
import com.gw.dzhyun.svc.finance.probuff.Dzhsvcfinance;
import com.gw.dzhyun.svc.finance.redis.FinanceBaseRedis;
import com.gw.dzhyun.svc.finance.util.BasicFinanceData;

/**
 * @author Litaojun
 * @date   2016年1月13日
 */
public class ShishiFinanceTest {
	FinanceBaseDao mysqlfbdao = new FinanceBaseDao();
	FinanceBaseRedis fbr = new FinanceBaseRedis();
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
	

}
