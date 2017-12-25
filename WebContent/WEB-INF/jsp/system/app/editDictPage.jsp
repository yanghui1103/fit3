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
	function saveEditDictSubmitFm() {
		if (!$("#editDictFm").form('enableValidation')
				.form('validate')) {
			return;
		}
		$.ajax({
			type : 'POST',
			url : ctx + "system/addNewDict",
			data : serializeFormToJSON($("#editDictFm")
					.serializeArray()),
			success : function(data) {
				promptMessageCallBack(data.res, data.msg, function() {
					completeSubmitCallTreeGd(data, "2", "dataDictTreeGd", "close");
				});
			},
			dataType : "JSON"
		});
	}
</script>
</head>
<body>
	<form 
		id="editDictFm" class="easyui-form" method="post"
		data-options="novalidate:false"> 
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="dict_value"  value="${model.dict_value }" style="width: 50%"
				data-options="label:'数据值:',required:true">
		</div>
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="dict_name"  value="${model.dict_name }" style="width: 50%"
				data-options="label:'数据名称:',required:true">
		</div>
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="num" style="width: 50%" value="${model.num }" 
				data-options="label:'序号:',required:true">
		</div>
		
		<div style="margin-bottom: 20px">
			<select class="easyui-combobox" name="can_add" label="可增加:"    style="width:50%;" data-options="required:true">
				<option value="1">是</option>
				<option value="0">否</option>
			</select>	
		</div>
		<div style="margin-bottom: 20px">
			<select class="easyui-combobox" name="can_edit" label="可修改:"   style="width:50%;" data-options="required:true">
				<option value="1">是</option>
				<option value="0">否</option>
			</select>	
		</div>
		<div style="margin-bottom: 20px">
			<select class="easyui-combobox" name="can_del" label="可删除:"   style="width:50%;" data-options="required:true">
				<option value="0">否</option>
				<option value="1">是</option>
			</select>	
		</div>
		<input name="fdid" type="hidden" value="${model.fdid }" />
	</form>
	<div style="position: fixed; right: 30px; bottom: 20px;">
		<button class="easyui-linkbutton" type=button
			onclick="saveEditDictSubmitFm()" style="width: 80px">保存</button>
	</div>
</body>
</html>