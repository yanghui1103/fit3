$(function() {
	$('.easyui-tree').tree({
		onClick : function(node) {
			// 在用户点击menu的时候提示 
			$.ajax({
				  type: 'POST',
				  url:  ctx+"system/getFrameUrlByMenuId",
				  data: {"menuId":node.id},
				  success: function(data){  
					  if(data.res !="1" ){
						  openNewTab(data)
					  }
				  },
				  dataType: "JSON"
				});
		}
	});

	$('#mainFrame').tabs({
		border : false,
		onSelect : function(title) {
			// alert(title + ' is selected');
		}
	});

});