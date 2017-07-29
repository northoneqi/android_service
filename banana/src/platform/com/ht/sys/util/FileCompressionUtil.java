/**
 * @Title: FileCompressionUtil.java 
 * @Package com.bluefat.sys.util 
 * @Description: 文件解压缩帮助类
 * @author qj  
 * @date 2013-9-11 下午9:46:53 
 * @version V1.0
 */
package com.ht.sys.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

/**
* <p>类功能说明:文件压缩类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午10:59:14
* @version V1.0
*/
public class FileCompressionUtil {
	public static final int BUFFER = 2048;

	private FileCompressionUtil() {
	}

	
	/**
	 * <p>压缩文件</p>
	 * @author qj  
	 * @date 2013-9-11 下午9:50:00 
	 * @param zipFileName  压缩文件名称
	 * @param filePath    文件路径
	 * @throws IOException
	 */
	public static void zipFilesInPath(final String zipFileName,
			final List<String> files) throws IOException {
		final FileOutputStream dest = new FileOutputStream(zipFileName);
		final ZipOutputStream out = new ZipOutputStream(
				new BufferedOutputStream(dest));
		out.setEncoding("gbk");
		try {
			byte[] data = new byte[BUFFER];
			for (String file : files) {
				final FileInputStream fi = new FileInputStream(file);
				final BufferedInputStream origin = new BufferedInputStream(fi,
						BUFFER);
				out.putNextEntry(new ZipEntry(file.substring(file.lastIndexOf("/")+15, file.length())));
				int count;
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
				origin.close();
				fi.close();
			}
		} finally {
			out.close();
			dest.close();
		}
	}

}
