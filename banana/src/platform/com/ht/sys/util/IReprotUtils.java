package com.ht.sys.util;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * IReprot帮助类,主要用于管理IREPORT的打印、显示、以及数据范围
 * @author 赵兴华
 */
public class IReprotUtils {
	public enum ExportType {
		pdf, xml, excel, html, stream
	}
	/**
	 * 真正的写 report 的 参数
	 * 
	 */
	public static void writeReport(String path, ExportType exportType,Map<String, Object> params, Connection connection,HttpServletResponse response){
		
		try{
			// 编译模板
			switch (exportType) {
			case pdf:
				 byte[] bytes = JasperRunManager.runReportToPdf(path, params, connection); 
				 response.setContentType("application/pdf");  
				 response.setContentLength(bytes.length);  
				 ServletOutputStream servletOutputStream = response.getOutputStream(); 
				 servletOutputStream.write(bytes, 0, bytes.length);  
				 servletOutputStream.flush();  
				 servletOutputStream.close();  
				break;
				
			case xml:
				JRXmlExporter  exporter = new JRXmlExporter();
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				JasperPrint jasperPrint = JasperFillManager.fillReport(path, params, connection);
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
				exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
				exporter.exportReport();
				
				byte[] bytes1 = byteArrayOutputStream.toByteArray();
				response.setContentType("application/xml");
				response.setContentLength(bytes1.length);
				ServletOutputStream ouputStream = response.getOutputStream();
				ouputStream.write(bytes1, 0, bytes1.length);
				ouputStream.flush();
				ouputStream.close();  
				
			case excel:
				JRXlsExporter xlsexporter = new JRXlsExporter();  
				ByteArrayOutputStream oStream = new ByteArrayOutputStream();  
				JasperPrint jrxexporter = JasperFillManager.fillReport(path, params, connection);  
				xlsexporter.setParameter(JRExporterParameter.JASPER_PRINT, jrxexporter);  
				xlsexporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oStream);  
				xlsexporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);  
				xlsexporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);  
				xlsexporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
				xlsexporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
				xlsexporter.exportReport();  
				  
				byte[] bytes2 = oStream.toByteArray();  
				response.setContentType("application/vnd.ms-excel");  
				response.setContentLength(bytes2.length);  
				ServletOutputStream ouputStream1 = response.getOutputStream();  
				ouputStream1.write(bytes2, 0, bytes2.length);  
				ouputStream1.flush();  
				ouputStream1.close();  
				
			case stream:
				File reportFile = new File(path);
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);
	            
				ServletOutputStream out=response.getOutputStream();
				ObjectOutputStream os=new ObjectOutputStream(out);
				os.writeObject(jasperPrint);
				os.flush();
				os.close();
				
			default:
				break;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}
