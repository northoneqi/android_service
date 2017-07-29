/**
* @Title: DictMap.java 
* @Package com.bluefat.sys.bean 
* @Description: 数据字典Map
* @author qj  
* @date 2013-8-18 下午5:26:30 
* @version V1.0
*/
package com.ht.sys.util;

import java.util.HashMap;
import java.util.Map;

/**
* <p>类功能说明: 数据字典常量</p>
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2013-11-15 上午10:57:58
* @version V1.0
*/
public class Constants {

	private static final String dataAuth = "1"; //数据权限   //此项禁止添加
	public static final String SEX_CODE = "1001";  //性别
	private static final String STATE_CODE = "1002"; //状态
	private static final String professionalTitle = "1003"; //职称
	private static final String jobPosition = "1004"; //职务
	private static final String billType = "1005"; //单据类型
	private static final String budgetProperties = "1006"; //预算性质
	private static final String projectStatus = "1007"; //项目状态
	private static final String activeStatus = "1008";  //活动状态
	private static final String playNum = "1009";  //众筹人份数
	private static final String cooperationType = "1010";  //合作类型
	private static final String state = "1011";  //状态
	private static final String payType = "1012";  //支付方式
	private static final String pickType = "1013";  //提货方式
	private static final String settleAccord = "1014";  //结算依据
	private static final String prepaymentType = "1015";  //预付款方式
	private static final String settleType = "1016";  //结算方式
	private static final String arrivalType = "1017";  //到货方式
	private static final String operationType = "1018";  //经营方式
	public static Map<String, String> getDictTypes(){
		Map<String, String> map = new HashMap<String, String>();
		map.put(dataAuth, "数据权限");
		map.put(SEX_CODE, "性别");
		map.put(STATE_CODE, "状态");
		map.put(professionalTitle, "职称");
		map.put(jobPosition, "职务");
		map.put(billType, "单据类型");
		map.put(budgetProperties, "预算性质");
		map.put(projectStatus, "项目状态");
		map.put(activeStatus, "活动状态");
		map.put(playNum, "众筹人份数");
		map.put(cooperationType, "合作类型");
		map.put(state, "状态");
		map.put(payType, "支付方式");
		map.put(pickType, "提货方式");
		map.put(settleAccord, "结算依据");
		map.put(prepaymentType, "预付款方式");
		map.put(settleType, "结算方式");
		map.put(arrivalType, "到货方式");
		map.put(operationType, "经营方式");
		return map;
	}
}
