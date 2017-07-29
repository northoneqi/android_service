package com.ht.sys.util;

import java.util.Map;

/**
 * <p>缓存帮助类</p>
 */
public class CacheUtil {

	private static CacheUtil instance;
	
	private static Map<String, Object> cacheObj;  //缓存容器
	
	/**
	 * <p>本容器采用单例模式实现</p>
	 * */
	public static CacheUtil getInstance() {
		if(instance == null){
			instance = new CacheUtil();
		}
	    return instance;
	}
	
	/**
	 * <p>添加缓存对象</p>
	 * @param key
	 * @param value
	 */
	public void add(String key, Object value){
		cacheObj.put(key, value);
	}
	
	/**
	 * <p>移除缓存对象</p>
	 * @param key
	 */
	public void evict(String key){
		cacheObj.remove(key);
	}
	
}
