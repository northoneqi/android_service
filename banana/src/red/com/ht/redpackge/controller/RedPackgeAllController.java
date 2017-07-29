package com.ht.redpackge.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ht.redpackge.entity.MciroActivity;
import com.ht.sys.controller.BaseController;

@Controller("RedPackgeAllController")
@RequestMapping(value="redpackge/all")
public class RedPackgeAllController extends BaseController<MciroActivity>{
	
	private transient final Log log = LogFactory.getLog(getClass());
	
	@RequestMapping(value="/redListpage", method={RequestMethod.GET})
	public String listpage(HttpServletRequest request,HttpServletResponse response ){
//		super.setButtons(request, model, "redpackge/all/redListpage.do");
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		log.debug("!!!!!!!!!!!!!!!");
		return "redPackge/redOrder";
	}

}
