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
                   { field: 'user_count', title: '使用人数', width: '30%' } ,
                   { field: 'parent_role_name', title: '父角色', width: '40%' } 
                   
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
             striped: true  //奇偶行是否区分   
	});  
}

//增加查询参数，在页面加载时运行  
function cpListreloadgrid() {  
	$('#rolelisttdg').datagrid('loadData',{total:0,rows:[]}); //清空DataGrid行数据
    $('#rolelisttdg').datagrid('options').queryParams= serializeFormToJSON($("#rolelistFM").serializeArray());  
    $("#rolelisttdg").datagrid('reload');
}  


function deleteRole(){
	var row = getSingleGridSelectData($("#rolelisttdg"));
	if(row==null) return ;
	promptMessageCallBack("3","是否确认删除该记录",function(){
		$.post(ctx+"system/deleteRole/"+row.fdid,function(data){
			promptMessage(data.res,data.msg);
			if(data.res == '2')
				postionListreloadgrid(); 
		});
	});

	
}


function openEditRole(){
	var row = getSingleGridSelectData($("#rolelisttdg"));
	if(row==null)
		return ;
	var role_name ="";
	$.ajax({
		url:ctx+"system/getRoleDetail/"+row.fdid ,
		success:function(data){
			role_name = data.role_name ;
			addNewTab("修改("+role_name+")资料",ctx+"system/openEditRole/"+row.fdid); 
		}
	});
}