package com.gw.dzhyun.forecast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/11/10.
 */
public class yzxyjycTest {
//    private static final Log LOG = LogFactory.getLog(yzxyjycTest.class);
    private static String configpath;
    private static String stockpath;
    private static Vector<String> stockcodes;
    private static String yunip;
    private static String yunport;
    private static String dbip;
    private static String dbport;
    private static String dbuser;
    private static String dbpassword;
    private static String appid;
    private static String secret_key;

    @BeforeClass
    public void GlobalInit() {
        configpath = System.getProperty("user.dir") + "/config/forecast.properties";
        PropertiesManager propertiesManager = new PropertiesManager(configpath);
        yunip = propertiesManager.getValue("yunip");
        yunport = propertiesManager.getValue("yunport");
        dbip = propertiesManager.getValue("dbip");
        dbport = propertiesManager.getValue("dbport");
        dbuser = propertiesManager.getValue("dbuser");
        dbpassword = propertiesManager.getValue("dbpassword");
        appid = propertiesManager.getValue("appid");
        secret_key = propertiesManager.getValue("secret_key");

        stockpath = System.getProperty("user.dir") + "/resources/stockcode.txt";   //������ȫ��
        stockcodes = FileUtil.getVectorFromFile(stockpath, "UTF-8");            //ʹ�á�/����win��linuxͨ��
    }

    @Test
    public void testYzxyjyc() throws Exception {
        boolean checkresult = true;
        for (String stockcode : stockcodes) {
            checkresult = checkyzxyjyc(stockcode);
            if (!checkresult) {
//                LOG.debug("�ڹ�Ʊ����Ϊ" + stockcode + "ʱ���Բ�ͨ��");
                break;
            }
        }
        assertTrue("һ����ҵ��Ԥ�����", checkresult);
    }

    public boolean checkyzxyjyc(String obj) throws Exception {
//        LOG.debug("checkyzxyjyc");

        String tokenString = "https://" + yunip + yunport + "/token/access?appid=" + appid + "&secret_key=" + secret_key;
        JSONObject tokenresult = JSON.parseObject(MyHttpUtil.getData(tokenString, "json"));
        String token = tokenresult.getString("token");
        String yunString = "http://" + yunip + yunport + "/forecasts/yzxyjyc?obj=" + obj + "&token=" + token;
        JSONObject yunresult = JSON.parseObject(MyHttpUtil.getData(yunString, "json"));

        String querysql = "select * from (\n" +
                "select CV, SUBSTRING_INDEX(CO,'.',1) obj, C2, C9, C12, C14 \n" +
                "from dzh_rsr.tRS0004 \n" +
                "where c4 = 'SH600000' \n" +
                "and C2 > 20150801000000 and CV > 0 and flag <> 1 and C3 = 1 \n" +
                "order by C2 desc\n" +
                ") b \n" +
                "group by obj,C9 \n" +
                "order by CV";
        ResultSet dbresult = MyDatabaseUtil.doQuerySqlS(dbip,dbport,dbuser,dbpassword,"dzh_rsr",querysql);
        JSONArray stockdata = new JSONArray();           //ĳֻ��Ʊ���о�����
        long today = 0;
        while (dbresult.next()) {
            long C2 = Long.valueOf(dbresult.getString("C2"));
            if (C2 >= today) {
                if (C2 > today) {
                    stockdata.clear();                               //���Ԥ��ʱ����£������֮ǰ��Ԥ�����
                    today = C2;
                }
                JSONObject datayear = new JSONObject();              //Ԥ����ȵ����
                datayear.put("baoGaoRiQi",today);
                datayear.put("yuCeNianDu", dbresult.getString("C9"));
                datayear.put("jingLiRun", dbresult.getString("C12"));
                datayear.put("meiGuShouYi",dbresult.getString("C14"));
                stockdata.add(datayear);
            }
        }
        boolean checkresult =  yunresult.getJSONObject("Data").getJSONArray("RepDataYiZhiXinYeJiYuCeOutPut").getJSONObject(0).
                getJSONArray("Data").equals(stockdata);

//        if (checkresult)
//            LOG.debug("һ����ҵ��Ԥ�����ͨ��");
//        else
//            LOG.debug("һ����ҵ��Ԥ����Բ�ͨ��");
        return checkresult;
    }
}
