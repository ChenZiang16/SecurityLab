	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	
	<title>信息安全实验室</title>
	<link type="text/css" href="./css/style.css" rel="stylesheet" />
	<script type="text/javascript" src="./js/jquery-1.42.min.js"></script>
	<script type="text/javascript" src="./js/jquery.KinSlideshow-1.1.js"></script>
	
	<script type="text/javascript">
		function getApparatus(){

			$.ajax({  
		           type: 'POST',  
		           dataType: "json",
		           async: false, 
		           url: '${pageContext.request.contextPath}/getApparatus.html' ,
		           data: $('#form1').serialize(),
		        
		           success: function (result) { 
		        	   var apparatus = JSON.parse(result);
		        	   var length = apparatus.length;
		        	   var appaInfo = "";
		        	   for(var i=0;i<length;i++){
		        		   var path = apparatus[i].path;
		                   path = path.replace("\\","-");
		                   path = path.replace("\\","-");
		                   path = path.replace("\\","-");
		                   path = path.replace("\\","-");
		        		   appaInfo += ("<img src='./getJpg.html?filePath="+path+"' width='100' height='80'><br/>");
		        	   }
		        	   alert(appaInfo);
						$("#appaInfo").html(appaInfo); 

		            } ,error : function() {
	                    alert("异常！");
	                }
		      }); 
			
		}
		
	
	
	</script>
	
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
		
		<div class="centerwrap">
		  <div class="center1000">
		      <div class="leftwrap">
		       	 <div class="lefttitle">用户中心</div>
		          <table width="100%" border="0" cellspacing="1" cellpadding="1" class="outline_leftmenu margin10">
		          	  
		              <tr>
		                <td><a href="./">用户权限</a></td>
		              </tr>
		              
		              <tr>
		                <td><a href="../zzjg/">普通用户</a></td>
		              </tr>
		              
		              <tr>
		                <td><a href="../xrld/">管理员</a></td>
		              </tr>
		              
		            </table>
		        </div>
		      
		      <div>
		        <center>
		      		<form method="post" id="form1">
						管理员：<input type="text" name="userName"></input><br>
						密码：<input type="password" name="password"></input><br>
					    <input type="submit" onclick="getApparatus()">&nbsp&nbsp</input><input type="reset"></input>
					</form>
					
		        </center>
		       </div>
		      
		    <div class="outline_r">
		    	 <div class="article_con" id="appaInfo"><style type="text/css">.TRS_Editor P{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor DIV{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor TD{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor TH{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor SPAN{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor FONT{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor UL{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor LI{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor A{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}</style><div class=TRS_Editor><div>
			  </div>
		</div>
			<center><iframe width="100%" scrolling="auto" frameborder="0"></iframe></center>
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