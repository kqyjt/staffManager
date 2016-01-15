<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>

<div class="chaxun_cont">

 <ul>
  
  <li><span>订单号：</span><input  type="text" id="toreturns_orderSn" name="orderSn" value="${param.orderId }" class="easyui-textbox" ></li>
  
  <li><span>订单类型：</span>
	  <select class="easyui-combobox" id="toreturns_goodsType" name="goodsType">
	  		<option value="">请选择</option>
			<option value="GT00">4G全国套餐</option>
			<option value="GT03">4G校园套餐</option>
			<option value="GT04">4G本地套餐</option>
			<option value="GT05">单宽带</option>
			<option value="GT06">智慧沃家</option>
			<option value="GT01">融合类商品</option>
			<option value="GT02">裸机</option>
		</select>
  </li>
  
  <li><span>配送方式：</span>
	  <select class="easyui-combobox" id="toreturns_deliverType" name="deliverType">
	  		<option value="">请选择</option>
			<option value="DT00">顺丰</option>
			<option value="DT01">EMS</option>
			<option value="DT02">自提</option>
		</select>
  </li>
  
 </ul>
 
 <div class="clear"></div>
 <div class="butt_cont"><a href="#" onclick="TORETURNS_PUBLIC.searchReturns()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a></div>
</div>

<div style="margin:10px;">
	<table id="thcz-datagrid"></table>

</div>

<script type="text/javascript">
	var TORETURNS_PUBLIC = TORETURNS_PUBLIC || {};
	TORETURNS_PUBLIC.queryUrl = managerPath + "/manager/controller/TOMainMgr.json?m=execute&f=returnsQuery";
	TORETURNS_PUBLIC.params = new Object();


	TORETURNS_PUBLIC.getpagedata = function()
{
	var clientRequest = new Tcsp.ClientRequest();
	
	clientRequest.postJSON(TORETURNS_PUBLIC.queryUrl,TORETURNS_PUBLIC.params,function(data) {
		//处理数据，重画数据区域
		$("#thcz-datagrid").datagrid("loadData", {total : data.result.dataSet.records, rows : data.result.dataSet.rows});
		
		$("#thcz-datagrid").datagrid("getPager").pagination({
			onSelectPage:function(pageNumber,pageSize){
				TORETURNS_PUBLIC.params.page = (pageNumber - 1) * pageSize;
				TORETURNS_PUBLIC.params.size = pageSize;
	     		
	     		 /*  clientRequest.postJSON(TORETURNS_PUBLIC.queryUrl,TORETURNS_PUBLIC.params,function(data){
				$("#thcz-datagrid").datagrid("loadData", {total : data.result.dataSet.records, rows : data.result.dataSet.list}); 
	   		    });  */
	    		    $.getJSON(TORETURNS_PUBLIC.queryUrl,TORETURNS_PUBLIC.params, function (data){
		         	$("#thcz-datagrid").datagrid('loadData', {total : data.result.dataSet.records, rows : data.result.dataSet.rows});
				}
				)
       	}  
	});
	},function(errresponse) {
		alter("没有数据");
	});
	
}   

TORETURNS_PUBLIC.searchReturns = function(){
	
	TORETURNS_PUBLIC.params.orderSn = $("#toreturns_orderSn").val();
	TORETURNS_PUBLIC.params.goodsType = $("#toreturns_goodsType").combobox('getValue');
	TORETURNS_PUBLIC.params.deliverType = $("#toreturns_deliverType").combobox('getValue');
	
	var clientRequest = new Tcsp.ClientRequest();
	clientRequest.postJSON(TORETURNS_PUBLIC.queryUrl,TORETURNS_PUBLIC.params,function(data){
		$("#thcz-datagrid").datagrid("loadData", {total : data.result.dataSet.records, rows : data.result.dataSet.list});
	});
	
}


TORETURNS_PUBLIC.updateActions=function(index){
	$('#thcz-datagrid').datagrid('updateRow',{
		index: index,
		row:{}
	});
}

