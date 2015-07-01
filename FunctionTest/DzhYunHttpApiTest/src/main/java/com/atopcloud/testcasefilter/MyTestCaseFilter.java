/**
 * 
 */
package com.atopcloud.testcasefilter;

import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;

/**
 * 我的测试基类，继承自Filter。
 * @author Administrator
 *
 */
public class MyTestCaseFilter extends Filter {
	public String methodname=null;
	
	public  MyTestCaseFilter(String name) {
		// TODO Auto-generated constructor stub
		this.methodname = name;
//		System.out.println("constructing "+name);
	}
	public  MyTestCaseFilter() {
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see org.junit.runner.manipulation.Filter#shouldRun(org.junit.runner.Description)
	 */
	@Override
	public boolean shouldRun(Description description) {
		// TODO Auto-generated method stub
		String name = description.getDisplayName().split("\\(")[0].trim();
		if(name.equals(this.methodname.trim()))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see org.junit.runner.manipulation.Filter#describe()
	 */
	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return methodname + "should run";
	}

}
