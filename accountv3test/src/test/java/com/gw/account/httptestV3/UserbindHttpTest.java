package com.gw.account.httptestV3;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.protobuf.InvalidProtocolBufferException;
import com.gw.account.utils.MyCheckUtil;
import com.gw.account.utils.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by zhangchaoxu on 2015/6/8.
 */
public class UserbindHttpTest {
	private User user = new User();
	private static String idcard_15_x;

	@BeforeClass
	public static void globalInit() {
		MyCheckUtil.initialize();
	}

	@Before
	public void setUp() throws IOException, SAXException, InterruptedException {
		user.createUser();
		idcard_15_x = String.format("%014d", Long.parseLong(user.getNumber()))
				+ "x";
	}

	// =================================正常绑定=======================================
	/**
	 * 新用户绑定邮箱及手机号正常验证
	 */
	@Test
	public void testNormal() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", user
				.getUname(), "keys", ImmutableList.of(ImmutableMap.of("email",
				user.getEmail(), "mobile", user.getMobile())), "reqdata", user
				.getreqdata()));
		String response = AccInterface.testUserbind(request);
		boolean result = MyCheckUtil.checkJsonResponseSolo(response, "result",
				"0");
		boolean checkresult = checkALL(request, response);
		assertTrue("新用户绑定邮箱及手机号正常验证", result && checkresult);
	}

	/**
	 * 新用户绑定身份证，大财账户，昵称正常验证
	 */
	@Test
	public void testKeytpNormal() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", user
				.getUname(), "keys", ImmutableList.of(ImmutableMap.of("idcard",
				user.getIdcard(), "lotterid", user.getLotterid(), "nickname",
				user.getNickname())), "reqdata", user.getreqdata()));
		String response = AccInterface.testUserbind(request);
		boolean result = MyCheckUtil.checkJsonResponseSolo(response, "result",
				"0");
		boolean checkresult = checkALL(request, response);
		assertTrue("新用户绑定身份证，大财账户，昵称正常验证", result && checkresult);
	}

	/**
	 * 新用户绑定十五位号身份证验证
	 */
	@Test
	public void testIdcard15() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname",
				user.getUname(), "keys",
				ImmutableList.of(ImmutableMap.of("idcard", user.getIdcard_15_x())),
				"reqdata", user.getreqdata()));
		String response = AccInterface.testUserbind(request);
		boolean result = MyCheckUtil.checkJsonResponseSolo(response, "result",
				"0");
		boolean checkresult = check(request, response);
		assertTrue("新用户绑定十五位号身份证验证", result && checkresult);
	}

	/**
	 * 新用户绑定理财宝、湘财资金、设备指纹验证
	 */
	@Test
	public void testKeytpNormal1() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", user
				.getUname(), "keys", ImmutableList.of(ImmutableMap.of("lcb",
				user.getLcb(), "termfp", user.getTermfp(), "xcid",
				user.getXcid()

		)), "reqdata", user.getreqdata()));
		String response = AccInterface.testUserbind(request);
		boolean result = MyCheckUtil.checkJsonResponseSolo(response, "result",
				"0");
		boolean checkresult = checkALL(request, response);
		assertTrue("新用户绑定理财宝、湘财资金、设备指纹验证", result && checkresult);
	}

	/**
	 * 新用户绑定QQ Openid，微信Openid，微信Unionid请求验证
	 */
	@Test
	public void testKeytpNormal3() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", user
				.getUname(), "keys", ImmutableList.of(ImmutableMap.of("qqid",
				user.getQqid(), "wxid", user.getWxid(), "wxoid", user.getWxid()

		)), "reqdata", user.getreqdata()));
		String response = AccInterface.testUserbind(request);
		boolean result = MyCheckUtil.checkJsonResponseSolo(response, "result",
				"0");
		boolean checkresult = checkALL(request, response);
		assertTrue("新用户绑定QQ Openid，微信Openid，微信Unionid请求验证", result
				&& checkresult);
	}

	// =================================重复绑定=======================================
	/**
	 * 验证非强制绑定,flush=false
	 */
	@Test
	public void testBangedToBandAbnormal() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", user.getUname(),
				"keys", ImmutableList.of(ImmutableMap.of("email",
						"343374585@qq.com", "mobile", "15271383652")), "reqdata", "zc2152"));
		String uname=user.getUname();
		@SuppressWarnings("unused")
		String response = AccInterface.testUserbind(request);
		String request1 = JSON.toJSONString(ImmutableMap.of("uname", uname,
				"keys", ImmutableList.of(ImmutableMap.of("email",
						"343374775@qq.com", "mobile", "15341383652", "flush",
						false)), "reqdata", "zc21352"));
		String response1 = AccInterface.testUserbind(request1);
		boolean result = MyCheckUtil.checkJsonResponseSolo(response1, "result",
				"114");
		assertTrue("验证非强制绑定,flush=false", result);
	}

	/**
	 * 验证强制绑定
	 */
	@Test
	public void testBangedToBandNormal() throws IOException, SAXException,
			NoSuchAlgorithmException {
		SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
		Date date = new Date();
		String number = df.format(date);
		String request = JSON.toJSONString(ImmutableMap.of("uname", user.getUname(),
				"keys", ImmutableList.of(ImmutableMap.of("email",
						"343374585@qq.com", "mobile", "15271383652")), "reqdata", "zc2152"));
		String uname=user.getUname();
		@SuppressWarnings("unused")
		String response = AccInterface.testUserbind(request);
		String request1 = JSON.toJSONString(ImmutableMap.of("uname", uname,
				"keys", ImmutableList.of(ImmutableMap.of("email",
						"343374775@qq.com", "mobile", "15341383652", "flush",
						true)), "reqdata", number));
		String response1 = AccInterface.testUserbind(request1);
		boolean result = MyCheckUtil.checkJsonResponseSolo(response1, "result",
				"0");
		boolean checkresult=check(request1,response1);
		assertTrue("验证非强制绑定,flush=true", result&&checkresult);
	}

	/**
	 * 验证已绑定用户解綁再绑定
	 */
	@Test
	public void testBangToBangNormal() throws IOException, SAXException,
			NoSuchAlgorithmException {
		//新建用戶并綁定
		String request = JSON.toJSONString(ImmutableMap.of("uname", user.getUname(),
				"keys", ImmutableList.of(ImmutableMap.of("email",
						user.getEmail(), "mobile",user.getMobile())),
				"reqdata", "zc21892"));
		String response = AccInterface.testUserbind(request);
		String uname=MyCheckUtil.getValueFromJsonResponse(response, "uname");
		//獲取用戶usertid
		String request3 = JSON.toJSONString(ImmutableMap.of("uname",uname));
		String response3 = AccInterface.testUserGet(request3);
		String usertid=MyCheckUtil.getValueFromJsonResponse(response3, "usertid");
		//解除綁定
		String request1 = JSON.toJSONString(ImmutableMap.of("uname",
				"!"+usertid, "keys", ImmutableList.of(ImmutableMap.of(
						"email", "mobile")),
				"reqdata", "zc21892"));
		@SuppressWarnings("unused")
		String response1 = AccInterface.testDelUserbind(request1);
		//重新綁定
		String request2 = JSON.toJSONString(ImmutableMap.of("uname", uname,
				"keys", ImmutableList.of(ImmutableMap.of("email",
						user.getEmail(), "mobile",user.getMobile())),
				"reqdata", "zc21892"));
		String response2 = AccInterface.testUserbind(request2);
		boolean checkresult = check(request2, response2);
		boolean result = MyCheckUtil.checkJsonResponseSolo(response2, "result",
				"0");
		assertTrue("验证已绑定用户解綁再绑定", result && checkresult);
	}

	// =================================异常参数绑定=======================================
	// =======================用户名等参数绑定=================
	/**
	 * 验证用户名不存在错误
	 */
	@Test
	public void testUnameError() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname",
				"620378790", "keys",
				ImmutableList.of(ImmutableMap.of("email", "343374585@qq.com",
						"mobile", "153652", "flush", false)), "reqdata",
				"zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "2");
		assertTrue("验证用户名不存在错误", checkresult);
	}

	/**
	 * 验证keytp参数不存在错误
	 */
	@Test
	public void testKeytpkeyError() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", user
				.getUname(), "keys", ImmutableList.of(ImmutableMap.of(
				"email133", user.getEmail(), "flush", false)), "reqdata", user
				.getreqdata()));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "113");
		assertTrue("验证keytp参数不存在错误", checkresult);
	}

	// =======================邮箱错误参数绑定=================
	/**
	 * 超过200字符的邮箱存在@绑定
	 *
	 * @throws IOException
	 * @throws SAXException
	 * @throws InterruptedException
	 */
	@Test
	public void testBindTooLongEmail() throws IOException, SAXException,
			InterruptedException {
		StringBuffer toolongemail = new StringBuffer(user.getEmail());
		for (int i = 0; i <= 192; i++) {
			toolongemail.append("t");
		}
		String email = toolongemail.toString() + "@126.com";
		String request = JSON.toJSONString(ImmutableMap.of("uname",
				user.getUname(), "keys",
				ImmutableList.of(ImmutableMap.of("email", email)), "reqdata",
				"zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "105");
		assertTrue("超过200字符的邮箱存在@绑定", checkresult);
	}

	/**
	 * 邮箱不存在@绑定
	 *
	 * @throws IOException
	 * @throws SAXException
	 * @throws InterruptedException
	 */
	@Test
	public void testLackofAtBindEmail() throws IOException, SAXException,
			InterruptedException {
		StringBuffer toolongemail = new StringBuffer(user.getNumber());
		for (int i = 0; i <= 10; i++) {
			toolongemail.append("t");
		}
		String email = toolongemail.toString() + "126.com";
		String request = JSON.toJSONString(ImmutableMap.of("uname",
				user.getUname(), "keys",
				ImmutableList.of(ImmutableMap.of("email", email)), "reqdata",
				"zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "105");
		assertTrue("邮箱不存在@绑定", checkresult);
	}

	/**
	 * @是首字符的邮箱绑定
	 *
	 * @throws IOException
	 * @throws SAXException
	 * @throws InterruptedException
	 */
	@Test
	public void testAtisFirstEmail() throws IOException, SAXException,
			InterruptedException {
		StringBuffer toolongemail = new StringBuffer(user.getNumber());
		for (int i = 0; i <= 10; i++) {
			toolongemail.append("t");
		}
		String email = "@" + toolongemail.toString() + "126.com";
		String request = JSON.toJSONString(ImmutableMap.of("uname",
				user.getUname(), "keys",
				ImmutableList.of(ImmutableMap.of("email", email)), "reqdata",
				"zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "105");
		assertTrue("@是首字符的邮箱绑定", checkresult);
	}

	/**
	 * @之后没有.的邮箱绑定
	 * @throws IOException
	 * @throws SAXException
	 * @throws InterruptedException
	 */
	@Test
	public void testBindLackofDotEmail() throws IOException, SAXException,
			InterruptedException {
		StringBuffer toolongemail = new StringBuffer(user.getNumber());
		for (int i = 0; i <= 10; i++) {
			toolongemail.append("t");
		}
		String email = toolongemail.toString() + "@126com";
		String request = JSON.toJSONString(ImmutableMap.of("uname",
				user.getUname(), "keys",
				ImmutableList.of(ImmutableMap.of("email", email)), "reqdata",
				"zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "105");
		assertTrue("@之后没有.的邮箱绑定", checkresult);
	}

	/**
	 * .之后不存在其他字符的邮箱绑定
	 *
	 * @throws IOException
	 * @throws SAXException
	 * @throws InterruptedException
	 */
	@Test
	public void testBindNullAftDotEmail() throws IOException, SAXException,
			InterruptedException {
		StringBuffer toolongemail = new StringBuffer(user.getNumber());
		for (int i = 0; i <= 10; i++) {
			toolongemail.append("t");
		}
		String email = toolongemail.toString() + "@163.";
		String request = JSON.toJSONString(ImmutableMap.of("uname",
				user.getUname(), "keys",
				ImmutableList.of(ImmutableMap.of("email", email)), "reqdata",
				"zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "105");
		assertTrue(".之后不存在其他字符的邮箱绑定", checkresult);
	}

	// =======================手机号错误参数绑定=================
	/**
	 * 验证keytp参数值手机号长度不够填写错误
	 */
	@Test
	public void testMobileLenError() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", user.getUname(),
				"keys",
				ImmutableList.of(ImmutableMap.of("email", user.getEmail(),
						"mobile", "153652", "flush", false)), "reqdata",
				"zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "104");
		assertTrue("验证keytp参数值手机号长度不够填写错误", checkresult);
	}

	/**
	 * 验证keytp参数值手机号首字符非1填写错误
	 */
	@Test
	public void testMobileOneNotFirstError() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", user.getUname(),
				"keys",
				ImmutableList.of(ImmutableMap.of("mobile", "53652633666")),
				"reqdata", "zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "104");
		assertTrue("验证keytp参数值手机号首字符非1填写错误", checkresult);
	}

	/**
	 * 验证keytp参数值手机号超过15位填写错误
	 */
	@Test
	public void testBindMorethan15MobileError() throws IOException,
			SAXException, NoSuchAlgorithmException {
		String request = JSON
				.toJSONString(ImmutableMap.of("uname", user.getUname(), "keys",
						ImmutableList.of(ImmutableMap.of("mobile",
								"1536526336663651")), "reqdata", "zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "104");
		assertTrue("验证keytp参数值手机号超过15位填写错误", checkresult);
	}

	/**
	 * 验证keytp参数值手机号低于11位填写错误
	 */
	@Test
	public void testBindLessthan11MobileError() throws IOException,
			SAXException, NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", user.getUname(),
				"keys",
				ImmutableList.of(ImmutableMap.of("mobile", "1536651123")),
				"reqdata", "zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "104");
		assertTrue("验证keytp参数值手机号低于11位填写错误", checkresult);
	}

	/**
	 * 验证keytp参数值手机号低于11位填写错误
	 */
	@Test
	public void testBindWithCharMobileError() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", user.getUname(),
				"keys",
				ImmutableList.of(ImmutableMap.of("mobile", "1536651123X")),
				"reqdata", "zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "104");
		assertTrue("验证keytp参数值手机号低于11位填写错误", checkresult);
	}

	// =======================身份证错误参数绑定=================
	/**
	 * 验证身份证非15或者18位
	 */
	@Test
	public void testIdcardError() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname",
				user.getUname(), "keys",
				ImmutableList.of(ImmutableMap.of("idcard", "1563363161")),
				"reqdata", "zc21652"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "104");
		assertTrue("验证身份证非15或者18位", checkresult);
	}

	/**
	 * 验证身份证非X绑定
	 */
	@Test
	public void testBindTailNotxXCharIdcardError() throws IOException,
			SAXException, NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", user
				.getUname(), "keys", ImmutableList.of(ImmutableMap.of("idcard",
				"3422251987041657RM")), "reqdata", "zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "104");
		assertTrue("验证身份证非X绑定", checkresult);
	}

	/**
	 * 验证身份证非末位X绑定
	 */
	@Test
	public void testBindNotTailCharIdcardError() throws IOException,
			SAXException, NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", user
				.getUname(), "keys", ImmutableList.of(ImmutableMap.of("idcard",
				"342225198704X65714")), "reqdata", "zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "104");
		assertTrue("验证身份证非末位X绑定", checkresult);
	}

	// =================================空参数绑定=======================================
	/**
	 * 验证用户名为空
	 */
	@Test
	public void testUnamenull() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", "", "keys",
				ImmutableList.of(ImmutableMap.of("email", "343374585@qq.com",
						"mobile", "15271383652", "flush", false)), "reqdata",
				"zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "101");
		assertTrue("验证用户名为空", checkresult);
	}

	/**
	 * 验证keys为空
	 */
	@Test
	public void testKeysnull() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", user.getUname(),
				"reqdata", "zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "101");
		assertTrue("验证keys为空", checkresult);
	}

	/**
	 * 验证keytp邮箱为空
	 */
	@Test
	public void testKeytpemailnull() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", user.getUname(),
				"keys", ImmutableList.of(ImmutableMap.of("email", "", "mobile",
						"15271383652", "flush", false)), "reqdata", "zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "101");
		assertTrue("验证keytp邮箱为空", checkresult);
	}

	/**
	 * 验证keytp手机号为空
	 */
	@Test
	public void testKeytpmobilenull() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", user.getUname(),
				"keys", ImmutableList.of(ImmutableMap.of("email", "123@11.com",
						"mobile", "", "flush", false)), "reqdata", "zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "101");
		assertTrue("验证keytp手机号为空", checkresult);
	}

	/**
	 * 验证keytp身份证号为空
	 */
	@Test
	public void testKeytpidcardnull() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", user.getUname(),
				"keys", ImmutableList.of(ImmutableMap.of("email", "123@11.com",
						"idcard", "", "flush", false)), "reqdata", "zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "101");
		assertTrue("验证keytp身份证号为空", checkresult);
	}

	/**
	 * 验证keytp的nickname为空
	 */
	@Test
	public void testKeytpnicknamenull() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", user.getUname(),
				"keys", ImmutableList.of(ImmutableMap.of("email", "123@11.com",
						"nickname", "", "flush", false)), "reqdata", "zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "101");
		assertTrue("验证keytp的nickname为空", checkresult);
	}

	/**
	 * 验证keytp的lotterid为空
	 */
	@Test
	public void testKeytplotteridnull() throws IOException, SAXException,
			NoSuchAlgorithmException {
		String request = JSON.toJSONString(ImmutableMap.of("uname", user.getUname(),
				"keys", ImmutableList.of(ImmutableMap.of("email", user.getEmail(),
						"lotterid", "", "flush", false)), "reqdata", "zc2152"));
		String response = AccInterface.testUserbind(request);
		boolean checkresult = MyCheckUtil.checkJsonResponseSolo(response,
				"result", "101");
		assertTrue("验证keytp的lotterid为空", checkresult);
	}

	// =================================工具方法=======================================

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws InvalidProtocolBufferException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @check方法request与response值对比
	 */
	public boolean check(String request, String response)
			throws InvalidProtocolBufferException, NoSuchAlgorithmException,
			UnsupportedEncodingException {
		String getuname = MyCheckUtil.getValueFromJsonResponse(response,
				"uname");
		String name = MyCheckUtil.getValueFromJsonResponse(request, "uname");
		boolean checkuname = getuname.equals(name);
		String getureqdate = MyCheckUtil.getValueFromJsonResponse(response,
				"req");
		String reqdate = MyCheckUtil.getValueFromJsonResponse(request,
				"reqdata");
		boolean checkreqdate = getureqdate.equals(reqdate);
		boolean checkresult = checkuname && checkreqdate;
		return checkresult;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws InvalidProtocolBufferException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @checkall方法新建用户值与response值对比
	 */
	public boolean checkALL(String request, String response)
			throws InvalidProtocolBufferException, NoSuchAlgorithmException,
			UnsupportedEncodingException {
		String getuname = MyCheckUtil.getValueFromJsonResponse(response,
				"uname");
		boolean checkuname = getuname.equals(user.getUname());
		String getureqdate = MyCheckUtil.getValueFromJsonResponse(response,
				"req");
		boolean checkreqdate = true;
		if (getureqdate != null) {
			checkreqdate = getureqdate.equals(user.getreqdata());
		} else {
			checkreqdate = true;
		}
		boolean checkemail = true;
		boolean checkmobile = true;
		boolean checknickname = true;
		boolean checkidcard = true;
		boolean checkidlcb = true;
		boolean checktermfp = true;
		boolean checkxcid = true;
		boolean checkqqid = true;
		boolean checkWxid = true;
		boolean checkWxoid = true;
		String json = response.toString();
		JSONObject jsonArray = JSONObject.parseObject(json);
		for (Map.Entry<String, Object> entry : jsonArray.entrySet()) {
			// System.out.println(String.format("%s:%s",entry.getKey(),entry.getValue()));
			if ("keys".equals(entry.getKey())) {
				JSONArray array = JSONArray.parseArray((String) entry.getValue());
				for (Object o : array) {
					JSONObject keysjson = JSONObject.parseObject(o.toString());
					if (keysjson.get("email") != null) {
						checkemail = user.getEmail().equals(
								keysjson.get("email"));
					} else {
						checkemail = true;
					}
					if (keysjson.get("mobile") != null) {
						checkmobile = user.getMobile().equals(
								keysjson.get("mobile"));
					} else {
						checkmobile = true;
					}
					if (keysjson.get("nickname") != null) {
						checkmobile = user.getNickname().equals(
								keysjson.get("nickname"));
					} else {
						checknickname = true;
					}
					if (keysjson.get("idcard") != null) {
						checkidcard = user.getIdcard().equals(
								keysjson.get("idcard"));
					} else {
						checkidcard = true;
					}
					if (keysjson.get("lcb") != null) {
						checkidlcb = user.getLcb().equals(keysjson.get("lcb"));
					} else {
						checkidlcb = true;
					}
					if (keysjson.get("termfp") != null) {
						checktermfp = user.getTermfp().equals(
								keysjson.get("termfp"));
					} else {
						checktermfp = true;
					}
					if (keysjson.get("xcid") != null) {
						checkxcid = user.getXcid().equals(keysjson.get("xcid"));
					} else {
						checkxcid = true;
					}
					if (keysjson.get("qqid") != null) {
						checkqqid = user.getQqid().equals(keysjson.get("qqid"));
					} else {
						checkqqid = true;
					}
					if (keysjson.get("Wxid") != null) {
						checkWxid = user.getWxid().equals(keysjson.get("Wxid"));
					} else {
						checkWxid = true;
					}
					if (keysjson.get("Wxoid") != null) {
						checkWxoid = user.getWxid().equals(
								keysjson.get("Wxoid"));
					} else {
						checkWxoid = true;
					}
				}

			}
		}
		boolean checkresult = checkuname && checkemail && checkmobile
				&& checknickname && checkidcard && checkidlcb && checktermfp
				&& checkxcid && checkqqid && checkWxid && checkWxoid
				&& checkreqdate;
		return checkresult;
	}
}
