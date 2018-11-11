package com.security.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class NoticeUtils {
	
	public static void alertAndBack(String content,String url,HttpServletResponse response){
		try {
			PrintWriter pw = response.getWriter();
		   	 response.setContentType("text/html;charset=utf-8");
		   	 //弹窗提示并返回原页面
		   	 pw.write("<script>alert('"+content+"');window.location.href = '"+url+"';</script>");
		   	 pw.flush();
		   	 pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void notice(String content,HttpServletResponse response){
		try {
			PrintWriter pw = response.getWriter();
			response.setContentType("text/html;charset=utf-8");
			//弹窗提示并返回原页面
			pw.write(content);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
