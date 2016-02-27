<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>
<input id = "apartmentSize3" value="${result.dataSet.apartmentSize }" type="hidden"/>

<form name="agreementForm" method="post" action="#" target="newwindow">
	<div class="chaxun_cont">
		<ul>
  			<li><span>工号：</span><input  type="text" id="agreement_id" name="agreement_id" value="" class="easyui-textbox" ></li>
  			<li><span>姓名：</span><input  type="text" id="agreement_name" name="agreement_name" value="" class="easyui-textbox" ></li>
			<li>
			  	<span>部门：</span>
			  	<select class="easyui-combobox" id="agreement_apartment" name="agreement_apartment" style="width:80px;">
					<c:forEach var="entry" items="${result.dataSet.apartmentList}">
						<option value="${entry.apartId}" selected="selected">${entry.apartName}</option>
					</c:forEach>
					<option value="" selected="selected">全部</option>
				</select>
			</li>
		</ul>
		<div class="butt_cont">
			<a href="#" id="agreementSearch" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">查询</a> 
		</div>
	</div>
</form>

<div style="margin: 10px;">
	<table id="agreement_datagrid"></table>
</div>

<script type="text/javascript">
	var agreement_util = agreement_util || {};
	agreement_util.params = new Object();
	agreement_util.queryUrl = managerPath + '/manager/staff/AgreementMgr.json?m=query&f=agreementQuery';
	agreement_util.getRowIndex = function(target) {
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	
	//部门
	agreement_util.apartmentList = [];
	var apartmentSize = $("#apartmentSize3").val();
	var opts = document.getElementById("agreement_apartment").childNodes;//获取option所有dom对象
	for(var i = 0 ; i < opts.length ; i++){
		if(opts.item(i).innerHTML!=undefined && opts.item(i).value!=""){
			agreement_util.apartmentList.push({"value":opts.item(i).value, "text":opts.item(i).innerHTML});
		}
	}
	agreement_util.unitformatter = function(value, rowData, rowIndex) {
		for (var i = 0; i <apartmentSize; i++) {
			if (agreement_util.apartmentList[i].value == value) {
				return agreement_util.apartmentList[i].text;
			}
		}
	}
	
	//编辑记录
	agreement_util.editrow = function(target) {
		$('#agreement_datagrid').datagrid('selectRow', agreement_util.getRowIndex(target));
		$('#agreement_datagrid').datagrid('beginEdit', agreement_util.getRowIndex(target));
	}
	
	//新增合同
	agreement_util.insert=function(){
		var url = managerPath + '/manager/staff/AgreementMgr.htm?m=execute&f=agreementUpdate';
		OpenTab("center_tabs",'新增合同',url,'icon-munich-collaboration');
	}
	
	//更新合同
	agreement_util.updateAgreement = function(target) {
		var row = $('#agreement_datagrid').datagrid('getSelected');
		var id = row.id;
		var url = managerPath + '/manager/staff/AgreementMgr.htm?m=execute&f=agreementUpdate&id='+id;
		OpenTab("center_tabs",'更新合同',url,'icon-munich-collaboration');
	}
	
	//删除记录
	agreement_util.deleterow = function(target) {
		var choosen_rows = agreement_util.grid.datagrid('getSelections').length;
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
				var row = $('#agreement_datagrid').datagrid('getSelected');
				var clientRequest = new Tcsp.ClientRequest();
				var params = new Object();
				params.id = row.id;
				var url = managerPath + "/manager/staff/AgreementMgr.json?m=execute&f=agreementRemove";
				clientRequest.postJSON(url,params,function(respData) {
					if (respData.result) {
						if (respData.result.success && respData.result.errorCode == '00000') {
							$('#agreement_datagrid').datagrid('reload');
							$('#agreement_datagrid').datagrid('clearSelections');
						} else {
							$.messager.alert("操作提示",respData.result.errorMsg);
						}
					}
				});
			}
		});
	}

	//多选的情况下才会用到
	agreement_util.getSelectedArr = function(dom) {
		var ids = [];
		var rows = $('#' + dom).datagrid('getSelections');
		for (var i = 0; i < rows.length; i++) {
			ids.push(rows[i].id);
		}
		return ids;
	}

	//取消编辑
	agreement_util.cancelrow = function(target) {
		$('#agreement_datagrid').datagrid('cancelEdit',agreement_util.getRowIndex(target));
	}
	
	agreement_util.updateActions = function(index) {
		
		$('#agreement_datagrid').datagrid('updateRow', {
			index : index,
			row : {}
		});
	}
	
	agreement_util.templateData = eval('[${result.dataSet.json}]');
	agreement_util.grid = $('#agreement_datagrid').datagrid({
		iconCls : 'icon-edit',
		//width:'auto',
		height : 'auto',
		nowrap : false,
		striped : true,
		border : true,
		collapsible : false,//是否可折叠的  
		fitColumns : true,//自动大小  
		queryParams : agreement_util.params,
		url : agreement_util.queryUrl,
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
				{title: '合同编号',field: 'agreeId',width:60,sortable: true,align:'left',editor:'text'},
				{title: '合同名称',field: 'agreeName',width:100,sortable: true,align:'left',editor:'text'},
				{title: '工号',field: 'staffId',width:60,sortable: true,align:'left',editor:'text'},
				{title: '姓名',field: 'staffName',width:60,sortable: true,align:'left',editor:'text'},
				{title: '部门',field: 'apartment',width:40,sortable: true,align:'left',
					formatter:agreement_util.unitformatter,
					editor:{
						type:'combobox',
						options:{
							data: agreement_util.apartmentList,
							valueField: "value",
							textField: "text"
						}
					}
				},
				{title: '职务',field: 'post',width:60,sortable: true,align:'left'},
				{title: '开始时间',field: 'starttime',width:80,sortable: true,align:'left',editor:'datebox'},
				{title: '结束时间',field: 'endtime',width:80,sortable: true,align:'left',editor:'datebox'},
				{title: '操作',field:'操作',sortable: false,align:'center',width:80,
		            formatter:function(value,row,index){
		            	var e = '<a href="#" onclick="agreement_util.updateAgreement(this)">更新</a> ';
						return e;
		            }
		        }
		] ],
		onBeforeEdit : function(index, row) {
			row.editing = true;
			agreement_util.updateActions(index);
		},
		onAfterEdit : function(index, row) {
			row.editing = false;
			agreement_util.updateActions(index);
		},
		onCancelEdit : function(index, row) {
			row.editing = false;
			agreement_util.updateActions(index);
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
	        handler: agreement_util.insert
		},'-',{
			text : '删除',
			iconCls : 'icon-remove',
			handler : agreement_util.deleterow
	    } ],
		pageList : [ 10,20,30 ],
		pageSize : 10

	});
	
	//查询
	$("#agreementSearch").click(function() {
		agreement_util.params.staffId = $("#agreement_id").val();
		agreement_util.params.staffName = $("#agreement_name").val();
		agreement_util.params.apartment = $("#agreement_apartment").combobox("getValue");
		$('#agreement_datagrid').datagrid('cancelEdit', 0);
		$('#agreement_datagrid').datagrid('reload');
	});
	
</script>
