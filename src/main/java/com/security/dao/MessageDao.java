package com.security.dao;

import java.util.List;

import com.security.entry.Message;

public interface MessageDao {
	
	public int insertMsg(Message msg);
	public List<Message> getMsgs();
	
}
