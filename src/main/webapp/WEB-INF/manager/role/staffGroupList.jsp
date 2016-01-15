<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>


<input type="hidden" id="staffGroup_id" value="${result.dataSet.group.id}"/>
<div style="clear:both;line-height:28px;font-size:14px;padding:3px 15px;background-color: #E0ECFF;color:#666;margin-top:5px;margin-botton:5px;">管理员组【${result.dataSet.group.groupName}】中的管理员</div>
<div class="chaxun_cont">
 <ul>
  <li><span>员工工号：</span><input id="regName" name="regName" class="easyui-textbox" ></li>
  <li><span>姓名：</span><input id="name" name="name" class="easyui-textbox" ></li>
  <span style="display:none"><input id="staffGroup_city" name="staffGroup_city" class="easyui-textbox" value="${result.dataSet.group.cityId}"/></span>
 </ul>
 <div class="butt_cont"><a href="#" id="staffGroup_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a></div>
</div>

<div style="margin:10px;">
	<table id="staffAdd_datagrid"></table>
</div>
<div style="clear:both;line-height:28px;font-size:14px;padding:3px 15px;background-color: #E0ECFF;color:#666;margin-top:5px;margin-botton:5px;">未加入管理员组的管理员</div>
<div class="chaxun_cont">
 <ul>
  <li><span>员工工号：</span><input id="nRegName" name="nRegName" class="easyui-textbox" ></li>
  <li><span>姓名：</span><input id="nName" name="nName" class="easyui-textbox" ></li>
  <span style="display:none"><input id="nStaffGroup_city" name="nStaffGroup_city" class="easyui-textbox" value="${result.dataSet.group.cityId}"/></span>
 </ul>
 <div class="butt_cont"><a href="#" id="staffGroup_add_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a></div>
</div>
<div style="margin:10px;">
	<table id="staffNotAdd_datagrid">
	</table>
</div>
<script type="text/javascript">
var staffGroup_util=staffGroup_util||{} ;
staffGroup_util.areaCode = [
   						{ "value": "000", "text": "山东" },
						{ "value": "001", "text": "淄博" },
						{ "value": "002", "text": "滨州" },
						{ "value": "003", "text": "临沂" },
						{ "value": "004", "text": "日照" },
						{ "value": "005", "text": "潍坊" },
						{ "value": "006", "text": "东营" },
						{ "value": "007", "text": "枣庄" },
						{ "value": "008", "text": "济宁" },
						{ "value": "009", "text": "菏泽" },
						{ "value": "010", "text": "莱芜" },
						{ "value": "011", "text": "烟台" },
						{ "value": "012", "text": "青岛" },
						{ "value": "013", "text": "济南" },
						{ "value": "014", "text": "泰安" },
						{ "value": "015", "text": "德州" },
						{ "value": "016", "text": "聊城" },
						{ "value": "017", "text": "威海" }
                      ];
staffGroup_util.unitformatter = function(value, rowData, rowIndex) {
   for (var i = 0; i <staffGroup_util.areaCode.length; i++) {
       if (staffGroup_util.areaCode[i].value == value) {
           return staffGroup_util.areaCode[i].text;
       }
   }
}

