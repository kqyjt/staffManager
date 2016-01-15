<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>

<div class="chaxun_cont">

 <ul>
  
  <li><span>订单号：</span><input  type="text" id="tomain_orderSn" name="orderSn" value="${param.orderSn }" class="easyui-textbox" ></li>
  
  <li><span>订单类型：</span>
	  <select class="easyui-combobox" id="tomain_goodsType" name="goodsType">
	  		<option value="">请选择</option>
			<option value="GT00">4G全国套餐</option>
			<option value="GT03">4G校园套餐</option>
			<option value="GT04">4G本地套餐</option>
			<option value="GT17">4G合约惠机</option>
			<option value="GT05">单宽带</option>
			<option value="GT06">智慧沃家</option>
			<option value="GT01">融合类商品</option>
			<option value="GT02">裸机</option>
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
	TOMAIN_PUBLIC.queryUrl = managerPath + "/manager/controller/TOMainMgr.json?m=execute&f=cityQuery";
	TOMAIN_PUBLIC.params = new Object();
	TOMAIN_PUBLIC.setDatagridQueryParams=function(){
		var queryParams = $('#ddcx-datagrid').datagrid('options').queryParams;  
		queryParams.orderSn = $("#tomain_orderSn").val();
		queryParams.goodsType = $("#tomain_goodsType").combobox('getValue');
	} 
	//查询按钮
	TOMAIN_PUBLIC.searchOrder = function(){
		TOMAIN_PUBLIC.setDatagridQueryParams();
		$("#ddcx-datagrid").datagrid("reload");
	}
	//表格初始化
	TOMAIN_PUBLIC.grid=$('#ddcx-datagrid').datagrid({
		queryParams:TOMAIN_PUBLIC.setDatagridQueryParams,
	    iconCls:'icon-edit',
	    //width:'auto',
	    height: 'auto',  
	    nowrap: false,  
	    striped: true,  
	    border: true,  
	    collapsible:false,//是否可折叠的  
	    fitColumns: false,//自动大小  
	    url: managerPath+'/manager/controller/TOMainMgr.json?m=execute&f=cityQuery',
	    //sortName: 'ID',  
	    //sortOrder: 'asc', 
	    loadMsg:'数据加载中...',
	    remoteSort:false,   
	    idField:'id',  
	    singleSelect:true,//只允许选择一行  
	    pagination:true, //这里添加分页控件 
	    rownumbers:true,//显示行号  
	    frozenColumns:[[]],
	    columns: [[
					{field: 'ck',checkbox:true},
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
							}else if(value=='GT17'){
								return '4G合约惠机';
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
	    	]
	    ],
	    loadFilter: function(data){
	    	var value = {
   		      "rows" : data.result.dataSet.list,  //行数据 
   		      "total" : data.result.dataSet.records   //总记录数
		   	}
  			return value;
	    },
	    pageNumber: 1,
	    pageList: [10,20,30,40,50],
	    pageSize: 10
	});
</script>


