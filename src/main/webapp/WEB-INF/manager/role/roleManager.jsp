<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>

<form name="roleForm" method="post" action="#" target="newwindow">
	<div class="chaxun_cont">
		<ul>
			<li>
				<span>角色名称:</span>
				<input id="roleName" name="roleName" class="easyui-textbox" value=''>
			</li>
		</ul>
		<div class="butt_cont">
			<a href="#" id="role_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">查询</a> 
		</div>
	</div>
</form>

<div style="margin: 10px;">
	<table id="role-datagrid"></table>
</div>

<script type="text/javascript">
	var role_util = role_util || {};
	role_util.params = new Object();
	role_util.queryUrl = managerPath + '/manager/sysman/RoleMgr.json?m=query&f=roleQuery';
	role_util.getRowIndex = function(target) {
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	
	//编辑记录
	role_util.editrow = function(target) {
		$('#role-datagrid').datagrid('selectRow', role_util.getRowIndex(target));
		$('#role-datagrid').datagrid('beginEdit', role_util.getRowIndex(target));
	}
	
	//新增记录
	role_util.insert=function(){
		$('#role-datagrid').datagrid('insertRow', {
			index: 0,
			row:{
			}
		});
		
		$('#role-datagrid').datagrid('selectRow',0);
		$('#role-datagrid').datagrid('beginEdit',0);
	}
	
	//删除记录
	role_util.deleterow = function(target) {
		var choosen_rows = role_util.grid.datagrid('getSelections').length;
		if (choosen_rows == 0) {
			var dlg = $.messager.alert({
				title : "操作提示",
				msg : "请选择您要删除的记录！"
			});
			setTimeout(function() {
				dlg.window('close');
			}, 5000);
			return;
		}
		
		$.messager.confirm('操作提示','确定后数据不可恢复,确认删除吗?', function(confirm_status) {
			if (confirm_status) {
				var row = $('#role-datagrid').datagrid('getSelected');
				var clientRequest = new Tcsp.ClientRequest();
				var params = new Object();
				params.id = row.ID;
				var url = managerPath + "/manager/sysman/RoleMgr.json?m=execute&f=roleRemove";
				clientRequest.postJSON(url,params,function(respData) {
					if (respData.result) {
						if (respData.result.success && respData.result.errorCode == '00000') {
							$('#role-datagrid').datagrid('reload');
							$('#role-datagrid').datagrid('clearSelections');
						} else {
							$.messager.alert("操作提示",respData.result.errorMsg);
						}
					}
				});
			}
		});
	}

	role_util.getSelectedArr = function(dom) {
		var ids = [];
		var rows = $('#' + dom).datagrid('getSelections');
		for (var i = 0; i < rows.length; i++) {
			ids.push(rows[i].ID);
		}
		return ids;
	}

	//保存记录
	role_util.saverow=function(target){
		var index=role_util.getRowIndex(target);
		$('#role-datagrid').datagrid('endEdit',index);
		$('#role-datagrid').datagrid('selectRow',index);
		var row = $('#role-datagrid').datagrid('getSelected');
		if (row){
			var clientRequest = new Tcsp.ClientRequest();
			var params = new Object();
			params.id = row.ID;
			params.roleName = row.roleName;
			params.remark = row.remark;
			params.updateStaff = row.updateStaff;
			params.updateTime = row.updateTime;
			var action='Add';
			if(typeof(params.roleName)=='undefined'||params.roleName==''){
				$.messager.alert("操作提示",'必填字值不能为空');
				return;
			}
			if(params.id){
				action='Edit';
			}else{
				action='Add';
			}
			var url = managerPath + "/manager/sysman/RoleMgr.json?m=execute&f=role"+action;
			clientRequest.postJSON(url,params,function(respData){
				  if(respData.result){
					  if(respData.result.success&&respData.result.errorCode=='00000'){
						  $('#role-datagrid').datagrid('reload');
					  }else{
						  $.messager.alert("操作提示",respData.result.errorMsg);
					  }
				  }
			});
		}
	}
	
	//取消编辑
	role_util.cancelrow = function(target) {
		$('#role-datagrid').datagrid('cancelEdit',role_util.getRowIndex(target));
	}
	
	role_util.updateActions = function(index) {
		
		$('#role-datagrid').datagrid('updateRow', {
			index : index,
			row : {}
		});
	}
	
	//权限更新
	role_util.updatePowerrow = function(target) {
		$('#role-datagrid').datagrid('selectRow', role_util.getRowIndex(target));
		var selectRow = $("#role-datagrid").datagrid("getSelected");
		if (selectRow == "" || selectRow == undefined) {
			$.messager.alert('提示信息',"请先选择一行记录!");
			return false;
		}
		var ID = selectRow.ID;
		var url = managerPath + "/manager/sysman/RoleMgr.htm?m=execute&f=roleUpdate&ID="+ID;
		OpenTab("center_tabs",'权限更新',url,'icon-munich-collaboration');
	}
	
	role_util.templateData = eval('[${result.dataSet.json}]');
	role_util.grid = $('#role-datagrid').datagrid({
		iconCls : 'icon-edit',
		//width:'auto',
		height : 'auto',
		nowrap : false,
		striped : true,
		border : true,
		collapsible : false,//是否可折叠的  
		fitColumns : true,//自动大小  
		queryParams : role_util.params,
		url : role_util.queryUrl,
		loadMsg : '数据加载中...',
		remoteSort : false,
		idField : 'ID',
		singleSelect : true,//是否单选  
		pagination : true,//分页控件  
		rownumbers : true,//行号   
		frozenColumns : [ [] ],
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			title : '角色ID',
			field : 'ID',
			width : 40,
			sortable : false,
			align : 'center'
		}, {
			title : '角色名称',
			field : 'roleName',
			width : 90,
			sortable : false,
			align : 'left',
			editor : 'text'
		}, {
			title : '备注',
			field : 'remark',
			width : 290,
			sortable : false,
			align : 'left',
			editor : 'text'
		}, {
			title : '更新员工',
			field : 'updateStaff',
			width : 60,
			sortable : false,
			align : 'center'
		}, {
			title : '更新时间',
			field : 'updateTime',
			width : 80,
			sortable : false,
			align : 'center'
		}, {title: '角色操作',field:'角色操作',sortable: false,align:'center',width:80,
            formatter:function(value,row,index){
            	if (row.editing){
					var s = '<a href="#" onclick="role_util.saverow(this)">保存</a> ';
					var c = '<a href="#" onclick="role_util.cancelrow(this)">取消</a>';
					return s+c;
				} else {
					var e = '<a href="#" onclick="role_util.editrow(this)">编辑</a> ';
					var f = '<a href="#" onclick="role_util.updatePowerrow(this)" style="margin-left:20px;">权限更新</a> ';
					return e+f;
				}
           }
        } ] ],
		onBeforeEdit : function(index, row) {
			row.editing = true;
			role_util.updateActions(index);
		},
		onAfterEdit : function(index, row) {
			row.editing = false;
			role_util.updateActions(index);
		},
		onCancelEdit : function(index, row) {
			row.editing = false;
			role_util.updateActions(index);
		},
		loadFilter : function(data) {
			if (data.result) {
				return data.result.dataSet;
			} else {
				return data;
			}
		},
		toolbar : [ {
			text: '新增',
	        iconCls: 'icon-add',
	        handler: role_util.insert
		},'-',{
			text : '删除',
			iconCls : 'icon-remove',
			handler : role_util.deleterow
	    } ],
		pageList : [ 10,20,30 ],
		pageSize : 10

	});
	
	//查询
	$("#role_search_btn").click(function() {
		role_util.params.roleName = $("#roleName").val();
		$('#role-datagrid').datagrid('cancelEdit', 0);
		$('#role-datagrid').datagrid('reload');
	});
	
</script>
