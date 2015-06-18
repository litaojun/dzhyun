package com.atopcloud.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

//取当前时间缀
public class MyCurrentTime {


    public static String MyTime() {
//	 	long MyTime1 = System.currentTimeMillis();
//	 	System.out.print(MyTime1);
//		return MyTime1;
        Date myDate = new Date(System.currentTimeMillis());
        SimpleDateFormat NewTime = new SimpleDateFormat("yyyyMMddHHmmss");
        String MyTime = NewTime.format(myDate);
        return MyTime;

    }
/*	
    public static void main(String arg[]) {
		MyCurrentTime ttMyTime = new MyCurrentTime();
	    long test = ttMyTime.MyTime()	;
	}
*/
}

