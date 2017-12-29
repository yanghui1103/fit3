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
	function createRoleSubmitFm() {
		if (!$("#createrolePageFm").form('enableValidation')
				.form('validate')) {
			return;
		}
		$.ajax({
			type : 'POST',
			url : ctx + "system/createRole",
			data : serializeFormToJSON($("#createrolePageFm")
					.serializeArray()),
			success : function(data) {
				promptMessageCallBack(data.res, data.msg, function() {
					completeSubmitCall(data, "2", "rolelisttdg", "close");
				});
			},
			dataType : "JSON"
		});
	}
</script>
</head>
<body>
	<form 
		id="createrolePageFm" class="easyui-form" method="post"
		data-options="novalidate:false">
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="role_name" style="width: 50%"
				data-options="label:'角色名称:',required:true,missingMessage:'请输入角色名称'">
		</div>
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="desp"
				style="width: 50%" data-options="label:'说明:',required:true">
		</div>
		<div style="margin-bottom: 20px">
			<input id="cc_ele" class="easyui-combobox" name="parent_id"
				style="width: 50%"
				data-options="fitColumns: true, required:true,limitToList:true,label: '父角色:' ,valueField:'fdid',textField:'role_name',url: '<%=basePath%>system/getMyRoles'"  />
		</div>
	</form>
	<div style="position: fixed; right: 30px; bottom: 20px;">
		<button class="easyui-linkbutton" type=button
			onclick="createRoleSubmitFm()" style="width: 80px">保存</button>
	</div>
</body>
</html>