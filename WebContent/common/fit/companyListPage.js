$(function() {
	// var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of
	renderAuthorityOperateBtnAll($("#companylist_toolBar"),ctx+"system/getOperationsByMenuId","900",false,"toolBar");

	cmpquery();
});


function deleteCompany(){
	var row = getSingleGridSelectData($("#companylisttdg"));
	if(row==null)	return ;
	promptMessageCallBack("3","是否确认删除该记录",function(){
		$.post(ctx+"system/deleteCompany/"+row.fdid,function(data){
			promptMessage(data.res,data.msg);
			cpListreloadgrid(); 
		});
	});
}
function cmpquery(){    
	$('#companylisttdg').datagrid({ 
		pagination:true,
	    url:ctx+'system/companyList/-9' ,   
        queryParams:   serializeFormToJSON($("#companylistFM").serializeArray()),
	    toolbar:$("div[name='companylisttb']"),
	    remoteSort: false, 
        columns: [[
                   { field: 'fdid', title: 'ID' ,hidden:true  },
                   { field: 'company_name', title: '组织名称', width: '25%',fixed:true  },
                   { field: 'company_type_name', title: '类型', width: '25%' }, 
                   { field: 'parent_company_name', title: '父组织', width: '25%' }, 
                   { field: 'company_address', title: '地址', width: '25%' }
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
	$('#companylisttdg').datagrid('loadData',{total:0,rows:[]}); //清空DataGrid行数据
    $('#companylisttdg').datagrid('options').queryParams= serializeFormToJSON($("#companylistFM").serializeArray());  
    $("#companylisttdg").datagrid('reload');
}  


function openEditCompany(){
	var row = getSingleGridSelectData($("#companylisttdg"));
	if(row !=null){ 
			addNewTab("修改",ctx+"system/openEditCompany/"+row.fdid); 
	}
}