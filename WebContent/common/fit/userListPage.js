$(function() {
	// var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of
	renderAuthorityOperateBtnAll($("#userlist_toolBar"),ctx+"system/getOperationsByMenuId","101",false,"toolBar");

	query(); 
});

function query(){    
	$('#userListPagedg').datagrid({ 
		pagination:true,
	    url:ctx+'system/userList/all' ,   
        queryParams:   serializeFormToJSON($("#userListPageFm").serializeArray()),
	    toolbar:$("div[name='userlistPagetb']"),
	    remoteSort: false, 
        columns: [[
                   { field: 'fdid', title: 'ID' ,hidden:true  },
                   { field: 'staff_name', title: '名称', width: '20%',fixed:true  },
                   { field: 'staff_number', title: '登录帐号', width: '20%' },
                   { field: 'phone', title: '联系电话', width: '20%' }, 
                   { field: 'postion_name', title: '岗位', width: '20%' }, 
                   { field: 'company_name', title: '所属公司', width: '20%',sortable:true }
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
function userListPage_query() {     
    $('#userListPagedg').datagrid('options').queryParams= serializeFormToJSON($("#userListPageFm").serializeArray());  
    $("#userListPagedg").datagrid('reload');  
}  


function clk(){
	 getSingleGridSelectData($("#userListPagedg")); 
	addExternalTab("baidu","http://mba.shisu.edu.cn/content/83")

}