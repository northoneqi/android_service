package com.ht.sys.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ht.sys.bean.SysLog;
import com.ht.sys.bean.SysResource;
import com.ht.sys.service.impl.DepartmentService;
import com.ht.sys.service.impl.SysLogService;
import com.ht.sys.service.impl.SysResourceService;
import com.ht.sys.service.impl.SysRoleService;
import com.ht.sys.service.impl.SysUserService;
import com.ht.sys.util.DateUtil;
import com.ht.sys.util.GenericsUtils;
import com.ht.sys.util.JsonDateValueProcessor;
import com.ht.sys.util.Pager;
import com.ht.sys.util.SysHelper;

public class BaseController<T> {

	Logger logger = null;
	public SysLogService logService;
	protected DepartmentService departmentService;
	protected SysUserService userService;
	protected SysRoleService roleService;
	protected SysResourceService resourceService;
	
	public BaseController(){
		logger = Logger.getLogger(GenericsUtils.getSuperClassGenricType(this.getClass()));
	}
	
	/**
	 * <p>注入属性编辑器</p>
	 * */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// true允许时间为空
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	
	
	/**
	 * <p>日志打印</p>
	 * @author qj  
	 * @date 2013-8-1 下午05:54:55 
	 * @param request
	 * @param optionMsg  需要打印的信息
	 */
	protected void printLog(HttpServletRequest request, String optionMsg){
		logger.info("【用户】--"+optionMsg);
		SysLog sysLog = new SysLog();
		sysLog.setLoginIP(this.getIpAddr(request));
		sysLog.setOperateModel(optionMsg);
		sysLog.setUserName(new SysHelper(request).getCurrentUser().getName());
		sysLog.setOperateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		logService.save(sysLog);
	}
	
	/**
	 * <p>日志打印</p>
	 * @author qj  
	 * @date 2013-8-1 下午05:54:55 
	 * @param request
	 * @param optionMsg  需要打印的信息
	 * @param e  异常信息
	 */
	protected void printLog(HttpServletRequest request, String optionMsg, Exception e){
		logger.info("【用户】--"+optionMsg);
		SysLog sysLog = new SysLog();
		sysLog.setLoginIP(this.getIpAddr(request));
		sysLog.setOperateModel(optionMsg);
		sysLog.setUserName(new SysHelper(request).getCurrentUser().getName());
		sysLog.setOperateTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		sysLog.setExceptionMsg(e.getMessage());
		logService.save(sysLog);
	}
	
