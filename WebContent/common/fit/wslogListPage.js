$(function() {
	wslogsquery();
});


function wslogsquery(){     
	$('#wsloglisttdg').datagrid({ 
		pagination:true,
		method: 'GET',
	    url:ctx+'system/loglist/61' ,   
        queryParams:   serializeFormToJSON($("#wsloglistFM").serializeArray()),
	    toolbar:$("div[name='wsloglisttb']"),
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
function wslogListreloadgrid() {  
	$('#wsloglisttdg').datagrid('loadData',{total:0,rows:[]}); //清空DataGrid行数据
    $('#wsloglisttdg').datagrid('options').queryParams= serializeFormToJSON($("#wsloglistFM").serializeArray());  
    $("#wsloglisttdg").datagrid('reload');
}  

 