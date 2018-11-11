package com.security.service;

import java.util.List;
import java.util.Map;

import com.security.entry.Apparatus;

public interface ApparatusService {
	List<Apparatus> getApparatus(Map<String,Object> map);
	
}
