package com.security.controller;

import java.util.Date;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.security.entry.CodeAndMsg;
import com.security.entry.Message;
import com.security.factory.BeanFactory;
import com.security.service.MessageService;
import com.security.utils.CookiesUtils;
import com.security.utils.JsonUtils;
import com.security.utils.MqSessionUtils;

@Controller
public class MessageController {
	
	private static Logger logger = Logger.getLogger(MessageController.class);
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping("message")
	public String message(HttpServletRequest request, HttpServletResponse response){
		return "message";
	}
	
	@RequestMapping("mq")
	public String mq(HttpServletRequest request, HttpServletResponse response){
		return "mq";
	}
	
	@RequestMapping("getMsgList")
	@ResponseBody
	public String getMsgList(HttpServletRequest request, HttpServletResponse response){
		List<Message> list = messageService.getMsgs();
		CodeAndMsg codeMsg = BeanFactory.getCodeAndMsgBean();
		if(list.isEmpty()){
			codeMsg.setCode("500");
			codeMsg.setMsg("获取留言列表失败！");
			return JsonUtils.parse(codeMsg);
		}
		codeMsg.setCode("200");
		codeMsg.setMsg("获取留言列表成功！");
		codeMsg.setContent(JsonUtils.parse(list));
		System.out.println(JsonUtils.parse(list));
		return JsonUtils.parse(codeMsg);
	}
	
	@RequestMapping("saveMessage")
	@ResponseBody
	public String saveMessage(HttpServletRequest request, HttpServletResponse response){
		
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String content = request.getParameter("content");
		
		CodeAndMsg codeMsg = BeanFactory.getCodeAndMsgBean();
		
		if(StringUtils.isBlank(content)){
			codeMsg.setCode("404");
			codeMsg.setMsg("留言内容不能为空！");
			return JsonUtils.parse(codeMsg);
		}
		
		if(StringUtils.isBlank(name)){
			codeMsg.setCode("404");
			codeMsg.setMsg("留言姓名不能为空！");
			return JsonUtils.parse(codeMsg);
		}
		
		Message msg = new Message();
		msg.setName(name);
		msg.setEmail(email);
		msg.setGender(gender);
		msg.setContent(content);
		msg.setUploadTime(new Date());
		
		int flag = messageService.insertMsg(msg);
		
		if(flag ==1){
			codeMsg.setCode("200");
			codeMsg.setMsg("留言成功！");
			return JsonUtils.parse(codeMsg);
		}
		
		codeMsg.setCode("500");
		codeMsg.setMsg("留言失败！");
		return JsonUtils.parse(codeMsg);
	}
	
	
	@RequestMapping("sendMessage")
	@ResponseBody
	public String sendMessage(HttpServletRequest request, HttpServletResponse response){
		
		String recipientsP = request.getParameter("recipients");
		String messageStr = request.getParameter("message");
		CodeAndMsg codeMsg =  BeanFactory.getCodeAndMsgBean();
		
		Session session;
		try{
			session = MqSessionUtils.getSession();
		}catch(Exception e){
			e.printStackTrace();
			codeMsg.setCode("500");
			codeMsg.setMsg(e.getMessage());
			return JsonUtils.parse(codeMsg);
		}
		
		try {
			Destination destination = session.createQueue(recipientsP);
			MessageProducer producer = session.createProducer(destination);
			TextMessage message = session.createTextMessage(messageStr);
			producer.send(message);
			session.commit();
		} catch (JMSException e) {
			e.printStackTrace();
			codeMsg.setCode("500");
			codeMsg.setMsg(e.getMessage());
			return JsonUtils.parse(codeMsg);
		}finally{
			try {
				session.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		
		codeMsg.setCode("200");
		codeMsg.setMsg("发送成功！");
		return JsonUtils.parse(codeMsg);
	}
	
	@RequestMapping("getMessage")
	@ResponseBody
	public String getMessage(HttpServletRequest request, HttpServletResponse response){
		
		String userId = CookiesUtils.getUserId(request);
		
		CodeAndMsg codeMsg = BeanFactory.getCodeAndMsgBean();
		
		if(StringUtils.isBlank(userId)){
			codeMsg.setCode("404");
			codeMsg.setMsg("用户未登录！");
			return JsonUtils.parse(codeMsg);
		}
		
		Session session;
		try{
			session = MqSessionUtils.getSession();
		}catch(Exception e){
			e.printStackTrace();
			codeMsg.setCode("500");
			codeMsg.setMsg(e.getMessage());
			return JsonUtils.parse(codeMsg);
		}
		
		TextMessage message;
		
		try {
			Destination destination = session.createQueue(userId);
			MessageConsumer consumer = session.createConsumer(destination);
			message = (TextMessage)consumer.receive(500);
			session.commit();
		} catch (JMSException e) {
			e.printStackTrace();
			codeMsg.setCode("500");
			codeMsg.setMsg(e.getMessage());
			return JsonUtils.parse(codeMsg);
		}finally{
			try {
				session.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		
		if(message !=null){
			try {
				codeMsg.setCode("200");
				codeMsg.setMsg("接收消息成功！");
				codeMsg.setContent(message.getText());
				return JsonUtils.parse(codeMsg);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		
		codeMsg.setCode("500");
		codeMsg.setMsg("无相关消息！");
		return JsonUtils.parse(codeMsg);
	}
	
}
