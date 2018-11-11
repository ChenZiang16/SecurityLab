	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	
	<head>
	
	<title>信息安全实验室</title>
	<link type="text/css" href="./css/second.css" rel="stylesheet" />
	<link type="text/css" href="./css/style.css" rel="stylesheet" />
	<script type="text/javascript" src="./js/jquery-1.42.min.js"></script>
	<script type="text/javascript" src="./js/jquery.KinSlideshow-1.1.js"></script>
	
	<script type="text/javascript">
	
			$(document).ready(function(){
				getMsgList();
			});
			
			function getMsgList(){
				
				$.ajax({  
			           type: 'GET',  
			           dataType: "json",
			           async: false, 
			           url: '${pageContext.request.contextPath }/getMsgList.html',
			           data: null,
			        
			           success: function (result) { 
			        	   var res = JSON.parse(result);
			        	   if(res.code==200){
			        		  
			        		   var messages = JSON.parse(res.content);
			        		   var msgList =document.getElementById("msgList");
			        		   for(var i =0;i<messages.length;i++){
			        			  
			        			   var msgNode = document.createElement("li");
			        			   msgNode.innerHTML= "<p style= 'width:740px;height:48px;'> <a style ='float:left;background-color:99FF66;'>"+messages[i].content +"</a><a style ='float:right;'>—— "+ messages[i].name+"</a></p><br><br>";
			        			   msgList.appendChild(msgNode);
			        		   }
			        		   
			        		  
			        	   }
			            } ,error : function() {
		                 alert("异常！");
		             }
			      }); 
				
			}

			function saveMessage(){

					$.ajax({  
				            
				           type: 'POST',  
				           dataType: "json",
				           async: false, 
				           url: '${pageContext.request.contextPath}/saveMessage.html' ,
				           data: $('#form1').serialize(),
				        
				           success: function (result) { 
				        	   
				        	   var dataObj = JSON.parse(result);
				        	   
				        	   alert(dataObj.msg);
				        	   
				        	   if(dataObj.code==200){
				        		   window.location = '${pageContext.request.contextPath }/message.html';
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
		       	  <div class="lefttitle">文献文档检索</div>
		          <table width="100%" border="0" cellspacing="1" cellpadding="1" class="outline_leftmenu margin10">
		          	  
		          	  <tr class="seled">
		                <td>留言展示</td>
		              </tr>
		               <tr>
		                <td>用户留言</td>
		              </tr>

		            </table>
		      </div>
		     
		   <div>
		      <center class="outer-wrap">
		      
		       <div class="inner-block"><br>
					<br><ul  id="msgList"> </ul>
				</div>
				
				<div class="inner-block hide"><br>
				     <form method="post" id = "form1" style="height: 300px">
					    <div class='message_left'>姓名：</div>
						<input type="text" class='input_message' name="name"/>
									
						<div class='message_left' style="float:left;">性别：</div>
						<div class="message_right radio_message"> <span class="span" > <input type='radio'  value='0' name="gender"/> 男</span> <span class="span"> <input type='radio'  value='1' name="gender"/> 女</span></div>			
			
						<div class='message_left'>联系邮箱：</div>
						<input type="text" class='input_message' name="email"/>
									
						<div class='message_left'>留言内容：</div>
						<textarea  class='area_message' name ="content"></textarea>
									
						<div class='sub_message' id='sub_message' onclick="saveMessage()">提交</div>
					</form>
		        </div>
		      
      	 		<div class="outline_r">
				  	<div class="article_title" id = "title"></div>
		    	  	<div class="article_con" id="zoom"><style type="text/css">.TRS_Editor P{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor DIV{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor TD{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor TH{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor SPAN{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor FONT{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor UL{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor LI{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}.TRS_Editor A{margin-top:5px;margin-bottom:5px;line-height:1.5;font-family:宋体;font-size:12pt;}</style><div class=TRS_Editor><div>
			    </div>
				
			
			</center> 
					
		      </div>
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
	<script>
	$('.outline_leftmenu').find('tr').on('click',function(){
		var _index=$(this).index()

		$('.outline_leftmenu tr').removeClass('seled')
		$(this).addClass('seled')
		$('.outer-wrap').find('.inner-block').hide()
		$('.outer-wrap').find('.inner-block').eq(_index).show()
		
	})
	</script>
	</html>