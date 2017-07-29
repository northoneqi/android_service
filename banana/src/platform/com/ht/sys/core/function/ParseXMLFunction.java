package com.ht.sys.core.function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * <p>解析功能xml文件</p>
 * <p>
 * 使用步骤：
 *    1.加载功能文件（即必须先调用load函数，否则不能正常使用该功能） 
 *    2.得到功能列表或者
 * </p>
 * */
public class ParseXMLFunction {
	private static ParseXMLFunction instance = null;
	
	private String configPath = "WEB-INF\\classes\\config";
	private String functionPattern = ".function.xml";
	private List<String> fileList = new ArrayList<String>();

	/**
	 * 得到解析配置文件实例
	 * */
	public static ParseXMLFunction getInstance(){
		if(instance == null){
			instance = new ParseXMLFunction();
		}
		return instance;
	}
	
	
	/**
	 * 加载xml文件，更据文件目录自动扫描功能文件，该文件格式必须为 XXX.function.xml,其他的不会解析
	 * 
	 * @param path
	 *            文件目录
	 * */
	public void load(String path) {
		File parentFile = new File(path+configPath);
		System.out.println(parentFile.getPath());
		File[] files = parentFile.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				String fileName = file.getName();
				if (fileName.endsWith(functionPattern)) {
					fileList.add(path + configPath + "\\" + fileName);
				}
			}
		}
	}

	/**
	 * 得到所有的功能列表
	 * 
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException 
	 * */
	public synchronized List<Accordion> getFunctionList() throws FileNotFoundException, UnsupportedEncodingException {
		List<Accordion> accordionList = new ArrayList<Accordion>();
		
		if (fileList.size() == 0)
			throw new RuntimeException("没有找到XXX.function.xml文件");

		XStream xstream = new XStream(new DomDriver());
		xstream.aliasType("accordion", Accordion.class);
		xstream.aliasType("menu", Menu.class);
		xstream.aliasType("button", Button.class);
		//xstream.useAttributeFor("icon",String.class);
		xstream.useAttributeFor("id", String.class);  //设置属性，否则不会自动读取xml的属性
		xstream.useAttributeFor("name", String.class);  //设置属性，否则不会自动读取xml的属性
		xstream.useAttributeFor("url", String.class);

		 //遍历所有的功能配置文件，将功能加入功能列表中
		for(String fileName: fileList){
			System.out.println(fileName);
			File f = new File(fileName);
			InputStreamReader read = new InputStreamReader(new FileInputStream(f),"UTF-8");
			BufferedReader reader=new BufferedReader(read);
			Accordion accordion = (Accordion)xstream.fromXML(reader);
			accordionList.add(accordion); 
		}
		
		return accordionList;
	}
	

	//得到功能的json数据格式
	public String getFunctionJson() throws FileNotFoundException, UnsupportedEncodingException{
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.alias("accordion", Accordion.class);
		xstream.setMode(XStream.NO_REFERENCES);   
		return xstream.toXML(this.getFunctionList());
	}
	
	public static void main(String args[]) throws UnsupportedEncodingException {
		ParseXMLFunction test = new ParseXMLFunction();
		//String path = ParseXMLFunction.class.getClassLoader().getResource("").getPath();
		String path = "D:/work/study/code/bluefat/config/";
		test.load(path);
		try {
			List<Accordion> list = test.getFunctionList();
			System.out.println(JSONArray.fromObject(list));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
