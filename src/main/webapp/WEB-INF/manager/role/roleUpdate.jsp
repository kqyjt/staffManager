<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>

<form name="powerForm" method="post" action="#" target="newwindow">
	<div class="chaxun_cont">
		<li>
			<span>角色名称:</span>
			<input id="update_name" name="update_name" class="easyui-textbox" value="${result.dataSet.roleName}">
		</li>
	</div>
</form>

<div style="margin: 10px;">
	<li><span style="text-align:left;display:block;">已有权限</span></li>
	<table id="power-datagrid" class="easyui-datagrid" style="width:auto;height:350px;" data-options="singleSelect: true,toolbar:'#qxgx_delete',rownumbers:true,fitColumns:true,striped:true,autoRowHeight:true">
		<thead>
			<tr>
				<th data-options="field : 'ck',checkbox : true"></th>
				<th data-options="field : 'menuId',width:35">菜单ID</th>
				<th data-options="field : 'menuName',width:200">菜单名称</th>
			</tr>
		</thead>
	</table>
	
	<!-- 删除按钮  -->
	<div id="qxgx_delete" style="height:auto">
		<table>
		  <tr>
			<td>
				<a onclick="power_util.deleterow()" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">
					删除
				</a>
			</td>
		  </tr>
		</table>
	</div>
	
	<li><span style="text-align:left;display:block;">未添加权限</span></li>
	<table id="module-datagrid" class="easyui-datagrid" style="width:auto;height:350px;" data-options="singleSelect: true,toolbar:'#qxgx_add',rownumbers:true,fitColumns:true,striped:true,autoRowHeight:true">
		<thead>
			<tr>
				<th data-options="field : 'ck',checkbox : true"></th>
				<th data-options="field : 'menuId',width:35">菜单ID</th>
				<th data-options="field : 'menuName',width:200">菜单名称</th>
			</tr>
		</thead>
	</table>
	
	<!-- 添加按钮  -->
	<div id="qxgx_add" style="height:auto">
		<table>
		  <tr>
			<td>
				<a onclick="module_util.addrow()" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">
					添加
				</a>
			</td>
		  </tr>
		</table>
	</div>
</div>

