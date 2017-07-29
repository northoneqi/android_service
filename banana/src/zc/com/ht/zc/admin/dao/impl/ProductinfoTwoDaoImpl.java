package com.ht.zc.admin.dao.impl;

import org.springframework.stereotype.Repository;

import com.ht.sys.dao.impl.BaseDaoImpl;
import com.ht.zc.admin.bean.ProductinfoTwo;
import com.ht.zc.admin.dao.ProductinfoTwoDao;

/**
 * ProductinfoTwo数据访问层
 * <p>众筹商品信息表</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * <p>联系方式:939474528@qq.com</p>
 * @author qj
 * @date 2014-11-7 10:43:27
 */
@Repository("productinfoTwoDaoImpl")
public class ProductinfoTwoDaoImpl extends BaseDaoImpl<ProductinfoTwo> implements ProductinfoTwoDao{
	
	/**
	 * 根据活动id删除众筹活动商品
	 * 
	 * @param activityId  众筹活动id
	 * */
	public void deleteByBackInfo(Long activityId){
		jdbcTemplate.update("delete from t_crowdfunding_productinfo_two where ACTIVITY_ID=?", new Object[]{activityId});
	}
}