staffGroup_util.formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};
staffGroup_util.getRowIndex=function(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
staffGroup_util.editrow=function(target){
	$('#staffAdd_datagrid').datagrid('selectRow',staffGroup_util.getRowIndex(target));
	$('#staffAdd_datagrid').datagrid('beginEdit', staffGroup_util.getRowIndex(target));
}

//将管理员移除管理员分组
staffGroup_util.deleterow=function(target){
	var choosen_rows = staffGroup_util.grid.datagrid('getSelections').length;
	if(choosen_rows==0){
		var dlg=$.messager.alert({title: "操作提示", msg: "请选择您要移出分组的管理员！"});
		setTimeout(function(){
		    dlg.window('close');
		}, 5000);
		return;
	}
	//$('#staffAdd_datagrid').datagrid('deleteRow', staffGroup_util.getRowIndex(target));
	$.messager.confirm('操作提示','确定将管理员移出分组吗?',function(confirm_status){
		if (confirm_status){
			var arr = staffGroup_util.getSelectedArr('staffAdd_datagrid');
			var clientRequest = new Tcsp.ClientRequest();
			var params = new Object();
			params.ids=arr.join(',');
			params.groupId='${result.dataSet.group.id}';
			var url = managerPath + "/manager/sysman/StaffGroupMgr.json?m=execute&f=staffRemove";
			clientRequest.postJSON(url,params,function(respData){
				  if(respData.result){
					  if(respData.result.success&&respData.result.errorCode=='00000'){
						  $('#staffNotAdd_datagrid').datagrid('reload');
						  $('#staffAdd_datagrid').datagrid('reload').datagrid('clearSelections');
					  }else{
						  $.messager.alert("操作提示",respData.result.errorMsg);
					  }
				  }
			});
		}
	});
}
staffGroup_util.getSelectedArr=function(dom) {
	var ids = [];
	var rows = $('#'+dom).datagrid('getSelections');
    for (var i = 0; i < rows.length; i++) {
        ids.push(rows[i].addId);
    }
    return ids;
}

staffGroup_util.getSelectedSerial=function(dom) {
    var ids = [];
    var rows = $('#staffNotAdd_datagrid').datagrid('getSelections');
    for (var i = 0; i < rows.length; i++) {
        ids.push(rows[i].staffId);
    }
    return ids;
}
staffGroup_util.getSelectedSerial1=function(dom) {
    var regNames = [];
    var rows = $('#staffNotAdd_datagrid').datagrid('getSelections');
    for (var i = 0; i < rows.length; i++) {
    	regNames.push(rows[i].regName);
    }
    return regNames;
}

staffGroup_util.cancelrow=function(target){
	$('#staffAdd_datagrid').datagrid('cancelEdit', staffGroup_util.getRowIndex(target));
}
staffGroup_util.updateActions=function(index){
	$('#staffAdd_datagrid').datagrid('updateRow',{
		index: index,
		row:{}
	});
}
staffGroup_util.getQueryParam=function(){
	var data={
		regName:$("#regName").val(),
		name:$("#name").val()
	}
	return data;
}
staffGroup_util.getQueryParamForAdd=function(){
	var data={
		regName:$("#nRegName").val(),
		name:$("#nName").val()
	}
	return data;
}
staffGroup_util.templateData=eval('[${result.dataSet.json}]');
staffGroup_util.grid=$('#staffAdd_datagrid').datagrid({  
    iconCls:'icon-edit',
    height: 'auto', 
    width:1100,
    nowrap: false,  
    striped: true,  
    border: true,  
    collapsible:false,//是否可折叠的  
    fitColumns: true,//自动大小  
    url: managerPath+'/manager/sysman/StaffGroupMgr.json?m=query&f=staffQuery&groupId=${result.dataSet.group.id}&areaCode=${result.dataSet.group.cityId}',
    loadMsg:'数据加载中...',
    remoteSort:false,   
    idField:'addId',  
    singleSelect:false,//是否单选  
    pagination:true,//分页控件  
    rownumbers:true,//行号   
    frozenColumns:[[]],
    columns: [[
    		{field: 'ck',checkbox:true},
    		{title: '关联id',field: 'addId',width:120,sortable: false,align:'left',hidden:true},
    		{title: '员工工号',field: 'regName',width:100,sortable: false,align:'left'},
    		{title: '姓名',field: 'name',width:100,sortable: false,align:'left'},
    		{title: '地市',field: 'areaCode',width:60,sortable: false,align:'center',
    			formatter:staffGroup_util.unitformatter,
				editor: { 
    				type: 'combobox', 
    				options: { 
    					data: staffGroup_util.areaCode, 
    					valueField: "value", 
    					textField: "text" 
    				} 
    			}
    		},
    		{title: '联系电话',field: 'mobilePhone',width:60,sortable: false,align:'center'},
    		{title: '更新时间',field: 'updateTime',width:100,sortable: false,align:'center'}
    	]],
    	onBeforeEdit:function(index,row){
			row.editing = true;
			staffGroup_util.updateActions(index);
		},
		onAfterEdit:function(index,row){
			row.editing = false;
			staffGroup_util.updateActions(index);
		},
		onCancelEdit:function(index,row){
			row.editing = false;
			staffGroup_util.updateActions(index);
		},
    loadFilter: function(data){
    		if (data.result){
    			return data.result.dataSet;
    		} else {
    			return data;
    		}
    },
    toolbar: [{
        text: '移出管理员组',
        iconCls: 'icon-remove',
        handler: staffGroup_util.deleterow
    }],
    pageList: [10,20,30],
    pageSize: 10

});


	$("#staffGroup_search_btn").click(function(){
		$('#staffAdd_datagrid').datagrid('reload',staffGroup_util.getQueryParam());
		$('#staffAdd_datagrid').datagrid('clearSelections');
	});
	$("#staffGroup_add_btn").click(function(){
		$('#staffNotAdd_datagrid').datagrid('reload',staffGroup_util.getQueryParamForAdd());
		$('#staffNotAdd_datagrid').datagrid('clearSelections');
	});


//将管理员加入管理员组
staffGroup_util.add_to_group=function(){
	var choosen_rows = staffGroup_util.addgrid.datagrid('getSelections').length;
	if(choosen_rows==0){
		var dlg=$.messager.alert({title: "操作提示", msg: "请选择您要加入分组的管理员！"});
		setTimeout(function(){
		    dlg.window('close');
		}, 5000);
		return;
	}
	var row = $('#staffNotAdd_datagrid').datagrid('getSelected');
	$.messager.confirm('操作提示','确定将所选管理员加入分组吗?',function(confirm_status){
		if (confirm_status){
			var arr = staffGroup_util.getSelectedSerial();
			var arr1 = staffGroup_util.getSelectedSerial1();
			var clientRequest = new Tcsp.ClientRequest();
			var params = new Object();
			params.ids=arr.join(',');
			params.regNames=arr1.join(',');
			params.groupId='${result.dataSet.group.id}';
			var url = managerPath + "/manager/sysman/StaffGroupMgr.json?m=execute&f=staffSave";
			clientRequest.postJSON(url,params,function(respData){
				  if(respData.result){
					  if(respData.result.success&&respData.result.errorCode=='00000'){
						  //$('#staffAdd_datagrid').datagrid('deleteRow', staffGroup_util.datagrid('getSelected'));
						  $('#staffAdd_datagrid').datagrid('reload');
						  $('#staffNotAdd_datagrid').datagrid('reload').datagrid('clearSelections');
					  }else{
						  $.messager.alert("操作提示",respData.result.errorMsg);
					  }
				  }
			});
		}
	});
}

staffGroup_util.addgrid=$('#staffNotAdd_datagrid').datagrid({  
    iconCls:'icon-edit',
    height: 'auto', 
    width:1100,
    nowrap: false,  
    striped: true,  
    border: true,  
    collapsible:false,//是否可折叠的  
    fitColumns: true,//自动大小  
    url: managerPath+'/manager/sysman/StaffGroupMgr.json?m=query&f=staffNotAddQuery&groupId=${result.dataSet.group.id}&areaCode=${result.dataSet.group.cityId}',
    loadMsg:'数据加载中...',
    remoteSort:false,   
    idField:'staffId',  
    singleSelect:false,//是否单选  
    pagination:true,//分页控件  
    rownumbers:true,//行号   
    frozenColumns:[[]],
    columns: [[
			{field: 'ck',checkbox:true},
			{title: '用户id',field: 'staffId',width:120,sortable: false,align:'left',hidden:true},
    		{title: '员工工号',field: 'regName',width:100,sortable: false,align:'left'},
    		{title: '姓名',field: 'name',width:100,sortable: false,align:'left'},
    		{title: '地市',field: 'areaCode',width:60,sortable: false,align:'center',
    			formatter:staffGroup_util.unitformatter,
				editor: { 
    				type: 'combobox', 
    				options: { 
    					data: staffGroup_util.areaCode, 
    					valueField: "value", 
    					textField: "text" 
    				} 
    			}
    		},
    		{title: '联系电话',field: 'mobilePhone',width:60,sortable: false,align:'center'},
    		{title: '更新时间',field: 'updateTime',width:100,sortable: false,align:'center'}
    	]],
    	onBeforeEdit:function(index,row){
			row.editing = true;
			staffGroup_util.updateActions(index);
		},
		onAfterEdit:function(index,row){
			row.editing = false;
			staffGroup_util.updateActions(index);
		},
		onCancelEdit:function(index,row){
			row.editing = false;
			staffGroup_util.updateActions(index);
		},
   	loadFilter: function(data){
   		if (data.result){
   			return data.result.dataSet;
   		} else {
   			return data;
   		}
   	},
    toolbar: [{
        text: '将所选管理员加入管理员组',
        iconCls: 'icon-add',
        handler: staffGroup_util.add_to_group
    }],
    pageList: [10,20,30],
    pageSize: 10

});
</script>
