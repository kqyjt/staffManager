<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>

<div class="chaxun_cont">

 <ul>
  
  <li><span>角色名称：</span><input  type="text" id="role_name" name="name" value="${param.name }" class="easyui-textbox" ></li>
  <li><span>归属部门：</span><input  type="text" id="role_departCode" name="departCode" value="${param.departCode }" class="easyui-textbox" ></li>
  <li><span>角色级别：</span><input  type="text" id="role_lvlId" name="lvlId" value="${param.lvlId }" class="easyui-textbox" ></li>
  <li><span>父级角色：</span><input  type="text" id="role_parentId" name="parentId" value="${param.parentId }" class="easyui-textbox" ></li>
 </ul>
 
 <div class="clear"></div>
 <div class="butt_cont"><a href="#" onclick="ROLES_PUBLIC.searchRole()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a></div>
</div>

<div style="margin:10px;">
	<table id="jsgl-datagrid"></table>

</div>

<script type="text/javascript">
	var ROLES_PUBLIC = ROLES_PUBLIC || {};
	ROLES_PUBLIC.queryUrl = managerPath + "/manager/system/TMRolesMgr.json?m=query&f=roleQuery";
	ROLES_PUBLIC.params = new Object();


ROLES_PUBLIC.getpagedata = function()
{
	var clientRequest = new Tcsp.ClientRequest();
	
	clientRequest.postJSON(ROLES_PUBLIC.queryUrl,ROLES_PUBLIC.params,function(data) {
		//处理数据，重画数据区域
		$("#jsgl-datagrid").datagrid("loadData", {total : data.result.dataSet.records, rows : data.result.dataSet.list});
		
		$("#jsgl-datagrid").datagrid("getPager").pagination({
			onSelectPage:function(pageNumber,pageSize){
				ROLES_PUBLIC.params.page = (pageNumber - 1) * pageSize;
				ROLES_PUBLIC.params.size = pageSize;
	     		
	     		 /*  clientRequest.postJSON(ROLES_PUBLIC.queryUrl,ROLES_PUBLIC.params,function(data){
				$("#jsgl-datagrid").datagrid("loadData", {total : data.result.dataSet.records, rows : data.result.dataSet.list}); 
	   		    });  */
	   		    $.getJSON(ROLES_PUBLIC.queryUrl,ROLES_PUBLIC.params, function (data){
		         	$("#jsgl-datagrid").datagrid('loadData', {total : data.result.dataSet.records, rows : data.result.dataSet.list});
				}
				)
       	}  
	});
	},function(errresponse) {
		alter("没有数据");
	});
	
}

ROLES_PUBLIC.searchRole = function(){
	
	ROLES_PUBLIC.params.name = $("#role_name").val();
	ROLES_PUBLIC.params.departCode = $("#role_departCode").val();
	ROLES_PUBLIC.params.lvlId = $("#role_lvlId").val();
	ROLES_PUBLIC.params.parentId = $("#role_parentId").val();
	
	var clientRequest = new Tcsp.ClientRequest();
	clientRequest.postJSON(ROLES_PUBLIC.queryUrl,ROLES_PUBLIC.params,function(data){
		$("#jsgl-datagrid").datagrid("loadData", {total : data.result.dataSet.records, rows : data.result.dataSet.list});
	});
}


