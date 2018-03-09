<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.bw.fit.common.util.*,com.bw.fit.common.util.*,org.apache.shiro.session.Session,com.bw.fit.system.model.User" 
	pageEncoding="UTF-8"%><jsp:include
	page="/common.jsp"></jsp:include>
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
	$(function() {
		var tabMaxNum =<%=PropertiesUtil.getValueByKey("system.tabMaxNum")%>	;
		var tabMaxNumPrompt =<%=PropertiesUtil.getValueByKey("system.tabMaxNumPrompt")%> ;
		$('#mainFrame').tabs({
			border : false,
			onSelect : function(title) {
				var $this = $(this);
				var tabCount = $this.tabs('tabs').length;
				if (tabMaxNum < tabCount) {
					if (tabMaxNumPrompt == "1") { // 如果要求提示
						promptMessage("1", "温馨提示:系统限制最多打开" + tabMaxNum + "页");
					}
					var tab = $this.tabs("getSelected");
					var index = $this.tabs('getTabIndex', tab);
					$this.tabs('close', 2);
				}
			}
		});
	});
	
	function jsLogOutSys(){	
		promptMessageCallBack("3","是否确认退出系统?",function(){
			$.post(ctx+"system/logOutSys",function(data){
				window.location.href = ctx + 'system/login';
			});
		}); 
	}
</script>
</head>
<body class="easyui-layout">
<%
	Session session_first = PubFun.getCurrentSession();
	User user_now = ((User) session_first.getAttribute("CurrentUser"));
%>
	<div data-options="region:'north',border:false"
		style="height: 60px; background: #B3DFDA; padding: 10px">
		<div style="float: right"> 
			<div  style="padding: 5px;">
				<a href="#" class="easyui-menubutton"
					data-options="menu:'#indexPageTopMm',iconCls:'icon-help'"><%= user_now.getUser_name() %></a>
			</div>
			<div id="indexPageTopMm" style="width: 150px;">
				<div><a href="javascript:jsLogOutSys();" class="easyui-menubutton"  >个人资料</a></div>
				<div><a href="javascript:jsLogOutSys();" class="easyui-menubutton"  >退出系统</a></div> 
				<div>
					<span>About</span>
					<div class="menu-content" style="padding: 10px; text-align: left">
						<p style="font-size: 14px; color: #444">本系统是Fit基础开发平台，第三个版本,V3.0.1，处于逻辑架构迭代过程中...</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'菜单栏'"
		style="width: 180px; padding: 5px;">
		<ul class="easyui-tree"
			data-options="url:'<%=basePath%>system/getMenuAuthTreeJson',method:'get',animate:true,dnd:true"></ul>
	</div>
	<!-- 	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div> -->
	<!-- 	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div> -->
	<div data-options="region:'',title:' ' ">
		<div id="mainFrame" class="easyui-tabs"
			data-options="fit:true,border:false,plain:true">
			<div title="我的主页" data-options="href:''" style="padding: 10px"></div>

		</div>
	</div>
</body>
</html>