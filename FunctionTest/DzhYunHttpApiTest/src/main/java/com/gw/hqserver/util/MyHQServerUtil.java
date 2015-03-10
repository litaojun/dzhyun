package com.gw.hqserver.util;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.ByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * 行情服务系统相关处理类，处理与行情服务系统的QTRS的通信，取得行情数据，作为云平台行情数据的标准参考物。
 * 使用jna技术。
 * @author Administrator
 *
 */
public class MyHQServerUtil {
	
//	/*
//	 * 模拟C的enum
//	 * @brief table 目前支持的字段数据类型
//	 */
//	public enum table_filed_type {
//		/**
//		 * @brief 空，无意义，占位符
//		 */
//		tft_null(0),
//		/**
//		 * @brief 1 字节整数，类型为 c
//		 */
//		tft_char(1),
//		/**
//		 * @brief 2 字节整数，类型为 h
//		 */
//		tft_short(2),
//		/**
//		 * @brief 4 字节整数，类型为 i    
//		 */
//		tft_int(3),
//		/**
//		 * @brief 8 字节整数，类型为 q
//		 */
//		tft_int64(4),
//		/**
//		 * @brief 4 字节浮点，类型为 f
//		 */
//		tft_float(5),
//		/**
//		 * @brief 8 字节浮点，类型为 d
//		 */
//		tft_double(6),
//		/**
//		 * @brief 8 字节指向字符串的指针，类型为 s 
//		 */
//		tft_str(7),
//		/**
//		 * @brief 8 字节指向table_t的指针，类型为 t 
//		 */
//		tft_table(8),
//		/**
//		 * @brief 1 字节整数，类型为 C
//		 */
//		tft_uchar(9),
//		/**
//		 * @brief 2 字节整数，类型为 H
//		 */
//		tft_ushort(10),
//		/**
//		 * @brief 4 字节整数，类型为 I    
//		 */
//		tft_uint(11),
//		/**
//		 * @brief 8 字节整数，类型为 Q
//		 */
//		tft_uint64(12),
//		/**
//		 * @brief 8 字节指向变长二进制的指针，类型为 b
//		 */
//		tft_binpt(13),
//		/**
//		 * @brief 8 字节时间类型数，类型为 T
//		 */
//		tft_timet(14),
//		/**
//		 * @brief 8 字节定点浮点数，类型为 p
//		 */
//		tft_pfloat(15),
//		/**
//		 * @brief 8 字节定点浮点数，类型为 v
//		 */
//		tft_vfloat(16);
//		
//		private int ncode;
//		private table_filed_type(int _ncode){
//			this.ncode = _ncode;
//		}
//		public int getValue()
//		{
//			return this.ncode;
//		}
//	}
	
	
	//CRootInfo
	/**
	 * 模拟C语言的结构体: 存根信息
	 typedef struct CRootInfo
	{
		int m_nThreld;
		int m_nLogFileSize;
		char* m_pRootProxyName;
	}*LPCROOTINFO;
	 * @author Administrator
	 *
	 */
	public static  class CRootInfo extends Structure {
		//公共字段的顺序，必须与C语言中的结构的顺序一致，否则会报错！  
		public int m_nThreld;
		public int m_nLogFileSize;
		public String m_pRootProxyName;
		//结构体传指针
		public static class ByReference extends CRootInfo implements Structure.ByReference{};
		//结构体传值
		public static class ByValue extends CRootInfo implements Structure.ByValue{};
		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList(new String[]{"m_nThreld","m_nLogFileSize","m_pRootProxyName"});
		}
	}
	
	/**
	 * 模拟c语言结构体：服务返回信息
	 * 
	typedef struct DSResponse
	{
	int m_nType;
	int m_nReqSeq;
	int m_nResSeq;
	int m_nState;
	table_t m_tResult; //响应返回数据   typedef void * table_t;  //在java中，用Pointer替代void*
	}
	 * @author Administrator
	 */
	public static class DSResponse extends Structure
	{
		//字段必须为public
		public int m_nType;
		public int m_nReqSeq;
		public int m_nResSeq;
		public int m_nState;
		public Pointer m_tResult; //响应返回数据  //Pointer替代table_t
		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList(new String[]{"m_nType","m_nReqSeq","m_nResSeq","m_nState","m_tResult"});
		}
		
		public static class ByReference extends DSResponse implements Structure.ByReference{};
		public static class ByValue extends DSResponse implements Structure.ByValue{};
	}
	/**
	 *模拟c语言函数指针（作为回调函数使用）：
	 *typedef void (*DSCallback)(const DSResponse* response, void* userdata);
	 * @author Administrator
	 *
	 */
	public static interface DSCallbackInterface extends Callback{
		public void invoke(final DSResponse.ByReference response, Pointer  userdata);
	}
	
	//实现DSCallbackInterface接口
	public static class  DSCallback implements DSCallbackInterface{
		@Override
		public void invoke(final DSResponse.ByReference response, Pointer  userdata)
		{
			//TODO:处理返回的数据
			//这里取到的数据作为标准数据
		}
	}
	
	
	//动态库类
	public interface HQServerLib extends Library
	{
		HQServerLib INSTANCE = (HQServerLib)Native.loadLibrary("Alib", HQServerLib.class);   //alib不带后缀，保证可以跨平台
		HQServerLib INSTANCE2 = (HQServerLib)Native.loadLibrary("C:\\windows\\SYSWOW64\\LibTableUtil.dll", HQServerLib.class);
		
		//声明原生函数的映射的java函数
		/**
		* @file table_process.h
		* @brief 处理table_t的数据
		* @author lzq
		* @date 05/03/2015
		* 解析table_t数据类型，得到string类型输出。
		*c原型：DZHAPI_DLL int PrintfTable(table_t fromtable, char* strall);
		*/
		public  int PrintfTable(Pointer fromtable, Memory  mem);
		
		
		//与c/c++  lib映射的函数
		/**
		  * @brief 初始化数据代理模块
		  * @retval >=0 成功初始化
		  * @remark 初始化后，外部应用可使用数据代理模块的功能,只调用一次即可
		*/
		//DZHAPI_DECLARE DZHAPI_DLL int alibInit();
		public int alibInit();
		
		/**
		  * @brief 销毁资源以及所有根代理
		  * @remark 退出程序时使用,只调用一次即可
		*/
		//DZHAPI_DECLARE DZHAPI_DLL void alibDestroy();
		public void alibDestroy();
		
		/**
		  * @brief 创建根数据代理
		  * @param[in] pRootInfo 根代理的名称，日志等配置
		  * @param[in] configure 配置信息，server_address=ip&server_port=port类似格式
		  * @param[in] callback 根数据代理连接状态发生变化时的回调函数
		  * @param[in] usedata 由应用赋值,异步回调函数中原值带回
		  * @param[in&out] pIntRootProxySeq 返回根代理描述符 >0 成功
		  * @retval 非空 创建成功
		  * @remark 创建根数据代理，每个应用必须创建至少一个根数据代理，根数据代理会连接到数据代理，以获取数据；根数据代理与数据代理间的连接状态发生变化时，会通过回调函数通知上层。
		*/
		//void*  proxyCreateRoot(CRootInfo* pRootInfo, const char* configure, DSCallback callback, void* usedata, int* pIntRootProxySeq);
		public Pointer proxyCreateRoot(CRootInfo.ByReference pRootInfo, String configure, DSCallbackInterface callback, Pointer usedata, IntByReference pIntRootProxySeq);
		
		/**
		  * @brief 创建子代理
		  * @param[in] configure 保留
		  * @param[in] nParentProxySeq 父代理描述符
		  * @param[in] pObj 由proxyCreateRoot返回
		  * @retval >0 返回子代理描述符
		*/
		//DZHAPI_DECLARE DZHAPI_DLL int proxyCreateSub (int nParentProxySeq, const char* configure, void* pObj);
		public int proxyCreateSub (int nParentProxySeq, String configure, Pointer pObj);
		
		/**
		  * @brief 取消指定代理下的所有请求
		  * @param[in] nProxySeq 代理描述符
		  * @param[in] pObj 由proxyCreateRoot返回
		  * @retval >=0 成功
		*/
		//DZHAPI_DECLARE DZHAPI_DLL int proxyCancelAllReq (int nProxySeq, void* pObj);
		public int proxyCancelAllReq (int nProxySeq, Pointer pObj);
		
		/**
		  * @brief 销毁指定代理
		  * @param[in] nProxySeq 代理描述符
		  * @param[in] pObj 由proxyCreateRoot返回
		  * @retval >=0 成功
		*/
		//DZHAPI_DECLARE DZHAPI_DLL int proxyDestroy(int nProxySeq, void* pObj);
		public int proxyDestroy(int nProxySeq, Pointer pObj);
		
		/**
		  * @brief 在指定代理下添加同步请求
		  * @param[in] nProxySeq 代理描述符
		  * @param[in] request 请求串
		  * @param[in&out] response 返回响应状态或数据
		  * @param[in] pObj 由proxyCreateRoot返回
		  * @retval >=0 成功（返回值非请求描述符）
		*/
		//DZHAPI_DECLARE  DZHAPI_DLL int syncReqData(int nProxySeq, const char* request, DSResponse* response, void* pObj);
		public int syncReqData(int nProxySeq, String request, DSResponse.ByReference response, Pointer pObj);
		
		/**
		  * @brief 在指定代理下添加异步请求
		  * @param[in] nProxySeq 代理描述符
		  * @param[in] request 请求串
		  * @param[in&out] response 返回响应状态和数据
		  * @param[in] callback 返回响应时回调函数
		  * @param[in] usedata 由应用赋值,异步回调函数中原值带回
		  * @param[in] pObj 由proxyCreateRoot返回
		  * @retval >0 返回请求描述符
		  * @retval =0 表示存在相同请求的历史数据，直接返回数据
		*/
		//DZHAPI_DECLARE DZHAPI_DLL int asyncReqData(int nProxySeq, const char* request, DSResponse* response, DSCallback callback, void* usedata, void* pObj);
		public int asyncReqData(int nProxySeq, String request, DSResponse.ByReference response, DSCallbackInterface callback, Pointer usedata, Pointer pObj);
		
		/**
		  * @brief 取消请求
		  * @param[in] nReqSeq 请求描述符
		  * @param[in] pObj 由proxyCreateRoot返回
		  * @retval >=0 取消请求成功
		*/
		//DZHAPI_DECLARE DZHAPI_DLL int cancelReq(int nReqSeq, void* pObj);
		public int cancelReq(int nReqSeq , Pointer pObj);
		
		//group请求
		/**
		  * @brief 在指定的代理下创建组
		  * @param[in] nProxySeq 代理描述符
		  * @param[in] callback 根数据代理连接状态发生变化时的回调函数
		  * @param[in] usedata 由应用赋值,异步回调函数中原值带回
		  * @param[in] pObj 由proxyCreateRoot返回
		  * @retval >0 返回组描述符
		*/
		//DZHAPI_DECLARE DZHAPI_DLL int groupCreate(int nProxySeq, DSCallback callback, void*usedata, void* pObj);
		public int groupCreate(int nProxySeq, DSCallbackInterface callback, Pointer usedata, Pointer pObj);
		
		/**
		  * @brief 在指定组下添加异步请求
		  * @param[in] nGroupSeq 组描述符
		  * @param[in] request 请求串
		  * @param[in&out] response 返回响应状态或数据
		  * @param[in] pObj 由proxyCreateRoot返回
		  * @retval >0 返回请求描述符
		  * @retval =0 表示存在相同请求的历史数据，直接返回数据
		*/
		//DZHAPI_DECLARE DZHAPI_DLL int groupAsyncReqData(int nGroupSeq, const char* request, DSResponse* response, void* pObj);
		public  int groupAsyncReqData(int nGroupSeq, String request, DSResponse.ByReference response, Pointer pObj);
		
		/**
		  * @brief 禁止后续往组中添加请求
		  * @param[in] nGroupSeq 组描述符
		  * @param[in] pObj 由proxyCreateRoot返回
		  * @retval >=0 成功
		*/
		//DZHAPI_DECLARE DZHAPI_DLL int groupReqEnd(int nGroupSeq, void* pObj);
		public int groupReqEnd(int nGroupSeq,Pointer pObj);
		
		/**
		  * @brief 取消组请求
		  * @param[in] nGroupSeq 组描述符
		  * @param[in] pObj 由proxyCreateRoot返回
		  * @retval >=0 取消组请求成功
		*/
		//DZHAPI_DECLARE DZHAPI_DLL int groupCancel(int nGroupSeq, void* pObj);
		public int groupCancel(int nGroupSeq, Pointer pObj);
		
		/**
		  * @brief 销毁组
		  * @param[in] nGroupSeq 组描述符
		  * @param[in] pObj 由proxyCreateRoot返回
		  * @retval >=0 销毁组成功
		*/
		//DZHAPI_DECLARE DZHAPI_DLL int groupDestroy(int nGroupSeq, void* pObj);
		public int groupDestroy(int nGroupSeq, Pointer pObj);
	}
	
	//处理table的动态库类（table文件由原北京世华提供）
	public  interface LibTableUtil extends Library
	{
		LibTableUtil INSTANCE = (LibTableUtil)Native.loadLibrary("C:\\windows\\SYSWOW64\\LibTableUtil.dll", LibTableUtil.class);
		
		//声明原生函数的映射的java函数
		/**
		* @file table_process.h
		* @brief 处理table_t的数据
		* @author lzq
		* @date 05/03/2015
		* 解析table_t数据类型，得到string类型输出。
		*c原型：DZHAPI_DLL int PrintfTable(table_t fromtable, char* strall);
		*/
		public  int PrintfTable(Pointer fromtable, Memory  mem);
	}
	//业务逻辑
	/**
	 * 根据输入参数{@code}g_nResTimes取动态行情
	 * @param g_nResTimes  需要服务器返回的响应数，1则为表示服务器返回1条信息（同步请求）；
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws InterruptedException 
	 */
	public static  String getQuoteDynaSingleOfHQServer(int g_nResTimes) throws UnsupportedEncodingException, InterruptedException
	{
		//请求参数
		String szField="";
		String szWhere="obj=SH601519";
		String szStart="-1";
		String szCount="1";
		int  nResMode = 0;
		String szParameter="type=dyna";
		int  nAsyncResMode = 0;                     //同步还是异步模式
		String szServiceName="quote";
		
		//login params
		String  ip = "10.15.108.76";
		String  port = "5184";
		String  app_name="";
		String  app_developer="";
		String  app_ver="";
		String  app_build="";
		String  user_name = "dzhappuser3";
		String  user_pwd = "123456";
		
		//1调用alibInit初始化
		int ret = HQServerLib.INSTANCE.alibInit();		
		if(ret < 0)
			return null;
		System.out.println("alib init ok");
		//2创建代理
		String configure = 	
				"server_address="
				+ip
				+"&server_port="
				+port
				+"&app_name="
				+app_name
				+"&app_developer="
				+app_developer
				+"&app_ver="
				+app_ver
				+"&app_build="
				+app_build
				+"&user_name="
				+user_name
				+"&user_password="
				+user_pwd;

		//创建存根对象
		CRootInfo.ByReference mRootInfo= new CRootInfo.ByReference();
		mRootInfo.m_nLogFileSize = 10*1024*1024;
		mRootInfo.m_nThreld = 3;
		mRootInfo.m_pRootProxyName = null;
		//创建回调
		DSCallbackInterface callback = new DSCallback();
		IntByReference pIntRootProxySeq = new IntByReference(-1);
		//创建代理
		Pointer pObj = HQServerLib.INSTANCE.proxyCreateRoot(mRootInfo, configure, callback, null, pIntRootProxySeq);
		if(pObj == null || pIntRootProxySeq.getValue() == -1)
			return null;
		System.out.println("alib proxyCreateRoot ok");
		//3发送请求
		String request=
				"service="
				+szServiceName
				+"&"
				+ szParameter
				+ "&field="
				+szField
				+"&where="
				+szWhere
				+"&start="
				+szStart
				+"&count="
				+szCount
				+"&response_times="
				+g_nResTimes
				+"&response_mode="
				+nResMode
				+"&async_response_mode="
				+nAsyncResMode;
 		if(g_nResTimes == 0)  //同步请求
		{
			DSResponse.ByReference response = new DSResponse.ByReference();
			int req1 = HQServerLib.INSTANCE.syncReqData(pIntRootProxySeq.getValue(), request, response, pObj);
			if(req1 >= 0) 
			{
				//TODO：处理返回的数值
				System.out.println("alib syncReqData ok");
				//打印获取到的数据
				if (null != response.m_tResult)
				{
					/*1使用byte数组传引用。该方法报错Invalid memory access。
					 * byte[] strall=new byte[8*1024];  //8kB大小作为输出
					 */
					
					/*2自定义ByReference子类。该方法报错Invalid memory access。
					StringByReference strall=new StringByReference();
					strall.setValue("hello stringbyreference");
					System.out.println(strall.getValue());
					 */
					
					/*
					 * 3使用Pointer。Invalid memory access。
					*/
					
					/*
					 * 4使用PointerByReference。仍然报错。
					 */
					
					/*
					 * 5修改c代码的std:string为char*。在java中使用Memory。
					 */
					//上面的代码归根结底，还是因为dll本身有问题导致的。
					//TODO:修复错误（可能是王建平dll有问题）
					Memory memory = new Memory(2*1024);
					//LibTableUtil.INSTANCE.PrintfTable(response.m_tResult, memory);
					HQServerLib.INSTANCE2.PrintfTable(response.m_tResult, memory);
					//WriteTable(strall);
					/*table_destory(response.m_tResult);*/
					//打印值
					String str = memory.getString(0); 
					System.out.println("out="+str);
				}
				System.out.println("alib syncReqData get null response"); 
//				if(g_nShow == 1)
//					printf("recv a sync req' res.\n");
//				gettimeofday(&tvEnd, NULL);
//				int span = 1000000*(tvEnd.tv_sec - tvStart.tv_sec)+tvEnd.tv_usec-tvStart.tv_usec;//us
//				printf("use %d us.\n\n", span);
			}
		}
		else   //异步请求
		{
			
		}
		
		//sleep 15s
		Thread.sleep(5*1000);
		
		//销毁所有根代理并卸载动态库
		HQServerLib.INSTANCE.alibDestroy();
		System.out.println("alib alibDestroy ok");
		return "";
		
	}
	

	public static void main(String[] args)
	{
		try {
			System.out.println("begin to get quote dyna!");
			getQuoteDynaSingleOfHQServer(0);
			System.out.println("after got quote dyna!");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	/**
//	 * 解析服务器返回的行情数据，得到字符串形式的解析后数据。
//	 * @param fromtable 输入；待解析数据，一块内存存储的数据
//	 * @param strall  输出；解析后的数据
//	 * @return
//	 */
//	//int PrintfTable(table_t fromtable,string& strall)
//	//下面有些数据类型可能有问题，需要调试
//	public int PrintfTable(Pointer fromtable,String strall)
//	{
//		if (fromtable != null)
//		{
//			int count = 0;
//			String buf="";
//			byte tmpvalue = 0;
//			String pBuf = null;
//			int colsize = 0;
//			int rows = LibTableUtil.INSTANCE.table_rows(fromtable);
//			int cols = LibTableUtil.INSTANCE.table_cols(fromtable);
//			//打印实际有效的数据
//			for (int i=0; i<rows; i++)
//			{
//				for (int j=0; j<cols; j++)
//				{
//					int  type=LibTableUtil.INSTANCE.table_col_type(fromtable, j) ;
//					switch(type)
//					{
//					case table_filed_type.tft_null.getValue(): //null类型
//						break;
//					case table_filed_type.tft_char.getValue():   //char类型
//					{
//						{
//							colsize = LibTableUtil.INSTANCE.table_col_size(fromtable, j);
//							if (colsize == 1)  //c的char为8bit，sizeof(char)是1
//							{
////								memset(buf,0,200);
////								int len = sprintf(buf,"%d\t",*(char*)table_get_buff(fromtable,i,j));
////								strall += buf;
//								Pointer ptr = LibTableUtil.INSTANCE.table_get_buff(fromtable,i,j);
//								buf = String.valueOf(ptr.getByte(0));	
//								buf +="\t";
//								strall += buf;
//							}
//							else
//							{
////								const char* colname = NULL;
////								memset(buf,0,200);
////								int len = sprintf(buf,"%s\t",(char*)table_get_buff(fromtable,i,j));
////
////								colname = table_col_name(fromtable,j);
////								strall += buf;
//								Pointer ptr = LibTableUtil.INSTANCE.table_get_buff(fromtable,i,j);
//								buf = String.valueOf(ptr.getString(0));
//								buf +="\t";
//								strall += buf;
//							}
//						}
//						
//						break;
//					}
//					case table_filed_type.tft_uchar.getValue():
//					{
////						memset(buf,0,200);
////						tmpvalue = *(BYTE*)LibTableUtil.INSTANCE.table_get_buff(fromtable,i,j);
////						sprintf(buf,"%u\t",tmpvalue);
////						strall += buf;
//						Pointer ptr = LibTableUtil.INSTANCE.table_get_buff(fromtable,i,j);
//						buf = String.valueOf(ptr.getInt(0));	
//						buf +="\t";
//						strall += buf;
//						break;
//					}
//					case table_filed_type.tft_short.getValue():
//					{
//						colsize = LibTableUtil.INSTANCE.table_col_size(fromtable, j);
//						if (colsize > 2 ) //sizeof(short) == 2
//						{
////							short tmp = 0;
////							char tmpbuf[200];
////							count = colsize/sizeof(short);
////							pBuf = (char*)LibTableUtil.INSTANCE.table_get_buff(fromtable,i,j);	
////							for (int m=0; m<count; m++)
////							{
////								memset(tmpbuf,0,200);
////								memcpy(tmpbuf,pBuf+m*sizeof(short),sizeof(short));
////								tmp = *(short*)(tmpbuf);
////								memset(buf,0,200);
////								sprintf(buf,"%d\t",tmp);
////								strall += buf;
////							}
//							count = colsize/2;
//							Pointer ptr_begin= LibTableUtil.INSTANCE.table_get_buff(fromtable,i,j);
//							short short_buf[] = new short[count];
//							ptr_begin.read(0, short_buf, 0, count);
//							for (int m=0; m<count; m++)
//							{
//								buf = String.valueOf(short_buf[m]);
//								buf += "\t";
//								strall += buf;
//							}
//						}
//						else
//						{
////							memset(buf,0,200);
////							sprintf(buf,"%d\t",LibTableUtil.INSTANCE.table_get_int(fromtable,i,j));
////							strall += buf;
//							int tmpint= LibTableUtil.INSTANCE.table_get_int(fromtable,i,j);
//							buf = String.valueOf(tmpint);
//							buf += "\t";
//							strall += buf;
//						}
//						break;
//					}
//					case table_filed_type.tft_int.getValue():
//					{
//						colsize = LibTableUtil.INSTANCE.table_col_size(fromtable, j);
//						if (colsize > 4)  //4 ==ptr.getShort(0)
//						{
////							short tmp = 0;
////							char tmpbuf[200];
////							count = colsize/sizeof(int);
////							pBuf = (char*)LibTableUtil.INSTANCE.table_get_buff(fromtable,i,j);	
////							for (int m=0; m<count; m++)
////							{
////								memset(tmpbuf,0,200);
////								memcpy(tmpbuf,pBuf+m*sizeof(int),sizeof(int));
////								tmp = *(int*)(tmpbuf);
////								memset(buf,0,200);
////								sprintf(buf,"%d\t",tmp);
////								strall += buf;
////							}
//							count = colsize/4;
//							Pointer ptr_begin= LibTableUtil.INSTANCE.table_get_buff(fromtable,i,j);
//							int int_buf[] = new int[count];
//							ptr_begin.read(0, int_buf, 0, count);
//							for (int m=0; m<count; m++)
//							{
//								buf = String.valueOf(int_buf[m]);
//								buf += "\t";
//								strall += buf;
//							}
//						}
//						else
//						{
////							memset(buf,0,200);
////							sprintf(buf,"%d\t",LibTableUtil.INSTANCE.table_get_int(fromtable,i,j));
////							strall += buf;
//							int tmpint= LibTableUtil.INSTANCE.table_get_int(fromtable,i,j);
//							buf = String.valueOf(tmpint);
//							buf += "\t";
//							strall += buf;
//						}
//						break;
//					}
//					case table_filed_type.tft_int64.getValue():
//					{
////						memset(buf,0,200);
////						sprintf(buf,"%lld\t",LibTableUtil.INSTANCE.table_get_int64(fromtable,i,j));
////						strall += buf;
//						long tmplong = LibTableUtil.INSTANCE.table_get_int64(fromtable,i,j);
//						buf = String.valueOf(tmplong);
//						buf += "\t";
//						strall += buf;
//						break;
//					}
//					case table_filed_type.tft_uint64.getValue():   //这个地方暂时将c的uint64当成java的long处理了，出问题再说
//					{
////						memset(buf,0,200);
////						sprintf(buf,"%llu\t",LibTableUtil.INSTANCE.table_get_uint64(fromtable,i,j));
////						strall += buf;
//						//ptr= 
//						long tmplong = LibTableUtil.INSTANCE.table_get_uint64(fromtable,i,j);
//						buf = String.valueOf(tmplong);
//						buf += "\t";
//						strall += buf;
//						break;
//					}
//					case table_filed_type.tft_timet.getValue():
//					{
////							memset(buf,0,200);
////							UINT64 value = LibTableUtil.INSTANCE.table_get_uint64(fromtable,i,j);
////							dzh_time_t tTime(value);
////							const char* pTtime = tTime.FormatTimeToString();
////							if (pTtime != NULL)
////								strcpy(buf, pTtime);
////							strall += buf;
////							strall += "\t";
//							long tmplong= LibTableUtil.INSTANCE.table_get_uint64(fromtable,i,j);
//							buf = String.valueOf(tmplong);
//							buf += "\t";
//							strall += buf;
//							break;
//					}
//					case table_filed_type.tft_pfloat.getValue():
//					{
////						memset(buf,0,200);
////						((PFloat)LibTableUtil.INSTANCE.table_get_uint64(fromtable,i,j)).GetStrValue(buf);
////						strall += buf;
////						strall += "\t";
////						break;
//						((PFloat)LibTableUtil.INSTANCE.table_get_uint64(fromtable,i,j)).GetStrValue(buf);
//						buf = String.valueOf(tmplong);
//						buf += "\t";
//						strall += buf;
//					}
//					case table_filed_type.tft_vfloat.getValue():
//						memset(buf,0,200);
//						((VFloat)LibTableUtil.INSTANCE.table_get_uint64(fromtable,i,j)).GetStrValue(buf);
//						strall += buf;
//						strall += "\t";
//						break;
//					case table_filed_type.tft_uint.getValue():
//						colsize = LibTableUtil.INSTANCE.table_col_size(fromtable, j);
//						if (colsize > sizeof(int))
//						{
//							short tmp = 0;
//							char tmpbuf[200];
//							count = colsize/sizeof(int);
//							pBuf = (char*)LibTableUtil.INSTANCE.table_get_buff(fromtable,i,j);	
//							for (int m=0; m<count; m++)
//							{
//								memset(tmpbuf,0,200);
//								memcpy(tmpbuf,pBuf+m*sizeof(int),sizeof(int));
//								tmp = *(int*)(tmpbuf);
//								memset(buf,0,200);
//								sprintf(buf,"%u\t",tmp);
//								strall += buf;
//							}
//						}
//						else
//						{
//							memset(buf,0,200);
//							sprintf(buf,"%u\t",LibTableUtil.INSTANCE.table_get_int(fromtable,i,j));
//							strall += buf;
//						}
//						break;
//					case table_filed_type.tft_float.getValue():				
//						colsize = LibTableUtil.INSTANCE.table_col_size(fromtable, j);
//						if (colsize > sizeof(float))
//						{
//							float tmp = 0;
//							char tmpbuf[200];
//							count = colsize/sizeof(float);
//							pBuf = (char*)LibTableUtil.INSTANCE.table_get_buff(fromtable,i,j);	
//							for (int m=0; m<count; m++)
//							{
//								memset(tmpbuf,0,200);
//								memcpy(tmpbuf,pBuf+m*sizeof(float),sizeof(float));
//								tmp = *(float*)(tmpbuf);
//								memset(buf,0,200);
//								sprintf(buf,"%f\t",tmp);
//								strall += buf;
//							}
//						}
//						else
//						{
//							memset(buf,0,200);
//							sprintf(buf,"%f\t",LibTableUtil.INSTANCE.table_get_float(fromtable,i,j));
//							strall += buf;
//						}
//						break;
//					case table_filed_type.tft_double.getValue():
//						colsize = LibTableUtil.INSTANCE.table_col_size(fromtable, j);
//						if (colsize > sizeof(double))
//						{
//							double tmp = 0;
//							char tmpbuf[200];
//							count = colsize/sizeof(double);
//							pBuf = (char*)LibTableUtil.INSTANCE.table_get_buff(fromtable,i,j);	
//							for (int m=0; m<count; m++)
//							{
//								memset(tmpbuf,0,200);
//								memcpy(tmpbuf,pBuf+m*sizeof(double),sizeof(double));
//								tmp = *(double*)(tmpbuf);
//								memset(buf,0,200);
//								sprintf(buf,"%lf\t",tmp);
//								strall += buf;
//							}
//						}
//						else
//						{
//							memset(buf,0,200);
//							sprintf(buf,"%lf\t",LibTableUtil.INSTANCE.table_get_double(fromtable,i,j));
//							strall += buf;
//						}
//						break;
//					case table_filed_type.tft_str.getValue():
//						memset(buf,0,200);
//						sprintf(buf,"%s\t",(char*)LibTableUtil.INSTANCE.table_get_str(fromtable,i,j));
//						strall += buf;
//						break;
//					case table_filed_type.tft_table.getValue():
//						PrintfTable(LibTableUtil.INSTANCE.table_get_table(fromtable,i,j),strall);
//						break;
//					default:
//						break;
//
//					}
//				}
//				strall += "\r\n";
//			}
//		}
//		return true;
//	}

}
