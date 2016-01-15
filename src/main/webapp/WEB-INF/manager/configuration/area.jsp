<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>

<div class="chaxun_cont">
 <ul>
<!--   <li><span>模板名称：</span><input  name="name" class="easyui-textbox" /></li>
 --> </ul>
<!--  <div class="butt_cont"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">导出</a></div>
 --></div>

<div style="margin:10px;">

	<table id="area-datagrid"></table>
</div>

<div id="area-mytoolbar" style="height:auto"></div>

<script type="text/javascript">
var area_util=area_util||{} ;

		
			area_util.grid=$('#area-datagrid').datagrid({  
			    iconCls:'icon-edit',
			    height: 'auto',  
			    nowrap: false,  
			    striped: true,  
			    border: true,  
			    collapsible:false,//是否可折叠的  
			    fitColumns: false,//自动大小  
			    url: managerPath+'/manager/configuration/ConfigMgr.json?m=query&f=allAreaInfo', 
			    loadMsg:'数据加载中...',
			    remoteSort:false,   
			    idField:'ID',  
			    singleSelect:true,//是否单选  
			    pagination:true,//分页控件  
			    rownumbers:true,//行号   
			    frozenColumns:[[]],
			    columns: [[
			    		{field: 'ck',checkbox:true},
			        	{title: '区域编码',field: 'code',width:80,sortable: true,align:'center',editor:'text'},
			        	{title: '区域名称',field: 'name',width:80,sortable: true,align:'center',editor:'text'},
			        	{title: '区域级别',field: 'lvlId',width:80,sortable: true,align:'center',editor:'text'},
			        	{title: '父级区域',field: 'parentId',width:80,sortable: true,align:'center',editor:{type:'numberbox',options:{precision:0}}},
			        	{title: '备注',field: 'remark',width:80,sortable: true,align:'center',editor:'text'},
			        	{title: '更新人员',field: 'updateStaffId',width:80,sortable: true,align:'center',editor:'text'},
			        	{title: '更新时间',field: 'updateTime',width:160,sortable: false,align:'center'},
			        	{title: '模板操作',field:'查看属性',sortable: false,align:'center',width:100,
			                formatter:function(value,row,index){
			                	if (row.editing){
									var s = '<a href="#" onclick="area_util.saverow(this)">保存</a> ';
									var c = '<a href="#" onclick="area_util.cancelrow(this)">取消</a>';
									return s+c;
								} else {
									var e = '<a href="#" onclick="area_util.editrow(this)">编辑</a> ';
									var d = '<a href="#" onclick="area_util.deleterow(this)">删除</a>';d='';
									return e+d;
								}
			               }
			            }
			    	]],
			    	onBeforeEdit:function(index,row){
						row.editing = true;
						area_util.updateActions(index);
					},
					onAfterEdit:function(index,row){
						row.editing = false;
						area_util.updateActions(index);
					},
					onCancelEdit:function(index,row){
						row.editing = false;
						area_util.updateActions(index);
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
			        handler: area_util.insert
			    },'-',{
			        text: '删除',
			        iconCls: 'icon-remove',
			        handler: area_util.deleterow
			    }],
			    pageList: [10,20,30],
			    pageSize: 10
			
			});
			function taskdetail(){}
			area_util.formatterDate = function(date) {
				var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
				var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
				return date.getFullYear() + '-' + month + '-' + day;
			};
			area_util.getRowIndex=function(target){
				var tr = $(target).closest('tr.datagrid-row');
				return parseInt(tr.attr('datagrid-row-index'));
			}
			area_util.editrow=function(target){
				$('#area-datagrid').datagrid('selectRow',area_util.getRowIndex(target));
				$('#area-datagrid').datagrid('beginEdit', area_util.getRowIndex(target));
			}
			area_util.deleterow=function(target){
				var choosen_rows = area_util.grid.datagrid('getSelections').length;
				if(choosen_rows==0){
					var dlg=$.messager.alert({title: "操作提示", msg: "请选择您要删除的记录！"});
					setTimeout(function(){
					    dlg.window('close');
					}, 5000);
					return;
				}
				$.messager.confirm('操作提示','确定后数据不可恢复,确认删除吗?',function(confirm_status){
					if (confirm_status){
						var row = $('#area-datagrid').datagrid('getSelected');
						var clientRequest = new Tcsp.ClientRequest();
						var params = new Object();
						params.area_id = row.id;
						var url = managerPath + "/manager/configuration/ConfigMgr.json?m=execute&f=deleteAreaInfo";
						clientRequest.postJSON(url,params,function(respData){
							  if(respData.result){
								  if(respData.result.success&&respData.result.errorCode=='00000'){
									  $('#area-datagrid').datagrid('reload');
								  }else{
									  $.messager.alert("操作提示",respData.result.errorMsg);
								  }
							  }
						});
					}
				});
			}
			area_util.cancelrow=function(target){
				$('#area-datagrid').datagrid('cancelEdit', area_util.getRowIndex(target));
			}
			area_util.updateActions=function(index){
				$('#area-datagrid').datagrid('updateRow',{
					index: index,
					row:{}
				});
			}
			area_util.show_attr=function(v){
				OpenTab('center_tabs','模板属性管理',managerPath+'/manager/configuration/ConfigMgr.json?m=query&f=allAreaInfo','icon-munich-collaboration');
			};
			
			
			area_util.insert=function(){
				var row = $('#area-datagrid').datagrid('getSelected');
				var index=$('#area-datagrid').datagrid('getData').rows.length;
				if (row){
					if(row.editing){
						$.messager.alert('操作提示','请先保存后,再新建模板');
						return;
					}
					var index = $('#area-datagrid').datagrid('getRowIndex', row);
				} else {
					index = 0;
				}
				$('#area-datagrid').datagrid('insertRow', {
					index: 0,
					row:{
						BEGIN_TIME:area_util.formatterDate(new Date()),
						END_TIME:'2099-12-31'
					}
				});
				$('#area-datagrid').datagrid('selectRow',0);
				$('#area-datagrid').datagrid('beginEdit',0);
			}
			area_util.saverow=function(target){
				var index=area_util.getRowIndex(target);
				$('#area-datagrid').datagrid('endEdit',index);
				$('#area-datagrid').datagrid('selectRow',index);
				var row = $('#area-datagrid').datagrid('getSelected');
				if (row){
					var clientRequest = new Tcsp.ClientRequest();
					var params = new Object();
					params.area_id = row.id;
					params.area_code = row.code;
					params.area_name = row.name;
					params.area_lvlId = row.lvlId;
					params.area_parentId = row.parentId;
					params.area_remark = row.remark;
					params.area_updateStaffId = row.updateStaffId;
					params.area_updateTime = row.updateTime;
					var action='create';
					if(params.area_id){
						action='update';
					}
					var url = managerPath + "/manager/configuration/ConfigMgr.json?m=execute&f="+action+"AreaInfo";
					clientRequest.postJSON(url,params,function(respData){
						  if(respData.result){
							  if(respData.result.success&&respData.result.errorCode=='00000'){
								  $('#area-datagrid').datagrid('reload');
							  }else{
								  $.messager.alert("操作提示",respData.result.errorMsg);
							  }
						  }
					});
				}
			}

</script>


