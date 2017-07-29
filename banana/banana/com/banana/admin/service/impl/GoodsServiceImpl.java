package com.banana.admin.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banana.admin.bean.Goods;
import com.banana.admin.dao.CityDao;
import com.banana.admin.dao.GoodsDao;
import com.banana.admin.service.GoodsService;
import com.ht.sys.service.impl.BaseServiceImpl;
/**
 * 商品 -  实现类
 * @author qj
 * @date 2015-11-11
 * @version 1.0
 */
@Service("GoodsService")
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements	GoodsService   {

	private GoodsDao goodsDao;

    @Autowired
	public void setCommodityRecordDao(GoodsDao goodsDao){
		super.setBaseDao(goodsDao);
        this.goodsDao = goodsDao;
    }

    
    public GoodsDao getGoodsDao(){
        return goodsDao;
    }
}
