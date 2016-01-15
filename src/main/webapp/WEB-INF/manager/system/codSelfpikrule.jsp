<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>
<input id = "cod_self_pick_rule_code" value="${result.dataSet.code }" type="hidden"/>
<input id = "cod_self_pick_rule_isProAdmin" value="${result.dataSet.isProAdmin }" type="hidden"/>
<input id = "cod_self_pick_rule_name" value="${result.dataSet.name }" type="hidden"/>
<!-- 查询条件 -->
<div class="chaxun_cont">
 <ul>
 	<li><span>地市：</span>
 		<select id = "cod_self_pick_rule_areaCode" name="cod_self_pick_rule_areaCode" class="easyui-combobox" style="width:129px;" data-options="editable:false"> 
	   		<c:forEach items="${result.dataSet.areaCode }" var="area">
	  			<option value="${area.code }">${area.name }</option>
	  		</c:forEach> 
		</select>
 	</li>
  	<li><span>状态：</span>
  		<select id = "cod_self_pick_rule_isOpen" name="cod_self_pick_rule_isOpen" class="easyui-combobox" style="width:129px;" data-options="editable:false"> 
	  		<option value="*">全部</option>
	  		<option value="1">开通</option>
	  		<option value="0">关闭</option>
		</select>
  	</li>
 </ul>
 <div class="butt_cont"><a href="#" onclick="COD_SELF_PIC_RULR.searchSelfPickRule()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a></div>
</div>
<!-- 表格 -->
<div style="margin:10px;">
	<table id="codGoodsrule-datagrid"></table>
