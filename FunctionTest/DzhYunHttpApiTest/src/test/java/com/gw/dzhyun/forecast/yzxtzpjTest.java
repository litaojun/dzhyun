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
 * Created by Hihiri on 2015/11/11.
 */
public class yzxtzpjTest {
//    private static final Log LOG = LogFactory.getLog(yzxtzpjTest.class);
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
    public void testYzxtzpj() throws Exception {
        boolean checkresult = true;
        for (String stockcode : stockcodes) {
            checkresult = checkyzxtzpj(stockcode);
            if (!checkresult) {
//                LOG.debug("�ڹ�Ʊ����Ϊ" + stockcode + "ʱ���Բ�ͨ��");
                break;
            }
        }
        assertTrue("һ����Ͷ����������", checkresult);
    }

    public boolean checkyzxtzpj(String obj) throws Exception {
//        LOG.debug("checkyzxtzpj");

        String tokenString = "https://" + yunip + yunport + "/token/access?appid=" + appid + "&secret_key=" + secret_key;
        JSONObject tokenresult = JSON.parseObject(MyHttpUtil.getData(tokenString, "json"));
        String token = tokenresult.getString("token");
        String yunString = "http://" + yunip + yunport + "/forecasts/yzxtzpj?obj=" + obj + "&token=" + token;
        JSONObject yunresult = JSON.parseObject(MyHttpUtil.getData(yunString, "json"));

        String querysql = "select CV, SUBSTRING_INDEX(CO,'.',1) obj, C2, \n" +
                "    case when C8<=1 then '����'     when C8>1 and C8<=2 then '����' \n" +
                "    when C8>2 and C8<=3 then '����' when C8>3 and C8<=4 then '����' \n" +
                "    when C8>4 and C8<=5 then '����' \n" +
                "    End AS '��������' \n" +
                "from dzh_rsr.tRS0010 \n" +
                "where DSymbol = 'SH600000' and C2 > 20150801000000 and CV > 0 and C5 = 3 and flag <> 1 \n" +
                "order by CV";
        ResultSet dbresult = MyDatabaseUtil.doQuerySqlS(dbip, dbport, dbuser, dbpassword,"dzh_rsr", querysql);
        JSONArray stockdata = new JSONArray();
        long lastest = 0;
        long now = 0;
        while (dbresult.next()) {
            long C2 = Long.valueOf(dbresult.getString("C2"));
            if (C2 >= lastest) {                                              //�������ڸ���
                JSONObject period = new JSONObject();
                period.put("pinJiRiQi", dbresult.getString("C2"));
                period.put("zhengTiPinJi", dbresult.getString("��������"));
                if (C2 >= now) {
                    lastest = now;
                    now = C2;
                    if (stockdata.size() == 2)                              //ԭ������������
                        stockdata.remove(0);
                    stockdata.add(period);
                } else {
                    lastest = C2;
                    stockdata.set(0, period);
                }
            }
        }
        boolean checkresult = yunresult.getJSONObject("Data").getJSONArray("RepDataYiZhiXinTouZiPinJiOutPut").getJSONObject(0).
                getJSONArray("Data").equals(stockdata);

//        if (checkresult)
//            LOG.debug("һ����Ͷ����������ͨ��");
//        else
//            LOG.debug("һ����Ͷ���������Բ�ͨ��");
        return checkresult;
    }
}
