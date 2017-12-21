function dataDictList(event, treeId, treeNode){ 
	var gotoHref = $("#gotoHref");
	gotoHref.attr("href","dictlist/" + treeNode.id);
	gotoHref.trigger("click");
}


$(function(){
	
	$('#dataDictdg').datagrid({ 
		pagination:true,
	    url:ctx+'system/companyList/-9' ,   
        queryParams:   serializeFormToJSON($("#companylistFM").serializeArray()),
	    toolbar:$("div[name='tb']"),
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
	
});