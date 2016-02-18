<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>
<input id = "apartmentSize1" value="${result.dataSet.apartmentSize }" type="hidden"/>

<form name="apartmentForm" method="post" action="#" target="newwindow">
	<div class="chaxun_cont">
		<ul>
  			<li><span>部门编号：</span><input  type="text" id="apartId" name="apartId" value="" class="easyui-textbox" ></li>
  			<li><span>部门名称：</span><input  type="text" id="apartName" name="apartName" value="" class="easyui-textbox" ></li>
		</ul>
		<div class="butt_cont">
			<a href="#" id="apartmentSearch" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">查询</a> 
		</div>
	</div>
</form>

<div style="margin: 10px;">
	<table id="apartment_datagrid"></table>
</div>

<script type="text/javascript">
	var apartment_util = apartment_util || {};
	apartment_util.params = new Object();
	apartment_util.queryUrl = managerPath + '/manager/staff/ApartmentMgr.json?m=query&f=apartmentQuery';
	apartment_util.getRowIndex = function(target) {
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	
	//编辑记录
	apartment_util.editrow = function(target) {
		$('#apartment_datagrid').datagrid('selectRow', apartment_util.getRowIndex(target));
		$('#apartment_datagrid').datagrid('beginEdit', apartment_util.getRowIndex(target));
	}
	
	//新增记录
	apartment_util.insert=function(){
		$('#apartment_datagrid').datagrid('insertRow', {
			index: 0,
			row:{
			}
		});
		
		$('#apartment_datagrid').datagrid('selectRow',0);
		$('#apartment_datagrid').datagrid('beginEdit',0);
	}
	
	//删除记录
	apartment_util.deleterow = function(target) {
		var choosen_rows = apartment_util.grid.datagrid('getSelections').length;
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
				var row = $('#apartment_datagrid').datagrid('getSelected');
				var clientRequest = new Tcsp.ClientRequest();
				var params = new Object();
				params.id = row.id;
				var url = managerPath + "/manager/staff/ApartmentMgr.json?m=execute&f=apartmentRemove";
				clientRequest.postJSON(url,params,function(respData) {
					if (respData.result) {
						if (respData.result.success && respData.result.errorCode == '00000') {
							$('#apartment_datagrid').datagrid('reload');
							$('#apartment_datagrid').datagrid('clearSelections');
						} else {
							$.messager.alert("操作提示",respData.result.errorMsg);
						}
					}
				});
			}
		});
	}

	//多选的情况下才会用到
	apartment_util.getSelectedArr = function(dom) {
		var ids = [];
		var rows = $('#' + dom).datagrid('getSelections');
		for (var i = 0; i < rows.length; i++) {
			ids.push(rows[i].id);
		}
		return ids;
	}

	//保存记录
	apartment_util.saverow=function(target){
		var index=apartment_util.getRowIndex(target);
		$('#apartment_datagrid').datagrid('endEdit',index);
		$('#apartment_datagrid').datagrid('selectRow',index);
		var row = $('#apartment_datagrid').datagrid('getSelected');
		if (row){
			var clientRequest = new Tcsp.ClientRequest();
			var params = new Object();
			params.id = row.id;
			params.apartId = row.apartId;
			params.apartName = row.apartName;
			params.phone = row.phone;
			params.description = row.description;
			var action='Add';
			if(typeof(params.apartId)=='undefined'||params.apartId==''){
				$.messager.alert("操作提示",'部门编号不能为空');
				return;
			}
			if(typeof(params.apartName)=='undefined'||params.apartName==''){
				$.messager.alert("操作提示",'部门名称不能为空');
				return;
			}
			if(typeof(params.phone)=='undefined'||params.phone==''){
				$.messager.alert("操作提示",'部门电话不能为空');
				return;
			}
			if(params.id){
				action='Edit';
			}else{
				action='Add';
			}
			var url = managerPath + "/manager/staff/ApartmentMgr.json?m=execute&f=apartment"+action;
			clientRequest.postJSON(url,params,function(respData){
				  if(respData.result){
					  if(respData.result.success&&respData.result.errorCode=='00000'){
						  $('#apartment_datagrid').datagrid('reload');
						  $('#apartment_datagrid').datagrid('clearSelections');
					  }else{
						  $.messager.alert("操作提示",respData.result.errorMsg);
					  }
				  }
			});
		}
	}
	
	//取消编辑
	apartment_util.cancelrow = function(target) {
		$('#apartment_datagrid').datagrid('cancelEdit',apartment_util.getRowIndex(target));
	}
	
	apartment_util.updateActions = function(index) {
		
		$('#apartment_datagrid').datagrid('updateRow', {
			index : index,
			row : {}
		});
	}
	
	apartment_util.templateData = eval('[${result.dataSet.json}]');
	apartment_util.grid = $('#apartment_datagrid').datagrid({
		iconCls : 'icon-edit',
		//width:'auto',
		height : 'auto',
		nowrap : false,
		striped : true,
		border : true,
		collapsible : false,//是否可折叠的  
		fitColumns : true,//自动大小  
		queryParams : apartment_util.params,
		url : apartment_util.queryUrl,
		loadMsg : '数据加载中...',
		remoteSort : false,
		idField : 'id',
		singleSelect : true,//是否单选  
		pagination : true,//分页控件  
		rownumbers : true,//行号   
		frozenColumns : [ [] ],
		columns : [ [ 
				{field: 'ck',checkbox:true},
				{title: '唯一标识',field: 'id',width:60,sortable:false,align:'center',hidden:true},
				{title: '部门编号',field: 'apartId',width:60,sortable: true,align:'left',editor:'text'},
				{title: '部门名称',field: 'apartName',width:60,sortable: true,align:'left',editor:'text'},
				{title: '部门电话',field: 'phone',width:60,sortable: true,align:'left',editor:'text'},
				{title: '部门描述',field: 'description',width:60,sortable: true,align:'left',editor:'text'},
				{title: '操作',field:'操作',sortable: false,align:'center',width:80,
		            formatter:function(value,row,index){
		            	if (row.editing){
							var s = '<a href="#" onclick="apartment_util.saverow(this)">保存</a> ';
							var c = '<a href="#" onclick="apartment_util.cancelrow(this)">取消</a>';
							return s+c;
						} else {
							var e = '<a href="#" onclick="apartment_util.editrow(this)">编辑</a> ';
							return e;
						}
		           }
		        } 
		] ],
		onBeforeEdit : function(index, row) {
			row.editing = true;
			apartment_util.updateActions(index);
		},
		onAfterEdit : function(index, row) {
			row.editing = false;
			apartment_util.updateActions(index);
		},
		onCancelEdit : function(index, row) {
			row.editing = false;
			apartment_util.updateActions(index);
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
	        handler: apartment_util.insert
		},'-',{
			text : '删除',
			iconCls : 'icon-remove',
			handler : apartment_util.deleterow
	    } ],
		pageList : [ 10,20,30 ],
		pageSize : 10

	});
	
	//查询
	$("#apartmentSearch").click(function() {
		apartment_util.params.apartId = $("#apartId").val();
		apartment_util.params.apartName = $("#apartName").val();
		$('#apartment_datagrid').datagrid('cancelEdit', 0);
		$('#apartment_datagrid').datagrid('reload');
	});
	
</script>
