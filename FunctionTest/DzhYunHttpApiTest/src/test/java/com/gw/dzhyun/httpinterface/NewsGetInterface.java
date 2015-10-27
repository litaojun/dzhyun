package com.gw.dzhyun.httpinterface;

import org.xml.sax.SAXException;

public interface NewsGetInterface 
{
	  /*获取单个新闻服务列表正确*/
      public void testOneStockOnetype() throws SAXException, Exception; 
      
      /*获取多个新闻服务列表正确。*/
      public void testOneStockNinetype() throws SAXException, Exception;
      
      /*获取单个研报服务列表正确。*/
      public void testMulStockOnetype() throws SAXException, Exception;
      
      /*获取多个研报服务列表正确。*/
      public void testMulStockNinetype() throws SAXException, Exception;
      
      /*输入股票代码参数obj为空。*/
      public void testNullStock() throws SAXException, Exception;
      
      /*输入类型参数type为空。*/
      public void testNulltype() throws SAXException, Exception;
      
      /*OBJ股票代码不存在。*/
      public void testErrorStock() throws SAXException, Exception; 
      
      /*TYPE参数不等于1,9。*/
      public void testErrorType() throws SAXException, Exception;
      
      /*多出其他参数。*/
      public void testOther() throws SAXException, Exception; 
      
      /*OBJ股票代码有200个*/
      public void testLargeData() throws SAXException, Exception; 
}
