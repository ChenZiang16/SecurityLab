package com.security.utils;

public enum NewsType {
	FOCUS_NEWS("0","焦点新闻"),
	COMPREHENSIVE_NEWS("1","综合新闻"),
	RESEARCH_TRENDS("2","科研动态");


	String code;
	String typeStr;
	
	NewsType(String code,String typeStr){
		this.code=code;
		this.typeStr=typeStr;
	}
	

	public void setCode(String code) {
		this.code = code;
	}


	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	

	public String getCode() {
		return code;
	}


	public String getTypeStr() {
		return typeStr;
	}
	
	public static String getCodeByTypeStr(String typeStr){
		
		for(NewsType news:NewsType.values()){
			if(news.getTypeStr().equals(typeStr)){
				return news.getCode();
			}
		}
		
		return "";
	}

}
