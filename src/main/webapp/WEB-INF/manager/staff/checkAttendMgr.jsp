<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>
<input id = "apartmentSize2" value="${result.dataSet.apartmentSize }" type="hidden"/>

<form name="checkAttendForm" method="post" action="#" target="newwindow">
	<div class="chaxun_cont">
		<ul>
			<li><span>工号：</span><input  type="text" id="checkAttendId" name="checkAttendId" value="" class="easyui-textbox" ></li>
  			<li><span>姓名：</span><input  type="text" id="checkAttendName" name="checkAttendName" value="" class="easyui-textbox" ></li>
  			<li>
			  	<span>部门：</span>
			  	<select class="easyui-combobox" id="checkAttend_apartment" name="checkAttend_apartment" style="width:80px;">
					<c:forEach var="entry" items="${result.dataSet.apartmentList}">
						<option value="${entry.apartId}" selected="selected">${entry.apartName}</option>
					</c:forEach>
					<option value="" selected="selected">全部</option>
				</select>
			</li>
			<li>
			  	<span>月份：</span>
			  	<select id = "checkAttend_month" name="checkAttend_month" class="easyui-combobox" style="width:129px;" data-options="editable:false"> 
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
		</ul>
		<div class="butt_cont">
			<a href="#" id="checkAttendSearch" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">查询</a> 
		</div>
	</div>
</form>

<div style="margin: 10px;">
	<table id="checkAttend_datagrid"></table>
</div>

