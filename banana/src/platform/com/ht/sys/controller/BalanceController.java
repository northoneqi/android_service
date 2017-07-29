package com.ht.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
* 类功能说明: 账目结算
* 类修改者	修改日期
* 修改说明
* <p>Description:BlueFat私活利器</p>
* <p>Copyright: Copyright (c) 2013@qj</p>
* <p>联系方式:939474528@qq.com</p>
* @author qj
* @date 2014-3-24 下午01:39:48
* @version V1.0
*/
@Controller("sys_BalanceController")
@RequestMapping("sys/balance")
public class BalanceController extends BaseController{

	@RequestMapping("listPage")
	public String listPage(){
		return "sys/balance_list_page";
	}
	
	@RequestMapping("monthBalance")
	public void monthBalance(){
		
	}
	
	@RequestMapping("yearBalance")
	public void yearBalance(){
		
	}
	
	@RequestMapping("projectBalance")
	public void projectBalance(){
		
	}
}
