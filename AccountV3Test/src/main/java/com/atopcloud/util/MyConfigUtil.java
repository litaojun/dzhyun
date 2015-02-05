package com.atopcloud.util;

/**
 * 封装获取测试配置的方法。
 * @author Administrator
 *
 */
public class MyConfigUtil {	
	public static String getConfig(String key){
		//读取环境变量
		String envPath = System.getProperty("user.dir") + "\\config\\env.properties";
		PropertiesManager pm = new PropertiesManager(envPath);
//		 ip = pm.getValue("ip");
//		 port=pm.getValue("port");
		return pm.getValue(key);
	}
}
