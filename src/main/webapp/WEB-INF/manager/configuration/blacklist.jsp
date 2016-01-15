<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>


<div class="chaxun_cont">
 <ul>
<!--   <li><span>模板名称：</span><input  name="name" class="easyui-textbox" ></li>
 --> </ul>
<!--  <div class="butt_cont"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">导出</a></div>
 --></div>

<div style="margin:10px;">

	<table id="black-datagrid"></table>
</div>

<div id="black-mytoolbar" style="height:auto"></div>


<script type="text/javascript">
var black_util=black_util||{} ;


black_util.formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
	};
black_util.getRowIndex=function(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
black_util.editrow=function(target){
	$('#black-datagrid').datagrid('selectRow',black_util.getRowIndex(target));
	$('#black-datagrid').datagrid('beginEdit', black_util.getRowIndex(target));
}
black_util.deleterow=function(target){
	var choosen_rows = black_util.grid.datagrid('getSelections').length;
	if(choosen_rows==0){
		var dlg=$.messager.alert({title: "操作提示", msg: "请选择您要删除的记录！"});
		setTimeout(function(){
		    dlg.window('close');
		}, 5000);
		return;
	}
	$.messager.confirm('操作提示','确定后数据不可恢复,确认删除吗?',function(confirm_status){
		if (confirm_status){
			var row = $('#black-datagrid').datagrid('getSelected');
			var clientRequest = new Tcsp.ClientRequest();
			var params = new Object();
			params.black_id = row.id;
			var url = managerPath + "/manager/configuration/ConfigMgr.json?m=execute&f=deleteBlackList";
			clientRequest.postJSON(url,params,function(respData){
				  if(respData.result){
					  if(respData.result.success&&respData.result.errorCode=='00000'){
						  $('#black-datagrid').datagrid('reload');
					  }else{
						  $.messager.alert("操作提示",respData.result.errorMsg);
					  }
				  }
			});
		}
	});
}
black_util.saverow=function(target){
	var index=black_util.getRowIndex(target);
	$('#black-datagrid').datagrid('endEdit',index);
	$('#black-datagrid').datagrid('selectRow',index);
	var row = $('#black-datagrid').datagrid('getSelected');
	if (row){
		var clientRequest = new Tcsp.ClientRequest();
		var params = new Object();
		params.black_id = row.id;
		params.black_cityId = row.cityId;
		params.black_credentialsType = row.credentialsType;
		params.black_credentialsCode = row.credentialsCode;
		params.black_custId = row.custId;
		params.black_state = row.state;
		params.black_refundCount = row.refundCount;
		params.black_beginTime = row.beginTime;
		params.black_endTime = row.endTime;
		params.black_remark = row.remark;
		params.black_createStaffId = row.createStaffId;
		params.black_updateStaffId = row.updateStaffId;
		params.black_createTime = row.createTime;
		params.black_updateTime = row.updateTime;
		var action='create';
		if(params.black_id){
			action='update';
		}
		var url = managerPath + "/manager/configuration/ConfigMgr.json?m=execute&f="+action+"BlackList";
		clientRequest.postJSON(url,params,function(respData){
			  if(respData.result){
				  if(respData.result.success&&respData.result.errorCode=='00000'){
					  $('#black-datagrid').datagrid('reload');
				  }else{
					  $.messager.alert("操作提示",respData.result.errorMsg);
				  }
			  }
		});
	}
}
black_util.cancelrow=function(target){
	$('#black-datagrid').datagrid('cancelEdit', black_util.getRowIndex(target));
}
black_util.insert=function(){
	var row = $('#black-datagrid').datagrid('getSelected');
	var index=$('#black-datagrid').datagrid('getData').rows.length;
	if (row){
		if(row.editing){
			$.messager.alert('操作提示','请先保存后,再新建模板');
			return;
		}
		var index = $('#black-datagrid').datagrid('getRowIndex', row);
	} else {
		index = 0;
	}
	$('#black-datagrid').datagrid('insertRow', {
		index: 0,
		row:{
			BEGIN_TIME:black_util.formatterDate(new Date()),
			END_TIME:'2099-12-31'
		}
	});
	$('#black-datagrid').datagrid('selectRow',0);
	$('#black-datagrid').datagrid('beginEdit',0);
}
black_util.show_attr=function(v){
	OpenTab('center_tabs','模板属性管理',managerPath+'/manager/configuration/ConfigMgr.json?m=query&f=allBlackList','icon-munich-collaboration');
};
black_util.updateActions=function(index){
	$('#black-datagrid').datagrid('updateRow',{
		index: index,
		row:{}
	});
}


		
			black_util.grid=$('#black-datagrid').datagrid({  
			    iconCls:'icon-edit',
			    height: 'auto',  
			    nowrap: false,  
			    striped: true,  
			    border: true,  
			    collapsible:false,//是否可折叠的  
			    fitColumns: false,//自动大小  
			    url: managerPath+'/manager/configuration/ConfigMgr.json?m=query&f=allBlackList', 
			    loadMsg:'数据加载中...',
			    remoteSort:false,   
			    idField:'ID',  
			    singleSelect:true,//是否单选  
			    pagination:true,//分页控件  
			    rownumbers:true,//行号   
			    frozenColumns:[[]],
			    columns: [[
			    		{field: 'ck',checkbox:true},
			        	{title: '地市编号',field: 'cityId',width:60,sortable:true,align:'center',editor:'text'},
			        	{title: '证件类型',field: 'credentialsType',width:60,sortable:true,align:'center',editor:'text'},
			        	{title: '证件号码',field: 'credentialsCode',width:60,sortable:true,align:'center',editor:'text'},
			        	{title: '用户ID',field: 'custId',width:60,sortable:true,align:'center',editor:{type:'numberbox',options:{precision:0}}},
			        	{title: '状态',field: 'state',width:60,sortable:true,align:'center'},
			        	{title: '违规次数',field: 'refundCount',width:120,sortable:true,align:'center',editor:{type:'numberbox',options:{precision:0}}},
			        	{title: '生效时间',field: 'beginTime',width:120,sortable:true,align:'center',editor:'datebox'},
			        	{title: '失效时间',field: 'endTime',width:120,sortable:true,align:'center',editor:'datebox'},
			        	{title: '备注',field: 'remark',width:120,sortable:true,align:'center',editor:'text'},  
			        	{title: '创建人员',field: 'createStaffId',width:120,sortable:true,align:'center',editor:'text'},
			        	{title: '更新人员',field: 'updateStaffId',width:120,sortable:true,align:'center',editor:'text'},
			        	{title: '创建时间',field: 'createTime',width:120,sortable:true,align:'center'},
			        	{title: '更新时间',field: 'updateTime',width:120,sortable:true,align:'center'},
			        	{title: '模板操作',field:'查看属性',sortable:true,align:'center',width:100,
			                formatter:function(value,row,index){
			                	if (row.editing){
									var s = '<a href="#" onclick="black_util.saverow(this)">保存</a> ';
									var c = '<a href="#" onclick="black_util.cancelrow(this)">取消</a>';
									return s+c;
								} else {
									var e = '<a href="#" onclick="black_util.editrow(this)">编辑</a> ';
									var d = '<a href="#" onclick="black_util.deleterow(this)">删除</a>';d='';
									return e+d;
								}
			               }
			            }
			    	]],
			    	onBeforeEdit:function(index,row){
						row.editing = true;
						black_util.updateActions(index);
					},
					onAfterEdit:function(index,row){
						row.editing = false;
						black_util.updateActions(index);
					},
					onCancelEdit:function(index,row){
						row.editing = false;
						black_util.updateActions(index);
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
			        handler: black_util.insert
			    }],
			    pageList: [10,20,30],
			    pageSize: 10
			
			});


function taskdetail(){}
</script>