/**
 * 角色列表
 */

$(function() { 
	// var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of
	renderAuthorityOperateBtnAll($("#rolelist_toolBar"),ctx+"system/getOperationsByMenuId","901",false,"toolBar");
 
	roleListquery(); 
	
	

	
});

function roleListquery(){    
	$('#rolelisttdg').datagrid({ 
		pagination:true,
	    url:ctx+'system/roleList/-9' ,   
        queryParams:   serializeFormToJSON($("#rolelistFM").serializeArray()),
	    toolbar:$("div[name='rolelisttb']"),
	    remoteSort: false, 
        columns: [[
                   { field: 'fdid', title: 'ID' ,hidden:true  },
                   { field: 'role_name', title: '角色名称', width: '30%',fixed:true  },
                   { field: 'user_count', title: '使用人数', width: '70%' } 
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
function cpListreloadgrid() {  
	$('#rolelisttdg').datagrid('loadData',{total:0,rows:[]}); //清空DataGrid行数据
    $('#rolelisttdg').datagrid('options').queryParams= serializeFormToJSON($("#rolelistFM").serializeArray());  
    $("#rolelisttdg").datagrid('reload');
}  