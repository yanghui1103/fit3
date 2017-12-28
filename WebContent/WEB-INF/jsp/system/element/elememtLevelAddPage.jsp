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
<script type="text/javascript">
	function clearForm2() {
		$('#createELEPageFm').form('clear');
	}

	function createELESubmitFm() {
		if (!$("#createELEPageFm").form('enableValidation')
				.form('validate')) {
			return;
		}
		$.ajax({
			type : 'POST',
			url : ctx + "system/createElementLevel",
			data : serializeFormToJSON($("#createELEPageFm")
					.serializeArray()),
			success : function(data) {
				promptMessageCallBack(data.res, data.msg, function() {
					completeSubmitCall(data, "2", "elementLevellisdg", "close");
				});
			},
			dataType : "JSON"
		});
	}
</script>
</head>
<body>
	<form id="createELEPageFm" class="easyui-form" method="post"
		data-options="novalidate:false">
		<div  style="margin-bottom: 20px">
			<input id="cc" class="easyui-combobox" name="role_id"   style="width:50%" 
    	data-options="fitColumns: true, required:true,limitToList:true,label: '角色:' ,valueField:'fdid',textField:'role_name',url: '<%=basePath%>system/getMyRoles'" />  
		</div>
	</form>
	<div style="position: fixed; right: 30px; bottom: 20px;">
		<button class="easyui-linkbutton" type=button
			onclick="createELESubmitFm()" style="width: 80px">保存</button>
	</div>
</body>
</html>