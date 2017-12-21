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
function submitForm() {
// 	$('#createCompanyPageFm').form('submit', {
// 		onSubmit : function() {  
// 			if(!$(this).form('enableValidation').form('validate'));{
// 				return ;
// 			} 
// 			 var tab =  getCurrentTab();
// 			 var mainTab = getMainFrameTab();
// 			 var index = mainTab.tabs('getTabIndex',tab);  
// 			 mainTab.find("#companylisttdg").datagrid('reload'); 
// 			 mainTab.tabs('close',index );  
// 		}
// 	});
}
function clearForm2() {  
	$('#createCompanyPageFm').form('clear');
}

function createCMPSubmitFm(){
	 if(!$("#createCompanyPageFm").form('enableValidation').form('validate')){
		return ;
	 } 
	 $.ajax({
		  type: 'POST',
		  url:  ctx+"system/createCompany",
		  data:  serializeFormToJSON($("#createCompanyPageFm").serializeArray()),
		  success: function(data){ 
			  promptMessageCallBack(data.res,data.msg,function(){
				  completeSubmitCall(data,"2","companylisttdg","close");
			  }); 
		  },
		  dataType: "JSON"
	 });
}

</script>
</head>
<body>
<form  action="<%=basePath %>system/createCompany" 
	id="createCompanyPageFm" class="easyui-form"  method="post" data-options="novalidate:false">
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="company_name" style="width:50%" data-options="label:'组织名称:',required:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="company_address" style="width:50%" data-options="label:'组织地址:',required:true">
			</div>
			<div style="margin-bottom:20px">
				<select class="easyui-combobox" name="company_type_id" data-options="label:'组织类型:',required:true" style="width:50%">				
						<option selected value=null>请选择</option>
						<c:forEach var="item" items="${OrgTypeList}" varStatus="s">
							<option value="${item.dict_value}">${item.dict_name}</option>
						</c:forEach>
				</select>
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-combotree" name="parent_company_id" data-options="url:'<%=basePath %>system/getCompanyTreeJSON',method:'get',label:'上级组织:',required:true" style="width:50%">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="company_order" style="width:50%" data-options="label:'序号:',required:true">		
			</div>
			<input name="fdid" type="hidden" value="${uuid }" />
		</form>
		<div style="position:fixed;right:30px;bottom:20px;">
			<button class="easyui-linkbutton" type=button onclick="createCMPSubmitFm()" style="width: 80px">保存</button>
		</div>  
</body>
</html>