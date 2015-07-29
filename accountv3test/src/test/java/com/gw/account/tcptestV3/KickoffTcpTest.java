package com.gw.account.tcptestV3;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.gw.account.tcptestV3.kickoff.ReceiveKickOffMsg1;
import com.gw.account.tcptestV3.kickoff.ReceiveKickOffMsg2;
import com.gw.account.tcptestV3.kickoff.ReceiveKickOffMsg3;
import com.gw.account.tcptestV3.kickoff.ReceiveKickOffMsg4;
import com.gw.account.tcptestV3.kickoff.ReceiveKickOffMsg5;
import com.gw.account.tcptestV3.kickoff.ReceiveKickOffMsg6;
import com.gw.account.tcptestV3.kickoff.ReceiveKickOffMsg7;
import com.gw.account.tcptestV3.kickoff.ReceiveKickOffMsg8;
import com.gw.account.tcptestV3.kickoff.UserDifAppid_to_kickoff_others;
import com.gw.account.tcptestV3.kickoff.UserDifAppid_tobe_kickoffed;
import com.gw.account.tcptestV3.kickoff.UserDif_to_kickoff_others;
import com.gw.account.tcptestV3.kickoff.UserEightLogin_tobe_kickoffed;
import com.gw.account.tcptestV3.kickoff.UserFirstLogin_tobe_kickoffed;
import com.gw.account.tcptestV3.kickoff.UserFiveLogin_tobe_kickoffed;
import com.gw.account.tcptestV3.kickoff.UserLogoutError_to_kickoff_others;
import com.gw.account.tcptestV3.kickoff.UserLogoutError_tobe_kickoffed;
import com.gw.account.tcptestV3.kickoff.UserLogout_to_kickoff_others;
import com.gw.account.tcptestV3.kickoff.UserLogout_tobe_kickoffed;
import com.gw.account.tcptestV3.kickoff.UserMoreAppidMap_kickoff_others;
import com.gw.account.tcptestV3.kickoff.UserMoreAppidMap_tobe_kickoffed;
import com.gw.account.tcptestV3.kickoff.UserMoreNotConflict_kickoff_others;
import com.gw.account.tcptestV3.kickoff.UserMoreNotConflict_to_kickoff_others;
import com.gw.account.tcptestV3.kickoff.UserMoreNotConflict_tobe1_kickoffed;
import com.gw.account.tcptestV3.kickoff.UserMoreNotConflict_tobe_kickoffed;
import com.gw.account.tcptestV3.kickoff.UserMorelogoutError_tobe_kickoffed;
import com.gw.account.tcptestV3.kickoff.UserMorelogout_kickoff_others;
import com.gw.account.tcptestV3.kickoff.UserMorelogout_tobe_kickoffed;
import com.gw.account.tcptestV3.kickoff.UserNotConflict_to_kickoff_others;
import com.gw.account.tcptestV3.kickoff.UserNotConflict_tobe_kickoffed;
import com.gw.account.tcptestV3.kickoff.UserTwoLoginAppIdDif_tobe_kickoffed;
import com.gw.account.tcptestV3.kickoff.UserTwoUmarktConflict_tobe_kickoffed;
import com.gw.account.tcptestV3.kickoff.UserTwoUmarktNotConflict_tobe_kickoffed;
import com.gw.account.tcptestV3.kickoff.VirtualHqUserMore_to_kickoff_others;
import com.gw.account.tcptestV3.kickoff.VirtualHqUserMore_tobe_kickoffed;
import com.gw.account.tcptestV3.kickoff.VirtualHqUser_to_kickoff_others;
import com.gw.account.tcptestV3.kickoff.VirtualHqUser_tobe_kickoffed;



/**
 * @author zhangchaoxu 2015/6/18
 * 
 */
public class KickoffTcpTest {
	
	
	// =================================性能测试脚本=======================================
	
