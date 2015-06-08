package com.gw.account.httptest;

import com.atopcloud.util.MyCurrentTime;
import com.atopcloud.util.MyUid;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

//

public class UpdpassTest {

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
/*
        String accresult = AccInterface.testAdduser("&uname=lidb&upass=123456");
		String accresult1 = AccInterface.testAdduser("&uname=lidb001&upass=111111&mobile=15901620000");
		String accresult2 = AccInterface.testAdduser("&uname=lidb002&upass=111111&email=15901620000@qq.com");
		String accresult3 = AccInterface.testAdduser("&uname=你好&upass=123456");
		String accresult4 = AccInterface.testAdduser("&uname=lidb003&upass=123456");
		String accresult5 = AccInterface.testAdduser("&uname=lidb004&upass=123456");
		String accresult6 = AccInterface.testAdduser("&uname=lidb006&upass=111111&mobile=15901110000");
		String accresult7 = AccInterface.testAdduser("&uname=lidb007&upass=111111&mobile=15901110001");
*/


    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    //Case1:必填字段全部正确书写请求
    public void testMustParamsseq() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        //	System.out.println("======Case1:必填字段全部正确书写请求=======");
        log.info("======Case1:必填字段全部正确书写请求=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String uname = "lidb1" + curtimeuname + "";
        String accresult = AccInterface.testAdduser("&uname=" + uname + "&upass=111111");
        String accresult1 = AccInterface.testUpdpass("&uname=" + uname + "&upass=111111");
        assertTrue("True", accresult1.contains("result=0"));

    }

    @Test
    //Case2:非必填字段全部正确书写请求mobile(uname取唯一key)
    public void testAllParams() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        //	System.out.println("======Case2:非必填字段全部正确书写请求mobile(uname取唯一key)=======");
        log.info("======Case2:非必填字段全部正确书写请求mobile(uname取唯一key)=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String mobile = MyUid.Monbile(curtimeuname);
        String accresult = AccInterface.testAdduserex("&prefix=lidb&gen=seq&upass=111111&keytp=mobile&key=" + mobile + "");
        String accresult1 = AccInterface.testUpdpass("&uname=" + mobile + "&upass=123456&opass=111111&keytp=mobile");
        assertTrue("True", accresult1.contains("result=0"));
    }

    @Test
    //Case3:非必填字段全部正确书写请求email(uname取唯一key)
    public void testEmail() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        //	System.out.println("======Case3:非必填字段全部正确书写请求email(uname取唯一key)=======");
        log.info("======Case3:非必填字段全部正确书写请求email(uname取唯一key)=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String mobile = MyUid.Monbile(curtimeuname);
        String email = MyUid.Email(curtimeuname);
        String accresult = AccInterface.testAdduserex("&prefix=lidb&gen=seq&upass=111111&keytp=email&key=" + email + "");
        String accresult1 = AccInterface.testUpdpass("&uname=" + email + "&upass=123456&opass=111111&keytp=email");
        assertTrue("True", accresult1.contains("result=0"));
    }

