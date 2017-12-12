$(function() {
	// var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of
	query();
});

function query(){ 
	$('#dg').datagrid({ 
		pagination:true,
	    url:ctx+'common/datagrid_data1.json',  
	    pageNumber:2, 
	    toolbar:$("#tb"),
	    remoteSort: false, 
	    frozenColumns:[[{ field: 'productid', title: '油品', width: 80,fixed:true },	                    
	                    { field: 'productname', title: '升数', width: 80,fixed:true  }]],
        columns: [[
                   { field: 'productname', title: '吨数', width: 80,fixed:true  },
                   { field: 'productname', title: '密度', width: 80 },
                   { field: 'productname', title: '金额', width: 80 }, 
                   { field: 'productname', title: '密度', width: 80 },
                   { field: 'productname', title: '金额', width: 80 }, 
                   { field: 'productname', title: '密度', width: 80 },
                   { field: 'productname', title: '金额', width: 80 }, 
                   { field: 'productname', title: '税率', width: 80, sortable: true },
                   { field: 'productname', title: '含税金额', width: 80, sortable: true },
                   { field: 'productname', title: '不含税金额', width: 80, sortable: true },
                   { field: 'productname', title: '税额', width: 80, sortable: true },
                   { field: 'productname', title: '收付款状态', width: 80, sortable: true },
                   { field: 'productname', title: '经办人', width: 80 },
                   { field: 'productname', title: '下账部门', width: 80 },
                   { field: 'productname', title: '上账状态', width: 80 },
                   { field: 'productname', title: '上账时间', width: 80},
                   { field: 'productname', title: '付款方式', width: 80 }
               ]],
             fit: true,
             idField: "productid",
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
    $("#dg").datagrid('reload');  

    query();
}  