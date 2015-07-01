package com.gw.dzhyun.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonFormatAssert {
	 public int  assertJsonArray(JSONArray a,String[] arr)
     {
		  int retcode=0;
	  	   if(a==null)
	  		   return -1;
	  	   for(int i=0;i<a.size();i++)
	  	   {
	  		  retcode = this.assertJson(a.getJSONObject(i),arr);
	  		  if(retcode == 1)
	  			  break;
	  	   }
	  	   return retcode;
     }
     public int assertJson(JSONObject a,String[] arr)
     {
  	      int retcode = 0;
  	    System.out.println("litaojun0000000");
  	      System.out.println("a.size()="+a.size());
  	      if(a.size() != arr.length)
  	    	 return 1;
  	    System.out.println("litaojun1111111"+a.toJSONString());
  	      for(int i=0;i<arr.length;i++)
  	      {
  	    	  if(!a.containsKey(arr[i]))
  	    	  {
  	    		System.out.println("litaojun222222");
  	    		 System.out.println("arr[i]="+arr[i]);
  	    		retcode = 1;
  	    		break;
  	    	  }
  	      }
  	    System.out.println("litaojun333333");
  	      return retcode;
     }
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
          
	}

}
