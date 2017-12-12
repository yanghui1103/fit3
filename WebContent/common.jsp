<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ include
	file="/include.inc.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/css/easyui_dm.css"> 
<script type="text/javascript"
	src="<%=basePath%>common/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>common/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>common/js/easyui/common.js"></script>
</head> 
</html>