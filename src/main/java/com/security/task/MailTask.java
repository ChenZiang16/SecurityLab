package com.security.task;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.security.controller.ApparatusController;
import com.security.controller.MailController;
import com.security.service.DocumentService;
import com.security.service.FilesService;
import com.security.service.UserService;
import com.security.utils.ReturnCode;

@Component
public class MailTask implements ApplicationContextAware{
	
	private static Logger logger = Logger.getLogger(MailTask.class);
	
	private  ApplicationContext applicationContext;
	
	@Autowired
	public ServletContext servletContext;
	@Autowired
	private MailController controller;
	@Autowired
	private UserService userService;
	@Autowired
	private FilesService filesService;
	@Autowired
	private DocumentService documentservice;
	
	public void sendMail(){
		
		Map<String,Integer> countMap;
		Integer countVisit = 0;
		Integer countIp = 0;
		try{
			countMap = (Map<String, Integer>) servletContext.getAttribute("countMap");
			countVisit = countMap.get("count");
			countIp = countMap.get("ipCount");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		Integer userCount = userService.countUser();
		Integer wordCount = filesService.countNum(null);
		Integer docCount = documentservice.countDoc();
		
		//邮件内容 
		String message = "实验室管理平台处理请求次数:"+countVisit+"，历史访问IP数:"+countIp+"，注册用户人数:" + userCount 
				+"，上传文献数量:" + wordCount +"，上传文档数量:" + docCount ;
		
		System.out.println(message);
		
		String code;
		
		try {
			code = controller.sendManagerMail(message);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		if(!ReturnCode.SUCCESS.getCode().equals(code)){
			System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") +"发送邮件失败");
		}else{
			System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") +"发送邮件成功");
		}
		
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		
		this.applicationContext = applicationContext;
		
	}

}
