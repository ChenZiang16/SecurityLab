package com.security.entry;

import java.math.BigDecimal;
import java.util.Date;

public class Apparatus {
	
	private int appaId;
	private String appaName;
	private String path;
	private String dept;
	private String manager;
	private String status;
	private Date importTime;
	private BigDecimal price;
	

	public int getAppaId() {
		return appaId;
	}
	public void setAppaId(int appaId) {
		this.appaId = appaId;
	}
	public String getAppaName() {
		return appaName;
	}
	public void setAppaName(String appaName) {
		this.appaName = appaName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getImportTime() {
		return importTime;
	}
	public void setImport_time(Date importTime) {
		this.importTime = importTime;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
