/**
 * 待办列表
 */
$(function() {
	todolistquery(); 
});

function todolistquery(){    
	$('#todolistdg').datagrid({ 
		pagination:true,
	    url:ctx+'systemPlus/todolist/64' ,   
        queryParams:   serializeFormToJSON($("#todolistFM").serializeArray()),
	    toolbar:$("div[name='todolisttb']"),
	    remoteSort: false, 
        columns: [[
                   { field: 'fdid', title: 'ID' ,hidden:true  },
                   { field: 'url_link', title: 'url_link' ,hidden:true  },
                   { field: 'link_data', title: 'link_data' ,hidden:true  },
                   { field: 'flow_id', title: 'flow_id' ,hidden:true  },
                   { field: 'subject', title: '主题', width: '50%',fixed:true  },
                   { field: 'app_name', title: '来源', width: '25%' },
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
            	 var request_url = ctx + row.url_link+"/"+ row.flow_id +"/"+  row.link_data ; 
            	 addNewTab("待办页", request_url); 
             }    
	});  


}


//增加查询参数，在页面加载时运行  
function todolistpage_query() {     
	$('#todolistdg').datagrid('loadData',{total:0,rows:[]}); //清空DataGrid行数据
    $('#todolistdg').datagrid('options').queryParams= serializeFormToJSON($("#todolistFM").serializeArray());  
    $("#todolistdg").datagrid('reload');  
}  


