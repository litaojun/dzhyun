/**
 * @classnmae FinanceUtil.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.finance.util;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.gw.dzhyun.svc.finance.probuff.Dzhsvcfinance;
//import com.gw.dzhyun.svc.finance.probuff.Dzhsvcfinance.BasicFinanceData;
//import com.gw.dzhyun.svc.finance.probuff.Dzhsvcfinance.DividData;

/**
 * @author Litaojun
 * @date   2016年1月8日
 */
public class FinanceUtil 
{

	/**
	 * 
	 * @param @param ddd
	 * @param @return
	 * @Title tranBasicFinanceDataHashMap
	 * @Description 将基本财务数据的PROBUFF结构类转换为自定义类数据
	 * @return HashMap<String,BasicFinanceData>
	 *
	 */
	public static HashMap<String,BasicFinanceData> tranBasicFinanceDataHashMap(HashMap<String,Dzhsvcfinance.BasicFinanceData> ddd)
	{
		HashMap<String,BasicFinanceData> retdata = new HashMap<String,BasicFinanceData>();
		Iterator<String> keys = ddd.keySet().iterator();
		while(keys.hasNext())
		{
			//System.out.println("222");
			String dat = keys.next();
			//System.out.println("key="+dat);
			Dzhsvcfinance.BasicFinanceData dd = ddd.get(dat);
			BasicFinanceData bfd = new BasicFinanceData();
			bfd.parseFromDzhsvcfinanceBasicFinanceData(dd);
			//bfd.print();
			retdata.put(dat, bfd);
		}
		return retdata;
	}
	
