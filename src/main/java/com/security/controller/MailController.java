package com.security.controller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.security.entry.CodeAndMsg;
import com.security.utils.JsonUtils;
import com.security.utils.ReturnCode;

@Controller
public class MailController {
	
	private static Logger logger = Logger.getLogger(MailController.class);
	
	
	@Value("#{app['emailPath']}")
	private String emailPath;
	@Value("#{app['taskEmailPath']}")
	private String taskEmailPath;
	@Value("#{app['emailAddress']}")
	private String emailAddress;
	
	@RequestMapping("sendMail")
	@ResponseBody
	public String sendMail(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException{
		
		CodeAndMsg codeMsg = new CodeAndMsg();
		
		String[] recipientsA = request.getParameter("recipients").split(";");
		
		if(StringUtils.isAnyBlank(recipientsA)){
			codeMsg.setCode("404");
			codeMsg.setMsg("接收人地址不能为空!");
			return JsonUtils.parse(codeMsg);
		}
		//正则表达式过滤非法收件人地址
		String regex = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";
		
		String[] recipientsB = new String[recipientsA.length];
		int flag =0;
		for(int i =0;i<recipientsA.length;i++){
			if(recipientsA[i].matches(regex)){
				recipientsB[flag] = recipientsA[i];
				flag++;
			}
		}
		
		if(StringUtils.isAnyBlank(recipientsB)){
			codeMsg.setCode("404");
			codeMsg.setMsg("无合法接收人地址!");
			return JsonUtils.parse(codeMsg);
		}
		//抄送
		String cc = request.getParameter("cc");
		//密送
		String bcc = request.getParameter("bcc");
		//正文
		String message = request.getParameter("message");
		
		Properties properties = new Properties();
		properties.put("mail.debug", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.host", "smtp.163.com");
		properties.put("mail.transport.protocol", "smtp");
		
		Session session = Session.getInstance(properties);
		Message msg  =new  MimeMessage(session);
		
		try {
			msg.setSubject("SecurityLab Administrator");
			msg.setText(message);
			//msg.setFrom(new InternetAddress("java_mail_004@163.com"));
			msg.setFrom(new InternetAddress("withchenziang@163.com"));
			
			if(StringUtils.isNotBlank(cc)){
				msg.addRecipient(MimeMessage.RecipientType.CC, new InternetAddress(cc));
			}
			if(StringUtils.isNotBlank(bcc)){
				msg.addRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(bcc));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			codeMsg.setCode("500");
			codeMsg.setMsg("发送邮件失败!");
			return JsonUtils.parse(codeMsg);
		}

		Transport transport;
		try {
			transport = session.getTransport();
			//transport.connect("java_mail_004","javamail");
			transport.connect("withchenziang", "wode163youxiang");
			
			Address[] addresses = new Address[recipientsB.length];
			for(int i=0;i<recipientsB.length;i++){
				addresses[i] = new InternetAddress(recipientsB[i]);
			}
			
			transport.sendMessage(msg, addresses);
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
			codeMsg.setCode("500");
			codeMsg.setMsg("发送邮件失败!");
			return JsonUtils.parse(codeMsg);
		}
		
		String time = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");

      OutputStream out = new FileOutputStream(emailPath+"MyEmail-"+time+".eml");
		
      try {
			msg.writeTo(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		codeMsg.setCode("200");
		codeMsg.setMsg("发送邮件成功!");
		return JsonUtils.parse(codeMsg);
	}
	
	public String sendManagerMail(String message) throws FileNotFoundException{
		
		String recipient = emailAddress;
		
		if(StringUtils.isBlank(recipient)){
			return ReturnCode.NO_RECIPIENT.getCode();
		}
		
		Properties properties = new Properties();
		properties.put("mail.debug", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.host", "smtp.163.com");
		properties.put("mail.transport.protocol", "smtp");
		
		Session session = Session.getInstance(properties);
		Message msg  =new  MimeMessage(session);
		
		try {
			msg.setSubject("SecurityLab Ranning Message");
			msg.setText(message);
			msg.setFrom(new InternetAddress("withchenziang@163.com"));
			
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnCode.EXCEPTION.getCode();
		}

		Transport transport;
		try {
			transport = session.getTransport();
			transport.connect("withchenziang","wode163youxiang");
			Address[] addresses = new Address[1];
			
			//String[] recipients = recipient.split(";");
			
			//addresses[1] = new InternetAddress(recipients[1]);
			addresses[0] = new InternetAddress(recipient);
			transport.sendMessage(msg,addresses);
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnCode.EXCEPTION.getCode();
		}
		
		String time = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");

		OutputStream out = new FileOutputStream(taskEmailPath+"MyTaskEmail-"+time+".eml");
		
	      try {
				msg.writeTo(out);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
				return ReturnCode.EXCEPTION.getCode();
			} catch (Exception e) {
				e.printStackTrace();
				return ReturnCode.EXCEPTION.getCode();
			}finally{
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
	      return ReturnCode.SUCCESS.getCode();
	}
	
}
