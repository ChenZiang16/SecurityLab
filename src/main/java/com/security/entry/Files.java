package com.security.entry;

import java.util.Date;
import java.util.List;

public class Files {

	private int fileId;
	private String fileType;
	private long userId;
	private String title;
	private String content;
	private String attachNum;
	private Date uploadTime;
	private Date deleteTime;
	private List<Attachment> attachList;

	public int getFileId() {
		return fileId;
	}
	
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}
	
	public String getAttachNum() {
		return attachNum;
	}

	public void setAttachNum(String attachNum) {
		this.attachNum = attachNum;
	}

	public List<Attachment> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<Attachment> attachList) {
		this.attachList = attachList;
	}

	
}
