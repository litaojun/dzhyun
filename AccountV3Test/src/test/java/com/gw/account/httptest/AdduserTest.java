package com.gw.account.httptest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.xml.sax.SAXException;

import com.atopcloud.util.MyCurrentTime;
import com.atopcloud.util.MyDatabaseUtil;
import com.atopcloud.util.MyRedisUtil;
import com.atopcloud.util.MyUid;
import com.atopcloud.util.MyBdbUtil;
import com.atopcloud.util.MyCheckBdb;



//
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.InvocationContext;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

import junit.framework.Assert;
import junit.framework.TestCase;

public class AdduserTest {
	
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
	//Case1:只传用户名密码正常测试
	public void testUnameupass() throws IOException,SAXException, ClassNotFoundException, SQLException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case1:只传用户名密码正常测试=======");
		String curtimeuname = MyCurrentTime.MyTime();
		String uname = "lidb"+curtimeuname+"";
		String accresult = AccInterface.testAdduser("&uname="+uname+"&upass="+upass+"");	
		assertTrue("True",accresult.contains("result=0"));
		String Myuid = MyUid.Uid(accresult);
		MyRedisUtil myredis = new MyRedisUtil();		
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
//		MyBdbUtil  mybdb = new MyBdbUtil();		
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue(ret);
	//	assertEquals(ret,"true");
		/*
		String myuid = mybdb.getValue("uid:"+Myuid+"");
		String myu = mybdb.getValue("u:lidb"+curtimeuname+"");
		String myuts = mybdb.getValue("uid:lidb"+curtimeuname+"");
		//校验bdb中的uid对应的用户名是否正确
		assertEquals(myuid,"lidb"+curtimeuname+"");
	//	assertTrue("True",myuts.contains("lidb"+curtimeuname+""));
		//校验bdb中u:key中是否包含用户名
		assertTrue("True",myu.contains("lidb"+curtimeuname+""));
		*/		
						
	}
	
	@Test
	//Case2:测试大写用户名注册后转换成小写用户名,存储在库中时也为小写
	public void testCapitalUname() throws IOException,SAXException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case2:测试大写用户名注册后转换成小写用户名,存储在库中时也为小写=======");
		String curtimeuname = MyCurrentTime.MyTime();
		String uname = "LIDB"+curtimeuname+"";
		String accresult = AccInterface.testAdduser("&uname="+uname+"&upass="+upass+"");	
		assertTrue("True",accresult.contains("result=0"));
		String Myuid = MyUid.Uid(accresult);
		MyRedisUtil myredis = new MyRedisUtil();		
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
//		System.out.println(myredisuid);
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,"lidb"+curtimeuname+"");
		//检验DBD中所存信息是否正确
		boolean ret = MyCheckBdb.CheckBdb("lidb"+curtimeuname+"","uid:"+Myuid+"","u:lidb"+curtimeuname+"","","","","");
		assertTrue(ret);	
	}
	
	@Test
	//Case3:非必填字段全部正确书写请求
	public void testAllparams() throws IOException,SAXException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case3:非必填字段全部正确书写请求=======");
		String curtimeuname = MyCurrentTime.MyTime();
		String uname = "lidb"+curtimeuname+"";
		String mobile = MyUid.Monbile(curtimeuname);
		String email = MyUid.Email(curtimeuname);
		String vname = MyUid.Monbile(curtimeuname); 
		String accresult = AccInterface.testAdduser("&uname="+uname+"&upass="+upass+"&mobile="+mobile+"&email="+email+"&vname="+vname+"");	
		assertTrue("True",accresult.contains("result=0"));
		String Myuid = MyUid.Uid(accresult);
		MyRedisUtil myredis = new MyRedisUtil();		
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		//检验DBD中所存信息是否正确
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"",email,mobile,"","");
		assertTrue(ret);		
												
	}
	
	@Test
	//Case4:中文用户名注册
	public void testChnUname() throws IOException,SAXException{
		System.out.println("======Case4:中文用户名注册=======");
		String uname = "你好"+curtimeuname+"";
		String accresult = AccInterface.testAdduser("&uname="+uname+"&upass="+upass+"");	
		assertTrue("True",accresult.contains("result=0"));
		String Myuid = MyUid.Uid(accresult);
		MyRedisUtil myredis = new MyRedisUtil();		
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
//		MyBdbUtil  mybdb = new MyBdbUtil();		
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue(ret);							
	}
	
	@Test
	//Case5:必添字段用户名缺失
	public void testDefectUname() throws IOException,SAXException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case5:必添字段用户名缺失=======");
		String curtimeuname = MyCurrentTime.MyTime();
		String mobile = MyUid.Monbile(curtimeuname);
		String email = MyUid.Email(curtimeuname);
		String vname = MyUid.Monbile(curtimeuname); 
		String accresult = AccInterface.testAdduser("&upass="+upass+"&mobile="+mobile+"&email="+email+"&vname="+vname+"");	
		assertTrue("True",accresult.contains("uname_bad"));	
		assertTrue("True",accresult.contains("result=101"));	
												
	}
	
	@Test
	//Case6:必添字段密码缺失
	public void testDefectUpass() throws IOException,SAXException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case6:必添字段密码缺失=======");
		String curtimeuname = MyCurrentTime.MyTime();
		String mobile = MyUid.Monbile(curtimeuname);
		String email = MyUid.Email(curtimeuname);
		String vname = MyUid.Monbile(curtimeuname); 
		String accresult = AccInterface.testAdduser("&uname="+uname+"&mobile="+mobile+"&email="+email+"&vname="+vname+"");	
		assertTrue("True",accresult.contains("upass_bad"));	
		assertTrue("True",accresult.contains("result=101"));
												
	}
	
	@Test
	//Case7:非必填字段缺失手机号
	public void testDefectMobile() throws IOException,SAXException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case7:非必填字段缺失手机号=======");
		String curtimeuname = MyCurrentTime.MyTime();
		String uname = "lidb"+curtimeuname+"";
		String mobile = MyUid.Monbile(curtimeuname);
		String email = MyUid.Email(curtimeuname);
		String vname = MyUid.Monbile(curtimeuname); 
		String accresult = AccInterface.testAdduser("&uname="+uname+"&upass="+upass+"&email="+email+"&vname="+vname+"");	
		assertTrue("True",accresult.contains("result=0"));
		String Myuid = MyUid.Uid(accresult);
		MyRedisUtil myredis = new MyRedisUtil();		
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		//检验DBD中所存信息是否正确
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"",email,"","","");
		assertTrue(ret);		
												
	}
	
	@Test
	//Case8:非必填字邮箱段缺失
	public void testDefectEmail() throws IOException,SAXException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case8:非必填字邮箱段缺失=======");
		String curtimeuname = MyCurrentTime.MyTime();
		String uname = "lidb"+curtimeuname+"";
		String mobile = MyUid.Monbile(curtimeuname);
		String vname = MyUid.Monbile(curtimeuname); 
		String accresult = AccInterface.testAdduser("&uname="+uname+"&upass="+upass+"&mobile="+mobile+"&vname="+vname+"");	
		assertTrue("True",accresult.contains("result=0"));
		String Myuid = MyUid.Uid(accresult);
		MyRedisUtil myredis = new MyRedisUtil();		
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		//检验DBD中所存信息是否正确
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","",mobile,"","");
		assertTrue(ret);		
												
	}
	
	@Test
	//Case9:非必填字段昵称昵称缺失
	public void testDefectvname() throws IOException,SAXException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case9:非必填字段昵称昵称缺失=======");
		String curtimeuname = MyCurrentTime.MyTime();
		String uname = "lidb"+curtimeuname+"";
		String mobile = MyUid.Monbile(curtimeuname);
		String email = MyUid.Email(curtimeuname);
		String accresult = AccInterface.testAdduser("&uname="+uname+"&upass="+upass+"&mobile="+mobile+"&email="+email+"");	
		assertTrue("True",accresult.contains("result=0"));
		String Myuid = MyUid.Uid(accresult);
		MyRedisUtil myredis = new MyRedisUtil();		
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
		//校验redis中的uid对应的用户名是否正确
		assertEquals(myredisuid,uname);
		//检验DBD中所存信息是否正确
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"",email,mobile,"","");
		assertTrue(ret);		
												
	}
	
	
	@Test
	//Case10:用户名重复测试
	public void testUnameSame() throws IOException,SAXException, ClassNotFoundException, SQLException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case10:用户名重复测试=======");
		String uname = "lidb";
		String accresult = AccInterface.testAdduser("&uname="+uname+"&upass="+upass+"");	
		assertTrue("True",accresult.contains("result=1"));							
	}
	
	@Test
	//Case11:用户名字节少于4字
	public void testUname3char() throws IOException,SAXException, ClassNotFoundException, SQLException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case11:用户名字节少于4字=======");
		String uname = "lid";
		String accresult = AccInterface.testAdduser("&uname="+uname+"&upass="+upass+"");	
		assertTrue("True",accresult.contains("result=3"));							
	}
	
	@Test
	//Case12:用户名字节大于50字节
	public void testUname51char() throws IOException,SAXException, ClassNotFoundException, SQLException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case11:用户名字节大于50字节=======");
		String uname = "lidb50000000000000000000000000000000000000000000000";
		String accresult = AccInterface.testAdduser("&uname="+uname+"&upass="+upass+"");	
		assertTrue("True",accresult.contains("result=3"));							
	}
	
	@Test
	//Case13:用户名特殊字符
	public void testUnameSpecialchar() throws IOException,SAXException, ClassNotFoundException, SQLException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case13:用户名特殊字符=======");
		String uname = "lidb!#$";
		String accresult = AccInterface.testAdduser("&uname="+uname+"&upass="+upass+"");	
		assertTrue("True",accresult.contains("result=101"));							
	}
	
	@Test
	//Case14:密码字节等于50测试
	public void testUpass50char() throws IOException,SAXException, ClassNotFoundException, SQLException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case14:密码字节等于50测试=======");
		String curtimeuname = MyCurrentTime.MyTime();
		String uname = "lidb"+curtimeuname+"";
		String upass50 = "lidb500000000000000000000000000000000000000000000";
		String accresult = AccInterface.testAdduser("&uname="+uname+"&upass="+upass50+"");	
		assertTrue("True",accresult.contains("result=0"));
		String Myuid = MyUid.Uid(accresult);
		MyRedisUtil myredis = new MyRedisUtil();		
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
		assertEquals(myredisuid,uname);
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		assertTrue(ret);
						
	}
	
	@Test
	//Case15:密码字节等于51测试
	public void testUpass51char() throws IOException,SAXException, ClassNotFoundException, SQLException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case15:密码字节等于51测试=======");
		String curtimeuname = MyCurrentTime.MyTime();
		String uname = "lidb"+curtimeuname+"";
		String upass50 = "lidb50000000000000000000000000000000000000000000001";
		String accresult = AccInterface.testAdduser("&uname="+uname+"&upass="+upass50+"");	
		assertTrue("True",accresult.contains("result=101"));
		assertTrue("True",accresult.contains("upass_bad"));
						
	}
	
	@Test
	//Case16:手机号码10位
	public void testMobile10char() throws IOException,SAXException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case16:手机号码9位=======");
		String curtimeuname = MyCurrentTime.MyTime();
		String uname = "lidb"+curtimeuname+"";
		String mobile = "1590162042";
		String email = MyUid.Email(curtimeuname);
		String vname = MyUid.Monbile(curtimeuname); 
		String accresult = AccInterface.testAdduser("&uname="+uname+"&upass="+upass+"&mobile="+mobile+"&email="+email+"&vname="+vname+"");	
		assertTrue("True",accresult.contains("result=104"));															
	}
	
	@Test
	//Case17:手机号码12位
	public void testMobile12char() throws IOException,SAXException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case17:手机号码12位=======");
		String curtimeuname = MyCurrentTime.MyTime();
		String uname = "lidb"+curtimeuname+"";
		String mobile = "159016204211";
		String email = MyUid.Email(curtimeuname);
		String vname = MyUid.Monbile(curtimeuname); 
		String accresult = AccInterface.testAdduser("&uname="+uname+"&upass="+upass+"&mobile="+mobile+"&email="+email+"&vname="+vname+"");	
		assertTrue("True",accresult.contains("result=104"));															
	}
	
	
	@Test
	//Case18:非电话号码注册.
	public void testNoMobile() throws IOException,SAXException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case18:非电话号码注册=======");
		String curtimeuname = MyCurrentTime.MyTime();
		String uname = "lidb"+curtimeuname+"";
		String mobile = "05901620421";
		String email = MyUid.Email(curtimeuname);
		String vname = MyUid.Monbile(curtimeuname); 
		String accresult = AccInterface.testAdduser("&uname="+uname+"&upass="+upass+"&mobile="+mobile+"&email="+email+"&vname="+vname+"");	
		assertTrue("True",accresult.contains("result=104"));															
	}
	
	@Test
	//Case19:邮箱格式错误注册
	public void testNoEmail() throws IOException,SAXException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case19:邮箱格式错误注册=======");
		String curtimeuname = MyCurrentTime.MyTime();
		String uname = "lidb"+curtimeuname+"";
		String mobile = MyUid.Monbile(curtimeuname);
		String email = "05901620421qq.com";
		String vname = MyUid.Monbile(curtimeuname); 
		String accresult = AccInterface.testAdduser("&uname="+uname+"&upass="+upass+"&mobile="+mobile+"&email="+email+"&vname="+vname+"");	
		assertTrue("True",accresult.contains("result=105"));															
	}
	
	@Test
	//Case20:字段名错误注册
	public void testErrorparams() throws IOException,SAXException, InterruptedException{
		Thread.sleep(1001);
		System.out.println("======Case20:字段名错误注册=======");
		String curtimeuname = MyCurrentTime.MyTime();
		String uname = "lidb"+curtimeuname+"";
		String mobile = MyUid.Monbile(curtimeuname);
		String email = MyUid.Email(curtimeuname);
		String vname = MyUid.Monbile(curtimeuname); 
		String accresult = AccInterface.testAdduser("&une="+uname+"&upass="+upass+"&mobile="+mobile+"&email="+email+"&vname="+vname+"");	
		assertTrue("True",accresult.contains("result=101"));			
		
	}
