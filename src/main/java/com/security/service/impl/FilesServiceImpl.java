package com.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.dao.FilesDao;
import com.security.entry.Attachment;
import com.security.entry.Files;
import com.security.entry.User;
import com.security.service.FilesService;
@Service
public class FilesServiceImpl implements FilesService {
	
	@Autowired
	private FilesDao filesDao;
	
	@Override
	public int upWords(Files file) {
		return filesDao.upWords(file);
	}

	@Override
	public Files getWords(Files file) {
		return filesDao.getWords(file);
	}

	@Override
	public int deleteWords(Files file) {
		return filesDao.deleteWords(file);
	}

	@Override
	public int countNum(User user) {
		return filesDao.countNum(user);
	}

	@Override
	public List<Files> getFileList(User user) {
		return filesDao.getFileList(user);
	}

	@Override
	public List<Attachment> getAttachList(Files file) {
		return filesDao.getAttachList(file);
	}


}
