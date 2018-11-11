package com.security.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class AppFilter
 */
public class AppFilter implements Filter {

	private static Logger logger = Logger.getLogger(AppFilter.class);
	private FilterConfig filterConfig;

    public AppFilter() {
    	System.out.println("** *** ****  SecurityLab load filter success !  *** *** ****");
    }


	public void destroy() {
    	System.out.println("** *** ****  SecurityLab filter destroyed !  *** *** ****");
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest )request;
		logger.info("** *** **** request url: "+ req.getRequestURL().toString());
		System.out.println("** *** **** request url: "+ req.getRequestURL().toString());
		
		ServletContext context = filterConfig.getServletContext();
		Map<String,Integer> map = (Map<String, Integer>) context.getAttribute("countMap");
		Set<String> countIpSet =(Set<String>) context.getAttribute("countIpSet"); 
		countIpSet.add(request.getRemoteAddr());
		Integer count = map.get("count");
		if(count==null){
			count =0;
		}

		count=count+1;
		//存储全部访问量
		map.put("count", count);
		
		//存储全部访问的ip
		map.put("ipCount", countIpSet.size());
		context.setAttribute("countMap", map);
		
		chain.doFilter(request, response);
	}

	//初始化
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig= filterConfig;
    	System.out.println("** *** ****  SecurityLab init filter success !  *** *** ****");
	}

}
