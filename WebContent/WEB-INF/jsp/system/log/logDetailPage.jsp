<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.bw.fit.common.util.*" pageEncoding="UTF-8"%><jsp:include
	page="/common.jsp" /><%@ include file="/include.inc.jsp"%>
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
</head>
<body>
	<div style="margin:20px 0;"></div>
	<div    style="width:80%;">
		<form id="ff" method="post">
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" value="${log.operator_name }" style="width:80%" data-options="label:'操作者'">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox"  value="${log.create_time }"  style="width:80%" data-options="label:'操作时间'">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox"  value="${log.url }"  style="width:80%" data-options="label:'URL'">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox"  value="${log.ip }"  style="width:80%" data-options="label:'IP'">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox"  value="${log.operate_function }"  style="width:80%" data-options="label:'HTTP请求'">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox"  value="${log.params }"  style="width:80%;height:60px" data-options="label:'请求参数',multiline:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox"  value="${log.res_desp }"  style="width:80%" data-options="label:'返回标志'">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox"  value="${log.msg }"  style="width:80%;height:60px" data-options="label:'返回消息',multiline:true">
			</div>
		</form>
	</div>
</body>
</html>