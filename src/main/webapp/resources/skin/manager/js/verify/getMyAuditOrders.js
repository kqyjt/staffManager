var VerifyOrder2 = {}
VerifyOrder2.params = {};
VerifyOrder2.currTab =  $('#center_tabs').tabs('getSelected'); // 获得当前tab

VerifyOrder2.setDatagridQueryParams=function(){
	var queryParams = $('#VerifyOrder2_datagrid').datagrid('options').queryParams;  
	queryParams.USER_ID=$.trim($("#VerifyOrder2_search").find("input[name='USER_ID']").val());
	queryParams.CITY_ID=$.trim($("#VerifyOrder2_search").find("input[name='CITY_ID']").val());
	queryParams.CARD_NUM=$.trim($("#VerifyOrder2_search").find("input[name='CARD_NUM']").val());
	queryParams.PHONE_NUM=$.trim($("#VerifyOrder2_search").find("input[name='PHONE_NUM']").val());
	queryParams.BEGIN_TIME=$.trim($("#VerifyOrder2_search").find("input[name='BEGIN_TIME']").val());
	queryParams.END_TIME=$.trim($("#VerifyOrder2_search").find("input[name='END_TIME']").val());
	queryParams.AUDIT_STATE=$.trim($("#VerifyOrder2_search").find("input[name='AUDIT_STATE']").val());
	queryParams.BIS_STATE=$.trim($("#VerifyOrder2_search").find("input[name='BIS_STATE']").val());
} 

// 订单查询
VerifyOrder2.searchSubmit = function(){
	VerifyOrder2.setDatagridQueryParams();
	$("#VerifyOrder2_datagrid").datagrid("reload");
}

//打开订单详情界面
VerifyOrder2.getDetail = function(){
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
	
	var selectRow = $("#VerifyOrder2_datagrid").datagrid("getSelected");
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

//打开审核详情界面
VerifyOrder2.gotoAudit = function(){
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
	
	var selectRow = $("#VerifyOrder2_datagrid").datagrid("getSelected");
	if (selectRow == "" || selectRow == undefined) {
		$.messager.alert('提示信息',"请先选择一行记录!",'info');
		return false;
	}
	
	if(selectRow.AUDIT_STATE == 'A1'){
		$.messager.alert('提示信息',"已审核通过!",'info');
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
		var url = managerPath + "/manager/verify/VerifyManagerMgr.htm?m=query&f=verifyDetail&userId="+userId + "&isaudit=1"+"&apptx="+apptx
		+"&certName="+encodeURIComponent(certName)+"&certId="+certId+"&sex="+sex+"&nation="+encodeURIComponent(nation)+"&exp="
		+exp+"&birthday="+birthday+"&issue="+encodeURIComponent(issue)+"&addr="+encodeURIComponent(addr);
		OpenTab("center_tabs",'审核详情',url,'icon-munich-collaboration');
	}); 
	
}

//打开业务办理界面
VerifyOrder2.gotoChangeBisState = function(){
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
	
	var selectRow = $("#VerifyOrder2_datagrid").datagrid("getSelected");
	if (selectRow == "" || selectRow == undefined) {
		$.messager.alert('提示信息',"请先选择一行记录!",'info');
		return false;
	}
	
	if(selectRow.AUDIT_STATE != 'A1'){
		$.messager.alert('提示信息',"实名补登信息未审核成功!",'info');
		return false;
	}
	
	if(selectRow.BIS_STATE == 'B1'){
		$.messager.alert('提示信息',"已办理成功!",'info');
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
		var url = managerPath + "/manager/verify/VerifyManagerMgr.htm?m=query&f=verifyDetail&userId="+userId + "&ischangebis=1"+"&apptx="+apptx
		+"&certName="+encodeURIComponent(certName)+"&certId="+certId+"&sex="+sex+"&nation="+encodeURIComponent(nation)+"&exp="
		+exp+"&birthday="+birthday+"&issue="+encodeURIComponent(issue)+"&addr="+encodeURIComponent(addr);
		OpenTab("center_tabs",'业务办理',url,'icon-munich-collaboration');
	}); 
}

function changebisstate_cancel(){
	$("#SEL_BIS_STATE").get(0).selectedIndex=0;
	$('#verify_order2_bisstate').dialog('close');
}

$("#SEL_AUDIT_STATE").combobox({
	onChange: function (n,o) {
		if(n == 'A2'){
			document.getElementById("AUDIT_FAIL_DIV").style.display="";
		} else {
			document.getElementById("AUDIT_FAIL_DIV").style.display="none";
			document.getElementById("AUDIT_FAIL_OTHER_REASON_DIV").style.display="none";
		}
		$('#AUDIT_FAIL_REASON').combobox('setValue', '');
		$("#AUDIT_ERROR_OTHER_REASON").val("");
	}
});

$("#AUDIT_FAIL_REASON").combobox({
	onChange: function (n,o) {
		if(n == '其他'){
			document.getElementById("AUDIT_FAIL_OTHER_REASON_DIV").style.display="";
		} else {
			document.getElementById("AUDIT_FAIL_OTHER_REASON_DIV").style.display="none";
		}
		$("#AUDIT_ERROR_OTHER_REASON").val("");
	}
});

