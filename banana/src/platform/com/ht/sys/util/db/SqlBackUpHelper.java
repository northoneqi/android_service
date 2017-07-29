/**
* @Title: SqlBackUp.java 
* @Package com.bluefat.sys.util.db 
* @Description: 数据库备份与还原 
* @author qj  
* @date 2013-8-18 上午12:15:23 
* @version V1.0
*/
package com.ht.sys.util.db;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
* <p>类功能说明:数据库备份还原帮助类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午11:11:36
* @version V1.0
*/
public class SqlBackUpHelper {
	private String databaseType = "mysql"; //数据库类型
	private String backupPath;
	private String serverUrl;
	private String database;
	private String user;
	private String password;
	public SqlBackUpHelper(){
		Properties properties = new Properties();
		InputStream ips;
		try {
			ips = new BufferedInputStream(new FileInputStream(SqlBackUpHelper.class.getClassLoader().getResource("").getPath()+"/application.properties"));
			properties.load(ips);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("没有找到文件【application.properties】");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读取文件【application.properties】发生错误");
		}
		backupPath = new File(SqlBackUpHelper.class.getClassLoader().getResource("").getPath()+"/").getParentFile().getPath()+"/dbback/";
		serverUrl = properties.getProperty("dataSource.serverName");
		database = properties.getProperty("dataSource.databaseName");
		user = properties.getProperty("dataSource.username");
		password = properties.getProperty("dataSource.password");
		String dialog = properties.getProperty("hibernate.dialect");
		if("org.hibernate.dialect.SQLServerDialect".equals(dialog)){
			databaseType = "sqlServer";
			
		}else if("org.hibernate.dialect.MySQLDialect".equals(dialog)){
			databaseType = "mysql";
			
		}else if("org.hibernate.dialect.Oracle10gDialect".equals(dialog)){
			databaseType = "orcal";
		}
	}
	
	

	public String getDatabaseType() {
		return databaseType;
	}



	/**
	 * <p>数据库备份 </p>
	 * @author qj  
	 * @date 2013-8-18 上午12:16:39 
	 * @param backupPath  备份的根目录
	 * */
	public String[] backUp(){
		String path[] = new String[3];
		
		try {
			Runtime rt = Runtime.getRuntime();
			path[0] = database+"_"+new SimpleDateFormat("yyyy_mm_dd_HH_mm_ss").format(new Date());
			String backupFile = backupPath;
			if("mysql".equals(databaseType)){
				path[0]+=".sql";
				// 调用 mysql 的 cmd:
				rt.exec("cmd /c mysqldump -h"+serverUrl+"  -u"+user+" -p"+password
							+" "+database+" > "+backupFile);// 设置导出编码为utf8。这里必须是utf8
				System.out.println("cmd /c mysqldump -h"+serverUrl+"  -u"+user+" -p"+password
							+" "+database+" > "+backupFile);
			}else if("sqlServer".equals(databaseType)){
				path[0]+=".bak";
				path[1]=database;
				path[2]=backupFile;
			}
			System.out.println("备份成功");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}
	
	/**
	 * <p>还原数据库</p>
	 * @author qj  
	 * @date 2013-8-20 上午8:42:23 
	 * @param path  数据库文件的路径
	 */
	public String load(String fileName){
		try {
			Runtime rt = Runtime.getRuntime();
			if("mysql".equals(databaseType)){
				// 调用 mysql 的 cmd:
				// rt.exec("create database demo");
				Process child = rt.exec("mysql -u"+user+" -p"+password+" "+database);
				OutputStream out = child.getOutputStream();// 控制台的输入信息作为输出流
				String inStr;
				StringBuffer sb = new StringBuffer("");
				String outStr;
				BufferedReader br = new BufferedReader(new InputStreamReader(
						new FileInputStream(backupPath+fileName), "utf8"));
				while ((inStr = br.readLine()) != null) {
					sb.append(inStr + "\r\n");
				}
				outStr = sb.toString();
	
				OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
				writer.write(outStr);
				// 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
				writer.flush();
				// 别忘记关闭输入输出流
				out.close();
				br.close();
				writer.close();
				return null;
			}
			else if("sqlServer".equals(databaseType)){
				String sql="use master;alter database "+database+" set offline with rollback immediate; ";
				sql += " restore database  "+database+"  from disk='"+(backupPath+fileName)+"'";
				sql +=  "with replace "; //解决备尚未备份数据库 数据库 的日志尾部
				sql += "alter database  "+database+" set onLine with rollback immediate;use "+database;
				return sql;
			}else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	
	public String getDatabase() {
		return database;
	}

	public static void main(String args[]){
		SqlBackUpHelper back = new SqlBackUpHelper();
		//back.backUp("E:/mysql/");
		back.load("E:/mysql/bluefat_2013_08_18_13_52_37.sql");
	}
}
