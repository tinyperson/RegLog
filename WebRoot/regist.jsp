<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册页面</title>
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
			return false
		}
		//密码不能为空
		var password = document.getElementById("password").value
		if (password == "") {
			alert("密码不能为空...")
			return false;
		}
		//确认密码要和密码一致
		var repassword = document.getElementById("repassword").value
		if (repassword != password) {
			alert("确认密码与密码不一致")
			return false;
		}
		//昵称不能为空
		var nickname = document.getElementById("nickname").value
		if (nickname == "") {
			alert("昵称不能为空...")
			return false
		}
		//邮箱输入正确
		var email = document.getElementById("email").value
		if (email.match("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$") == null) {
			alert("邮箱不正确")
			return false
		}
		//加密
		var passHash = md5(document.getElementById("password").value)
		document.getElementById("password").value = passHash
	}
	
</script>
</head>
<body>
	<h3>注册页面</h3>
	<font color="red"> ${message}</font>
	<form action="${pageContext.request.contextPath}/regist" method="post"
		onsubmit="return validateForm();">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="username" id="username"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" id="password">
				</td>
			</tr>
			<tr>
				<td>确认密码：</td>
				<td><input type="password" name="repassword" id="repassword">
				</td>
			</tr>
			<tr>
				<td>昵称：</td>
				<td><input type="text" name="nickname" id="nickname"></td>
			</tr>
			<tr>
				<td>邮箱：</td>
				<td><input type="text" name="email" id="email"></td>
			</tr>
			<tr>
				<td>验证码：</td>
				<td><input type="text" name="checkcode" id="checkcode">
					<img src="${pageContext.request.contextPath}/checkimage"
					style="cursor:pointer" onclick="changeImage();" id="image"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="注册"></td>
			</tr>
		</table>
	</form>
</body>
</html>