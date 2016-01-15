<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>

<div class="chaxun_cont">

 <ul>
  
  <li><span>角色标识：</span><input  type="text" id="role_right_roleId" name="roleId" value="${param.roleId }" class="easyui-textbox" ></li>
  <li><span>资源类型：</span><input  type="text" id="role_right_resType" name="resType" value="${param.resType }" class="easyui-textbox" ></li>
  <li><span>资源标识：</span><input  type="text" id="role_right_resId" name="resId" value="${param.resId }" class="easyui-textbox" ></li>
  <li><span>更新员工：</span><input  type="text" id="role_right_updateStaffId" name="updateStaffId" value="${param.updateStaffId }" class="easyui-textbox" ></li>
 </ul>
 
 <div class="clear"></div>
 <div class="butt_cont"><a href="#" onclick="ROLE_RIGHT_PUBLIC.searchRoleRight()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a></div>
</div>

<div style="margin:10px;">
	<table id="qxgl-datagrid"></table>

</div>

<script type="text/javascript">
	var ROLE_RIGHT_PUBLIC = ROLE_RIGHT_PUBLIC || {};
	ROLE_RIGHT_PUBLIC.queryUrl = managerPath + "/manager/system/TMRoleRightMgr.json?m=query&f=roleRightQuery";
	ROLE_RIGHT_PUBLIC.params = new Object();


ROLE_RIGHT_PUBLIC.getpagedata = function()
{
	var clientRequest = new Tcsp.ClientRequest();
	
	clientRequest.postJSON(ROLE_RIGHT_PUBLIC.queryUrl,ROLE_RIGHT_PUBLIC.params,function(data) {
		//处理数据，重画数据区域
		$("#qxgl-datagrid").datagrid("loadData", {total : data.result.dataSet.records, rows : data.result.dataSet.list});
		
		$("#qxgl-datagrid").datagrid("getPager").pagination({
			onSelectPage:function(pageNumber,pageSize){
				ROLE_RIGHT_PUBLIC.params.page = (pageNumber - 1) * pageSize;
				ROLE_RIGHT_PUBLIC.params.size = pageSize;
	     		
	     		 /*  clientRequest.postJSON(ROLE_RIGHT_PUBLIC.queryUrl,ROLE_RIGHT_PUBLIC.params,function(data){
				$("#qxgl-datagrid").datagrid("loadData", {total : data.result.dataSet.records, rows : data.result.dataSet.list}); 
	   		    });  */
	   		    $.getJSON(ROLE_RIGHT_PUBLIC.queryUrl,ROLE_RIGHT_PUBLIC.params, function (data){
		         	$("#qxgl-datagrid").datagrid('loadData', {total : data.result.dataSet.records, rows : data.result.dataSet.list});
				}
				)
       	}  
	});
	},function(errresponse) {
		alter("没有数据");
	});
	
}

ROLE_RIGHT_PUBLIC.searchRoleRight = function(){
	
	ROLE_RIGHT_PUBLIC.params.roleId = $("#role_right_roleId").val();
	ROLE_RIGHT_PUBLIC.params.resType = $("#role_right_resType").val();
	ROLE_RIGHT_PUBLIC.params.resId = $("#role_right_resId").val();
	ROLE_RIGHT_PUBLIC.params.updateStaffId = $("#role_right_updateStaffId").val();
	
	var clientRequest = new Tcsp.ClientRequest();
	clientRequest.postJSON(ROLE_RIGHT_PUBLIC.queryUrl,ROLE_RIGHT_PUBLIC.params,function(data){
		$("#qxgl-datagrid").datagrid("loadData", {total : data.result.dataSet.records, rows : data.result.dataSet.list});
	});
}


