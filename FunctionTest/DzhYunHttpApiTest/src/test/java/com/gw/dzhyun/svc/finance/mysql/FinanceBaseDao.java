/**
 * @classnmae FinanceBaseDao.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.svc.finance.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gw.dzhyun.fjjj.BaseDao;
import com.gw.dzhyun.svc.finance.util.BasicFinanceData;
import com.gw.dzhyun.svc.finance.util.DividData;
import com.gw.dzhyun.svc.finance.util.RongZiRongQuanShuJu;
import com.gw.dzhyun.svc.topicEvent.EventObjData;
import com.gw.dzhyun.svc.topicEvent.EventTopicData;
import com.gw.dzhyun.svc.topicEvent.FutureEvent;
import com.gw.dzhyun.svc.topicEvent.StockFutureEvent;

/**
 * @author Litaojun
 * @date   2016年1月6日
 */
public class FinanceBaseDao extends BaseDao 
{
	/**
	 * 
	 * @param @param obj
	 * @param @return
	 * @param @throws SQLException
	 * @Title getSsFinanceDataListByObj
	 * @Description 从MYSQL数据库中查询实时财务数据
	 * @return BasicFinanceData
	 *
	 */
	public BasicFinanceData getSsFinanceDataListByObj(String obj) throws SQLException
	{


		String sql = "SELECT SUBSTRING_INDEX(CO,'.',1) as Obj, EndDate, C35, C3," +
					" C4,C5,C6,C7,C8,C9,C10,C11,C12,C13,C14,C15,C16,C17,C18,C19,C20,C21,C22," + 
					 "C23,C24,C25,C26,C27,C28,C29,C30,C31,C32,C33,C34,C1,C36,C37,C38,C39,C40," + 
					" C41,C42,C43,C44,C45,C46,C47,C48,C49,C50,ETIME,CV FROM tIT8270 where" + 
					"  (etime > '' or (etime = '' and CV > '')) " +
					" and SUBSTRING_INDEX(CO,'.',1)= '" + obj +"'" ;
		//System.out.println(sql);
		ResultSet result = select(sql);
		BasicFinanceData category = null;
		while (null != result && result.next()) {
			 category = assembleBasicFinanceData(result);
			
			
		}

		return category;
		
	}
	
