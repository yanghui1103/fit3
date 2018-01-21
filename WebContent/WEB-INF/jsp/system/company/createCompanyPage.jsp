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
		$('#createCompanyPageFm').form('clear');
	}

	function createCMPSubmitFm() {
		if (!$("#createCompanyPageFm").form('enableValidation')
				.form('validate')) {
			return;
		}
		$.ajax({
			type : 'POST',
			url : ctx + "system/createCompany",
			data : serializeFormToJSON($("#createCompanyPageFm")
					.serializeArray()),
			success : function(data) {
				promptMessageCallBack(data.res, data.msg, function() {
					completeSubmitCall(data, "2", "companylisttdg", "close");
				});
			},
			dataType : "JSON"
		});
	}
</script>
</head>
<body>
	<form action="<%=basePath%>system/createCompany"
		id="createCompanyPageFm" class="easyui-form" method="post"
		data-options="novalidate:false">
		<div class="fit_form_row">
			<input class="easyui-textbox" name="company_name" 
				data-options="label:'组织名称:',required:true">
		</div>
		<div class="fit_form_row">
			<input class="easyui-textbox" name="company_address"
				 data-options="label:'组织地址:'">
		</div>
		<div class="fit_form_row">
			<select class="easyui-combogrid"  name="company_type_cd"   data-options="limitToList:true,
					panelWidth: 500,
					idField: 'dict_value',
					textField: 'dict_name',
					url: '<%=basePath%>system/getDataDict/100022',
					method: 'get', 
					columns: [[
						{field:'dict_value',title:'值',width:80},
						{field:'dict_name',title:'名称',width:120} 
					]],
					fitColumns: true, 
					required:true,
					label: '组织类型:' 
				">
			</select>
		</div>
		<div class="fit_form_row">
			<input class="easyui-combotree" name="parent_id"
				data-options="url:'<%=basePath%>system/getCompanyTree/0',method:'get',label:'上级组织:',required:true"
				>
		</div>
		<div class="fit_form_row">
			<input class="easyui-textbox" name="company_order" 
				data-options="label:'序号:',required:true">
		</div>
	</form>
	<div style="position: fixed; right: 30px; bottom: 20px;">
		<button class="easyui-linkbutton" type=button
			onclick="createCMPSubmitFm()" style="width: 80px">保存</button>
	</div>
</body>
</html>