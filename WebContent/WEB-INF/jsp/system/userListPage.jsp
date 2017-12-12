<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.bw.fit.common.model.LogUser,com.bw.fit.common.util.*"
	isELIgnored="false" pageEncoding="UTF-8"%><jsp:include page="/common.jsp"></jsp:include>
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
	<table id="userListPagedg"  >
	</table>
	<div name="userlistPagetb" style="padding: 2px 5px;"> 
		<form id="userListPageFm">
			<div id="userlist_toolBar" class="easyui-accordion">
			关键词: <input name="keyWords" class="easyui-textbox"
				style="width: 200px">
			<a class="easyui-linkbutton" iconcls="icon-search" onclick="userListPage_query()">查询</a>
			</div>
		</form>
	</div>
<script type="text/javascript" src="<%=basePath%>common/fit/userListPage.js"></script>
</body>
</html>