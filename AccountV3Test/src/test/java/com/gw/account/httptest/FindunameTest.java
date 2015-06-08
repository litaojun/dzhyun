package com.gw.account.httptest;

import com.atopcloud.util.MyCurrentTime;
import com.atopcloud.util.MyRedisUtil;
import com.atopcloud.util.MyUname;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//

public class FindunameTest {

    //下面的变量设置缺省值
    private String method = "findunamebykey";            //=findunamebykey
    private String appid;             //10 字节否默认不填，除非明确告知需填入分配的appid
    private String sql;
    private String rediskey;
    private String curtimeuname = MyCurrentTime.MyTime(); //使每次传递用户名不重复 ，不用初始数据库
    private String keytpval = "&keytp=";
    private String keyval = "&key=";

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    //正常参数验证
    @Test
    //Case1.1.1:必填字段全部正确书写请求（mobile）
    public void testMustParamsMobile() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "13673991650";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.1.1:必填字段全部正确书写请求（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
        //校验redis中的uid对应的用户名是否正确
        String uname = MyUname.Uname(accresult);
        MyRedisUtil myredis = new MyRedisUtil();
        String myredisuname = myredis.getValue("k_2:" + key + "");
        assertEquals(myredisuname, uname);
    }

    @Test
    //Case1.1.2:必填字段全部正确书写请求（email）
    public void testMustParamsEmail() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "email";
        String key = "renguohua@gw.com.cn";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.1.2:必填字段全部正确书写请求（email）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
        //校验redis中的uid对应的用户名是否正确
        String uname = MyUname.Uname(accresult);
        MyRedisUtil myredis = new MyRedisUtil();
        String myredisuname = myredis.getValue("k_1:" + key + "");
        assertEquals(myredisuname, uname);
    }

    @Test
    //Case1.1.3:必填字段全部正确书写请求（idcard）
    public void testMustParamsIdcard() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "412724198412072233";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.1.3:必填字段全部正确书写请求（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
        //校验redis中的uid对应的用户名是否正确
        String uname = MyUname.Uname(accresult);
        MyRedisUtil myredis = new MyRedisUtil();
        String myredisuname = myredis.getValue("k_10:" + key + "");
        assertEquals(myredisuname, uname);
    }

    @Test
    //Case1.1.3.1:必填字段全部正确书写请求（idcard）
    public void testMustParamsIdcard31() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "41272419841207251X";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.1.3.1:必填字段全部正确书写请求（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
        //校验redis中的uid对应的用户名是否正确
        String uname = MyUname.Uname(accresult);
        MyRedisUtil myredis = new MyRedisUtil();
        String myredisuname = myredis.getValue("k_10:" + key + "");
        assertEquals(myredisuname, uname);
    }

    @Test
    //Case1.1.3.2:必填字段全部正确书写请求（idcard）
    public void testMustParamsIdcard32() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "41272445698745X";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.1.3.2:必填字段全部正确书写请求（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
        //校验redis中的uid对应的用户名是否正确
        String uname = MyUname.Uname(accresult);
        MyRedisUtil myredis = new MyRedisUtil();
        String myredisuname = myredis.getValue("k_10:" + key + "");
        assertEquals(myredisuname, uname);
    }

    @Test
    //Case1.1.3.3:必填字段全部正确书写请求（idcard）
    public void testMustParamsIdcard33() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "41272419841207251x";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.1.3.3:必填字段全部正确书写请求（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
        //校验redis中的uid对应的用户名是否正确
        //String uname = MyUname.Uname(accresult);
        //MyRedisUtil myredis = new MyRedisUtil();
        //String myredisuname = myredis.getValue("k_10:"+key+"");
        //assertEquals(myredisuname,uname);
    }

    @Test
    //Case1.1.3.4:必填字段全部正确书写请求（idcard）
    public void testMustParamsIdcard34() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "41272445698745x";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.1.3.4:必填字段全部正确书写请求（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
        //校验redis中的uid对应的用户名是否正确
        //String uname = MyUname.Uname(accresult);
        //MyRedisUtil myredis = new MyRedisUtil();
        //String myredisuname = myredis.getValue("k_10:"+key+"");
        //assertEquals(myredisuname,uname);
    }

    @Test
    //Case1.1.4:必填字段全部正确书写请求（qqid）
    public void testMustParamsQqid() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "qqid";
        String key = "564911127";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.1.4:必填字段全部正确书写请求（qqid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
        //校验redis中的uid对应的用户名是否正确
        String uname = MyUname.Uname(accresult);
        MyRedisUtil myredis = new MyRedisUtil();
        String myredisuname = myredis.getValue("k_13:" + key + "");
        assertEquals(myredisuname, uname);
    }

    @Test
    //Case1.1.5:必填字段全部正确书写请求（wxid）
    public void testMustParamsWxid() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "wxid";
        String key = "rain_xcy";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.1.5:必填字段全部正确书写请求（wxid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
        //校验redis中的uid对应的用户名是否正确
        String uname = MyUname.Uname(accresult);
        MyRedisUtil myredis = new MyRedisUtil();
        String myredisuname = myredis.getValue("k_22:" + key + "");
        assertEquals(myredisuname, uname);
    }

    @Test
    //Case1.1.6:必填字段全部正确书写请求（xcid）
    public void testMustParamsXcid() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "xcid";
        String key = "6543210987";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.1.6:必填字段全部正确书写请求（xcid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
        //校验redis中的uid对应的用户名是否正确
        String uname = MyUname.Uname(accresult);
        MyRedisUtil myredis = new MyRedisUtil();
        String myredisuname = myredis.getValue("k_27:" + key + "");
        assertEquals(myredisuname, uname);
    }

    // method参数大小写敏感
    @Test
    //Case1.2.1:必填字段全部正确书写请求（mobile）
    public void testMustParamsmethodMobile() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "13673991650";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.2.1:必填字段全部正确书写请求（mobile）=======");
        String accresult = AccInterface.testFindunamebykeyD(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
    }

    @Test
    //Case1.2.2:必填字段全部正确书写请求（email）
    public void testMustParamsmethodEmail() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "email";
        String key = "renguohua@gw.com.cn";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.2.2:必填字段全部正确书写请求（email）=======");
        String accresult = AccInterface.testFindunamebykeyD(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
    }

    @Test
    //Case1.2.3:必填字段全部正确书写请求（idcard）
    public void testMustParamsmethodIdcard() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "412724198412072233";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.2.3:必填字段全部正确书写请求（idcard）=======");
        String accresult = AccInterface.testFindunamebykeyD(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
    }

    @Test
    //Case1.2.4:必填字段全部正确书写请求（qqid）
    public void testMustParamsmethodQqid() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "qqid";
        String key = "564911127";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.2.4:必填字段全部正确书写请求（qqid）=======");
        String accresult = AccInterface.testFindunamebykeyD(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
    }

    @Test
    //Case1.2.5:必填字段全部正确书写请求（wxid）
    public void testMustParamsmethodWxid() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "wxid";
        String key = "rain_xcy";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.2.5:必填字段全部正确书写请求（wxid）=======");
        String accresult = AccInterface.testFindunamebykeyD(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
    }

    @Test
    //Case1.2.6:必填字段全部正确书写请求（xcid）
    public void testMustParamsmethodXcid() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "xcid";
        String key = "6543210987";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.2.6:必填字段全部正确书写请求（xcid）=======");
        String accresult = AccInterface.testFindunamebykeyD(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
    }

    //key参数大小写敏感
    @Test
    //Case1.3.1:必填字段全部正确书写请求（mobile）
    public void testMustParamsMobileKey() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "MOBILE";
        String key = "13673991651";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.3.1:必填字段全部正确书写请求（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=113"));
    }

    @Test
    //Case1.3.2:必填字段全部正确书写请求（email）
    public void testMustParamsEmailKey() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "EMAIL";
        String key = "renguohua@gw.com.cn";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.3.2:必填字段全部正确书写请求（email）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=113"));
    }

    @Test
    //Case1.3.3:必填字段全部正确书写请求（idcard）
    public void testMustParamsIdcardKey() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "IDCARD";
        String key = "412724198412072233";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.3.3:必填字段全部正确书写请求（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=113"));
    }

    @Test
    //Case1.3.4:必填字段全部正确书写请求（qqid）
    public void testMustParamsQqidKey() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "QQID";
        String key = "564911127";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.3.4:必填字段全部正确书写请求（qqid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=113"));
    }

    @Test
    //Case1.3.5:必填字段全部正确书写请求（wxid）
    public void testMustParamsWxidKey() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "WXID";
        String key = "rain_xcy";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.3.5:必填字段全部正确书写请求（wxid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=113"));
    }

    @Test
    //Case1.3.6:必填字段全部正确书写请求（xcid）
    public void testMustParamsXcidKey() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "XCID";
        String key = "6543210987";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case1.3.6:必填字段全部正确书写请求（xcid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=113"));
        ;
    }

    //keytp参数为空验证
    @Test
    //Case2.1.1:keytp为空（mobile）
    public void testMustParamsMobilenull() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = " ";
        String key = "13673991651";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.1.1:keytp为空（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=113"));
    }

    @Test
    //Case2.1.2:keytp为空（email）
    public void testMustParamsEmailnull() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = " ";
        String key = "renguohua@gw.com.cn";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.1.2:keytp为空（email）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=113"));
    }

    @Test
    //Case2.1.3:keytp为空（idcard）
    public void testMustParamsIdcardnull() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = " ";
        String key = "412724198412072233";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.1.3:keytp为空（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=113"));
    }

    @Test
    //Case2.1.4:keytp为空（qqid）
    public void testMustParamsQqidnull() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = " ";
        String key = "564911127";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.1.4:keytp为空（qqid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=113"));
    }

    @Test
    //Case2.1.5:keytp为空（wxid）
    public void testMustParamsWxidnull() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = " ";
        String key = "rain_xcy";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.1.5:keytp为空（wxid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=113"));
    }

    @Test
    //Case2.1.6:keytp为空（xcid）
    public void testMustParamsXcidnull() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = " ";
        String key = "6543210987";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.1.6:keytp为空（xcid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=113"));
    }

    //key参数为空验证
    @Test
    //Case2.2.1:key为空（mobile）
    public void testMustParamsMobileNULL() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.2.1:key为空（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=101"));
    }

    @Test
    //Case2.2.2:key为空（email）
    public void testMustParamsEmailNULL() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "email";
        String key = "";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.2.2:key为空（email）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=101"));
    }

    @Test
    //Case2.2.3:key为空（idcard）
    public void testMustParamsIdcardNULL() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.2.3:key为空（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=101"));
    }

    @Test
    //Case2.2.4:key为空（qqid）
    public void testMustParamsQqidNULL() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "qqid";
        String key = "";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.2.4:key为空（qqid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=101"));
    }

    @Test
    //Case2.2.5:key为空（wxid）
    public void testMustParamsWxidNULL() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "wxid";
        String key = "";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.2.5:key为空（wxid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=101"));
    }

    @Test
    //Case2.2.6:key为空（xcid）
    public void testMustParamsXcidNULL() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "xcid";
        String key = "";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.2.6:key为空（xcid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=101"));
    }

    //keytp参数 异常
    @Test
    //Case2.3.1:keytp参数包含空格（mobile）
    public void testMustParamsnullMobile() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = " mobile";
        String key = "13673991651";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.3.1:keytp参数包含空格（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=113"));
    }

    @Test
    //Case2.3.2:keytp参数包含空格（email）
    public void testMustParamsnullEmail() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "email ";
        String key = "renguohua@gw.com.cn";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.3.2:keytp参数包含空格（email）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=113"));
    }

    @Test
    //Case2.3.3:keytp参数包含空格（idcard）
    public void testMustParamsnullIdcard() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "id card";
        String key = "412724198412072233";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.3.3:keytp参数包含空格（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=113"));
    }

    @Test
    //Case2.3.4:keytp参数（qqid）
    public void testMustParamsnullQqid() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "ｑｑｉｄ";
        String key = "564911127";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.3.4:keytp参数包含空格（qqid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=113"));
    }

    @Test
    //Case2.3.5:keytp参数包含空格（wxid）
    public void testMustParamsnullWxid() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "wxdi";
        String key = "rain_xcy";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.3.5:keytp参数包含空格（wxid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=113"));
    }

    @Test
    //Case2.3.6:keytp参数长度溢出（xcid）
    public void testMustParamsnullXcid() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "xcidCase2.3.1:keytp参数包含空格Case2.3.1:keytp参数包含空格Case2.3.1:keytp参数包含空格Case2.3.1:keytp参数包含空格Case2.3.1:keytp参数包含空格Case2.3.1:keytp参数包含空格";
        String key = "6543210987";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.3.6:keytp参数包含空格（xcid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=113"));
    }

    @Test
    //Case2.3.7:keytp参数包含特殊字符（mobile）
    public void testMustParamsnulMobile() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mob~!@#$%^&*()<>ile";
        String key = "13673991650";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case2.3.7:keytp参数包含空格（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=101"));
    }
