package com.ht.sys.util;

import java.util.List;

/**
 * <p>
 * 数据响应类
 * </p>
 * 
 * @author qj   
 * @date 2015-11-11 下午01:56:21 
 * @version 1.0
 */
public class Pager<T> {
	/**
	 * 返回码 ,0：表示成功 ；1：表示失败
	 */
	private String event = "1";
	/**
	 * 描述信息
	 */
	private String message;
	/**
	 * 起始位置, 默认为-1
	 */
	private int start = -1;

	/**
	 * 最大查询条数, 默认为-1
	 */
	private int limit = -1;

	/**
	 * 单个数据对象
	 */
	private T object;
	/**
	 * 多个数据对象
	 */
	private List<T> objectList;
	/**
	 * 当前页
	 */
	private int currentPage;
	/**
	 * 每页条数
	 */
	private int pageSize;
	/**
	 * 总数
	 */
	private int total;
	/**
	 * 总页数
	 */
	private int totalPage;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public List<T> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<T> objectList) {
		this.objectList = objectList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
