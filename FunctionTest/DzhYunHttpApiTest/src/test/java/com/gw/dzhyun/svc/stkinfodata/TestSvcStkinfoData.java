package com.gw.dzhyun.svc.stkinfodata;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import com.alibaba.fastjson.JSONObject;
import com.gw.dzhyun.fjjj.BaseDao;
import com.gw.dzhyun.proxy.JedisOperator;
import com.gw.dzhyun.util.BytesHexStringTran;
import com.gw.dzhyun.util.ReadFileUtil;

public class TestSvcStkinfoData  extends BaseDao {
	JedisOperator jrdor = new JedisOperator();
	public ArrayList<byte[]> getRedisDataList(String key,int start ,int end)
	{
		return this.jrdor.getJredisList(key.getBytes(), start, end);
	}
	public JSONObject getRedisDataLen(String[] keyarr)
	{
		JSONObject rtjs = new JSONObject();
		for(String key:keyarr)
		{
			long tmp = this.jrdor.getJredisLLen("A30"+key);
			rtjs.put("A30"+key, tmp);
		}
		return rtjs;
	}
	public JSONObject getMysqlDataLen()
	{
		JSONObject jsonobj = new JSONObject();
		try {
			String sql = "select CONCAT('A30',m.sc) SCDM,count(1) ALLCOUNT from "+
						"(SELECT SUBSTRING(CO,1,2) sc,SUBSTRING_INDEX(CO,'.',1)  as Obj,etime "+
						"from tDD2002 " + 
						"where SUBSTRING(CO,1,2) in('IF','B$','SH','SO','SW','SZ','HI','ZI')) m " +
						"group by m.sc";

			ResultSet result = select(sql);
			while (null != result && result.next()) {
				jsonobj.put(result.getString("SCDM"), result.getLong("ALLCOUNT"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonobj;
	}
	
	public JSONObject getMysqlEtimeByMarket(String[] sc)
	{
		JSONObject jsonobj = new JSONObject();
		try {
			String sql = "SELECT SUBSTR(SUBSTRING_INDEX(CO,'.',1),1,2)  as Obj,FROM_UNIXTIME(UNIX_TIMESTAMP(max(etime)),'%Y-%m-%d %H:%i:%s') as updateTime"+
					" from tDD2002 " +  
					" where SUBSTRING(CO,1,2) in('IF','B$','SH','SO','SW','SZ','HI','ZI') " +
					" group by SUBSTR(SUBSTRING_INDEX(CO,'.',1),1,2)";
			
			ResultSet result = select(sql);
			while (null != result && result.next()) {
				jsonobj.put(result.getString("Obj"), result.getString("updateTime"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonobj;
	}
	
	public JSONObject getRedisEtimeByMarket(String[] sc)
	{
		JSONObject rtjs = new JSONObject();
		for(String key:sc)
		{
			String tmp = this.jrdor.getValueByKey("A82"+key+"A3");
			rtjs.put(key, tmp);
		}
		return rtjs;
	}

	public int compareJson(JSONObject redisjson,JSONObject mysqljson)
	{
		int ret = 1;
		HashSet<String> hst = (HashSet<String>) redisjson.keySet();
		Iterator<String> keys = hst.iterator();
		while(keys.hasNext())
		{
			
			String curkey = keys.next();
			if(redisjson.get(curkey) instanceof String)
			{
				if(!redisjson.get(curkey).equals(mysqljson.get(curkey)))
				{
					ret = 0;
					break;
				}
			}
			if(redisjson.get(curkey) != mysqljson.get(curkey))
			{
				ret = 0;
				break;
			}
		}
		return ret;
	}
	public int compareJson(String[] keyarr)
	{
		int ret = 1;
		JSONObject redisjs = this.getRedisDataLen(keyarr);
		JSONObject mysqljs = this.getMysqlDataLen();
		if(redisjs.keySet().size() != mysqljs.keySet().size())
			return 0;
		HashSet<String> hst = (HashSet<String>) redisjs.keySet();
		Iterator<String> keys = hst.iterator();
		while(keys.hasNext())
		{
			
			String curkey = keys.next();
			if(redisjs.get(curkey) != mysqljs.get(curkey))
			{
				ret = 0;
				break;
			}
		}
		
		return ret;
	}
	public void writeRedisByteTofile(String pathstr,String[] keyarr) throws IOException
	{
		JSONObject redisjs = this.getRedisDataLen(keyarr);
		//HashSet<String> hst = (HashSet<String>) redisjs.keySet();
		Iterator<String> keys = redisjs.keySet().iterator();
		while(keys.hasNext())
		{
			String key = keys.next();
			long tmp = Long.parseLong((String) redisjs.get(key));
			ArrayList<byte[]> curkeylist = this.getRedisDataList(key, 0, (int) tmp);
			ReadFileUtil.printByteArr(curkeylist);
			ReadFileUtil.writeByteToFile(pathstr + key+".dat" , curkeylist);

		}
		
	}
	
	/*
	 * 
	 */
	public ArrayList<byte[]> getArrListByReidsKey(String key)
	{
		long tmp = this.jrdor.getJredisLLen("A30"+key);
		//System.out.println("A30"+key+"="+tmp);
		ArrayList<byte[]> curkeylist = this.getRedisDataList("A30"+key, 0, (int) tmp);
		return curkeylist;
	}
	
	public ArrayList<String> byteArrTranStrArr(ArrayList<byte[]> btarr)
	{
		ArrayList<String> rets = new ArrayList<String>();
		for(byte[] byt : btarr)
		{
			String tmpstr = BytesHexStringTran.bytesToHexString(byt);
			rets.add(tmpstr);
		}
		return rets;
	}
	
	
	public ArrayList<byte[]> readFileDataToByteArr(String pathstr) throws IOException
	{
		return ReadFileUtil.readFileToByteArr(pathstr);
	}
	
	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated中文还是会报错吗 method stub
		TestSvcStkinfoData tsd = new TestSvcStkinfoData();
		JSONObject tsj = tsd.getMysqlDataLen();
		System.out.println(tsj.toJSONString());
		JSONObject resjs = tsd.getRedisDataLen(new String[] {"SH","SW","SZ","SO","ZI","HI","B$"});
		System.out.println(resjs.toJSONString());
//		tsd.writeRedisByteTofile("C:\\litaojun\\", new String[] {"SH","SW","SZ","SO","ZI","HI","B$"});
//		ArrayList<byte[]> alb = tsd.readFileDataToByteArr("C:\\litaojun\\A30SW.dat");
//		ReadFileUtil.printByteArr(alb);
		JSONObject a = tsd.getMysqlEtimeByMarket(new String[] {"SH","SW","SZ","SO","ZI","HI","B$"});
		System.out.println(a.toJSONString());
		JSONObject b = tsd.getRedisEtimeByMarket(new String[] {"SH","SW","SZ","SO","ZI","HI","B$"});
		System.out.println(b.toJSONString());

	}
	

}
