package com.banana.admin.dao.impl;

import org.springframework.stereotype.Repository;

import com.banana.admin.bean.City;
import com.banana.admin.bean.Goods;
import com.banana.admin.dao.CityDao;
import com.banana.admin.dao.GoodsDao;
import com.ht.sys.dao.impl.BaseDaoImpl;

/**
 * 商品 - DAO实现类
 * @author qj
 * @date 2015-11-11
 * @version 1.0
 */
@Repository("GoodsDao")
public class GoodsDaoImpl extends BaseDaoImpl <Goods> implements GoodsDao{

}
