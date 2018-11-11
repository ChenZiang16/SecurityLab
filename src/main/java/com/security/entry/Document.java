package com.security.entry;

import java.util.Date;

public class Document {
	
	private long docId;
	private long userId;
	private String title;
	private String path;
	private Date uploadTime; 
	
	public long getDocId() {
		return docId;
	}
	public void setDocId(long docId) {
		this.docId = docId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
