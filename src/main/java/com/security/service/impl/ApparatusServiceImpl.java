package com.security.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.dao.ApparatusDao;
import com.security.entry.Apparatus;
import com.security.service.ApparatusService;
@Service
public class ApparatusServiceImpl implements ApparatusService {
	@Autowired
	private ApparatusDao apparatusdao;
	
	@Override
	public List<Apparatus> getApparatus(Map<String,Object> map) {
		return apparatusdao.getApparatus(map);
	}

}
