package com.banana.admin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 城市 - 实体类
 * @date 2015-11-11
 * @author qj
 * @version 1.0
 */
@Entity
@Table(name = "city")
public class City implements java.io.Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 城市名称
	 */
	private String cityName;
	/**
	 * 城市排序键
	 */
	private String citySortkey;

	@Id
	@GeneratedValue
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "city_name")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "city_sortkey")
	public String getCitySortkey() {
		return citySortkey;
	}

	public void setCitySortkey(String citySortkey) {
		this.citySortkey = citySortkey;
	}

}
