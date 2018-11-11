package com.security.entry;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class News {
	
	private long newsId;
	private String title;
	private String Content;
	private String newsType;
	@JsonFormat(pattern="[yyyy-MM-dd]")
	private Date publishTime;
	
	public long getNewsId() {
		return newsId;
	}
	public void setNewsId(long newsId) {
		this.newsId = newsId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

}
