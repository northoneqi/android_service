package com.ht.zc.admin.service;

import java.io.Serializable;

import com.ht.sys.service.BaseService;
import com.ht.zc.admin.bean.BackinfoTwo;
import com.ht.zc.admin.bean.ProductinfoTwo;

/**
 * BackinfoTwo业务服务层接口
 * <p>众筹活动计划</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * @author qj
 * @date 2014-11-6 12:26:05
 */
public interface BackinfoTwoService extends BaseService<BackinfoTwo>{

	/**
	 * 保存众筹活动计划
	 * 
	 * @param backinfoTwo  众筹活动计划
	 * @param productinfoTwo 活动商品
	 * */
	public void save(BackinfoTwo backinfoTwo, ProductinfoTwo productinfoTwo);
	
	
	/**
	 * 更新众筹活动计划
	 * 
	 * @param backinfoTwo  众筹活动计划
	 * @param productinfoTwo 活动商品
	 * */
	public void update(BackinfoTwo backinfoTwo, ProductinfoTwo productinfoTwo);
	
	
	/**
	 * 批量删除众筹活动
	 * 
	 * @param ids 众筹活动的id
	 * */
	public void deleteBackInfo(Serializable[] ids);
}