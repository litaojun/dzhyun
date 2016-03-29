/**
 * @classnmae SinglePropertyDao.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.app.singleProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.gw.dzhyun.fjjj.BaseDao;
import com.gw.dzhyun.fjjj.MigrationStruct;
import com.gw.dzhyun.svc.singleProperty.DanShangPinShuXingInfo;

/**
 * @author Litaojun
 * @date   2015年12月3日
 */
public class SinglePropertyDao extends BaseDao 
{

	
	/**
	 * 
	 * @param @return
	 * @Title getSdprtyHashMap
	 * @Description 获取单商品属性的HASHMAP数据
	 * @return HashMap<String,DanShangPinShuXingInfo>
	 *
	 */
	public HashMap<String,DanShangPinShuXingInfo> getSdprtyHashMapByMysql(String scdm) {
		HashMap<String,DanShangPinShuXingInfo> list = new HashMap<String,DanShangPinShuXingInfo>();
		try {
			String sql = "(select SUBSTRING_INDEX(a.CO,\".\",1) obj ,a.C1,a.C4,a.C5,a.C8, "+
                         "a.C9,a.C10,a.C14,a.C30,a.C37,b.C5 BC5 from dzh_dd.tDD2002 a " +
                         "LEFT JOIN dzh_stock.tEQ9112 b on a.CO=b.CO where SUBSTRING(a.CO, 1, 2)='"+scdm+"'" + 
                         "  order by a.ETIME,a.CV )" +
                        "UNION" + 
                         "(select SUBSTRING_INDEX(a.CO,\".\",1) obj ,a.C1,a.C4,a.C5,a.C8," + 
                       "a.C9,a.C10,a.C14,a.C30,a.C37,b.C5 BC5 from dzh_dd.tDD2002 a JOIN " +
                        "dzh_stock.tEQ9112 b on a.CO=b.CO where SUBSTRING(b.CO, 1, 2) = '"+scdm+"'" +
                        "order by b.ETIME,b.CV);";
			
			ResultSet result = select(sql);
			while (null != result && result.next()) {
				DanShangPinShuXingInfo category = assembleUser(result);
				list.put(category.getObj(),category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	// 组装User对象
		private DanShangPinShuXingInfo assembleUser(ResultSet result) {
			try {
				if (null != result) {
					DanShangPinShuXingInfo mst =  new DanShangPinShuXingInfo();
					mst.setObj(result.getString("obj"));
					mst.setShangshishijian(result.getString("C1"));
					mst.setZhongwenjiancheng(result.getString("C4"));
					mst.setYingwenquancheng(result.getString("C5"));
					mst.setXiaoshudianweishu(result.getInt("C8"));
					mst.setJiaoyishijianleixin(result.getInt("C9"));
					mst.setJiaoyibizhong(result.getString("C10"));
					mst.setTuishishijian(result.getString("C14"));
					mst.setShangshizhuangtai(result.getInt("C30")); 
					mst.setZhengquanleibie(result.getString("C37"));
					mst.setTingpai(result.getInt("BC5"));
					return mst;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			SinglePropertyDao a = new SinglePropertyDao();
			HashMap<String,DanShangPinShuXingInfo> hmp = a.getSdprtyHashMapByMysql("SH");
			Iterator<String> keys =  hmp.keySet().iterator();
			System.out.println(hmp.size());
			while(keys.hasNext())
			{
				String key =  keys.next();
				DanShangPinShuXingInfo tmp = hmp.get(key);
				tmp.printDanShangPinShuXingInfoToString();
				
			}
			
			

		}
}
