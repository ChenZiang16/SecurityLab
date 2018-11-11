<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>


	<script src="./js/jquery-3.3.1.min.js" type="text/javascript"></script>
	 
	<style>
        .error {
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 4px;
            color: #a94442;
            background-color: #f2dede;
            border: 1px solid #ebccd1;
        }
        .msg {
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 4px;
            color: #31708f;
            background-color: #d9edf7;
            border: 1px solid #bce8f1;
        }
        #login-box {
            width: 300px;
            padding: 20px;
            margin: 100px auto;
            background: #fff;
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border: 1px solid #000;
        }
        .hide {
            display: none;
        }
        .show {
            display: block;
        }
    </style>
</head>
<body>

	<div id="login-box">
        <h1>请登录：</h1>
        <div class="error" th:class="${error}? 'show error' : 'hide'" th:text="${error}"></div>
        <div class="msg" th:class="${msg}? 'show msg' : 'hide'" th:text="${msg}"></div>
        <form name='loginForm' action="${pageContext.request.contextPath }/verify.html" method='GET'>
            <table>
                <tr>
                    <td>用户名:</td>
                    <td><input type='text' name='userName' /></td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td><input type='password' name='password' /></td>
                </tr>
                <tr>
                    <td colspan='2'>
                        <input type="submit" value="提交" />
                    </td>
                </tr>
            </table>
            <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
        </form>
    </div>
	
</body>
</html>