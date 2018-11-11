package com.security.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.security.entry.CodeAndMsg;
import com.security.entry.Document;
import com.security.entry.Files;
import com.security.entry.User;
import com.security.factory.BeanFactory;
import com.security.service.DocumentService;
import com.security.service.FilesService;
import com.security.utils.CookiesUtils;
import com.security.utils.JsonUtils;
import com.security.utils.NoticeUtils;

@Controller
public class UpLoadFileController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(UpLoadFileController.class);
	
	@Value("#{app['rootPath1']}")
	private String rootPath1;
	@Value("#{app['rootPath2']}")
	private String rootPath2;
	@Value("#{app['rootPath3']}")
	private String rootPath3;
	@Autowired
	private FilesService filesService;
	@Autowired
	private DocumentService documentservice;
	
	@RequestMapping("/upload")
	public ModelAndView upLoad(HttpServletRequest request,HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		User user = new User();
		user.setGender(1);
		model.addObject("user", user);
		model.setViewName("test");
		return model;
	}
	
	/**
	 * 2018-03-17 16:13:20
	 * 上传文献
	 */
	@RequestMapping("upWords")
	@ResponseBody
	public String upWords(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String userId = CookiesUtils.getUserId(request);
		CodeAndMsg codeMsg = BeanFactory.getCodeAndMsgBean();
		if(StringUtils.isBlank(userId)){
			codeMsg.setCode("404");
			codeMsg.setMsg("用户未登录！");
			return JsonUtils.parse(codeMsg);
		}
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		

		if(StringUtils.isAnyBlank(title,content)){
			logger.error("标题或者内容不能为空!");
			codeMsg.setCode("404");
			codeMsg.setMsg("标题或者内容不能为空!");
			return JsonUtils.parse(codeMsg);
		}
		
		Files checker = BeanFactory.getFilesBean();
		checker.setTitle(title);
		checker.setUserId(Long.valueOf(userId));
		try{
			checker =filesService.getWords(checker);
			if(checker !=null){
				codeMsg.setCode("500");
				codeMsg.setMsg("已存在相同标题文献！");
				return JsonUtils.parse(codeMsg);
			}
		}catch(Exception e){
			e.printStackTrace();
			codeMsg.setCode("500");
			codeMsg.setMsg("上传文献失败！");
			return JsonUtils.parse(codeMsg);
		}

		
		Files file = BeanFactory.getFilesBean();
		file.setTitle(title);
		file.setContent(content);
		file.setUserId(Long.valueOf(userId));
		file.setUploadTime(new Date());
		
		try{
			int flag = filesService.upWords(file);
			
			if(flag ==1){
				
				logger.info("用户"+userId +"上传文献 '"+title+"'完成！");
				
				codeMsg.setCode("200");
				codeMsg.setMsg("上传文献成功！");
				return JsonUtils.parse(codeMsg);
			}else{
				codeMsg.setCode("500");
				codeMsg.setMsg("上传文献失败！");
				return JsonUtils.parse(codeMsg);
			}
		}catch(Exception e){
			logger.error("用户"+file.getUserId() +"上传文献 '"+title+"'失败！");
			e.printStackTrace();
			codeMsg.setCode("500");
			codeMsg.setMsg("上传文献失败！");
			return JsonUtils.parse(codeMsg);
		}
		
		
		
	}
	
	/**
	 * 2018-03-17 16:26:26
	 * 查阅文献
	 */
	@RequestMapping("getWords")
	public ModelAndView getWords(HttpServletRequest request,HttpServletResponse response){
		
		String userId = CookiesUtils.getUserId(request);
		ModelAndView mv = new ModelAndView();
		
		if(StringUtils.isBlank(userId)){
			mv.setViewName("index");
			return mv;
		}
		
		String titile = request.getParameter("titile");
		String fileIdStr = request.getParameter("fileId");
		
		if(StringUtils.isAllBlank(titile,fileIdStr)){
			mv.setViewName("index");
			return mv;
		}
		
		Files file = BeanFactory.getFilesBean();
		file.setUserId(new Integer(userId).intValue());
		file.setTitle(titile);
		
		if(StringUtils.isNotBlank(fileIdStr)){
			file.setFileId(new Integer(fileIdStr).intValue());
		}
		
		String content;
		String title;
		
		try{
			file = filesService.getWords(file);
			content = file.getContent();
			title = file.getTitle();
		}catch(Exception e){
			logger.error("用户"+file.getUserId() +"查看文献失败！");
			e.printStackTrace();
			mv.setViewName("index");
			
			return mv;
		}
		
		if(StringUtils.isAnyBlank(content,title)){
			logger.error("用户"+file.getUserId() +"查看文献失败！");
		}
		
		logger.info("用户"+file.getUserId() +"查看文献 '" + title+"'成功！");
		
		ModelMap mm = new ModelMap();
		mm.put("title", title);
		mm.put("content", content);
		
		mv.addObject("view",mm);
		mv.setViewName("news");
		
		return mv;
	}
	
	/**
	 * 2018-03-17 16:26:26
	 * 查阅文献列表
	 */
	@RequestMapping("getWordList")
	@ResponseBody
	public String getWordList(HttpServletRequest request,HttpServletResponse response){
		
		String userId = CookiesUtils.getUserId(request);
		String admin = request.getParameter("role");
		
		CodeAndMsg codeMsg = new CodeAndMsg();
		
		if(StringUtils.isAllBlank(userId,admin)){
			codeMsg.setCode("404");
			codeMsg.setMsg("用户未登录！");
			return JsonUtils.parse(codeMsg);
		}
		
		List<Files> fileList = null;
		
		User user = new User();
		if(StringUtils.isNotBlank(userId)){
			user.setId(Long.valueOf(userId));
		}
		
		try{
			fileList = filesService.getFileList(user);
		}catch(Exception e){
			e.printStackTrace();
			codeMsg.setCode("500");
			codeMsg.setMsg("获取文献列表失败!");
			return JsonUtils.parse(codeMsg);
		}
		
		codeMsg.setCode("200");
		codeMsg.setMsg("获取文献列表成功!");
		codeMsg.setContent(JsonUtils.parse(fileList));
		return JsonUtils.parse(codeMsg);
	}
	
	/**
	 * 2018-03-17 16:16:20
	 * 上传附件
	 */
	@RequestMapping("uploadFile")
	@ResponseBody
	public String upLoadFile(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String userId = CookiesUtils.getUserId(request);
		
	     CodeAndMsg codeMsg = BeanFactory.getCodeAndMsgBean();
		
			if(StringUtils.isBlank(userId)){
				codeMsg.setCode("404");
				codeMsg.setMsg("用户未登录！");
				return JsonUtils.parse(codeMsg);
			}
		
		   //获取解析器  
		     CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		     

		     
		     //判断是否是文件  
		     if(resolver.isMultipart(request)){  
		         //进行转换  
		         MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);  
		         //获取所有文件名称  
		         Iterator<String> it = multiRequest.getFileNames();  
		         while(it.hasNext()){  
			             //根据文件名称取文件  
			             MultipartFile file = multiRequest.getFile(it.next());
			            
			             String fileName = file.getOriginalFilename();
			            
			             String rootPath = "";
			             if(fileName.endsWith(".txt") || fileName.endsWith(".doc") ||fileName.endsWith(".pdf") ||fileName.endsWith(".xls")){
			            	 rootPath = rootPath1;
			             }else if(fileName.endsWith(".png") || fileName.endsWith(".jpg")){
			            	 rootPath = rootPath2;
			             }else{
			            	 rootPath = rootPath3;
			             }

			             File path = new File(rootPath);
			             //没有则创建该路径,时间有限,不做进一步优化了
			             if(!path.exists()){
			            	 path.mkdir();
			             };
			             
			             String localPath = rootPath + fileName;  
			             //创建一个新的文件对象，创建时需要一个参数，参数是文件所需要保存的位置
			             File newFile = new File(localPath); 
			             if(newFile.exists()){
			            	 logger.error("已存在名为'"+fileName+"'的文件，不再进行存储!");
							codeMsg.setCode("500");
							codeMsg.setMsg("已存在名为"+fileName+"的文件，不再进行存储!");
			            	 continue;
			             }
			             //上传的文件写入到指定的文件中  
			             file.transferTo(newFile); 

			             try{
			            	 Document doc = new Document();
			            	 doc.setUserId(Long.valueOf(userId));
			            	 doc.setPath(localPath);
			            	 doc.setTitle(fileName);
			            	 doc.setUploadTime(new Date());
			            	 int flag = documentservice.insertDoc(doc);
			            	 
			            	 if(flag!=1){
			            		 codeMsg.setCode("500");
								codeMsg.setMsg(fileName+"保存上传记录时报错!");
			            	 }
			            	 
			             }catch(Exception e){
			            	 e.printStackTrace();
			             }
			             
			             logger.info("********"+fileName+"文件文件存储成功!");
			         }  
		         
		         	if(StringUtils.isBlank(codeMsg.getCode())){
						codeMsg.setCode("200");
						codeMsg.setMsg("上传文件成功!");	
		         	}

					return JsonUtils.parse(codeMsg);
			}
				codeMsg.setCode("500");
				codeMsg.setMsg("上传文件失败!");
				return JsonUtils.parse(codeMsg);
	}
	
	/**
	 * 2018-04-05 21:16:20
	 * 获取附件列表
	 * @throws IOException 
	 */
	@RequestMapping("getFileList")
	@ResponseBody
	public String getFileList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//文件全路径
		final List<String> paths = new ArrayList<>();
		//文件名
		final List<String> names = new ArrayList<>();

		//对三个文件夹遍历
		String[] filePaths={rootPath1,rootPath2,rootPath3}; 
		
		for(String p :filePaths){
			java.nio.file.Files.walkFileTree(Paths.get(p), new FileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(Path dir,
						BasicFileAttributes attrs) throws IOException {
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file,
						BasicFileAttributes attrs) throws IOException {
					
					paths.add(file.toString());
					names.add(file.getFileName().toString());
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc)
						throws IOException {
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc)
						throws IOException {
					return FileVisitResult.CONTINUE;
				} 
				
			});
		}
		
		Map<String,Object> collection = new HashMap<String,Object>();
		collection.put("paths", paths);
		collection.put("names", names);

		return JsonUtils.parse(collection);
	}
	
	/**
	 * 2018-04-06 01:16:20
	 * 获取附件内容
	 * @throws IOException 
	 */
	@RequestMapping("getFileContent")
	public void getFileContent(HttpServletRequest request,HttpServletResponse response){
		String path = request.getParameter("path");
		
		response.setContentType("application/msword;charset=GB2312");
		//response.setContentType("text/html;charset=UTF-8");		
		File file = new File(path);
		
		FileInputStream in = null;
		
		try { 
		in = new FileInputStream(file); 
		} catch (FileNotFoundException e) { 
		e.printStackTrace(); 
		} 
		WordExtractor extractor = null; 
		String text = ""; 
		try {
			extractor = new WordExtractor(in);
			text = extractor.getText(); 
			try {
				response.getWriter().write(text);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * 2018-05-12 13:16:20
	 * 获取文档信息
	 * @throws IOException 
	 */
	@RequestMapping("getDocTitle")
	@ResponseBody
	public String getDocTitle(HttpServletRequest request,HttpServletResponse response){
		
		String userId = CookiesUtils.getUserId(request);
		String admin = request.getParameter("role");
	    CodeAndMsg codeMsg = BeanFactory.getCodeAndMsgBean();
		
			if(StringUtils.isAllBlank(userId,admin)){
				codeMsg.setCode("404");
				codeMsg.setMsg("用户未登录！");
				return JsonUtils.parse(codeMsg);
			}
			
		String title = request.getParameter("title");
		String docId = request.getParameter("docId");
		
			if(StringUtils.isAllBlank(title,docId)){
				codeMsg.setCode("404");
				codeMsg.setMsg("请输入文档名或ID！");
				return JsonUtils.parse(codeMsg);
			}
			
			Document doc = new Document();
			
			if(StringUtils.isNotBlank(docId)){
				doc.setDocId(Long.valueOf(docId));
			}
			
			if(StringUtils.isNotBlank(userId)){
				doc.setUserId(Long.valueOf(userId));
			}
			
			doc.setTitle(title);
			
			try{
				doc = documentservice.getDoc(doc);
				if(doc!=null){
					codeMsg.setCode("200");
					codeMsg.setMsg("查询文档成功！");
					codeMsg.setContent(JsonUtils.parse(doc));
					
					logger.info("文档查询结果"+JsonUtils.parse(doc));
					
					return JsonUtils.parse(codeMsg);
				}
			}catch(Exception e){
				e.printStackTrace();
				codeMsg.setCode("500");
				codeMsg.setMsg("查询文档失败！");
				return JsonUtils.parse(codeMsg);
			}
			
			codeMsg.setCode("500");
			codeMsg.setMsg("未查到相关文档！");
			return JsonUtils.parse(codeMsg);
	}
	
	
	/**
	 * 2018-04-07 15:16:20
	 * 获取附件内容
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 */
	@RequestMapping("downloadFile")
	public void downloadFile(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		
		String filePath = request.getParameter("path");
		String name = request.getParameter("name");
		if(StringUtils.isAnyBlank(filePath,name)){
			return;
		}
	    File file = new File(filePath);

	    response.setContentType("application/octet-stream");
	    response.setContentType("application/vnd.ms-excel;charset=UTF-8");
	    response.setHeader("Content-Disposition","attachment;filename=" + new String(name.getBytes("gb2312"), "ISO8859-1"));
	    response.setContentLength((int) file.length());
	    
	    FileInputStream fis = null;
	    try {
	        fis = new FileInputStream(file);
	        byte[] buffer = new byte[512];
	        int count = 0;
	        while ((count = fis.read(buffer)) > 0) {
	        	response.getOutputStream().write(buffer, 0, count);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	try {
				response.getOutputStream().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	try {
				response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
		
	}
	
}
