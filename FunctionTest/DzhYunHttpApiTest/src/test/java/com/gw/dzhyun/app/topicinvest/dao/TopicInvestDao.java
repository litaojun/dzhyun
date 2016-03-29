package com.gw.dzhyun.app.topicinvest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gw.dzhyun.fjjj.BaseDao;
import com.gw.dzhyun.fjjj.MigrationStruct;

public class TopicInvestDao  extends BaseDao {

	/**
	 * 
	 * @param @param id
	 * @param @return
	 * @Title getListByUId
	 * @Description 通过主题ID获取其下面所有成分股票代码列表
	 * @return List<String>
	 *
	 */
	public List<String> getObjListByTopicid(long id) {
		List<String> list = new ArrayList<String>();
		try {
			String sql = "select SUBSTRING_INDEX(b.CO,'.',1) as Obj, b.HotTopicID, " +
 "a.TopicName, a.flag topicFlag, b.flag objFlag ,b.etime from tEQ9601  b " +
 "join tEQ9602 a on  b.HotTopicID = a.HotTopicID where b.etime > '%s' and b.HotTopicID ="+id + "  and b.flag = 0 " + 
 "order by etime";
			
			ResultSet result = select(sql);
			while (null != result && result.next()) {
				String category = assembleUser(result);
				list.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

		private String assembleUser(ResultSet result) {
			try {
				if (null != result) {
					String curstr = result.getString("Obj");
					return curstr;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		public ArrayList<String> getAllTopicList() throws SQLException
		{
			ArrayList<String> list = new ArrayList<String>();
			String sql = "select b.HotTopicID from tEQ9602 b where b.Flag=0 and b.CategoryID=1 and EXISTS(select 1 from tEQ9601 a where a.HotTopicID = b.HotTopicID and a.flag = 0)";
			ResultSet result = select(sql);
			while (null != result && result.next()) {
				String category = assembleAllTopic(result);
				list.add(category);
			}
			return list;
			
		}
		private String assembleAllTopic(ResultSet result) {
			try {
				if (null != result) {
					String curstr = result.getString("HotTopicID");
					//System.out.println("TopicInvestDao->assembleAllTopic->HotTopicID="+curstr);
					return curstr;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		//SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
        String datestr = "2015-01-23";
        Date dates = format.parse(datestr);
		Long time=new Long(445555555);
		long t = (long)1434643200*1000;
		Date date1 = new Date(t); 
		String d = format.format(t);
		Date date=format.parse(d);
		System.out.println("Format To String(Date):"+dates);
		System.out.println("Format To Date:"+dates);
	}

}
