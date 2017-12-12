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
	<table   >
	</table>
	<div name="tb" style="padding: 2px 5px;">
		<form name="fm">
		关键词: <input name="keyWords" class="easyui-textbox"   style="width:200px"> 
		<a href="#" class="easyui-linkbutton"   onclick="reloadgrid()">查询</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" >新增</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="clk()">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-del" >删除</a>
		</form>
	</div>
<script type="text/javascript" src="<%=basePath%>common/fit/companyList2Page.js"></script>
</body>
</html>