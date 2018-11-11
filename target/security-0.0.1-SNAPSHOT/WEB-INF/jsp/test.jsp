<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<script src="./js/jquery-1.42.min.js" type="text/javascript"></script>
	 
	 
	<script type="text/javascript">
	
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
	           success: function (returndata) {  
	               alert("上传文件成功！");
	               window.location = '${pageContext.request.contextPath}/index.html';
	           },  
	           error: function (returndata) {  
	        	   alert("上传文件异常！"); 
	           }  
	      });  
	 }
	

	
		
	</script>
</head>
<body>

	<form id= "upFile" enctype="multipart/form-data">
	       登录名：<input type="text" name="loginName" id ="tt"/>
       <br>
		<input type="file" name = "file" id = "files"> </input>
		<input type = "button" onclick = "doUpload()" value="提交"></input>
	</form>
	
	<form method="post" action = "${pageContext.request.contextPath }/uploadWords.html">
	      标题：<input type="text" name="title" id ="wordTitle"/>
       <br>
		<input type="textarea" name = "content" id = "words"> </input>
		<input type = "submit" onclick = "upWords()"></input>
	</form>
	
</body>
</html>