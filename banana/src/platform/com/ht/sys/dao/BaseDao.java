package com.ht.sys.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.Nullable;
import org.elasticsearch.index.query.QueryBuilder;

import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

/** 
* <p>类功能说明: 数据库基本CRUD封装类</p>
* <p>Title: ds.java</p> 
* <p>Description:</p> 
* <p>Copyright: Copyright (c) 2013</p> 
* @author qj 
* @date 2013-7-24 下午02:47:29
* @version V1.0
*/
public abstract interface BaseDao<T> {

	/**
	 * <p>清空缓存 </p>
	 * @author qj  
	 * @date 2013-7-24 下午02:46:44 
	 */
	public abstract void clear();

	/**
	 * <p>存储实体</p>
	 * @author qj  
	 * @date 2013-7-24 下午02:51:24 
	 * @param entity  实体
	 */
	public abstract void save(T entity);

	
	/**
	 * <p>批量存储实体</p>
	 * @author qj  
	 * @date 2013-7-24 下午02:51:49 
	 * @param list  实体列表
	 */
	public abstract void saveList(List<T> list);

	
	/**
	 * <p>更新实体</p>
	 * @author qj  
	 * @date 2013-7-24 下午02:52:17 
	 * @param entity  实体
	 */
	public abstract void update(T entity);

	
	/**
	 * <p>批量更新实体</p>
	 * @author qj  
	 * @date 2013-7-24 下午02:52:40 
	 * @param list  实体列表
	 */
	public abstract void updateList(List<T> list);

	
	/**
	 * <p>根据ID查找实体</p>
	 * @author qj  
	 * @date 2013-7-24 下午02:53:28 
	 * @param id  实体编号
	 * @return  如果查到返回实体，否则返回null
	 */
	public abstract T get(Serializable id);

	
	/**
	 * <p>延迟查找实体</p>
	 * @author qj  
	 * @date 2013-7-24 下午02:54:20 
	 * @param id  实体编号
	 * @return  如果查找到实体则返回这个实体的代理对象，否则返回null
	 */
	public abstract T load(Serializable id);
	
	/**
	 * <p>根据条件得到值</p>
	 * @author qj  
	 * @date 2013-7-24 下午04:01:58 
	 * @param paramString
	 * @param values
	 * @return List<T>
	 */
	public List<T> get(String[] paramString, String[] values);

	
	/**
	 * <p>查询所有的</p>
	 * @author qj  
	 * @date 2013-7-24 下午02:55:41 
	 * @return List<T>
	 */
	public abstract List<T> getAll();

	
	/**
	 * <p>删除实体</p>
	 * @author qj  
	 * @date 2013-7-24 下午02:53:04 
	 * @param entity 实体
	 */
	public abstract void delete(T entity);
	
	
	/**
	 * <p>根据id删除实体</p>
	 * @author qj  
	 * @date 2013-7-24 下午04:06:20 
	 * @param id
	 */
	public abstract void delete(Serializable id);

	
	/**
	 * <p>批量删除</p>
	 * @author qj  
	 * @date 2013-7-24 下午04:05:40 
	 * @param ids  编号数组
	 */
	public abstract void delete(Serializable...ids);


	/**
	 * <p>删除所有的实体</p>
	 * @author qj  
	 * @date 2013-8-13 上午11:56:46 
	 */
	public abstract void delete();
	
	/**
	 * <p>根据条件删除实体</p>
	 * @author qj  
	 * @date 2013-8-13 上午11:56:46 
	 * @param sql sql语句
	 */
	public abstract void delete(String sql);
	
	/**
	 * <p>分页查询</p>
	 * @author qj  
	 * @date 2013-7-31 下午02:11:15 
	 * @param pager  分页信息
	 * @param queryUtil  查询条件信息
	 */
	public abstract void findByPager(Pager<T> pager, QueryUtil queryUtil);
	
	/**
	 * <p>不带条件的分页查询</p>
	 * @author qj  
	 * @date 2013-7-31 下午02:11:15 
	 * @param pager  分页信息
	 */
	public abstract void findByPager(Pager<T> pager);
	
	/**
	 * <p>条件查询</p>
	 * @author qj  
	 * @date 2013-7-31 下午02:11:15 
	 * @param queryUtil  查询条件信息
	 */
	public abstract List<T> findByCondition(QueryUtil queryUtil);
	
	/**
	 * <p>创建索引</p>
	 * @param index 索引名
	 * @param type 类型
	 * @param id 主键
	 * @return
	 */
	public abstract IndexResponse saveDoc(String index, String type, String id, String json);

	/**
	 * <p>创建索引</p>
	 * @param index 索引名
	 * @param type 类型
	 * @param 主键自动生成
	 * @return
	 */
	public abstract IndexResponse saveDoc(String index, String type, String json);
	
	/**
	 * <p>根据id删除文档</p>
	 * @param index 索引
	 * @param type 类型
	 * @param id 主键
	 * @return
	 */
	public abstract DeleteRequestBuilder  deleteDoc(String index, String type, String id);

	/**
	 * <p>批量删除文档</p>
	 * @param index
	 * @param type
	 * @param queryBuilder
	 */
	public abstract void deleteDoc(String index, String type, QueryBuilder queryBuilder);

	/**
	 * <p>获取数据</p>
	 * @param index 索引
	 * @param type 类型
	 * @param id 主键
	 * @return
	 */
	public abstract GetResponse getDoc(String index, String type, String id);
	
	
    /**
     * 搜索
     * indexNames    索引名称
     * content 查询内容
     * from    从第几条记录开始（必须大于等于0）
     * offset    一共显示多少条记录（必须大于0）
     * sortField    排序字段名称
     * sortType    排序方式（asc，desc）
     * */
    public abstract SearchResponse search(String index, String type, Map<String, Object> content, int from, int offset, @Nullable String sortField, @Nullable String sortType);


}
