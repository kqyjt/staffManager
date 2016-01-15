<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>

<div class="chaxun_cont">

 <ul>
  
  <li><span>订单编码：</span><input  type="text" id="tomain_orderSn" name="orderSn" value="${param.orderSn }" class="easyui-textbox" ></li>
  
  <li><span>订单渠道：</span>
	  <select class="easyui-combobox" id="tomain_orderChannel" name="orderChannel">
	  		<option value="">请选择</option>
			<option value="CT00">自然人</option>
			<option value="CT01">能人</option>
		</select>
  </li>
  
  <li><span>订单来源：</span>
	  <select class="easyui-combobox" id="tomain_orderSource" name="orderSource">
	  		<option value="">请选择</option>
			<option value="web">PC端</option>
			<option value="android">手机APP：android</option>
			<option value="ios">手机APP：IOS</option>
			<option value="wechat">手机：微信</option>
			<option value="wap">手机：网页</option>
		</select>
  </li>
 </ul>
 
 <div class="clear"></div>
 <div class="butt_cont"><a href="#" onclick="TOMAIN_PUBLIC.searchOrder()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a></div>
</div>

<div style="margin:10px;">
	<table id="ddcx-datagrid"></table>

</div>

<script type="text/javascript">
	var TOMAIN_PUBLIC = TOMAIN_PUBLIC || {};
	TOMAIN_PUBLIC.queryUrl = managerPath + "/manager/controller/TOMainMgr.json?m=execute&f=mainQuery";
	TOMAIN_PUBLIC.params = new Object();


	TOMAIN_PUBLIC.getpagedata = function()
{
	var clientRequest = new Tcsp.ClientRequest();
	
	clientRequest.postJSON(TOMAIN_PUBLIC.queryUrl,TOMAIN_PUBLIC.params,function(data) {
		//处理数据，重画数据区域
		$("#ddcx-datagrid").datagrid("loadData", {total : data.result.dataSet.records, rows : data.result.dataSet.list});
		
		$("#ddcx-datagrid").datagrid("getPager").pagination({
			onSelectPage:function(pageNumber,pageSize){
				TOMAIN_PUBLIC.params.page = (pageNumber - 1) * pageSize;
				TOMAIN_PUBLIC.params.size = pageSize;
	     		
	     		 /*  clientRequest.postJSON(TOMAIN_PUBLIC.queryUrl,TOMAIN_PUBLIC.params,function(data){
				$("#ddcx-datagrid").datagrid("loadData", {total : data.result.dataSet.records, rows : data.result.dataSet.list}); 
	   		    });  */
	    		    $.getJSON(TOMAIN_PUBLIC.queryUrl,TOMAIN_PUBLIC.params, function (data){
		         	$("#ddcx-datagrid").datagrid('loadData', {total : data.result.dataSet.records, rows : data.result.dataSet.list});
				}
				)
       	}  
	});
	},function(errresponse) {
		alter("没有数据");
	});
	
}   

TOMAIN_PUBLIC.searchOrder = function(){
	TOMAIN_PUBLIC.params.orderSn = $("#tomain_orderSn").val();
	TOMAIN_PUBLIC.params.orderChannel = $("#tomain_orderChannel").combobox('getValue');
	TOMAIN_PUBLIC.params.orderSource = $("#tomain_orderSource").combobox('getValue');
	
	var clientRequest = new Tcsp.ClientRequest();
	clientRequest.postJSON(TOMAIN_PUBLIC.queryUrl,TOMAIN_PUBLIC.params,function(data){
		$("#ddcx-datagrid").datagrid("loadData", {total : data.result.dataSet.records, rows : data.result.dataSet.list});
	});
	
}


