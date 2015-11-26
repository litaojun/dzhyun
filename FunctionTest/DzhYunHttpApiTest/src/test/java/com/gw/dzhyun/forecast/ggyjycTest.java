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
public class ggyjycTest {
//    private static final Log LOG = LogFactory.getLog(ggyjycTest.class);
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
    public void testGgyjyc() throws Exception {
        boolean checkresult = true;
        for (String stockcode : stockcodes) {
            checkresult = checkggyjyc(stockcode);
            if (!checkresult) {
//                LOG.debug("�ڹ�Ʊ����Ϊ" + stockcode + "ʱ���Բ�ͨ��");
                break;
            }
        }
        assertTrue("һ����ҵ��Ԥ�����", checkresult);
    }

    public boolean checkggyjyc(String obj) throws Exception {
//        LOG.debug("checkggyjyc");

        String tokenString = "https://" + yunip + yunport + "/token/access?appid=" + appid + "&secret_key=" + secret_key;
        JSONObject tokenresult = JSON.parseObject(MyHttpUtil.getData(tokenString, "json"));
        String token = tokenresult.getString("token");
        String yunString = "http://" + yunip + yunport + "/forecasts/ggyjyc?obj=" + obj + "&token=" + token;
        JSONObject yunresult = JSON.parseObject(MyHttpUtil.getData(yunString, "json"));

        String querysql = "select CV,SUBSTRING_INDEX(CO,'.',1) obj,C40,C10,C12, C22 \n" +
                "from \n" +
                "(SELECT CV,CO,C40,C11,C10,C12,c22 \n" +
                "FROM dzh_rsr.tRS0002 \n" +
                "where DSymbol = 'SH600000' and C10 > 20150801000000 and CV > 0\n" +
                "order by C10 desc) b \n" +
                "group by CO,C11,C12 \n" +
                "order by CV";
        ResultSet dbresult = MyDatabaseUtil.doQuerySqlS(dbip, dbport, dbuser, dbpassword,"dzh_rsr", querysql);
        JSONArray stockdata = new JSONArray();        //ĳֻ��Ʊ���о�����ҵ��Ԥ��
        while (dbresult.next()) {
            String yanJiuJiGou = dbresult.getString("C40");
            String baoGaoRiQi = dbresult.getString("C10");
            boolean flag = false;         //�����ڴ��о���ĸ���ҵ��Ԥ��
            for (int i=0; i < stockdata.size(); i++) {
                if (stockdata.getJSONObject(i).get("yanJiuJiGou").equals(yanJiuJiGou) &&       //�����о�����ʱ��Ͼ�
                        Long.valueOf(stockdata.getJSONObject(i).getString("baoGaoRiQi")) <
                        Long.valueOf(baoGaoRiQi)) {
                    stockdata.getJSONObject(i).put("baoGaoRiQi",baoGaoRiQi);                   //����Ϊ��ʱ��
                    JSONObject traderdatayear = new JSONObject();                              //�о���ĸ���ҵ��Ԥ��
                    traderdatayear.put("yuCeNianDu", dbresult.getString("C12"));
                    traderdatayear.put("meiGuShouYi", dbresult.getString("C22"));
                    stockdata.getJSONObject(i).getJSONArray("data").clear();
                    stockdata.getJSONObject(i).getJSONArray("data").add(traderdatayear);        //����Ϊ�¸���ҵ��Ԥ��
                    flag = true;
                } else if (stockdata.getJSONObject(i).get("yanJiuJiGou").equals(yanJiuJiGou) && //�����о�����ʱ�����
                        Long.valueOf(stockdata.getJSONObject(i).getString("baoGaoRiQi")) ==
                                Long.valueOf(baoGaoRiQi)) {
                    JSONObject traderdatayear = new JSONObject();                               //�о���ĸ���ҵ��Ԥ��
                    traderdatayear.put("yuCeNianDu", dbresult.getString("C12"));
                    traderdatayear.put("meiGuShouYi", dbresult.getString("C22"));
                    stockdata.getJSONObject(i).getJSONArray("data").add(traderdatayear);
                    flag = true;
                } else if (stockdata.getJSONObject(i).get("yanJiuJiGou").equals(yanJiuJiGou) && //�����о�����ʱ�����
                        Long.valueOf(stockdata.getJSONObject(i).getString("baoGaoRiQi")) >
                                Long.valueOf(baoGaoRiQi)) {
                    flag = true;
                }
            }
            if (!flag) {
                JSONObject trader = new JSONObject();                     //�о���
                trader.put("yanJiuJiGou",yanJiuJiGou);                    //�о������
                trader.put("baoGaoRiQi",baoGaoRiQi);                      //ȡ���¸���ҵ��Ԥ������
                JSONArray traderdata = new JSONArray();                   //�о���ĸ���ҵ��Ԥ���б�
                JSONObject traderdatayear = new JSONObject();             //�о���ĸ���ҵ��Ԥ��
                traderdatayear.put("yuCeNianDu", yanJiuJiGou);
                traderdatayear.put("meiGuShouYi", baoGaoRiQi);
                traderdata.add(traderdatayear);
                trader.put("data",traderdata);
            }
        }
        boolean checkresult = yunresult.getJSONObject("Data").getJSONArray("RepDataGeGuYeJiYuCeOutPut").getJSONObject(0).
                getJSONArray("data").equals(stockdata);

//        if (checkresult)
//            LOG.debug("����ҵ��Ԥ�����ͨ��");
//        else
//            LOG.debug("����ҵ��Ԥ����Բ�ͨ��");
        return checkresult;
    }
}
