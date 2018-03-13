$(function() {
	// var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of
	renderAuthorityOperateBtnAll($("#jobloglist_toolBar"),ctx+"system/getOperationsByMenuId","961",false,"toolBar");
	joblogsquery();
});


function joblogsquery(){     
	$('#jobloglisttdg').datagrid({ 
		pagination:true,
		method: 'GET',
	    url:ctx+'system/loglist/59' ,   
        queryParams:   serializeFormToJSON($("#jobloglistFM").serializeArray()),
	    toolbar:$("div[name='jobloglisttb']"),
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
function jobloglistreloadgrid() {  
	$('#jobloglisttdg').datagrid('loadData',{total:0,rows:[]}); //清空DataGrid行数据
    $('#jobloglisttdg').datagrid('options').queryParams= serializeFormToJSON($("#jobloglistFM").serializeArray());  
    $("#jobloglisttdg").datagrid('reload');
}  