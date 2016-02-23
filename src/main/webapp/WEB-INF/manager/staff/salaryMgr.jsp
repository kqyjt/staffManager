<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>
<input id = "apartmentSize1" value="${result.dataSet.apartmentSize }" type="hidden"/>

<form name="salaryForm" method="post" action="#" target="newwindow">
	<div class="chaxun_cont">
		<ul>
			<li>
			  	<span>月份：</span>
			  	<select id = "salary_month" name="salary_month" class="easyui-combobox" style="width:129px;" data-options="editable:false"> 
			  		<option value="">全部</option>  
			   		<option value="01">一月</option>   
			   		<option value="02">二月</option> 
			   		<option value="03">三月</option> 
			   		<option value="04">四月</option> 
			   		<option value="05">五月</option> 
			   		<option value="06">六月</option> 
			   		<option value="07">七月</option> 
			   		<option value="08">八月</option> 
			   		<option value="09">九月</option> 
			   		<option value="10">十月</option> 
			   		<option value="11">十一月</option> 
			   		<option value="12">十二月</option>  
				</select>
			</li>
  			<li><span>工号：</span><input  type="text" id="salary_id" name="salary_id" value="" class="easyui-textbox" ></li>
  			<li><span>姓名：</span><input  type="text" id="salary_name" name="salary_name" value="" class="easyui-textbox" ></li>
		</ul>
		<div class="butt_cont">
			<a href="#" id="salarySearch" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">查询</a> 
		</div>
	</div>
</form>

<div style="margin: 10px;">
	<table id="salary_datagrid"></table>
</div>

