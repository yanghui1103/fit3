<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.bw.fit.common.util.*" isELIgnored="false"
	pageEncoding="UTF-8"%><jsp:include page="/common.jsp"></jsp:include>
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
	<div name="dataDictTb" style="padding: 2px 5px;"> 
		<a class="easyui-linkbutton" iconcls="icon-add" id="dataDictAdd">增加</a>
		<a class="easyui-linkbutton" iconcls="icon-edit" id="dataDictEdit">修改</a>
		<a class="easyui-linkbutton" iconcls="icon-remove" id="dataDictDel">删除</a>
	</div>
	<table title="数据字典列表" id="dataDictTreeGd"  class="easyui-treegrid" style="width:100%;height:100%"
			data-options="
				url: '<%=basePath %>system/getDataDictList/0',
				method: 'get',
				lines: true,
				rownumbers: true,
				idField: 'fdid',
				treeField: 'dict_name'
			">
		<thead>
			<tr>
				<th data-options="field:'fdid'" hidden=true>名称</th>
				<th data-options="field:'dict_name'" width="30%">名称</th>
				<th data-options="field:'dict_value'" width="25%" align="right">值</th>
				<th data-options="field:'num'" width="15%">序号</th>
				<th data-options="field:'can_add'" width="10%">可增加</th>
				<th data-options="field:'can_edit'" width="10%">可修改</th>
				<th data-options="field:'can_del'" width="10%">可删除</th>
			</tr>
		</thead>
	</table>

<script type="text/javascript" src="<%=basePath%>common/fit/dataDict.js"></script>
</body>
</html>