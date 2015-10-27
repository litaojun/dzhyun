package com.gw.dzhyun.app.topicinvest;

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

	public List<String> getListByUId() {
		List<String> list = new ArrayList<String>();
		try {
			String sql = "select SUBSTRING_INDEX(b.CO,'.',1) as Obj, b.HotTopicID, " +
 "a.TopicName, a.flag topicFlag, b.flag objFlag ,b.etime from tEQ9601  b " +
 "join tEQ9602 a on  b.HotTopicID = a.HotTopicID where b.etime > '%s' and b.HotTopicID =52  and b.flag = 0 " + 
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
	// 组装User对象
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
