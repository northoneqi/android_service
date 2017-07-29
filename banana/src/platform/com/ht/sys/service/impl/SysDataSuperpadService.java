package com.ht.sys.service.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ht.sys.bean.SysDataSuperpad;
import com.ht.sys.bean.SysUser;
import com.ht.sys.dao.impl.SysDataSuperpadDao;
import com.ht.sys.dao.impl.SysUserDao;
import com.ht.sys.util.db.QueryUtil;
import com.ht.sys.util.db.SqlBackUpHelper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Service("sys_datasuperpadService")
public class SysDataSuperpadService extends BaseServiceImpl<SysDataSuperpad>{
	@Autowired private SysDataSuperpadDao sysDataSuperpadDao;
	@Autowired private SysUserDao sysUserDao;
	@Autowired private PasswordEncoder passwordEncoder;
	private XStream xStream=new XStream(new DomDriver());//解析器
	
	public String testConnection(SysDataSuperpad sysDataSuperpad){
		String res="";
		try {
			sysDataSuperpadDao.testConnection(sysDataSuperpad);
			res="{success:true,msg:\"连接成功!\"}";
		} catch (Exception e) {
			res="{success:false,msg:\"错误信息"+e.getLocalizedMessage().replace("\"", "'")+"\"}";
		}
		return res;
	}
	public String saveConnection(SysDataSuperpad sysDataSuperpad,OutputStream ops){
		String res="";
		try{
			sysDataSuperpadDao.testConnection(sysDataSuperpad);//检测一下是否是正确的连接
			xStream.toXML(sysDataSuperpad,ops);
			res="{success:true,msg:'保存成功!'}";
		}catch(Exception e){
			res="{success:false,msg:\"错误信息"+e.getLocalizedMessage()+"\"}";
		}
		return res;
	}
	public SysDataSuperpad readConnection(InputStream ips){
		SysDataSuperpad sysDataSuperpad=null;
		try{
			sysDataSuperpad=(SysDataSuperpad)xStream.fromXML(ips);
		}catch(Exception e){
			e.printStackTrace();
		}
		return sysDataSuperpad;
	}
	
	public String leadingInConnection(SysDataSuperpad sysDataSuperpad,String tables){
		String res="";
		SqlBackUpHelper helper = new SqlBackUpHelper();
		try{
			if(sysDataSuperpadDao.trantData(sysDataSuperpad, tables,helper.getDatabase())){
				 /**同步完成后更新所同步用户密码**/
//				 QueryUtil queryUtil=new QueryUtil();
//				 queryUtil.put("userName", "!=", "admin");
//				 List<SysUser> userList=sysUserDao.findByCondition(queryUtil);
//				 for(SysUser sysUser:userList){
//					 sysUser.setPassword(passwordEncoder.encodePassword("0000",sysUser.getUserName()));
//					 sysUserDao.update(sysUser);
//				 }
				 res="{success:true,msg:'数据传输成功!'}";
			}else{
				res="{success:false,msg:'数据传输失败!'}";
			}
		}catch(Exception e){
			res="{success:false,msg:\"错误信息"+e.getLocalizedMessage().replace("\"", "'")+"\"}";
		}
		return res;
	}
	
	//导出数据到用友数据库中
	public String exportData(SysDataSuperpad sysDataSuperpad,String tables) throws Exception{
		SqlBackUpHelper helper = new SqlBackUpHelper();
		//sysDataSuperpadDao.exportData(sysDataSuperpad, tables, helper.getDatabase());
		return "导出成功";
	}
	
	public SysDataSuperpadDao getSysDataSuperpadDao() {
		return sysDataSuperpadDao;
	}
	public void setSysDataSuperpadDao(SysDataSuperpadDao sysDataSuperpadDao) {
		super.setBaseDao(sysDataSuperpadDao);
		this.sysDataSuperpadDao = sysDataSuperpadDao;
	}
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	public SysUserDao getSysUserDao() {
		return sysUserDao;
	}
	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
	
}
