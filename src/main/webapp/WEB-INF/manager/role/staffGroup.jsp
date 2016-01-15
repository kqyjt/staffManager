<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>
<input id = "isProvManager" value="${result.dataSet.isProManager }" type="hidden"/>
<input id = "staffCityCode" value="${result.dataSet.staffCityCode }" type="hidden"/>
<input id = "staffCityName" value="${result.dataSet.staffCityName }" type="hidden"/>

<div class="chaxun_cont">
 <ul>
  <li><span>管理员组ID：</span><input id="staffGroupId"  name="staffGroupId" class="easyui-textbox" value=''></li>
  <li><span>分组名称：</span><input id="staffGroupName"  name="staffGroupName" class="easyui-textbox" value=''></li>
  <li><span>地市：</span>
   <select class="easyui-combobox" id="staffGroupArea" name="staffGroupArea" style="width:80px;">
	<c:forEach var="entry" items="${result.dataSet.area}">
		<c:if test="${entry.key=='000'}"><option value="${entry.key}" selected="selected">${entry.value}</option></c:if>
		<c:if test="${entry.key!='000'}"><option value="${entry.key}">${entry.value}</option></c:if>
	</c:forEach>
	<c:forEach var="entry" items="${result.dataSet.area}">
		<c:if test="${entry.key=='000'}"><option value="" selected="selected">全部</option></c:if>
	</c:forEach>	
   </select></li>
 </ul>
 <div class="butt_cont"><a href="#" id="staffGroupSearch" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a></div>
</div>

<div style="margin:10px;">
	<table id="staff_group_datagrid"></table>
</div>
<script type="text/javascript">
var staff_group_util=staff_group_util||{} ;
staff_group_util.isProManager = $("#isProvManager").val();
if(staff_group_util.isProManager == "yes"){
	staff_group_util.areaCode = [
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
} else {
	var code = $('#staffCityCode').val();
	var name = $('#staffCityName').val();
	staff_group_util.areaCode = [
		{ "value": code, "text": name }
    ];
}
staff_group_util.unitformatter = function(value, rowData, rowIndex) {
   for (var i = 0; i <staff_group_util.areaCode.length; i++) {
       if (staff_group_util.areaCode[i].value == value) {
           return staff_group_util.areaCode[i].text;
       }
   }
}

staff_group_util.formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};
staff_group_util.getRowIndex=function(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}

//编辑管理员组信息
staff_group_util.editrow=function(target){
	$('#staff_group_datagrid').datagrid('selectRow',staff_group_util.getRowIndex(target));
	$('#staff_group_datagrid').datagrid('beginEdit', staff_group_util.getRowIndex(target));
}

//删除管理员组
staff_group_util.deleterow=function(target){
	var choosen_rows = staff_group_util.grid.datagrid('getSelections').length;
	if(choosen_rows==0){
		var dlg=$.messager.alert({title: "操作提示", msg: "请选择您要删除的记录！"});
		setTimeout(function(){
		    dlg.window('close');
		}, 5000);
		return;
	}
	
	$.messager.confirm('操作提示','确定后数据不可恢复,确认删除吗?',function(confirm_status){
		if (confirm_status){
			var row = $('#staff_group_datagrid').datagrid('getSelected');
			var clientRequest = new Tcsp.ClientRequest();
			var params = new Object();
			params.id = row.id;
			var url = managerPath + "/manager/sysman/StaffGroupMgr.json?m=execute&f=staffGroupRemove";
			clientRequest.postJSON(url,params,function(respData){
				  if(respData.result){
					  if(respData.result.success&&respData.result.errorCode=='00000'){
						  $('#staff_group_datagrid').datagrid('reload');
						  $('#staff_group_datagrid').datagrid('clearSelections');
					  }else{
						  $.messager.alert("操作提示",respData.result.errorMsg);
					  }
				  }
			});
		}
	});
}

//保存管理员组（新增和更新）
staff_group_util.saverow=function(target){
	var index=staff_group_util.getRowIndex(target);
	$('#staff_group_datagrid').datagrid('endEdit',index);
	$('#staff_group_datagrid').datagrid('selectRow',index);
	var row = $('#staff_group_datagrid').datagrid('getSelected');
	if (row){
		var clientRequest = new Tcsp.ClientRequest();
		var params = new Object();
		params.id = row.id;
		params.groupName = row.groupName;
		params.groupCity = row.groupCity;
		params.remark = row.remark;
		var action='Save';
		if(typeof(params.groupName)=='undefined'||typeof(params.groupCity)=='undefined'||params.groupName==''||params.groupCity==''){
			$.messager.alert("操作提示",'必填字值不能为空');
			return;
		}
		if(params.id){
			action='Update';
		}else{
			action='Save';
		}
		var url = managerPath + "/manager/sysman/StaffGroupMgr.json?m=execute&f=staffGroup"+action;
		clientRequest.postJSON(url,params,function(respData){
			  if(respData.result){
				  if(respData.result.success&&respData.result.errorCode=='00000'){
					  $('#staff_group_datagrid').datagrid('reload');
					  $('#staff_group_datagrid').datagrid('clearSelections');
				  }else{
					  $.messager.alert("操作提示",respData.result.errorMsg);
				  }
			  }
		});
	}
}
staff_group_util.cancelrow=function(target){
	$('#staff_group_datagrid').datagrid('cancelEdit', staff_group_util.getRowIndex(target));
}

