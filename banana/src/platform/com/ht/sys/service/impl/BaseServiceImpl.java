package com.ht.sys.service.impl;

import java.io.Serializable;
import java.util.List;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.transaction.annotation.Transactional;

import com.ht.sys.dao.BaseDao;
import com.ht.sys.service.BaseService;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

/** 
 * <p>类功能说明: 系统服务封装类</p>
 * <p>Title: BaseServiceImpl.java</p> 
 * <p>Description:</p> 
 * <p>Copyright: Copyright (c) 2013</p> 
 * @author qj 
 * @date 2013-7-25 下午09:27:52
 * @version V1.0
 */

@Transactional
public class BaseServiceImpl<T> implements BaseService<T>{
	
	protected BaseDao<T> baseDao;
	
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * <p>清空缓存 </p>
	 * @author qj  
	 * @date 2013-7-24 下午02:46:44 
	 */
	public void clear(){
		this.baseDao.clear();
	}

	/**
	 * <p>存储实体</p>
	 * @author qj  
	 * @date 2013-7-24 下午02:51:24 
	 * @param entity  实体
	 */
	public void save(T entity){
		this.baseDao.save(entity);
	}

	
	/**
	 * <p>批量存储实体</p>
	 * @author qj  
	 * @date 2013-7-24 下午02:51:49 
	 * @param list  实体列表
	 */
	public void saveList(List<T> list){
		this.baseDao.saveList(list);
	}

	
	/**
	 * <p>更新实体</p>
	 * @author qj  
	 * @date 2013-7-24 下午02:52:17 
	 * @param entity  实体
	 */
	public void update(T entity){
		this.baseDao.update(entity);
	}

	
	/**
	 * <p>批量更新实体</p>
	 * @author qj  
	 * @date 2013-7-24 下午02:52:40 
	 * @param list  实体列表
	 */
	public void updateList(List<T> list){
		this.baseDao.updateList(list);
	}

	
	/**
	 * <p>根据ID查找实体</p>
	 * @author qj  
	 * @date 2013-7-24 下午02:53:28 
	 * @param id  实体编号
	 * @return  如果查到返回实体，否则返回null
	 */
	public T get(Serializable id){
		return this.baseDao.get(id);
	}

	
	/**
	 * <p>延迟查找实体</p>
	 * @author qj  
	 * @date 2013-7-24 下午02:54:20 
	 * @param id  实体编号
	 * @return  如果查找到实体则返回这个实体的代理对象，否则返回null
	 */
	public T load(Serializable id){
		return this.baseDao.load(id);
	}
	
	/**
	 * <p>根据条件得到值</p>
	 * @author qj  
	 * @date 2013-7-24 下午04:01:58 
	 * @param paramString
	 * @param values
	 * @return
	 */
	public List<T> get(String[] paramString, String[] values){
		return this.baseDao.get(paramString, values);
	}

	
	/**
	 * <p>查询所有的</p>
	 * @author qj  
	 * @date 2013-7-24 下午02:55:41 
	 * @return
	 */
	public List<T> getAll(){
		return this.baseDao.getAll();
	}

	
	/**
	 * <p>删除实体</p>
	 * @author qj  
	 * @date 2013-7-24 下午02:53:04 
	 * @param entity 实体
	 */
	public void delete(T entity){
		this.baseDao.delete(entity);
	}
	
	
	/**
	 * <p>根据id删除实体</p>
	 * @author qj  
	 * @date 2013-7-24 下午04:06:20 
	 * @param id
	 */
	public void delete(Serializable id){
		this.baseDao.delete(id);
	}

	
	/**
	 * <p>批量删除</p>
	 * @author qj  
	 * @date 2013-7-24 下午04:05:40 
	 * @param ids  编号数组
	 */
	public void delete(Serializable...ids){
		this.baseDao.delete(ids);
	}
	
	/**
	 * <p>删除所有的数据</p>
	 * @author qj  
	 * @date 2013-7-24 下午04:05:40 
	 */
	public void delete(){
		this.baseDao.delete();
	}
	
	

	/**
	 * <p>分页查询</p>
	 * @author qj  
	 * @date 2013-7-31 下午02:11:15 
	 * @param pager  分页信息
	 * @param queryUtil  查询条件信息
	 */
	public void findByPager(Pager<T> pager, QueryUtil queryUtil){
		this.baseDao.findByPager(pager, queryUtil);
	}
	
	/**
	 * <p>不带条件的分页查询</p>
	 * @author qj  
	 * @date 2013-7-31 下午02:11:15 
	 * @param pager  分页信息
	 */
	public void findByPager(Pager<T> pager){
		this.baseDao.findByPager(pager);
	}
	
	/**
	 * <p>条件查询</p>
	 * @author qj  
	 * @date 2013-7-31 下午02:11:15 
	 * @param queryUtil  查询条件信息
	 */
	public List<T> findByCondition(QueryUtil queryUtil){
		return this.baseDao.findByCondition(queryUtil);
	}

	@Override
	public IndexResponse saveDoc(String index, String type, String id, String json) {
		return this.baseDao.saveDoc(index, type, id, json);
	}

	@Override
	public IndexResponse saveDoc(String index, String type, String json) {
		return this.baseDao.saveDoc(index, type, json);
	}

	@Override
	public DeleteResponse deleteDoc(String index, String type, String id) {
		return null;
	}

	@Override
	public void deleteDoc(String index, String type, QueryBuilder queryBuilder) {
		
	}

	@Override
	public GetResponse getDoc(String index, String type, String id) {
		return null;
	}
}
