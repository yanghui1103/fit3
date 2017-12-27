/**
 * 岗位列表
 */

$(function() { 
	// var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of
	renderAuthorityOperateBtnAll($("#postionlist_toolBar"),ctx+"system/getOperationsByMenuId","903",false,"toolBar");
 
	postionListquery(); 
	
	

	
});

function postionListquery(){    
	$('#postionlisttdg').datagrid({ 
		pagination:true,
	    url:ctx+'system/postionList/-9' ,   
        queryParams:   serializeFormToJSON($("#postionlistFM").serializeArray()),
	    toolbar:$("div[name='postionlisttb']"),
	    remoteSort: false, 
        columns: [[
                   { field: 'fdid', title: 'ID' ,hidden:true  },
                   { field: 'postion_name', title: '岗位名称', width: '30%',fixed:true  },
                   { field: 'desp', title: '说明', width: '50%' },
                   { field: 'user_count', title: '使用人数', width: '20%',fixed:true  }, 
               ]],
             fit: true,    
             idField: "fdid",
             pagination: true,
             singleSelect:true,
             rownumbers: true, 
             fitColumns:true,
             pageNumber: 1,
             pageSize: 20,
             pageList: [ 20, 30, 40, 50],
             striped: true //奇偶行是否区分                    
	});  


}

//增加查询参数，在页面加载时运行  
function postionListreloadgrid() {  
	$('#postionlisttdg').datagrid('loadData',{total:0,rows:[]}); //清空DataGrid行数据
    $('#postionlisttdg').datagrid('options').queryParams= serializeFormToJSON($("#postionlistFM").serializeArray());  
    $("#postionlisttdg").datagrid('reload');
}  


function deletePostion(){

	promptMessageCallBack("3","是否确认删除该记录",function(){
		var row = getSingleGridSelectData($("#postionlisttdg"));
		if(row==null) return ;
		$.post(ctx+"system/deletePostion/"+row.fdid,function(data){
			promptMessage(data.res,data.msg);
			if(data.res == '2')
				postionListreloadgrid(); 
		});
	});
}
 