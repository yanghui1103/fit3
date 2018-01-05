/**
 * 待办列表
 */
$(function() {
	toreadlistquery(); 
});

function toreadlistquery(){    
	$('#toreadlistdg').datagrid({ 
		pagination:true,
	    url:ctx+'systemPlus/toreadlist/all' ,   
        queryParams:   serializeFormToJSON($("#toreadlistFM").serializeArray()),
	    toolbar:$("div[name='toreadlisttb']"),
	    remoteSort: false, 
        columns: [[
                   { field: 'fdid', title: 'ID' ,hidden:true  },
                   { field: 'subject', title: '主题', width: '50%',fixed:true  },
                   { field: 'model_name', title: '来源', width: '20%' },
                   { field: 'create_time', title: '时间', width: '20%' },
                   { field: 'status', title: '状态', width: '10%' }
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
function toreadlistpage_query() {     
	$('#toreadlistdg').datagrid('loadData',{total:0,rows:[]}); //清空DataGrid行数据
    $('#toreadlistdg').datagrid('options').queryParams= serializeFormToJSON($("#toreadlistFM").serializeArray());  
    $("#toreadlistdg").datagrid('reload');  
}  