</div>
<!-- 工具栏 -->
<div id="codGoodsrule-mytoolbar" style="height:auto"></div>
<script type="text/javascript">
	var COD_SELF_PIC_RULR = COD_SELF_PIC_RULR || {};
	COD_SELF_PIC_RULR.queryUrl = managerPath+'/manager/system/CodRuleMgr.json?m=query&f=queryCodSelfPickRule';
	COD_SELF_PIC_RULR.params = new Object();
	COD_SELF_PIC_RULR.setDatagridQueryParams=function(){
		var queryParams = $('#codGoodsrule-datagrid').datagrid('options').queryParams;  
		queryParams.areaCode = $("#cod_self_pick_rule_areaCode").combobox("getValue");
		queryParams.isOpen = $("#cod_self_pick_rule_isOpen").combobox("getValue");
	} 

	COD_SELF_PIC_RULR.searchSelfPickRule = function(){
		COD_SELF_PIC_RULR.setDatagridQueryParams();
		$("#codGoodsrule-datagrid").datagrid("reload");
	}
	COD_SELF_PIC_RULR.getRowIndex=function(target){
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr('datagrid-row-index'));
	}
	COD_SELF_PIC_RULR.editrow=function(target){
		$('#codGoodsrule-datagrid').datagrid('selectRow',COD_SELF_PIC_RULR.getRowIndex(target));
		$('#codGoodsrule-datagrid').datagrid('beginEdit', COD_SELF_PIC_RULR.getRowIndex(target));
	}
	

	COD_SELF_PIC_RULR.saverow=function(target){
		var index=COD_SELF_PIC_RULR.getRowIndex(target);
		$('#codGoodsrule-datagrid').datagrid('endEdit',index);
		$('#codGoodsrule-datagrid').datagrid('selectRow',index);
		var row = $('#codGoodsrule-datagrid').datagrid('getSelected');
		if (row){
			var clientRequest = new Tcsp.ClientRequest();
			var params = new Object();
			
			params.id = row.id;
			params.cityId =  row.cityId;
			params.isOpen =  row.isOpen;
			params.remark =  row.remark;
			var action='Save';
			if(params.id){
				action='Update';
			}else{
				action='Save';
			}
			var url = managerPath + "/manager/system/CodRuleMgr.json?m=execute&f=codSelfPickRule"+action;
			
			clientRequest.postJSON(url,params,function(respData){
				  if(respData.result){
					  if(respData.result.success&&respData.result.errorCode=='00000'){
						  $('#codGoodsrule-datagrid').datagrid('reload');
					  }else{
						  $.messager.alert("操作提示",respData.result.errorMsg);
					  }
				  }
			});
		}
	}

	COD_SELF_PIC_RULR.cancelrow=function(target){
		$('#codGoodsrule-datagrid').datagrid('cancelEdit', COD_SELF_PIC_RULR.getRowIndex(target));
	}

	COD_SELF_PIC_RULR.updateActions=function(index){
		$('#codGoodsrule-datagrid').datagrid('updateRow',{
			index: index,
			row:{}
		});
	}
	COD_SELF_PIC_RULR.areaCodeAll;
	COD_SELF_PIC_RULR.isProAdmin = $('#cod_self_pick_rule_isProAdmin').val();
	if(COD_SELF_PIC_RULR.isProAdmin == 'yes'){
		COD_SELF_PIC_RULR.areaCodeAll = [
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
		var code = $('#cod_self_pick_rule_code').val();
		var name = $('#cod_self_pick_rule_name').val();
		COD_SELF_PIC_RULR.areaCodeAll = [
										{ "value": code, "text": name }
		                            ];
	}
	COD_SELF_PIC_RULR.isOpen = [
	               { "value": "0", "text": "关闭" }, 
	               { "value": "1", "text": "开通" }
	              ];
	 

	COD_SELF_PIC_RULR.unitformatter = function(value, rowData, rowIndex) {
	    for (var i = 0; i < COD_SELF_PIC_RULR.isOpen.length; i++) {
	        if (COD_SELF_PIC_RULR.isOpen[i].value == value) {
	            return COD_SELF_PIC_RULR.isOpen[i].text;
	        }
	    }
	}
	
	COD_SELF_PIC_RULR.unitAreaformatter = function(value, rowData, rowIndex) {
	    for (var i = 0; i < COD_SELF_PIC_RULR.areaCodeAll.length; i++) {
	        if (COD_SELF_PIC_RULR.areaCodeAll[i].value == value) {
	            return COD_SELF_PIC_RULR.areaCodeAll[i].text;
	        }
	    }
	}
	COD_SELF_PIC_RULR.grid = $('#codGoodsrule-datagrid').datagrid({
		queryParams:COD_SELF_PIC_RULR.setDatagridQueryParams,
		url :managerPath+'/manager/system/CodRuleMgr.json?m=query&f=queryCodSelfPickRule',
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
	    frozenColumns:[[]],
	    queryParams : COD_SELF_PIC_RULR.params,
	    columns: [[
		    		{field: 'ck',checkbox:true},
		    		/* {title: '唯一标识',field: 'id',width:80,sortable:false,align:'center'}, */
		    		{title: '地市',field: 'cityId',width:60,sortable: true,
		    			formatter: COD_SELF_PIC_RULR.unitAreaformatter, 
		    			align: 'center'
		    		},
		    		/* {title: '地市名称', field: 'cityName', width:100,sortable: false,align:'center',editor:'text'}, */
		    		{title: '状态',field: 'isOpen',width:60,sortable:false,
		    			formatter: COD_SELF_PIC_RULR.unitformatter, 
		    			align: 'center', 
		    			editor: { 
		    				type: 'combobox', 
		    				options: { 
				    					data: COD_SELF_PIC_RULR.isOpen, 
				    					valueField: "value", 
				    					textField: "text" 
		    						} 
		    					},
    					formatter:function(val,rec){
    						if (val == 0){
    							return '<span style="color:red;">关闭</span>';
    						} else {
    							return '开通';
    						}
    					} 
		    		},
		        	{title: '备注', field: 'remark', width:120,sortable: false,align:'center',editor:'text'},
		        	{title: '创建人', field: 'createStaff', width:120,sortable: false,align:'center'},
		        	{title: '创建时间', field: 'createTime', width:200,sortable: false,align:'center'},
		        	{title: '更新人', field: 'updateStaff', width:100,sortable: false,align:'center'},
		        	{title: '更新时间', field: 'updateTime', width:200,sortable: false,align:'center'},
		        	{title: '操作',field:'查看',sortable: false,align:'center',width:100,
		                formatter:function(value,row,index){
		                	if (row.editing){
								var s = '<a href="#" onclick="COD_SELF_PIC_RULR.saverow(this)">保存</a> ';
								var c = '<a href="#" onclick="COD_SELF_PIC_RULR.cancelrow(this)">取消</a>';
								return s+c;
							} else {
								var e = '<a href="#" onclick="COD_SELF_PIC_RULR.editrow(this)">编辑</a> ';
								var d = '<a href="#" onclick="COD_SELF_PIC_RULR.deleterow(this)">删除</a>';d='';
								//return e+d+a;
								return e+d;
							}
		               }
		            }
		    	]],
    	onBeforeEdit:function(index,row){
			row.editing = true;
			COD_SELF_PIC_RULR.updateActions(index);
		},
		onAfterEdit:function(index,row){
			row.editing = false;
			COD_SELF_PIC_RULR.updateActions(index);
		},
		onCancelEdit:function(index,row){
			row.editing = false;
			COD_SELF_PIC_RULR.updateActions(index);
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
		detailFormatter:function(index,row){
			 return '<div style="padding:2px"><table class="ddv"></table></div>';
        },
	    pageNumber: 1,
	    pageList: [10,20,30,40,50],
	    pageSize: 20
	});
	
</script>
