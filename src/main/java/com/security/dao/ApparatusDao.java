package com.security.dao;

import java.util.List;
import java.util.Map;

import com.security.entry.Apparatus;

public interface ApparatusDao {
	List<Apparatus> getApparatus(Map<String,Object> map);
}