//参数逻辑约束及边界值验证

    @Test
    //Case3.1.1:参数逻辑及边界验证（email）
    public void testMustParamsEmail1() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "email";
        String key = "renguohuagw.com.cn";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.1.1:参数逻辑及边界验证（email）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.1.2:参数逻辑及边界验证（email）
    public void testMustParamsEmail2() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "email";
        String key = "@gw.com.cn";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.1.2:参数逻辑及边界验证（email）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.1.3:参数逻辑及边界验证（email）
    public void testMustParamsEmail3() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "email";
        String key = "renguohua@";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.1.3:参数逻辑及边界验证（email）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.1.4:参数逻辑及边界验证（email）
    public void testMustParamsEmail4() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "email";
        String key = "renguohua@.gwcom.cn";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.1.4:参数逻辑及边界验证（email））=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.1.5:参数逻辑及边界验证（email）
    public void testMustParamsEmail5() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "email";
        String key = "renguohua@gw.com.";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.1.5:参数逻辑及边界验证（email））=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.1.6:参数逻辑及边界验证（email）
    public void testMustParamsEmail6() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "email";
        String key = "renguohua@gw.com.ｃｎ";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.1.6:参数逻辑及边界验证（email））=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.1.7:参数逻辑及边界验证（email）
    public void testMustParamsEmail7() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "email";
        String key = "renguohua~!@#$%^&*()@gw.com.cn";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.1.7:参数逻辑及边界验证（email））=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.1.8:参数逻辑及边界验证（email）
    public void testMustParamsEmail8() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "email";
        String key = "任国华123~!@#$%^&*()@gw.com.cn";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.1.8:参数逻辑及边界验证（email））=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.1.9:参数逻辑及边界验证（email）
    public void testMustParamsEmail9() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "email";
        String key = "renguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohuarenguohua@gw.com.cn";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.1.9:参数逻辑及边界验证（email））=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.1.10:参数逻辑及边界验证（email）
    public void testMustParamsEmail10() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "email";
        String key = "renguOhua1@gw.com.cN";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.1.10:参数逻辑及边界验证（email））=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.1:参数逻辑及边界值（mobile）
    public void testMustParamsMobile1() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "abcdefg";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.1:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.2:参数逻辑及边界值（mobile）
    public void testMustParamsMobile2() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "任国华";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.2:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.3:参数逻辑及边界值（mobile）
    public void testMustParamsMobile3() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "abcdefgqwertyuiopasdfghjklzxcvbnmabcdefgqwertyuiopasdfghjklzxcvbnmabcdefgqwertyuiopasdfghjklzxcvbnmabcdefgqwertyuiopasdfghjklzxcvbnmabcdefgqwertyuiopasdfghjklzxcvbnmabcdefgqwertyuiopasdfghjklzxcvbnmabcdefgqwertyuiopasdfghjklzxcvbnm";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.3:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.4:参数逻辑及边界值（mobile）
    public void testMustParamsMobile4() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "~!@#$%^&*()_+<>{}|";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.4:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.5:参数逻辑及边界值（mobile）
    public void testMustParamsMobile5() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "1";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.5:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.6:参数逻辑及边界值（mobile）
    public void testMustParamsMobile6() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "1367399165";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.6:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.7:参数逻辑及边界值（mobile）
    public void testMustParamsMobile7() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "136739916512";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.7:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.8:参数逻辑及边界值（mobile）
    public void testMustParamsMobile8() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "13673991651234";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.8:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.9:参数逻辑及边界值（mobile）
    public void testMustParamsMobile9() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "136739916512345";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.9:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.10:参数逻辑及边界值（mobile）
    public void testMustParamsMobile10() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "1367399165123456";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.10:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.11:参数逻辑及边界值（mobile）
    public void testMustParamsMobile11() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "23673991651";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.11:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.12:参数逻辑及边界值（mobile）
    public void testMustParamsMobile12() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = " 13673991651";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.12:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.13:参数逻辑及边界值（mobile）
    public void testMustParamsMobile13() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "1367399 1651";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.13:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.14:参数逻辑及边界值（mobile）
    public void testMustParamsMobile14() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "13673991651 ";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.14:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.15:参数逻辑及边界值（mobile）
    public void testMustParamsMobile15() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "1367399165X";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.15:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.16:参数逻辑及边界值（mobile）
    public void testMustParamsMobile16() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "136739916X1";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.16:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.17:参数逻辑及边界值（mobile）
    public void testMustParamsMobile17() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "136739916贰1";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.17:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.18:参数逻辑及边界值（mobile）
    public void testMustParamsMobile18() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "13673991６５０";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.18:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.2.19:参数逻辑及边界值（mobile）
    public void testMustParamsMobile19() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "mobile";
        String key = "13673991659";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.2.19:参数逻辑及边界值（mobile）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.3.1:参数逻辑及边界值（idcard）
    public void testMustParamsIdcard1() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "4127";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.3.1:参数逻辑及边界值（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.3.2:参数逻辑及边界值（idcard）
    public void testMustParamsIdcard2() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "41275678910234";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.3.2:参数逻辑及边界值（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.3.3:参数逻辑及边界值（idcard）
    public void testMustParamsIdcard3() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "4127567891023456";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.3.3:参数逻辑及边界值（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.3.4:参数逻辑及边界值（idcard）
    public void testMustParamsIdcard4() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "412756789102345678";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.3.4:参数逻辑及边界值（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.3.5:参数逻辑及边界值（idcard）
    public void testMustParamsIdcard5() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "412756789102345678412756789102345678412756789102345678412756789102345678412756789102345678412756789102345678";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.3.5:参数逻辑及边界值（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.3.6:参数逻辑及边界值（idcard）
    public void testMustParamsIdcard6() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "41272445698745N";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.3.6:参数逻辑及边界值（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.3.7:参数逻辑及边界值（idcard）
    public void testMustParamsIdcard7() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "41272419841207251n";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.3.7:参数逻辑及边界值（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.3.8:参数逻辑及边界值（idcard）
    public void testMustParamsIdcard8() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = " 41272445698745X";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.3.8:参数逻辑及边界值（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.3.9:参数逻辑及边界值（idcard）
    public void testMustParamsIdcard9() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "412724456 98745X";
        String params = keytpval + keytp + keyval + key;                        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.3.9:参数逻辑及边界值（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.3.10:参数逻辑及边界值（idcard）
    public void testMustParamsIdcard10() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "41272445698745X ";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.3.10:参数逻辑及边界值（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
    }

    @Test
    //Case3.3.11:参数逻辑及边界值（idcard）
    public void testMustParamsIdcard11() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "41272419841207251x ";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.3.11:参数逻辑及边界值（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.3.12:参数逻辑及边界值（idcard）
    public void testMustParamsIdcard12() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "4127241984120725 1x";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.3.12:参数逻辑及边界值（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.3.13:参数逻辑及边界值（idcard）
    public void testMustParamsIdcard13() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = " 41272419841207251x";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.3.13:参数逻辑及边界值（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.3.14:参数逻辑及边界值（idcard）
    public void testMustParamsIdcard14() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "~!@#$%^&*()_+<>";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.3.14:参数逻辑及边界值（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.3.15:参数逻辑及边界值（idcard）
    public void testMustParamsIdcard15() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "~!@#$%^&*()_+<>{}|";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.3.15:参数逻辑及边界值（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.3.16:参数逻辑及边界值（idcard）
    public void testMustParamsIdcard16() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "４１２７２４１９８４１２０７２２３３";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.3.16:参数逻辑及边界值（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.3.17:参数逻辑及边界值（idcard）
    public void testMustParamsIdcard17() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "idcard";
        String key = "４１２７２４４５６９８７４５ｘ";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.3.17:参数逻辑及边界值（idcard）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.4.1:参数逻辑及边界值（wxid）
    public void testMustParamsWxid1() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "wxid";
        String key = "ｒａｉｎ＿ｘｃｙ";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.4.1:参数逻辑及边界值（wxid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.4.2:参数逻辑及边界值（wxid）
    public void testMustParamsWxid2() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "wxid";
        String key = "rain_xcy ";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.4.2:参数逻辑及边界值（wxid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
    }

    @Test
    //Case3.4.3:参数逻辑及边界值（wxid）
    public void testMustParamsWxid3() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "wxid";
        String key = "rai n_xcy";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.4.3:参数逻辑及边界值（wxid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.4.4:参数逻辑及边界值（wxid）
    public void testMustParamsWxid4() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "wxid";
        String key = " rain_xcy";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.4.4:参数逻辑及边界值（wxid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.4.5:参数逻辑及边界值（wxid）
    public void testMustParamsWxid5() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "wxid";
        String key = "~!@#$%^&*()_+<>{}|";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.4.5:参数逻辑及边界值（wxid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.4.6:参数逻辑及边界值（wxid）
    public void testMustParamsWxid6() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "wxid";
        String key = "testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.4.6:参数逻辑及边界值（wxid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.4.7:参数逻辑及边界值（wxid）
    public void testMustParamsWxid7() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "wxid";
        String key = "RAin_xcy";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.4.7:参数逻辑及边界值（wxid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.5.1:参数逻辑及边界值（xcid）
    public void testMustParamsXcid1() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "xcid";
        String key = "testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6testMustParamsWxid6";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.5.1:参数逻辑及边界值（xcid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.5.2:参数逻辑及边界值（xcid）
    public void testMustParamsXcid2() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "xcid";
        String key = " 6543210987";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.5.2:参数逻辑及边界值（xcid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.5.3:参数逻辑及边界值（xcid）
    public void testMustParamsXcid3() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "xcid";
        String key = "6 543210987";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.5.3:参数逻辑及边界值（xcid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.5.4:参数逻辑及边界值（xcid）
    public void testMustParamsXcid4() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "xcid";
        String key = "6543210987 ";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.5.4:参数逻辑及边界值（xcid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=0"));
    }

    @Test
    //Case3.5.5:参数逻辑及边界值（xcid）
    public void testMustParamsXcid5() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "xcid";
        String key = "６５４３２１０９８７";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.5.5:参数逻辑及边界值（xcid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

    @Test
    //Case3.5.6:参数逻辑及边界值（xcid）
    public void testMustParamsXcid6() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        String keytp = "xcid";
        String key = "~!@#$%^&*()_+{}|<>?";
        String params = keytpval + keytp + keyval + key;
        //请求输入
        Thread.sleep(1001);
        System.out.println("======Case3.5.6:参数逻辑及边界值（xcid）=======");
        String accresult = AccInterface.testFindunamebykey(params);
        //校验返回值
        assertTrue("True", accresult.contains("result=2"));
    }

}
