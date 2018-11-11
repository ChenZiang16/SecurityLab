package com.security.service;

import java.util.List;

import com.security.entry.Message;

public interface MessageService {
	public int insertMsg(Message msg);
	public List<Message> getMsgs();
}
