$(function(){
	
	$("#dataDictAdd").click(function(){ 
		var row = getSingleTreeGridSelectData($("#dataDictTreeGd"));
		if(row !=null){
			var url = ctx + "system/getThisDataDictInfo/"+row.fdid ; 
			$.post(url,function(data){
				if(data.can_add == '1'){
					addExternalTab("增加_"+data.dict_name+"_子节点",ctx + "system/gotoIframe/system/app/addNewDictPage/"+row.fdid);
				}else{
					promptMessage("1","不允许增加子节点");
				}
			});
		}
	});

	$("#dataDictEdit").click(function(){ 
		var row = getSingleTreeGridSelectData($("#dataDictTreeGd"));
		if(row !=null){
			var url = ctx + "system/getThisDataDictInfo/"+row.fdid ;
			$.post(url,function(data){
				if(data.can_edit == '1'){
					addExternalTab("修改_"+data.dict_name ,ctx + "system/gotoIframe/system/app/editDictPage/"+row.fdid);
				}else{
					promptMessage("1","不允许修改本节点");
				}
			});
		}
	});
	

	$("#dataDictDel").click(function(){ 
		var row = getSingleTreeGridSelectData($("#dataDictTreeGd"));
		if(row !=null){			
			var url = ctx + "system/getThisDataDictInfo/"+row.fdid ;
			$.post(url,function(data){
				if(data.can_del=="0"){
					promptMessage("1","不允许删除本节点");		
				}	else{
					promptMessageCallBack("3","是否确认删除该记录",function(){

						$.post(ctx+"system/deleteDict/"+row.fdid ,function(data){
							promptMessage(data.res,data.msg);		
						});
					});
				}	
			});
		}
	});
	
});