	/**
	 * 
	 * @param @param hmdata
	 * @param @param fd
	 * @param @return
	 * @Title compareMysqlRedisBasicFinanceData
	 * @Description 财务数据MYSQL和REDIS中比对
	 * @return boolean
	 *
	 */
	public static boolean compareMysqlRedisBasicFinanceData(HashMap<String,BasicFinanceData> hmdata ,ArrayList<BasicFinanceData> fd)
	{
		boolean sign =  true;

		for(BasicFinanceData bfd:fd)
		{
			//System.out.println("bfd.getRepdate()="+bfd.getRepdate());
			BasicFinanceData tmp = hmdata.get(bfd.getRepdate());
			sign = bfd.equals(tmp);
			if(!sign)
					break;
		}
		return sign;
	}
	public static boolean compareMysqlRedisDividData(HashMap<String,DividData> hmdata ,ArrayList<DividData> fd)
	{
		boolean sign =  true;
        Set<String> hkey = hmdata.keySet();
        for(String sg:hkey)
        	System.out.println(sg);
		for(DividData bfd:fd)
		{
			//System.out.println("bfd.getRepdate()="+bfd.getChuquanriqi());
			DividData tmp = hmdata.get(bfd.getChuquanriqi());
			System.out.println("litaojun-------"+bfd.getChuquanriqi());
			System.out.println("litaojun-------------------"+tmp.getChuquanriqi());
			sign = bfd.equals(tmp);
			if(!sign)
				break;
		}
		return sign;
	}
	public static boolean compareMysqlRedisDividData2(HashMap<String,DividData> hmdata ,ArrayList<DividData> fd)
	{
		boolean sign =  true;
        Set<String> hkey = hmdata.keySet();
        ArrayList<DividData> als = new ArrayList<DividData>();
        for(String sg:hkey)
        {
        	als.add(hmdata.get(sg));
        	//System.out.println(sg);
        }
        System.out.println(als.size()+"-------"+fd.size());
        sign = als.containsAll(fd);
		
		return sign;
	}
	public static boolean compareListMysqlRedisRongZiRongQuanShuJu(ArrayList<RongZiRongQuanShuJu> mysql,ArrayList<RongZiRongQuanShuJu> redis)
	{
		boolean sign = true;
		for(RongZiRongQuanShuJu ss:mysql)
			ss.print();
		//System.out.println("----------------------------------");
		for(RongZiRongQuanShuJu rs:redis)
		{

			sign = mysql.contains(rs);
			//System.out.println("----------------------------------222" + sign);
			if(!sign)
			{
				rs.print();
				break;
			}
		}
		return sign;
	}
	public static HashMap<String,DividData> tranDividDataHashMap(HashMap<String,Dzhsvcfinance.DividData> ddd)
	{
		HashMap<String,DividData> retdata = new HashMap<String,DividData>();
		Iterator<String> keys = ddd.keySet().iterator();
		while(keys.hasNext())
		{
			//System.out.println("222");
			String dat = keys.next();
			//System.out.println("key="+dat);
			Dzhsvcfinance.DividData dd = ddd.get(dat);
			DividData bfd = new DividData();
			bfd.parseFromDzhsvcfinanceDividData(dd);
			//System.out.println(bfd.getPaiXiShuShuiHou());
			//bfd.print();
			retdata.put(dat, bfd);
		}
		return retdata;
	}
	public static ArrayList<RongZiRongQuanShuJu> tranRongZiRongQuanShuJuHashMap(ArrayList<Dzhsvcfinance.RongZiRongQuanShuJu> ddd)
	{
		ArrayList<RongZiRongQuanShuJu> retdata = new ArrayList<RongZiRongQuanShuJu>();
		for(Dzhsvcfinance.RongZiRongQuanShuJu rzsj:ddd)
		{

			RongZiRongQuanShuJu bfd = new RongZiRongQuanShuJu();
			bfd.parseFromDzhsvcfinanceRongZiRongQuanShuJu(rzsj);
			//bfd.print();
            retdata.add(bfd);
		}
		return retdata;
	}
	public static DividData findDivDataByChuQuanRiQi(ArrayList<DividData> alst,String cqrq)
	{
		DividData a = null;
		for(DividData tmp:alst)
		{
			System.out.println("ssss--------"+tmp.getChuquanriqi());
			if(tmp.getChuquanriqi().equals(cqrq))
			{
				tmp.print();
				a = tmp;
			}
		}
		return a;
	}
	public static BasicFinanceData findBasicFinanceDataByRepdate(ArrayList<BasicFinanceData> alst,String cqrq)
	{
		BasicFinanceData a = null;
		for(BasicFinanceData tmp:alst)
		{
			System.out.println("ssss--------"+tmp.getRepdate());
			if(tmp.getRepdate().equals(cqrq))
			{
				tmp.print();
				a = tmp;
			}
		}
		return a;
	}
	public static RongZiRongQuanShuJu findRongZiRongQuanShuJuByJiaoYiRiQi(ArrayList<RongZiRongQuanShuJu> alst,String cqrq)
	{
		RongZiRongQuanShuJu a = null;
		for(RongZiRongQuanShuJu tmp:alst)
		{
			//System.out.println("ssss--------"+tmp.getJiaoYiRiQi());
			if(tmp.getJiaoYiRiQi().equals(cqrq))
			{
				tmp.print();
				a = tmp;
			}
		}
		return a;
	}
	
	public static boolean compareAddMysqlDividDataInRedis(HashMap<String,DividData> redishmdata ,DividData mysqlfd)
	{
		boolean sign =  true;
        Set<String> hkey = redishmdata.keySet();
        ArrayList<DividData> als = new ArrayList<DividData>();
        for(String sg:hkey)
        {
        	als.add(redishmdata.get(sg));
        	//System.out.println(sg);
        }
        sign = als.contains(mysqlfd);
		
		return sign;
	}
	public static boolean compareAddMysqlBasicFinanceDataInRedis(HashMap<String,BasicFinanceData> redishmdata ,BasicFinanceData mysqlfd)
	{
		boolean sign =  true;
        Set<String> hkey = redishmdata.keySet();
        ArrayList<BasicFinanceData> als = new ArrayList<BasicFinanceData>();
        for(String sg:hkey)
        {
        	als.add(redishmdata.get(sg));
        	//System.out.println(sg);
        }
        sign = als.contains(mysqlfd);

		return sign;
	}
	
	/**
	 * @param @param args
	 * @Title main
	 * @Description TODO
	 * @return void
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