//新增管理员组
staff_group_util.insert=function(){
	$('#staff_group_datagrid').datagrid('insertRow', {
		index: 0,
		row:{
		}
	});
	$('#staff_group_datagrid').datagrid('selectRow',0);
	$('#staff_group_datagrid').datagrid('beginEdit',0);
}
staff_group_util.updateActions=function(index){
	$('#staff_group_datagrid').datagrid('updateRow',{
		index: index,
		row:{}
	});
}
staff_group_util.getQueryParam=function(){
	var data={
		groupId:$("#staffGroupId").val(),			
		groupName:$("#staffGroupName").val(),
		groupCity:$('#staffGroupArea').combobox('getValue')
	}
	return data;
}
staff_group_util.templateData=eval('[${result.dataSet.json}]');
staff_group_util.grid=$('#staff_group_datagrid').datagrid({  
    iconCls:'icon-edit',
    width:1100,
    height: 'auto',  
    nowrap: false,  
    striped: true,  
    border: true,  
    collapsible:false,//是否可折叠的  
    fitColumns: true,//自动大小  
    url: managerPath+'/manager/sysman/StaffGroupMgr.json?m=query&f=staffGroupQuery',
    loadMsg:'数据加载中...',
    remoteSort:false,   
    idField:'id',  
    singleSelect:true,//是否单选  
    pagination:true,//分页控件  
    rownumbers:true,//行号   
    frozenColumns:[[]],
    columns: [[
    		{field: 'ck',checkbox:true},
    		{title: '分组ID',field: 'id',width:100,sortable:false,align:'center'},
    		{title: '分组名称',field: 'groupName',width:150,sortable: false,align:'left',editor:{type: 'textbox', options: { required: true ,missingMessage:'请填写分组名称'}}},
    		{title: '地市',field: 'groupCity',width:100,sortable: false,align:'center',
				formatter:staff_group_util.unitformatter,
				editor: { 
    				type: 'combobox', 
    				options: { 
		    					data: staff_group_util.areaCode, 
		    					valueField: "value", 
		    					textField: "text" 
    						} 
    					},
    					
    		},
    		{title: '备注',field: 'remark',width:150,sortable: false,align:'left',editor:'text'},
    		{title: '创建时间',field: 'createTime',width:150,sortable: false,align:'left'},
    		{title: '管理员组操作',field:'管理员组操作',sortable: false,align:'center',width:220,
                formatter:function(value,row,index){
                	if (row.editing){
						var s = '<a href="#" onclick="staff_group_util.saverow(this)">保存</a> ';
						var c = '<a href="#" onclick="staff_group_util.cancelrow(this)">取消</a>';
						return s+c;
					} else {
						var e = '<a href="#" onclick="staff_group_util.editrow(this)">编辑</a> ';
						var d = '<a href="#" onclick="staff_group_util.showStaff(\''+row.id+'\',\''+row.groupName+'\')" style="margin-left:20px;">查看管理员列表</a>';
						return e+d;
					}
               }
            }
    	]],
    	onBeforeEdit:function(index,row){
			row.editing = true;
			staff_group_util.updateActions(index);
		},
		onAfterEdit:function(index,row){
			row.editing = false;
			staff_group_util.updateActions(index);
		},
		onCancelEdit:function(index,row){
			row.editing = false;
			staff_group_util.updateActions(index);
		},
    loadFilter: function(data){
    		if (data.result){
    			return data.result.dataSet;
    		} else {
    			return data;
    		}
    },
    toolbar: [{
        text: '新增',
        iconCls: 'icon-add',
        handler: staff_group_util.insert
    },'-',{
        text: '删除',
        iconCls: 'icon-remove',
        handler: staff_group_util.deleterow
    }],
    pageList: [10,20,30],
    pageSize: 10

});
staff_group_util.showStaff=function(v,n){
	if(v&&v!='undefined'){
		if($('#center_tabs').tabs('exists','查看分组管理员')){
			$('#center_tabs').tabs('close','查看分组管理员');
		}
		OpenTab('center_tabs','查看分组管理员',managerPath+'/manager/sysman/StaffGroupMgr.htm?m=query&f=staffGroupListQuery&id='+v,'icon-munich-collaboration');
	}else{
		var dlg=$.messager.alert({title: "操作提示", msg: "暂不能显示其属性列表,请保存模板"});
		setTimeout(function(){
		    dlg.window('close');
		}, 5000);
	}
}

	$("#staffGroupSearch").click(function(){
		$('#staff_group_datagrid').datagrid('reload',staff_group_util.getQueryParam());
		$('#staff_group_datagrid').datagrid('clearSelections');
	});


</script>
