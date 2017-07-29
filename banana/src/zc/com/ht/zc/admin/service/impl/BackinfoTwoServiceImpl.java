package com.ht.zc.admin.service.impl;


import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.sys.service.impl.BaseServiceImpl;
import com.ht.zc.admin.bean.BackinfoTwo;
import com.ht.zc.admin.bean.ProductinfoTwo;
import com.ht.zc.admin.dao.BackinfoTwoDao;
import com.ht.zc.admin.dao.ProductinfoTwoDao;
import com.ht.zc.admin.service.BackinfoTwoService;

/**
 * BackinfoTwo 业务服务层
 * <p>众筹活动计划</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * @author qj
 * @date 2014-11-6 12:26:04
 */
@Service("backinfoTwoServiceImpl")
public class BackinfoTwoServiceImpl extends BaseServiceImpl<BackinfoTwo> implements BackinfoTwoService{
	private BackinfoTwoDao backinfoTwoDao;
	private ProductinfoTwoDao productinfoTwoDao;
	
	public BackinfoTwoServiceImpl(){
		super();
	}
	
	/**
	 * 保存众筹活动计划
	 * 
	 * @param backinfoTwo  众筹活动计划
	 * @param productinfoTwo 活动商品
	 * */
	public void save(BackinfoTwo backinfoTwo, ProductinfoTwo productinfoTwo){
		backinfoTwoDao.save(backinfoTwo);
		productinfoTwo.setActivityId(backinfoTwo.getId());
		productinfoTwoDao.save(productinfoTwo);
	}
	
	
	/**
	 * 更新众筹活动计划
	 * 
	 * @param backinfoTwo  众筹活动计划
	 * @param productinfoTwo 活动商品
	 * */
	public void update(BackinfoTwo backinfoTwo, ProductinfoTwo productinfoTwo){
		backinfoTwoDao.update(backinfoTwo);
		productinfoTwoDao.deleteByBackInfo(backinfoTwo.getId());
		productinfoTwo.setActivityId(backinfoTwo.getId());
		productinfoTwoDao.save(productinfoTwo);
	}
	
	/**
	 * 批量删除众筹活动
	 * 
	 * @param ids 众筹活动的id
	 * */
	public void deleteBackInfo(Serializable[] ids){
		for (Serializable id : ids) {
			BackinfoTwo backinfoTwo = backinfoTwoDao.get(id);
			if (backinfoTwo == null) {
				throw new RuntimeException("没有找到相应的众筹活动计划");
			}
			productinfoTwoDao.deleteByBackInfo(backinfoTwo.getId());
			backinfoTwoDao.delete(backinfoTwo);
		}
	}

	@Autowired
	public void setBackinfoTwoDao(BackinfoTwoDao backinfoTwoDao) {
		super.baseDao = backinfoTwoDao;
		this.backinfoTwoDao = backinfoTwoDao;
	}

	@Autowired
	public void setProductinfoTwoDao(ProductinfoTwoDao productinfoTwoDao) {
		this.productinfoTwoDao = productinfoTwoDao;
	}
	
}