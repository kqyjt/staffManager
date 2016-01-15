<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>

<!-- 查询条件及按钮 -->
<form name="selfpkCity_form" method="post" action="#" target="newwindow">
	<div class="chaxun_cont">
		<ul>
			<li>
			  	<span>地市：</span>
			  	<select id="selfpk_areaCode" name="selfpk_areaCode" style="width:129px;" class="easyui-combobox" data-options="editable:false">
						<option value="${result.dataSet.areaCode}">${result.dataSet.homeCity}</option>
				</select>
			</li>
			<li><span>联系人：</span><input id="selfpk_regName" name="selfpk_regName" class="easyui-textbox" ></li>
		</ul>
	 	<div class="clear"></div>
	 	<div class="butt_cont">
	 		<a href="#" class="easyui-linkbutton" onclick="selfpk_util.searchSelfpkInfo()" data-options="iconCls:'icon-search'" style="width:80px">
	 			查询
 			</a>
		</div>
	</div>
</form>

<div style="margin:10px;">

	<table id="selfpk-datagrid"></table>
</div>

<div id="selfpk-mytoolbar" style="height:auto"></div>

<script type="text/javascript">
	var selfpk_util=selfpk_util||{} ;
	selfpk_util.params = new Object();
	selfpk_util.queryUrl = managerPath+'/manager/configuration/ConfigMgr.json?m=query&f=allSelfPkAdd';
	
	selfpk_util.searchSelfpkInfo = function(){
		selfpk_util.params.areaCode = $("#selfpk_areaCode").combobox('getValue');
		selfpk_util.params.regName = $("#selfpk_regName").val();
		$("#selfpk-datagrid").datagrid('reload');
	}
	
	selfpk_util.formatterDate = function(date) {
		var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
		return date.getFullYear() + '-' + month + '-' + day;
		};
	selfpk_util.getRowIndex=function(target){
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	selfpk_util.editrow=function(target){
		$('#selfpk-datagrid').datagrid('selectRow',selfpk_util.getRowIndex(target));
		$('#selfpk-datagrid').datagrid('beginEdit', selfpk_util.getRowIndex(target));
	}
	selfpk_util.deleterow=function(target){
		var choosen_rows = selfpk_util.grid.datagrid('getSelections').length;
		if(choosen_rows==0){
			var dlg=$.messager.alert({title: "操作提示", msg: "请选择您要删除的记录！"});
			setTimeout(function(){
			    dlg.window('close');
			}, 5000);
			return;
		}
		$.messager.confirm('操作提示','确定后数据不可恢复,确认删除吗?',function(confirm_status){
			if (confirm_status){
				var row = $('#selfpk-datagrid').datagrid('getSelected');
				var clientRequest = new Tcsp.ClientRequest();
				var params = new Object();
				params.selfpk_id = row.id;
				var url = managerPath + "/manager/configuration/ConfigMgr.json?m=execute&f=deleteSelfPkAdd";
				clientRequest.postJSON(url,params,function(respData){
					  if(respData.result){
						  if(respData.result.success&&respData.result.errorCode=='00000'){
							  $('#selfpk-datagrid').datagrid('reload');
							  $('#selfpk-datagrid').datagrid('clearSelections');
						  }else{
							  $.messager.alert("操作提示",respData.result.errorMsg);
						  }
					  }
				});
			}
		});
	}
	selfpk_util.saverow=function(target){
		 var index=selfpk_util.getRowIndex(target);
		$('#selfpk-datagrid').datagrid('endEdit',index);
		$('#selfpk-datagrid').datagrid('selectRow',index); 
		var row = $('#selfpk-datagrid').datagrid('getSelected');
		if (row){
			var clientRequest = new Tcsp.ClientRequest();
			var params = new Object();
			params.selfpk_id = row.id;
			params.selfpk_cityId = $("#selfpk_areaCode").combobox('getValue');
			params.selfpk_pickupAddress = row.pickupAddress;
			params.selfpk_contacts = row.contacts;
			params.selfpk_telephone = row.telephone;
			params.selfpk_openTime = row.openTime;
			params.selfpk_remark = row.remark;		
			var action='create';
			if(params.selfpk_id){
				action='update';
			}
			if(typeof(params.selfpk_pickupAddress)=='undefined'||params.selfpk_pickupAddress==''){
				$.messager.alert("操作提示",'自提地址不能为空');
				return;
			} else if(typeof(params.selfpk_contacts)=='undefined'||params.selfpk_contacts==''){
				$.messager.alert("操作提示",'联系人不能为空');
				return;
			} else if(typeof(params.selfpk_telephone)=='undefined'||params.selfpk_telephone==''){
				$.messager.alert("操作提示",'联系电话不能为空');
				return;
			} else if(typeof(params.selfpk_openTime)=='undefined'||params.selfpk_openTime==''){
				$.messager.alert("操作提示",'自提时间不能为空');
				return;
			}
			
			var url = managerPath + "/manager/configuration/ConfigMgr.json?m=execute&f="+action+"SelfPkAdd";
			clientRequest.postJSON(url,params,function(respData){
				  if(respData.result){
					  if(respData.result.success&&respData.result.errorCode=='00000'){
						  $.messager.alert("操作提示",'保存成功');
						  $('#selfpk-datagrid').datagrid('reload');
						  $('#selfpk-datagrid').datagrid('clearSelections');
					  }else{
						  $.messager.alert("操作提示",respData.result.errorMsg);
					  }
				  }
			});
		}
	}
	selfpk_util.cancelrow=function(target){
		$('#selfpk-datagrid').datagrid('cancelEdit', selfpk_util.getRowIndex(target));
	}
	selfpk_util.insert=function(){
		var row = $('#selfpk-datagrid').datagrid('getSelected');
		var index=$('#selfpk-datagrid').datagrid('getData').rows.length;
		if (row){
			if(row.editing){
				$.messager.alert('操作提示','请先保存后,再新建模板');
				return;
			}
			var index = $('#selfpk-datagrid').datagrid('getRowIndex', row);
		} else {
			index = 0;
		}
		$('#selfpk-datagrid').datagrid('insertRow', {
			index: 0,
			row:{
				BEGIN_TIME:selfpk_util.formatterDate(new Date()),
				END_TIME:'2099-12-31'
			}
		});
		$('#selfpk-datagrid').datagrid('selectRow',0);
		$('#selfpk-datagrid').datagrid('beginEdit',0);
	}
	selfpk_util.show_attr=function(v){
		OpenTab('center_tabs','模板属性管理',managerPath+'/manager/configuration/ConfigMgr.json?m=query&f=allSelfPkAdd','icon-munich-collaboration');
	};
	selfpk_util.updateActions=function(index){
		$('#selfpk-datagrid').datagrid('updateRow',{
			index: index,
			row:{}
		});
	}
	
	selfpk_util.grid=$('#selfpk-datagrid').datagrid({  
	    iconCls:'icon-edit',
	    height: 'auto',  
	    nowrap: false,  
	    striped: true,  
	    border: true,  
	    collapsible:false,//是否可折叠的  
	    fitColumns: false,//自动大小  
	    url: selfpk_util.queryUrl, 
	    queryParams : selfpk_util.params,
	    loadMsg:'数据加载中...',
	    remoteSort:false,   
	    idField:'id',  
	    singleSelect:true,//是否单选  
	    pagination:true,//分页控件  
	    rownumbers:true,//行号   
	    frozenColumns:[[]],
	    columns: [[
	    		{field: 'ck',checkbox:true},
	    		{title: 'id',field: 'id',width:80,sortable: true,align:'left',hidden:true},
	        	{title: '地市',field: 'cityId',width:100,sortable: false,align:'center',
	    			formatter:function(value,row){
	    				if(value=='000'){
	    					return '山东';
	    				}else if(value=='172'){
	    					return '淄博';
	    				}else if(value=='185'){
	    					return '滨州';
	    				}else if(value=='182'){
	    					return '临沂';
	    				}else if(value=='180'){
	    					return '日照';
	    				}else if(value=='176'){
	    					return '潍坊';
	    				}else if(value=='174'){
	    					return '东营';
	    				}else if(value=='173'){
	    					return '枣庄';
	    				}else if(value=='177'){
	    					return '济宁';
	    				}else if(value=='186'){
	    					return '菏泽';
	    				}else if(value=='181'){
	    					return '莱芜';
	    				}else if(value=='175'){
	    					return '烟台';
	    				}else if(value=='171'){
	    					return '青岛';
	    				}else if(value=='170'){
	    					return '济南';
	    				}else if(value=='178'){
	    					return '泰安';
	    				}else if(value=='183'){
	    					return '德州';
	    				}else if(value=='184'){
	    					return '聊城';
	    				}else if(value=='179'){
	    					return '威海';
	    				}else{
	    					return value;
	    				}
	    			}
	    		},
	        	{title: '自提地址',field: 'pickupAddress',width:300,sortable: true,align:'left',editor:'text'},
	        	{title: '联系人',field: 'contacts',width:80,sortable: true,align:'left',editor:'text'},
	        	{title: '联系电话',field: 'telephone',width:110,sortable: true,align:'left',editor:{type:'numberbox',options:{precision:0}}},
	        	{title: '自提时间',field: 'openTime',width:160,sortable: true,align:'left',editor:'text'},
	        	{title: '公交路线',field: 'busLine',width:160,sortable: false,align:'center',hidden:true},
	        	{title: '备注',field: 'remark',width:220,sortable: false,align:'left',editor:'text'},
	        	{title: '创建人员',field: 'createStaffId',width:80,sortable: false,align:'center',hidden:true},
	        	{title: '更新人员',field: 'updateStaffId',width:80,sortable: false,align:'center',hidden:true},
	        	{title: '创建时间',field: 'createTime',width:160,sortable: false,align:'center',hidden:true},
	        	{title: '更新时间',field: 'updateTime',width:160,sortable: false,align:'center',hidden:true},
	        	{title: '操作',field:'查看属性',sortable: false,align:'center',width:100,
	                formatter:function(value,row,index){
	                	if (row.editing){
							var s = '<a href="#" onclick="selfpk_util.saverow(this)">保存</a> ';
							var c = '<a href="#" onclick="selfpk_util.cancelrow(this)">取消</a>';
							return s+c;
						} else {
							var e = '<a href="#" onclick="selfpk_util.editrow(this)">编辑</a> ';
							return e;
						}
	               }
	            }
	    	]],
    	onBeforeEdit:function(index,row){
			row.editing = true;
			selfpk_util.updateActions(index);
		},
		onAfterEdit:function(index,row){
			row.editing = false;
			selfpk_util.updateActions(index);
		},
		onCancelEdit:function(index,row){
			row.editing = false;
			selfpk_util.updateActions(index);
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
	        handler: selfpk_util.insert
	    },'-',{
	        text: '删除',
	        iconCls: 'icon-remove',
	        handler: selfpk_util.deleterow
	    }],
	    pageList: [10,20,30],
	    pageSize: 10
	
	});
	
	function taskdetail(){}
</script>