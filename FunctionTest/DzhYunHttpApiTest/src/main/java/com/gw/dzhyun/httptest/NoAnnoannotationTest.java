package com.gw.dzhyun.httptest;

import junit.framework.*;

public class NoAnnoannotationTest extends TestCase {

	public NoAnnoannotationTest(String name)
	{
		super(name);
	}
	
	@Override
	public void setUp()
	{
		System.out.println("初始化" );	
	}
	
	@Override
	public void tearDown()
	{
		System.out.println("结束操作" );			
	}
	
	public void testXXX()
	{
		System.out.println("这是无注解代码示例" );
		fail("这是无注解代码示例" );
	}
}
