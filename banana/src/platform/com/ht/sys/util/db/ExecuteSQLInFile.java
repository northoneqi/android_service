package com.ht.sys.util.db;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 目的：这个程序用来从sql指令文件中读取sql语句来进行数据库交互
 * 总结：程序可以融合为一个整体主要与三个对象有关-----Connection对象，Statement对象，ResultSet对象
 * 		1.Scanner对象还有一个比较实用的构造函数-----public Scanner (File filename)，可以从文件扫描数据的，不错哟（实现了sql语句与程序的分离了）
 * 		2.在获得有效的sql语句时，trim函数比较实用------trim() 函数返回副本，但是忽略前导空白和尾部空白，蛮好的
 * 		3.当所用的连接是短时间的，就可以将连接对象的close函数放在finally句块中，而无需考虑关闭语句和结果集对象了
 * @author sornor
 * @version 1.0
 */
public class ExecuteSQLInFile {
	
	public static void excute() {
		try {
			System.out.println(ExecuteSQLInFile.class.getClassLoader().getResource("").getPath()+"init.sql");
			InputStream ips = new BufferedInputStream(new FileInputStream(ExecuteSQLInFile.class.getClassLoader().getResource("").getPath()+"init.sql"));
			
			String path = ExecuteSQLInFile.class.getClassLoader().getResource("").getPath()+"init.sql";
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(path), "utf8"));
			
			Connection conn = ConnectionDB.getConnection();	//连接数据库
			
			try {
				Statement sta = conn.createStatement();		//获得语句对象
				String inStr;
				while ((inStr = br.readLine()) != null) {
					String sql = inStr;		//sql是要执行的sql语句
					if(sql.trim().endsWith(";")) {		//trim（）函数忽略前导空白和尾部空白了
						sql = sql.trim();	
						sql = sql.substring(0,sql.length()-1);		//获得不包含“;”的sql语句
					}
					//执行sql语句了
					try{
						boolean hasResultSet = sta.execute(sql);
						if(hasResultSet) ConnectionDB.showResultSet(sta);	
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			  } catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
					conn.close();
				} 
		}
		catch (FileNotFoundException e1) {
			throw new RuntimeException(e1.getMessage());
		}
		catch (SQLException e2) {
			throw new RuntimeException(e2.getMessage());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		ExecuteSQLInFile.excute();
	}
}
