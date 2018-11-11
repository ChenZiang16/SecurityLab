package com.security.dao;

import java.util.List;

import com.security.entry.News;

public interface NewsDao {
	
	public News getNews(News news);
	
	public List<News> getNewsList(String newsType);
	
	public int upNew(News news);
}
