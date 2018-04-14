<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.bw.fit.common.util.*"
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
	<table id="userlisttdg"  >
	</table>
	<div name="userlisttb" style="padding: 2px 5px;"> 
		<form id="userlistFM">
			<div id="userlist_toolBar" class="easyui-accordion">
			关键词: <input name="keyWords" class="easyui-textbox"
				style="width: 200px">
			<a class="easyui-linkbutton" iconcls="icon-search" onclick="userListPage_query()">查询</a>
			<a class="easyui-linkbutton" iconcls="icon-search" onclick="ff()">测试上传</a>
			<div id="dd" >附件</div>
			</div>
		</form>
	</div>
<script type="text/javascript" src="<%=basePath%>common/fit/userListPage.js"></script>
<script type="text/javascript">
$(function(){
	$('#dd').dialog({    
	    title: '附件框',    
	    width: 800,    
	    height: 500,    
	    closed: true,    
	    cache: false,    
	    minimizable:false,
	    maximizable:false,
	    resizable:true,
	    href: ctx +"systemPlus/openAttachmentPage/-9/-9/2",    
	    modal: true   
	});   
});
function ff(){
	$('#dd').dialog('open');
}
</script>
</body>
</html>