ROLES_PUBLIC.formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};
ROLES_PUBLIC.getRowIndex=function(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
ROLES_PUBLIC.editrow=function(target){
	$('#jsgl-datagrid').datagrid('selectRow',ROLES_PUBLIC.getRowIndex(target));
	$('#jsgl-datagrid').datagrid('beginEdit', ROLES_PUBLIC.getRowIndex(target));
}

ROLES_PUBLIC.insert=function(){
	var row = $('#jsgl-datagrid').datagrid('getSelected');
	var index=$('#jsgl-datagrid').datagrid('getData').rows.length;
	if (row){
		if(row.editing){
			$.messager.alert('操作提示','请先保存后,再新增角色');
			return;
		}
		var index = $('#jsgl-datagrid').datagrid('getRowIndex', row);
	} else {
		index = 0;
	}
	$('#jsgl-datagrid').datagrid('insertRow', {
		index: 0,
		row:{
			updateTime:ROLES_PUBLIC.formatterDate(new Date())
		}
	});
	$('#jsgl-datagrid').datagrid('selectRow',0);
	$('#jsgl-datagrid').datagrid('beginEdit',0);
}

ROLES_PUBLIC.deleterow = function(target){

    //var choosen_rows = ROLES_PUBLIC.grid.datagrid('getSelections').length;
    
	//var row = $('#jsgl-datagrid').datagrid('getSelected');
	
    var choosen_rows = $('#jsgl-datagrid').datagrid('getSelections').length;
	if(choosen_rows==0){
		var dlg=$.messager.alert({title: "操作提示", msg: "请选择您要删除的记录！"});
		setTimeout(function(){
		    dlg.window('close');
		}, 5000);
		return;
	}
	//$('#cppz-datagrid').datagrid('deleteRow', ROLES_PUBLIC.getRowIndex(target));
	$.messager.confirm('操作提示','确定后数据不可恢复,确认删除吗?',function(confirm_status){
		if (confirm_status){
			var row = $('#jsgl-datagrid').datagrid('getSelected');
			var clientRequest = new Tcsp.ClientRequest();
			var params = new Object();
			params.id = row.id;
			var url = managerPath + "/manager/system/TMRolesMgr.json?m=execute&f=roleRemove";
			clientRequest.postJSON(url,params,function(respData){
				  if(respData.result){
					  if(respData.result.success&&respData.result.errorCode=='00000'){
						  //$('#cppz-datagrid').datagrid('deleteRow', ROLES_PUBLIC.datagrid('getSelected'));
						  $('#jsgl-datagrid').datagrid('reload');
					  }else{
						  $.messager.alert("操作提示",respData.result.errorMsg);
					  }
				  }
			});
		}
	});

}

ROLES_PUBLIC.saverow=function(target){
	
	var index=ROLES_PUBLIC.getRowIndex(target);
	$('#jsgl-datagrid').datagrid('endEdit',index);
	$('#jsgl-datagrid').datagrid('selectRow',index);
	var row = $('#jsgl-datagrid').datagrid('getSelected');
	if (row){
		var clientRequest = new Tcsp.ClientRequest();
		var params = new Object();
		
		params.id = row.id;
		params.Name =  row.name;
		params.DepartCode =  row.departCode;
		params.LvlId =  row.lvlId;
		params.ParentId =  row.parentId;
		params.Remark =  row.remark;
		params.UpdateStaffId =  row.updateStaffId;
		params.UpdateTime =  row.updateTime;
		
		var action='Save';
		if(params.id){
			action='Update';
		}else{
			action='Save';
		}
		var url = managerPath + "/manager/system/TMRolesMgr.json?m=execute&f=role"+action;
		
		clientRequest.postJSON(url,params,function(respData){
			  if(respData.result){
				  if(respData.result.success&&respData.result.errorCode=='00000'){
					  $('#jsgl-datagrid').datagrid('reload');
				  }else{
					  $.messager.alert("操作提示",respData.result.errorMsg);
				  }
			  }
		});
	}
}

ROLES_PUBLIC.cancelrow=function(target){
	$('#jsgl-datagrid').datagrid('cancelEdit', ROLES_PUBLIC.getRowIndex(target));
}

ROLES_PUBLIC.show_right=function(roleId){
	OpenTab('center_tabs','分配权限',managerPath+'/manager/system/TMRoleRightMgr.htm?m=query&f=qx&roleId='+roleId,'icon-munich-collaboration');
	
};

ROLES_PUBLIC.updateActions=function(index){
	$('#jsgl-datagrid').datagrid('updateRow',{
		index: index,
		row:{}
	});
}

ROLES_PUBLIC.grid=$('#jsgl-datagrid').datagrid({  
    iconCls:'icon-edit',
    //width:'auto',
    height: 'auto',  
    nowrap: false,  
    striped: true,  
    border: true,  
    collapsible:false,//是否可折叠的  
    fitColumns: false,//自动大小  
    url: managerPath+'/manager/system/TMRolesMgr.json?m=query&f=roleQuery',
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
    		{title: '角色名称',field: 'name',width:80,sortable: true,align:'left',editor:'text'},
    		{title: '归属部门', field: 'departCode', width:100,sortable: false,align:'center',editor:'text'},
    		{title: '角色级别',field: 'lvlId',width:100,sortable:false,align:'center',editor:'text'},
			{title: '父级角色', field: 'parentId',width:60,sortable: false,align:'center',editor:'text'},
        	{title: '备注', field: 'remark', width:80,sortable: false,align:'center',editor:'text'},
        	{title: '更新员工', field: 'updateStaffId', width:120,sortable: false,align:'center',editor:'text'},
        	{title: '更新时间', field: 'updateTime', width:80,sortable: false,align:'center',editor:'datetimebox'},
        	{title: '操作',field:'查看',sortable: false,align:'center',width:220,
                formatter:function(value,row,index){
                	var r='<a onclick="ROLES_PUBLIC.show_right(\''+row.id+'\')">权限</a>';
                	if (row.editing){
						var s = '<a href="#" onclick="ROLES_PUBLIC.saverow(this)">保存</a> ';
						var c = '<a href="#" onclick="ROLES_PUBLIC.cancelrow(this)">取消</a>';
						return s+c;
					} else {
						var e = '<a href="#" onclick="ROLES_PUBLIC.editrow(this)">编辑</a> ';
						var d = '<a href="#" onclick="ROLES_PUBLIC.deleterow(this)">删除</a>';d='';
						return e+d+r;
						//return e+d;
					}
                	return "<a onclick='ROLES_PUBLIC.show_right(\""+row.id+"\")'>"+'权限'+"</a>";
               }
            }
    	]],
    	onBeforeEdit:function(index,row){
			row.editing = true;
			ROLES_PUBLIC.updateActions(index);
		},
		onAfterEdit:function(index,row){
			row.editing = false;
			ROLES_PUBLIC.updateActions(index);
		},
		onCancelEdit:function(index,row){
			row.editing = false;
			ROLES_PUBLIC.updateActions(index);
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
        handler: ROLES_PUBLIC.insert
    },'-',{
        text: '删除',
        iconCls: 'icon-remove',
        handler: ROLES_PUBLIC.deleterow
    }],
    pageNumber: 1,
    pageList: [10,20,30,40,50],
    pageSize: 10

});

	

	ROLES_PUBLIC.getpagedata();
	
</script>