	/**
	 * 
	 * @param @param obj
	 * @param @return
	 * @param @throws SQLException
	 * @Title getBasicFinanceDataListByObj
	 * @Description 从MYSQL数据库中查询基本财务数据
	 * @return ArrayList<BasicFinanceData>
	 *
	 */
	public ArrayList<BasicFinanceData> getBasicFinanceDataListByObj(String obj) throws SQLException
	{
		ArrayList<BasicFinanceData> sete = new ArrayList<BasicFinanceData>();

		String sql = "SELECT SUBSTRING_INDEX(CO,'.',1) as Obj, EndDate, C35, C3," +
					" C4,C5,C6,C7,C8,C9,C10,C11,C12,C13,C14,C15,C16,C17,C18,C19,C20,C21,C22," + 
					 "C23,C24,C25,C26,C27,C28,C29,C30,C31,C32,C33,C34,C1,C36,C37,C38,C39,C40," + 
					" C41,C42,C43,C44,C45,C46,C47,C48,C49,C50,ETIME,CV FROM tIT8271 where" + 
					" (etime > '' or (etime = '' and CV > '')) " +
					" and SUBSTRING_INDEX(CO,'.',1)= '" + obj +"'" ;
		//System.out.println(sql);
		ResultSet result = select(sql);
		while (null != result && result.next()) {
			BasicFinanceData category = assembleBasicFinanceData(result);
			sete.add(category);
			
		}

		return sete;
		
	}
	private BasicFinanceData assembleBasicFinanceData(ResultSet result) {
		try {
			if (null != result) {
				String obj                 = result.getString("Obj");         
				String repdate             = result.getString("EndDate");    
				long floatdate           =   result.getLong("C35");          
				Double earnps              = result.getDouble("C3");         
				Double assetps             = result.getDouble("C4");         
				Double rona                = result.getDouble("C5");         
				Double cashps              = result.getDouble("C6");         
				Double accufundps          = result.getDouble("C7");         
				Double unapproprofitps     = result.getDouble("C8");         
				Double rateonequity        = result.getDouble("C9");         
				Double profitinc           = result.getDouble("C10");        
				Double incomeinc           = result.getDouble("C11");        
				Double grossprofit         = result.getDouble("C12");        
				Double adjassetps          = result.getDouble("C13");        
				Double asset               = result.getDouble("C14");        
				Double floatasset          = result.getDouble("C15");        
				Double fixedasset          = result.getDouble("C16");        
				Double intasset            = result.getDouble("C17");        
				Double floatdebet          = result.getDouble("C18");        
				Double longdebet           = result.getDouble("C19");        
				Double alldebet            = result.getDouble("C20");        
				Double hoderequity         = result.getDouble("C21");        
				Double capitalfund         = result.getDouble("C22");        
				Double cashfloat           = result.getDouble("C23");        
				Double investfloat         = result.getDouble("C24");        
				Double raisefloat          = result.getDouble("C25");        
				Double cashinc             = result.getDouble("C26");        
				Double mainincome          = result.getDouble("C27");        
				Double mainprofit          = result.getDouble("C28");        
				Double tradeprofit         = result.getDouble("C29");        
				Double investprofit        = result.getDouble("C30");        
				Double otherbalance        = result.getDouble("C31");        
				Double allprofit           = result.getDouble("C32");        
				Double netprofit           = result.getDouble("C33");        
				Double unapproprofit       = result.getDouble("C34");        
				Double totalshare          = result.getDouble("C1");         
				Double norestrictshare     = result.getDouble("C36");        
				Double ashare              = result.getDouble("C37");        
				Double bshare              = result.getDouble("C38");        
				Double foreignshare        = result.getDouble("C39");        
				Double otherfloatshare     = result.getDouble("C40");        
				Double restrictshare       = result.getDouble("C41");        
				Double nationshare         = result.getDouble("C42");        
				Double natcorpshare        = result.getDouble("C43");        
				Double domestcorpshare     = result.getDouble("C44");        
				Double domestindshare      = result.getDouble("C45");        
				Double otherlaunchshare    = result.getDouble("C46");        
				Double raisecorpshare      = result.getDouble("C47");        
				Double foreigncorpshare    = result.getDouble("C48");        
				Double foreingIndshare     = result.getDouble("C49");        
				Double preforothershare    = result.getDouble("C50");        
				BasicFinanceData a = new BasicFinanceData();
				a.setObj(obj);
				a.setRepdate(repdate);
				a.setFloatdate(floatdate);
				a.setEarnps(earnps);
				a.setAssetps(assetps);
				a.setRona(rona);
				a.setCashps(cashps);
				a.setAccufundps(accufundps);
				a.setUnapproprofitps(unapproprofitps);
				a.setRateonequity(rateonequity);
				a.setProfitinc(profitinc);
				a.setIncomeinc(incomeinc);
				a.setGrossprofit(grossprofit);
				a.setAdjassetps(adjassetps);
				a.setAsset(asset);
				a.setFloatasset(floatasset);
				a.setFixedasset(fixedasset);
				a.setIntasset(intasset);
				a.setFloatdebet(floatdebet);
				a.setLongdebet(longdebet);
				a.setAlldebet(alldebet);
				a.setHoderequity(hoderequity);
				a.setCapitalfund(capitalfund);
				a.setCashfloat(cashfloat);
				a.setInvestfloat(investfloat);
				a.setRaisefloat(raisefloat);
				a.setCashinc(cashinc);
				a.setMainincome(mainincome);
				a.setMainprofit(mainprofit);
				a.setTradeprofit(tradeprofit);
				a.setInvestprofit(investprofit);
				a.setOtherbalance(otherbalance);
				a.setAllprofit(allprofit);
				a.setNetprofit(netprofit);
				a.setUnapproprofit(unapproprofit);
				a.setTotalshare(totalshare);
				a.setNorestrictshare(norestrictshare);
				a.setAshare(ashare);
				a.setBshare(bshare);
				a.setForeignshare(foreignshare);
				a.setOtherfloatshare(otherfloatshare);
				a.setRestrictshare(restrictshare);
				a.setNationshare(nationshare);
				a.setNatcorpshare(natcorpshare);
				a.setDomestcorpshare(domestcorpshare);
				a.setDomestindshare(domestindshare);
				a.setOtherlaunchshare(otherlaunchshare);
				a.setRaisecorpshare(raisecorpshare);
				a.setForeigncorpshare(foreigncorpshare);
				a.setForeingindshare(foreingIndshare);
				a.setPreforothershare(preforothershare);
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
	
	/**
	 * 
	 * @param @param obj
	 * @param @return
	 * @param @throws SQLException
	 * @Title getDividDataListByObj
	 * @Description 从MYSQL中查询除权数据
	 * @return ArrayList<DividData>
	 *
	 */
	public ArrayList<DividData> getDividDataListByObj(String obj) throws SQLException
	{
		ArrayList<DividData> sete = new ArrayList<DividData>();

		String sql = "SELECT SUBSTRING_INDEX(CO,'.',1) obj, C7" +
					   " ,C9,C10,C11,C12,C14,C15,C13,etime FROM tEQ3005" +
					   " where  (etime > '' or" +
					  " (etime = '' and CV > '')) and SUBSTRING_INDEX(CO,'.',1) = '" + obj +"'" ;
		System.out.println(sql);
		ResultSet result = select(sql);
		int i =0;
		while (null != result && result.next()) {
			DividData category = assembleDividData(result);
			category.print();
			i++;
			sete.add(category);
			
		}
		System.out.println("i="+i);

		return sete;
		
	}
	private DividData assembleDividData(ResultSet result) {
		try {
			if (null != result) {
				String obj = result.getString("obj");         
				String chuQuanRiQi = result.getString("C7");  
				String guQuanDenJiRi  = result.getString("C9");            
				Double songGuShu  = result.getDouble("C10");         
				Double zhuanZengGuShu  = result.getDouble("C11");         
				Double paiXiShu  = result.getDouble("C12");         
				Double peiGuShu  = result.getDouble("C14");         
				Double peiGuJia  = result.getDouble("C15");   
				Double paiXiShuShuiHou  = result.getDouble("C13");   
				DividData a = new DividData();
				
				a.setObj(obj);
				a.setChuquanriqi(chuQuanRiQi);
				a.setGuquandenjiri(guQuanDenJiRi);
				a.setSonggushu(songGuShu);
				a.setZhuanzenggushu(zhuanZengGuShu);
				a.setPaixishu(paiXiShu);
				a.setPeigushu(peiGuShu);
				a.setPeigujia(peiGuJia);
				a.setPaiXiShuShuiHou(paiXiShuShuiHou);
				return a;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	/**
	 * 
	 * @param @param obj
	 * @param @return
	 * @param @throws SQLException
	 * @Title getRongZiRongQuanShuJuListByObj
	 * @Description 从MYSQL中查询融资融卷数据
	 * @return ArrayList<RongZiRongQuanShuJu>
	 *
	 */
	public ArrayList<RongZiRongQuanShuJu> getRongZiRongQuanShuJuListByObj(String obj) throws SQLException
	{
		ArrayList<RongZiRongQuanShuJu> sete = new ArrayList<RongZiRongQuanShuJu>();

		String sql = "select SUBSTRING_INDEX(CO,'.',1) as Obj,C1,C2," +
					 "C3,C4,C5,C6,C7,C8,C9,C11,C12,C13,etime,cv from tEQ4010 where " +
					 " (etime > '' or (etime = '' and " +
					 "CV > '')) and SUBSTRING_INDEX(CO,'.',1) = '" + obj +"'" ;
		//System.out.println(sql);
		ResultSet result = select(sql);
		while (null != result && result.next()) {
			RongZiRongQuanShuJu category = assembleRongZiRongQuanShuJu(result);
			sete.add(category);
		}

		return sete;
		
	}
	private RongZiRongQuanShuJu assembleRongZiRongQuanShuJu(ResultSet result) {
		try {
			if (null != result) {
				String obj                 = result.getString("obj");            
				String earnps              = result.getString("C1");         
				Double assetps             = result.getDouble("C2");         
				Double rona                = result.getDouble("C3");         
				Double cashps              = result.getDouble("C4");         
				Double accufundps          = result.getDouble("C5");         
				Double unapproprofitps     = result.getDouble("C6");         
				Double rateonequity        = result.getDouble("C7");         
				Double profitinc           = result.getDouble("C8");        
				Double incomeinc           = result.getDouble("C9");    
				Double income           = result.getDouble("C11");   
				Double grossprofit         = result.getDouble("C12");        
				Double adjassetps          = result.getDouble("C13");        
				RongZiRongQuanShuJu a = new RongZiRongQuanShuJu();
				a.setObj(obj);
				a.setJiaoYiRiQi(earnps);
				a.setRongZiYuE(assetps);
				a.setRongZiMaiRuE(rona);
				a.setRongZiChangHuanE(cashps);
				a.setRongQuanYuLiang(accufundps);
				a.setRongQuanYuE(unapproprofitps);
				a.setRongQuanMaiChuLiang(rateonequity);
				a.setRongQuanChangHuanLiang(profitinc);
				a.setRongZiRongQuanYuE(incomeinc);
				a.setRongQuanMaiChuE(income);
				a.setRongQuanChangHuanE(grossprofit);
				a.setRongZiRongQuanLiang(adjassetps);
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

	/**
	 * @param @param args
	 * @Title main
	 * @Description TODO
	 * @return void
	 * @throws SQLException 
	 * 
	 */
	public static void main(String[] args) throws SQLException 
	{
		// TODO Auto-generated method stub
		FinanceBaseDao fbd = new FinanceBaseDao();
		BasicFinanceData bdlist = fbd.getSsFinanceDataListByObj("SH600816");
		bdlist.print();
//		for(RongZiRongQuanShuJu bd:bdlist)
//		{
//			bd.print();
//		}
	}

}