ROLE_RIGHT_PUBLIC.formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};
ROLE_RIGHT_PUBLIC.getRowIndex=function(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
ROLE_RIGHT_PUBLIC.editrow=function(target){
	$('#qxgl-datagrid').datagrid('selectRow',ROLE_RIGHT_PUBLIC.getRowIndex(target));
	$('#qxgl-datagrid').datagrid('beginEdit', ROLE_RIGHT_PUBLIC.getRowIndex(target));
}

ROLE_RIGHT_PUBLIC.insert=function(){

	var row = $('#qxgl-datagrid').datagrid('getSelected');
	var index=$('#qxgl-datagrid').datagrid('getData').rows.length;
	if (row){
		if(row.editing){
			$.messager.alert('操作提示','请先保存后,再新增权限');
			return;
		}
		var index = $('#qxgl-datagrid').datagrid('getRowIndex', row);
	} else {
		index = 0;
	}
	$('#qxgl-datagrid').datagrid('insertRow', {
		index: 0,
		row:{
			updateTime:ROLE_RIGHT_PUBLIC.formatterDate(new Date())
		}
	});
	$('#qxgl-datagrid').datagrid('selectRow',0);
	$('#qxgl-datagrid').datagrid('beginEdit',0);
}

ROLE_RIGHT_PUBLIC.deleterow = function(target){

    //var choosen_rows = ROLE_RIGHT_PUBLIC.grid.datagrid('getSelections').length;
    
	//var row = $('#qxgl-datagrid').datagrid('getSelected');
	
    var choosen_rows = $('#qxgl-datagrid').datagrid('getSelections').length;
	if(choosen_rows==0){
		var dlg=$.messager.alert({title: "操作提示", msg: "请选择您要删除的记录！"});
		setTimeout(function(){
		    dlg.window('close');
		}, 5000);
		return;
	}
	//$('#cppz-datagrid').datagrid('deleteRow', ROLE_RIGHT_PUBLIC.getRowIndex(target));
	$.messager.confirm('操作提示','确定后数据不可恢复,确认删除吗?',function(confirm_status){
		if (confirm_status){
			var row = $('#qxgl-datagrid').datagrid('getSelected');
			var clientRequest = new Tcsp.ClientRequest();
			var params = new Object();
			params.id = row.id;
			var url = managerPath + "/manager/system/TMRoleRightMgr.json?m=execute&f=roleRightRemove";
			clientRequest.postJSON(url,params,function(respData){
				  if(respData.result){
					  if(respData.result.success&&respData.result.errorCode=='00000'){
						  //$('#cppz-datagrid').datagrid('deleteRow', ROLE_RIGHT_PUBLIC.datagrid('getSelected'));
						  $('#qxgl-datagrid').datagrid('reload');
					  }else{
						  $.messager.alert("操作提示",respData.result.errorMsg);
					  }
				  }
			});
		}
	});

}

ROLE_RIGHT_PUBLIC.saverow=function(target){
	var index=ROLE_RIGHT_PUBLIC.getRowIndex(target);
	$('#qxgl-datagrid').datagrid('endEdit',index);
	$('#qxgl-datagrid').datagrid('selectRow',index);
	var row = $('#qxgl-datagrid').datagrid('getSelected');
	if (row){
		var clientRequest = new Tcsp.ClientRequest();
		var params = new Object();
		
		params.id = row.id;
		params.RoleId =  row.roleId;
		params.ResType =  row.resType;
		params.ResId =  row.resId;
		params.UpdateStaffId =  row.updateStaffId;
		params.UpdateTime =  row.updateTime;
		
		var action='Save';
		if(params.id){
			action='Update';
		}else{
			action='Save';
		}
		var url = managerPath + "/manager/system/TMRoleRightMgr.json?m=execute&f=roleRight"+action;
		
		clientRequest.postJSON(url,params,function(respData){
			  if(respData.result){
				  if(respData.result.success&&respData.result.errorCode=='00000'){
					  $('#qxgl-datagrid').datagrid('reload');
				  }else{
					  $.messager.alert("操作提示",respData.result.errorMsg);
				  }
			  }
		});
	}
}

ROLE_RIGHT_PUBLIC.cancelrow=function(target){
	$('#qxgl-datagrid').datagrid('cancelEdit', ROLE_RIGHT_PUBLIC.getRowIndex(target));
}

ROLE_RIGHT_PUBLIC.updateActions=function(index){
	$('#qxgl-datagrid').datagrid('updateRow',{
		index: index,
		row:{}
	});
}

ROLE_RIGHT_PUBLIC.grid=$('#qxgl-datagrid').datagrid({  
    iconCls:'icon-edit',
    //width:'auto',
    height: 'auto',  
    nowrap: false,  
    striped: true,  
    border: true,  
    collapsible:false,//是否可折叠的  
    fitColumns: false,//自动大小  
    url: managerPath+'/manager/system/TMRoleRightMgr.json?m=query&f=roleRightQuery',
    //sortName: 'ID',  
    //sortOrder: 'asc', 
    loadMsg:'数据加载中...',
    remoteSort:false,   
    idField:'id',  
    singleSelect:true,//只允许选择一行  
    pagination:true,//分页控件  
    rownumbers:true,//显示行号  
    frozenColumns:[[]],
    columns: [[
    		{field: 'ck',checkbox:true},
    		{title: '唯一标识',field: 'id',width:80,sortable:false,align:'center'},
    		{title: '角色标识',field: 'roleId',width:80,sortable: true,align:'left',editor:'text'},
    		{title: '资源类型', field: 'resType', width:100,sortable: false,align:'center',editor:'text'},
    		{title: '资源标识',field: 'resId',width:100,sortable:false,align:'center',editor:'text'},
        	{title: '更新员工', field: 'updateStaffId', width:120,sortable: false,align:'center',editor:'text'},
        	{title: '更新时间', field: 'updateTime', width:80,sortable: false,align:'center',editor:'datetimebox'},
        	{title: '操作',field:'查看',sortable: false,align:'center',width:220,
                formatter:function(value,row,index){
                	
                	if (row.editing){
						var s = '<a href="#" onclick="ROLE_RIGHT_PUBLIC.saverow(this)">保存</a> ';
						var c = '<a href="#" onclick="ROLE_RIGHT_PUBLIC.cancelrow(this)">取消</a>';
						return s+c;
					} else {
						var e = '<a href="#" onclick="ROLE_RIGHT_PUBLIC.editrow(this)">编辑</a> ';
						var d = '<a href="#" onclick="ROLE_RIGHT_PUBLIC.deleterow(this)">删除</a>';d='';
						//return e+d+a;
						return e+d;
					}
               }
            }
    	]],
    	onBeforeEdit:function(index,row){
			row.editing = true;
			ROLE_RIGHT_PUBLIC.updateActions(index);
		},
		onAfterEdit:function(index,row){
			row.editing = false;
			ROLE_RIGHT_PUBLIC.updateActions(index);
		},
		onCancelEdit:function(index,row){
			row.editing = false;
			ROLE_RIGHT_PUBLIC.updateActions(index);
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
        handler: ROLE_RIGHT_PUBLIC.insert
    },'-',{
        text: '删除',
        iconCls: 'icon-remove',
        handler: ROLE_RIGHT_PUBLIC.deleterow
    }],
    pageNumber: 1,
    pageList: [10,20,30,40,50],
    pageSize: 10

});

	

	ROLE_RIGHT_PUBLIC.getpagedata();
	

</script>

