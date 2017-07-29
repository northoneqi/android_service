package com.ht.sys.dao.impl;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.apache.commons.lang.ArrayUtils;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.CommonTermsQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.Assert;

import com.ht.sys.dao.BaseDao;
import com.ht.sys.util.ElasticsearchUtil;
import com.ht.sys.util.GenericsUtils;
import com.ht.sys.util.Pager;
import com.ht.sys.util.db.QueryUtil;

/**
 *    类功能说明: 基本数据库CRUD封装类  
 * <p>
 * Title: BaseDaoImpl.java
 * </p>
 *    
 * <p>
 * Description:
 * </p>
 *    
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 *    @author qj   @date 2013-7-25 下午08:45:17  @version V1.0
 */

public class BaseDaoImpl<T> extends ElasticsearchUtil implements BaseDao<T> {

	protected Class<T> entityClass;
	protected HibernateTemplate hibernateTemplate;
	protected SessionFactory sessionFactory;
	protected JdbcTemplate jdbcTemplate;
	protected Session session;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());
	}

	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
		this.sessionFactory = hibernateTemplate.getSessionFactory();
		// 不能在此给得到session，否则session不在一个线程中,下面的的初始化session是一种错误的做法
		// this.session = this.sessionFactory.getCurrentSession();
	}

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	/**
	 *  
	 * <p>
	 * 得到当前线程中的session
	 * </p>
	 *  @author qj    @date 2013-7-25 下午11:40:02 
	 * 
	 * @return 返回当前线程中的session
	 */
	public Session getSession() {
		this.session = this.sessionFactory.getCurrentSession();
		return this.session;
	}

	/**
	 *  
	 * <p>
	 * 清空缓存
	 * </p>
	 *  @author qj    @date 2013-7-24 下午02:46:44 
	 */
	public void clear() {
		this.getSession().flush();
		this.getSession().clear();
	}

	/**
	 * <p>
	 * 删除实体
	 * </p>
	 *  @author qj    @date 2013-7-24 下午02:53:04 
	 * 
	 * @param entity
	 *            实体
	 */
	public void delete(T entity) {
		Assert.notNull(entity, "删除实体不能为空");
		this.getSession().delete(entity);
	}

	/**
	 *  
	 * <p>
	 * 根据id删除实体
	 * </p>
	 *  @author qj    @date 2013-7-24 下午04:06:20 
	 * 
	 * @param id
	 */
	public void delete(Serializable id) {
		Assert.notNull(id, "主键不能为空");
		Object obj = this.getSession().get(entityClass, id);
		this.getSession().delete(obj);

	}

	/**
	 *  
	 * <p>
	 * 批量删除
	 * </p>
	 *  @author qj    @date 2013-7-24 下午04:05:40 
	 * 
	 * @param ids
	 *            编号数组
	 */
	public void delete(Serializable... ids) {
		Assert.notEmpty(ids, "ids 主键不能为空");
		for (int i = 0; i < ids.length; i++) {
			Object entity = this.getSession().get(this.entityClass, ids[i]);
			this.getSession().delete(entity);

			if (i % 20 == 0) {
				this.clear();
			}
		}
	}

	/**
	 *  
	 * <p>
	 * 删除所有的实体
	 * </p>
	 *  @author qj    @date 2013-8-13 上午11:56:46 
	 */
	public void delete() {
		this.getSession().createQuery("delete " + entityClass.getName())
				.executeUpdate();
		// this.jdbcTemplate.execute("TRUNCATE TABLE sys_log");
	}

	/**
	 *  
	 * <p>
	 * 根据条件删除实体
	 * </p>
	 *  @author qj    @date 2013-8-13 上午11:56:46 
	 * 
	 * @param sql
	 *            sql语句
	 */
	public void delete(String sql) {
		this.jdbcTemplate.execute(sql);
	}

	/**
	 *  
	 * <p>
	 * 根据ID查找实体
	 * </p>
	 *  @author qj    @date 2013-7-24 下午02:53:28 
	 * 
	 * @param id
	 *            实体编号
	 * @return 如果查到返回实体，否则返回null
	 */
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		Assert.notNull(id, "主键不能为空");
		return (T) this.getSession().get(entityClass, id);
	}

	/**
	 *  
	 * <p>
	 * 根据条件得到值
	 * </p>
	 *  @author qj    @date 2013-7-24 下午04:01:58 
	 * 
	 * @param paramString
	 * @param values
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<T> get(String[] paramString, String[] values) {
		String hql = "SELECT o FROM " + entityClass.getName() + " o ";
		if (paramString.length > 0) {
			hql += " WHERE ";
			for (String str : paramString) {
				hql += str;
			}
		}
		Query query = this.getSession().createQuery(hql);
		this.setQueryParams(query, values);
		List<T> list = query.list();
		return list;
	}

	/**
	 *  
	 * <p>
	 * 查询所有的
	 * </p>
	 *  @author qj    @date 2013-7-24 下午02:55:41 
	 * 
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		ClassMetadata classMetadata = this.sessionFactory
				.getClassMetadata(this.entityClass);
		String hql = "from " + this.entityClass.getName();
		if (ArrayUtils.contains(classMetadata.getPropertyNames(), "orderIndex")) {
			hql = hql + " as entity order by entity." + "orderIndex" + " asc";
		}
		return (List<T>) this.getSession().createQuery(hql).setCacheable(true)
				.list();
	}

	/**
	 *  
	 * <p>
	 * 延迟查找实体
	 * </p>
	 *  @author qj    @date 2013-7-24 下午02:54:20 
	 * 
	 * @param id
	 *            实体编号
	 * @return 如果查找到实体则返回这个实体的代理对象，否则返回null
	 */
	@SuppressWarnings("unchecked")
	public T load(Serializable id) {
		Assert.notNull(id, "主键不能为空");
		return (T) this.getSession().load(this.entityClass, id);
	}

	/**
	 *  
	 * <p>
	 * 存储实体
	 * </p>
	 *  @author qj    @date 2013-7-24 下午02:51:24 
	 * 
	 * @param entity
	 *            实体
	 */
	public void save(T entity) {
		Assert.notNull(entity, "保持实例类对象是空的，必须实例化");
		this.getSession().save(entity);
	}

	/**
	 *  
	 * <p>
	 * 批量存储实体
	 * </p>
	 *  @author qj    @date 2013-7-24 下午02:51:49 
	 * 
	 * @param list
	 *            实体列表
	 */
	public void saveList(List<T> list) {
		Assert.notEmpty(list, "批量保存的对象不能为空");
		for (int i = 0; i < list.size(); i++) {
			this.getSession().save(list.get(i));
			if (i % 100 == 0) {
				this.clear();
			}
		}
	}

	/**
	 *  
	 * <p>
	 * 更新实体
	 * </p>
	 *  @author qj    @date 2013-7-24 下午02:52:17 
	 * 
	 * @param entity
	 *            实体
	 */
	public void update(T entity) {
		Assert.notNull(entity, "更新实例类对象是空的，必须实例化");
		this.getSession().merge(entity);
	}

	/**
	 *  
	 * <p>
	 * 批量更新实体
	 * </p>
	 *  @author qj    @date 2013-7-24 下午02:52:40 
	 * 
	 * @param list
	 *            实体列表
	 */
	public void updateList(List<T> list) {
		Assert.notEmpty(list, "批量更新的对象不能为空");
		for (int i = 0; i < list.size(); i++) {
			this.getSession().saveOrUpdate(list.get(i));
			if (i % 100 == 0) {
				this.clear();
			}
		}
	}

	/**
	 *  
	 * <p>
	 * 设置查询参数的值
	 * </p>
	 *  @author qj    @date 2013-7-26 下午09:26:38 
	 * 
	 * @param query
	 * @param queryParams
	 */
	protected void setQueryParams(Query query, Object[] queryParams) {
		if (queryParams != null && queryParams.length > 0) {
			for (int i = 0; i < queryParams.length; i++) {
				query.setParameter(i, queryParams[i]);
			}
		}
	}

	/**
	 * <p>
	 * 组装order by语句
	 * </p>
	 * 
	 * @param orderby
	 * @return String
	 */
	protected String buildOrderby(Map<String, String> orderby) {
		StringBuffer orderbyql = new StringBuffer("");
		if (orderby != null && orderby.size() > 0) {
			orderbyql.append(" order by ");
			for (String key : orderby.keySet()) {
				orderbyql.append("").append(key).append(" ")
						.append(orderby.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length() - 1);
		}
		return orderbyql.toString();
	}

	/**
	 * <p>
	 * 组装group by语句
	 * </p>
	 * 
	 * @param groupby
	 * @return String
	 */
	protected String buildGroupby(Set<String> groupby) {
		StringBuffer groupbyhql = new StringBuffer("");
		if (groupby != null && groupby.size() > 0) {
			groupbyhql.append(" group by ");
			for (String item : groupby) {
				groupbyhql.append(item).append(",");
			}
			groupbyhql.deleteCharAt(groupbyhql.length() - 1);
		}
		return groupbyhql.toString();
	}

	/**
	 *  
	 * <p>
	 * 分页查询
	 * </p>
	 *  @author qj    @date 2013-7-31 下午02:11:15 
	 * 
	 * @param pager
	 *            分页信息
	 * @param queryUtil
	 *            查询条件信息
	 */
	@SuppressWarnings("unchecked")
	public void findByPager(Pager<T> pager, QueryUtil queryUtil) {
		Assert.notNull(pager, "分页信息不能为空");
		Assert.notNull(queryUtil, "查询条件不能为空");

		String entityname = this.entityClass.getName();
		String whereHql = queryUtil.getWherewhereHql();
		Map<String, String> orderBy = queryUtil.getOrderBy();
		Set<String> groupBy = queryUtil.getGroupBy();

		String hql = "select o from "
				+ entityname
				+ " o "
				+ (whereHql == null || "".equals(whereHql.trim()) ? ""
						: " where 1=1 " + whereHql) + buildOrderby(orderBy)
				+ buildGroupby(groupBy);
		Query query = getSession().createQuery(hql);
		setQueryParams(query, queryUtil.getParames());

		if (pager.getStart() != -1 && pager.getLimit() != -1) {
			query.setFirstResult(pager.getStart()).setMaxResults(
					pager.getLimit());
		}
		List<T> list = (List<T>) query.list();
		pager.setObjectList(list);

		query = getSession().createQuery(
				"select count("
						+ getCountField(this.entityClass)
						+ ") from "
						+ entityname
						+ " o "
						+ (whereHql == null || "".equals(whereHql.trim()) ? ""
								: "where 1=1 " + whereHql));
		setQueryParams(query, queryUtil.getParames());

		Object obj = query.uniqueResult();
		pager.setTotal(new Integer((obj == null ? "0" : obj.toString())));
	}

	/**
	 *  
	 * <p>
	 * 不带条件的分页查询
	 * </p>
	 *  @author qj    @date 2013-7-31 下午02:11:15 
	 * 
	 * @param pager
	 *            分页信息
	 */
	@SuppressWarnings("unchecked")
	public void findByPager(Pager<T> pager) {
		Assert.notNull(pager, "分页信息不能为空");

		String entityname = this.entityClass.getName();

		String hql = "select o from " + entityname + " o ";
		Query query = getSession().createQuery(hql);

		if (pager.getStart() != -1 && pager.getLimit() != -1) {
			query.setFirstResult(pager.getStart()).setMaxResults(
					pager.getLimit());
		}
		List<T> list = (List<T>) query.list();
		pager.setObjectList(list);

		query = getSession().createQuery(
				"select count(" + getCountField(this.entityClass) + ") from "
						+ entityname + " o ");
		Object obj = query.uniqueResult();
		pager.setTotal(new Integer((obj == null ? "0" : obj.toString())));
	}

	/**
	 *  
	 * <p>
	 * 条件查询
	 * </p>
	 *  @author qj    @date 2013-7-31 下午02:11:15 
	 * 
	 * @param queryUtil
	 *            查询条件信息
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCondition(QueryUtil queryUtil) {
		Assert.notNull(queryUtil, "查询条件不能为空");

		String entityname = this.entityClass.getName();
		String whereHql = queryUtil.getWherewhereHql();
		Map<String, String> orderBy = queryUtil.getOrderBy();
		Set<String> groupBy = queryUtil.getGroupBy();

		String hql = "select o from "
				+ entityname
				+ " o "
				+ (whereHql == null || "".equals(whereHql.trim()) ? ""
						: " where 1=1 " + whereHql) + buildOrderby(orderBy)
				+ buildGroupby(groupBy);
		Query query = getSession().createQuery(hql);
		setQueryParams(query, queryUtil.getParames());

		List<T> list = (List<T>) query.list();
		return list;
	}

	/**
	 * <p>
	 * 获取实体的名称
	 * </p>
	 * 
	 * @param <E>
	 * @param clazz
	 *            实体类
	 * @return String
	 */
	protected static <E> String getEntityName(Class<E> clazz) {
		String entityname = clazz.getSimpleName();
		Entity entity = clazz.getAnnotation(Entity.class);
		if (entity.name() != null && !"".equals(entity.name())) {
			entityname = entity.name();
		}
		return entityname;
	}

	protected static <E> String getCountField(Class<E> clazz) {
		String out = "o";
		try {
			PropertyDescriptor[] propertyDescriptors = Introspector
					.getBeanInfo(clazz).getPropertyDescriptors();
			for (PropertyDescriptor propertydesc : propertyDescriptors) {
				Method method = propertydesc.getReadMethod();
				if (method != null
						&& method.isAnnotationPresent(EmbeddedId.class)) {
					PropertyDescriptor[] ps = Introspector.getBeanInfo(
							propertydesc.getPropertyType())
							.getPropertyDescriptors();
					out = "o."
							+ propertydesc.getName()
							+ "."
							+ (!ps[1].getName().equals("class") ? ps[1]
									.getName() : ps[0].getName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	/**
	 * <p>
	 * 取得当前类的实际表名
	 * </p>
	 * 
	 * @return String
	 */
	protected String getTableName() {
		AbstractEntityPersister classMetadata = (SingleTableEntityPersister) sessionFactory
				.getClassMetadata(entityClass);
		return classMetadata.getTableName();
	}

	/**
	 * sqlserver分页
	 * */
	public String getPaginationSql(int start, int limit, String tableName,
			String fields, String filter, String group, String orderBy,
			String primaryKey, String primaryKeyTable) {
		String sql = "SELECT TOP " + limit + " " + fields + "  FROM "
				+ tableName + ((filter != null) ? " where " + filter : " 1=1 ")
				+ "and " + primaryKey + " not in" + "( " + "SELECT top "
				+ start + " " + primaryKey + " FROM " + primaryKeyTable
				+ ((filter != null) ? " where " + filter : " ")
				+ ((group != null) ? " group by " + group : " ")
				+ ((orderBy != null) ? " order by " + orderBy : " ") + ") "
				+ ((group != null) ? " group by " + group : " ")
				+ ((orderBy != null) ? " order by " + orderBy : " ");
		return sql;
	}

	/**
	 * <p>
	 * 创建索引
	 * </p>
	 * 
	 * @param index
	 *            索引名
	 * @param type
	 *            类型
	 * @param id
	 *            主键
	 * @return
	 */
	@Override
	public IndexResponse saveDoc(String index, String type, String id,
			String json) {
		// return client.prepareIndex(index, type, id).setSource(json).get();
		return null;
	}

	/**
	 * <p>
	 * 创建索引
	 * </p>
	 * 
	 * @param index
	 *            索引名
	 * @param type
	 *            类型
	 * @param 主键自动生成
	 * @return
	 */
	@Override
	public IndexResponse saveDoc(String index, String type, String json) {
		// return client.prepareIndex(index, type).setSource(json).get();
		return null;
	}

	@Override
	public DeleteRequestBuilder deleteDoc(String index, String type, String id) {
		// return client.prepareDelete(index, type, id);
		return null;
	}

	@Override
	public void deleteDoc(String index, String type, QueryBuilder queryBuilder) {
		// TODO Auto-generated method stub

	}

	@Override
	public GetResponse getDoc(String index, String type, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchResponse search(String index, String type,
			Map<String, Object> content, int from, int offset,
			String sortField, String sortType) {

		if (offset <= 0) {
			return null;
		}

		// SearchResponse response =
		// client.prepareSearch().execute().actionGet();

		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
				.should(QueryBuilders.matchQuery("content", "华为手机"))
				.should(QueryBuilders.matchQuery("title", "手机"));

		SearchRequestBuilder sbuilder = client
				.prepareSearch("sentiment")
				// index name
				// .setTypes( "dataType") //type name
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(queryBuilder) // Query
				.setPostFilter(QueryBuilders.rangeQuery("artype").from(1).to(9)) // Filter
				.setFrom(0).setSize(10);
		System.out.println(sbuilder.toString());
		SearchResponse responses = sbuilder.execute().actionGet();
		System.out.println(responses.toString());

		return responses;
	}

	/**
	 * 组装数据
	 * 
	 * @param content
	 *            内容条件
	 * @return
	 */
	private QueryBuilder queryLoad(Map<String, Object> map) {

		QueryBuilder queryBuilders = null;

		for (Map.Entry<String, Object> entry : map.entrySet()) {

			queryBuilders = QueryBuilders.termQuery(entry.getKey(),
					entry.getValue());

		}

		return queryBuilders;
	}

	/* 简单的值校验 */
	private boolean checkValue(Object[] values) {
		if (values == null) {
			return false;
		} else if (values.length == 0) {
			return false;
		} else if (values[0] == null) {
			return false;
		} else if (values[0].toString().trim().isEmpty()) {
			return false;
		}
		return true;
	}

}
