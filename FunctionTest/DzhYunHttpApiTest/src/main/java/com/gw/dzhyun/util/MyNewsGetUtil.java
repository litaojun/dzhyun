package com.gw.dzhyun.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dzhyun.proto.Dzhoutput.QuoteDyna;

public class MyNewsGetUtil {
	public static JSONArray getJSONArrayByJsonString(String jsonstring)
	{
		JSONArray a = null;
		JSONObject jsonUAResponse = JSON.parseObject(jsonstring);
		//获取UAResponse的Data部分。
		int retcode = jsonUAResponse.getIntValue("Err");
		if(retcode == 0)
		{
     		JSONObject jsonMSG = jsonUAResponse.getJSONObject("Data");   
			//获取Data中的RepDataQuoteDynaSingle部分（这是个数组）
			 a = jsonMSG.getJSONArray("RepDataNewsInfoValue");
		}
		return a;
	}
	public static int getErrCodeByJsonString(String jsonstring)
	{
		int retcode =0 ;
		JSONObject jsonUAResponse = JSON.parseObject(jsonstring);
		//获取UAResponse的Data部分。
		retcode = jsonUAResponse.getIntValue("Err");
		return retcode;
	}
	
	public static JSONObject getJSONObjByStr(String jsonstr)
	{
		JSONObject jsonUAResponse=null;
		if(jsonstr != null)
		       jsonUAResponse = JSON.parseObject(jsonstr);
		return jsonUAResponse;
	}
	
