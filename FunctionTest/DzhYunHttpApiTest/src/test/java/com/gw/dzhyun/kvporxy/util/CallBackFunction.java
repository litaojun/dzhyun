/**
 * @classnmae CallBackFunction.java
 * @username  Litaojun
 * @Description TODO
 */
package com.gw.dzhyun.kvporxy.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Litaojun
 * @date   2015年12月17日
 */
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public  @interface CallBackFunction {
	 String id() default "litaojun";
}
class CallBackTool {
	public CallBackTool()
	{
		
	}
	public static HashMap<String,Method> getMethodByAnnotation(Class base,Class annontation)
	{
		HashMap<String,Method> retmd = new HashMap<String,Method>();
		Method[] tmm = base.getDeclaredMethods();
		for(Method a:tmm)
		{
			boolean sign = a.isAnnotationPresent(annontation);
			if(sign)
			{
               
               Object tm = a.getAnnotation(annontation);
               if(tm instanceof CallBackFunction)
               {
            	   CallBackFunction o = (CallBackFunction)tm;
            	   String id = o.id();
            	   retmd.put(id, a);
               }
			}
		}
		return retmd;
	}
	public static void reflectMethonCallBack(Object o,Method method,Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		if(o!=null)
		     method.invoke(o, args);
	}
	
}
