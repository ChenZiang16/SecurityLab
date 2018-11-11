package com.security.controller;

import java.util.List;

import javax.servlet.http.Cookie;
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
import com.security.entry.User;
import com.security.service.UserService;
import com.security.utils.JsonUtils;
import com.security.utils.MD5Utils;

@Controller
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(RegisterController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户注册操作
	 */
	@RequestMapping("/register")
	@ResponseBody
	protected String registerUser(HttpServletRequest request, HttpServletResponse response){
		
		String name = request.getParameter("userName");
		String pwd = request.getParameter("password");
		String rePwd = request.getParameter("repassword");
		String telephone = request.getParameter("telephone");
		String gender = request.getParameter("gender");
		String nationality = request.getParameter("nationality");
		String address = request.getParameter("address");
		String dept = request.getParameter("address");
		
		CodeAndMsg codeMsg = new CodeAndMsg();

		if(StringUtils.isAnyBlank(name,pwd)){
			codeMsg.setCode("404");
			codeMsg.setMsg("用户名或密码不能为空");
			return JsonUtils.parse(codeMsg);
		}
		
		if(!StringUtils.equals(pwd, rePwd)){
			codeMsg.setCode("404");
			codeMsg.setMsg("两次输入密码不一致！");
			return JsonUtils.parse(codeMsg);
		}
		
		List<User> userList = userService.getUserList();
		
		for(User user:userList){
			if(name.equals(user.getName())){
				codeMsg.setCode("500");
				codeMsg.setMsg("此用户名已被注册！");
				return JsonUtils.parse(codeMsg);
			}
		}
		
		User user = new User();
		user.setName(name);
		user.setGender(Long.valueOf(gender));
		user.setAddress(address);
		user.setDept(dept);
		user.setNationality(nationality);
		//密码加密
		user.setPassword(MD5Utils.encodeByMd5(pwd));
		user.setTelephone(telephone);
		
		int flag = 0;
		try{
			flag = userService.registerUser(user);
		}catch(Exception e){
			logger.error("新用户"+name+"注册时产生异常！");
			e.printStackTrace();
		}
		
		
		if(flag != 0){
			
			User result = userService.getUser(user);
			
			Cookie cookie = new Cookie("userId",String.valueOf(result.getId()));
			response.addCookie(cookie);
			
			logger.info("新用户"+name+"注册成功！");
			codeMsg.setCode("200");
			codeMsg.setMsg("注册成功！");
			return JsonUtils.parse(codeMsg);
		}
		
		logger.info("新用户"+name+"注册失败！");
		codeMsg.setCode("500");
		codeMsg.setMsg(name + "注册失败！");
		return JsonUtils.parse(codeMsg);
	}

}

