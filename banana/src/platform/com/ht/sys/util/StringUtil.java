package com.ht.sys.util;

/**
* <p>类功能说明: 字符串帮助类</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-6 下午09:18:04
* @version V1.0
*/
public class StringUtil {

	/**
	 * <p>首字母转为大写</p>
	 * @author qj
	 * @date 2013-11-6 下午09:18:15
	 * @param @param str
	 * @param @return    
	 * @return String   
	 * @throws
	 */
	public static String  upperFirst(String str){
		if(str == null || str.equals("")) {
			throw new RuntimeException("空字符串不能首字母大写转换");
		}
		
		return str.substring(0, 1).toUpperCase()+str.substring(1);
	}
	
	
	/**
	 * <p>首字母转为小写</p>
	 * @author qj
	 * @date 2013-11-6 下午09:18:15
	 * @param @param str
	 * @param @return    
	 * @return String   
	 * @throws
	 */
	public static String  lowerFirst(String str){
		if(str == null || str.equals("")) {
			throw new RuntimeException("空字符串不能首字母小写转换");
		}
		
		return str.substring(0, 1).toLowerCase()+str.substring(1);
	}
	
	
	public static void main(String[] args){
		System.out.println(upperFirst("s"));
	}


	public static boolean isEmpty(String indexName) {

		if (indexName == null && "".equals(indexName)) {
			return true;
		}
		return false;
	}
}
