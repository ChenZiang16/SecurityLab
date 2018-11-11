package com.security.entry;

import java.util.Date;

public class Attachment {
	
	private int attachId;
	private int fileId;
	private String attachName;
	private String path;
	private int deleteFlag;
	private Date uploadTime;
	private Date deleteTime;
	
	public int getAttachId() {
		return attachId;
	}
	public void setAttachId(int attachId) {
		this.attachId = attachId;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public String getAttachName() {
		return attachName;
	}
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
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
	
}
