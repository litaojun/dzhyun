/**
 * @classnmae RongZiRongQuanShuJuTest.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.finance.test;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.dzhyun.svc.finance.mysql.FinanceBaseDao;
import com.gw.dzhyun.svc.finance.probuff.Dzhsvcfinance;
import com.gw.dzhyun.svc.finance.redis.FinanceBaseRedis;
import com.gw.dzhyun.svc.finance.util.BasicFinanceData;
import com.gw.dzhyun.svc.finance.util.FinanceUtil;
import com.gw.dzhyun.svc.finance.util.RongZiRongQuanShuJu;

/**
 * @author Litaojun
 * @date   2016年1月13日
 */
public class RongZiRongQuanShuJuTest {
	FinanceBaseDao mysqlfbdao = new FinanceBaseDao();
	FinanceBaseRedis fbr = new FinanceBaseRedis();
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
		System.out.println("result.len="+result.size());
		ArrayList<RongZiRongQuanShuJu> daolist = this.mysqlfbdao.getRongZiRongQuanShuJuListByObj("SH600028");
		System.out.println("daolist.len="+daolist.size());
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
	
	
	@Test
	public void testAddRongZiRongQuanShuJuSZ() throws InvalidProtocolBufferException, SQLException
	{
		System.out.println("testAddRongZiRongQuanShuJuSZ----start");
        String adddate = "20151114000000";
		ArrayList<Dzhsvcfinance.RongZiRongQuanShuJu> ff  = fbr.getRongZiRongQuanShuJuFromRedis("E20", "SZ", 68);
		ArrayList<RongZiRongQuanShuJu> redisresult = FinanceUtil.tranRongZiRongQuanShuJuHashMap(ff);
		ArrayList<RongZiRongQuanShuJu> daolist = this.mysqlfbdao.getRongZiRongQuanShuJuListByObj("SZ000157");
		RongZiRongQuanShuJu newdivid = FinanceUtil.findRongZiRongQuanShuJuByJiaoYiRiQi(daolist, adddate);
		newdivid.print();
		boolean sign = redisresult.contains(newdivid);
		assertTrue(sign);

	}
}
