package com.ht.zc.admin.controller;

import java.awt.print.Pageable;
import java.io.Serializable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ht.sys.controller.BaseController;
import com.ht.zc.admin.bean.ProductinfoTwo;
import com.ht.zc.admin.service.ProductinfoTwoService;

/**
 * ProductinfoTwo 访问控制层
 * <p>众筹商品信息表</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * <p>联系方式:939474528@qq.com</p>
 * @author qj
 * @date 2014-11-7 10:43:27
 */
@Controller("productinfoTwoController")
@RequestMapping("admin/productinfoTwo")
public class ProductinfoTwoController extends BaseController<ProductinfoTwo>{
	@Resource(name = "productinfoTwoServiceImpl")
	private ProductinfoTwoService productinfoTwoService;
	
	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "/admin/productinfoTwo/add";
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(HttpServletResponse response, ProductinfoTwo productinfoTwo) {
		try{
			productinfoTwoService.save(productinfoTwo);
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg(response, false, "添加失败！");
		}
		super.setAjaxMsg(response, true, "添加成功！");
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Integer id, ModelMap model) {
		model.addAttribute("productinfoTwo", productinfoTwoService.get(id));
		return "/admin/productinfoTwo/edit";
	}
	
	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(HttpServletResponse response, ProductinfoTwo productinfoTwo) {
		try{
			ProductinfoTwo pProductinfoTwo = productinfoTwoService.get(productinfoTwo.getCrowdfundingProductId());
			if (pProductinfoTwo == null ) {
				super.setAjaxMsg(response, false, "没有找到该角色！");
			}
			productinfoTwoService.update(productinfoTwo);
		}catch(Exception e){
			e.printStackTrace();
			super.setAjaxMsg(response, false, "更新失败！");
		}
		
		super.setAjaxMsg(response, true, "更新成功！");
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		//model.addAttribute("page", productinfoTwoService.findByPager(pageable));
		return "/admin/productinfoTwo/list";
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public void delete(Integer[] ids, HttpServletResponse response) {
		if (ids != null) {
			for (Integer id : ids) {
				ProductinfoTwo productinfoTwo = productinfoTwoService.get(id);
				if (productinfoTwo == null) {
					throw new RuntimeException("没有找到相应的角色");
				}
			}
			productinfoTwoService.delete((Serializable[])ids);
			super.setAjaxMsg(response, true, "删除成功");
		}
	}
	
}
