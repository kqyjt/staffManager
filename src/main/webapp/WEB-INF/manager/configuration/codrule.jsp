<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>



<div class="chaxun_cont">
 <ul>
<!--   <li><span>模板名称：</span><input  name="name" class="easyui-textbox" ></li>
 --> </ul>
<!--  <div class="butt_cont"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">导出</a></div>
 --></div>

<div style="margin:10px;">

	<table id="codrule-datagrid"></table>
</div>

<div id="codrule-mytoolbar" style="height:auto"></div>



<script type="text/javascript">
var codrule_util=codrule_util||{} ;
codrule_util.formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
	};
codrule_util.getRowIndex=function(target){
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
codrule_util.editrow=function(target){
	$('#codrule-datagrid').datagrid('selectRow',codrule_util.getRowIndex(target));
	$('#codrule-datagrid').datagrid('beginEdit', codrule_util.getRowIndex(target));
}
codrule_util.deleterow=function(target){
	var choosen_rows = codrule_util.grid.datagrid('getSelections').length;
	if(choosen_rows==0){
		var dlg=$.messager.alert({title: "操作提示", msg: "请选择您要删除的记录！"});
		setTimeout(function(){
		    dlg.window('close');
		}, 5000);
		return;
	}
	$.messager.confirm('操作提示','确定后数据不可恢复,确认删除吗?',function(confirm_status){
		if (confirm_status){
			var row = $('#codrule-datagrid').datagrid('getSelected');
			var clientRequest = new Tcsp.ClientRequest();
			var params = new Object();
			params.codrule_id = row.id;
			var url = managerPath + "/manager/configuration/ConfigMgr.json?m=execute&f=deleteCodRule";
			clientRequest.postJSON(url,params,function(respData){
				  if(respData.result){
					  if(respData.result.success&&respData.result.errorCode=='00000'){
						  $('#codrule-datagrid').datagrid('reload');
					  }else{
						  $.messager.alert("操作提示",respData.result.errorMsg);
					  }
				  }
			});
		}
	});
}
codrule_util.saverow=function(target){
	var index=codrule_util.getRowIndex(target);
	$('#codrule-datagrid').datagrid('endEdit',index);
	$('#codrule-datagrid').datagrid('selectRow',index);
	var row = $('#codrule-datagrid').datagrid('getSelected');
	if (row){
		var clientRequest = new Tcsp.ClientRequest();
		var params = new Object();
		params.codrule_id = row.id;
		params.codrule_cityId = row.cityId;
		params.codrule_cityName = row.cityName;
		params.codrule_lowestPrice = row.lowestPrice;
		params.codrule_endMonthday = row.endMonthday;
		params.codrule_beginMonthday = row.beginMonthday;
		params.codrule_isUse = row.isUse;
		params.codrule_remark = row.remark;
		
		var action='create';
		if(params.codrule_id){
			action='update';
		}
		var url = managerPath + "/manager/configuration/ConfigMgr.json?m=execute&f="+action+"CodRule";
		clientRequest.postJSON(url,params,function(respData){
			  if(respData.result){
				  if(respData.result.success&&respData.result.errorCode=='00000'){
					  $('#codrule-datagrid').datagrid('reload');
				  }else{
					  $.messager.alert("操作提示",respData.result.errorMsg);
				  }
			  }
		});
	}
}
codrule_util.cancelrow=function(target){
	$('#codrule-datagrid').datagrid('cancelEdit', codrule_util.getRowIndex(target));
}
codrule_util.insert=function(){
	var row = $('#codrule-datagrid').datagrid('getSelected');
	var index=$('#codrule-datagrid').datagrid('getData').rows.length;
	if (row){
		if(row.editing){
			$.messager.alert('操作提示','请先保存后,再新建模板');
			return;
		}
		var index = $('#codrule-datagrid').datagrid('getRowIndex', row);
	} else {
		index = 0;
	}
	$('#codrule-datagrid').datagrid('insertRow', {
		index: 0,
		row:{
			BEGIN_TIME:codrule_util.formatterDate(new Date()),
			END_TIME:'2099-12-31'
		}
	});
	$('#codrule-datagrid').datagrid('selectRow',0);
	$('#codrule-datagrid').datagrid('beginEdit',0);
}
codrule_util.show_attr=function(v){
	OpenTab('center_tabs','模板属性管理',managerPath+'/manager/configuration/ConfigMgr.json?m=query&f=allCodRule','icon-munich-collaboration');
};
codrule_util.updateActions=function(index){
	$('#codrule-datagrid').datagrid('updateRow',{
		index: index,
		row:{}
	});
};
codrule_util.isOpen = [
         	               { "value": "0", "text": "未生效" }, 
         	               { "value": "1", "text": "生效" }
         	              ];
         	 

codrule_util.unitformatter = function(value, rowData, rowIndex) {
         	    for (var i = 0; i < codrule_util.isOpen.length; i++) {
         	        if (codrule_util.isOpen[i].value == value) {
         	            return codrule_util.isOpen[i].text;
         	        }
         	    }
         	}

		
			codrule_util.grid=$('#codrule-datagrid').datagrid({  
			    iconCls:'icon-edit',
			    height: 'auto',  
			    nowrap: false,  
			    striped: true,  
			    border: true,  
			    collapsible:false,//是否可折叠的  
			    url: managerPath+'/manager/configuration/ConfigMgr.json?m=query&f=allCodRule', 
			    loadMsg:'数据加载中...',
			    remoteSort:false,   
			    idField:'ID',  
			    singleSelect:true,//是否单选  
			    pagination:true,//分页控件  
			    rownumbers:true,//行号   
			    frozenColumns:[[]],
			    fitColumns: true,//设置为 true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动。
			    //width:'100%',
			    columns: [[
			    		{field: 'ck',checkbox:true},
			        	/* {title: '地市编码',field: 'cityId',width:80,sortable: true,align:'center',editor:'text'}, */
			        	{title: '地市名称',field: 'cityName',width:80,sortable: true,align:'center',editor:'text'},
			        	{title: '最低金额限制',field: 'lowestPrice',width:80,sortable: true,align:'center',editor:{type:'numberbox',options:{precision:2}}},
			        	{title: '月末失效天数',field: 'endMonthday',width:80,sortable: true,align:'center',editor:{type:'numberbox',options:{precision:0}}},
			        	{title: '月初失效天数',field: 'beginMonthday',width:80,sortable: true,hidden:"true",editor:{type:'numberbox',options:{required:true,setValue:'0',}}},
			        	{title: '是否生效',field: 'isUse',width:100,sortable: false,align:'center',
			        		editor: { 
			    				type: 'combobox', 
			    				options: { 
					    					data: codrule_util.isOpen, 
					    					valueField: "value", 
					    					textField: "text" 
			    						} 
			    					},
			        		formatter: codrule_util.unitformatter, 
			        		formatter:function(val,rec){
	    						if (val == 0){
	    							return '未生效';
	    						} else {
	    							return '生效';
	    						}
	    					}
			    		},
			        	{title: '备注',field: 'remark',width:80,sortable: true,align:'center',editor:'text'},
			        	{title: '更新时间',field: 'updateTime',width:160,sortable: false,align:'center'},
			        	{title: '更新人员',field: 'updateStaffId',width:100,sortable: false,align:'center'},
			        	{title: '模板操作',field:'查看属性',sortable: false,align:'center',width:100,
			                formatter:function(value,row,index){
			                	if (row.editing){
									var s = '<a href="#" onclick="codrule_util.saverow(this)">保存</a> ';
									var c = '<a href="#" onclick="codrule_util.cancelrow(this)">取消</a>';
									return s+c;
								} else {
									var e = '<a href="#" onclick="codrule_util.editrow(this)">编辑</a> ';
									var d = '<a href="#" onclick="codrule_util.deleterow(this)">删除</a>';d='';
									return e+d;
								}
			               }
			            }
			    	]],
			    	onBeforeEdit:function(index,row){
						row.editing = true;
						codrule_util.updateActions(index);
					},
					onAfterEdit:function(index,row){
						row.editing = false;
						codrule_util.updateActions(index);
					},
					onCancelEdit:function(index,row){
						row.editing = false;
						codrule_util.updateActions(index);
					},
			    loadFilter: function(data){
			    		if (data.result){
			    			return data.result.dataSet;
			    		} else {
			    			return data;
			    		}
			    },
			   /*  toolbar: [{
			        text: '新增',
			        iconCls: 'icon-add',
			        handler: codrule_util.insert
			    },'-',{
			        text: '删除',
			        iconCls: 'icon-remove',
			        handler: codrule_util.deleterow
			    }],  */
			    pageList: [10,20,30],
			    pageSize: 10
			
			});

function taskdetail(){}
</script>