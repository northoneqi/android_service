package com.ht.zc.admin.dao;

import com.ht.sys.dao.BaseDao;
import com.ht.zc.admin.bean.ProductinfoTwo;

/**
 * ProductinfoTwo 数据访问层接口
 * <p>众筹商品信息表</p>
 * <p>Copyright: Copyright (c) 2014@qj</p>
 * <p>联系方式:939474528@qq.com</p>
 * @author qj
 * @date 2014-11-7 10:43:27
 */
public interface ProductinfoTwoDao extends BaseDao<ProductinfoTwo>{

	/**
	 * 根据活动id删除众筹活动商品
	 * 
	 * @param activityId  众筹活动id
	 * */
	public void deleteByBackInfo(Long activityId);
}