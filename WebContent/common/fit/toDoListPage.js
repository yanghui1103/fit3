/**
 * 待办列表
 */
$(function() {
	todolistquery(); 
});

function todolistquery(){    
	$('#todolistdg').datagrid({ 
		pagination:true,
	    url:ctx+'systemPlus/todolist/all' ,   
        queryParams:   serializeFormToJSON($("#todolistFM").serializeArray()),
	    toolbar:$("div[name='todolisttb']"),
	    remoteSort: false, 
        columns: [[
                   { field: 'fdid', title: 'ID' ,hidden:true  },
                   { field: 'subject', title: '主题', width: '50%',fixed:true  },
                   { field: 'model_name', title: '来源', width: '25%' },
                   { field: 'create_time', title: '时间', width: '25%' }
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
            	 addNewTab("待办页",ctx+"systemPlus/toDo/"+row.fdid); 
             }    
	});  


}


//增加查询参数，在页面加载时运行  
function todolistPage_query() {     
	$('#todolistdg').datagrid('loadData',{total:0,rows:[]}); //清空DataGrid行数据
    $('#todolistdg').datagrid('options').queryParams= serializeFormToJSON($("#todolistFM").serializeArray());  
    $("#todolistdg").datagrid('reload');  
}  


