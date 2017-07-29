/**
* @Title: PropertyReader.java 
* @Package com.bluefat.sys.util 
* @Description: 读取properties文件的帮助类
* @author qj  
* @date 2013-8-22 下午6:02:38 
* @version V1.0
*/
package com.ht.sys.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
* <p>类功能说明: 属性读取帮助类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午11:05:44
* @version V1.0
*/
public class PropertyReader {

	/**
	 * <p>读取属性文件</p>
	 * @author qj  
	 * @date 2013-8-22 下午6:10:02 
	 * @param fileName  属性文件
	 * @param key  读取的属性名称
	 * @return  返回读取的值
	 */
	public static String read(String fileName, String key){
		Properties properties = new Properties();
		String value = "";
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(fileName),"UTF-8");
			BufferedReader reader=new BufferedReader(read);
			properties.load(reader);
			value = properties.getProperty(key);
			reader.close();
			read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("没有找到文件【application.properties】");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读取文件【application.properties】发生错误");
		}
		
		return value;
	}
}
