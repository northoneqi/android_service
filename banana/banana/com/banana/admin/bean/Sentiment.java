package com.banana.admin.bean;

import java.util.Date;

public class Sentiment implements java.io.Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	//主键id
	private String id;
	//标题
	private String title;
	//url
	private String url;
	//HTML
	private String html;
	//内容
	private String content;
	//时间
	private Date time;
	//创建时间
	private Date createTime;
	//来源
	private String source;
	//评论
	private String comment;
	//转发
	private int forwarded;
	//类型
	private String type;
	//行业id
	private int industryId;
	//语词
	private int semantic;
	//地址
	private String area;
	//主题名
	private String subjectName;
	//话题名
	private String topicName;
	//相似
	private int similarity;
	//评论数
	private int comments;
	//作者
	private String author;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getForwarded() {
		return forwarded;
	}
	public void setForwarded(int forwarded) {
		this.forwarded = forwarded;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getIndustryId() {
		return industryId;
	}
	public void setIndustryId(int industryId) {
		this.industryId = industryId;
	}
	public int getSemantic() {
		return semantic;
	}
	public void setSemantic(int semantic) {
		this.semantic = semantic;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public int getSimilarity() {
		return similarity;
	}
	public void setSimilarity(int similarity) {
		this.similarity = similarity;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	//描述
	private String remark;


}
