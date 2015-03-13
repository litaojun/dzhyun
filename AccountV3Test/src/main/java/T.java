import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.xml.sax.SAXException;

import com.atopcloud.util.MyCheckBdb;
import com.atopcloud.util.MyRedisUtil;
import com.atopcloud.util.MyUid;
import com.gw.account.httptest.AccInterface;


public class T {

	public static void main(String[] args) throws ClassNotFoundException, IOException, SAXException, InterruptedException {
		// TODO Auto-generated method stub
		testMustParamsEmail();
	}
	public static void testMustParamsEmail() throws IOException,SAXException, ClassNotFoundException, InterruptedException{
		String params = "&keytp=email&key=renguohua1@gw.com.cn";
		
		//请求输入
		Thread.sleep(1001);
		System.out.println("======Case1.1.2:必填字段全部正确书写请求（email）=======");		
		String accresult = AccInterface.testFindunamebykey(params);	
		
		//校验返回值
		//assertTrue("True",accresult.contains("result=0"));		
		String Myuid = MyUid.Uidex(accresult);
		String uname = MyUid.Una(accresult);
		MyRedisUtil myredis = new MyRedisUtil();			
		String myredisuid = myredis.getValue("uid:"+Myuid+"");
				
		//校验redis中的uid对应的用户名是否正确
		//assertEquals(myredisuid,uname);
		boolean ret = MyCheckBdb.CheckBdb(uname,"uid:"+Myuid+"","u:"+uname+"","","","","");
		//sassertTrue("",ret);						
	}
}
