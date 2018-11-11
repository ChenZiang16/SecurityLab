package com.security.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.security.entry.CodeAndMsg;
import com.security.entry.User;
import com.security.factory.BeanFactory;
import com.security.service.DocumentService;
import com.security.service.FilesService;
import com.security.service.UserService;
import com.security.utils.CookiesUtils;
import com.security.utils.JsonUtils;
import com.security.utils.MD5Utils;
@Controller
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private FilesService filesService;
	@Autowired
	private DocumentService documentservice;
	
	/**
	 * 用户登录操作
	 */
	@RequestMapping("/login")
	@ResponseBody
	protected String loginUser(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		
		String name = request.getParameter("userName");
		String pwd = request.getParameter("password");
		
		CodeAndMsg codeMsg = new CodeAndMsg();
		
		if(StringUtils.isAnyBlank(name,pwd)){
			codeMsg.setCode("500");
			codeMsg.setMsg("用户名或密码不能为空");
			return JsonUtils.parse(codeMsg);
		}
		
		//密码加密后验证
		User user = new User();
		user.setName(name);
		user.setPassword(MD5Utils.encodeByMd5(pwd));
		user = userService.getUser(user);
		
		if(user ==null){
			codeMsg.setCode("500");
			codeMsg.setMsg("密码错误");
			return JsonUtils.parse(codeMsg);
		}
		
		Cookie cookie = new Cookie("userId",String.valueOf(user.getId()));
		response.addCookie(cookie);
		
		codeMsg.setCode("200");
		codeMsg.setMsg(name);
		return JsonUtils.parse(codeMsg);
		
	}
	
	/**
	 * 用户退出登录操作
	 */
	@RequestMapping("/logOut")
	@ResponseBody
	protected String logOut(HttpServletRequest request, HttpServletResponse response){
		
		Cookie[] cookies = request.getCookies();
		CodeAndMsg codeMsg = new CodeAndMsg();
		for(Cookie cookie:cookies){
			if("userId".equals(cookie.getName())){
				
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				codeMsg.setCode("200");
				codeMsg.setMsg("退出成功！");
				return JsonUtils.parse(codeMsg);
				
			}
		}
		
		codeMsg.setCode("500");
		codeMsg.setMsg("用户未登录！");
		return JsonUtils.parse(codeMsg);
		
	}
	
	/**
	 * 2018-04-07 11:01:20
	 * 获取用户简要信息,初始化用户界面时
	 */
	@RequestMapping("/getUserList")
	@ResponseBody
	protected String getUserList(HttpServletRequest request, HttpServletResponse response){
		
		List<User> users = userService.getUserList();
		
		CodeAndMsg codeMsg = BeanFactory.getCodeAndMsgBean();
		
		if(users !=null){
			codeMsg.setCode("200");
			codeMsg.setMsg("获取用户信息成功！");
			System.out.println(JsonUtils.parse(users));
			codeMsg.setContent(JsonUtils.parse(users));
			return JsonUtils.parse(codeMsg);
		}
		
		codeMsg.setCode("500");
		codeMsg.setMsg("获取用户信息失败！");
		return JsonUtils.parse(codeMsg);
	}
	
	/**
	 * 2018-04-07 11:01:20
	 * 获取用户简要信息,初始化用户界面时
	 */
	@RequestMapping("/getUserInfo")
	@ResponseBody
	protected String getUserInfo(HttpServletRequest request, HttpServletResponse response){
		
		String userId = CookiesUtils.getUserId(request);
		
		CodeAndMsg codeMsg = BeanFactory.getCodeAndMsgBean();
		
		if(StringUtils.isBlank(userId)){
			codeMsg.setCode("404");
			codeMsg.setMsg("用户未登录！");
			return JsonUtils.parse(codeMsg);
		}
		
		User user = new User();
		user.setId(Long.valueOf(userId));
		
		try{
			user = userService.getUser(user);
			if(user != null){
				codeMsg.setCode("200");
				codeMsg.setMsg("获取用户信息成功！");
				codeMsg.setContent(JsonUtils.parse(user));
				return JsonUtils.parse(codeMsg);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		codeMsg.setCode("500");
		codeMsg.setMsg("获取用户信息失败！");
		return JsonUtils.parse(codeMsg);
	}
	
	/**
	 * 2018-05-16 11:01:20
	 * 获取用户简要信息，管理员查询
	 */
	@RequestMapping("/getUserInfoAdmin")
	@ResponseBody
	protected String getUserInfoAdmin(HttpServletRequest request, HttpServletResponse response){
		
		String userName = request.getParameter("userName");
		String userId = request.getParameter("userId");
		
		CodeAndMsg codeMsg = BeanFactory.getCodeAndMsgBean();
		
		if(StringUtils.isAllBlank(userName,userId)){
			codeMsg.setCode("404");
			codeMsg.setMsg("请输入用户名或用户id！");
			return JsonUtils.parse(codeMsg);
		}
		
		User user = new User();
		
		user.setName(userName);
		if(StringUtils.isNotBlank(userId)){
			user.setId(Long.valueOf(userId));
		}
		
		
		try{
			user = userService.getUser(user);
			if(user != null){
				codeMsg.setCode("200");
				codeMsg.setMsg("获取用户信息成功！");
				codeMsg.setContent(JsonUtils.parse(user));
				return JsonUtils.parse(codeMsg);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		codeMsg.setCode("500");
		codeMsg.setMsg("获取用户信息失败！");
		return JsonUtils.parse(codeMsg);
	}
	
	
	@RequestMapping("/index")
	protected String index(HttpServletRequest request, HttpServletResponse response){
		return "index";
	}
	
	@RequestMapping("/labAbstra")
	protected String labAbstra(HttpServletRequest request, HttpServletResponse response){
		return "labAbstra";
	}
	
	@RequestMapping("/literatures")
	protected String literatures(HttpServletRequest request, HttpServletResponse response){
		return "literatures";
	}
	
	@RequestMapping("/documents")
	protected String documents(HttpServletRequest request, HttpServletResponse response){
		return "documents";
	}
	
	@RequestMapping("/users")
	protected String userCenter(HttpServletRequest request, HttpServletResponse response){
		return "users";
	}
	
	@RequestMapping("/apparatus")
	protected String apparatus(HttpServletRequest request, HttpServletResponse response){
		return "apparatus";
	}
	
	@RequestMapping("/connectUs")
	protected String connectUs(HttpServletRequest request, HttpServletResponse response){
		return "connectUs";
	}
	
	@RequestMapping("/getVideo")
	protected String getVedio(HttpServletRequest request, HttpServletResponse response){
		return "videoShow";
	}
	
	@RequestMapping("/administrator")
	protected String administrator(HttpServletRequest request, HttpServletResponse response){
		String error = request.getParameter("error");
		if(StringUtils.isNotBlank(error)){
			return "index";
		}
		return "administrator";
	}
	
	@RequestMapping("/loginAdmin")
	protected String loginAdmin(HttpServletRequest request, HttpServletResponse response){
		return "login";
	}
	
	@RequestMapping("/error")
	protected String error(HttpServletRequest request, HttpServletResponse response){
		return "error";
	}
	
	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	protected String verify(@RequestParam(value = "error", required = false) String error,HttpServletRequest request, HttpServletResponse response){
		
		if(StringUtils.isNotBlank(error)){
			return "error";
		}
		
		return "login";
	}
	
	@RequestMapping("/count")
	@ResponseBody
	protected String count(HttpServletRequest request, HttpServletResponse response){
		
		Map<String,Object> map = new HashMap<>();
		
		try{
			
			Integer userCount = userService.countUser();
			map.put("userCount", userCount);
			Integer wordCount = filesService.countNum(null);
			map.put("wordCount", wordCount);
			Integer docCount = documentservice.countDoc();
			map.put("docCount", docCount);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		CodeAndMsg codeMsg = new CodeAndMsg();
		
		if(map != null){
			codeMsg.setCode("200");
			codeMsg.setMsg("查询成功!");
			codeMsg.setContent(JsonUtils.parse(map));
			return JsonUtils.parse(codeMsg);
		}
		
		codeMsg.setCode("500");
		codeMsg.setMsg("查询失败!");
		return JsonUtils.parse(codeMsg);
		
	}
	
}

