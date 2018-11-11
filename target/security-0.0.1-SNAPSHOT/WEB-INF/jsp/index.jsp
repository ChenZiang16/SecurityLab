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
			$(document).ready(function(){
				$("#KinSlideshow").KinSlideshow();
				getNewsList();
			});
			
			function getNewsList(){
				
				$.ajax({  
			           type: 'GET',  
			           dataType: "json",
			           async: false, 
			           url: '${pageContext.request.contextPath }/getNewsList.html',
			           data: null,
			        
			           success: function (result) { 
			        	   
			        	   var pointNewsList = document.getElementById("pointNewsList");  
			        	   var multiNewsList = document.getElementById("multiNewsList");  
			        	   var scienceNewsList = document.getElementById("scienceNewsList");  

			        	   var res = JSON.parse(result);
			        	   if(res.code ==200){
			        		   var content = JSON.parse(res.content);
			        		   
			        		   var point = content.point;
			        		   for(var i=0;i<point.length;i++){
					        	   var newnode = document.createElement("li");
					        	   newnode.innerHTML="<span>"+point[i].publishTime+"</span><a href='./getNews.html?id="+point[i].newsId+"'>"+point[i].title+"</a>";
					        	   pointNewsList.appendChild(newnode);
				        	   }
			        		   
			        		   var multi = content.multi;
			        		   for(var i=0;i<multi.length;i++){
					        	   var newnode = document.createElement("li");
					        	   newnode.innerHTML="<span>"+multi[i].publishTime+"</span><a href='./getNews.html?id="+multi[i].newsId+"'>"+multi[i].title+"</a>";
					        	   multiNewsList.appendChild(newnode);
				        	   }
			        		   
			        		   var science = content.science;
			        		   for(var i=0;i<science.length;i++){
					        	   var newnode = document.createElement("li");
					        	   newnode.innerHTML="<span>"+science[i].publishTime+"</span><a href='./getNews.html?id="+science[i].newsId+"'>"+science[i].title+"</a>";
					        	   scienceNewsList.appendChild(newnode);
				        	   }
			        		   
			        	   }
			        	   
			        	
			            } ,error : function() {
		                    alert("异常！");
		                }
			      }); 	
				
			}
			
			function getJpg(){
				
				alert(100);
				$('#aaa').html("<img src='./getJpg.html' width='100' height='80'>");
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
				href="./literatures.html" target="_top">文献文档</a>| <a
				target="_top">科研成果</a>| <a
				target="_top">人才队伍</a>| <a
				href="./users.html" target="_top">用户中心</a>| <a
				href="./administrator.html" target="_top">管理员</a>| <a
				href="./getVideo.html" target="_top">机构展示</a>| <a
				href="./connectUs.html" target="_top">联系我们</a>|
		</div>
	</div>

	<!-- Center -->

	<div class="centerwrap">
		<div class="topnews_wrap">

			<div class="topnews">
				<div class="news_title margin10">
					焦点新闻
				</div>
				<ul class="news_list" id="pointNewsList">


				</ul>
			</div>

			<div class="topnews_img">
				<!--幻灯片新闻开始-->

				<div id="KinSlideshow" style="visibility: hidden;">
					<a target="_blank"><img src="./images/201806/a.jpg" alt="实验室精密仪器..." title="实验室精密仪器..." width="400" height="257" border="0"/></a>
					<a target="_blank"><img src="./images/201805/b.jpg" alt="研究人员工作..." title="研究人员工作..." width="400" height="257" border="0"/></a>
					<a target="_blank"><img src="./images/201804/c.jpg" alt="实验室设备调试..." title="实验室设备调试..." width="400" height="257" border="0"/></a>
					<a target="_blank"><img src="./images/201804/d.jpg" alt="实验室化学试剂..." title="实验室化学试剂..." width="400" height="257" border="0"/></a>
					<a target="_blank"><img src="./images/201804/e.jpg" alt="工作人员进行实验..." title="工作人员进行实验..." width="400" height="257" border="0"/></a>
				</div>

				<!--幻灯片新闻结束-->
			</div>
			<div class="clear"></div>


		</div>
		<div class="center1000">
			<div class="leftwrap">
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
				
<!-- 				用户信息start 
				<div class="user-wrap">
					<div class="lefttitle">
						用户信息
					</div>
					<div class="sidewrap  margin10" >
						<div class='welcome'>
							你好，<span class="username"></span>
						</div>
						<input type="button" value="退出登录" />
					</div>
				</div>
			用户信息end -->
				
				
<!-- 				<div class="lefttitle">
					<a href='./xglj/'>管理员&gt;&gt;</a>文档文献
				</div>
				<div class="sidewrap margin10"> -->
<%-- 						<form action="${pageContext.request.contextPath }/login.html"> --%>
<!-- 							查询文献：<input type="text" name="userName"></input><br> -->
<!-- 					    <input type="submit">&nbsp&nbsp</input><input type="reset"></input> -->
<!-- 					    </form> -->
<%-- 						<form action="${pageContext.request.contextPath }/login.html"> --%>
<!-- 							查询文档：<input type="password" name="password"></input><br> -->
<!-- 					    <input type="submit">&nbsp&nbsp</input><input type="reset"></input> -->
<!-- 						</form> -->
<!-- 				</div> -->
			</div>
			<div class="cenwrap">
				<div class="news_title margin10" >
					综合新闻
				</div>
				<ul class="news_list margin20" id = "multiNewsList">


				</ul>
				<div class="news_title margin10">
					科研动态
				</div>
				<ul class="news_list"  id = "scienceNewsList">


				</ul>
				<br><br><br>
			</div>

			<div class="rightwrap">



				<div class="lefttitle">
					相关链接
				</div>

				<div class="sidewrap margin10">
					<ul class="sidelist">

						<li><a href="http://www.cnki.net/">中国知网</a></li>

						<li><a href="http://www.sciencenet.cn">科学网</a></li>

						<li><a href="https://www.nstl.gov.cn/">国家科技图书文献中心</a></li>

						<li><a href="http://projects.cnki.net/">科研项目数据库</a></li>

						<li><a href="http://www.nlc.gov.cn/">中国国家图书馆</a></li>

					</ul>
				</div>
			</div>

			<div class="clear"></div>

		</div>
	</div>


	<!-- Bottom -->
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