package com.security.controller;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.security.entry.CodeAndMsg;
import com.security.entry.Document;
import com.security.factory.BeanFactory;
import com.security.service.DocumentService;
import com.security.utils.CookiesUtils;
import com.security.utils.JsonUtils;
@Controller
public class DocumentController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(DocumentController.class);
	
	@Autowired
	private DocumentService documentservice;
	
	@RequestMapping("/getDocList")
	@ResponseBody
	public String getDocList(HttpServletRequest request, HttpServletResponse response){
		
		String userId = CookiesUtils.getUserId(request);
		String admin = request.getParameter("role");
		
		CodeAndMsg codeMsg = BeanFactory.getCodeAndMsgBean();
		
		
		if(StringUtils.isAllBlank(userId,admin)){
			codeMsg.setCode("404");
			codeMsg.setMsg("用户未登录！");
			return JsonUtils.parse(codeMsg);
		}
		
		List<Document> docList = null;
		try{
			docList = documentservice.getDocList(userId);
		}catch(Exception e){
			e.printStackTrace();
			codeMsg.setCode("500");
			codeMsg.setMsg("获取文档列表失败!");
			return JsonUtils.parse(codeMsg);
		}
		
		codeMsg.setCode("200");
		codeMsg.setMsg("获取文档列表成功!");
		codeMsg.setContent(JsonUtils.parse(docList));
		
		return JsonUtils.parse(codeMsg);
	}
	
	
	
}
