	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	
	
	<title>信息安全实验室</title>
	<link type="text/css" href="./css/style.css" rel="stylesheet" />
	<script type="text/javascript" src="./js/jquery-1.42.min.js"></script>
	<script type="text/javascript" src="./js/jquery.KinSlideshow-1.1.js"></script>
	
	</head>
	
	<body>
		<!-- Top -->
		<div class="topwrap">
			<div class="banner"></div>
			<div class="menuwrap">
				<a href="./index.html" target="_top">首页</a>| <a
					href="./labAbstra.html" target="_top">实验室概况</a>| <a
					target="_top">科研成果</a>|<a
					href="./getVideo.html" target="_top">机构展示</a>| <a
					href="./literatures.html" target="_top">文献文档</a>| <a
					href="./users.html" target="_top">用户中心</a>| <a
					href="./message.html">用户留言</a>| <a
					href="./administrator.html" target="_top">管理员</a>| <a
					href="./connectUs.html" target="_top">联系我们</a>|
			</div>
		</div>
		
		
		<div class="topwrap">
		
		<div class="centerwrap">
		  <div class="center1000">
		      <div class="leftwrap"><br>
		       	  <div class="lefttitle">实验室模块</div>
				<table width="100%" border="0" cellspacing="1" cellpadding="1"
					class="jg_list margin10">
					<tr>
						<td><a href='./labAbstra.html'>实验室简介</a></td>
						<td><a href='./literatures.html'>文档文献</a></td>
					</tr>
					<tr>
						<td><a href='./users.html'>用户中心</a></td>
						<td><a href='./administrator.html'>管理员</a></td>
					</tr>
					<tr>
						<td><a href='./getVideo.html'>机构展示</a></td>
						<td><a href='./connectUs.html'>联系我们</a></td>
					</tr>
				</table>
				
			</div>
		      
		      <div class="outline_r">
				  
				  <div class="article_title">${view.title}</div>
		    <table width="100%" border="0" cellspacing="1" cellpadding="1" class="article_ex">
		        <tr>
		          <td align="center" width="33%" id="source">文章来源：</td>
				  <script type="text/javascript">
						var source = '';
						if (source == ''){
							$("#source").html("");
						}
					</script>
		        </tr>
		      </table>
			  
		     <div class="article_con" id="zoom"><style type="text/css">.TRS_Editor P{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor DIV{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor TD{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor TH{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor SPAN{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor FONT{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor UL{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor LI{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor A{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}</style><div class=TRS_Editor><div>
				<textarea rows="30" cols="100">${view.content}</textarea>
			</div>
		
		</div></div>
			  <table width="100%" border="0" cellspacing="1" cellpadding="1" style="margin:10px auto 20px auto" id="appendix">
		        <tr>
		          <td align="left" style="padding-left:20px; font-weight:bold">附件：</td>
		        </tr>
				<tr>
					<td style="padding-left:30px;line-height:22px;height:22px"></td>
				</tr>
		      </table>
			  
			  <script type="text/javascript">
			  	var appendix = '';
				if(appendix == ''){
					$("#appendix").hide();
				}
			  </script>       
		      </div>
		       
		        <div class="clear"></div>
		    </div>
		</div>
				
		
		
		
	<div class="bottomwrap">
		<table width="1000" border="0" cellspacing="1" cellpadding="1"
			style="margin: 0 auto;">
			<tr>
				<td width="300" align="center"><a href="http://www.zzuli.edu.cn"
					target="_blank"><img
						src="./images/sign.jpg" width="243"
						height="74" /></a></td>
				<td width="30" align="center"><img
					src="http://www.sklois.cn/images/bottomline.gif" /></td>
				<td class="copyright"><span style="font-family: Arial">&copy;</span>郑州轻工业学院版权所有
					备案序号：豫ICP备05002456号<br /> 电话：0371-63925110 传真：0371-63556320<br />
					地址：郑州市高新区科学大道166号</td>
			</tr>
		</table>
	</div>
	
	</body>
	
	</html>