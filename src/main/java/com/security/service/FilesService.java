package com.security.service;

import java.util.List;

import com.security.entry.Attachment;
import com.security.entry.Files;
import com.security.entry.User;

public interface FilesService {
	
	//上传文献内容
	public int upWords(Files file);
	//获取文献内容
	public Files getWords(Files file);
	//删除文献
	public int deleteWords(Files file);
	//统计文献数量
	public int countNum(User user);
	//获取文件列表
	public List<Files> getFileList(User user);
	//获取附件列表
	public List<Attachment> getAttachList(Files file);
	
}
