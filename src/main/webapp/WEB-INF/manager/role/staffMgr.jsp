<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>
<input id = "isProviManager" value="${result.dataSet.isProManager }" type="hidden"/>
<input id = "staff_cityCode" value="${result.dataSet.code }" type="hidden"/>
<input id = "staff_cityName" value="${result.dataSet.name }" type="hidden"/>

<form name="staffForm" method="post" action="#" target="newwindow">
	<div class="chaxun_cont">
		<ul>
			<li><span>员工工号：</span><input  type="text" id="staff_id" name="staffId" value="" class="easyui-textbox" ></li>
  			<li><span>姓名：</span><input  type="text" id="staff_name" name="name" value="" class="easyui-textbox" ></li>
  			<li>
			  	<span>地市：</span>
			  	<select class="easyui-combobox" id="staff_areaCode" name="staff_areaCode" style="width:80px;">
					<c:forEach var="entry" items="${result.dataSet.area}">
						<c:if test="${entry.key=='000'}"><option value="${entry.key}" selected="selected">${entry.value}</option></c:if>
						<c:if test="${entry.key!='000'}"><option value="${entry.key}">${entry.value}</option></c:if>
					</c:forEach>
					<c:forEach var="entry" items="${result.dataSet.area}">
						<c:if test="${entry.key=='000'}"><option value="" selected="selected">全部</option></c:if>
					</c:forEach>
				</select>
			</li>
		</ul>
		<div class="butt_cont">
			<a href="#" id="staff_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">查询</a> 
		</div>
	</div>
</form>

<div style="margin: 10px;">
	<table id="staff-datagrid"></table>
</div>

