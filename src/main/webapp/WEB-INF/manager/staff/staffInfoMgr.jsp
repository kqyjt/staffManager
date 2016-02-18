<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>
<input id = "apartmentSize" value="${result.dataSet.apartmentSize }" type="hidden"/>

<form name="staffInfoForm" method="post" action="#" target="newwindow">
	<div class="chaxun_cont">
		<ul>
			<li><span>工号：</span><input  type="text" id="staffInfoId" name="staffInfoId" value="" class="easyui-textbox" ></li>
  			<li><span>姓名：</span><input  type="text" id="staffInfoName" name="staffInfoName" value="" class="easyui-textbox" ></li>
  			<li><span>身份证号：</span><input  type="text" id="staffIdCard" name="staffIdCard" value="" class="easyui-textbox" ></li>
  			<li>
			  	<span>部门：</span>
			  	<select class="easyui-combobox" id="staff_apartment" name="staff_apartment" style="width:80px;">
					<c:forEach var="entry" items="${result.dataSet.apartmentList}">
						<option value="${entry.apartId}" selected="selected">${entry.apartName}</option>
					</c:forEach>
					<option value="" selected="selected">全部</option>
				</select>
			</li>
		</ul>
		<div class="butt_cont">
			<a href="#" id="staffInfoSearch" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">查询</a> 
		</div>
	</div>
</form>

<div style="margin: 10px;">
	<table id="staffInfo_datagrid"></table>
</div>

