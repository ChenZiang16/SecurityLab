package com.security.utils;

import org.apache.commons.lang3.StringUtils;

public enum ReturnCode {
	
	UP_WORDS_CODE("1001","标题或者内容不能为空!"),
	NO_RECIPIENT("1002","接收人地址不能为空!"),
	EXCEPTION("500","成功"),
	SUCCESS("200","成功");
	
	String msg;
	String code;
	
	private ReturnCode(String code,String msg){
		this.msg =msg;
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}

	public String getCode() {
		return code;
	}
	
	public String getMsgByCode(String code){
		
		for(ReturnCode codes:ReturnCode.values()){
			if(StringUtils.endsWith(codes.getCode(), code)){
				return codes.getMsg();
			}
		}
		
		return "";
	}


}
