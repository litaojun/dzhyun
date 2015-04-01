package com.gw.hqserver.util;

public class ParamsConfigure {		
		//login params
		public String  ip = "10.15.108.76";
		public String  port = "5184";
		public String  app_name="";
		public String  app_developer="";
		public String  app_ver="";
		public String  app_build="";
		public String  user_name = "dzhappuser3";
		public String  user_pwd = "123456";

		public String getConfigure()
		{
			return 	"server_address="
							+ip
							+"&server_port="
							+port
							+"&app_name="
							+app_name
							+"&app_developer="
							+app_developer
							+"&app_ver="
							+app_ver
							+"&app_build="
							+app_build
							+"&user_name="
							+user_name
							+"&user_password="
							+user_pwd;
		}
		
		
}
