<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>

<!-- 查询条件及按钮 -->
<form name="selfpk_form" method="post" action="#" target="newwindow">
	<div class="chaxun_cont">
		<ul>
			<li>
			  	<span>地市：</span>
			  	<select id = "selfpk_areaCode" name="selfpk_areaCode" class="easyui-combobox" style="width:129px;" data-options="editable:false"> 
			  		<option value="">全部</option>  
			   		<option value="170">济南市</option>   
			   		<option value="179">威海市</option> 
			   		<option value="175">烟台市</option> 
			   		<option value="171">青岛市</option> 
			   		<option value="180">日照市</option> 
			   		<option value="186">菏泽市</option> 
			   		<option value="174">东营市</option> 
			   		<option value="172">淄博市</option> 
			   		<option value="183">德州市</option> 
			   		<option value="177">济宁市</option> 
			   		<option value="182">临沂市</option> 
			   		<option value="185">滨州市</option> 
			   		<option value="176">潍坊市</option> 
			   		<option value="178">泰安市</option> 
			   		<option value="173">枣庄市</option> 
			   		<option value="181">莱芜市</option> 
			   		<option value="184">聊城市</option>    
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
	
	selfpk_util.areaCode = [
							{ "value": "172", "text": "淄博" },
							{ "value": "185", "text": "滨州" },
							{ "value": "182", "text": "临沂" },
							{ "value": "180", "text": "日照" },
							{ "value": "176", "text": "潍坊" },
							{ "value": "174", "text": "东营" },
							{ "value": "173", "text": "枣庄" },
							{ "value": "177", "text": "济宁" },
							{ "value": "186", "text": "菏泽" },
							{ "value": "181", "text": "莱芜" },
							{ "value": "175", "text": "烟台" },
							{ "value": "171", "text": "青岛" },
							{ "value": "170", "text": "济南" },
							{ "value": "178", "text": "泰安" },
							{ "value": "183", "text": "德州" },
							{ "value": "184", "text": "聊城" },
							{ "value": "179", "text": "威海" }
	                   ];
	selfpk_util.unitformatter = function(value, rowData, rowIndex) {
	   for (var i = 0; i <selfpk_util.areaCode.length; i++) {
	       if (selfpk_util.areaCode[i].value == value) {
	           return selfpk_util.areaCode[i].text;
	       }
	   }
	}
	
	//查询自提信息
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
	
	//删除自提信息
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
	
	//保存自提信息
	selfpk_util.saverow=function(target){
		 var index=selfpk_util.getRowIndex(target);
		$('#selfpk-datagrid').datagrid('endEdit',index);
		$('#selfpk-datagrid').datagrid('selectRow',index); 
		var row = $('#selfpk-datagrid').datagrid('getSelected');
		if (row){
			var clientRequest = new Tcsp.ClientRequest();
			var params = new Object();
			params.selfpk_id = row.id;
			params.selfpk_cityId = row.cityId;
			params.selfpk_pickupAddress = row.pickupAddress;
			params.selfpk_contacts = row.contacts;
			params.selfpk_telephone = row.telephone;
			params.selfpk_openTime = row.openTime;
			params.selfpk_remark = row.remark;		
			var action='create';
			if(params.selfpk_id){
				action='update';
			}
			if(typeof(params.selfpk_cityId)=='undefined'||params.selfpk_cityId==''){
				$.messager.alert("操作提示",'地市不能为空');
				return;
			} else if(typeof(params.selfpk_pickupAddress)=='undefined'||params.selfpk_pickupAddress==''){
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
					formatter:selfpk_util.unitformatter,
					editor: { 
	    				type: 'combobox', 
	    				options: { 
			    					data: selfpk_util.areaCode, 
			    					valueField: "value", 
			    					textField: "text" 
	    						} 
	    					},
	    					
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