<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>
<input id = "cod_goods_rule_code" value="${result.dataSet.code }" type="hidden"/>
<input id = "cod_goods_rule_isProAdmin" value="${result.dataSet.isProAdmin }" type="hidden"/>
<input id = "cod_goods_rule_name" value="${result.dataSet.name }" type="hidden"/>
<!-- 查询条件 -->
<div class="chaxun_cont">
 <ul>
 	<li><span>地市：</span>
 		<select id = "cod_goods_rule_areaCode" name="cod_goods_rule_areaCode" class="easyui-combobox" style="width:129px;" data-options="editable:false"> 
	   		<c:forEach items="${result.dataSet.areaCode }" var="area">
	  			<option value="${area.code }">${area.name }</option>
	  		</c:forEach> 
		</select>
 	</li>
  	<li><span>商品类型：</span>
  		<select id = "cod_goods_rule_goodsType" name="cod_goods_rule_goodsType" class="easyui-combobox" style="width:129px;" data-options="editable:false"> 
	  		<option value="*" selected="selected">全部类型</option>
	  		<option value="GT00">4G全国套餐</option>
	  		<option value="GT03">4G校园套餐</option>
	  		<option value="GT04">4G本地套餐</option>
	  		<option value="GT05">单宽带</option>
	  		<option value="GT06">智慧沃家</option>
	  		<option value="GT07">3G存费送费</option>
	  		<option value="GT08">上网卡</option>
	  		<option value="GT09">青岛38元沃派</option>
	  		<option value="GT10">4g存费送费（全国）</option>
	  		<option value="GT11">4g存费送费（校园）</option>
	  		<option value="GT12">4g存费送费（本地）</option>
	  		<option value="GT13">4G流量王</option>
	  		<option value="GT14">4G9分卡</option>
	  		<option value="GT15">4G主副卡</option>
	  		<option value="GT01">融合类商品</option>
	  		<option value="GT02">裸机</option>
		</select>
  	</li>
 </ul>
 <div class="butt_cont"><a href="#" onclick="COD_GOODS_RULR.searchGoodsRule()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a></div>
</div>
<!-- 表格 -->
<div style="margin:10px;">
	<table id="cod_goods_rule_datagrid"></table>
