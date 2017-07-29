package com.ht.sys.util.db;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 目的：   从配置文件JDBC.properties中获取数据库连接信息，然后与数据库连接
 * 	总结：1.ProPerties是一个持久的属性集，属性列表中的每一个键和对应的值都是一个字符串
 * 		  2.可以由Statement对象直接得到ResultSet ，要用到的函数是  Statment -- getObjectListSet();
 * @author sornor
 * @version　1.0
 */
public class ConnectionDB {
	/**
	 * 连接数据库
	 */
	public static Connection getConnection () {
		try {
			//先定义一个输入文件流了
			InputStream input = new BufferedInputStream(new FileInputStream(SqlBackUpHelper.class.getClassLoader().getResource("").getPath()+"/application.properties"));
			Properties prop = new Properties();	//创建一个无默认值的空属性列表
			prop.load(input);	//从输入流中读取属性列表
			String drivers = prop.getProperty("dataSource.driverClassName");
			if(drivers != null)  
				Class.forName(drivers);		//反射的机制，创建drivers对象
			String serverName = prop.getProperty("dataSource.serverName");
			String databaseName = prop.getProperty("dataSource.databaseName");
			String url = prop.getProperty("dataSource.url");
			url = url.replace("${dataSource.serverName}", serverName);
			url = url.replace("${dataSource.databaseName}", databaseName);
			String name = prop.getProperty("dataSource.username");
			String password = prop.getProperty("dataSource.password");
			
			Connection conn = DriverManager.getConnection(url,name,password);
			System.out.println("good");
			return conn;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*public static void main(String[] args) {
		ConnectionDB.getConnection();
	}*/
	
	
	/**
	 *	目的：展示数据库的结果集，也就是数据库的内容
	 *	注意了：只有当语句对象中有结果集时才会执行这个程序的，也就是说只有执行select操作时才会执行这段代码了
	 */
	public static void showResultSet (Statement sta) {
		ResultSet rs = null;
		int columns = 0;
		ResultSetMetaData rsmd = null;
		try {
			 rs = sta.getResultSet();		//***获取语句对象的结果集了
			  rsmd = rs.getMetaData();		//获得元数据
			columns = rsmd.getColumnCount();	//查询结果中的列数了
			
			//下面输出列名
			for(int i = 1;i <= columns;i++) {
				if (i > 1) System.out.print(", ");
				System.out.print(rsmd.getColumnLabel(i));		
			}
			System.out.println();
			
			//输出数据
			while(rs.next()) {
				for(int j = 1;j <= columns;j++) {
					if(j > 1) System.out.print(", ");
					System.out.print(rs.getString(j));		//如果第j列的数据类型不是String类型，getString（）方法会自动将数据转换成String型
				}
				System.out.println();
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		System.out.println("show successfully!");
		
	}
}
