package com.ht.sys.autocode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.sys.bean.SysInfo;
import com.ht.sys.util.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class AutoCode {

	/**应用名称*/
	private String app;
	/**实体名称*/
	private String entity;
	/**应用描述*/
	private String description;
	/**存值*/
	private Map<String, Object> data = new HashMap<String, Object>();
	private Configuration cfg = new Configuration();
	/**项目根路径*/
	private String basePath = "";
	/**生成jsp对应的路径*/
	private String jspPath = "";
	private Class<?> clazz;
	
	/**
	 * 构造器
	 * @param app		应用名称
	 * @param entity	实体名称
	 * @param description	应用描述
	 */
	public AutoCode(String app, Class<?> clazz, String description){
		this.app = app;
		this.entity = clazz.getSimpleName();
		this.description = description;
		this.clazz = clazz;
		
		data.put("app", app);
		data.put("entity", entity);
		data.put("description", this.description);
		
		cfg.setClassForTemplateLoading(AutoCode.class ,  "/com/bluefat/sys/autocode");
		cfg.setSharedVariable("upperFC",  new UpperFirstCharacter()); //添加一个"宏"共享变量用来将属性名首字母大写 
		cfg.setSharedVariable("lowerFC",  new LowerFirstCharacter()); //添加一个"宏"共享变量用来将属性名首字母小写 
		
		basePath = AutoCode.class.getClassLoader().getResource("")+"";
		jspPath = basePath.replaceFirst("/build/classes", "")+"WebContent/"+"WEB-INF/views/"+app+"/"; 
		
		basePath = basePath.replaceFirst("/build/classes", "")+"src/";  //项目源代码路径
		basePath = basePath.replace("file:/", "");  //必须去掉file协议，否则路径非法
		
		jspPath = jspPath.replace("file:/", "");
	}
	
	/**
	 * <p>Description:创建文件</p>
	 * @author qj
	 * @date 2013-11-6 下午07:47:10
	 * @param @param fileName		文件路径和文件名的组合
	 * @param @throws IOException    
	 * @return File   				返回创建的文件
	 * @throws
	 */
	public static File createFile(String fileName) throws IOException {
		File file = new File(fileName);
		
		File parent = file.getParentFile(); 
		if(parent!=null&&!parent.exists()){ 
			parent.mkdirs(); 
		}
		
		if(!file.exists()){
			file.createNewFile();
		}
		
		System.out.println("生成模板:"+fileName);
		return file;
	}
	
	
	/**
	 * 生成dao
	 * @author qj
	 * @date 2013-11-6 下午10:18:09
	 * @param @throws IOException
	 * @param @throws TemplateException    
	 * @return void   
	 * @throws
	 */
	public void createDaoTemplate() throws IOException, TemplateException{
		//选择模板
		Template template = cfg.getTemplate("DaoTemplate.ftl");
		String path = basePath+"com/bluefat/"+app+"/dao/"+entity+"Dao.java";
		//java文件的生成目录  
		File file = createFile(path);
		FileOutputStream fos = new  FileOutputStream(file); 
		
		OutputStreamWriter writer = new OutputStreamWriter(fos, "utf-8");
		template.process(data, writer);
		
		writer.flush();
		fos.flush();  
        fos.close();
	}
	
	/**
	 * 生成service
	 * @author qj
	 * @date 2013-11-6 下午10:18:48
	 * @param @throws IOException
	 * @param @throws TemplateException    
	 * @return void   
	 * @throws
	 */
	public void createServiceTemplate() throws IOException, TemplateException{
		//选择模板
		Template template = cfg.getTemplate("ServiceTemplate.ftl");
		String path = basePath+"com/bluefat/"+app+"/service/impl/"+entity+"Service.java";
		//java文件的生成目录  
		File file = createFile(path);
		FileOutputStream fos = new  FileOutputStream(file); 
		
		OutputStreamWriter writer = new OutputStreamWriter(fos, "utf-8");
		template.process(data, writer);
		
		writer.flush();
		fos.flush();  
        fos.close();
	}
	
	/**
	 * @Description:生成controller
	 * @author qj
	 * @date 2013-11-7 上午08:27:20
	 * @param @throws IOException
	 * @param @throws TemplateException    
	 * @return void   
	 * @throws
	 */
	public void createControllerTemlate() throws IOException, TemplateException{
		//选择模板
		Template template = cfg.getTemplate("ControllerTemplate.ftl");
		String path = basePath+"com/bluefat/"+app+"/controller/"+entity+"Controller.java";
		//java文件的生成目录  
		File file = createFile(path);
		FileOutputStream fos = new  FileOutputStream(file); 
		
		OutputStreamWriter writer = new OutputStreamWriter(fos, "utf-8");
		template.process(data, writer);
		
		writer.flush();
		fos.flush();  
        fos.close();
	}
	
	
	/**
	 * @Description: 生成jsp模板
	 * @author qj
	 * @date 2013-11-7 上午09:25:38
	 * @param     
	 * @return void   
	 * @throws IOException 
	 * @throws TemplateException 
	 */
	public void createJspTemplate() throws IOException, TemplateException {
		//选择模板
		Template template = cfg.getTemplate("JspTemplate.ftl");
		String path = jspPath+StringUtil.lowerFirst(entity)+"_list.jsp";
		//java文件的生成目录  
		File file = createFile(path);
		FileOutputStream fos = new  FileOutputStream(file); 
		
		OutputStreamWriter writer = new OutputStreamWriter(fos, "utf-8");
		
		List<ModelField> list = ReflectObject2ExtJSModel.createModel(clazz);
		
		data.put("fields", list);
		template.process(data, writer);
		
		writer.flush();
		fos.flush();  
        fos.close();
	}
	
	
	/**
	 * <p>Description:一次性生成dao,service,controller,jsp</p>
	 * @author qj
	 * @date 2013-11-7 下午03:45:20
	 * @param @throws IOException
	 * @param @throws TemplateException    
	 * @return void   
	 * @throws
	 */
	public void autoComplete() throws IOException, TemplateException{
		//生成dao
		this.createDaoTemplate();
		
		//生成service
		this.createServiceTemplate();
		
		//生成controller
		this.createControllerTemlate();
		
		//生成页面
		//this.createJspTemplate();
	}
	
	public static void main(String[] args) throws IOException, TemplateException{

		System.out.println("***************** 开始按照模板生成代码：***********************");
		
		long startTime = System.currentTimeMillis();
		AutoCode autoCode = new AutoCode("sys", SysInfo.class, "系统基本信息");
		
		//生成dao测试
		autoCode.createDaoTemplate();
		
		//生成service测试
		autoCode.createServiceTemplate();
		
		//生成controller测试
		autoCode.createControllerTemlate();
		
		//生成页面测试
		//autoCode.createJspTemplate();
		
		//一次性完成所有的代码生成
		//autoCode.autoComplete();
		
		long endTime = System.currentTimeMillis();
		System.out.println("***************** 代码生成完毕,共耗时:"+(endTime-startTime)+"ms ***********************");
	}
}
