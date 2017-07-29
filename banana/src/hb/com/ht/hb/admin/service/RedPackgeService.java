package com.ht.hb.admin.service;

import com.ht.hb.admin.bean.RedOrderDetail;
import com.ht.sys.service.BaseService;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

public interface RedPackgeService extends BaseService<RedOrderDetail>{
	
	public void  list(Pager<Object[]>  pager, QueryUtil queryUtil);
	

}