<script type="text/javascript">
	var temp = -1;
	var power_util = power_util || {};
	power_util.params = new Object();
	power_util.params.roleName = $("#update_name").val();
	$("#update_name").attr("readonly", true);
	power_util.queryUrl = managerPath + "/manager/sysman/RoleMgr.json?m=query&f=menuQuery";
	
	power_util.getRowIndex = function(target) {
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	
	power_util.getSelectedArr = function(dom) {
		var moduleIds = [];
		var rows = $('#' + dom).datagrid('getSelections');
		for (var i = 0; i < rows.length; i++) {
			moduleIds.push(rows[i].moduleId);
		}
		return moduleIds;
	}
	
	//删除记录
	power_util.deleterow = function() {
		var choosen_rows = power_util.grid.datagrid('getSelections').length;
		var modules = -1;
		if(temp != -1){
			modules = power_util.getSelectedArr('ddv-'+temp);
		}
		
		if ((choosen_rows == 0 && modules == -1) || modules+'a' == 'a') {
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
				var clientRequest = new Tcsp.ClientRequest();
				power_util.params.roleName = $("#update_name").val();
				var url;
				
				if(temp == -1){
					var selectRow = $("#power-datagrid").datagrid("getSelected");
					power_util.params.menuId = selectRow.menuId;
					url = managerPath + "/manager/sysman/RoleMgr.json?m=execute&f=menuRemove";
				} else {
					var arr = power_util.getSelectedArr('ddv-'+temp);
					power_util.params.moduleIds=arr.join(',');
					url = managerPath + "/manager/sysman/RoleMgr.json?m=execute&f=powerRemove";
				}
				
				clientRequest.postJSON(url,power_util.params,function(respData) {
					if (respData.result) {
						if (respData.result.success && respData.result.errorCode == '00000') {
							if(temp != -1){
								$('#ddv-'+temp).datagrid('reload');
								$('#ddv-'+temp).datagrid('clearSelections');
							}
							
							$('#power-datagrid').datagrid('clearSelections');
							$('#module-datagrid').datagrid('reload');
							$('#power-datagrid').datagrid('reload');
							
						} else {
							$.messager.alert("操作提示",respData.result.errorMsg);
						}
					}
					
				});
				temp = -1;
			}
		});
		
	}

	power_util.updateActions = function(index) {
		$('#power-datagrid').datagrid('updateRow', {
			index : index,
			row : {}
		});
	}

	power_util.templateData = eval('[${result.dataSet.json}]');
	power_util.grid = $('#power-datagrid').datagrid({
		iconCls : 'icon-edit',
		//width:'auto',
		height : 300,
		width : 1000,
		nowrap : false,
		striped : true,
		border : true,
		collapsible : false,//是否可折叠的  
		fitColumns : true,//自动大小  
		url : power_util.queryUrl,
		view: detailview,
		queryParams : power_util.params,
		loadMsg : '数据加载中...',
		remoteSort : false,
		singleSelect : true,//是否单选  
		pagination : true,//分页控件
		rownumbers : true,//行号   
		
		loadFilter : function(data) {
			if (data.result) {
				return data.result.dataSet;
			} else {
				return data;
			}
		},
		
		detailFormatter:function(index,row){    
	        return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';    
	    },
	    onExpandRow: function(index,row){ 
	    	var url = managerPath + "/manager/sysman/RoleMgr.json?m=query&f=powerQuery";
	        $('#ddv-'+index).datagrid({ 
	        	height : 'auto',
	    		width : 400,
	    		nowrap : false,
	    		striped : true,
	    		border : true,
	    		collapsible : false,//是否可折叠的  
	    		fitColumns : true,//自动大小  
	    		url : url,
	    		queryParams : {
	    			roleName: power_util.params.roleName,
	    			menuId: row.menuId
            	},
	    		loadMsg : '数据加载中...',
	    		remoteSort : false,
	    		idField : 'moduleId',
	    		singleSelect : false,//是否单选  
	    		rownumbers : true,//行号   
	    		frozenColumns : [ [] ],
	    		columns : [ [ {
	    			field : 'ck1',
	    			checkbox : true
	    		}, {
	    			title : '权限ID',
	    			field : 'moduleId',
	    			width : 160,
	    			sortable : false,
	    			align : 'center'
	    		}, {
	    			title : '权限名称',
	    			field : 'powerName',
	    			width : 240,
	    			sortable : false,
	    			align : 'left'
	    		} ] ],
	    		
	    		loadFilter : function(data) {
	    			if (data.result) {
	    				return data.result.dataSet;
	    			} else {
	    				return data;
	    			}
	    		},
	    		onLoadSuccess:function(data){
	    			temp = index;
	    			$('#ddv-'+index).datagrid('selectAll');
	    			var modules = power_util.getSelectedArr('ddv-'+index);
	                $('#power-datagrid').datagrid('fixDetailRowHeight',index);    
	            } 
	        });
	        
	        $('#power-datagrid').datagrid('fixDetailRowHeight',index);
	    },
	    
	    onCollapseRow: function(index,row){
	    	temp = -1;
	    },
	    pageList : [ 10 ],
		pageSize : 10
	});
</script>

