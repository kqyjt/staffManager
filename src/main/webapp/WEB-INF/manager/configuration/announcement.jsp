<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>

<div style="margin:10px;">
	<table id="ann-datagrid"></table>
</div>

<div id="ann-mytoolbar" style="height:auto"></div>

<script type="text/javascript">
	var ann_util=ann_util||{} ;
	
	ann_util.formatterDate = function(date) {
		var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
		return date.getFullYear() + '-' + month + '-' + day;
	};
	
	ann_util.getRowIndex=function(target){
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	
	ann_util.deleterow=function(target){
		var choosen_rows = ann_util.grid.datagrid('getSelections').length;
		if(choosen_rows==0){
			var dlg=$.messager.alert({title: "操作提示", msg: "请选择您要删除的记录！"});
			setTimeout(function(){
			    dlg.window('close');
			}, 5000);
			return;
		}
		$.messager.confirm('操作提示','确定后数据不可恢复,确认删除吗?',function(confirm_status){
			if (confirm_status){
				var row = $('#ann-datagrid').datagrid('getSelected');
				var clientRequest = new Tcsp.ClientRequest();
				var params = new Object();
				params.ann_id = row.id;
				var url = managerPath + "/manager/sysman/AnnouncementMgr.json?m=execute&f=deleteAnn";
				clientRequest.postJSON(url,params,function(respData){
					  if(respData.result){
						  if(respData.result.success&&respData.result.errorCode=='00000'){
							  $('#ann-datagrid').datagrid('reload');
							  $('#ann-datagrid').datagrid('clearSelections');
						  }else{
							  $.messager.alert("操作提示",respData.result.errorMsg);
						  }
					  }
				});
			}
		});
	}
	
	ann_util.cancelrow=function(target){
		$('#ann-datagrid').datagrid('cancelEdit', ann_util.getRowIndex(target));
	}
	
	//新增公告
	ann_util.insert=function(){
		var url = managerPath + "/manager/sysman/AnnouncementMgr.htm?m=execute&f=updateAnn";
		OpenTab("center_tabs",'新增公告',url,'icon-munich-collaboration');
	}
	
	//公告更新
	ann_util.updateAnnRow = function(target) {
		$('#ann-datagrid').datagrid('selectRow', ann_util.getRowIndex(target));
		var selectRow = $("#ann-datagrid").datagrid("getSelected");
		if (selectRow == "" || selectRow == undefined) {
			$.messager.alert('提示信息',"请先选择一行记录!");
			return false;
		}
		var annId = selectRow.id;
		var url = managerPath + "/manager/sysman/AnnouncementMgr.htm?m=execute&f=updateAnn&annId="+annId;
		OpenTab("center_tabs",'公告更新',url,'icon-munich-collaboration');
	}
	
	ann_util.updateActions=function(index){
		$('#ann-datagrid').datagrid('updateRow',{
			index: index,
			row:{}
		});
	}
	
	ann_util.grid=$('#ann-datagrid').datagrid({  
	    iconCls:'icon-edit',
	    height: 'auto', 
	    width: 1050,
	    nowrap: false,  
	    striped: true,  
	    border: true,  
	    collapsible:false,//是否可折叠的  
	    fitColumns: false,//自动大小  
	    url: managerPath+'/manager/sysman/AnnouncementMgr.json?m=query&f=allAnn', 
	    loadMsg:'数据加载中...',
	    remoteSort:false,   
	    idField:'id',  
	    singleSelect:true,//是否单选  
	    pagination:true,//分页控件  
	    rownumbers:true,//行号   
	    frozenColumns:[[]],
	    columns: [[
	    		{field: 'ck',checkbox:true},
	    		{title: 'id',field: 'id',width:80,sortable: true,align:'center',hidden:true},
	        	{title: '目标城市',field: 'cityId',width:80,sortable: true,align:'center',
	    			formatter:function(value,row){
	    				if(value=='0000'){
	    					return '山东';
	    				}else if(value=='0533'){
	    					return '淄博';
	    				}else if(value=='0543'){
	    					return '滨州';
	    				}else if(value=='0539'){
	    					return '临沂';
	    				}else if(value=='0633'){
	    					return '日照';
	    				}else if(value=='0536'){
	    					return '潍坊';
	    				}else if(value=='0546'){
	    					return '东营';
	    				}else if(value=='0632'){
	    					return '枣庄';
	    				}else if(value=='0537'){
	    					return '济宁';
	    				}else if(value=='0530'){
	    					return '菏泽';
	    				}else if(value=='0634'){
	    					return '莱芜';
	    				}else if(value=='0535'){
	    					return '烟台';
	    				}else if(value=='0532'){
	    					return '青岛';
	    				}else if(value=='0531'){
	    					return '济南';
	    				}else if(value=='0538'){
	    					return '泰安';
	    				}else if(value=='0534'){
	    					return '德州';
	    				}else if(value=='0635'){
	    					return '聊城';
	    				}else if(value=='0631'){
	    					return '威海';
	    				}else{
	    					return value;
	    				}
	    			}
	    		},
	        	{title: '公告类型',field: 'annType',width:80,sortable: true,align:'center',editor:'text'},
	        	{title: '公告标题',field: 'annTitle',width:240,sortable: true,align:'center',editor:'text'},
	/*         	{title: '公告内容',field: 'annContent',width:80,sortable: true,align:'center',editor:'text'},
	 */        	{title: '链接地址',field: 'linkUrl',width:160,sortable: true,align:'center',editor:'text',hidden:true},
	        	{title: '生效时间',field: 'beginTime',width:160,sortable: true,align:'center',editor:'datebox'},
	        	{title: '失效时间',field: 'endTime',width:160,sortable: true,align:'center',editor:'datebox'},
	        	{title: '备注',field: 'remark',width:120,sortable: true,align:'center',editor:'text',hidden:true},
	        	{title: '创建时间',field: 'createTime',width:160,sortable: true,align:'center'},
	        	{title: '更新时间',field: 'updateTime',width:160,sortable: true,align:'center',hidden:true},
	        	{title: '创建人',field: 'createStaffId',width:120,sortable: true,align:'center',editor:'text',hidden:true},
	        	{title: '更新人',field: 'updateStaffId',width:120,sortable: true,align:'center',editor:'text',hidden:true},
	        	{title: '操作',field:'查看属性',sortable: false,align:'center',width:100,
	                formatter:function(value,row,index){
	                	var e = '<a href="#" onclick="ann_util.updateAnnRow(this)">更新</a> ';
						return e;
	               }
	            }
	    	]],
    	onBeforeEdit:function(index,row){
			row.editing = true;
			ann_util.updateActions(index);
		},
		onAfterEdit:function(index,row){
			row.editing = false;
			ann_util.updateActions(index);
		},
		onCancelEdit:function(index,row){
			row.editing = false;
			ann_util.updateActions(index);
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
	        handler: ann_util.insert
	    },'-',{
	        text: '删除',
	        iconCls: 'icon-remove',
	        handler: ann_util.deleterow
	    }],
	    pageList: [10,20,30],
	    pageSize: 10
	
	});

</script>
