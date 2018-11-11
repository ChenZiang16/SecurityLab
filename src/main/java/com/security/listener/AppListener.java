package com.security.listener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.security.task.MailTask;

/**
 * Application Lifecycle Listener implementation class AppListener
 *
 */
public class AppListener implements ServletContextListener {

    public AppListener() {
    }

    
    public void contextInitialized(ServletContextEvent arg0) {
    	  ServletContext context = arg0.getServletContext();
    	  //统计全部访问量
          Map<String, Integer> countMap = new HashMap<String, Integer>();
          //利用set集合的不可重复性统计ip
          Set<String> countIpSet = new HashSet<String>();
          context.setAttribute("countMap", countMap);
          context.setAttribute("countIpSet", countIpSet);
          
    }

    
    public void contextDestroyed(ServletContextEvent arg0) {
    }
	
}
