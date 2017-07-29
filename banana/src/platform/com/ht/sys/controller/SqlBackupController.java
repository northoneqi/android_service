package com.ht.sys.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ht.sys.bean.SqlBackup;
import com.ht.sys.service.impl.SqlBackupService;
import com.ht.sys.util.Pager;
import com.ht.sys.util.RequestUtil;
import com.ht.sys.util.db.QueryUtil;
import com.ht.sys.util.db.SqlBackUpHelper;

/** 
 * <p>类功能说明: 数据库备份与还原</p>
 * <p>Title: SysLogController.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-7-31 下午08:59:43
 * @version V1.0
 */
@Controller("sys_sqlBackupController")
@RequestMapping("sys/sqlbackup/")
public class SqlBackupController extends SysController<SqlBackup>{

	private SqlBackupService sqlBackupService;

	/**
	 * <p>数据库备份与还原列表页</p>
	 * @author qj  
	 * @date 2013-7-31 下午10:26:58 
	 * @return
	 */
	@RequestMapping("listPage")
	public String sqlBackupListPage(){
		return "sys/sqlbackup_list";
	}
	
	/**
	 * <p>查询数据库备份与还原信息</p>
	 * @author qj  
	 * @date 2013-7-31 下午10:27:57 
	 * @param pager  分页信息
	 */
	@RequestMapping("query")
	public void query(HttpServletRequest request, HttpServletResponse response,
			Pager<SqlBackup> pager){
		RequestUtil.printAttritbute(request);
		try{
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.putOrder("code+0", "desc");
			sqlBackupService.findByPager(pager, queryUtil);
			super.setGridJson(response, pager.getTotal(), pager.getObjectList());
			logger.info("查询数据库备份与还原信息");
		}catch(Exception e){
			e.printStackTrace();
			super.setGridJson(response, "系统繁忙，请稍后再试");
			super.printLog(request, "查询数据库备份与还原信息异常【"+e.getMessage()+"】");
		}
	}
	
	/**
	 * <p>手动备份数据库</p>
	 * @author qj  
	 * @date 2013-8-20 下午10:49:20 
	 * @param request
	 * @param response
	 */
	@RequestMapping("backup")
	public void backup(HttpServletRequest request, HttpServletResponse response){
		try{
			SqlBackUpHelper helper = new SqlBackUpHelper();
			String[] backPath = helper.backUp();
			SqlBackup backup = new SqlBackup();
			backup.setCode(sqlBackupService.getCode("code"));
			backup.setName("手动备份");
			backup.setFileName(backPath[0]);
			if(sqlBackupService.BackBpDataForSQL(backPath)){
				sqlBackupService.save(backup);
				super.setAjaxMsg(response, true, "备份成功");
				super.printLog(request, "手动备份数据库");
			}else{
				super.setAjaxMsg(response, false, "备份失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg(response, false, "备份失败");
			super.printLog(request, "手动备份数据库", e);
		}
	}
	
	
	/**
	 * <p>还原数据库</p>
	 * @author qj  
	 * @date 2013-8-20 下午11:18:14 
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping("load")
	public void load(HttpServletRequest request, HttpServletResponse response,
			String id){
		try{
			SqlBackup sqlBackup = sqlBackupService.get(id);
			SqlBackUpHelper helper = new SqlBackUpHelper();
			
			String hql=helper.load(sqlBackup.getFileName());
			if(hql!=null){
				sqlBackupService.sqlCallBack(hql);
			}
			super.setAjaxMsg(response, true, "还原成功");
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg(response, false, "还原失败:"+e.getMessage());
			super.printLog(request, "还原数据库", e);
		}
	}
	/**
	 * <p>删除数据库备份与还原信息</p>
	 * @author qj  
	 * @date 2013-7-31 下午10:28:59 
	 * @param request
	 * @param response
	 * @param ids
	 */
	@RequestMapping("delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, 
			String[] ids){
		try{
			if(ids == null || ids.length == 0){
				sqlBackupService.delete();
			}else {
				sqlBackupService.delete((Serializable[])ids);
			}
			super.setAjaxMsg(response, false, "删除成功！");
			super.printLog(request, "删除数据库备份与还原信息");
			
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "删除数据库备份与还原信息", e);
			super.setAjaxMsg(response, false, "删除失败！");
		}
	}
	
	/**
	 * <p>下载备份的数据库文件</p>
	 * @author qj  
	 * @date 2013-8-22 下午6:20:07 
	 * @param request
	 * @param response
	 * @param id   备份文件实体id
	 */
	@RequestMapping("downloadSql")
	public void downloadSql(HttpServletRequest request, HttpServletResponse response,
			String id){
		try{
			String rootPath = super.getRootPath();  //得到web-inf路径
			System.out.println(rootPath);
			SqlBackup sqlBackup = sqlBackupService.get(id);
			String filePath = sqlBackup.getFileName();
			super.download(response, rootPath+"dbback/"+filePath, "");
			super.printLog(request, "下载数据库备份文件");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "下载数据库备份文件", e);
			super.setAjaxMsg("文件不存在");
		}
	}
	
	public SqlBackupService getSqlBackupService() {
		return sqlBackupService;
	}

	@Autowired
	public void setSqlBackupService(SqlBackupService sqlBackupService) {
		this.sqlBackupService = sqlBackupService;
	}
	
	
}
