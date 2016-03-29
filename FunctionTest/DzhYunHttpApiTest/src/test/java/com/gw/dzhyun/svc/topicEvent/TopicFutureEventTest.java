/**
 * @classnmae TopicFutureEventTest.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.topicEvent;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

/**
 * @author Litaojun
 * @date   2016年1月12日
 */
public class TopicFutureEventTest {
	
	TopicEventDao ted = new TopicEventDao();
	TopicEventRedisBase x = new TopicEventRedisBase();
	
	/**
	 * 
	 * @param @throws SQLException
	 * @Title TestTopicFutureEvent
	 * @Description  股票未来事件(前瞻日历)
	 *               需求：http://dms.gw.com.cn/pages/viewpage.action?pageId=135299775
	 *               任务：http://ims.gw.com.cn/browse/DZHYUN-478
	 *               SH600879|6487
	 *               比较SH600879MYSQL书籍库中与REDIS中股票的未来事件数据一致性
	 * @return void
	 *
	 */
	@Test
	public void TestTopicFutureEvent() throws SQLException
	{
		StockFutureEvent s  = ted.getAllEventListByObj("SH600879");
		//s.print();
		//System.out.println("---------------------------------------------------------------------");
		Topicevent.StockFutureEvent tse = x.getStockFutureEventByKeyFromRedis("E00", "SH", 6487);
		StockFutureEvent sfe = new StockFutureEvent();
		sfe.parseFromStockFutureEvent(tse);
		//sfe.print();
		boolean sign = sfe.equals(s);
		assertTrue(sign);
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
