package com.gw.account.httptest;

import com.atopcloud.util.MyCheckBdb;
import com.atopcloud.util.MyCurrentTime;
import com.atopcloud.util.MyRedisUtil;
import com.atopcloud.util.MyUid;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//

public class AdduserexTest {

    Logger log = Logger.getLogger(UpdpassTest.class);
    //下面的变量设置缺省值
    private String method = "adduser";            //=adduser
    private String uname = "lidbv3";              //string 50 字节是不少于4 个字节的中英文字符（非法字符定义详细↗ ）
    private String upass = "123456";              //string;50 字节是密码明文或MD5 加密串（HTTP 接口需填为MD5 加密串）
    private String mobile = "20150212173701";        //string;15 字节否验证过的手机号
    private String email = "1918880552@qq.com";   //string;50 字节否验证过的邮箱
    private String vname = "Hello是你好";           // string;50 字节否昵称（nickname）
    private String appid = "test001";             //10 字节否默认不填，除非明确告知需填入分配的appid
    private String sql;
    private String rediskey;
    private String curtimeuname = MyCurrentTime.MyTime(); //使每次传递用户名不重复 ，不用初始数据库

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    //Case1:必填字段全部正确书写请求（gen=seq）
    public void testMustParamsseq() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case1:必填字段全部正确书写请求（gen=seq）=======");
        String accresult = AccInterface.testAdduserex("&gen=seq");
        assertTrue("True", accresult.contains("result=0"));
        String Myuid = MyUid.Uidex(accresult);
        String uname = MyUid.Una(accresult);
        MyRedisUtil myredis = new MyRedisUtil();
        String myredisuid = myredis.getValue("uid:" + Myuid + "");
        //校验redis中的uid对应的用户名是否正确
        assertEquals(myredisuid, uname);
        boolean ret = MyCheckBdb.CheckBdb(uname, "uid:" + Myuid + "", "u:" + uname + "", "", "", "", "");
        assertTrue(ret);

    }

    @Test
    //Case2:必填字段全部正确书写请求（gen=rand）
    public void testMustParamsrand() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case2:必填字段全部正确书写请求（gen=rand）=======");
        String accresult = AccInterface.testAdduserex("&gen=rand");
        assertTrue("True", accresult.contains("result=0"));
        String Myuid = MyUid.Uidex(accresult);
        String uname = MyUid.Una(accresult);
        MyRedisUtil myredis = new MyRedisUtil();
        String myredisuid = myredis.getValue("uid:" + Myuid + "");
        //校验redis中的uid对应的用户名是否正确
        assertEquals(myredisuid, uname);
        boolean ret = MyCheckBdb.CheckBdb(uname, "uid:" + Myuid + "", "u:" + uname + "", "", "", "", "");
        assertTrue(ret);

    }


    @Test
    //Case3:非必填字段全部正确书写请求
    public void testAllparams() throws IOException, SAXException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case3:非必填字段全部正确书写请求=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String mobile = MyUid.Monbile(curtimeuname);
        String accresult = AccInterface.testAdduserex("&prefix=lidb&gen=seq&upass=" + upass + "&keytp=mobile&key=" + mobile + "");
        assertTrue("True", accresult.contains("result=0"));
        String Myuid = MyUid.Uidex(accresult);
        String uname = MyUid.Una(accresult);
        MyRedisUtil myredis = new MyRedisUtil();
        String myredisuid = myredis.getValue("uid:" + Myuid + "");
        //校验redis中的uid对应的用户名是否正确
        assertEquals(myredisuid, uname);
        //检验DBD中所存信息是否正确
        boolean ret = MyCheckBdb.CheckBdb(uname, "uid:" + Myuid + "", "u:" + uname + "", "", mobile, "", "");
        assertTrue(ret);

    }

    @Test
    //Case4:有前缀prefix，gen为seq时
    public void testPrefixseq() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case3:有前缀prefix，gen为seq时=======");
        String accresult = AccInterface.testAdduserex("&prefix=lidb&gen=seq");
        assertTrue("True", accresult.contains("result=0"));
        String Myuid = MyUid.Uidex(accresult);
        String uname = MyUid.Una(accresult);
        MyRedisUtil myredis = new MyRedisUtil();
        String myredisuid = myredis.getValue("uid:" + Myuid + "");
        //校验redis中的uid对应的用户名是否正确
        assertEquals(myredisuid, uname);
        boolean ret = MyCheckBdb.CheckBdb(uname, "uid:" + Myuid + "", "u:" + uname + "", "", "", "", "");
        assertTrue(ret);

    }

    @Test
    //Case5:有前缀prefix，gen为rand时
    public void testPrefixrand() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case5:有前缀prefix，gen为rand时=======");
        String accresult = AccInterface.testAdduserex("&prefix=lidb&gen=rand");
        assertTrue("True", accresult.contains("result=0"));
        String Myuid = MyUid.Uidex(accresult);
        String uname = MyUid.Una(accresult);
        MyRedisUtil myredis = new MyRedisUtil();
        String myredisuid = myredis.getValue("uid:" + Myuid + "");
        //校验redis中的uid对应的用户名是否正确
        assertEquals(myredisuid, uname);
        boolean ret = MyCheckBdb.CheckBdb(uname, "uid:" + Myuid + "", "u:" + uname + "", "", "", "", "");
        assertTrue(ret);

    }

    @Test
    //Case6:缺失gen字段时
    public void testDefectgen() throws IOException, SAXException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case6:缺失gen字段时=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String mobile = MyUid.Monbile(curtimeuname);
        String accresult = AccInterface.testAdduserex("&prefix=lidb&upass=" + upass + "&keytp=mobile&key=" + mobile + "");
        assertTrue("True", accresult.contains("result=101"));

    }

    @Test
    //Case7:缺失keytp字段时
    public void testDefectkeytp() throws IOException, SAXException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case7:缺失keytp字段时=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String mobile = MyUid.Monbile(curtimeuname);
        String accresult = AccInterface.testAdduserex("&prefix=lidb&upass=" + upass + "&gen=seq&key=" + mobile + "");
        assertTrue("True", accresult.contains("result=101"));

    }

    @Test
    //Case8:缺失key字段时
    public void testDefectkey() throws IOException, SAXException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case8:缺失key字段时=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String mobile = MyUid.Monbile(curtimeuname);
        String accresult = AccInterface.testAdduserex("&prefix=lidb&upass=" + upass + "&gen=seq&keytp=mobile");
        assertTrue("True", accresult.contains("result=101"));

    }

    @Test
    //Case9:缺失upass字段时
    public void testDefectupassy() throws IOException, SAXException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case9:缺失upass字段时=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String mobile = MyUid.Monbile(curtimeuname);
        String accresult = AccInterface.testAdduserex("&prefix=lidb&gen=seq&keytp=mobile&key=" + mobile + "");
        assertTrue("True", accresult.contains("result=0"));
        String Myuid = MyUid.Uidex(accresult);
        String uname = MyUid.Una(accresult);
        MyRedisUtil myredis = new MyRedisUtil();
        String myredisuid = myredis.getValue("uid:" + Myuid + "");
        //校验redis中的uid对应的用户名是否正确
        assertEquals(myredisuid, uname);
        //检验DBD中所存信息是否正确
        boolean ret = MyCheckBdb.CheckBdb(uname, "uid:" + Myuid + "", "u:" + uname + "", "", mobile, "", "");
        assertTrue(ret);

    }

    @Test
    //Case10:前缀prefix为20字节时
    public void testPrefix20char() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case11:前缀prefix为20字节时=======");
        String accresult = AccInterface.testAdduserex("&prefix=lidbllllllllllllllll&gen=seq");
        assertTrue("True", accresult.contains("result=0"));
        String Myuid = MyUid.Uidex(accresult);
        String uname = MyUid.Una(accresult);
        MyRedisUtil myredis = new MyRedisUtil();
        String myredisuid = myredis.getValue("uid:" + Myuid + "");
        //校验redis中的uid对应的用户名是否正确
        assertEquals(myredisuid, uname);
        boolean ret = MyCheckBdb.CheckBdb(uname, "uid:" + Myuid + "", "u:" + uname + "", "", "", "", "");
        assertTrue(ret);

    }

    @Test
    //Case11:前缀prefix为21字节时
    public void testPrefix21char() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case11:前缀prefix为21字节时=======");
        String accresult = AccInterface.testAdduserex("&prefix=lidblllllllllllllllll&gen=seq");
        assertTrue("True", accresult.contains("result=101"));
    }

    @Test
    //Case12:密码字节数为50时
    public void testupass50char() throws IOException, SAXException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case3:非必填字段全部正确书写请求=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String mobile = MyUid.Monbile(curtimeuname);
        String upass = "lidb5000000000000000000000000000000000000000000000";
        String accresult = AccInterface.testAdduserex("&prefix=lidb&gen=seq&upass=" + upass + "&keytp=mobile&key=" + mobile + "");
        assertTrue("True", accresult.contains("result=0"));
        String Myuid = MyUid.Uidex(accresult);
        String uname = MyUid.Una(accresult);
        MyRedisUtil myredis = new MyRedisUtil();
        String myredisuid = myredis.getValue("uid:" + Myuid + "");
        //校验redis中的uid对应的用户名是否正确
        assertEquals(myredisuid, uname);
        //检验DBD中所存信息是否正确
        boolean ret = MyCheckBdb.CheckBdb(uname, "uid:" + Myuid + "", "u:" + uname + "", "", mobile, "", "");
        assertTrue(ret);

    }

    @Test
    //Case13:密码字节数为51时
    public void testupass51char() throws IOException, SAXException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case13:密码字节数为51时=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String mobile = MyUid.Monbile(curtimeuname);
        String upass = "lidb50000000000000000000000000000000000000000000001";
        String accresult = AccInterface.testAdduserex("&prefix=lidb&gen=seq&upass=" + upass + "&keytp=mobile&key=" + mobile + "");
        assertTrue("True", accresult.contains("result=101"));

    }

    @Test
    //Case14:当keytp=mobile时，key不为正常手机号注册(其余keytp与key不对应测试)
    public void testErrorMobile() throws IOException, SAXException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case14:当keytp=mobile时，key不为正常手机号注册(其余keytp与key不对应测试)=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String mobile = MyUid.Monbile(curtimeuname);
        String accresult = AccInterface.testAdduserex("&prefix=lidb&gen=seq&upass=" + upass + "&keytp=mobile&key=" + curtimeuname + "");
        assertTrue("True", accresult.contains("result=104"));

    }

    @Test
    //Case15:当key重复时测试
    public void testSamekey() throws IOException, SAXException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case15:当key重复时测试=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String mobile = MyUid.Monbile(curtimeuname);
        String accresult = AccInterface.testAdduserex("&prefix=lidb&gen=seq&upass=" + upass + "&keytp=mobile&key=10225155940");
        String accresult1 = AccInterface.testAdduserex("&prefix=lidb&gen=seq&upass=" + upass + "&keytp=mobile&key=10225155940");
        assertTrue("True", accresult1.contains("result=114"));
    }

    @Test
    //Case16:字段名错误注册
    public void testErrorparams() throws IOException, SAXException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case16:字段名错误注册=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String mobile = MyUid.Monbile(curtimeuname);
        String accresult = AccInterface.testAdduserex("&prefix=lidb&gn=seq&upass=" + upass + "&keytp=mobile&key=" + mobile + "");
        assertTrue("True", accresult.contains("result=101"));


    }

    @Test
    //Case17:当keytp=email时，key不为正常邮箱注册(其余keytp与key不对应测试)
    public void testErrorEmail() throws IOException, SAXException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case17:当keytp=email时，key不为正常手机号注册(其余keytp与key不对应测试)=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String mobile = MyUid.Monbile(curtimeuname);
        String accresult = AccInterface.testAdduserex("&prefix=lidb&gen=seq&upass=" + upass + "&keytp=email&key=" + mobile + "");
        assertTrue("True", accresult.contains("result=105"));
        assertTrue("True", accresult.contains("email_error"));

    }

    @Test
    //Case18:非必填字段全部正确返回格式校验
    public void testresultparams() throws IOException, SAXException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case18:非必填字段全部正确返回格式校验=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String mobile = MyUid.Monbile(curtimeuname);
        String accresult = AccInterface.testAdduserex("&prefix=lidb&gen=seq&upass=" + upass + "&keytp=mobile&key=" + mobile + "");
        assertTrue("True", accresult.contains("result=0"));
        assertTrue("True", accresult.contains("mobile=" + mobile + ""));
        assertTrue("True", accresult.contains("upass=" + upass + ""));
        String Myuid = MyUid.Uidex(accresult);
        assertTrue("True", accresult.contains("usertid=" + Myuid + ""));
        String uname = MyUid.Una(accresult);
        MyRedisUtil myredis = new MyRedisUtil();
        String myredisuid = myredis.getValue("uid:" + Myuid + "");
        //校验redis中的uid对应的用户名是否正确
        assertEquals(myredisuid, uname);
        //检验DBD中所存信息是否正确
        boolean ret = MyCheckBdb.CheckBdb(uname, "uid:" + Myuid + "", "u:" + uname + "", "", mobile, "", "");
        assertTrue(ret);

    }

    @Test
    //Case19:手机号重复增加
    public void testSameMobile() throws IOException, SAXException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case19:手机号重复增加=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String mobile = MyUid.Monbile(curtimeuname);
        String accresult = AccInterface.testAdduserex("&prefix=lidb&gen=seq&upass=" + upass + "&keytp=mobile&key=" + mobile + "");
        String accresult1 = AccInterface.testAdduserex("&prefix=lidb&gen=seq&upass=" + upass + "&keytp=mobile&key=" + mobile + "");
        assertTrue("True", accresult1.contains("result=114"));

    }

    @Test
    //Case20:邮箱注册
    public void testEmail() throws IOException, SAXException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case20:邮箱注册=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String email = MyUid.Email(curtimeuname);
        String accresult = AccInterface.testAdduserex("&prefix=lidb&gen=seq&upass=" + upass + "&keytp=email&key=" + email + "");
        assertTrue("True", accresult.contains("result=0"));
        assertTrue("True", accresult.contains("email=" + email + ""));
        assertTrue("True", accresult.contains("upass=" + upass + ""));
        String Myuid = MyUid.Uidex(accresult);
        assertTrue("True", accresult.contains("usertid=" + Myuid + ""));
        String uname = MyUid.Una(accresult);
        MyRedisUtil myredis = new MyRedisUtil();
        String myredisuid = myredis.getValue("uid:" + Myuid + "");
        //校验redis中的uid对应的用户名是否正确
        assertEquals(myredisuid, uname);
        //检验DBD中所存信息是否正确
        boolean ret = MyCheckBdb.CheckBdb(uname, "uid:" + Myuid + "", "u:" + uname + "", email, "", "", "");
        assertTrue(ret);

    }

    @Test
    //Case21:邮箱重复增加
    public void testSameEmail() throws IOException, SAXException, InterruptedException {
        Thread.sleep(1001);
        log.info("======Case20:邮箱重复增加=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String mobile = MyUid.Monbile(curtimeuname);
        String email = MyUid.Email(curtimeuname);
        String accresult = AccInterface.testAdduserex("&prefix=lidb&gen=seq&upass=" + upass + "&keytp=email&key=" + email + "");
        String accresult1 = AccInterface.testAdduserex("&prefix=lidb&gen=seq&upass=" + upass + "&keytp=email&key=" + email + "");
        assertTrue("True", accresult1.contains("result=114"));

    }

}