<script type="text/javascript">
	var temp1 = -1;
	var module_util = module_util || {};
	module_util.params = new Object();
	module_util.params.roleName = $("#update_name").val();
	module_util.queryUrl = managerPath + "/manager/sysman/RoleMgr.json?m=query&f=noMenuQuery";
	
	//添加
	module_util.addrow = function(target) {
		
		var choosen_rows = module_util.grid.datagrid('getSelections').length;
		var modules = -1;
		if(temp1 != -1){
			modules = module_util.getSelectedArr('ddvv-'+temp1);
		}
		
		if((choosen_rows == 0 && modules == -1) || modules+'a' == 'a'){
			var dlg=$.messager.alert({title: "操作提示", msg: "请选择您要添加的权限！"});
			setTimeout(function(){
			    dlg.window('close');
			}, 5000);
			return;
		}
		
		var clientRequest = new Tcsp.ClientRequest();
		module_util.params.roleName = $("#update_name").val();
		var url;
		
		if(temp1 == -1){
			var selectRow = $("#module-datagrid").datagrid("getSelected");
			module_util.params.menuId = selectRow.menuId;
			url = managerPath + "/manager/sysman/RoleMgr.json?m=execute&f=menuSave";
		} else {
			var arr = module_util.getSelectedArr('ddvv-'+temp1);
			module_util.params.moduleNames=arr.join(',');
			url = managerPath + "/manager/sysman/RoleMgr.json?m=execute&f=roleSave";
		}
		
 		clientRequest.postJSON(url,module_util.params,function(respData) {
			if (respData.result) {
				if (respData.result.success && respData.result.errorCode == '00000') {
					if(temp1 != -1){
						$('#ddvv-'+temp1).datagrid('reload');
						$('#ddvv-'+temp1).datagrid('clearSelections');
					}
					
					$('#module-datagrid').datagrid('reload');
					$('#module-datagrid').datagrid('clearSelections');
					
					$('#power-datagrid').datagrid('reload');
				} else {
					$.messager.alert("操作提示",respData.result.errorMsg);
				}
			}
			
		});
 		
 		$('#power-datagrid').datagrid('reload');
 		$('#module-datagrid').datagrid('reload');
 		$('#power-datagrid').datagrid('reload');
 		temp1 = -1;
	}
	
	module_util.getRowIndex = function(target) {
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	
	module_util.getSelectedArr = function(dom) {
		var moduleNames = [];
		var rows = $('#' + dom).datagrid('getSelections');
		for (var i = 0; i < rows.length; i++) {
			moduleNames.push(rows[i].powerName);
		}
		return moduleNames;
	}

	module_util.updateActions = function(index) {
		$('#module-datagrid').datagrid('updateRow', {
			index : index,
			row : {}
		});
	}

	module_util.templateData = eval('[${result.dataSet.json}]');
	module_util.grid = $('#module-datagrid').datagrid({
		iconCls : 'icon-edit',
		//width:'auto',
		height : 300,
		width : 1000,
		nowrap : false,
		striped : true,
		border : true,
		collapsible : false,//是否可折叠的  
		fitColumns : true,//自动大小  
		url : module_util.queryUrl,
		view: detailview,
		queryParams : module_util.params,
		loadMsg : '数据加载中...',
		remoteSort : false,
		pagination : true,//分页控件
		singleSelect : true,//是否单选  
		rownumbers : true,//行号   

		loadFilter : function(data) {
			if (data.result) {
				return data.result.dataSet;
			} else {
				return data;
			}
		},
		
		detailFormatter:function(index,row){    
	        return '<div style="padding:2px"><table id="ddvv-' + index + '"></table></div>';    
	    },
	    onExpandRow: function(index,row){ 
	    	var url = managerPath + "/manager/sysman/RoleMgr.json?m=query&f=noPowerQuery";
	        $('#ddvv-'+index).datagrid({ 
	        	height : 'auto',
	    		width : 400,
	    		nowrap : false,
	    		striped : true,
	    		border : true,
	    		collapsible : false,//是否可折叠的  
	    		fitColumns : true,//自动大小  
	    		url : url,
	    		queryParams : {
	    			roleName: module_util.params.roleName,
	    			menuId: row.menuId
            	},
	    		loadMsg : '数据加载中...',
	    		remoteSort : false,
	    		idField : 'moduleId',
	    		singleSelect : false,//是否单选  
	    		rownumbers : true,//行号   
	    		frozenColumns : [ [] ],
	    		columns : [ [ {
	    			field : 'ck1',
	    			checkbox : true
	    		}, {
	    			title : '权限ID',
	    			field : 'moduleId',
	    			width : 160,
	    			sortable : false,
	    			align : 'center'
	    		}, {
	    			title : '权限名称',
	    			field : 'powerName',
	    			width : 240,
	    			sortable : false,
	    			align : 'left'
	    		} ] ],
	    		
	    		loadFilter : function(data) {
	    			if (data.result) {
	    				return data.result.dataSet;
	    			} else {
	    				return data;
	    			}
	    		},
	    		onLoadSuccess:function(data){
	    			temp1 = index;
	    			$('#ddvv-'+index).datagrid('selectAll');
	    			var modules = module_util.getSelectedArr('ddvv-'+index);
	                $('#module-datagrid').datagrid('fixDetailRowHeight',index);    
	            } 
	        });
	        
	        $('#module-datagrid').datagrid('fixDetailRowHeight',index);
	    },
	    
	    onCollapseRow: function(index,row){
	    	temp1 = -1;
	    },
	    pageList : [ 10 ],
		pageSize : 10
	});
</script>