	/**
	 * <p>返回Ajax弹出提示信息</p>
	 * 
	 * @param response
	 * @param msg
	 * @throws IOException
	 */
	public void setAjaxMsg(HttpServletResponse response, boolean boo, String msg){
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("{success: "+boo+",data:{instruction: '"+msg+"'}}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * <p>返回ajax数据</p>
	 * 
	 * @param response
	 * @param msg
	 * @throws IOException
	 */
	public void setAjaxMsg(HttpServletResponse response, String msg){
		try {
			response.setContentType("text/html;charset=utf-8");
			System.out.println(msg);
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>返回elastic数据</p>
	 * @param response
	 * @param data 数据
	 */
	public void setSearchMsg(HttpServletResponse response, String data) {
		
		OutputStream out = null;
		BufferedWriter bOut = null;
		OutputStreamWriter swr = null;
		try {
			out = response.getOutputStream();
			swr = new OutputStreamWriter(out,"UTF-8");
			bOut = new BufferedWriter(swr);
			bOut.write(data);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bOut.close();
				swr.close();
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * <p>返回Combo数据</p>
	 * 
	 * @param response
	 * @param msg
	 * @throws IOException
	 */
	public void setComboMsg(HttpServletResponse response, String msg){
		try {
			response.setContentType("text/html;charset=utf-8");
			System.out.println(msg);
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setLoadFormMsg(HttpServletResponse response, String msg){
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String setAjaxMsg(String msg){
		return "{success: true,data:{instruction: '"+msg+"'}}";
	}
	
	/**
	 * <p>返回Dialog弹出提示信息</p>
	 * 
	 * @param response
	 * @param msg
	 * @param url
	 * @throws IOException
	 */
	public static void setDialogMsg(HttpServletResponse response, String msg, String url) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script type='text/javascript'>function f(){" +
					"alert('"+msg+"');window.location.href='"+url+"';} f();</script>");
		} catch (Exception e) {
			System.out.println(" 设置返回弹出信息失败---" + e);
		}

	}
	
	/**
	 * <p>android返回json数据</p>
	 * @param response
	 * @param object
	 */
	public static void putAndroidJson(HttpServletResponse response, Object object) {
		try {
			String data = new GsonBuilder().create().toJson(object);
			response.getWriter().print(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>查询数据成功后输出表格数据的json格式</p>
	 * @author qj  
	 * @date 2013-7-31 下午10:36:54 
	 * @param response
	 * @param total  总条数
	 * @param list 数据
	 */
	public void setGridJson(HttpServletResponse response, int total, List<?> list){
		JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
		StringBuffer jsonStrBuf = new StringBuffer();
		jsonStrBuf.append("{'total':").append(total).append(",");
		try{
			jsonStrBuf.append("'success': ").append(true).append(",");
			jsonStrBuf.append("'root':").append(JSONArray.fromObject(list,jsonConfig).toString());
		}catch(Exception e){
			e.printStackTrace();
			jsonStrBuf.append("'success': false,");
			jsonStrBuf.append("'msg': '数据转换出错',");
		}
		
		jsonStrBuf.append("}");
		
		try {
			System.out.println(jsonStrBuf);
			response.getWriter().print(jsonStrBuf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>查询数据成功后输出表格数据的json格式</p>
	 * @author qj  
	 * @date 2013-9-16 下午05:42:35 
	 * @param response
	 * @param total			//行总数
	 * @param list			//数据
	 * @param excludes		//过滤的属性
	 */
	public void setGridJson(HttpServletResponse response, int total, List<T> list, String[] excludes){
		StringBuffer jsonStrBuf = new StringBuffer();
		jsonStrBuf.append("{'total':").append(total).append(",");
		try{
			JsonConfig config = new JsonConfig();
			config.setExcludes(excludes);
			jsonStrBuf.append("'success': ").append(true).append(",");
			jsonStrBuf.append("'root':").append(JSONArray.fromObject(list, config).toString());
		}catch(Exception e){
			e.printStackTrace();
			jsonStrBuf.append("'success': false,");
			jsonStrBuf.append("'msg': '数据转换出错',");
		}
		jsonStrBuf.append("}");
		
		try {
			System.out.println(jsonStrBuf);
			response.getWriter().print(jsonStrBuf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * <p>查询数据成功后输出表格数据的json格式</p>
	 * @author qj  
	 * @date 2013-9-16 下午05:42:35 
	 * @param response
	 * @param total			//行总数
	 * @param list			//数据
	 * @param excludes		//过滤的属性
	 */
	public void setGridJson(HttpServletResponse response, int total, List<T> list, String[] excludes, double totalMoney){
		StringBuffer jsonStrBuf = new StringBuffer();
		jsonStrBuf.append("{'total':").append(total).append(",");
		try{
			JsonConfig config = new JsonConfig();
			config.setExcludes(excludes);
			jsonStrBuf.append("'success': ").append(true).append(",");
			jsonStrBuf.append("'root':").append(JSONArray.fromObject(list, config).toString());
		}catch(Exception e){
			jsonStrBuf.append("'success': false,");
			jsonStrBuf.append("'msg': '数据转换出错',");
		}
		
		jsonStrBuf.append("}");
		
		try {
			System.out.println(jsonStrBuf);
			response.getWriter().print(jsonStrBuf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>查询数据失败后后输出表格数据的json格式</p>
	 * @author qj  
	 * @date 2013-7-31 下午10:36:54 
	 * @param response
	 * @param total  总条数
	 * @param list 数据
	 */
	public void setGridJson(HttpServletResponse response, String msg){
		StringBuffer jsonStrBuf = new StringBuffer();
		jsonStrBuf.append("{'msg': '").append(msg).append("',");
		jsonStrBuf.append("'success': ").append(true).append("}");
		
		try {
			response.getWriter().print(jsonStrBuf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setComboJson(HttpServletResponse response, Map<String, String> map){
		StringBuffer jsonStrBuf = new StringBuffer();
		try{
			jsonStrBuf.append("[").append("{code:'',").append("name: '--请选择--'},");
			java.util.Set<String> set = map.keySet();
			java.util.Iterator<String> it = set.iterator();
			while(it.hasNext()){
				String key = it.next();
				jsonStrBuf.append("{code:'").append(key).append("',")
						  .append("name: '").append(map.get(key)).append("'},");
			}
			
			if(jsonStrBuf.lastIndexOf(",") == (jsonStrBuf.length()-1)){
				jsonStrBuf.deleteCharAt(jsonStrBuf.length()-1);
			}
			
			jsonStrBuf.append("]");
		}catch(Exception e){
			e.printStackTrace();
			jsonStrBuf.append("'success': false,");
			jsonStrBuf.append("'msg': '数据转换出错',");
		}
		
		try {
			System.out.println(jsonStrBuf);
			//response.getWriter().print("[{dCode:'1001',dName:'性别'},{dCode:'1002', dName:'状态'}]");
			response.getWriter().print(jsonStrBuf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>为extjs返回表单信息拼接json字符串 </p>
	 * 
	 * @param response
	 * @param boo
	 * @param obj
	 * */
	public void setExtJsFormJson(HttpServletResponse response, boolean boo, Object obj){
		StringBuffer jsonStrBuf = new StringBuffer();
		JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
		if(obj == null ){
			jsonStrBuf.append("{'success': false, 'errMessage':")
			   .append("'没有找到该记录，该记录可能已被删除！'}");
		}else if(!boo){
			jsonStrBuf.append("{'success': false, 'errMessage':")
			   .append("'查找错误！'}");
		}else{
			String root = JSONObject.fromObject(obj,jsonConfig).toString();
			jsonStrBuf.append("{'success': true, 'data':")
					  .append(root).append("}");
		}
		try {
			System.out.println(jsonStrBuf);
			response.getWriter().print(jsonStrBuf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * <p>判断一个字符串是否为空或者为null</p>
	 * */
	public boolean isEmpty(String str){
		if(str == null || str.trim().equals("") || str.trim().equals("请选择")){
			return true;
		}
		
		return false;
	}

	
	/**
	 * <p>得到客户的ip</p>
	 * @author qj  
	 * @date 2013-8-12 下午05:33:48 
	 * @param request
	 * @return 客户的ip地址
	 */
	protected String getIpAddr(HttpServletRequest request) {  
		if (request.getHeader("x-forwarded-for") == null) {  
			return request.getRemoteAddr();  
	    }  
	    return request.getHeader("x-forwarded-for");  
	}
	
	
	/**
	 * <p>文件下载 </p>
	 * @author qj  
	 * @date 2013-8-22 下午5:55:25 
	 * @param response
	 * @param filePath  文件路径
	 * @param mimeType  头部信息
	 */
	public void download(HttpServletResponse response, String filePath, String mimeType){
		try {
            // path是指欲下载的文件的路径。
            File file = new File(filePath);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
           // String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
            byte[] buffer = new byte[1024*8];
            while(fis.read(buffer) != -1){	
            	toClient.write(buffer);
            }
            fis.close();
            
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("要下载的文件不存在");
        }
	}
	
	/**
	 * <p>excel导出</p>
	 * @author qj  
	 * @date 2013-9-12 上午9:09:49 
	 * @param request
	 * @param response
	 * @param list   要导出的数据
	 * @param config excel的配置文件
	 * @throws ExcelException 
	 * @throws UnsupportedEncodingException 
	 */
	public void excelExport(HttpServletRequest request, HttpServletResponse response,
			List<?> list, String fileName, String config) throws ExcelException, UnsupportedEncodingException{
		try {
			ExcelUtils.addValue("list", list);
			response.reset();
			response.addHeader("Content-Disposition", "attachment; filename="
					+new String(fileName.getBytes("GBK"), "ISO_8859_1")+ ".xls");
			response.setContentType("application/vnd.ms-excel");
			OutputStream out = response.getOutputStream();
			
			ExcelUtils.export(request.getSession().getServletContext(), config, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void excelExport(HttpServletRequest request, HttpServletResponse response,
			Object obj, String fileName, String config) throws ExcelException, UnsupportedEncodingException{
		try {
			ExcelUtils.addValue("document", obj);
			response.reset();
			response.addHeader("Content-Disposition", "attachment; filename="
					+new String(fileName.getBytes("GBK"), "ISO_8859_1")+ ".xls");
			response.setContentType("application/vnd.ms-excel");
			OutputStream out = response.getOutputStream();
			
			ExcelUtils.export(request.getSession().getServletContext(), config, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * <p>得到web-inf的路径</p>
	 * @author qj  
	 * @date 2013-8-22 下午6:18:05 
	 * @return 返回web-inf的路径
	 */
	public String getRootPath(){
		
		
/*		Properties properties = new Properties();
        InputStream in = getClass().getResourceAsStream("elastic.properties");
        properties.load(in);
        properties.getProperty("mail.smtp.host");
        properties.getProperty("mail.sender.username");
        properties.getProperty("mail.sender.password");*/
		
		
		String path = new File(BaseController.class.getClassLoader().getResource("").getPath()+"/").getParentFile().getPath()+"/";
		return path;
	}
	
	public void setButtons(HttpServletRequest request, Model model, String url){
		SysResource resource = resourceService.getByUrl(url);
		String parentCode = resource.getCode();
		SysHelper helper = new SysHelper(request);
		String roleCode = helper.getCurrentUser().getRoleCode();
		List<SysResource> buttons = roleService.getButton(roleCode, parentCode);
		String buttonNames = "";
		int index = 0;
		for(SysResource button: buttons){
			if(index > 0) buttonNames += ",";
			buttonNames += button.getName();
			index++;
		}
		model.addAttribute("buttons", buttonNames);
	}
	
	public SysLogService getLogService() {
		return logService;
	}

	@Autowired
	public void setLogService(SysLogService logService) {
		this.logService = logService;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	@Autowired
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public SysUserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(SysUserService userService) {
		this.userService = userService;
	}

	public SysRoleService getRoleService() {
		return roleService;
	}

	@Autowired
	public void setRoleService(SysRoleService roleService) {
		this.roleService = roleService;
	}

	public SysResourceService getResourceService() {
		return resourceService;
	}

	@Autowired
	public void setResourceService(SysResourceService resourceService) {
		this.resourceService = resourceService;
	}
}
