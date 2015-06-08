package com.atopcloud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 用于清理数据库
 *
 * @author Administrator
 */
public class MyDatabaseUtil {
//	private static String db_ip ="10.15.144.92";
//	private static String db_port="3306";
//	private static String db_user ="mysql";
//	private static String db_pwd ="mysql";
//	private static String db_dbname ="per_test_result";
//	private static String db_table ="moneytestdata";

    private static String url = "jdbc:mysql://10.15.201.24:3306/INETACT";
    private static String driver = "com.mysql.jdbc.Driver";

    private static Connection con = null;
    private static Statement stmt = null;

    /**
     * 执行更新语句
     *
     * @param ip
     * @param port
     * @param user
     * @param pwd
     * @param dbname
     * @param sql
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static boolean doUpdateSql(String ip, String port, String user, String pwd, String dbname, String updatesql) throws SQLException, ClassNotFoundException {
        url = "jdbc:mysql://" + ip + ":" + port + "/" + dbname;

        //open
        Class.forName(driver);
        con = DriverManager.getConnection(url, user, pwd);
        //con.setAutoCommit(false);
        //System.out.println("Open db ok!");
        //excute sql
        stmt = con.createStatement();
        int num = stmt.executeUpdate(updatesql);
        //con.commit();
        //System.out.println("deleted " + num + " rows!");
        //close
        if (stmt != null) stmt.close();
        if (con != null) con.close();
        //System.out.println("Close db ok!");
        return true;
    }

    public static int doQuerySql(String ip, String port, String user, String pwd, String dbname, String queryesql) throws SQLException, ClassNotFoundException {
        url = "jdbc:mysql://" + ip + ":" + port + "/" + dbname;

        //open
        Class.forName(driver);
        con = DriverManager.getConnection(url, user, pwd);
        con.setAutoCommit(false);
        //System.out.println("Open db ok!");
        //excute sql

        stmt = con.createStatement();
        ResultSet result = stmt.executeQuery(queryesql);
        con.commit();
        //System.out.println("deleted " + num + " rows!");
        int num = 0;
        if (result != null) {
            result.last();
            num = result.getRow();
        }
        //close
        if (stmt != null) stmt.close();
        if (con != null) con.close();
        //System.out.println("Close db ok!");
        return num;
    }

    public static int dosureQuerySql(String queryesql) throws SQLException, ClassNotFoundException {
        //	url = "jdbc:mysql://" + ip + ":" + port + "/" + dbname;

        String user = "root";
        String pwd = "";
        //open
        Class.forName(driver);
        con = DriverManager.getConnection(url, user, pwd);
        con.setAutoCommit(false);
        //System.out.println("Open db ok!");
        //excute sql

        stmt = con.createStatement();
        ResultSet result = stmt.executeQuery(queryesql);
        con.commit();
        //System.out.println("deleted " + num + " rows!");
        int num = 0;
        if (result != null) {
            result.last();
            num = result.getRow();
        }
        //close
        if (stmt != null) stmt.close();
        if (con != null) con.close();
        //System.out.println("Close db ok!");
        return num;
    }

}
