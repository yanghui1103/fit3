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
	<div class="easyui-panel" 
		style="width: 100%;height:100%; max-width: 100%; padding: 20px 20px;">
		<div style="margin-bottom: 20px">
			<input class="easyui-filebox" data-options="accept:'*',multiple:false,buttonText: '选择文件'"
				style="width: 50%">
		<button class="easyui-linkbutton" type=button onclick="upLoadAttachmentFile()"
			style="width: 80px">上传</button> 
		<button class="easyui-linkbutton" type=button onclick="upLoadAttachmentFile()"
			style="width: 80px">删除</button> 
<!-- 		<button class="easyui-linkbutton" type=button onclick="upLoadAttachmentFile()" -->
<!-- 			style="width: 80px">预览</button>  -->
<!-- 		<button class="easyui-linkbutton" type=button onclick="upLoadAttachmentFile()" -->
<!-- 			style="width: 80px">下载</button>  -->
		</div>			
	</div>
	<table id="sysAttachmentListTb"></table>
	<script type="text/javascript">
	function upLoadAttachmentFile() {
        var fileObj = document.getElementById("filebox_file_id_1").files[0]; // 获取文件对象
        var FileController =  '<%=basePath%>saleShip/importSaleShipExcel'  ;                    
        // FormData 对象
        var form = new FormData();
        form.append("author", "yangh");                        // 可以增加表单数据
        form.append("file", fileObj);                           // 文件对象
        // XMLHttpRequest 对象
        var xhr = new XMLHttpRequest();
        xhr.open("post", FileController, true);
		//         xhr.onload = function () {
		//             alert("上传完成!");
		//         };
        xhr.send(form);
        xhr.onreadystatechange = function() {
	        	  if(xhr.readyState == 4 && xhr.status == 200){
		        	  var data = JSON.parse(xhr.responseText) ;
                       
          			  promptMessage(data.res,data.msg); 
	         }
        }
    }

	</script>
</body>
</html>