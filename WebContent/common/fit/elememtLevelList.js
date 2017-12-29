/**
 * 权限相关级别
 */
$(function() {
	renderAuthorityOperateBtnAll($("#elementLevellis_toolBar"),ctx+"system/getOperationsByMenuId","908",false,"toolBar");
	elementListQuery(); 
		
});



function elementListQuery(){    
	$('#elementLevellisdg').datagrid({ 
		pagination:true,
	    url:ctx+'system/elementLevelList/-9' ,   
        queryParams:   serializeFormToJSON($("#elementLevellisFM").serializeArray()),
	    toolbar:$("div[name='elementLevellistb']"),
	    remoteSort: false, 
        columns: [[
                   { field: 'fdid', title: 'ID' ,hidden:true  },
                   { field: 'role_name', title: '角色名称', width: '20%',fixed:true  },
                   { field: 'menu_name', title: '菜单名称', width: '20%' },
                   { field: 'element_type_name', title: '权限类型', width: '20%' }, 
                   { field: 'level_desp', title: '级别描述', width: '20%' }, 
                   { field: 'version_time', title: '分配时间', width: '20%' }
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
 