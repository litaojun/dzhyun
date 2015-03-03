package com.gw.hqserver.util;
/**
 * 处理与行情服务系统的QTRS的通信。
 */
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;

/**
 * 行情服务系统相关处理类。处理与行情服务系统的QTRS的通信。使用jna技术。
 * @author Administrator
 *
 */
public class MyHQServerUtil {
//	//CRootInfo
//	/**
//	 * 模拟C语言的结构体:
//	 typedef struct CRootInfo
//	{
//		int m_nThreld;
//		int m_nLogFileSize;
//		char* m_pRootProxyName;
//	}*LPCROOTINFO;
//	 * @author Administrator
//	 *
//	 */
//	public static  class CRootInfo extends Structure {
//		public static class ByReference extends CRootInfo implements Structure.ByReference{};
//		public static class ByValue extends CRootInfo implements Structure.ByValue{};
//		public int m_nThreld;
//		public int m_nLogFileSize;
//		public String m_pRootProxyName;
//
//	}
//
//	//服务器返回结构体
//	/*
//	int m_nType;
//	int m_nReqSeq;
//	int m_nResSeq;
//	int m_nState;
//	table_t m_tResult; //响应返回数据
//	 */
//	public class DSResponse
//	{
//		int m_nType;
//		int m_nState;
//		int m_nReqSeq;
//		int m_nResSeq;
//		Object m_tResult;  //存放数据
//	}
//	
//	//动态库类
//	public interface HQServerLib extends Library
//	{
//		HQServerLib INSTANCE = (HQServerLib)Native.loadLibrary("Alib", HQServerLib.class);   //alib不带后缀，保证可以跨平台
//		
//		//与c/c++  lib映射的函数
//		public int alibInit();
//		public void alibDestroy();
//		/**
//		  * @brief 创建根数据代理
//		  * @param[in] pRootInfo 根代理的名称，日志等配置
//		  * @param[in] configure 配置信息，server_address=ip&server_port=port类似格式
//		  * @param[in] callback 根数据代理连接状态发生变化时的回调函数
//		  * @param[in] usedata 由应用赋值,异步回调函数中原值带回
//		  * @param[in&out] pIntRootProxySeq 返回根代理描述符 >0 成功
//		  * @retval 非空 创建成功
//		  * @remark 创建根数据代理，每个应用必须创建至少一个根数据代理，根数据代理会连接到数据代理，以获取数据；根数据代理与数据代理间的连接状态发生变化时，会通过回调函数通知上层。
//		*/
//		//void*  proxyCreateRoot(CRootInfo* pRootInfo, const char* configure, DSCallback callback, void* usedata, int* pIntRootProxySeq);
//		public Object proxyCreateRoot(CRootInfo pRootInfo, String configure, DSCallback callback, Object usedata, int nRootProxySeq);
//		public int proxyCreateSub (int nParentProxySeq, const char* configure, void* pObj);
//		public int proxyCancelAllReq (int nProxySeq, void* pObj);
//		public int proxyDestroy(int nProxySeq, void* pObj);
//		public int syncReqData(int nProxySeq, const char* request, DSResponse* response, void* pObj);
//		public int asyncReqData(int nProxySeq, const char* request, DSResponse* response, DSCallback callback, void* usedata, void* pObj);
//		public int cancelReq(int nReqSeq , void* pObj);
//		
//		//group请求
//		public int groupCreate(int nProxySeq, DSCallback callback, void*usedata, void* pObj);
//		public  int groupAsyncReqData(int nGroupSeq, const char* request, DSResponse* response, void* pObj);
//		public int groupReqEnd(int nGroupSeq, void* pObj);
//		public int groupCancel(int nGroupSeq, void* pObj);
//		public int groupDestroy(int nGroupSeq, void* pObj);
//		
//	}	
//	
//	
//	
}

//class CRootInfo
//{
//	int m_nThreld;
//	int m_nLogFileSize;
//	String m_pRootProxyName;
//}
//
//class DSResponse
//{
//	int m_nType;
//	int m_nState;
//	int m_nReqSeq;
//	int m_nResSeq;
//	table_t m_tResult;
//
//	public DSResponse()
//	{
//		m_nType = -1;
//		m_nState = -1;
//		m_nReqSeq = -1;
//		m_nResSeq = -1;
//		m_tResult = null;
//	}
//}
