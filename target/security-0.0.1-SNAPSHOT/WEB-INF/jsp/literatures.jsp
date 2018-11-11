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
					getWordList();
					getDocList();
				});
				
				function upWords(){

					$.ajax({  
				            
				           type: 'POST',  
				           dataType: "json",
				           async: false, 
				           url: '${pageContext.request.contextPath }/upWords.html' ,
				           data: $('#form3').serialize(),
				        
				           success: function (result) { 
				        	   var dataObj = JSON.parse(result);
				        	   if(dataObj.code==200){
				        		   alert(dataObj.msg);
				        		   window.location = '${pageContext.request.contextPath }/literatures.html';
				        	   }else{
				        		   alert(dataObj.msg);
				        	   }
				        	   
				           } ,error : function() {
			                    alert("异常！");
			                }
				      }); 
					
				}
				
				function getWordList(){
					$.ajax({  
			            
				           type: 'POST',  
				           dataType: "json",
				           async: false, 
				           url: '${pageContext.request.contextPath }/getWordList.html' ,
				           data: null,
				        
				           success: function (result) {
				        	   
				        	   var wordList =document.getElementById("wordList");

				        	   var res = JSON.parse(result);
				        	  
				        	   if(res.code ==200){
				        		   var content = JSON.parse(res.content);
				        		 
					        	   for(var i=0;i<content.length;i++){
						        	   var wordNode = document.createElement("li");
						        	   wordNode.innerHTML= "<a href='./getWords.html?fileId="+content[i].fileId+"'>"+content[i].title+"</a>";
						        	   wordList.appendChild(wordNode);
					        	   }  
				        		   
				        	   }
				        	   
				           } ,error : function() {
			                    alert("异常！");
			                }
				      });
				}
				
				function getDocList(){
					$.ajax({  
			            
				           type: 'POST',  
				           dataType: "json",
				           async: false, 
				           url: '${pageContext.request.contextPath }/getDocList.html' ,
				           data: null,
				        
				           success: function (result) {
				        	   
				        	   var docList =document.getElementById("docList");

				        	   var res = JSON.parse(result);
				        	  
				        	   if(res.code ==200){
				        		   var content = JSON.parse(res.content);
				        		 
					        	   for(var i=0;i<content.length;i++){
						        	   var wordNode = document.createElement("li");
						        	   var path = content[i].path;
						        	   var path = path.replace("\\","\/");
					                   var path = path.replace("\\","\/");
					                   var path = path.replace("\\","\/");
					                   docList.innerHTML= "<a href='./downloadFile.html?path="+path+"&name="+content[i].title+"'>"+content[i].title+"   下载</a>";
						        	   docList.appendChild(wordNode);
					        	   }  
				        		   
				        	   }
				        	   
				           } ,error : function() {
			                    alert("异常！");
			                }
				      });
				}
				
				function getDoc(){
					
					$.ajax({  
			            
				           type: 'POST',  
				           dataType: "json",
				           async: false, 
				           url: '${pageContext.request.contextPath }/getDocTitle.html' ,
				           data:  $('#form1').serialize(),
				        
				           success: function (result) {
				        	   
				        	   var docList =document.getElementById("docList2");
				        	  
				        	   var res = JSON.parse(result);
				        	   
				        	   if(res.code ==200){ 
				        		   $("#docList2").html("");
				        		   var content = JSON.parse(res.content);
					        	   var wordNode = document.createElement("li");
					        	   var path = content.path;
					        	   var path = path.replace("\\","\/");
				                   var path = path.replace("\\","\/");
				                   var path = path.replace("\\","\/");
					        	   wordNode.innerHTML= "<a href='./downloadFile.html?path="+path+"&name="+content.title+"'>"+content.title+"   下载</a>   <input type = 'button' value = '重新查询' onclick = 'queryDocAgin()'></input>";
					        	   docList.appendChild(wordNode);
					        	   $("#formDoc").hide();
					        	   $('#docList2').show();

				        	   }else{
				        		   alert(res.msg);
				        	   }
				        	   
				           } ,error : function() {
			                    alert("异常！");
			                }
				      });
					
				}
				
				function queryDocAgin(){
					$('#docList2').hide();
					 $('#formDoc').show();
					 document.getElementById("form1").reset();
				}
				
				function doUpload() { 
					
				      var formData = new FormData($("#upFile")[0]); 
						
				      $.ajax({  
				           url: '${pageContext.request.contextPath}/uploadFile.html' ,  
				           type: 'POST',  
				           data: formData,  
				           async: true,  
				           cache: false,  
				           contentType: false,  
				           processData: false,  
				           success: function (result) { 
				        	   var res = JSON.parse(result);
				        	   var re = JSON.parse(res);
				        	   alert(re.msg); 
				        	   
				        	   if(re.code == 200){
				        		  window.location = '${pageContext.request.contextPath}/index.html';
				        	   }
				              
				           },  
				           error: function (returndata) {  
				        	   alert("上传文件异常！"); 
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
					href="./literatures.html" target="_top">文献文档</a>| <a
					target="_top">科研成果</a>| <a
					target="_top">人才队伍</a>| <a
					href="./users.html" target="_top">用户中心</a>| <a
					href="./administrator.html" target="_top">管理员</a>| <a
					href="./getVideo.html" target="_top">机构展示</a>| <a
					href="./connectUs.html" target="_top">联系我们</a>|
			</div>
		</div>
		
		<div class="centerwrap">
		  <div class="center1000">
		      <div class="leftwrap">
		       	  <div class="lefttitle">文献文档检索</div>
		          <table width="100%" border="0" cellspacing="1" cellpadding="1" class="outline_leftmenu margin10">
		          	  
		          	  <tr class="seled">
		                <td>文献检索</td>
		              </tr>
		               <tr>
		                <td>文档检索</td>
		              </tr>
		          	  
		              <tr>
		                <td>文献列表</td>
		              </tr>
		              
		              <tr>
		                <td>文档列表</td>
		              </tr>
		              
		              <tr>
		                <td>文献上传</td>
		              </tr>
		              
		              <tr>
		                <td>文档上传</td>
		              </tr>
		              

		            </table>
		      </div>
		     
		      <div>
		      <center class="outer-wrap">
		      
		       <div class="inner-block"><br>
		       <form method="post" id = "form0" style="height: 300px" action="${pageContext.request.contextPath }/getWords.html">
					文献名：<input type="text" name="titile"></input><br>
					FileId：<input type="text" name="fileId"></input><br>
						    <input type="submit">&nbsp&nbsp</input><input type="reset"></input>
				</form>
				</div>
				
				<div class="inner-block hide"><br>
				     <form method="post" id = "form1" style="height: 300px">
				     <div id = "formDoc">
						文档名：<input type="text" name="title"></input><br>
						DocId：<input type="text" name="docId"></input><br>
					    <input type="button" value="提交" onclick="getDoc()">&nbsp&nbsp</input><input type="reset"></input>
					</div>
						<div class="document-list"><br>
				      		<ul id = "docList2"></ul>
		      	 		</div>
					</form>
		        </div>
		      
		       <div class="inner-block hide  document-list"><br>
			      	<ul id="wordList">

			      	 </ul>
		      	 </div>
		      	 <div class="inner-block hide document-list"><br>
			      	<ul id = "docList">
			      	 </ul>
		      	 </div>
		      	 
		      	<div class="inner-block hide"><br>
		      	  <form method="post" id = "form3" style="height: 300px">
						文献标题：<input type="text" name="title"></input><br><br>
						文献正文：<textarea name ="content" cols="100" rows="20"></textarea><br>
						
					    <input type="button" onclick="upWords()" value="提交">&nbsp&nbsp</input><input type="reset"></input>
				</form>
		        </div>
		        
		        <div class="inner-block hide"><br><br><br>
				     <form method="post" id = "upFile" style="height: 300px" enctype="multipart/form-data">
							<input type="file" name = "file" id = "files"> </input>
							<input type = "button" onclick = "doUpload()" value="提交"></input>
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