TOMAIN_PUBLIC.updateActions=function(index){
	$('#ddcx-datagrid').datagrid('updateRow',{
		index: index,
		row:{}
	});
}

		
		TOMAIN_PUBLIC.grid=$('#ddcx-datagrid').datagrid({
			
		    iconCls:'icon-edit',
		    //width:'auto',
		    height: 'auto',  
		    nowrap: false,  
		    striped: true,  
		    border: true,  
		    collapsible:false,//是否可折叠的  
		    fitColumns: false,//自动大小  
		    url: managerPath+'/manager/controller/TOMainMgr.json?m=execute&f=mainQuery',
		    //sortName: 'ID',  
		    //sortOrder: 'asc', 
		    loadMsg:'数据加载中...',
		    remoteSort:false,   
		    idField:'id',  
		    singleSelect:true,//只允许选择一行  
		    pagination:true,//分页控件  
		    rownumbers:true,//显示行号  
		    frozenColumns:[[]],
		    columns: [[
		    		{field: 'ck',checkbox:true},
		    		{title: '唯一标识',field: 'id',width:80,sortable:false,align:'center'},
		    		{title: '订单编码',field: 'orderSn',width:80,sortable: true,align:'left',editor:'text'},
		    		{title: '订单渠道',field: 'orderChannel',width:80,sortable: true,align:'left',editor:'text',
		    			formatter:function(value,row,index){
		                	if (value=='CT00'){
								return '自然人';
							}else if (value=='CT01'){
								return '能人';
							}
		               }
		    		},
		    		{title: '订单来源',field: 'orderSource',width:100,sortable:false,align:'center',editor:'text',
		    			formatter:function(value,row,index){
		                	if (value=='web'){
								return 'PC端';
							}else if (value=='android'){
								return '手机APP：android';
							}else if (value=='ios'){
								return '手机APP：IOS';
							}else if (value=='wechat'){
								return '手机：微信';
							}else if (value=='wap'){
								return '手机：网页';
							}
		               }
		    		},
					{title: '来源标识', field: 'orderSourceId',width:60,sortable: false,align:'center',editor:'text'},
		        	{title: '下单客户', field: 'custId', width:80,sortable: false,align:'center',editor:'text'},
		        	{title: '归属地市', field: 'custArea', width:100,sortable: false,align:'center',editor:'text',
		        		formatter:function(value,row,index){
		                	if (value=='001'){
								return '淄博';
							} else if (value=='002'){
								return '滨州';
							}else if (value=='003'){
								return '临沂';
							}else if (value=='004'){
								return '日照';
							}else if (value=='005'){
								return '潍坊';
							}else if (value=='006'){
								return '东营';
							}else if (value=='007'){
								return '枣庄';
							}else if (value=='008'){
								return '济宁';
							}else if (value=='009'){
								return '菏泽';
							}else if (value=='010'){
								return '莱芜';
							}else if (value=='011'){
								return '烟台';
							}else if (value=='012'){
								return '青岛';
							}else if (value=='013'){
								return '济南';
							}else if (value=='014'){
								return '泰安';
							}else if (value=='015'){
								return '德州';
							}else if (value=='016'){
								return '聊城';
							}else if (value=='017'){
								return '威海';
							}else if (value=='000'){
								return '山东';
							}
		               }
		        	},
		        	{title: '订单状态', field: 'state', width:120,sortable: false,align:'center',editor:'text',
		        		formatter:function(value,row,index){
		                	if (value=='OM01'){
								return '待审核';
							}
		               }
		        	},
		        	{title: '订单费用', field: 'orderFee', width:80,sortable: false,align:'center',editor:'text'},
		        	{title: '物流费用', field: 'mailFee', width:80,sortable: false,align:'center',editor:'text'},
		        	{title: '应收费用', field: 'costs', width:100,sortable: false,align:'center',editor:'text'},
		        	{title: '实收费用', field: 'paid', width:80,sortable: false,align:'center',editor:'text'},
		        	{title: '生成时间', field: 'createTime', width:80,sortable: false,align:'center',editor:'datetimebox'},
		        	{title: '失效时间', field: 'invalidTime', width:100,sortable: false,align:'center',editor:'datetimebox'},
		        	{title: '更新员工', field: 'updateStaffId', width:60,sortable: false,align:'center',editor:'text'},
		        	{title: '更新时间', field: 'updateTime', width:120,sortable: false,align:'center',editor:'datetimebox'},
		        	{title: '备注', field: 'remark', width:120,sortable: false,align:'center',editor:'text'}
		        	
		        	
		    	]],
		    	onBeforeEdit:function(index,row){
					row.editing = true;
					TOMAIN_PUBLIC.updateActions(index);
				},
				onAfterEdit:function(index,row){
					row.editing = false;
					TOMAIN_PUBLIC.updateActions(index);
				},
				onCancelEdit:function(index,row){
					row.editing = false;
					TOMAIN_PUBLIC.updateActions(index);
				},
		    loadFilter: function(data){
		    		if (data.result){
		    			return data.result.dataSet;
		    		} else {
		    			return data;
		    		}
		    },
		    pageNumber: 1,
		    pageList: [10,20,30,40,50],
		    pageSize: 10
		
		});
	
 		TOMAIN_PUBLIC.getpagedata();
	
</script>

