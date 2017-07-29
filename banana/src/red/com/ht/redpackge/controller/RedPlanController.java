package com.ht.redpackge.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ht.redpackge.entity.MciroActivity;
import com.ht.sys.controller.BaseController;

@Controller("RedPlanController")
public class RedPlanController extends BaseController<MciroActivity> {
	
	@RequestMapping(value="/planList" , method={RequestMethod.GET})
	public String planList(HttpServletRequest request,HttpServletResponse response){
		System.out.println("!!!!!!!!!!!!!!!!!!11"+request.getRequestURL());
		return "redPackge/planlist";
	}
}