<script type="text/javascript">
	var staff_util = staff_util || {};
	staff_util.params = new Object();
	staff_util.queryUrl = managerPath + '/manager/sysman/StaffMgr.json?m=query&f=staffQuery';
	staff_util.getRowIndex = function(target) {
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	staff_util.isProManager = $("#isProviManager").val();
	if(staff_util.isProManager == "yes"){
		staff_util.areaCode = [
			{ "value": "000", "text": "山东" },
			{ "value": "001", "text": "淄博" },
			{ "value": "002", "text": "滨州" },
			{ "value": "003", "text": "临沂" },
			{ "value": "004", "text": "日照" },
			{ "value": "005", "text": "潍坊" },
			{ "value": "006", "text": "东营" },
			{ "value": "007", "text": "枣庄" },
			{ "value": "008", "text": "济宁" },
			{ "value": "009", "text": "菏泽" },
			{ "value": "010", "text": "莱芜" },
			{ "value": "011", "text": "烟台" },
			{ "value": "012", "text": "青岛" },
			{ "value": "013", "text": "济南" },
			{ "value": "014", "text": "泰安" },
			{ "value": "015", "text": "德州" },
			{ "value": "016", "text": "聊城" },
			{ "value": "017", "text": "威海" }
	    ];
	} else {
		var code = $('#staff_cityCode').val();
		var name = $('#staff_cityName').val();
		staff_util.areaCode = [
			{ "value": code, "text": name }
	    ];
	}
	staff_util.unitformatter = function(value, rowData, rowIndex) {
	    for (var i = 0; i <staff_util.areaCode.length; i++) {
	        if (staff_util.areaCode[i].value == value) {
	            return staff_util.areaCode[i].text;
	        }
	    }
	}
	//编辑记录
	staff_util.editrow = function(target) {
		$('#staff-datagrid').datagrid('selectRow', staff_util.getRowIndex(target));
		$('#staff-datagrid').datagrid('beginEdit', staff_util.getRowIndex(target));
	}
	
	//新增记录
	staff_util.insert=function(){
		$('#staff-datagrid').datagrid('insertRow', {
			index: 0,
			row:{
			}
		});
		
		$('#staff-datagrid').datagrid('selectRow',0);
		$('#staff-datagrid').datagrid('beginEdit',0);
	}
	
	//删除记录
	staff_util.deleterow = function(target) {
		var choosen_rows = staff_util.grid.datagrid('getSelections').length;
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
				var row = $('#staff-datagrid').datagrid('getSelected');
				var clientRequest = new Tcsp.ClientRequest();
				var params = new Object();
				params.id = row.id;
				var url = managerPath + "/manager/sysman/StaffMgr.json?m=execute&f=staffRemove";
				clientRequest.postJSON(url,params,function(respData) {
					if (respData.result) {
						if (respData.result.success && respData.result.errorCode == '00000') {
							$('#staff-datagrid').datagrid('reload');
							$('#staff-datagrid').datagrid('clearSelections');
						} else {
							$.messager.alert("操作提示",respData.result.errorMsg);
						}
					}
				});
			}
		});
	}

	staff_util.getSelectedArr = function(dom) {
		var ids = [];
		var rows = $('#' + dom).datagrid('getSelections');
		for (var i = 0; i < rows.length; i++) {
			ids.push(rows[i].id);
		}
		return ids;
	}

	//保存记录
	staff_util.saverow=function(target){
		var index=staff_util.getRowIndex(target);
		$('#staff-datagrid').datagrid('endEdit',index);
		$('#staff-datagrid').datagrid('selectRow',index);
		var row = $('#staff-datagrid').datagrid('getSelected');
		if (row){
			var clientRequest = new Tcsp.ClientRequest();
			var params = new Object();
			params.id = row.id;
			params.staffId = row.staffId;
			params.name = row.name;
			params.areaCode = row.areaCode;
			params.phoneNumber = row.phoneNumber;
			params.email = row.email;
			/* params.updateStaff = row.updateStaff;
			params.updateTime = row.updateTime; */
			var action='Add';
			if(typeof(params.staffId)=='undefined'||params.staffId==''){
				$.messager.alert("操作提示",'员工工号不能为空');
				return;
			}
			if(typeof(params.areaCode)=='undefined'||params.areaCode==''){
				$.messager.alert("操作提示",'地市不能为空');
				return;
			}
			if(params.id){
				action='Edit';
			}else{
				action='Add';
			}
			var url = managerPath + "/manager/sysman/StaffMgr.json?m=execute&f=staff"+action;
			clientRequest.postJSON(url,params,function(respData){
				  if(respData.result){
					  if(respData.result.success&&respData.result.errorCode=='00000'){
						  $('#staff-datagrid').datagrid('reload');
						  $('#staff-datagrid').datagrid('clearSelections');
						  if(action == 'Add'){
							  $.messager.alert("操作提示",' 新增员工成功，初始密码为qw12!@  <br/>  请先为该新增工号赋权并告知员工登陆后立即修改密码！');
						  }
						  if(action == 'Edit'){
							  $.messager.alert("操作提示",'员工信息更新成功');
						  }
					  }else{
						  $.messager.alert("操作提示",respData.result.errorMsg);
					  }
				  }
			});
		}
	}
	
	//取消编辑
	staff_util.cancelrow = function(target) {
		$('#staff-datagrid').datagrid('cancelEdit',staff_util.getRowIndex(target));
	}
	
	staff_util.updateActions = function(index) {
		
		$('#staff-datagrid').datagrid('updateRow', {
			index : index,
			row : {}
		});
	}
	
	staff_util.templateData = eval('[${result.dataSet.json}]');
	staff_util.grid = $('#staff-datagrid').datagrid({
		iconCls : 'icon-edit',
		//width:'auto',
		height : 'auto',
		nowrap : false,
		striped : true,
		border : true,
		collapsible : false,//是否可折叠的  
		fitColumns : true,//自动大小  
		queryParams : staff_util.params,
		url : staff_util.queryUrl,
		loadMsg : '数据加载中...',
		remoteSort : false,
		idField : 'id',
		singleSelect : true,//是否单选  
		pagination : true,//分页控件  
		rownumbers : true,//行号   
		frozenColumns : [ [] ],
		columns : [ [ 
				{field: 'ck',checkbox:true},
				{title: '员工ID',field: 'id',width:80,sortable:false,align:'center'},
				{title: '员工工号',field: 'staffId',width:80,sortable: true,align:'left',editor:'text'},
				{title: '姓名',field: 'name',width:80,sortable: true,align:'left',editor:'text'},
				{title: '地市',field: 'areaCode',width:100,sortable: false,align:'center',
					formatter:staff_util.unitformatter,
					editor: { 
	    				type: 'combobox', 
	    				options: { 
			    					data: staff_util.areaCode, 
			    					valueField: "value", 
			    					textField: "text" 
	    						} 
	    					},
	    					
	    		},
				{title: '联系电话', field: 'phoneNumber', width:80,sortable: false,align:'center',editor:'text'},
				{title: 'EMAIL', field: 'email', width:100,sortable: false,align:'center',editor:'text'},
				{title: '更新员工', field: 'updateStaffId', width:60,sortable: false,align:'center'},
				{title: '更新时间', field: 'updateTime', width:120,sortable: false,align:'center'},
				{title: '操作',field:'操作',sortable: false,align:'center',width:80,
		            formatter:function(value,row,index){
		            	if (row.editing){
							var s = '<a href="#" onclick="staff_util.saverow(this)">保存</a> ';
							var c = '<a href="#" onclick="staff_util.cancelrow(this)">取消</a>';
							return s+c;
						} else {
							var e = '<a href="#" onclick="staff_util.editrow(this)">编辑</a> ';
							return e;
						}
		           }
		        } 
		] ],
		onBeforeEdit : function(index, row) {
			row.editing = true;
			staff_util.updateActions(index);
		},
		onAfterEdit : function(index, row) {
			row.editing = false;
			staff_util.updateActions(index);
		},
		onCancelEdit : function(index, row) {
			row.editing = false;
			staff_util.updateActions(index);
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
	        handler: staff_util.insert
		},'-',{
			text : '删除',
			iconCls : 'icon-remove',
			handler : staff_util.deleterow
	    } ],
		pageList : [ 10,20,30 ],
		pageSize : 10

	});
	
	//查询
	$("#staff_search_btn").click(function() {
		staff_util.params.staffId = $("#staff_id").val();
		staff_util.params.name = $("#staff_name").val();
		staff_util.params.areaCode = $("#staff_areaCode").combobox('getValue');
		$('#staff-datagrid').datagrid('cancelEdit', 0);
		$('#staff-datagrid').datagrid('reload');
	});
	
</script>