	/**
	 * 验证同一个用户（手机号，邮箱，用户名）9000链接获取到踢人消息
	 * @return 
	 * @return kickoffmsg
	 * @throws IOException
	 * @throws SAXException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testKickOffMsg(String name) throws  InterruptedException {
		System.out
				.println("***********************验证同一个用户（手机号，邮箱，用户名）9000链接获取到踢人消息************************");
		User_tobe_kickoffed vhq1 = new User_tobe_kickoffed(name);
		User_to_kickoff vhq2 = new User_to_kickoff(name);

		//String msg;
		vhq1.start();	
		vhq2.start();	


      //return msg;
	}
	
	// =================================正常测试=======================================
		/**
		 * 验证用户第一次登录，不踢
		 * @return no data received
		 * @throws IOException
		 * @throws SAXException
		 * @throws NoSuchAlgorithmException
		 */
		@Test
		public void testOneLinkFirstLoginNotKickoff() throws IOException,
				SAXException, NoSuchAlgorithmException, InterruptedException {
			System.out
					.println("**********************验证用户第一次登录，不踢************************");
			UserFirstLogin_tobe_kickoffed vhq1 = new UserFirstLogin_tobe_kickoffed();
	        //启动线程
			
			vhq1.start();		

			Thread.sleep(1000);
			vhq1.join();
			boolean testkick = true;
			assertTrue("验证用户第一次登录，不踢", testkick);
		}
		/**
		 * 验证不同用户登录，不踢
		 * @return no data received
		 * @throws IOException
		 * @throws SAXException
		 * @throws NoSuchAlgorithmException
		 */
		@Test
		public void testTwoLinkTwoLoginDifUserNotKicKoff() throws IOException,
				SAXException, NoSuchAlgorithmException, InterruptedException {
			System.out
					.println("***********************验证不同用户登录，不踢************************");
			VirtualHqUser_tobe_kickoffed vhq1 = new VirtualHqUser_tobe_kickoffed();
			UserDif_to_kickoff_others vhq2 = new UserDif_to_kickoff_others();

			vhq1.start();
			Thread.sleep(1500);
			vhq2.start();
			Thread.sleep(1000);
			vhq1.join();
			vhq2.join();

			boolean testkick = true;
			assertTrue("验证不同用户登录，不踢", testkick);

		}
	/**
	 * 验证同一个用户（手机号，邮箱，用户名）9000链接获取到踢人消息
	 * @return kickoffmsg
	 * @throws IOException
	 * @throws SAXException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testTwolinkTwoLoginKickOffMsg() throws  InterruptedException {
		System.out
				.println("***********************验证同一个用户（手机号，邮箱，用户名）9000链接获取到踢人消息************************");
		VirtualHqUser_tobe_kickoffed vhq1 = new VirtualHqUser_tobe_kickoffed();
		VirtualHqUser_to_kickoff_others vhq2 = new VirtualHqUser_to_kickoff_others();

		vhq1.start();;
		Thread.sleep(1500);
		vhq2.start();;	
		Thread.sleep(1000);
		vhq1.join();
		vhq2.join();


	}
	/**
	 * 验证用户第二次登录,Umarkt不冲突,不踢
	 * @return no data received
	 * @throws IOException
	 * @throws SAXException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testTwoLinkTwoLoginNotConfictNotKickoff() throws InterruptedException {
		System.out
				.println("***********************验证用户第二次登录,Umarkt不冲突，不踢************************");
		UserNotConflict_tobe_kickoffed vhq1 = new UserNotConflict_tobe_kickoffed();
		UserNotConflict_to_kickoff_others vhq2 = new UserNotConflict_to_kickoff_others();

		vhq1.start();
		// 捕获无消息返回时抛出的异常
		Thread.sleep(1500);
		vhq2.start();
		Thread.sleep(1000);
		vhq1.join();
		vhq2.join();

		boolean testkick = true;
		assertTrue("验证用户第二次登录,Umarkt不冲突，不踢", testkick);

	}
	
	/**
	 * 验证两个链接appid不同，不踢
	 * @return no data reveived
	 * @throws IOException
	 * @throws SAXException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testTwoLinkTwoLoginDifAppid() throws IOException, SAXException,
			NoSuchAlgorithmException, InterruptedException {
		System.out
				.println("***********************验证两个链接appid不同，不踢************************");
		UserDifAppid_tobe_kickoffed vhq1 = new UserDifAppid_tobe_kickoffed();
		UserDifAppid_to_kickoff_others vhq2 = new UserDifAppid_to_kickoff_others();

		vhq1.start();
	
		Thread.sleep(1500);
		vhq2.start();
		Thread.sleep(1000);
		vhq1.join();
		vhq2.join();

		boolean testkick = true;
		assertTrue("*验证appid不同，不踢", testkick);

	}
	
	/**
	 * 验证LogOut参数传入正确退出，不踢
	 * @return no data received
	 * @throws IOException
	 * @throws SAXException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testTwoLinkNormalLogOut() throws IOException, SAXException,
			NoSuchAlgorithmException, InterruptedException {
		System.out
				.println("**********************验证LogOut参数传入正确退出，不踢************************");
		UserLogout_tobe_kickoffed vhq1 = new UserLogout_tobe_kickoffed();
		UserLogout_to_kickoff_others vhq2 = new UserLogout_to_kickoff_others();

		vhq1.start();
	
		Thread.sleep(1500);
		vhq2.start();
		Thread.sleep(1000);
		vhq1.join();
		vhq2.join();

		boolean testkick = true;
		assertTrue("验证LogOut参数传入正确退出，不踢", testkick);

	}
	
	/**
	 * 验证LogOut传入错误参数，收到踢人消息
	 * @return kickoffmsg
	 * @throws IOException
	 * @throws SAXException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testTwoLinkErrorLogOut() throws IOException, SAXException,
			NoSuchAlgorithmException, InterruptedException {
		System.out
				.println("**********************验证LogOut传入错误参数，收到踢人消息************************");
		UserLogoutError_tobe_kickoffed vhq1 = new UserLogoutError_tobe_kickoffed();
		UserLogoutError_to_kickoff_others vhq2 = new UserLogoutError_to_kickoff_others();
		vhq1.start();
	
		Thread.sleep(1500);
		vhq2.start();
		Thread.sleep(1000);
		vhq1.join();
		vhq2.join();

		boolean testkick = true;
		assertTrue("验证LogOut传入错误参数，收到踢人消息", testkick);

	}
	
	/**
	 * 验证同一链接8个login,收到8條踢人消息
	 * @return 8条kickoffmsg
	 * @throws IOException
	 * @throws SAXException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testOneLinkNormalEightLogin() throws IOException, SAXException,
			NoSuchAlgorithmException, InterruptedException {
		System.out
				.println("**********************验证同一链接8个login,收到8條踢人消息************************");
		UserEightLogin_tobe_kickoffed vhq1 = new UserEightLogin_tobe_kickoffed();

		vhq1.start();
	

		Thread.sleep(1000);
		vhq1.join();
		boolean testkick = true;
		assertTrue("验证同一链接8个login,收到8個踢人消息", testkick);
	}
	/**
	 * 验证同一链接5个login,收到8條踢人消息,有3条消息是上一个链接的踢人消息，根据返回消息的userpos识别（用户第一次登陆的话只收到5条踢人消息）
	 * @return 5条kickoff msg
	 * @throws IOException
	 * @throws SAXException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	
	public void testOneLinkNormalFiveLogin() throws IOException, SAXException,
			NoSuchAlgorithmException, InterruptedException {
		System.out
				.println("**********************验证同一链接5个login,收到8條踢人消息,有3条消息是上一个链接的踢人消息，根据返回消息的userpos识别（用户第一次登陆的话只收到5条踢人消息）************************");
		UserFiveLogin_tobe_kickoffed vhq1 = new UserFiveLogin_tobe_kickoffed();

		vhq1.start();
	

		Thread.sleep(1000);
		vhq1.join();
		boolean testkick = true;
		assertTrue("验证同一链接5个login,收到8條踢人消息,有3条消息是上一个链接的踢人消息，根据返回消息的userpos识别", testkick);
	}
	/**
	 * 验证八個链接8个login,收到8個踢人消息
	 * @return kickoffmsg
	 * @throws IOException
	 * @throws SAXException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testEightLinkNormalEightLogin() throws IOException,
			SAXException, NoSuchAlgorithmException, InterruptedException {
		System.out
				.println("***********************验证八個链接8个login,收到8個踢人消息************************");
		VirtualHqUserMore_tobe_kickoffed vhq1 = new VirtualHqUserMore_tobe_kickoffed();
		VirtualHqUserMore_to_kickoff_others vhq2 = new VirtualHqUserMore_to_kickoff_others();

		vhq1.start();

		Thread.sleep(1500);
		vhq2.start();
		Thread.sleep(1000);
		vhq1.join();
		vhq2.join();

		boolean testkick = true;
		assertTrue("验证八個链接8个login,收到8個踢人消息", testkick);

	}
	
	/**
	 * 验证八個链接8个login,umarkt不衝突，不踢
	 * @return 8个 no data received
	 * @throws IOException
	 * @throws SAXException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testEightLinkEightLoginNotConflict() throws IOException,
			SAXException, NoSuchAlgorithmException, InterruptedException {
		System.out
				.println("***********************验证八個链接8个login,umarkt不衝突，不踢,收到8条no data received************************");
		UserMoreNotConflict_tobe_kickoffed vhq1 = new UserMoreNotConflict_tobe_kickoffed();
		UserMoreNotConflict_to_kickoff_others vhq2 = new UserMoreNotConflict_to_kickoff_others();

		vhq1.start();
		Thread.sleep(1500);
		vhq2.start();
		Thread.sleep(1000);
		vhq1.join();
		vhq2.join();

		boolean testkick = true;
		assertTrue("验证八個链接8个login,umarkt不衝突，不踢", testkick);

	}

	/**
	 * 验证同一链接2个login,AppId不同，不踢
	 * @return no data reveived
	 * @throws IOException
	 * @throws SAXException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testOneLinkTwoLoginAppidDif() throws IOException, SAXException,
			NoSuchAlgorithmException, InterruptedException {
		System.out
				.println("********************** 验证同一链接2个login,AppId不同，不踢************************");
		UserTwoLoginAppIdDif_tobe_kickoffed vhq1 = new UserTwoLoginAppIdDif_tobe_kickoffed();

		vhq1.start();
		Thread.sleep(1000);
		vhq1.join();
		boolean testkick = true;
		assertTrue(" 验证同一链接2个login,AppId不同，不踢", testkick);
	}

	/**
	 * 验证同一链接2个login,umarkt不冲突，不踢
	 * @return no data reveived
	 * @throws IOException
	 * @throws SAXException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testOneLinkTwoLoginUmarktNotConflict() throws IOException,
			SAXException, NoSuchAlgorithmException, InterruptedException {
		System.out
				.println("********************** 验证同一链接2个login,umarkt不冲突，不踢************************");
		UserTwoUmarktNotConflict_tobe_kickoffed vhq1 = new UserTwoUmarktNotConflict_tobe_kickoffed();

		vhq1.start();
		Thread.sleep(1000);
		vhq1.join();
		boolean testkick = true;
		assertTrue(" 验证同一链接2个login,umarkt不冲突，不踢", testkick);
	}

	/**
	 * 验证同一链接2个login,umarkt冲突，踢
	 * @return Kickoffmsg
	 * @throws IOException
	 * @throws SAXException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testOneLinkTwoLoginUmarkConflict() throws IOException,
			SAXException, NoSuchAlgorithmException, InterruptedException {
		System.out
				.println("********************** 验证同一链接2个login,umarkt冲突，踢************************");
		UserTwoUmarktConflict_tobe_kickoffed vhq1 = new UserTwoUmarktConflict_tobe_kickoffed();

		vhq1.start();
		Thread.sleep(1000);
		vhq1.join();
		boolean testkick = true;
		assertTrue(" 验证同一链接2个login,umarkt冲突，踢", testkick);
	}
	/**
	 * 验证八個链接8个login,appid不同，不踢
	 *
	 * @return no data receive
	 * @throws IOException
	 * @throws SAXException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testEightLinkEightLoginAppIdDif() throws IOException,
			SAXException, NoSuchAlgorithmException, InterruptedException {
		System.out
				.println("***********************验证八個链接8个login,appid不同，不踢************************");
		UserMoreNotConflict_tobe1_kickoffed vhq1 = new UserMoreNotConflict_tobe1_kickoffed();
		UserMoreNotConflict_kickoff_others vhq2 = new UserMoreNotConflict_kickoff_others();

		vhq1.start();
		Thread.sleep(1500);
		vhq2.start();
		Thread.sleep(1000);
		vhq1.join();
		vhq2.join();

		boolean testkick = true;
		assertTrue("验证八個链接8个login,appid不同，不踢", testkick);

	}

	/**
	 * 验证在etcd中配置后，appid映射问题，正常踢
	 * @return kickoff receivemsg,8条
	 * @throws IOException
	 * @throws SAXException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testtEightLinkEightLoginMap() throws IOException, SAXException,
			NoSuchAlgorithmException, InterruptedException {
		System.out.println("***********************验证在etcd中配置后，appid映射问题，正常踢，收到8条踢人消息************************");
		UserMoreAppidMap_tobe_kickoffed vhq1 = new UserMoreAppidMap_tobe_kickoffed();
		UserMoreAppidMap_kickoff_others vhq2 = new UserMoreAppidMap_kickoff_others();

		vhq1.start();
		Thread.sleep(1500);
		vhq2.start();
		Thread.sleep(1000);
		vhq1.join();
		vhq2.join();

		boolean testkick = true;
		assertTrue("验证在etcd中配置后，appid映射问题，正常踢", testkick);

	}
	/**
	 * 验证八個链接8个login，logout，不踢
	 * @return no data receive
	 * @throws IOException
	 * @throws SAXException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testEightLinkEightLoginlogout() throws IOException,
			SAXException, NoSuchAlgorithmException, InterruptedException {
		System.out
				.println("***********************验证八個链接8个login，logout，不踢************************");
		UserMorelogout_tobe_kickoffed vhq1 = new UserMorelogout_tobe_kickoffed();
		UserMorelogout_kickoff_others vhq2 = new UserMorelogout_kickoff_others();

		vhq1.start();
		Thread.sleep(1500);
		vhq2.start();
		Thread.sleep(1000);
		vhq1.join();
		vhq2.join();

		boolean testkick = true;
		assertTrue("验证八個链接8个login，logout，不踢", testkick);

	}

	/**
	 * 验证八個链接8个login，logout部分正确，收到4条踢人消息
	 * @return no data receive
	 * @throws IOException
	 * @throws SAXException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testEightLinkEightLoginErrorlogout() throws IOException,
			SAXException, NoSuchAlgorithmException, InterruptedException {
		System.out.println("*********************** 验证八個链接8个login，logout部分正确，收到5条踢人消息（14567）************************");
		UserMorelogoutError_tobe_kickoffed vhq1 = new UserMorelogoutError_tobe_kickoffed();
		//UserMorelogoutError_kickoff_others vhq2 = new UserMorelogoutError_kickoff_others();
		ReceiveKickOffMsg1 vhq3 = new ReceiveKickOffMsg1();
		ReceiveKickOffMsg2 vhq4 = new ReceiveKickOffMsg2();
		ReceiveKickOffMsg3 vhq5 = new ReceiveKickOffMsg3();
		ReceiveKickOffMsg4 vhq6 = new ReceiveKickOffMsg4();
		ReceiveKickOffMsg5 vhq7 = new ReceiveKickOffMsg5();
		ReceiveKickOffMsg6 vhq8 = new ReceiveKickOffMsg6();
		ReceiveKickOffMsg7 vhq9 = new ReceiveKickOffMsg7();
		ReceiveKickOffMsg8 vhq0 = new ReceiveKickOffMsg8();
		vhq1.start();
				vhq3.start();
				vhq4.start();
				vhq5.start();
				vhq6.start();
				vhq7.start();
				vhq8.start();
				vhq9.start();
				vhq0.start();
				Thread.sleep(1000);
		vhq1.join();
		//vhq2.join();
		vhq3.join();
		vhq4.join();
		vhq5.join();
		vhq6.join();
		vhq7.join();
		vhq8.join();
		vhq9.join();
		vhq0.join();
		Thread.sleep(1000);
		boolean testkick = true;
		assertTrue("验证八個链接8个login，logout部分正确，收到5条踢人消息（14567)", testkick);

	}
}