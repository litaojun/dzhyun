package com.gw.dzhyun.util;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class TestCaseManagr {
	private TestCaseStyle[] caseList =null;
	private List<TestCaseStyle> curList = new LinkedList();
	
	public TestCaseManagr(String path,String weet,int row) throws IOException
	{
			ExcelCommon a=new ExcelCommon(path,weet);
			String b[][] = a.readSheetToArray(row,7);
			caseList = new TestCaseStyle[row];
			for(int i=0;i<b.length;i++)
			{
				//System.out.println("i=========================="+i);
				caseList[i]=new TestCaseStyle(b[i]);
				curList.add(caseList[i]);
				//System.out.println(caseList[i].toString());
			}
			
	}

	public Collection getCurList(String[] classPara)
	{
		List a =new LinkedList();
		for(int i = 0;i<this.curList.size();i++)
		{
			a.add(new Object[]{this.curList.get(i),classPara});
		}
		return a;
	}
	public Collection traveCollection()
	{
		List a =new LinkedList();
		//System.out.println("caseList.length="+caseList.length);
		for(int i=0;i<caseList.length;i++)
		{
			//System.out.println("i="+i);
			a.add(caseList[i].traveToDataArray());
		}
		return a;
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		try {
			TestCaseManagr a = new TestCaseManagr("E:\\环境文档\\测试用例.xlsx","Sheet1",2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
