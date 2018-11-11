package com.security.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.security.entry.CodeAndMsg;
import com.security.entry.News;
import com.security.service.NewsService;
import com.security.utils.JsonUtils;
import com.security.utils.NewsType;

@Controller
public class NewsController  extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(NewsController.class);
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping("/getNews")
	public ModelAndView getNews(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mv = new ModelAndView();
		
		String newsId = request.getParameter("id");
		
		if(StringUtils.isBlank(newsId)){
			mv.setViewName("index");
			return mv;
		}
		
		News news = new News();
		news.setNewsId(Long.valueOf(newsId));
		try{
			news = newsService.getNews(news);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		ModelMap mm = new ModelMap();
		mm.put("title", news.getTitle());
		mm.put("content", news.getContent());
		
		mv.addObject("view",mm);
		mv.setViewName("news");
		
		return mv;
		
	}
	
	@RequestMapping("/getNewsList")
	@ResponseBody
	public String getNewsList(HttpServletRequest request, HttpServletResponse response){
		
		Map<String,Object> newsMap = new HashMap<>();
		CodeAndMsg codeMsg = new CodeAndMsg();
		try{
			
			newsMap.put("point", newsService.getNewsList("0"));
			newsMap.put("multi", newsService.getNewsList("1"));
			newsMap.put("science", newsService.getNewsList("2"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(newsMap !=null){
			codeMsg.setCode("200");
			codeMsg.setMsg("查询成功!");
			codeMsg.setContent(JsonUtils.parse(newsMap));
			return JsonUtils.parse(codeMsg);
		}
		
		codeMsg.setCode("500");
		codeMsg.setMsg("查询失败!");
		return JsonUtils.parse(codeMsg);
	}
	
	@RequestMapping("/upNews")
	@ResponseBody
	public String upNews(HttpServletRequest request, HttpServletResponse response){
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String newsType = request.getParameter("newsType");
		
		CodeAndMsg codeMsg = new CodeAndMsg();

		if(StringUtils.isBlank(title)){
			codeMsg.setCode("500");
			codeMsg.setMsg("标题不能为空！");
			return JsonUtils.parse(codeMsg);
		}
		
		if(StringUtils.isBlank(content)){
			codeMsg.setCode("500");
			codeMsg.setMsg("正文不能为空！");
			return JsonUtils.parse(codeMsg);
		}
		
		News news = new News();
		
		news.setTitle(title);	
		news.setContent(content);
		news.setNewsType(NewsType.getCodeByTypeStr(newsType));
		news.setPublishTime(new Date());
		
		try{
			newsService.upNew(news);
		}catch(Exception e){
			e.printStackTrace();
			codeMsg.setCode("500");
			codeMsg.setMsg("添加新闻出错！");
			return JsonUtils.parse(codeMsg);
		}
		
		codeMsg.setCode("200");
		codeMsg.setMsg("添加成功！");
		return JsonUtils.parse(codeMsg);
		
	}


}
