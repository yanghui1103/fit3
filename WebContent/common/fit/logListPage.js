$(function() {
	// var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of
	renderAuthorityOperateBtnAll($("#loglist_toolBar"),ctx+"system/getOperationsByMenuId","960",false,"toolBar");
	logsquery();
});


function logsquery(){     
	$('#loglisttdg').datagrid({ 
		pagination:true,
		method: 'GET',
	    url:ctx+'system/loglist/58' ,   
        queryParams:   serializeFormToJSON($("#loglistFM").serializeArray()),
	    toolbar:$("div[name='loglisttb']"),
	    remoteSort: false, 
        columns: [[
                   { field: 'fdid', title: 'ID' ,hidden:true  },
                   { field: 'operator_name', title: '操作者', width: '20%',fixed:true  },
                   { field: 'ip', title: 'IP', width: '20%' }, 
                   { field: 'version_time', title: '操作时间', width: '20%' }, 
                   { field: 'url', title: '请求', width: '20%' }, 
                   { field: 'res_desp', title: '返回标志', width: '20%' }
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
             striped: true, //奇偶行是否区分           
             //双击事件  
             onDblClickRow: function (index, row) {  
            	 addNewTab("日志详情",ctx+"system/openLogDetail/"+row.fdid); 
             }  
	});  


}

//增加查询参数，在页面加载时运行  
function logListreloadgrid() {  
	$('#loglisttdg').datagrid('loadData',{total:0,rows:[]}); //清空DataGrid行数据
    $('#loglisttdg').datagrid('options').queryParams= serializeFormToJSON($("#loglistFM").serializeArray());  
    $("#loglisttdg").datagrid('reload');
}  


function openLogDetail(){
	var row = getSingleGridSelectData($("#loglisttdg"));
	if(row !=null){ 
			addNewTab("修改",ctx+"system/openEditCompany/"+row.fdid); 
	}
}