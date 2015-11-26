package com.gw.dzhyun.forecast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atopcloud.util.FileUtil;
import com.atopcloud.util.MyConfigUtil;
import com.atopcloud.util.MyDatabaseUtil;
import com.atopcloud.util.MyHttpUtil;
import com.atopcloud.util.PropertiesManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static org.junit.Assert.assertTrue;

/**
 * Created by Hihiri on 2015/11/12.
 */
public class ggtzybTest {
//    private static final Log LOG = LogFactory.getLog(ggtzybTest.class);
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
    public static void GlobalInit() {
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
    public void testGgtzyb() throws Exception {
        boolean checkresult = true;
        for (String stockcode : stockcodes) {
            checkresult = checkggtzyb(stockcode);
            if (!checkresult) {
//                LOG.debug("�ڹ�Ʊ����Ϊ" + stockcode + "ʱ���Բ�ͨ��");
                break;
            }
        }
        assertTrue("һ����ҵ��Ԥ�����", checkresult);
    }

    public boolean checkggtzyb(String obj) throws Exception {
//        LOG.debug("checkggtzyb");

        String tokenString = "https://" + yunip + yunport + "/token/access?appid=" + appid + "&secret_key=" + secret_key;
        JSONObject tokenresult = JSON.parseObject(MyHttpUtil.getData(tokenString, "json"));
        String token = tokenresult.getString("token");
        String yunString = "http://" + yunip + yunport + "/forecasts/ggtzyb?obj=" + obj + "&token=" + token;
        JSONObject yunresult = JSON.parseObject(MyHttpUtil.getData(yunString, "json"));

        String querysql = "select t.cv,SUBSTRING_INDEX(t.CO,'.',1) obj,t.c4,t.c7,c.cc4,d.cc5,t.c5,t.C3 \n" +
                "from \n" +
                "(SELECT a.cv,a.co,a.c7,a.c4,a.c5,a.C24,a.C26, b.C3 \n" +
                "FROM dzh_rsr.tRS0001 a \n" +
                "left join dzh_rsr.tRS0003 b \n" +
                "on a.c2=b.c1 \n" +
                "WHERE DSymbol = 'SH600000' and a.C4 > 20150801000000  and a.CV > 0 and c14 IN('10101','10102','10103') AND a.flag <> 1) t \n" +
                "\n" +
                "LEFT JOIN \n" +
                "(SELECT c3, c4 cc4 \n" +
                "FROM dzh_dd.tDD0026 \n" +
                "WHERE c2=1000212813) c \n" +
                "ON t.c24 = c.c3 \n" +
                "\n" +
                "LEFT JOIN \n" +
                "(SELECT c3,c4 cc5 \n" +
                "FROM dzh_dd.tDD0026 \n" +
                "WHERE c2=1000212815) d \n" +
                "ON t.c26=d.c3 \n" +
                "order by CV";
        ResultSet dbresult = MyDatabaseUtil.doQuerySqlS(dbip, dbport, dbuser, dbpassword, "ggtzyb", querysql);
        JSONArray stockdata = new JSONArray();        //ĳֻ��Ʊ���о�����ҵ��Ԥ��
        while (dbresult.next()) {
            JSONObject yanbaoday = new JSONObject();
            yanbaoday.put("baoGaoRiQi", dbresult.getString("C4"));
            yanbaoday.put("yanJiuJiGou", dbresult.getString("C7"));
            yanbaoday.put("pinJiLeiBie", dbresult.getString("CC4"));
            yanbaoday.put("pinJiBianDong", dbresult.getString("CC5"));
            yanbaoday.put("yanBaoBiaoTi", dbresult.getString("C5"));
            String yanbaoneirong = "http://rdfile.gw.com.cn" + dbresult.getString("C3").substring(12);
            yanbaoday.put("yanBaoNeiRong", yanbaoneirong);
            stockdata.add(yanbaoday);
        }
        boolean checkresult =  yunresult.getJSONObject("Data").getJSONArray("RepDataGeGuTouZiYanBaoOutPut").getJSONObject(0).
        getJSONArray("Data").equals(stockdata);

//        if (checkresult)
//            LOG.debug("����Ͷ���б�����ͨ��");
//        else
//            LOG.debug("����Ͷ���б����Բ�ͨ��");
        return checkresult;
    }
}
