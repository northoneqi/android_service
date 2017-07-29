package com.ht.sys.util;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
* <p>类功能说明: 系统运行环境帮助类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-14 下午07:24:33
* @version V1.0
*/
public class SysEnvironmentUtil {

	public static Map<String, String> sysMap = new HashMap<String, String>();
	
	static {
		/**
		 * 设置系统运行环境
		 * */
		sysMap.put("user.name", System.getProperty("user.name")); //用户的账户名称
		sysMap.put("user.dir", System.getProperty("user.dir"));  //用户工作目录
        sysMap.put("user.home", System.getProperty("user.home"));  //用户的home路径
        sysMap.put("java.class.path", System.getProperty("java.class.path"));   //类所在的路径
        sysMap.put("os.name", System.getProperty("os.name"));  //操作系统的名称
        sysMap.put("os.version", System.getProperty("os.version"));  //操作系统的版本 
        sysMap.put("os.arch", System.getProperty("os.arch"));  //操作系统的架构
        sysMap.put("java.vm.version", System.getProperty("java.vm.version"));  //虚拟机实现的版本
        sysMap.put("java.vm.vendor", System.getProperty("java.vm.vendor"));  //虚拟机实现的生产商
        sysMap.put("java.io.tmpdir", System.getProperty("java.io.tmpdir"));  //默认临时文件路径
        sysMap.put("java.class.version", System.getProperty("java.class.version"));  //java类格式化的版本
        sysMap.put("java.version", System.getProperty("java.version"));  //java运行环境的版本
        sysMap.put("java.vendor", System.getProperty("java.vendor"));  //java运行环境的生产商
        sysMap.put("java.home", System.getProperty("java.home")); //java的安装路径
        
        //内存监控
	}
	
	public static void main(String[] args) throws UnknownHostException{
		Runtime runTime= Runtime.getRuntime();
		System.out.println(runTime.freeMemory()/1024/1024);  //剩余内存
		System.out.println(runTime.totalMemory()/1024/1024); //已分配内存
		System.out.println(runTime.maxMemory()/1024/1024); //最大内存
	}
}
