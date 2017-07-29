package com.ht.box.admin.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "T_MciroUserInfo")
public class MciroUserInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  int ID;
	private String Name;
	private String Address;
	private String Telephone;
	private Date PeisongTime;
	private String PeisongMoment;
	private String OpenId;
	private String Area;
	private int ActivityId;
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	public Date getPeisongTime() {
		return PeisongTime;
	}
	public void setPeisongTime(Date peisongTime) {
		PeisongTime = peisongTime;
	}
	public String getPeisongMoment() {
		return PeisongMoment;
	}
	public void setPeisongMoment(String peisongMoment) {
		PeisongMoment = peisongMoment;
	}
	public String getOpenId() {
		return OpenId;
	}
	public void setOpenId(String openId) {
		OpenId = openId;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public int getActivityId() {
		return ActivityId;
	}
	public void setActivityId(int activityId) {
		ActivityId = activityId;
	}
	

}
