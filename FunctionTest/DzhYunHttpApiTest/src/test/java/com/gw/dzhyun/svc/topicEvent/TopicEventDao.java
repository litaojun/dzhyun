/**
 * @classnmae TopicEventDao.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.topicEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gw.dzhyun.fjjj.BaseDao;

/**
 * @author Litaojun
 * @date   2016年1月6日
 */
public class TopicEventDao extends BaseDao 
{
	public StockFutureEvent getAllEventListByObj(String obj) throws SQLException
	{
		StockFutureEvent sete = new StockFutureEvent();
		sete.setObj(obj);
		ArrayList<FutureEvent> list = new ArrayList<FutureEvent>();
		String sql = "select a.EventId ,Title,ExpectTime from tEQ9606 a join " + 
					 " tEQ9604 b on b.flag<>1 and a.eventId = b.eventId left join tEQ9605 c on  b.flag<>1 and a.eventId = c.eventId " +
					 " where a.flag <> 1 and ExpectTime > '' " +
					 " and Exists (select * from  tEQ9606 where flag <> 1 and etime > '' or " +
					 " (etime = '' and cv > '') limit 1) and SUBSTRING_INDEX(a.CO,'.',1) = '" + obj +"'" ;
		System.out.println(sql);
		ResultSet result = select(sql);
		while (null != result && result.next()) {
			FutureEvent category = assembleAllEvent(result);
			list.add(category);
		}
		sete.setFeList(list);
		return sete;
		
	}
	private FutureEvent assembleAllEvent(ResultSet result) {
		try {
			if (null != result) {
				long eventid = result.getLong("EventId");
				String title = result.getString("Title");
				String expecttime = result.getString("ExpectTime");
				FutureEvent a = new FutureEvent();
				a.setTitle(title);
				a.setExpectTime(expecttime);
				a.setEventid(eventid);
				ArrayList<EventObjData> alev = this.getAllObjByEventID(eventid);
				ArrayList<EventTopicData> aled = this.getAllTopicidByEventID(eventid);
				a.setEetlist(aled);
				a.setEodlist(alev);
				//System.out.println("TopicInvestDao->assembleAllTopic->HotTopicID="+curstr);
				return a;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	public ArrayList<EventObjData> getAllObjByEventID(long eventid) throws SQLException
	{
		ArrayList<EventObjData> list = new ArrayList<EventObjData>();
		String sql = "select SUBSTRING_INDEX(a.CO,'.',1) as Obj,StockName from tEQ9606 a join " + 
					 "tEQ9604 b on b.flag<>1 and a.eventId = b.eventId left join tEQ9605 c on " + 
					" b.flag<>1 and a.eventId = c.eventId where a.flag <> 1 and ExpectTime > '' " + 
					" and Exists (select * from  tEQ9606 where flag <> 1 and etime > '' or " +
					" (etime = '' and cv > '') limit 1) and a.EventID=' " + eventid +"'" ;
		ResultSet result = select(sql);
		while (null != result && result.next()) {
			EventObjData category = assembleAllObj(result);
			list.add(category);
		}
		return list;
		
	}
	private EventObjData assembleAllObj(ResultSet result) {
		try {
			if (null != result) {
				
				String obj = result.getString("Obj");
				String stockName = result.getString("StockName");
				//System.out.println("TopicInvestDao->assembleAllTopic->HotTopicID="+curstr);
				EventObjData data = new EventObjData();
				data.setName(stockName);
				data.setObj(obj);
				return data;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        return null;
	}
	public ArrayList<EventTopicData> getAllTopicidByEventID(long eventid) throws SQLException
	{
		ArrayList<EventTopicData> list = new ArrayList<EventTopicData>();
		String sql = "select DISTINCT HotTopicID,TopicName from tEQ9606 a join " + 
					 "tEQ9604 b on b.flag<>1 and a.eventId = b.eventId left join tEQ9605 c on " + 
					" b.flag<>1 and a.eventId = c.eventId where a.flag <> 1 and ExpectTime > '' " + 
					" and Exists (select * from  tEQ9606 where flag <> 1 and etime > '' or " +
					" (etime = '' and cv > '') limit 1) and a.EventID='" + eventid +"'" ;
		ResultSet result = select(sql);
		while (null != result && result.next()) {
			EventTopicData EventTopicData = assembleAllTopicid(result);
			list.add(EventTopicData);
		}
		return list;
		
	}
	private EventTopicData assembleAllTopicid(ResultSet result) {
		try {
			if (null != result) {
				
				long httopid = result.getLong("HotTopicID");
				String TopicName = result.getString("TopicName");
				//System.out.println("TopicInvestDao->assembleAllTopic->HotTopicID="+curstr);
				EventTopicData data = new EventTopicData(httopid,TopicName);
				return data;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public static void main(String[] args) throws SQLException
	{
		TopicEventDao ted = new TopicEventDao();
		StockFutureEvent s  = ted.getAllEventListByObj("SH600879");
		s.print();
		TopicEventRedisBase x = new TopicEventRedisBase();
		Topicevent.StockFutureEvent tse = x.getStockFutureEventByKeyFromRedis("E00", "SH", 6487);
		StockFutureEvent sfe = new StockFutureEvent();
		sfe.parseFromStockFutureEvent(tse);
		sfe.print();
		boolean sign = sfe.equals(s);
		if(sign)
			System.out.println("xxx");
		else
			System.out.println("yyy");
	}
}
