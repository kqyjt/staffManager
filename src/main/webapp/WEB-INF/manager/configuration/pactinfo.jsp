<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>


<div class="chaxun_cont">
 <ul>
<!--   <li><span>模板名称：</span><input  name="name" class="easyui-textbox" ></li>
 --> </ul>
<!--  <div class="butt_cont"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">导出</a></div>
 --></div>

<div style="margin:10px;">

	<table id="pact-datagrid"></table>
</div>

<div id="pact-mytoolbar" style="height:auto"></div>



<script type="text/javascript">
var pact_util=pact_util||{} ;
pact_util.formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
	};
pact_util.getRowIndex=function(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
pact_util.editrow=function(target){
	$('#pact-datagrid').datagrid('selectRow',pact_util.getRowIndex(target));
	$('#pact-datagrid').datagrid('beginEdit', pact_util.getRowIndex(target));
}
pact_util.deleterow=function(target){
	var choosen_rows = pact_util.grid.datagrid('getSelections').length;
	if(choosen_rows==0){
		var dlg=$.messager.alert({title: "操作提示", msg: "请选择您要删除的记录！"});
		setTimeout(function(){
		    dlg.window('close');
		}, 5000);
		return;
	}
	$.messager.confirm('操作提示','确定后数据不可恢复,确认删除吗?',function(confirm_status){
		if (confirm_status){
			var row = $('#pact-datagrid').datagrid('getSelected');
			var clientRequest = new Tcsp.ClientRequest();
			var params = new Object();
			params.pact_id = row.id;
			var url = managerPath + "/manager/configuration/ConfigMgr.json?m=execute&f=deletePactInfo";
			clientRequest.postJSON(url,params,function(respData){
				  if(respData.result){
					  if(respData.result.success&&respData.result.errorCode=='00000'){
						  $('#pact-datagrid').datagrid('reload');
					  }else{
						  $.messager.alert("操作提示",respData.result.errorMsg);
					  }
				  }
			});
		}
	});
}
pact_util.saverow=function(target){
	var index=pact_util.getRowIndex(target);
	$('#pact-datagrid').datagrid('endEdit',index);
	$('#pact-datagrid').datagrid('selectRow',index);
	var row = $('#pact-datagrid').datagrid('getSelected');
	if (row){
		var clientRequest = new Tcsp.ClientRequest();
		var params = new Object();
		params.pact_id = row.id;
		params.pact_pactType = row.pactType;
		params.pact_pactCode = row.pactCode;
		params.pact_cityId = row.cityId;
		params.pact_pactContent = row.pactContent;
		params.pact_remark = row.remark;
		params.pact_createStaffId = row.createStaffId;
		params.pact_updateStaffId = row.updateStaffId;
		params.pact_createTime = row.createTime;
		params.pact_updateTime = row.updateTime;
		var action='create';
		if(params.pact_id){
			action='update';
		}
		var url = managerPath + "/manager/configuration/ConfigMgr.json?m=execute&f="+action+"PactInfo";
		clientRequest.postJSON(url,params,function(respData){
			  if(respData.result){
				  if(respData.result.success&&respData.result.errorCode=='00000'){
					  $('#pact-datagrid').datagrid('reload');
				  }else{
					  $.messager.alert("操作提示",respData.result.errorMsg);
				  }
			  }
		});
	}
}
pact_util.cancelrow=function(target){
	$('#pact-datagrid').datagrid('cancelEdit', pact_util.getRowIndex(target));
}
pact_util.insert=function(){
	var row = $('#pact-datagrid').datagrid('getSelected');
	var index=$('#pact-datagrid').datagrid('getData').rows.length;
	if (row){
		if(row.editing){
			$.messager.alert('操作提示','请先保存后,再新建模板');
			return;
		}
		var index = $('#pact-datagrid').datagrid('getRowIndex', row);
	} else {
		index = 0;
	}
	$('#pact-datagrid').datagrid('insertRow', {
		index: 0,
		row:{
			BEGIN_TIME:pact_util.formatterDate(new Date()),
			END_TIME:'2099-12-31'
		}
	});
	$('#pact-datagrid').datagrid('selectRow',0);
	$('#pact-datagrid').datagrid('beginEdit',0);
}
pact_util.show_attr=function(v){
	OpenTab('center_tabs','模板属性管理',managerPath+'/manager/configuration/ConfigMgr.json?m=query&f=allPactInfo','icon-munich-collaboration');
};
pact_util.updateActions=function(index){
	$('#pact-datagrid').datagrid('updateRow',{
		index: index,
		row:{}
	});
}



		pact_util.grid=$('#pact-datagrid').datagrid({  
		    iconCls:'icon-edit',
		    height: 'auto',  
		    nowrap: false,  
		    striped: true,  
		    border: true,  
		    collapsible:false,//是否可折叠的  
		    fitColumns: false,//自动大小  
		    url: managerPath+'/manager/configuration/ConfigMgr.json?m=query&f=allPactInfo', 
		    loadMsg:'数据加载中...',
		    remoteSort:false,   
		    idField:'ID',  
		    singleSelect:true,//是否单选  
		    pagination:true,//分页控件  
		    rownumbers:true,//行号   
		    frozenColumns:[[]],
		    columns: [[
		    		{field: 'ck',checkbox:true},
		        	{title: '协议类型',field: 'pactType',width:80,sortable: true,align:'center',editor:'text'},
		        	{title: '协议编号',field: 'pactCode',width:80,sortable: true,align:'center',editor:'text'},
		        	{title: '地市编号',field: 'cityId',width:80,sortable: true,align:'center',editor:'text'},
		        	{title: '协议内容',field: 'pactContent',width:80,sortable: true,align:'center',editor:'text'},
		        	{title: '备注',field: 'remark',width:80,sortable: true,align:'center',editor:'text'},
		        	{title: '创建人员',field: 'createStaffId',width:80,sortable: true,align:'center',editor:'text'},
		        	{title: '更新人员',field: 'updateStaffId',width:160,sortable: false,align:'center',editor:'text'},
		        	{title: '创建时间',field: 'createTime',width:160,sortable: true,align:'center'},
		        	{title: '更新时间',field: 'updateTime',width:160,sortable: false,align:'center'},
		        	{title: '模板操作',field:'查看属性',sortable: false,align:'center',width:100,
		                formatter:function(value,row,index){
		                	if (row.editing){
								var s = '<a href="#" onclick="pact_util.saverow(this)">保存</a> ';
								var c = '<a href="#" onclick="pact_util.cancelrow(this)">取消</a>';
								return s+c;
							} else {
								var e = '<a href="#" onclick="pact_util.editrow(this)">编辑</a> ';
								var d = '<a href="#" onclick="pact_util.deleterow(this)">删除</a>';d='';
								return e+d;
							}
		               }
		            }
		    	]],
		    	onBeforeEdit:function(index,row){
					row.editing = true;
					pact_util.updateActions(index);
				},
				onAfterEdit:function(index,row){
					row.editing = false;
					pact_util.updateActions(index);
				},
				onCancelEdit:function(index,row){
					row.editing = false;
					pact_util.updateActions(index);
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
		        handler: pact_util.insert
		    },'-',{
		        text: '删除',
		        iconCls: 'icon-remove',
		        handler: pact_util.deleterow
		    }],
		    pageList: [10,20,30],
		    pageSize: 10
		
		});

function taskdetail(){}
</script>