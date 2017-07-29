/**
* @Title: SysFileController.java 
* @Package com.bluefat.sys.controller 
* @Description: 附件 
* @author qj  
* @date 2013-9-11 上午11:40:11 
* @version V1.0
*/
package com.ht.sys.controller;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.ht.sys.bean.SysFile;
import com.ht.sys.service.impl.SysFileService;
import com.ht.sys.util.FileCompressionUtil;
import com.ht.sys.util.FileUtil;
import com.ht.sys.util.RequestUtil;
import com.ht.sys.util.db.QueryUtil;

/**
* <p>类功能说明:文件上传下载</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午09:41:58
* @version V1.0
*/
@Controller("sys_fileController")
@RequestMapping("sys/file")
public class SysFileController extends SysController<SysFile>{

	private SysFileService fileService;

	/**
	 * <p>保存上传文件</p>
	 * @author qj  
	 * @date 2013-9-11 下午4:17:56 
	 * @param request
	 * @param response
	 * @param sysFile
	 */
	@RequestMapping("upload")
	public void upload(HttpServletRequest request, HttpServletResponse response,
			SysFile sysFile){
		try{
			String code = "";
			
			RequestUtil.printAttritbute(request);
			
			DefaultMultipartHttpServletRequest  multipartRequest = (DefaultMultipartHttpServletRequest ) request;

	        String newName = FileUtil.save(multipartRequest, super.getRootPath()+"upload/");
	        sysFile.setNewName(newName);
	        
	        if(isEmpty(sysFile.getCode())){
	        	code = fileService.getCode("code");
	        	sysFile.setCode(code);
	        }else{
	        	code = sysFile.getCode();
	        }
	        
	        sysFile.setName(URLDecoder.decode(sysFile.getName(), "UTF-8"));
	        fileService.save(sysFile);

	        super.setAjaxMsg(response, true, "上传成功', code: '"+code +"', id: '"+sysFile.getId());
	        super.printLog(multipartRequest, "上传文件");
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg(response, true, "上传成功" );
			super.printLog(request, "上传文件", e);
		}
	}
	
	/**
	 * <p>下载文件</p>
	 * @author qj  
	 * @date 2013-9-11 下午9:24:09 
	 * @param request
	 * @param response
	 * @param code  文件编号
	 */
	@RequestMapping("download")
	public void download(HttpServletRequest request, HttpServletResponse response,
			String code){
		try{
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.put("code", "=", code);
			List<SysFile> list = fileService.findByCondition(queryUtil);
			List<String> files = new ArrayList<String>();
			for(SysFile sysFile : list) {
				files.add(super.getRootPath()+"upload/"+sysFile.getNewName());
			}
			
			File file = new File(super.getRootPath()+"upload/"+new Date().getTime()+".zip");
			
			FileCompressionUtil.zipFilesInPath(file.getCanonicalPath(), files);
			
			super.download(response, file.getCanonicalPath(), "");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "下载文件", e);
			super.setAjaxMsg(response, "您要下载的文件已经不存在，可能已被删除！！！");
		}
	}
	
	/**
	 * <p>查询文件</p>
	 * @param request
	 * @param response
	 * @param code   文件组号
	 */
	@RequestMapping("queryByCode")
	public void queryByCode(HttpServletRequest request, HttpServletResponse response,
			String code){
		try{
			QueryUtil queryUtil = new QueryUtil();
			queryUtil.put("code", "=", code);
			List<SysFile> list = fileService.findByCondition(queryUtil);
			super.setGridJson(response, list.size(), list);
			logger.info("查询附件");
		}catch(Exception e){
			e.printStackTrace();
			super.printLog(request, "查询附件", e);
			super.setGridJson(response, "系统繁忙，请稍后再试");
		}
	}
	
	/**
	 * <p>按组号删除文件</p>
	 * @param request
	 * @param response
	 * @param code  文件组号
	 */
	@RequestMapping("deleteByCode")
	public void deleteByCode(HttpServletRequest request, HttpServletResponse response,
			String code){
		try{
			fileService.deleteByCode(code);
			super.setAjaxMsg(response, true, "删除成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg(response, false, "删除失败，可能文件已经被删除！");
		}
	}
	
	/**
	 * <p>删除指定的文件</p>
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping("delete")
	public void delete(HttpServletRequest request, HttpServletResponse response,
			String id){
		try{
			fileService.delete(id);
			super.setAjaxMsg(response, true, "删除成功！");
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg(response, false, "删除失败，可能文件已经被删除！");
		}
	}
	
	public SysFileService getFileService() {
		return fileService;
	}

	@Autowired
	public void setFileService(SysFileService fileService) {
		this.fileService = fileService;
	}
	
	
}
