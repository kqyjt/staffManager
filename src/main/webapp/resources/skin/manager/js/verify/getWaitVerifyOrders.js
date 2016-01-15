var VerifyOrder1 = {}
VerifyOrder1.params = {};
VerifyOrder1.currTab =  $('#center_tabs').tabs('getSelected'); // 获得当前tab

VerifyOrder1.setDatagridQueryParams=function(){
	var queryParams = $('#VerifyOrder1_datagrid').datagrid('options').queryParams;  
	queryParams.USER_ID=$.trim($("#VerifyOrder1_search").find("input[name='USER_ID']").val());
	queryParams.CITY_ID=$.trim($("#VerifyOrder1_search").find("input[name='CITY_ID']").val());
	queryParams.CARD_NUM=$.trim($("#VerifyOrder1_search").find("input[name='CARD_NUM']").val());
	queryParams.PHONE_NUM=$.trim($("#VerifyOrder1_search").find("input[name='PHONE_NUM']").val());
	queryParams.BEGIN_TIME=$.trim($("#VerifyOrder1_search").find("input[name='BEGIN_TIME']").val());
	queryParams.END_TIME=$.trim($("#VerifyOrder1_search").find("input[name='END_TIME']").val());
} 

// 订单查询
VerifyOrder1.searchSubmit = function(){
	VerifyOrder1.setDatagridQueryParams();
	$("#VerifyOrder1_datagrid").datagrid("reload");
}

// 订单领用
VerifyOrder1.dispense = function(){
	var checkeRows = $("#VerifyOrder1_datagrid").datagrid("getChecked");
	if (checkeRows == "" || checkeRows == undefined) {
		$.messager.alert('提示信息',"请先选择一行记录!",'info');
		return false;
	}
	VerifyOrder1.params = {};
	var orderIds = "";
	for(var i = 0;i < checkeRows.length;i++){
		orderIds += checkeRows[i].USER_ID + ",";
	}
	
	var u = managerPath + '/manager/verify/VerifyManagerMgr.json?m=execute&f=updateVerifyOrder';

	$.ajax({
        url: u,
        secureuri: false,
        data: {USER_IDS:orderIds},
        dataType: 'json',
        type: 'POST',
        success: function (respData) {
        	var result = respData.result;
        	if(result.success){
        		//alert("领用成功!");
        		$("#VerifyOrder1_datagrid").datagrid("reload");
	    	}else{
	    		alert(result.dataSet.errorDetail);
	    	}
        },
	    error: function(respData){ 
	    	Alertone(result.dataSet.errorDetail);
	    }
    });
}

//过滤返回值前缀
$('#VerifyOrder1_datagrid').datagrid({
	loadFilter: function(data){
		if (data.result.dataSet){
			return data.result.dataSet;
		} else {
			return data;
		}
	},
	onClickRow: function(rowIndex, rowData){
		//OrderApprove.gotoApprove();
	},
	onBeforeLoad: function(param){
		//OrderApprove.setDatagridQueryParams();
	}
});
