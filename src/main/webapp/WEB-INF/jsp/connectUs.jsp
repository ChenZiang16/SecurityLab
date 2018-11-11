	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	
	
	<title>信息安全实验室</title>
	<link type="text/css" href="./css/style.css" rel="stylesheet" />
	<script type="text/javascript" src="./js/jquery-1.42.min.js"></script>
	<script type="text/javascript" src="./js/jquery.KinSlideshow-1.1.js"></script>

	<script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=2hrNbb1Q7T6Z847qj4WfmGBR5kuMU7lo">

</script>
	
	<script type="text/javascript">
				$(document).ready(function(){
					$("#KinSlideshow").KinSlideshow();
				});
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
		  
		  
		 	   
		   	<div class="article_title">联系我们</div>
		    <table width="100%" border="0" cellspacing="1" cellpadding="1" class="article_ex">
		        <tr>
		          <td align="center" width="34%">发布时间：2018-05-17</td>
		        </tr>
		      </table>
		     
			

		
		<div id=mapcontainer></div> 
		<div class="article_con" id="zoom"><div class=TRS_Editor><p align="justify">联系电话:0371-6820520</p>
			<p align="justify">传真地址:0371-6820520</p>
				<p align="justify">地址:郑州市高新区科学大道166号</p>
				
				<script type="text/javascript"> 
					var map = new BMap.Map("mapcontainer");
					// 创建地图实例  
					var point = new BMap.Point(113.516021,34.816449);
					// 创建点坐标  
					map.centerAndZoom(point, 15);
					// 初始化地图，设置中心点坐标和地图级别  
				</script>  
			
			
			</div>
		</div>
			 
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