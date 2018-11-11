package com.security.entry;

import java.util.List;

public class User {

	private long id;
	private String name;
	private String password;
	private String telephone;
	private String address;
	private String nationality;
	private int literatureNum;
	private String dept;
	private long gender;
	private List<Files> fileList;
	
	public long getGender() {
		return gender;
	}

	public void setGender(long gender) {
		this.gender = gender;
	}

	public void setId(long id){
		this.id =id;
	}
	
	public long getId(){
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public int getLiteratureNum() {
		return literatureNum;
	}

	public void setLiteratureNum(int literatureNum) {
		this.literatureNum = literatureNum;
	}
	
	public List<Files> getFileList() {
		return fileList;
	}

	public void setFileList(List<Files> fileList) {
		this.fileList = fileList;
	}

	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dept=" + dept
				+ ", gender=" + gender + "]";
	}

}