$("#SEL_BIS_STATE").combobox({
	onChange: function (n,o) {
		if(n == 'B2'){
			document.getElementById("BIS_FAIL_REASON_DIV").style.display="";
		} else {
			document.getElementById("BIS_FAIL_REASON_DIV").style.display="none";
			document.getElementById("BIS_ERROR_OTHER_REASON_DIV").style.display="none";
		}
		$('#BIS_FAIL_REASON').combobox('setValue', '');
		$("#BIS_ERROR_OTHER_REASON").val("");
	}
});

$("#BIS_FAIL_REASON").combobox({
	onChange: function (n,o) {
		if(n == '其它存在风险用户'){
			document.getElementById("BIS_ERROR_OTHER_REASON_DIV").style.display="";
		} else {
			document.getElementById("BIS_ERROR_OTHER_REASON_DIV").style.display="none";
		}
		$("#BIS_ERROR_OTHER_REASON").val("");
	}
});


function auditInfo_save(){
	//document.getElementById("auditSubmit").style.display='none';
	//设置href属性
	document.getElementById("auditSubmit").href = "javascript:void(0);";
	//设置disabled属性
	document.getElementById("auditSubmit").setAttribute("disabled", "disabled");
	
	//document.getElementById("auditSubmit").disabled=true;
	
	var state = $('#SEL_AUDIT_STATE').combobox('getValue');
	if(state == 'A2'){
		var reason = $('#AUDIT_FAIL_REASON').combobox('getValue');
		if(reason == ''){
			$.messager.alert('提示信息',"请选择审核不通过原因!",'info');
			return false;
		}
		if(reason == '其他'){
			if($("#AUDIT_ERROR_OTHER_REASON").val() == ''){
				$.messager.alert('提示信息',"请填写审核不通过原因!",'info');
				return false;
			}
		}
	}
	var url = managerPath + "/manager/verify/VerifyManagerMgr.json?m=execute&f=saveAuditInfo";
	var params = {userId:$("#USER_ID_HIDDEN").val(),SEL_AUDIT_STATE:$('#SEL_AUDIT_STATE').combobox('getValue'),AUDIT_FAIL_REASON:$('#AUDIT_FAIL_REASON').combobox('getValue'),AUDIT_ERROR_OTHER_REASON:$("#AUDIT_ERROR_OTHER_REASON").val()};
		
	var clientRequest = new Tcsp.ClientRequest();
	clientRequest.postJSON(url,params,function(respData){
		  if(respData != ''){
			  	var approveResult = "操作成功";
				if (respData.result.errorCode != '00000') {
					approveResult = "操作失败，原因为：" + respData.result.errorMsg;
					$.messager.alert('提示信息',approveResult,'info');
				} else {
					$('#center_tabs').tabs('close','审核详情');
					$("#VerifyOrder2_datagrid").datagrid("reload");
				}
		  }
	});
}

function changebisstate_save(){
	//document.getElementById("bisSubmit").style.display='none';
	//设置href属性
	document.getElementById("bisSubmit").href = "javascript:void(0);";
	//设置disabled属性
	document.getElementById("bisSubmit").setAttribute("disabled", "disabled");
	
	//document.getElementById("bisSubmit").disabled=true;
	var state = $('#SEL_BIS_STATE').combobox('getValue');
	if(state == 'B2'){
		var reason = $('#BIS_FAIL_REASON').combobox('getValue');
		if(reason == ''){
			$.messager.alert('提示信息',"请选择业务办理失败原因!",'info');
			return false;
		}
		if(reason == '其它存在风险用户'){
			if($("#BIS_ERROR_OTHER_REASON").val() == ''){
				$.messager.alert('提示信息',"请填写业务办理失败原因!",'info');
				return false;
			}
		}
	}
	var url = managerPath + "/manager/verify/VerifyManagerMgr.json?m=execute&f=saveBisStateInfo";
	var params = {userId:$("#USER_ID_HIDDEN").val(),SEL_BIS_STATE:$('#SEL_BIS_STATE').combobox('getValue'),BIS_FAIL_REASON:$('#BIS_FAIL_REASON').combobox('getValue'),BIS_ERROR_OTHER_REASON:$("#BIS_ERROR_OTHER_REASON").val()};
		
	var clientRequest = new Tcsp.ClientRequest();
	clientRequest.postJSON(url,params,function(respData){
		  if(respData != ''){
			  	var approveResult = "操作成功";
				if (respData.result.errorCode != '00000') {
					approveResult = "操作失败，原因为：" + respData.result.errorMsg;
				} else {
					$('#center_tabs').tabs('close','业务办理');
					$("#VerifyOrder2_datagrid").datagrid("reload");
				}
		  }
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
$('#VerifyOrder2_datagrid').datagrid({
	loadFilter: function(data){
		if (data.result.dataSet){
			return data.result.dataSet;
		} else {
			return data;
		}
	},
	onClickRow: function(rowIndex, rowData){
		//VerifyOrder2.gotoAudit();
	},
	onBeforeLoad: function(param){
		VerifyOrder2.setDatagridQueryParams();
	}
});
