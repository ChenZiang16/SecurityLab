	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	
	
	<title>信息安全实验室</title>
	<link type="text/css" href="./css/style.css" rel="stylesheet" />
	<script type="text/javascript" src="./js/jquery-1.42.min.js"></script>
	<script type="text/javascript" src="./js/amq_jquery_adapter.js"></script>
	<script type="text/javascript" src="./js/amq.js"></script>
		
	<script type="text/javascript">
	

	$(document).ready(function(){ 
		 
		myDestination = "topic://chat";
		
		amq = org.activemq.Amq;
		  amq.init({ 
		    uri: 'amq', 
		    logging: true,
		    timeout: 20,
		    clientId:(new Date()).getTime().toString()
		  });
		  
		  var userId = getUserId('userId');
		 
		  var myHandler ={
				    rcvMessage: function(message){
				    	
						var messageList = document.getElementById("messageList"); 
						
						var newnode = document.createElement("li");

						//newnode.innerHTML="<a>"+ message.wholeText +"</a>";
						 newnode.innerHTML="<p> <a style ='float:left;background-color:99FF66;'>"+message.wholeText +"</a></p><br><br>";
						 
						 messageList.appendChild(newnode);
				    }
				  };
		  
		  amq.addListener("TEST",myDestination,myHandler.rcvMessage, {selector:"identifier='TEST'"});
		 
		  
	}); 
	
	  function go(){
		  amq.sendMessage(myDestination,$('#topicContent').val(),"amq-msg-type=>'text'");
		  alert("发布消息成功！");
		  $('#form1').get(0).reset();
	  }
	
	//获取userId
	function getUserId(name){
		
			var strcookie = document.cookie;//获取cookie字符串
			var arrcookie = strcookie.split("; ");//分割
			//遍历匹配
			for ( var i = 0; i < arrcookie.length; i++) {
				
			var arr = arrcookie[i].split("=");
				if (arr[0] == name){
				 return arr[1];
				}
				
			}
			return "";
		}
	
	
	function sendMessage(){
		$.ajax({  
	           type: 'POST',  
	           dataType: "json",
	           async: false, 
	           url: '${pageContext.request.contextPath }/sendMessage.html',
	           data: $('#form1').serialize(),
	        
	           success: function (result) { 
	        	   var res = JSON.parse(result);
	        	   alert(res.msg);
	        	   if(res.code ==200){
	        		   document.getElementById("form1").reset()
	        	   }
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
		          	  
		              <tr class="seled">
		                <td>发布消息</td>
		              </tr>
		              
		              <tr>
		                <td>发送消息</td>
		              </tr>
		              
		              <tr>
		                <td>历史消息</td>
		              </tr>
		              
		            </table>
		        </div>
		      
		      <div>
		        <center class='login-regieter'>
				<div class='login-wrap register-login-block'>
					
					<h3>发布消息</h3><br>
						<p >发送消息给所有用户</p><br>
						<form id = "form1" >
								发布内容：<br><textarea rows="20" cols="80" id = "topicContent"></textarea><br><br>
										<input type="reset"></input>&nbsp&nbsp&nbsp
										<input type="button" value = "发布" onclick="go()"></input>
						</form>
						
						<div style="height: 200px;width: 200px"></div>
						
				</div>
				
				<div class='register-wrap register-login-block'>
					<h3>发送消息</h3>
					<p>发送消息给用户</p>
						<form id = "form2" >
							       接收用户<input type="text" name="message"></input><br>
							       发送内容：<input type="text" name="message"></input><br>
									<input type="button" value = "发布" onclick="go()"></input>
						</form>
				</div>
				
				<div class='register-wrap register-login-block'>
					<h3>历史消息</h3>
						<ul id = "messageList"></ul>
				</div>
				
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
	
	
</script>
	</html>