<script type="text/javascript">
	var checkAttend_util = checkAttend_util || {};
	checkAttend_util.params = new Object();
	checkAttend_util.queryUrl = managerPath + '/manager/staff/CheckAttendMgr.json?m=query&f=checkAttendQuery';
	checkAttend_util.getRowIndex = function(target) {
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	
	//月份
	checkAttend_util.isMonth = [
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
	checkAttend_util.unitformatter1 = function(value, rowData, rowIndex) {
		for (var i = 0; i <checkAttend_util.isMonth.length; i++) {
			if (checkAttend_util.isMonth[i].value == value) {
				return checkAttend_util.isMonth[i].text;
			}
		}
	}
	
	//部门
	checkAttend_util.apartmentList = [];
	var apartmentSize = $("#apartmentSize2").val();
	var opts = document.getElementById("checkAttend_apartment").childNodes;//获取option所有dom对象
	for(var i = 0 ; i < opts.length ; i++){
		if(opts.item(i).innerHTML!=undefined && opts.item(i).value!=""){
			checkAttend_util.apartmentList.push({"value":opts.item(i).value, "text":opts.item(i).innerHTML});
		}
	}
	checkAttend_util.unitformatter2 = function(value, rowData, rowIndex) {
		for (var i = 0; i <apartmentSize; i++) {
			if (checkAttend_util.apartmentList[i].value == value) {
				return checkAttend_util.apartmentList[i].text;
			}
		}
	}
	
	//编辑记录
	checkAttend_util.editrow = function(target) {
		$('#checkAttend_datagrid').datagrid('selectRow', checkAttend_util.getRowIndex(target));
		$('#checkAttend_datagrid').datagrid('beginEdit', checkAttend_util.getRowIndex(target));
	}
	
	//新增记录
	checkAttend_util.insert=function(){
		$('#checkAttend_datagrid').datagrid('insertRow', {
			index: 0,
			row:{
			}
		});
		
		$('#checkAttend_datagrid').datagrid('selectRow',0);
		$('#checkAttend_datagrid').datagrid('beginEdit',0);
	}
	
	//删除记录
	checkAttend_util.deleterow = function(target) {
		var choosen_rows = checkAttend_util.grid.datagrid('getSelections').length;
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
				var row = $('#checkAttend_datagrid').datagrid('getSelected');
				var clientRequest = new Tcsp.ClientRequest();
				var params = new Object();
				params.id = row.id;
				var url = managerPath + "/manager/staff/CheckAttendMgr.json?m=execute&f=checkAttendRemove";
				clientRequest.postJSON(url,params,function(respData) {
					if (respData.result) {
						if (respData.result.success && respData.result.errorCode == '00000') {
							$('#checkAttend_datagrid').datagrid('reload');
							$('#checkAttend_datagrid').datagrid('clearSelections');
						} else {
							$.messager.alert("操作提示",respData.result.errorMsg);
						}
					}
				});
			}
		});
	}

	//多选的情况下才会用到
	checkAttend_util.getSelectedArr = function(dom) {
		var ids = [];
		var rows = $('#' + dom).datagrid('getSelections');
		for (var i = 0; i < rows.length; i++) {
			ids.push(rows[i].id);
		}
		return ids;
	}

	//保存记录
	checkAttend_util.saverow=function(target){
		var index=checkAttend_util.getRowIndex(target);
		$('#checkAttend_datagrid').datagrid('endEdit',index);
		$('#checkAttend_datagrid').datagrid('selectRow',index);
		var row = $('#checkAttend_datagrid').datagrid('getSelected');
		if (row){
			var clientRequest = new Tcsp.ClientRequest();
			var params = new Object();
			params.id = row.id;
			params.staffId = row.staffId;
			params.staffName = row.staffName;
			params.checkMonth = row.checkMonth;
			params.absenceCount = row.absenceCount;
			params.apartment = row.apartment;
			params.post = row.post;
			var action='Add';
			if(typeof(params.staffId)=='undefined'||params.staffId==''){
				$.messager.alert("操作提示",'员工工号不能为空');
				return;
			}
			if(typeof(params.staffName)=='undefined'||params.staffName==''){
				$.messager.alert("操作提示",'员工姓名不能为空');
				return;
			}
			if(typeof(params.checkMonth)=='undefined'||params.checkMonth==''){
				$.messager.alert("操作提示",'月份不能为空');
				return;
			}
			if(typeof(params.absenceCount)=='undefined'||params.absenceCount==''){
				$.messager.alert("操作提示",'出勤记录不能为空');
				return;
			}
			if(params.id){
				action='Edit';
			}else{
				action='Add';
			}
			var url = managerPath + "/manager/staff/CheckAttendMgr.json?m=execute&f=checkAttend"+action;
			clientRequest.postJSON(url,params,function(respData){
				  if(respData.result){
					  if(respData.result.success&&respData.result.errorCode=='00000'){
						  $('#checkAttend_datagrid').datagrid('reload');
						  $('#checkAttend_datagrid').datagrid('clearSelections');
					  }else{
						  $.messager.alert("操作提示",respData.result.errorMsg);
					  }
				  }
			});
		}
	}
	
	//取消编辑
	checkAttend_util.cancelrow = function(target) {
		$('#checkAttend_datagrid').datagrid('cancelEdit',checkAttend_util.getRowIndex(target));
	}
	
	checkAttend_util.updateActions = function(index) {
		
		$('#checkAttend_datagrid').datagrid('updateRow', {
			index : index,
			row : {}
		});
	}
	
	checkAttend_util.templateData = eval('[${result.dataSet.json}]');
	checkAttend_util.grid = $('#checkAttend_datagrid').datagrid({
		iconCls : 'icon-edit',
		//width:'auto',
		height : 'auto',
		nowrap : false,
		striped : true,
		border : true,
		collapsible : false,//是否可折叠的  
		fitColumns : true,//自动大小  
		queryParams : checkAttend_util.params,
		url : checkAttend_util.queryUrl,
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
				{title: '工号',field: 'staffId',width:60,sortable: true,align:'left',editor:'text'},
				{title: '姓名',field: 'staffName',width:60,sortable: true,align:'left',editor:'text'},
				{title: '部门',field: 'apartment',width:80,sortable: true,align:'left',
					formatter:checkAttend_util.unitformatter2
				},
				{title: '职务',field: 'post',width:80,sortable: true,align:'left'},
				{title: '月份',field: 'checkMonth',width:80,sortable: true,align:'left',
					formatter:checkAttend_util.unitformatter1,
					editor:{
						type:'combobox',
						options:{
							data: checkAttend_util.isMonth,
							valueField: "value",
							textField: "text"
						}
					}
				},
				{title: '缺勤记录',field: 'absenceCount',width:80,sortable: true,align:'left',editor:'text'},
				{title: '操作',field:'操作',sortable: false,align:'center',width:80,
		            formatter:function(value,row,index){
		            	if (row.editing){
							var s = '<a href="#" onclick="checkAttend_util.saverow(this)">保存</a> ';
							var c = '<a href="#" onclick="checkAttend_util.cancelrow(this)">取消</a>';
							return s+c;
						} else {
							var e = '<a href="#" onclick="checkAttend_util.editrow(this)">编辑</a> ';
							return e;
						}
		           }
		        } 
		] ],
		onBeforeEdit : function(index, row) {
			row.editing = true;
			checkAttend_util.updateActions(index);
		},
		onAfterEdit : function(index, row) {
			row.editing = false;
			checkAttend_util.updateActions(index);
		},
		onCancelEdit : function(index, row) {
			row.editing = false;
			checkAttend_util.updateActions(index);
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
	        handler: checkAttend_util.insert
		},'-',{
			text : '删除',
			iconCls : 'icon-remove',
			handler : checkAttend_util.deleterow
	    } ],
		pageList : [ 10,20,30 ],
		pageSize : 10

	});
	
	//查询
	$("#checkAttendSearch").click(function() {
		checkAttend_util.params.checkAttendId = $("#checkAttendId").val();
		checkAttend_util.params.checkAttendName = $("#checkAttendName").val();
		checkAttend_util.params.checkAttendApartment = $("#checkAttend_apartment").combobox('getValue');
		checkAttend_util.params.checkAttendMonth = $("#checkAttend_month").combobox('getValue');
		$('#checkAttend_datagrid').datagrid('cancelEdit', 0);
		$('#checkAttend_datagrid').datagrid('reload');
	});
	
</script>