//查看退款详情
TORETURNS_PUBLIC.getOrderDetail = function(){
	var selectRow = $("#thcz-datagrid").datagrid("getSelected");
	if (selectRow == "" || selectRow == undefined) {
		$.messager.alert('提示信息',"请先选择一行记录!",'info');
		return false;
	}
	var orderId = selectRow.id;
	var url = managerPath + "/manager/controller/TOMainMgr.htm?m=query&f=getOrderById&orderId="+orderId;
	OpenTab("center_tabs",'查看退款详情',url,'icon-munich-collaboration');
}


		TORETURNS_PUBLIC.grid=$('#thcz-datagrid').datagrid({
			
		    iconCls:'icon-edit',
		    //width:'auto',
		    height: 'auto',  
		    nowrap: false,  
		    striped: true,  
		    border: true,  
		    collapsible:false,//是否可折叠的  
		    fitColumns: false,//自动大小  
		    url: managerPath+'/manager/controller/TOMainMgr.json?m=execute&f=returnsQuery',
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
		    		{title: '订单号',field: 'orderSn',width:80,sortable: true,align:'left',editor:'text'},
		    		{title: '订单状态', field: 'state', width:120,sortable: false,align:'center',
						formatter:function(value,row,index){
		                	if (value=='OM00'){
								return '待支付';
							}else if(value=='OM01'){
								return '待审核';
							}else if(value=='OM02'){
								return '审核不通过';
							}else if(value=='OM03'){
								return '待分配/待领取';
							}else if(value=='OM04'){
								return '待开户';
							}else if(value=='OM05'){
								return '开户失败';
							}else if(value=='OM06'){
								return '待配送';
							}else if(value=='OM07'){
								return '待签收';
							}else if(value=='OM08'){
								return '已签收';
							}else if(value=='OM09'){
								return '拒绝签收';
							}else if(value=='OM10'){
								return '已取消';
							}else if(value=='OM11'){
								return '已失效';
							}else if(value=='OM12'){
								return '已退单';
							}else if(value=='OM13'){
								return '退回能人/自然人';
							}else if(value=='OM14'){
								return '待自提(配送方式为自提时)';
							}else if(value=='OM15'){
								return '归档成功';
							}else if(value=='OM16'){
								return '归档失败';
							}
		               }	
					},
					{title: '订单处理人', field: 'updateStaffId', width:120,sortable: false,align:'center',editor:'text'},
		    		{title: '订单类型',field: 'goodsType',width:80,sortable: true,align:'left',
						formatter:function(value,row,index){
		                	if (value=='GT00'){
								return '4G全国套餐';
							}else if(value=='GT03'){
								return '4G校园套餐';
							}else if(value=='GT04'){
								return '4G本地套餐';
							}else if(value=='GT05'){
								return '单宽带';
							}else if(value=='GT06'){
								return '智慧沃家';
							}else if(value=='GT01'){
								return '融合类商品';
							}else if(value=='GT02'){
								return '裸机';
							}
		               }
					},
		    		{title: '商品名称',field: 'goodsName',width:100,sortable:false,align:'center',editor:'text'},
					{title: '入网号码', field: 'phoneNumber',width:60,sortable: false,align:'center',editor:'text'},
		        	{title: '归属地', field: 'areaName', width:80,sortable: false,align:'center',editor:'text'},
		        	{title: '能人姓名', field: 'custName', width:100,sortable: false,align:'center',editor:'text'},
		        	{title: '能人账号', field: 'regName', width:100,sortable: false,align:'center',editor:'text'},
		        	{title: '能人电话', field: 'mobilePhone', width:120,sortable: false,align:'center',editor:'text'},
		        	{title: '配送方式', field: 'deliverType', width:80,sortable: false,align:'center',
						formatter:function(value,row,index){
		                	if (value=='DT00'){
								return '顺丰';
							}else if(value=='DT01'){
								return 'EMS';
							}else if(value=='DT02'){
								return '自提';
							}
		               }	
					},
		        	{title: '物流单号', field: 'mailNo', width:80,sortable: false,align:'center',editor:'text'},
		        	{title: '支付方式', field: 'payType', width:100,sortable: false,align:'center',
						formatter:function(value,row,index){
		                	if (value=='pay_on_delivery'){
								return '货到付款';
							}else if(value=='al'){
								return '支付宝在线支付';
							}else if(value=='ap'){
								return '支付宝手机支付';
							}else if(value=='aw'){
								return '支付宝手机网页支付';
							}else if(value=='ew'){
								return '联行手机支付';
							}else if(value=='el'){
								return '联行在线支付';
							}else if(value=='yl'){
								return '易宝网页支付';
							}else if(value=='yeePay'){
								return '易宝支付';
							}else if(value=='aliPay'){
								return '支付宝支付';
							}else if(value=='ewPay'){
								return '联行支付';
							}else if(value=='onlinepay'){
								return '在线支付';
							}else if(value=='mobilepay'){
								return '移动支付';
							}else if(value=='couponpay'){
								return '券卡支付';
							}else if(value=='cashpay'){
								return '现金支付';
							}
		               }	
					},
		        	{title: '支付金额', field: 'paid', width:80,sortable: false,align:'center',editor:'text'},
		    		{title: '增加预存款',field: 'prestoreFee',width:80,sortable: true,align:'left',editor:'text'},
		    		{title: '支付状态', field: 'payState', width:80,sortable: false,align:'center',
						formatter:function(value,row,index){
		                	if (value=='PI00'){
								return '待支付';
							}else if(value=='PI01'){
								return '支付成功';
							}else if(value=='PI02'){
								return '已退费';
							}else if(value=='PI03'){
								return '退费中';
							}
		               }	
					},
		        	{title: '支付时间', field: 'reqPaytime', width:120,sortable: false,align:'center',editor:'datetimebox'},
		        	{title: '支付订单号', field: 'paySn', width:120,sortable: false,align:'center',editor:'text'},
					{title: '订单创建日期', field: 'createTime', width:120,sortable: false,align:'center',editor:'datetimebox'},
					{title: '订单失效日期', field: 'invalidTime', width:120,sortable: false,align:'center',editor:'datetimebox'},
					{title: '订单备注', field: 'remark', width:120,sortable: false,align:'center',editor:'text'}
		        	
		    	]],
		    	onBeforeEdit:function(index,row){
					row.editing = true;
					TORETURNS_PUBLIC.updateActions(index);
				},
				onAfterEdit:function(index,row){
					row.editing = false;
					TORETURNS_PUBLIC.updateActions(index);
				},
				onCancelEdit:function(index,row){
					row.editing = false;
					TORETURNS_PUBLIC.updateActions(index);
				},
		    loadFilter: function(data){
		    		if (data.result){
		    			return data.result.dataSet;
		    		} else {
		    			return data;
		    		}
		    },
		    onClickRow: function(rowIndex, rowData){
		    	TORETURNS_PUBLIC.getOrderDetail();
		    },
		    pageNumber: 1,
		    pageList: [10,20,30,40,50],
		    pageSize: 10
		
		});
		
		
		 	TORETURNS_PUBLIC.getpagedata();
	
</script>

</body>
</html>