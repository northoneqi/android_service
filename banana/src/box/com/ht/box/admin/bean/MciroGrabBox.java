package com.ht.box.admin.bean;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "T_MciroGrabBox")
public class MciroGrabBox implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ID;
	private String OpenId;
	private String CreatTime;
	private int PeisongStatus;
	private int SKU;
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
	public String getOpenId() {
		return OpenId;
	}
	public void setOpenId(String openId) {
		OpenId = openId;
	}
	public int getPeisongStatus() {
		return PeisongStatus;
	}
	public void setPeisongStatus(int peisongStatus) {
		PeisongStatus = peisongStatus;
	}
	public int getSKU() {
		return SKU;
	}
	public void setSKU(int sKU) {
		SKU = sKU;
	}
	public String getCreatTime() {
		return CreatTime;
	}
	public void setCreatTime(String creatTime) {
		CreatTime = creatTime;
	}
	public int getActivityId() {
		return ActivityId;
	}
	public void setActivityId(int activityId) {
		ActivityId = activityId;
	}
	
	
	

}
