package com.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.dao.MessageDao;
import com.security.entry.Message;
import com.security.service.MessageService;
@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	MessageDao messageDao;

	@Override
	public int insertMsg(Message msg) {
		return messageDao.insertMsg(msg);
	}

	@Override
	public List<Message> getMsgs() {
		return messageDao.getMsgs();
	}

}
