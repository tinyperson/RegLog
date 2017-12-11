<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录页面</title>
<script src="js/md5.min.js"></script>
<script type="text/javascript">
	function changeImage() {
		document.getElementById("image").src = "${pageContext.request.contextPath}/checkimage?"
				+ new Date().getTime()
	}
	function validateForm() {
		//用户名不能为空
		var username = document.getElementById("username").value
		if (username == "") {
			alert("用户名不能为空...")
			return false;
		}
		//密码不能为空
		var password = document.getElementById("password").value
		if (password == "") {
			alert("密码不能为空...")
			return false;
		}
		
		//加密
		var passHash = md5(document.getElementById("password").value)
		document.getElementById("password").value = passHash
	}
	function init(){
	//使用js函数实现url解码
	var vl = decodeURI("${cookie.username.value}")
	document.getElementById("username").value=vl
	}
</script>
</head>
<body onload="init()">
	<h3>某网站登录页面</h3>
	<font color="red"> ${message}</font>
	<form action="${pageContext.request.contextPath }/login" method="post"
		onsubmit="return validateForm();">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="username" id="username"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" value="${cookie.password.value}" id="password">
				</td>
			</tr>
			<tr>
				<td>验证码：</td>
				<td><input type="text" name="checkcode" id="checkcode">
					<img src="${pageContext.request.contextPath}/checkimage"
					style="cursor:pointer" onclick="changeImage();" id="image"></td>
			</tr>
			<tr>
				<td colspan="2">记住用户名和密码<input type="checkbox" name="remember"
					value="on"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="登录"></td>
			</tr>
		</table>
	</form>
</body>
</html>