    @Test
    //Case4:中文用户名密码修改
    public void testChnUname() throws IOException, SAXException, InterruptedException {
        //	System.out.println("======Case4:中文用户名密码修改=======");
        log.info("======Case4:中文用户名密码修改=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String uname = "你好" + curtimeuname + "";
        String accresult = AccInterface.testAdduser("&uname=" + uname + "&upass=123456");
        String accresult1 = AccInterface.testUpdpass("&uname=" + uname + "&upass=111111");
        assertTrue("True", accresult1.contains("result=0"));
    }


    @Test
    //Case5:缺失用户名修改
    public void testDefectUname() throws IOException, SAXException, InterruptedException {
        //	System.out.println("======Case5:缺失用户名修改=======");
        log.info("======Case5:缺失用户名修改=======");
        String accresult = AccInterface.testUpdpass("&upass=123456&opass=111111&keytp=mobile");
        assertTrue("True", accresult.contains("result=101"));

    }

    @Test
    //Case6:缺失新密码修改
    public void testDefectUpass() throws IOException, SAXException, InterruptedException {
        //	System.out.println("======Case6:缺失新密码修改=======");
        log.info("======Case6:缺失新密码修改=======");
        String accresult = AccInterface.testUpdpass("&uname=lidb&opass=111111&keytp=mobile");
        assertTrue("True", accresult.contains("result=101"));
    }

    @Test
    //Case7:缺失Keytp修改
    public void testDefectKeytp() throws IOException, SAXException, InterruptedException {
        //	System.out.println("======Case7:缺失Keytp修改=======");
        log.info("======Case7:缺失Keytp修改=======");
        String accresult = AccInterface.testUpdpass("uname=15901620000&upass=123456&opass=111111");
        assertTrue("True", accresult.contains("result=103"));
    }

    @Test
    //Case8:缺失key字段时
    public void testDefectkey() throws IOException, SAXException, InterruptedException {
        //	System.out.println("======Case8:缺失key字段时=======");
        log.info("======Case8:缺失key字段时=======");
        String accresult = AccInterface.testUpdpass("&upass=111111&opass=123456&keytp=email");
        assertTrue("True", accresult.contains("result=101"));

    }


    @Test
    //Case9:修改成相同的密码
    public void testSamepass() throws IOException, SAXException, InterruptedException {
        //	System.out.println("======Case9:修改成相同的密码=======");
        log.info("======Case9:修改成相同的密码=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String uname = "你好1" + curtimeuname + "";
        String accresult = AccInterface.testAdduser("&uname=" + uname + "&upass=123456");
        String accresult1 = AccInterface.testUpdpass("&uname=" + uname + "&upass=123456");
        assertTrue("True", accresult1.contains("result=0"));
    }


    @Test
    //Case10:修改后密码51字节
    public void testpass51chars() throws IOException, SAXException, InterruptedException {
        //	System.out.println("======Case10:修改后密码51字节=======");
        log.info("======Case10:修改后密码51字节=======");
        String accresult = AccInterface.testUpdpass("&uname=lidb003&upass=lidb50000000000000000000000000000000000000000000001");
        assertTrue("True", accresult.contains("result=101"));
    }


    @Test
    //Case11:字段名错误修改
    public void testErrorParams() throws IOException, SAXException, InterruptedException {
        //	System.out.println("======Case11:字段名错误注册=======");
        log.info("======Case11:字段名错误注册=======");
        String accresult = AccInterface.testUpdpass("&un=lidb072004&upass=111111");
        assertTrue("True", accresult.contains("result=101"));
    }

    @Test

    //Case12:原始密码错误修改
    public void testErrorupass() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        //	System.out.println("======Case12:原始密码错误修改=======");
        log.info("======Case12:原始密码错误修改=======");
        String curtimeuname = MyCurrentTime.MyTime();
        String uname = "lidb1" + curtimeuname + "";
        String accresult = AccInterface.testAdduser("&uname=" + uname + "&upass=111111");
        String accresult1 = AccInterface.testUpdpass("&uname=" + uname + "&upass=111111&opass=125556");
        assertTrue("True", accresult1.contains("result=59"));
    }

    //Case13:不存在的用户名修改
    public void testNotuname() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        //	System.out.println("======Case13:不存在的用户名修改=======");
        log.info("======Case13:不存在的用户名修改=======");
        String accresult = AccInterface.testUpdpass("&uname=12testtt&upass=111111&opass=123456");
        assertTrue("True", accresult.contains("result=0"));
    }

    //Case14:密码为md5密码
    public void testmd5upass() throws IOException, SAXException, ClassNotFoundException, InterruptedException {
        //	System.out.println("======Case13:不存在的用户名修改=======");
        log.info("======Case14:密码为md5密码=======");
        String accresult = AccInterface.testUpdpass("&uname=lidb006&upass=111111&opass=96e79218965eb72c92a549dd5a330112");
        assertTrue("True", accresult.contains("result=0"));
    }

}
