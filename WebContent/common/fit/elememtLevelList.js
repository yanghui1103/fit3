/**
 * 权限相关级别
 */
$(function() {
	// var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of
	renderAuthorityOperateBtnAll($("#elementLevellis_toolBar"),ctx+"system/getOperationsByMenuId","908",false,"toolBar");
 
	query(); 
	
	

	
});



function query(){    
	$('#elementLevellisdg').datagrid({ 
		pagination:true,
	    url:ctx+'system/elementLevelList/-9' ,   
        queryParams:   serializeFormToJSON($("#elementLevellisFM").serializeArray()),
	    toolbar:$("div[name='elementLevellistb']"),
	    remoteSort: false, 
        columns: [[
                   { field: 'fdid', title: 'ID' ,hidden:true  },
                   { field: 'company_name', title: '组织名称', width: '25%',fixed:true  },
                   { field: 'company_type_name', title: '类型', width: '25%' },
                   { field: 'company_name', title: '父组织', width: '25%' }, 
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
function eleLevelLisReloadgrid() {  
	$('#elementLevellisdg').datagrid('loadData',{total:0,rows:[]}); //清空DataGrid行数据
    $('#elementLevellisdg').datagrid('options').queryParams= serializeFormToJSON($("#elementLevellisFM").serializeArray());  
    $("#elementLevellisdg").datagrid('reload');
}  
 