</div>
<!-- 工具栏 -->
<div id="cod_goods_rule_mytoolbar" style="height:auto"></div>
<script type="text/javascript">
	var COD_GOODS_RULR = COD_GOODS_RULR || {};
	COD_GOODS_RULR.queryUrl = managerPath+'/manager/system/CodRuleMgr.json?m=query&f=queryCodGoodsRule';
	COD_GOODS_RULR.params = new Object();
	COD_GOODS_RULR.setDatagridQueryParams=function(){
		var queryParams = $('#cod_goods_rule_datagrid').datagrid('options').queryParams;  
		queryParams.areaCode = $("#cod_goods_rule_areaCode").combobox("getValue");
		queryParams.goodsType = $("#cod_goods_rule_goodsType").combobox("getValue");
	} 
	COD_GOODS_RULR.searchGoodsRule = function(){
		COD_GOODS_RULR.setDatagridQueryParams();
		$("#cod_goods_rule_datagrid").datagrid("reload");
	}
	COD_GOODS_RULR.getRowIndex=function(target){
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	COD_GOODS_RULR.editrow=function(target){
		$('#cod_goods_rule_datagrid').datagrid('selectRow',COD_GOODS_RULR.getRowIndex(target));
		$('#cod_goods_rule_datagrid').datagrid('beginEdit', COD_GOODS_RULR.getRowIndex(target));
	}

	COD_GOODS_RULR.insert=function(){
		var row = $('#cod_goods_rule_datagrid').datagrid('getSelected');
		var index=$('#cod_goods_rule_datagrid').datagrid('getData').rows.length;
		if (row){
			if(row.editing){
				$.messager.alert('操作提示','请先保存后,再新建模板');
				return;
			}
			var index = $('#cod_goods_rule_datagrid').datagrid('getRowIndex', row);
		} else {
			index = 0;
		}
		$('#cod_goods_rule_datagrid').datagrid('insertRow', {
			index: 0,
			row:{
				
			}
		});
		$('#cod_goods_rule_datagrid').datagrid('selectRow',0);
		$('#cod_goods_rule_datagrid').datagrid('beginEdit',0);
	}

	COD_GOODS_RULR.deleterow = function(target){
	    var choosen_rows = $('#cod_goods_rule_datagrid').datagrid('getSelections').length;
		if(choosen_rows==0){
			var dlg=$.messager.alert({title: "操作提示", msg: "请选择您要删除的记录！"});
			setTimeout(function(){
			    dlg.window('close');
			}, 5000);
			return;
		}
		//$('#cppz-datagrid').datagrid('deleteRow', COD_GOODS_RULR.getRowIndex(target));
		$.messager.confirm('操作提示','确定后数据不可恢复,确认删除吗?',function(confirm_status){
			if (confirm_status){
				var row = $('#cod_goods_rule_datagrid').datagrid('getSelected');
				var clientRequest = new Tcsp.ClientRequest();
				var params = new Object();
				params.id = row.id;
				var url = managerPath + "/manager/system/CodRuleMgr.json?m=execute&f=codGoodsRuleRemove";
				clientRequest.postJSON(url,params,function(respData){
					  if(respData.result){
						  if(respData.result.success&&respData.result.errorCode=='00000'){
							  //$('#cppz-datagrid').datagrid('deleteRow', COD_GOODS_RULR.datagrid('getSelected'));
							  $('#cod_goods_rule_datagrid').datagrid('reload');
						  }else{
							  $.messager.alert("操作提示",respData.result.errorMsg);
						  }
					  }
				});
			}
		});

	}

	COD_GOODS_RULR.saverow=function(target){
		var index=COD_GOODS_RULR.getRowIndex(target);
		$('#cod_goods_rule_datagrid').datagrid('endEdit',index);
		$('#cod_goods_rule_datagrid').datagrid('selectRow',index);
		var row = $('#cod_goods_rule_datagrid').datagrid('getSelected');
		if (row){
			var clientRequest = new Tcsp.ClientRequest();
			var params = new Object();
			
			params.id = row.id;
			params.cityId =  row.cityId;
			params.goodsType =  row.goodsType;
			params.remark =  row.remark;
			var action='Save';
			if(params.id){
				action='Update';
			}else{
				action='Save';
			}
			var url = managerPath + "/manager/system/CodRuleMgr.json?m=execute&f=codGoodsRule"+action;
			
			clientRequest.postJSON(url,params,function(respData){
				  if(respData.result){
					  if(respData.result.success&&respData.result.errorCode=='00000'){
						  $('#cod_goods_rule_datagrid').datagrid('reload');
					  }else{
						  $.messager.alert("操作提示",respData.result.errorMsg);
					  }
				  }
			});
		}
	}

	COD_GOODS_RULR.cancelrow=function(target){
		$('#cod_goods_rule_datagrid').datagrid('cancelEdit', COD_GOODS_RULR.getRowIndex(target));
	}

	COD_GOODS_RULR.updateActions=function(index){
		$('#cod_goods_rule_datagrid').datagrid('updateRow',{
			index: index,
			row:{}
		});
	}
	COD_GOODS_RULR.areaCodeAll;
	COD_GOODS_RULR.isProAdmin = $('#cod_goods_rule_isProAdmin').val();
	if(COD_GOODS_RULR.isProAdmin == 'yes'){
		COD_GOODS_RULR.areaCodeAll = [
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
	}else{
		var code = $('#cod_goods_rule_code').val();
		var name = $('#cod_goods_rule_name').val();
		COD_GOODS_RULR.areaCodeAll = [
										{ "value": code, "text": name }
		                            ];
	}
	COD_GOODS_RULR.goodsType = [
	               { "value": "GT00", "text": "4G全国套餐" }, 
	               { "value": "GT03", "text": "4G校园套餐" }, 
	               { "value": "GT04", "text": "4G本地套餐" },
	               { "value": "GT05", "text": "单宽带" }, 
	               { "value": "GT06", "text": "智慧沃家" }, 
	               { "value": "GT07", "text": "3G存费送费" },
	               { "value": "GT08", "text": "上网卡" }, 
	               { "value": "GT09", "text": "青岛38元沃派" }, 
	               { "value": "GT10", "text": "4g存费送费（全国）" },
	               { "value": "GT11", "text": "4g存费送费（校园）" }, 
	               { "value": "GT12", "text": "4g存费送费（本地）" }, 
	               { "value": "GT13", "text": "4G流量王" },
	               { "value": "GT14", "text": "4G9分卡" },
	               { "value": "GT15", "text": "4G主副卡" }, 
	               { "value": "GT01", "text": "融合类商品" }, 
	               { "value": "GT02", "text": "裸机" },
	              ];
	 

	COD_GOODS_RULR.unitformatter = function(value, rowData, rowIndex) {
	    for (var i = 0; i < COD_GOODS_RULR.goodsType.length; i++) {
	        if (COD_GOODS_RULR.goodsType[i].value == value) {
	            return COD_GOODS_RULR.goodsType[i].text;
	        }
	    }
	}
	
	COD_GOODS_RULR.unitAreaformatter = function(value, rowData, rowIndex) {
	    for (var i = 0; i < COD_GOODS_RULR.areaCodeAll.length; i++) {
	        if (COD_GOODS_RULR.areaCodeAll[i].value == value) {
	            return COD_GOODS_RULR.areaCodeAll[i].text;
	        }
	    }
	}
	COD_GOODS_RULR.grid = $('#cod_goods_rule_datagrid').datagrid({
		queryParams:COD_GOODS_RULR.setDatagridQueryParams,
		url :managerPath+'/manager/system/CodRuleMgr.json?m=query&f=queryCodGoodsRule',
		iconCls:'icon-edit',
	    height: 'auto',  
	    nowrap: false,  
	    striped: true,  
	    border: true,  
	    collapsible:false,//是否可折叠的  
	    fitColumns: false,//设置为 true，则会自动扩大或缩小列的尺寸以适应网格的宽度并且防止水平滚动。
	    loadMsg:'数据加载中...',
	    remoteSort:false,   
	    idField:'id',  
	    singleSelect:true,//是否单选  
	    pagination:true,//分页控件  
	    rownumbers:true,//行号   
        height: 'auto',
        width:'100%',
        fitColumns:true,
        height:350,
	    frozenColumns:[[]],
	    queryParams : COD_GOODS_RULR.params,
	    columns: [[
		    		{field: 'ck',checkbox:true},
		    		{title: '地市',field: 'cityId',width:60,sortable: true,
		    			formatter: COD_GOODS_RULR.unitAreaformatter, 
		    			align: 'center', 
		    			editor: { 
		    				type: 'combobox', 
		    				options: { 
				    					data: COD_GOODS_RULR.areaCodeAll, 
				    					valueField: "value", 
				    					textField: "text" 
		    						} 
		    					}
		    		},
		    		{title: '商品类型',field: 'goodsType',width:150,sortable:false,
		    			formatter: COD_GOODS_RULR.unitformatter, 
		    			align: 'center', 
		    			editor: { 
		    				type: 'combobox', 
		    				options: { 
				    					data: COD_GOODS_RULR.goodsType, 
				    					valueField: "value", 
				    					textField: "text" 
		    						} 
		    					}
		    		},
		        	{title: '备注', field: 'remark', width:120,sortable: false,align:'center',editor:'text'},
		        	{title: '创建人', field: 'createStaffId', width:80,sortable: false,align:'center'},
		        	{title: '创建时间', field: 'createTime', width:200,sortable: false,align:'center'},
		        	{title: '更新人', field: 'updateStaffId', width:80,sortable: false,align:'center'},
		        	{title: '更新时间', field: 'updateTime', width:200,sortable: false,align:'center'},
		        	{title: '操作',field:'查看',sortable: false,align:'center',width:100,
		                formatter:function(value,row,index){
		                	if (row.editing){
								var s = '<a href="#" onclick="COD_GOODS_RULR.saverow(this)">保存</a> ';
								var c = '<a href="#" onclick="COD_GOODS_RULR.cancelrow(this)">取消</a>';
								return s+c;
							} else {
								var e = '<a href="#" onclick="COD_GOODS_RULR.editrow(this)">编辑</a> ';
								var d = '<a href="#" onclick="COD_GOODS_RULR.deleterow(this)">删除</a>';d='';
								//return e+d+a;
								return e+d;
							}
		               }
		            }
		    	]],
    	onBeforeEdit:function(index,row){
			row.editing = true;
			COD_GOODS_RULR.updateActions(index);
		},
		onAfterEdit:function(index,row){
			row.editing = false;
			COD_GOODS_RULR.updateActions(index);
		},
		onCancelEdit:function(index,row){
			row.editing = false;
			COD_GOODS_RULR.updateActions(index);
		},
		pagination:true, //这里添加分页控件 
		//解析返回的json中的Rows
		loadFilter: function(data){
			var value = {
		   		      "rows" : data.result.dataSet.rows,  //行数据 
		   		      "total" : data.result.dataSet.records   //总记录数
		   		    }
    		return value;
   		},
   		toolbar: [{
	        text: '新增',
	        iconCls: 'icon-add',
	        handler: COD_GOODS_RULR.insert
	    },'-',{
	        text: '删除',
	        iconCls: 'icon-remove',
	        handler: COD_GOODS_RULR.deleterow
	    }],
	    pageNumber: 1,
	    pageList: [10,20,30,40,50],
	    pageSize: 10
	});
	
</script>