<script type="text/javascript">
	var staffInfo_util = staffInfo_util || {};
	staffInfo_util.params = new Object();
	staffInfo_util.queryUrl = managerPath + '/manager/staff/StaffInfoMgr.json?m=query&f=staffInfoQuery';
	staffInfo_util.getRowIndex = function(target) {
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	
	//婚姻状况
	staffInfo_util.isMarriage = [
		{ "value": "00", "text": "已婚" },
		{ "value": "01", "text": "未婚" }
	];
	staffInfo_util.unitformatter1 = function(value, rowData, rowIndex) {
		for (var i = 0; i <staffInfo_util.isMarriage.length; i++) {
			if (staffInfo_util.isMarriage[i].value == value) {
				return staffInfo_util.isMarriage[i].text;
			}
		}
	}
	
	//部门
	staffInfo_util.apartmentList = [];
	var apartmentSize = $("#apartmentSize").val();
	var opts = document.getElementById("staff_apartment").childNodes;//获取option所有dom对象
	for(var i = 0 ; i < opts.length ; i++){
		if(opts.item(i).innerHTML!=undefined && opts.item(i).value!=""){
			staffInfo_util.apartmentList.push({"value":opts.item(i).value, "text":opts.item(i).innerHTML});
		}
	}
	staffInfo_util.unitformatter2 = function(value, rowData, rowIndex) {
		for (var i = 0; i <apartmentSize; i++) {
			if (staffInfo_util.apartmentList[i].value == value) {
				return staffInfo_util.apartmentList[i].text;
			}
		}
	}
	
	//编辑记录
	staffInfo_util.editrow = function(target) {
		$('#staffInfo_datagrid').datagrid('selectRow', staffInfo_util.getRowIndex(target));
		$('#staffInfo_datagrid').datagrid('beginEdit', staffInfo_util.getRowIndex(target));
	}
	
	//新增记录
	staffInfo_util.insert=function(){
		$('#staffInfo_datagrid').datagrid('insertRow', {
			index: 0,
			row:{
			}
		});
		
		$('#staffInfo_datagrid').datagrid('selectRow',0);
		$('#staffInfo_datagrid').datagrid('beginEdit',0);
	}
	
	//删除记录
	staffInfo_util.deleterow = function(target) {
		var choosen_rows = staffInfo_util.grid.datagrid('getSelections').length;
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
				var row = $('#staffInfo_datagrid').datagrid('getSelected');
				var clientRequest = new Tcsp.ClientRequest();
				var params = new Object();
				params.id = row.id;
				var url = managerPath + "/manager/staff/StaffInfoMgr.json?m=execute&f=staffInfoRemove";
				clientRequest.postJSON(url,params,function(respData) {
					if (respData.result) {
						if (respData.result.success && respData.result.errorCode == '00000') {
							$('#staffInfo_datagrid').datagrid('reload');
							$('#staffInfo_datagrid').datagrid('clearSelections');
						} else {
							$.messager.alert("操作提示",respData.result.errorMsg);
						}
					}
				});
			}
		});
	}

	//多选的情况下才会用到
	staffInfo_util.getSelectedArr = function(dom) {
		var ids = [];
		var rows = $('#' + dom).datagrid('getSelections');
		for (var i = 0; i < rows.length; i++) {
			ids.push(rows[i].id);
		}
		return ids;
	}

	//保存记录
	staffInfo_util.saverow=function(target){
		var index=staffInfo_util.getRowIndex(target);
		$('#staffInfo_datagrid').datagrid('endEdit',index);
		$('#staffInfo_datagrid').datagrid('selectRow',index);
		var row = $('#staffInfo_datagrid').datagrid('getSelected');
		if (row){
			var clientRequest = new Tcsp.ClientRequest();
			var params = new Object();
			params.id = row.id;
			params.staffInfoId = row.staffInfoId;
			params.staffInfoName = row.staffInfoName;
			params.sex = row.sex;
			params.nation = row.nation;
			params.idcard = row.idcard;
			params.birthday = row.birthday;
			params.address = row.address;
			params.birthplace = row.birthplace;
			params.diploma = row.diploma;
			params.graduation = row.graduation;
			params.special = row.special;
			params.marriage = row.marriage;
			params.apartment = row.apartment;
			params.post = row.post;
			var action='Add';
			if(typeof(params.staffInfoId)=='undefined'||params.staffInfoId==''){
				$.messager.alert("操作提示",'员工工号不能为空');
				return;
			}
			if(typeof(params.staffInfoName)=='undefined'||params.staffInfoName==''){
				$.messager.alert("操作提示",'员工姓名不能为空');
				return;
			}
			if(typeof(params.sex)=='undefined'||params.sex==''){
				$.messager.alert("操作提示",'员工性别不能为空');
				return;
			}
			if(typeof(params.nation)=='undefined'||params.nation==''){
				$.messager.alert("操作提示",'员工民族不能为空');
				return;
			}
			if(typeof(params.idcard)=='undefined'||params.idcard==''){
				$.messager.alert("操作提示",'员工身份证号不能为空');
				return;
			}
			if(typeof(params.birthday)=='undefined'||params.birthday==''){
				$.messager.alert("操作提示",'员工生日不能为空');
				return;
			}
			if(typeof(params.address)=='undefined'||params.address==''){
				$.messager.alert("操作提示",'员工家庭住址不能为空');
				return;
			}
			if(typeof(params.birthplace)=='undefined'||params.birthplace==''){
				$.messager.alert("操作提示",'员工籍贯不能为空');
				return;
			}
			if(typeof(params.diploma)=='undefined'||params.diploma==''){
				$.messager.alert("操作提示",'员工学历不能为空');
				return;
			}
			if(typeof(params.graduation)=='undefined'||params.graduation==''){
				$.messager.alert("操作提示",'员工毕业院校不能为空');
				return;
			}
			if(typeof(params.special)=='undefined'||params.special==''){
				$.messager.alert("操作提示",'员工专业不能为空');
				return;
			}
			if(typeof(params.marriage)=='undefined'||params.marriage==''){
				$.messager.alert("操作提示",'员工婚姻状况不能为空');
				return;
			}
			if(typeof(params.apartment)=='undefined'||params.apartment==''){
				$.messager.alert("操作提示",'员工部门不能为空');
				return;
			}
			if(typeof(params.post)=='undefined'||params.post==''){
				$.messager.alert("操作提示",'员工职位不能为空');
				return;
			}
			if(params.id){
				action='Edit';
			}else{
				action='Add';
			}
			var url = managerPath + "/manager/staff/StaffInfoMgr.json?m=execute&f=staffInfo"+action;
			clientRequest.postJSON(url,params,function(respData){
				  if(respData.result){
					  if(respData.result.success&&respData.result.errorCode=='00000'){
						  $('#staffInfo_datagrid').datagrid('reload');
						  $('#staffInfo_datagrid').datagrid('clearSelections');
					  }else{
						  $.messager.alert("操作提示",respData.result.errorMsg);
					  }
				  }
			});
		}
	}
	
	//取消编辑
	staffInfo_util.cancelrow = function(target) {
		$('#staffInfo_datagrid').datagrid('cancelEdit',staffInfo_util.getRowIndex(target));
	}
	
	staffInfo_util.updateActions = function(index) {
		
		$('#staffInfo_datagrid').datagrid('updateRow', {
			index : index,
			row : {}
		});
	}
	
	staffInfo_util.templateData = eval('[${result.dataSet.json}]');
	staffInfo_util.grid = $('#staffInfo_datagrid').datagrid({
		iconCls : 'icon-edit',
		//width:'auto',
		height : 'auto',
		nowrap : false,
		striped : true,
		border : true,
		collapsible : false,//是否可折叠的  
		fitColumns : false,//自动大小  
		queryParams : staffInfo_util.params,
		url : staffInfo_util.queryUrl,
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
				{title: '工号',field: 'staffInfoId',width:60,sortable: true,align:'left',editor:'text'},
				{title: '姓名',field: 'staffInfoName',width:60,sortable: true,align:'left',editor:'text'},
				{title: '性别',field: 'sex',width:60,sortable: true,align:'left',editor:'text'},
				{title: '民族',field: 'nation',width:60,sortable: true,align:'left',editor:'text'},
				{title: '身份证号',field: 'idcard',width:140,sortable: true,align:'left',editor:'text'},
				{title: '出生年月',field: 'birthday',width:80,sortable: true,align:'left',editor:'datebox'},
				{title: '家庭住址',field: 'address',width:150,sortable: true,align:'left',editor:'text'},
				{title: '籍贯',field: 'birthplace',width:150,sortable: true,align:'left',editor:'text'},
				{title: '学历',field: 'diploma',width:60,sortable: true,align:'left',editor:'text'},
				{title: '毕业院校',field: 'graduation',width:110,sortable: true,align:'left',editor:'text'},
				{title: '专业',field: 'special',width:80,sortable: true,align:'left',editor:'text'},
				{title: '婚姻状况',field: 'marriage',width:80,sortable: true,align:'left',
					formatter:staffInfo_util.unitformatter1,
					editor:{
						type:'combobox',
						options:{
							data: staffInfo_util.isMarriage,
							valueField: "value",
							textField: "text"
						}
					}
				},
				{title: '部门',field: 'apartment',width:80,sortable: true,align:'left',
					formatter:staffInfo_util.unitformatter2,
					editor:{
						type:'combobox',
						options:{
							data: staffInfo_util.apartmentList,
							valueField: "value",
							textField: "text"
						}
					}
				},
				{title: '职务',field: 'post',width:80,sortable: true,align:'left',editor:'text'},
				{title: '创建时间', field: 'createtime', width:140,sortable: false,align:'left'},
				{title: '更新时间', field: 'updatetime', width:140,sortable: false,align:'left'},
				{title: '操作',field:'操作',sortable: false,align:'center',width:80,
		            formatter:function(value,row,index){
		            	if (row.editing){
							var s = '<a href="#" onclick="staffInfo_util.saverow(this)">保存</a> ';
							var c = '<a href="#" onclick="staffInfo_util.cancelrow(this)">取消</a>';
							return s+c;
						} else {
							var e = '<a href="#" onclick="staffInfo_util.editrow(this)">编辑</a> ';
							return e;
						}
		           }
		        } 
		] ],
		onBeforeEdit : function(index, row) {
			row.editing = true;
			staffInfo_util.updateActions(index);
		},
		onAfterEdit : function(index, row) {
			row.editing = false;
			staffInfo_util.updateActions(index);
		},
		onCancelEdit : function(index, row) {
			row.editing = false;
			staffInfo_util.updateActions(index);
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
	        handler: staffInfo_util.insert
		},'-',{
			text : '删除',
			iconCls : 'icon-remove',
			handler : staffInfo_util.deleterow
	    } ],
		pageList : [ 10,20,30 ],
		pageSize : 10

	});
	
	//查询
	$("#staffInfoSearch").click(function() {
		staffInfo_util.params.staffInfoId = $("#staffInfoId").val();
		staffInfo_util.params.staffInfoName = $("#staffInfoName").val();
		staffInfo_util.params.staffIdCard = $("#staffIdCard").val();
		staffInfo_util.params.staffApartment = $("#staff_apartment").combobox('getValue');
		$('#staffInfo_datagrid').datagrid('cancelEdit', 0);
		$('#staffInfo_datagrid').datagrid('reload');
	});
	
</script>
