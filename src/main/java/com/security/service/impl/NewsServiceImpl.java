package com.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.security.dao.NewsDao;
import com.security.entry.News;
import com.security.service.NewsService;
@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private NewsDao newsdao;
	
	@Override
	public News getNews(News news) {
		return newsdao.getNews(news);
	}

	@Override
	public List<News> getNewsList(String newsType) {
		return newsdao.getNewsList(newsType);
	}

	@Override
	public int upNew(News news){
		return newsdao.upNew(news);
	}
	
	

}
