package com.security.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.security.entry.Apparatus;
import com.security.service.ApparatusService;
import com.security.utils.JsonUtils;

@Controller
public class ApparatusController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ApparatusController.class);
	
	@Autowired
	private ApparatusService apparatusservice;
	
	/**
	 *根据获取器材列表 
	 */
	@RequestMapping("/getApparatus")
	@ResponseBody
	public String getApparatusWithRight(HttpServletRequest request, HttpServletResponse response){
		//FIXME添加用户权限校验
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("role", 0);
		map.put("type", 1);
		List<Apparatus> list = apparatusservice.getApparatus(map);
		System.out.println(JsonUtils.parse(list));
		return JsonUtils.parse(list);
	}
	
	@RequestMapping("/getJpg")
	protected void getJpg(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String filePath = request.getParameter("filePath");
		filePath = filePath.replace("-", "\\");	
		File file = new File(filePath);
		FileInputStream in = new FileInputStream(file);
		
		Image image = ImageIO.read(in);
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		
		BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		tag.getGraphics().drawImage(image, 0, 0, width, height, null);
		ImageIO.write(tag, "JPEG", response.getOutputStream());
		
	}

}
