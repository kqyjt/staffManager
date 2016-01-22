var VerifyOrder3 = {}
VerifyOrder3.params = {};
VerifyOrder3.currTab =  $('#center_tabs').tabs('getSelected'); // 获得当前tab

VerifyOrder3.setDatagridQueryParams=function(){
	var queryParams = $('#VerifyOrder3_datagrid').datagrid('options').queryParams;  
	queryParams.USER_ID=$.trim($("#VerifyOrder3_search").find("input[name='USER_ID']").val());
	queryParams.CITY_ID=$.trim($("#VerifyOrder3_search").find("input[name='CITY_ID']").val());
	queryParams.CARD_NUM=$.trim($("#VerifyOrder3_search").find("input[name='CARD_NUM']").val());
	queryParams.PHONE_NUM=$.trim($("#VerifyOrder3_search").find("input[name='PHONE_NUM']").val());
	queryParams.BEGIN_TIME=$.trim($("#VerifyOrder3_search").find("input[name='BEGIN_TIME']").val());
	queryParams.END_TIME=$.trim($("#VerifyOrder3_search").find("input[name='END_TIME']").val());
	queryParams.AUDIT_STATE=$.trim($("#VerifyOrder3_search").find("input[name='AUDIT_STATE']").val());
	queryParams.BIS_STATE=$.trim($("#VerifyOrder3_search").find("input[name='BIS_STATE']").val());
	queryParams.AUDIT_STAFF=$.trim($("#VerifyOrder3_search").find("input[name='AUDIT_STAFF']").val());
} 

// 订单查询
VerifyOrder3.searchSubmit = function(){
	VerifyOrder3.setDatagridQueryParams();
	$("#VerifyOrder3_datagrid").datagrid("reload");
}

// 订单领用
VerifyOrder3.exprotVerifyOrderList = function(){
	$("#verifyOrderForm").submit();
}

VerifyOrder3.viewDetail  = function(){
	if (!Array.prototype.forEach) {  
	    Array.prototype.forEach = function(fun /*, thisp*/){  
	        var len = this.length;  
	        if (typeof fun != "function")  
	            throw new TypeError();  
	        var thisp = arguments[1];  
	        for (var i = 0; i < len; i++){  
	            if (i in this)  
	                fun.call(thisp, this[i], i, this);  
	        }  
	    };  
	}
	
	var selectRow = $("#VerifyOrder3_datagrid").datagrid("getSelected");
	if (selectRow == "" || selectRow == undefined) {
		$.messager.alert('提示信息',"请先选择一行记录!",'info');
		return false;
	}
	var userId = selectRow.USER_ID;
	var cardNum = selectRow.CARD_NUM;
	var userName = selectRow.USER_NAME;
	var clientRequest = new Tcsp.ClientRequest();
	var url2 = managerPath + "/manager/verify/CertVerify.json?m=execute&f=certVerify";
	var params = new Object();
	
	params.certId=cardNum;
	params.certName=userName;
	 
	clientRequest.postJSON(url2,params,function(response) {
		var apptx = response.result.dataSet.apptx;
		var certName = response.result.dataSet.certName;
		var certId = response.result.dataSet.certId;
		var sex = response.result.dataSet.sex;
		var nation = response.result.dataSet.nation;
		var exp = response.result.dataSet.exp;
		var birthday = response.result.dataSet.birthday;
		var issue = response.result.dataSet.issue;
		var addr = response.result.dataSet.addr;
		var url = managerPath + "/manager/verify/VerifyManagerMgr.htm?m=query&f=verifyDetail&userId="+userId+"&apptx="+apptx
		+"&certName="+encodeURIComponent(certName)+"&certId="+certId+"&sex="+sex+"&nation="+encodeURIComponent(nation)+"&exp="
		+exp+"&birthday="+birthday+"&issue="+encodeURIComponent(issue)+"&addr="+encodeURIComponent(addr);
		OpenTab("center_tabs",'订单详情',url,'icon-munich-collaboration');
	});
	
}

function verifyOrder2ConvertState(value){
	if(value=='A0'){
		return '待审核';
	}else if(value=='A1'){
		return '已通过';
	}else if(value=='A2'){
		return '未通过';
	}else if(value=='B0'){
		return '待办理';
	}else if(value=='B1'){
		return '已办理';
	}else if(value=='B2'){
		return '办理失败';
	}else {
		return '异常';
	}
}

//过滤返回值前缀
$('#VerifyOrder3_datagrid').datagrid({
	loadFilter: function(data){
		if (data.result.dataSet){
			return data.result.dataSet;
		} else {
			return data;
		}
	},
	onClickRow: function(rowIndex, rowData){
		VerifyOrder3.viewDetail();
	},
	onBeforeLoad: function(param){
		VerifyOrder3.setDatagridQueryParams();
	}
});
