/**
* @Title: FileUtil.java 
* @Package com.bluefat.sys.util 
* @Description: 文件帮助类
* @author qj  
* @date 2013-9-11 下午3:24:52 
* @version V1.0
*/
package com.ht.sys.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.fileupload.util.Streams;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

/**
* <p>类功能说明:文件保存类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午11:00:04
* @version V1.0
*/
public class FileUtil {
	/**
	 * <p>保存上传的文件到本地磁盘 返回保存文件后的总路径如"file1,file2,file3"</p>
	 * */
	public static String save(DefaultMultipartHttpServletRequest muliRequest, String path) {
		Map<String, MultipartFile> fileMap = muliRequest.getFileMap();
		/************* 保存附件 *************/
		Iterator<MultipartFile> iter = fileMap.values().iterator();
		String filenames = "";
		while (iter.hasNext()) { // 判断是否有内容
			MultipartFile mf = iter.next();

			try {
				InputStream bis = mf.getInputStream();
				if (bis.available() <= 0)
					continue;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
				/*String filename = sdf.format(new Date())
						+ mf.getOriginalFilename();*/
				String originalFilename = mf.getOriginalFilename();
				String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
				String filename = sdf.format(new Date())+suffix;
				filenames += filename + ",";
				System.out.println(filename);
				FileOutputStream fos = new FileOutputStream(new File(
						path + filename));
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				Streams.copy(bis, bos, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (filenames.length() > 0) {
			filenames = filenames.substring(0, filenames.length() - 1);
		}

		return filenames;
	}
	
	/** 
	 * <p>删除单个文件 </p>
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();
	        flag = true;
	    }  
	    return flag;  
	}  
}
