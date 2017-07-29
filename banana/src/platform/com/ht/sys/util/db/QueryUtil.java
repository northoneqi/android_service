package com.ht.sys.util.db;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>查询条件帮助类</p>
 * @author qj
 */
public class QueryUtil {
	/**where语句*/
	private StringBuffer whereHql = new StringBuffer();
	/**where语句中对应的占位符*/
	private ArrayList<Object> parames = new ArrayList<Object>();
	/**排序方式*/
	private static List<String> orderPatterns = new ArrayList<String>();
	/**待排序字段和排序方式*/
	private Map<String, String> orderby = new LinkedHashMap<String, String>();
	/**分组排序*/
	private Set<String> groupby = new HashSet<String>();

	static{
		orderPatterns.add("asc");
		orderPatterns.add("desc");
	}
	
	
	/**
	 * <p>拼接hql语句，只支持占位符（防止sql注入）</p>
	 * @author qj  
	 * @date 2013-7-31 下午01:48:23 
	 * @param columnName  实体对象的属性
	 * @param condition  
	 * 				条件表达式,暂时只支持'=', '!=', 'likeL','likeR','like','>','->'
	 * 				'<','<=','between', 'in', 'not in', null基本条件
	 * @param values  条件表达式所对应的值
	 */
	public void put(String columnName, String condition, Object... values) {
		
		if (columnName == null || columnName.equals(""))
			throw new RuntimeException("columnName不能为空");
		if (condition == null || condition.equals(""))
			throw new RuntimeException("condition不能为空");
		if (values == null && condition.equals("null")){
			//当条件为null时，不必要设置null
		}else if(values == null || "".equals(values)){
			throw new RuntimeException("values不能为空");
		}

		if (condition.equals("=")) {
			//对于等于的处理
			whereHql.append(" and "+columnName+" = ? ");
			parames.add(values[0]);
			
		} else if (condition.equals("!=")) {
			//对于等于的处理
			whereHql.append(" and "+columnName+" != ? ");
			parames.add(values[0]);
			
		} else if (condition.equals("likeL")) {
			//对于左模糊查询
			whereHql.append(" and " + columnName + " like ? ");
			parames.add("%"+values[0]);

		} else if (condition.equals("like")) {
			//对于模糊查询
			whereHql.append(" and " + columnName + " like ? ");
			parames.add("%"+values[0]+"%");

		}else if (condition.equals("likeR")) {
			//对于右模糊查询
			whereHql.append(" and " + columnName + " like ? ");
			parames.add(values[0]+"%");

		} else if (condition.equals(">")) {
			//对于大于的处理
			whereHql.append(" and " + columnName + " > ? ");
			parames.add(values[0]);
			
		}else if (condition.equals(">=")) {
			//对于大于等于的处理
			whereHql.append(" and " + columnName + " >= ? ");
			parames.add(values[0]);
			
		} else if (condition.equals("<")) {
			//对于小于处理
			whereHql.append(" and " + columnName + " < ? ");
			parames.add(values[0]);
			
		}else if (condition.equals("<=")) {
			//对于小于等于处理
			whereHql.append(" and " + columnName + " <= ? ");
			parames.add(values[0]);
			
		} else if (condition.equals("between")) {

			if (values.length != 2) {
				throw new RuntimeException("between的比较值只能是两个");
			}
			
			whereHql.append(" and " + columnName + " between ? and ? ");
			parames.add(values[0]);
			parames.add(values[1]);

		} else if (condition.equals("in")) {
			//对于in的处理
			whereHql.append(" and " + columnName + " in(");
			for (int i = 0; i < values.length - 1; i++) {
				whereHql.append("?,");
				parames.add(values[i]);
			}

			whereHql.append("?) ");
			parames.add(values[values.length - 1]);
		} else if (condition.equals("not in")) {
			//对于in的处理
			whereHql.append(" and " + columnName + " not in(");
			for (int i = 0; i < values.length - 1; i++) {
				whereHql.append("?,");
				parames.add(values[i]);
			}

			whereHql.append("?) ");
			parames.add(values[values.length - 1]);
		} else if(condition.equals("null")){
			//对于null的处理
			whereHql.append(" and " + columnName + " is null ");
			
		}else{
			throw new RuntimeException("您输入的条件表达式'"+condition+"'暂时不支持，请等待下一个版本");
		}
	}
	
	public void addCondition(String condition, Object... values){
		whereHql.append(condition);
		
		for (int i = 0; i < values.length; i++) {
			parames.add(values[i]);
		}
	}
	/**
	 * @Description: 存放待排序字段和方式
	 * @author qj  
	 * @date 2013-7-31 下午01:52:07 
	 * @param columnName   待排序字段
	 * @param orderPattern 排序方式，只有desc,asc两种
	 */
	public void putOrder(String columnName, String orderPattern){
		if(orderPatterns.contains(orderPattern)){
			orderby.put(columnName, orderPattern);
		}else{
			throw new RuntimeException("不支持的排序方式'"+orderPattern+"'");
		}
	}

	/**
	 * <p> 得到拼接后的whereHql</p>
	 * @author qj  
	 * @date 2013-7-31 下午01:53:19 
	 * @return  返回hql的条件表达式
	 */
	public String getWherewhereHql(){
		return whereHql.toString();
	}
	
	/**
	 * <p>得到排序map</p>
	 * @author qj  
	 * @date 2013-7-31 下午01:53:57 
	 * @return  返回排序的map
	 */
	public Map<String, String> getOrderBy(){
		return this.orderby;
	}
	
	
	/**
	 * <p>得到值</p>
	 * @author qj  
	 * @date 2013-7-31 下午01:54:18 
	 * @return  返回占位符中的值
	 */
	public Object[] getParames(){
		return parames.toArray();
	}
	
	/**
	 * <p>得到值</p>
	 * @author qj  
	 * @date 2013-7-31 下午01:54:18 
	 * @return  返回占位符中的值
	 */
	public ArrayList<Object> getArrayParames(){
		return parames;
	}
	
	
	/**
	 * <p>添加分组字段</p>
	 * @author qj  
	 * @date 2013-7-31 下午01:54:44 
	 * @param columnName  要分组的字段
	 */
	public void putGroupField(String columnName){
		groupby.add(" "+columnName);
	}
	
	
	/**
	 * <p>得到分组信息</p>
	 * @author qj  
	 * @date 2013-7-31 下午01:55:11 
	 * @return 返回分组信息
	 */
	public Set<String> getGroupBy(){
		return groupby;
	}
	
	
	public StringBuffer getWhereHql() {
		return whereHql;
	}


	public void setWhereHql(StringBuffer whereHql) {
		this.whereHql = whereHql;
	}


	public static List<String> getOrderPatterns() {
		return orderPatterns;
	}


	public static void setOrderPatterns(List<String> orderPatterns) {
		QueryUtil.orderPatterns = orderPatterns;
	}


	public Map<String, String> getOrderby() {
		return orderby;
	}


	public void setOrderby(Map<String, String> orderby) {
		this.orderby = orderby;
	}


	public Set<String> getGroupby() {
		return groupby;
	}


	public void setGroupby(Set<String> groupby) {
		this.groupby = groupby;
	}


	public void setParames(ArrayList<Object> parames) {
		this.parames = parames;
	}


	/**
	 * 测试
	 * */
	public static void main(String args[]) {
		QueryUtil pa = new QueryUtil();
		pa.put("name", "=2", 1, 2, 3);
		System.out.println(pa);
	}
}
