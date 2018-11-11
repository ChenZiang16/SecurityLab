package com.security.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	static ObjectMapper mapper;
	static{
		 mapper = new ObjectMapper();  
	}
	
	public static String parse(Object obj){
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
