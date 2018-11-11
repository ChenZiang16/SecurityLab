package com.security.service;

import java.util.List;

import com.security.entry.News;

public interface NewsService {
	//获取新闻信息
	public News getNews(News news);
	//获取新闻列表
	public List<News> getNewsList(String newsType);
	
	public int upNew(News news);
}
