package com.gw.dzhyun.util;

import java.util.HashMap;

public class TestCaseStyle 
{
    private String functionPoint;
    private String preConditions;
    private String opeSteps;
    private HashMap inputData;
    private String[] expectedResult;
    private HashMap preData;
    private String testResult;
    public TestCaseStyle(String[] a)
    {
    	inputData =new HashMap();
    	preData = new HashMap();
    	iniData(a);
    }
    public String getOpeSteps()
    {
    	return this.opeSteps;
    }
    public HashMap getInputData()
    {
    	return this.inputData;
    }
    public HashMap getPreData()
    {
    	return this.preData;
    }
    public void iniData(String[] a)
    {
    	if(a==null || a.length != 7)
    		return;
    	functionPoint = a[0];
    	preConditions = a[1];
    	opeSteps = a[2];
//    	System.out.println("a[0]="+a[0]);
//    	System.out.println("a[1]="+a[1]);
//    	System.out.println("a[2]="+a[2]);
//    	System.out.println("a[3]="+a[3]);
    	String[] temp = a[3].split("\n");
    	for(int i=0;i<temp.length;i++)
    	{
    		//System.out.println("i="+i+";temp="+temp[i]);
    		String aa = temp[i].split("=")[0];
    		String bb = temp[i].split("=")[1];
    		//System.out.println("aa="+aa+" ;bb="+bb);
    		inputData.put(aa,bb);
    	
    	}
    	expectedResult =  a[4].split("\n");
    	temp = a[5].split("\n");
    	for(int i=0;i<temp.length;i++)
    	{
    		preData.put(temp[i].split("=")[0], temp[i].split("=")[1]);
    	}
    	testResult = a[6];
    }
    public String toString()
    {
        String a= inputData.toString();
        String b =preData.toString();
        return a+b;
    }
    public Object[] traveToDataArray()
    {
    	Object[] a=new Object[4];
    	a[0]=inputData.get("code").toString();
    	a[1]=inputData.get("type").toString();
    	//a[2]=preData.get("errcode");
    	if(preData.get("errcode") == null)
    		a[2]=null;
    	else
    		a[2]=preData.get("errcode").toString();
    	a[3]=Integer.parseInt(preData.get("retcode").toString());
    	//System.out.println("a[0]="+a[0]+";;a[1]="+a[1]+";;a[2]="+a[2]+";;a[3]="+a[3]);
    	return a;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