/*
	public void testEmail() throws IOException,SAXException{
		String accresult = AccInterface.testAdduser("&uname=johnny4&upass=123qwe");				
		try {
			int accmysql = MyDatabaseUtil.doQuerySql("10.15.201.24", "3306", "root", "", "INETACT", "SELECT * FROM T_MOBILE_BIND_INFO");
			System.out.print(accmysql);	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		assertTrue("True",accresult.contains("result=0"));
		MyRedisUtil accre = new MyRedisUtil();
		String accredis = accre.getValue("GwInetActApp:RECIEVE_CODE:2:ZVQBJY");		
		System.out.print(accredis);
		
	}
*/	
/*
	@Test
	public void testEmail() throws IOException,SAXException{
		String accresult = AccInterface.testAdduser("正常登录","johnny","123qwe","");
		assertTrue("True",accresult.contains("result=0"));
		System.out.print(accresult);
	}
*/	
	/*
	@Test
	public void testEmail2() throws IOException,SAXException{
		String accresult = AccInterface.testAdduser("","johnny","123qwe","%26email%3Djohnny%40sohu.com");
		assertTrue("True",accresult.contains("result=0"));
		System.out.print(accresult);
	}
	*/
	/*
	@Test
	public void testEmail() throws IOException,SAXException{
		WebConversation web = new WebConversation();
		String url = "http://10.15.108.114:9001/AccService/AccServlet.do?method=adduser&uname=bjohnny&upass=123qwe&email=soh@sohu.com";
		GetMethodWebRequest get = new GetMethodWebRequest(url);
		WebResponse response = web.getResponse(get);
		System.out.print(response.getText());
		assertTrue("True",response.getText().contains("result=0"));
		
	}
	*/
}
