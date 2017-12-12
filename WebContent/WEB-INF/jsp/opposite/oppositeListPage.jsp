<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.bw.fit.common.model.LogUser,com.bw.fit.common.util.*"
	isELIgnored="false" pageEncoding="UTF-8"%><%@ include
	file="/include.inc.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

	<div style="margin: 0px;0"></div>
	<table id="dg" title="Custom DataGrid Pager">
	</table>
	<div id="tb" style="padding: 2px 5px;">
		<form id="fm">
		Date From: <input class="easyui-datebox" style="width: 110px">
		To: <input class="easyui-datebox" style="width: 110px">
		Language: <select class="easyui-combobox" panelHeight="auto"
			style="width: 100px">
			<option value="java">Java</option>
			<option value="c">C</option>
			<option value="basic">Basic</option>
			<option value="perl">Perl</option>
			<option value="python">Python</option>
		</select> <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="reloadgrid()">Search</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
		</form>
	</div>
	<script type="text/javascript"
		src="<%=basePath%>common/fit/oppositeListPage.js"></script>
</body>
</html>