$(function() {
	// var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of
	query();
});

function query(){    
	$('table').datagrid({ 
		pagination:true,
	    url:ctx+'system/companyList/all' ,   
        queryParams:   serializeFormToJSON($("#fm").serializeArray()),
	    toolbar:$("div[name='tb']"),
	    remoteSort: false, 
        columns: [[
                   { field: 'fdid', title: 'ID' ,hidden:true  },
                   { field: 'company_name', title: '组织名称', width: '25%',fixed:true  },
                   { field: 'company_type_name', title: '类型', width: '25%' },
                   { field: 'parent_company_name', title: '父组织', width: '25%' }, 
                   { field: 'company_address', title: '地址', width: '25%' }
               ]],
             fit: true,    
             idField: "company_name",
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
function reloadgrid() {    
    $('#table').datagrid('options').queryParams= serializeFormToJSON($("#fm").serializeArray());  
    $("#table").datagrid('reload');  
}  


function clk(){
	getSingleGridSelectData($('#table'),"s"); 
	addNewTab("baidu",ctx + "system/userList/all")

}