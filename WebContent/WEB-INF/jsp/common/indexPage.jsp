<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.bw.fit.common.util.*"
	pageEncoding="UTF-8"%><jsp:include page="/common.jsp"></jsp:include>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title><%=PropertiesUtil.getValueByKey("system.full_name")%></title> 
<script type="text/javascript"
	src="<%=basePath%>common/fit/indexPage.js"></script>
<script type="text/javascript">
$(function(){
	var  tabMaxNum = <%=PropertiesUtil.getValueByKey("system.tabMaxNum")%>;
	var  tabMaxNumPrompt = <%=PropertiesUtil.getValueByKey("system.tabMaxNumPrompt")%>
	$('#mainFrame').tabs({    
	    border:false,    
	    onSelect:function(title){     
	    	var $this  = $(this) ;
	    	var tabCount = $this.tabs('tabs').length;
	    	if(tabMaxNum<tabCount){
	    		if(tabMaxNumPrompt == "1"){ // 如果要求提示
		    		promptMessage("1","温馨提示:系统限制最多打开"+tabMaxNum+"页");
	    		}
	    		var tab = $this.tabs("getSelected");
	    		var index = $this.tabs('getTabIndex',tab);  
	    		$this.tabs('close',2);  
	    	} 
	    }    
	});  
});
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height: 60px; background: #B3DFDA; padding: 10px"></div>
	<div data-options="region:'west',split:true,title:'菜单栏'"
		style="width: 180px; padding: 5px;">
		<ul class="easyui-tree" 
			data-options="url:'<%=basePath%>system/getMenuAuthTreeJson',method:'get',animate:true,dnd:true"></ul>
	</div>
	<!-- 	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div> -->
	<!-- 	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div> -->
	<div data-options="region:'',title:' '">
		<div id="mainFrame" class="easyui-tabs"
			data-options="fit:true,border:false,plain:true">
			<div title="我的主页" data-options="href:''"
				style="padding: 10px"></div>
			
		</div>
	</div>
</body>
</html>