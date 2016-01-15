<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>


<form name="staffModuleForm" method="post" action="#" target="newwindow">
	<div class="chaxun_cont">
		<ul>
			<li>
				<span>员工工号:</span>
				<input id="staff_module_id" name="staff_module_id" class="easyui-textbox" value=''>
			</li>
		</ul>
		<div class="butt_cont"> 
			<a href="#" id="staff_query_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">查询</a>
		</div>
	</div>
</form>

<div style="margin: 10px;">
	<li><span style="text-align:left;display:block;">所属角色</span></li>
	<table id="staff_att_datagrid"></table>
	<li><span style="text-align:left;display:block;">角色</span></li>
	<table id="staff_datagrid"></table>
</div>

<script type="text/javascript">
	var staff_att_util = staff_att_util || {};
	staff_att_util.params = new Object();
	staff_att_util.queryUrl = managerPath + "/manager/sysman/RoleMgr.json?m=query&f=staffAttQuery";
	
	//查询
	$("#staff_query_btn").click(function() {
 		var clientRequest = new Tcsp.ClientRequest();
 		staff_att_util.params.staffId = $("#staff_module_id").val();
 		var url = managerPath + "/manager/sysman/RoleMgr.json?m=query&f=staffAttQuery";
 		clientRequest.postJSON(url,staff_att_util.params,function(respData) {
			if (respData.result) {
				if (respData.result.success && respData.result.errorCode == '00000') {
					$('#staff_att_datagrid').datagrid('reload');
				} else {
					$.messager.alert("操作提示",respData.result.errorMsg);
				}
			}
		});
 		$('#staff_att_datagrid').datagrid('reload');
	}); 
	
	//解除角色
	staff_att_util.deleteRole = function(target){
 		var clientRequest = new Tcsp.ClientRequest();
 		staff_att_util.params.staffId = $("#staff_module_id").val();
 		var url = managerPath + "/manager/sysman/RoleMgr.json?m=execute&f=staffRemove";
 		$.messager.confirm('确认对话框', '您确认要解除角色吗？', function(r){
 			if(r){
 				clientRequest.postJSON(url,staff_att_util.params,function(respData) {
 					if (respData.result) {
 						if (respData.result.success && respData.result.errorCode == '00000') {
 							$('#staff_att_datagrid').datagrid('reload');
 						} else {
 							$.messager.alert("操作提示",respData.result.errorMsg);
 						}
 					}
 				});
 			}
 		});
	}

	staff_att_util.templateData = eval('[${result.dataSet.json}]');
	staff_att_util.grid = $('#staff_att_datagrid').datagrid({
		iconCls : 'icon-edit',
		//width:'auto',
		height : 'auto',
		nowrap : false,
		striped : true,
		border : true,
		collapsible : false,//是否可折叠的  
		fitColumns : true,//自动大小  
		url : staff_att_util.queryUrl,
		queryParams : staff_att_util.params,
		loadMsg : '数据加载中...',
		remoteSort : false,
		singleSelect : true,//是否单选  
		pagination : true,//分页控件  
		rownumbers : true,//行号   
		frozenColumns : [ [] ],
		columns : [ [ {
			title : '角色名称',
			field : 'roleName',
			width : 40,
			sortable : false,
			align : 'left'
		}, {
			title : '权限备注',
			field : 'remark',
			width : 140,
			sortable : false,
			align : 'left'
		},{title: '操作',field:'操作',sortable: false,align:'center',width:40,
            formatter:function(value,row,index){
				var e = '<a href="#" onclick="staff_att_util.deleteRole(this)">解除角色</a> ';
				return e;
            }
        } ] ],
		
		loadFilter : function(data) {
			if (data.result) {
				return data.result.dataSet;
			} else {
				return data;
			}
		},
		pageList : [1],
		pageSize : 1

	});
</script>

<script type="text/javascript">
	var staff_module_util = staff_module_util || {};
	staff_module_util.params = new Object();
	staff_module_util.queryUrl = managerPath + "/manager/sysman/RoleMgr.json?m=query&f=roleQuery";
	
	//更换角色
	staff_module_util.changerow = function(target) {
		var choosen_rows = staff_module_util.grid.datagrid('getSelections').length;
		if (choosen_rows == 0) {
			var dlg = $.messager.alert({
				title : "操作提示",
				msg : "请选择您要更换的角色！"
			});
			setTimeout(function() {
				dlg.window('close');
			}, 5000);
			return;
		}
		
		var row = $('#staff_datagrid').datagrid('getSelected');
 		var clientRequest = new Tcsp.ClientRequest();
 		staff_module_util.params.staffId = $("#staff_module_id").val();
 		staff_module_util.params.roleId = row.ID;
 		var url = managerPath + "/manager/sysman/RoleMgr.json?m=execute&f=roleChange";
 		clientRequest.postJSON(url,staff_module_util.params,function(respData) {
			if (respData.result) {
				if (respData.result.success && respData.result.errorCode == '00000') {
					$('#staff_att_datagrid').datagrid('reload');
				} else {
					$.messager.alert("操作提示",respData.result.errorMsg);
				}
			}
		});
 		
 		$('#staff_att_datagrid').datagrid('reload');
 		$('#staff_att_datagrid').datagrid('reload');
	}
	
	staff_module_util.getRowIndex = function(target) {
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	
	staff_module_util.editrow = function(target) {
		$('#staff_datagrid').datagrid('selectRow', staff_module_util.getRowIndex(target));
		$('#staff_datagrid').datagrid('beginEdit', staff_module_util.getRowIndex(target));
	}
	
	staff_module_util.getSelectedArr = function(dom) {
		var ids = [];
		var rows = $('#' + dom).datagrid('getSelections');
		for (var i = 0; i < rows.length; i++) {
			ids.push(rows[i].moduleId);
		}
		return ids;
	}

	staff_module_util.cancelrow = function(target) {
		$('#staff_datagrid').datagrid('cancelEdit',staff_module_util.getRowIndex(target));
	}
	
	staff_module_util.updateActions = function(index) {
		$('#staff_datagrid').datagrid('updateRow', {
			index : index,
			row : {}
		});
	}

	staff_module_util.templateData = eval('[${result.dataSet.json}]');
	staff_module_util.grid = $('#staff_datagrid').datagrid({
		iconCls : 'icon-edit',
		//width:'auto',
		height : 'auto',
		nowrap : false,
		striped : true,
		border : true,
		collapsible : false,//是否可折叠的  
		fitColumns : true,//自动大小  
		url : staff_module_util.queryUrl,
		queryParams : staff_module_util.params,
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
			title : '角色id',
			field : 'ID',
			width : 40,
			sortable : false,
			align : 'left',
			hidden:true
		}, {
			title : '角色名称',
			field : 'roleName',
			width : 40,
			sortable : false,
			align : 'left'
		}, {
			title : '权限备注',
			field : 'remark',
			width : 140,
			sortable : false,
			align : 'left'
		} ] ],
		onBeforeEdit : function(index, row) {
			row.editing = true;
			staff_module_util.updateActions(index);
		},
		onAfterEdit : function(index, row) {
			row.editing = false;
			staff_module_util.updateActions(index);
		},
		onCancelEdit : function(index, row) {
			row.editing = false;
			staff_module_util.updateActions(index);
		},
		loadFilter : function(data) {
			if (data.result) {
				return data.result.dataSet;
			} else {
				return data;
			}
		},
		toolbar : [ {
			text : '更换角色',
			iconCls : 'icon-remove',
			handler : staff_module_util.changerow
		} ],
		pageList : [ 10,20,30,50 ],
		pageSize : 10

	});
</script>
