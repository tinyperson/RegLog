<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>页面</title>

</head>

<body>
	<c:if test="${not empty loginUser}">
	    欢迎您，${loginUser.nickname }
	    <a href="${pageContext.request.contextPath }/logout">注销</a>
	</c:if>
	<c:if test="${empty loginUser}">
	对不起，您还没有登录 &nbsp;&nbsp;&nbsp;
	<a href="${pageContext.request.contextPath }/login.jsp">登录</a>
	<a href="${pageContext.request.contextPath }/regist.jsp">注册</a>
	</c:if>
</body>
</html>
