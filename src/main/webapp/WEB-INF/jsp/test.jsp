<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link type="text/css" href="./css/second.css" rel="stylesheet" />
	<script src="./js/jquery-1.42.min.js" type="text/javascript"></script>
	
	<script>
	
		$(document).ready(function(){
			getGender();
		});
	
		function getGender(){
			var gender = ${user.gender};
			if(gender==0){
				$("#gender").html("男");
			}else{
				$("#gender").html("女");
			}
			
		}
	
	
	
	</script>
	 
	 
</head>
<body>
						
	<div>性别：<a id = "gender"></a></div>
	
</body>
</html>