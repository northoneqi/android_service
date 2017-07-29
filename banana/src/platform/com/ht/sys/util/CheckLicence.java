package com.ht.sys.util;

import java.util.Date;

/**
* 类功能说明: 检查系统授权是否合法
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2014-4-10 上午09:28:02
* @version V1.0
*/
public class CheckLicence {

	public static boolean isLegal() {
		boolean boo = true;
		String key = "test"; //加密密匙
		//判断文件是否经过加密
		String fileUrl = CheckLicence.class.getResource("/").getPath()+"licence.ini";
		//得到解密后的临时文件
		String tempUrl = CheckLicence.class.getResource("/").getPath()+"licence.tmp";
		
		String keyTmp = FileEncryptAndDecrypt.readFileLastByte(fileUrl, key.length());
		if(key.equals(keyTmp)){
			//生成解密文件
			try {
				FileEncryptAndDecrypt.decrypt(fileUrl, tempUrl, key.length());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//将文件先加密，然后生成一份临时解密文件
			try {
				FileEncryptAndDecrypt.encrypt(fileUrl, key);
				FileEncryptAndDecrypt.decrypt(fileUrl, tempUrl, key.length());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//授权公司
		String companyName = PropertyReader.read(tempUrl, "companyName");
		System.out.println(companyName);
		
		//机器号
		String macCode = PropertyReader.read(tempUrl, "macCode");
		System.out.println(macCode);
		
		//授权日期
		String authDate = PropertyReader.read(tempUrl, "authDate");
		System.out.println(authDate);
		
		//授权时间限制,默认为30天有效期
		int limitDate = Integer.parseInt(PropertyReader.read(tempUrl, "limitDate"));
		System.out.println(limitDate);
		
		Date date = DateUtil.addDay(DateUtil.stringToDate(authDate, "yyyy-MM-dd"), limitDate);
		
		//PropertyReader
		//判断当前日期是否是在授权的日期范围内
		Date curDate = new Date();
		if(curDate.after(date)){
			boo = false;
		}
		
		//删除临时生成的解密文件
		FileUtil.deleteFile(tempUrl);
		return boo;
	}
	
	public static void main(String args[]) throws Exception{
		System.out.println(CheckLicence.isLegal());
	}
}
