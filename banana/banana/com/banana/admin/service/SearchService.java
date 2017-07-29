package com.banana.admin.service;

import net.sf.json.JSONObject;

import com.banana.admin.bean.Sentiment;
import com.ht.sys.service.BaseService;

/**
 * <p>搜索接口</p>
 * @author qj
 * @date 2016-09-14
 * @version 1.0
 */
public interface SearchService extends BaseService<Sentiment> {

	//索引数据列表
	public String queryList(JSONObject dataJson);
	
}
