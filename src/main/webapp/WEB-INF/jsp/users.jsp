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
		getUserInfo();
	});
	
	function getUserInfo(){
		$.ajax({  
	           type: 'POST',  
	           dataType: "json",
	           async: false, 
	           url: '${pageContext.request.contextPath }/getUserInfo.html',
	           data: null,
	        
	           success: function (result) { 
	        	   var res = JSON.parse(result);
	        	   if(res.code==200){
	        		   var content = JSON.parse(res.content);
	        		   var txt = "姓名："+content.name+"<br>性别："+content.gender+"&nbsp国籍："+content.nationality+"<br>电话:"+content.telephone+"<br>地址:"+content.address;
	        		  $('#userInformation').html(txt); 
	        	   }
	            } ,error : function() {
                 alert("异常！");
             }
	      }); 
	}
	
	
	function register(){

		$.ajax({  
	           type: 'POST',  
	           dataType: "json",
	           async: false, 
	           url: '${pageContext.request.contextPath }/register.html',
	           data: $('#registerForm').serialize(),
	        
	           success: function (result) { 
	        	   var res = JSON.parse(result);
	        	  alert(res.msg);
	        	  if(res.code==200){
	        		  $('#registerForm').get(0).reset(); 
	        	  }
	            } ,error : function() {
                    alert("异常！");
                }
	      }); 
	}
	
	
	function logOut(){

		$.ajax({  
	           type: 'POST',  
	           dataType: "json",
	           async: false, 
	           url: '${pageContext.request.contextPath }/logOut.html',
	           data: $('#logOut').serialize(),
	        
	           success: function (result) { 
	        	   var res = JSON.parse(result);
	        	   alert(res.msg);
	        	   if(res.code==200){
	        		   window.location = './index.html'; 
	        	   }
	        	   
	            } ,error : function() {
                    alert("异常！");
                }
	      }); 
	}
	
	function cancelLogOut(){
		window.location = './users.html'; 
	}
	
/* 		function getUserInfo(){
			$.ajax({  
		           type: 'POST',  
		           dataType: "json",
		           async: false, 
		           url: '${pageContext.request.contextPath }/getUserInfo.html' ,
		           data: $('#form1').serialize(),
		        
		           success: function (result) { 
		        	   
		        	   var user = JSON.parse(result);
		        	   var userInfo = "<table><tr><td><a>id</a></td><td><a>name</a></td><td><a>gender</a></td><td><a>telephone</a></td><td><a>address</a></td><td><a>nationality</a></td><td><a>literatureNum</a></td></tr>";
		        	   userInfo += "<tr><td><a>"+user.id+"</a></td><td><a>"+user.name+"</a></td><td><a>"+user.gender+"</a></td><td><a>"+user.telephone+"</a></td><td><a>"+user.address+"</a></td><td><a>"+user.nationality+"</a></td><td><a>"+user.literatureNum+"</a></td></tr></table>"

		        	   $('#userInfo').html("userInfo"); 

		            } ,error : function() {
	                    alert("异常！");
	                }
		      }); 
			
			
		}
		 */
	
	
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
		          	  
		              <tr class="seled">
		                <td>用户登录</td>
		              </tr>
		              
		              <tr>
		                <td>用户注册</td>
		              </tr>
		              
		              <tr>
		                <td>用户信息</td>
		              </tr>
		              
		              <tr>
		                <td>退出登录</td>
		              </tr>
		              
		            </table>
		        </div>
		      
		      <div>
		        <center class='login-regieter'>
		        <!-- 				登录start -->
				<div class='login-wrap register-login-block'>
					
					<div class="sidewrap  margin10" >
					<h3>用户登录</h3>
						<form id = "loginForm" >
							用户名：<input type="text" name="userName"></input><br>
							密码：<input type="password" name="password"></input><br>
						    <input type="button" value="提交" onclick="login()">&nbsp&nbsp</input><input type="reset"></input>
						</form>
					</div>
				</div>
				<!-- 				登录end -->
				<!-- 				注册start -->
				
				<div class='register-wrap register-login-block'>
					
					<div class="sidewrap  margin10" style="text-align: left;">
						<h3>用户注册</h3>
						<form id="registerForm" action="${pageContext.request.contextPath }/login.html">
							用户名：<input type="text" name="userName"></input><br>
							性别：<input type="radio" name = "gender" value="0">男</input>
							<input type="radio" name = "gender" value="1">女</input>&nbsp
							国籍：<select name="nationality">
								<option>中国</option>
								<option>美国</option>
								<option>英国</option>
								<option>法国</option>
								<option>新加坡</option>
								<option>其他</option>
							</select><br>
							密码：<input type="password" name="password"></input>
						   	确认密码：<input type="password" name="repassword"></input><br>
						   	Tel：<input type="text" name="telephone"></input>
						   	地址：<input type="text" name="address"></input><br>
						   	部门：<input type="text" name="dept"></input><br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						   	<input type="button" value="提交" onclick="register()"/>&nbsp&nbsp</input><input type="reset"></input>
						</form>
					</div>
				</div>
				
				<div class='register-wrap register-login-block'>
					
					<p id = "userInformation"></p>
					
				</div>
				
				<div class='register-wrap register-login-block'>
					
					<div class="sidewrap  margin10" >
					<h3>退出登录</h3>
					<br/>
						<form id = "logOut" >
						    <input type="button" value="退出" onclick="logOut()">&nbsp&nbsp</input>
						    <input type="button" value="取消" onclick="cancelLogOut()">&nbsp&nbsp</input>
						</form>
					</div>
				</div>
				
				<!-- 				注册end -->
		      		<!-- <form method="post" id="form1">
						用户名：<input type="text" name="userName"></input><br>
						密码：<input type="password" name="password"></input><br>
					    <input type="submit" onclick="getUserInfo()">&nbsp&nbsp</input><input type="reset"></input>
					</form> -->
		        </center>
		       </div>
		      
		    <div class="outline_r">
		    	 <div class="article_con" id="userInfo"><style type="text/css">.TRS_Editor P{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor DIV{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor TD{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor TH{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor SPAN{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor FONT{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor UL{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor LI{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor A{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}</style><div class=TRS_Editor><div>
			  </div>
		</div>
		
		<div>
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
	<script>
	var loginWrap=$('.login-wrap')
	var registerWrap=$('.register-wrap')
	
	$('.outline_leftmenu').find('tr').on('click',function(){
		var _index=$(this).index()

		$('.outline_leftmenu tr').removeClass('seled')
		$(this).addClass('seled')
		$('.login-regieter').find('.register-login-block').hide()
		$('.login-regieter').find('.register-login-block').eq(_index).show()
		
	})
	
				function login(){
				var loginWrap=$('.login-wrap')
				var userWrap=$('.user-wrap')
				$.ajax({  
			           type: 'POST',  
			           dataType: "json",
			           async: false, 
			           url: '${pageContext.request.contextPath }/login.html',
			           data: $('#loginForm').serialize(),
			        
			           success: function (result) { 
			        	   var res = JSON.parse(result);
			        	  var code = res.code;
							if(code==200){
							alert('登录成功')
							window.location='./index.html';
							}else{
								alert(res.msg);
							}
			            } ,error : function() {
		                    alert("异常！");
		                }
			      }); 
			}
	
</script>
	</html>