	/**
	 * 根据{@code objcode}（股票代码）取得行情(QuoteDyna)。
	 * @param jsonstring 
	 * @param objcode
	 * @return JSONObject
	 */
	public static JSONObject getNewsGetByObjCode(String jsonstring)
	{
		/*
		 {"Qid":"","Err":0,"Counter":1,"Data":{"Id":20,"RepDataQuoteDynaSingle":[{"Obj":"SH600000","Data":{"Time":1423807969,"LastClose":1435,"High":1467,"Open":1450,"Low":1435,"New":1456,"Volume":51472,"Amount":2.4968515e+11}}]}}
		*/
		/*新格式：
		 {"Qid":"","Err":0,"Counter":1,"Data":{"Id":24,"RepDataNewsInfoValue":[{"ver":"13432993431","act":1,"newsID":200000417912,"newsTitle":"业内人士：银行获券商牌照今年有望成行"},{"ver":"13433152934","act":1,"newsID":406542196,"newsTitle":"浦发银行发行国内首单工程机械贷款资产证券化项目"},{"ver":"13461984351","act":1,"newsID":406645400,"newsTitle":"浦发银行“参付通” 提升贸易融资功能"},{"ver":"13462112432","act":1,"newsID":200000418662,"newsTitle":"地方债置换刺激股市大涨，分析称沪指有望冲击前期高点"},{"ver":"13465124831","act":1,"newsID":406697947,"newsTitle":"(上互动)浦发银行：对于县域网点的布局主要是加强全国百强县支行的建设"},{"ver":"13465139293","act":1,"newsID":5145071408,"newsTitle":"2015年03月12日浦发银行大宗交易"},{"ver":"13516207394","act":1,"newsID":406877429,"newsTitle":"浦发银行再发150亿元优先股 股息率5.50%低于上期"},{"ver":"13517151604","act":1,"newsID":406884890,"newsTitle":"浦发银行完成第二期150亿元优先股发行，票面股息率5.5%"},{"ver":"13527212663","act":1,"newsID":406942709,"newsTitle":"浦发银行高管变动初步落定"},{"ver":"13527268303","act":1,"newsID":406934030,"newsTitle":"浦发信用卡力推“合伙人”计划"},{"ver":"13530928077","act":1,"newsID":406990108,"newsTitle":"浦发银行：去年净利润470.26亿 同比增14.92%"},{"ver":"13535521230","act":1,"newsID":407003814,"newsTitle":"浦发行2014年净利增近15%拟每10股派7.57元"},{"ver":"13536575722","act":1,"newsID":407008284,"newsTitle":"净利增14.9%  不良贷款率上升0.32个百分点"},{"ver":"13537116884","act":1,"newsID":407001021,"newsTitle":"浦发银行去年净利增一成五"},{"ver":"13537134883","act":1,"newsID":407007982,"newsTitle":"吞机构当金控，浦发走到了哪儿？"},{"ver":"13537224209","act":1,"newsID":407001637,"newsTitle":"浦发招商银行业务结构优化"},{"ver":"13537390676","act":1,"newsID":200000420664,"newsTitle":"浦发银行去年净利润同比增14.92%，不良率升0.32至1.06%"},{"ver":"13537990306","act":1,"newsID":5152217757,"newsTitle":"浦银国际3月下旬开业 浦发银行加速大投行布局"},{"ver":"13547188436","act":1,"newsID":407070288,"newsTitle":"单极格局有改变 浦发“三脚凳”计划初见成效"},{"ver":"13547315513","act":1,"newsID":407072209,"newsTitle":"招行、浦发净利润增速双滑坡 银行板块确认估值底部"},{"ver":"13547945229","act":1,"newsID":407036410,"newsTitle":"浦发银行高薪董秘沈思退位 不良贷款四年涨150亿"},{"ver":"13548012322","act":1,"newsID":407071060,"newsTitle":"浦银国际3月下旬开业"},{"ver":"13548100995","act":1,"newsID":200000421046,"newsTitle":"3月20日报摘——21世纪经济报道"},{"ver":"13548351834","act":1,"newsID":407076816,"newsTitle":"浦发银行去年净利增长 14.92%"},{"ver":"13549046137","act":1,"newsID":407077327,"newsTitle":"浦发行压低净利增速：资产质量压力未减"},{"ver":"13550516771","act":1,"newsID":407064191,"newsTitle":"浦发及招商银行2014年不良贷款率均上升"},{"ver":"13552510045","act":1,"newsID":407108525,"newsTitle":"(上互动)浦发银行：监管机构尚未放开商业银行参股或收购证券公司的限制"},{"ver":"13583074325","act":1,"newsID":407164222,"newsTitle":"浦发银行二期优先股周四起挂牌转让"},{"ver":"13583737658","act":1,"newsID":407163897,"newsTitle":"浦发创新 万众创业"},{"ver":"13584035548","act":1,"newsID":407169460,"newsTitle":"浦发银行去年净利增长14.92%"},{"ver":"13585057210","act":1,"newsID":407167949,"newsTitle":"浦发不良率两年翻倍 转型金融市场摆脱对公依赖"},{"ver":"13594330660","act":1,"newsID":407227250,"newsTitle":"股份制银行排位赛白热化：招行领跑优势缩小兴业浦发展开“小数点后的争夺”"},{"ver":"13595645272","act":1,"newsID":200000421955,"newsTitle":"融资融券余额持续创新高，部分券商额度紧张"},{"ver":"13606423826","act":1,"newsID":5157960763,"newsTitle":"浦发银行战略转型初见成效"},{"ver":"13607202888","act":1,"newsID":200000422258,"newsTitle":"机构人士：外资撤离A股属暂时性"},{"ver":"13607509419","act":1,"newsID":5157965599,"newsTitle":"埋下不良贷款地雷 银行增速跌入个位"},{"ver":"13609911168","act":1,"newsID":5157957779,"newsTitle":"不良贷款恶化拉低银行利润 中信银行招行息差收窄"},{"ver":"13617809869","act":1,"newsID":407343671,"newsTitle":"浦发银行小微客户全国培育计划正式启动"},{"ver":"13628612808","act":1,"newsID":407419842,"newsTitle":"浦银国际香港开业 第一期5亿港元增资完成"},{"ver":"13631588845","act":1,"newsID":5161051593,"newsTitle":"浦发银行不良率激增 制造业罪魁祸首"},{"ver":"13632773857","act":1,"newsID":407438310,"newsTitle":"浦发银行南京分行辖属两家支行顺利开业"},{"ver":"13673126059","act":1,"newsID":200000423527,"newsTitle":"浦发银行副行长商洪波辞职"},{"ver":"13673236681","act":1,"newsID":407558450,"newsTitle":"浦发银行：副行长商洪波辞职"},{"ver":"13674071184","act":1,"newsID":5163751350,"newsTitle":"2015年03月30日浦发银行大宗交易"},{"ver":"13682428773","act":1,"newsID":200000423757,"newsTitle":"长信基金再创股债双佳绩"},{"ver":"13683500943","act":1,"newsID":407574736,"newsTitle":"浦发信用卡“弯道超车”后劲足"},{"ver":"13686000579","act":1,"newsID":5165095989,"newsTitle":"2015年03月31日浦发银行大宗交易"},{"ver":"13693495283","act":1,"newsID":407635467,"newsTitle":"浦发银行国际化战略迈出新步伐"},{"ver":"13694712603","act":1,"newsID":407649179,"newsTitle":"国内首现生物医药专业银行"}]}}
		 */
		JSONObject jsonUAResponse = JSON.parseObject(jsonstring);
		//获取UAResponse的Data部分。
		int retcode = jsonUAResponse.getIntValue("Err");
		if(retcode != 0)
			return null;
		JSONObject jsonMSG = jsonUAResponse.getJSONObject("Data");   
		//获取Data中的RepDataQuoteDynaSingle部分（这是个数组）
		JSONArray jsonQDSArray = jsonMSG.getJSONArray("RepDataNewsInfoValue");  //QuoteDynaSingle数组
		if(jsonQDSArray==null || jsonQDSArray.equals(""))
		{
			return null;
		}
		//System.out.println("ss="+jsonQDSArray.size());
		String ver,newsTitle;
		int act;
		long newsID;
		JSONObject jsonQDS=null;
		for(int i=0;i<jsonQDSArray.size();i++)
		{
			jsonQDS =  jsonQDSArray.getJSONObject(i);
			ver =jsonQDS.getString("ver");
			act =jsonQDS.getIntValue("act");
			newsID =jsonQDS.getLongValue("newsID");
			newsTitle = jsonQDS.getString("newsTitle");
			//System.out.println("ver="+ver+";act="+act+";newsID="+newsID+"newsTitle="+newsTitle);
		}
		return jsonQDS;
	}
	public static JSONObject getIndicatorCalc(String jsonstring)
	{
		JSONObject jsonUAResponse = JSON.parseObject(jsonstring);
		int retcode = jsonUAResponse.getIntValue("Err");
		if(retcode != 0)
			return null;
		JSONObject jsonMSG = jsonUAResponse.getJSONObject("Data");   
		//获取Data中的RepDataQuoteDynaSingle部分（这是个数组）
		JSONArray jsonQDSArray = jsonMSG.getJSONArray("RepDataZhiBiaoShuChu");  //QuoteDynaSingle数组
		if(jsonQDSArray==null || jsonQDSArray.equals(""))
		{
			//System.out.println("33333333333");
			return null;
		}
		return jsonUAResponse;
	}
	public static JSONObject getNews(String jsonstring)
	{
		/*
		 {"Qid":"","Err":0,"Counter":1,"Data":{"Id":20,"RepDataQuoteDynaSingle":[{"Obj":"SH600000","Data":{"Time":1423807969,"LastClose":1435,"High":1467,"Open":1450,"Low":1435,"New":1456,"Volume":51472,"Amount":2.4968515e+11}}]}}
		*/
		/*新格式：
		 {"Qid":"","Err":0,"Counter":1,"Data":{"Id":24,"RepDataNewsInfoValue":[{"ver":"13432993431","act":1,"newsID":200000417912,"newsTitle":"业内人士：银行获券商牌照今年有望成行"},{"ver":"13433152934","act":1,"newsID":406542196,"newsTitle":"浦发银行发行国内首单工程机械贷款资产证券化项目"},{"ver":"13461984351","act":1,"newsID":406645400,"newsTitle":"浦发银行“参付通” 提升贸易融资功能"},{"ver":"13462112432","act":1,"newsID":200000418662,"newsTitle":"地方债置换刺激股市大涨，分析称沪指有望冲击前期高点"},{"ver":"13465124831","act":1,"newsID":406697947,"newsTitle":"(上互动)浦发银行：对于县域网点的布局主要是加强全国百强县支行的建设"},{"ver":"13465139293","act":1,"newsID":5145071408,"newsTitle":"2015年03月12日浦发银行大宗交易"},{"ver":"13516207394","act":1,"newsID":406877429,"newsTitle":"浦发银行再发150亿元优先股 股息率5.50%低于上期"},{"ver":"13517151604","act":1,"newsID":406884890,"newsTitle":"浦发银行完成第二期150亿元优先股发行，票面股息率5.5%"},{"ver":"13527212663","act":1,"newsID":406942709,"newsTitle":"浦发银行高管变动初步落定"},{"ver":"13527268303","act":1,"newsID":406934030,"newsTitle":"浦发信用卡力推“合伙人”计划"},{"ver":"13530928077","act":1,"newsID":406990108,"newsTitle":"浦发银行：去年净利润470.26亿 同比增14.92%"},{"ver":"13535521230","act":1,"newsID":407003814,"newsTitle":"浦发行2014年净利增近15%拟每10股派7.57元"},{"ver":"13536575722","act":1,"newsID":407008284,"newsTitle":"净利增14.9%  不良贷款率上升0.32个百分点"},{"ver":"13537116884","act":1,"newsID":407001021,"newsTitle":"浦发银行去年净利增一成五"},{"ver":"13537134883","act":1,"newsID":407007982,"newsTitle":"吞机构当金控，浦发走到了哪儿？"},{"ver":"13537224209","act":1,"newsID":407001637,"newsTitle":"浦发招商银行业务结构优化"},{"ver":"13537390676","act":1,"newsID":200000420664,"newsTitle":"浦发银行去年净利润同比增14.92%，不良率升0.32至1.06%"},{"ver":"13537990306","act":1,"newsID":5152217757,"newsTitle":"浦银国际3月下旬开业 浦发银行加速大投行布局"},{"ver":"13547188436","act":1,"newsID":407070288,"newsTitle":"单极格局有改变 浦发“三脚凳”计划初见成效"},{"ver":"13547315513","act":1,"newsID":407072209,"newsTitle":"招行、浦发净利润增速双滑坡 银行板块确认估值底部"},{"ver":"13547945229","act":1,"newsID":407036410,"newsTitle":"浦发银行高薪董秘沈思退位 不良贷款四年涨150亿"},{"ver":"13548012322","act":1,"newsID":407071060,"newsTitle":"浦银国际3月下旬开业"},{"ver":"13548100995","act":1,"newsID":200000421046,"newsTitle":"3月20日报摘——21世纪经济报道"},{"ver":"13548351834","act":1,"newsID":407076816,"newsTitle":"浦发银行去年净利增长 14.92%"},{"ver":"13549046137","act":1,"newsID":407077327,"newsTitle":"浦发行压低净利增速：资产质量压力未减"},{"ver":"13550516771","act":1,"newsID":407064191,"newsTitle":"浦发及招商银行2014年不良贷款率均上升"},{"ver":"13552510045","act":1,"newsID":407108525,"newsTitle":"(上互动)浦发银行：监管机构尚未放开商业银行参股或收购证券公司的限制"},{"ver":"13583074325","act":1,"newsID":407164222,"newsTitle":"浦发银行二期优先股周四起挂牌转让"},{"ver":"13583737658","act":1,"newsID":407163897,"newsTitle":"浦发创新 万众创业"},{"ver":"13584035548","act":1,"newsID":407169460,"newsTitle":"浦发银行去年净利增长14.92%"},{"ver":"13585057210","act":1,"newsID":407167949,"newsTitle":"浦发不良率两年翻倍 转型金融市场摆脱对公依赖"},{"ver":"13594330660","act":1,"newsID":407227250,"newsTitle":"股份制银行排位赛白热化：招行领跑优势缩小兴业浦发展开“小数点后的争夺”"},{"ver":"13595645272","act":1,"newsID":200000421955,"newsTitle":"融资融券余额持续创新高，部分券商额度紧张"},{"ver":"13606423826","act":1,"newsID":5157960763,"newsTitle":"浦发银行战略转型初见成效"},{"ver":"13607202888","act":1,"newsID":200000422258,"newsTitle":"机构人士：外资撤离A股属暂时性"},{"ver":"13607509419","act":1,"newsID":5157965599,"newsTitle":"埋下不良贷款地雷 银行增速跌入个位"},{"ver":"13609911168","act":1,"newsID":5157957779,"newsTitle":"不良贷款恶化拉低银行利润 中信银行招行息差收窄"},{"ver":"13617809869","act":1,"newsID":407343671,"newsTitle":"浦发银行小微客户全国培育计划正式启动"},{"ver":"13628612808","act":1,"newsID":407419842,"newsTitle":"浦银国际香港开业 第一期5亿港元增资完成"},{"ver":"13631588845","act":1,"newsID":5161051593,"newsTitle":"浦发银行不良率激增 制造业罪魁祸首"},{"ver":"13632773857","act":1,"newsID":407438310,"newsTitle":"浦发银行南京分行辖属两家支行顺利开业"},{"ver":"13673126059","act":1,"newsID":200000423527,"newsTitle":"浦发银行副行长商洪波辞职"},{"ver":"13673236681","act":1,"newsID":407558450,"newsTitle":"浦发银行：副行长商洪波辞职"},{"ver":"13674071184","act":1,"newsID":5163751350,"newsTitle":"2015年03月30日浦发银行大宗交易"},{"ver":"13682428773","act":1,"newsID":200000423757,"newsTitle":"长信基金再创股债双佳绩"},{"ver":"13683500943","act":1,"newsID":407574736,"newsTitle":"浦发信用卡“弯道超车”后劲足"},{"ver":"13686000579","act":1,"newsID":5165095989,"newsTitle":"2015年03月31日浦发银行大宗交易"},{"ver":"13693495283","act":1,"newsID":407635467,"newsTitle":"浦发银行国际化战略迈出新步伐"},{"ver":"13694712603","act":1,"newsID":407649179,"newsTitle":"国内首现生物医药专业银行"}]}}
		 */
		JSONObject jsonUAResponse = JSON.parseObject(jsonstring);
		//获取UAResponse的Data部分。
		int retcode = jsonUAResponse.getIntValue("Err");
		if(retcode != 0)
			return null;
		JSONObject jsonMSG = jsonUAResponse.getJSONObject("Data");   
		//获取Data中的RepDataQuoteDynaSingle部分（这是个数组）
		JSONArray jsonQDSArray = jsonMSG.getJSONArray("RepDataQuoteDynaSingle");  //QuoteDynaSingle数组
		if(jsonQDSArray==null || jsonQDSArray.equals(""))
		{
			//System.out.println("33333333333");
			return null;
		}
		//System.out.println("ss="+jsonQDSArray.size());
		String ver,newsTitle;
		int act;
		long newsID;
		JSONObject jsonQDS=null;
		for(int i=0;i<jsonQDSArray.size();i++)
		{
			jsonQDS =  jsonQDSArray.getJSONObject(i).getJSONObject("Data");
			//System.out.println("jsonQDS="+jsonQDS);
			if(jsonQDS != null &&jsonQDS.containsKey("ShiJian") && jsonQDS.containsKey("ZuiXinJia") && jsonQDS.containsKey("KaiPanJia"))
			{
				//System.out.println("11111111111");
					ver =jsonQDS.getString("ShiJian");
					act =jsonQDS.getIntValue("ZuiXinJia");
					newsID =jsonQDS.getLongValue("KaiPanJia");
					newsTitle = jsonQDS.getString("ZuiGaoJia");
			}
			else
			{
				//System.out.println("22222222222222");
				return null;
			}
			//System.out.println("ver="+ver+";act="+act+";newsID="+newsID+"newsTitle="+newsTitle);
		}
		return jsonQDS;
	}
}
