package com.security.dao;

import java.util.List;

import com.security.entry.Attachment;
import com.security.entry.Files;
import com.security.entry.User;

public interface FilesDao {
	public int upWords(Files file);
	public Files getWords(Files file);
	public int deleteWords(Files file);
	public int countNum(User user);
	public List<Files> getFileList(User user);
	public List<Attachment> getAttachList(Files file);
}
