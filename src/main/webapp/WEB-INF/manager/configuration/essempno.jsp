<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>



<div class="chaxun_cont">
 <ul>
<!--   <li><span>模板名称：</span><input  name="name" class="easyui-textbox" ></li>
 --> </ul>
<!--  <div class="butt_cont"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">导出</a></div>
 --></div>

<div style="margin:10px;">

	<table id="ess-datagrid"></table>
</div>

<div id="ess-mytoolbar" style="height:auto"></div>



<script type="text/javascript">
var ess_util=ess_util||{} ;
ess_util.formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
	};
ess_util.getRowIndex=function(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
ess_util.editrow=function(target){
	$('#ess-datagrid').datagrid('selectRow',ess_util.getRowIndex(target));
	$('#ess-datagrid').datagrid('beginEdit', ess_util.getRowIndex(target));
}
ess_util.deleterow=function(target){
	var choosen_rows = ess_util.grid.datagrid('getSelections').length;
	if(choosen_rows==0){
		var dlg=$.messager.alert({title: "操作提示", msg: "请选择您要删除的记录！"});
		setTimeout(function(){
		    dlg.window('close');
		}, 5000);
		return;
	}
	$.messager.confirm('操作提示','确定后数据不可恢复,确认删除吗?',function(confirm_status){
		if (confirm_status){
			var row = $('#ess-datagrid').datagrid('getSelected');
			var clientRequest = new Tcsp.ClientRequest();
			var params = new Object();
			params.ess_id = row.id;
			var url = managerPath + "/manager/configuration/ConfigMgr.json?m=execute&f=deleteEss";
			clientRequest.postJSON(url,params,function(respData){
				  if(respData.result){
					  if(respData.result.success&&respData.result.errorCode=='00000'){
						  $('#ess-datagrid').datagrid('reload');
					  }else{
						  $.messager.alert("操作提示",respData.result.errorMsg);
					  }
				  }
			});
		}
	});
}
ess_util.saverow=function(target){
	var index=ess_util.getRowIndex(target);
	$('#ess-datagrid').datagrid('endEdit',index);
	$('#ess-datagrid').datagrid('selectRow',index);
	var row = $('#ess-datagrid').datagrid('getSelected');
	if (row){
		var clientRequest = new Tcsp.ClientRequest();
		var params = new Object();
		params.ess_id = row.id;
		params.ess_cityId = row.cityId;
		params.ess_cityName = row.cityName;
		params.ess_isUse = row.isUse;
		params.ess_cityNo = row.cityNo;
		params.ess_employeeId = row.employeeId;
		params.ess_chlTypeCode = row.chlTypeCode;
		params.ess_chlCode = row.chlCode;
		params.ess_cityCode = row.cityCode;
		params.ess_remark = row.remark;
		params.ess_createStaffId = row.createStaffId;
		params.ess_updateStaffId = row.updateStaffId;	
		params.ess_createTime = row.createTime;
		params.ess_updateTime = row.updateTime;	
		var action='create';
		if(params.ess_id){
			action='update';
		}
		var url = managerPath + "/manager/configuration/ConfigMgr.json?m=execute&f="+action+"Ess";
		clientRequest.postJSON(url,params,function(respData){
			  if(respData.result){
				  if(respData.result.success&&respData.result.errorCode=='00000'){
					  $('#ess-datagrid').datagrid('reload');
				  }else{
					  $.messager.alert("操作提示",respData.result.errorMsg);
				  }
			  }
		});
	}
}
ess_util.cancelrow=function(target){
	$('#ess-datagrid').datagrid('cancelEdit', ess_util.getRowIndex(target));
}
ess_util.insert=function(){
	var row = $('#ess-datagrid').datagrid('getSelected');
	var index=$('#ess-datagrid').datagrid('getData').rows.length;
	if (row){
		if(row.editing){
			$.messager.alert('操作提示','请先保存后,再新建模板');
			return;
		}
		var index = $('#ess-datagrid').datagrid('getRowIndex', row);
	} else {
		index = 0;
	}
	$('#ess-datagrid').datagrid('insertRow', {
		index: 0,
		row:{
			BEGIN_TIME:ess_util.formatterDate(new Date()),
			END_TIME:'2099-12-31'
		}
	});
	$('#ess-datagrid').datagrid('selectRow',0);
	$('#ess-datagrid').datagrid('beginEdit',0);
}
ess_util.show_attr=function(v){
	OpenTab('center_tabs','模板属性管理',managerPath+'/manager/configuration/ConfigMgr.json?m=query&f=allEss','icon-munich-collaboration');
};
ess_util.updateActions=function(index){
	$('#ess-datagrid').datagrid('updateRow',{
		index: index,
		row:{}
	});
}

			ess_util.grid=$('#ess-datagrid').datagrid({  
			    iconCls:'icon-edit',
			    height: 'auto',  
			    nowrap: false,  
			    striped: true,  
			    border: true,  
			    collapsible:false,//是否可折叠的  
			    fitColumns: true,//自动大小  
			    url: managerPath+'/manager/configuration/ConfigMgr.json?m=query&f=allEss', 
			    loadMsg:'数据加载中...',
			    remoteSort:false,   
			    idField:'ID',  
			    singleSelect:true,//是否单选  
			    pagination:true,//分页控件  
			    rownumbers:true,//行号   
			    frozenColumns:[[]],
			    columns: [[
			    		{field: 'ck',checkbox:true},
			        	{title: '地市名称',field: 'cityName',width:80,sortable: true,align:'center'},
			        	{title: '地市代码',field: 'cityNo',width:80,sortable: true,align:'center'},
			        	{title: '调用状态',field: 'isUse',width:80,sortable: true,align:'center',editor:{type:'combobox',options:{precision:0,min:0,data:[{ "value": "0", "text": "暂停调用"}, { "value": "1", "text": "正常调用" }],valueField:"value",textField:"text"}}
							,formatter:function(value){
								if(value=='0'){
									return '暂停调用';
								}else {
									return '正常调用';
								}
							}
						},
			        	{title: 'ESS工号',field: 'employeeId',width:80,sortable: true,align:'center'},
			        	{title: '渠道类型',field: 'chlTypeCode',width:80,sortable: true,align:'center'},
			        	{title: '渠道编码',field: 'chlCode',width:80,sortable: true,align:'center'},
			        	{title: '地市编码',field: 'cityCode',width:80,sortable: false,align:'center'},
			        	{title: '操作人',field: 'updateStaffId',width:80,sortable: false,align:'center'},
			        	{title: '更新时间',field: 'updateTime',width:160,sortable: false,align:'center'},
			        	{title: '模板操作',field:'查看属性',sortable: false,align:'center',width:100,
			                formatter:function(value,row,index){
			                	if (row.editing){
									var s = '<a href="#" onclick="ess_util.saverow(this)">保存</a> ';
									var c = '<a href="#" onclick="ess_util.cancelrow(this)">取消</a>';
									return s+c;
								} else {
									var e = '<a href="#" onclick="ess_util.editrow(this)">编辑</a> ';
									var d = '<a href="#" onclick="ess_util.deleterow(this)">删除</a>';d='';
									return e+d;
								}
			               }
			            }
			    	]],
			    	onBeforeEdit:function(index,row){
						row.editing = true;
						ess_util.updateActions(index);
					},
					onAfterEdit:function(index,row){
						row.editing = false;
						ess_util.updateActions(index);
					},
					onCancelEdit:function(index,row){
						row.editing = false;
						ess_util.updateActions(index);
					},
			    loadFilter: function(data){
			    		if (data.result){
			    			return data.result.dataSet;
			    		} else {
			    			return data;
			    		}
			    },
			    pageList: [20],
			    pageSize: 20
			
			});

function taskdetail(){}
</script>