<script type="text/javascript">
	var salary_util = salary_util || {};
	salary_util.params = new Object();
	salary_util.queryUrl = managerPath + '/manager/staff/SalaryMgr.json?m=query&f=salaryQuery';
	salary_util.getRowIndex = function(target) {
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	
	//月份
	salary_util.isMonth = [
		{ "value": "01", "text": "一月" },
		{ "value": "02", "text": "二月" },
		{ "value": "03", "text": "三月" },
		{ "value": "04", "text": "四月" },
		{ "value": "05", "text": "五月" },
		{ "value": "06", "text": "六月" },
		{ "value": "07", "text": "七月" },
		{ "value": "08", "text": "八月" },
		{ "value": "09", "text": "九月" },
		{ "value": "10", "text": "十月" },
		{ "value": "11", "text": "十一月" },
		{ "value": "12", "text": "十二月" }
	];
	salary_util.unitformatter = function(value, rowData, rowIndex) {
		for (var i = 0; i <salary_util.isMonth.length; i++) {
			if (salary_util.isMonth[i].value == value) {
				return salary_util.isMonth[i].text;
			}
		}
	}
	
	//职工状况
	salary_util.isWork = [
		{ "value": "00", "text": "在职" },
		{ "value": "01", "text": "离职" }
	];
	salary_util.unitformatter1 = function(value, rowData, rowIndex) {
		for (var i = 0; i <salary_util.isWork.length; i++) {
			if (salary_util.isWork[i].value == value) {
				return salary_util.isWork[i].text;
			}
		}
	}
	
	//编辑记录
	salary_util.editrow = function(target) {
		$('#salary_datagrid').datagrid('selectRow', salary_util.getRowIndex(target));
		$('#salary_datagrid').datagrid('beginEdit', salary_util.getRowIndex(target));
	}
	
	//新增记录
	salary_util.insert=function(){
		var url = managerPath + '/manager/staff/SalaryMgr.htm?m=execute&f=salaryUpdate';
		OpenTab("center_tabs",'新增工资记录',url,'icon-munich-collaboration');
	}
	
	//更新工资
	salary_util.updateSalary = function(target) {
		var row = $('#salary_datagrid').datagrid('getSelected');
		var id = row.id;
		var url = managerPath + '/manager/staff/SalaryMgr.htm?m=execute&f=salaryUpdate&id='+id;
		OpenTab("center_tabs",'更新工资记录',url,'icon-munich-collaboration');
	}
	
	//删除记录
	salary_util.deleterow = function(target) {
		var choosen_rows = salary_util.grid.datagrid('getSelections').length;
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
				var row = $('#salary_datagrid').datagrid('getSelected');
				var clientRequest = new Tcsp.ClientRequest();
				var params = new Object();
				params.id = row.id;
				var url = managerPath + "/manager/staff/SalaryMgr.json?m=execute&f=salaryRemove";
				clientRequest.postJSON(url,params,function(respData) {
					if (respData.result) {
						if (respData.result.success && respData.result.errorCode == '00000') {
							$('#salary_datagrid').datagrid('reload');
							$('#salary_datagrid').datagrid('clearSelections');
						} else {
							$.messager.alert("操作提示",respData.result.errorMsg);
						}
					}
				});
			}
		});
	}

	//多选的情况下才会用到
	salary_util.getSelectedArr = function(dom) {
		var ids = [];
		var rows = $('#' + dom).datagrid('getSelections');
		for (var i = 0; i < rows.length; i++) {
			ids.push(rows[i].id);
		}
		return ids;
	}

	//取消编辑
	salary_util.cancelrow = function(target) {
		$('#salary_datagrid').datagrid('cancelEdit',salary_util.getRowIndex(target));
	}
	
	salary_util.updateActions = function(index) {
		
		$('#salary_datagrid').datagrid('updateRow', {
			index : index,
			row : {}
		});
	}
	
	salary_util.templateData = eval('[${result.dataSet.json}]');
	salary_util.grid = $('#salary_datagrid').datagrid({
		iconCls : 'icon-edit',
		//width:'auto',
		height : 'auto',
		nowrap : false,
		striped : true,
		border : true,
		collapsible : false,//是否可折叠的  
		fitColumns : true,//自动大小  
		queryParams : salary_util.params,
		url : salary_util.queryUrl,
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
				{title: '月份',field: 'month',width:80,sortable: true,align:'left',
					formatter:salary_util.unitformatter,
					editor:{
						type:'combobox',
						options:{
							data: salary_util.isMonth,
							valueField: "value",
							textField: "text"
						}
					}
				},
				{title: '工号',field: 'staffId',width:60,sortable: true,align:'left',editor:'text'},
				{title: '姓名',field: 'staffName',width:60,sortable: true,align:'left',editor:'text'},
				{title: '职工状况',field: 'state',width:80,sortable: true,align:'left',
					formatter:salary_util.unitformatter1,
					editor:{
						type:'combobox',
						options:{
							data: salary_util.isWork,
							valueField: "value",
							textField: "text"
						}
					}
				},
				{title: '职务',field: 'post',width:60,sortable: true,align:'left'},
				{title: '录入时间',field: 'inputtime',width:80,sortable: true,align:'left',editor:'datebox'},
				{title: '工龄',field: 'workage',width:150,sortable: true,align:'left',editor:'text'},
				{title: '实发工资',field: 'realitySalary',width:60,sortable: true,align:'left',editor:'text'},
				{title: '操作',field:'操作',sortable: false,align:'center',width:80,
		            formatter:function(value,row,index){
		            	var e = '<a href="#" onclick="salary_util.updateSalary(this)">更新</a> ';
						return e;
		            }
		        }
		] ],
		onBeforeEdit : function(index, row) {
			row.editing = true;
			salary_util.updateActions(index);
		},
		onAfterEdit : function(index, row) {
			row.editing = false;
			salary_util.updateActions(index);
		},
		onCancelEdit : function(index, row) {
			row.editing = false;
			salary_util.updateActions(index);
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
	        handler: salary_util.insert
		},'-',{
			text : '删除',
			iconCls : 'icon-remove',
			handler : salary_util.deleterow
	    } ],
		pageList : [ 10,20,30 ],
		pageSize : 10

	});
	
	//查询
	$("#salarySearch").click(function() {
		salary_util.params.staffId = $("#salary_id").val();
		salary_util.params.staffName = $("#salary_name").val();
		salary_util.params.month = $("#salary_month").combobox("getValue");
		$('#salary_datagrid').datagrid('cancelEdit', 0);
		$('#salary_datagrid').datagrid('reload');
	});
	
</script>
